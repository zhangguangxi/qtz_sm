package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtCczxIncomeDao;
import com.qtz.sm.wallet.vo.WtCczxIncome;
/**
 * <p>Title:WtCczxIncomeDaoImpl</p>
 * <p>Description:仓储中心分润流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtCczxIncomeDaoImpl")
public class WtCczxIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtCczxIncome,java.lang.Long> implements WtCczxIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtCczxIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtCczxIncome> queryTotalAmountGroup(WtCczxIncome cczxIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", cczxIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + cczxIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtCczxIncome cczxIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", cczxIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + cczxIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modIncomeStat(WtCczxIncome cczxIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", cczxIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + cczxIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modSettlementTime(WtCczxIncome cczxIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", cczxIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + cczxIncome + ")调用【报错】了！", e);
		}
	}
}