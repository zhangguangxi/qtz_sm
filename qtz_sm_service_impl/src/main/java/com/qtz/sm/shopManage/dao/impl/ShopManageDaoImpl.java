package com.qtz.sm.shopManage.dao.impl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.dao.ShopInfoDao;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.shopManage.dao.ShopManageDao;
/**
 * <p>Title:ShopManageDaoImpl</p>
 * <p>Description:便利店管理公司DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Repository("shopManageDaoImpl")
public class ShopManageDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shopManage.vo.ShopManage,Long> implements ShopManageDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopManageDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}

	@Override
	public String findMaxCode(String prefix) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopInfoDao.class).findMaxCode(prefix);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
}