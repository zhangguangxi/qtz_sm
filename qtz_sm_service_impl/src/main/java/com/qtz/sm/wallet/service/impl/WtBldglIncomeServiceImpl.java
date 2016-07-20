package com.qtz.sm.wallet.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.wallet.dao.WtBldglIncomeDao;
import com.qtz.sm.wallet.service.WtBldglIncomeService;
import com.qtz.sm.wallet.vo.WtBldglIncome;
/**
 * <p>Title:WtBldglIncomeServiceImpl</p>
 * <p>Description:便利店管理公司应收货款流水服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Service("wtBldglIncomeServiceImpl")
public class WtBldglIncomeServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtBldglIncome,java.lang.Long> implements WtBldglIncomeService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtBldglIncomeServiceImpl.class);
	/**注入便利店管理公司应收货款流水DAO接口类*/
	@Resource(name="wtBldglIncomeDaoImpl")
    private WtBldglIncomeDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtBldglIncome,java.lang.Long> getDao() {
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
	public List<WtBldglIncome> queryTotalAmountGroup(WtBldglIncome wtBldglIncome) throws ServiceException {
		try {
			return dao.queryTotalAmountGroup(wtBldglIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStatToSettling(WtBldglIncome wtBldglIncome) throws ServiceException {
		try {
			return dao.modIncomeStatToSettling(wtBldglIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStat(WtBldglIncome wtBldglIncome) throws ServiceException {
		try {
			int result = dao.modIncomeStat(wtBldglIncome);
			return result;
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Long findCount(WtBldglIncome wtBldglIncome) throws ServiceException {
		try {
			return dao.findCount(wtBldglIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 修改结算周期， 对冻结状态的收入，如果结算周期变更则通知修改结算时间
	 * 
	 * @param ownerId	管理公司Id
	 * @param settlementCycle	结算周期，单位：天
	 * @return
	 */
	@Override
	public void modSettlementTime(Long ownerId, int settlementCycle) throws ServiceException {
		try {
			WtBldglIncome wtIncome = new WtBldglIncome();
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