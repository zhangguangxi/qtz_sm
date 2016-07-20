package com.qtz.sm.stc.page;

import java.util.Date;

import com.mall.core.vo.Pager;

/**

 * <p>Title:CsYccInfoPage</p>

 * <p>Description:云仓储公司信息分页类</p>

 * <p>Copyright: Copyright (c) 2016</p>

 * <p>Company: 深圳市好实再商贸有限公司</p>

 * @author 谷一帅- 75423426@qq.com

 * @version v1.0 2016-05-19

 */

public class CsYccInfoPage extends Pager<com.qtz.sm.stc.vo.CsYccInfo,java.lang.Long> implements java.io.Serializable{



	/**类的版本号*/

	private static final long serialVersionUID = 1669004535515137L;



	
	/** 云仓储公司ID */
	private java.lang.Long dmId;
	/** 云仓储公司编号(有业务规则) */
	private java.lang.String identifier;
	/** 云仓储公司(企业,公司)名称(唯一) */
	private java.lang.String name;
	/** 联系人手机 */
	private java.lang.String contactPhone;
	/** 成立时间 */
	private Date establishTime;
	/** 结算周期 */
	private java.lang.Integer ettlementCycle;
	/** 供货时间 */
	private java.lang.Integer deliveryTime;
	/** 补货周期 */
	private java.lang.Integer restockCycle;
	/** 经营范围(JSON字符串) */
	private java.lang.String businessScope;
	/** 经营品牌(JSON字符串) */
	private java.lang.String businessBrand;
	/** 营业执照链接 */
	private java.lang.String licence;
	/** 身份证正面 */
	private java.lang.String idCardFront;
	/** 身份证反面 */
	private java.lang.String idCardBehind;
	/** 法人(lp,legal person)名称 */
	private java.lang.String lpName;
	/** 法人(lp,legal person)身份证 */
	private java.lang.String lpIdCard;
	/** 状态:0正常,1审核中,2停用 */
	private java.lang.Integer status;
	/** 供货区域 */
	private java.lang.String supply;
	/** 配送半径 */
	private java.lang.String distributionRadius;
	/** 加盟费 */
	private java.lang.Double leaguePrice;
	public java.lang.Long getDmId() {
	    return this.dmId;
	}
	public void setDmId(java.lang.Long dmId) {
	    this.dmId=dmId;
	}
	public java.lang.String getIdentifier() {
	    return this.identifier;
	}
	public void setIdentifier(java.lang.String identifier) {
	    this.identifier=identifier;
	}
	public java.lang.String getName() {
	    return this.name;
	}
	public void setName(java.lang.String name) {
	    this.name=name;
	}
	public java.lang.String getContactPhone() {
	    return this.contactPhone;
	}
	public void setContactPhone(java.lang.String contactPhone) {
	    this.contactPhone=contactPhone;
	}
	public Date getEstablishTime() {
	    return this.establishTime;
	}
	public void setEstablishTime(Date establishTime) {
	    this.establishTime=establishTime;
	}
	public java.lang.Integer getEttlementCycle() {
	    return this.ettlementCycle;
	}
	public void setEttlementCycle(java.lang.Integer ettlementCycle) {
	    this.ettlementCycle=ettlementCycle;
	}
	public java.lang.Integer getDeliveryTime() {
	    return this.deliveryTime;
	}
	public void setDeliveryTime(java.lang.Integer deliveryTime) {
	    this.deliveryTime=deliveryTime;
	}
	public java.lang.Integer getRestockCycle() {
	    return this.restockCycle;
	}
	public void setRestockCycle(java.lang.Integer restockCycle) {
	    this.restockCycle=restockCycle;
	}
	public java.lang.String getBusinessScope() {
	    return this.businessScope;
	}
	public void setBusinessScope(java.lang.String businessScope) {
	    this.businessScope=businessScope;
	}
	public java.lang.String getBusinessBrand() {
	    return this.businessBrand;
	}
	public void setBusinessBrand(java.lang.String businessBrand) {
	    this.businessBrand=businessBrand;
	}
	public java.lang.String getLicence() {
	    return this.licence;
	}
	public void setLicence(java.lang.String licence) {
	    this.licence=licence;
	}
	public java.lang.String getIdCardFront() {
	    return this.idCardFront;
	}
	public void setIdCardFront(java.lang.String idCardFront) {
	    this.idCardFront=idCardFront;
	}
	public java.lang.String getIdCardBehind() {
	    return this.idCardBehind;
	}
	public void setIdCardBehind(java.lang.String idCardBehind) {
	    this.idCardBehind=idCardBehind;
	}
	public java.lang.String getLpName() {
	    return this.lpName;
	}
	public void setLpName(java.lang.String lpName) {
	    this.lpName=lpName;
	}
	public java.lang.String getLpIdCard() {
	    return this.lpIdCard;
	}
	public void setLpIdCard(java.lang.String lpIdCard) {
	    this.lpIdCard=lpIdCard;
	}
	public java.lang.Integer getStatus() {
	    return this.status;
	}
	public void setStatus(java.lang.Integer status) {
	    this.status=status;
	}
	public java.lang.String getSupply() {
	    return this.supply;
	}
	public void setSupply(java.lang.String supply) {
	    this.supply=supply;
	}
	public java.lang.String getDistributionRadius() {
	    return this.distributionRadius;
	}
	public void setDistributionRadius(java.lang.String distributionRadius) {
	    this.distributionRadius=distributionRadius;
	}
	public java.lang.Double getLeaguePrice() {
	    return this.leaguePrice;
	}
	public void setLeaguePrice(java.lang.Double leaguePrice) {
	    this.leaguePrice=leaguePrice;
	}
	public String toString() {
	    return "[" + "dmId:" + getDmId() +"," + "identifier:" + getIdentifier() +"," + "name:" + getName() +"," + "contactPhone:" + getContactPhone() +"," + "establishTime:" + getEstablishTime() +"," + "ettlementCycle:" + getEttlementCycle() +"," + "deliveryTime:" + getDeliveryTime() +"," + "restockCycle:" + getRestockCycle() +"," + "businessScope:" + getBusinessScope() +"," + "businessBrand:" + getBusinessBrand() +"," + "licence:" + getLicence() +"," + "idCardFront:" + getIdCardFront() +"," + "idCardBehind:" + getIdCardBehind() +"," + "lpName:" + getLpName() +"," + "lpIdCard:" + getLpIdCard() +"," + "status:" + getStatus() +"," + "supply:" + getSupply() +"," + "distributionRadius:" + getDistributionRadius() +"," + "leaguePrice:" + getLeaguePrice() +"]";
	}

}