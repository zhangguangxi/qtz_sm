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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.DateUtil;
import com.mall.core.common.enums.YesOrNoEnum;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ActionException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.goods.enums.DisableEnum;
import com.qtz.sm.goods.model.GdGoodsTypeOutJson;
import com.qtz.sm.goods.page.GdGoodsTypePage;
import com.qtz.sm.goods.service.GdGoodsTypePropertyOptionService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.service.GdGoodsTypeService;
import com.qtz.sm.goods.vo.GdGoodsType;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;
import com.qtz.sm.session.vo.User;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

@RestController
@RequestMapping("/gd/goodsType")
public class GoodsTypeController extends BaseController {
	@Autowired
	private GdGoodsTypeService gdGoodsTypeService;
	@Autowired
	private GdGoodsTypePropertyService gdTypeProesService;
	@Autowired
	private GdGoodsTypePropertyOptionService gdTypeProOptionService;

	/**
	 * 查询商品分类列表<br/>
	 * @param sid 凭证
	 * @param pageIndex 当前页
	 * @param pageSize 页大小
	 * @param name 分类名称
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "查询商品分类列表",
            notes = "查询商品分类列表",
            position = 0)
	@RequestMapping(value = "listGoodsTypesByPage", method = RequestMethod.POST)
	public void listGoodsTypesByPage(@RequestHeader("token") String sid,
			@ApiParam(value = "当前页", required = true) @RequestParam(defaultValue = "1") Integer pageIndex, 
			@ApiParam(value = "页大小", required = true) @RequestParam(defaultValue = "20") Integer pageSize,
			@ApiParam(value = "搜索关键字", required = false) @RequestParam(required = false) String name, 
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		try {
			checkLogin(sid, response);
						
			GdGoodsTypePage page = new GdGoodsTypePage();
			if (StringUtils.isNotBlank(name)){
				page.setNameOrPinyinLike(name);
			}
			page.setNowPage(pageIndex); 
			page.setPageSize(pageSize);
			page.setOrderField(GdGoodsType.CREATEON);
			page.setOrderDirection(false);
			Pager<GdGoodsType, Long> pager = gdGoodsTypeService.query(page, GdGoodsType.class);
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(RespMsg.goods_type_get_fail, response, request);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}

	/**
	 * 获取父分类下的直接商品分类
	 * @param sid 凭证
	 * @param parentId 父分类ID
	 * @param response
	 * @param request
	 */
	@ApiOperation(value = "获取父分类下的直接商品分类",
            notes = "获取父分类下的直接商品分类",
            position = 1)
	@RequestMapping(value = "listGoodsTypesByParentId", method = RequestMethod.POST)
	public void listGoodsTypesByParentId(@RequestHeader("token") String sid, 
			@ApiParam(value = "父分类ID", required = true) @RequestParam Long parentId,
			HttpServletResponse response, HttpServletRequest request) 
			throws IOException{
		try {
			checkLogin(sid, response);
			
			List<GdGoodsType> goodsTypes = gdGoodsTypeService.listGoodsTypesByParentId(parentId);
			RespJsonPHandler.respOK(goodsTypes, response, request);
		} catch (ServiceException e) {
			log.error(e);
			RespJsonPHandler.respError(RespMsg.goods_type_get_fail, response, request);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}

	/**
	 * 根据ID获取商品分类信息
	 * @param sid 凭证
	 * @param dmId 商品分类ID
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "根据ID获取商品分类信息",
            notes = "根据ID获取商品分类信息",
            position = 2)
	@RequestMapping(value = "getGoodsTypeById", method = RequestMethod.POST)
	public void getGoodsTypeById(@RequestHeader("token") String sid, 
			@ApiParam(value = "商品分类ID", required = true) @RequestParam Long dmId,
			HttpServletResponse response) throws IOException {
		try {
			checkLogin(sid, response);
			
			GdGoodsType gdType = gdGoodsTypeService.findVo(dmId, null);
			//获取父分类
			String[] parentTypeStrs = gdGoodsTypeService.getGoodsTypeStrs(dmId, ",");
			String pNameChain = parentTypeStrs[1];
			if (parentTypeStrs[1].contains(",")) {
				pNameChain = StringUtils.substring(parentTypeStrs[1], 0, parentTypeStrs[1].lastIndexOf(","));
			} else {
				pNameChain = "";
			}
			gdType.setParentNameChain(pNameChain);
			
			// 基本属性
			GdGoodsTypeProperty proesKey = new GdGoodsTypeProperty();
			proesKey.setStatus(DisableEnum.enable.getValue());
			proesKey.setIsKey(YesOrNoEnum.YES.getValue());
			proesKey.setGoodsTypeId(dmId);
			List<GdGoodsTypeProperty> gdTypeProesKey = gdTypeProesService.findList(proesKey);

			if (gdTypeProesKey != null && gdTypeProesKey.size() > 0) {
				for (int i = 0; i < gdTypeProesKey.size(); i++) {
					GdGoodsTypeProperty pro = gdTypeProesKey.get(i);
					GdGoodsTypePropertyOption op = new GdGoodsTypePropertyOption();
					op.setGoodsTypeProId(pro.getDmId());
					op.setStatus(DisableEnum.enable.getValue());
					List<GdGoodsTypePropertyOption> options = gdTypeProOptionService.findList(op);
					pro.setOptions(options);
				}
			}
			gdType.setGdTypeProesKey(gdTypeProesKey);

			// 销售属性
			GdGoodsTypeProperty proesSale = new GdGoodsTypeProperty();
			proesSale.setStatus(DisableEnum.enable.getValue());
			proesSale.setIsSale(YesOrNoEnum.YES.getValue());
			proesSale.setGoodsTypeId(dmId);
			List<GdGoodsTypeProperty> gdTypeProesSale = gdTypeProesService.findList(proesSale);

			if (gdTypeProesSale != null && gdTypeProesSale.size() > 0) {
				for (int i = 0; i < gdTypeProesSale.size(); i++) {
					GdGoodsTypeProperty pro = gdTypeProesSale.get(i);
					GdGoodsTypePropertyOption op = new GdGoodsTypePropertyOption();
					op.setGoodsTypeProId(pro.getDmId());
					op.setStatus(DisableEnum.enable.getValue());
					List<GdGoodsTypePropertyOption> options = gdTypeProOptionService.findList(op);
					pro.setOptions(options);
				}
			}
			gdType.setGdTypeProesSale(gdTypeProesSale);
			RespHandler.respOK(gdType, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.goods_type_get_fail, response);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
	}

	/**
	 * 
	 * 添加商品分类
	 * @author  guangxi.zhang
	 * @param   sid                凭证
	 * @param   goodsTypeJson      商品分类信息JSON
	 * @param   response
	 * @throws  IOException
	 */
	@ApiOperation(value = "添加商品分类",
            notes = "",
            position = 3)
   @RequestMapping(value="addGoodstype",method=RequestMethod.POST,consumes="application/json")
    public void addGoodstype(@RequestHeader("token")String sid,
    		@ApiParam(value = "商品分类信息JSON", required = true) @RequestBody String goodsTypeJson,
    		HttpServletResponse response, HttpServletRequest request) throws IOException{
        try {
        	  // 获取登陆信息
  			  checkLogin(sid, response);
  			  User user = getUserJsonp(sid, response, request);
  			  Long supplierChainId = user.getDmId();
  			  
              log.info("addGoodstype>>>>>>商品分类JSON:"+goodsTypeJson);
              GdGoodsTypeOutJson goodsTypeOut = JSON.parseObject(goodsTypeJson,GdGoodsTypeOutJson.class);
          	
              gdGoodsTypeService.addGoodsType(supplierChainId,goodsTypeOut);
              RespHandler.respOK(response);
        }catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		}
        catch (Exception e) {
            log.error("addGoodstype>>>>>>添加商品分类失败!",e);
            RespHandler.respError(RespMsg.add_goods_type_fail, response);
        } 
    }
	
	
   /**
    * 修改商品分类信息
    * @param sid           凭证
    * @param goodsTypeJson 商品分类信息JSON
    * @param response
    */
	@ApiOperation(value = "修改商品分类",
            notes = "",
            position = 4)
	@RequestMapping(value = "updateGoodsType", method = RequestMethod.POST, consumes = "application/json")
	public void updateGoodsType(@RequestHeader("token") String sid, 
			@ApiParam(value = "商品分类信息JSON", required = true) @RequestBody GdGoodsType goodsType,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		try {
			 // 获取登陆信息
		    checkLogin(sid, response);
		    User user = getUserJsonp(sid, response, request);
		    Long supplierChainId = user.getDmId();
			log.info("updateGoodsType>>>>>>商品分类JSON:"+goodsType);
			/*
		    log.info("updateGoodsType>>>>>>商品分类JSON:"+goodsTypeJson);
		    
		    JSONObject goodsTypeJsonObj = JSON.parseObject(goodsTypeJson);
		    Long createBy = goodsTypeJsonObj.getLongValue("createBy");
		    Long createOn = goodsTypeJsonObj.getLongValue("createOn");
		    Long dmId = goodsTypeJsonObj.getLongValue("dmId");
		    Integer level = goodsTypeJsonObj.getInteger("level");
		    Long parentId = goodsTypeJsonObj.getLongValue("parentId");
		    String name  = goodsTypeJsonObj.getString("name");
		    String pinyin  = goodsTypeJsonObj.getString("pinyin");
		    Integer seq  = goodsTypeJsonObj.getInteger("seq");
		    Integer status  = goodsTypeJsonObj.getInteger("status");
		    Long updateBy = goodsTypeJsonObj.getLongValue("updateBy");
		    Long updateOn = goodsTypeJsonObj.getLongValue("updateOn");
		    
		    JSONArray saleArray = goodsTypeJsonObj.getJSONArray("gdTypeProesSale");
		    JSONArray basicArray = goodsTypeJsonObj.getJSONArray("gdTypeProesKey");
		    
		    List<GdGoodsTypeProperty> basicProps = parseGoodsPropJson(basicArray);
		    List<GdGoodsTypeProperty> saleProps = parseGoodsPropJson(saleArray);
		    
		    GdGoodsType goodsType = new GdGoodsType(name, pinyin, parentId, level, createOn, createBy, updateBy, updateOn);
		    goodsType.setPinyin(pinyin);
		    goodsType.setSeq(seq);
		    goodsType.setStatus(status);
		    goodsType.setDmId(dmId);
		    goodsType.setGdTypeProesSale(saleProps);
		    goodsType.setGdTypeProesKey(basicProps);
		    */
		  
			gdGoodsTypeService.updateGoodsType(supplierChainId, goodsType);
			RespHandler.respOK(response);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		} catch (ServiceException e) {
			log.error("updateGoodsType>>>>>>更新上分类失败!",e);
			RespHandler.respError(RespMsg.mod_goods_type_fail, response);
		}
	}

