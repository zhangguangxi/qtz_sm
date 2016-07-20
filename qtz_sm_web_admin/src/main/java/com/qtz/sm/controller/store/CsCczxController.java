package com.qtz.sm.controller.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mall.core.common.Constants;
import com.mall.core.common.MD5Util;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.DdmSession;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.session.service.SessionService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.stc.page.CczxGoodsPage;
import com.qtz.sm.stc.page.CsCczxGoodsQuantityPage;
import com.qtz.sm.stc.service.CczxGoodsService;
import com.qtz.sm.stc.service.CsCczxGoodsQuantityService;
import com.qtz.sm.stc.vo.CczxGoods;
import com.qtz.sm.stc.vo.CsCczxGoodsQuantity;
import com.qtz.sm.store.service.CsCczxAddressService;
import com.qtz.sm.store.service.CsCczxInfoService;
import com.qtz.sm.store.service.CsCczxStaffService;
import com.qtz.sm.store.service.CsCczxStockService;
import com.qtz.sm.store.vo.CsCczxAddress;
import com.qtz.sm.store.vo.CsCczxInfo;
import com.qtz.sm.store.vo.CsCczxStaff;
import com.qtz.sm.store.vo.CsCczxStock;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.vo.WtBankCard;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * Title:(仓储中心控制类)<br/>
 * Description:(仓储中心控制类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 郭云龙 
 * @version v1.0 2016年5月17日
 */
@Controller
@RequestMapping(value = "/cscczx/")
public class CsCczxController extends BaseController {

    private static final LogTool log = LogTool.getInstance(CsCczxController.class);

    @Resource(name = "sessionServiceImpl")
    private SessionService sessionService;
    @Resource(name = "csCczxInfoServiceImpl")
    private CsCczxInfoService csCczxInfoService;
    @Resource(name = "csCczxStockServiceImpl")
    private CsCczxStockService csCczxStockService;

    @Resource(name = "csCczxStaffServiceImpl")
    private CsCczxStaffService csCczxStaffService;
    @Autowired
    private CczxGoodsService cczxGoodsService;
	@Autowired
	private CsCczxGoodsQuantityService csCczxGoodsQuantityService;
	
    @Autowired
	private GdGoodsService goodsService;
    
    @Autowired
    private CsCczxAddressService csCczxAddressService;
    
    @Autowired
	private WtBankCardService wtBankCardService;
    
    @Autowired
    private CsRegionsService csRegionsService;
    
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
        	CsCczxStaff staff = new CsCczxStaff();
        	staff.setPhone( maps.get("name").toString());
            staff.setPwd(MD5Util.getMD5(maps.get("pwd").toString()));
            List<CsCczxStaff> staffList = csCczxStaffService.findList(staff);
            if (null != staffList && staffList.size() == 1) {
                staff = staffList.get(0);
                if (staff.getStatus().intValue() != 0) {
                    RespJsonPHandler.respError(RespMsg.user_seal_number, response, request);
                    return;
                } else {
                    Long CczxId = staff.getCczxId();
                    CsCczxInfo CczxInfo = csCczxInfoService.findVo(CczxId, null);
                    User user = buildLoginUser(staff, CczxInfo);
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
     * @param CczxInfo
     * @return
     */
    private User buildLoginUser(CsCczxStaff staff, CsCczxInfo cczxInfo) {
        User user = new User();
        //所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
        user.setCompanyDmId(cczxInfo.getDmId());
        user.setCompanyName(cczxInfo.getName());
        user.setCompanyType(4);
        user.setPwd(null);
        user.setLpName(cczxInfo.getLpName());
        user.setName(cczxInfo.getName());
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
     * 仓储中心获取我的信息
     *
     * @param token
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "getMyInfo", method = RequestMethod.POST)
    public void getMyInfo(@RequestHeader("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	 Map<String, Object> resMap = new HashMap<String, Object>();
    		 User user = getUserJsonp(token, response, request);
     		//登陆检查
//             checkLogin(token, response);
    		Long dmId = user.getCompanyDmId();
             CsCczxInfo  csCczxInfo = csCczxInfoService.findVo(dmId, null);
             CsCczxAddress CsCczxAddressInfo   = csCczxAddressService.findVoByCczxId(dmId);
             WtBankCard wtBankCardInfo = wtBankCardService.queryWtBankCardGroupInfo(dmId,CompanyType.StorageCenter.value());
//     		if(null == CsCczxAddressInfo || null == csCczxInfo || null == wtBankCardInfo){
//     			//数据未找到
//     			RespHandler.respError(RespMsg.not_found, response);
// 				return;		
//     		}
             if(null != csCczxInfo){
//            	 JSONObject jsonCsYccInfo = JSONObject.fromObject(csCczxInfo); 
            	 resMap.put("csCczxInfo",csCczxInfo );
             }
             
             if( null != CsCczxAddressInfo){
//            	 JSONObject json = JSONObject.fromObject(CsCczxAddressInfo); 
         		 resMap.put("csCczxAddress",CsCczxAddressInfo );
             }
           //省、市、区、镇
             Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(CsCczxAddressInfo.getProvinceId(), CsCczxAddressInfo.getCityId(), CsCczxAddressInfo.getCountyId(),
            		 CsCczxAddressInfo.getTownId());

            		
     		 
     		 
     		 resMap.put("wtBankCardInfoJson",wtBankCardInfo );
     		 resMap.put("provinceId",CsCczxAddressInfo.getProvinceId());
             resMap.put("provinceName",csRegionsMap.get("province")!=null?csRegionsMap.get("province").getName():"");
             resMap.put("cityId",CsCczxAddressInfo.getCityId());
             resMap.put("cityName",csRegionsMap.get("city")!=null?csRegionsMap.get("city").getName():"");
             resMap.put("countyId",CsCczxAddressInfo.getCountyId());
             resMap.put("countyName",csRegionsMap.get("area")!=null?csRegionsMap.get("area").getName():"");
             resMap.put("townId",CsCczxAddressInfo.getTownId()!=null?CsCczxAddressInfo.getTownId():"");
             resMap.put("townName",csRegionsMap.get("town")!=null?csRegionsMap.get("town").getName():"");
     		 RespHandler.respOK(resMap, response);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }

	
	
	
	
	
	/**
	 * 仓储中心-商品管理 -我要进货-商品信息-分页
	 * @param token
	 * @param cczxGoods
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@ApiOperation(value = "仓储中心-商品管理 -我要进货-商品信息-分页",
            notes = "仓储中心-商品管理 -我要进货-商品信息-分页",
            position = 8)
    @RequestMapping(value = "/goodsCczxPage", method = RequestMethod.POST)
	public void goodsCczxPage(@RequestHeader("token") String token,
			 @RequestBody CczxGoods cczxGoods , HttpServletRequest request,HttpServletResponse response)throws IOException{	
		CczxGoodsPage cczxGoodsPage = new CczxGoodsPage();
		
		cczxGoodsPage.setNowPage(cczxGoods.getPageNum());
		cczxGoodsPage.setPageSize(cczxGoods.getPageSize());
		Set<Long> set = null;
		try {
			 List<CczxGoods > list  = cczxGoodsService.findList(cczxGoods);
			 Pager<CczxGoods, Long>  pager = new Pager<CczxGoods, Long>();
			 
			//根据条件查询出GoodsId


				if( null != list && list.size() > 0){
					set = new HashSet<Long>();
					for(int i = 0;i<list.size(); i++){
						if( null != list.get(i).getGoodsId() ){
							set.add(list.get(i).getGoodsId());		
						}	
					}
					cczxGoodsPage.setGoodsIds(set);
				    pager = cczxGoodsService.query(cczxGoodsPage,CczxGoods.class);
					if(pager!=null && pager.getList() !=null && pager.getList().size()>0){
						for (int i = 0; i < pager.getList().size(); i++) {
							for (int j = 0; j < list.size(); j++) {
								if(pager.getList().get(i).getGoodsId().equals(list.get(j).getGoodsId())){
									pager.getList().get(i).setCczxGoodsInfo(list.get(j).getCczxGoodsInfo());
								}
							}
							
						}
					}
					
				}
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException e) {
			log.error("【仓储中心-商品管理 -我要进货-商品信息-分页】列表出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
		
		
	}


	/**
	 * 仓储中心-商品管理 -商品列表-分页
	 * @param token
	 * @param map
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "仓储中心-商品管理 -商品列表-分页",
            notes = "仓储中心-商品管理 -商品列表-分页",
            position = 8)
    @RequestMapping(value = "/cczxGoodsQuantityPage", method = RequestMethod.POST)
	public void cczxGoodsQuantityPage(@RequestHeader("token") String token,
			 @RequestBody CsCczxGoodsQuantity csCczxGoodsQuantity  , HttpServletRequest request,HttpServletResponse response)throws IOException{
		CsCczxGoodsQuantityPage csCczxGoodsQuantityPage = new CsCczxGoodsQuantityPage();

		Set<Long> set = null;
		try {
			csCczxGoodsQuantityPage.setNowPage(csCczxGoodsQuantity.getPageNum());
			csCczxGoodsQuantityPage.setPageSize(csCczxGoodsQuantity.getPageSize());
			//根据条件查询出GoodsId
			List<CsCczxGoodsQuantity > list = csCczxGoodsQuantityService.findList(csCczxGoodsQuantity);
			Pager<CsCczxGoodsQuantity, Long>  pager = new Pager<CsCczxGoodsQuantity, Long>();
			if( null != list && list.size() > 0){
				set = new HashSet<Long>();
				for(int i = 0;i<list.size(); i++){
					
					if( null != list.get(i).getGoodsId() ){
						set.add(list.get(i).getGoodsId());		
					}	
				}
				csCczxGoodsQuantityPage.setGoodsIds(set);
			    pager = csCczxGoodsQuantityService.query(csCczxGoodsQuantityPage, CsCczxGoodsQuantity.class);	
				if(pager!=null && pager.getList() !=null && pager.getList().size()>0){
					for (int i = 0; i < pager.getList().size(); i++) {
						for (int j = 0; j < list.size(); j++) {
							if(pager.getList().get(i).getGoodsId().equals(list.get(j).getGoodsId())){
								pager.getList().get(i).setSkuAndStockInfo(list.get(j).getSkuAndStockInfo());
							}
						}
						
					}
				}
				
			}
			RespJsonPHandler.respOK(pager, response, request);			
		} catch (ServiceException e) {
			log.error("【仓储中心-商品管理 -商品列表-分页】列表出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
	}

	
	
	/**
	 * 仓储中心-商品列表 -商品详情
	 * @param sid
	 * @param goodsId
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "仓储中心-商品列表 -商品详情",
            notes = "仓储中心-商品列表 -商品详情",
            position = 9)
	@RequestMapping(value = "goodsStockInfoById", method = RequestMethod.POST)
	public void goodsStockInfoById(@RequestHeader("token") String token, @RequestBody Map<String,Object> map,
			HttpServletRequest request,HttpServletResponse response)throws IOException {
		//获取仓储中心进货价和库存信息
		Map<Long, Double> priceMap = new HashMap<Long, Double>();
		Map<Long, Integer> stockMap = new HashMap<Long, Integer>();
		
		//获取商品价格库存列表
//		List<CsGysStock> stockList = gysStockService.getStocksByGoodsId(goodsId);
		
		try {
			List<CsCczxStock> stockList = csCczxStockService.queryStockQuantityAndPrice(Long.valueOf(map.get("goodsId").toString()));
			if (stockList != null) {
				for (CsCczxStock stock:stockList) {
					if(null != stock.getYccglPrice() && null != stock.getYccglRate()){
						//仓储中心进货价 = 云仓储管理公司议价后价格  * (1+云仓储管理公司议价率)
						priceMap.put(stock.getSkuId(), (stock.getYccglPrice() *(stock.getYccglPrice() + stock.getYccglRate())));
						stockMap.put(stock.getSkuId(), stock.getStockQuantity());
					}
					
				}
			}
			//需要改动 priceMap stockMap
			GdGoodsOutJson goodsOutJson = goodsService.getGoodsDetail(Long.valueOf(map.get("goodsId").toString()), priceMap, stockMap, false, false, null, null);
			SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
					SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse};  

			//此处用阿里的JSONObject
			com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
			json.put("code", 0);
			json.put("data", goodsOutJson);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			log.debug("<<<<<<<<调试输出日志:"+json.toString()+">>>>>>>");
			write.write(JSON.toJSONString(json, features));
			write.flush();
			write.close();
//			RespJsonPHandler.respOK(json, response, request);
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-商品管理 -商品库-商品详情】出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, null);
		}
	}
}
