package com.qtz.sm.wallet.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:WtCczxIncomeSettlement</p>
 * <p>Description:超市收入对账结算记录VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 肖来龙 - lois siau
 * @version v1.0 2016-05-25
 */
public class WtCczxIncomeSettlement extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1677649946691584L;

	//	/** 主键ID *///	private java.lang.Long dmId;	/** 收款人ID */	private java.lang.Long ownerId;	/** 对账结算金额 */	private java.lang.Double totalAmount;	/** 对账方式 1:自动,2:手动 */	private java.lang.Integer optType;	/** 操作人 */	private java.lang.Long createBy;	/** 操作时间 */	private java.lang.Long createTime;	/** 备注 */	private java.lang.String remark;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getOwnerId() {	    return this.ownerId;	}	public void setOwnerId(java.lang.Long ownerId) {	    this.ownerId=ownerId;	}	public java.lang.Double getTotalAmount() {	    return this.totalAmount;	}	public void setTotalAmount(java.lang.Double totalAmount) {	    this.totalAmount=totalAmount;	}	public java.lang.Integer getOptType() {	    return this.optType;	}	public void setOptType(java.lang.Integer optType) {	    this.optType=optType;	}	public java.lang.Long getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.Long createBy) {	    this.createBy=createBy;	}	public java.lang.Long getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Long createTime) {	    this.createTime=createTime;	}	public java.lang.String getRemark() {	    return this.remark;	}	public void setRemark(java.lang.String remark) {	    this.remark=remark;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "ownerId:" + getOwnerId() +"," + "totalAmount:" + getTotalAmount() +"," + "optType:" + getOptType() +"," + "createBy:" + getCreateBy() +"," + "createTime:" + getCreateTime() +"," + "remark:" + getRemark() +"]";	}
}