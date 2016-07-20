package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.goods.dao.GdGoodsBrandsDao;
import com.qtz.sm.goods.vo.GdGoodsBrands;
/**
 * <p>Title:GdGoodsBrandsDaoImpl</p>
 * <p>Description:品牌系列DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsBrandsDaoImpl")
public class GdGoodsBrandsDaoImpl extends MyBaitsDaoImpl<GdGoodsBrands,java.lang.Long> implements GdGoodsBrandsDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsBrandsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}