package com.qtz.sm.store.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:CsCczxStockPage</p>
 * <p>Description:仓储中心库存分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-17
 */
public class CsCczxStockPage extends Pager<com.qtz.sm.store.vo.CsCczxStock,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1666611934578689L;

		/** 主键ID */	private java.lang.Long dmId;	/** 供应商ID */	private java.lang.Long gysId;	/** 商品SKU */	private java.lang.Long skuId;	/** 数量 */	private java.lang.Integer stockQuantity;	/** 待配送数量 */	private java.lang.Integer awaitQuantity;	/** 在售状态(0在售,1停售) */	private java.lang.Integer isOnsale;	/** 上架时间 */	private java.lang.Long onsaleTime;
	/**仓储中心ID**/
	private Long cczxId;
		public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGysId() {	    return this.gysId;	}	public void setGysId(java.lang.Long gysId) {	    this.gysId=gysId;	}	public java.lang.Long getSkuId() {	    return this.skuId;	}	public void setSkuId(java.lang.Long skuId) {	    this.skuId=skuId;	}	public java.lang.Integer getStockQuantity() {	    return this.stockQuantity;	}	public void setStockQuantity(java.lang.Integer stockQuantity) {	    this.stockQuantity=stockQuantity;	}	public java.lang.Integer getAwaitQuantity() {	    return this.awaitQuantity;	}	public void setAwaitQuantity(java.lang.Integer awaitQuantity) {	    this.awaitQuantity=awaitQuantity;	}	public java.lang.Integer getIsOnsale() {	    return this.isOnsale;	}	public void setIsOnsale(java.lang.Integer isOnsale) {	    this.isOnsale=isOnsale;	}	public java.lang.Long getOnsaleTime() {	    return this.onsaleTime;	}	public void setOnsaleTime(java.lang.Long onsaleTime) {	    this.onsaleTime=onsaleTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "gysId:" + getGysId() +"," + "skuId:" + getSkuId() +"," + "stockQuantity:" + getStockQuantity() +"," + "awaitQuantity:" + getAwaitQuantity() +"," + "isOnsale:" + getIsOnsale() +"," + "onsaleTime:" + getOnsaleTime() +"]";	}
	public Long getCczxId() {
		return cczxId;
	}
	public void setCczxId(Long cczxId) {
		this.cczxId = cczxId;
	}
}