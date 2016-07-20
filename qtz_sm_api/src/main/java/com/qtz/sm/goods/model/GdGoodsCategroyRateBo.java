package com.qtz.sm.goods.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
* @Title: GdGoodsCategroyRateBo.java
* @Package com.qtz.sm.goods.model
* @Description: 商品分类议价业务对象
* @author 欧江波 meoujb@163.com
* @date 2016年6月20日 下午4:01:37
* @version V1.0
 */
public class GdGoodsCategroyRateBo implements Serializable {

	private static final long serialVersionUID = 1L;
	//主键ID
	private Long dmId;
	// 商品分类ID
	private Long goodsTypeId;
	//商品分类父ID
	private Long parentId;
	//分类名称
	private String goodsTypeName;
	// 胖胖超市议价
	private Double rate;
	// 议价类型,参考CompanyType枚举类型 
	private Integer rateType;
	//子分类议价
	List<GdGoodsCategroyRateBo> childs = new ArrayList<GdGoodsCategroyRateBo>();
	
	public GdGoodsCategroyRateBo(){
		
	}
	public GdGoodsCategroyRateBo(Long dmId, Long goodsTypeId, Long parentId, String goodsTypeName,Integer rateType) {
		super();
		this.dmId = dmId;
		this.goodsTypeId = goodsTypeId;
		this.parentId = parentId;
		this.goodsTypeName = goodsTypeName;
		this.rateType = rateType;
	}
	public Long getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(Long goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getRateType() {
		return rateType;
	}
	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}
	public List<GdGoodsCategroyRateBo> getChilds() {
		return childs;
	}
	public void setChilds(List<GdGoodsCategroyRateBo> childs) {
		this.childs = childs;
	}
	public Long getDmId() {
		return dmId;
	}
	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}
	@Override
	public String toString() {
		return "GdGoodsCategroyRateBo [dmId=" + dmId + ", goodsTypeId=" + goodsTypeId + ", parentId=" + parentId
				+ ", goodsTypeName=" + goodsTypeName + ", rate=" + rate + ", rateType=" + rateType + ", childs="
				+ childs + "]";
	}
}
