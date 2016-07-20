package com.qtz.sm.batch.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:CsBatGoodsSkuPage</p>
 * <p>Description:批发单商品与SKU关系分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
public class CsBatGoodsSkuPage extends Pager<com.qtz.sm.batch.vo.CsBatGoodsSku,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1660901532633089L;

		/** 主键ID */	private java.lang.Long dmId;	/** 批发单商品ID */	private java.lang.Long batGoodsId;	/** 规格ID */	private java.lang.Long skuId;	/** 批发数量 */	private java.lang.Integer batNum;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getBatGoodsId() {	    return this.batGoodsId;	}	public void setBatGoodsId(java.lang.Long batGoodsId) {	    this.batGoodsId=batGoodsId;	}	public java.lang.Long getSkuId() {	    return this.skuId;	}	public void setSkuId(java.lang.Long skuId) {	    this.skuId=skuId;	}	public java.lang.Integer getBatNum() {	    return this.batNum;	}	public void setBatNum(java.lang.Integer batNum) {	    this.batNum=batNum;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "batGoodsId:" + getBatGoodsId() +"," + "skuId:" + getSkuId() +"," + "batNum:" + getBatNum() +"]";	}
}