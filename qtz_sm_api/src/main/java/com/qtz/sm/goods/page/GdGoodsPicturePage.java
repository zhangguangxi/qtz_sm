package com.qtz.sm.goods.page;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.vo.GdGoodsPicture;
/**
 * <p>Title:GdGoodsPicturePage</p>
 * <p>Description:商品图片分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsPicturePage extends Pager<GdGoodsPicture,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1625492734855169L;

		/** 主键ID */	private java.lang.Long dmId;	/** 商品ID */	private java.lang.Long goodsId;	/** 商品属性ID */	private java.lang.Long propertyId;	/** 图片地址 */	private java.lang.String picUrl;	/** 创建时间 */	private java.lang.Long createOn;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsId() {	    return this.goodsId;	}	public void setGoodsId(java.lang.Long goodsId) {	    this.goodsId=goodsId;	}	public java.lang.Long getPropertyId() {	    return this.propertyId;	}	public void setPropertyId(java.lang.Long propertyId) {	    this.propertyId=propertyId;	}	public java.lang.String getPicUrl() {	    return this.picUrl;	}	public void setPicUrl(java.lang.String picUrl) {	    this.picUrl=picUrl;	}	public java.lang.Long getCreateOn() {	    return this.createOn;	}	public void setCreateOn(java.lang.Long createOn) {	    this.createOn=createOn;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsId:" + getGoodsId() +"," + "propertyId:" + getPropertyId() +"," + "picUrl:" + getPicUrl() +"," + "createOn:" + getCreateOn() +"]";	}
}