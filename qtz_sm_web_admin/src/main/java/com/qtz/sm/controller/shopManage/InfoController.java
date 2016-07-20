package com.qtz.sm.controller.shopManage;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shopManage.page.ShopManagePage;
import com.qtz.sm.shopManage.service.ShopManageService;
import com.qtz.sm.shopManage.vo.ShopManage;
import com.qtz.sm.shopManage.vo.ShopManageVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * <p>Title:InfoController</p>
 * <p>Description:便利店管理公司Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016-04-26
 */
@Api(value = "/shop/manage", description = "便利店管理公司管理")
@Controller("ShopManageInfoController")
@RequestMapping("/shop/manage")
public class InfoController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(InfoController.class);
    /**
     * 注入便利店管理公司Service类
     */
    @Resource(name = "shopManageServiceImpl")
    private ShopManageService shopManageService;

    /**
     * 
     * @Description:查询便利店管理公司信息
     * @param token
     * @param shopManageId	便利店管理公司ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:33:33
     */
    @ApiOperation(value = "查询便利店管理公司信息",
            notes = "查询便利店管理公司信息",
            position = 0)
    @RequestMapping(value = "/getShopManageInfo", method = RequestMethod.GET)
    public void queryById(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店管理公司ID",required=true) @RequestParam Long shopManageId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            ShopManageVo shopManageVo = shopManageService.findById(shopManageId);
            if (null == shopManageVo) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
                RespJsonPHandler.respOK(shopManageVo, response, request);
            }
        } catch (ServiceException se) {
            log.error("查询便利店管理公司基本信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店管理公司基本信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:查询一页便利店管理公司数据
     * @param token
     * @param params   查询参数
     * @param pageNum  页面游标
     * @param pageSize 页面大小
     * @param shopManagePage 查询对象参数信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:33:48
     */
    @ApiOperation(value = "查询一页便利店管理公司数据",
            notes = "查询一页便利店管理公司数据",
            position = 1)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void queryPage(@RequestHeader("token") String token,
                          @ApiParam("页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          @RequestBody ShopManagePage shopManagePage,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {

            //登陆检查
            checkLogin(token, response);

            shopManagePage.setNowPage(pageNum);
            shopManagePage.setPageSize(pageSize);
            shopManagePage.setOrderField("update_time");
            shopManagePage.setOrderDirection(false);

            //分页查询
            Pager<ShopManage, Long> pager = shopManageService.query(shopManagePage, ShopManage.class);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询便利店管理公司列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店管理公司列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }

    }

    /**
     * 
     * @Description:添加便利店管理公司，同时为它创建一个系统用户，和一个钱包
     * @param token
     * @param shopManage	便利店管理公司信息
     * @param request	
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:34:19
     */
    @ApiOperation(value = "添加便利店管理公司",
            notes = "添加便利店管理公司，同时为它创建一个系统用户，和一个钱包",
            position = 2)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
                    @ApiParam(value = "便利店管理公司信息", required = true) @RequestBody ShopManage shopManage,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {

            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(shopManage);
            //信息填充
            Date date = new Date();
            shopManage.setEstablishTime(date);
            shopManage.setCreateTime(date);
            shopManage.setUpdateTime(date);
            
            //先判断公司名称是否存在
            ShopManage query = new ShopManage();
            query.setName(shopManage.getName());
            List<ShopManage> list = shopManageService.findList(query);
            if(null != list && list.size()>0){
            	RespHandler.respError(RespMsg.shop_manage_name_have, response);
            }else{
            	//添加
            	shopManageService.addVoStaffAddWallet(shopManage);
                RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("添加便利店管理公司出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加便利店出管理公司现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店管理公司信息，如果修改了手机号码，则必须同时修改它所关联系统用户的手机号码
     * @param token
     * @param shopManage	便利店管理公司信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:34:37
     */
    @ApiOperation(value = "修改便利店管理公司信息",
            notes = "修改便利店管理公司信息，如果修改了手机号码，则必须同时修改它所关联系统用户的手机号码",
            position = 3)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
                       @ApiParam(value = "便利店管理公司信息", required = true) @RequestBody ShopManage shopManage,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {

            //登陆检查
            checkLogin(token, response);

            //数据验证
            checkModVo(shopManage);
            shopManage.setUpdateTime(new Date());

            //先判断公司名称是否存在
            ShopManage query = new ShopManage();
            query.setName(shopManage.getName());
            List<ShopManage> list = shopManageService.findList(query);
            if(null != list && list.size()>0 && !list.get(0).getDmId().equals(shopManage.getDmId())){
            	RespHandler.respError(RespMsg.shop_manage_name_have, response);
            }else{
	            //1、当手机号码未修改时，则不更新用户信息
	            //2、当手机号码对应的用户不存在，则为它新建用户
	            //3、当修改了手机号码时，同时修改它所关联系统用户的手机号码
	            shopManageService.modVoAndStaff(shopManage);
	            RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("修改便利店管理公司信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店管理公司信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:添加便利店管理公司时，数据验证
     * @param shopManage	便利店管理公司对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:34:53
     */
    private void checkVo(ShopManage shopManage) throws ServiceException {
        if (StringUtils.isEmpty(shopManage.getName())) {
            throw new ServiceException(ExceptionCode.SHOP_MANAGE_NAME_IS_NULL,"便利店管理公司名称为空！");
        }
        if (StringUtils.isEmpty(shopManage.getMobile())) {
            throw new ServiceException(ExceptionCode.PHONE_IS_NULL,"便利店管理公司手机号码为空！");
        }
        if (!StringUtils.checkMobile(shopManage.getMobile())) {
            throw new ServiceException(ExceptionCode.PHONE_FOMAT_ERROR,"便利店管理公司手机号码格式不对！");
        }
        if (StringUtils.isEmpty(shopManage.getLpName())) {
            throw new ServiceException(ExceptionCode.LPNAME_IS_NULL,"法人名称为空！");
        }
        if (StringUtils.isEmpty(shopManage.getLpIdCard())) {
            throw new ServiceException(ExceptionCode.LPIDCARD_IS_NULL,"法人身份证为空！");
        }
        if (StringUtils.isEmpty(shopManage.getLicence())) {
            throw new ServiceException(ExceptionCode.LICENCE_IS_NULL,"营业执照为空！");
        }
        if (StringUtils.isEmpty(shopManage.getIdCardFront())) {
            throw new ServiceException(ExceptionCode.IDCARDFRONT_IS_NULL,"身份证正面照片为空！");
        }
        if (StringUtils.isEmpty(shopManage.getIdCardBehind())) {
            throw new ServiceException(ExceptionCode.IDCARDBEHIND_IS_NULL,"身份证反面照片空！");
        }
        if (null == shopManage.getProvinceId()) {
            throw new ServiceException(ExceptionCode.PROVINCEID_IS_NULL,"省份地址为空！");
        }
        if (null == shopManage.getCityId()) {
            throw new ServiceException(ExceptionCode.CITYID_IS_NULL,"城市地址为空！");
        }
        if (StringUtils.isEmpty(shopManage.getCode())) {
            String code = shopManageService.createCode(shopManage.getProvinceId(), shopManage.getCityId());
            shopManage.setCode(code);
        }
        if (StringUtils.isEmpty(shopManage.getLpIdCard()) || !StringUtils.checkIDCard(shopManage.getLpIdCard())) {
            throw new ServiceException(ExceptionCode.LPIDCARD_NOT_TRUE,"身份证格式不对！");
        }
    }

    /**
     * 
     * @Description:修改便利店管理公司时，数据验证
     * @param shopManage	便利店管理公司对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:35:19
     */
    private void checkModVo(ShopManage shopManage) throws ServiceException {
        if (null == shopManage.getDmId()) {
            throw new ServiceException(ExceptionCode.SHOP_MANAGE_ID_IS_NULL,"便利店管理公司ID为空！");
        }
        checkVo(shopManage);
    }
}