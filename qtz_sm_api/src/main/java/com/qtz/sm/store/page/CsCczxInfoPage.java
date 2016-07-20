package com.qtz.sm.store.page;

import java.io.Serializable;
import java.util.Date;

import com.mall.core.vo.Pager;
import com.qtz.sm.store.vo.CsCczxInfo;

/**
 * Title:CsCczxInfoPage<br/>
 * Description:(仓储中心信息Page分页类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsCczxInfoPage extends Pager<CsCczxInfo,Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -1824979860326627163L;
    /**(仓储中心ID)*/
    private Long dmId;
    /**(仓储中心编号(有业务规则))*/
    private String identifier;
    /**(仓储中心(企业,公司)名称(唯一))*/
    private String name;
    /**(联系人手机)*/
    private String contactPhone;
    /**(成立时间)*/
    private Date establishTime;
    /**(加盟费)*/
    private Double joinFee;
    /**(结算周期)*/
    private Integer ettlementCycle;
    /**(供货时间)*/
    private Integer deliveryTime;
    /**(补货周期)*/
    private Integer restockCycle;
    /**(经营范围(JSON字符串))*/
    private String businessScope;
    /**(经营品牌(JSON字符串))*/
    private String businessBrand;
    /**(营业执照链接)*/
    private String licence;
    /**(身份证正面)*/
    private String idCardFront;
    /**(身份证反面)*/
    private String idCardBehind;
    /**(法人(lp,legal persion)名称)*/
    private String lpName;
    /**(法人(lp,legal persion)身份证)*/
    private String lpIdCard;
    /**(状态:0正常,1审核中,2停用)*/
    private Byte status;
    /**分成点*/
    private Integer fenRunPoint;
	/** 供货区域配送半径 */
	private String distributionRadius;
	
	
    /**起始时间*/
    private Date startTime;
    private String establishTimeStart;
    /**结束时间*/
    private Date endTime;
    private String establishTimeEnd;
    


    public Long getDmId(){
        return this.dmId;
    }
    public void setDmId(Long dmId){
        this.dmId = dmId;
    }
    public String getIdentifier(){
        return this.identifier;
    }
    public void setIdentifier(String identifier){
        this.identifier = identifier;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getContactPhone(){
        return this.contactPhone;
    }
    public void setContactPhone(String contactPhone){
        this.contactPhone = contactPhone;
    }
    public Date getEstablishTime(){
        return this.establishTime;
    }
    public void setEstablishTime(Date establishTime){
        this.establishTime = establishTime;
    }
    public Double getJoinFee(){
        return this.joinFee;
    }
    public void setJoinFee(Double joinFee){
        this.joinFee = joinFee;
    }
    public Integer getEttlementCycle(){
        return this.ettlementCycle;
    }
    public void setEttlementCycle(Integer ettlementCycle){
        this.ettlementCycle = ettlementCycle;
    }
    public Integer getDeliveryTime(){
        return this.deliveryTime;
    }
    public void setDeliveryTime(Integer deliveryTime){
        this.deliveryTime = deliveryTime;
    }
    public Integer getRestockCycle(){
        return this.restockCycle;
    }
    public void setRestockCycle(Integer restockCycle){
        this.restockCycle = restockCycle;
    }
    public String getBusinessScope(){
        return this.businessScope;
    }
    public void setBusinessScope(String businessScope){
        this.businessScope = businessScope;
    }
    public String getBusinessBrand(){
        return this.businessBrand;
    }
    public void setBusinessBrand(String businessBrand){
        this.businessBrand = businessBrand;
    }
    public String getLicence(){
        return this.licence;
    }
    public void setLicence(String licence){
        this.licence = licence;
    }
    public String getIdCardFront(){
        return this.idCardFront;
    }
    public void setIdCardFront(String idCardFront){
        this.idCardFront = idCardFront;
    }
    public String getIdCardBehind(){
        return this.idCardBehind;
    }
    public void setIdCardBehind(String idCardBehind){
        this.idCardBehind = idCardBehind;
    }
    public String getLpName(){
        return this.lpName;
    }
    public void setLpName(String lpName){
        this.lpName = lpName;
    }
    public String getLpIdCard(){
        return this.lpIdCard;
    }
    public void setLpIdCard(String lpIdCard){
        this.lpIdCard = lpIdCard;
    }
    public Byte getStatus(){
        return this.status;
    }
    public void setStatus(Byte status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "CsCczxInfoPage[" +
        "dmId=" + dmId +
        ",identifier=" + identifier +
        ",name=" + name +
        ",contactPhone=" + contactPhone +
        ",establishTime=" + establishTime +
        ",joinFee=" + joinFee +
        ",ettlementCycle=" + ettlementCycle +
        ",deliveryTime=" + deliveryTime +
        ",restockCycle=" + restockCycle +
        ",businessScope=" + businessScope +
        ",businessBrand=" + businessBrand +
        ",licence=" + licence +
        ",idCardFront=" + idCardFront +
        ",idCardBehind=" + idCardBehind +
        ",lpName=" + lpName +
        ",lpIdCard=" + lpIdCard +
        ",status=" + status +
        ",distributionRadius=" + distributionRadius +
        ']';
    }
	public java.lang.String getDistributionRadius() {
		return distributionRadius;
	}
	public void setDistributionRadius(java.lang.String distributionRadius) {
		this.distributionRadius = distributionRadius;
	}
	public Integer getFenRunPoint() {
		return fenRunPoint;
	}
	public void setFenRunPoint(Integer fenRunPoint) {
		this.fenRunPoint = fenRunPoint;
	}
	public String getEstablishTimeStart() {
		return establishTimeStart;
	}
	public void setEstablishTimeStart(String establishTimeStart) {
		this.establishTimeStart = establishTimeStart;
	}
	public String getEstablishTimeEnd() {
		return establishTimeEnd;
	}
	public void setEstablishTimeEnd(String establishTimeEnd) {
		this.establishTimeEnd = establishTimeEnd;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
