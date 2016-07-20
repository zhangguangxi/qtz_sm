package com.qtz.sm.shop.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.shop.page.ShopGoodsPage;

/**
 * <p>Title:ShopGoodsSkuDao</p>
 * <p>Description:便利店商品skuDAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
public interface ShopGoodsSkuDao extends BizDao<com.qtz.sm.shop.vo.ShopGoodsSku, Long> {

    /**
     * 增加实际库存
     *
     * @param shopSkuId  便利店商品sku ID
     * @param stock      需要增加的实际库存数
     * @param updateTime 更新时间
     * @throws DaoException
     */
    void addActualStock(@Param("shopSkuId") Long shopSkuId,
                        @Param("stock") int stock,
                        @Param("updateTime") Date updateTime) throws DaoException;

    /**
     * 减实际库存，加冻结库存
     *
     * @param shopSkuId  便利店商品sku ID
     * @param stock      需要减掉的实际库存数
     * @param updateTime 更新时间
     * @throws DaoException
     */
    void subActualStock(@Param("shopSkuId") Long shopSkuId,
                        @Param("stock") int stock,
                        @Param("updateTime") Date updateTime) throws DaoException;

    /**
     * 减冻结库存
     *
     * @param shopSkuId  便利店商品sku ID
     * @param stock      要减掉的冻结库存数
     * @param updateTime 更新时间
     * @throws DaoException
     */
    void subFreezeStock(@Param("shopSkuId") Long shopSkuId,
                        @Param("stock") int stock,
                        @Param("updateTime") Date updateTime) throws DaoException;
    
    /**
     * 根据便利店ID以及查询条件查询商品ID
     * @Description:TODO
     * @param shopGoodsSku
     * @return
     * @throws DaoException
     * List<Long>
     * @exception:
     * @author: SunXuan
     * @time:2016年6月7日 上午11:08:51
     */
    public List<Long> findGoodIdList(ShopGoodsPage shopGoodsPage) throws DaoException;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}