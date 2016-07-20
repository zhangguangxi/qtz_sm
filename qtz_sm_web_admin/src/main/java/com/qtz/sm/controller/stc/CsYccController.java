package com.qtz.sm.controller.stc;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.qtz.sm.goods.service.GdGoodsCategroyRateService;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.service.GdGoodsSkuRateService;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;
import com.qtz.sm.session.service.SessionService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.stc.model.ResultBo;
import com.qtz.sm.stc.page.CczxGoodsPage;
import com.qtz.sm.stc.service.CczxGoodsService;
import com.qtz.sm.stc.service.CsCczxSplitPointService;
import com.qtz.sm.stc.service.CsYccAddressService;
import com.qtz.sm.stc.service.CsYccInfoService;
import com.qtz.sm.stc.service.CsYccStaffService;
import com.qtz.sm.stc.vo.CczxGoods;
import com.qtz.sm.stc.vo.CsCczxSplitPoint;
import com.qtz.sm.stc.vo.CsYccAddress;
import com.qtz.sm.stc.vo.CsYccInfo;
import com.qtz.sm.stc.vo.CsYccStaff;
import com.qtz.sm.store.page.CsCczxInfoPage;
import com.qtz.sm.store.service.CsCczxAddressService;
import com.qtz.sm.store.service.CsCczxInfoService;
import com.qtz.sm.store.service.CsCczxStaffService;
import com.qtz.sm.store.service.CsCczxStockService;
import com.qtz.sm.store.vo.CsCczxAddress;
import com.qtz.sm.store.vo.CsCczxInfo;
import com.qtz.sm.store.vo.CsCczxStaff;
import com.qtz.sm.store.vo.CsCczxStock;
import com.qtz.sm.supp.service.CsGysInfoService;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.service.WtCczxIncomeService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.vo.WtBankCard;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * Title:(云仓储管理公司控制类)<br/>
 * Description:(云仓储管理公司控制类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 谷一帅
 * @version v1.0 2016年4月26日
 */
@Controller
@RequestMapping(value = "/csycc/")
public class CsYccController extends BaseController {

    private static final LogTool log = LogTool.getInstance(CsYccController.class);


    @Autowired
    private CsYccInfoService csYccInfoService;
    
    @Autowired
    private CsYccAddressService csYccAddressService;
    
    @Autowired
    private CsCczxInfoService csCczxInfoService;
    
    @Autowired
    private CsCczxAddressService csCczxAddressService;
    
    @Autowired
	private WtBankCardService wtBankCardService;

	@Autowired
	private GdGoodsCategroyRateService rateService;
	
    @Autowired
    private GdGoodsSkuRateService gdGoodsSkuRateService;
    @Autowired
    private CsRegionsService csRegionsService;
    
    @Autowired
    private CczxGoodsService cczxGoodsService;
    
    @Autowired
	private GdGoodsService goodsService;
    
    @Autowired
   	private CsCczxStockService csCczxStockService;
    
    
    @Autowired
   	private CsCczxStaffService csCczxStaffService;
    
    @Autowired
   	private WtWalletService wtWalletService;
    
    @Autowired
   	private WtCczxIncomeService wtCczxIncomeService;
    
    @Autowired
   	private CsCczxSplitPointService csCczxSplitPointService;
    @Autowired
    private SessionService sessionService;
    
    @Autowired
    private  CsYccStaffService csYccStaffService;
    @Autowired
    private CsGysInfoService csGysInfoService;
    
    
    
    

    
    
    
    
    
    /**
     * 云仓储管理公司-云仓储-员工登录
     *
     * @param phone
     * @param pwd
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "loginYcc", method = RequestMethod.POST)
    public void loginYcc(@RequestBody Map<String,String> mapParamet, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
    		CsYccStaff staff = new CsYccStaff();
            staff.setPhone(mapParamet.get("name"));
            staff.setPwd(MD5Util.getMD5(mapParamet.get("pwd")));
            List<CsYccStaff> staffList = csYccStaffService.findList(staff);
            if (null != staffList && staffList.size() == 1) {
                staff = staffList.get(0);
                if (staff.getStatus().intValue() != 0) {
                    RespJsonPHandler.respError(RespMsg.user_seal_number, response, request);
                    return;
                } else {
                    Long yccId = staff.getYccId();
                    CsYccInfo csYccInfo = csYccInfoService.findVo(yccId, null);
                    User user = buildYccLoginUser(staff, csYccInfo);
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
     * 云仓储管理公司-云仓储-构建登录用户
     *
     * @param staff
     * @param CczxInfo
     * @return
     */							   
    private User buildYccLoginUser(CsYccStaff staff, CsYccInfo csYccInfo) {
        User user = new User();
        //所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
        user.setCompanyDmId(csYccInfo.getDmId());
        user.setCompanyName(csYccInfo.getName());
        user.setCompanyType(3);
        user.setPwd(null);
        user.setLpName(csYccInfo.getLpName());
        user.setName(csYccInfo.getName());
        user.setStatus(Integer.valueOf(staff.getStatus()));
        user.setPhone(staff.getPhone());
        user.setDmId(staff.getDmId());
        return user;
    }
    
    
    
    
    
    
    
    
    
    
    /**
     * 云仓储管理公司-仓储中心列表
     * @param token
     * @param params
     * @param pageNum
     * @param pageSize
     * @param request
     * @param response
     */
    @ApiOperation(value = "云仓储管理公司-仓储中心列表-分页",
            notes = "云仓储管理公司-仓储中心列表-分页",
            position = 0)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void queryPage(@RequestHeader("token") String token,
    		@RequestBody CsCczxInfo csCczxInfo , HttpServletRequest request,HttpServletResponse response) {
			try {
				//登陆检查
	            checkLogin(token, response);
	            CsCczxInfoPage csCczxInfoPage = new CsCczxInfoPage();
	            if( null != csCczxInfo.getEstablishTimeStart() && !"".equals( csCczxInfo.getEstablishTimeStart() )){
	            	Date timeStart = DateUtil.strToDate(csCczxInfo.getEstablishTimeStart());
	            	csCczxInfoPage.setStartTime(timeStart);
	            }
	            if(  null != csCczxInfo.getEstablishTimeEnd() && !"".equals( csCczxInfo.getEstablishTimeEnd() )){
	            	Date timeEnd = DateUtil.strToDate(csCczxInfo.getEstablishTimeEnd());
	            	csCczxInfoPage.setEndTime(timeEnd);
	            }
	            csCczxInfoPage.setContactPhone(csCczxInfo.getContactPhone());
	            csCczxInfoPage.setName(csCczxInfo.getName());
	            csCczxInfoPage.setIdentifier(csCczxInfo.getIdentifier());
	            csCczxInfoPage.setNowPage(csCczxInfo.getPageNum());
	            csCczxInfoPage.setPageSize(csCczxInfo.getPageSize());
	            Pager<CsCczxInfo, Long> pager = csCczxInfoService.query(csCczxInfoPage, CsCczxInfo.class);
				RespJsonPHandler.respOK(pager, response, request);
			} catch (ServiceException e) {
				log.error("【仓储中心列表-分页】列表出错！", e);
	            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
			} catch (ActionException e) {
				log.error("【仓储中心列表-分页】用户未登录："+ e);
				RespHandler.respError(RespMsg.user_not_login, response);
			}    	
    }
    
    /**
     * 云仓储管理公司-我的信息-公司信息
     *
     1* @param dmId    云仓储公司ID
     2* @param request
     3* @param response
     4* @throws IOException
     */
    @ApiOperation(value = "云仓储管理公司-我的信息-公司信息",
            notes = "云仓储管理公司-我的信息-公司信息",
            position = 1)
    @RequestMapping(value = "queryCompanyInfo", method = RequestMethod.POST)
    public void queryCompanyInfo(@RequestHeader("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
    		 Map<String, Object> resMap = new HashMap<String, Object>();
    		 User user = getUserJsonp(token, response, request);
    		 CsYccInfo c = new CsYccInfo();
    		 c.setDmId(user.getCompanyDmId());
    		//登陆检查
//            checkLogin(token, response);
    		
            CsYccInfo  csYccInfo = csYccInfoService.findVo(c.getDmId(),null);
    		CsYccAddress csYccAddress = csYccAddressService.findVoByYccId(c.getDmId());
    		WtBankCard wtBankCardInfo = wtBankCardService.queryWtBankCardGroupInfo(c.getDmId(),CompanyType.CloudStorage.value());
    		Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(csYccAddress.getProvinceId(), csYccAddress.getCityId(), csYccAddress.getCountyId(),
    				csYccAddress.getTownId());

    		 
    		 resMap.put("csYccInfo",csYccInfo );
    		 resMap.put("csYccAddress",csYccAddress );
    		 resMap.put("wtBankCardInfoJson",wtBankCardInfo );
    		 
    		 resMap.put("provinceId",csYccAddress.getProvinceId());
             resMap.put("provinceName",csRegionsMap.get("province")!=null?csRegionsMap.get("province").getName():"");
             resMap.put("cityId",csYccAddress.getCityId());
             resMap.put("cityName",csRegionsMap.get("city")!=null?csRegionsMap.get("city").getName():"");
             resMap.put("countyId",csYccAddress.getCountyId());
             resMap.put("countyName",csRegionsMap.get("area")!=null?csRegionsMap.get("area").getName():"");
             resMap.put("townId",csYccAddress.getTownId()!=null?csYccAddress.getTownId():"");
             resMap.put("townName",csRegionsMap.get("town")!=null?csRegionsMap.get("town").getName():"");
    		 
    		 RespHandler.respOK(resMap, response);
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-我的信息-公司信息】ServiceException异常："+e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} 
    }
    

    /**
     * 云仓储管理公司-仓储中心管理-新增仓储
     *
     1* @param csYccInfo   仓储类对象
     2* @param request
     3* @param response
     4* @throws IOException
     */
    @ApiOperation(value = "云仓储管理公司-仓储中心管理-新增仓储",
            notes = "云仓储管理公司-仓储中心管理-新增仓储",
            position = 2)    
    @RequestMapping(value = "addWarehousing", method = RequestMethod.POST)
    public void addWarehousing(@RequestHeader("token") String token, @RequestBody Map<String,String> map,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
    	CsCczxInfo csCczxInfo = new CsCczxInfo();
    	CsCczxAddress csCczxAddress = new CsCczxAddress();
    	 String identifier = "";
		try {
			identifier = csGysInfoService.findNextIdentifier(Integer.valueOf(map.get("provinceId").toString()),Integer.valueOf(map.get("cityId").toString()) );
		} catch (NumberFormatException | ServiceException e2) {

		}
    	if(null != map.get("name") && !"".equals(map.get("name"))){
    		csCczxInfo.setName(map.get("name"));
    	}
	   	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	   	 if( null != map.get("establishTime") && !"".equals(map.get("establishTime"))){
	   		Date establishTime = null;
			try {
				establishTime = df.parse(map.get("establishTime"));
			} catch (ParseException e1) {
	
			}
			csCczxInfo.setEstablishTime(establishTime);  
	   	 }
  	   
		if( null != identifier && !"".equals(identifier)){
			csCczxInfo.setIdentifier(identifier);
		}
		if( null != map.get("contactPhone") && !"".equals(map.get("contactPhone"))){
			csCczxInfo.setContactPhone(map.get("contactPhone"));
		}
		if( null != map.get("ettlementCycle") && !"".equals(map.get("ettlementCycle"))){
			csCczxInfo.setEttlementCycle(Integer.valueOf(map.get("ettlementCycle")));  
		}
		if( null != map.get("deliveryTime") && !"".equals(map.get("deliveryTime"))){
			csCczxInfo.setDeliveryTime(Integer.valueOf(map.get("deliveryTime")));    
		}
		if( null != map.get("restockCycle") && !"".equals(map.get("restockCycle"))){
			csCczxInfo.setRestockCycle(Integer.valueOf(map.get("restockCycle")));
		}
		if( null != map.get("distributionRadius") && !"".equals(map.get("distributionRadius"))){
			csCczxInfo.setDistributionRadius(map.get("distributionRadius"));
		}
		if( null != map.get("lpName") && !"".equals(map.get("lpName"))){
			csCczxInfo.setLpName(map.get("lpName"));
		}
		if( null != map.get("lpIdCard") && !"".equals(map.get("lpIdCard"))){
			csCczxInfo.setLpIdCard(map.get("lpIdCard"));
		}
		if( null != map.get("idCardFront") && !"".equals(map.get("idCardFront"))){
			csCczxInfo.setIdCardFront(map.get("idCardFront"));
		}
		if( null != map.get("idCardBehind") && !"".equals(map.get("idCardBehind"))){
			csCczxInfo.setIdCardBehind(map.get("idCardBehind"));
		}
		if( null != map.get("licence") && !"".equals(map.get("licence"))){
			csCczxInfo.setLicence(map.get("licence"));
		}
		if( null != map.get("joinFee") && !"".equals(map.get("joinFee"))){
			csCczxInfo.setJoinFee(Double.valueOf(map.get("joinFee")));				
		}
		if( null != map.get("fenRunPoint") && !"".equals(map.get("fenRunPoint"))){
			csCczxInfo.setFenRunPoint(Double.valueOf(map.get("fenRunPoint")));
		}
		if( null != map.get("provinceId") && !"".equals(map.get("provinceId"))){
			csCczxAddress.setProvinceId(Integer.valueOf(map.get("provinceId")));
		}
		if( null != map.get("cityId") && !"".equals(map.get("cityId"))){
			csCczxAddress.setCityId(Integer.valueOf(map.get("cityId")));
		}
		if( null != map.get("countyId") && !"".equals(map.get("countyId"))){
			csCczxAddress.setCountyId(Integer.valueOf(map.get("countyId")));
		}
		if( null != map.get("address") && !"".equals(map.get("address"))){
			csCczxAddress.setAddress(map.get("address"));
		}
    	
    	//参数验证
        try {       	
        	//登陆检查
            checkLogin(token, response);
            //仓储中心数据检查
			checkVo(csCczxInfo);
			//仓储中心地址检查
			checkCczxAddressVo(csCczxAddress);

			CsCczxInfo info = csCczxInfoService.findVoByCsCczxInfoName(csCczxInfo.getName());
			if( info != null ){
				RespHandler.respError(RespMsg.ycc_name_exist, response);
			}else{	
//				csCczxInfo.setDmId(new Long(((FifteenLongId) SpringContextHolder.getApplicationContext().getBean(FifteenLongIdImpl.class)).nextId()));
				csCczxInfoService.addCczxInfo(csCczxInfo, csCczxAddress);
//				//添加仓储中心地址
//				csCczxAddress.setCczxId(csCczxInfo.getDmId());
//				csCczxAddress.setFullAddress(csRegionsService.getFullAddressByIds(csCczxAddress.getProvinceId(),csCczxAddress.getCityId(), csCczxAddress.getCountyId(),null,null)+csCczxAddress.getFullAddress());
//				csCczxAddressService.addVo(csCczxAddress);
//				//添加员工信息start
//				addCczxStaff(csCczxInfo);
//				//添加员工信息end
//				//添加钱包信息
//				wtWalletService.addCczxWallet(csCczxInfo.getDmId(),csCczxInfo.getName());

				RespHandler.respOK(response);
			}
			
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-仓储中心管理-新增仓储】ServiceException异常："+e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (ActionException e) {
			log.error("【云仓储管理公司-仓库中心管理-编辑】用户未登录："+ e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}

    }
    
    
    /**
     * 云仓储管理公司-仓库中心管理-详情
     *
     1* @param dmId    云仓储公司ID
     2* @param request
     3* @param response
     4* @throws IOException
     */
    @ApiOperation(value = "云仓储管理公司-仓库中心管理-详情",
            notes = "云仓储管理公司-仓库中心管理-详情",
            position = 3)
    @RequestMapping(value = "queryWarehousingInfo", method = RequestMethod.POST)
    public void queryWarehousingInfo(@RequestHeader("token") String token,@RequestBody CsCczxInfo c, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	try {
    		 Map<String, Object> resMap = new HashMap<String, Object>();
//    		 User user = getUserJsonp(token, response, request);
     		//登陆检查
//             checkLogin(token, response);
//    		 c.setDmId(user.getCompanyDmId());
             CsCczxInfo  csCczxInfo = csCczxInfoService.findVo(c.getDmId(), null);
             CsCczxAddress CsCczxAddressInfo   = csCczxAddressService.findVoByCczxId(c.getDmId());
             WtBankCard wtBankCardInfo = wtBankCardService.queryWtBankCardGroupInfo(c.getDmId(),CompanyType.CloudStorage.value());

             if(null != csCczxInfo){
//            	 JSONObject jsonCsYccInfo = JSONObject.fromObject(csCczxInfo); 
            	 resMap.put("csCczxInfo",csCczxInfo );
             }
             
             if( null != CsCczxAddressInfo){

         		 resMap.put("csCczxAddress",CsCczxAddressInfo );
             }
           //省、市、区、镇
             Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(CsCczxAddressInfo.getProvinceId(), CsCczxAddressInfo.getCityId(), CsCczxAddressInfo.getCountyId(),
            		 CsCczxAddressInfo.getTownId());

            		
             if( null != wtBankCardInfo){
            	 resMap.put("wtBankCardInfoJson",wtBankCardInfo );
             }
     		 
     		 
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
			log.error("【云仓储管理公司-仓库中心管理-详情】ServiceException异常："+e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
    }
    
    
    /**
     * 云仓储管理公司-仓库中心管理-编辑
     1* @param csYccInfo  仓储类对象
     2* @param request
     3* @param response
     */
    @ApiOperation(value = "云仓储管理公司-仓库中心管理-编辑",
            notes = "云仓储管理公司-仓库中心管理-编辑",
            position = 4)
    @RequestMapping(value = "updateWarehousingInfo", method = RequestMethod.POST)
    public void updateWarehousingInfo(@RequestHeader("token") String token, @RequestBody Map<String,String> map,
    		HttpServletRequest request, HttpServletResponse response){
    	CsCczxInfo csCczxInfo = new CsCczxInfo();
    	CsCczxAddress csCczxAddress = new CsCczxAddress();	
    	try {
    		String identifier = "";
    		
    			identifier = csGysInfoService.findNextIdentifier(Integer.valueOf(map.get("provinceId").toString()),Integer.valueOf(map.get("cityId").toString()) );
    		
    	   	User user = getUserJsonp(token, response, request);
        	csCczxInfo.setDmId(Long.valueOf(map.get("dmId")));
        	
        	csCczxInfo.setName(map.get("name"));
    	   	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
    	   	 if( null != map.get("establishTime") && !"".equals(map.get("establishTime"))){
    	   		Date establishTime = null;
    			try {
    				establishTime = df.parse(map.get("establishTime"));
    			} catch (ParseException e1) {
    	
    			}
    			csCczxInfo.setEstablishTime(establishTime);  
    	   	 }
	   	   
			if( null != identifier && !"".equals(identifier)){
				csCczxInfo.setIdentifier(identifier);
			}
			if( null != map.get("contactPhone") && !"".equals(map.get("contactPhone"))){
				csCczxInfo.setContactPhone(map.get("contactPhone"));
			}
			if( null != map.get("ettlementCycle") && !"".equals(map.get("ettlementCycle"))){
				csCczxInfo.setEttlementCycle(Integer.valueOf(map.get("ettlementCycle")));  
			}
			if( null != map.get("deliveryTime") && !"".equals(map.get("deliveryTime"))){
				csCczxInfo.setDeliveryTime(Integer.valueOf(map.get("deliveryTime")));    
			}
			if( null != map.get("restockCycle") && !"".equals(map.get("restockCycle"))){
				csCczxInfo.setRestockCycle(Integer.valueOf(map.get("restockCycle")));
			}
			if( null != map.get("distributionRadius") && !"".equals(map.get("distributionRadius"))){
				csCczxInfo.setDistributionRadius(map.get("distributionRadius"));
			}
			if( null != map.get("lpName") && !"".equals(map.get("lpName"))){
				csCczxInfo.setLpName(map.get("lpName"));
			}
			if( null != map.get("lpIdCard") && !"".equals(map.get("lpIdCard"))){
				csCczxInfo.setLpIdCard(map.get("lpIdCard"));
			}
			if( null != map.get("idCardFront") && !"".equals(map.get("idCardFront"))){
				csCczxInfo.setIdCardFront(map.get("idCardFront"));
			}
			if( null != map.get("idCardBehind") && !"".equals(map.get("idCardBehind"))){
				csCczxInfo.setIdCardBehind(map.get("idCardBehind"));
			}
			if( null != map.get("licence") && !"".equals(map.get("licence"))){
				csCczxInfo.setLicence(map.get("licence"));
			}
			if( null != map.get("joinFee") && !"".equals(map.get("joinFee"))){
				csCczxInfo.setJoinFee(Double.valueOf(map.get("joinFee")));				
			}
			if( null != map.get("fenRunPoint") && !"".equals(map.get("fenRunPoint"))){
				csCczxInfo.setFenRunPoint(Double.valueOf(map.get("fenRunPoint")));
			}
			if( null != map.get("provinceId") && !"".equals(map.get("provinceId"))){
				csCczxAddress.setProvinceId(Integer.valueOf(map.get("provinceId")));
			}
			if( null != map.get("cityId") && !"".equals(map.get("cityId"))){
				csCczxAddress.setCityId(Integer.valueOf(map.get("cityId")));
			}
			if( null != map.get("countyId") && !"".equals(map.get("countyId"))){
				csCczxAddress.setCountyId(Integer.valueOf(map.get("countyId")));
			}
			if( null != map.get("address") && !"".equals(map.get("address"))){
				csCczxAddress.setAddress(map.get("address"));
			}else{
				csCczxAddress.setAddress("");
			}
	
    		//登陆检查
            checkLogin(token, response);
    		//仓储信息，数据验证
    		checkVo(csCczxInfo);
    		//修改仓储时，数据验证
			checkModVo(csCczxInfo);
			//仓储地址，数据验证
			checkCczxAddressVo(csCczxAddress);
			
			CsCczxInfo cczxEntity = csCczxInfoService.findVo(csCczxInfo.getDmId(), null);
			CsCczxAddress csCczxAddressEntity =csCczxAddressService.findVoByCczxId(csCczxInfo.getDmId());
			
			if(null == csCczxAddressEntity){
				RespHandler.respError(RespMsg.ycc_addRess_not_exist, response);
			}else{
					
				
				//修改员工信息
				CsCczxStaff staff = new CsCczxStaff();
				staff.setCczxId(csCczxInfo.getDmId());
				staff.setPhone(csCczxInfo.getContactPhone());
	            List<CsCczxStaff> staffList = csCczxStaffService.findList(staff);
				
	          //当前仓储中心没有对应手机号码的用户，则新建
	            if (null == staffList || staffList.size() == 0) {
	                /*为便利店添加用户*/
	            	addCczxStaff(csCczxInfo);
	            } else {
	                //手机号码有变更
	                if (!csCczxInfo.getContactPhone().equals(cczxEntity.getContactPhone())) {
	                    staff = staffList.get(0);
	                    //修改手机号码
	                    staff.setPhone(csCczxInfo.getContactPhone());
	                    csCczxStaffService.modVoNotNull(staff);
	                }
	            }	
	            
	          //修改仓储信息
				csCczxInfoService.modVoNotNull(csCczxInfo);
				
				csCczxAddressEntity.setProvinceId(csCczxAddress.getProvinceId());
				csCczxAddressEntity.setCityId(csCczxAddress.getCityId());
				csCczxAddressEntity.setCountyId(csCczxAddress.getCountyId());
				csCczxAddressEntity.setTownId(csCczxAddress.getTownId());
				csCczxAddressEntity.setAddress(csCczxAddress.getAddress()!=null?map.get("address"):"");
				csCczxAddressEntity.setFullAddress(csRegionsService.getFullAddressByIds(csCczxAddress.getProvinceId(),csCczxAddress.getCityId(), csCczxAddress.getCountyId(),null,"")+csCczxAddress.getAddress());

				//修改仓储地址
				csCczxAddressService.modVoNotNull(csCczxAddressEntity);
				//修改钱包结算周期
				if(csCczxInfo.getEttlementCycle() != cczxEntity.getEttlementCycle()){
					if( null != csCczxInfo.getEttlementCycle()){
						wtCczxIncomeService.modSettlementTime(csCczxInfo.getDmId(),csCczxInfo.getEttlementCycle());
					}
					
				}
				RespHandler.respOK(response);
			}
			
			
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-仓库中心管理-编辑】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (IOException e) {
			log.error("【云仓储管理公司-仓库中心管理-编辑】IOException异常："+ e);
			RespJsonPHandler.respServerError(response, request);
		} catch (ActionException e) {
			log.error("【云仓储管理公司-仓库中心管理-编辑】用户未登录："+ e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
    }
    
    
    /**
     * 云仓储管理公司-仓库中心管理-启用，禁用
     * @param token
     * @param c
     * @param request
     * @param response
     * @throws IOException
     */
    @ApiOperation(value = "云仓储管理公司-仓库中心管理-启用，禁用",
            notes = "云仓储管理公司-仓库中心管理-启用，禁用",
            position = 3)
    @RequestMapping(value = "updateCczxStatus", method = RequestMethod.POST)
    public void updateCczxStatus(@RequestHeader("token") String token,@RequestBody CsCczxInfo c, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	try {
			csCczxInfoService.modVoNotNull(c);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-仓库中心管理-启用，禁用】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
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
            position = 5)
	@RequestMapping(value = "getTree", method = RequestMethod.POST)
	public void getTree(HttpServletResponse response, HttpServletRequest request) throws IOException{
		//取得树形结构
		try {
			Integer rateType = CompanyType.CloudStorage.value();
			List<GdGoodsCategroyRateBo> childs = rateService.findTree(0L, rateType);
			
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
            position = 6)
	@RequestMapping(value = "update", method = RequestMethod.POST,consumes="application/json")
	public void update(@RequestHeader("token") String token,@ApiParam("分类溢价率集合") @RequestBody ResultBo result,HttpServletResponse response,
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
     * 云仓储管理公司-商品管理-商品库-便利店进货价议价
     * @param token
     * @param gdGoodsSkuRate
     * @param request
     * @param response
     */
	@ApiOperation(value = "云仓储管理公司-商品管理-商品库-便利店进货价议价",
            notes = "云仓储管理公司-商品管理-商品库-便利店进货价议价",
            position = 7)
    @RequestMapping(value = "goodsCczxBldglPrice", method = RequestMethod.POST)
    public void goodsCczxBldglPrice(@RequestHeader("token") String token,@RequestBody Map<String,String> map,
    		HttpServletRequest request, HttpServletResponse response)throws IOException{
		 GdGoodsSkuRate gdGoodsSkuRate = new GdGoodsSkuRate();
    	try {
    		gdGoodsSkuRate.setDmId(Long.valueOf(map.get("dmId")));
    		gdGoodsSkuRate.setSkuId(Long.valueOf(map.get("skuId")));
    		gdGoodsSkuRate.setYccglPrice(Double.valueOf(map.get("yccglPrice")));
    		//登陆检查
            checkLogin(token, response);
//    		checkModGdGoodsSkuRateVo(gdGoodsSkuRate);
//    		checkGdGoodsSkuRateVo(gdGoodsSkuRate);
//    		GdGoodsSkuRate gdSkuRate = gdGoodsSkuRateService.findVo(gdGoodsSkuRate.getDmId(), null);
    		
			gdGoodsSkuRateService.modVoNotNull(gdGoodsSkuRate);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-商品管理-商品库-便利店进货价议价】ServiceException异常："+ e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (ActionException e) {
			log.error("【云仓储管理公司-商品管理-商品库-便利店进货价议价】用户未登录："+ e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
    }
	
	
	/**
	 * 云仓储管理公司-商品管理 -商品库-分页
	 * @param token
	 * @param cczxGoods
	 * @param pageNum
	 * @param pageSize
	 * @param request
	 * @param response
	 */
	@ApiOperation(value = "云仓储管理公司-商品管理 -商品库-分页",
            notes = "云仓储管理公司-商品管理 -商品库-分页",
            position = 8)
    @RequestMapping(value = "/goodsCczxPage", method = RequestMethod.POST)
	public void goodsCczxPage(@RequestHeader("token") String token,
			 @RequestBody CczxGoods cczxGoods, HttpServletRequest request,HttpServletResponse response)throws IOException{	
		CczxGoodsPage cczxGoodsPage = new CczxGoodsPage();	
		cczxGoodsPage.setNowPage(cczxGoods.getPageNum());
		cczxGoodsPage.setPageSize(cczxGoods.getPageSize());
		if( null != cczxGoods.getOnsaleTimeStart() && !"".equals( cczxGoods.getOnsaleTimeStart() ) ){
			Long timeStart = DateUtil.getStrDataTimes(cczxGoods.getOnsaleTimeStart());
			cczxGoods.setOnsaleTimeStart(timeStart.toString());
		}
		if( null != cczxGoods.getOnsaleTimeEnd() && !"".equals( cczxGoods.getOnsaleTimeEnd() ) ){
			Long timeEnd = DateUtil.getStrDataTimes(cczxGoods.getOnsaleTimeEnd());
			cczxGoods.setOnsaleTimeEnd(timeEnd.toString());
		}

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
			log.error("【云仓储管理公司-商品管理 -商品库-分页】列表出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
		
		
	}
	
	/**
	 * 云仓储管理公司-商品管理 -商品库-商品详情
	 * @param sid
	 * @param goodsId
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "云仓储管理公司-商品管理 -商品库-商品详情",
            notes = "云仓储管理公司-商品管理 -商品库-商品详情",
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
	
	/**
	 * 云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置",
            notes = "云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置",
            position = 10)
	@RequestMapping(value = "setUpThe", method = RequestMethod.POST)
	public void setUpThe(@RequestHeader("token") String token, @RequestBody CsCczxSplitPoint csCczxSplitPoint,
			HttpServletRequest request,HttpServletResponse response)throws IOException {
		try {
			User user = getUserJsonp(token, response, request);
			csCczxSplitPoint.setCsCczxId(user.getCompanyDmId());
//        	csCczxInfo.setDmId(Long.valueOf(map.get("dmId")));
			CsCczxSplitPoint csCczxSplitPointEntity = csCczxSplitPointService.findByCczxIdVo(csCczxSplitPoint.getCsCczxId());
			
			if(csCczxSplitPointEntity == null ){
				csCczxSplitPointService.addVo(csCczxSplitPoint);
			}else{
				Long id = csCczxSplitPointEntity.getDmId();
				csCczxSplitPoint.setDmId(id);
//				csCczxSplitPoint.setSplitPoint(csCczxSplitPoint.getSplitPoint());
				csCczxSplitPointService.modVoNotNull(csCczxSplitPoint);
			}
//			CsCczxSplitPoint a = csCczxSplitPointService.findByCczxIdVo(csCczxSplitPoint.getCsCczxId());
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置】出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
		
	}
	
	/**
	 * 云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置(已设置)",
            notes = "云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置(已设置)",
            position = 10)
	@RequestMapping(value = "querySetUpThe", method = RequestMethod.POST)
	public void querySetUpThe(@RequestHeader("token") String token, @RequestBody CsCczxSplitPoint csCczxSplitPoint,
			HttpServletRequest request,HttpServletResponse response)throws IOException {
		try {
			User user = getUserJsonp(token, response, request);
			csCczxSplitPoint.setCsCczxId(user.getCompanyDmId());
			CsCczxSplitPoint csCczxSplitPointEntity = csCczxSplitPointService.findByCczxIdVo(csCczxSplitPoint.getCsCczxId());
			RespHandler.respOK(csCczxSplitPointEntity,response);
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-仓储中心管理-仓储中心设置-分成点设置】出错！", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorMessage(), response, request);
		}
		
	}
	
    
	
	
	
	
    /**
     * 云仓储管理公司-云仓储-新增仓储
     *
     1* @param csYccInfo   仓储类对象
     2* @param request
     3* @param response
     4* @throws IOException
     */
    @ApiOperation(value = "云仓储管理公司-云仓储-新增仓储",
            notes = "云仓储管理公司-云仓储-新增仓储",
            position = 11)    
    @RequestMapping(value = "addYccInfo", method = RequestMethod.POST)
    public void addYccInfo( @RequestBody Map<String,String> map,
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
    	CsYccInfo csYccInfo = new CsYccInfo();
    	CsYccAddress csYccAddress = new CsYccAddress();
    	String identifier = "";
		
		try {
			identifier = csGysInfoService.findNextIdentifier(Integer.valueOf(map.get("provinceId").toString()),Integer.valueOf(map.get("cityId").toString()) );
		} catch (NumberFormatException | ServiceException e2) {
			log.error("【云仓储管理公司-云仓储-新增仓储初始化】ServiceException异常："+e2);
		}
    	csYccInfo.setName(map.get("name"));
    	 DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
    	 Date establishTime = null;
		try {
			establishTime = df.parse(map.get("establishTime"));
		} catch (ParseException e1) {

		}
		csYccInfo.setIdentifier(identifier);
		csYccInfo.setContactPhone(map.get("contactPhone"));
		csYccInfo.setEstablishTime(establishTime);    
		csYccInfo.setEttlementCycle(Integer.valueOf(map.get("ettlementCycle")));  
		csYccInfo.setDeliveryTime(Integer.valueOf(map.get("deliveryTime")));    
		csYccInfo.setRestockCycle(Integer.valueOf(map.get("restockCycle")));
		csYccInfo.setDistributionRadius(map.get("distributionRadius"));
		csYccInfo.setLpName(map.get("lpName"));
		csYccInfo.setLpIdCard(map.get("lpIdCard"));
		csYccInfo.setIdCardFront(map.get("idCardFront"));
		csYccInfo.setIdCardBehind(map.get("idCardBehind"));
		csYccInfo.setLicence(map.get("licence"));
		csYccInfo.setStatus(0);
//    	csCczxInfo.setJoinFee(Double.valueOf(map.get("joinFee")));
//    	csCczxInfo.setFenRunPoint(Double.valueOf(map.get("fenRunPoint")));
//    	
		csYccAddress.setProvinceId(Integer.valueOf(map.get("provinceId")));
		csYccAddress.setCityId(Integer.valueOf(map.get("cityId")));
		csYccAddress.setCountyId(Integer.valueOf(map.get("countyId")));
		csYccAddress.setFullAddress(map.get("fullAddress"));
    	
    	//参数验证
        try {       	

        	csYccInfoService.addYccInfo(csYccInfo,csYccAddress);
        	RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error("【云仓储管理公司-仓储中心管理-新增仓储】ServiceException异常："+e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}

    }
	
	
	
    /**
     * 仓储信息，数据验证
     * @param csYccInfo 仓储对象
     * @throws ServiceException
     */
    private void checkVo(CsCczxInfo csCczxInfo) throws ServiceException {
        if (StringUtils.isEmpty(csCczxInfo.getName())) {
            throw new ServiceException(ExceptionCode.CS_YCC_NAME_IS_NULL, "仓储名称不能为空.");
        }
        
        if (StringUtils.isEmpty(csCczxInfo.getLicence())) {
            throw new ServiceException(ExceptionCode.CS_YCC_LICENCE_IS_NULL, "营业执照链接不能为空.");
        }
        if (StringUtils.isEmpty(csCczxInfo.getIdCardFront())) {
            throw new ServiceException(ExceptionCode.CS_YCC_IDCARDFRONT_IS_NULL, "身份证正面不能为空.");
        }
        if (StringUtils.isEmpty(csCczxInfo.getIdCardBehind())) {
            throw new ServiceException(ExceptionCode.CS_YCC_IDCARDBEHIND_IS_NULL, "身份证反面不能为空.");
        }
        if (null == csCczxInfo.getStatus()) {
        	csCczxInfo.setStatus(new Byte("0"));
        }

    }
    
    /**
     * 仓储地址，数据验证
     * @param csYccInfo 仓储对象
     * @throws ServiceException
     */
    private void checkCczxAddressVo( CsCczxAddress csCczxAddress) throws ServiceException {
        if (null == csCczxAddress.getProvinceId()) {
            throw new ServiceException(ExceptionCode.CS_YCC_NAME_IS_NULL, "省ID不能为空.");
        }
        
        if (null == csCczxAddress.getCityId()) {
            throw new ServiceException(ExceptionCode.CS_YCC_CITY_ID_IS_NULL, "市ID不能为空.");
        }
        if (null == csCczxAddress.getCountyId()) {
            throw new ServiceException(ExceptionCode.CS_YCC_COUNTY_ID_IS_NULL, "县ID不能为空.");
        }
//        if (null == csCczxAddress.getTownId()) {
//            throw new ServiceException(ExceptionCode.CS_YCC_TOWN_ID_IS_NULL, "镇,街道ID不能为空.");
//        }
    }
    
    /**
     * 修改仓储时，数据验证
     *
     * @param shopManage 便利店管理公司对象
     * @throws ServiceException
     */
    private void checkModVo( CsCczxInfo csCczxInfo) throws ServiceException {
        if (null == csCczxInfo.getDmId()) {
            throw new ServiceException("仓库公司ID为空！");
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

    	if (null == gdGoodsSkuRate.getBldglPrice() ||   gdGoodsSkuRate.getBldglPrice() <= 0) {
   		 	throw new ServiceException(ExceptionCode.CS_GYL_GYLPRICE_IS_NULL, "仓储中心进货价不能为空或者小于等于0.");
       }
    }
    
    
    private void addCczxStaff(CsCczxInfo csCczxInfo ) throws ServiceException{
    	CsCczxStaff staff = new CsCczxStaff();
		staff.setCczxId(csCczxInfo.getDmId());
		staff.setName(csCczxInfo.getName());
		staff.setPhone(csCczxInfo.getContactPhone());
		staff.setAccountType(new Byte("0"));
		//密码为身份证后6位
        String lpIdCard = csCczxInfo.getLpIdCard();
        String pwd = lpIdCard.substring((lpIdCard.length() - 6));     
        staff.setPwd(MD5Util.getMD5("123456"));
        staff.setStatus(new Byte("0"));
		csCczxStaffService.addVo(staff);
    	
    }

}
