package com.qtz.sm.store.vo;

import java.io.Serializable;
import java.util.Date;

import com.mall.core.vo.VO;

/**
 * Title:CsCczxInfo<br/>
 * Description:(仓储中心信息VO实体类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsCczxInfo extends VO<Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -9118600858640430479L;
    /**(仓储中心ID)*/
//    private Long dmId;
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
    private Double fenRunPoint;
    
    
    /**起始时间*/
    private Date startTime;
    private String establishTimeStart;
    /**结束时间*/
    private Date endTime;
    private String establishTimeEnd;
    
	private Integer pageNum;
	
	private Integer pageSize;
    
    
    /** 供货区域配送半径 */
	private String distributionRadius;
	/** 仓储中心地址 */
	private String  fullAddress;
    public String getFullAddress() {
		return fullAddress;
	}
	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}
	public String getDistributionRadius() {
		return distributionRadius;
	}
	public void setDistributionRadius(String distributionRadius) {
		this.distributionRadius = distributionRadius;
	}

	//非数据库字段
    /** 地址JSON */
    private String jsonAddress;
    /** 销售区域JSON */
    private String jsonArea;
    
    public String getJsonArea() {
		return jsonArea;
	}
	public void setJsonArea(String jsonArea) {
		this.jsonArea = jsonArea;
	}
	public String getJsonAddress() {
		return jsonAddress;
	}
	public void setJsonAddress(String jsonAddress) {
		this.jsonAddress = jsonAddress;
	}
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
        return "CsCczxInfo[" +
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
	public Double getFenRunPoint() {
		return fenRunPoint;
	}
	public void setFenRunPoint(Double fenRunPoint) {
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
