package com.qtz.sm.common.solr.impl;


import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.solr.SearchShopSolrService;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.search.vo.ShopSearch;
import com.qtz.sm.search.vo.SupeGoodsSearch;
import com.qtz.sm.supermarket.service.SupermarketBusinessService;
import com.qtz.sm.supermarket.vo.SupermarketBusiness;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.*;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

/**
 * Title:CsRegionsServiceImpl<br/>
 * Description:(搜索引擎类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 郭云龙 252735833@qq.com
 * @version v1.0 2016-06-03
 */

@Service("searchShopSolrServiceImpl")
public class SearchShopSolrServiceImpl implements SearchShopSolrService {


    private static final Logger logger = LoggerFactory.getLogger(SearchShopSolrServiceImpl.class);
//    public static final String SOLR_URL = "http://localhost:8983/solr/searchShop";

    public static final String SOLR_SHOP_URL = "http://192.168.0.54:8081/solr/seach_shop_core";


    public static final String SOLR_SUPE_GOODS_URL = "http://192.168.0.54:8081/solr/seach_supe_goods_core";

    public static final String SOLR_CCZX_URL = "http://192.168.0.54:8081/solr/seach_cczx_core";
    
    @Resource(name="supermarketBusinessServiceImpl")
    private SupermarketBusinessService supermarketBusinessService;
    
    @Override
    public Pager<ShopSearch, Long> searchShopList(SearchPage page)throws ServiceException{
        List<ShopSearch> vos = new ArrayList<ShopSearch>();

        Pager<ShopSearch, Long> flowPage = new Pager<ShopSearch, Long>();
        flowPage.setPageSize(page.getPageSize());
        flowPage.setNowPage(page.getNowPage());

        try {

            HttpSolrClient cli = new HttpSolrClient(SOLR_SHOP_URL);

            String condition="*:*";
			if(page != null){
				if(page.getKeyword() != null){
					condition = "category_name:"+page.getKeyword() + " OR " +" name:*"+page.getKeyword()+"*";
				}
			}
            System.out.println(condition);
            SolrQuery q = new SolrQuery();
            q.set("q", condition);
            q.set("pt",page.getLatitude()+","+page.getLongitude());
            q.set("fl","*,dist:geodist()");
            q.set("fq", "{!geofilt}");//距离过滤函数
            q.set("fq","disabled:false");//false表示启用 true表示禁用
            q.set("sfield", "lat_lon");//经纬度的字段
            q.set("d","10000000000000000000");
            q.set("sort", "geodist() asc");//根据距离排序
            q.set("start", (page.getNowPage()-1) * page.getPageSize());
            q.set("rows", page.getPageSize());
//            params.put("q", "*:*");
//            params.put("fq", "{!geofilt}");//距离过滤函数
//            params.put("pt", "31.26552,121.460815");//当前经纬度
//            params.put("sfield", "latlng");//经纬度的字段
//            params.put("d", "2");//就近2公里的所有酒店
//            params.put("sort", "geodist() asc");//根据距离排序
//            params.put("fl", "*,score");
//            params.put("start", "0");//记录开始位置
//            params.put("rows", "10");//查询的行数
//			q.set("sort", "crtime asc");
            QueryResponse query = cli.query(q);
            SolrDocumentList results = query.getResults();
            flowPage.setRowCount((int)results.getNumFound());
            for (SolrDocument doc : results) {
                System.out.println(doc);
                ShopSearch ss = new ShopSearch();
                ss.setDmId(doc.getFieldValue("dmId")==null?0:Long.parseLong(doc.getFieldValue("dmId").toString()));
                ss.setName(doc.getFieldValue("name")==null?"":doc.getFieldValue("name").toString());
                ss.setIcon(doc.getFieldValue("shop_business_icon")==null?"":doc.getFieldValue("shop_business_icon").toString());
                ss.setMinimumMoney(doc.getFieldValue("shop_business_minimum_money")==null?0:Double.parseDouble(doc.getFieldValue("shop_business_minimum_money").toString()));
                ss.setLogisticsMoney(doc.getFieldValue("shop_business_logistics_money")==null?0:Double.parseDouble(doc.getFieldValue("shop_business_logistics_money").toString()));
                ss.setScopes(doc.getFieldValue("category_name"));
                ss.setSendPrice("true".equals(doc.getFieldValue("shop_business_is_send_price"))?true:false);

                if(doc.getFieldValue("dist") !=null&&Double.parseDouble(doc.getFieldValue("dist").toString())<1){
                    ss.setDistance(new Double(Double.parseDouble(doc.getFieldValue("dist").toString())*1000).intValue()+"m");
                }else{
                    DecimalFormat df = new DecimalFormat("0.00");
                    ss.setDistance(df.format(doc.getFieldValue("dist"))+"km");
                }
                //判断服务范围
                if(doc.getFieldValue("dist") !=null&&doc.getFieldValue("shop_business_service_scope") !=null&&Double.parseDouble(doc.getFieldValue("shop_business_service_scope").toString())-Double.parseDouble(doc.getFieldValue("dist").toString())>0){
                    ss.setServiceScope(true);
                }else{
                    ss.setServiceScope(false);
                }

                //判断营业状态
                Date sysDate = new Date();
                Date startTime = (Date) doc.getFieldValue("shop_business_service_start_time");
                Date endTime = (Date) doc.getFieldValue("shop_business_service_end_time");
                SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
                int sysdateValue = Integer.parseInt(sdf.format(sysDate));
                int startTimeValue = 0;
                int endTimeValue = 2400;
                if(startTime == null){
                    startTimeValue = 2401;
                }else{
                    startTimeValue = Integer.parseInt(sdf.format(startTime));
                }
                if(endTime == null){
                    endTimeValue = -1;
                }else{
                    endTimeValue = Integer.parseInt(sdf.format(endTime));
                }

                if("false".equals(doc.getFieldValue("shopStatus")+"")
                        &&sysdateValue >= startTimeValue
                        &&sysdateValue <= endTimeValue){
                    ss.setOpened(true);
                }else{
                    ss.setOpened(false);
                }

                vos.add(ss);
            }

//            for (SolrDocument doc : results) {
//                Map<String,Object> map = new HashMap<String,Object>();
//                map.put("dmId",doc.getFieldValue("dmId"));
//                map.put("name",doc.getFieldValue("name"));
//                map.put("shop_business_icon",doc.getFieldValue("shop_business_icon"));
//                map.put("hop_business_minimum_money",doc.getFieldValue("hop_business_minimum_money"));
//                map.put("shop_business_logistics_money",doc.getFieldValue("shop_business_logistics_money"));
//                map.put("shop_business_is_send_price",doc.getFieldValue("shop_business_is_send_price"));
//                map.put("category_name",doc.getFieldValue("category_name"));
//                if(doc.getFieldValue("dist") !=null&&Double.parseDouble(doc.getFieldValue("dist").toString())<1){
//                    map.put("dist",new Double(Double.parseDouble(doc.getFieldValue("dist").toString())*1000).intValue()+"m");
//                }else{
//                    DecimalFormat df = new DecimalFormat("0.00");
//                    map.put("dist",df.format(doc.getFieldValue("dist"))+"km");
//                }
//                vos.add(map);
//            }
            flowPage.setList(vos);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            throw new ServiceException(e);
        }
        return flowPage;
    }


