package com.qtz.sm.common.solr;


import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.search.vo.Search;
import com.qtz.sm.search.vo.ShopSearch;
import com.qtz.sm.search.vo.SupeGoodsSearch;
import com.qtz.sm.shop.page.ShopInfoPage;

import java.util.List;
import java.util.Map;

/**
 * Title:CsRegionsServiceImpl<br/>
 * Description:(搜索引擎类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 郭云龙 252735833@qq.com
 * @version v1.0 2016-06-03
 */

public interface SearchShopSolrService {
    /**
     * 根据搜索信息查询搜索列表
     *
     * @param page
     * @return
     * @throws ServiceException
     */
/*    public List<Map<String, Object>> search(ShopInfoPage page);*/
    /**
     * 根据搜索信息查询搜索便利店
     *
     * @param page
     * @return
     * @throws ServiceException
     */
    public Pager<ShopSearch, Long> searchShopList(SearchPage page) throws ServiceException;

    /**
     * 根据定位信息查询搜索便利店首页
     *
     * @param page
     * @return
     * @throws ServiceException
     */
    public Pager<ShopSearch, Long> searchIndexShopList(SearchPage page) throws ServiceException;


    /**
     * 根据搜索信息查询搜索超市商品
     *
     * @param page
     * @return
     * @throws ServiceException
     */
    public Pager<SupeGoodsSearch, Long> searchSupeGoodsList(SearchPage page) throws ServiceException;


    /**
     * 根据经纬度获取仓储中心信息
     *
     * @param page
     * @return
     * @throws ServiceException
     */
    public Object searchCCZXInfo(SearchPage page)throws ServiceException;
}
