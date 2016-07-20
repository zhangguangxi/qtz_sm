package com.qtz.sm.shop.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItem;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItemVo;
/**
 * <p>Title:ShopPurchaseOrderItemService</p>
 * <p>Description:便利店采购订单项服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选- Laven
 * @version v1.0 2016-05-04
 */
public interface ShopPurchaseOrderItemService extends BaseService<com.qtz.sm.shop.vo.ShopPurchaseOrderItem,Long> {
	/**
	 * 根据订货单编号ID获取订货单商品详情 包含sku值
	 * @Description:TODO
	 * @param shopPurchaseOrderItem
	 * @return
	 * @throws ServiceException
	 * List<ShopPurchaseOrderItemVo>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月25日 上午9:23:58
	 */
	List<ShopPurchaseOrderItemVo> getOrderItem( ShopPurchaseOrderItem shopPurchaseOrderItem) throws ServiceException;
}