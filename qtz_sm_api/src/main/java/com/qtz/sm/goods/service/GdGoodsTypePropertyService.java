package com.qtz.sm.goods.service;
import java.util.List;
import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
/**
 * <p>Title:GdGoodsTypePropertyService</p>
 * <p>Description:商品分类属性服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @reversion 欧江波 928482427@qq.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsTypePropertyService extends BaseService<GdGoodsTypeProperty,java.lang.Long> {
	
	/**
	 * 获取商品分类属性列表
	 * @author 欧江波 928482427@qq.com
	 * @param onlyEnable  			是否只取可用属性
	 * @param goodsTypeId			商品分类ID
	 * @param isKey					是否关键属性
	 * @param isSale				是否销售属性
	 * @param withValueOptions		是否把属性可选属性值也取出来
	 * @param withParentProps		是否父商品分类的属性也取出来
	 * @return				商品分类属性列表
	 * @throws ServiceException
	 */
	public List<GdGoodsTypeProperty> getGoodsTypePropList(Long goodsTypeId, boolean onlyEnable, boolean isKey, 
			boolean isSale, boolean withValueOptions, boolean withParentProps) throws ServiceException;
	
	/**
	 * 获取商品分类属性Map，键为商品分类属性ID，值为商品分类属性对象
	 * @author 欧江波 928482427@qq.com
	 * @param onlyEnable  			是否只取可用属性
	 * @param goodsTypeId			商品分类ID
	 * @param isKey					是否关键属性
	 * @param isSale				是否销售属性
	 * @param withValueOptions		是否把属性可选属性值也取出来
	 * @param withParentProps		是否父商品分类的属性也取出来
	 * @return
	 * @throws ServiceException
	 */
	public Map<Long, GdGoodsTypeProperty> getGoodsTypePropMap(Long goodsTypeId, boolean onlyEnable, boolean isKey, 
			boolean isSale, boolean withValueOptions, boolean withParentProps) throws ServiceException;
}