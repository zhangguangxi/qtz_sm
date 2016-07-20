package com.qtz.sm.wallet.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.enums.BankAccountType;
import com.qtz.sm.session.service.SessionService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.wallet.dao.WtBankCardDao;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.vo.WtBankCard;
/**
 * <p>Title:WtBankCardServiceImpl</p>
 * <p>Description:银行卡信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-25
 */
@Service("wtBankCardServiceImpl")
public class WtBankCardServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtBankCard,java.lang.Long> implements WtBankCardService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(WtBankCardServiceImpl.class);
	/**注入银行卡信息DAO接口类*/
	@Resource(name="wtBankCardDaoImpl")
    private WtBankCardDao dao;
    
	@Resource(name="sessionServiceImpl")
	private SessionService  sessionService;
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtBankCard,java.lang.Long> getDao() {
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
	public WtBankCard saveBindingBank(String sid,String cardNum, String bankName,String bankBranch,String bankAddress , String cardholderName,Integer accountType) throws ServiceException {

		User user = sessionService.getSessionUser(sid);
		if(user==null){
			throw new ServiceException(ExceptionCode.USER_NO_LOGIN,"user no login!");
		}
		if (accountType==null) {
			throw new ServiceException(ExceptionCode.NULL_EXCEPTION,"null exception"+user.getCompanyName()+"_"+bankName+"_"+cardNum);
		}
		
		if (StringUtils.isEmpty(cardholderName)) {
			throw new ServiceException(ExceptionCode.CARDHOLDERNAME_PARAM_NULL,"没有持卡人名字");
		}
		
		if(accountType==BankAccountType.BUSINESS.value()){
			if(!cardholderName.equals(user.getCompanyName())){
				throw new ServiceException(ExceptionCode.WALLET_CARDHOLDERNAME_ERROR,"持卡人错误"+user.getCompanyName()+" "+cardholderName);
				
			}
		}else{
			if(!cardholderName.equals(user.getCompanyName())){
				throw new ServiceException(ExceptionCode.WALLET_CARDHOLDERNAME_ERROR,"持卡人错误"+user.getLpName()+" "+cardholderName);
				
			}
		}
		if (StringUtils.isEmpty(cardNum)) {
			throw new ServiceException(ExceptionCode.NULL_EXCEPTION,"cardNum is empty!");
		}
		
		if (StringUtils.isEmpty(bankName)) {
			throw new ServiceException(ExceptionCode.NULL_EXCEPTION,"bankName is empty!");
		}
		
		if (StringUtils.isEmpty(bankAddress)) {
			throw new ServiceException(ExceptionCode.NULL_EXCEPTION,"bankAddress is empty!");
		}
		

		try {
			WtBankCard bankCard=new WtBankCard();
			
			bankCard.setCompanyType(user.getCompanyType());
			bankCard.setCompanyId(user.getCompanyDmId());
			bankCard.setCardNum(cardNum);
			
			Long result= dao.findCount(bankCard);
			if (result>0) {
					throw new ServiceException(ExceptionCode.EXIST_BANKCARD," 存在银行卡号");
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		WtBankCard bankCardInfo=new WtBankCard();
		bankCardInfo.setCompanyType(user.getCompanyType());
		bankCardInfo.setCompanyId(user.getCompanyDmId());
		bankCardInfo.setAccountType(accountType);
		bankCardInfo.setBankName(bankName);
		bankCardInfo.setBankBranch(bankBranch);
		bankCardInfo.setBankAddress(bankAddress);
		bankCardInfo.setCardNum(cardNum);
		bankCardInfo.setEnable(1);
		bankCardInfo.setCreateTime(System.currentTimeMillis());
		
		bankCardInfo  = save(bankCardInfo);
		return bankCardInfo;
	
	}
	@Override
	public WtBankCard saveBindingBank(WtBankCard wtBankCard) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			WtBankCard bankCard=new WtBankCard();
			
			bankCard.setCompanyType(wtBankCard.getCompanyType());
			bankCard.setCompanyId(wtBankCard.getCompanyId());
			bankCard.setCardNum(wtBankCard.getCardNum());
			
			Long result= dao.findCount(bankCard);
			if (result>0) {
					throw new ServiceException(ExceptionCode.EXIST_BANKCARD," 存在银行卡号");
			}
			WtBankCard querywtBankCards=new WtBankCard();
			querywtBankCards.setCompanyId(wtBankCard.getCompanyId());
			List<WtBankCard> list=dao.findList(querywtBankCards);
			if(list.size()>0){
				throw new ServiceException(ExceptionCode.WTAA_USER_BAKE," 每个用户只能绑定一张银行卡");
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		wtBankCard.setEnable(1);
		wtBankCard.setCreateTime(System.currentTimeMillis());
		
		WtBankCard bankCardInfo  = save(wtBankCard);
		return bankCardInfo;
	
	}
	
	@Override
	public WtBankCard queryWtBankCardGroupInfo(Long companyId,int companyType) throws ServiceException{
		try {
			return dao.queryWtBankCardGroupInfo(companyId,companyType);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
}