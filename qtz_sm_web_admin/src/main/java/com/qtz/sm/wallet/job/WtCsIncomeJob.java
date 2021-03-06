package com.qtz.sm.wallet.job;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.mall.core.common.FifteenLongId;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.wallet.config.WalletConstants;
import com.qtz.sm.wallet.enums.IncomeStatusEnum;
import com.qtz.sm.wallet.service.WtCsIncomeService;
import com.qtz.sm.wallet.service.WtCsIncomeSettlementService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.vo.WtCsIncome;
import com.qtz.sm.wallet.vo.WtCsIncomeSettlement;

/**
 * <p>Title:WtCsIncomeJob</p>
 * <p>Description:超市收入对账任务类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 肖来龙 - lois siau
 * @version v1.0 2016-05-23
 */

@Component(value = "wtCsIncomeJob")
public class WtCsIncomeJob {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtCsIncomeJob.class);
	
	private static Lock lock = new ReentrantLock();
	
	@Resource
	private FifteenLongId fifteenLongId;
	
	@Resource(name="wtCsIncomeServiceImpl")
	private WtCsIncomeService csIncomeService;
	
	@Resource(name="wtCsIncomeSettlementServiceImpl")
	private WtCsIncomeSettlementService csIncomeSettlementService;
	
	@Resource(name="wtWalletServiceImpl")
	private WtWalletService walletService;
	
	public void run(){
		log.debug("超市收入对账任务开始----------->>>>>>>");
		
		if(lock.tryLock()){
			try {
				long now = System.currentTimeMillis();
				WtCsIncome csIncomeQuery = new WtCsIncome();
				csIncomeQuery.setUpdateTime(now);
				csIncomeQuery.setReleaseTime(now);

//				步骤一：将待解冻的收入流水 结算状态设置“结算进行中” 这个中间状态
				int modResult = csIncomeService.modIncomeStatToSettling(csIncomeQuery);
				if(modResult <= 0){
					log.info("超市没有待解冻需要结算的收入流水");
					return ;
				}
				log.info("超市待结算的收入流水条数：" + modResult);
				
//				步骤二： 分组将各个收款人待解冻的收入分组统计
				List<WtCsIncome> resultList = null;
				resultList = csIncomeService.queryTotalAmountGroup(csIncomeQuery);
				if(!CollectionUtils.isEmpty(resultList)){
					for (WtCsIncome wtCsIncome : resultList) {
						//步骤三：开始逐个用户收入结算
						
						WtCsIncomeSettlement csIncomeSm = new WtCsIncomeSettlement();
						try {
							long pk = fifteenLongId.nextId();
							csIncomeSm.setDmId(pk);
							csIncomeSm.setOwnerId(wtCsIncome.getOwnerId());
							csIncomeSm.setTotalAmount(wtCsIncome.getAmount());
							csIncomeSm.setOptType(WalletConstants.INCOME_SETTLEMENT_OPT_TYPE_AUTO);	//1表示自动，2表示手动
							csIncomeSm.setCreateTime(now);
							csIncomeSm.setCreateBy(0L);
							csIncomeSm.setRemark("自动对账结算");
							
							Boolean result = csIncomeSettlementService.settlement(csIncomeSm);
							if(result == false){
								throw new ServiceException("结算失败");
							}
						} catch (ServiceException e) {
							log.error(e);
							
							boolean handleResult = false;	//默认是处理失败，所以
							//若是钱包检测有异常导致的对账失败则迅速失败不进行重试， 其他类型的异常重试2次
							if(e.getErrorType() != ExceptionCode.WALLET_ERROR && e.getErrorType() != ExceptionCode.WALLET_AMOUNT_UNNORMAL){
								int retryTimes = 2;
								while(retryTimes-- > 0){
									try {
										csIncomeSettlementService.settlement(csIncomeSm);	//对账结算重试
										handleResult = true;
										break;	//无异常则 break 循环
									} catch (Exception e1) {
										log.error(e1);
									}
								}
							}
							if(handleResult == false){
								try {
									WtCsIncome csIncomeMod = new WtCsIncome();
									csIncomeMod.setOwnerId(wtCsIncome.getOwnerId());
									csIncomeMod.setUpdateTime(System.currentTimeMillis());
									csIncomeMod.setReleaseTime(now);
									csIncomeMod.setStat(IncomeStatusEnum.FAIL.getValue());
									csIncomeService.modIncomeStat(csIncomeMod );

									csIncomeSm.setRemark("自动对账结算失败，errorCode:" + e.getErrorType() + ",errorMessage：" + e.getErrorMessage());
									csIncomeSettlementService.addVo(csIncomeSm);
								} catch (Exception e1) {
									log.error(e1);
								}
							}
						}
						catch (Exception e) {
							log.error(e);
						}
						
					}
				}
				
			} catch (Exception e) {
				log.error(e);
			}finally{
				lock.unlock();
			}
		}else{
			log.debug("未获取到锁，前一个任务还未结束！");
		}
		
		log.debug("超市收入对账任务结束------------>>>>>>>>");
	}
	
}
