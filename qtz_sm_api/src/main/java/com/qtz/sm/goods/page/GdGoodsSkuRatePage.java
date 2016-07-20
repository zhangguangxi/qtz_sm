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

		/** 主键ID */	private java.lang.Long dmId;	/** 商品ID */	private java.lang.Long skuId;	/** 供应链议价率 */	private java.lang.Double gylRate;	/** 云仓储管理公司议价率 */	private java.lang.Double yccglRate;	/** 便利店管理公司议价率 */	private java.lang.Double bldglRate;	/** 胖胖超市议价率 */	private java.lang.Double ppcsRate;	/** 供应链议价后价格 */	private java.lang.Double gylPrice;	/** 云仓储管理公司议价后价格 */	private java.lang.Double yccglPrice;	/** 便利店管理公司议价后价格 */	private java.lang.Double bldglPrice;	/** 胖胖超市议价后价格 */	private java.lang.Double ppcsPrice;
	
	
	/**
	 * 溢价源单类型 溢价源单类型(0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市议价；)GoodsTypeRateTypeEnum
	 */
	private Integer sourceType;
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getSkuId() {	    return this.skuId;	}	public void setSkuId(java.lang.Long skuId) {	    this.skuId=skuId;	}	public java.lang.Double getGylRate() {	    return this.gylRate;	}	public void setGylRate(java.lang.Double gylRate) {	    this.gylRate=gylRate;	}	public java.lang.Double getYccglRate() {	    return this.yccglRate;	}	public void setYccglRate(java.lang.Double yccglRate) {	    this.yccglRate=yccglRate;	}	public java.lang.Double getBldglRate() {	    return this.bldglRate;	}	public void setBldglRate(java.lang.Double bldglRate) {	    this.bldglRate=bldglRate;	}	public java.lang.Double getPpcsRate() {	    return this.ppcsRate;	}	public void setPpcsRate(java.lang.Double ppcsRate) {	    this.ppcsRate=ppcsRate;	}	public java.lang.Double getGylPrice() {	    return this.gylPrice;	}	public void setGylPrice(java.lang.Double gylPrice) {	    this.gylPrice=gylPrice;	}	public java.lang.Double getYccglPrice() {	    return this.yccglPrice;	}	public void setYccglPrice(java.lang.Double yccglPrice) {	    this.yccglPrice=yccglPrice;	}	public java.lang.Double getBldglPrice() {	    return this.bldglPrice;	}	public void setBldglPrice(java.lang.Double bldglPrice) {	    this.bldglPrice=bldglPrice;	}	public java.lang.Double getPpcsPrice() {	    return this.ppcsPrice;	}	public void setPpcsPrice(java.lang.Double ppcsPrice) {	    this.ppcsPrice=ppcsPrice;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "skuId:" + getSkuId() +"," + "gylRate:" + getGylRate() +"," + "yccglRate:" + getYccglRate() +"," + "bldglRate:" + getBldglRate() +"," + "ppcsRate:" + getPpcsRate() +"," + "gylPrice:" + getGylPrice() +"," + "yccglPrice:" + getYccglPrice() +"," + "bldglPrice:" + getBldglPrice() +"," + "ppcsPrice:" + getPpcsPrice() +"]";	}
}