package com.qtz.sm.controller.shop;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shop.service.ShopGoodsService;
import com.qtz.sm.shop.service.ShopGoodsSkuService;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopValueVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description:便利店商品Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016-04-26
 */
@Api(value = "/shop/sku", description = "便利店商品SKU管理")
@Controller("ShopGoodsSkuController")
@RequestMapping("/shop/sku")
public class ShopGoodsSkuController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(ShopGoodsSkuController.class);
    /**
     * 注入便利店商品skuService类
     */
    @Resource(name = "shopGoodsSkuServiceImpl")
    private ShopGoodsSkuService shopGoodsSkuService;

    @Resource(name = "shopGoodsServiceImpl")
    private ShopGoodsService shopGoodsService;

    /**
     * 
     * @Description:通过便利店ID与商品ID查询sku信息列表
     * @param token       token
     * @param shopId      便利店ID
     * @param shopGoodsId 便利店商品ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:32:14
     */
    @ApiOperation(value = "通过便利店ID与商品ID，查询SKU信息",
            notes = "通过便利店ID与商品ID，查询SKU信息",
            position = 0)
    @RequestMapping(value = "/shopGoodsId", method = RequestMethod.GET)
    public void queryByShopGoodsId(@RequestHeader("token") String token,
                                   @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                                   @ApiParam(value = "便利店商品ID") @RequestParam("shopGoodsId") Long shopGoodsId,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
           //根据便利店ID跟商品ID 找出所属sku列表
    		ShopGoods shopGoods = new ShopGoods();
    		shopGoods.setGoodsId(shopGoodsId);
    		shopGoods.setShopId(shopId);
            List<ShopValueVo> valList = shopGoodsService.findSkuList(shopGoods);
            if(valList!=null && valList.size()>0){
            	RespHandler.respOK(valList, response);
            }else{
            	RespHandler.respError(RespMsg.not_found, response);
            }
            
        } catch (ServiceException se) {
            log.error("通过便利店ID与商品ID查询sku信息列表失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("通过便利店ID与商品ID查询sku信息列表出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }
}