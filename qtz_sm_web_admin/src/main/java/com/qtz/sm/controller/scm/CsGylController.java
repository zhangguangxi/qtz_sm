package com.qtz.sm.controller.scm;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mall.core.common.Constants;
import com.mall.core.common.DateUtil;
import com.mall.core.common.MD5Util;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.DdmSession;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.model.GdGoodsCategroyRateBo;
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.service.GdGoodsBrandsService;
import com.qtz.sm.goods.service.GdGoodsCategroyRateService;
import com.qtz.sm.goods.service.GdGoodsOperationHistoryService;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.service.GdGoodsSkuRateService;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.goods.vo.GdGoodsBrands;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;
import com.qtz.sm.goods.vo.GdGoodsOperationHistory;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;
import com.qtz.sm.scm.page.CsGylGoodsVoPage;
import com.qtz.sm.scm.service.CsGylAddressService;
import com.qtz.sm.scm.service.CsGylGoodsService;
import com.qtz.sm.scm.service.CsGylInfoService;
import com.qtz.sm.scm.service.CsGylStaffService;
import com.qtz.sm.scm.vo.CsGylAddress;
import com.qtz.sm.scm.vo.CsGylGoodsVo;
import com.qtz.sm.scm.vo.CsGylInfo;
import com.qtz.sm.scm.vo.CsGylStaff;
import com.qtz.sm.session.service.SessionService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.stc.model.ResultBo;
import com.qtz.sm.supp.page.CsGysInfoPage;
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
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.service.WtGysIncomeService;
import com.qtz.sm.wallet.vo.WtBankCard;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


/**
 * Title:(供应链控制类)<br/>
 * Description:(供应链控制类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author gys
 * @version v1.0 2016年5月11日
 */
@Api(value = "csgyl/", description = "供应链控制类")
@Controller
@RequestMapping(value = "csgyl/")
public class CsGylController extends BaseController {

    private static final LogTool log = LogTool.getInstance(CsGylController.class);
    
    @Resource(name = "sessionServiceImpl")
    private SessionService sessionService;

//    @Resource(name = "csGylInfoServiceImpl")
//    private CsGylInfoService csGylInfoService;

    @Resource(name = "csGylStaffServiceImpl")
    private CsGylStaffService csGylStaffService;
    
    @Resource(name = "csGysInfoServiceImpl")
    private CsGysInfoService csGysInfoService;

    @Resource(name = "gdGoodsCategroyRateServiceImpl")
    private GdGoodsCategroyRateService gdGoodsCategroyRateService;
    
    @Autowired
    private GdGoodsService gdGoodsService;
    
    @Autowired
    private GdGoodsSkuRateService gdGoodsSkuRateService;
    
    @Autowired
    private GdGoodsOperationHistoryService gdGoodsOperationHistoryService;
    
	@Autowired
	private GdGoodsCategroyRateService rateService;
	
	@Autowired
	private CsGylGoodsService csGylGoodsService;
	
    @Autowired
    private WtGysIncomeService wtGysIncomeService;
    
    
    @Autowired
    private  CsGylInfoService csGylInfoService;
    
    @Autowired
    private  CsGylAddressService csGylAddressService;
    
    @Autowired
    private CsGysAddressService csGysAddressService;
    
    @Autowired
    private CsRegionsService csRegionsService;
    
    @Autowired
    private CsGysStaffService csGysStaffService;
    
    @Autowired
    private  GdGoodsBrandsService gdGoodsBrandsService;
    
    @Autowired
    private CsGysDeliveryRegionService csGysDeliveryRegionService;
    
    @Autowired
	private WtBankCardService wtBankCardService;
    
    @Autowired
    private CsGysStockService csGysStockService;
    

    
    /**
     * 供应链员工登录
     *
     * @param phone
     * @param pwd
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public void login(@RequestBody Map<String,String> mapParamet, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	CsGylStaff staff = new CsGylStaff();
            staff.setPhone(mapParamet.get("name"));
            staff.setPwd(MD5Util.getMD5(mapParamet.get("pwd")));
            List<CsGylStaff> staffList = csGylStaffService.findList(staff);
            if (null != staffList && staffList.size() == 1) {
                staff = staffList.get(0);
                if (staff.getStatus().intValue() != 0) {
                    RespJsonPHandler.respError(RespMsg.user_seal_number, response, request);
                    return;
                } else {
                    Long gylId = staff.getGylId();
                    CsGylInfo gylInfo = csGylInfoService.findVo(gylId, null);
                    User user = buildLoginUser(staff, gylInfo);
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
     * 供应链员工登录构建登录用户
     *
     * @param staff
     * @param gysInfo
     * @return
     */
    private User buildLoginUser(CsGylStaff staff, CsGylInfo gylInfo) {
        User user = new User();
        //所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
        user.setCompanyDmId(gylInfo.getDmId());
        user.setCompanyName(gylInfo.getName());
        user.setCompanyType(2);
        user.setPwd(null);
        user.setLpName(gylInfo.getLpName());
        user.setName(gylInfo.getName());
        user.setStatus(Integer.valueOf(staff.getStatus()));
        user.setPhone(staff.getPhone());
        user.setDmId(staff.getDmId());
        return user;
    }

