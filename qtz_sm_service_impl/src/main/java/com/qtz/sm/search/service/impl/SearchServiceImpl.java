package com.qtz.sm.search.service.impl;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.search.dao.SearchDao;
import com.qtz.sm.search.dao.SearchListDao;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.search.service.SearchListService;
import com.qtz.sm.search.service.SearchService;
import com.qtz.sm.search.vo.Search;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>Title:SearchListServiceImpl</p>
 * <p>Description:搜索列表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
@Service("searchServiceImpl")
public class SearchServiceImpl extends BaseServiceImpl<com.qtz.sm.search.vo.SearchList,Long> implements SearchService {


	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(SearchServiceImpl.class);
	/**注入搜索列表DAO接口类*/
	@Resource(name="searchDaoImpl")
	private SearchDao dao;
	/**
	 * 【取得】业务DAO对象
	 * @return 	业务DAO对象
	 */
	@Override
	protected BizDao<com.qtz.sm.search.vo.SearchList,Long> getDao() {
		return dao;
	}
	/**
	 * 【取得】日志对象
	 * @return 	日志对象
	 */
	@Override
	protected LogTool getLog() {
		return log;
	}
	@Override
	public void delSearchUser(Long searchUser) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			this.dao.delSearchUser(searchUser);
		} catch (DaoException e) {
			throw new ServiceException();
		}
	}
	@Override
	public Pager<Search, Long> searchShopList(SearchPage page) throws ServiceException {
		try {
			return dao.searchShopList(page);
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Pager<Search, Long> searchShopGoodsList(SearchPage page) throws ServiceException {
		try {
			return dao.searchShopGoodsList(page);
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}


	@Override
	public Pager<Search, Long> searchGoodsList(SearchPage page) throws ServiceException {
		try {
			return dao.searchGoodsList(page);
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}


	@Override
	public Pager<Search, Long> indexQueryList(SearchPage page) throws ServiceException {
		try {
			return dao.indexQueryList(page);
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
}