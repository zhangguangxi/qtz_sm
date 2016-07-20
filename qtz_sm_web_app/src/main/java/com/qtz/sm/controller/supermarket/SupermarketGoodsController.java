package com.qtz.sm.controller.supermarket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.store.service.CsCczxStockService;
import com.qtz.sm.supermarket.page.SupermarketAdvertisementPage;
import com.qtz.sm.supermarket.service.SupermarketAdvertisementService;
import com.qtz.sm.supermarket.service.SupermarketCategoryService;
import com.qtz.sm.supermarket.vo.SupermarketAdvertisement;
import com.qtz.sm.supermarket.vo.SupermarketCategoryVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsVo;
import com.qtz.sm.supermarket.vo.SupermarketHomegeVo;
import com.qtz.sm.supermarket.vo.SupermarketSkuStatus;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>Title:SupermarketGoodsController</p>
 * <p>Description:超市商品Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author yangwei
 * @version v1.0 2016-06-06
 */
@Api(value = "/supermarket/goodsAPP", description = "超市商品管理")
@Controller("SupermarketGoodsController")
@RequestMapping("/supermarket/goodsAPP")
public class SupermarketGoodsController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(SupermarketGoodsController.class);
    /**
     * 注入超市类目服务Service类
     */
    @Resource(name = "supermarketCategoryServiceImpl")
    private SupermarketCategoryService supermarketCategoryServiceImpl;
    
    /**
     * 注入超市广告位服务Service类
     */
    @Resource(name = "supermarketAdvertisementServiceImpl")
    private SupermarketAdvertisementService supermarketAdvertisementServiceImpl;
    
    /**
     * 注入仓储中心库存服务Service类
     */
//    @Resource(name = "csCczxStockServiceImpl")
//    private CsCczxStockService csCczxStockServiceImpl;
    
    /**
     * 查询超市首页信息
     *
     * @param token    token
     * @param shopId   ID
     * @param pageNum  页面游标
     * @param pageSize 页面大小
     * @param request  request
     * @param response response
     */
    @ApiOperation(value = "查询超市首页信息",
            notes = "查询超市首页信息",
            position = 0)
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public void getList(
    		@ApiParam("经度") @RequestParam Double latitude,
    		@ApiParam("纬度") @RequestParam Double longitude,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            List<SupermarketCategoryVo> categoryList = supermarketCategoryServiceImpl.getAll(latitude,longitude);
            SupermarketAdvertisementPage page = new SupermarketAdvertisementPage();
            page.setPageSize(6);
            Pager<SupermarketAdvertisement,Long> adList = supermarketAdvertisementServiceImpl.query(page, null);
            SupermarketHomegeVo result = new SupermarketHomegeVo();
            result.setAdList(adList.getList());
            result.setCategoryList(categoryList);
            RespJsonPHandler.respOK(result, response, request);
        } catch (ServiceException se) {
            log.error("查询超市首页信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市首页信息出现系统错误.", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 查询超市商品规格信息(超市首页使用)
     *
     * @param cczxId    仓储中心ID
     * @param goodsId   商品ID
     * @param request  request
     * @param response response
     */
    @ApiOperation(value = "查询超市商品规格信息(超市首页使用)",
            notes = "查询超市商品规格信息(超市首页使用)",
            position = 1)
    @RequestMapping(value = "/getGoodsSpecifications", method = RequestMethod.GET)
    public void getGoodsSpecifications(
    		 @ApiParam(value = "仓储中心ID", required = true) @RequestParam("cczxId") Long cczxId,
             @ApiParam(value = "商品ID") @RequestParam("goodsId") Long goodsId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            SupermarketGoodsVo goodsDetail = supermarketCategoryServiceImpl.getGoodsSpecifications(cczxId,goodsId);
            SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse};  
			
			JSONObject json=new JSONObject();
			json.put("code", 0);
			json.put("data", goodsDetail);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			write.write(JSON.toJSONString(json, features));
			write.flush();
			write.close();
        } catch (ServiceException se) {
            log.error("查询超市商品规格信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市商品规格信息出现系统错误.", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 查询超市商品规格信息
     *
     * @param cczxId    仓储中心ID
     * @param goodsId   商品ID
     * @param request  request
     * @param response response
     */
    @ApiOperation(value = "查询超市商品规格信息",
            notes = "查询超市商品规格信息",
            position = 2)
    @RequestMapping(value = "/getGoodsDetail", method = RequestMethod.GET)
    public void getGoodsDetail(
    		 @ApiParam(value = "仓储中心ID", required = true) @RequestParam("cczxId") Long cczxId,
             @ApiParam(value = "商品ID") @RequestParam("goodsId") Long goodsId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            SupermarketGoodsVo goodsDetail = supermarketCategoryServiceImpl.getGoodsDetail(cczxId,goodsId);
            SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse};  
			
			JSONObject json=new JSONObject();
			json.put("code", 0);
			json.put("data", goodsDetail);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			write.write(JSON.toJSONString(json, features));
			write.flush();
			write.close();
        } catch (ServiceException se) {
            log.error("查询超市商品规格信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市商品规格信息出现系统错误.", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 查询超市商品库存信息
     *
     * @param cczxId    仓储中心ID
     * @param skuIds    skuID字符串
     * @param request  request
     * @param response response
     */
    @ApiOperation(value = "查询超市商品库存信息",
            notes = "查询超市商品库存信息",
            position = 3)
    @RequestMapping(value = "/getGoodsStatus", method = RequestMethod.GET)
    public void getGoodsStatus(
    		 @ApiParam(value = "仓储中心ID", required = true) @RequestParam("cczxId") Long cczxId,
             @ApiParam(value = "skuID字符串") @RequestParam("skuIds") String skuIds,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            if(cczxId==null||com.alibaba.dubbo.common.utils.StringUtils.isEmpty(skuIds))
            {
            	 log.error("参数格式有误！");
            	 RespHandler.respError(RespMsg.illegalParameter_error, response);
            }
//            List<SupermarketSkuStatus> result = csCczxStockServiceImpl.getGoodsStock(cczxId,skuIds);
            List<SupermarketSkuStatus> result = null;
            RespJsonPHandler.respOK(result, response, request);
//        } catch (ServiceException se) {
//            log.error("查询超市商品库存信息出错！", se);
//            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市商品库存信息出现系统错误.", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 查询可能喜欢的商品信息
     *
     * @param cczxId    仓储中心ID
     * @param request  request
     * @param response response
     */
    @ApiOperation(value = "查询超市商品库存信息",
            notes = "查询超市商品库存信息",
            position = 4)
    @RequestMapping(value = "/getEnjoyGoodsList", method = RequestMethod.GET)
    public void getEnjoyGoodsList(
    		 @ApiParam(value = "仓储中心ID", required = true) @RequestParam("cczxId") Long cczxId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
        	List<SupermarketGoodsVo> result = supermarketCategoryServiceImpl.getEnjoyGoodsList(cczxId);
            RespJsonPHandler.respOK(result, response, request);
        } catch (ServiceException se) {
            log.error("查询超市商品库存信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市商品库存信息出现系统错误.", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

}