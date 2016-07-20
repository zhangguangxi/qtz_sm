package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtGysIncomeDao;
import com.qtz.sm.wallet.vo.WtGysIncome;
/**
 * <p>Title:WtGysIncomeDaoImpl</p>
 * <p>Description:供应商应收货款流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtGysIncomeDaoImpl")
public class WtGysIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtGysIncome,java.lang.Long> implements WtGysIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtGysIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtGysIncome> queryTotalAmountGroup(WtGysIncome gysIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", gysIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + gysIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtGysIncome gysIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", gysIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + gysIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modIncomeStat(WtGysIncome gysIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", gysIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + gysIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modSettlementTime(WtGysIncome gysIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", gysIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + gysIncome + ")调用【报错】了！", e);
		}
	}
	
}