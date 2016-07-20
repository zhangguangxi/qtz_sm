package com.qtz.sm.goods.model;

import java.io.Serializable;

/**
 * Title:GdGoodsSkuFilter
 * Description:商品详情页SKU过滤器
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @author 谭林清 - tanlinqingaction@126.com
 * @Reversion 欧江波 928482427@qq.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsSkuFilter extends Serializable{

	/**
	 * 返回true代表接受，false不接受
	 * @param skuPropBo	SKU属性BO对象
	 * @return
	 */
	public boolean accept(Long skuId);
}
