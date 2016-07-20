package com.qtz.sm.controller.batch;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.ProcessingCheckExceptionUtil;
import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.batch.page.CsBatOrderPage;
import com.qtz.sm.batch.service.CsBatOrderService;
import com.qtz.sm.batch.vo.CsBatGoods;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
import com.qtz.sm.batch.vo.CsBatOrder;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.page.GdGoodsPage;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.shop.vo.ShopValueVo;
import com.qtz.sm.store.service.CsCczxAddressService;
import com.qtz.sm.store.vo.CsCczxAddress;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author Administrator
 *
 */
@Api(value = "batch/", description = "批发单控制类")
@RestController
@RequestMapping(value = "batch/")
public class BatchController extends BaseController {


	@Autowired
	private CsBatOrderService csBatOrderService;
	@Autowired
	private GdGoodsService gdGoodsService;
	
	/**
	 * 批发单列表
	 * 
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "批发单列表",
            notes = "批发单列表",
            position = 0)
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(@RequestHeader("token") String token,@ApiParam("字段名称") @RequestParam(required=false) String columnName,
    		@ApiParam("字段值") @RequestParam(required=false) String columnValue,
    		@ApiParam("状态") @RequestParam(required=false) Integer status, 
    		@ApiParam("开始时间") @RequestParam(required=false) Long startTimeStamp, @ApiParam("结束时间") @RequestParam(required=false) Long endTimeStamp,
    		@ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex, @ApiParam("行数") @RequestParam(defaultValue = "20") Integer pageSize,
			HttpServletResponse response, HttpServletRequest request) {
		//取得树形结构
		try {
			CsBatOrderPage page = new CsBatOrderPage();
			page.setNowPage(pageIndex);
			page.setPageSize(pageSize);
			page.setStatus(status);
//			page.setCreateTime(minCreateTime);
//			page.setCreateTime(maxCreateTime);
			Pager<CsBatOrder, Long> pager = this.csBatOrderService.query(page, CsBatOrder.class);
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 批发单详情
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "批发单详情",
            notes = "批发单详情",
            position = 0)
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public void detail(@RequestHeader("token") String token,@RequestParam Long dmId,HttpServletResponse response, HttpServletRequest request) {
		//取得树形结构
		try {
			
			CsBatOrder csBatOrder = this.csBatOrderService.findVo(dmId, new CsBatOrder());
			RespJsonPHandler.respOK(csBatOrder, response, request);
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	/**
	 * 新增批发单
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "新增批发单",
            notes = "新增批发单",
            position = 0)
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public void add(@RequestHeader("token") String token,@ModelAttribute("csBatOrder") CsBatOrder csBatOrder,@RequestParam String goodsArrayJson,HttpServletResponse response, HttpServletRequest request) {
		try {

//			Map<String,Object> map = JSONArray.parseObject(csBatOrderJson,HashMap.class);
//			CsBatOrder csBatOrder = new CsBatOrder();
//			csBatOrder.setDmId(Long.parseLong(map.get("dmId").toString()));
//			csBatOrder.setStatus(Integer.parseInt(map.get("status").toString()));
			this.csBatOrderService.addVo(csBatOrder);

			//解析goodsArrayJson获得CsBatGoods与CsBatGoodsSku的数据
			Map<String,Object> map = JSONArray.parseObject(goodsArrayJson,HashMap.class);
			
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	
	
	/**
	 * 批发单状态操作
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "批发单状态操作",
            notes = "批发单状态操作",
            position = 0)
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public void update(@RequestHeader("token") String token,@ModelAttribute("csBatOrder") CsBatOrder csBatOrder,HttpServletResponse response, HttpServletRequest request) {
		//取得树形结构
		try {

//			Map<String,Object> map = JSONArray.parseObject(csBatOrderJson,HashMap.class);
//			CsBatOrder csBatOrder = new CsBatOrder();
//			csBatOrder.setDmId(Long.parseLong(map.get("dmId").toString()));
//			csBatOrder.setStatus(Integer.parseInt(map.get("status").toString()));
			this.csBatOrderService.modVoNotNull(csBatOrder);
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
	}
	/**
	 * 批发单列表
	 * 
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "批发单列表",
            notes = "批发单列表",
            position = 0)
	@RequestMapping(value = "getList", method = RequestMethod.GET)
	public void getList(@RequestHeader("token") String token,
			@ApiParam("批发单编号") @RequestParam(required=false) Long orderId,
    		@ApiParam("收货人姓名") @RequestParam(required=false) String reciever,
    		@ApiParam("电话") @RequestParam(required=false) String contactPhone,
    		@ApiParam("收货地址") @RequestParam(required=false) String fullAddress,
    		@ApiParam("状态") @RequestParam(required=false) Integer status, 
    		@ApiParam("开始时间") @RequestParam(required=false) Long startTimeStamp, @ApiParam("结束时间") @RequestParam(required=false) Long endTimeStamp,
    		@ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex, @ApiParam("行数") @RequestParam(defaultValue = "20") Integer pageSize,
			HttpServletResponse response, HttpServletRequest request) {
		//取得树形结构
		try {
			CsBatOrderPage page = new CsBatOrderPage();
			page.setDmId(orderId);
			page.setReciever(reciever);
			page.setContactPhone(contactPhone);
			page.setFullAddress(fullAddress);
			page.setNowPage(pageIndex);
			page.setPageSize(pageSize);
			page.setStatus(status);
			page.setStartTime(startTimeStamp);
			page.setEndTime(endTimeStamp);
			Pager<CsBatOrder, Long> pager = this.csBatOrderService.query(page, CsBatOrder.class);
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException e) {
            log.error(e);
            RespHandler.respError(RespMsg.get_batOrder_list_error, response);
		}
	}
	
	/**
	 * 获取批发单详情
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "获取批发单详情",
            notes = "获取批发单详情",
            position = 0)
	@RequestMapping(value = "getOrderdetail", method = RequestMethod.GET)
	public void getOrderdetail(@RequestHeader("token") String token,
			@ApiParam("批发单编号") @RequestParam(required=true) Long dmId,HttpServletResponse response, HttpServletRequest request) {
		//取得树形结构
		try {
			// 登陆检查
			checkLogin(token, response);
			CsBatOrder csBatOrder = this.csBatOrderService.getOrderDetail(dmId);
			RespJsonPHandler.respOK(csBatOrder, response, request);
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (Exception e) {
			log.error("获取批发单详情出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}
	
	
	/**
	 * 修改批发单状态操作
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "修改批发单状态操作",
            notes = "修改批发单状态操作",
            position = 0)
	@RequestMapping(value = "updateStatus", method = RequestMethod.POST)
	public void updateStatus(@RequestHeader("token") String token,@ApiParam(value = "批发单dmId", required = true) @RequestParam Long dmId,
			@ApiParam(value = "需要修改批发单的状态", required = true) @RequestParam Integer status,
			@ApiParam(value = "物流公司")  String logisticsCompany,
			@ApiParam(value ="快递单号")  String logisticsNumber,HttpServletResponse response, HttpServletRequest request) {
		//取得树形结构
		try {
			// 登陆检查
			checkLogin(token, response);
			CsBatOrder csBatOrder = new CsBatOrder();
			csBatOrder.setDmId(dmId);
			csBatOrder.setStatus(status);
			csBatOrder.setLogisticsCompany(logisticsCompany);
			csBatOrder.setLogisticsNumber(logisticsNumber);
			checkParam(csBatOrder);
			this.csBatOrderService.updateBatOrder(csBatOrder);
			RespJsonPHandler.respOK(response, request);
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (Exception e) {
			log.error("修改批发单状态操作出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}
	
	private void checkParam(CsBatOrder csBatOrder)throws ServiceException {
		if(csBatOrder.getStatus().intValue()==2)
		{
			if(StringUtil.isEmpty(csBatOrder.getLogisticsCompany()))
			{
				throw new ServiceException("物流公司名称不能为空");
			}
			if(StringUtil.isEmpty(csBatOrder.getLogisticsCompany()))
			{
				throw new ServiceException("物流公司名称不能为空");
			}
		}
		
	}

	/**
	 * 
	 * @Description:分页查询我要进货的商品列表
	 * @param token
	 * @param pageNum	页面游标
	 * @param pageSize	 页面大小
	 * @param request
	 * @param response
	 * void
	 * @exception:
	 * @author: Yangwei
	 * @time:2016年06月15日 
	 */
	@ApiOperation(value = "我要进货的商品列表", notes = "分页查询我要进货的商品列表", position = 1)
	@RequestMapping(value = "getGoodsPage", method = RequestMethod.GET)
	public void getGoodsPage(@RequestHeader("token") String token,
			@ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
			@ApiParam(value ="页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			// 登陆检查
			checkLogin(token, response);
			GdGoodsPage page = new GdGoodsPage();
			page.setNowPage(pageNum);
			page.setPageSize(pageSize);
			page.setOrderField("update_on");
			page.setOrderDirection(false);
			// 分页查询
			Pager<GdGoods,Long> gdGoodsPage= gdGoodsService.query(page, GdGoods.class);
			//根据商品ID 找出 所有sku 列表 
			if (gdGoodsPage.getList() != null && gdGoodsPage.getList().size() != 0) {
				for (int i = 0; i < gdGoodsPage.getList().size(); i++) {
					List<ShopValueVo> list = gdGoodsService.findSkuList(gdGoodsPage.getList().get(i).getDmId());
					gdGoodsPage.getList().get(i).setValList(list);
				}
			}
			RespJsonPHandler.respOK(gdGoodsPage, response, request);
		} catch (ServiceException se) {
			log.error("分页查询我要进货的商品列表出错！", se);
			RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
		} catch (Exception e) {
			log.error("分页查询我要进货的商品列表出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}
	
	/**
	 * 
	 * @Description:添加仓储中心批发单
	 * @param token
	 * @param CsBatOrder	仓储中心批发单信息
	 * @param result 
	 * @param request
	 * @param response
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月31日 下午6:24:24
	 */
	@ApiOperation(value = "添加仓储中心批发单", notes = "添加仓储中心批发单", position = 2)
	@RequestMapping(value = "addBatch", method = RequestMethod.POST)
	public void addBatch(@RequestHeader("token") String token,
			@ApiParam(value = "仓储中心批发单信息", required = true) @RequestBody CsBatOrder csBatOrder,
			BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (result.hasErrors()) {
				RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,
						ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
				return;
			} else {
				// 登陆检查
				checkLogin(token, response);

				// 订单状态 0：待受理，1：待配送，2：配送中，3：已完成
				csBatOrder.setStatus(0);
				// 信息填充
				Date date = new Date();
				csBatOrder.setCreateTime(date.getTime());

				// 添加订单
				csBatOrderService.addOrder(csBatOrder);
				RespHandler.respOK(response);
			}
		} catch (ServiceException se) {
			log.error("添加仓储中心批发单出错！", se);
			RespHandler.respError(RespMsg.add_batOrder_error, response);
		} catch (Exception e) {
			log.error("添加仓储中心批发单出现系统错误！", e);
			RespJsonPHandler.respServerError(response, request);
		}
	}
}
