package com.qtz.sm.controller.shopManage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shopManage.service.ShopManageSplitPointService;
import com.qtz.sm.shopManage.vo.ShopManageSplitPoint;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * Description:
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @author 孙选 
 * @version v1.0 2016/6/1
 */
@Api(value = "/shop/manageSplitPoint", description = "便利店管理公司分成点")
@Controller("ShopManageSplitPointController")
@RequestMapping("/shop/manageSplitPoint")
public class ShopManageSplitPointController extends BaseController {

	private static LogTool log = LogTool.getInstance(ShopManageSplitPointController.class);

	/**
	 * 便利店管理公司分成点
	 */
	@Resource(name = "shopManageSplitPointServiceImpl")
	private ShopManageSplitPointService shopManageSplitPointService;

	/**
	 * 
	 * @Description:便利店管理分成点查询
	 * @param token
	 * @param request
	 * @param response
	 *            void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:36:01
	 */
	@ApiOperation(value = "便利店管理分成点查询", notes = "便利店管理分成点查询", position = 1)
	@RequestMapping(value = "/getSplitPointInfo", method = RequestMethod.GET)
	public void getSplitPointInfo(@RequestHeader("token") String token,
			@ApiParam(value = "便利店管理公司用户名", required = true) @RequestParam Long shopManageId,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			ShopManageSplitPoint shopManageSplitPoint = new ShopManageSplitPoint();
			shopManageSplitPoint.setShopManageId(shopManageId);
			List<ShopManageSplitPoint> list = shopManageSplitPointService.findList(shopManageSplitPoint);
			if (null == list || list.size() == 0) {
				RespHandler.respError(RespMsg.not_found, response);
			} else {
				RespJsonPHandler.respOK(list.get(0), response, request);
			}
		} catch (ServiceException se) {
			log.error("便利店管理分成点查询验证错误！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("便利店管理分成点查询出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description:添加便利店管理分成点
	 * @param token
	 * @param request
	 * @param response
	 *            void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:36:01
	 */
	@ApiOperation(value = "添加便利店管理分成点", notes = "添加便利店管理分成点", position = 2)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestHeader("token") String token,
			@ApiParam(value = "便利店公司分成点", required = true) @RequestBody ShopManageSplitPoint shopManageSplitPoint,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 登陆检查
			checkLogin(token, response);
			// 添加数据验证
			checkVo(shopManageSplitPoint);
			//先查询是否存在
			ShopManageSplitPoint query = new ShopManageSplitPoint();
			query.setShopManageId(shopManageSplitPoint.getShopManageId());
			List<ShopManageSplitPoint> list = shopManageSplitPointService.findList(query);
			if (null == list || list.size() == 0) {
				shopManageSplitPointService.addVo(shopManageSplitPoint);
				RespHandler.respOK(response);
			} else {
				RespHandler.respError(RespMsg.shop_manage_split_point_not_null, response);
			}
		} catch (ServiceException se) {
			log.error("添加便利店管理分成点验证错误！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("添加便利店管理分成点系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description:修改便利店管理分成点
	 * @param token
	 * @param request
	 * @param response
	 *            void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:36:01
	 */
	@ApiOperation(value = "修改便利店管理分成点", notes = "修改便利店管理分成点", position = 3)
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestHeader("token") String token,
			@ApiParam(value = "便利店公司分成点", required = true) @RequestBody ShopManageSplitPoint shopManageSplitPoint,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 登陆检查
			checkLogin(token, response);
			// 修改数据验证
			checkModVo(shopManageSplitPoint);
			
			ShopManageSplitPoint query = shopManageSplitPointService.findVo(shopManageSplitPoint.getDmId(),null);
			if(!StringUtil.isEmpty(query)){
				shopManageSplitPointService.modVoNotNull(shopManageSplitPoint);
				RespHandler.respOK(response);
			}else{
				RespHandler.respError(RespMsg.not_found, response);
			}
			
		} catch (ServiceException se) {
			log.error("修改便利店管理分成点验证错误！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("修改便利店管理分成点出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description:修改数据验证
	 * @param shopManageSplitPoint
	 * @throws ServiceException
	 *             void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:35:19
	 */
	private void checkModVo(ShopManageSplitPoint shopManageSplitPoint) throws ServiceException {
		if (null == shopManageSplitPoint.getDmId()) {
			throw new ServiceException(ExceptionCode.DMID_IS_NULL,"便利店分成点ID为空！");
		}
		checkVo(shopManageSplitPoint);
	}

	/**
	 * 
	 * @Description:添加数据验证
	 * @param shopManageSplitPoint
	 * @throws ServiceException
	 *             void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:34:53
	 */
	private void checkVo(ShopManageSplitPoint shopManageSplitPoint) throws ServiceException {
		if (null == shopManageSplitPoint.getSplitPoint()) {
			shopManageSplitPoint.setSplitPoint(0d);
		}
		if (null == shopManageSplitPoint.getShopManageId()) {
			throw new ServiceException(ExceptionCode.SHOP_MANAGE_ID_IS_NULL,"便利店公司ID为空！");
		}
	}
}
