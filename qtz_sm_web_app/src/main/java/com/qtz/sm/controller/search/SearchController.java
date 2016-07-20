package com.qtz.sm.controller.search;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.solr.SearchShopSolrService;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.search.page.SearchHotListPage;
import com.qtz.sm.search.page.SearchListPage;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.search.service.SearchHotListService;
import com.qtz.sm.search.service.SearchListService;
import com.qtz.sm.search.service.SearchTypeService;
import com.qtz.sm.search.vo.SearchHotList;
import com.qtz.sm.search.vo.SearchList;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopInfo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>
 * Title:SearchController
 * </p>
 * <p>
 * Description:搜索Controller类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 * 
 * @author 郭云龙
 * @version v1.0 2016-05-25
 */
// @Api(value = "/search", description = "搜索控制类")
// @Controller("searchController")
// @RequestMapping("/search")
public class SearchController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(SearchController.class);
    
    @Resource(name = "searchListServiceImpl")
    private SearchListService searchListServiceImpl;
    
    @Resource(name = "searchTypeServiceImpl")
    private SearchTypeService searchTypeServiceImpl;
    
    @Resource(name = "searchHotListServiceImpl")
    private SearchHotListService searchHotListServiceImpl;
    
    /**
     * 注入便利店Service类
     */
    @Resource(name = "shopInfoServiceImpl")
    private ShopInfoService shopInfoService;
    
    // @ApiOperation(value = "分类列表",
    // notes = "分类列表",
    // position = 0)
    // @RequestMapping(value = "/typeList", method = RequestMethod.GET)
    // public void typeList(
    // HttpServletRequest request,
    // HttpServletResponse response){
    // try {
    // List<SearchType> searchTypes = this.searchTypeServiceImpl.findList(new SearchType());
    //
    // RespJsonPHandler.respOK(searchTypes, response, request);
    // } catch (ServiceException e) {
    // e.printStackTrace();
    // log.error(e);
    // RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
    // }
    // }
    
    @ApiOperation(value = "搜索记录列表", notes = "搜索记录列表", position = 0)
    @RequestMapping(value = "/hisList", method = RequestMethod.GET)
    public void hisList(@RequestHeader("token") String token, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            SearchListPage page = new SearchListPage();
            page.setNowPage(1);
            page.setPageSize(10);
            page.setOrderField("search_time");
            page.setOrderDirection(false);
            Pager<SearchList, Long> searchTypes = this.searchListServiceImpl.query(page, SearchList.class);
            // List<SearchList> searchTypes = this.searchListServiceImpl.findList(new SearchList());
            
            RespJsonPHandler.respOK(searchTypes, response, request);
        }
        catch (ServiceException e) {
            e.printStackTrace();
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    // @ApiOperation(value = "搜索",
    // notes = "搜索",
    // position = 0)
    // @RequestMapping(value = "/add", method = RequestMethod.POST)
    // public void add(@RequestHeader("token") String token,@ApiParam("搜索") @RequestBody SearchList searchList,
    // HttpServletRequest request,
    // HttpServletResponse response){
    // try {
    // //增加搜记录
    // this.searchListServiceImpl.addVo(searchList);
    // RespJsonPHandler.respOK(null, response, request);
    // } catch (ServiceException e) {
    // e.printStackTrace();
    // log.error(e);
    // RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
    // }
    // }
    
    @ApiOperation(value = "清空搜索列表", notes = "清空搜索列表", position = 0)
    @RequestMapping(value = "/cleanHis", method = RequestMethod.GET)
    public void cleanHis(@RequestHeader("token") String token, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            User user = getUserJsonp(token, response, request);
            if (null == user) {
                return;
            }
            this.searchListServiceImpl.delSearchUser(user.getDmId());
            RespJsonPHandler.respOK(null, response, request);
        }
        catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    @ApiOperation(value = "搜索", notes = "搜索", position = 0)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void search(@ApiParam("token") @RequestHeader("token") String token,
            @ApiParam("经度") @RequestParam Double latitude, @ApiParam("纬度") @RequestParam Double longitude,
            @ApiParam("搜索关键字") @RequestParam String keyword,
            @ApiParam("搜索分类 1-店铺 2-商品") @RequestParam Long searchTypeId,
            @ApiParam("搜索方式 0-便利店 1-超市") @RequestParam Integer searchWays,
            @ApiParam("商品ID") @RequestParam(required = false) Long shopId,
            @ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex,
            @ApiParam("行数") @RequestParam(defaultValue = "10") Integer pageSize, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            // 搜索数据集合
            User user = getUserJsonp(token, response, request);
            if (null == user) {
                return;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            
            if (searchTypeId == 1 && searchWays == 0) {
                
                ShopInfoPage page = new ShopInfoPage();
                page.setNowPage(pageIndex);
                page.setPageSize(pageSize);
                page.setLatitude(latitude);
                page.setLongitude(longitude);
                page.setScope(keyword);
                page.setName(keyword);
                Pager<ShopInfo, Long> pager = this.searchListServiceImpl.searchShopList(page);
                map.put("pager", pager);
            }
            else if (searchTypeId == 2 && searchWays == 0) {
                
                ShopInfoPage page = new ShopInfoPage();
                page.setNowPage(pageIndex);
                page.setPageSize(pageSize);
                page.setLatitude(latitude);
                page.setLongitude(longitude);
                page.setKeyword(keyword);
                page.setDmId(shopId);
                Pager<ShopGoods, Long> pager = this.searchListServiceImpl.searchShopGoodsList(page);
                map.put("pager", pager);
            }
            else if (searchTypeId == 2 && searchWays == 1) {
                
                ShopInfoPage page = new ShopInfoPage();
                page.setNowPage(pageIndex);
                page.setPageSize(pageSize);
                page.setLatitude(latitude);
                page.setLongitude(longitude);
                page.setKeyword(keyword);
                Pager<ShopGoods, Long> pager = this.searchListServiceImpl.searchShopGoodsList(page);
                map.put("pager", pager);
                
            }
            
            map.put("searchTypeId", searchTypeId);
            map.put("searchWays", searchWays);
            RespJsonPHandler.respOK(map, response, request);
            // 插入搜索记录
            SearchList searchList = new SearchList();
            searchList.setKeyword(keyword);
            searchList.setSearchTypeId(searchTypeId);
            searchList.setSearchUser(user.getDmId());
            searchList.setShopId(shopId);
            searchList.setSearchWays(searchWays);
            searchList.setSearchTime(new Date().getTime());
            this.searchListServiceImpl.addVo(searchList);
        }
        catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    @ApiOperation(value = "获取热门搜索列表", notes = "获取热门搜索列表", position = 0)
    @RequestMapping(value = "/hotList", method = RequestMethod.GET)
    public void hotList(@ApiParam("搜索分类 1-店铺 2-商品") @RequestParam Long searchTypeId,
            @ApiParam("搜索方式 0-便利店 1-超市") @RequestParam Integer searchWays, HttpServletRequest request,
            HttpServletResponse response) {
        try {
            SearchHotListPage page = new SearchHotListPage();
            page.setNowPage(1);
            page.setPageSize(9);
            page.setOrderField("search_time");
            page.setOrderDirection(false);
            page.setSearchTypeId(searchTypeId);
            page.setSearchWays(searchWays);
            Pager<SearchHotList, Long> searchTypes = this.searchHotListServiceImpl.query(page, SearchHotList.class);
            RespJsonPHandler.respOK(searchTypes, response, request);
        }
        catch (ServiceException e) {
            e.printStackTrace();
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    /**
     * 便利店首页列表
     *
     * @param token token
     * @param pageSize 页面大小
     * @param request request
     * @param response response
     */
    @ApiOperation(value = "便利店首页列表", notes = "便利店首页列表", position = 1)
    @RequestMapping(value = "/indexList", method = RequestMethod.GET)
    public void indexList(@RequestHeader("token") String token, @ApiParam("经度") @RequestParam Double latitude,
            @ApiParam("纬度") @RequestParam Double longitude,
            @ApiParam("便利店经营范围") @RequestParam(required = false) String scope,
            @ApiParam(value = "页面游标") @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
            @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // 登陆检查
            checkLogin(token, response);
            // 获取库存信息
            ShopInfoPage page = new ShopInfoPage();
            page.setNowPage(pageIndex);
            page.setPageSize(pageSize);
            page.setLatitude(latitude);
            page.setLongitude(longitude);
            page.setScope(scope);
            Pager<ShopInfo, Long> pager = this.searchListServiceImpl.searchShopList(page);
            RespJsonPHandler.respOK(pager, response, request);
        }
        catch (ServiceException se) {
            log.error("查询便利店一页商品信息(基本信息)失败.", se);
            // TODO 错误码尚未提供
            RespHandler.respError(RespMsg.shop_business_query_id, response);
        }
        catch (Exception e) {
            log.error("查询便利店一页商品信息(基本信息)出现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }
    

    
}