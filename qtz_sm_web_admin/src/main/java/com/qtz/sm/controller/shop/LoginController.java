package com.qtz.sm.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.Constants;
import com.mall.core.common.MD5Util;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.DdmSession;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.session.service.SessionService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.service.ShopStaffService;
import com.qtz.sm.shop.vo.ShopInfo;
import com.qtz.sm.shop.vo.ShopStaff;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016/4/26
 */
@Api(value = "/shop", description = "便利店登陆、登出")
@Controller("ShopLoginController")
@RequestMapping("/shop")
public class LoginController extends BaseController {

    private static LogTool log = LogTool.getInstance(LoginController.class);

    /**
     * session管理服务
     */
    @Resource(name = "sessionServiceImpl")
    private SessionService sessionService;

    /**
     * 便利店员工服务
     */
    @Resource(name = "shopStaffServiceImpl")
    private ShopStaffService shopStaffService;

    /**
     * 便利店服务
     */
    @Resource(name = "shopInfoServiceImpl")
    private ShopInfoService shopInfoService;

    /**
     * 便利店员工登陆
     *
     * @param name     便利店员工手机号码
     * @param pwd      密码
     * @param request  请求对象
     * @param response 响应对象
     */
    @ApiOperation(value = "便利店员工登陆",
            notes = "便利店员工登陆",
            position = 0)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@ApiParam(value = "登录名", required = true) @RequestParam String name,
                      @ApiParam(value = "密码", required = true) @RequestParam String pwd,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        try {
            ShopStaff shopStaff = new ShopStaff();
            shopStaff.setPhone(name);
            shopStaff.setPwd(MD5Util.getMD5(pwd));
            List<ShopStaff> shopStaffList = shopStaffService.findList(shopStaff);
            if (null != shopStaffList && shopStaffList.size() == 1) {
                shopStaff = shopStaffList.get(0);
                if (shopStaff.getStatus() == 1) {
                    RespHandler.respError(RespMsg.user_seal_number, response);
                } else {
                    Long shopId = shopStaff.getShopId();
                    ShopInfo shopInfo = shopInfoService.findVo(shopId, null);
                    User user = constructUser(shopStaff, shopInfo);
                    user.setIp(request.getRemoteAddr());//IP地址
                    //向session中保存用户
                    DdmSession ddmSession = sessionService.newAppSession();
                    ddmSession.save(Constants.SESSION_USER, user);
                    sessionService.saveAppSession(ddmSession);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("token", ddmSession.getId());
                    map.put("user", user);
                    RespHandler.respOK(map, response);
                }
            } else {
                RespHandler.respError(RespMsg.user_no_existence, response);
            }
        } catch (ServiceException se) {
            log.error("便利店员工登陆验证错误！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("便利店员工登陆出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 便利店员工登出
     *
     * @param token    token
     * @param request  请求对象
     * @param response 响应对象
     */
    @ApiOperation(value = "便利店员工登出",
            notes = "便利店员工登出",
            position = 1)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(@RequestHeader("token") String token,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            sessionService.removeAppSession(token);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("便利店员工登出验证错误！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("便利店员工登出出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 便利店当前员工密码修改
     *
     * @param token           token
     * @param oldPassword     旧密码
     * @param newPassword     新密码
     * @param confirmPassword 新密码确认
     * @param request         request
     * @param response        response
     */
    @ApiOperation(value = "便利店当前员工密码修改",
            notes = "便利店当前员工密码修改",
            position = 2)
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
    public void modifyPassword(@RequestHeader("token") String token,
                               @ApiParam(value = "旧密码", required = true) @RequestParam("oldPassword") String oldPassword,
                               @ApiParam(value = "新密码", required = true) @RequestParam("newPassword") String newPassword,
                               @ApiParam(value = "新密码确认", required = true) @RequestParam("confirmPassword") String confirmPassword,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            User user = this.getUser(token);
            ShopStaff shopStaff = new ShopStaff();
            shopStaff.setPhone(user.getPhone());
            shopStaff.setPwd(MD5Util.getMD5(oldPassword));
            List<ShopStaff> shopStaffList = shopStaffService.findList(shopStaff);
            if (null != shopStaffList && shopStaffList.size() == 1) {
                shopStaff = shopStaffList.get(0);
                if (shopStaff.getStatus() == 1) {
                    RespHandler.respError(RespMsg.user_seal_number, response);
                } else {
                    if (oldPassword.equals(newPassword)) {
                        RespHandler.respError(RespMsg.pwd_param_equal, response);
                        return;
                    } else if (newPassword.equals(confirmPassword)) {
                        //修改密码
                        shopStaff.setPwd(MD5Util.getMD5(newPassword));
                        shopStaffService.modVoNotNull(shopStaff);
                        //退出重新登陆
                        sessionService.removeAppSession(token);
                    } else {
                        RespHandler.respError(RespMsg.confirmPw_error, response);
                        return;
                    }
                    RespHandler.respOK(response);
                    return;
                }
            } else {
                RespHandler.respError(RespMsg.oldPw_error, response);
            }

        } catch (ServiceException se) {
            log.error("便利店当前员工密码修改失败", se);
            RespHandler.respError(RespMsg.alterPassword_error(se.getErrorType()), response);
        } catch (Exception e) {
            log.error("便利店当前员工密码修改出现系统错误", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 构建User对象
     *
     * @param shopStaff 便利店员工表
     * @param shopInfo  便利店信息表
     * @return user对象
     */
    private User constructUser(ShopStaff shopStaff, ShopInfo shopInfo) {
        User user = new User();
        BeanUtils.copyProperties(shopInfo, user);
//        BeanUtils.copyProperties(shopStaff, user);
        user.setName(shopStaff.getName());//用户名称
        user.setPhone(shopStaff.getPhone());//登陆手机号码
        user.setStatus(shopStaff.getStatus());//用户状态0启用1禁用
        user.setDmId(shopStaff.getDmId());//用户ID
        
        user.setCompanyDmId(shopInfo.getDmId());//便利店ID
        user.setCompanyName(shopInfo.getName());//便利店名称
        user.setLpName(shopInfo.getLpName());//法人名称
        //所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
        user.setCompanyType(6);
        user.setPwd(null);
        return user;
    }
}
