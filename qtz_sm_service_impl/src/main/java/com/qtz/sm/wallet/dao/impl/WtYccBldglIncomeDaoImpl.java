package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtYccBldglIncomeDao;
import com.qtz.sm.wallet.vo.WtYccBldglIncome;
/**
 * <p>Title:WtYccBldglIncomeDaoImpl</p>
 * <p>Description:云仓储向便利店管理公司应收货款流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtYccBldglIncomeDaoImpl")
public class WtYccBldglIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtYccBldglIncome,java.lang.Long> implements WtYccBldglIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtYccBldglIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtYccBldglIncome> queryTotalAmountGroup(WtYccBldglIncome wtYccBldglIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", wtYccBldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + wtYccBldglIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtYccBldglIncome wtYccBldglIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", wtYccBldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + wtYccBldglIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modIncomeStat(WtYccBldglIncome wtYccBldglIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", wtYccBldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + wtYccBldglIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modSettlementTime(WtYccBldglIncome wtYccBldglIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", wtYccBldglIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + wtYccBldglIncome + ")调用【报错】了！", e);
		}
	}
}