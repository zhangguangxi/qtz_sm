package com.qtz.sm.controller.shop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.vo.ShopInfo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>Title:GoodsController</p>
 * <p>Description:便利店Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 郭云龙
 * @version v1.0 2016-05-26
 */
@Api(value = "/shop", description = "便利店控制类")
@Controller("ShopController")
@RequestMapping("/shop")
public class ShopController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(ShopController.class);
    
    /**
     * 注入便利店Service类
     */
    @Resource(name = "shopInfoServiceImpl")
    private ShopInfoService shopInfoService;

    
    @ApiOperation(value = "查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息",
            notes = "查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息",
            position = 0)
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public void detail(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            ShopInfo ShopInfo = this.shopInfoService.findVo(shopId,null);

			RespJsonPHandler.respOK(ShopInfo, response, request);
        } catch (ServiceException se) {
            log.error("查询便利店商品详情失败.", se);
            //TODO 错误码尚未提供
            RespHandler.respError(RespMsg.shop_business_query_id, response);
        } catch (Exception e) {
            log.error("查询便利店商品详情出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }
    
   


}