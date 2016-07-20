package com.qtz.sm.wallet.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtCsIncome;
/**
 * <p>Title:WtCsIncomeDao</p>
 * <p>Description:超市应收货款流水DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtCsIncomeDao extends BizDao<com.qtz.sm.wallet.vo.WtCsIncome,java.lang.Long> {

	List<WtCsIncome> queryTotalAmountGroup(WtCsIncome csIncome) throws DaoException;

	int modIncomeStat(WtCsIncome csIncomeQuery) throws DaoException;

	int modSettlementTime(WtCsIncome csIncome) throws DaoException;

	int modIncomeStatToSettling(WtCsIncome csIncome) throws DaoException;
}