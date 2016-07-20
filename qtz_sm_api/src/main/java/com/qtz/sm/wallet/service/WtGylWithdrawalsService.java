package com.qtz.sm.wallet.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.wallet.page.WtGylWithdrawalsPage;
import com.qtz.sm.wallet.vo.WtGylWithdrawals;
import com.qtz.sm.wallet.vo.WtGysWithdrawals;
/**
 * <p>Title:WtGylWithdrawalsService</p>
 * <p>Description:供应链提现流水服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-11
 */
public interface WtGylWithdrawalsService extends BaseService<com.qtz.sm.wallet.vo.WtGylWithdrawals,java.lang.Long> {
	/**
	 * 申请提现
	 */
	public void saveApplyWithdrawals(WtGylWithdrawals vo) throws ServiceException;
	/**
	 * 同意提现
	 */
	public void saveAccepWithdrawals(WtGylWithdrawals vo) throws ServiceException;
	/**
	 * 驳回提现
	 */
	public void saveRejectWithdrawals(WtGylWithdrawals vo) throws ServiceException;
	/**
	 * 追回提现
	 */
	public void saveRecoverWithdrawals(WtGylWithdrawals vo) throws ServiceException;
	
	/**
	 * 获取提现列表
	 */
	public List<WtGysWithdrawals> getWithdrawalsList(WtGylWithdrawalsPage page) throws ServiceException;
	
}