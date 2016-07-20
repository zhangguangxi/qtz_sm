package com.qtz.sm.controller.shopManage;

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
import com.qtz.sm.shopManage.page.ShopManageCategoryPage;
import com.qtz.sm.shopManage.service.ShopManageCategoryService;
import com.qtz.sm.shopManage.vo.ShopManageCategory;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * <p>Title:CategoryController</p>
 * <p>Description:便利店管理公司管理公司经营范围经营范围分类Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016-06-06
 */
@Api(value = "/shopManage/category", description = "便利店管理公司管理公司经营范围经营范围分类管理")
@Controller("ShopManageCategoryController")
@RequestMapping("/shopManage/category")
public class ShopManageCategoryController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(ShopManageCategoryController.class);
    /**
     * 注入便利店管理公司经营范围分类Service类
     */
    @Resource(name = "shopManageCategoryServiceImpl")
    private ShopManageCategoryService shopManageCategoryService;
    

    /**
     * 
     * @Description:查询便利店管理公司经营范围经营范围分类信息
     * @param token          token
     * @param shopCategoryId 便利店管理公司经营范围分类ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月6日 上午11:17:19
     */
    @ApiOperation(value = "查询便利店管理公司经营范围经营范围分类信息",
            notes = "查询便利店管理公司经营范围经营范围分类信息",
            position = 0)
    @RequestMapping(value = "/getInfoById", method = RequestMethod.GET)
    public void getInfoById(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店管理公司经营范围经营范围分类ID",required=true) @RequestParam Long dmId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            ShopManageCategory shopManageCategory = shopManageCategoryService.findVo(dmId, null);
            if (null == shopManageCategory) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
                RespHandler.respOK(shopManageCategory, response);
            }
        } catch (ServiceException se) {
            log.error("查询便利店管理公司经营范围经营范围分类信息失败.", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店管理公司经营范围经营范围分类信息出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }

    /**
     * 
     * @Description:查询一页便利店管理公司经营范围分类数据
     * @param token    		token
     * @param shopMangaId   便利店管理公司ID
     * @param pageNum  		页面游标
     * @param pageSize 		页面大小
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月6日 上午11:17:19
     */
    @ApiOperation(value = "查询一页便利店管理公司经营范围分类数据",
            notes = "查询一页便利店管理公司经营范围分类数据",
            position = 1)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void queryPage(@RequestHeader("token") String token,
                          @ApiParam(value = "便利店管理公司ID", required = true) @RequestParam("shopManageId") Long shopManageId,
                          @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                          @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response); 

            //分页参数填充
            ShopManageCategoryPage shopCategoryPage = new ShopManageCategoryPage();
            shopCategoryPage.setShopManageId(shopManageId);
            shopCategoryPage.setNowPage(pageNum);
            shopCategoryPage.setPageSize(pageSize);
            shopCategoryPage.setOrderField("create_time");
            shopCategoryPage.setOrderDirection(false);
            //分页查询
            Pager<ShopManageCategory, Long> pager = shopManageCategoryService.query(shopCategoryPage, ShopManageCategory.class);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询便利店管理公司经营范围分类列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询便利店管理公司经营范围分类列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:添加便利店管理公司经营范围分类
     * @param token        			token
     * @param shopManageCategory 	信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月6日 上午11:17:19
     */
    @ApiOperation(value = "添加便利店管理公司经营范围分类",
            notes = "添加便利店管理公司经营范围分类",
            position = 2)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
                    @ApiParam(value = "便利店管理公司经营范围分类对象", required = true) @RequestBody ShopManageCategory shopManageCategory,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(shopManageCategory);
            
            //信息填充
            Date date = new Date();
            shopManageCategory.setCreateTime(date);
            shopManageCategory.setUpdateTime(date);
            
            //先判断经营范围分类名字是否已存在
            ShopManageCategory query = new ShopManageCategory();
            query.setShopManageId(shopManageCategory.getShopManageId());
            query.setCategoryName(shopManageCategory.getCategoryName());
            List<ShopManageCategory> list = shopManageCategoryService.findList(query);
            if(null != list && list.size() > 0){
            	RespHandler.respError(RespMsg.shop_manage_category_name_have, response);
            }else{
            	//添加
                shopManageCategoryService.addVo(shopManageCategory);
                RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("添加便利店管理公司经营范围分类出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加便利店管理公司经营范围分类出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店管理公司经营范围分类信息
     * @param token        token
     * @param shopManageCategory 经营范围分类信息
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月6日 上午11:17:19
     */
    @ApiOperation(value = "修改便利店管理公司经营范围分类信息",
            notes = "修改便利店管理公司经营范围分类信息",
            position = 3)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
                       @ApiParam(value = "便利店管理公司经营范围分类对象", required = true) @RequestBody ShopManageCategory shopManageCategory,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆验证
            checkLogin(token, response);

            //数据验证
            checkModVo(shopManageCategory);
            shopManageCategory.setUpdateTime(new Date());

            //先判断经营范围分类名字是否已存在
            ShopManageCategory query = new ShopManageCategory();
            query.setShopManageId(shopManageCategory.getShopManageId());
            query.setCategoryName(shopManageCategory.getCategoryName());
            List<ShopManageCategory> list = shopManageCategoryService.findList(query);
            if(null != list && list.size() > 0 && !list.get(0).getDmId().equals(shopManageCategory.getDmId())){
            	RespHandler.respError(RespMsg.shop_manage_category_name_have, response);
            }else{
	            //修改
	            shopManageCategoryService.modVoNotNull(shopManageCategory);
	            RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("修改便利店管理公司经营范围分类信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店管理公司经营范围分类信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改便利店管理公司经营范围分类状态，便利店管理公司经营范围分类状态：0启用，1禁用
     * @param token          token
     * @param dmId 			   便利店管理公司经营范围分类ID
     * @param status         便利店管理公司经营范围分类状态
     * @param request
     * @param response
     * @PathVariable 是获取在这个请求的URL里就是个变量{shopCategoryId}
     * @RequestParam 用来获得静态的URL请求入参
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月6日 上午11:17:19
     */
    @ApiOperation(value = "修改便利店管理公司经营范围分类状态，便利店管理公司经营范围分类状态：0启用，1禁用",
            notes = "修改便利店管理公司经营范围分类状态，便利店管理公司经营范围分类状态：0启用，1禁用",
            position = 4)
    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
    public void updateStatus(@RequestHeader("token") String token,
                       @ApiParam(value="便利店管理公司经营范围分类ID", required = true) @RequestParam Long dmId,
                       @ApiParam(value = "需要修改的经营范围分类状态", required = true) @RequestParam Integer status,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            ShopManageCategory shopManageCategory = new ShopManageCategory();
            shopManageCategory.setDmId(dmId);
            shopManageCategory.setStatus(status);
            shopManageCategory.setUpdateTime(new Date());
            
            ShopManageCategory query = shopManageCategoryService.findVo(dmId, null);
            if(!StringUtil.isEmpty(query)){
            	shopManageCategoryService.modVoNotNull(shopManageCategory);
                RespJsonPHandler.respOK(response, request);
            }else{
            	RespHandler.respError(RespMsg.not_found, response);
            }
        } catch (ServiceException se) {
            log.error("修改便利店管理公司经营范围分类状态出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改便利店管理公司经营范围分类状态出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 
     * @Description:添加便利店管理公司类目时，数据验证
     * @param shopCategory 便利店管理公司经营范围分类对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:19:27
     */
    private void checkVo(ShopManageCategory shopManageCategory) throws ServiceException {
        if (null == shopManageCategory.getShopManageId()) {
            throw new ServiceException(ExceptionCode.SHOP_MANAGE_ID_IS_NULL, "便利店管理公司ID不能为空.");
        }
        if (StringUtils.isEmpty(shopManageCategory.getCategoryName())) {
            throw new ServiceException(ExceptionCode.SHOP_MANAGE_CATEGORY_NAME_IS_NULL, "经营范围分类名称不能为空.");
        }
        if (null == shopManageCategory.getStatus()) {
        	shopManageCategory.setStatus(0);
        }
        if (null == shopManageCategory.getSort()) {
        	shopManageCategory.setSort(1);
        }
    }

    /**
     * 
     * @Description:修改便利店管理公司类目时，数据验证
     * @param shopCategory  便利店管理公司经营范围分类对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:19:39
     */
    private void checkModVo(ShopManageCategory shopManageCategory) throws ServiceException {
        if (null == shopManageCategory.getDmId()) {
            throw new ServiceException(ExceptionCode.SHOP_MANAGE_CATEGORY_ID_IS_NULL,"便利店管理公司经营范围分类ID不能为空.");
        }
        checkVo(shopManageCategory);
    }
}