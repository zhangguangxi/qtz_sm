package com.qtz.sm.controller.goods;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespCode;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.model.GdGoodsCategroyRateBo;
import com.qtz.sm.goods.model.GdGoodsCategroyRateUpdateBo;
import com.qtz.sm.goods.service.GdGoodsCategroyRateService;
import com.qtz.sm.session.vo.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
* @Title: CategropRateController.java
* @Package com.qtz.sm.controller.goods
* @Description: 商品分类议价接口
* @author 欧江波 meoujb@163.com
* @date 2016年6月20日 下午5:15:12
* @version V1.0
 */
@Api(value = "categoryRate/", description = "分类溢价率")
@RestController
@RequestMapping(value = "/gd/categoryRate")
public class CategropRateController extends BaseController {

	@Autowired
	private GdGoodsCategroyRateService rateService;
	
	
	@ApiOperation(value = "批量编辑分类溢价率",
            notes = "编辑分类溢价率",
            position = 0)
	@RequestMapping(value = "updateTree", method = RequestMethod.POST)
	public void updateTree(@RequestHeader("token") String token, 
			@ApiParam("分类溢价率集合") @RequestBody List<GdGoodsCategroyRateUpdateBo> categropRates, 
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		try {
			// 获取登陆信息
			checkLogin(token, response);
			User user = getUserJsonp(token, response, request);
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
			rateService.updateTree(categropRates, companyType);
			RespHandler.respOK(response);
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}
	
	
	@ApiOperation(value = "获取分类溢价树列表",notes = "获取分类溢价树列表", position = 5)
	@RequestMapping(value = "getTree", method = RequestMethod.POST)
	public void getTree(@RequestHeader("token") String token,
			HttpServletResponse response, HttpServletRequest request) throws IOException{
		try {
			// 获取登陆信息
			checkLogin(token, response);
			User user = getUserJsonp(token, response, request);
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
			List<GdGoodsCategroyRateBo> list = rateService.findTree(0L, companyType);
			RespJsonPHandler.respOK(list, response, request);
		} catch (ServiceException e) {
			log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}
	
}
