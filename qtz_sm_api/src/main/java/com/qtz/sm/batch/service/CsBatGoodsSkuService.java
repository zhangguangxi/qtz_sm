package com.qtz.sm.batch.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.batch.vo.CsBatGoodsSku;

/**
 * Title:CsBatGoodsSkuService<br/>
 * Description:(批发单商品属性值SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-21
 */
public interface CsBatGoodsSkuService extends BaseService<CsBatGoodsSku,Long>{

	/**
	 * 根据批发单下面商品ID获取商品SKU详情信息
	 * @author yangwei
	 * @param dmId		批发单下面商品ID
	 * @throws ServiceException
	 */
	List<CsBatGoodsSku> getSkuInfoByDmId(Long dmId)throws ServiceException;

	/**
	 * 根据批发单ID获取商品SKU信息(用户更新对应SKU的库存)
	 * @author yangwei
	 * @param dmId		批发单ID
	 * @throws ServiceException
	 */
	List<CsBatGoodsSku> findListByBatOrderId(Long batOrderId)throws ServiceException;

}