package com.qtz.sm.controller.supermarket;

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
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.supermarket.page.SupermarketInfoPage;
import com.qtz.sm.supermarket.service.SupermarketInfoService;
import com.qtz.sm.supermarket.vo.SupermarketInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * <p>Title:InfoController</p>
 * <p>Description:超市基本信息Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选
 * @version v1.0 2016-04-26
 */
@Api(value = "/supermarket/info", description = "超市基本信息管理")
@Controller("SupermarketInfoController")
@RequestMapping("/supermarket/info")
public class InfoController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(InfoController.class);
    /**
     * 注入超市基本信息Service类
     */
    @Resource(name = "supermarketInfoServiceImpl")
    private SupermarketInfoService supermarketInfoService;

    @ApiOperation(value = "超市基本信息",
            notes = "超市基本信息",
            position = 0)
    @RequestMapping(value = "/getSupermarketInfo", method = RequestMethod.GET)
    public void getSupermarketInfo(@RequestHeader("token") String token,
                          @ApiParam(value = "超市ID",required=true) @RequestParam Long supermarketId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {

            //登陆检查
            checkLogin(token, response);

            SupermarketInfo supermarketInfo = supermarketInfoService.findVo(supermarketId, null);
            if (null == supermarketInfo) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
                RespHandler.respOK(supermarketInfo, response);
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
     * @Description:查询一页超市数据
     * @param token    token
     * @param pageNum  页面游标
     * @param pageSize 页面大小
     * @param supermarketInfoPage 	查询对象信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:41:58
     */
    @ApiOperation(value = "查询一页超市数据",
            notes = "查询一页超市数据",
            position = 1)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void queryPage(@RequestHeader("token") String token,
                          @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          @RequestBody SupermarketInfoPage supermarketInfoPage,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            supermarketInfoPage.setNowPage(pageNum);
            supermarketInfoPage.setPageSize(pageSize);
            supermarketInfoPage.setOrderField("update_time");
            supermarketInfoPage.setOrderDirection(false);

            //分页查询
            Pager<SupermarketInfo, Long> pager = supermarketInfoService.query(supermarketInfoPage, null);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询超市列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:添加超市，同时为超市添加一个系统用户，和添加一个钱包
     * @param token
     * @param supermarketInfo	超市信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:42:30
     */
    @ApiOperation(value = "添加超市",
            notes = "添加超市，同时为超市添加一个系统用户，和添加一个钱包",
            position = 2)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
                    @ApiParam(value = "超市信息对象", required = true) @RequestBody SupermarketInfo supermarketInfo,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(supermarketInfo);
            //信息填充
            Date date = new Date();
            supermarketInfo.setCreateTime(date);
            supermarketInfo.setUpdateTime(date);
            
            SupermarketInfo query = new SupermarketInfo();
            query.setName(supermarketInfo.getName());
            List<SupermarketInfo> list= supermarketInfoService.findList(query);
            if(null != list && list.size()>0){
            	RespHandler.respError(RespMsg.supermarket_name_have, response);
            }else{
            	//添加
                supermarketInfoService.addVoStaffAddWallet(supermarketInfo);
                RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("添加超市出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加超市出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改超市信息，如果修改了手机号码，则必须同时修改它所关联系统用户的手机号码
     * @param token
     * @param supermarketInfo	超市信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:42:42
     */
    @ApiOperation(value = "修改超市信息",
            notes = "修改超市信息，如果修改了手机号码，则必须同时修改它所关联系统用户的手机号码",
            position = 3)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
                       @ApiParam(value = "超市信息对象", required = true) @RequestBody SupermarketInfo supermarketInfo,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆验证
            checkLogin(token, response);

            //参数验证
            checkModVo(supermarketInfo);
            //数据填充
            supermarketInfo.setUpdateTime(new Date());

            SupermarketInfo query = new SupermarketInfo();
            query.setName(supermarketInfo.getName());
            List<SupermarketInfo> list= supermarketInfoService.findList(query);
            if(null != list && list.size()>0 && list.get(0).getDmId().equals(supermarketInfo.getDmId())){
            	RespHandler.respError(RespMsg.supermarket_name_have, response);
            }else{
	            //1、当手机号码未修改时，则不更新用户信息
	            //2、当手机号码对应的用户不存在，则为它新建用户
	            //3、当修改了手机号码时，同时修改它所关联系统用户的手机号码
	            supermarketInfoService.modVoAndStaff(supermarketInfo);
	            RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("修改超市信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便超市信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:添加超市时，数据验证
     * @param supermarketInfo	超市信息
     * @throws ServiceException	
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:42:53
     */
    private void checkVo(SupermarketInfo supermarketInfo) throws ServiceException {
        if (StringUtils.isEmpty(supermarketInfo.getName())) {
            throw new ServiceException("超市名称为空！");
        }
        if (StringUtils.isEmpty(supermarketInfo.getMobile())) {
            throw new ServiceException("超市手机号码为空！");
        }
        if (!StringUtils.checkMobile(supermarketInfo.getMobile())) {
            throw new ServiceException("超市手机号码格式不对！");
        }
        if (StringUtils.isEmpty(supermarketInfo.getLpName())) {
            throw new ServiceException("法人名称为空！");
        }
        if (StringUtils.isEmpty(supermarketInfo.getLpIdCard())) {
            throw new ServiceException("法人身份证为空！");
        }
        if (StringUtils.isEmpty(supermarketInfo.getLicence())) {
            throw new ServiceException("营业执照为空！");
        }
        if (StringUtils.isEmpty(supermarketInfo.getIdCardFront())) {
            throw new ServiceException("身份证正面照片为空！");
        }
        if (StringUtils.isEmpty(supermarketInfo.getIdCardBehind())) {
            throw new ServiceException("身份证反面照片空！");
        }
        if (null == supermarketInfo.getCode()) {
            //超市只有一个，保留字段
            supermarketInfo.setCode("");
        }
        if (StringUtils.isEmpty(supermarketInfo.getLpIdCard()) || !StringUtils.checkIDCard(supermarketInfo.getLpIdCard())) {
            throw new ServiceException("身份证格式不对！");
        }
    }

    /**
     * 
     * @Description:修改超市时，数据验证
     * @param supermarketInfo	超市信息
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:43:04
     */
    private void checkModVo(SupermarketInfo supermarketInfo) throws ServiceException {
        if (null == supermarketInfo.getDmId()) {
            throw new ServiceException("超市信息ID不能为空.");
        }
        checkVo(supermarketInfo);
    }

}