	/**
	 * 将商品属性JSON转换成对象
	 * @param propArray 商品属性JSON
	 * @return
	 */
	private List<GdGoodsTypeProperty> parseGoodsPropJson(JSONArray propArray) {
		List<GdGoodsTypeProperty> saleProps = new ArrayList<GdGoodsTypeProperty>();
		if (propArray == null || propArray.isEmpty()) {
			return saleProps;
		}
		for (int i =0; i<propArray.size(); i++) {
			JSONObject propObj = propArray.getJSONObject(i);
			if (propObj == null) {
				continue;
			}
			Integer dataType = propObj.getInteger("dataType");
			Long propDmId = propObj.getLong("dmId");
			Long goodsTypeId = propObj.getLong("goodsTypeId");
			Integer isKey = propObj.getInteger("isKey");
			Integer isSale = propObj.getInteger("isSale");
			Integer required = propObj.getInteger("required");
			Integer status = propObj.getInteger("status");
			String name = propObj.getString("name");
			String code = propObj.getString("code");
			JSONArray optionsArray = propObj.getJSONArray("options");
			
			//属性值
			List<GdGoodsTypePropertyOption> options = new ArrayList<GdGoodsTypePropertyOption>();
			if (optionsArray != null && !optionsArray.isEmpty()) {
				for (int j =0; j<optionsArray.size(); j++) {
					JSONObject propOptionObj = optionsArray.getJSONObject(j);
					if (propOptionObj == null) {
						continue;
					}
					Long propOptionDmId = propOptionObj.getLong("dmId");
					Long goodsTypeProId = propOptionObj.getLong("goodsTypeProId");
					String val = propOptionObj.getString("val");
					Integer optionStatus = propOptionObj.getInteger("status");
					
					GdGoodsTypePropertyOption option = new GdGoodsTypePropertyOption();
					option.setGoodsTypeProId(goodsTypeProId);
					option.setVal(val);
					option.setStatus(optionStatus);
					option.setDmId(propOptionDmId);
					
					options.add(option);
				}
			}
			
			GdGoodsTypeProperty prop = new GdGoodsTypeProperty(goodsTypeId, name, dataType, isSale, isKey, required, status);
			prop.setCode(code);
			prop.setDmId(propDmId);
			prop.setOptions(options);
    		saleProps.add(prop);
		}
		return saleProps;
	}
   
