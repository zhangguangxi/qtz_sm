package com.qtz.sm.wallet.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.wallet.dao.WtCsIncomeDao;
import com.qtz.sm.wallet.service.WtCsIncomeService;
import com.qtz.sm.wallet.vo.WtCsIncome;
/**
 * <p>Title:WtCsIncomeServiceImpl</p>
 * <p>Description:超市应收货款流水服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Service("wtCsIncomeServiceImpl")
public class WtCsIncomeServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtCsIncome,java.lang.Long> implements WtCsIncomeService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtCsIncomeServiceImpl.class);
	/**注入超市应收货款流水DAO接口类*/
	@Resource(name="wtCsIncomeDaoImpl")
    private WtCsIncomeDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtCsIncome,java.lang.Long> getDao() {
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
	public List<WtCsIncome> queryTotalAmountGroup(WtCsIncome wtCsIncome) throws ServiceException {
		try {
			return dao.queryTotalAmountGroup(wtCsIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStatToSettling(WtCsIncome csIncomeQuery) throws ServiceException {
		try {
			return dao.modIncomeStatToSettling(csIncomeQuery);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStat(WtCsIncome csIncomeQuery) throws ServiceException {
		try {
			return dao.modIncomeStat(csIncomeQuery);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public Long findCount(WtCsIncome csIncome) throws ServiceException {
		try {
			return dao.findCount(csIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 修改结算周期， 对冻结状态的收入，如果结算周期变更则通知修改结算时间
	 * 
	 * @param ownerId	超市ID
	 * @param settlementCycle	结算周期，单位：天
	 * @return
	 */
	@Override
	public void modSettlementTime(Long ownerId, int settlementCycle) throws ServiceException {
		try {
			WtCsIncome wtIncome = new WtCsIncome();
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