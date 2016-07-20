package com.qtz.sm.shop.page;import java.util.List;import java.util.Set;import com.mall.core.vo.Pager;/** * <p>Title:ShopGoodsPage</p> * <p>Description:便利店商品分页类</p> * <p>Copyright: Copyright (c) 2016</p> * <p>Company: 深圳擎天柱信息技术有限公司</p> * @author 孙选 * @version v1.0 2016-05-03 */public class ShopGoodsPage extends Pager<com.qtz.sm.shop.vo.ShopGoods,Long> implements java.io.Serializable{	/**类的版本号*/	private static final long serialVersionUID = 1646841566939137L;		/** 主键ID */	private Long dmId;	/** 店铺ID */	private Long shopId;	/** 商品ID */	private Long goodsId;	/** 店铺类目ID */	private Long shopCategoryId;	/** 状态 */	private Integer status;	/**  */	private java.util.Date onlineTime;	/**  */	private java.util.Date createTime;	/**  */	private java.util.Date updateTime;		//**************查询条件**************//	/** 商品名称 */	private String name;	/** 商品编码 */	private String code;	/** 上架时间范围  */	private java.util.Date startDate;	private java.util.Date endDate;	private Integer parmCode;//1查询所有便利店所有商品，2查询分类下的商品，3查询未绑定分类的商品	/** 可以库存范围  */	private Integer startActual;	private Integer endActual;	private Long skuId;//属性编号（SkuID）	//***********组合参数*************//	private Set<Long> shopGoodsIds;		public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public java.util.Date getStartDate() {		return startDate;	}	public void setStartDate(java.util.Date startDate) {		this.startDate = startDate;	}	public java.util.Date getEndDate() {		return endDate;	}	public void setEndDate(java.util.Date endDate) {		this.endDate = endDate;	}	public Integer getParmCode() {		return parmCode;	}	public void setParmCode(Integer parmCode) {		this.parmCode = parmCode;	}	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getShopId() {	    return this.shopId;	}	public void setShopId(Long shopId) {	    this.shopId=shopId;	}	public Long getGoodsId() {	    return this.goodsId;	}	public void setGoodsId(Long goodsId) {	    this.goodsId=goodsId;	}	public Long getShopCategoryId() {	    return this.shopCategoryId;	}	public void setShopCategoryId(Long shopCategoryId) {	    this.shopCategoryId=shopCategoryId;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public java.util.Date getOnlineTime() {	    return this.onlineTime;	}	public void setOnlineTime(java.util.Date onlineTime) {	    this.onlineTime=onlineTime;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "shopId:" + getShopId() +"," + "goodsId:" + getGoodsId() +"," + "shopCategoryId:" + getShopCategoryId() +"," + "status:" + getStatus() +"," + "onlineTime:" + getOnlineTime() +"," + "createTime:" + getCreateTime() +"," + "updateTime:" + getUpdateTime() +"]";	}	public Integer getStartActual() {		return startActual;	}	public void setStartActual(Integer startActual) {		this.startActual = startActual;	}	public Integer getEndActual() {		return endActual;	}	public void setEndActual(Integer endActual) {		this.endActual = endActual;	}	public Long getSkuId() {		return skuId;	}	public void setSkuId(Long skuId) {		this.skuId = skuId;	}	public Set<Long> getShopGoodsIds() {		return shopGoodsIds;	}	public void setShopGoodsIds(Set<Long> shopGoodsIds) {		this.shopGoodsIds = shopGoodsIds;	}	public String getCode() {		return code;	}	public void setCode(String code) {		this.code = code;	}}