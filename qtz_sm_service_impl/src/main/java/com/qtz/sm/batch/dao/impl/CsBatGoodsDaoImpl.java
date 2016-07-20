package com.qtz.sm.batch.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.batch.dao.CsBatGoodsDao;
import com.qtz.sm.batch.vo.CsBatGoods;
import com.qtz.sm.goods.dao.GdGoodsDao;
/**
 * <p>Title:CsBatGoodsDaoImpl</p>
 * <p>Description:批发单与商品关系DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
@Repository("csBatGoodsDaoImpl")
public class CsBatGoodsDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.batch.vo.CsBatGoods,java.lang.Long> implements CsBatGoodsDao {
	/**MYBatis命名空间名*/
	private static String preName = CsBatGoodsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<CsBatGoods> getGoodsInfoByOrderId(Long orderId) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(CsBatGoodsDao.class).getGoodsInfoByOrderId(orderId);
	}
}