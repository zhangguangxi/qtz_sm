package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtBldglIncomeDao;
import com.qtz.sm.wallet.vo.WtBldglIncome;
/**
 * <p>Title:WtBldglIncomeDaoImpl</p>
 * <p>Description:便利店管理公司应收货款流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtBldglIncomeDaoImpl")
public class WtBldglIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtBldglIncome,java.lang.Long> implements WtBldglIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtBldglIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtBldglIncome> queryTotalAmountGroup(WtBldglIncome bldglIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", bldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + bldglIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtBldglIncome bldglIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", bldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + bldglIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modIncomeStat(WtBldglIncome bldglIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", bldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + bldglIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modSettlementTime(WtBldglIncome bldglIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", bldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + bldglIncome + ")调用【报错】了！", e);
		}
	}
}