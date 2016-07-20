package com.qtz.sm.wallet.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.wallet.vo.WtBldIncome;
/**
 * <p>Title:WtBldIncomeService</p>
 * <p>Description:便利店分润流水服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtBldIncomeService extends BaseService<com.qtz.sm.wallet.vo.WtBldIncome,java.lang.Long> {

	List<WtBldIncome> queryTotalAmountGroup(WtBldIncome wtBldIncome) throws ServiceException;

	/**
	 * 修改待解冻收入流水状态 为结算进行中
	 * stat:状态:0对账完成,1冻结中,2退货,3结算进行中,4：结算失败...
	 * @param wtBldIncome
	 * @return
	 * @throws ServiceException
	 */
	int modIncomeStatToSettling(WtBldIncome wtBldIncome) throws ServiceException;
	
	int modIncomeStat(WtBldIncome wtBldIncome) throws ServiceException;

	/**
	 * 修改结算周期的通知， 对冻结状态的收入，如果结算周期变更则通知修改结算时间
	 * 
	 * @param ownerId	便利店Id
	 * @param settlementCycle	结算周期，单位：天
	 * @return
	 */
	void modSettlementTime(Long ownerId, int settlementCycle) throws ServiceException;
	
}