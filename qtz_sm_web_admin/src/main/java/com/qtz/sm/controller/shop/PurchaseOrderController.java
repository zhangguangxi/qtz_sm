package com.qtz.sm.controller.shop;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.DateUtil;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.ProcessingCheckExceptionUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.page.GdGoodsPage;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.shop.page.ShopPurchaseOrderPage;
import com.qtz.sm.shop.service.ShopGoodsService;
import com.qtz.sm.shop.service.ShopPurchaseOrderService;
import com.qtz.sm.shop.vo.ShopPurchaseOrder;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItem;
import com.qtz.sm.shop.vo.ShopPurchaseOrderVo;
import com.qtz.sm.shop.vo.ShopValueVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * Description:
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @author 孙选 
 * @version v1.0 2016/5/4
 */
@Api(value = "/shop/order/purchase", description = "便利店采购订单管理")
@Controller
@RequestMapping("/shop/order/purchase")
public class PurchaseOrderController extends BaseController {

	LogTool log = LogTool.getInstance(PurchaseOrderController.class);

	@Resource(name = "shopPurchaseOrderServiceImpl")
	private ShopPurchaseOrderService shopPurchaseOrderService;

	@Autowired
	private GdGoodsService gdGoodsService;

	@Autowired
    private ShopGoodsService shopGoodsService;

