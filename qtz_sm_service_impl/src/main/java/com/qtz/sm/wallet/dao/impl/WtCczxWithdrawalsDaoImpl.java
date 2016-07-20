package com.qtz.sm.wallet.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.wallet.dao.WtCczxWithdrawalsDao;
/**
 * <p>Title:WtCczxWithdrawalsDaoImpl</p>
 * <p>Description:仓储中心提现流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtCczxWithdrawalsDaoImpl")
public class WtCczxWithdrawalsDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtCczxWithdrawals,java.lang.Long> implements WtCczxWithdrawalsDao {
	/**MYBatis命名空间名*/
	private static String preName = WtCczxWithdrawalsDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}