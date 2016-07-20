package com.qtz.sm.search.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.search.vo.Search;
import com.qtz.sm.shop.page.ShopGoodsPage;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopInfo;
/**
 * <p>Title:SearchListService</p>
 * <p>Description:搜索列表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
public interface SearchListService extends BaseService<com.qtz.sm.search.vo.SearchList,Long> {
	/**
	 * 按照搜索人删除搜索记录
	 * @param searchUser
	 * @throws ServiceException
	 */
	public void delSearchUser(Long searchUser) throws ServiceException;
	
	/**
	 * 按照定位搜索便利店
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pager<ShopInfo, Long> searchShopList(ShopInfoPage page) throws ServiceException;



	/**
	 * 按照定位搜索便利店商品
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pager<ShopGoods, Long> searchShopGoodsList(ShopInfoPage page) throws ServiceException;

	/**
	 * 按照定位搜索商品
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pager<Search, Long> searchGoodsList(SearchPage page) throws ServiceException;

	/**
	 * 首页获取便利店列表
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pager<ShopInfo, Long> indexQueryList(ShopInfoPage page) throws ServiceException;
	

	
}