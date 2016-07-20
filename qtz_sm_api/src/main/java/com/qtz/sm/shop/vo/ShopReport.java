package com.qtz.sm.shop.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopReport</p>
 * <p>Description:店铺举报信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
public class ShopReport extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1681808705259520L;

		/** 主键ID */	private java.lang.Long dmId;	/** 被举报店铺ID */	private java.lang.Long shopId;	/** 被举报店铺名称 */	private java.lang.String shopName;	/** 举报类型 */	private java.lang.Integer reprotType;	/** 举报内容 */	private java.lang.String reportContent;	/** 举报者联系电话 */	private java.lang.String mobile;	/** 举报详情 */	private java.lang.String reportDetail;	/** 图片路径 */	private java.lang.String picUrl;	/** 举报者主键ID */	private java.lang.Long userId;	/** 创建时间 */	private java.util.Date createTime;	/** 修改时间 */	private java.util.Date updateTime;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getShopId() {	    return this.shopId;	}	public void setShopId(java.lang.Long shopId) {	    this.shopId=shopId;	}	public java.lang.String getShopName() {	    return this.shopName;	}	public void setShopName(java.lang.String shopName) {	    this.shopName=shopName;	}	public java.lang.Integer getReprotType() {	    return this.reprotType;	}	public void setReprotType(java.lang.Integer reprotType) {	    this.reprotType=reprotType;	}	public java.lang.String getReportContent() {	    return this.reportContent;	}	public void setReportContent(java.lang.String reportContent) {	    this.reportContent=reportContent;	}	public java.lang.String getMobile() {	    return this.mobile;	}	public void setMobile(java.lang.String mobile) {	    this.mobile=mobile;	}	public java.lang.String getReportDetail() {	    return this.reportDetail;	}	public void setReportDetail(java.lang.String reportDetail) {	    this.reportDetail=reportDetail;	}	public java.lang.String getPicUrl() {	    return this.picUrl;	}	public void setPicUrl(java.lang.String picUrl) {	    this.picUrl=picUrl;	}	public java.lang.Long getUserId() {	    return this.userId;	}	public void setUserId(java.lang.Long userId) {	    this.userId=userId;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopId:" + getShopId() +"," + "shopName:" + getShopName() +"," + "reprotType:" + getReprotType() +"," + "reportContent:" + getReportContent() +"," + "mobile:" + getMobile() +"," + "reportDetail:" + getReportDetail() +"," + "picUrl:" + getPicUrl() +"," + "userId:" + getUserId() +"," + "createTime:" + getCreateTime() +"," + "updateTime:" + getUpdateTime() +"]";	}
}