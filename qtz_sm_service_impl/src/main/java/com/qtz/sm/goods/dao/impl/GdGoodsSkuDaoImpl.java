package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.goods.dao.GdGoodsSkuDao;
import com.qtz.sm.goods.vo.GdGoodsSku;
/**
 * <p>Title:GdGoodsSkuDaoImpl</p>
 * <p>Description:商品SKUDAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsSkuDaoImpl")
public class GdGoodsSkuDaoImpl extends MyBaitsDaoImpl<GdGoodsSku,java.lang.Long> implements GdGoodsSkuDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsSkuDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}