package com.qtz.sm.shopManage.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopManageCategory</p>
 * <p>Description:便利店管理公司运营分类VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
public class ShopManageCategory extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1694538681010176L;

		/**  *///	private java.lang.Long dmId;	/** 便利店管理公司ID */	private java.lang.Long shopManageId;	/** 分类名称 */	private java.lang.String categoryName;	/** 排序 */	private java.lang.Integer sort;	/** 状态0启用1禁用 */	private java.lang.Integer status;	/**  */	private java.util.Date createTime;	/**  */	private java.util.Date updateTime;
	
	//查询条件
	private Long shopId;//便利店ID
	
		public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getShopManageId() {	    return this.shopManageId;	}	public void setShopManageId(java.lang.Long shopManageId) {	    this.shopManageId=shopManageId;	}	public java.lang.String getCategoryName() {	    return this.categoryName;	}	public void setCategoryName(java.lang.String categoryName) {	    this.categoryName=categoryName;	}	public java.lang.Integer getSort() {	    return this.sort;	}	public void setSort(java.lang.Integer sort) {	    this.sort=sort;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopManageId:" + getShopManageId() +"," + "categoryName:" + getCategoryName() +"," + "sort:" + getSort() +"," + "status:" + getStatus() +"," + "createTime:" + getCreateTime() +"," + "updateTime:" + getUpdateTime() +"]";	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}