    /**
     * 供应链员工登出
     *
     * @param token
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "logout")
    public void logout(@RequestHeader("token") String  token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            sessionService.removeAppSession(token);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }

    
    /**
     * 供应链-我的信息-查看我的信息
     *
     * @param token
     * @param request
     * @param response
     * @throws IOException
     */
	@ApiOperation(value = "供应链我的信息",
            notes = "供应链我的信息",
            position = 0)
	@RequestMapping(value = "getMyInfo")
    public void getMyInfo(@RequestHeader("token") String  token, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	Map<String, Object> resMap = new HashMap<String, Object>();
            User user = getUserJsonp(token, response, request);
//            if (null == user) {
//                return;
//            }
//            Long suppId =Long.valueOf("1707893367965696");
            Long suppId = user.getCompanyDmId();
            CsGylInfo info = csGylInfoService.findDeatailVo(suppId);
            CsGylAddress csGylAddress = new CsGylAddress();
            csGylAddress.setGylId(suppId);
            List<CsGylAddress> list = csGylAddressService.findList(csGylAddress);
            
            WtBankCard wtBankCardInfo = wtBankCardService.queryWtBankCardGroupInfo(suppId,CompanyType.SupplyChain.value());
            
          //省、市、区、镇
            Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(list.get(0).getProvinceId(), list.get(0).getCityId(), list.get(0).getCountyId(),
            		list.get(0).getTownId());
            
//            JSONObject csGylInfo = JSONObject.fromObject(info); 
//            JSONObject jsonCsGylAddress = JSONObject.fromObject(list.get(0)); 
            resMap.put("csGylInfo",info!=null ?info:"" );
   		 	resMap.put("jsonCsGylAddress",list.get(0)!=null && list.size()>0 ?list.get(0):"");
   		 resMap.put("wtBankCardInfo",wtBankCardInfo!=null ?wtBankCardInfo:"");
   		 
   		 resMap.put("provinceId",list.get(0).getProvinceId()!=null?list.get(0).getProvinceId():"");	
   		 resMap.put("provinceName",csRegionsMap.get("province")!=null?csRegionsMap.get("province").getName():"");
         resMap.put("cityId",list.get(0).getCityId()!=null?list.get(0).getCityId():"");
         resMap.put("cityName",csRegionsMap.get("city")!=null?csRegionsMap.get("city").getName():"");
         resMap.put("countyId",list.get(0).getCountyId()!=null?list.get(0).getCountyId():"");
         resMap.put("countyName",csRegionsMap.get("area")!=null?csRegionsMap.get("area").getName():"");
         resMap.put("townId",list.get(0).getTownId()!=null?list.get(0).getTownId():"");
         resMap.put("townName",csRegionsMap.get("town")!=null?csRegionsMap.get("town").getName():"");

   		 
   		 	RespHandler.respOK(resMap, response);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    /**
     * 由省市ID获取供应商编号
     *
     * @param provinceId
     * @param cityId
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "供应商编号", notes = "供应商编号",  position = 1)
    @RequestMapping(value = "getIdentifier", method = RequestMethod.POST)
    public void getIdentifier(@RequestHeader("token") String token,@RequestBody Map<String,String> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map<String, Object> resMap = new HashMap<String, Object>();
            String result = csGysInfoService.findNextIdentifier(Integer.valueOf(map.get("provinceId")), Integer.valueOf(map.get("cityId")));
            resMap.put("prefix", result.substring(0, 6));
            resMap.put("endfix", result.substring(6));
            resMap.put("result", result);
            RespJsonPHandler.respOK(resMap, response, request);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }

    /**
     * 供应链-供应商管理-新增供应商
     *
     * @param vo
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "新增供应商信息", notes = "新增供应商信息",  position = 1)
    @RequestMapping(value = "addSupplier", method = RequestMethod.POST)
    public void addSupplier(@RequestHeader("token") String token,@RequestBody Map<String,Object> map,
    		 HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        		
                String identifier = csGysInfoService.findNextIdentifier(Integer.valueOf(map.get("provinceId").toString()),Integer.valueOf(map.get("cityId").toString()) );

                CsGysInfo csGysInfo =  mapSetCsGysInfo(map,identifier);            
                
                csGysInfoService.addSuppAndInit(csGysInfo,map);
              
                RespJsonPHandler.respOK(identifier, response, request);
        } catch (ServiceException e) {
        	log.error("【供应链-供应商-新增供应商信息】出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
        }
    }
    

    /**
     *供应链-供应商- 供应商列表
     *
     * @param vo
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "供应商列表", notes = "供应商列表",  position = 1)
    @RequestMapping(value = "querySupplier", method = RequestMethod.POST)
    public void querySupplier(@RequestHeader("token") String token,@RequestBody CsGysInfo csGysInfo,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
			User user = getUserJsonp(token, response, request);
//	        if (null == user) {
//	            return;
//	        }
			 CsGysInfoPage page = new CsGysInfoPage();
			if( null != csGysInfo.getEstablishTimeStart() && !"".equals( csGysInfo.getEstablishTimeStart() )){
            	Date timeStart = DateUtil.strToDate(csGysInfo.getEstablishTimeStart());
            	page.setStartTime(timeStart);
            }
            if(  null != csGysInfo.getEstablishTimeEnd() && !"".equals( csGysInfo.getEstablishTimeEnd() )){
            	Date timeEnd = DateUtil.strToDate(csGysInfo.getEstablishTimeEnd());
            	page.setEndTime(timeEnd);
            }

	       
			page.setNowPage(csGysInfo.getPageNum());
			page.setPageSize(csGysInfo.getPageSize());
			page.setContactPhone(csGysInfo.getContactPhone());
			page.setName(csGysInfo.getName());
			page.setIdentifier(csGysInfo.getIdentifier());

			Pager<CsGysInfo, Long> pager = this.csGysInfoService.query(page, CsGysInfo.class);

			RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    

    /**
     * 供应链-供应商管理-供应商列表-分页dmId获取供应商详情
     *
     * @param token
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "供应链-供应商管理-供应商列表-分页dmId获取供应商详情", notes = "供应链-供应商管理-供应商列表-分页dmId获取供应商详情",  position = 1)
    @RequestMapping(value = "getSupplier")
    public void getSupplier(@RequestHeader("token") String token,@RequestBody Map<String,String> map,
    		 HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
       	 Map<String, Object> resMap = new HashMap<String, Object>();

           User user = getUserJsonp(token, response, request);

           CsGysInfo info = csGysInfoService.findDeatailVo(Long.valueOf(map.get("dmId")));
           CsGysAddress csGysAddress = csGysAddressService.findOnlyAddress(Long.valueOf(map.get("dmId")));   		
           WtBankCard wtBankCardInfo = wtBankCardService.queryWtBankCardGroupInfo(Long.valueOf(map.get("dmId")),CompanyType.SupplyChain.value());
           CsGysDeliveryRegion dr = new CsGysDeliveryRegion();
           dr.setGysId(Long.valueOf(map.get("dmId")));
           List<CsGysDeliveryRegion> drList = csGysDeliveryRegionService.findList(dr);
           List<CsGysDeliveryRegion> list = new ArrayList<CsGysDeliveryRegion>();

           
           GdGoodsBrands ggb = new GdGoodsBrands();

           ggb.setSupplierId(Long.valueOf(map.get("dmId")));       
           List<GdGoodsBrands> ggbList = gdGoodsBrandsService.findList(ggb);
           if(null != ggbList && ggbList.size() > 0 ){
           	 resMap.put("gdBusinessBrandList", ggbList);
           }


           Map<String, CsRegions> temp = null;
           for(CsGysDeliveryRegion v : drList){
           	temp = csRegionsService.getAddressByIds(Integer.valueOf(v.getProvinceId().toString()), Integer.valueOf(v.getCityId().toString()),null,null);
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
           resMap.put("wtBankCardInfo",wtBankCardInfo!=null ?wtBankCardInfo:"");
           RespJsonPHandler.respOK(resMap, response, request);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }

    /**
     *供应链-供应商管理-供应商修改
     *
     * @param vo
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "修改供应商信息", notes = "修改供应商信息",  position = 1)
    @RequestMapping(value = "updateSupplier", method = RequestMethod.POST)
    public void updateSupplier(@RequestHeader("token") String token,@RequestBody Map<String,Object> map,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {	 
        try {
//        	User user = getUserJsonp(token, response, request);
//        	CsGysInfo gysInfo = csGysInfoService.findVo(user.getCompanyDmId(), null);
//        	CsGysInfo gysInfo = csGysInfoService.findVo(Long.valueOf(map.get("dmId")), null);
        	
        	CsGysInfo csGysInfo = new CsGysInfo ();
        	String identifier = csGysInfoService.findNextIdentifier(Integer.valueOf(map.get("provinceId").toString()),Integer.valueOf(map.get("cityId").toString()) );
        	
        	csGysInfo =  mapSetCsGysInfo(map,identifier);
			csGysInfoService.modSuppInfo(csGysInfo , map);
        	

//            	 CsGysInfo csGysInfo =  mapSetCsGysInfo(map,map.get("identifier"));
//                csGysInfoService.modVoNotNull(csGysInfo);
//                
//                CsGysAddress csGysAddress = new CsGysAddress();
//                csGysAddress.setDmId(csGysInfo.getDmId());
//                List<CsGysAddress> list = csGysAddressService.findList(csGysAddress);
//                if( list.size() > 0 ){
//                	csGysAddress = list.get(0);
//                	
//                	csGysAddress.setProvinceId(Integer.valueOf(map.get("provinceId")));
//                	csGysAddress.setCityId(Integer.valueOf(map.get("cityId")));
//                	csGysAddress.setCountyId(Integer.valueOf(map.get("countyId")));
//                	csGysAddress.setAddress(map.get("Address"));
//                	csGysAddress.setFullAddress(csRegionsService.getFullAddressByIds(csGysAddress.getProvinceId(),csGysAddress.getCityId(), csGysAddress.getCountyId(),null,null)+csGysAddress.getAddress());
//                	
//                	csGysAddressService.modVoNotNull(csGysAddress);
//                }
//                
//                
//              //修改员工信息
//                CsGysStaff staff = new CsGysStaff();
//				staff.setGysId(csGysInfo.getDmId());
//				staff.setPhone(csGysInfo.getContactPhone());
//	            List<CsGysStaff> staffList = csGysStaffService.findList(staff);
//				
//	          //当前仓储中心没有对应手机号码的用户，则新建
//	            if (null == staffList || staffList.size() == 0) {
//	                /*为便利店添加用户*/
//	            	addGysStaff(csGysInfo);
//	            } else {
//	                //手机号码有变更
//	                if (!csGysInfo.getContactPhone().equals(gysInfo.getContactPhone())) {
//	                    staff = staffList.get(0);
//	                    //修改手机号码
//	                    staff.setPhone(csGysInfo.getContactPhone());
//	                    csGysStaffService.modVoNotNull(staff);
//	                }
//	            }	
//                
//                
//               
//                if(csGysInfo.getEttlementCycle() != gysInfo.getEttlementCycle()){
//                	 //修改结算周期
//                	if(  null != csGysInfo.getEttlementCycle()){
//                		 wtGysIncomeService.modSettlementTime(csGysInfo.getDmId(),csGysInfo.getEttlementCycle());
//                	}
//                   
//                }
               
//            }
                RespHandler.respOK(response);
        } catch (ServiceException e) {
        	log.error("【供应链-供应商管理-供应商修改】出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    /**
     *供应链-供应商管理-供应商列表-启用，禁用
     *
     * @param vo
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "修改供应商信息", notes = "修改供应商信息",  position = 1)
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    public void updateStatus(@RequestHeader("token") String token,@RequestBody CsGysInfo csGysInfo,HttpServletRequest request, HttpServletResponse response)throws IOException{
    	try {
			csGysInfoService.modVoNotNull(csGysInfo);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【供应链-供应商管理-供应商列表-启用，禁用】出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
    }
    
    
    /**
     * 供应链公司 -商品管理 -商品审核分页
     * @param token
     * @param map
     * @param request
     * @param response
     */
    @ApiOperation(value = "供应链公司 -商品管理 -商品审核分页", notes = "供应链公司 -商品管理 -商品审核分页",  position = 1)
    @RequestMapping(value = "queryGylGoodsPage", method = RequestMethod.POST)
    public void queryGylGoodsPage(@RequestHeader("token") String token, @RequestBody CsGylGoodsVo csGylGoodsVo, HttpServletRequest request, HttpServletResponse response){
    	
		try {
			CsGylGoodsVoPage csGylGoodsVoPage = new CsGylGoodsVoPage();
			csGylGoodsVoPage.setNowPage(csGylGoodsVo.getPageNum());
			csGylGoodsVoPage.setPageSize(csGylGoodsVo.getPageSize());
			
			 if( null != csGylGoodsVo.getOnsaleTimeStart() && !"".equals( csGylGoodsVo.getOnsaleTimeStart() )){
				 Long timeStart = DateUtil.getStrDataTimes(csGylGoodsVo.getOnsaleTimeStart());
				 String temp = timeStart.toString();
				 csGylGoodsVo.setOnsaleTimeStart(temp.substring(0,temp.length()-3));
	         }
	         if(  null != csGylGoodsVo.getOnsaleTimeEnd() && !"".equals( csGylGoodsVo.getOnsaleTimeEnd() )){
	        	 Long timeEnd = DateUtil.getStrDataTimes(csGylGoodsVo.getOnsaleTimeEnd());
	        	 String temp = timeEnd.toString();
	        	 csGylGoodsVo.setOnsaleTimeEnd(temp.substring(0,temp.length()-3));
	         }
			

			Pager<CsGylGoodsVo, Long> pager = new Pager<CsGylGoodsVo, Long> ();
			List<CsGylGoodsVo> list = csGylGoodsService.findList(csGylGoodsVo);
			Set<Long> set = null;	
			//根据条件查询出GoodsId
			if( null != list && list.size() > 0){
				set = new HashSet<Long>();
				for(int i = 0;i<list.size(); i++){
					
					if( null != list.get(i).getGoodsId() ){
						set.add(list.get(i).getGoodsId());		
					}	
				}
				csGylGoodsVoPage.setGoodsIds(set);
			    pager = csGylGoodsService.query(csGylGoodsVoPage, CsGylGoodsVo.class);	
				if(pager!=null && pager.getList() !=null && pager.getList().size()>0){
					for (int i = 0; i < pager.getList().size(); i++) {
						for (int j = 0; j < list.size(); j++) {
							if(pager.getList().get(i).getGoodsId().equals(list.get(j).getGoodsId())){
								pager.getList().get(i).setCsGylGoodsVoInfo(list.get(j).getCsGylGoodsVoInfo());
							}
						}
						
					}
				}
				
			}
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException e) {
			log.error("【供应链公司  -  商品管理 - 商品审核分页-分页】列表出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
    	
    }
    
    
    /**
     *供应链公司 -商品管理 -商品库-分页
     * @param token
     * @param map
     * @param request
     * @param response
     */
    @ApiOperation(value = "供应链公司 -商品管理 -商品库-分页", notes = "供应链公司 -商品管理 -商品库-分页",  position = 1)
    @RequestMapping(value = "queryGylGoodsStockPage", method = RequestMethod.POST)
    public void queryGylGoodsStockPage(@RequestHeader("token") String token, @RequestBody CsGylGoodsVo csGylGoodsVo, HttpServletRequest request, HttpServletResponse response){
    	try {
			CsGylGoodsVoPage csGylGoodsVoPage = new CsGylGoodsVoPage();
			csGylGoodsVoPage.setNowPage(csGylGoodsVo.getPageNum());
			csGylGoodsVoPage.setPageSize(csGylGoodsVo.getPageSize());
			
			 if( null != csGylGoodsVo.getOnsaleTimeStart() && !"".equals( csGylGoodsVo.getOnsaleTimeStart() )){
				 Long timeStart = DateUtil.getStrDataTimes(csGylGoodsVo.getOnsaleTimeStart());
				 String temp = timeStart.toString();
				 csGylGoodsVo.setOnsaleTimeStart(temp.substring(0,temp.length()-3));
	         }
	         if(  null != csGylGoodsVo.getOnsaleTimeEnd() && !"".equals( csGylGoodsVo.getOnsaleTimeEnd() )){
	        	 Long timeEnd = DateUtil.getStrDataTimes(csGylGoodsVo.getOnsaleTimeEnd());
	        	 String temp = timeEnd.toString();
	        	 csGylGoodsVo.setOnsaleTimeEnd(temp.substring(0,temp.length()-3));
	         }
			

			Pager<CsGylGoodsVo, Long> pager = new Pager<CsGylGoodsVo, Long> ();
			List<CsGylGoodsVo> list = csGylGoodsService.findList(csGylGoodsVo);
			Set<Long> set = null;	
			//根据条件查询出GoodsId
			if( null != list && list.size() > 0){
				set = new HashSet<Long>();
				for(int i = 0;i<list.size(); i++){
					
					if( null != list.get(i).getGoodsId() ){
						set.add(list.get(i).getGoodsId());		
					}	
				}
				csGylGoodsVoPage.setGoodsIds(set);
			    pager = csGylGoodsService.query(csGylGoodsVoPage, CsGylGoodsVo.class);	
				if(pager!=null && pager.getList() !=null && pager.getList().size()>0){
					for (int i = 0; i < pager.getList().size(); i++) {
						for (int j = 0; j < list.size(); j++) {
							if(pager.getList().get(i).getGoodsId().equals(list.get(j).getGoodsId())){
								pager.getList().get(i).setCsGylGoodsVoInfo(list.get(j).getCsGylGoodsVoInfo());
							}
						}
						
					}
				}
				
			}
			RespJsonPHandler.respOK(pager, response, request);
		
		} catch (ServiceException e) {
			log.error("【供应链公司 -商品管理 -商品库-分页】列表出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
    	
    }
    
    /**
     *供应链-商品管理-审核商品
     *
     * @param vo
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "审核商品", notes = "审核商品",  position = 1)
    @RequestMapping(value = "auditGoodsSku", method = RequestMethod.POST)
    public void auditGoodsSku(@RequestHeader("token") String token,@RequestBody Map<String,String>map,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
    	GdGoods vo = new GdGoods ();
    	
        try {
        	User user = getUserJsonp(token, response, request);
        	vo.setDmId(Long.valueOf(map.get("goodsId")));
        	vo.setStatus(Integer.valueOf(map.get("status")));
        	//登陆检查
            checkLogin(token, response);
			checkModVo(vo);     	
        	gdGoodsService.modVoNotNull(vo);
        	GdGoodsOperationHistory addVo = new GdGoodsOperationHistory();
        	addVo.setContent("审核商品");
        	addVo.setReason(map.get("remark"));
        	addVo.setGoodsId(vo.getDmId());
        	gdGoodsOperationHistoryService.addVo(addVo);
        	RespHandler.respOK(response);
        } catch (ServiceException e) {
        	log.error("【供应链-商品管理-审核商品】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        } catch (ActionException e) {
        	log.error("【供应链-商品管理-审核商品】用户未登录："+ e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
    }


    

	/**
	 * 供应链-商品管理-商品审核-商品详情
	 * @param sid  凭证
	 * @param dmId 商品ID
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "供应链-商品管理-商品审核-商品详情",
            notes = "供应链-商品管理-商品审核-商品详情",
            position = 2)
	@RequestMapping(value = "getGylGoodsById", method = RequestMethod.POST)
	public void getGylGoodsById(@RequestHeader("token") String sid,  @RequestBody Map<String,Object> map,
			HttpServletResponse response)
			throws IOException {
		try {
			//获取供应商商品SKU属性价格和库存信息
			Map<Long, Double> priceMap = new HashMap<Long, Double>();
			Map<Long, Integer> stockMap = new HashMap<Long, Integer>();
			
			//获取商品价格库存列表
			List<CsGysStock> stockList = csGysStockService.getStocksByGoodsId(Long.valueOf( map.get("goodsId").toString()));
			 Long pageType = Long.valueOf(map.get("goodsId").toString());
			if (stockList != null) {
				for (CsGysStock stock:stockList) {
					priceMap.put(stock.getSkuId(), stock.getPrice());
					stockMap.put(stock.getSkuId(), stock.getStockQuantity());
				}
			}
			boolean withPropConfigBoolean = false;
			boolean withAllSku = false;
			if (pageType != null) {	//1、商品详情页面面，2商品添加页面，3、商品编辑页
				if (pageType == 1) { 
					withPropConfigBoolean = false;
					withAllSku = false;
				} else if (pageType == 2) {
					withPropConfigBoolean = false;
					withAllSku = true;
				} else if (pageType == 3) {
					withPropConfigBoolean = true;
					withAllSku = true;
				}
			}
										//gdGoodsService
			GdGoodsOutJson goodsOutJson = gdGoodsService.getGoodsDetail(Long.valueOf( map.get("goodsId").toString()), priceMap, stockMap, withPropConfigBoolean, withAllSku, null, null);
			SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse};  
			//String data = JSON.toJSONString(goodsOutJson);
			//RespHandler.respOK(data, response);
			
			com.alibaba.fastjson.JSONObject json=new com.alibaba.fastjson.JSONObject();
			json.put("code", 0);
			json.put("data", goodsOutJson);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			log.debug("<<<<<<<<调试输出日志:"+json.toString()+">>>>>>>");
			write.write(JSON.toJSONString(json, features));
			write.flush();
			write.close();
		} catch (ServiceException e) {
			log.error("获取商品详情失败", e);
			RespHandler.respError(RespMsg.not_found, response);
		} 
	}

    
    
    /**
     * 供应链-商品管理-商品库-仓储中心进货价议价
     * @param token
     * @param gdGoodsSkuRate
     * @param request
     * @param response
     */
    @RequestMapping(value = "goodsCczxYccglPrice", method = RequestMethod.POST)
    public void goodsCczxYccglPrice(@RequestHeader("token") String token,@RequestBody GdGoodsSkuRate gdGoodsSkuRate,
    		HttpServletRequest request, HttpServletResponse response)throws IOException{

    	try {
    		//登陆检查
            checkLogin(token, response);
//    		checkModGdGoodsSkuRateVo(gdGoodsSkuRate);
//    		checkGdGoodsSkuRateVo(gdGoodsSkuRate);
    		
			gdGoodsSkuRateService.modVoNotNull(gdGoodsSkuRate);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【供应链-商品管理-商品库-仓储中心进货价议价】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (ActionException e) {
			log.error("【供应链-商品管理-审核商品】用户未登录："+ e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
    }
   
    

	/**
	 * 获取分类溢价信息
	 * 
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "查询分类溢价率",
            notes = "查询分类溢价率",
            position = 0)
	@RequestMapping(value = "getTree", method = RequestMethod.POST)
	public void getTree(HttpServletResponse response, HttpServletRequest request) throws IOException{
		//取得树形结构
		try {
			Integer rateType = CompanyType.SupplyChain.value();
			List<GdGoodsCategroyRateBo> childs = gdGoodsCategroyRateService.findTree(0L, rateType);
			
			//List<Map<String,Object>> childs = GdGoodsCategroyRateUtil.treeGdGoodsCategroyRate(this.rateService.findTree(), 0);
			RespJsonPHandler.respOK(childs, response, request);
		} catch (ServiceException e) {
			log.error("【供应链-商品管理-商品议价(分类列表)-查询分类溢价率】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	/**
	 * 编辑分类溢价信息
	 * 
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "编辑分类溢价率",
            notes = "编辑分类溢价率",
            position = 0)
	@RequestMapping(value = "update", method = RequestMethod.POST,consumes="application/json")
	public void update(@ApiParam("分类溢价率集合") @RequestBody ResultBo result,HttpServletResponse response,
			HttpServletRequest request)throws IOException {
		try {

//			List<GdGoodsCategroyRate> categropRates = JSONArray.parseArray(categropRateJson, GdGoodsCategroyRate.class);

//			System.out.println(categropRates);
//			System.out.println(categropRates.size());
			for(GdGoodsCategroyRate categropRate:result.getList()){
//				System.out.println(categropRate);
//				System.out.println(categropRate.getGylRate());
				this.rateService.modVoNotNull(categropRate);
			}
//			for(GdGoodsCategroyRate categropRate:categroyRateList.getCategropRates()){
//				this.rateService.modVoNotNull(categropRate);
//				throw new ServiceException();
//			}
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【供应链-商品管理-商品议价(分类列表)-查询分类溢价率编辑】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
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
        //经营品牌
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
        //供货区域
        List <CsGysDeliveryRegion> regionList = new ArrayList<CsGysDeliveryRegion>();
        if( null != map.get("ghqy") && !"".equals(map.get("ghqy"))){           
        	List gbMapList = (List) map.get("ghqy");
        	
        	for (int i = 0; i < gbMapList.size(); i++) {
        		LinkedHashMap<String, String> linkedMap = (LinkedHashMap<String, String>) gbMapList.get(i);
        		CsGysDeliveryRegion gd = new CsGysDeliveryRegion();
        		for (Map.Entry<String, String> entry: linkedMap.entrySet()) {
            		String key = entry.getKey();
            		String value = entry.getValue();
            		if("ghqyProvinceId".equals(key)){
            			if( null !=value && !"".equals(value)){
            				gd.setProvinceId(Long.valueOf(value));
            			}
            			
            		}
            		if("ghqyCityId".equals(key)){
            			if( null !=value && !"".equals(value)){
            				gd.setCityId(Long.parseLong(value));
            			}
            			
            		}
    			}
        		regionList.add(gd);
			}
        	
        } 

        csGysInfo.setGdGoodsBrandList(gdList);
        csGysInfo.setRegionList(regionList);
		return csGysInfo;
		
	} 
    
    
    /**
     * 供应链公司-供应链信息-初始化
     *
     1* @param    仓储类对象
     2* @param request
     3* @param response
     4* @throws IOException
     */
    @ApiOperation(value = "供应链公司-供应链信息-初始化",
            notes = "供应链公司-供应链信息-初始化",
            position = 11)    
    @RequestMapping(value = "addGylInfo", method = RequestMethod.POST)
    public void addGylInfo(@RequestBody Map<String,String> map,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
    	CsGylInfo csGylInfo = new CsGylInfo();
    	CsGylAddress csGylAddress = new CsGylAddress();
    	
    	String identifier = "";
		
		try {
			identifier = csGysInfoService.findNextIdentifier(Integer.valueOf(map.get("provinceId").toString()),Integer.valueOf(map.get("cityId").toString()) );
		} catch (NumberFormatException | ServiceException e2) {
			log.error("【供应链公司-供应链信息-初始化】ServiceException异常："+e2);
		}
    	csGylInfo.setName(map.get("name"));
    	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
    	 Date establishTime = null;
		try {
			establishTime = df.parse(map.get("establishTime"));
		} catch (ParseException e1) {

		}
		csGylInfo.setIdentifier(identifier);
		csGylInfo.setContactPhone(map.get("contactPhone"));
		csGylInfo.setEstablishTime(establishTime);    
		csGylInfo.setEttlementCycle(Integer.valueOf(map.get("ettlementCycle")));  
		csGylInfo.setDeliveryTime(Integer.valueOf(map.get("deliveryTime")));    
		csGylInfo.setRestockCycle(Integer.valueOf(map.get("restockCycle")));

		csGylInfo.setLpName(map.get("lpName"));
		csGylInfo.setLpIdCard(map.get("lpIdCard"));
		csGylInfo.setIdCardFront(map.get("idCardFront"));
		csGylInfo.setIdCardBehind(map.get("idCardBehind"));
		csGylInfo.setLicence(map.get("licence"));
		csGylInfo.setStatus(new Byte("0"));
		
		
		csGylAddress.setProvinceId(Integer.valueOf(map.get("provinceId")));
		csGylAddress.setCityId(Integer.valueOf(map.get("cityId")));
		csGylAddress.setCountyId(Integer.valueOf(map.get("countyId")));
		csGylAddress.setAddress(map.get("address"));

    	
    	//参数验证
        try {       	

        	csGylInfoService.addGylInfo(csGylInfo,csGylAddress);
        	RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【供应链公司-供应链信息-初始化】ServiceException异常："+e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}

    }
    
    
    
    

    
    
    
    /**
     * 修改时商品SKU溢价率实体对象，数据验证
     *
     * @param gdGoodsSkuRate
     * @throws ServiceException
     */
    private void checkModGdGoodsSkuRateVo( GdGoodsSkuRate gdGoodsSkuRate) throws ServiceException {
        if (null == gdGoodsSkuRate.getSkuId()) {
            throw new ServiceException("商品SKU溢价率实体对象ID为空！");
        }
    }
    
    /**
     * 商品SKU溢价率实体
     * @param gdGoodsSkuRate
     */
    private void checkGdGoodsSkuRateVo(GdGoodsSkuRate gdGoodsSkuRate)throws ServiceException{
    	if (null == gdGoodsSkuRate.getSkuId()) {
    		 throw new ServiceException(ExceptionCode.CS_GYL_SKUID_IS_NULL, "商品SKU溢价率实体skuId不能为空.");
        }

    	if (null == gdGoodsSkuRate.getYccglPrice() ||   gdGoodsSkuRate.getYccglPrice() <= 0) {
   		 	throw new ServiceException(ExceptionCode.CS_GYL_GYLPRICE_IS_NULL, "仓储中心进货价不能为空或者小于等于0.");
       }
    }
    
    /**
     * 修改时供应商商品对象，数据验证
     *
     * @param shopManage 便利店管理公司对象
     * @throws ServiceException
     */
    private void checkModVo( GdGoods gdGoods) throws ServiceException {
        if (null == gdGoods.getDmId()) {
            throw new ServiceException("供应商商品对象ID为空！");
        }
    }
    
    /**
     * 查询条件
     * @param map
     * @return
     */
    private CsGylGoodsVoPage setCsGylGoodsVoPageInfo(Map<String,String> map){
    	CsGylGoodsVoPage csGylGoodsVoPage = new CsGylGoodsVoPage();
		//queryGoodsType商品查询 1：商品编号，2：商品名称，3：属性编号，4商品来源
		if( null != map.get("queryGoodsType") && !"".equals(map.get("queryGoodsType")) ){
			if( "1".equals(map.get("queryGoodsType")) ){
				if( null != map.get("goodsId") && !"".equals(map.get("goodsId")) ){
					csGylGoodsVoPage.setGoodsId(Long.valueOf(map.get("goodsId")) );
				}	
			}
			else if( "2".equals(map.get("queryGoodsType")) ){
				if( null != map.get("goodsName") && !"".equals(map.get("goodsName")) ){
					csGylGoodsVoPage.setGoodsName( map.get("goodsName"));
				}					
			}
			else if( "3".equals(map.get("queryGoodsType")) ){
				if( null != map.get("goodsNum") && !"".equals(map.get("goodsNum")) ){
					csGylGoodsVoPage.setGoodsNum( map.get("goodsNum"));
				}					
			}
			else if( "4".equals(map.get("queryGoodsType")) ){
				if( null != map.get("goodsSource") && !"".equals(map.get("goodsSource")) ){
					csGylGoodsVoPage.setGoodsSource(map.get("goodsSource"));					
				}
			}
		}
		
		//priceType商品查询 1：进货价,2:仓储中心进货价
		if( null != map.get("priceType") && !"".equals(map.get("priceType")) ){
			if("1".equals(map.get("priceType"))){
				// 起始进货价
				if( null != map.get("priceStart") && !"".equals(map.get("priceStart")) ){
					csGylGoodsVoPage.setPriceStart(Double.valueOf(map.get("priceStart")));
				}
				// 结束进货价
				if( null != map.get("priceEnd") && !"".equals(map.get("priceEnd")) ){
					csGylGoodsVoPage.setPriceEnd(Double.valueOf(map.get("priceEnd")));	
				}
			}else if("2".equals(map.get("priceType"))){
				//起始仓储中心进货价
				if( null != map.get("cczxInPriceStart") && !"".equals(map.get("cczxInPriceStart")) ){
					csGylGoodsVoPage.setCczxInPriceStart(Double.valueOf(map.get("cczxInPriceStart")));
				}
				// 结束仓储中心进货价
				if( null != map.get("cczxInPriceEnd") && !"".equals(map.get("cczxInPriceEnd")) ){
					csGylGoodsVoPage.setCczxInPriceEnd(Double.valueOf(map.get("cczxInPriceEnd")));
				}
			}			
		}
	
		//上架:1上架，2停售
		if( null != map.get("isOnsale") && !"".equals(map.get("isOnsale")) ){
			csGylGoodsVoPage.setIsOnsale(Integer.valueOf(map.get("isOnsale")));
		}
		
		//起始上架时间
		if( null != map.get("onsaleTimeStart") && !"".equals(map.get("onsaleTimeStart")) ){
			csGylGoodsVoPage.setOnsaleTimeStart(Long.valueOf(map.get("onsaleTimeStart")));	
		}
		//结束上架时间
		if( null != map.get("onsaleTimeEnd") && !"".equals(map.get("onsaleTimeEnd")) ){	
			csGylGoodsVoPage.setOnsaleTimeEnd(Long.valueOf(map.get("onsaleTimeEnd")));
		}

		csGylGoodsVoPage.setNowPage(Integer.valueOf(map.get("pageNum")));
		csGylGoodsVoPage.setPageSize(Integer.valueOf(map.get("pageSize")));
    	return csGylGoodsVoPage;
    	
    }
    
    
    private void addGysStaff(CsGysInfo csGysInfo) throws ServiceException {
    	CsGysStaff staff = new CsGysStaff();
		staff.setGysId(csGysInfo.getDmId());
		staff.setName(csGysInfo.getName());
		staff.setPhone(csGysInfo.getContactPhone());
		staff.setAccountType(new Byte("0"));
		//密码为身份证后6位
        String lpIdCard = csGysInfo.getLpIdCard();
        String pwd = lpIdCard.substring((lpIdCard.length() - 6));     
        staff.setPwd(MD5Util.getMD5("123456"));
        staff.setStatus(new Byte("0"));
        csGysStaffService.addVo(staff);
    }
}
