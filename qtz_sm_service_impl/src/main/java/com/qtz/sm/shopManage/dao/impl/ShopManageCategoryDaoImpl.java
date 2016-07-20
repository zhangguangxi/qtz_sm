package com.qtz.sm.shopManage.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.dao.ShopInfoDao;
import com.qtz.sm.shopManage.dao.ShopManageCategoryDao;
import com.qtz.sm.shopManage.vo.ShopManageCategory;
/**
 * <p>Title:ShopManageCategoryDaoImpl</p>
 * <p>Description:便利店管理公司运营分类DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
@Repository("ShopManageCategoryDaoImpl")
public class ShopManageCategoryDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shopManage.vo.ShopManageCategory,java.lang.Long> implements ShopManageCategoryDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopManageCategoryDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<ShopManageCategory> findListByShopId(ShopManageCategory shopManageCategory) throws DaoException{
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopManageCategoryDao.class).findListByShopId(shopManageCategory);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
}