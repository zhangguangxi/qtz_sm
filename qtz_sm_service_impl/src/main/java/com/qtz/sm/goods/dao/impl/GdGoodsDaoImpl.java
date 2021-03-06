package com.qtz.sm.goods.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.dao.GdGoodsDao;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.shop.vo.ShopValueVo;
/**
 * <p>Title:GdGoodsDaoImpl</p>
 * <p>Description:商品DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsDaoImpl")
public class GdGoodsDaoImpl extends MyBaitsDaoImpl<GdGoods,java.lang.Long> implements GdGoodsDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public List<ShopValueVo> findSkuList(Long goodsId) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(GdGoodsDao.class).findSkuList(goodsId);
	}
}