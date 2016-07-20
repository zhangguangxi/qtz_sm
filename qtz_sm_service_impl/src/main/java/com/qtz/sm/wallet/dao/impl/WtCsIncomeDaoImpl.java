package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtCsIncomeDao;
import com.qtz.sm.wallet.vo.WtCsIncome;
/**
 * <p>Title:WtCsIncomeDaoImpl</p>
 * <p>Description:超市应收货款流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtCsIncomeDaoImpl")
public class WtCsIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtCsIncome,java.lang.Long> implements WtCsIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtCsIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtCsIncome> queryTotalAmountGroup(WtCsIncome csIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", csIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + csIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtCsIncome csIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", csIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + csIncome + ")调用【报错】了！", e);
		}
	}
	
	
	@Override
	public int modIncomeStat(WtCsIncome csIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", csIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + csIncome + ")调用【报错】了！", e);
		}
	}
	
	
	@Override
	public int modSettlementTime(WtCsIncome csIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", csIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + csIncome + ")调用【报错】了！", e);
		}
	}
	
}