package com.qtz.sm.controller.shop;

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

import com.mall.core.common.DateUtil;
import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shop.service.ShopBusinessService;
import com.qtz.sm.shop.vo.ShopBusiness;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>Title:BusinessController</p>
 * <p>Description:便利店营业信息Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Api(value = "/shop/business", description = "便利店营业信息管理")
@Controller
@RequestMapping("/shop/business")
public class BusinessController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(BusinessController.class);
    /**
     * 注入便利店营业信息Service类
     */
    @Resource(name = "shopBusinessServiceImpl")
    private ShopBusinessService shopBusinessService;
    
    /**
     * @Description:查询便利店营业信息
     * @param token    token
     * @param shopId   便利店ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:15:01
     */
    @ApiOperation(value = "查询便利店营业信息",
            notes = "查询便利店营业信息",
            position = 0)
    @RequestMapping(value = "/shopId", method = RequestMethod.GET)
    public void queryById(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店ID") @RequestParam("shopId") Long shopId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            ShopBusiness shopBusiness = shopBusinessService.findVoByShopId(shopId);
            if (null == shopBusiness) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
            	//格式化下营业时间
            	if(!StringUtil.isEmpty(shopBusiness.getServiceStartTime())){
            		shopBusiness.setServiceStartTimeStr(DateUtil.dateToStr(shopBusiness.getServiceStartTime(), DateUtil.timePattern));
            	}
            	if(!StringUtil.isEmpty(shopBusiness.getServiceEndTime())){
            		shopBusiness.setServiceEndTimeStr(DateUtil.dateToStr(shopBusiness.getServiceEndTime(), DateUtil.timePattern));
            	}
                RespHandler.respOK(shopBusiness, response);
            }
        } catch (ServiceException se) {
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店营业信息出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * @Description:添加便利店营业信息
     * @param token        token
     * @param shopBusiness 店铺营业信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:15:22
     */
    @ApiOperation(value = "添加便利店营业信息",
            notes = "添加便利店营业信息",
            position = 1)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
                    @ApiParam(value = "便利店营业信息对象", required = true) @RequestBody ShopBusiness shopBusiness,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(shopBusiness);

            //信息填充
            Date date = new Date();
            shopBusiness.setCreateTime(date);
            shopBusiness.setUpdateTime(date);
            
            //先查询是否已经存在
            ShopBusiness query = new ShopBusiness();
            query.setShopId(shopBusiness.getShopId());
            List<ShopBusiness> list = shopBusinessService.findList(query);
            if(null == list || list.size() == 0 ){
            	//添加
                shopBusinessService.addVo(shopBusiness);
                RespHandler.respOK(response);
            }else{
            	RespHandler.respError(RespMsg.shop_business_have, response);
            }
            
        } catch (ServiceException se) {
            log.error("添加便利店营业信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加便利店出营业信息现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店营业信息
     * @param token        token
     * @param shopBusiness 店铺营业信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:15:44
     */
    @ApiOperation(value = "修改便利店营业信息",
            notes = "修改便利店营业信息",
            position = 2)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
                       @ApiParam(value = "便利店营业信息对象", required = true) @RequestBody ShopBusiness shopBusiness,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆验证
            checkLogin(token, response);

            //参数验证
            checkModVo(shopBusiness);
            shopBusiness.setUpdateTime(new Date());

            //先查询是否已经存在
            ShopBusiness query =  shopBusinessService.findVo(shopBusiness.getDmId(), null);
            if(null == query ){
            	RespHandler.respError(RespMsg.shop_business_query_id, response);
            }else{
            	 //修改营业信息
                shopBusinessService.modVoNotNull(shopBusiness);
                RespHandler.respOK(response);
            }
           
        } catch (ServiceException se) {
            log.error("修改便利店营业信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店信息营业出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:添加便利店营业信息时，数据验证
     * @param shopBusiness 便利店营业信息对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:16:32
     */
    private void checkVo(ShopBusiness shopBusiness) throws ServiceException {
        if (null == shopBusiness.getShopId()) {
            throw new ServiceException(ExceptionCode.SHOP_ID_IS_NULL, "便利店ID不能为空.");
        }
        //营业时间转换
        if(!StringUtil.isEmpty(shopBusiness.getServiceStartTimeStr())){
        	shopBusiness.setServiceStartTime(DateUtil.strToDate(shopBusiness.getServiceStartTimeStr(),DateUtil.timePattern));
        }
        if(!StringUtil.isEmpty(shopBusiness.getServiceEndTimeStr())){
        	shopBusiness.setServiceEndTime(DateUtil.strToDate(shopBusiness.getServiceEndTimeStr(),DateUtil.timePattern));
        }
    }

    /**
     * 
     * @Description: 修改便利店营业信息时，数据验证
     * @param shopBusiness  便利店营业信息对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:16:47
     */
    private void checkModVo(ShopBusiness shopBusiness) throws ServiceException {
        if (null == shopBusiness.getDmId()) {
            throw new ServiceException(ExceptionCode.SHOP_BUSINESS_ID_IS_NULL, "便利店营业信息ID不能为空.");
        }
        if (null == shopBusiness.getIsSendPrice()) {
            shopBusiness.setIsSendPrice(0);
        }
        checkVo(shopBusiness);
    }
}