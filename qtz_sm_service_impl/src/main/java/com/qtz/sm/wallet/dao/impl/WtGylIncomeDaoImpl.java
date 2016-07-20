package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtGylIncomeDao;
import com.qtz.sm.wallet.vo.WtGylIncome;
/**
 * <p>Title:WtGylIncomeDaoImpl</p>
 * <p>Description:供应链应收货款流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtGylIncomeDaoImpl")
public class WtGylIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtGylIncome,java.lang.Long> implements WtGylIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtGylIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtGylIncome> queryTotalAmountGroup(WtGylIncome gylIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", gylIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + gylIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtGylIncome gylIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", gylIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + gylIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modIncomeStat(WtGylIncome gylIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", gylIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + gylIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modSettlementTime(WtGylIncome gylIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", gylIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + gylIncome + ")调用【报错】了！", e);
		}
	}
	
}