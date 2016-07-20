package com.qtz.sm.goods.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:GdGoodsPropertyVal</p>
 * <p>Description:商品属性实际值VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-22
 */
public class GdGoodsPropertyVal extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1717223556302848L;

		/** 主键ID */	private java.lang.Long dmId;	/** 商品ID */	private java.lang.Long goodsId;	/** 自定义KEY */	private java.lang.String otherKey;	/** 商品分类属性ID */	private java.lang.Long goodsTypeProId;	/** 主键ID */	private java.lang.Long goodsTypeProValId;	/** 填写值 */	private java.lang.String otherValue;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsId() {	    return this.goodsId;	}	public void setGoodsId(java.lang.Long goodsId) {	    this.goodsId=goodsId;	}	public java.lang.String getOtherKey() {	    return this.otherKey;	}	public void setOtherKey(java.lang.String otherKey) {	    this.otherKey=otherKey;	}	public java.lang.Long getGoodsTypeProId() {	    return this.goodsTypeProId;	}	public void setGoodsTypeProId(java.lang.Long goodsTypeProId) {	    this.goodsTypeProId=goodsTypeProId;	}	public java.lang.Long getGoodsTypeProValId() {	    return this.goodsTypeProValId;	}	public void setGoodsTypeProValId(java.lang.Long goodsTypeProValId) {	    this.goodsTypeProValId=goodsTypeProValId;	}	public java.lang.String getOtherValue() {	    return this.otherValue;	}	public void setOtherValue(java.lang.String otherValue) {	    this.otherValue=otherValue;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsId:" + getGoodsId() +"," + "otherKey:" + getOtherKey() +"," + "goodsTypeProId:" + getGoodsTypeProId() +"," + "goodsTypeProValId:" + getGoodsTypeProValId() +"," + "otherValue:" + getOtherValue() +"]";	}
}