package com.qtz.sm.wallet.dao.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtBldIncomeDao;
import com.qtz.sm.wallet.vo.WtBldIncome;
/**
 * <p>Title:WtBldIncomeDaoImpl</p>
 * <p>Description:便利店分润流水DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Repository("wtBldIncomeDaoImpl")
public class WtBldIncomeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtBldIncome,java.lang.Long> implements WtBldIncomeDao {
	/**MYBatis命名空间名*/
	private static String preName = WtBldIncomeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public List<WtBldIncome> queryTotalAmountGroup(WtBldIncome bldIncome) throws DaoException{
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryTotalAmountGroup", bldIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryTotalAmountGroup(" + bldIncome + ")调用【报错】了！", e);
		}
	}

	@Override
	public int modIncomeStatToSettling(WtBldIncome bldIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStatToSettling", bldIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStatToSettling(" + bldIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modIncomeStat(WtBldIncome bldIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modIncomeStat", bldIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modIncomeStat(" + bldIncome + ")调用【报错】了！", e);
		}
	}
	
	@Override
	public int modSettlementTime(WtBldIncome bldIncome) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modSettlementTime", bldIncome);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".modSettlementTime(" + bldIncome + ")调用【报错】了！", e);
		}
	}
}