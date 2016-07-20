package com.qtz.sm.wallet.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.wallet.dao.WtWithdrawalsDao;
/**
 * <p>Title:WtWithdrawalsDaoImpl</p>
 * <p>Description:银行卡信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-29
 */
@Repository("wtWithdrawalsDaoImpl")
public class WtWithdrawalsDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtWithdrawals,java.lang.Long> implements WtWithdrawalsDao {
	/**MYBatis命名空间名*/
	private static String preName = WtWithdrawalsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}