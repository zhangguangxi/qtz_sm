package com.qtz.sm.shop.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopSales</p>
 * <p>Description:店铺销量信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-06-01
 */
public class ShopSales extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1687923188664320L;

		/** 主键ID */	private java.lang.Long dmId;	/** 店铺ID */	private java.lang.Long shopId;	/** 店铺商品ID */	private java.lang.Long shopGoodsId;	/** 店铺skuID */	private java.lang.Long shopSkuId;	/** 销量 */	private java.lang.Long sales;	/** 更新时间 */	private java.util.Date updateTime;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getShopId() {	    return this.shopId;	}	public void setShopId(java.lang.Long shopId) {	    this.shopId=shopId;	}	public java.lang.Long getShopGoodsId() {	    return this.shopGoodsId;	}	public void setShopGoodsId(java.lang.Long shopGoodsId) {	    this.shopGoodsId=shopGoodsId;	}	public java.lang.Long getShopSkuId() {	    return this.shopSkuId;	}	public void setShopSkuId(java.lang.Long shopSkuId) {	    this.shopSkuId=shopSkuId;	}	public java.lang.Long getSales() {	    return this.sales;	}	public void setSales(java.lang.Long sales) {	    this.sales=sales;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopId:" + getShopId() +"," + "shopGoodsId:" + getShopGoodsId() +"," + "shopSkuId:" + getShopSkuId() +"," + "sales:" + getSales() +"," + "updateTime:" + getUpdateTime() +"]";	}
}