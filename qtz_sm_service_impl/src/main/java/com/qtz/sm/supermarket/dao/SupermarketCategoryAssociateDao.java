package com.qtz.sm.supermarket.dao;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.supermarket.page.SupermarketCategoryPage;
import com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate;
import com.qtz.sm.supermarket.vo.SupermarketCategoryGoods;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Title:SupermarketCategoryAssociateDao
 * </p>
 * <p>
 * Description:超市类目与商品类目关联表DAO接口类
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
public interface SupermarketCategoryAssociateDao
        extends BizDao<com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate, Long> {
        
    /**
     * 通过超市分类ID查询关联商品分类信息
     *
     * @param supermarketCategoryId 超市分类ID
     * @return 关联商品分类列表
     * @throws DaoException
     */
    List<SupermarketCategoryAssociate> queryBySupermarketCategoryId(
            @Param("supermarketCategoryId") Long supermarketCategoryId) throws DaoException;
            
    /**
     * 删除2级超市分类关联的商品ID
     * 
     * @Description:TODO
     * @param supermarketCategoryId
     * @throws DaoException void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午4:13:00
     */
    void delByCategoryId(@Param("supermarketCategoryId") Long supermarketCategoryId) throws DaoException;
    
    /**
     * 根据超市ID 查询已经关联的商品分类
     *
     * @param supermarketId 超市ID
     * @return 关联后台分类信息列表
     * @throws ServiceException
     */
    List<SupermarketCategoryAssociate> getList(SupermarketCategoryAssociate supermarketCategoryAssociate)
            throws DaoException;
            
    /**
     * 
     * 根据超市商品分类ID获取商品相关信息
     * 
     * @version 2016年6月6日下午3:32:27
     * @author guangxi.zhang
     * @param supermarketCategoryId 超市商品分类id
     * @return 超市商品列表
     * @throws DaoException
     */
    Pager<SupermarketCategoryGoods, Long> queryGoodsInfoPageBySupermarketCategoryId(@Param("cczxId") Long cczxId,
            @Param("firstLevelSupermarketCategoryId") Long firstLevelSupermarketCategoryId,
            @Param("secondLevelSupermarketCategoryId") Long secondLevelSupermarketCategoryId) throws DaoException;
            
    /**
     * 
     * 根据条件(超市分类id,仓储中心id)查询超市商品分类下的商品信息列表
     * 
     * @author guangxi.zhang
     * @param pager 分页对象
     * @return
     * @throws DaoException 异常对象
     */
    List<SupermarketCategoryGoods> findGoodsList(SupermarketCategoryPage pager) throws DaoException;
    
    /**
     * 
     * 根据条件(超市分类id,仓储中心id)查询超市商品分类下的商品信息数量
     * 
     * @author guangxi.zhang
     * @param pager 分页对象
     * @return
     * @throws DaoException 异常对象
     */
    Integer findGoodsCount(SupermarketCategoryPage pager) throws DaoException;
    
}