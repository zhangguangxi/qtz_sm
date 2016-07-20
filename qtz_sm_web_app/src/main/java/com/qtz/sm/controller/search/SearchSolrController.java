package com.qtz.sm.controller.search;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qtz.sm.search.vo.Search;
import com.qtz.sm.search.vo.ShopSearch;
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
import com.qtz.sm.common.solr.SearchShopSolrService;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.search.vo.SearchList;
import com.qtz.sm.search.vo.SupeGoodsSearch;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>Title:SearchSolrController</p>
 * <p>Description:搜索引擎Controller类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 郭云龙
 * @version v1.0 2016-06-03
 */
@Api(value = "/searchSolr", description = "搜索控制类")
@Controller("searchSolrController")
//@RequestMapping("/searchSolr")
@RequestMapping("/search")
public class SearchSolrController extends BaseController {
    /**
     * 初始化日志对象
     */
    private final static Logger log = Logger.getLogger(SearchSolrController.class);


	@Resource(name = "searchShopSolrServiceImpl")
	private SearchShopSolrService searchShopSolrService;
    
  /*  @ApiOperation(value = "搜索",
            notes = "搜索",
            position = 0)
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public void search(@ApiParam("token") @RequestHeader("token") String token,
    		@ApiParam("经度") @RequestParam Double latitude,
    		@ApiParam("纬度") @RequestParam Double longitude,
    		@ApiParam("搜索关键字") @RequestParam String keyword,
    		@ApiParam("搜索分类") @RequestParam Long searchTypeId,
    		@ApiParam("便利店ID") @RequestParam(required = false) Long shopId,
    		@ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex,
    		@ApiParam("行数") @RequestParam(defaultValue = "10") Integer pageSize,
            HttpServletRequest request,
            HttpServletResponse response){
		try {
	    	//搜索数据集合
			User user = getUserJsonp(token, response, request);
            if (null == user) {
                return;
            }
			Map<String,Object> map = new HashMap<String,Object>();

            if(searchTypeId ==1){

                ShopInfoPage page = new ShopInfoPage();
    			page.setNowPage(pageIndex);
    			page.setPageSize(pageSize);
    			page.setLatitude(latitude);
    			page.setLongitude(longitude);
    			page.setScope(keyword);
    			page.setName(keyword);
				map.put("pager", this.searchShopSolrService.search(page));
//    			Pager<ShopInfo, Long> pager = this.searchListServiceImpl.searchShopList(page);
//    			map.put("pager", pager);
            }else if(searchTypeId ==2){

                ShopInfoPage page = new ShopInfoPage();
    			page.setNowPage(pageIndex);
    			page.setPageSize(pageSize);
    			page.setLatitude(latitude);
    			page.setLongitude(longitude);
				page.setKeyword(keyword);
				page.setDmId(shopId);
//    			Pager<ShopGoods, Long> pager = this.searchListServiceImpl.searchShopGoodsList(page);
//    			map.put("pager", pager);
            }

			map.put("searchTypeId", searchTypeId);
			RespJsonPHandler.respOK(map,response, request);
	    	//插入搜索记录
	    	SearchList searchList = new SearchList();
	    	searchList.setKeyword(keyword);
	    	searchList.setSearchTypeId(searchTypeId);
	    	searchList.setSearchUser(user.getDmId());
	    	searchList.setShopId(shopId);
	    	searchList.setSearchTime(new Date().getTime());
//			this.searchListServiceImpl.addVo(searchList);
		} catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
		}
    }*/

