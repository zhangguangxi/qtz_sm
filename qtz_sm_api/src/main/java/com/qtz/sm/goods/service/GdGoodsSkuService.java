package com.qtz.sm.goods.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.model.GdGoodsSkuPropBo;
import com.qtz.sm.goods.vo.GdGoodsSku;
/**
 * <p>Title:GdGoodsSkuService</p>
 * <p>Description:商品SKU服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsSkuService extends BaseService<GdGoodsSku,java.lang.Long> {
	
	/**
	 * 【获取商品SKU属性BO列表】，已经添加的组合
	 * @param goodsTypeId	商品分类ID
	 * @param goodsId		商品ID
	 * @return
	 * @throws ServiceException
	 */
	public List<GdGoodsSkuPropBo> getSkuPropBoList(Long goodsTypeId, Long goodsId) throws ServiceException;
	
	/**
	 * 【获取商品所有SKU组合列表】，包括（尚未添加到商品SKU表）中的组合，返回结果类似[ [黑色，XL],[黑色, L],... ]
	 * @param goodsTypeId	商品分类ID
	 * @param goodsId		商品ID
	 * @param propCombines	属性组，类似[ [黑色,白色], [XL,L] ]
	 * @return
	 * @throws ServiceException
	 */
	public List<GdGoodsSkuPropBo> getAllSkuPropBoList(Long goodsTypeId, Long goodsId, List<List<GdGoodsPropertyBo>> propCombines) throws ServiceException;
	
	/**
	 * 获取SKU属性值串，形如“黑色,XL”
	 * @param skuPropList  
	 * @return			属性值ID串，属性值名称串
	 */
	public String[/**属性值ID串，属性值名称串*/]  getSkuValueStr(List<GdGoodsPropertyBo> skuPropList) throws ServiceException;
	
	
}