package com.qtz.sm.goods.model;

import com.mall.core.vo.VO;

/**
 * <p>Title:商品属性</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 欧江波 - meoujb@163.com
 * @version 1.0 2016年5月24日
 */
public class GdGoodsPropertyBo extends VO<java.lang.Long> implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 属性ID
	 */
	private Long propId;
	
	/**
	 * 属性ID名称
	 */
	private String propIdName;
	/**
	 * 自定义KEY
	 */
	private String otherKey;

	/***
	 * 属性键ID
	 */
	private Long propValId;
	
	/**
	 * 属性值名称
	 */
	private String propValName;
	
	/**
	 * 其他值
	 */
	private String otherValue;
	
	public GdGoodsPropertyBo() {
		super();
	}

	public GdGoodsPropertyBo(Long propId, String propIdName, String otherKey, Long propValId, String propValName,
			String otherValue) {
		super();
		this.propId = propId;
		this.propIdName = propIdName;
		this.otherKey = otherKey;
		this.propValId = propValId;
		this.propValName = propValName;
		this.otherValue = otherValue;
	}

	public Long getPropId() {
		return propId;
	}

	public void setPropId(Long propId) {
		this.propId = propId;
	}

	public String getPropIdName() {
		return propIdName;
	}

	public void setPropIdName(String propIdName) {
		this.propIdName = propIdName;
	}

	public Long getPropValId() {
		return propValId;
	}

	public void setPropValId(Long propValId) {
		this.propValId = propValId;
	}

	public String getPropValName() {
		return propValName;
	}

	public void setPropValName(String propValName) {
		this.propValName = propValName;
	}

	public String getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(String otherValue) {
		this.otherValue = otherValue;
	}

	public String getOtherKey() {
		return otherKey;
	}

	public void setOtherKey(String otherKey) {
		this.otherKey = otherKey;
	}

	@Override
	public String toString() {
		return "GdGoodsPropertyBo [propId=" + propId + ", propIdName=" + propIdName + ", otherKey=" + otherKey
				+ ", propValId=" + propValId + ", propValName=" + propValName + ", otherValue=" + otherValue + "]";
	}
}
