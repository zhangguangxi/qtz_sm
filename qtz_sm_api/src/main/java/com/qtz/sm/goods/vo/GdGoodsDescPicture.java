package com.qtz.sm.goods.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:GdGoodsDescPicture</p>
 * <p>Description:商品描述图片VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-16
 */
public class GdGoodsDescPicture extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1708683909351424L;

		/** 主键ID */	private java.lang.Long dmId;	/** 商品ID */	private java.lang.Long goodsId;	/** 图片地址 */	private java.lang.String picUrl;	/** 创建时间 */	private java.lang.Long createOn;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsId() {	    return this.goodsId;	}	public void setGoodsId(java.lang.Long goodsId) {	    this.goodsId=goodsId;	}	public java.lang.String getPicUrl() {	    return this.picUrl;	}	public void setPicUrl(java.lang.String picUrl) {	    this.picUrl=picUrl;	}	public java.lang.Long getCreateOn() {	    return this.createOn;	}	public void setCreateOn(java.lang.Long createOn) {	    this.createOn=createOn;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsId:" + getGoodsId() +"," + "picUrl:" + getPicUrl() +"," + "createOn:" + getCreateOn() +"]";	}
}