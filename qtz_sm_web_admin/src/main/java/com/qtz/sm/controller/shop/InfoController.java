package com.qtz.sm.controller.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.weaver.ArrayAnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.shop.page.ShopInfoOperationHistoryPage;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.service.ShopInfoOperationHistoryService;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.vo.ShopInfo;
import com.qtz.sm.shop.vo.ShopInfoOperationHistory;
import com.qtz.sm.shop.vo.ShopInfoVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * <p>Title:InfoController</p>
 * <p>Description:便利店基本信息Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016-04-26
 */
@Api(value = "/shop/info", description = "店基本信息操作")
@RestController("ShopInfoController")
@RequestMapping("/shop/info")
public class InfoController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(InfoController.class);
    /**
     * 注入便利店基本信息Service类
     */
    @Resource(name = "shopInfoServiceImpl")
    private ShopInfoService shopInfoService;
    
    @Resource(name = "csRegionsServiceImpl")
	private CsRegionsService csRegionsService;
    
    @Autowired
    private ShopInfoOperationHistoryService shopInfoOperationHistoryService;

    /**
     * 
     * @Description:查询便利店信息
     * @param token    token
     * @param shopId   便利店ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:20:04
     */
    @ApiOperation(value = "查询便利店信息",
            notes = "查询便利店信息",
            position = 0)
    @RequestMapping(value = "/getShopInfo", method = RequestMethod.GET)
    public void queryById(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店ID",required=true) @RequestParam Long shopId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            ShopInfoVo shopInfoVo = shopInfoService.findById(shopId);
            if (null == shopInfoVo) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
                RespHandler.respOK(shopInfoVo, response);
            }
        } catch (ServiceException se) {
            log.error("查询便利店基本信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店基本信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:查询一页便利店数据
     * @param token        token
     * @param shopManageId 所属便利店管理公司ID
     * @param params       查询参数
     * @param pageNum      页面游标
     * @param pageSize     页面大小
     * @param shopInfoPage 查询对象信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:20:17
     */
    @ApiOperation(value = "查询一页便利店数据",
            notes = "查询一页便利店数据",
            position = 1)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void queryPage(@RequestHeader("token") String token,
                          @ApiParam(value = "所属便利店管理公司ID", required = true) @RequestParam("shopManageId") Long shopManageId,
                          @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          @RequestBody ShopInfoPage shopInfoPage,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {

            //登陆检查
            checkLogin(token, response);
            
            shopInfoPage.setShopManageId(shopManageId);
            shopInfoPage.setNowPage(pageNum);
            shopInfoPage.setPageSize(pageSize);
            shopInfoPage.setOrderField("update_time");
            shopInfoPage.setOrderDirection(false);

            //分页查询
            Pager<ShopInfo, Long> pager = shopInfoService.query(shopInfoPage, ShopInfo.class);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询便利店列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }

    }

    /**
     * 
     * @Description:添加便利店，同时为便利店添加一个系统用户，和添加一个钱包
     * @param token    token
     * @param shopInfo 便利店信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:20:55
     */
    @ApiOperation(value = "添加便利店",
            notes = "添加便利店，同时为便利店添加一个系统用户，和添加一个钱包",
            position = 2)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
                    @ApiParam(value = "便利店信息对象", required = true) @RequestBody ShopInfo shopInfo,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(shopInfo);
            //信息填充
            Date date = new Date();
            shopInfo.setCreateTime(date);
            shopInfo.setUpdateTime(date);
            //先查询该便利店管理公司下的便利店名称是否已存在
            ShopInfo query = new ShopInfo();
            query.setShopManageId(shopInfo.getShopManageId());
            query.setName(shopInfo.getName());
            List<ShopInfo> list =  shopInfoService.findList(query);
            if(null != list && list.size()>0){
            	RespHandler.respError(RespMsg.shop_name_have, response);
            }else{
            	//添加
                shopInfoService.addVoStaffAddWallet(shopInfo);
                RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("添加便利店出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加便利店出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店信息，如果修改了手机号码，则必须同时修改它所关联系统用户的手机号码
     * @param token    token
     * @param shopInfo 便利店信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:21:08
     */
    @ApiOperation(value = "修改便利店信息",
            notes = "修改便利店信息，如果修改了手机号码，则必须同时修改它所关联系统用户的手机号码",
            position = 3)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
                       @ApiParam(value = "便利店信息对象", required = true) @RequestBody ShopInfo shopInfo,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {

            //获取登陆信息
            User user = getUserJsonp(token, response, request);
            //参数验证
            checkModVo(shopInfo);
            shopInfo.setUpdateTime(new Date());
            //先查询该便利店管理公司下的便利店名称是否已存在
            ShopInfo query = new ShopInfo();
            query.setShopManageId(shopInfo.getShopManageId());
            query.setName(shopInfo.getName());
            List<ShopInfo> list =  shopInfoService.findList(query);
            if(null != list && list.size()>0 && !list.get(0).getDmId().equals(shopInfo.getDmId())){
            	RespHandler.respError(RespMsg.shop_name_have, response);
            }else{
	            //1、当手机号码未修改时，则不更新用户信息
	            //2、当手机号码对应的用户不存在，则为它新建用户
	            //3、当修改了手机号码时，同时修改它所关联系统用户的手机号码
            	//4、先删除运营分类，再重新添加
            	//5、判断是否需要添加操作记录
            	//6、判断是否更改结算周期
            	//7、判断是否更换便利店名称
	            shopInfoService.modVoAndStaff(shopInfo,user);
	            RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("修改便利店信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店营业状态，便利店营业状态：0营业中，1停止营业
     * @param token    token
     * @param dmId     便利店营业信息ID
     * @param status   需要修改的营业状态
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:21:23
     */
    @ApiOperation(value = "修改便利店营业状态",
            notes = "修改便利店营业状态，便利店营业状态：0营业中，1停止营业",
            position = 4)
    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
    public void updateStatus(@RequestHeader("token") String token,
                       @ApiParam(value = "便利店营业信息ID" , required = true) @RequestParam Long dmId,
                       @ApiParam(value = "需要修改的营业状态", required = true) @RequestParam Integer status,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {

            //登陆检查
            checkLogin(token, response);

            ShopInfo shopInfo = new ShopInfo();
            shopInfo.setDmId(dmId);
            shopInfo.setStatus(status);
            shopInfo.setUpdateTime(new Date());
            
            ShopInfo query = shopInfoService.findVo(dmId, null);
            if(!StringUtil.isEmpty(query)){
            	shopInfoService.modVoNotNull(shopInfo);
                RespJsonPHandler.respOK(response, request);
            }else{
            	RespHandler.respError(RespMsg.not_found, response);
            }
        } catch (ServiceException se) {
            log.error("修改便利营业状态出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店营业状态出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店状态，便利店状态：0正常，1禁用
     * @param token    token
     * @param dmId     便利店ID
     * @param disabled 需要修改的便利店状态
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:21:36
     */
    @ApiOperation(value = "修改便利店状态",
            notes = "修改便利店状态，便利店状态：0正常，1禁用",
            position = 5)
    @RequestMapping(value = "/disabled", method = RequestMethod.GET)
    public void disabled(@RequestHeader("token") String token,
                         @ApiParam(value ="便利店ID", required = true) @RequestParam Long dmId,
                         @ApiParam(value = "状态0正常，1禁用", required = true) @RequestParam Integer disabled,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        try {

        	//获取登陆信息
            User user = getUserJsonp(token, response, request);

            ShopInfo shopInfo = new ShopInfo();
            shopInfo.setDmId(dmId);
            shopInfo.setDisabled(disabled);
            shopInfo.setUpdateTime(new Date());
            ShopInfo query = shopInfoService.findVo(dmId, null);
            if(!StringUtil.isEmpty(query)){
	            shopInfoService.updateStatus(shopInfo, user);
	            RespJsonPHandler.respOK(response, request);
            }else{
            	RespHandler.respError(RespMsg.not_found, response);
            }
        } catch (ServiceException se) {
            log.error("修改便利店状态出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店状态出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 
     * @Description:分页查询便利店操作记录
     * @param token
     * @param dmId  	便利店ID
     * @param pageNum
     * @param pageSize
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月17日 上午11:41:10
     */
    @ApiOperation(value = "分页查询便利店操作记录",
    		notes = "分页查询便利店操作记录",
    		position = 5)
    @RequestMapping(value = "/queryPageHistory", method = RequestMethod.GET)
    public void queryPageHistory(@RequestHeader("token") String token,
    		 @ApiParam(value = "便利店ID", required = true) @RequestParam Long dmId,
             @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
             @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	try {
    		
    		 //登陆检查
            checkLogin(token, response);
    		
    		ShopInfo shopInfo = new ShopInfo();
    		shopInfo.setDmId(dmId);
    		ShopInfo query = shopInfoService.findVo(dmId, null);
    		if(!StringUtil.isEmpty(query)){
    			ShopInfoOperationHistoryPage page = new ShopInfoOperationHistoryPage();
    			page.setShopId(dmId);
    			page.setNowPage(pageNum);
    			page.setPageSize(pageSize);
    			page.setOrderField("operate_on");
    			page.setOrderDirection(false);
                //分页查询
                Pager<ShopInfoOperationHistory, Long> pager = shopInfoOperationHistoryService.query(page, ShopInfoOperationHistory.class);
                List<ShopInfo> list2 = new ArrayList<>();
                list2.add(query);
                pager.setList2(list2);;
                RespJsonPHandler.respOK(pager, response, request);
    		}else{
    			RespHandler.respError(RespMsg.not_found, response);
    		}
    	} catch (ServiceException se) {
    		log.error("分页查询便利店操作记录出错！", se);
    		RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
    	} catch (Exception e) {
    		log.error("分页查询便利店操作记录出现系统错误！", e);
    		RespJsonPHandler.respServerError(response, request);
    	}
    }
    
    /**
     * 
     * @Description:添加便利店时获取便利店code
     * @param token    token
     * @param dmId     市级ID
     * @param shopManageId 便利店管理公司ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月15日 下午10:21:36
     */
    @ApiOperation(value = "添加便利店时获取便利店code",
    		notes = "添加便利店时获取便利店code",
    		position = 5)
    @RequestMapping(value = "/getCode", method = RequestMethod.GET)
    public void getCode(@RequestHeader("token") String token,
    		@ApiParam(value ="市级ID", required = true) @RequestParam Integer dmId,
    		@ApiParam(value = "便利店管理公司ID", required = true) @RequestParam Long shopManageId,
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	try {
    		 //登陆检查
            checkLogin(token, response);
    		
    		StringBuffer code = new StringBuffer();//code 是 所选市级电话区号+ 该市级下的便利店编号 + 1
        	CsRegions csRegions = csRegionsService.findVo(dmId, null);
	    	if(null != csRegions){
	    		code .append(csRegions.getCityCode());
	    	}else{
	    		code.append("0000");//默认北京市的区号
	    	}
	    	//查询属于该便利店管理公司的所在改市级下的所有便利店
	    	ShopInfo query = new ShopInfo();
			query.setShopManageId(shopManageId);
			query.setCityId(dmId);
	    	code.append(shopInfoService.findList(query).size()+1);
	    	RespHandler.respOK(code.toString(), response);
    	} catch (ServiceException se) {
    		log.error("获取便利店code出错！", se);
    		RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
    	} catch (Exception e) {
    		log.error("获取便利店code出现系统错误！", e);
    		RespJsonPHandler.respServerError(response, request);
    	}
    }


    /**
     * 
     * @Description:添加便利店时，数据验证
     * @param shopInfo  便利店信息
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:22:01
     */
    private void checkVo(ShopInfo shopInfo) throws ServiceException {
        if (StringUtils.isEmpty(shopInfo.getName())) {
            throw new ServiceException(ExceptionCode.SHOP_NAME_IS_NULL, "便利店名称为空！");
        }
        if (StringUtils.isEmpty(shopInfo.getMobile())) {
            throw new ServiceException(ExceptionCode.PHONE_IS_NULL, "便利店手机号码为空！");
        }
        if (!StringUtils.checkMobile(shopInfo.getMobile())) {
            throw new ServiceException(ExceptionCode.PHONE_FOMAT_ERROR, "便利店手机号码格式不对！");
        }
        if (null == shopInfo.getShopManageId()) {
            throw new ServiceException(ExceptionCode.SHOP_MANAGE_ID_IS_NULL, "关联的便利店管理公司ID为空！");
        }
        if (null == shopInfo.getCczxId()) {
            throw new ServiceException(ExceptionCode.CZZX_ID_NULL, "关联的仓储中心ID为空！");
        }
        if (null == shopInfo.getShopManageCategoryPropertyList() || shopInfo.getShopManageCategoryPropertyList().size() == 0) {
        	throw new ServiceException(ExceptionCode.SHOP_MANAGE_CATEGORY_ID_IS_NULL, "关联的经营范围ID为空！");
        }
        if (StringUtils.isEmpty(shopInfo.getLpName())) {
            throw new ServiceException(ExceptionCode.LPNAME_IS_NULL, "法人名称为空！");
        }
        if (StringUtils.isEmpty(shopInfo.getLpIdCard())) {
            throw new ServiceException(ExceptionCode.LPIDCARD_IS_NULL, "法人身份证为空！");
        }
        if (StringUtils.isEmpty(shopInfo.getLicence())) {
            throw new ServiceException(ExceptionCode.LICENCE_IS_NULL, "营业执照为空！");
        }
        if (StringUtils.isEmpty(shopInfo.getIdCardFront())) {
            throw new ServiceException(ExceptionCode.IDCARDFRONT_IS_NULL, "身份证正面照片为空！");
        }
        if (StringUtils.isEmpty(shopInfo.getIdCardBehind())) {
            throw new ServiceException(ExceptionCode.IDCARDBEHIND_IS_NULL, "身份证反面照片为空！");
        }
        if (null == shopInfo.getProvinceId()) {
            throw new ServiceException(ExceptionCode.PROVINCEID_IS_NULL, "省份地址为空！");
        }
        if (null == shopInfo.getCityId()) {
            throw new ServiceException(ExceptionCode.CITYID_IS_NULL, "城市地址为空！");
        }
        //便利店编号
        if (StringUtils.isEmpty(shopInfo.getCode())) {
//        String code = shopInfoService.createCode(shopInfo.getProvinceId(), shopInfo.getCityId());
	    	StringBuffer code = new StringBuffer();//code 是 所选市级电话区号+ 该市级下的便利店编号 + 1
	    	CsRegions csRegions = csRegionsService.findVo(shopInfo.getCityId(), null);
	    	if(null != csRegions){
	    		code .append(csRegions.getCityCode());
	    	}else{
	    		code.append("0000");//默认北京市的区号
	    	}
	    	//查询属于该便利店管理公司的所在改市级下的所有便利店
	    	ShopInfo query = new ShopInfo();
			query.setShopManageId(shopInfo.getShopManageId());
			query.setCityId(shopInfo.getCityId());
			
	    	code.append(shopInfoService.findList(query).size()+1);
	        shopInfo.setCode(code.toString());
		}
        if (null == shopInfo.getStatus()) {
            shopInfo.setStatus(0);
        }
        if (null == shopInfo.getDisabled()) {
            shopInfo.setDisabled(0);
        }
        if (StringUtils.isEmpty(shopInfo.getLpIdCard()) || !StringUtils.checkIDCard(shopInfo.getLpIdCard())) {
            throw new ServiceException(ExceptionCode.LPIDCARD_NOT_TRUE,"身份证格式不对！");
        }
    }
    
    
    /**
     * 
     * @Description:修改便利店时，数据验证
     * @param shopInfo  便利店信息
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:22:14
     */
    private void checkModVo(ShopInfo shopInfo) throws ServiceException {
        if (null == shopInfo.getDmId()) {
            throw new ServiceException(ExceptionCode.SHOP_ID_IS_NULL, "便利店ID不能为空.");
        }
        checkVo(shopInfo);
    }
}