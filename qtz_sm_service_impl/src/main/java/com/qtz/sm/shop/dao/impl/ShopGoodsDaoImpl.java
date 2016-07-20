package com.qtz.sm.shop.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.dao.ShopGoodsDao;
import com.qtz.sm.shop.page.ShopGoodsPage;
import com.qtz.sm.shop.vo.ShopGdGoodsVo;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopGoodsSkuVo;
import com.qtz.sm.shop.vo.ShopGoodsVo;
import com.qtz.sm.shop.vo.ShopValueVo;
/**
 * <p>Title:ShopGoodsDaoImpl</p>
 * <p>Description:便利店商品DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选 - Laven
 * @version v1.0 2016-04-26
 */
@Repository("shopGoodsDaoImpl")
public class ShopGoodsDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopGoods,Long> implements ShopGoodsDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopGoodsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public void updateBatch(Map<String,Object>  map) throws DaoException {
		try{
			getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).updateBatch(map);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public List<ShopValueVo> findSkuList(Long goodsId) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).findSkuList(goodsId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public List<ShopGdGoodsVo> findGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).findGdGoodsListByShopId(shopGoodsPage);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public List<ShopGdGoodsVo> queryGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).queryGdGoodsListByShopId(shopGoodsPage);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public List<ShopValueVo> findSkuListByShopId(ShopGoods shopGoods) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).findSkuListByShopId(shopGoods);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public Integer findGdGoodsListByShopIdCount(ShopGoodsPage shopGoodsPage) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).findGdGoodsListByShopIdCount(shopGoodsPage);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public ShopGoodsVo getShopGoodsVo(ShopGoods shopGoods) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).getShopGoodsVo(shopGoods);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public List<ShopGoodsSkuVo> getShopGoodsSkus(ShopGoods shopGoods) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).getShopGoodsSkus(shopGoods);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public List<ShopGoodsVo> getShopGoodsVoList(ShopGoods shopGoods) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).getShopGoodsVoList(shopGoods);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public Integer getSpecificationCount(ShopGoods shopGoods) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).getSpecificationCount(shopGoods);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public Double getGoodsMinPrice(ShopGoods shopGoods) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).getGoodsMinPrice(shopGoods);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public void updateBatchStatus(Map<String, Object> map) throws DaoException {
		try{
			getMyBaitsTemplate().getSqlSession().getMapper(ShopGoodsDao.class).updateBatchStatus(map);
		}catch(Exception e){
			throw new DaoException(e);
		}
		
	}
}