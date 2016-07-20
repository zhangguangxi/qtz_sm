package com.qtz.sm.wallet.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.FifteenLongId;
import com.mall.core.common.response.RespCode;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.wallet.dao.WtBankCardDao;
import com.qtz.sm.wallet.dao.WtGysWithdrawalsDao;
import com.qtz.sm.wallet.dao.WtWithdrawalsOperationDao;
import com.qtz.sm.wallet.enums.WalletItemEnum;
import com.qtz.sm.wallet.enums.WithdrawalsStatusEnum;
import com.qtz.sm.wallet.page.WtGysWithdrawalsPage;
import com.qtz.sm.wallet.service.WtGysWithdrawalsService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.vo.WtBankCard;
import com.qtz.sm.wallet.vo.WtGysWithdrawals;
import com.qtz.sm.wallet.vo.WtWallet;
import com.qtz.sm.wallet.vo.WtWithdrawalsOperation;

import cache.BaseProperties;
/**
 * <p>Title:WtGysWithdrawalsServiceImpl</p>
 * <p>Description:供应商提现流水服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-11
 */
@Service("wtGysWithdrawalsServiceImpl")
public class WtGysWithdrawalsServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtGysWithdrawals,java.lang.Long> implements WtGysWithdrawalsService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtGysWithdrawalsServiceImpl.class);
	
	/**注入供应商提现流水DAO接口类*/
	@Resource(name="wtGysWithdrawalsDaoImpl")
    private WtGysWithdrawalsDao dao;
    
	@Resource(name="wtBankCardDaoImpl")
	private WtBankCardDao wtBankCardDao; 
	
	@Autowired
	private FifteenLongId fifteenLongId;
	

	@Resource(name = "wtWalletServiceImpl")
	private WtWalletService wtWalletService;
		
	@Resource(name = "wtWithdrawalsOperationDaoImpl")
	private WtWithdrawalsOperationDao wtWithdrawalsOperationDao;
	
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtGysWithdrawals,java.lang.Long> getDao() {
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
	public void saveApplyWithdrawals(WtGysWithdrawals vo) throws ServiceException {
		try {
			
			WtBankCard bankCard=wtBankCardDao.findVo(vo.getBankcardId(),null);
			if(null==bankCard){
				throw new ServiceException(RespCode.exist_bing_bankcard," 没有找到银行卡");
			}if(!bankCard.getCompanyId().equals(vo.getApplyId())){								
				throw new ServiceException(RespCode.not_belong_bing_bankcard," 该银行卡不属于该组织请重新绑定银行卡");
 			}
			
			vo.setDmId(fifteenLongId.nextId());
			
			vo.setAccountType(bankCard.getAccountType());
			vo.setBankcardId(bankCard.getDmId());
			vo.setBankName(bankCard.getBankName());
			vo.setBankNo(bankCard.getCardNum());
			vo.setCardholderName(bankCard.getCardholder());
			
			vo.setCreateOn(System.currentTimeMillis());
			vo.setAstatus(WithdrawalsStatusEnum.APPLY.getValue());
			
			vo.setApplyTime(System.currentTimeMillis());
			vo.setStatisticsDate(System.currentTimeMillis());
			
			//从配置文件中读取供应商结算周期的设置参数
			String period=BaseProperties.getBaseProperties("gys.withdrawals.period");
			vo.setReleaseDate(period==null?System.currentTimeMillis():System.currentTimeMillis() + Integer.valueOf(period)* 3600 * 24 * 1000);			
			vo.setSourceId(vo.getDmId());
			vo.setSourceType(WithdrawalsStatusEnum.APPLY.getValue());
			//添加到提现流水表
			dao.addVo(vo);
			//添加操作记录表
			addWtWithdrawalsOperation(vo, "申请提现");
			
			//申请提现对应应收钱包减少
			//获取自己公司的钱包
			WtWallet wVo = new WtWallet();
			wVo.setOwnerId(vo.getApplyId());
			wVo.setOwnerType(CompanyType.Supplier.value());
			List<WtWallet> walletList = wtWalletService.findList(wVo);

			if (null == walletList || walletList.size() == 0) {
				throw new ServiceException(RespCode.wall_not_set," 钱包没设定");
			}
			WtWallet wIncomed = null;			
			for (WtWallet w : walletList) {			
				if (w.getItemId() == WalletItemEnum.INCOMED.getValue()) {
					wIncomed = w;
					break;
				}
			}
						
			if (null == wIncomed) {
				throw new ServiceException(RespCode.wall_not_initialized," 钱包没初始化");
			}

			// 钱包 应收货款减少
			wIncomed.setItemAmount(wIncomed.getItemAmount() - vo.getAmount());
			wIncomed.setUpdateTime(System.currentTimeMillis());
			wtWalletService.modVoNotNull(wIncomed);				
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void saveAccepWithdrawals(WtGysWithdrawals vo) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			WtGysWithdrawals withdrawals = dao.findVo(vo.getDmId(), null);
			if (null == withdrawals) {
				throw new ServiceException(RespCode.not_present_application," 没有发现该提现！");
			}
			if (withdrawals.getAstatus() != WithdrawalsStatusEnum.APPLY.getValue()) {
				throw new ServiceException(RespCode.not_to_mention_present," 只能对没提现的申请操作");
			}
			
			if (vo.getAstatus() != WithdrawalsStatusEnum.APPLIED.getValue()) {
				throw new ServiceException(RespCode.consent_to_withdrawal," 只能执行同意提现操作!");
			}
			
			//同意提现对应提现金额增加，已付金额增加			
			//获取自己公司的钱包
			WtWallet wVo = new WtWallet();
			wVo.setOwnerId(withdrawals.getApplyId());
			wVo.setOwnerType(CompanyType.Supplier.value());
			List<WtWallet> walletList = wtWalletService.findList(wVo);

			if (null == walletList || walletList.size() == 0) {
				throw new ServiceException(RespCode.wall_not_set," 钱包没设定");
			}

			WtWallet wWithdrawals = null;		
			
			for (WtWallet w : walletList) {
				if (w.getItemId() == WalletItemEnum.WITHDRAWALS.getValue()) {
					wWithdrawals = w;
					break;
				}
			}
			
			//获取收款人已付钱包
			WtWallet wPaid=wtWalletService.getPaidByTissue(wWithdrawals.getPayerId(), CompanyType.SupplyChain.value(), CompanyType.Supplier.value(),WalletItemEnum.PAID.getValue());
			//获取收款人未付钱包
			WtWallet uwPaid=wtWalletService.getPaidByTissue(wWithdrawals.getPayerId(), CompanyType.SupplyChain.value(), CompanyType.Supplier.value(),WalletItemEnum.UPPAID.getValue());
			
			if (null == wWithdrawals ||null == wPaid || null == uwPaid ) {
				throw new ServiceException(RespCode.wall_not_initialized," 钱包没初始化");
			}

			// 标记为已经提现
			withdrawals.setDealRemark(vo.getDealRemark());
			withdrawals.setUpdateBy(withdrawals.getDealBy());
			withdrawals.setUpdateOn(System.currentTimeMillis());
			withdrawals.setDealBy(withdrawals.getDealBy());
			withdrawals.setDealOn(System.currentTimeMillis());
			withdrawals.setAstatus(vo.getAstatus());

			this.modVoNotNull(withdrawals);			
			//添加操作记录表
			addWtWithdrawalsOperation(withdrawals, "同意提现");
			if (vo.getAstatus() == WithdrawalsStatusEnum.APPLIED.getValue()) {
				// 钱包提现金额增加
				wWithdrawals.setItemAmount(wWithdrawals.getItemAmount() + withdrawals.getAmount());
				wWithdrawals.setUpdateTime(System.currentTimeMillis());
				wtWalletService.modVoNotNull(wWithdrawals);

				//收款人的未付钱包减少
				uwPaid.setItemAmount(uwPaid.getItemAmount() - withdrawals.getAmount());
				uwPaid.setUpdateTime(System.currentTimeMillis());
				wtWalletService.modVoNotNull(uwPaid);			
				
				//收款人的已付钱包增加
				wPaid.setItemAmount(wPaid.getItemAmount() + withdrawals.getAmount());	//增加
				wPaid.setUpdateTime(System.currentTimeMillis());
				wtWalletService.modVoNotNull(wPaid);
			}

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void saveRejectWithdrawals(WtGysWithdrawals vo) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			WtGysWithdrawals withdrawals = dao.findVo(vo.getDmId(), null);
			if (null == withdrawals) {
				throw new ServiceException(RespCode.not_present_application," 没有发现该提现！");
			}
			if (withdrawals.getAstatus() != WithdrawalsStatusEnum.APPLY.getValue()) {
				throw new ServiceException(RespCode.not_to_mention_present," 只能对没提现的申请操作");
			}
			
			if (vo.getAstatus() != WithdrawalsStatusEnum.APPLYBACK.getValue()
					&& vo.getAstatus() != WithdrawalsStatusEnum.APPLYCANCEL.getValue()) {
				throw new ServiceException(RespCode.to_mention_bh_present," 只能执行驳回操作！");
			}
			
			//驳回提现对应应收钱包回滚初始化	
			//获取自己公司的钱包
			WtWallet wVo = new WtWallet();
			wVo.setOwnerId(withdrawals.getApplyId());
			wVo.setOwnerType(CompanyType.Supplier.value());
			List<WtWallet> walletList = wtWalletService.findList(wVo);

			if (null == walletList || walletList.size() == 0) {
				throw new ServiceException(RespCode.wall_not_set," 钱包没设定");
			}

			WtWallet wIncomed = null;
			for (WtWallet w : walletList) {
				if (w.getItemId() == WalletItemEnum.INCOMED.getValue()) {
					wIncomed = w;
					break;
				}
			}

			if (null == wIncomed) {
				throw new ServiceException(RespCode.wall_not_initialized," 钱包没初始化");
			}
			

			withdrawals.setSourceType(WithdrawalsStatusEnum.APPLYBACK.getValue());		
			withdrawals.setDealRemark(vo.getDealRemark());
			withdrawals.setDealBy(withdrawals.getDealBy());
			withdrawals.setDealOn(System.currentTimeMillis());
			
			withdrawals.setUpdateBy(withdrawals.getDealBy());
			withdrawals.setUpdateOn(System.currentTimeMillis());
			withdrawals.setStatisticsDate(System.currentTimeMillis());			
			withdrawals.setAstatus(WithdrawalsStatusEnum.APPLYBACK.getValue());
			

			this.modVoNotNull(withdrawals);	
			//添加操作记录表
			addWtWithdrawalsOperation(withdrawals, "驳回提现");

			double amount=withdrawals.getAmount();
			// 应收货款增加
			wIncomed.setItemAmount(wIncomed.getItemAmount() + amount);
			wIncomed.setUpdateTime(System.currentTimeMillis());
			wtWalletService.modVoNotNull(wIncomed);

			

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public void saveRecoverWithdrawals(WtGysWithdrawals vo) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			WtGysWithdrawals withdrawals = dao.findVo(vo.getDmId(), null);
			if (null == withdrawals) {
				throw new ServiceException(RespCode.not_present_application," 没有发现该申请！");
			}
			if (withdrawals.getAstatus() != WithdrawalsStatusEnum.APPLYBACK.getValue()) {
				throw new ServiceException(RespCode.already_to_bh_present," 只能对已驳回的申请操作！");
			}
			
			if (vo.getAstatus() != WithdrawalsStatusEnum.RECOVER.getValue()) {
				throw new ServiceException(RespCode.to_mention_zh_present," 只能执行追回操作！");
			}
			
			//追回提现对应应收钱包减少，提现金额增加，已付金额增加	
			//获取自己公司的钱包
			WtWallet wVo = new WtWallet();
			wVo.setOwnerId(withdrawals.getApplyId());
			wVo.setOwnerType(CompanyType.Supplier.value());
			List<WtWallet> walletList = wtWalletService.findList(wVo);

			if (null == walletList || walletList.size() == 0) {
				throw new ServiceException(RespCode.wall_not_set," 钱包没设定");
			}

			WtWallet wWithdrawals = null;
			WtWallet wIncomed = null;
			for (WtWallet w : walletList) {
				if (w.getItemId() == WalletItemEnum.WITHDRAWALS.getValue()) {
					wWithdrawals = w;
					continue;
				}
				if (w.getItemId() == WalletItemEnum.INCOMED.getValue()) {
					wIncomed = w;
					continue;
				}
			}
			
			//获取收款人已付钱包
			WtWallet wPaid=wtWalletService.getPaidByTissue(wWithdrawals.getPayerId(), CompanyType.SupplyChain.value(), CompanyType.Supplier.value(),WalletItemEnum.PAID.getValue());
			//获取收款人未付钱包
			WtWallet uwPaid=wtWalletService.getPaidByTissue(wWithdrawals.getPayerId(), CompanyType.SupplyChain.value(), CompanyType.Supplier.value(),WalletItemEnum.UPPAID.getValue());
			
			if (null == wWithdrawals ||null == wPaid || null == uwPaid ) {
				throw new ServiceException(RespCode.wall_not_initialized," 钱包没初始化");
			}
			
			double amount=withdrawals.getAmount();
					
			withdrawals.setSourceType(WithdrawalsStatusEnum.RECOVER.getValue());		
			withdrawals.setDealOn(System.currentTimeMillis());
			withdrawals.setDealRemark(vo.getDealRemark());
			withdrawals.setDealBy(withdrawals.getDealBy());
			
			withdrawals.setUpdateBy(withdrawals.getDealBy());
			withdrawals.setUpdateOn(System.currentTimeMillis());
			withdrawals.setStatisticsDate(System.currentTimeMillis());
			
			withdrawals.setAstatus(WithdrawalsStatusEnum.RECOVER.getValue());
			
			this.modVoNotNull(withdrawals);
			//添加操作记录表
			addWtWithdrawalsOperation(withdrawals, "追回提现");
			// 钱包提现金额增加
			wWithdrawals.setItemAmount(wWithdrawals.getItemAmount() +amount);
			wWithdrawals.setUpdateTime(System.currentTimeMillis());
			wtWalletService.modVoNotNull(wWithdrawals);

			// 应收货款减少
			wIncomed.setItemAmount(wIncomed.getItemAmount() - amount);
			wIncomed.setUpdateTime(System.currentTimeMillis());
			wtWalletService.modVoNotNull(wIncomed);
			
			//收款人的未付钱包减少
			uwPaid.setItemAmount(uwPaid.getItemAmount() - withdrawals.getAmount());
			uwPaid.setUpdateTime(System.currentTimeMillis());
			wtWalletService.modVoNotNull(uwPaid);	
			
			//收款人的已付钱包增加
			wPaid.setItemAmount(wPaid.getItemAmount() +amount);	//增加
			wPaid.setUpdateTime(System.currentTimeMillis());
			wtWalletService.modVoNotNull(wPaid);

		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 添加操作记录表 date: 2016年6月8日 上午10:49:10 <br/>
	 * @author 王栋
	 * @throws DaoException 
	 */
	public void addWtWithdrawalsOperation(WtGysWithdrawals vo, String createContent) throws DaoException {
		WtWithdrawalsOperation wtWithdrawalsOperation = new WtWithdrawalsOperation();
		wtWithdrawalsOperation.setDmId(fifteenLongId.nextId());
		wtWithdrawalsOperation.setAmount(vo.getAmount());
		wtWithdrawalsOperation.setCreateBy(vo.getApplyId());
		wtWithdrawalsOperation.setCreateContent(createContent);
		wtWithdrawalsOperation.setCreateTime(System.currentTimeMillis());
		wtWithdrawalsOperation.setRemark(vo.getDealRemark());
		wtWithdrawalsOperation.setSourceId(vo.getSourceId());
		wtWithdrawalsOperationDao.addVo(wtWithdrawalsOperation);
	}
	
	@Override
	public List<WtGysWithdrawals> getWithdrawalsList(WtGysWithdrawalsPage page) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}