package com.qtz.sm.controller.supermarket;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.qtz.sm.supermarket.service.SupermarketBusinessService;
import com.qtz.sm.supermarket.vo.SupermarketBusiness;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>Title:BusinessController</p>
 * <p>Description:超市营业信息Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选
 * @version v1.0 2016-06-14
 */
@Api(value = "/supermarketBusiness", description = "超市营业信息管理")
@Controller("SupermarketBusinessController")
@RequestMapping("/supermarketBusiness")
public class BusinessController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(BusinessController.class);
    
    @Autowired
    private SupermarketBusinessService supermarketBusinessService;
    
    /**
     * @Description:查询超市营业信息
     * @param token    token
     * @param supermarketId   超市ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月14日 下午6:15:01
     */
    @ApiOperation(value = "查询超市营业信息",notes = "查询超市营业信息",position = 0)
    @RequestMapping(value = "/queryInfo", method = RequestMethod.GET)
    public void queryInfo(@RequestHeader("token") String token,
                          @ApiParam(value = "超市ID") @RequestParam Long supermarketId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            SupermarketBusiness supermarketBusiness = new SupermarketBusiness();
            supermarketBusiness.setSupermarketId(supermarketId);
            List<SupermarketBusiness> list = supermarketBusinessService.findList(supermarketBusiness);
            if (null == list || list.size() == 0 ) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
            	supermarketBusiness = list.get(0);
            	//格式化下营业时间
            	if(!StringUtil.isEmpty(supermarketBusiness.getServiceStartTime())){
            		supermarketBusiness.setServiceStartTimeStr(DateUtil.dateToStr(supermarketBusiness.getServiceStartTime(), DateUtil.timePattern));
            	}
            	if(!StringUtil.isEmpty(supermarketBusiness.getServiceEndTime())){
            		supermarketBusiness.setServiceEndTimeStr(DateUtil.dateToStr(supermarketBusiness.getServiceEndTime(), DateUtil.timePattern));
            	}
                RespHandler.respOK(supermarketBusiness, response);
            }
        } catch (ServiceException se) {
            log.error("查询超市营业信息失败.", se);
            RespHandler.respError(RespMsg.supermarket_business_query_id, response);
        } catch (Exception e) {
            log.error("查询超市营业信息出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * @Description:添加超市营业信息
     * @param token        token
     * @param supermarketBusiness 店铺营业信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月14日 下午6:15:01
     */
    @ApiOperation(value = "添加超市营业信息",
            notes = "添加超市营业信息",
            position = 1)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
                    @ApiParam(value = "超市营业信息对象", required = true) @RequestBody SupermarketBusiness supermarketBusiness,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(supermarketBusiness);

            //信息填充
            Date date = new Date();
            supermarketBusiness.setCreateTime(date);
            supermarketBusiness.setUpdateTime(date);
            
            //先查询是否已经存在
            SupermarketBusiness query = new SupermarketBusiness();
            query.setSupermarketId(supermarketBusiness.getSupermarketId());
            List<SupermarketBusiness> list = supermarketBusinessService.findList(query);
            if(null == list || list.size() == 0 ){
            	//添加
            	supermarketBusinessService.addVo(supermarketBusiness);
                RespHandler.respOK(response);
            }else{
            	RespHandler.respError(RespMsg.supermarket_business_have, response);
            }
            
        } catch (ServiceException se) {
            log.error("添加超市营业信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加超市出营业信息现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改超市营业信息
     * @param token        token
     * @param supermarketBusiness 店铺营业信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月14日 下午6:15:01
     */
    @ApiOperation(value = "修改超市营业信息",
            notes = "修改超市营业信息",
            position = 2)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
                       @ApiParam(value = "超市营业信息对象", required = true) @RequestBody SupermarketBusiness supermarketBusiness,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆验证
            checkLogin(token, response);

            //参数验证
            checkModVo(supermarketBusiness);
            supermarketBusiness.setUpdateTime(new Date());

            //先查询是否已经存在
            SupermarketBusiness query =  supermarketBusinessService.findVo(supermarketBusiness.getDmId(), null);
            if(null == query ){
            	RespHandler.respError(RespMsg.not_found, response);
            }else{
            	 //修改营业信息
            	supermarketBusinessService.modVoNotNull(supermarketBusiness);
                RespHandler.respOK(response);
            }
           
        } catch (ServiceException se) {
            log.error("修改超市营业信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改超市信息营业出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:添加超市营业信息时，数据验证
     * @param supermarketBusiness 超市营业信息对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月14日 下午6:15:01
     */
    private void checkVo(SupermarketBusiness supermarketBusiness) throws ServiceException {
        if (null == supermarketBusiness.getSupermarketId()) {
            throw new ServiceException(ExceptionCode.SUPERMARKET_ID_IS_NULL, "超市ID不能为空.");
        }
        //营业时间转换
        if(!StringUtil.isEmpty(supermarketBusiness.getServiceStartTimeStr())){
        	supermarketBusiness.setServiceStartTime(DateUtil.strToDate(supermarketBusiness.getServiceStartTimeStr(),DateUtil.timePattern));
        }
        if(!StringUtil.isEmpty(supermarketBusiness.getServiceEndTimeStr())){
        	supermarketBusiness.setServiceEndTime(DateUtil.strToDate(supermarketBusiness.getServiceEndTimeStr(),DateUtil.timePattern));
        }
    }

    /**
     * 
     * @Description: 修改超市营业信息时，数据验证
     * @param supermarketBusiness  超市营业信息对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月14日 下午6:15:01
     */
    private void checkModVo(SupermarketBusiness supermarketBusiness) throws ServiceException {
        if (null == supermarketBusiness.getDmId()) {
            throw new ServiceException(ExceptionCode.DMID_IS_NULL, "编号ID不能为空.");
        }
        checkVo(supermarketBusiness);
    }
}