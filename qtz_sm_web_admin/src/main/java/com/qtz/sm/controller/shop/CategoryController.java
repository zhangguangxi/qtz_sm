package com.qtz.sm.controller.shop;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shop.page.ShopCategoryPage;
import com.qtz.sm.shop.service.ShopCategoryService;
import com.qtz.sm.shop.vo.ShopCategory;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * <p>Title:CategoryController</p>
 * <p>Description:便利店类目Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016-05-26
 */
@Api(value = "/shop/category", description = "便利店分类管理")
@Controller("ShopCategoryController")
@RequestMapping("/shop/category")
public class CategoryController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(CategoryController.class);
    /**
     * 注入便利店分类Service类
     */
    @Resource(name = "shopCategoryServiceImpl")
    private ShopCategoryService shopCategoryService;
    

    /**
     * 
     * @Description:查询便利店分类信息
     * @param token          token
     * @param shopCategoryId 便利店分类ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:17:19
     */
    @ApiOperation(value = "查询便利店分类信息",
            notes = "查询便利店分类信息",
            position = 0)
    @RequestMapping(value = "/getShopCategoryInfo", method = RequestMethod.GET)
    public void queryById(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店分类ID",required=true) @RequestParam Long shopCategoryId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            ShopCategory shopCategory = shopCategoryService.findVo(shopCategoryId, null);
            if (null == shopCategory) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
                RespHandler.respOK(shopCategory, response);
            }
        } catch (ServiceException se) {
            log.error("查询便利店分类信息失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店分类信息出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 
     * @Description:查询一页便利店分类数据
     * @param token    token
     * @param shopId   便利店ID
     * @param pageNum  页面游标
     * @param pageSize 页面大小
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:17:33
     */
    @ApiOperation(value = "查询一页便利店分类数据",
            notes = "查询一页便利店分类数据",
            position = 1)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void queryPage(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店ID", required = true) @RequestParam("shopId") Long shopId,
                          @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //分页参数填充
            ShopCategoryPage shopCategoryPage = new ShopCategoryPage();
            shopCategoryPage.setShopId(shopId);
            shopCategoryPage.setNowPage(pageNum);
            shopCategoryPage.setPageSize(pageSize);
            shopCategoryPage.setOrderField("create_time");
            shopCategoryPage.setOrderDirection(false);
            //分页查询
            Pager<ShopCategory, Long> pager = shopCategoryService.query(shopCategoryPage, ShopCategory.class);
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
     * 
     * @Description:添加便利店分类
     * @param token        token
     * @param shopCategory 店铺分类信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:17:53
     */
    @ApiOperation(value = "添加便利店分类",
            notes = "添加便利店分类",
            position = 2)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
                    @ApiParam(value = "便利店分类对象", required = true) @RequestBody ShopCategory shopCategory,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(shopCategory);
            
            //信息填充
            Date date = new Date();
            shopCategory.setCreateTime(date);
            shopCategory.setUpdateTime(date);
            
            //先判断分类名字是否已存在
            ShopCategory query = new ShopCategory();
            query.setShopId(shopCategory.getShopId());
            query.setName(shopCategory.getName());
            List<ShopCategory> list = shopCategoryService.findList(query);
            if(null != list && list.size() > 0){
            	RespHandler.respError(RespMsg.shop_category_name_have, response);
            }else{
            	//添加
                shopCategoryService.addVo(shopCategory);
                RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("添加便利店分类出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加便利店分类出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店分类信息
     * @param token        token
     * @param shopCategory 便利店分类信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:18:09
     */
    @ApiOperation(value = "修改便利店分类信息",
            notes = "修改便利店分类信息",
            position = 3)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
                       @ApiParam(value = "便利店分类对象", required = true) @RequestBody ShopCategory shopCategory,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆验证
            checkLogin(token, response);

            //数据验证
            checkModVo(shopCategory);
            shopCategory.setUpdateTime(new Date());

            //先判断分类名字是否已存在
            ShopCategory query = new ShopCategory();
            query.setShopId(shopCategory.getShopId());
            query.setName(shopCategory.getName());
            List<ShopCategory> list = shopCategoryService.findList(query);
            if(null != list && list.size() > 0 && !list.get(0).getDmId().equals(shopCategory.getDmId())){
            	RespHandler.respError(RespMsg.shop_category_name_have, response);
            }else{
	            //修改
	            shopCategoryService.modVoNotNull(shopCategory);
	            RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("修改便利店分类信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店分类信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店分类状态
     * @param token          token
     * @param shopCategoryId 便利店分类ID
     * @param status         便利店分类状态
     * @param request
     * @param response
     * @PathVariable 是获取在这个请求的URL里就是个变量{shopCategoryId}
     * @RequestParam 用来获得静态的URL请求入参
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:18:25
     */
    @ApiOperation(value = "修改便利店分类状态",
            notes = "修改便利店分类状态，便利店分类状态：0启用，1禁用",
            position = 4)
    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
    public void updateStatus(@RequestHeader("token") String token,
                       @ApiParam(value="便利店分类ID", required = true) @RequestParam Long shopCategoryId,
                       @ApiParam(value = "需要修改的分类状态", required = true) @RequestParam Integer status,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            ShopCategory shopCategory = new ShopCategory();
            shopCategory.setDmId(shopCategoryId);
            shopCategory.setStatus(status);
            shopCategory.setUpdateTime(new Date());

            ShopCategory query = shopCategoryService.findVo(shopCategoryId, null);
            if(!StringUtil.isEmpty(query)){
            	 shopCategoryService.modVoNotNull(shopCategory);
                 RespJsonPHandler.respOK(response, request);
            }else{
            	 RespHandler.respError(RespMsg.not_found, response);
            }
           
        } catch (ServiceException se) {
            log.error("修改便利营业状态出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店营业状态出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 
     * @Description:添加便利店类目时，数据验证
     * @param shopCategory 便利店分类对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:19:27
     */
    private void checkVo(ShopCategory shopCategory) throws ServiceException {
        if (null == shopCategory.getShopId()) {
            throw new ServiceException(ExceptionCode.SHOP_ID_IS_NULL, "便利店ID不能为空.");
        }
        if (StringUtils.isEmpty(shopCategory.getName())) {
            throw new ServiceException(ExceptionCode.SHOP_CATEGORY_NAME_IS_NULL, "便利店分类名称不能为空.");
        }
        if (null == shopCategory.getStatus()) {
            shopCategory.setStatus(0);
        }
        if (null == shopCategory.getSort()) {
            shopCategory.setSort(0);
        }
    }

    /**
     * 
     * @Description:修改便利店类目时，数据验证
     * @param shopCategory  便利店分类对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:19:39
     */
    private void checkModVo(ShopCategory shopCategory) throws ServiceException {
        if (null == shopCategory.getDmId()) {
            throw new ServiceException(ExceptionCode.SHOP_CATEGORY_ID_IS_NULL,"便利店分类ID不能为空.");
        }
        checkVo(shopCategory);
    }
}