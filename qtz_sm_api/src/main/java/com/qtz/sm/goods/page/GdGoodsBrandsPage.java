package com.qtz.sm.goods.page;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.vo.GdGoodsBrands;
/**
 * <p>Title:GdGoodsBrandsPage</p>
 * <p>Description:品牌系列分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsBrandsPage extends Pager<GdGoodsBrands,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1625492722616321L;

		/** 主键ID */	private java.lang.Long dmId;	/** 商品分类ID */	private java.lang.Long goodsTypeId;	/** 供应商ID */	private java.lang.Long supplierId;	/** 中文名称 */	private java.lang.String cnName;	/** 英文名称 */	private java.lang.String enName;	/** 拼音 */	private java.lang.String pinyin;	/** 父级ID */	private java.lang.Long parentId;	/** 层级 */	private java.lang.Integer level;	/** 顺序号 */	private java.lang.Integer seq;	/** 品牌LOGO地址 */	private java.lang.String logoUrl;	/** 品牌官方地址 */	private java.lang.String siteUrl;	/** 品牌故事 */	private java.lang.String story;	/** 描述 */	private java.lang.String remark;	/** 状态(0：启用，1：禁用) */	private java.lang.Integer status;	/** 创建人 */	private java.lang.Long createBy;	/** 创建时间 */	private java.lang.Long createOn;	/** 更新人 */	private java.lang.Long updateBy;	/** 更新时间 */	private java.lang.Long updateOn;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getGoodsTypeId() {	    return this.goodsTypeId;	}	public void setGoodsTypeId(java.lang.Long goodsTypeId) {	    this.goodsTypeId=goodsTypeId;	}	public java.lang.Long getSupplierId() {	    return this.supplierId;	}	public void setSupplierId(java.lang.Long supplierId) {	    this.supplierId=supplierId;	}	public java.lang.String getCnName() {	    return this.cnName;	}	public void setCnName(java.lang.String cnName) {	    this.cnName=cnName;	}	public java.lang.String getEnName() {	    return this.enName;	}	public void setEnName(java.lang.String enName) {	    this.enName=enName;	}	public java.lang.String getPinyin() {	    return this.pinyin;	}	public void setPinyin(java.lang.String pinyin) {	    this.pinyin=pinyin;	}	public java.lang.Long getParentId() {	    return this.parentId;	}	public void setParentId(java.lang.Long parentId) {	    this.parentId=parentId;	}	public java.lang.Integer getLevel() {	    return this.level;	}	public void setLevel(java.lang.Integer level) {	    this.level=level;	}	public java.lang.Integer getSeq() {	    return this.seq;	}	public void setSeq(java.lang.Integer seq) {	    this.seq=seq;	}	public java.lang.String getLogoUrl() {	    return this.logoUrl;	}	public void setLogoUrl(java.lang.String logoUrl) {	    this.logoUrl=logoUrl;	}	public java.lang.String getSiteUrl() {	    return this.siteUrl;	}	public void setSiteUrl(java.lang.String siteUrl) {	    this.siteUrl=siteUrl;	}	public java.lang.String getStory() {	    return this.story;	}	public void setStory(java.lang.String story) {	    this.story=story;	}	public java.lang.String getRemark() {	    return this.remark;	}	public void setRemark(java.lang.String remark) {	    this.remark=remark;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public java.lang.Long getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.Long createBy) {	    this.createBy=createBy;	}	public java.lang.Long getCreateOn() {	    return this.createOn;	}	public void setCreateOn(java.lang.Long createOn) {	    this.createOn=createOn;	}	public java.lang.Long getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.Long updateBy) {	    this.updateBy=updateBy;	}	public java.lang.Long getUpdateOn() {	    return this.updateOn;	}	public void setUpdateOn(java.lang.Long updateOn) {	    this.updateOn=updateOn;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "goodsTypeId:" + getGoodsTypeId() +"," + "supplierId:" + getSupplierId() +"," + "cnName:" + getCnName() +"," + "enName:" + getEnName() +"," + "pinyin:" + getPinyin() +"," + "parentId:" + getParentId() +"," + "level:" + getLevel() +"," + "seq:" + getSeq() +"," + "logoUrl:" + getLogoUrl() +"," + "siteUrl:" + getSiteUrl() +"," + "story:" + getStory() +"," + "remark:" + getRemark() +"," + "status:" + getStatus() +"," + "createBy:" + getCreateBy() +"," + "createOn:" + getCreateOn() +"," + "updateBy:" + getUpdateBy() +"," + "updateOn:" + getUpdateOn() +"]";	}
}