package com.qtz.sm.shop.dao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.vo.ShopSales;
/**
 * <p>Title:ShopSalesDao</p>
 * <p>Description:店铺销量信息DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
public interface ShopSalesDao extends BizDao<com.qtz.sm.shop.vo.ShopSales,java.lang.Long> {

	/**
	 * 
	 * @Description:根据便利店ID和SKUID统计销售信息
	 * @param shopSales
	 * @return
	 * @throws DaoException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月27日
	 */
	Integer countSales(ShopSales shopSales)throws DaoException;
	
}