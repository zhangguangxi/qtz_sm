package com.qtz.sm.goods.service;
import java.util.List;
import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.vo.GdGoodsPropertyVal;
/**
 * <p>Title:GdGoodsPropertyValService</p>
 * <p>Description:商品属性实际值服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsPropertyValService extends BaseService<GdGoodsPropertyVal,java.lang.Long> {
	
	/**
	 * 根据商品ID获取商品属性BO列表
	 * @author 欧江波 928482427@qq.com
	 * @param goodsId 商品ID
	 * @return 商品属性BO列表
	 * @throws ServiceException
	 */
	public List<GdGoodsPropertyBo> getGoodsPropBoList(Long goodsTypeId, Long goodsId) throws ServiceException;
	

	/**
	 * 根据商品分类ID和商品ID获取商品属性BO_MAP,键为属性ID，值为属性_BO列表
	 * @author 欧江波 928482427@qq.com
	 * @param goodsTypeId 商品分类ID
	 * @param goodsId 商品ID
	 * @return 商品属性BO列表
	 * @throws ServiceException
	 */
	public Map<Long, List<GdGoodsPropertyBo>> getGoodsPropBoMap(Long goodsTypeId, Long goodsId) throws ServiceException;
}