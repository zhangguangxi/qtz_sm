package com.qtz.sm.batch.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
/**
 * <p>Title:CsBatGoodsSkuDao</p>
 * <p>Description:批发单商品与SKU关系DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
public interface CsBatGoodsSkuDao extends BizDao<com.qtz.sm.batch.vo.CsBatGoodsSku,java.lang.Long> {

	/**
	 * 根据批发单下面商品ID获取商品SKU详情信息
	 * @author yangwei
	 * @param dmId		批发单下面商品ID
	 * @throws DaoException
	 */
	List<CsBatGoodsSku> getSkuInfoByDmId(Long goodsId) throws DaoException;

	/**
	 * 根据批发单ID获取商品SKU信息(用户更新对应SKU的库存)
	 * @author yangwei
	 * @param dmId		批发单下面商品ID
	 * @throws DaoException
	 */
	List<CsBatGoodsSku> findListByBatOrderId(Long batOrderId) throws DaoException;
	
}