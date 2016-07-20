package com.qtz.sm.goods.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:GdGoodsSkuProperty</p>
 * <p>Description:商品SKU属性值VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-29
 */
public class GdGoodsSkuProperty extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1640962642528256L;

	/** 商品SKU ID */	private java.lang.Long goodsSkuId;	/** 属性ID */	private java.lang.Long proId;	/** 属性值ID */	private java.lang.Long proValId;	/** 填写值 */	private java.lang.String otherValue;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsSkuId() {	    return this.goodsSkuId;	}	public void setGoodsSkuId(java.lang.Long goodsSkuId) {	    this.goodsSkuId=goodsSkuId;	}	public java.lang.Long getProId() {	    return this.proId;	}	public void setProId(java.lang.Long proId) {	    this.proId=proId;	}	public java.lang.Long getProValId() {	    return this.proValId;	}	public void setProValId(java.lang.Long proValId) {	    this.proValId=proValId;	}	public java.lang.String getOtherValue() {	    return this.otherValue;	}	public void setOtherValue(java.lang.String otherValue) {	    this.otherValue=otherValue;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsSkuId:" + getGoodsSkuId() +"," + "proId:" + getProId() +"," + "proValId:" + getProValId() +"," + "otherValue:" + getOtherValue() +"]";	}
}