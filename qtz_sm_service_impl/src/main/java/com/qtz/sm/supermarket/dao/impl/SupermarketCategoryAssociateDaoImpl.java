package com.qtz.sm.supermarket.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.sm.supermarket.dao.SupermarketCategoryAssociateDao;
import com.qtz.sm.supermarket.page.SupermarketCategoryPage;
import com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate;
import com.qtz.sm.supermarket.vo.SupermarketCategoryGoods;

/**
 * <p>
 * Title:SupermarketCategoryAssociateDaoImpl
 * </p>
 * <p>
 * Description:超市类目与商品类目关联表DAO实现类
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
@Repository("supermarketCategoryAssociateDaoImpl")
public class SupermarketCategoryAssociateDaoImpl
        extends MyBaitsDaoImpl<com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate, Long>
        implements SupermarketCategoryAssociateDao {
    /**
     * MYBatis命名空间名
     */
    private static String preName = SupermarketCategoryAssociateDao.class.getName();
    
    /**
     * 【取得】MYBatis命名空间名
     *
     * @return MYBatis命名空间名
     */
    @Override
    protected String getPreName() {
        return preName;
    }
    
    @Override
    public List<SupermarketCategoryAssociate> queryBySupermarketCategoryId(Long supermarketCategoryId)
            throws DaoException {
        try {
            return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryAssociateDao.class)
                    .queryBySupermarketCategoryId(supermarketCategoryId);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public void delByCategoryId(Long supermarketCategoryId) throws DaoException {
        try {
            getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryAssociateDao.class)
                    .delByCategoryId(supermarketCategoryId);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }
    
    @Override
    public List<SupermarketCategoryAssociate> getList(SupermarketCategoryAssociate supermarketCategoryAssociate)
            throws DaoException {
        try {
            return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryAssociateDao.class)
                    .getList(supermarketCategoryAssociate);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }
    
    
    @Override
    public Pager<SupermarketCategoryGoods,Long> queryGoodsInfoPageBySupermarketCategoryId(Long cczxId,Long firstLevelSupermarketCategoryId,Long secondLevelSupermarketCategoryId)
            throws DaoException {
        try {
	            return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryAssociateDao.class).queryGoodsInfoPageBySupermarketCategoryId(cczxId,firstLevelSupermarketCategoryId,secondLevelSupermarketCategoryId);
	        }
	        catch (Exception e) {
	             throw new DaoException(e);
	        }
	    }

    @Override
    public List<SupermarketCategoryGoods> findGoodsList(SupermarketCategoryPage pager) throws DaoException {
        try {
            return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryAssociateDao.class).findGoodsList(pager);
        }
        catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Integer findGoodsCount(SupermarketCategoryPage pager) throws DaoException {
        try {
            return getMyBaitsTemplate().getSqlSession().getMapper(SupermarketCategoryAssociateDao.class).findGoodsCount(pager);
        }
        catch (Exception e) {
            throw new DaoException();
        }
    }
}