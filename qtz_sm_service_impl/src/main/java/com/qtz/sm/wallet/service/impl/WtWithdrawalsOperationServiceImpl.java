package com.qtz.sm.wallet.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.wallet.dao.WtWithdrawalsOperationDao;
import com.qtz.sm.wallet.service.WtWithdrawalsOperationService;
/**
 * <p>Title:WtWithdrawalsOperationServiceImpl</p>
 * <p>Description:钱包操作记录表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息有限公司</p>
 * @author wangdong - wangdongn@126.com
 * @version v1.0 2016-06-08
 */
@Service("wtWithdrawalsOperationServiceImpl")
public class WtWithdrawalsOperationServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtWithdrawalsOperation,java.lang.Long> implements WtWithdrawalsOperationService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtWithdrawalsOperationServiceImpl.class);
	/**注入钱包操作记录表DAO接口类*/
	@Resource(name="wtWithdrawalsOperationDaoImpl")
    private WtWithdrawalsOperationDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtWithdrawalsOperation,java.lang.Long> getDao() {
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
}