package com.qtz.sm.goods.page;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.vo.GdGoodsSku;
/**
 * <p>Title:GdGoodsSkuPage</p>
 * <p>Description:商品SKU分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsSkuPage extends Pager<GdGoodsSku,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1625492737050625L;

		/** 主键ID */	private java.lang.Long dmId;	/** 商品ID */	private java.lang.Long goodsId;	/** 商品库存ID */	private java.lang.Long stockinId;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsId() {	    return this.goodsId;	}	public void setGoodsId(java.lang.Long goodsId) {	    this.goodsId=goodsId;	}	public java.lang.Long getStockinId() {	    return this.stockinId;	}	public void setStockinId(java.lang.Long stockinId) {	    this.stockinId=stockinId;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsId:" + getGoodsId() +"," + "stockinId:" + getStockinId() +"]";	}
}