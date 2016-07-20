package com.qtz.sm.supermarket.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.supermarket.dao.SupermarketAdvertisementDao;
/**
 * <p>Title:AdvertisementDaoImpl</p>
 * <p>Description:广告位DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱有限公司</p>
 * @author 张光喜 - zhangguangxito@sina.cn 
 * @version v1.0 2016-05-31
 */
@Repository("supermarketAdvertisementDaoImpl")
public class SupermarketAdvertisementDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.supermarket.vo.SupermarketAdvertisement,java.lang.Long> implements SupermarketAdvertisementDao {
	/**MYBatis命名空间名*/
	private static String preName = SupermarketAdvertisementDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}