package com.qtz.sm.shop.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.shop.dao.ShopCommentDao;
/**
 * <p>Title:ShopCommentDaoImpl</p>
 * <p>Description:店铺评论信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
@Repository("shopCommentDaoImpl")
public class ShopCommentDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopComment,java.lang.Long> implements ShopCommentDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopCommentDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}