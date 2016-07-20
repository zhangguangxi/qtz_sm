package com.qtz.sm.controller.goods;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mall.core.common.response.RespCode;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.controller.utils.UploadOssService;
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.supp.service.CsGysStockService;
import com.qtz.sm.supp.vo.CsGysStock;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

@RestController
@RequestMapping(value = "/gd/goods") 
public class GoodsController extends BaseController {

	@Autowired
	private GdGoodsService goodsService;
	
	@Autowired
	private GdGoodsTypePropertyService propService;
	
	@Autowired
	private CsGysStockService gysStockService;
	
	/**
	 * 【添加商品信息】
	 * @param sid 		凭证
	 * @param goodsJson 商品JSON	
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "添加商品信息",
            notes = "添加商品信息",
            position = 0)
	@RequestMapping(value = "addGoods", method = RequestMethod.POST, consumes = "application/json")
	public void addGoods(@RequestHeader("token") String sid, 
			@ApiParam(value = "商品JSON", required = true) @RequestBody String goodsJson, 
			HttpServletResponse response) throws IOException {
        try {
			log.info("添加商品JSON:"+goodsJson);
			if(StringUtils.isBlank(goodsJson)){
				log.error("添加商品JSON为空,供应商ID:"+sid);
				RespHandler.respError(RespMsg.add_goods_fail, response);
				return ;
			}
			//Long supplierId = Long.parseLong(sid);
			
			//登陆检查
			checkLogin(sid, response);
			User user = getUser(sid);
			Long supplierId = user.getCompanyDmId();
			
			GdGoodsOutJson goodsOut = JSON.parseObject(goodsJson, GdGoodsOutJson.class);
			
			goodsService.addGoods(goodsOut, supplierId);
			RespHandler.respOK(response);
		} catch (ServiceException e1) {
			log.error("添加商品详情失败", e1);
			RespHandler.respError(RespMsg.add_goods_fail, response);
		} catch (ActionException e){
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}
	
	/**
	 * 【编辑商品信息】
	 * @param sid 		凭证
	 * @param goodsJson 商品JSON	
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "编辑商品信息",
            notes = "编辑商品信息",
            position = 1)
	@RequestMapping(value = "editGoods", method = RequestMethod.POST, consumes = "application/json")
	public void editGoods(@RequestHeader("token") String sid, 
			@ApiParam(value = "商品JSON", required = true) @RequestBody String goodsJson, 
			HttpServletResponse response, HttpServletRequest request) throws IOException {
        try {
        	// 获取登陆信息
			checkLogin(sid, response);
			User user = getUserJsonp(sid, response, request);
			
			log.info("编辑商品JSON:"+goodsJson);
			Long supplierId = user.getDmId();
			GdGoodsOutJson goodsOut = JSON.parseObject(goodsJson, GdGoodsOutJson.class);
			if (goodsOut == null) {
				log.error("编辑商品:上行JSON解析错误");
				RespHandler.respError(RespMsg.Goods.gd_goods_edit_json_error, response);
				return ;
			}
			
			int result = goodsService.modGoods(goodsOut, supplierId, user.getIp(), user.getCompanyDmId());
			if (result == 0) {
				RespHandler.respOK(response);
			} else if (result == RespCode.gd_goods_edit_json_error) {
				RespHandler.respError(RespMsg.Goods.gd_goods_edit_json_error, response);
			} else if (result == RespCode.gd_goods_edit_sku_combine_exsit) {
				RespHandler.respError(RespMsg.Goods.gd_goods_edit_sku_combine_exsit, response);
			}
			RespHandler.respOK(response);
		} catch (ServiceException e1) {
			log.error("编辑商品详情失败", e1);
			RespHandler.respError(RespMsg.edit_goods_fail, response);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}

	/**
	 * 【获取商品详情信息】
	 * @param sid  凭证
	 * @param dmId 商品ID
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "获取商品详情信息",
            notes = "获取商品详情信息",
            position = 2)
	@RequestMapping(value = "getGoodsById", method = RequestMethod.POST)
	public void getGoodsById(@RequestHeader("token") String sid, 
			@ApiParam(value = "商品ID", required = true) @RequestParam Long goodsId, 
			@ApiParam(value = "页面类型，1、商品详情页面面，2商品添加页面，3、商品编辑页", required = true) @RequestParam Long pageType, 
			HttpServletResponse response)
			throws IOException {
		try {
			// 获取登陆信息
			checkLogin(sid, response);
			
			//获取供应商商品SKU属性价格和库存信息
			Map<Long, Double> priceMap = new HashMap<Long, Double>();
			Map<Long, Integer> stockMap = new HashMap<Long, Integer>();
			
			//获取商品价格库存列表
			List<CsGysStock> stockList = gysStockService.getStocksByGoodsId(goodsId);
			if (stockList != null) {
				for (CsGysStock stock:stockList) {
					priceMap.put(stock.getSkuId(), stock.getPrice());
					stockMap.put(stock.getSkuId(), stock.getStockQuantity());
				}
			}
			boolean withPropConfigBoolean = false;
			boolean withAllSku = false;
			if (pageType != null) {	//1、商品详情页面面，2商品添加页面，3、商品编辑页
				if (pageType == 1) { 
					withPropConfigBoolean = false;
					withAllSku = false;
				} else if (pageType == 2) {
					withPropConfigBoolean = false;
					withAllSku = true;
				} else if (pageType == 3) {
					withPropConfigBoolean = true;
					withAllSku = false;
				}
			}
			
			GdGoodsOutJson goodsOutJson = goodsService.getGoodsDetail(goodsId, priceMap, stockMap, withPropConfigBoolean, withAllSku, null, null);
			SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse};  
			//String data = JSON.toJSONString(goodsOutJson);
			//RespHandler.respOK(data, response);
			
			JSONObject json=new JSONObject();
			json.put("code", 0);
			json.put("data", goodsOutJson);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			log.debug("<<<<<<<<调试输出日志:"+json.toString()+">>>>>>>");
			write.write(JSON.toJSONString(json, features));
			write.flush();
			write.close();
		} catch (ServiceException e) {
			log.error("获取商品详情失败", e);
			RespHandler.respError(RespMsg.not_found, response);
		}  catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}
	
	/**
	 * 【获取商品分类属性配置】
	 * @param sid 		凭证	
	 * @param categoryId 商品分类ID
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "获取商品分类属性配置",
            notes = "获取商品分类属性配置",
            position = 3)
	@RequestMapping(value = "getGoodsPropConfigs", method = RequestMethod.POST)
	public void getGoodsPropConfigs(@RequestHeader("token") String sid, 
			@ApiParam(value = "商品分类ID", required = true) @RequestParam Long goodsTypeId, 
			HttpServletResponse response) throws IOException {
		try {
			// 获取登陆信息
			checkLogin(sid, response);
						
			List<GdGoodsTypeProperty> basicPropConfigs = propService.getGoodsTypePropList(goodsTypeId, true, true, false, true, true);
			List<GdGoodsTypeProperty> skuPropConfigs = propService.getGoodsTypePropList(goodsTypeId, true, false, true, true, true);
			
			Map<String, List<GdGoodsTypeProperty>> propsMap = new HashMap<String, List<GdGoodsTypeProperty>>();
			propsMap.put("basicPropConfigs", basicPropConfigs);
			propsMap.put("skuPropConfigs", skuPropConfigs);
			
			RespHandler.respOK(propsMap, response);
		} catch (ServiceException e) {
			log.error("获取商品分类属性配置失败", e);
			RespHandler.respError(RespMsg.Goods.gd_get_prop_config_fail, response);
		}  catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}
	
	/**
	 * 【上传商品图片】
	 * @param sid 		凭证	
	 * @param file		
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "上传商品图片",
            notes = "上传商品图片",
            position = 3)
	@RequestMapping(value = "uploadGoodsImg", method = RequestMethod.POST)
	public void uploadGoodsImg(@ApiParam(value = "文件", required = true) @RequestParam(value = "file", required = true) MultipartFile file, 
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String fileName =  "";
		try {
			String token = request.getParameter("token");
			if(StringUtils.isNotBlank(token)){
				log.info("token_is================="+token);
			}
			// 获取登陆信息
			//checkLogin(token, response);
			
			fileName = file.getOriginalFilename();
			InputStream stream = file.getInputStream();
			
			String filePath = UploadOssService.uploadImgFile(fileName, stream);
			Map<String, String> fileMap = new HashMap<String, String>();
			fileMap.put("filePath", filePath);
			RespHandler.respOK(fileMap, response);
		} catch (IOException e) {
			log.error("上传文件失败，fileName = " + fileName, e);
			RespHandler.respError(RespMsg.Goods.gd_upload_img_fail, response);
		}  catch (Exception e) {
			log.error(e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}
}
