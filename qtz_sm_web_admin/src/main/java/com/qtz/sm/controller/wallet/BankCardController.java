package com.qtz.sm.controller.wallet;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.enums.BankAccountType;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.wallet.page.WtBankCardPage;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.vo.WtBankCard;

@Controller
@RequestMapping(value = "/wallet/bankCard/")
public class BankCardController extends BaseController{

	@Resource(name="wtBankCardServiceImpl")
	private WtBankCardService wtBankCardService;

	@RequestMapping(value = "binding", method = RequestMethod.POST)
	public void bindingBank(@RequestHeader("token") String sid, WtBankCard vo , HttpServletResponse response) throws IOException {
		try {			
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			if (StringUtils.isEmpty(vo.getAccountType())) {
				RespHandler.respError(RespMsg.binding_bankAccountType_param_null, response);
				return;
			}
			
			if(vo.getAccountType()==BankAccountType.PERSON.value()){				
				if(!vo.getCardholder().equals(user.getLpName())){
					RespHandler.respError(RespMsg.binding_bankName_lpname_null, response);
					return;
				}
			}
			
			if(vo.getAccountType()==BankAccountType.BUSINESS.value()){
				if(!vo.getCardholder().equals(user.getCompanyName())){
					RespHandler.respError(RespMsg.binding_bankName_companyName_null, response);
					return;
				}
			}
			
			if (StringUtils.isEmpty(vo.getCardNum())) {
				RespHandler.respError(RespMsg.binding_bankNum_param_null, response);
				return;
			}
			String cardNum= vo.getCardNum();
			cardNum=cardNum.replaceAll(" ", "");
			
			if (StringUtils.isEmpty(vo.getBankName())) {
				RespHandler.respError(RespMsg.binding_bankName_param_null, response);
				return;
			}
			
			if (StringUtils.isEmpty(vo.getBankAddress())) {
				RespHandler.respError(RespMsg.binding_bankAddress_param_null, response);
				return;
			}
			
			vo.setCompanyType(user.getCompanyType());
			vo.setCompanyId(user.getCompanyDmId());
			WtBankCard saveBindingBank = wtBankCardService.saveBindingBank(vo);
			RespHandler.respOK(saveBindingBank, response);
		} catch (ServiceException e) {
			log.error(e);
			if (e.getErrorType() == ExceptionCode.USER_NO_AUTHEN) {
				RespHandler.respError(RespMsg.account_no_auth, response);
			}else if (e.getErrorType() == ExceptionCode.NULL_EXCEPTION) {
				RespHandler.respError(RespMsg.binding_bankName_param_null, response);
			} 
			else if (e.getErrorType() == ExceptionCode.DOES_NOT_SUPPORT_THE_BANK) {
				RespHandler.respError(RespMsg.does_not_support_the_bank, response);
			} else if (e.getErrorType() == ExceptionCode.EXIST_BANKCARD) {
				RespHandler.respError(RespMsg.EXIST_BANKCARD, response);
			}else if (e.getErrorType() == ExceptionCode.WTAA_USER_BAKE) {
				RespHandler.respError(RespMsg.WTAA_USER_BAKE, response);
			}	
			else {
				RespHandler.respError(RespMsg.bindingBank_error(e.getErrorType()), response);
			}
		}
	}	
	
	@RequestMapping(value = "delBinding", method = RequestMethod.POST)
	public void delBindingBank(@RequestHeader("token") String sid,
			@RequestParam Long bankId, HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			WtBankCard wtBankCard = wtBankCardService.findVo(bankId, null);
			if (wtBankCard == null) {
				RespHandler.respError(RespMsg.bank_inexistence, response);
				return;
			}
			wtBankCardService.delId(bankId);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(
					RespMsg.delBindingBank_error(e.getErrorType()), response);
		}
	}
	
	@RequestMapping(value = "getList/{pageIndex}", method = RequestMethod.GET)
	public void getBankCardList(@RequestHeader("token") String sid,
			@PathVariable Integer pageIndex,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			WtBankCardPage page = new WtBankCardPage();
			page.setCompanyType(user.getCompanyType());
			page.setCompanyId(user.getCompanyDmId());
			page.setNowPage(pageIndex);
			
			Pager<WtBankCard, Long> query = wtBankCardService.query(page,null);
			RespHandler.respOK(query, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
		} 
	}
	
	/*
	 * 根据组织ID查询是否绑定银行卡
	 */
	@RequestMapping(value = "getPpshGroup ", method = RequestMethod.GET)
	public void getPpshGroup(@RequestHeader("token") String sid,
			@RequestParam Long organizeId,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			if (organizeId == null) {
				RespHandler.respError(RespMsg.wallet_groupId_not_exist, response);
				return;
			}
			WtBankCard bankCard= wtBankCardService.queryWtBankCardGroupInfo(organizeId,user.getCompanyType());
			if(bankCard==null){
				RespHandler.respOK(bankCard, response);
			}else{
				String cardNum=bankCard.getCardNum().length()>4?bankCard.getCardNum().substring(bankCard.getCardNum().length()-4):bankCard.getCardNum();
				bankCard.setCardNum("**** **** **** ***"+cardNum);
				RespHandler.respOK(bankCard, response);
			}
			
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
		} 
	}
	
	@RequestMapping(value = "getInfo", method = RequestMethod.GET)
	public void getBankCardInfo(@RequestHeader("token") String sid,
			@RequestParam Long bankId,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			WtBankCard bankCard= wtBankCardService.findVo(bankId, null);
			if(bankCard==null){
				RespHandler.respError(RespMsg.not_found, response);
			}else{
				String cardNum=bankCard.getCardNum().length()>4?bankCard.getCardNum().substring(bankCard.getCardNum().length()-4):bankCard.getCardNum();
				bankCard.setCardNum("**** **** **** ***"+cardNum);
				RespHandler.respOK(bankCard, response);
			}
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
		} 
	}

}
