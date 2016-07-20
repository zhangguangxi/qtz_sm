package com.qtz.sm.search.dao.impl;

import com.mall.core.common.Global;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.sm.search.dao.SearchDao;
import com.qtz.sm.search.dao.SearchListDao;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.search.vo.Search;
import com.qtz.sm.search.vo.SearchList;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title:SearchListDaoImpl</p>
 * <p>Description:搜索列表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
@Repository("searchDaoImpl")
public class SearchDaoImpl extends MyBaitsDaoImpl<SearchList,Long> implements SearchDao {


	/**MYBatis命名空间名*/
	private static String preName = SearchDao.class.getName();
	/**
	 * 【取得】MYBatis命名空间名
	 * @return  	MYBatis命名空间名
	 */
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public void delSearchUser(@Param("searchUser") Long searchUser) throws DaoException {
		try{
			getMyBaitsTemplate().getSqlSession().getMapper(SearchListDao.class).delSearchUser(searchUser);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	@Override
	public Pager<Search, Long> searchShopGoodsList(SearchPage page) throws DaoException {
		Pager<Search, Long> flowPage = new Pager<Search, Long>();
		flowPage.setPageSize(page.getPageSize());
		flowPage.setNowPage(page.getNowPage());
		try {
			String sqlidOne = "searchShopGoodsCount";
			String sqlidTwo = "searchShopGoodsList";
			if (null != preName && !preName.equals("")) {
				sqlidOne = preName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != preName && !preName.equals("")) {
				sqlidTwo = preName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getMyBaitsTemplate().getSqlSession().selectOne(sqlidOne, page);
			List<Search> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	@Override
	public Pager<Search, Long> searchGoodsList(SearchPage page) throws DaoException {
		Pager<Search, Long> flowPage = new Pager<Search, Long>();
		flowPage.setPageSize(page.getPageSize());
		flowPage.setNowPage(page.getNowPage());
		try {
			String sqlidOne = "searchGoodsCount";
			String sqlidTwo = "searchGoodsList";
			if (null != preName && !preName.equals("")) {
				sqlidOne = preName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != preName && !preName.equals("")) {
				sqlidTwo = preName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getMyBaitsTemplate().getSqlSession().selectOne(sqlidOne, page);
			List<Search> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}


	@Override
	public Pager<Search, Long> searchShopList(SearchPage page) throws DaoException {
		Pager<Search, Long> flowPage = new Pager<Search, Long>();
		flowPage.setPageSize(page.getPageSize());
		flowPage.setNowPage(page.getNowPage());
		try {
			String sqlidOne = "searchShopCount";
			String sqlidTwo = "searchShopList";
			if (null != preName && !preName.equals("")) {
				sqlidOne = preName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != preName && !preName.equals("")) {
				sqlidTwo = preName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getMyBaitsTemplate().getSqlSession().selectOne(sqlidOne, page);
			List<Search> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pager<Search, Long> indexQueryList(SearchPage page) throws DaoException {
		Pager<Search, Long> flowPage = new Pager<Search, Long>();
		flowPage.setPageSize(page.getPageSize());
		flowPage.setNowPage(page.getNowPage());
		try {
			String sqlidOne = "indexQueryCount";
			String sqlidTwo = "indexQueryList";
			if (null != preName && !preName.equals("")) {
				sqlidOne = preName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != preName && !preName.equals("")) {
				sqlidTwo = preName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getMyBaitsTemplate().getSqlSession().selectOne(sqlidOne, page);
			List<Search> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}