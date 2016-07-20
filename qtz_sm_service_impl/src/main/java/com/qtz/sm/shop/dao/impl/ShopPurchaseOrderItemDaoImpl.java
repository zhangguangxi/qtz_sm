package com.qtz.sm.shop.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.dao.ShopPurchaseOrderItemDao;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItem;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItemVo;
/**
 * <p>Title:ShopPurchaseOrderItemDaoImpl</p>
 * <p>Description:便利店采购订单项DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选 - Laven
 * @version v1.0 2016-05-04
 */
@Repository("shopPurchaseOrderItemDaoImpl")
public class ShopPurchaseOrderItemDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopPurchaseOrderItem,Long> implements ShopPurchaseOrderItemDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopPurchaseOrderItemDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<ShopPurchaseOrderItemVo> getOrderItem(ShopPurchaseOrderItem shopPurchaseOrderItem) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopPurchaseOrderItemDao.class).getOrderItem(shopPurchaseOrderItem);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
}