package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.goods.dao.GdGoodsSkuPropertyDao;
import com.qtz.sm.goods.vo.GdGoodsSkuProperty;
/**
 * <p>Title:GdGoodsSkuPropertyDaoImpl</p>
 * <p>Description:商品SKU属性DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsSkuPropertyDaoImpl")
public class GdGoodsSkuPropertyDaoImpl extends MyBaitsDaoImpl<GdGoodsSkuProperty,java.lang.Long> implements GdGoodsSkuPropertyDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsSkuPropertyDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}