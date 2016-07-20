package com.qtz.sm.wallet.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtCczxIncome;
/**
 * <p>Title:WtCczxIncomeDao</p>
 * <p>Description:仓储中心分润流水DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtCczxIncomeDao extends BizDao<com.qtz.sm.wallet.vo.WtCczxIncome,java.lang.Long> {

	List<WtCczxIncome> queryTotalAmountGroup(WtCczxIncome cczxIncome) throws DaoException;

	int modIncomeStat(WtCczxIncome cczxIncome) throws DaoException;

	int modSettlementTime(WtCczxIncome cczxIncome) throws DaoException;

	int modIncomeStatToSettling(WtCczxIncome cczxIncome) throws DaoException;
}