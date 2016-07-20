package com.qtz.sm.shop.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.shop.dao.ShopStaffDao;
/**
 * <p>Title:ShopStaffDaoImpl</p>
 * <p>Description:便利店员工DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Repository("shopStaffDaoImpl")
public class ShopStaffDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopStaff,Long> implements ShopStaffDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopStaffDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}