    @Override
    public Pager<ShopSearch, Long> searchIndexShopList(SearchPage page)throws ServiceException{
        List<ShopSearch> vos = new ArrayList<ShopSearch>();

        Pager<ShopSearch, Long> flowPage = new Pager<ShopSearch, Long>();
        flowPage.setPageSize(page.getPageSize());
        flowPage.setNowPage(page.getNowPage());

        try {

            HttpSolrClient cli = new HttpSolrClient(SOLR_SHOP_URL);

            String condition="*:*";
            if(page != null){
                if(page.getKeyword() != null){
                    condition = "category_name:"+page.getKeyword() ;
                }
            }
            System.out.println(condition);
            SolrQuery q = new SolrQuery();
            q.set("q", condition);
            q.set("pt",page.getLatitude()+","+page.getLongitude());
            q.set("fl","*,dist:geodist()");
            q.set("fq", "{!geofilt}");//距离过滤函数
            q.set("fq","disabled:false");//false表示启用 true表示禁用
            q.set("sfield", "lat_lon");//经纬度的字段
            q.set("d","10000000000000000000");
            q.set("sort", "geodist() asc");//根据距离排序
            q.set("start", (page.getNowPage()-1) * page.getPageSize());
            q.set("rows", page.getPageSize());

            QueryResponse query = cli.query(q);
            SolrDocumentList results = query.getResults();
            flowPage.setRowCount((int)results.getNumFound());
            for (SolrDocument doc : results) {
                System.out.println(doc);
                ShopSearch ss = new ShopSearch();
                ss.setDmId(doc.getFieldValue("dmId")==null?0:Long.parseLong(doc.getFieldValue("dmId").toString()));
                ss.setName(doc.getFieldValue("name")==null?"":doc.getFieldValue("name").toString());
                ss.setIcon(doc.getFieldValue("shop_business_icon")==null?"":doc.getFieldValue("shop_business_icon").toString());
                ss.setMinimumMoney(doc.getFieldValue("shop_business_minimum_money")==null?0:Double.parseDouble(doc.getFieldValue("shop_business_minimum_money").toString()));
                ss.setLogisticsMoney(doc.getFieldValue("shop_business_logistics_money")==null?0:Double.parseDouble(doc.getFieldValue("shop_business_logistics_money").toString()));
                ss.setScopes(doc.getFieldValue("category_name"));
                ss.setSendPrice("true".equals(doc.getFieldValue("shop_business_is_send_price"))?true:false);

                if(doc.getFieldValue("dist") !=null&&Double.parseDouble(doc.getFieldValue("dist").toString())<1){
                    ss.setDistance(new Double(Double.parseDouble(doc.getFieldValue("dist").toString())*1000).intValue()+"m");
                }else{
                    DecimalFormat df = new DecimalFormat("0.00");
                    ss.setDistance(df.format(doc.getFieldValue("dist"))+"km");
                }

                //判断服务范围
                if(doc.getFieldValue("dist") !=null&&doc.getFieldValue("shop_business_service_scope") !=null&&Double.parseDouble(doc.getFieldValue("shop_business_service_scope").toString())-Double.parseDouble(doc.getFieldValue("dist").toString())>0){
                    ss.setServiceScope(true);
                }else{
                    ss.setServiceScope(false);
                }

                //判断营业状态
                Date sysDate = new Date();
                Date startTime = (Date) doc.getFieldValue("shop_business_service_start_time");
                Date endTime = (Date) doc.getFieldValue("shop_business_service_end_time");
                SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
                int sysdateValue = Integer.parseInt(sdf.format(sysDate));
                int startTimeValue = 0;
                int endTimeValue = 2400;
                if(startTime == null){
                    startTimeValue = 2401;
                }else{
                    startTimeValue = Integer.parseInt(sdf.format(startTime));
                }
                if(endTime == null){
                    endTimeValue = -1;
                }else{
                    endTimeValue = Integer.parseInt(sdf.format(endTime));
                }
                if("false".equals(doc.getFieldValue("shopStatus"))
                        &&sysdateValue >= startTimeValue
                        &&sysdateValue <= endTimeValue){
                    ss.setOpened(true);
                }else{
                    ss.setOpened(false);
                }

                vos.add(ss);
            }

            flowPage.setList(vos);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            throw new ServiceException(e);
        }
        return flowPage;
    }


