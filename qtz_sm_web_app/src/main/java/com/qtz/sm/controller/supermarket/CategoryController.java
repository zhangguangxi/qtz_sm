package com.qtz.sm.controller.supermarket;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.supermarket.page.SupermarketCategoryPage;
import com.qtz.sm.supermarket.service.SupermarketCategoryAssociateService;
import com.qtz.sm.supermarket.service.SupermarketCategoryService;
import com.qtz.sm.supermarket.vo.SupermarketCategory;
import com.qtz.sm.supermarket.vo.SupermarketCategoryGoods;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * <p>
 * Title:com.qtz.sm.supermarket.controller.CategoryController
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 *
 * @author 刘晓峰 - Laven.liu
 * @version v1.0 2016/4/29
 */
@Api(value = "/supermarket/category", description = "超市分类管理")
@Controller("SupermarketCategoryController")
@RequestMapping("/supermarket/category")
public class CategoryController extends BaseController {
    
    private static LogTool log = LogTool.getInstance(CategoryController.class);
    
    @Resource(name = "supermarketCategoryServiceImpl")
    private SupermarketCategoryService supermarketCategoryService;
    
    @Resource(name = "supermarketCategoryAssociateServiceImpl")
    private SupermarketCategoryAssociateService supermarketCategoryAssociateService;
    
