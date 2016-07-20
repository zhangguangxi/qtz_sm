package com.qtz.sm.shopManage.vo;import com.mall.core.vo.VO;/** * <p>Title:ShopManage</p> * <p>Description:便利店管理公司VO类</p> * <p>Copyright: Copyright (c) 2016</p> * <p>Company: 深圳擎天柱信息技术有限公司</p> * @author 刘晓峰 - Laven * @version v1.0 2016-04-26 */public class ShopManage extends VO<Long> implements java.io.Serializable {		/**类的版本号*/	private static final long serialVersionUID = 1636495960524800L;		/** 主键ID *///	private Long dmId;	/** 公司编号 */	private String code;	/** 公司名称 */	private String name;	/** 手机号码 */	private String mobile;	/** 省id */	private Integer provinceId;	/** 城id */	private Integer cityId;	/** 县/区id */	private Integer areaId;	/** 镇/街道id */	private Integer townId;	/** 详细地址 */	private String address;	/** 结算周期 */	private Integer ettlementCycle;	/** 法人(lp,legal persion)名称 */	private String lpName;	/** 法人(lp,legal persion)身份证 */	private String lpIdCard;	/** 法人户籍所在地 */	private String lpAddress;	/** 营业执照 */	private String licence;	/** 身份证正面 */	private String idCardFront;	/** 身份证反面 */	private String idCardBehind;	/** 成立时间 */	private java.util.Date establishTime;	/**  */	private java.util.Date createTime;	/**  */	private java.util.Date updateTime;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public String getCode() {	    return this.code;	}	public void setCode(String code) {	    this.code=code;	}	public String getName() {	    return this.name;	}	public void setName(String name) {	    this.name=name;	}	public String getMobile() {	    return this.mobile;	}	public void setMobile(String mobile) {	    this.mobile=mobile;	}	public Integer getProvinceId() {	    return this.provinceId;	}	public void setProvinceId(Integer provinceId) {	    this.provinceId=provinceId;	}	public Integer getCityId() {	    return this.cityId;	}	public void setCityId(Integer cityId) {	    this.cityId=cityId;	}	public Integer getAreaId() {	    return this.areaId;	}	public void setAreaId(Integer areaId) {	    this.areaId=areaId;	}	public Integer getTownId() {	    return this.townId;	}	public void setTownId(Integer townId) {	    this.townId=townId;	}	public String getAddress() {	    return this.address;	}	public void setAddress(String address) {	    this.address=address;	}	public Integer getEttlementCycle() {	    return this.ettlementCycle;	}	public void setEttlementCycle(Integer ettlementCycle) {	    this.ettlementCycle=ettlementCycle;	}	public String getLpName() {	    return this.lpName;	}	public void setLpName(String lpName) {	    this.lpName=lpName;	}	public String getLpIdCard() {	    return this.lpIdCard;	}	public void setLpIdCard(String lpIdCard) {	    this.lpIdCard=lpIdCard;	}	public String getLpAddress() {	    return this.lpAddress;	}	public void setLpAddress(String lpAddress) {	    this.lpAddress=lpAddress;	}	public String getLicence() {	    return this.licence;	}	public void setLicence(String licence) {	    this.licence=licence;	}	public String getIdCardFront() {	    return this.idCardFront;	}	public void setIdCardFront(String idCardFront) {	    this.idCardFront=idCardFront;	}	public String getIdCardBehind() {	    return this.idCardBehind;	}	public void setIdCardBehind(String idCardBehind) {	    this.idCardBehind=idCardBehind;	}	public java.util.Date getEstablishTime() {	    return this.establishTime;	}	public void setEstablishTime(java.util.Date establishTime) {	    this.establishTime=establishTime;	}	public java.util.Date getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.util.Date createTime) {	    this.createTime=createTime;	}	public java.util.Date getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.util.Date updateTime) {	    this.updateTime=updateTime;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "code:" + getCode() +"," + "name:" + getName() +"," + "mobile:" + getMobile() +"," + "provinceId:" + getProvinceId() +"," + "cityId:" + getCityId() +"," + "areaId:" + getAreaId() +"," + "townId:" + getTownId() +"," + "address:" + getAddress() +"," + "ettlementCycle:" + getEttlementCycle() +"," + "lpName:" + getLpName() +"," + "lpIdCard:" + getLpIdCard() +"," + "lpAddress:" + getLpAddress() +"," + "licence:" + getLicence() +"," + "idCardFront:" + getIdCardFront() +"," + "idCardBehind:" + getIdCardBehind() +"," + "establishTime:" + getEstablishTime() +"," + "createTime:" + getCreateTime() +"," + "updateTime:" + getUpdateTime() +"]";	}}