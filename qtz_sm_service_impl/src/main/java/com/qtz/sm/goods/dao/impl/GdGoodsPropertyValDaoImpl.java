package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.goods.dao.GdGoodsPropertyValDao;
import com.qtz.sm.goods.vo.GdGoodsPropertyVal;
/**
 * <p>Title:GdGoodsPropertyValDaoImpl</p>
 * <p>Description:商品属性实际值DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsPropertyValDaoImpl")
public class GdGoodsPropertyValDaoImpl extends MyBaitsDaoImpl<GdGoodsPropertyVal,java.lang.Long> implements GdGoodsPropertyValDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsPropertyValDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}