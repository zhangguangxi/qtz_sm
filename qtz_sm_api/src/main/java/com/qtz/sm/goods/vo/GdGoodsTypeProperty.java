package com.qtz.sm.goods.vo;

import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.VO;

/**
 * <p>
 * Title:GdGoodsTypeProperty
 * </p>
 * <p>
 * Description:商品分类属性VO类
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
public class GdGoodsTypeProperty extends VO<java.lang.Long> implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1625492725565440L;
	/** 商品分类ID */
	private java.lang.Long goodsTypeId;
	/** 属性编码 */
	private java.lang.String code;
	/** 属性名称 */
	private java.lang.String name;
	/** 输入类型（0：单选，1：多选，2：单行输入，3：多行输入，4：枚举单选，5：枚举多选，6：图片，7：附件） */
	private java.lang.Integer dataType;
	/** 是否销售属性 */
	private java.lang.Integer isSale;
	/** 是否关键属性 */
	private java.lang.Integer isKey;
	/** 是否必填或必选(0：是，1：否) */
	private java.lang.Integer required;
	/** 状态 */
	private java.lang.Integer status;

	////////////////////////
	/**
	 * 属性选项值
	 */
	private List<GdGoodsTypePropertyOption> options = new ArrayList<GdGoodsTypePropertyOption>();
	/**
	 * 商品属性实际值
	 */
	private List<GdGoodsPropertyVal> vals;

	public GdGoodsTypeProperty() {
		super();
	}

	public GdGoodsTypeProperty(Long goodsTypeId, String name, Integer dataType, Integer isSale, Integer isKey,
			Integer required, Integer status) {
		super();
		this.goodsTypeId = goodsTypeId;
		this.name = name;
		this.dataType = dataType;
		this.isSale = isSale;
		this.isKey = isKey;
		this.required = required;
		this.status = status;
	}

	public List<GdGoodsPropertyVal> getVals() {
		return vals;
	}

	public void setVals(List<GdGoodsPropertyVal> vals) {
		this.vals = vals;
	}

	public List<GdGoodsTypePropertyOption> getOptions() {
		return options;
	}

	public void setOptions(List<GdGoodsTypePropertyOption> options) {
		this.options = options;
	}

	public java.lang.Long getDmId() {
		return this.dmId;
	}

	public void setDmId(java.lang.Long dmId) {
		this.dmId = dmId;
	}

	public java.lang.Long getGoodsTypeId() {
		return this.goodsTypeId;
	}

	public void setGoodsTypeId(java.lang.Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public java.lang.String getCode() {
		return this.code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer getDataType() {
		return this.dataType;
	}

	public void setDataType(java.lang.Integer dataType) {
		this.dataType = dataType;
	}

	public java.lang.Integer getIsSale() {
		return this.isSale;
	}

	public void setIsSale(java.lang.Integer isSale) {
		this.isSale = isSale;
	}

	public java.lang.Integer getIsKey() {
		return this.isKey;
	}

	public void setIsKey(java.lang.Integer isKey) {
		this.isKey = isKey;
	}

	public java.lang.Integer getRequired() {
		return this.required;
	}

	public void setRequired(java.lang.Integer required) {
		this.required = required;
	}

	public java.lang.Integer getStatus() {
		return this.status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "GdGoodsTypeProperty [goodsTypeId=" + goodsTypeId + ", code=" + code + ", name=" + name + ", dataType="
				+ dataType + ", isSale=" + isSale + ", isKey=" + isKey + ", required=" + required + ", status=" + status
				+ ", options=" + options + ", vals=" + vals + "]";
	}
}