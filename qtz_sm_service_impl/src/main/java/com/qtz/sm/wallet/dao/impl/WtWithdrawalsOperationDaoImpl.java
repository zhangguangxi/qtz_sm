package com.qtz.sm.wallet.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.wallet.dao.WtWithdrawalsOperationDao;
/**
 * <p>Title:WtWithdrawalsOperationDaoImpl</p>
 * <p>Description:钱包操作记录表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息有限公司</p>
 * @author wangdong - wangdongn@126.com
 * @version v1.0 2016-06-08
 */
@Repository("wtWithdrawalsOperationDaoImpl")
public class WtWithdrawalsOperationDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtWithdrawalsOperation,java.lang.Long> implements WtWithdrawalsOperationDao {
	/**MYBatis命名空间名*/
	private static String preName = WtWithdrawalsOperationDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}