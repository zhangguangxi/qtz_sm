package com.qtz.sm.controller.wallet;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.response.RespCode;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.wallet.enums.WalletItemEnum;
import com.qtz.sm.wallet.model.WtOrder;
import com.qtz.sm.wallet.page.WtWalletPage;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.service.WtWithdrawalsOperationService;
import com.qtz.sm.wallet.validator.ValidFirst;
import com.qtz.sm.wallet.vo.WtWallet;
import com.qtz.sm.wallet.vo.WtWithdrawalsOperation;

@RestController
@RequestMapping(value = "/wallet/wallet/")
public class WalletController extends BaseController {

	@Resource(name = "wtWalletServiceImpl")
	private WtWalletService wtWalletService;

	@Resource(name = "wtBankCardServiceImpl")
	private WtBankCardService wtBankCardService;
	
	@Resource(name = "wtWithdrawalsOperationServiceImpl")
	private WtWithdrawalsOperationService wtWithdrawalsOperationService;
	
	/*
	 * 我的钱包
	 */
	@RequestMapping(value = "getMyWallet", method = RequestMethod.GET)
	public void getMyWallet(@RequestHeader("token") String sid, HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}

			WtWallet vo = new WtWallet();
			vo.setOwnerId(user.getCompanyDmId());
			vo.setOwnerType(user.getCompanyType());

			List<WtWallet> walletList = wtWalletService.findList(vo);

			RespHandler.respOK(walletList, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
		}
	}

	/*
	 * 我的应收钱包
	 */
	@RequestMapping(value = "getWalletIncome", method = RequestMethod.GET)
	public void getWalletIncome(@RequestHeader("token") String sid, HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}

			WtWallet vo = new WtWallet();
			vo.setOwnerId(user.getCompanyDmId());
			vo.setOwnerType(user.getCompanyType());
			vo.setItemId(WalletItemEnum.INCOMED.getValue());

			List<WtWallet> walletList = wtWalletService.findList(vo);

			RespHandler.respOK(walletList, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
		}
	}

	
	/*
	 * 我的应收钱包对应对方的未付钱包
	 */
	@RequestMapping(value = "getUppaidList", method = RequestMethod.GET)
	public void getUppaidList(@RequestHeader("token") String sid, WtWalletPage page, HttpServletResponse response)
			throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			page.setPayerId(user.getCompanyDmId());
			page.setPayerType(user.getCompanyType());
			Pager<WtWallet, Long> paper = wtWalletService.getUppaidList(page);
			RespHandler.respOK(paper, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
		}
	}
	
	/**
	 * 订单完成，给各级组织分配收入， 订单分类：超市订单、便利店订单，根据订单系统提供的信息进行分润。
	 * 
	 * @param sid
	 * @param order 订单信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "distributeIncome", method = RequestMethod.POST)	
	public void distributeIncome(@RequestHeader("token") String sid, @RequestBody @Valid WtOrder order, HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
		   wtWalletService.addDistributions(order);
		   
		   RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(new JSONObject(RespMsg.respOperationMsg(RespCode.wtallet_distriute_income_fail, e.getErrorMessage()) ), response);
		} catch (Exception e){
			log.error(e);
			RespHandler.respError(RespMsg.Wallet.DISTRIBUTE_INCOME_FAIL, response);
		}
	}
	
	/**
	 * 订单退货，撤销分润。
	 * 
	 * @param sid
	 * @param order 订单信息
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "unDistributeIncome", method = RequestMethod.POST)	
	public void unDistributeIncome(@RequestHeader("token") String sid, @RequestBody @Validated(ValidFirst.class) WtOrder order, HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
		   wtWalletService.modUnDistributions(order);
		   
		   RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(new JSONObject(RespMsg.respOperationMsg(RespCode.wtallet_undistriute_income_fail, e.getErrorMessage()) ), response);
		} catch (Exception e) {
			log.error(e);
			RespHandler.respError(RespMsg.Wallet.UNDISTRIBUTE_INCOME_FAIL, response);
		}
	}
	
	/**
	 *<p>操作记录</P> 
	 * @param sourceId 源ID
	 * @return 
	 */
	@RequestMapping(value = "getOperateWithdrawalsList", method = RequestMethod.POST)
	public void getOperateWithdrawalsList(@RequestHeader("token") String sid, Long sourceId,HttpServletResponse response)
			throws IOException {
		try {
			    User user = getUser(sid);

				if (user == null) {
					RespHandler.respError(RespMsg.user_not_login, response);
					return;
				}
				if (sourceId == null) {
					RespHandler.respError(RespMsg.wallet_sourceId_not_exist, response);
					return;
				}
				WtWithdrawalsOperation obj=new WtWithdrawalsOperation();
				obj.setSourceId(sourceId);
				List<WtWithdrawalsOperation> query = wtWithdrawalsOperationService.findList(obj);
				RespHandler.respOK(query, response);
			} catch (ServiceException e) {
				log.error(e);
				RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
			}
	}
}
