package com.qtz.sm.wallet.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.wallet.vo.WtBldIncomeSettlement;
/**
 * <p>Title:WtBldIncomeSettlementService</p>
 * <p>Description:超市收入对账结算记录服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 肖来龙 - lois siau
 * @version v1.0 2016-05-25
 */
public interface WtBldIncomeSettlementService extends BaseService<com.qtz.sm.wallet.vo.WtBldIncomeSettlement,java.lang.Long> {

	boolean settlement(WtBldIncomeSettlement incomeSm) throws ServiceException;
	
}