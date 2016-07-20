package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.goods.dao.GdGoodsPictureDao;
import com.qtz.sm.goods.vo.GdGoodsPicture;
/**
 * <p>Title:GdGoodsPictureDaoImpl</p>
 * <p>Description:商品图片DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsPictureDaoImpl")
public class GdGoodsPictureDaoImpl extends MyBaitsDaoImpl<GdGoodsPicture,java.lang.Long> implements GdGoodsPictureDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsPictureDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}