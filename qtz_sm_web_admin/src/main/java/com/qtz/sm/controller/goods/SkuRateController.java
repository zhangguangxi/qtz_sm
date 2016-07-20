package com.qtz.sm.controller.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespCode;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.service.GdGoodsSkuRateService;
import com.qtz.sm.goods.service.GdGoodsSkuService;
import com.qtz.sm.goods.vo.GdGoodsSku;
import com.qtz.sm.session.vo.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>Title:SkuRateController</p>
 * <p>Description:商品SKU议价Action类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @reversion 欧江波 meoujb@163.com
 * @version v1.0 2016-05-19
 */
@Api(value = "/gd/skuRate", description = "SKU议价控制类")
@RestController
@RequestMapping(value = "/gd/skuRate")
public class SkuRateController extends BaseController {

	@Autowired
	private GdGoodsSkuRateService skuRateService;
	@Autowired
	private GdGoodsSkuService skuService;
	
	/**
	 * 
	 * @author 欧江波 meoujb@163.com
	 */
    @ApiOperation(value = "更新SKU议价信息", notes = "更新SKU议价信息",  position = 1)
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public void updatePrice(@RequestHeader("token") String token, 
    		@ApiParam(value = "SKUID", required=true) @RequestParam Long skuId, 
    		@ApiParam(value = "新价格", required=true) @RequestParam Double price, 
    		HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	// 获取登陆信息
			checkLogin(token, response);
			User user = getUserJsonp(token, response, request);
        	String ip = user.getIp();
        	//公司类型，参考CompanyType定义, 1:供应商，2：供应链，3：云仓储，4：仓储中心，5：便利店管理公司，6：便利店，7：超市，8：胖胖生活
        	Integer companyType = user.getCompanyType();
        	List<Integer> avaCompanies = new ArrayList<Integer>();
        	avaCompanies.add(CompanyType.SupplyChain.value());
        	avaCompanies.add(CompanyType.CloudStorage.value());
        	avaCompanies.add(CompanyType.StoreManager.value());
        	avaCompanies.add(CompanyType.SuperMarket.value());
        	if (!avaCompanies.contains(companyType)) {
        		log.info("该公司类型人员不能议价,CompanyType = " + companyType);
        		RespHandler.respException(RespCode._500,  "该公司类型人员不能议价,CompanyType = " + companyType, response);
        		return ;
        	}
        	
        	//查询商品ID
        	GdGoodsSku goodsSkuVo = skuService.findVo(skuId, null);
        	if (goodsSkuVo == null){
        		RespHandler.respException(RespCode._500, "SKU对应的商品不存在，skuId="+skuId, response);
        		return ;
        	}
        	skuRateService.modifyPrice(goodsSkuVo.getGoodsId(), skuId, price, companyType, ip, user.getDmId());
        	RespHandler.respOK(response);
        } catch (ServiceException e) {
            log.error("修改价格失败"+e);
            RespHandler.respException(RespCode._500, "修改价格失败", response);
        } catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
    }
    
    
    /**
	 * 商品库列表
	 * @param token
	 * @param status
	 * @param startTimeStamp
	 * @param endTimeStamp
	 * @param minPrice
	 * @param maxPrice
	 * @param sourceType
	 * @param pageIndex
	 * @param pageSize
	 * @param request
	 * @param response
	 * @throws IOException
	 */
//	@Deprecated
//	@RequestMapping(value = "listGoodsStock", method = RequestMethod.GET)
//	public void listGoodsStock(@RequestHeader("token") String token, @ApiParam("状态") @RequestParam(required=false) Integer status,
//			@ApiParam("开始时间") @RequestParam(required=false) Long startTimeStamp, @ApiParam("结束时间") @RequestParam(required=false) Long endTimeStamp,
//			@ApiParam("最低价") @RequestParam(required=false) Long minPrice,@ApiParam("最高价") @RequestParam(required=false) Long maxPrice,
//			@ApiParam("议价类型 0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市议价；") @RequestParam Integer rateType,
//			@ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex,@ApiParam("行数") @RequestParam(defaultValue = "20") Integer pageSize,
//			HttpServletRequest request, HttpServletResponse response) throws IOException {
//		try {
//			User user = getUserJsonp(token, response, request);
//	        if (null == user) {
//	            return;
//	        }
//	        //商品库列表
//	        GdGoodsStockPage page = new GdGoodsStockPage();
//			page.setNowPage(pageIndex);
//			page.setPageSize(pageSize);
//			page.setRateType(rateType);
//			page.setEndTimeStamp(endTimeStamp);
//			page.setStartTimeStamp(startTimeStamp);
//			page.setMaxPrice(maxPrice);
//			page.setMinPrice(minPrice);
//			page.setIsOnsale(status);
//			Pager<GdGoodsStock, Long> pager = this.skuRateService.listGoodsStock(page);
//			
//			RespJsonPHandler.respOK(pager, response, request);
//	        
//		} catch (ServiceException e) {
//            log.error(e);
//            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
//        }
//	}
	
}
