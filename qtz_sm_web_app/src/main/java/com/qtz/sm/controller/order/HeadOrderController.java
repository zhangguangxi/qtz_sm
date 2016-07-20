//package com.qtz.sm.controller.order;
//
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespJsonPHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ServiceException;
//import com.qtz.orig.common.Result;
//import com.qtz.orig.order.service.SupermarketService;
//import com.qtz.orig.order.vo.SupermarketGoods;
//import com.qtz.orig.order.vo.SupermarketOrder;
//import com.qtz.sm.controller.BaseController;
//import com.qtz.sm.goods.service.GdGoodsSkuRateService;
//import com.qtz.sm.goods.vo.GdGoodsSkuRate;
//import com.wordnik.swagger.annotations.Api;
//import com.wordnik.swagger.annotations.ApiOperation;
//import com.wordnik.swagger.annotations.ApiParam;
//
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// * <p>SupermarketOrderController</p>
// * <p>Description:前端订单操作Controller类</p>
// * <p>Copyright: Copyright (c) 2016</p>
// * <p>Company: 深圳擎天柱信息技术有限公司</p>
// *
// * @author yangwei
// * @version v1.0 2016-06-18
// */
//@Api(value = "/headOrder/order", description = "前端订单操作")
//@Controller("HeadOrderController")
//@RequestMapping("/headOrder/order")
//public class HeadOrderController extends BaseController {
//    /**
//     * 初始化日志对象
//     */
//    private final static Logger log = Logger.getLogger(HeadOrderController.class);
//    /**
//     * 注入超市类目服务Service类
//     */
//    @Autowired
//    private SupermarketService supermarketService;
//
//    @Autowired
//    private GdGoodsSkuRateService gdGoodsSkuRateService;
//
//    /**
//     * 用户创建订单操作
//     *
//	 * @param supermarketOrder 订单信息
//     */
//    @ApiOperation(value = "用户创建订单操作",
//            notes = "用户创建订单操作",
//            position = 0)
//    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
//    public void createOrder(@RequestHeader("token") String token,
////    		@ApiParam(value = "订单信息", required = true) @RequestBody SupermarketOrder supermarketOrder,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            SupermarketOrder supermarketOrder = new SupermarketOrder();
//            supermarketOrder.setOrderStatus(1);
//            supermarketOrder.setOrderPrice(100d);
//            supermarketOrder.setCouponPrice(10d);
//            supermarketOrder.setPaymentPrice(90d);
//            supermarketOrder.setCouponId(12345646L);
//            supermarketOrder.setUserId(getUser(token).getDmId());
//            supermarketOrder.setSellerId(13245689L);
//            supermarketOrder.setCrtime(new Date().getTime());
//            supermarketOrder.setNote("快速送达");
//            supermarketOrder.setGoodsCount(3);
//            supermarketOrder.setExpectedTime(new Date().getTime());
//            supermarketOrder.setReceivingPhone("13486321365");
//            supermarketOrder.setReceivingName("张三");
//            supermarketOrder.setReceivingAddress("大冲国际中心五号楼41层");
//            supermarketOrder.setOrderType(1);
//            supermarketOrder.setStorageId(12345678931L);
//            List<SupermarketGoods> SupermarketGoods = new ArrayList<SupermarketGoods>();
//            SupermarketGoods goods = new SupermarketGoods();
//            goods.setGoodsName("2016夏季商场同款_888888888");
//            goods.setGoodsNum(3);
//            goods.setGoodsPrice(40D);
//            goods.setSkuId(1706283527178240L);
//            goods.setGoodsId(1706283527047168L);
//            goods.setSupplyPrice(30D);
//            goods.setDiscountPrice(10D);
//            goods.setGoodsImg("https://img.alicdn.com/imgextra/i4/454291526/TB2tFDNpXXXXXXSXFXXXXXXXXXX-454291526.jpg");
//            goods.setRemark("&lt;p class=&quot;");
//            goods.setGoodsProValMsg("123");
//            goods.setSellerId(13245689L);
//            GdGoodsSkuRate rate = gdGoodsSkuRateService.getGoodsSkuRate(goods.getGoodsId(), goods.getSkuId());
//            if(rate==null)
//            {
//            	log.error("用户创建订单操作出错！");
//            	RespHandler.respError(RespMsg.agreedRefund_error(), response);
//            }else
//            {
//            	goods.setStorePercent(rate.getBldglRate());
//            	goods.setSupermarketPercent(rate.getPpcsRate());
//            	goods.setSupplyChainPercent(rate.getGylRate());
//            	goods.setStoragePercent(rate.getYccglRate());
//            	SupermarketGoods.add(goods);
//            	supermarketOrder.setSupermarketGoods(SupermarketGoods);
//            	Result<SupermarketOrder> result = supermarketService.createOrder(supermarketOrder);
//            	if(result.isSuccess())
//            	{
//            		RespJsonPHandler.respOK(result.getCode(), response, request);
//            	}else
//            	{
//            		log.error("用户创建订单操作出错！");
//            		RespHandler.respError(RespMsg.subOrder_error(0), response);
//            	}
//            }
//        } catch (ServiceException se) {
//            log.error("用户创建订单操作出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("用户创建订单操作出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 用户支付订单操作
//     *
//	 * @param supermarketOrder 订单信息
//     */
//    @ApiOperation(value = "用户支付订单操作",
//            notes = "用户支付订单操作",
//            position = 1)
//    @RequestMapping(value = "/payOrder", method = RequestMethod.POST)
//    public void payOrder(@RequestHeader("token") String token,
//    		@ApiParam(value = "订单信息", required = true) @RequestBody SupermarketOrder supermarketOrder,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.createOrder(supermarketOrder);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	log.error("用户支付订单操作出错！");
//            	RespHandler.respError(RespMsg.query_orderPayment_status_failure, response);
//            }
//        } catch (ServiceException se) {
//            log.error("用户支付订单操作出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("用户支付订单操作出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 用户确认收货操作
//     *
//     * @param orderId    订单编号
//     */
//    @ApiOperation(value = "用户确认收货操作",
//            notes = "用户确认收货操作",
//            position = 2)
//    @RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
//    public void confirmOrder(@RequestHeader("token") String token,
//    		@ApiParam("用户ID") @RequestParam Long userId,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.userConfirmReceipt(userId, orderId);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	log.error("用户确认收货出错！");
//            	RespHandler.respError(RespMsg.confirmReceipt_error(), response);
//            }
//        } catch (ServiceException se) {
//            log.error("用户确认收货出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("用户确认收货系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 会员申请退货操作
//     *
//     * @param userId　会员ID
//	 * @param supermarketOrderId　超市ID
//	 * @param reason 退货原因
//	 * @param goodsImg
//     */
//    @ApiOperation(value = "会员申请退货操作",
//            notes = "会员申请退货操作",
//            position = 3)
//    @RequestMapping(value = "/returnOrder", method = RequestMethod.POST)
//    public void returnOrder(@RequestHeader("token") String token,
//    		@ApiParam("用户ID") @RequestParam Long userId,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//    		@ApiParam("退货原因") @RequestParam String reason,
//    		@ApiParam(value="退货上传图片", required = false) @RequestParam String goodsImg,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.userApplicationForReturn(userId,orderId, reason,goodsImg);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	log.error("会员申请退货操作出错！");
//            	RespHandler.respError(RespMsg.applyRefund_error(), response);
//            }
//        } catch (ServiceException se) {
//            log.error("会员申请退货操作出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("会员申请退货操作出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 用户取消订单操作
//     *
//	 * @param userId 会员ID
//	 * @param supermarketOrderId　超市ID
//     */
//    @ApiOperation(value = "用户取消订单操作",
//            notes = "用户取消订单操作",
//            position = 4)
//    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
//    public void cancelOrder(@RequestHeader("token") String token,
//    		@ApiParam("用户ID") @RequestParam Long userId,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.userCancelOrder(userId, orderId);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	log.error("用户取消订单操作出错！");
//            	RespHandler.respError(RespMsg.cancelOrder_error(), response);
//            }
//        } catch (ServiceException se) {
//            log.error("用户取消订单操作出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("用户取消订单操作出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//}