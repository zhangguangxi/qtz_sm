package com.qtz.sm.controller.wallet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.wallet.model.WithdrawalsOut;
import com.qtz.sm.wallet.page.WtGysIncomePage;
import com.qtz.sm.wallet.page.WtGysWithdrawalsPage;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.service.WtGysIncomeService;
import com.qtz.sm.wallet.service.WtGysWithdrawalsService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.vo.WtGysIncome;
import com.qtz.sm.wallet.vo.WtGysWithdrawals;

@Controller
@RequestMapping("/wallet/wtGys/")
public class WtGysWalletController extends BaseController {

	@Resource(name = "wtGysIncomeServiceImpl")
	private WtGysIncomeService wtGysIncomeService;

	@Resource(name = "wtGysWithdrawalsServiceImpl")
	private WtGysWithdrawalsService wtGysWithdrawalsService;
	
	@Resource(name="wtWalletServiceImpl")
	private WtWalletService wtWalletService;
	
	@Resource(name="wtBankCardServiceImpl")
	private WtBankCardService wtBankCardService;
	
	@RequestMapping(value = "getIncomeList", method = RequestMethod.POST)
	public void getIncomeList(@RequestHeader("token") String sid,  @RequestBody WtGysIncomePage page , HttpServletResponse response)
			throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}

			page.setOwnerId(user.getCompanyDmId());
			page.setOrderField("create_time");
			Pager<WtGysIncome, Long> query = wtGysIncomeService.query(page, null);
			RespHandler.respOK(query, response);

		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getIncomeList_error(e.getErrorType()), response);
		}
	}

	@RequestMapping(value = "getWithdrawalsList", method = RequestMethod.POST)
	public void getWithdrawalsList(@RequestHeader("token") String sid,  @RequestBody WtGysWithdrawalsPage page,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
//			page.setApplyId(user.getCompanyDmId());
			Pager<WtGysWithdrawals, Long> query = wtGysWithdrawalsService.query(page, null);
			RespHandler.respOK(query, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
		}
	}
	
//	/**
//	 *<p>提现审核</P> 
//	 * @param page
//	 * @return 
//	 */
//	@RequestMapping(value = "auditWithdrawalsList", method = RequestMethod.POST)
//	public void auditWithdrawalsList(@RequestHeader("token") String sid,  @RequestBody WtGysWithdrawalsPage page,
//			HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			if (user == null) {
//				RespHandler.respError(RespMsg.user_not_login, response);
//				return;
//			}
////			page.setApplyId(user.getCompanyDmId());
//			Pager<WtGysWithdrawals, Long> query = wtGysWithdrawalsService.queryAudit(page, null);
//			RespHandler.respOK(query, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
//		}
//	}
	
	

	/**
	 *<p>申请提现</P> 
	 * @param amount 提现金额
	 * @param bankName 银行名称（包含支行 地址等信息）
	 * @return 
	 */
	@RequestMapping(value = "applyWithdrawals", method = RequestMethod.POST)
	public void applyWithdrawals(@RequestHeader("token") String sid, Double  amount ,Long bankCardId, HttpServletResponse response)
			throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			
			if (amount== null) {
				RespHandler.respError(RespMsg.wallet_amount_not_exist, response);
				return;
			}
			if (bankCardId== null) {
				RespHandler.respError(RespMsg.wallet_bankCardId_not_exist, response);
				return;
			}
	
			WtGysWithdrawals withdrawals=new WtGysWithdrawals();
			withdrawals.setApplyId(user.getCompanyDmId());
			withdrawals.setApplyName(user.getCompanyName());
			withdrawals.setApplyPhone(user.getPhone());
			
			withdrawals.setBankcardId(bankCardId);
			withdrawals.setAmount(amount);
	
			wtGysWithdrawalsService.saveApplyWithdrawals(withdrawals);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error(e);
			getrespError(e.getErrorType(),response);
		}
	}
	/**
	 * 同意提现
	 */
	@RequestMapping(value = "acceptWithdrawals", method = RequestMethod.POST)
	public void acceptWithdrawals(@RequestHeader("token") String sid, @RequestBody List<WtGysWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			for(WtGysWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtGysWithdrawalsService.saveAccepWithdrawals(wd);
					out.setMsgCode(0);
					out.setMsg("操作成功！");
				} catch (Exception e) {
					out.setMsgCode(-1);
					out.setMsg(e.getMessage());
					continue;
				}
			}
			
			RespHandler.respOK(outList, response);

		} catch (ServiceException e) {
			log.error(e);
			getrespError(e.getErrorType(),response);
		}
	}

	/**
	 * 驳回提现
	 */
	@RequestMapping(value = "rejectWithdrawals", method = RequestMethod.POST)
	public void rejectWithdrawals(@RequestHeader("token") String sid, @RequestBody List<WtGysWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			for(WtGysWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtGysWithdrawalsService.saveRejectWithdrawals(wd);
					out.setMsgCode(0);
					out.setMsg("操作成功！");
				} catch (Exception e) {
					out.setMsgCode(-1);
					out.setMsg(e.getMessage());
					continue;
				}
			}
			
			RespHandler.respOK(outList, response);

		} catch (ServiceException e) {
			log.error(e);
			getrespError(e.getErrorType(),response);
		}
	}

	/**
	 * 追回提现
	 */
	@RequestMapping(value = "recoverWithdrawals", method = RequestMethod.POST)
	public void recoverWithdrawals(@RequestHeader("token") String sid, @RequestBody List<WtGysWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			for(WtGysWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtGysWithdrawalsService.saveRecoverWithdrawals(wd);
					out.setMsgCode(0);
					out.setMsg("操作成功！");
				} catch (Exception e) {
					out.setMsgCode(-1);
					out.setMsg(e.getMessage());
					continue;
				}
			}
			
			RespHandler.respOK(outList, response);

		} catch (ServiceException e) {
			log.error(e);
			getrespError(e.getErrorType(),response);
		}
	}

}