	/**
	 * 修改商品分类状态
	 * @param sid 凭证
	 * @param dmId 商品分类ID
	 * @param response
	 * @throws IOException
	 */
	@ApiOperation(value = "修改商品分类状态",
            notes = "",
            position = 5)
	@RequestMapping(value = "updateGoodsTypeStatus", method = RequestMethod.POST)
	public void updateGoodsTypeStatus(@RequestHeader("token") String sid, 
			@ApiParam(value = "商品分类ID", required = true) @RequestParam long dmId,
			HttpServletResponse response) throws IOException {
		try {
			checkLogin(sid, response);
			
			User user = getUser(sid);
			GdGoodsType gtType = gdGoodsTypeService.findVo(dmId, null);
			if (gtType == null) {
				RespHandler.respError(RespMsg.goods_type_not_exsist, response);
				return;
			}
			int statusTmp = gtType.getStatus();
			gtType.setStatus(statusTmp == DisableEnum.enable.getValue() ? DisableEnum.disable.getValue(): DisableEnum.enable.getValue());
			gtType.setUpdateBy(user.getDmId());
			gtType.setUpdateOn(DateUtil.getTimeInSeconds());
			gdGoodsTypeService.modVo(gtType);
			RespHandler.respOK(response);
		} catch (ActionException e) {
			log.error("尚未登录", e);
			RespHandler.respError(RespMsg.user_not_login, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respError(RespMsg.mod_goods_type_fail, response);
		}
	}
}
