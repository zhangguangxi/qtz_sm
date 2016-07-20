package com.qtz.sm.goods.page;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.vo.GdGoodsType;
/**
 * <p>Title:GdGoodsTypePage</p>
 * <p>Description:商品分类分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsTypePage extends Pager<GdGoodsType,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1625492715407360L;
	
	/** 主键ID */	private java.lang.Long dmId;	/** 名称 */	private java.lang.String name;	/** 拼音 */	private java.lang.String pinyin;	/** 父级ID */	private java.lang.Long parentId;	/** 层级(1,2,3....) */	private java.lang.Integer level;	/** 顺序号 */	private java.lang.Integer seq;	/** 备注 */	private java.lang.String remark;	/** 状态(0：启用，1：禁用) */	private java.lang.Integer status;	/** 创建人 */	private java.lang.Long createOn;	/** 创建时间 */	private java.lang.Long createBy;	/** 更新人 */	private java.lang.Long updateBy;	/** 更新时间 */	private java.lang.Long updateOn;
	
	/**
	 * 名称或拼音查询
	 */
	private String nameOrPinyinLike;
	
	public String getNameOrPinyinLike() {
		return nameOrPinyinLike;
	}
	public void setNameOrPinyinLike(String nameOrPinyinLike) {
		this.nameOrPinyinLike = nameOrPinyinLike;
	}
	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.String getPinyin() {	    return this.pinyin;	}	public void setPinyin(java.lang.String pinyin) {	    this.pinyin=pinyin;	}	public java.lang.Long getParentId() {	    return this.parentId;	}	public void setParentId(java.lang.Long parentId) {	    this.parentId=parentId;	}	public java.lang.Integer getLevel() {	    return this.level;	}	public void setLevel(java.lang.Integer level) {	    this.level=level;	}	public java.lang.Integer getSeq() {	    return this.seq;	}	public void setSeq(java.lang.Integer seq) {	    this.seq=seq;	}	public java.lang.String getRemark() {	    return this.remark;	}	public void setRemark(java.lang.String remark) {	    this.remark=remark;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public java.lang.Long getCreateOn() {	    return this.createOn;	}	public void setCreateOn(java.lang.Long createOn) {	    this.createOn=createOn;	}	public java.lang.Long getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.Long createBy) {	    this.createBy=createBy;	}	public java.lang.Long getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.Long updateBy) {	    this.updateBy=updateBy;	}	public java.lang.Long getUpdateOn() {	    return this.updateOn;	}	public void setUpdateOn(java.lang.Long updateOn) {	    this.updateOn=updateOn;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "name:" + getName() +"," + "pinyin:" + getPinyin() +"," + "parentId:" + getParentId() +"," + "level:" + getLevel() +"," + "seq:" + getSeq() +"," + "remark:" + getRemark() +"," + "status:" + getStatus() +"," + "createOn:" + getCreateOn() +"," + "createBy:" + getCreateBy() +"," + "updateBy:" + getUpdateBy() +"," + "updateOn:" + getUpdateOn() +"]";	}
}