    @Override
    public Map<String,Object> searchCCZXInfo(SearchPage page)throws ServiceException {
        //第一步，建立连接 查询仓储中心
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            HttpSolrClient cli = new HttpSolrClient(SOLR_CCZX_URL);
            String con="{!func}add($v1)";
            SolrQuery q = new SolrQuery();
            q.setRows(1000000000);
            q.set("q",con);
            q.set("fq","cczx_status:0");
            q.set("v1","sub(distribution_radius,geodist(lat_lon,"+page.getLatitude()+","+page.getLongitude()+"))");
            q.set("fl","*,dist:geodist(lat_lon,"+page.getLatitude()+","+page.getLongitude()+"),sub:sub(distribution_radius,geodist(lat_lon,"+page.getLatitude()+","+page.getLongitude()+"))");
            q.set("sort", "geodist(lat_lon,"+page.getLatitude()+","+page.getLongitude()+") asc");//根据距离排序
            QueryResponse query = null;
            query = cli.query(q);
            SolrDocumentList res = query.getResults();
            for (SolrDocument doc : res) {
                System.out.println(doc);
                if(doc.getFieldValue("sub") !=null&&Double.parseDouble(doc.getFieldValue("sub").toString())>=0){
                    map.put("cczxID",doc.getFieldValue("dmId"));
                    String[] strs = doc.getFieldValue("lat_lon").toString().split(",");
                    String latitude = strs[0];
                    String longitude = strs[1];
                    map.put("latitude", latitude);
                    map.put("longitude", longitude);
                    map.put("distributionRadius", doc.getFieldValue("distribution_radius"));
                    //查询超市运营信息
                    List<SupermarketBusiness> list = supermarketBusinessService.findList(new SupermarketBusiness());
                    if(list != null && list.size()>0){
                    	 SupermarketBusiness business = list.get(0);
                    	 map.put("supermarketId", business.getSupermarketId());
                    	 map.put("serviceStartTime", business.getServiceStartTime());
                    	 map.put("serviceEndTime", business.getServiceEndTimeStr());
                    	 map.put("minimumMoney", business.getMinimumMoney());
                    	 map.put("servicePromise", business.getServicePromise());
                    }
                    break;
                }
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
            logger.error(e.toString());
            throw new ServiceException(e);
        }

        return map;
    }

