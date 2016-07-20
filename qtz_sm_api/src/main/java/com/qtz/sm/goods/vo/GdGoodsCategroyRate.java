package com.qtz.sm.goods.vo;

import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.VO;

/**
 * <p>
 * Title:GdGoodsCategroyRate
 * </p>
 * <p>
 * Description:商品分类溢价率VO类
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
public class GdGoodsCategroyRate extends VO<java.lang.Long> implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1625492743899136L;

	/** 商品分类ID */
	private java.lang.Long goodsTypeId;
	/** 供应链议价 */
	private java.lang.Double gylRate;
	/** 云仓储管理公司议价 */
	private java.lang.Double yccglRate;
	/** 便利店管理公司议价 */
	private java.lang.Double bldglRate;
	/** 胖胖超市议价 */
	private java.lang.Double ppcsRate;
	
	//非数据库字段,商品分类父ID
	private Long parentId;
	//分类名称
	private String name;
	//子分类议价
	List<GdGoodsCategroyRate> childs = new ArrayList<GdGoodsCategroyRate>();

	public GdGoodsCategroyRate() {
		super();
	}

	public GdGoodsCategroyRate(Long dmId,Long goodsTypeId, Double gylRate, Double yccglRate, Double bldglRate, Double ppcsRate) {
		super();
		this.dmId = dmId;
		this.goodsTypeId = goodsTypeId;
		this.gylRate = gylRate;
		this.yccglRate = yccglRate;
		this.bldglRate = bldglRate;
		this.ppcsRate = ppcsRate;
	}

	public GdGoodsCategroyRate(Long goodsTypeId, Double gylRate, Double yccglRate, Double bldglRate, Double ppcsRate) {
		super();
		this.goodsTypeId = goodsTypeId;
		this.gylRate = gylRate;
		this.yccglRate = yccglRate;
		this.bldglRate = bldglRate;
		this.ppcsRate = ppcsRate;
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

	public java.lang.Double getGylRate() {
		return gylRate;
	}

	public void setGylRate(java.lang.Double gylRate) {
		this.gylRate = gylRate;
	}

	public java.lang.Double getYccglRate() {
		return this.yccglRate;
	}

	public void setYccglRate(java.lang.Double yccglRate) {
		this.yccglRate = yccglRate;
	}

	public java.lang.Double getBldglRate() {
		return this.bldglRate;
	}

	public void setBldglRate(java.lang.Double bldglRate) {
		this.bldglRate = bldglRate;
	}

	public java.lang.Double getPpcsRate() {
		return this.ppcsRate;
	}

	public void setPpcsRate(java.lang.Double ppcsRate) {
		this.ppcsRate = ppcsRate;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GdGoodsCategroyRate> getChilds() {
		return childs;
	}

	public void setChilds(List<GdGoodsCategroyRate> childs) {
		this.childs = childs;
	}

	@Override
	public String toString() {
		return "GdGoodsCategroyRate [goodsTypeId=" + goodsTypeId + ", gylRate=" + gylRate + ", yccglRate=" + yccglRate
				+ ", bldglRate=" + bldglRate + ", ppcsRate=" + ppcsRate + ", parentId=" + parentId + ", name=" + name
				+ ", childs=" + childs + "]";
	}
}