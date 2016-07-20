package com.qtz.sm.controller.shopMAndSuperMKGoods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.model.GdGoodsOperationHistoryListJson;
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.service.GdGoodsOperationHistoryService;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.shop.vo.ShopGdGoodsSkuFilter;
import com.qtz.sm.shopManage.page.ShopMAndSuperMKGoodsPage;
import com.qtz.sm.shopManage.service.ShopManageService;
import com.qtz.sm.shopManage.vo.GoodsSkuVo;
import com.qtz.sm.shopManage.vo.ShopMAndSuperMKGoods;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * Description: Copyright: Copyright (c) 2016 Company: 深圳市擎天柱信息科技有限公司
 * 
 * @author 孙选
 * @version v1.0 2016/6/8
 */
@Api(value = "/shopMAndSuperMk/goods", description = "便利店管理公司和超市商品管理")
@Controller("ShopMAndSuperMKGoodsController")
@RequestMapping("/shopMAndSuperMk/goods")
public class ShopMAndSuperMKGoodsController extends BaseController {

	private static LogTool log = LogTool.getInstance(ShopMAndSuperMKGoodsController.class);

	@Autowired
	private GdGoodsService gdGoodsService;
	@Autowired
	private ShopManageService shopManageServicel;
	@Autowired
	private GdGoodsOperationHistoryService gdGoodsOperationHistoryService;

	/**
	 * 
	 * @Description:分页查询商品库
	 * @param response
	 * @param request
	 * @param token
	 * @param type
	 *            0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市
	 * @param pageNum
	 * @param pageSize
	 * @param shopManageGoodsGoodsPage
	 * @throws IOException
	 *             void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月12日 上午11:48:45
	 */
	@ApiOperation(value = "分页查询商品库", notes = "分页查询商品库", position = 5)
	@RequestMapping(value = "/getGoodsPages", method = RequestMethod.POST)
	public void getGoodsPages(HttpServletResponse response, HttpServletRequest request,
			@RequestHeader("token") String token,
			@ApiParam("页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
			@ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "查询类型:2-便利店管理公司3-超市", required = true) @RequestParam Integer type,
			@ApiParam(value = "查询条件") @RequestBody ShopMAndSuperMKGoodsPage shopMAndSuperMKGoodsPage)
					throws IOException {
		try {
			// 登陆检查
			checkLogin(token, response);

			shopMAndSuperMKGoodsPage.setNowPage(pageNum);
			shopMAndSuperMKGoodsPage.setPageSize(pageSize);
			shopMAndSuperMKGoodsPage.setOrderField("update_on");
			shopMAndSuperMKGoodsPage.setOrderDirection(false);
			// 2：便利店管理公司议价；3：胖胖超市
			shopMAndSuperMKGoodsPage.setType(type);
			if (!type.equals(2) && !type.equals(3)) {
				RespHandler.respError(RespMsg.not_found_type, response);
			}
			Pager<ShopMAndSuperMKGoods, Long> pager = shopManageServicel.queryGoods(shopMAndSuperMKGoodsPage);
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException e) {
			log.error("【商品管理-商品库(分类列表)】ServiceException异常：" + e);
			RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (Exception e) {
			log.error("查询商品库列表出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}

	/**
	 * 
	 * @Description:查询商品详情
	 * @param token
	 *            token
	 * @param goodsId
	 *            便利店商品ID
	 * @param type
	 *            0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市
	 * @param request
	 * @param response
	 *            void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:26:17
	 */
	@ApiOperation(value = "查询商品详情", notes = "查询商品详情", position = 0)
	@RequestMapping(value = "/getGoodsInfo", method = RequestMethod.GET)
	public void getGoodsInfo(@RequestHeader("token") String token,
			@ApiParam(value = "商品ID", required = true) @RequestParam Long goodsId,
			@ApiParam(value = "查询类型:2-便利店管理公司3-超市", required = true) @RequestParam Integer type,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			// 登陆检查
			checkLogin(token, response);
			ShopMAndSuperMKGoods shopMAndSuperMKGoods = new ShopMAndSuperMKGoods();
			shopMAndSuperMKGoods.setGoodsId(goodsId);
			shopMAndSuperMKGoods.setType(type);
			List<ShopMAndSuperMKGoods> list = shopManageServicel.getShopManageGoodsList(shopMAndSuperMKGoods);// 根据便利店ID和商品ID查询商品所对应的sku属性值
			final Map<Long, Integer> skuStockMap = new HashMap<>();
			final Map<Long, Double> skuPriceMap = new HashMap<>();
			if (list != null && list.size() > 0) {
				shopMAndSuperMKGoods = list.get(0);
				for (int i = 0; i < shopMAndSuperMKGoods.getGoodsSkuVos().size(); i++) {
					GoodsSkuVo goodsSkuVo = shopMAndSuperMKGoods.getGoodsSkuVos().get(i);
					skuStockMap.put(goodsSkuVo.getSkuId(), goodsSkuVo.getStock());
					Double price = 0d;
					if (goodsSkuVo.getBldglPrice() != null && type.equals(2)) {
						price = goodsSkuVo.getBldglPrice();
					} else if (goodsSkuVo.getCczxPrice() != null && type.equals(3)) {
						price = goodsSkuVo.getCczxPrice();
					}
					skuPriceMap.put(goodsSkuVo.getSkuId(), price);
				}
			}

			GdGoodsOutJson goodsOut = gdGoodsService.getGoodsDetail(goodsId, skuPriceMap, skuStockMap, false, false,
					null, new ShopGdGoodsSkuFilter(skuStockMap));
			if (goodsOut == null) {
				RespHandler.respError(RespMsg.not_found, response);
			} else {
				RespJsonPHandler.respOK(goodsOut, response, request);
			}
		} catch (ServiceException se) {
			log.error("查询商品详情失败.", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("查询商品详情出现系统错误.", e);
			RespHandler.respServerError(response);
		}
	}

	/**
	 * 
	 * @Description:分页查询商品的操作记录
	 * @param token
	 *            token
	 * @param goodsId
	 *            商品ID
	 * @param pageNum
	 * @param pageSize
	 * @param response
	 *            void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:26:17
	 */
	@ApiOperation(value = "分页查询商品的操作记录", notes = "分页查询商品的操作记录", position = 0)
	@RequestMapping(value = "/queryOperationHistory", method = RequestMethod.GET)
	public void queryOperationHistory(@RequestHeader("token") String token,
			@ApiParam("页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
			@ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@ApiParam(value = "商品ID", required = true) @RequestParam Long goodsId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 获取登陆信息
			User user = getUserJsonp(token, response, request);
			List<Long> oprators = new ArrayList<>();
			oprators.add(user.getDmId());
			Map<Long, String> userMap = new HashMap<>();
			userMap.put(user.getDmId(), user.getName());
			Map<Long, String> positionMap = new HashMap<>();
			positionMap.put(user.getDmId(), "管理员");
			GdGoodsOperationHistoryListJson json = gdGoodsOperationHistoryService.getGoodsOpretorHistory(goodsId,
					oprators, userMap, positionMap, pageNum, pageSize);
			RespJsonPHandler.respOK(json, response, request);
		} catch (ServiceException se) {
			log.error("分页查询商品的操作记录失败.", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("分页查询商品的操作记录现系统错误.", e);
			RespHandler.respServerError(response);
		}
	}

}
