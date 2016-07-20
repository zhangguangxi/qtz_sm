package com.qtz.sm.goods.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:GdGoodsSkuRatePage</p>
 * <p>Description:商品SKU议价分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-19
 */
public class GdGoodsSkuRatePage extends Pager<com.qtz.sm.goods.vo.GdGoodsSkuRate,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1669295778514945L;

	
	
	
	/**
	 * 溢价源单类型 溢价源单类型(0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市议价；)GoodsTypeRateTypeEnum
	 */
	private Integer sourceType;
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
}