package com.qtz.sm.wallet.page;

import com.mall.core.vo.Pager;

/**
 * <p>
 * Title:WtGysWithdrawalsPage
 * </p>
 * <p>
 * Description:供应商提现流水分页类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 * 
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public class WtGysWithdrawalsPage extends Pager<com.qtz.sm.wallet.vo.WtGysWithdrawals, java.lang.Long>
		implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1659115041818625L;

	/** 主键ID */
	private java.lang.Long dmId;
	/** 申请人ID */
	private java.lang.Long applyId;
	/** 申请人名称 */
	private java.lang.String applyName;
	/** 申请人联系电话 */
	private java.lang.String applyPhone;
	/** 申请人操作用户 */
	private java.lang.Long userId;
	/** 申请日期 */
	private java.lang.Long applyTime;
	/** 源单类型(0：表示货款提现，1：平台分润提现，2：退款提现) */
	private java.lang.Integer sourceType;
	/** 提现金额 */
	private java.lang.Double amount;
	/** 状态(0：表示对账中，1：表示已提现，2：表示驳回，3：表示取消) */
	private java.lang.Integer astatus;
	/** 创建人 */
	private java.lang.String createBy;
	/** 创建时间 */
	private java.lang.Long createOn;
	/** 处理人 */
	private java.lang.Long dealBy;
	/** 处理时间 */
	private java.lang.Long dealOn;
	/** 处理备注 */
	private java.lang.String dealRemark;
	/** 更新人 */
	private java.lang.Long updateBy;
	/** 更新时间 */
	private java.lang.Long updateOn;
	/** 创建日期(日期类型) */
	private java.lang.Long statisticsDate;
	/** 解冻时间 */
	private java.lang.Long releaseDate;
	/** 预计到账金额 */
	private java.lang.Double arrivalAmount;
	/** 税务扣点税率 */
	private java.lang.Double taxDeduRate;
	/** 扣税金额 */
	private java.lang.Double taxDeduMoney;
	/** 源单ID */
	private java.lang.Long sourceId;
	/** 银行卡ID */
	private java.lang.Long bankcardId;
	/** 收款银行 */
	private java.lang.String bankName;
	/** 收款卡号 */
	private java.lang.String bankNo;
	/** 开户行地址 */
	private java.lang.String bankAddress;
	/** 持卡人名字 */
	private java.lang.String cardholderName;
	/** 账号类型(0对公;1对私) */
	private java.lang.Integer accountType;

	/** 创建时间开始查询时间 */
	private java.lang.Long startCreateOn;
	/** 创建时间结束查询时间 */
	private java.lang.Long endCreateOn;

	
	public java.lang.Long getStartCreateOn() {
		return startCreateOn;
	}

	public void setStartCreateOn(java.lang.Long startCreateOn) {
		this.startCreateOn = startCreateOn;
	}

	public java.lang.Long getEndCreateOn() {
		return endCreateOn;
	}

	public void setEndCreateOn(java.lang.Long endCreateOn) {
		this.endCreateOn = endCreateOn;
	}

	public java.lang.Long getDmId() {
		return this.dmId;
	}

	public void setDmId(java.lang.Long dmId) {
		this.dmId = dmId;
	}

	public java.lang.Long getApplyId() {
		return this.applyId;
	}

	public void setApplyId(java.lang.Long applyId) {
		this.applyId = applyId;
	}

	public java.lang.String getApplyName() {
		return this.applyName;
	}

	public void setApplyName(java.lang.String applyName) {
		this.applyName = applyName;
	}

	public java.lang.String getApplyPhone() {
		return this.applyPhone;
	}

	public void setApplyPhone(java.lang.String applyPhone) {
		this.applyPhone = applyPhone;
	}

	public java.lang.Long getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}

	public java.lang.Long getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(java.lang.Long applyTime) {
		this.applyTime = applyTime;
	}

	public java.lang.Integer getSourceType() {
		return this.sourceType;
	}

	public void setSourceType(java.lang.Integer sourceType) {
		this.sourceType = sourceType;
	}

	public java.lang.Double getAmount() {
		return this.amount;
	}

	public void setAmount(java.lang.Double amount) {
		this.amount = amount;
	}

	public java.lang.Integer getAstatus() {
		return this.astatus;
	}

	public void setAstatus(java.lang.Integer astatus) {
		this.astatus = astatus;
	}

	public java.lang.String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}

	public java.lang.Long getCreateOn() {
		return this.createOn;
	}

	public void setCreateOn(java.lang.Long createOn) {
		this.createOn = createOn;
	}

	public java.lang.Long getDealBy() {
		return this.dealBy;
	}

	public void setDealBy(java.lang.Long dealBy) {
		this.dealBy = dealBy;
	}

	public java.lang.Long getDealOn() {
		return this.dealOn;
	}

	public void setDealOn(java.lang.Long dealOn) {
		this.dealOn = dealOn;
	}

	public java.lang.String getDealRemark() {
		return this.dealRemark;
	}

	public void setDealRemark(java.lang.String dealRemark) {
		this.dealRemark = dealRemark;
	}

	public java.lang.Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(java.lang.Long updateBy) {
		this.updateBy = updateBy;
	}

	public java.lang.Long getUpdateOn() {
		return this.updateOn;
	}

	public void setUpdateOn(java.lang.Long updateOn) {
		this.updateOn = updateOn;
	}

	public java.lang.Long getStatisticsDate() {
		return this.statisticsDate;
	}

	public void setStatisticsDate(java.lang.Long statisticsDate) {
		this.statisticsDate = statisticsDate;
	}

	public java.lang.Long getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(java.lang.Long releaseDate) {
		this.releaseDate = releaseDate;
	}

	public java.lang.Double getArrivalAmount() {
		return this.arrivalAmount;
	}

	public void setArrivalAmount(java.lang.Double arrivalAmount) {
		this.arrivalAmount = arrivalAmount;
	}

	public java.lang.Double getTaxDeduRate() {
		return this.taxDeduRate;
	}

	public void setTaxDeduRate(java.lang.Double taxDeduRate) {
		this.taxDeduRate = taxDeduRate;
	}

	public java.lang.Double getTaxDeduMoney() {
		return this.taxDeduMoney;
	}

	public void setTaxDeduMoney(java.lang.Double taxDeduMoney) {
		this.taxDeduMoney = taxDeduMoney;
	}

	public java.lang.Long getSourceId() {
		return this.sourceId;
	}

	public void setSourceId(java.lang.Long sourceId) {
		this.sourceId = sourceId;
	}

	public java.lang.Long getBankcardId() {
		return this.bankcardId;
	}

	public void setBankcardId(java.lang.Long bankcardId) {
		this.bankcardId = bankcardId;
	}

	public java.lang.String getBankName() {
		return this.bankName;
	}

	public void setBankName(java.lang.String bankName) {
		this.bankName = bankName;
	}

	public java.lang.String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(java.lang.String bankNo) {
		this.bankNo = bankNo;
	}

	public java.lang.String getBankAddress() {
		return this.bankAddress;
	}

	public void setBankAddress(java.lang.String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public java.lang.String getCardholderName() {
		return this.cardholderName;
	}

	public void setCardholderName(java.lang.String cardholderName) {
		this.cardholderName = cardholderName;
	}

	public java.lang.Integer getAccountType() {
		return this.accountType;
	}

	public void setAccountType(java.lang.Integer accountType) {
		this.accountType = accountType;
	}

	public String toString() {
		return "[" + "dmId:" + getDmId() + "," + "applyId:" + getApplyId() + "," + "applyName:" + getApplyName() + ","
				+ "applyPhone:" + getApplyPhone() + "," + "userId:" + getUserId() + "," + "applyTime:" + getApplyTime()
				+ "," + "sourceType:" + getSourceType() + "," + "amount:" + getAmount() + "," + "astatus:"
				+ getAstatus() + "," + "createBy:" + getCreateBy() + "," + "createOn:" + getCreateOn() + "," + "dealBy:"
				+ getDealBy() + "," + "dealOn:" + getDealOn() + "," + "dealRemark:" + getDealRemark() + ","
				+ "updateBy:" + getUpdateBy() + "," + "updateOn:" + getUpdateOn() + "," + "statisticsDate:"
				+ getStatisticsDate() + "," + "releaseDate:" + getReleaseDate() + "," + "arrivalAmount:"
				+ getArrivalAmount() + "," + "taxDeduRate:" + getTaxDeduRate() + "," + "taxDeduMoney:"
				+ getTaxDeduMoney() + "," + "sourceId:" + getSourceId() + "," + "bankcardId:" + getBankcardId() + ","
				+ "bankName:" + getBankName() + "," + "bankNo:" + getBankNo() + "," + "bankAddress:" + getBankAddress()
				+ "," + "cardholderName:" + getCardholderName() + "," + "accountType:" + getAccountType() + "]";
	}
}