package com.qtz.sm.wallet.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.wallet.dao.WtYccBldglWithdrawalsDao;
/**
 * <p>Title:WtYccBldglWithdrawalsDaoImpl</p>
 * <p>Description:云仓储向便利店管理公司提现流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtYccBldglWithdrawalsDaoImpl")
public class WtYccBldglWithdrawalsDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtYccBldglWithdrawals,java.lang.Long> implements WtYccBldglWithdrawalsDao {
	/**MYBatis命名空间名*/
	private static String preName = WtYccBldglWithdrawalsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}

}