package com.qtz.sm.search.dao.impl;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.mall.core.common.Global;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.sm.search.dao.SearchListDao;
import com.qtz.sm.search.vo.SearchList;
import com.qtz.sm.shop.page.ShopInfoPage;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopInfo;
import com.qtz.sm.supp.dao.CsGysInfoDao;
/**
 * <p>Title:SearchListDaoImpl</p>
 * <p>Description:搜索列表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
@Repository("searchListDaoImpl")
public class SearchListDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.search.vo.SearchList,Long> implements SearchListDao {
	/**MYBatis命名空间名*/
	private static String preName = SearchListDao.class.getName();
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
	public Pager<ShopGoods, Long> searchShopGoodsList(ShopInfoPage page) throws DaoException {
		Pager<ShopGoods, Long> flowPage = new Pager<ShopGoods, Long>();
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
			page.setRowCount(rowCount);
			List<ShopGoods> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	

	@Override
	public Pager<ShopInfo, Long> indexQueryList(ShopInfoPage page) throws DaoException {
		Pager<ShopInfo, Long> flowPage = new Pager<ShopInfo, Long>();
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
			page.setRowCount(rowCount);
			List<ShopInfo> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public Pager<ShopInfo, Long> searchShopList(ShopInfoPage page) throws DaoException {
		Pager<ShopInfo, Long> flowPage = new Pager<ShopInfo, Long>();
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
			page.setRowCount(rowCount);
			List<ShopInfo> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}