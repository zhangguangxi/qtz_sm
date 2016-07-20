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
import com.qtz.sm.wallet.service.WtGylIncomeService;
import com.qtz.sm.wallet.service.WtGylIncomeSettlementService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.vo.WtGylIncome;
import com.qtz.sm.wallet.vo.WtGylIncomeSettlement;

/**
 * <p>Title:WtCsIncomeJob</p>
 * <p>Description:超市收入对账任务类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 肖来龙 - lois siau
 * @version v1.0 2016-05-23
 */

@Component(value = "wtGylIncomeJob")
public class WtGylIncomeJob {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtGylIncomeJob.class);
	
	private static Lock lock = new ReentrantLock();
	
	@Resource
	private FifteenLongId fifteenLongId;
	
	@Resource(name="wtGylIncomeServiceImpl")
	private WtGylIncomeService gylIncomeService;
	
	@Resource(name="wtGylIncomeSettlementServiceImpl")
	private WtGylIncomeSettlementService gylIncomeSettlementService;
	
	@Resource(name="wtWalletServiceImpl")
	private WtWalletService walletService;
	
	public void run(){
		log.debug("供应链的收入对账任务开始----------->>>>>>>");
		
		if(lock.tryLock()){
			try {
				long now = System.currentTimeMillis();
				WtGylIncome gylIncomeParam = new WtGylIncome();
				gylIncomeParam.setUpdateTime(now);
				gylIncomeParam.setReleaseTime(now);

//				步骤一：将待解冻的收入流水 结算状态设置“结算进行中” 这个中间状态
				int modResult = gylIncomeService.modIncomeStatToSettling(gylIncomeParam);
				if(modResult <= 0){
					log.info("供应链没有待解冻需要结算的收入流水");
					return ;
				}
				log.info("供应链待结算的收入流水条数：" + modResult);
				
//				步骤二： 分组将各个收款人待解冻的收入分组统计
				List<WtGylIncome> resultList = null;
				resultList = gylIncomeService.queryTotalAmountGroup(gylIncomeParam);
				if(!CollectionUtils.isEmpty(resultList)){
					for (WtGylIncome wtIncome : resultList) {
						//步骤三：开始结算
						
						WtGylIncomeSettlement incomeSm = new WtGylIncomeSettlement();
						try {
							long pk = fifteenLongId.nextId();
							incomeSm.setDmId(pk);
							incomeSm.setOwnerId(wtIncome.getOwnerId());
							incomeSm.setTotalAmount(wtIncome.getAmount());
							incomeSm.setOptType(WalletConstants.INCOME_SETTLEMENT_OPT_TYPE_AUTO);	//1表示自动，2表示手动
							incomeSm.setCreateTime(now);
							incomeSm.setCreateBy(0L);
							incomeSm.setRemark("自动对账结算");
							
							Boolean result = gylIncomeSettlementService.settlement(incomeSm);
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
										gylIncomeSettlementService.settlement(incomeSm);	//对账结算重试
										handleResult = true;
										break;	//无异常则 break 循环
									} catch (Exception e1) {
										log.error(e1);
									}
								}
							}
							if(handleResult == false){
								try {
									WtGylIncome incomeMod = new WtGylIncome();
									incomeMod.setOwnerId(wtIncome.getOwnerId());
									incomeMod.setUpdateTime(System.currentTimeMillis());
									incomeMod.setReleaseTime(now);
									incomeMod.setStat(IncomeStatusEnum.FAIL.getValue());
									gylIncomeService.modIncomeStat(incomeMod );
									

									incomeSm.setRemark("自动对账结算失败，errorCode:" + e.getErrorType() + ",errorMessage：" + e.getErrorMessage());
									gylIncomeSettlementService.addVo(incomeSm);
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
		
		log.debug("供应链的收入对账任务结束------------>>>>>>>>");
	}
	
}