	/**
	 * 
	 * @Description:通过采购订单ID查询订单详情
	 * @param token
	 * @param orderId	采购订单ID
	 * @param request
	 * @param response
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:23:05
	 */
	@ApiOperation(value = "通过采购订单ID查询订单详情", notes = "通过采购订单ID查询订单详情", position = 0)
	@RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
	public void getOrderInfo(@RequestHeader("token") String token,
			@ApiParam(value = "采购订单ID", required = true) @RequestParam Long orderId, 
			HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 登陆检查
			checkLogin(token, response);

			// 查询订单详细信息
			ShopPurchaseOrderVo shopPurchaseOrderVo = shopPurchaseOrderService.findById(orderId);
			if (null == shopPurchaseOrderVo) {
				RespHandler.respError(RespMsg.not_found, response);
			} else {
				RespHandler.respOK(shopPurchaseOrderVo, response);
			}
		} catch (ServiceException se) {
			log.error("通过采购订单ID查询订单详情出错！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("通过采购订单ID查询订单详情出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description:查询便利店的一页采购订单数据
	 * @param token
	 * @param shopId	所属便利店ID
	 * @param pageNum	 页面游标
	 * @param pageSize	页面大小
	 * @param shopPurchaseOrderPage 查询参数封装对象
	 * @param request
	 * @param response
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:23:27
	 */
	@ApiOperation(value = "查询便利店的一页采购订单数据", notes = "查询便利店的一页采购订单数据", position = 1)
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public void queryPage(@RequestHeader("token") String token,
			@ApiParam(value = "所属便利店ID", required = true) @RequestParam("shopId") Long shopId,
			@ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
			@ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			@RequestBody ShopPurchaseOrderPage shopPurchaseOrderPage,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			// 登陆检查
			checkLogin(token, response);

			shopPurchaseOrderPage.setShopId(shopId);
			shopPurchaseOrderPage.setNowPage(pageNum);
			shopPurchaseOrderPage.setPageSize(pageSize);
			shopPurchaseOrderPage.setOrderField("update_time");
			shopPurchaseOrderPage.setOrderDirection(false);

			// 分页查询
			Pager<ShopPurchaseOrder, Long> pager = shopPurchaseOrderService.query(shopPurchaseOrderPage, ShopPurchaseOrder.class);
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException se) {
			log.error("查询便利店的一页采购订单数据出错！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("查询便利店的一页采购订单数据出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description:分页查询我要进货的商品列表
	 * @param token
	 * @param pageNum	页面游标
	 * @param pageSize	 页面大小
	 * @param request
	 * @param response
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:24:06
	 */
	@ApiOperation(value = "我要进货的商品列表", notes = "分页查询我要进货的商品列表", position = 1)
	@RequestMapping(value = "/getGoodsPage", method = RequestMethod.GET)
	public void getGoodsPage(@RequestHeader("token") String token,
			@ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
			@ApiParam(value ="页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			// 登陆检查
			checkLogin(token, response);
			GdGoodsPage page = new GdGoodsPage();
			page.setNowPage(pageNum);
			page.setPageSize(pageSize);
			page.setOrderField("update_on");
			page.setOrderDirection(false);
			// 分页查询
			Pager<GdGoods,Long> gdGoodsPage= gdGoodsService.query(page, GdGoods.class);
			//根据商品ID 找出 所有sku 列表 
			if (gdGoodsPage.getList() != null && gdGoodsPage.getList().size() != 0) {
				for (int i = 0; i < gdGoodsPage.getList().size(); i++) {
					List<ShopValueVo> list = shopGoodsService.findSkuList(gdGoodsPage.getList().get(i).getDmId());
					gdGoodsPage.getList().get(i).setValList(list);
				}
			}
			RespJsonPHandler.respOK(gdGoodsPage, response, request);
		} catch (ServiceException se) {
			log.error("分页查询我要进货的商品列表出错！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("分页查询我要进货的商品列表出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description:添加便利店采购订单
	 * @param token
	 * @param shopPurchaseOrder	 便利店采购订单及订单项信息
	 * @param result 
	 * @param request
	 * @param response
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:24:24
	 */
	@ApiOperation(value = "添加便利店采购订单", notes = "添加便利店采购订单", position = 2)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@RequestHeader("token") String token,
			@ApiParam(value = "便利店采购订单及订单项信息", required = true) @RequestBody ShopPurchaseOrder shopPurchaseOrder,
			BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,
						ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
				return;
			} else {
				// 登陆检查
				checkLogin(token, response);

				// 计算订单sku总数
				Integer skuCount = statisticSku(shopPurchaseOrder);
				shopPurchaseOrder.setSkuNum(skuCount);

				// 订单编号
				String orderNo = createOrderNo();
				shopPurchaseOrder.setCode(orderNo);
				// 订单状态 0：待受理，1：待配送，2：配送中，3：已完成
				shopPurchaseOrder.setStatus(0);
				// 信息填充
				Date date = new Date();
				shopPurchaseOrder.setCreateTime(date);
				shopPurchaseOrder.setUpdateTime(date);
				// 添加订单
				shopPurchaseOrderService.addOrder(shopPurchaseOrder);
				RespHandler.respOK(response);
			}
		} catch (ServiceException se) {
			log.error("添加便利店采购订单！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("添加便利店采购订单出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}
	/**
	 * 
	 * @Description:修改便利店采购订单的状态，采购订单状态：0：待受理，1：待配送，2：配送中，3：已完成
	 * @param token
	 * @param dmId	          采购订单信息dmId
	 * @param status	 需要修改便利店采购订单的状态
	 * @param request
	 * @param response
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:25:32
	 */
	@ApiOperation(value = "修改便利店采购订单的状态", notes = "修改便利店采购订单的状态，采购订单状态：0：待受理，1：待配送，2：配送中，3：已完成", position = 4)
	@RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
	public void updateStatus(@RequestHeader("token") String token, 
			@ApiParam(value = "采购订单信息dmId", required = true) @RequestParam Long dmId,
			@ApiParam(value = "需要修改便利店采购订单的状态", required = true) @RequestParam Integer status,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 登陆检查
			checkLogin(token, response);
			// 修改订单状态，订单状态修改一定是从前往后修改，不可跨状态修改，不能逆修改
			// 1、当订单状态从“待受理”修改为“待配送”时，仓储中心商品sku减实际库存，加锁定库存
			// 2、当订单状态从“待配送”修改为“配送中”是，仓储中心商品sku减锁定库存
			// 3、当订单状态从“配送中”修改为“已完成”是，便利店商品sku增加实际库存
			shopPurchaseOrderService.modStatus(dmId, status);
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException se) {
			log.error("修改便利店采购订单的状态出错！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("修改便利店采购订单的状态出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description: 统计一个采购订单的中sku中数量
	 * @param shopPurchaseOrder	 采购订单对象
	 * @return 总sku数量
	 * Integer
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:25:52
	 */
	private Integer statisticSku(ShopPurchaseOrder shopPurchaseOrder) {
		List<ShopPurchaseOrderItem> shopPurchaseOrderItemList = shopPurchaseOrder.getShopPurchaseOrderItemList();
		Integer count = 0;
		if (shopPurchaseOrderItemList != null && shopPurchaseOrderItemList.size() > 0) {
			for (ShopPurchaseOrderItem shopPurchaseOrderItem : shopPurchaseOrderItemList) {
				count += shopPurchaseOrderItem.getPurchaseNum();
			}
		}
		return count;
	}

	/**
	 * 
	 * @Description: 创建订单编号
	 * @return订单编号
	 * String
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:26:04
	 */
	private String createOrderNo() {
		// TODO 订单规则 暂时用时间串
		return DateUtil.getNow();
	}
}
