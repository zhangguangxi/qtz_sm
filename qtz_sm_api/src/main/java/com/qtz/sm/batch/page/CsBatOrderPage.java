package com.qtz.sm.batch.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:CsBatOrderPage</p>
 * <p>Description:批发单基础信息分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
public class CsBatOrderPage extends Pager<com.qtz.sm.batch.vo.CsBatOrder,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1660901523458049L;

		/** 批发单ID */	private java.lang.Long dmId;	/** 仓储中心ID */	private java.lang.Long cczxId;	/** 供应商ID */	private java.lang.Long gysId;	/** 状态(0待受理,1待配送,2配送中,3已完成) */	private java.lang.Integer status;	/** 收货人 */	private java.lang.String reciever;	/** 联系电话 */	private java.lang.String contactPhone;	/** 收货地址全称 */	private java.lang.String fullAddress;	/** 收货地址ID(仓储中心地址ID) */	private java.lang.Long addrId;	/** 商品总数 */	private java.lang.Integer totalNum;	/** 下单时间 */	private java.lang.Long createTime;	/** 接单时间 */	private java.lang.Long confirmTime;	/** 配送时间 */	private java.lang.Long deliveryTime;	/** 完成时间 */	private java.lang.Long finishTime;	/** 物流公司 */	private java.lang.String logisticsCompany;	/** 物流单号 */	private java.lang.String logisticsNumber;	/** 备注 */	private java.lang.String remarks;
	/** 下单开始时间 */
	private java.lang.Long startTime;
	/** 下单结束时间 */
	private java.lang.Long endTime;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getCczxId() {	    return this.cczxId;	}	public void setCczxId(java.lang.Long cczxId) {	    this.cczxId=cczxId;	}	public java.lang.Long getGysId() {	    return this.gysId;	}	public void setGysId(java.lang.Long gysId) {	    this.gysId=gysId;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public java.lang.String getReciever() {	    return this.reciever;	}	public void setReciever(java.lang.String reciever) {	    this.reciever=reciever;	}	public java.lang.String getContactPhone() {	    return this.contactPhone;	}	public void setContactPhone(java.lang.String contactPhone) {	    this.contactPhone=contactPhone;	}	public java.lang.String getFullAddress() {	    return this.fullAddress;	}	public void setFullAddress(java.lang.String fullAddress) {	    this.fullAddress=fullAddress;	}	public java.lang.Long getAddrId() {	    return this.addrId;	}	public void setAddrId(java.lang.Long addrId) {	    this.addrId=addrId;	}	public java.lang.Integer getTotalNum() {	    return this.totalNum;	}	public void setTotalNum(java.lang.Integer totalNum) {	    this.totalNum=totalNum;	}	public java.lang.Long getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Long createTime) {	    this.createTime=createTime;	}	public java.lang.Long getConfirmTime() {	    return this.confirmTime;	}	public void setConfirmTime(java.lang.Long confirmTime) {	    this.confirmTime=confirmTime;	}	public java.lang.Long getDeliveryTime() {	    return this.deliveryTime;	}	public void setDeliveryTime(java.lang.Long deliveryTime) {	    this.deliveryTime=deliveryTime;	}	public java.lang.Long getFinishTime() {	    return this.finishTime;	}	public void setFinishTime(java.lang.Long finishTime) {	    this.finishTime=finishTime;	}	public java.lang.String getLogisticsCompany() {	    return this.logisticsCompany;	}	public void setLogisticsCompany(java.lang.String logisticsCompany) {	    this.logisticsCompany=logisticsCompany;	}	public java.lang.String getLogisticsNumber() {	    return this.logisticsNumber;	}	public void setLogisticsNumber(java.lang.String logisticsNumber) {	    this.logisticsNumber=logisticsNumber;	}	public java.lang.String getRemarks() {	    return this.remarks;	}	public void setRemarks(java.lang.String remarks) {	    this.remarks=remarks;	}	public java.lang.Long getStartTime() {
		return startTime;
	}
	public void setStartTime(java.lang.Long startTime) {
		this.startTime = startTime;
	}
	public java.lang.Long getEndTime() {
		return endTime;
	}
	public void setEndTime(java.lang.Long endTime) {
		this.endTime = endTime;
	}
	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "cczxId:" + getCczxId() +"," + "gysId:" + getGysId() +"," + "status:" + getStatus() +"," + "reciever:" + getReciever() +"," + "contactPhone:" + getContactPhone() +"," + "fullAddress:" + getFullAddress() +"," + "addrId:" + getAddrId() +"," + "totalNum:" + getTotalNum() +"," + "createTime:" + getCreateTime() +"," + "confirmTime:" + getConfirmTime() +"," + "deliveryTime:" + getDeliveryTime() +"," + "finishTime:" + getFinishTime() +"," + "logisticsCompany:" + getLogisticsCompany() +"," + "logisticsNumber:" + getLogisticsNumber() +"," + "remarks:" + getRemarks() +"]";	}
}