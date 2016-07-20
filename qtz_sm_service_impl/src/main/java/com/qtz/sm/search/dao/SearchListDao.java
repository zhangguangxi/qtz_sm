package com.qtz.sm.search.dao;
import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopInfo;
/**
 * <p>Title:SearchListDao</p>
 * <p>Description:搜索列表DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
public interface SearchListDao extends BizDao<com.qtz.sm.search.vo.SearchList,Long> {

	public void delSearchUser(@Param("searchUser") Long searchUser) throws DaoException;

	

	/**
	 * 按照定位搜索商品
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pager<ShopGoods, Long> searchShopGoodsList(ShopInfoPage page) throws DaoException;
	

    /**
     * 首页列表分页
     * @param page
     * @return
     * @throws DaoException
     */
    public Pager<ShopInfo, Long> indexQueryList(ShopInfoPage page) throws DaoException ;
    

	
	/**
	 * 按照定位搜索便利店
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pager<ShopInfo, Long> searchShopList(ShopInfoPage page) throws DaoException;
}