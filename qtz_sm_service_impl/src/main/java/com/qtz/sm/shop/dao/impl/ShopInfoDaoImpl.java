package com.qtz.sm.shop.dao.impl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.supp.dao.CsGysInfoDao;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.shop.dao.ShopInfoDao;
/**
 * <p>Title:ShopInfoDaoImpl</p>
 * <p>Description:便利店基本信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Repository("shopInfoDaoImpl")
public class ShopInfoDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopInfo,Long> implements ShopInfoDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopInfoDao.class.getName();
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