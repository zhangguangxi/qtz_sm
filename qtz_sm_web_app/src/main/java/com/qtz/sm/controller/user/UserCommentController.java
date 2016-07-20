//package com.qtz.sm.controller.user;
//
//import java.io.IOException;
//import java.util.Date;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.mall.core.common.response.RespHandler;
//import com.mall.core.common.response.RespMsg;
//import com.mall.core.exception.ExceptionCode;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.vo.Pager;
//import com.qtz.sm.controller.BaseController;
//import com.qtz.sm.user.page.UserCommentPage;
//import com.qtz.sm.user.service.UserCommentService;
//import com.qtz.sm.user.vo.UserComment;
//import com.qtz.sm.user.vo.UserReport;
//import com.wordnik.swagger.annotations.ApiParam;
//
//import utils.StringUtils;
//
//@RestController
//@RequestMapping(value = "/user/comment")
//public class UserCommentController extends BaseController {
//
//	@Autowired
//	private UserCommentService userCommentService;
//
//	/**
//	 * 添加用户评论信息<br/>
//	 *
//	 * @param sid
//	 *            凭证
//	 * @param userComment
//	 *            用户评论信息对象
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "addCommnet", method = RequestMethod.POST)
//	public void addCommnet(@RequestHeader("token") String sid,
//			UserComment userComment,
//			HttpServletResponse response) throws IOException {
//		try {
//			//登陆检查
//            checkLogin(sid, response);
//
//            //参数验证
//            checkVo(userComment);
//            //信息填充
//            userComment.setCommentsId(getUserId(sid, response));
//            Date date = new Date();
//            userComment.setCreateOn(date);
//            userComment.setUpdateOn(date);
//
//            //添加
//            userCommentService.addVo(userComment);
//            RespHandler.respOK(response);
//        } catch (ServiceException se) {
//            log.error("添加用户评论信息出错！", se);
//            RespHandler.respError(RespMsg.getBankList_error(se.getErrorType()), response);
//        } catch (Exception e) {
//            log.error("添加用户评论信息出现系统错误！", e);
//            RespHandler.respServerError(response);
//        }
//	}
//
//	/**
//	 * 获取用户评论信息<br/>
//	 *
//	 * @param sid
//	 *            凭证
//	 * @param dmID
//	 *            评论信息ID
//	 * @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "getCommentInfo", method = RequestMethod.GET)
//	public void getCommentInfo(@RequestHeader("token") String sid,
//			@ApiParam(value = "评论信息ID", required = true) @RequestParam("dmID") Long dmID,
//			HttpServletResponse response) throws IOException {
//		try {
//			//登陆检查
//            checkLogin(sid, response);
//
//
//            //添加
//            UserComment userComment = userCommentService.findVo(dmID, null);
//            RespHandler.respOK(userComment,response);
//        } catch (ServiceException se) {
//            log.error("获取用户评论信息出错！", se);
//            RespHandler.respError(RespMsg.getBankList_error(se.getErrorType()), response);
//        } catch (Exception e) {
//            log.error("获取用户评论信息出现系统错误！", e);
//            RespHandler.respServerError(response);
//        }
//	}
//
//	/**
//	 * 获取一页评论信息<br/>
//	 *
//	 * @param sid
//	 *            凭证
//	 * @param userComment
//	 *            评论信息对象* @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "getList", method = RequestMethod.GET)
//	public void getList(@RequestHeader("token") String sid,
//			UserComment userComment,
//            @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
//            @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
//			HttpServletResponse response) throws IOException {
//		try {
//			//登陆检查
//            checkLogin(sid, response);
//
//            //转换参数
//            UserCommentPage page = new UserCommentPage();
//            page.setCommentsId(userComment.getCommentsId());
//            page.setShopId(userComment.getShopId());
//            page.setOrderId(userComment.getOrderId());
//            page.setNowPage(pageNum);
//            page.setPageSize(pageSize);
//            page.setOrderField("create_on");
//            page.setOrderDirection(false);
//            //获取数据
//            Pager<UserComment, Long> result = userCommentService.query(page, UserComment.class);
//            RespHandler.respOK(result,response);
//        } catch (ServiceException se) {
//            log.error("批量获取用户评论信息出错！", se);
//            RespHandler.respError(RespMsg.getBankList_error(se.getErrorType()), response);
//        } catch (Exception e) {
//            log.error("批量获取用户评论信息出现系统错误！", e);
//            RespHandler.respServerError(response);
//        }
//	}
//
//	/**
//	 * 删除评论信息<br/>
//	 *
//	 * @param sid
//	 *            凭证
//	 * @param userComment
//	 *            dmID* @param response
//	 * @throws IOException
//	 */
//	@RequestMapping(value = "delComment", method = RequestMethod.POST)
//	public void delComment(@RequestHeader("token") String sid,
//			@ApiParam(value = "评论信息ID", required = true) @RequestParam("dmID") Long dmID,
//			HttpServletResponse response) throws IOException {
//		try {
//			//登陆检查
//            checkLogin(sid, response);
//
//            //删除
//            userCommentService.delId(dmID);
//            RespHandler.respOK(response);
//        } catch (ServiceException se) {
//            log.error("删除用户评论信息出错！", se);
//            RespHandler.respError(RespMsg.getBankList_error(se.getErrorType()), response);
//        } catch (Exception e) {
//            log.error("删除用户评论信息出现系统错误！", e);
//            RespHandler.respServerError(response);
//        }
//	}
//
//
//    /**
//     * 添加便利店时，数据验证
//     *
//     * @param shopInfo 便利店信息
//     * @throws ServiceException
//     */
//    private void checkVo(UserComment userComment) throws ServiceException {
//        if (null == userComment.getOrderId()) {
//            throw new ServiceException("订单号为空！");
//        }
//        if (null == userComment.getShopId()) {
//            throw new ServiceException(ExceptionCode.SHOP_ID_IS_NULL, "便利店ID为空！");
//        }
//        if (null == userComment.getLevel()) {
//            throw new ServiceException("星级评价为空！");
//        }
//        if (StringUtils.isEmpty(userComment.getTitle())) {
//            throw new ServiceException("评论标签为空！");
//        }
//        if (StringUtils.isEmpty(userComment.getComments())) {
//        	throw new ServiceException("评论内容为空！");
//        }
//
//    }
//
//}
