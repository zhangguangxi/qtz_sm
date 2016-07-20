package com.qtz.sm.wallet.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.wallet.vo.WtBldglIncome;
/**
 * <p>Title:WtBldglIncomeService</p>
 * <p>Description:便利店管理公司应收货款流水服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public interface WtBldglIncomeService extends BaseService<com.qtz.sm.wallet.vo.WtBldglIncome,java.lang.Long> {

	/**
	 * 	分组查询每个收款人该解冻的收入货款总额
	 * 	stat:状态:0对账完成,1冻结中,2退货,3结算进行中,4：结算失败...
	 * 
	 * @param wtBldglIncome
	 * @return
	 * @throws ServiceException
	 */
	List<WtBldglIncome> queryTotalAmountGroup(WtBldglIncome wtBldglIncome) throws ServiceException;

	/**
	 * 修改待解冻收入流水状态 为结算进行中
	 * stat:状态:0对账完成,1冻结中,2退货,3结算进行中,4：结算失败...
	 * 
	 * @param wtBldglIncome
	 * @return
	 * @throws ServiceException
	 */
	int modIncomeStatToSettling(WtBldglIncome wtBldglIncome) throws ServiceException;
	
	/**
	 * 修改收入流水状态
	 * stat:状态:0对账完成,1冻结中,2退货,4：结算失败...
	 * 
	 * @param wtBldglIncome
	 * @return
	 * @throws ServiceException
	 */
	int modIncomeStat(WtBldglIncome wtBldglIncome) throws ServiceException;

	/**
	 * 查询结果条数
	 * 
	 * @param wtBldglIncome
	 * @return
	 * @throws ServiceException
	 */
	Long findCount(WtBldglIncome wtBldglIncome)  throws ServiceException;

	/**
	 * 修改结算周期的通知， 对冻结状态的收入，如果结算周期变更则通知修改结算时间
	 * 
	 * @param ownerId	便利店管理公司Id
	 * @param settlementCycle	结算周期，单位：天
	 * @return
	 */
	void modSettlementTime(Long ownerId, int settlementCycle) throws ServiceException;
	
}