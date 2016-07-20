package com.qtz.sm.scm.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mall.core.vo.VO;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;

/**
 * Title:CsGylInfo<br/>
 * Description:(供应链公司信息VO实体类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsGylInfo extends VO<Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -5680835405403831421L;
    /**(供应链公司ID)*/
//    private Long dmId;
    /**(供应链公司编号(有业务规则))*/
    private String identifier;
    /**(供应链公司(企业,公司)名称(唯一))*/
    private String name;
    /**(联系人手机)*/
    private String contactPhone;
    /**(成立时间)*/
    private Date establishTime;
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

    //非数据库字段
    /** 地址JSON */
    private String jsonAddress;

    /** 商品分类溢价信息  */
    private List<GdGoodsCategroyRate> gdGoodsCategroyRates;
   
	public List<GdGoodsCategroyRate> getGdGoodsCategroyRates() {
		return gdGoodsCategroyRates;
	}
	public void setGdGoodsCategroyRates(List<GdGoodsCategroyRate> gdGoodsCategroyRates) {
		this.gdGoodsCategroyRates = gdGoodsCategroyRates;
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
        return "CsGylInfo[" +
        "dmId=" + dmId +
        ",identifier=" + identifier +
        ",name=" + name +
        ",contactPhone=" + contactPhone +
        ",establishTime=" + establishTime +
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
        ']';
    }
	

}
