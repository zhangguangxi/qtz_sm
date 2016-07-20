package com.qtz.sm.shopManage.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.shopManage.dao.ShopManageSplitPointDao;
/**
 * <p>Title:ShopManageSplitPointDaoImpl</p>
 * <p>Description:便利店公司分成点DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-01
 */
@Repository("shopManageSplitPointDaoImpl")
public class ShopManageSplitPointDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shopManage.vo.ShopManageSplitPoint,java.lang.Long> implements ShopManageSplitPointDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopManageSplitPointDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}