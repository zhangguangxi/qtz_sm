package com.qtz.sm.wallet.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtYccCsIncome;
/**
 * <p>Title:WtYccCsIncomeDao</p>
 * <p>Description:云仓储向超市应收货款流水DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtYccCsIncomeDao extends BizDao<com.qtz.sm.wallet.vo.WtYccCsIncome,java.lang.Long> {

	List<WtYccCsIncome> queryTotalAmountGroup(WtYccCsIncome yccCsIncome) throws DaoException;

	int modIncomeStat(WtYccCsIncome yccCsIncome) throws DaoException;

	int modSettlementTime(WtYccCsIncome wtYccCsIncome) throws DaoException;

	int modIncomeStatToSettling(WtYccCsIncome wtYccCsIncome) throws DaoException;
}