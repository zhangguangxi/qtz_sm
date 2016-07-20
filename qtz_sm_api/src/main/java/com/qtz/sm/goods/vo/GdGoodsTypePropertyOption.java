package com.qtz.sm.goods.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:GdGoodsTypePropertyOption</p>
 * <p>Description:商品分类属性值选项VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsTypePropertyOption extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1625492728154112L;
	/** 商品分类属性ID */	private java.lang.Long goodsTypeProId;	/** 值 */	private java.lang.String val;	/** 状态 */	private java.lang.Integer status;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsTypeProId() {	    return this.goodsTypeProId;	}	public void setGoodsTypeProId(java.lang.Long goodsTypeProId) {	    this.goodsTypeProId=goodsTypeProId;	}	public java.lang.String getVal() {	    return this.val;	}	public void setVal(java.lang.String val) {	    this.val=val;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsTypeProId:" + getGoodsTypeProId() +"," + "val:" + getVal() +"," + "status:" + getStatus() +"]";	}
}