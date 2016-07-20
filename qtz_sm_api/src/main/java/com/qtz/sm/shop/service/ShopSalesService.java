package com.qtz.sm.shop.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.shop.vo.ShopSales;
/**
 * <p>Title:ShopSalesService</p>
 * <p>Description:店铺销量信息服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
public interface ShopSalesService extends BaseService<com.qtz.sm.shop.vo.ShopSales,java.lang.Long> {

	/**
	 * 
	 * @Description:根据便利店ID和SKUID统计销售信息
	 * @param shopGoods
	 * @return
	 * @throws ServiceException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月28日 
	 */
	Integer countSales(ShopSales shopSales)throws ServiceException;
	
}