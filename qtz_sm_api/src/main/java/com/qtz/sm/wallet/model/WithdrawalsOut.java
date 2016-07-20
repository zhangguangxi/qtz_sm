package com.qtz.sm.wallet.model;

public class WithdrawalsOut implements java.io.Serializable {

	private static final long serialVersionUID = -7402087920674744462L;

	// 提现的流水号
	private java.lang.Long id;
	// 原单Id
	private java.lang.Long sourceId;
	// 提现者ID
	private java.lang.Long userId;
	private java.lang.Integer msgCode;
	private String msg;
	
	public java.lang.Long getId() {
		return id;
	}
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	public java.lang.Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(java.lang.Long sourceId) {
		this.sourceId = sourceId;
	}
	public java.lang.Long getUserId() {
		return userId;
	}
	public void setUserId(java.lang.Long userId) {
		this.userId = userId;
	}
	public java.lang.Integer getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(java.lang.Integer msgCode) {
		this.msgCode = msgCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
