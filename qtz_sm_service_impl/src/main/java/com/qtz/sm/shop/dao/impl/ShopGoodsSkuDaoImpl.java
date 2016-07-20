package com.qtz.sm.shop.dao.impl;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.dao.ShopGoodsSkuDao;
import com.qtz.sm.shop.page.ShopGoodsPage;

/**
 * <p>Title:ShopGoodsSkuDaoImpl</p>
 * <p>Description:便利店商品skuDAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Repository("shopGoodsSkuDaoImpl")
public class ShopGoodsSkuDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopGoodsSku,Long> implements ShopGoodsSkuDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopGoodsSkuDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}

	@Override
	public void addActualStock(Long shopSkuId, int stock, Date updateTime) throws DaoException {
		try{
			getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsSkuDao.class).addActualStock(shopSkuId, stock, updateTime);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	@Override
	public void subActualStock(Long shopSkuId, int stock, Date updateTime) throws DaoException {
		try{
			getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsSkuDao.class).subActualStock(shopSkuId, stock, updateTime);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	@Override
	public void subFreezeStock(Long shopSkuId, int stock, Date updateTime) throws DaoException {
		try{
			getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsSkuDao.class).subFreezeStock(shopSkuId, stock, updateTime);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	@Override
	public List<Long> findGoodIdList(ShopGoodsPage shopGoodsPage) throws DaoException {
		try{
		 return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsSkuDao.class).findGoodIdList(shopGoodsPage);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
}