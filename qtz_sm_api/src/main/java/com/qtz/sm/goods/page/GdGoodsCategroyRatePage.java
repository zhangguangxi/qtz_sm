package com.qtz.sm.goods.page;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;
/**
 * <p>Title:GdGoodsCategroyRatePage</p>
 * <p>Description:商品SKU溢价率分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsCategroyRatePage extends Pager<GdGoodsCategroyRate,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1625492743899137L;

		/** 主键ID */	private java.lang.Long dmId;	/** 商品分类ID */	private java.lang.Long goodsTypeId;	/** 供应链议价 */	private java.lang.Double glyRate;	/** 云仓储管理公司议价 */	private java.lang.Double yccglRate;	/** 便利店管理公司议价 */	private java.lang.Double bldglRate;	/** 胖胖超市议价 */	private java.lang.Double ppcsRate;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsTypeId() {	    return this.goodsTypeId;	}	public void setGoodsTypeId(java.lang.Long goodsTypeId) {	    this.goodsTypeId=goodsTypeId;	}	public java.lang.Double getGlyRate() {	    return this.glyRate;	}	public void setGlyRate(java.lang.Double glyRate) {	    this.glyRate=glyRate;	}	public java.lang.Double getYccglRate() {	    return this.yccglRate;	}	public void setYccglRate(java.lang.Double yccglRate) {	    this.yccglRate=yccglRate;	}	public java.lang.Double getBldglRate() {	    return this.bldglRate;	}	public void setBldglRate(java.lang.Double bldglRate) {	    this.bldglRate=bldglRate;	}	public java.lang.Double getPpcsRate() {	    return this.ppcsRate;	}	public void setPpcsRate(java.lang.Double ppcsRate) {	    this.ppcsRate=ppcsRate;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsTypeId:" + getGoodsTypeId() +"," + "glyRate:" + getGlyRate() +"," + "yccglRate:" + getYccglRate() +"," + "bldglRate:" + getBldglRate() +"," + "ppcsRate:" + getPpcsRate() +"]";	}
}