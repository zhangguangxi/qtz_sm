package com.qtz.sm.batch.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.batch.dao.CsBatGoodsSkuDao;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
/**
 * <p>Title:CsBatGoodsSkuDaoImpl</p>
 * <p>Description:批发单商品与SKU关系DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
@Repository("csBatGoodsSkuDaoImpl")
public class CsBatGoodsSkuDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.batch.vo.CsBatGoodsSku,java.lang.Long> implements CsBatGoodsSkuDao {
	/**MYBatis命名空间名*/
	private static String preName = CsBatGoodsSkuDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<CsBatGoodsSku> getSkuInfoByDmId(Long goodsId) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(CsBatGoodsSkuDao.class).getSkuInfoByDmId(goodsId);
	}
	@Override
	public List<CsBatGoodsSku> findListByBatOrderId(Long batOrderId) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(CsBatGoodsSkuDao.class).findListByBatOrderId(batOrderId);
	}
}