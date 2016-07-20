package com.qtz.sm.wallet.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.wallet.service.WtWithdrawalsService;
import com.qtz.sm.wallet.dao.WtWithdrawalsDao;
/**
 * <p>Title:WtWithdrawalsServiceImpl</p>
 * <p>Description:银行卡信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-29
 */
@Service("wtWithdrawalsServiceImpl")
public class WtWithdrawalsServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtWithdrawals,java.lang.Long> implements WtWithdrawalsService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtWithdrawalsServiceImpl.class);
	/**注入银行卡信息DAO接口类*/
	@Resource(name="wtWithdrawalsDaoImpl")
    private WtWithdrawalsDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtWithdrawals,java.lang.Long> getDao() {
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