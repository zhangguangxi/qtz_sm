package com.qtz.sm.supermarket.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.supermarket.dao.SupermarketInfoDao;
/**
 * <p>Title:SupermarketInfoDaoImpl</p>
 * <p>Description:超市基本信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Repository("supermarketInfoDaoImpl")
public class SupermarketInfoDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.supermarket.vo.SupermarketInfo,Long> implements SupermarketInfoDao {
	/**MYBatis命名空间名*/
	private static String preName = SupermarketInfoDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}