package com.qtz.sm.supermarket.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:AdvertisementPage</p>
 * <p>Description:广告位分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱科技有限公司</p>
 * @author 张光喜 - zhangguangxito@sina.cn 
 * @version v1.0 2016-05-31
 */
public class SupermarketAdvertisementPage extends Pager<com.qtz.sm.supermarket.vo.SupermarketAdvertisement,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1686433504118785L;

		/** 主键id */	private java.lang.Long dmId;	/** 图片 */	private java.lang.String icon;	/** 跳转方式 */	private java.lang.String url;	/** 排序 */	private java.lang.Integer sort;	/** 类型(0:商家  1:个人) */	private java.lang.Integer type;	/** 客户端类型(0:全部 1:安卓 2:ios) */	private java.lang.String clientType;	/** 状态(0:启用 1:禁用) */	private java.lang.Integer status;	/** 创建时间 */	private java.util.Date createTime;	/** 更新时间 */	private java.util.Date updateTime;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.String getIcon() {	    return this.icon;	}	public void setIcon(java.lang.String icon) {	    this.icon=icon;	}	public java.lang.String getUrl() {	    return this.url;	}	public void setUrl(java.lang.String url) {	    this.url=url;	}	public java.lang.Integer getSort() {	    return this.sort;	}	public void setSort(java.lang.Integer sort) {	    this.sort=sort;	}	public java.lang.Integer getType() {	    return this.type;	}	public void setType(java.lang.Integer type) {	    this.type=type;	}	public java.lang.String getClientType() {	    return this.clientType;	}	public void setClientType(java.lang.String clientType) {	    this.clientType=clientType;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "icon:" + getIcon() +"," + "url:" + getUrl() +"," + "sort:" + getSort() +"," + "type:" + getType() +"," + "clientType:" + getClientType() +"," + "status:" + getStatus() +"," + "createTime:" + getCreateTime() +"," + "updateTime:" + getUpdateTime() +"]";	}
}