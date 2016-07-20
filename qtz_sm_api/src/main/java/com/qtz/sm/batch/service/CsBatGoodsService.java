package com.qtz.sm.batch.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.batch.vo.CsBatGoods;

/**
 * Title:CsBatGoodsService<br/>
 * Description:(批发单商品SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-21
 */
public interface CsBatGoodsService extends BaseService<CsBatGoods,Long>{

	/**
	 * 根据批发单ID获取商品详情信息
	 * @author yangwei
	 * @param dmId		批发单ID
	 * @throws ServiceException
	 */
	List<CsBatGoods> getGoodsInfoByOrderId(Long dmId)throws ServiceException;

}