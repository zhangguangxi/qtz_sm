package com.qtz.sm.goods.vo;

import com.mall.core.vo.VO;

/**
 * <p>
 * Title:GdGoodsSku
 * </p>
 * <p>
 * Description:商品SKUVO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsSku extends VO<java.lang.Long> implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1625492737050624L;

	/** 商品ID */
	private java.lang.Long goodsId;
	
	/** 商品库存ID */
	private Long stockinId;

	public GdGoodsSku() {
		super();
	}

	public GdGoodsSku(Long goodsId) {
		super();
		this.goodsId = goodsId;
	}

	public java.lang.Long getDmId() {
		return this.dmId;
	}

	public void setDmId(java.lang.Long dmId) {
		this.dmId = dmId;
	}

	public java.lang.Long getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}

	public Long getStockinId() {
		return stockinId;
	}

	public void setStockinId(Long stockinId) {
		this.stockinId = stockinId;
	}

	@Override
	public String toString() {
		return "GdGoodsSku [goodsId=" + goodsId + ", stockinId=" + stockinId + "]";
	}
}