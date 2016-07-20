package com.qtz.sm.controller.supermarket;

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
import com.qtz.sm.supermarket.service.SupermarketInfoService;
import com.qtz.sm.supermarket.service.SupermarketStaffService;
import com.qtz.sm.supermarket.vo.SupermarketInfo;
import com.qtz.sm.supermarket.vo.SupermarketStaff;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import org.apache.commons.pool2.UsageTracking;
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
 * <p>Title:com.qtz.sm.supermarket.controller.LoginController</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 孙选 - Laven
 * @version v1.0 2016/4/27
 */
@Api(value = "/supermarket", description = "超市登陆、登出")
@Controller("SupermarketLoginController")
@RequestMapping("/supermarket")
public class LoginController extends BaseController {

    private static LogTool log = LogTool.getInstance(LoginController.class);

    /**
     * session管理服务
     */
    @Resource(name = "sessionServiceImpl")
    private SessionService sessionService;

    /**
     * 超市员工服务
     */
    @Resource(name = "supermarketStaffServiceImpl")
    private SupermarketStaffService supermarketStaffService;

    /**
     * 超市服务
     */
    @Resource(name = "supermarketInfoServiceImpl")
    private SupermarketInfoService supermarketInfoService;

    /**
     * 
     * @Description:超市登陆
     * @param name     超市员工手机号码
     * @param pwd      密码
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:43:22
     */
    @ApiOperation(value = "超市登陆",
            notes = "超市登陆",
            position = 0)
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@ApiParam(value = "超市用户名", required = true) @RequestParam String name,
                      @ApiParam(value = "密码", required = true) @RequestParam String pwd,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        try {
            SupermarketStaff supermarketStaff = new SupermarketStaff();
            supermarketStaff.setPhone(name);
            supermarketStaff.setPwd(MD5Util.getMD5(pwd));
            List<SupermarketStaff> supermarketStaffList = supermarketStaffService.findList(supermarketStaff);
            if (null != supermarketStaffList && supermarketStaffList.size() == 1) {
                supermarketStaff = supermarketStaffList.get(0);
                if (supermarketStaff.getStatus() == 1) {
                    RespHandler.respError(RespMsg.user_seal_number, response);
                } else {
                    Long supermarketId = supermarketStaff.getSupermarketId();
                    SupermarketInfo supermarketInfo = supermarketInfoService.findVo(supermarketId, null);
                    User user = constructUser(supermarketStaff, supermarketInfo);
                    user.setIp(request.getRemoteAddr());//IP地址
                    //向session中保存用户
                    DdmSession ddmSession = sessionService.newAppSession();
                    ddmSession.save(Constants.SESSION_USER, user);
                    sessionService.saveAppSession(ddmSession);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("token", ddmSession.getId());
                    user.setPwd(null);
                    map.put("user", user);
                    RespHandler.respOK(map, response);
                }
            } else {
                RespHandler.respError(RespMsg.user_no_existence, response);
            }
        } catch (ServiceException se) {
            log.error("超市员工登陆验证错误！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("超市员工登陆出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:超市登出
     * @param token
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:43:35
     */
    @ApiOperation(value = "超市登出",
            notes = "超市登出",
            position = 1)
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(@RequestHeader("token") String token,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            sessionService.removeAppSession(token);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("超市员工登出验证错误！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("超市员工登出出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:超市当前员工密码修改
     * @param token           token
     * @param oldPassword     旧密码
     * @param newPassword     新密码
     * @param confirmPassword 新密码确认
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:43:42
     */
    @ApiOperation(value = "超市当前员工密码修改",
            notes = "超市当前员工密码修改",
            position = 2)
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.GET)
    public void modifyPassword(@RequestHeader("token") String token,
                               @ApiParam(value = "旧密码", required = true) @RequestParam("oldPassword") String oldPassword,
                               @ApiParam(value = "新密码", required = true) @RequestParam("newPassword") String newPassword,
                               @ApiParam(value = "新密码确认", required = true) @RequestParam("confirmPassword") String confirmPassword,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        try {
            //获取当前用户信息
            User user = this.getUser(token);
            SupermarketStaff supermarketStaff = new SupermarketStaff();
            supermarketStaff.setPhone(user.getPhone());
            supermarketStaff.setPwd(MD5Util.getMD5(oldPassword));
            List<SupermarketStaff> supermarketStaffList = supermarketStaffService.findList(supermarketStaff);
            if (null != supermarketStaffList && supermarketStaffList.size() == 1) {
                supermarketStaff = supermarketStaffList.get(0);
                //判断是否被禁用
                if (supermarketStaff.getStatus() == 1) {
                    RespHandler.respError(RespMsg.user_seal_number, response);
                } else {
                    //判断新旧密码是否相等
                    if (oldPassword.equals(newPassword)) {
                        RespHandler.respError(RespMsg.pwd_param_equal, response);
                        return;
                        //判断两次新密码是否相等
                    } else if (newPassword.equals(confirmPassword)) {
                        //修改密码
                        supermarketStaff.setPwd(MD5Util.getMD5(newPassword));
                        supermarketStaffService.modVoNotNull(supermarketStaff);
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
            log.error("超市当前员工密码修改失败", se);
            RespHandler.respError(RespMsg.alterPassword_error(se.getErrorType()), response);
        } catch (Exception e) {
            log.error("超市当前员工密码修改出现系统错误", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 
     * @Description:构建User对象
     * @param supermarketStaff 超市员工对象
     * @param supermarketInfo  超市信息对象
     * @return
     * User
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:43:54
     */
    private User constructUser(SupermarketStaff supermarketStaff, SupermarketInfo supermarketInfo) {
        User user = new User();
        BeanUtils.copyProperties(supermarketInfo, user);
//        BeanUtils.copyProperties(supermarketStaff, user);
        user.setDmId(supermarketStaff.getDmId());//用户ID
        user.setPhone(supermarketStaff.getPhone());//用户登陆手机
        user.setName(supermarketStaff.getName());//用户名称
        user.setStatus(supermarketStaff.getStatus());//用户状态0启用1禁用
        
        user.setCompanyDmId(supermarketInfo.getDmId());//超市ID
        user.setCompanyName(supermarketInfo.getName());//超市名称
        user.setLpName(supermarketInfo.getLpName());//法人名称
        //所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
        user.setCompanyType(7);
        user.setPwd(null);
        return user;
    }
}
