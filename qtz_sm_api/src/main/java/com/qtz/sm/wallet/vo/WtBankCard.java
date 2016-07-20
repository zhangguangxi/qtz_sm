package com.qtz.sm.wallet.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:WtBankCard</p>
 * <p>Description:银行卡信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-26
 */
public class WtBankCard extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1636587845519360L;

		/** 主键ID */	//private java.lang.Long dmId;	/** 法人类型 */	private java.lang.Integer companyType;	/** 公司ID */	private java.lang.Long companyId;	/** 账号类型(0对公;1对私) */	private java.lang.Integer accountType;	/** 持卡人姓名 */	private java.lang.String cardholder;	/** 银行名称 */	private java.lang.String bankName;	/** 支行名称 */	private java.lang.String bankBranch;	/** 开户行地址 */	private java.lang.String bankAddress;	/** 银行卡号 */
	private java.lang.String cardNum;
	/** 使能 */	private java.lang.Integer enable;	/** 创建日期 */	private java.lang.Long createTime;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Integer getCompanyType() {	    return this.companyType;	}	public void setCompanyType(java.lang.Integer companyType) {	    this.companyType=companyType;	}	public java.lang.Long getCompanyId() {	    return this.companyId;	}	public void setCompanyId(java.lang.Long companyId) {	    this.companyId=companyId;	}	public java.lang.Integer getAccountType() {	    return this.accountType;	}	public void setAccountType(java.lang.Integer accountType) {	    this.accountType=accountType;	}	public java.lang.String getCardholder() {	    return this.cardholder;	}	public void setCardholder(java.lang.String cardholder) {	    this.cardholder=cardholder;	}	public java.lang.String getBankName() {	    return this.bankName;	}	public void setBankName(java.lang.String bankName) {	    this.bankName=bankName;	}	public java.lang.String getBankBranch() {	    return this.bankBranch;	}	public void setBankBranch(java.lang.String bankBranch) {	    this.bankBranch=bankBranch;	}	public java.lang.String getBankAddress() {	    return this.bankAddress;	}	public void setBankAddress(java.lang.String bankAddress) {	    this.bankAddress=bankAddress;	}
	public java.lang.String getCardNum() {
	    return this.cardNum;
	}
	public void setCardNum(java.lang.String cardNum) {
	    this.cardNum=cardNum;
	}	public java.lang.Integer getEnable() {	    return this.enable;	}	public void setEnable(java.lang.Integer enable) {	    this.enable=enable;	}	public java.lang.Long getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Long createTime) {	    this.createTime=createTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "companyType:" + getCompanyType() +"," + "companyId:" + getCompanyId() +"," + "accountType:" + getAccountType() +"," + "cardholder:" + getCardholder() +"," + "bankName:" + getBankName() +"," + "bankBranch:" + getBankBranch() +"," + "bankAddress:" + getBankAddress() +"," + "cardNum:" + getCardNum() +"," + "enable:" + getEnable() +"," + "createTime:" + getCreateTime() +"]";	}
}