package com.qtz.sm.wallet.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtBldglIncome;
/**
 * <p>Title:WtBldglIncomeDao</p>
 * <p>Description:便利店管理公司应收货款流水DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtBldglIncomeDao extends BizDao<com.qtz.sm.wallet.vo.WtBldglIncome,java.lang.Long> {

	List<WtBldglIncome> queryTotalAmountGroup(WtBldglIncome bldglIncome) throws DaoException;

	int modIncomeStat(WtBldglIncome bldglIncome) throws DaoException;

	int modSettlementTime(WtBldglIncome wtIncome) throws DaoException;

	int modIncomeStatToSettling(WtBldglIncome bldglIncome) throws DaoException;
}