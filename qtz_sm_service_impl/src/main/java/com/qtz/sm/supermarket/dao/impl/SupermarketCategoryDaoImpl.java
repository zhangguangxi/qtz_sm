package com.qtz.sm.supermarket.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.supermarket.dao.SupermarketCategoryDao;
import com.qtz.sm.supermarket.vo.SupermarketCategoryVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsSkuVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsVo;
/**
 * <p>Title:SupermarketCategoryDaoImpl</p>
 * <p>Description:超市类目DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-29
 */
@Repository("supermarketCategoryDaoImpl")
public class SupermarketCategoryDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.supermarket.vo.SupermarketCategory,Long> implements SupermarketCategoryDao {
	/**MYBatis命名空间名*/
	private static String preName = SupermarketCategoryDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<SupermarketCategoryVo> findFirstLevelAll(int level) throws DaoException {
		try {
	          return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryDao.class).findFirstLevelAll(level);
	       } catch (Exception e) {
	           throw new DaoException(e);
	       }
	}
	@Override
	public List<SupermarketGoodsVo> getGoodsList(Map<String,String> param) throws DaoException {
		try {
	          return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryDao.class).getGoodsList(param);
	       } catch (Exception e) {
	           throw new DaoException(e);
	       }
	}
	@Override
	public SupermarketGoodsVo getSupermarketGoodsVo(Long goodsId) throws DaoException {
		try {
	          return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryDao.class).getSupermarketGoodsVo(goodsId);
	       } catch (Exception e) {
	           throw new DaoException(e);
	       }
	}
	@Override
	public List<SupermarketGoodsSkuVo> getShopGoodsSkus(Map<String,Long> param) throws DaoException {
		try {
	          return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryDao.class).getShopGoodsSkus(param);
	       } catch (Exception e) {
	           throw new DaoException(e);
	       }
	}
	@Override
	public SupermarketGoodsVo getSupermarketGoodsDetail(Long goodsId) throws DaoException {
		try {
	          return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryDao.class).getSupermarketGoodsDetail(goodsId);
	       } catch (Exception e) {
	           throw new DaoException(e);
	       }
	}
	@Override
	public List<SupermarketGoodsVo> getEnjoyGoodsList(Long cczxId) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryDao.class).getEnjoyGoodsList(cczxId);
	}
}