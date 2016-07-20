package com.qtz.sm.goods.vo;

import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.VO;

/**
 * <p>
 * Title:GdGoodsType
 * </p>
 * <p>
 * Description:商品分类VO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsType extends VO<java.lang.Long> implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1625492715390976L;

	public static final String CREATEON = "create_On";

	/** 名称 */
	private java.lang.String name;
	/** 拼音 */
	private java.lang.String pinyin;
	/** 父级ID */
	private java.lang.Long parentId;
	/** 层级(1,2,3....) */
	private java.lang.Integer level;
	/** 顺序号 */
	private java.lang.Integer seq;
	/** 备注 */
	private java.lang.String remark;
	/** 状态(0：启用，1：禁用) */
	private java.lang.Integer status;
	/** 创建人 */
	private java.lang.Long createOn;
	/** 创建时间 */
	private java.lang.Long createBy;
	/** 更新人 */
	private java.lang.Long updateBy;
	/** 更新时间 */
	private java.lang.Long updateOn;

	/////////////// search conditions///////////////////////
	/**
	 * 名称或拼音查询
	 */
	private String nameOrPinyinLike;
	/**
	 * 销售属性
	 */
	private List<GdGoodsTypeProperty> gdTypeProesSale   = new ArrayList<GdGoodsTypeProperty>();
	/**
	 * 基本属性
	 */
	private List<GdGoodsTypeProperty> gdTypeProesKey = new ArrayList<GdGoodsTypeProperty>();


	/** 子分类 */
	private List<GdGoodsType> gdGoodsTypeChild;
	
	/** 上级分类 */
	private List<GdGoodsType> parents = new ArrayList<GdGoodsType>();
	/** 分类串（包含当前分类和当前分类的父分类），类似，服装->男装->T恤 */
	private String parentNameChain;
	
	private String parentIdChain;

	public GdGoodsType() {
		super();
	}

	public GdGoodsType(String name, String pinyin, Long parentId, Integer level, Long createOn, Long createBy,
			Long updateBy, Long updateOn) {
		super();
		this.name = name;
		this.pinyin = pinyin;
		this.parentId = parentId;
		this.level = level;
		this.createOn = createOn;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.updateOn = updateOn;
	}

	public List<GdGoodsTypeProperty> getGdTypeProesSale() {
		return gdTypeProesSale;
	}

	public void setGdTypeProesSale(List<GdGoodsTypeProperty> gdTypeProesSale) {
		this.gdTypeProesSale = gdTypeProesSale;
	}

	public List<GdGoodsTypeProperty> getGdTypeProesKey() {
		return gdTypeProesKey;
	}

	public void setGdTypeProesKey(List<GdGoodsTypeProperty> gdTypeProesKey) {
		this.gdTypeProesKey = gdTypeProesKey;
	}

	public void setNameOrPinyinLike(String nameOrPinyinLike) {
		this.nameOrPinyinLike = nameOrPinyinLike;
	}

	public String getNameOrPinyinLike() {
		return nameOrPinyinLike;
	}

	public java.lang.Long getDmId() {
		return this.dmId;
	}

	public void setDmId(java.lang.Long dmId) {
		this.dmId = dmId;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getPinyin() {
		return this.pinyin;
	}

	public void setPinyin(java.lang.String pinyin) {
		this.pinyin = pinyin;
	}

	public java.lang.Long getParentId() {
		return this.parentId;
	}

	public void setParentId(java.lang.Long parentId) {
		this.parentId = parentId;
	}

	public java.lang.Integer getLevel() {
		return this.level;
	}

	public void setLevel(java.lang.Integer level) {
		this.level = level;
	}

	public java.lang.Integer getSeq() {
		return this.seq;
	}

	public void setSeq(java.lang.Integer seq) {
		this.seq = seq;
	}

	public java.lang.String getRemark() {
		return this.remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.Long getCreateOn() {
		return this.createOn;
	}

	public void setCreateOn(java.lang.Long createOn) {
		this.createOn = createOn;
	}

	public java.lang.Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(java.lang.Long createBy) {
		this.createBy = createBy;
	}

	public java.lang.Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(java.lang.Long updateBy) {
		this.updateBy = updateBy;
	}

	public java.lang.Long getUpdateOn() {
		return this.updateOn;
	}

	public void setUpdateOn(java.lang.Long updateOn) {
		this.updateOn = updateOn;
	}
	
	public List<GdGoodsType> getGdGoodsTypeChild() {
		return gdGoodsTypeChild;
	}
	public void setGdGoodsTypeChild(List<GdGoodsType> gdGoodsTypeChild) {
		this.gdGoodsTypeChild = gdGoodsTypeChild;
	}

	public List<GdGoodsType> getParents() {
		return parents;
	}

	public void setParents(List<GdGoodsType> parents) {
		this.parents = parents;
	}

	public String getParentNameChain() {
		return parentNameChain;
	}

	public void setParentNameChain(String parentNameChain) {
		this.parentNameChain = parentNameChain;
	}

	public String getParentIdChain() {
		return parentIdChain;
	}

	public void setParentIdChain(String parentIdChain) {
		this.parentIdChain = parentIdChain;
	}

	@Override
	public String toString() {
		return "GdGoodsType [name=" + name + ", pinyin=" + pinyin + ", parentId=" + parentId + ", level=" + level
				+ ", seq=" + seq + ", remark=" + remark + ", status=" + status + ", createOn=" + createOn
				+ ", createBy=" + createBy + ", updateBy=" + updateBy + ", updateOn=" + updateOn + ", nameOrPinyinLike="
				+ nameOrPinyinLike + ", gdTypeProesSale=" + gdTypeProesSale + ", gdTypeProesKey=" + gdTypeProesKey
				+ ", gdGoodsTypeChild=" + gdGoodsTypeChild + ", parents=" + parents + ", parentNameChain="
				+ parentNameChain + ", parentIdChain=" + parentIdChain + "]";
	}
}