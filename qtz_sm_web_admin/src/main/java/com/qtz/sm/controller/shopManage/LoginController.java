package com.qtz.sm.controller.shopManage;

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
import com.qtz.sm.shopManage.service.ShopManageService;
import com.qtz.sm.shopManage.service.ShopManageStaffService;
import com.qtz.sm.shopManage.vo.ShopManage;
import com.qtz.sm.shopManage.vo.ShopManageStaff;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:com.qtz.sm.shop.controller.LoginController</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 孙选
 * @version v1.0 2016/4/26
 */
@Api(value = "/shop/manage", description = "便利店管理公司登陆、登出")
@Controller("ShopManageLoginController")
@RequestMapping("/shop/manage")
public class LoginController extends BaseController {

    private static LogTool log = LogTool.getInstance(LoginController.class);

    /**
     * session管理服务
     */
    @Resource(name = "sessionServiceImpl")
    private SessionService sessionService;

    /**
     * 便利店管理公司员工服务
     */
    @Resource(name = "shopManageStaffServiceImpl")
    private ShopManageStaffService shopManageStaffService;

    /**
     * 便利店管理公司服务
     */
    @Resource(name = "shopManageServiceImpl")
    private ShopManageService shopManageService;

    /**
     * 
     * @Description:便利店管理公司登陆
     * @param name     便利店管理公司店员工手机号码
     * @param pwd      密码
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:35:49
     */
    @ApiOperation(value = "便利店管理公司登陆",
            notes = "便利店管理公司登陆",
            position = 0)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@ApiParam(value = "便利店管理公司用户名", required = true) @RequestParam String name,
                      @ApiParam(value = "密码", required = true) @RequestParam String pwd,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        try {
            ShopManageStaff shopManageStaff = new ShopManageStaff();
            shopManageStaff.setPhone(name);
            shopManageStaff.setPwd(MD5Util.getMD5(pwd));
            List<ShopManageStaff> shopManageStaffList = shopManageStaffService.findList(shopManageStaff);
            if (null != shopManageStaffList && shopManageStaffList.size() == 1) {
                shopManageStaff = shopManageStaffList.get(0);
                if (shopManageStaff.getStatus() == 1) {
                    RespHandler.respError(RespMsg.user_seal_number, response);
                } else {
                    Long shopManageId = shopManageStaff.getShopManageId();
                    ShopManage shopManage = shopManageService.findVo(shopManageId, null);
                    User user = constructUser(shopManageStaff, shopManage);
                    user.setIp(request.getRemoteAddr());//IP地址
                    //向session中保存用户
                    DdmSession ddmSession = sessionService.newAppSession();
                    ddmSession.save(Constants.SESSION_USER, user);
                    sessionService.saveAppSession(ddmSession);
                    Map<String, Object> map = new HashMap<>();
                    map.put("token", ddmSession.getId());
                    map.put("user", user);
                    RespHandler.respOK(map, response);
                }
            } else {
                RespHandler.respError(RespMsg.user_no_existence, response);
            }
        } catch (ServiceException se) {
            log.error("便利店管理公司员工登陆验证错误！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("便利店管理公司员工登陆出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:便利店管理公司登出
     * @param token
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:36:01
     */
    @ApiOperation(value = "便利店管理公司登出",
            notes = "便利店管理公司登出",
            position = 1)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(@RequestHeader("token") String token,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            sessionService.removeAppSession(token);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("便利店管理公司员工登出验证错误！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("便利店管理公司员工登出出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:便利店管理公司当前员工密码修改
     * @param token           token
     * @param oldPassword     旧密码
     * @param newPassword     新密码
     * @param confirmPassword 新密码确认
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:36:09
     */
    @ApiOperation(value = "便利店管理公司当前员工密码修改",
            notes = "便利店管理公司当前员工密码修改",
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
            ShopManageStaff shopManageStaff = new ShopManageStaff();
            shopManageStaff.setPhone(user.getPhone());
            shopManageStaff.setPwd(MD5Util.getMD5(oldPassword));
            List<ShopManageStaff> shopManageStaffList = shopManageStaffService.findList(shopManageStaff);
            if (null != shopManageStaffList && shopManageStaffList.size() == 1) {
                shopManageStaff = shopManageStaffList.get(0);
                if (shopManageStaff.getStatus() == 1) {
                    RespHandler.respError(RespMsg.user_seal_number, response);
                } else {
                    if (oldPassword.equals(newPassword)) {
                        RespHandler.respError(RespMsg.pwd_param_equal, response);
                        return;
                    } else if (newPassword.equals(confirmPassword)) {
                        //修改密码
                        shopManageStaff.setPwd(MD5Util.getMD5(newPassword));
                        shopManageStaffService.modVoNotNull(shopManageStaff);
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
            log.error("便利店管理公司当前员工密码修改失败", se);
            RespHandler.respError(RespMsg.alterPassword_error(se.getErrorType()), response);
        } catch (Exception e) {
            log.error("便利店管理公司当前员工密码修改改出现系统错误", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 
     * @Description:构建User对象
     * @param shopManageStaff 便利店管理公司员工对象
     * @param shopManage      便利店管理公司信息对象
     * @return
     * User
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:36:21
     */
    private User constructUser(ShopManageStaff shopManageStaff, ShopManage shopManage) {
        User user = new User();
        BeanUtils.copyProperties(shopManage, user);
//        BeanUtils.copyProperties(shopManageStaff, user);
        
        user.setPhone(shopManageStaff.getPhone());//用户登陆手机
        user.setName(shopManageStaff.getName());//用户名称
        user.setStatus(shopManageStaff.getStatus());//用户状态 0启用1禁用
        user.setDmId(shopManageStaff.getDmId());//用户ID
        
       
        user.setCompanyDmId(shopManage.getDmId());//便利店管理公司ID
        user.setCompanyName(shopManage.getName());//便利店管理公司名称
        user.setLpName(shopManage.getLpName());//法人名称
        //所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
        user.setCompanyType(5);
        user.setPwd(null);
        return user;
    }
}
