package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.goods.dao.GdGoodsDescPictureDao;
/**
 * <p>Title:GdGoodsDescPictureDaoImpl</p>
 * <p>Description:商品描述图片DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-16
 */
@Repository("gdGoodsDescPictureDaoImpl")
public class GdGoodsDescPictureDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.goods.vo.GdGoodsDescPicture,java.lang.Long> implements GdGoodsDescPictureDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsDescPictureDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}