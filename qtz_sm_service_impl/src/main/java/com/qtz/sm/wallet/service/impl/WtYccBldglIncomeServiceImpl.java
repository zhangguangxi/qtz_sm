package com.qtz.sm.wallet.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.wallet.dao.WtYccBldglIncomeDao;
import com.qtz.sm.wallet.service.WtYccBldglIncomeService;
import com.qtz.sm.wallet.vo.WtYccBldglIncome;
/**
 * <p>Title:WtYccBldglIncomeServiceImpl</p>
 * <p>Description:云仓储向便利店管理公司应收货款流水服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Service("wtYccBldglIncomeServiceImpl")
public class WtYccBldglIncomeServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtYccBldglIncome,java.lang.Long> implements WtYccBldglIncomeService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtYccBldglIncomeServiceImpl.class);
	/**注入云仓储向便利店管理公司应收货款流水DAO接口类*/
	@Resource(name="wtYccBldglIncomeDaoImpl")
    private WtYccBldglIncomeDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtYccBldglIncome,java.lang.Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	
	@Override
	public List<WtYccBldglIncome> queryTotalAmountGroup(WtYccBldglIncome wtYccBldglIncome) throws ServiceException {
		try {
			return dao.queryTotalAmountGroup(wtYccBldglIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStatToSettling(WtYccBldglIncome wtYccBldglIncome) throws ServiceException {
		try {
			return dao.modIncomeStatToSettling(wtYccBldglIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStat(WtYccBldglIncome wtYccBldglIncome) throws ServiceException {
		try {
			return dao.modIncomeStat(wtYccBldglIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 修改结算周期， 对冻结状态的收入，如果结算周期变更则通知修改结算时间
	 * 
	 * @param ownerId	云仓储管理公司ID
	 * @param settlementCycle	结算周期，单位：天
	 * @return
	 */
	@Override
	public void modSettlementTime(Long ownerId, int settlementCycle) throws ServiceException {
		try {
			WtYccBldglIncome wtIncome = new WtYccBldglIncome();
			wtIncome.setOwnerId(ownerId);
			wtIncome.setReleaseTime(settlementCycle*86400000L);		//86400000 = 24*3600*1000
			wtIncome.setUpdateTime(System.currentTimeMillis());
			dao.modSettlementTime(wtIncome );
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
		
	}
	
}