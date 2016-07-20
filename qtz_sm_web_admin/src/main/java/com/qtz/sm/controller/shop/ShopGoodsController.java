package com.qtz.sm.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.shop.page.ShopGoodsPage;
import com.qtz.sm.shop.service.ShopGoodsService;
import com.qtz.sm.shop.vo.ShopGdGoodsSkuFilter;
import com.qtz.sm.shop.vo.ShopGdGoodsVo;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopValueVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>Description:便利店商品Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016-04-26
 */
@Api(value = "/shop/goods", description = "便利店商品管理")
@Controller("ShopGoodsController")
@RequestMapping("/shop/goods")
public class ShopGoodsController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(ShopGoodsController.class);
    /**
     * 注入便利店商品Service类
     */
    @Resource(name = "shopGoodsServiceImpl")
    private ShopGoodsService shopGoodsService;
    
    @Autowired
	private GdGoodsService gdGoodsService;

    /**
     * 
     * @Description:查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息 
     * @param token       token
     * @param shopId      便利店ID
     * @param shopGoodsId 便利店商品ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:26:17
     */
    @ApiOperation(value = "查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息",
            notes = "查询便利店商品详情，包括便利店分类信息，商品品牌信息，便利店sku信息",
            position = 0)
    @RequestMapping(value = "/getShopGoodsInfo", method = RequestMethod.GET)
    public void getShopGoodsInfo(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                          @ApiParam(value = "商品ID", required = true) @RequestParam Long shopGoodsId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            ShopGoods shopGoods = new ShopGoods();
    		shopGoods.setGoodsId(shopGoodsId);
    		shopGoods.setShopId(shopId);
			List<ShopValueVo> valList = shopGoodsService.findSkuList(shopGoods);//根据便利店ID和商品ID查询商品所对应的sku属性值
			final Map<Long, Integer> skuStockMap = new HashMap<>();
			if(valList!=null && valList.size()>0){
				for (int i = 0; i < valList.size(); i++) {
					skuStockMap.put(valList.get(i).getSkuId(), valList.get(i).getActualNum());
				}
			}
            GdGoodsOutJson goodsOut = gdGoodsService.getGoodsDetail(shopGoodsId, new HashMap<Long, Double>(), skuStockMap, false, false, null, new ShopGdGoodsSkuFilter(skuStockMap));
            if(goodsOut==null){
            	 RespHandler.respError(RespMsg.not_found, response);
            }else{
            	RespJsonPHandler.respOK(goodsOut, response, request);
            }
        } catch (ServiceException se) {
            log.error("查询便利店商品详情失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店商品详情出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 
     * @Description:查询便利店一页商品信息(基本信息)
     * @param token
     * @param shopId	便利店ID
     * @param pageNum	页面游标
     * @param pageSize	页面大小
     * @param parmCode	查询类别 (所有便利店进货的商品parmCode=1,未分类商品parmCode=3)
     * @param shopGoodsPage	查询商品信息 
     *        提供商品名称、skuId、商品编号 模糊查询
     *        可用库存 范围查询
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:26:47
     */
    @ApiOperation(value = "查询便利店一页商品信息(基本信息)",
            notes = "查询便利店一页商品信息(基本信息)",
            position = 1)
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public void queryPage(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                          @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          @ApiParam(value = "查询类别1-所有便利店进货商品3-未分类商品",required = true) @RequestParam("parmCode") Integer parmCode,
                          @RequestBody ShopGoodsPage shopGoodsPage,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            // 参数对象转换
            shopGoodsPage.setParmCode(parmCode);
            shopGoodsPage.setShopId(shopId);
            shopGoodsPage.setNowPage(pageNum);
            shopGoodsPage.setPageSize(pageSize);
            shopGoodsPage.setOrderField("update_on");
            shopGoodsPage.setOrderDirection(false);

            //分页查询
//            Pager<ShopGdGoodsVo, Long> pager = shopGoodsService.findGdGoodsListByShopId(shopGoodsPage);
//            //根据便利店ID跟商品ID 找出所属sku列表 
//            if(pager !=null && pager.getList()!=null && pager.getList().size()>0){
//            	for (int i = 0; i < pager.getList().size(); i++) {
//            		ShopGoods shopGoods = new ShopGoods();
//            		shopGoods.setGoodsId(pager.getList().get(i).getDmId());
//            		shopGoods.setShopId(shopId);
//					List<ShopValueVo> valList = shopGoodsService.findSkuList(shopGoods);
//					pager.getList().get(i).setValList(valList);
//				}
//            }
            Pager<ShopGdGoodsVo, Long> pager = shopGoodsService.queryGdGoodsListByShopId(shopGoodsPage);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询便利店一页商品信息(基本信息)失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店一页商品信息(基本信息)出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 
     * @Description:通过便利店分类ID以及便利店ID查询一页便利店商品信息(基本信息)
     * @param token
     * @param shopCategoryId	便利店分类ID
     * @param shopId			便利店ID
     * @param pageNum	   		页面游标
     * @param pageSize	   		页面大小
     * @param shopGoodsPage     查询信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:27:38
     */
    @ApiOperation(value = "通过便利店分类ID查询一页便利店商品信息(基本信息)",
            notes = "通过便利店分类ID查询一页便利店商品信息(基本信息)",
            position = 2)
    @RequestMapping(value = "/shopCategoryId", method = RequestMethod.POST)
    public void queryByShopCategory(@RequestHeader("token") String token,
                                    @ApiParam(value = "便利店分类ID", required = true) @RequestParam("shopCategoryId") Long shopCategoryId,
                                    @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                                    @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                        			@ApiParam(value = "页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                        			@RequestBody ShopGoodsPage shopGoodsPage,
                        			HttpServletRequest request,
                                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            
            shopGoodsPage.setShopId(shopId);
            shopGoodsPage.setShopCategoryId(shopCategoryId);
            shopGoodsPage.setParmCode(2); //1查询所有便利店所有商品，2查询分类下的商品，3查询未绑定分类的商品
            shopGoodsPage.setNowPage(pageNum);
            shopGoodsPage.setPageSize(pageSize);
            shopGoodsPage.setOrderField("update_on");
            shopGoodsPage.setOrderDirection(false);
           //分页查询
            Pager<ShopGdGoodsVo, Long> pager = shopGoodsService.findGdGoodsListByShopId(shopGoodsPage);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("通过便利店分类ID查询一页便利店商品信息(基本信息)失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("通过便利店分类ID查询一页便利店商品信息(基本信息)出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 
     * @Description:批量或单个修改便利店商品便利店内部分类
     * @param token        token
     * @param shopGoodsIds 便利店商品ID列表
     * @param shopCategoryId 修改后的便利店商品分类ID
     * @param shopId       便利店ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:28:14
     */
    @ApiOperation(value = "批量或单个修改便利店商品便利店内部分类",
            notes = "批量或单个修改便利店商品便利店内部分类",
            position = 3)
    @RequestMapping(value = "/changeCategory", method = RequestMethod.GET)
    public void changeShopCategory(@RequestHeader("token") String token,
    		 					   @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
    							   @ApiParam(value = "修改后的便利店商品分类ID", required = true) @RequestParam("shopCategoryId") Long shopCategoryId,
    							   @RequestParam("shopGoodsIds") List<Long> shopGoodsIds,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            
            //批量修改操作
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("shopGoodsIds", shopGoodsIds);
            map.put("shopCategoryId", shopCategoryId);
            map.put("shopId", shopId);
            shopGoodsService.updateBatch(map);
            
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("批量或单个修改便利店商品便利店内部分类失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("批量或单个修改便利店商品便利店内部分类出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }
}