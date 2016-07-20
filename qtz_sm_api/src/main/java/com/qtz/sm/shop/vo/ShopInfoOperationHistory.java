package com.qtz.sm.shop.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopInfoOperationHistory</p>
 * <p>Description:便利店操作记录VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-17
 */
public class ShopInfoOperationHistory extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1710103458531328L;

		/** 主键ID *///	private java.lang.Long dmId;	/** 操作人ID */	private java.lang.Long operator;	/** 操作人名称 */	private String operatorName;	/** 便利店ID */	private java.lang.Long shopId;	/** IP地址 */	private java.lang.String ip;	/** 操作内容，如将价格15改为10 */	private java.lang.String content;	/** 操作原因，如降价 */	private java.lang.String reason;	/** 操作时间 */	private java.lang.Long operateOn;
		public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getOperator() {	    return this.operator;	}	public void setOperator(java.lang.Long operator) {	    this.operator=operator;	}	public java.lang.Long getShopId() {	    return this.shopId;	}	public void setShopId(java.lang.Long shopId) {	    this.shopId=shopId;	}	public java.lang.String getIp() {	    return this.ip;	}	public void setIp(java.lang.String ip) {	    this.ip=ip;	}	public java.lang.String getContent() {	    return this.content;	}	public void setContent(java.lang.String content) {	    this.content=content;	}	public java.lang.String getReason() {	    return this.reason;	}	public void setReason(java.lang.String reason) {	    this.reason=reason;	}	public java.lang.Long getOperateOn() {	    return this.operateOn;	}	public void setOperateOn(java.lang.Long operateOn) {	    this.operateOn=operateOn;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "operator:" + getOperator() +"," + "shopId:" + getShopId() +"," + "ip:" + getIp() +"," + "content:" + getContent() +"," + "reason:" + getReason() +"," + "operateOn:" + getOperateOn() +"]";	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}