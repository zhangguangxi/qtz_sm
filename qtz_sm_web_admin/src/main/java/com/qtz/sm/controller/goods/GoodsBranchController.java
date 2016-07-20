package com.qtz.sm.controller.goods;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.service.GdGoodsBrandsService;
import com.qtz.sm.goods.vo.GdGoodsBrands;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/gd/goodsBranch")
public class GoodsBranchController extends BaseController {
	@Autowired
	private GdGoodsBrandsService gdBrandsService;

	/**
	 * 获取供应商的品牌
	 * @param sid 凭证
	 * @param dmId 供应商ID
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "获取供应商品牌",
            notes = "",
            position = 0)
	@RequestMapping(value = "getBranchsBySupplierId", method = RequestMethod.POST)
	public void getBranchsBySupplierId(@RequestHeader("token") String sid, @ApiParam(value = "供应商ID", required = true) @RequestParam long supplierId,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
			checkLogin(sid, response);
			
			List<GdGoodsBrands> brands = gdBrandsService.getBrandsList(supplierId);
			RespHandler.respOK(brands, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.add_goods_type_fail, response);
		} catch (ActionException e){
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}

}
