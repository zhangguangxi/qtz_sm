package com.qtz.sm.supermarket.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.supermarket.dao.SupermarketBusinessDao;
/**
 * <p>Title:SupermarketBusinessDaoImpl</p>
 * <p>Description:超市运营信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-14
 */
@Repository("supermarketBusinessDaoImpl")
public class SupermarketBusinessDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.supermarket.vo.SupermarketBusiness,java.lang.Long> implements SupermarketBusinessDao {
	/**MYBatis命名空间名*/
	private static String preName = SupermarketBusinessDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}