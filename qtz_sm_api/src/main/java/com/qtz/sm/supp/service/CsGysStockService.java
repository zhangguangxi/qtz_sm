package com.qtz.sm.supp.service;

import java.util.List;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
import com.qtz.sm.supp.vo.CsGysStock;

/**
 * Title:CsGysStockService<br/>
 * Description:(供应商商品库存SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGysStockService extends BaseService<CsGysStock,Long>{

	
    /**
     * 供应商----扣减库存
     * @param skuId
     * @param deductionsQuantity
     * @throws ServiceException
     */
    
     void gysDeductionsStock(Long skuId,Integer deductionsQuantity,Integer status)throws ServiceException;
     
     /**
 	 * 根据商品ID获取供应商商品库存价格列表
 	 * @author 欧江波 928482427@qq.com
 	 * @param goodsId	商品ID
 	 * @return
 	 * @throws DaoException
 	 */
 	List<CsGysStock> getStocksByGoodsId(Long goodsId) throws ServiceException;

 	/**
 	 * 根据供应商ID和SKU ID更新对应sku库存信息
 	 * @author yangwei
 	 * @param sku	批发单SKU信息
 	 * @param orderStatus	批发单状态
 	 * @return
 	 * @throws ServiceException
 	 */
	void updateStock(CsBatGoodsSku sku, int orderStatus) throws ServiceException;
	
	
	List<CsGysStock> queryFindList(CsGysStock csGysStock) throws ServiceException;
}