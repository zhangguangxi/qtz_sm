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
import com.qtz.sm.wallet.page.WtYccBldglIncomePage;
import com.qtz.sm.wallet.page.WtYccBldglWithdrawalsPage;
import com.qtz.sm.wallet.page.WtYccCsIncomePage;
import com.qtz.sm.wallet.page.WtYccCsWithdrawalsPage;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.service.WtYccBldglIncomeService;
import com.qtz.sm.wallet.service.WtYccBldglWithdrawalsService;
import com.qtz.sm.wallet.service.WtYccCsIncomeService;
import com.qtz.sm.wallet.service.WtYccCsWithdrawalsService;
import com.qtz.sm.wallet.vo.WtYccBldglIncome;
import com.qtz.sm.wallet.vo.WtYccBldglWithdrawals;
import com.qtz.sm.wallet.vo.WtYccCsIncome;
import com.qtz.sm.wallet.vo.WtYccCsWithdrawals;

@Controller
@RequestMapping(value="/wallet/wtYcc/")
public class WtYccWalletController extends BaseController{

	@Resource(name = "wtYccBldglIncomeServiceImpl")
	private WtYccBldglIncomeService wtYccBldglIncomeService;
	
	@Resource(name="wtYccCsIncomeServiceImpl")
	private WtYccCsIncomeService wtYccCsIncomeService;
	
	@Resource(name = "wtYccBldglWithdrawalsServiceImpl")
	private WtYccBldglWithdrawalsService wtYccBldglWithdrawalsService;
	
	@Resource(name="wtYccCsWithdrawalsServiceImpl")
	private WtYccCsWithdrawalsService wtYccCsWithdrawalsService; 
	
	@Resource(name="wtWalletServiceImpl")
	private WtWalletService wtWalletService;
	
