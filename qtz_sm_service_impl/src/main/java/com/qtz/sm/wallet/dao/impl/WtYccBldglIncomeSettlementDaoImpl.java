package com.qtz.sm.wallet.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.wallet.dao.WtYccBldglIncomeSettlementDao;
/**
 * <p>Title:WtYccBldglIncomeSettlementDaoImpl</p>
 * <p>Description:超市收入对账结算记录DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 肖来龙 - lois siau
 * @version v1.0 2016-05-25
 */
@Repository("wtYccBldglIncomeSettlementDaoImpl")
public class WtYccBldglIncomeSettlementDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtYccBldglIncomeSettlement,java.lang.Long> implements WtYccBldglIncomeSettlementDao {
	/**MYBatis命名空间名*/
	private static String preName = WtYccBldglIncomeSettlementDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}