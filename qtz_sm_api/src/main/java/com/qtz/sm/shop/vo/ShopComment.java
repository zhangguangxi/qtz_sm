package com.qtz.sm.shop.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopComment</p>
 * <p>Description:店铺评论信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
public class ShopComment extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1681805665585152L;

		/** 主键ID */	private java.lang.Long dmId;	/** 店铺ID */	private java.lang.Long shopId;	/** 星级评价 */	private java.lang.Double level;	/** 评价标签 */	private java.lang.String title;	/** 评论人ID */	private java.lang.Long commentId;	/** 评论内容 */	private java.lang.String comment;	/** 创建时间 */	private java.util.Date createOn;	/** 更新时间 */	private java.util.Date updateOn;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getShopId() {	    return this.shopId;	}	public void setShopId(java.lang.Long shopId) {	    this.shopId=shopId;	}	public java.lang.Double getLevel() {	    return this.level;	}	public void setLevel(java.lang.Double level) {	    this.level=level;	}	public java.lang.String getTitle() {	    return this.title;	}	public void setTitle(java.lang.String title) {	    this.title=title;	}	public java.lang.Long getCommentId() {	    return this.commentId;	}	public void setCommentId(java.lang.Long commentId) {	    this.commentId=commentId;	}	public java.lang.String getComment() {	    return this.comment;	}	public void setComment(java.lang.String comment) {	    this.comment=comment;	}	public java.util.Date getCreateOn() {	    return this.createOn;	}	public void setCreateOn(java.util.Date createOn) {	    this.createOn=createOn;	}	public java.util.Date getUpdateOn() {	    return this.updateOn;	}	public void setUpdateOn(java.util.Date updateOn) {	    this.updateOn=updateOn;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopId:" + getShopId() +"," + "level:" + getLevel() +"," + "title:" + getTitle() +"," + "commentId:" + getCommentId() +"," + "comment:" + getComment() +"," + "createOn:" + getCreateOn() +"," + "updateOn:" + getUpdateOn() +"]";	}
}