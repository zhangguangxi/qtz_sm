package com.qtz.sm.shopManage.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopManageCategoryProperty</p>
 * <p>Description:便利店管理公司运营分类与便利店关联VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
public class ShopManageCategoryProperty extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1694770609883136L;

		/**  *///	private java.lang.Long dmId;	/** 便利店管理公司运营分类ID */	private java.lang.Long shopManageCategoryId;	/** 便利店ID */	private java.lang.Long shopId;
		public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getShopManageCategoryId() {	    return this.shopManageCategoryId;	}	public void setShopManageCategoryId(java.lang.Long shopManageCategoryId) {	    this.shopManageCategoryId=shopManageCategoryId;	}	public java.lang.Long getShopId() {	    return this.shopId;	}	public void setShopId(java.lang.Long shopId) {	    this.shopId=shopId;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopManageCategoryId:" + getShopManageCategoryId() +"," + "shopId:" + getShopId() +"]";	}
}