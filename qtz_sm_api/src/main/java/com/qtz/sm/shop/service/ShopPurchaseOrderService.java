package com.qtz.sm.shop.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.shop.vo.ShopPurchaseOrder;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItem;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItemVo;
import com.qtz.sm.shop.vo.ShopPurchaseOrderVo;

/**
 * <p>Title:ShopPurchaseOrderService</p>
 * <p>Description:便利店采购订单服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-05-04
 */
public interface ShopPurchaseOrderService extends BaseService<com.qtz.sm.shop.vo.ShopPurchaseOrder, Long> {

	List<ShopPurchaseOrderItemVo> getList (ShopPurchaseOrderItem shopPurchaseOrderItem)throws ServiceException;
	
    /**
     * 通过便利店采购订单ID查询订单详情
     *
     * @param purchaseOrderId 便利店采购订单ID
     * @return 便利店采购订单详情
     * @throws ServiceException
     */
    ShopPurchaseOrderVo findById(Long purchaseOrderId) throws ServiceException;

    /**
     * 添加一条采购订单信息，同时保存订单项数据
     *
     * @param shopPurchaseOrder 采购订单信息
     * @throws ServiceException
     */
    void addOrder(ShopPurchaseOrder shopPurchaseOrder) throws ServiceException;

    /**
     * 修改订单状态，订单状态修改一定是从前往后修改，不可跨状态修改，不能逆修改
     * 1、当订单状态从“待受理”修改为“待配送”时，仓储中心商品sku减实际库存，加锁定库存
     * 2、当订单状态从“待配送”修改为“配送中”是，仓储中心商品sku减锁定库存
     * 3、当订单状态从“配送中”修改为“已完成”是，便利店商品sku增加实际库存
     *
     * @param purchaseOrderId 便利店采购订单ID
     * @param status          需要修改的订单状态
     * @throws ServiceException
     */
    void modStatus(Long purchaseOrderId, int status) throws ServiceException;

}