package com.qtz.sm.goods.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.dao.GdGoodsCategroyRateDao;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;

/**
 * <p>
 * Title:GdGoodsCategroyRateDaoImpl
 * </p>
 * <p>
 * Description:商品SKU溢价率DAO实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsCategroyRateDaoImpl")
public class GdGoodsCategroyRateDaoImpl extends MyBaitsDaoImpl<GdGoodsCategroyRate, java.lang.Long>
		implements GdGoodsCategroyRateDao {
	/** MYBatis命名空间名 */
	private static String preName = GdGoodsCategroyRateDao.class.getName();

	/**
	 * 【取得】MYBatis命名空间名
	 * 
	 * @return MYBatis命名空间名
	 */
	@Override
	protected String getPreName() {
		return preName;
	}


	@Override
	public List<GdGoodsCategroyRate> getRatesWithCategoryInfo(@Param(value="parentGoodsTypeId")Long parentGoodsTypeId) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().getMapper(GdGoodsCategroyRateDao.class).getRatesWithCategoryInfo(parentGoodsTypeId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}