	@ApiOperation(value = "便利店-店铺搜索",
			notes = "便利店-店铺搜索",
			position = 0)
	@RequestMapping(value = "/shopSearch", method = RequestMethod.GET)
	public void shopSearch(@ApiParam("经度") @RequestParam Double latitude,
						   @ApiParam("纬度") @RequestParam Double longitude,
						   @ApiParam("搜索关键字") @RequestParam String keyword,
						   @ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex,
						   @ApiParam("行数") @RequestParam(defaultValue = "10") Integer pageSize,
						   HttpServletRequest request,
						   HttpServletResponse response){

		SearchPage page = new SearchPage();
		page.setNowPage(pageIndex);
		page.setPageSize(pageSize);
		page.setLatitude(latitude);
		page.setLongitude(longitude);
		page.setKeyword(keyword);
		Pager<ShopSearch, Long> pager;
		try {
			pager = this.searchShopSolrService.searchShopList(page);
			RespJsonPHandler.respOK(pager,response, request);
			//插入搜索记录
			SearchList searchList = new SearchList();
			searchList.setKeyword(keyword);
			searchList.setSearchTypeId(1l);
			searchList.setSearchWays(0);
			searchList.setSearchTime(new Date().getTime());
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@ApiOperation(value = "超市-商品搜索",
			notes = "超市-商品搜索",
			position = 0)
//	@RequestMapping(value = "/supeGoodsSearch", method = RequestMethod.GET)
	@RequestMapping(value = "/supermarketGoodsSearch", method = RequestMethod.GET)
	public void supeGoodsSearch(@ApiParam("经度") @RequestParam Double latitude,
						   @ApiParam("纬度") @RequestParam Double longitude,
						   @ApiParam("搜索关键字") @RequestParam String keyword,
						   @ApiParam("页码") @RequestParam(defaultValue = "1") Integer pageIndex,
						   @ApiParam("行数") @RequestParam(defaultValue = "10") Integer pageSize,
						   HttpServletRequest request,
						   HttpServletResponse response){

		SearchPage page = new SearchPage();
		page.setNowPage(pageIndex);
		page.setPageSize(pageSize);
		page.setLatitude(latitude);
		page.setLongitude(longitude);
		page.setKeyword(keyword);
		Pager<SupeGoodsSearch, Long> pager;
		try {
			pager = this.searchShopSolrService.searchSupeGoodsList(page);
			RespJsonPHandler.respOK(pager,response, request);
			//插入搜索记录
			SearchList searchList = new SearchList();
			searchList.setKeyword(keyword);
			searchList.setSearchTypeId(2l);
			searchList.setSearchWays(1);
			searchList.setSearchTime(new Date().getTime());
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
    /**
     *
     * 查询仓储中心相关信息
     * @version 2016年6月24日上午11:06:46
     * @author  guangxi.zhang
     * @param   longitude       纬度
     * @param   latitude        经度
     * @param   request
     * @param   response
     */
    @ApiOperation(value = "查询仓储中心id", notes = "获取仓储中心id", position = 1)
    @RequestMapping(value = "/getCczxInfo", method = RequestMethod.GET)
    public void getCczxInfo(@ApiParam("经度") @RequestParam Double longitude,
            @ApiParam("纬度") @RequestParam Double latitude, HttpServletRequest request, HttpServletResponse response) {
        try {
            SearchPage page = new SearchPage();
            page.setLatitude(latitude);
            page.setLongitude(longitude);
            Object data = this.searchShopSolrService.searchCCZXInfo(page);
            RespHandler.respOK(data, response);
        }
        catch (ServiceException se) {
            log.error("通过搜索引擎查询仓储中心id失败.", se);
            RespHandler.respError(RespMsg.cczx_id_not_exist, response);
        }
        catch (Exception e) {
            log.error("通过铜鼓查询仓储中心id现系统错误.", e);
            RespHandler.respServerError(response);
        }
    }


	/**
	 * 便利店首页列表
	 *
	 * @param pageSize  页面大小
	 * @param request   request
	 * @param response  response
	 */
	@ApiOperation(value = "便利店首页列表", notes = "便利店首页列表", position = 1)
	@RequestMapping(value = "/indexList", method = RequestMethod.GET)
	public void indexList(@ApiParam("经度") @RequestParam Double latitude,
						  @ApiParam("纬度") @RequestParam Double longitude, @ApiParam("便利店经营范围") @RequestParam(required = false) String scope,
						  @ApiParam(value = "页面游标") @RequestParam(value = "pageIndex", defaultValue = "0") Integer pageIndex,
						  @ApiParam("页面大小") @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
						  HttpServletRequest request, HttpServletResponse response) {
		try {
			//获取库存信息
			SearchPage page = new SearchPage();
			page.setNowPage(pageIndex);
			page.setPageSize(pageSize);
			page.setLatitude(latitude);
			page.setLongitude(longitude);
			page.setKeyword(scope);
			Pager<ShopSearch, Long> pager;
			pager = this.searchShopSolrService.searchIndexShopList(page);
			RespJsonPHandler.respOK(pager, response, request);
		} catch (ServiceException se) {
			log.error("查询便利店信息.", se);
			//TODO 错误码尚未提供
			RespHandler.respError(RespMsg.shop_business_query_id, response);
		} catch (Exception e) {
			log.error("查询便利店信息出现系统错误.", e);
			RespHandler.respServerError(response);
		}
	}
}