package com.qtz.sm.supp.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.mall.core.common.RegexUtil;
import com.mall.core.vo.VO;
import com.qtz.sm.goods.vo.GdGoodsBrands;

/**
 * Title:CsGysInfo<br/>
 * Description:(供应商信息VO实体类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsGysInfo extends VO<Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = 4317013863146147376L;
    /**(供应商ID)*/
//    private Long dmId;
    @NotBlank(message = "编号不能为空")
    @Length(min = 16,max = 16,message = "编号格式错误")
    /**(供应商编号(有业务规则))*/
    private String identifier;

    @NotBlank(message="供应商名称不能为空")
    @Length(min=2,max=60,message="供应商名称2-60之间")
    /**(供应商(企业,公司)名称(唯一))*/
    private String name;

    @NotBlank(message="联系人手机号不能为空")
	@Pattern(regexp = RegexUtil.MOBILE,message = "联系人手机号格式错误")
    /**(联系人手机)*/
    private String contactPhone;

    /**(成立时间)*/
    private Date establishTime;

    /**(结算周期)*/
    private Integer ettlementCycle;

    /**(供货时间,天)*/
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
    //经营品牌
    private List<GdGoodsBrands> gdGoodsBrandList;
    //供货去也
    private List <CsGysDeliveryRegion> regionList;

    @NotBlank(message="法人名称不能为空")
    @Length(min=2,max=5,message="法人名称2-5之间")
    /**(法人(lp,legal person)名称)*/
    private String lpName;

    @NotBlank(message="法人身份证号不能为空")
    @Length(min=15,max=20,message="法人身份证号15-20之间")
    /**(法人(lp,legal person)身份证号)*/
    private String lpIdCard;

    /**(状态:0正常,1审核中,2停用)*/
    private Byte status;


    //非数据库字段
    /** 地址JSON */
    private String jsonAddress;
    /** 销售区域JSON */
    private String jsonArea;
    
    
    /**起始时间*/
    private Date startTime;
    private String establishTimeStart;
    /**结束时间*/
    private Date endTime;
    private String establishTimeEnd;
    
	private Integer pageNum;
	
	private Integer pageSize;

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

    public String getJsonAddress() {
        return jsonAddress;
    }

    public void setJsonAddress(String jsonAddress) {
        this.jsonAddress = jsonAddress;
    }

    public String getJsonArea() {
        return jsonArea;
    }

    public void setJsonArea(String jsonArea) {
        this.jsonArea = jsonArea;
    }

    @Override
    public String toString() {
        return "CsGysInfo{" +
                "dmId=" + dmId +
                ", identifier='" + identifier + '\'' +
                ", name='" + name + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", establishTime=" + establishTime +
                ", ettlementCycle=" + ettlementCycle +
                ", deliveryTime=" + deliveryTime +
                ", restockCycle=" + restockCycle +
                ", businessScope='" + businessScope + '\'' +
                ", businessBrand='" + businessBrand + '\'' +
                ", licence='" + licence + '\'' +
                ", idCardFront='" + idCardFront + '\'' +
                ", idCardBehind='" + idCardBehind + '\'' +
                ", lpName='" + lpName + '\'' +
                ", lpIdCard='" + lpIdCard + '\'' +
                ", status=" + status +
                ", jsonAddress='" + jsonAddress + '\'' +
                ", jsonArea='" + jsonArea + '\'' +
                '}';
    }
	public Date getEstablishTime() {
		return establishTime;
	}
	public void setEstablishTime(Date establishTime) {
		this.establishTime = establishTime;
	}
	public List<GdGoodsBrands> getGdGoodsBrandList() {
		return gdGoodsBrandList;
	}
	public void setGdGoodsBrandList(List<GdGoodsBrands> gdGoodsBrandList) {
		this.gdGoodsBrandList = gdGoodsBrandList;
	}
	public List<CsGysDeliveryRegion> getRegionList() {
		return regionList;
	}
	public void setRegionList(List<CsGysDeliveryRegion> regionList) {
		this.regionList = regionList;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getEstablishTimeStart() {
		return establishTimeStart;
	}
	public void setEstablishTimeStart(String establishTimeStart) {
		this.establishTimeStart = establishTimeStart;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
}
