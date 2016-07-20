package com.qtz.sm.session.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class UserClientInfo implements java.io.Serializable {
	/**()*/
	private static final long serialVersionUID = 1L;
	/**
	 * 终端名称
	 */
	private String clientName = "";
	/**
	 * 终端系统
	 */
	private String clientOS = "";
	/**
	 * 终端IP
	 */
	private String clientIP = "";
	/**
	 * 终端网络
	 */
	private String clientNet = "";
	/**
	 * 客户端物理地址
	 */
	private String macAddress = "";
	/**
	 * 客户端版本信息
	 */
	@NotEmpty(message = "不能为空")
	private String version = "";
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientOS() {
		return clientOS;
	}

	public void setClientOS(String clientOS) {
		this.clientOS = clientOS;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getClientNet() {
		return clientNet;
	}

	public void setClientNet(String clientNet) {
		this.clientNet = clientNet;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}

