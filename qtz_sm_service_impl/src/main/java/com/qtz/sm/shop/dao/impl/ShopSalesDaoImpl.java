package com.qtz.sm.shop.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.dao.ShopGoodsDao;
import com.qtz.sm.shop.dao.ShopSalesDao;
import com.qtz.sm.shop.vo.ShopSales;
/**
 * <p>Title:ShopSalesDaoImpl</p>
 * <p>Description:店铺销量信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
@Repository("shopSalesDaoImpl")
public class ShopSalesDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopSales,java.lang.Long> implements ShopSalesDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopSalesDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public Integer countSales(ShopSales shopSales) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(ShopSalesDao.class).countSales(shopSales);
	}
}