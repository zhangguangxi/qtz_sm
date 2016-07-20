package com.qtz.sm.goods.vo;
import java.util.List;

import com.mall.core.vo.VO;
import com.qtz.sm.shop.vo.ShopValueVo;
/**
 * <p>Title:GdGoods</p>
 * <p>Description:商品VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-05-26
 */
public class GdGoods extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1678897741793280L;

		/** 主键ID *///	private java.lang.Long dmId;	/** 供应商ID */	private java.lang.Long supplierId;	/** 品牌系列ID */	private java.lang.Long brandsId;	/** 商品分类ID */	private java.lang.Long goodsTypeId;	/** 编码 */	private java.lang.String code;	/** 名称 */	private java.lang.String name;	/** 商品描述 */	private java.lang.String remark;	/** 状态:0未审核，1审核通过，2审核未通过，3上架，4下架 */	private java.lang.Integer status;	/** 创建人 */	private java.lang.Long createBy;	/** 创建时间 */	private java.lang.Long createOn;	/** 更新人 */	private java.lang.Long updateBy;	/** 更新时间 */	private java.lang.Long updateOn;	/** 商品属性值信息 */	private java.lang.String goodsProValMsg;
	
	//*******************//
	/** 商品sku列表值 */
	private List<ShopValueVo> valList;
		public List<ShopValueVo> getValList() {
		return valList;
	}
	public void setValList(List<ShopValueVo> valList) {
		this.valList = valList;
	}
	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getSupplierId() {	    return this.supplierId;	}	public void setSupplierId(java.lang.Long supplierId) {	    this.supplierId=supplierId;	}	public java.lang.Long getBrandsId() {	    return this.brandsId;	}	public void setBrandsId(java.lang.Long brandsId) {	    this.brandsId=brandsId;	}	public java.lang.Long getGoodsTypeId() {	    return this.goodsTypeId;	}	public void setGoodsTypeId(java.lang.Long goodsTypeId) {	    this.goodsTypeId=goodsTypeId;	}	public java.lang.String getCode() {	    return this.code;	}	public void setCode(java.lang.String code) {	    this.code=code;	}	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.String getRemark() {	    return this.remark;	}	public void setRemark(java.lang.String remark) {	    this.remark=remark;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public java.lang.Long getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.Long createBy) {	    this.createBy=createBy;	}	public java.lang.Long getCreateOn() {	    return this.createOn;	}	public void setCreateOn(java.lang.Long createOn) {	    this.createOn=createOn;	}	public java.lang.Long getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.Long updateBy) {	    this.updateBy=updateBy;	}	public java.lang.Long getUpdateOn() {	    return this.updateOn;	}	public void setUpdateOn(java.lang.Long updateOn) {	    this.updateOn=updateOn;	}	public java.lang.String getGoodsProValMsg() {	    return this.goodsProValMsg;	}	public void setGoodsProValMsg(java.lang.String goodsProValMsg) {	    this.goodsProValMsg=goodsProValMsg;	}	
	@Override
	public String toString() {
		return "GdGoods [dmId=" + dmId + ", supplierId=" + supplierId + ", brandsId=" + brandsId + ", goodsTypeId="
				+ goodsTypeId + ", code=" + code + ", name=" + name + ", remark=" + remark + ", status=" + status
				+ ", createBy=" + createBy + ", createOn=" + createOn + ", updateBy=" + updateBy + ", updateOn="
				+ updateOn + ", goodsProValMsg=" + goodsProValMsg + ", valList=" + valList + "]";
	}
}