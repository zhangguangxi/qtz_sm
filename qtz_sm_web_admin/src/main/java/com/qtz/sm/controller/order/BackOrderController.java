//package com.qtz.sm.controller.order;
//
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespJsonPHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ServiceException;
//import com.qtz.orig.common.Result;
//import com.qtz.orig.order.page.SupermarketPage;
//import com.qtz.orig.order.service.SupermarketService;
//import com.qtz.orig.order.vo.SupermarketOrder;
//import com.qtz.sm.controller.BaseController;
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
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
///**
// * <p>SupermarketOrderController</p>
// * <p>Description:后端订单管理Controller类</p>
// * <p>Copyright: Copyright (c) 2016</p>
// * <p>Company: 深圳擎天柱信息技术有限公司</p>
// *
// * @author yangwei
// * @version v1.0 2016-06-18
// */
//@Api(value = "/backOrder/order", description = "后端订单管理")
//@Controller("BackOrderController")
//@RequestMapping("/backOrder/order")
//public class BackOrderController extends BaseController {
//    /**
//     * 初始化日志对象
//     */
//    private final static Logger log = Logger.getLogger(BackOrderController.class);
//    /**
//     * 注入超市类目服务Service类
//     */
//    @Autowired
//    private SupermarketService supermarketService;
//
//    private static final int SUPERMARKET_ORDER_TYPE = 1;
//
//    private static final int SHOP_ORDER_TYPE = 2;
//
//
//    /**
//     * 分页查询超市/便利店订单信息
//     *
//     * @param sellerId    卖家ID
//     * @param orderId    订单编号
//     * @param receivingName   收货人姓名
//     * @param receivingPhone  电话
//     * @param receivingAddress 收货地址
//     * @param status  订单状态
//     * @param startTime 开始时间
//     * @param endTime 结束时间
//     */
//    @ApiOperation(value = "分页查询超市/便利店订单信息",
//            notes = "分页查询超市/便利店订单信息",
//            position = 0)
//    @RequestMapping(value = "/getList", method = RequestMethod.GET)
//    public void getList(@RequestHeader("token") String token,
//    		@ApiParam(value="卖家ID") @RequestParam(required=true)  Long sellerId,
//    		@ApiParam(value="订单编号", required = false)  Long orderId,@ApiParam(value="收货人姓名", required = false)  String receivingName,
//    		@ApiParam(value="电话", required = false)  String receivingPhone,@ApiParam(value="收货地址", required = false)  String receivingAddress,
//    		@ApiParam(value="订单状态", required = false)  Integer status,@ApiParam(value="开始时间", required = false)  Long startTime,
//    		@ApiParam(value="结束时间", required = false)  Long endTime,
//    		@ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex, @ApiParam("行数") @RequestParam(defaultValue = "20") Integer pageSize,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            SupermarketPage page = new SupermarketPage();
//            page.setSellerId(sellerId);
//            page.setDmId(orderId);
//            page.setReceivingName(receivingName);
//            page.setReceivingPhone(receivingPhone);
//            page.setReceivingAddress(receivingAddress);
//            page.setOrderStatus(status);
//            page.setOrderStartTime(startTime);
//            page.setOrderEndTime(endTime);
//            page.setNowPage(pageIndex);
//            page.setPageSize(pageSize);
//            com.qtz.orig.common.Result<SupermarketOrder> result = supermarketService.backstagePager(page);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getPager().getList(), response, request);
//            }else
//            {
//            	log.error("分页查询超市/便利店订单信息信息出错！卖家ID："+sellerId);
//                RespHandler.respError(RespMsg.getOrderList_error(1), response);
//            }
//        } catch (ServiceException se) {
//            log.error("分页查询超市/便利店订单信息信息出错！卖家ID："+sellerId, se);
//            RespHandler.respError(RespMsg.getOrderList_error(1), response);
//        } catch (Exception e) {
//            log.error("分页查询超市/便利店订单信息信息出现系统错误.卖家ID："+sellerId, e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 分页查询仓储中心订单信息
//     *
//     * @param storageId   仓储中心ID
//     * @param orderId    订单编号
//     * @param receivingName   收货人姓名
//     * @param receivingPhone  电话
//     * @param receivingAddress 收货地址
//     * @param status  订单状态
//     * @param startTime 开始时间
//     * @param endTime 结束时间
//     */
//    @ApiOperation(value = "分页查询仓储中心订单信息",
//            notes = "分页查询仓储中心订单信息",
//            position = 1)
//    @RequestMapping(value = "/getCczxList", method = RequestMethod.GET)
//    public void getCczxList(@RequestHeader("token") String token,
//    		@ApiParam(value="仓储中心ID") @RequestParam(required=true)  Long storageId,
//    		@ApiParam(value="订单编号", required = false)  Long orderId,@ApiParam(value="收货人姓名", required = false)  String receivingName,
//    		@ApiParam(value="电话", required = false)  String receivingPhone,@ApiParam(value="收货地址", required = false)  String receivingAddress,
//    		@ApiParam(value="订单状态", required = false)  Integer status,@ApiParam(value="开始时间", required = false)  Long startTime,
//    		@ApiParam(value="结束时间", required = false)  Long endTime,
//    		@ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex, @ApiParam("行数") @RequestParam(defaultValue = "20") Integer pageSize,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            SupermarketPage page = new SupermarketPage();
////            page.set
//            page.setDmId(orderId);
//            page.setReceivingName(receivingName);
//            page.setReceivingPhone(receivingPhone);
//            page.setReceivingAddress(receivingAddress);
//            page.setOrderStatus(status);
//            page.setOrderStartTime(startTime);
//            page.setOrderEndTime(endTime);
//            page.setNowPage(pageIndex);
//            page.setPageSize(pageSize);
//            com.qtz.orig.common.Result<SupermarketOrder> result = supermarketService.backstagePager(page);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getPager().getList(), response, request);
//            }else
//            {
//            	log.error("分页查询仓储中心订单信息！仓储中心ID："+storageId);
//                RespHandler.respError(RespMsg.getOrderList_error(1), response);
//            }
//        } catch (ServiceException se) {
//            log.error("分页查询超市/便利店订单信息信息出错！", se);
//            RespHandler.respError(RespMsg.getOrderList_error(1), response);
//        } catch (Exception e) {
//            log.error("分页查询超市/便利店订单信息信息出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 查询订单详情信息
//     *
//     * @param orderId    订单编号
//     */
//    @ApiOperation(value = "查询订单详情信息",
//            notes = "查询订单详情信息",
//            position = 2)
//    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.GET)
//    public void getOrderInfo(@RequestHeader("token") String token,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.backstageOrderInfo(orderId);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCarrier(), response, request);
//            }else
//            {
//            	log.error("查询订单详情信息！");
//                RespHandler.respError(RespMsg.order_null, response);
//            }
//        } catch (ServiceException se) {
//            log.error("查询订单详情信息！", se);
//            RespHandler.respError(RespMsg.order_null, response);
//        } catch (Exception e) {
//            log.error("查询订单详情信息出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 超市是否接单操作
//     *
//     * @param orderId    订单编号
//     * @param status    3：拒绝接单,4:接单
//     * @param sellerId    所属仓储中心ID
//     */
//    @ApiOperation(value = "超市是否接单操作",
//            notes = "超市是否接单操作",
//            position = 3)
//    @RequestMapping(value = "/whetherSuperMarketReceiveOrder", method = RequestMethod.POST)
//    public void whetherSuperMarketReceiveOrder(@RequestHeader("token") String token,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//    		@ApiParam("订单状态") @RequestParam int status,
//    		@ApiParam("所属仓储中心ID") @RequestParam Long sellerId,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.updateOrderStatus(orderId, status, sellerId,SUPERMARKET_ORDER_TYPE);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	if(status == 3)
//            	{
//            		log.error("超市拒绝接单出错！");
//            		RespHandler.respError(RespMsg.refusedOrder_error(status), response);
//            	}else if(status == 4)
//            	{
//            		log.error("超市接单出错！");
//            		RespHandler.respError(RespMsg.receivingOrder_error(status), response);
//            	}
//            }
//        } catch (ServiceException se) {
//            log.error("更新超市订单状态出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("更新超市订单状态出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 便利店是否接单操作
//     *
//     * @param orderId    订单编号
//     * @param status    3：拒绝接单,4:接单
//     * @param sellerId    所属仓储中心ID
//     */
//    @ApiOperation(value = "便利店是否接单操作",
//            notes = "便利店是否接单操作",
//            position = 4)
//    @RequestMapping(value = "/whetherShopReceiveOrder", method = RequestMethod.POST)
//    public void whetherShopReceiveOrder(@RequestHeader("token") String token,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//    		@ApiParam("订单状态") @RequestParam int status,
//    		@ApiParam("所属仓储中心ID") @RequestParam Long sellerId,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.updateOrderStatus(orderId, status, sellerId,SHOP_ORDER_TYPE);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	if(status == 3)
//            	{
//            		log.error("便利店拒绝接单出错！");
//            		RespHandler.respError(RespMsg.refusedOrder_error(status), response);
//            	}else if(status == 4)
//            	{
//            		log.error("便利店接单出错！");
//            		RespHandler.respError(RespMsg.receivingOrder_error(status), response);
//            	}
//            }
//        } catch (ServiceException se) {
//            log.error("更新便利店订单状态出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("更新便利店订单状态出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 超市是否同意退款操作
//     *
//     * @param orderId    订单编号
//     * @param status    ２:同意退款,８:不同意退款
//     * @param sellerId    所属仓储中心ID
//     * @param reason    理由
//     */
//    @ApiOperation(value = "超市是否同意退款操作",
//            notes = "超市是否同意退款操作",
//            position = 5)
//    @RequestMapping(value = "/whetherSuperMarketRefundOrder", method = RequestMethod.POST)
//    public void whetherSuperMarketRefundOrder(@RequestHeader("token") String token,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//    		@ApiParam("订单状态") @RequestParam int status,
//    		@ApiParam("所属仓储中心ID") @RequestParam Long sellerId,
//    		@ApiParam("理由") @RequestParam String reason,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.sellerOperationRefund(orderId, status, sellerId,reason,SUPERMARKET_ORDER_TYPE);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	if(status == 2)
//            	{
//            		log.error("超市同意退款出错！");
//            		RespHandler.respError(RespMsg.agreedRefund_error(), response);
//            	}else if(status == 8)
//            	{
//            		log.error("超市拒绝退款出错！");
//            		RespHandler.respError(RespMsg.refusedRefund_error(), response);
//            	}
//            }
//        } catch (ServiceException se) {
//            log.error("更新超市订单状态出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("更新超市订单状态出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 便利店是否同意退款操作
//     *
//     * @param orderId    订单编号
//     * @param status    ２:同意退款,８:不同意退款
//     * @param sellerId    所属仓储中心ID
//     * @param reason    理由
//     */
//    @ApiOperation(value = "便利店是否同意退款操作",
//            notes = "便利店是否同意退款操作",
//            position = 6)
//    @RequestMapping(value = "/whetherShopRefundOrder", method = RequestMethod.POST)
//    public void whetherShopRefundOrder(@RequestHeader("token") String token,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//    		@ApiParam("订单状态") @RequestParam int status,
//    		@ApiParam("所属仓储中心ID") @RequestParam Long sellerId,
//    		@ApiParam("理由") @RequestParam String reason,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.sellerOperationRefund(orderId, status, sellerId,reason,SHOP_ORDER_TYPE);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	if(status == 2)
//            	{
//            		log.error("便利店同意退款出错！");
//            		RespHandler.respError(RespMsg.agreedRefund_error(), response);
//            	}else if(status == 8)
//            	{
//            		log.error("便利店拒绝退款出错！");
//            		RespHandler.respError(RespMsg.refusedRefund_error(), response);
//            	}
//            }
//        } catch (ServiceException se) {
//            log.error("更新便利店订单状态出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("更新便利店订单状态出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//    /**
//     * 添加仲裁操作
//     *
//     * @param orderId    订单编号
//     * @param status    介入操作状态:２同意退款　８不同意退款　（0:完成，1:未支付，2:同意退款3：拒绝接单 5：商家待接单，7退款中，8：不同意退款 ，4:待收货）
//     * @param resultInfo    仲裁结果信息
//     */
//    @ApiOperation(value = "添加仲裁操作",
//            notes = "添加仲裁操作",
//            position =7)
//    @RequestMapping(value = "/arbitrationOrder", method = RequestMethod.POST)
//    public void arbitrationOrder(@RequestHeader("token") String token,
//    		@ApiParam("订单编号") @RequestParam Long orderId,
//    		@ApiParam("订单状态") @RequestParam int status,
//    		@ApiParam("仲裁结果信息") @RequestParam String resultInfo,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //登陆检查
//            checkLogin(token, response);
//            Result<SupermarketOrder> result = supermarketService.adminIntervention(orderId, status,resultInfo);
//            if(result.isSuccess())
//            {
//            	RespJsonPHandler.respOK(result.getCode(), response, request);
//            }else
//            {
//            	log.error("添加仲裁出错！");
//            	RespHandler.respError(RespMsg.agreedRefund_error(), response);
//            }
//        } catch (ServiceException se) {
//            log.error("添加仲裁出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
//        } catch (Exception e) {
//            log.error("添加仲裁出现系统错误.", e);
//            RespJsonPHandler.respServerError(response, request);
//        }
//    }
//
//}