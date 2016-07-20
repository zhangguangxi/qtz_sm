package com.qtz.sm.wallet.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtGylIncome;
/**
 * <p>Title:WtGylIncomeDao</p>
 * <p>Description:供应链应收货款流水DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtGylIncomeDao extends BizDao<com.qtz.sm.wallet.vo.WtGylIncome,java.lang.Long> {

	List<WtGylIncome> queryTotalAmountGroup(WtGylIncome gylIncome) throws DaoException;

	int modIncomeStat(WtGylIncome gylIncome) throws DaoException;

	int modSettlementTime(WtGylIncome gylIncome) throws DaoException;

	int modIncomeStatToSettling(WtGylIncome gylIncome) throws DaoException;
}