package com.qtz.sm.supermarket.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:SupermarketBusiness</p>
 * <p>Description:超市运营信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-14
 */
public class SupermarketBusiness extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1706242999584768L;

		/**  *///	private java.lang.Long dmId;	/** 超市ID */	private java.lang.Long supermarketId;	/** 营业开始时间（精确到时分） */	private java.util.Date serviceStartTime;	/** 营业结束时间（精确到时分） */	private java.util.Date serviceEndTime;	/** 起送金额 */	private java.lang.Double minimumMoney;	/** 配送时限(分钟) */	private java.lang.Integer servicePromise;	/**  */	private java.util.Date createTime;	/**  */	private java.util.Date updateTime;
	
	//*******接收参数*******//
	/** 营业开始时间（精确到时分） */
	private String serviceStartTimeStr;
	/** 营业结束时间（精确到时分） */
	private String serviceEndTimeStr;
	
		public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getSupermarketId() {	    return this.supermarketId;	}	public void setSupermarketId(java.lang.Long supermarketId) {	    this.supermarketId=supermarketId;	}	public java.util.Date getServiceStartTime() {	    return this.serviceStartTime;	}	public void setServiceStartTime(java.util.Date serviceStartTime) {	    this.serviceStartTime=serviceStartTime;	}	public java.util.Date getServiceEndTime() {	    return this.serviceEndTime;	}	public void setServiceEndTime(java.util.Date serviceEndTime) {	    this.serviceEndTime=serviceEndTime;	}	public java.lang.Double getMinimumMoney() {	    return this.minimumMoney;	}	public void setMinimumMoney(java.lang.Double minimumMoney) {	    this.minimumMoney=minimumMoney;	}	public java.lang.Integer getServicePromise() {	    return this.servicePromise;	}	public void setServicePromise(java.lang.Integer servicePromise) {	    this.servicePromise=servicePromise;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "supermarketId:" + getSupermarketId() +"," + "serviceStartTime:" + getServiceStartTime() +"," + "serviceEndTime:" + getServiceEndTime() +"," + "minimumMoney:" + getMinimumMoney() +"," + "servicePromise:" + getServicePromise() +"," + "createTime:" + getCreateTime() +"," + "updateTime:" + getUpdateTime() +"]";	}
	public String getServiceStartTimeStr() {
		return serviceStartTimeStr;
	}
	public void setServiceStartTimeStr(String serviceStartTimeStr) {
		this.serviceStartTimeStr = serviceStartTimeStr;
	}
	public String getServiceEndTimeStr() {
		return serviceEndTimeStr;
	}
	public void setServiceEndTimeStr(String serviceEndTimeStr) {
		this.serviceEndTimeStr = serviceEndTimeStr;
	}
}