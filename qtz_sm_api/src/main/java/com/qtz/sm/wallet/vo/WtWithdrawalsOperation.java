package com.qtz.sm.wallet.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:WtWithdrawalsOperation</p>
 * <p>Description:钱包操作记录表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息有限公司</p>
 * @author wangdong - wangdongn@126.com
 * @version v1.0 2016-06-08
 */
public class WtWithdrawalsOperation extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1697375587895296L;

		/** 主键ID */	private java.lang.Long dmId;	/** 提现金额 */	private java.lang.Double amount;	/** 操作时间 */	private java.lang.Long createTime;	/** 操作内容 */	private java.lang.String createContent;	/** 操作人 */	private java.lang.Long createBy;	/** 备注 */	private java.lang.String remark;	/** 源单ID */	private java.lang.Long sourceId;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Double getAmount() {	    return this.amount;	}	public void setAmount(java.lang.Double amount) {	    this.amount=amount;	}	public java.lang.Long getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Long createTime) {	    this.createTime=createTime;	}	public java.lang.String getCreateContent() {	    return this.createContent;	}	public void setCreateContent(java.lang.String createContent) {	    this.createContent=createContent;	}	public java.lang.Long getCreateBy() {	    return this.createBy;	}	public void setCreateBy(java.lang.Long createBy) {	    this.createBy=createBy;	}	public java.lang.String getRemark() {	    return this.remark;	}	public void setRemark(java.lang.String remark) {	    this.remark=remark;	}	public java.lang.Long getSourceId() {	    return this.sourceId;	}	public void setSourceId(java.lang.Long sourceId) {	    this.sourceId=sourceId;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "amount:" + getAmount() +"," + "createTime:" + getCreateTime() +"," + "createContent:" + getCreateContent() +"," + "createBy:" + getCreateBy() +"," + "remark:" + getRemark() +"," + "sourceId:" + getSourceId() +"]";	}
}