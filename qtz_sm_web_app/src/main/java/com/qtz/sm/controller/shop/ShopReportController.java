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
import com.qtz.sm.shop.page.ShopReportPage;
import com.qtz.sm.shop.service.ShopReportService;
import com.qtz.sm.shop.vo.ShopReport;
import com.qtz.sm.supermarket.vo.SupermarketCategory;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

@RestController
@RequestMapping(value = "/shop/report")
public class ShopReportController extends BaseController {

	@Autowired
	private ShopReportService shopReportService;

	/**
	 * 添加用户举报信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param shopReport
	 *            用户举报信息对象
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "addReport", method = RequestMethod.POST)
	public void addReport(@RequestHeader("token") String sid, 
			ShopReport shopReport,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //参数验证
            checkVo(shopReport);
            //信息填充
            shopReport.setUserId(getUserId(sid, response));
            Date date = new Date();
            shopReport.setCreateTime(date);
            shopReport.setUpdateTime(date);

            //添加
            shopReportService.addVo(shopReport);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("添加用户举报信息出错！", se);
            RespHandler.respError(RespMsg.addReport_error, response);
        } catch (Exception e) {
            log.error("添加用户举报信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
	/**
	 * 获取用户举报信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param dmID
	 *            举报信息ID
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "getReportInfo", method = RequestMethod.GET)
	public void getReportInfo(@RequestHeader("token") String sid, 
			@ApiParam(value = "举报信息ID", required = true) @RequestParam("dmID") Long dmID,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //添加
            ShopReport shopReport = shopReportService.findVo(dmID, null);
            RespHandler.respOK(shopReport,response);
        } catch (ServiceException se) {
            log.error("获取用户举报信息出错！", se);
            RespHandler.respError(RespMsg.getReport_error, response);
        } catch (Exception e) {
            log.error("获取用户举报信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
	/**
	 * 获取一页举报信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param shopReport
	 *            用户ID* @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "getList", method = RequestMethod.GET)
	public void getList(@RequestHeader("token") String sid, 
			ShopReport shopReport,
            @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //转换参数
            ShopReportPage page = new ShopReportPage();
            page.setUserId(shopReport.getUserId());
            page.setShopId(shopReport.getShopId());
            page.setReprotType(shopReport.getReprotType());
            page.setMobile(shopReport.getMobile());
            page.setNowPage(pageNum);
            page.setPageSize(pageSize);
            page.setOrderField("create_time");
            page.setOrderDirection(false);
            //获取数据
            Pager<ShopReport, Long> result = shopReportService.query(page, ShopReport.class);
            RespHandler.respOK(result,response);
        } catch (ServiceException se) {
            log.error("批量获取用户举报信息出错！", se);
            RespHandler.respError(RespMsg.getReport_error, response);
        } catch (Exception e) {
            log.error("批量获取用户举报信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
	/**
	 * 删除举报信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param shopReport
	 *            dmID* @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "delReport", method = RequestMethod.POST)
	public void delReport(@RequestHeader("token") String sid, 
			@ApiParam(value = "举报信息ID", required = true) @RequestParam("dmID") Long dmID,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //删除
            shopReportService.delId(dmID);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("删除用户举报信息出错！", se);
            RespHandler.respError(RespMsg.delReport_error, response);
        } catch (Exception e) {
            log.error("删除用户举报信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	

    /**
     * 添加便利店时，数据验证
     *
     * @param shopInfo 便利店信息
     * @throws ServiceException
     */
    private void checkVo(ShopReport shopReport) throws ServiceException {
        if (StringUtils.isEmpty(shopReport.getShopName())) {
            throw new ServiceException(ExceptionCode.SHOP_NAME_IS_NULL, "便利店名称为空！");
        }
        if (null == shopReport.getShopId()) {
            throw new ServiceException(ExceptionCode.SHOP_ID_IS_NULL, "便利店ID为空！");
        }
        if (null == shopReport.getReprotType()) {
            throw new ServiceException("举报类型为空！");
        }
        if (StringUtils.isEmpty(shopReport.getMobile())) {
            throw new ServiceException("举报者联系电话为空！");
        }
        if (StringUtils.isEmpty(shopReport.getReportDetail())) {
            throw new ServiceException("举报详情为空！");
        }
    }

}
