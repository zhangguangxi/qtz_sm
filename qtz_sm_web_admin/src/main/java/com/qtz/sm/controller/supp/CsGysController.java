package com.qtz.sm.controller.supp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.core.common.Constants;
import com.mall.core.common.DateUtil;
import com.mall.core.common.MD5Util;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.DdmSession;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.service.GdGoodsBrandsService;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.service.GdGoodsSkuService;
import com.qtz.sm.goods.vo.GdGoodsBrands;
import com.qtz.sm.session.service.SessionService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.supp.page.CsGysStockPage;
import com.qtz.sm.supp.service.CsGysAddressService;
import com.qtz.sm.supp.service.CsGysDeliveryRegionService;
import com.qtz.sm.supp.service.CsGysInfoService;
import com.qtz.sm.supp.service.CsGysStaffService;
import com.qtz.sm.supp.service.CsGysStockService;
import com.qtz.sm.supp.vo.CsGysAddress;
import com.qtz.sm.supp.vo.CsGysDeliveryRegion;
import com.qtz.sm.supp.vo.CsGysInfo;
import com.qtz.sm.supp.vo.CsGysStaff;
import com.qtz.sm.supp.vo.CsGysStock;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Title:(供应商控制类)<br/>
 * Description:(供应商控制类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author GYL 郭云龙
 * @version v1.0 2016年5月13日
 */

@Api(value = "/csgys/", description = "供应商控制类")
@Controller
@RequestMapping(value = "/csgys/")
public class CsGysController extends BaseController {

    private static final LogTool log = LogTool.getInstance(CsGysController.class);

    @Resource(name = "sessionServiceImpl")
    private SessionService sessionService;

    @Resource(name = "csGysInfoServiceImpl")
    private CsGysInfoService csGysInfoService;

    @Resource(name = "csGysStaffServiceImpl")
    private CsGysStaffService csGysStaffService;
    
    @Resource(name = "csGysStockServiceImpl")
    private CsGysStockService csGysStockService;


    @Resource(name = "gdGoodsServiceImpl")
    private GdGoodsService gdGoodsService;

    @Resource(name = "gdGoodsSkuServiceImpl")
    private GdGoodsSkuService gdGoodsSkuService;
    

    @Autowired
    private CsGysAddressService csGysAddressService;
    
    @Autowired
    private CsRegionsService csRegionsService;
    
    @Autowired
    private CsGysDeliveryRegionService csGysDeliveryRegionService;
    

    
    @Autowired
    private  GdGoodsBrandsService gdGoodsBrandsService;
    /**
     * 供应商员工登录
     *
     * @param phone
     * @param pwd
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(@RequestBody Map<String,Object> maps, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CsGysStaff staff = new CsGysStaff();
            staff.setPhone( maps.get("name").toString());
            staff.setPwd(MD5Util.getMD5(maps.get("pwd").toString()));
            List<CsGysStaff> staffList = csGysStaffService.findList(staff);
            if (null != staffList && staffList.size() == 1) {
                staff = staffList.get(0);
                if (staff.getStatus().intValue() != 0) {
                    RespJsonPHandler.respError(RespMsg.user_seal_number, response, request);
                    return;
                } else {
                    Long gysId = staff.getGysId();
                    CsGysInfo gysInfo = csGysInfoService.findVo(gysId, null);
                    User user = buildLoginUser(staff, gysInfo);
                    //向session中保存用户
                    DdmSession ddmSession = sessionService.newAppSession();
                    ddmSession.save(Constants.SESSION_USER, user);
                    sessionService.saveAppSession(ddmSession);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("token", ddmSession.getId());
                    user.setPwd(null);
                    map.put("user", user);
                    RespHandler.respOK(map, response);
                    RespJsonPHandler.respOK(map, response, request);
                }
            } else {
                RespJsonPHandler.respError(RespMsg.user_no_existence, response, request);
            }
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }

    
    /**
     * 供应商员工登录构建登录用户
     *
     * @param staff
     * @param gysInfo
     * @return
     */
    private User buildLoginUser(CsGysStaff staff, CsGysInfo gysInfo) {
        User user = new User();
        //所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
        user.setCompanyDmId(gysInfo.getDmId());
        user.setCompanyName(gysInfo.getName());
        user.setCompanyType(1);
        user.setPwd(null);
        user.setLpName(gysInfo.getLpName());
        user.setName(gysInfo.getName());
        user.setStatus(Integer.valueOf(staff.getStatus()));
        user.setPhone(staff.getPhone());
        user.setDmId(staff.getDmId());
        return user;
    }

    /**
     * 供应商员工登出
     *
     * @param token
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public void logout(@RequestHeader("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            sessionService.removeAppSession(token);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }


    
    
    /**
     * 供应链-供应商管理-供应商列表-供应商获取详情
     *
     * @param token
     * @param request
     * @param response
     * @throws IOException
     */
//    @ApiOperation(value = "供应链-供应商管理-供应商列表-供应商获取详情", notes = "供应链-供应商管理-供应商列表-供应商获取详情",  position = 1)
//    @RequestMapping(value = "csGysDeatil", method = RequestMethod.GET)
//    public void csGysDeatil(@RequestHeader("token") String token,@RequestParam Long dmId, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//        	 Map<String, Object> resMap = new HashMap<String, Object>();
//            CsGysInfo info = csGysInfoService.findDeatailVo(dmId);
//            CsGysAddress csGysAddress = csGysAddressService.findOnlyAddress(dmId);
//            resMap.put("csGysInfo", info);
//            resMap.put("csGysAddress", csGysAddress);
//            
//            
//          //省、市、区、镇
//            Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(csGysAddress.getProvinceId(), csGysAddress.getCityId(), csGysAddress.getCountyId(),
//            		csGysAddress.getTownId());
//
//           		
//    		 
//            resMap.put("provinceId",csGysAddress.getProvinceId());
//            resMap.put("provinceName",csRegionsMap.get("province")!=null?csRegionsMap.get("province").getName():"");
//            resMap.put("cityId",csGysAddress.getCityId());
//            resMap.put("cityName",csRegionsMap.get("city")!=null?csRegionsMap.get("city").getName():"");
//            resMap.put("countyId",csGysAddress.getCountyId());
//            resMap.put("countyName",csRegionsMap.get("area")!=null?csRegionsMap.get("area").getName():"");
//            resMap.put("townId",csGysAddress.getTownId()!=null?csGysAddress.getTownId():"");
//            resMap.put("townName",csRegionsMap.get("town")!=null?csRegionsMap.get("town").getName():"");
//            RespJsonPHandler.respOK(resMap, response, request);
//        } catch (ServiceException e) {
//            log.error(e);
//            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
//        }
//    }
    
    /**
     * 供应商-我的信息 - 供应商获取我的信息
     *
     * @param token
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "供应商获取我的信息", notes = "供应商获取我的信息",  position = 1)
    @RequestMapping(value = "getMyInfo", method = RequestMethod.POST)
    public void getMyInfo(@RequestHeader("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	 Map<String, Object> resMap = new HashMap<String, Object>();

            User user = getUserJsonp(token, response, request);
//            if (null == user) {
//                return;
//            }
            Long suppId = user.getCompanyDmId();
            CsGysInfo info = csGysInfoService.findDeatailVo(suppId);
            CsGysAddress csGysAddress = csGysAddressService.findOnlyAddress(suppId);
            CsGysDeliveryRegion dr = new CsGysDeliveryRegion();
            dr.setGysId(suppId);
            List<CsGysDeliveryRegion> drList = csGysDeliveryRegionService.findList(dr);
            List<CsGysDeliveryRegion> list = new ArrayList<CsGysDeliveryRegion>();

            
            GdGoodsBrands ggb = new GdGoodsBrands();
//            ggb.setSupplierId(Long.valueOf("1706523788511232"));
            ggb.setSupplierId(suppId);       
            List<GdGoodsBrands> ggbList = gdGoodsBrandsService.findList(ggb);
            if(null != ggbList && ggbList.size() > 0 ){
            	 resMap.put("gdBusinessBrandList", ggbList);
            }


            Map<String, CsRegions> temp = null;
            for(CsGysDeliveryRegion v : drList){
            	temp = csRegionsService.getAddressByIds(csGysAddress.getProvinceId(), csGysAddress.getCityId(),null,null);
            	v.setProvinceName( temp.get("province")!=null?temp.get("province").getName():"");
            	v.setCityName(temp.get("city")!=null?temp.get("city").getName():"");
            	list.add(v);
            }
            
            //省、市、区、镇
            Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(csGysAddress.getProvinceId(), csGysAddress.getCityId(), csGysAddress.getCountyId(),
            		csGysAddress.getTownId());

           		
            resMap.put("info", info);
            resMap.put("csGysAddress", csGysAddress);
            resMap.put("provinceId",csGysAddress.getProvinceId()!=null?csGysAddress.getProvinceId():"");
            resMap.put("provinceName",csRegionsMap.get("province")!=null?csRegionsMap.get("province").getName():"");
            resMap.put("cityId",csGysAddress.getCityId());
            resMap.put("cityName",csRegionsMap.get("city")!=null?csRegionsMap.get("city").getName():"");
            resMap.put("countyId",csGysAddress.getCountyId());
            resMap.put("countyName",csRegionsMap.get("area")!=null?csRegionsMap.get("area").getName():"");
            resMap.put("townId",csGysAddress.getTownId()!=null?csGysAddress.getTownId():"");
            resMap.put("townName",csRegionsMap.get("town")!=null?csRegionsMap.get("town").getName():"");
            resMap.put("csGysDeliveryRegion", list);
            RespJsonPHandler.respOK(resMap, response, request);

        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    

    
    

	/**
	 * 供应商-商品管理 -商品列表-分页
	 * @param token
	 * @param csGysStock
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "queryGysGoodsPage", method = RequestMethod.POST)
	public void queryGysGoodsPage(@RequestHeader("token") String token, @RequestBody CsGysStock csGysStock ,
			HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		CsGysStockPage csGysStockPage= new CsGysStockPage();	
		csGysStockPage.setNowPage(csGysStock.getPageNum());
		csGysStockPage.setPageSize(csGysStock.getPageSize());
		 if( null != csGysStock.getOnsaleTimeStart() && !"".equals( csGysStock.getOnsaleTimeStart() )){
			 Long timeStart = DateUtil.getStrDataTimes(csGysStock.getOnsaleTimeStart());
			 String temp = timeStart.toString();
			 csGysStock.setOnsaleTimeStart(temp.substring(0,temp.length()-3));
         }
         if(  null != csGysStock.getOnsaleTimeEnd() && !"".equals( csGysStock.getOnsaleTimeEnd() )){
        	 Long timeEnd = DateUtil.getStrDataTimes(csGysStock.getOnsaleTimeEnd());
        	 String temp = timeEnd.toString();
        	 csGysStock.setOnsaleTimeEnd(temp.substring(0,temp.length()-3));
         }
//		CsGysStock csGysStock = new CsGysStock();
		try {
			//登陆检查
//            checkLogin(token, response);
            Pager<CsGysStock,Long> pager = new Pager<CsGysStock,Long> ();
			List<CsGysStock> list = csGysStockService.queryFindList(csGysStock);
			Set<Long> set = null;	
			//根据条件查询出GoodsId
			if( null != list && list.size() > 0){
				set = new HashSet<Long>();
				for(int i = 0;i<list.size(); i++){
					if( null != list.get(i).getGoodsId() ){
						set.add(list.get(i).getGoodsId());		
					}	
				}
				csGysStockPage.setGoodsIds(set);
				pager = csGysStockService.query(csGysStockPage, CsGysStock.class);
				if(pager!=null && pager.getList() !=null && pager.getList().size()>0){
					for (int i = 0; i < pager.getList().size(); i++) {
						for (int j = 0; j < list.size(); j++) {
							if(pager.getList().get(i).getGoodsId().equals(list.get(j).getGoodsId())){
								pager.getList().get(i).setCsGysStockInfo(list.get(j).getCsGysStockInfo());
							}
						}
						
					}
				}
				
			}
			
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException e) {
			log.error("【供应商-商品管理 -商品列表-分页】列表出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		} 
	}
	
	
	
	
	
	
	/**
     * 供应商-商品管理-下架操作
     * @param token
     * @param csGysStock
     * @param request
     * @param response
     */
	@RequestMapping(value = "updateDiscontinued", method = RequestMethod.POST)
	public void updateDiscontinued(@RequestHeader("token") String token,@RequestBody CsGysStock csGysStock,HttpServletRequest request, HttpServletResponse response){
		
		try {
			//登陆检查
            checkLogin(token, response);
//			checkModVo(csGysStock);
            Long time = DateUtil.getTimeInSeconds();
            String temp = time.toString();
            time =Long.parseLong( temp.substring(0,temp.length()-3));
            csGysStock.setOnsaleTime(time);
			csGysStockService.modVoNotNull(csGysStock);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【供应商-商品管理-下架操作】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (ActionException e) {
			log.error("【供应商-商品管理-下架操作】用户未登录："+ e);
			RespHandler.respError(RespMsg.user_not_login, response);
		} catch (IOException e) {
			log.error("【供应商-商品管理-下架操作】IOException异常："+ e);
			RespJsonPHandler.respServerError(response, request);
		}
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 修改时供应商商品库存对象，数据验证
     *
     * @param shopManage 便利店管理公司对象
     * @throws ServiceException
     */
    private void checkModVo( CsGysStock csGysStock) throws ServiceException {
        if (null == csGysStock.getDmId()) {
            throw new ServiceException("供应商商品库存对象ID为空！");
        }
    }
    
    /**
     * 设置信息
     * @param map
     * @param identifier
     * @return
     * @throws ServiceException
     */
	private CsGysInfo mapSetCsGysInfo(Map<String,Object> map,String identifier) throws ServiceException {
		

		CsGysInfo csGysInfo = new CsGysInfo();
		if( null != map.get("dmId") && !"".equals(map.get("dmId"))){
       	 	csGysInfo.setDmId(Long.valueOf(map.get("dmId").toString()));
        }
        if( null != map.get("establishTime") && !"".equals(map.get("establishTime"))){
        	DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
   	   	 	Date establishTime = null;
   			try {
   				establishTime = df.parse(map.get("establishTime").toString());
   			} catch (ParseException e1) {

   			}
   			csGysInfo.setEstablishTime(establishTime);
        }
        if( null != identifier && !"".equals(identifier)){
        	csGysInfo.setIdentifier(identifier);
        }
        if( null != map.get("name") && !"".equals(map.get("name"))){
        	 csGysInfo.setName(map.get("name").toString());
        }
        if( null != map.get("contactPhone") && !"".equals(map.get("contactPhone"))){
        	csGysInfo.setContactPhone(map.get("contactPhone").toString());
        }
        if( null != map.get("ettlementCycle") && !"".equals(map.get("ettlementCycle"))){
        	csGysInfo.setEttlementCycle(Integer.valueOf(map.get("ettlementCycle").toString()));
        }
        if( null != map.get("deliveryTime") && !"".equals(map.get("deliveryTime"))){
        	csGysInfo.setDeliveryTime(Integer.valueOf(map.get("deliveryTime").toString()));
        }
        if( null != map.get("restockCycle") && !"".equals(map.get("restockCycle"))){
        	csGysInfo.setRestockCycle(Integer.valueOf(map.get("restockCycle").toString()));
        }
        if( null != map.get("businessBrand") && !"".equals(map.get("businessBrand"))){
        	csGysInfo.setBusinessBrand(map.get("businessBrand").toString());
        }
        if( null != map.get("businessScope") && !"".equals(map.get("businessScope"))){
        	csGysInfo.setBusinessScope(map.get("businessScope").toString());
        }
        if( null != map.get("licence") && !"".equals(map.get("licence"))){
        	csGysInfo.setLicence(map.get("licence").toString());
        }
        if( null != map.get("idCardFront") && !"".equals(map.get("idCardFront"))){
        	 csGysInfo.setIdCardFront(map.get("idCardFront").toString());
        }
        if( null != map.get("idCardBehind") && !"".equals(map.get("idCardBehind"))){
        	 csGysInfo.setIdCardBehind(map.get("idCardBehind").toString());
        }
        if( null != map.get("lpName") && !"".equals(map.get("lpName"))){
        	csGysInfo.setLpName(map.get("lpName").toString());
        }
        if( null != map.get("lpIdCard") && !"".equals(map.get("lpIdCard"))){
        	csGysInfo.setLpIdCard(map.get("lpIdCard").toString());
        }
        if( null != map.get("status") && !"".equals(map.get("status"))){
        	csGysInfo.setStatus(new Byte(map.get("status").toString()));
        }
        if( null != map.get("jsonAddress") && !"".equals(map.get("jsonAddress"))){
        	 csGysInfo.setJsonAddress(map.get("jsonAddress").toString());
        }
        if( null != map.get("jsonArea") && !"".equals(map.get("jsonArea"))){           
            csGysInfo.setJsonArea(map.get("jsonArea").toString());
        } 
        List <GdGoodsBrands> gdList = new ArrayList<GdGoodsBrands>();
        if( null != map.get("gdGoodsBrandList") && !"".equals(map.get("gdGoodsBrandList"))){           
        	List gbMapList = (List) map.get("gdGoodsBrandList");
        	
        	for (int i = 0; i < gbMapList.size(); i++) {
        		LinkedHashMap<String, String> linkedMap = (LinkedHashMap<String, String>) gbMapList.get(i);
        		GdGoodsBrands gb = new GdGoodsBrands();
        		for (Map.Entry<String, String> entry: linkedMap.entrySet()) {
            		String key = entry.getKey();
            		String value = entry.getValue();
            		if("cnName".equals(key)){
            			if( null !=value && !"".equals(value)){
            				gb.setCnName(value);
            			}
            			
            		}
            		if("dmId".equals(key)){
            			if( null !=value && !"".equals(value)){
            				gb.setDmId(Long.parseLong(value));
            			}
            			
            		}
    			}
        		gdList.add(gb);
			}
        	
        } 

        csGysInfo.setGdGoodsBrandList(gdList);
		return csGysInfo;
		
	} 
    

    
}
