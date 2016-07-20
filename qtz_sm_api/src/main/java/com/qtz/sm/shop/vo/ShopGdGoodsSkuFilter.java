package com.qtz.sm.shop.vo;

import java.util.HashMap;
import java.util.Map;

import com.qtz.sm.goods.model.GdGoodsSkuFilter;

/**
 * 
 * @Description:过滤sku
 * @author:SunXuan
 * @time:2016年6月6日 下午6:38:18
 */
public class ShopGdGoodsSkuFilter implements GdGoodsSkuFilter {

	
	
	/** 
	 * @Description: serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	private static final long serialVersionUID = 1L;
	Map<Long, Integer> skuStockMap = new HashMap<>();
	@Override
	public boolean accept(Long skuId) {
		if (skuStockMap.containsKey(skuId)){
			return true;
		}
		return false;
	}
	
	public ShopGdGoodsSkuFilter(Map<Long, Integer> skuStockMap){
		this.skuStockMap = skuStockMap;
	}

}
