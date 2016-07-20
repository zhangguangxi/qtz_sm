package com.qtz.sm.wallet.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtYccBldglIncome;
/**
 * <p>Title:WtYccBldglIncomeDao</p>
 * <p>Description:云仓储向便利店管理公司应收货款流水DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtYccBldglIncomeDao extends BizDao<com.qtz.sm.wallet.vo.WtYccBldglIncome,java.lang.Long> {

	List<WtYccBldglIncome> queryTotalAmountGroup(WtYccBldglIncome wtYccBldglIncome) throws DaoException;

	int modIncomeStat(WtYccBldglIncome wtYccBldglIncome) throws DaoException;

	int modSettlementTime(WtYccBldglIncome wtYccBldglIncome) throws DaoException;

	int modIncomeStatToSettling(WtYccBldglIncome wtYccBldglIncome) throws DaoException;
}