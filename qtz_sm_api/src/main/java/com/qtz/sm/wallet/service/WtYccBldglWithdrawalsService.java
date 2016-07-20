package com.qtz.sm.wallet.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.wallet.page.WtYccBldglWithdrawalsPage;
import com.qtz.sm.wallet.vo.WtYccBldglWithdrawals;
/**
 * <p>Title:WtYccBldglWithdrawalsService</p>
 * <p>Description:云仓储向便利店管理公司提现流水服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-11
 */
public interface WtYccBldglWithdrawalsService extends BaseService<com.qtz.sm.wallet.vo.WtYccBldglWithdrawals,java.lang.Long> {
	/**
	 * 申请提现
	 */
	public void saveApplyWithdrawals(WtYccBldglWithdrawals vo) throws ServiceException;
	/**
	 * 同意提现
	 */
	public void saveAccepWithdrawals(WtYccBldglWithdrawals vo) throws ServiceException;
	/**
	 * 驳回提现
	 */
	public void saveRejectWithdrawals(WtYccBldglWithdrawals vo) throws ServiceException;
	/**
	 * 追回提现
	 */
	public void saveRecoverWithdrawals(WtYccBldglWithdrawals vo) throws ServiceException;
	
	/**
	 * 获取提现列表
	 */
	public List<WtYccBldglWithdrawals> getWithdrawalsList(WtYccBldglWithdrawalsPage page) throws ServiceException;
}