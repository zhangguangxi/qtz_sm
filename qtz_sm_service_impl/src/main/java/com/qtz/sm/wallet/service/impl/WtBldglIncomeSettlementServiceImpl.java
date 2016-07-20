package com.qtz.sm.wallet.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mall.core.common.jsonUtil.JSONUtils;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.wallet.dao.WtBldglIncomeSettlementDao;
import com.qtz.sm.wallet.enums.IncomeStatusEnum;
import com.qtz.sm.wallet.enums.WalletItemEnum;
import com.qtz.sm.wallet.service.WtBldglIncomeService;
import com.qtz.sm.wallet.service.WtBldglIncomeSettlementService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.vo.WtBldglIncome;
import com.qtz.sm.wallet.vo.WtBldglIncomeSettlement;
import com.qtz.sm.wallet.vo.WtWallet;
/**
 * <p>Title:WtBldglIncomeSettlementServiceImpl</p>
 * <p>Description:超市收入对账结算记录服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 肖来龙 - lois siau
 * @version v1.0 2016-05-25
 */
@Service("wtBldglIncomeSettlementServiceImpl")
public class WtBldglIncomeSettlementServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtBldglIncomeSettlement,java.lang.Long> implements WtBldglIncomeSettlementService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtBldglIncomeSettlementServiceImpl.class);
	/**注入超市收入对账结算记录DAO接口类*/
	@Resource(name="wtBldglIncomeSettlementDaoImpl")
    private WtBldglIncomeSettlementDao dao;
 
	@Resource(name="wtBldglIncomeServiceImpl")
	private WtBldglIncomeService bldglIncomeService;
	
	@Resource(name="wtWalletServiceImpl")
	private WtWalletService walletService;
	
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtBldglIncomeSettlement,java.lang.Long> getDao() {
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
	
	/**
	 * 开始结算
	 */
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	@Override
	public boolean settlement(WtBldglIncomeSettlement incomeSm) throws ServiceException {
		log.info("便利店管理公司收入结算，请求参数---------->>" + JSONUtils.Object2JSON(incomeSm));
		try {
			Long ownerId = incomeSm.getOwnerId();
			Double amount = incomeSm.getTotalAmount();
			Long createTime = incomeSm.getCreateTime();		//解冻时间点
			
			
			//步骤一： 校验钱包，	校验对应钱包是否存在以及对账中钱包余额是否够扣，如果不够，那肯定是前面对账有问题，将其设为结算失败的。
			Long incomeWalletId = null;
			WtWallet incomeWt = walletService.getWallet(ownerId, CompanyType.StoreManager.value(), WalletItemEnum.INCOME.getValue(), CompanyType.PPSH.value());
			if(incomeWt != null && checkIncomeWallet(incomeWt, amount)){
				incomeWalletId = incomeWt.getDmId();
			}else{
				return false;
			}
			
			Long incomedWalletId = null;
			WtWallet incomedWt = walletService.getWallet(ownerId, CompanyType.StoreManager.value(), WalletItemEnum.INCOMED.getValue(), CompanyType.PPSH.value());
			if(incomedWt != null){
				incomedWalletId = incomedWt.getDmId();
			}else{
				return false;
			}
			
			
			//步骤二：结算记录表添加记录
			dao.addVo(incomeSm);
			
			
			//步骤三：对账中钱包扣除
			final long now = System.currentTimeMillis();
			
			WtWallet incomeWalletMod = new WtWallet();
			incomeWalletMod.setDmId(incomeWalletId);
			incomeWalletMod.setItemAmount(-amount);	//扣除
			incomeWalletMod.setUpdateTime(now);
			log.info("便利店管理公司收入结算，从对账中钱包中扣减：" + JSONUtils.Object2JSON(incomeWalletMod));
			walletService.modWalletAmountIncr(incomeWalletMod);
			
			
			//步骤四：应收钱包增加
			
			WtWallet incomedWalletMod = new WtWallet();
			incomedWalletMod.setDmId(incomedWalletId);
			incomedWalletMod.setItemAmount(amount);	//增加
			incomedWalletMod.setUpdateTime(now);
			log.info("便利店管理公司收入结算，向应收钱包增加：" + JSONUtils.Object2JSON(incomedWalletMod));
			walletService.modWalletAmountIncr(incomedWalletMod);
			
			
			//步骤五：将超市收入结算状态设为对账结算完成

			WtBldglIncome wtIncome = new WtBldglIncome();
			wtIncome.setOwnerId(ownerId);
			wtIncome.setReleaseTime(createTime);
			wtIncome.setUpdateTime(now);
			wtIncome.setStat(IncomeStatusEnum.FINISH.getValue());
			wtIncome.setIncomeSettlementId(incomeSm.getDmId());
			bldglIncomeService.modIncomeStat(wtIncome );
			
			//结算成功
			return true;
		} catch(ServiceException e){
			log.error(e);
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
		
	}
	
	
	/**
	 * 判断对账中钱包金额是否有问题，若是比收入流水的少则有问题。
	 * 
	 * @param wtWallet
	 * @param amount
	 * @return
	 * @throws ServiceException 
	 */
	private boolean checkIncomeWallet(WtWallet wtWallet, Double amount) throws ServiceException{
		// 对账中钱包金额判断
		if(wtWallet.getItemAmount() < amount){
			// 钱包有问题，对账中钱包的金额比结算的金额少
			final String errMsg = "钱包有问题，对账中钱包的金额比结算的金额少， 查询出的钱包信息" + JSONUtils.Object2JSON(wtWallet);
			log.error(errMsg);
			throw new ServiceException(ExceptionCode.WALLET_AMOUNT_UNNORMAL, errMsg);
		}
		return true;
	}
}