package com.qtz.sm.supermarket.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.solr.SearchShopSolrService;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.supermarket.dao.SupermarketCategoryAssociateDao;
import com.qtz.sm.supermarket.page.SupermarketCategoryPage;
import com.qtz.sm.supermarket.service.SupermarketCategoryAssociateService;
import com.qtz.sm.supermarket.service.SupermarketCategoryService;
import com.qtz.sm.supermarket.vo.SupermarketCategory;
import com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate;
import com.qtz.sm.supermarket.vo.SupermarketCategoryGoods;

/**
 * <p>
 * Title:SupermarketCategoryAssociateServiceImpl
 * </p>
 * <p>
 * Description:超市类目与商品类目关联表服务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 *
 * @author Sunxuan - Laven
 * @version v1.0 2016-04-29
 */
@Service("supermarketCategoryAssociateServiceImpl")
public class SupermarketCategoryAssociateServiceImpl
		extends BaseServiceImpl<com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate, Long>
		implements SupermarketCategoryAssociateService {
	/**
	 * 初始化日志对象
	 */
	private static LogTool log = LogTool.getInstance(SupermarketCategoryAssociateServiceImpl.class);
	/**
	 * 注入超市类目与商品类目关联表DAO接口类
	 */
	@Resource(name = "supermarketCategoryAssociateDaoImpl")
	private SupermarketCategoryAssociateDao dao;

	@Resource(name = "supermarketCategoryServiceImpl")
	private SupermarketCategoryService supermarketCategoryService;
	
	@Resource(name="searchShopSolrServiceImpl")
	private SearchShopSolrService searchShopSolrService;

	/**
	 * 【取得】业务DAO对象
	 *
	 * @return 业务DAO对象
	 */
	@Override
	protected BizDao<com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate, Long> getDao() {
		return dao;
	}

	/**
	 * 【取得】日志对象
	 *
	 * @return 日志对象
	 */
	@Override
	protected LogTool getLog() {
		return log;
	}

	@Override
	public List<SupermarketCategoryAssociate> findBySupermarketCategoryId(Long supermarketCategoryId)
			throws ServiceException {
		try {
			return dao.queryBySupermarketCategoryId(supermarketCategoryId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	// Service Manage
	@Override
	public void addCategoryAssociate(Long supermarketCategoryId, List<Long> categoryIds) throws ServiceException {
		// 超市叶子分类关联后台叶子分类
		// 1、超市分类必须是叶子分类
		// 2、后台分类也必须是叶子分类
		// 3、后台叶子分类如果已经被当前超市其他叶子分类关联，则不能再被关联
		SupermarketCategory supermarketCategory = supermarketCategoryService.findVo(supermarketCategoryId, null);
		if (2 != supermarketCategory.getLevel().intValue()) {
			throw new ServiceException("超市分类必须是叶子分类才能关联后台叶子分类！");
		}
		List<SupermarketCategoryAssociate> addAssociateList = new ArrayList<>();
		for (Long categoryId : categoryIds) {
			// TODO 确定是后台叶子分类
			// 确定没有被当前超市的其他分类关联过，因目前只有一个超市，so,不用关联超市查询。
			SupermarketCategoryAssociate supermarketCategoryAssociate = new SupermarketCategoryAssociate();
			supermarketCategoryAssociate.setGoodsCategoryId(categoryId);
			List<SupermarketCategoryAssociate> supermarketCategoryAssociateList = this
					.findList(supermarketCategoryAssociate);
			if (supermarketCategoryAssociateList != null && supermarketCategoryAssociateList.size() >= 0) {
				throw new ServiceException("超市分类必须是叶子分类才能关联后台叶子分类！");
			} else {
				supermarketCategoryAssociate.setSupermarketCategoryId(supermarketCategoryId);
				addAssociateList.add(supermarketCategoryAssociate);
			}
		}
		this.addList(addAssociateList);
	}

	@Override
	public void addCategoryAssociate(SupermarketCategory supermarketCategory2) throws ServiceException {
		try {
			// 先删除,后添加
			this.dao.delByCategoryId(supermarketCategory2.getDmId());
			//后添加
			this.addList(supermarketCategory2.getSupermarketCategoryAssociateList());
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public List<SupermarketCategoryAssociate> getList(SupermarketCategoryAssociate supermarketCategoryAssociate) throws ServiceException {
		try {
			return dao.getList(supermarketCategoryAssociate);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

    @Override
    public Pager<SupermarketCategoryGoods,Long> queryGoodsInfoPageBySupermarketCategoryId(SupermarketCategoryPage supermarketCategoryPage)
            throws ServiceException {
        Pager<SupermarketCategoryGoods, Long> pager = new Pager<SupermarketCategoryGoods,Long>();
       try {
           pager.setNowPage(supermarketCategoryPage.getNowPage());
           pager.setPageSize(supermarketCategoryPage.getPageSize());
           pager.setOrderField(supermarketCategoryPage.getOrderField());
           pager.setOrderDirection(false);
         //通过solr获取"仓储中心Id"
           SearchPage searchPage = new SearchPage();
           searchPage.setLatitude(supermarketCategoryPage.getLatitude());
           searchPage.setLongitude(supermarketCategoryPage.getLongitude());
           Map<String,Object> map = (  Map<String,Object>)searchShopSolrService.searchCCZXInfo(searchPage);
           long cczxId = Long.parseLong(map.get("cczxID").toString());
           supermarketCategoryPage.setCczxId(cczxId);
           List<SupermarketCategoryGoods>  list = dao.findGoodsList(supermarketCategoryPage);
           if(list == null || list.size() == 0){
               pager.setRowCount(0);
               pager.setList(new ArrayList<SupermarketCategoryGoods>());
               return pager;
           }
           Integer rowCount = dao.findGoodsCount(supermarketCategoryPage);
           pager.setRowCount(rowCount);
           pager.setList(list);
           return pager;
       }
        catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } 
    }
	
}