package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtYccCsIncomeDao;
import com.qtz.sm.wallet.vo.WtYccCsIncome;
/**
 * <p>Title:WtYccCsIncomeDaoImpl</p>
 * <p>Description:云仓储向超市应收货款流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtYccCsIncomeDaoImpl")
public class WtYccCsIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtYccCsIncome,java.lang.Long> implements WtYccCsIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtYccCsIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtYccCsIncome> queryTotalAmountGroup(WtYccCsIncome wtYccCsIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", wtYccCsIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + wtYccCsIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtYccCsIncome wtYccCsIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", wtYccCsIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + wtYccCsIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modIncomeStat(WtYccCsIncome wtYccCsIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", wtYccCsIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + wtYccCsIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modSettlementTime(WtYccCsIncome wtYccCsIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", wtYccCsIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + wtYccCsIncome + ")调用【报错】了！", e);
		}
	}
	
}