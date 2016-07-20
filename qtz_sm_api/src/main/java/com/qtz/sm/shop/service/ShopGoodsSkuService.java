package com.qtz.sm.shop.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;

/**
 * <p>Title:ShopGoodsSkuService</p>
 * <p>Description:便利店商品sku服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
public interface ShopGoodsSkuService extends BaseService<com.qtz.sm.shop.vo.ShopGoodsSku, Long> {

    /**
     * 增加实际库存
     *
     * @param shopSkuId 便利店商品sku ID
     * @param stock     需要增加的实际库存数
     * @throws ServiceException
     */
    void modAddActualStock(Long shopSkuId, int stock) throws ServiceException;

    /**
     * 减实际库存，加冻结库存
     *
     * @param shopSkuId 便利店商品sku ID
     * @param stock     需要减掉的实际库存数
     * @throws ServiceException
     */
    void modSubActualStock(Long shopSkuId, int stock) throws ServiceException;

    /**
     * 减冻结库存
     *
     * @param shopSkuId 便利店商品sku ID
     * @param stock     要减掉的冻结库存数
     * @throws ServiceException
     */
    void modSubFreezeStock(Long shopSkuId, int stock) throws ServiceException;
}