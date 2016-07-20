package com.qtz.sm.wallet.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:WtWallet</p>
 * <p>Description:钱包信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-13
 */
public class WtWallet extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1660539661846528L;

	//	/** 主键ID *///	private java.lang.Long dmId;	/** 属主类型 */	private java.lang.Integer ownerType;	/** 属主ID */	private java.lang.Long ownerId;	/** 属主名称 */	private java.lang.String ownerName;	/** 项目ID */	private java.lang.Integer itemId;	/** 项目名称 */	private java.lang.String itemName;	/** 项目总额 */	private java.lang.Double itemAmount;	/** 付款人类型 */	private java.lang.Integer payerType;	/** 付款人Id */	private java.lang.Long payerId;
	/** 创建时间 */	private java.lang.Long createTime;	/** 修改人 */	private java.lang.Long createBy;
		/** 上次结算时间 */	private java.lang.Long updateTime;	/** 修改人 */	private java.lang.Long updateBy;
	
	/** 项目总额范围查询 */
	private java.lang.Double minAmount;
	
	/** 项目总额范围查询 */
	private java.lang.Double maxAmount;
		public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Integer getOwnerType() {	    return this.ownerType;	}	public void setOwnerType(java.lang.Integer ownerType) {	    this.ownerType=ownerType;	}	public java.lang.Long getOwnerId() {	    return this.ownerId;	}	public void setOwnerId(java.lang.Long ownerId) {	    this.ownerId=ownerId;	}	public java.lang.String getOwnerName() {	    return this.ownerName;	}	public void setOwnerName(java.lang.String ownerName) {	    this.ownerName=ownerName;	}	public java.lang.Integer getItemId() {	    return this.itemId;	}	public void setItemId(java.lang.Integer itemId) {	    this.itemId=itemId;	}	public java.lang.String getItemName() {	    return this.itemName;	}	public void setItemName(java.lang.String itemName) {	    this.itemName=itemName;	}	public java.lang.Double getItemAmount() {	    return this.itemAmount;	}	public void setItemAmount(java.lang.Double itemAmount) {	    this.itemAmount=itemAmount;	}	public java.lang.Integer getPayerType() {	    return this.payerType;	}	public void setPayerType(java.lang.Integer payerType) {	    this.payerType=payerType;	}	public java.lang.Long getPayerId() {	    return this.payerId;	}	public void setPayerId(java.lang.Long payerId) {	    this.payerId=payerId;	}	public java.lang.Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	public java.lang.Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.Long createBy) {
		this.createBy = createBy;
	}
	public java.lang.Long getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.lang.Long updateTime) {	    this.updateTime=updateTime;	}	public java.lang.Long getUpdateBy() {	    return this.updateBy;	}	public void setUpdateBy(java.lang.Long updateBy) {	    this.updateBy=updateBy;	}
		public java.lang.Double getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(java.lang.Double minAmount) {
		this.minAmount = minAmount;
	}
	public java.lang.Double getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(java.lang.Double maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	@Override
	public String toString() {
		return "WtWallet [ownerType=" + ownerType + ", ownerId=" + ownerId + ", ownerName=" + ownerName + ", itemId="
				+ itemId + ", itemName=" + itemName + ", itemAmount=" + itemAmount + ", payerType=" + payerType
				+ ", payerId=" + payerId + ", createTime=" + createTime + ", createBy=" + createBy + ", updateTime="
				+ updateTime + ", updateBy=" + updateBy + ", minAmount=" + minAmount + ", maxAmount=" + maxAmount + "]";
	}
}