	@Resource(name="wtBankCardServiceImpl")
	private WtBankCardService wtBankCardService;
	
	
	@RequestMapping(value = "getBldglIncomeList", method = RequestMethod.POST)
	public void getBldglIncomeList(@RequestHeader("token") String sid,  @RequestBody WtYccBldglIncomePage page,HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return ;
			}
			page.setOwnerId(user.getCompanyDmId());
			page.setOrderField("create_time");
			Pager<WtYccBldglIncome, Long> query = wtYccBldglIncomeService.query(page,null);
			RespHandler.respOK(query, response);

		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getIncomeList_error(e.getErrorType()), response);
		} 
	}

	@RequestMapping(value = "getCsIncomeList", method = RequestMethod.POST)
	public void getCsIncomeList(@RequestHeader("token") String sid,  @RequestBody WtYccCsIncomePage page,HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return ;
			}
			page.setOwnerId(user.getCompanyDmId());
			page.setOrderField("create_time");
			Pager<WtYccCsIncome, Long> query = wtYccCsIncomeService.query(page,null);
			RespHandler.respOK(query, response);

		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.getIncomeList_error(e.getErrorType()), response);
		} 
	}
	
	@RequestMapping(value = "getBldglWithdrawalsList", method = RequestMethod.POST)
	public void getBldglWithdrawalsList(@RequestHeader("token") String sid,  @RequestBody WtYccBldglWithdrawalsPage page,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
//			page.setApplyId(user.getCompanyDmId());
			Pager<WtYccBldglWithdrawals, Long> query = wtYccBldglWithdrawalsService.query(page, null);
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
//	@RequestMapping(value = "auditBldglWithdrawalsList", method = RequestMethod.POST)
//	public void auditBldglWithdrawalsList(@RequestHeader("token") String sid,  @RequestBody WtYccBldglWithdrawalsPage page,
//			HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			if (user == null) {
//				RespHandler.respError(RespMsg.user_not_login, response);
//				return;
//			}
////			page.setApplyId(user.getCompanyDmId());
//			Pager<WtYccBldglWithdrawals, Long> query = wtYccBldglWithdrawalsService.queryAudit(page, null);
//			RespHandler.respOK(query, response);
//		} catch (ServiceException e) {
//			log.error(e);
//			RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
//		}
//	}
	
	
	
//	/**
//	 *<p>操作记录</P> 
//	 * @param sourceId 源ID
//	 * @return 
//	 * @throws DaoException 
//	 */
//	@RequestMapping(value = "getBldglOperateWithdrawalsList", method = RequestMethod.POST)
//	public void getBldglOperateWithdrawalsList(@RequestHeader("token") String sid, Long sourceId,HttpServletResponse response)
//			throws IOException {
//		try {
//			    User user = getUser(sid);
//
//				if (user == null) {
//					RespHandler.respError(RespMsg.user_not_login, response);
//					return;
//				}
//				if (sourceId == null) {
//					RespHandler.respError(RespMsg.wallet_sourceId_not_exist, response);
//					return;
//				}
//				List<WtYccBldglWithdrawals> query = wtYccBldglWithdrawalsService.findOperateTakeList(sourceId);
//				RespHandler.respOK(query, response);
//			} catch (ServiceException e) {
//				log.error(e);
//				RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
//			}
//	}
	
	
//	/**
//	 *<p>操作记录</P> 
//	 * @param sourceId 源ID
//	 * @return 
//	 */
//	@RequestMapping(value = "getcsOperateWithdrawalsList", method = RequestMethod.POST)
//	public void getcsOperateWithdrawalsList(@RequestHeader("token") String sid, Long sourceId,HttpServletResponse response)
//			throws IOException {
//		try {
//			    User user = getUser(sid);
//
//				if (user == null) {
//					RespHandler.respError(RespMsg.user_not_login, response);
//					return;
//				}
//				if (sourceId == null) {
//					RespHandler.respError(RespMsg.wallet_sourceId_not_exist, response);
//					return;
//				}
//				List<WtYccCsWithdrawals> query = wtYccCsWithdrawalsService.findOperateTakeList(sourceId);
//				RespHandler.respOK(query, response);
//			} catch (ServiceException e) {
//				log.error(e);
//				RespHandler.respError(RespMsg.getBankList_error(e.getErrorType()), response);
//			}
//	}

	/**
	 *<p>申请提现</P> 
	 * @param amount 提现金额
	 * @param bankName 银行名称（包含支行 地址等信息）
	 * @return 
	 */
	@RequestMapping(value = "applyBldglWithdrawals", method = RequestMethod.POST)
	public void applyBldglWithdrawals(@RequestHeader("token") String sid, Double  amount ,Long bankCardId,Long gylId, HttpServletResponse response)
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
	
			WtYccBldglWithdrawals withdrawals=new WtYccBldglWithdrawals();
			withdrawals.setApplyId(user.getCompanyDmId());
			withdrawals.setApplyName(user.getCompanyName());
			withdrawals.setApplyPhone(user.getPhone());
			
			withdrawals.setBankcardId(bankCardId);
			withdrawals.setAmount(amount);
	
			wtYccBldglWithdrawalsService.saveApplyWithdrawals(withdrawals);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error(e);
			getrespError(e.getErrorType(),response);
		}
	}
	/**
	 * 同意提现
	 */
	@RequestMapping(value = "acceptBldglWithdrawals", method = RequestMethod.POST)
	public void acceptBldglWithdrawals(@RequestHeader("token") String sid, @RequestBody List<WtYccBldglWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			for(WtYccBldglWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtYccBldglWithdrawalsService.saveAccepWithdrawals(wd);
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
	@RequestMapping(value = "rejectBldglWithdrawals", method = RequestMethod.POST)
	public void rejectBldglWithdrawals(@RequestHeader("token") String sid, @RequestBody List<WtYccBldglWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			for(WtYccBldglWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtYccBldglWithdrawalsService.saveRejectWithdrawals(wd);
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
	@RequestMapping(value = "recoverBldglWithdrawals", method = RequestMethod.POST)
	public void recoverBldglWithdrawals(@RequestHeader("token") String sid,@RequestBody  List<WtYccBldglWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			
			for(WtYccBldglWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtYccBldglWithdrawalsService.saveRecoverWithdrawals(wd);
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
	
	@RequestMapping(value = "getCsWithdrawalsList", method = RequestMethod.POST)
	public void getCsWithdrawalsList(@RequestHeader("token") String sid,  @RequestBody WtYccCsWithdrawalsPage page,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
//			page.setApplyId(user.getCompanyDmId());
			Pager<WtYccCsWithdrawals, Long> query = wtYccCsWithdrawalsService.query(page, null);
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
//	@RequestMapping(value = "auditCsWithdrawalsList", method = RequestMethod.POST)
//	public void auditCsWithdrawalsList(@RequestHeader("token") String sid,  @RequestBody WtYccCsWithdrawalsPage page,
//			HttpServletResponse response) throws IOException {
//		try {
//			User user = getUser(sid);
//			if (user == null) {
//				RespHandler.respError(RespMsg.user_not_login, response);
//				return;
//			}
////			page.setApplyId(user.getCompanyDmId());
//			Pager<WtYccCsWithdrawals, Long> query = wtYccCsWithdrawalsService.queryAudit(page, null);
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
	@RequestMapping(value = "applyCsWithdrawals", method = RequestMethod.POST)
	public void applyCsWithdrawals(@RequestHeader("token") String sid, Double  amount ,Long bankCardId,Long gylId, HttpServletResponse response)
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
	
			WtYccCsWithdrawals withdrawals=new WtYccCsWithdrawals();
			withdrawals.setApplyId(user.getCompanyDmId());
			withdrawals.setApplyName(user.getCompanyName());
			withdrawals.setApplyPhone(user.getPhone());
			
			withdrawals.setUserId(user.getDmId());
			withdrawals.setBankcardId(bankCardId);
			withdrawals.setAmount(amount);
	
			wtYccCsWithdrawalsService.saveApplyWithdrawals(withdrawals);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
			log.error(e);
			getrespError(e.getErrorType(),response);
		}
	}
	/**
	 * 同意提现
	 */
	@RequestMapping(value = "acceptCsWithdrawals", method = RequestMethod.POST)
	public void acceptCsWithdrawals(@RequestHeader("token") String sid, @RequestBody List<WtYccCsWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			for(WtYccCsWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtYccCsWithdrawalsService.saveAccepWithdrawals(wd);
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
	@RequestMapping(value = "rejectCsWithdrawals", method = RequestMethod.POST)
	public void rejectCsWithdrawals(@RequestHeader("token") String sid,@RequestBody List<WtYccCsWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
	
			for(WtYccCsWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtYccCsWithdrawalsService.saveRejectWithdrawals(wd);
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
	@RequestMapping(value = "recoverCsWithdrawals", method = RequestMethod.POST)
	public void recoverCsWithdrawals(@RequestHeader("token") String sid,@RequestBody List<WtYccCsWithdrawals> withdrawalsList,
			HttpServletResponse response) throws IOException {
		try {
			User user = getUser(sid);
			if (user == null) {
				RespHandler.respError(RespMsg.user_not_login, response);
				return;
			}
			List<WithdrawalsOut> outList=new ArrayList<WithdrawalsOut>();
			
			for(WtYccCsWithdrawals wd:withdrawalsList){
				WithdrawalsOut out=new WithdrawalsOut();
				out.setId(wd.getDmId());
				out.setSourceId(wd.getSourceId());
				out.setUserId(user.getDmId());
				outList.add(out);
				try {
					wd.setDealBy(user.getDmId());
					wd.setUserId(user.getDmId());
					wtYccCsWithdrawalsService.saveRecoverWithdrawals(wd);
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
