package com.qtz.sm.controller.shop;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shop.page.ShopCommentPage;
import com.qtz.sm.shop.service.ShopCommentService;
import com.qtz.sm.shop.vo.ShopComment;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

@RestController
@RequestMapping(value = "/shop/comment")
public class ShopCommentController extends BaseController {

	@Autowired
	private ShopCommentService shopCommentService;

	/**
	 * 添加用户评论信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param shopComment
	 *            用户评论信息对象
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "addCommnet", method = RequestMethod.POST)
	public void addCommnet(@RequestHeader("token") String sid, 
			ShopComment shopComment,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //参数验证
            checkVo(shopComment);
            //信息填充
            shopComment.setCommentId(getUserId(sid, response));
            Date date = new Date();
            shopComment.setCreateOn(date);
            shopComment.setUpdateOn(date);

            //添加
            shopCommentService.addVo(shopComment);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("添加用户评论信息出错！", se);
            RespHandler.respError(RespMsg.addComment_error, response);
        } catch (Exception e) {
            log.error("添加用户评论信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
	/**
	 * 获取用户评论信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param dmID
	 *            评论信息ID
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "getCommentInfo", method = RequestMethod.GET)
	public void getCommentInfo(@RequestHeader("token") String sid, 
			@ApiParam(value = "评论信息ID", required = true) @RequestParam("dmID") Long dmID,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);
            

            //添加
            ShopComment shopComment = shopCommentService.findVo(dmID, null);
            RespHandler.respOK(shopComment,response);
        } catch (ServiceException se) {
            log.error("获取用户评论信息出错！", se);
            RespHandler.respError(RespMsg.getComment_error, response);
        } catch (Exception e) {
            log.error("获取用户评论信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
	/**
	 * 获取一页评论信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param shopComment
	 *            评论信息对象* @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "getList", method = RequestMethod.GET)
	public void getList(@RequestHeader("token") String sid, 
			ShopComment shopComment,
			Double startLevel,Double endLevel,
            @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //转换参数
            ShopCommentPage page = new ShopCommentPage();
            page.setCommentId(shopComment.getCommentId());
            page.setShopId(shopComment.getShopId());
            page.setLevel(shopComment.getLevel());
            page.setTitle(shopComment.getTitle());
            page.setStartLevel(startLevel);
            page.setEndLevel(endLevel);
            page.setNowPage(pageNum);
            page.setPageSize(pageSize);
            page.setOrderField("create_on");
            page.setOrderDirection(false);
            //获取数据
            Pager<ShopComment, Long> result = shopCommentService.query(page, ShopComment.class);
            RespHandler.respOK(result,response); 
        } catch (ServiceException se) {
            log.error("批量获取用户评论信息出错！", se);
            RespHandler.respError(RespMsg.getComment_error, response);
        } catch (Exception e) {
            log.error("批量获取用户评论信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
	/**
	 * 删除评论信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param shopComment
	 *            dmID* @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "delComment", method = RequestMethod.POST)
	public void delComment(@RequestHeader("token") String sid, 
			@ApiParam(value = "评论信息ID", required = true) @RequestParam("dmID") Long dmID,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //删除
            shopCommentService.delId(dmID);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("删除用户评论信息出错！", se);
            RespHandler.respError(RespMsg.removeComment_error, response);
        } catch (Exception e) {
            log.error("删除用户评论信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	

    /**
     * 添加便利店时，数据验证
     *
     * @param shopInfo 便利店信息
     * @throws ServiceException
     */
    private void checkVo(ShopComment shopComment) throws ServiceException {
        if (null == shopComment.getShopId()) {
            throw new ServiceException(ExceptionCode.SHOP_ID_IS_NULL, "便利店ID为空！");
        }
        if (null == shopComment.getLevel()) {
            throw new ServiceException("星级评价为空！");
        }
        if (StringUtils.isEmpty(shopComment.getTitle())) {
            throw new ServiceException("评论标签为空！");
        }
        if (StringUtils.isEmpty(shopComment.getComment())) {
        	throw new ServiceException("评论内容为空！");
        }

    }

}
