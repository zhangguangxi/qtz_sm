package com.qtz.sm.shop.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:ShopSalesPage</p>
 * <p>Description:店铺销量信息分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
public class ShopSalesPage extends Pager<com.qtz.sm.shop.vo.ShopSales,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1682050935277569L;

		/** 主键ID */	private java.lang.Long dmId;	/** 店铺ID */	private java.lang.Long shopId;	/** 商品ID */	private java.lang.Long goodsId;	/** skuID */	private java.lang.Long skuId;	/** 销量 */	private java.lang.Long sales;	/** 更新时间 */	private java.util.Date updateTime;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getShopId() {	    return this.shopId;	}	public void setShopId(java.lang.Long shopId) {	    this.shopId=shopId;	}	public java.lang.Long getGoodsId() {	    return this.goodsId;	}	public void setGoodsId(java.lang.Long goodsId) {	    this.goodsId=goodsId;	}	public java.lang.Long getSkuId() {	    return this.skuId;	}	public void setSkuId(java.lang.Long skuId) {	    this.skuId=skuId;	}	public java.lang.Long getSales() {	    return this.sales;	}	public void setSales(java.lang.Long sales) {	    this.sales=sales;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopId:" + getShopId() +"," + "goodsId:" + getGoodsId() +"," + "skuId:" + getSkuId() +"," + "sales:" + getSales() +"," + "updateTime:" + getUpdateTime() +"]";	}
}