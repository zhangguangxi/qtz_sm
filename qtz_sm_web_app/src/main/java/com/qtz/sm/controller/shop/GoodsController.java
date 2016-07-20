package com.qtz.sm.controller.shop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shop.page.ShopCategoryPage;
import com.qtz.sm.shop.service.ShopCategoryService;
import com.qtz.sm.shop.service.ShopGoodsService;
import com.qtz.sm.shop.vo.ShopCategory;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopGoodsVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:GoodsController</p>
 * <p>Description:便利店商品Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Api(value = "/shop/goodsAPP", description = "便利店商品管理")
@Controller("ShopGoodsController")
@RequestMapping("/shop/goodsAPP")
public class GoodsController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(GoodsController.class);
    /**
     * 注入便利店商品Service类
     */
    @Resource(name = "shopGoodsServiceImpl")
    private ShopGoodsService shopGoodsService;
    
    /**
     * 注入便利店分类Service类
     */
    @Resource(name = "shopCategoryServiceImpl")
    private ShopCategoryService shopCategoryService;
    
    /**
     * 查询便利店一页商品信息(基本信息)
     *
     * @param token     token
     * @param shopId    便利店ID
     * @param shopGoods 查询商品信息
     * @param pageNum   页面游标
     * @param pageSize  页面大小
     * @param request   request
     * @param response  response
     */
    @ApiOperation(value = "查询便利店一页商品信息(基本信息)",
            notes = "查询便利店一页商品信息(基本信息)",
            position = 1)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void queryPage(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                          @ApiParam("便利店查询商品信息") @RequestParam("shopGoods") ShopGoods shopGoods,
                          @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            //TODO  查询方法尚未提供
            RespHandler.respError(RespMsg.not_found, response);
        } catch (ServiceException se) {
            log.error("查询便利店一页商品信息(基本信息)失败.", se);
            //TODO 错误码尚未提供
            RespHandler.respError(RespMsg.shop_business_query_id, response);
        } catch (Exception e) {
            log.error("查询便利店一页商品信息(基本信息)出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 通过便利店分类ID查询一页便利店商品信息(基本信息)
     *
     * @param token          token
     * @param shopCategoryId 便利店分类ID
     * @param request        request
     * @param response       response
     */
    @ApiOperation(value = "通过便利店分类ID查询一页便利店商品信息(基本信息)",
            notes = "通过便利店分类ID查询一页便利店商品信息(基本信息)",
            position = 2)
    @RequestMapping(value = "/shopCategoryId", method = RequestMethod.GET)
    public void queryByShopCategory(@RequestHeader("token") String token,
                                    @ApiParam(value = "便利店分类ID", required = true) @RequestParam("shopCategoryId") Long shopCategoryId,
                                    HttpServletRequest request,
                                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            //TODO  查询方法尚未提供
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("通过便利店分类ID查询一页便利店商品信息(基本信息)失败.", se);
            //TODO 错误码尚未提供
            RespHandler.respError(RespMsg.shop_business_query_id, response);
        } catch (Exception e) {
            log.error("通过便利店分类ID查询一页便利店商品信息(基本信息)出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 批量或单个修改便利店商品便利店内部分类
     *
     * @param token        token
     * @param shopGoodsIds 便利店商品ID列表
     * @param shopCategory 修改后的便利店商品分类ID
     * @param request      request
     * @param response     response
     */
    @ApiOperation(value = "批量或单个修改便利店商品便利店内部分类",
            notes = "批量或单个修改便利店商品便利店内部分类",
            position = 3)
    @RequestMapping(value = "/changeCategory", method = RequestMethod.POST)
    public void changeShopCategory(@RequestHeader("token") String token,
                                   @RequestParam("shopGoodsIds") List<Long> shopGoodsIds,
                                   @RequestParam("shopCategory") Long shopCategory,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            
            //批量修改操作
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("shopGoodsIds", shopGoodsIds);
            map.put("shopCategory", shopCategory);
            shopGoodsService.updateBatch(map);
            
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("批量或单个修改便利店商品便利店内部分类失败.", se);
            //TODO 错误码尚未提供
            RespHandler.respError(RespMsg.shop_business_query_id, response);
        } catch (Exception e) {
            log.error("批量或单个修改便利店商品便利店内部分类出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }
    
    /**
     * 查询一页便利店分类数据以及分类下商品信息
     *
     * @param token    token
     * @param shopId   便利店ID
     * @param pageNum  页面游标
     * @param pageSize 页面大小
     * @param request  request
     * @param response response
     */
    @ApiOperation(value = "查询一页便利店分类数据以及分类下商品信息",
            notes = "查询一页便利店分类数据以及分类下商品信息",
            position = 1)
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public void getList(
                          @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                          @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {

            //分页参数填充
            ShopCategoryPage shopCategoryPage = new ShopCategoryPage();
            shopCategoryPage.setShopId(shopId);
            shopCategoryPage.setNowPage(pageNum);
            shopCategoryPage.setPageSize(pageSize);

            //分页查询
            Pager<ShopCategory, Long> pager = shopCategoryService.query(shopCategoryPage, ShopCategory.class);
            List<ShopCategory> list = pager.getList();
            for(ShopCategory shopCategory : list)
            {
            	ShopGoods shopGoods = new ShopGoods();
            	shopGoods.setShopCategoryId(shopCategory.getDmId());
            	shopGoods.setShopId(shopId);
            	List<ShopGoodsVo> result = shopGoodsService.getShopGoodsVoList(shopGoods);
            	shopCategory.setList(result);
            }
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询便利店分类列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店分类列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息 (待完善)
     *
     * @param token       token
     * @param shopId      便利店ID
     * @param shopGoodsId 便利店商品ID
     * @param request     request
     * @param response    response
     */
    @ApiOperation(value = "查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息",
            notes = "查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息",
            position = 0)
    @RequestMapping(value = "/getGoodsDetails", method = RequestMethod.GET)
    public void getGoodsDetails(
                          @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                          @ApiParam(value = "商品ID") @RequestParam("shopGoodsId") Long shopGoodsId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
        	ShopGoods shopGoods = new ShopGoods();
    		shopGoods.setGoodsId(shopGoodsId);
    		shopGoods.setShopId(shopId);
    		ShopGoodsVo shopGoodsVo = shopGoodsService.getShopGoodsVo(shopGoods);
    		SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
					SerializerFeature.DisableCircularReferenceDetect,
					SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse};  
			
			JSONObject json=new JSONObject();
			json.put("code", 0);
			json.put("data", shopGoodsVo);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			write.write(JSON.toJSONString(json, features));
			write.flush();
			write.close();
        } catch (ServiceException se) {
            log.error("查询便利店商品详情失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店商品详情出现系统错误.", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
}