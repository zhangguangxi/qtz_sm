package com.qtz.sm.store.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
import com.qtz.sm.batch.vo.CsBatOrder;
import com.qtz.sm.store.vo.CsCczxStock;
import com.qtz.sm.supermarket.vo.SupermarketSkuStatus;

import java.util.List;

/**
 * Title:CsCczxStockService<br/>
 * Description:(仓储中心商品库存SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-25
 */
public interface CsCczxStockService extends BaseService<CsCczxStock,Long>{
	
	
	 /**
     * 仓储中心----扣减库存
     * @param skuId
     * @param deductionsQuantity
     * @throws ServiceException
     */
	void cczxDeductionsStock(Long skuId,Integer deductionsQuantity,Integer status) throws ServiceException;
	
	/**
	 * 云仓储管理公司    商品管理    商品库     商品详情   商品规格
	 * @param goodsId
	 * @return
	 * @throws ServiceException
	 */
	List<CsCczxStock> queryStockQuantityAndPrice(Long goodsId)throws ServiceException;

	/**
	 * 仓储中心确认收货增加库存信息
	 * @param sku 批发单sku信息
	 * @param nowOrder 批发单信息
	 * @throws ServiceException
	 */
	void updateStock(CsBatGoodsSku sku, CsBatOrder nowOrder)throws ServiceException;
	
	/**
	 * 根据订单信息更新仓储中心库存-
	 * @param order 订单信息
	 * @param status 状态：0，创建订单扣减库存加预占库存；1,取消订单返回库存减预占库存；2,商家拒绝接单返回库存减预占库存；3，用户确认收货扣减预占库存；4，商家确认收货退款返回库存减预占库存
	 * @throws ServiceException
	 */
	void updateSuperMarketStockByOrder(SupermarketOrder order, int status)throws ServiceException;
	
	/**
	 * 根据订单信息更新便利店库存-
	 * @param order 订单信息
	 * @param status 状态：0，创建订单扣减库存加预占库存；1,取消订单返回库存减预占库存；2,商家拒绝接单返回库存减预占库存；3，用户确认收货扣减预占库存；4，商家确认收货退款返回库存减预占库存
	 * @throws ServiceException
	 */
	void updateShopStockByOrder(SupermarketOrder order, int status)throws ServiceException;

	/**
	 * 根据仓储中心ID和SKUID获取商品库存信息
	 * @param cczxId 仓储中心ID
	 * @param skuIds skuId字符串
	 * @throws ServiceException
	 */
	List<SupermarketSkuStatus> getGoodsStock(Long cczxId, String skuIds)throws ServiceException;

}