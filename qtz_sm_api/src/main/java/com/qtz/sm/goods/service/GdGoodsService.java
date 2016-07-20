package com.qtz.sm.goods.service;

import java.util.List;
import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.enums.GoodsStatusEnum;
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.model.GdGoodsSkuFilter;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.shop.vo.ShopValueVo;

/**
 * Title:GdGoodsService
 * Description:商品服务接口类
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @author 谭林清 - tanlinqingaction@126.com
 * @Reversion 欧江波 928482427@qq.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsService extends BaseService<GdGoods, java.lang.Long> {

	/**
	 * 更新商品状态
	 * @param goodsId		商品名称
	 * @param newGoodsStatusEnum	新状态
	 * @throws ServiceException
	 */
	void updateGoodsStatus(Long goodsId, GoodsStatusEnum newGoodsStatusEnum) throws ServiceException;
	
	/////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 添加商品
	 * @author 欧江波 928482427@qq.com
	 * @param goodsOut		商品对象
	 * @param supplierId	供应商ID
	 * @throws ServiceException
	 */
	void addGoods(GdGoodsOutJson goodsOut, Long supplierId) throws ServiceException;

	/**
	 * 获取商品详情
	 * @author 欧江波 928482427@qq.com
	 * @param goodsId 			商品ID
	 * @param skuPriceMap		SKU价格信息
	 * @param skuStockMap 		SKU库存信息
	 * @param withPropConfig 	是否包含属性配置信息
	 * @param withAllSku		是否包含所有的SKU，包括尚未添加到商品SKU表中的SKU属性值组合
	 * @param  skuFilter		sku过滤器，只返回通过检测的SKU
	 * @param extraInfo 		附加额外信息，需要实现序列号接口
	 * @throws ServiceException
	 */
	GdGoodsOutJson getGoodsDetail(Long goodsId, Map<Long, Double> skuPriceMap, Map<Long, Integer> skuStockMap, boolean withPropConfig, boolean withAllSku, Object extraInfo, GdGoodsSkuFilter skuFilter) throws ServiceException;
	
	/**
	 * 编辑商品详情
	 * @author 欧江波 928482427@qq.com
	 * @param goodsOut		商品对象
	 * @param supplierId	供应商ID
	 * @throws ServiceException
	 */
	Integer modGoods(GdGoodsOutJson goodsOut, Long supplierId, String ip, Long operator) throws ServiceException;

	/**
	 * 根据商品ID获取属性列
	 * @author yangwei
	 * @param goodsId		商品ID
	 * @throws ServiceException
	 */
	List<ShopValueVo> findSkuList(Long goodsId)throws ServiceException;
	
	/**
	 * 校验SKU组合
	 * @param skuId			SKU编号	
	 * @param valueStr		SKU属性值ID串
	 * @return
	 * @throws ServiceException
	 */
	//boolean checkSkuCombine(Long skuId, String valueIdStr) throws ServiceException;
	
	/**
	 * 校验SKU价格
	 * @param skuId
	 * @param newPrice
	 * @return
	 * @throws ServiceException
	 */
	//boolean checkSkuPrice(Long skuId, double newPrice) throws ServiceException;
	
}
