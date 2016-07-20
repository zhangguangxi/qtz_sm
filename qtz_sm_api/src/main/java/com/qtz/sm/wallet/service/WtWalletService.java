package com.qtz.sm.wallet.service;

import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.wallet.model.WtOrder;
import com.qtz.sm.wallet.page.WtWalletPage;
import com.qtz.sm.wallet.vo.WtWallet;

/**
 * <p>
 * Title:WtWalletService
 * </p>
 * <p>
 * Description:钱包信息服务接口类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 * 
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-25
 */
public interface WtWalletService extends BaseService<com.qtz.sm.wallet.vo.WtWallet, java.lang.Long> {

	//添加供应商钱包
	public void addGysWallet(long companyId, String companyName,long payerId) throws ServiceException;

	public void addGylWallet(long companyId, String companyName,long payerId) throws ServiceException;

	public void addYccWallet(long companyId, String companyName,long cspayerId,long bldglpayerId) throws ServiceException;

	public void addCczxWallet(long companyId, String companyName,long payerId) throws ServiceException;

	public void addCsWallet(long companyId, String companyName) throws ServiceException;

	public void addBldglWallet(long companyId, String companyName) throws ServiceException;

	public void addBldWallet(long companyId, String companyName,long payerId) throws ServiceException;

	public Pager<WtWallet,Long> getUppaidList(WtWalletPage wpage) throws ServiceException;
	
//	//分配货款跟利润
//	public void addDistributions(long orderId, Long orderType, Long sellId,List<WtOrderDetail> orderDetails) throws ServiceException;

	/**
	 * 增量操作钱包
	 * 
	 * @param wallet
	 * @return
	 * @throws ServiceException
	 */
	public int modWalletAmountIncr(WtWallet wallet) throws ServiceException;

	/**
	 * 根据 钱包所有者、所有者类型、钱包类型、付款人类型 查询对应钱包
	 * 
	 * 
	 * @param wallet	正常返回一个钱包，若查询出多个或者没查到则返回空
	 * @return
	 * @throws ServiceException
	 */
	public WtWallet getWallet(WtWallet wallet) throws ServiceException;

	/**
	 * /**
	 * 根据 钱包所有者、所有者类型、钱包类型、付款人类型 查询对应钱包
	 * 
	 * 
	 * @param ownerId	钱包所有者
	 * @param ownerType	钱包所有者类型，（供应商、供应链、胖胖超市...）
	 * @param itemId	钱包类型，（ 0:对账中，1：应收，2：已付，3：未付，4：提现）
	 * @param payerType	付款人类型，（供应商、供应链、胖胖超市...）
	 * @return
	 * @throws ServiceException
	 */
	public WtWallet getWallet(Long ownerId, Integer ownerType, Integer itemId, Integer payerType) throws ServiceException;
	
	/**
	 * 分配货款和利润
	 * 
	 * @param order	订单信息跟明细
	 * @throws ServiceException
	 */
	void addDistributions(WtOrder order) throws ServiceException;

	/**
	 * 取消分润
	 * 
	 * @param order
	 * @throws ServiceException
	 */
	void modUnDistributions(WtOrder order) throws ServiceException;
	
	/**
	* 检查对应付款人的已付钱包
	* */
	public WtWallet getPaidByTissue(Long payerId, int ownerType, int payerType,int itemId);
	
	/**
	 * 修改钱包中的名称
	 * @Description:TODO
	 * @param map
	 * @throws ServiceException
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月21日 下午4:22:11
	 */
	public void updateOwnerName(Map<String, Object> map) throws ServiceException;
}