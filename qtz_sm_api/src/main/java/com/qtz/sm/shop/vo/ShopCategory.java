package com.qtz.sm.shop.vo;import java.util.List;import com.mall.core.vo.VO;/** * <p>Title:ShopCategory</p> * <p>Description:便利店类目VO类</p> * <p>Copyright: Copyright (c) 2016</p> * <p>Company: 深圳擎天柱信息技术有限公司</p> * @author 刘晓峰 - Laven * @version v1.0 2016-04-26 */public class ShopCategory extends VO<Long> implements java.io.Serializable {		/**类的版本号*/	private static final long serialVersionUID = 1636504370399232L;		/** 主键ID *///	private Long dmId;	/** 店铺ID */	private Long shopId;	/** 类目名称 */	private String name;	/** 类目图标 */	private String icon;	/** 排序 */	private Integer sort;	/** 状态 */	private Integer status;	/**  */	private java.util.Date createTime;	/**  */	private java.util.Date updateTime;	/** 分类信息下商品列表 */	List<ShopGoodsVo> list;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getShopId() {	    return this.shopId;	}	public void setShopId(Long shopId) {	    this.shopId=shopId;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getIcon() {	    return this.icon;	}	public void setIcon(String icon) {	    this.icon=icon;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public List<ShopGoodsVo> getList() {		return list;	}	public void setList(List<ShopGoodsVo> list) {		this.list = list;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopId:" + getShopId() +"," + "name:" + getName() +"," + "icon:" + getIcon() +"," + "sort:" + getSort() +"," + "status:" + getStatus() +"," + "createTime:" + getCreateTime() +"," + "updateTime:" + getUpdateTime() +"]";	}}