    @ApiOperation(value = "超市分类基本信息", notes = "超市分类基本信息", position = 0)
    @RequestMapping(value = "/{supermarketCategoryId}", method = RequestMethod.GET)
    public void queryById(@RequestHeader("token") String token,
            @ApiParam(value = "超市分类ID") @PathVariable Long supermarketCategoryId, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 登陆检查
            checkLogin(token, response);
            SupermarketCategory supermarketCategory = supermarketCategoryService.findById(supermarketCategoryId);
            if (null == supermarketCategory) {
                RespHandler.respError(RespMsg.not_found, response);
            }
            else {
                RespHandler.respOK(supermarketCategory, response);
            }
        }
        catch (ServiceException se) {
            log.error("查询超市分类基本信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        }
        catch (Exception e) {
            log.error("查询超市分类基本信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 
     * 获取一级商品分类集合
     * @version 2016年6月16日上午11:27:59
     * @author  guangxi.zhang
     * @param   token
     * @param   request
     * @param   response
     */
    @ApiOperation(value = "获取一级商品分类集合", notes = "获取一级商品分类集合(guangxi.zhang)", position = 0)
    @RequestMapping(value = "/list/firstCatetory", method = RequestMethod.GET)
    public void queryFirstCategory(@RequestHeader("token") String token, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            /** 登录检查 */
            checkLogin(token, response);
            /** 参数填充 */
            SupermarketCategory category = new SupermarketCategory();
            category.setLevel(1);
            /** 查询一级商品分类列表 */
            List<SupermarketCategory> categoryList = supermarketCategoryService.findList(category);
            if (categoryList != null && categoryList.size() > 0) {
                for (SupermarketCategory sc1 : categoryList) {
                    SupermarketCategory sc2 = new SupermarketCategory();
                    sc2.setLevel(2);
                    sc2.setPid(sc1.getDmId());
                    List<SupermarketCategory> categoryLevel2List = supermarketCategoryService.findList(sc2);
                    sc1.setSupermarketCategoryList(categoryLevel2List);
                }
            }
            log.info("CategoryController>>>>>>queryFirstCategory查询一级商品分类categoryList:"
                    + JSONObject.toJSONString(categoryList));
            if (categoryList == null || categoryList.size() == 0) {
                RespHandler.respError(RespMsg.not_found, response);
                
            }
            else {
                RespHandler.respOK(categoryList, response);
            }
        }
        catch (ServiceException se) {
            log.error("CategoryController>>>>>>queryFirstCategory查询一级商品分类失败", se);
            RespHandler.respError(RespMsg.find_second_category_fail, response);
        }
        catch (Exception e) {
            log.error("查询一级商品分类信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 查询一页超市一级分类数据
     *
     * @param token token
     * @param supermarketId 超市ID
     * @param pageNum 页面游标
     * @param pageSize 页面大小
     * @param request request
     * @param response response
     */
    @ApiOperation(value = "查询一页超市一级分类数据", notes = "查询一页超市一级分类数据", position = 1)
    @RequestMapping(value = "/list/first", method = RequestMethod.GET)
    public void queryFirstCategoryPage(@RequestHeader("token") String token,
            @ApiParam(value = "超市ID", required = true) @RequestParam("supermarketId") Long supermarketId,
            @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // 登陆检查
            checkLogin(token, response);
            
            // 分页查询
            Pager<SupermarketCategory, Long> pager = querPage(supermarketId, 0L, pageNum, pageSize);
            RespJsonPHandler.respOK(pager, response, request);
        }
        catch (ServiceException se) {
            log.error("查询超市一级分类列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        }
        catch (Exception e) {
            log.error("查询超市一级分类列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 查询一页超市二级分类数据
     *
     * @param token token
     * @param supermarketId 超市ID
     * @param pageNum 页面游标
     * @param pageSize 页面大小
     * @param request request
     * @param response response
     */
    @ApiOperation(value = "查询一页超市二级分类数据", notes = "查询一页超市二级分类数据", position = 2)
    @RequestMapping(value = "/list/second", method = RequestMethod.GET)
    public void querySecondCategoryPage(@RequestHeader("token") String token,
            @ApiParam(value = "超市ID", required = true) @RequestParam("supermarketId") Long supermarketId,
            @ApiParam(value = "一级类目ID", required = true) @RequestParam("pid") Long pid,
            @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // 登陆检查
            checkLogin(token, response);
            
            // 分页查询
            Pager<SupermarketCategory, Long> pager = querPage(supermarketId, pid, pageNum, pageSize);
            RespJsonPHandler.respOK(pager, response, request);
        }
        catch (ServiceException se) {
            log.error("查询超市二级分类列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        }
        catch (Exception e) {
            log.error("查询超市二级分类列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 添加超市分类
     *
     * @param token token
     * @param supermarketCategory 超市分类对象
     * @param request 请求对象
     * @param response 响应对象
     */
    @ApiOperation(value = "添加超市分类", notes = "添加超市分类", position = 3)
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestHeader("token") String token,
            @ApiParam(value = "超市分类信息对象", required = true) SupermarketCategory supermarketCategory,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // 登陆检查
            checkLogin(token, response);
            
            // 参数验证
            checkVo(supermarketCategory);
            
            // 信息填充
            Date date = new Date();
            supermarketCategory.setCreateTime(date);
            supermarketCategory.setUpdateTime(date);
            
            // 添加
            supermarketCategoryService.addVo(supermarketCategory);
            RespHandler.respOK(response);
        }
        catch (ServiceException se) {
            log.error("添加超市分类出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        }
        catch (Exception e) {
            log.error("添加超市分类出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 修改超市分类信息
     *
     * @param token token
     * @param supermarketCategory 超市分类信息对象
     * @param request 请求对象
     * @param response 响应对象
     */
    @ApiOperation(value = "修改超市分类信息", notes = "超市分类信息对象", position = 4)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
            @ApiParam(value = "超市分类信息对象", required = true) SupermarketCategory supermarketCategory,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // 登陆验证
            checkLogin(token, response);
            
            // 参数验证
            checkModVo(supermarketCategory);
            supermarketCategory.setUpdateTime(new Date());
            
            // 修改数据
            supermarketCategoryService.modVoNotNull(supermarketCategory);
            RespHandler.respOK(response);
        }
        catch (ServiceException se) {
            log.error("修改超市分类信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        }
        catch (Exception e) {
            log.error("修改超市分类信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 修改超市分类状态
     *
     * @param token token
     * @param shopCategoryId 超市分类ID
     * @param status 超市分类状态
     * @param request request
     * @param response response
     */
    @ApiOperation(value = "修超市分类状态", notes = "修改超市分类状态，便利店分类状态：正常，禁用", position = 4)
    @RequestMapping(value = "/status/{shopCategoryId}", method = RequestMethod.POST)
    public void status(@RequestHeader("token") String token, @ApiParam("超市分类ID") @PathVariable Long shopCategoryId,
            @ApiParam(value = "需要修改的分类状态", required = true) @RequestParam Integer status, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 登陆检查
            checkLogin(token, response);
            
            SupermarketCategory supermarketCategory = new SupermarketCategory();
            supermarketCategory.setDmId(shopCategoryId);
            supermarketCategory.setStatus(status);
            supermarketCategory.setUpdateTime(new Date());
            
            supermarketCategoryService.modVoNotNull(supermarketCategory);
            RespJsonPHandler.respOK(response, request);
        }
        catch (ServiceException se) {
            log.error("修改超市分类状态出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        }
        catch (Exception e) {
            log.error("修改超市分类状态出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 超市叶子分类关联后台叶子分类
     *
     * @param token token
     * @param supermarketCategoryId 超市分类ID
     * @param categoryIds 后台分类列表
     * @param request request
     * @param response response
     */
    @ApiOperation(value = "超市叶子分类关联后台叶子分类", notes = "超市叶子分类关联后台叶子分类", position = 5)
    @RequestMapping(value = "/associate/{supermarketCategoryId}", method = RequestMethod.POST)
    public void associate(@RequestHeader("token") String token,
            @ApiParam("超市分类ID") @PathVariable("supermarketCategoryId") Long supermarketCategoryId,
            @ApiParam(value = "后台分类列表", required = true) List<Long> categoryIds, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // TODO 数据验证
            supermarketCategoryAssociateService.addCategoryAssociate(supermarketCategoryId, categoryIds);
            RespHandler.respOK(response);
        }
        catch (ServiceException se) {
            log.error("超市叶子分类关联后台叶子分类失败！", se);
            // TODO 错误码尚未提供
            RespHandler.respError(RespMsg.server_throws_exception, response);
        }
        catch (Exception e) {
            log.error("超市叶子分类关联后台叶子分类出现系统错误！", e);
            RespHandler.respServerError(response);
        }
    }
    
    /**
     * 查询一页超市分类数据
     *
     * @param supermarketId 超市ID
     * @param pid 超市分类父节点ID
     * @param pageNum 页面游标
     * @param pageSize 页面大小
     * @return 一页超市分类数据
     * @throws ServiceException
     */
    private Pager<SupermarketCategory, Long> querPage(Long supermarketId, Long pid, Integer pageNum, Integer pageSize)
            throws ServiceException {
        // 参数填充
        SupermarketCategoryPage supermarketCategoryPage = new SupermarketCategoryPage();
        supermarketCategoryPage.setSupermarketId(supermarketId);
        supermarketCategoryPage.setPid(pid);
        supermarketCategoryPage.setNowPage(pageNum);
        supermarketCategoryPage.setPageSize(pageSize);
        
        // 分页查询
        Pager<SupermarketCategory, Long> pager = supermarketCategoryService.query(supermarketCategoryPage, null);
        return pager;
    }
    
    /**
     * 添加超市分类时，数据检查
     *
     * @param supermarketCategory 超市分类对象
     * @throws ServiceException
     */
    private void checkVo(SupermarketCategory supermarketCategory) throws ServiceException {
        if (null == supermarketCategory.getPid()) {
            supermarketCategory.setPid(0L);
            // 一级分类
            supermarketCategory.setLevel(1);
        }
        else {
            // 二级分类
            supermarketCategory.setLevel(2);
        }
        if (StringUtils.isEmpty(supermarketCategory.getName())) {
            throw new ServiceException("超市分类名称为空！");
        }
        if (null == supermarketCategory.getStatus()) {
            supermarketCategory.setStatus(0);
        }
        if (null == supermarketCategory.getSort()) {
            supermarketCategory.setSort(0);
        }
    }
    
    /**
     * 修改超市分类时，数据检查
     *
     * @param supermarketCategory
     * @throws ServiceException
     */
    private void checkModVo(SupermarketCategory supermarketCategory) throws ServiceException {
        if (null == supermarketCategory.getDmId()) {
            throw new ServiceException("超市分类ID为空！");
        }
        checkVo(supermarketCategory);
    }
    
    
    
    /**
     * 
     * 获取超市二级商品分类下商品信息
     * @version 2016年6月6日下午5:30:15
     * @author guangxi.zhang
     * @param token
     * @param latitude
     * @param longitude
     * @param firstLevelSupermarketCategoryId  超市一级级商品分类id
     * @param secondLevelSupermarketCategoryId 超市二级商品分类id
     * @param pageNum                          页码
     * @param pageSize                         每页显示记录数
     * @param request
     * @param response
     */
    @ApiOperation(value = "获取超市二级商品分类下商品信息", notes = "获取超市二级商品分类下商品信息(guangxi.zhang)", position = 0)
    @RequestMapping(value = "/list/secondCatetoryGoodsInfoPage", method = RequestMethod.GET)
    public void querysecondCatetoryGoodsInfoPage(@RequestHeader("token") String token,
            @ApiParam(value = "纬度", required = true)@RequestParam(value="latitude",required=true) Long latitude,
            @ApiParam(value = "经度", required = true)@RequestParam(value="longitude",required=true) Long longitude,
            @ApiParam(value = "超市一级商品分类id",required=true) @RequestParam(value="firstLevelSupermarketCategoryId",required=true)  Long firstLevelSupermarketCategoryId,
            @ApiParam(value = "超市二级商品分类id",required=false) @RequestParam(value="secondLevelSupermarketCategoryId",required=false) Long secondLevelSupermarketCategoryId,
            @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
            @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            /** 登录检查 */
            checkLogin(token, response);
            /** 查询 */
            log.info("CategoryController>>>>>>querysecondCatetoryGoodsInfo查询超市二级商品分类相关信息请求参数:latitude=" + latitude+",longitude="+longitude
                    + "  secondLevelSupermarketCategoryId=" + secondLevelSupermarketCategoryId
                    + " firstLevelSupermarketCategoryId=" + firstLevelSupermarketCategoryId);
                    
            /** 参数填充*/
            SupermarketCategoryPage supermarketCategoryPage = new SupermarketCategoryPage();
            supermarketCategoryPage.setDmId(secondLevelSupermarketCategoryId);
            supermarketCategoryPage.setPid(firstLevelSupermarketCategoryId);
            
            supermarketCategoryPage.setLatitude(latitude);
            supermarketCategoryPage.setLongitude(longitude);
            supermarketCategoryPage.setNowPage(pageNum);
            supermarketCategoryPage.setPageSize(pageSize);
            supermarketCategoryPage.setOrderDirection(false);
            supermarketCategoryPage.setCondition(pageSize,pageNum);
            // 分页查询
            Pager<SupermarketCategoryGoods, Long> pager = supermarketCategoryAssociateService
                    .queryGoodsInfoPageBySupermarketCategoryId(supermarketCategoryPage);
            RespJsonPHandler.respOK(pager, response, request);
        }
        catch (ServiceException se) {
            log.error("CategoryController>>>>>>querysecondCatetoryGoodsInfo查询超市二级商品分类分页信息失败", se);
            RespHandler.respError(RespMsg.find_second_category_goods_fail , response);
            
        }
        catch (Exception e) {
            log.error("CategoryController>>>>>>querysecondCatetoryGoodsInfo查询超市二级商品分类分页信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    
    /**
     * 
     * 根据一级商品分类id查询二级商品分类列表
     * 
     * @version 2016年6月6日下午5:30:15
     * @author guangxi.zhang
     * @param token 凭证
     * @param supermarketCategoryId 超市二级商品分类id
     * @param request
     * @param response
     */
    @ApiOperation(value = "根据一级商品分类id查询二级商品分类列表", notes = "根据一级商品分类id查询二级商品分类列表(guangxi.zhang)", position = 0)
    @RequestMapping(value = "/list/secondCatetoryByFirstCategoryId", method = RequestMethod.GET)
    public void querysecondCatetoryByFirstCategoryId(@RequestHeader("token") String token,
            @ApiParam(value = "超市一级商品分类id") @RequestParam(value="firstLevelSupermarketCategoryId") Long firstLevelSupermarketCategoryId,
            HttpServletRequest request,
            HttpServletResponse response) {
            try {
                /** 登录检查 */
                checkLogin(token, response);
                /** 查询 */
                SupermarketCategory supermarketCategory = new SupermarketCategory();
                supermarketCategory.setPid(firstLevelSupermarketCategoryId);
                List<SupermarketCategory> categoryList = supermarketCategoryService.findList(supermarketCategory);
                if (categoryList == null || categoryList.size() == 0) {
                    RespHandler.respError(RespMsg.not_found, response);
                }
                else {
                    RespHandler.respOK(categoryList, response);
                }
            }
            catch (ServiceException se) {
                log.error("CategoryController>>>>>>querysecondCatetoryByFirstCategoryId据一级商品分类id查询二级商品分类列表", se);
                RespHandler.respError(RespMsg.find_second_category_fail, response);
            }
            catch (Exception e) {
                log.error("CategoryController>>>>>>querysecondCatetoryByFirstCategoryId据一级商品分类id查询二级商品分类列表信息出现系统错误！", e);
                RespJsonPHandler.respServerError(response, request);
            }
        
    }
}
