package com.qtz.sm.supermarket.vo;import com.mall.core.vo.VO;import java.util.List;/** * <p>Title:SupermarketCategory</p> * <p>Description:超市类目APPVO类</p> * <p>Copyright: Copyright (c) 2016</p> * <p>Company: 深圳擎天柱信息技术有限公司</p> * @author yangwei * @version v1.0 2016-06-03 */public class SupermarketCategoryVo extends VO<Long> implements java.io.Serializable {		/**类的版本号*/	private static final long serialVersionUID = 1123717230073294472L;		/** 主键ID *///	private Long dmId;	/** 所属超市ID */	private Long supermarketId;	/** 父类目ID */	private Long pid;	/** 类目名称 */	private String name;	/** 类目图标 */	private String icon;	/** 层级(1级分类,2级分类,3分类....) */	private Integer level;	/** 排序 */	private Integer sort;	/** 状态 0启用1禁用*/	private Integer status;	/**  */	private java.util.Date updateTime;	/**  */	private java.util.Date createTime;	/**  超市商品信息List*/	private List<SupermarketGoodsVo> goodsVoList;	/**	 * 关联后台类目信息	 */	private SupermarketCategoryAssociate supermarketCategoryAssociate;		/**	 * 商品分类名称组合1级>2级>级	 */	private String goodsCategoryNames;		public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getSupermarketId() {		return supermarketId;	}	public void setSupermarketId(Long supermarketId) {		this.supermarketId = supermarketId;	}	public Long getPid() {	    return this.pid;	}	public void setPid(Long pid) {	    this.pid=pid;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getIcon() {	    return this.icon;	}	public void setIcon(String icon) {	    this.icon=icon;	}	public Integer getLevel() {	    return this.level;	}	public void setLevel(Integer level) {	    this.level=level;	}	public Integer getSort() {	    return this.sort;	}	public void setSort(Integer sort) {	    this.sort=sort;	}	public Integer getStatus() {	    return this.status;	}	public void setStatus(Integer status) {	    this.status=status;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public List<SupermarketGoodsVo> getGoodsVoList() {		return goodsVoList;	}	public void setGoodsVoList(List<SupermarketGoodsVo> goodsVoList) {		this.goodsVoList = goodsVoList;	}	public String getGoodsCategoryNames() {		return goodsCategoryNames;	}	public void setGoodsCategoryNames(String goodsCategoryNames) {		this.goodsCategoryNames = goodsCategoryNames;	}	public SupermarketCategoryAssociate getSupermarketCategoryAssociate() {		return supermarketCategoryAssociate;	}	public void setSupermarketCategoryAssociate(SupermarketCategoryAssociate supermarketCategoryAssociate) {		this.supermarketCategoryAssociate = supermarketCategoryAssociate;	}	}