package com.qtz.sm.wallet.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.wallet.vo.WtYccCsIncome;
/**
 * <p>Title:WtYccCsIncomeService</p>
 * <p>Description:云仓储向超市应收货款流水服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtYccCsIncomeService extends BaseService<com.qtz.sm.wallet.vo.WtYccCsIncome,java.lang.Long> {

	List<WtYccCsIncome> queryTotalAmountGroup(WtYccCsIncome wtYccCsIncome) throws ServiceException;

	/**
	 * 修改待解冻收入流水状态 为结算进行中
	 * stat:状态:0对账完成,1冻结中,2退货,3结算进行中,4：结算失败...
	 *  
	 * @param wtYccCsIncome
	 * @return
	 * @throws ServiceException
	 */
	int modIncomeStatToSettling(WtYccCsIncome wtYccCsIncome) throws ServiceException;
	
	/**
	 * 修改收入流水状态
	 * @param wtYccCsIncome
	 * @return
	 * @throws ServiceException
	 */
	int modIncomeStat(WtYccCsIncome wtYccCsIncome) throws ServiceException;

	/**
	 * 修改结算周期的通知， 对冻结状态的收入，如果结算周期变更则通知修改结算时间
	 * 
	 * @param ownerId	云仓储管理中心Id
	 * @param settlementCycle	结算周期，单位：天
	 * @return
	 */
	void modSettlementTime(Long ownerId, int settlementCycle) throws ServiceException;
}