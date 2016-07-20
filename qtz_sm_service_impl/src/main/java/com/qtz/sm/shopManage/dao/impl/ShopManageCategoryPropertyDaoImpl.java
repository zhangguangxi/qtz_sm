package com.qtz.sm.shopManage.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shopManage.dao.ShopManageCategoryDao;
import com.qtz.sm.shopManage.dao.ShopManageCategoryPropertyDao;
/**
 * <p>Title:ShopManageCategoryPropertyDaoImpl</p>
 * <p>Description:便利店管理公司运营分类与便利店关联DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
@Repository("ShopManageCategoryPropertyDaoImpl")
public class ShopManageCategoryPropertyDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shopManage.vo.ShopManageCategoryProperty,java.lang.Long> implements ShopManageCategoryPropertyDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopManageCategoryPropertyDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public void delByShopId(Long shopId) throws DaoException {
		try{
			getMyBaitsTemplate().getSqlSession().getMapper(ShopManageCategoryPropertyDao.class).delByShopId(shopId);
		}catch(Exception e){
			throw new DaoException(e);
		}
		
	}
}