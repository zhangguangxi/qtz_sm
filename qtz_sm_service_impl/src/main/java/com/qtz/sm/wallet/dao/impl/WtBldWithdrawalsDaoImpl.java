package com.qtz.sm.wallet.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.wallet.dao.WtBldWithdrawalsDao;
/**
 * <p>Title:WtBldWithdrawalsDaoImpl</p>
 * <p>Description:便利店提现流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtBldWithdrawalsDaoImpl")
public class WtBldWithdrawalsDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtBldWithdrawals,java.lang.Long> implements WtBldWithdrawalsDao {
	/**MYBatis命名空间名*/
	private static String preName = WtBldWithdrawalsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}