    @Override
    public Pager<SupeGoodsSearch, Long> searchSupeGoodsList(SearchPage page)throws ServiceException{

        List<SupeGoodsSearch> vos = new ArrayList<SupeGoodsSearch>();

        Pager<SupeGoodsSearch, Long> flowPage = new Pager<SupeGoodsSearch, Long>();
        flowPage.setPageSize(page.getPageSize());
        flowPage.setNowPage(page.getNowPage());
        try {
            Object cczxID = this.searchCCZXInfo(page).get("cczxID");

            //按照仓储中心ID获取库存
            HttpSolrClient cli = new HttpSolrClient(SOLR_SUPE_GOODS_URL);

            System.out.println("cczxID======="+cczxID);

            String condition="cczx_id:"+cczxID;
            if(page != null){
                if(page.getKeyword() != null){
                    condition += " AND goods_name:*"+page.getKeyword()+"* ";
                }
            }
            System.out.println(condition);
            SolrQuery q = new SolrQuery();
            q.set("q", condition);
            q.set("fl","*");
            q.set("fq","goods_status:0");
            q.set("start", (page.getNowPage()-1) * page.getPageSize());
            q.set("rows", page.getPageSize());
            q.set("sort", "price asc");
//            q.set("group",true);
//            q.set("group.field","goods_id");
//
//            q.set("stats",true);
//            q.set("stats.field","price");
//            q.set("stats.facet","goods_id");

            QueryResponse query = cli.query(q);
//            NamedList<Object> ns = query.getResponse();

//            GroupResponse gr = query.getGroupResponse();
//            System.out.println(query);
//            List<GroupCommand> groupList = gr.getValues();
//            System.out.println(groupList);
////            flowPage.setRowCount(groups.size());
//            for(GroupCommand groupCommand : groupList) {
//                List<Group> groups = groupCommand.getValues();
//                flowPage.setRowCount(groupCommand.getMatches());
//                for(Group group : groups) {
//                    SupeGoodsSearch sgs = new SupeGoodsSearch();
////                    System.out.println(group.getGroupValue()+"|||||"+group.getResult());
//                    SolrDocumentList results = group.getResult();
//                    sgs.setNumFound(results.getNumFound());
//                    for (SolrDocument doc : results) {
//
//                        sgs.setDmId(doc.getFieldValue("dmId")==null?0:Long.parseLong(doc.getFieldValue("dmId").toString()));
//                        sgs.setGoods_id(doc.getFieldValue("goods_id")==null?0:Long.parseLong(doc.getFieldValue("goods_id").toString()));
//                        sgs.setGoods_sku_id(doc.getFieldValue("goods_sku_id")==null?0:Long.parseLong(doc.getFieldValue("goods_sku_id").toString()));
//                        sgs.setGoods_name(doc.getFieldValue("goods_name")==null?"":doc.getFieldValue("goods_name").toString());
//                        sgs.setPrice(doc.getFieldValue("price")==null?0:Double.parseDouble(doc.getFieldValue("price").toString()));
//                        sgs.setGoods_status(doc.getFieldValue("goods_status")==null?0:Integer.parseInt(doc.getFieldValue("goods_status").toString()));
//                        vos.add(sgs);
//                    }
//                    flowPage.setList(vos);
//                }
//            }

            SolrDocumentList results = query.getResults();
            flowPage.setRowCount((int)results.getNumFound());
            for (SolrDocument doc : results) {
                SupeGoodsSearch sgs = new SupeGoodsSearch();
                sgs.setDmId(cczxID==null?0:Long.parseLong(cczxID.toString()));
                sgs.setGoodsId(doc.getFieldValue("goods_id")==null?0:Long.parseLong(doc.getFieldValue("goods_id").toString()));
                sgs.setGoodsName(doc.getFieldValue("goods_name")==null?"":doc.getFieldValue("goods_name").toString());
                sgs.setPrice(doc.getFieldValue("price")==null?0:Double.parseDouble(doc.getFieldValue("price").toString()));
                sgs.setStatus(doc.getFieldValue("goods_status")==null?0:Integer.parseInt(doc.getFieldValue("goods_status").toString()));
                sgs.setSkuNum(doc.getFieldValue("sku_num")==null?0:Long.parseLong(doc.getFieldValue("sku_num").toString()));
                sgs.setPicURL(doc.getFieldValue("pic_url")==null?"":doc.getFieldValue("pic_url").toString());
                vos.add(sgs);
            }
            flowPage.setList(vos);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
            throw new ServiceException(e);
        }

        return flowPage;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    public Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
}
