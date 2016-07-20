package com.qtz.sm.wallet.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.wallet.dao.WtCczxIncomeDao;
import com.qtz.sm.wallet.service.WtCczxIncomeService;
import com.qtz.sm.wallet.vo.WtCczxIncome;
/**
 * <p>Title:WtCczxIncomeServiceImpl</p>
 * <p>Description:仓储中心分润流水服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
@Service("wtCczxIncomeServiceImpl")
public class WtCczxIncomeServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtCczxIncome,java.lang.Long> implements WtCczxIncomeService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtCczxIncomeServiceImpl.class);
	/**注入仓储中心分润流水DAO接口类*/
	@Resource(name="wtCczxIncomeDaoImpl")
    private WtCczxIncomeDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtCczxIncome,java.lang.Long> getDao() {
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
	public List<WtCczxIncome> queryTotalAmountGroup(WtCczxIncome wtCczxIncome) throws ServiceException {
		try {
			return dao.queryTotalAmountGroup(wtCczxIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStatToSettling(WtCczxIncome wtCczxIncome) throws ServiceException {
		try {
			return dao.modIncomeStatToSettling(wtCczxIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public int modIncomeStat(WtCczxIncome wtCczxIncome) throws ServiceException {
		try {
			return dao.modIncomeStat(wtCczxIncome);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 修改结算周期， 对冻结状态的收入，如果结算周期变更则通知修改结算时间
	 * 
	 * @param ownerId	仓储中心ID
	 * @param settlementCycle	结算周期，单位：天
	 * @return
	 */
	@Override
	public void modSettlementTime(Long ownerId, int settlementCycle) throws ServiceException {
		try {
			WtCczxIncome wtIncome = new WtCczxIncome();
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