package com.qtz.sm.session.vo;

public class User implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1055154874009600L;
	/** 用户ID */
	private long dmId;
	/** 姓名 */
	private java.lang.String name;
	/** 手机号 */
	private java.lang.String phone;
	/** 登录账号 */
	private java.lang.String account;
	/** 密码 */
	private java.lang.String pwd;
	
	/**(账号类型(0管理员,1普通员工))*/
    private Integer accountType;
    /**
	所在公司类别 (供应商:1 供应链:2  云仓储:3  仓储中心:4 便利店管理公司:5 便利店:6 超市:7)
    * */
	private int companyType;
	/**
	 * 所在公司ID
	 */
	private long companyDmId;
	/**
	 * 所在公司名称
	 */
	private String companyName;
	
	/**(法人(lp,legal person)名称)*/
    private String lpName;
	
	/** 0  正常  1 屏蔽 */
	private java.lang.Integer status;
	/**禁用说明**/
	private String stopTxt;
	/***禁用时间**/
	private Long stopTime;
	/** 注册时间 */
	private java.lang.Long ctrime;
	/**
	 * 账号变化次数 默认0
	 */
	private Integer accountChanges;
	/**
	 * 推送Code值
	 */
	private String pushCode;
	/**
	 * 步奏  0未开,1 注册, 2完善   3等待审核
	 */ 
	private Integer  steps  ;
	
	/** 所属仓储中心ID */
	private Long cczxId;
	
	/** 超市ID */
	private Long supermarketId;
	
	private String ip;//用户操作IP
	
	public long getDmId() {
		return dmId;
	}
	public void setDmId(long dmId) {
		this.dmId = dmId;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getPhone() {
		return phone;
	}
	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}
	public java.lang.String getAccount() {
		return account;
	}
	public void setAccount(java.lang.String account) {
		this.account = account;
	}
	public java.lang.String getPwd() {
		return pwd;
	}
	public void setPwd(java.lang.String pwd) {
		this.pwd = pwd;
	}
	
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public int getCompanyType() {
		return companyType;
	}
	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}
	public long getCompanyDmId() {
		return companyDmId;
	}
	public void setCompanyDmId(long companyDmId) {
		this.companyDmId = companyDmId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getLpName() {
		return lpName;
	}
	public void setLpName(String lpName) {
		this.lpName = lpName;
	}
	public java.lang.Integer getStatus() {
		return status;
	}
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	public String getStopTxt() {
		return stopTxt;
	}
	public void setStopTxt(String stopTxt) {
		this.stopTxt = stopTxt;
	}
	public Long getStopTime() {
		return stopTime;
	}
	public void setStopTime(Long stopTime) {
		this.stopTime = stopTime;
	}
	public java.lang.Long getCtrime() {
		return ctrime;
	}
	public void setCtrime(java.lang.Long ctrime) {
		this.ctrime = ctrime;
	}
	public Integer getAccountChanges() {
		return accountChanges;
	}
	public void setAccountChanges(Integer accountChanges) {
		this.accountChanges = accountChanges;
	}
	public String getPushCode() {
		return pushCode;
	}
	public void setPushCode(String pushCode) {
		this.pushCode = pushCode;
	}
	public Integer getSteps() {
		return steps;
	}
	public void setSteps(Integer steps) {
		this.steps = steps;
	}
	public Long getCczxId() {
		return cczxId;
	}
	public void setCczxId(Long cczxId) {
		this.cczxId = cczxId;
	}
	public Long getSupermarketId() {
		return supermarketId;
	}
	public void setSupermarketId(Long supermarketId) {
		this.supermarketId = supermarketId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

}
