package com.qtz.sm.shop.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.shop.dao.ShopInfoOperationHistoryDao;
/**
 * <p>Title:ShopInfoOperationHistoryDaoImpl</p>
 * <p>Description:便利店操作记录DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-17
 */
@Repository("shopInfoOperationHistoryDaoImpl")
public class ShopInfoOperationHistoryDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.shop.vo.ShopInfoOperationHistory,java.lang.Long> implements ShopInfoOperationHistoryDao {
	/**MYBatis命名空间名*/
	private static String preName = ShopInfoOperationHistoryDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}