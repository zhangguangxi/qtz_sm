package com.qtz.sm.shopManage.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.shopManage.dao.ShopMAndSuperMKGoodsDao;
/**
 * <p>Title:ShopManageGoodsDaoImpl</p>
 * <p>Description:商品库DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-12
 */
@Repository("shopMAndSuperMKGoodsDaoImpl")
public class ShopMAndSuperMKGoodsDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shopManage.vo.ShopMAndSuperMKGoods,Long> implements ShopMAndSuperMKGoodsDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopMAndSuperMKGoodsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}