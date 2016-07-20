package com.qtz.sm.shopManage.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:ShopManageSplitPointPage</p>
 * <p>Description:便利店公司分成点分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-01
 */
public class ShopManageSplitPointPage extends Pager<com.qtz.sm.shopManage.vo.ShopManageSplitPoint,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1688060914518017L;

		/**  */	private java.lang.Long dmId;	/** 便利店公司ID */	private java.lang.Long shopManageId;	/** 分成点 */	private java.lang.Double splitPoint;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getShopManageId() {	    return this.shopManageId;	}	public void setShopManageId(java.lang.Long shopManageId) {	    this.shopManageId=shopManageId;	}	public java.lang.Double getSplitPoint() {	    return this.splitPoint;	}	public void setSplitPoint(java.lang.Double splitPoint) {	    this.splitPoint=splitPoint;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopManageId:" + getShopManageId() +"," + "splitPoint:" + getSplitPoint() +"]";	}
}