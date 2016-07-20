package com.qtz.sm.wallet.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtBldIncome;
/**
 * <p>Title:WtBldIncomeDao</p>
 * <p>Description:便利店分润流水DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtBldIncomeDao extends BizDao<com.qtz.sm.wallet.vo.WtBldIncome,java.lang.Long> {

	List<WtBldIncome> queryTotalAmountGroup(WtBldIncome bldIncome) throws DaoException;

	int modIncomeStat(WtBldIncome bldIncome) throws DaoException;

	int modSettlementTime(WtBldIncome bldIncome) throws DaoException;

	int modIncomeStatToSettling(WtBldIncome bldIncome) throws DaoException;
}