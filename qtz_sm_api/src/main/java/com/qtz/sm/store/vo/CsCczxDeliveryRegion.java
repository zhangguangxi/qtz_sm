package com.qtz.sm.store.vo;

import java.io.Serializable;

import com.mall.core.vo.VO;

/**
 * Title:CsCczxDeliveryRegion<br/>
 * Description:(仓储中心供货区域VO实体类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsCczxDeliveryRegion extends VO<Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = 1617434607884977046L;
    /**(主键ID)*/
    private Long dmId;
    /**(仓储中心ID)*/
    private Long cczxId;
    /**(省ID)*/
    private Long provinceId;
    /**(市ID)*/
    private Long cityId;
    /**(限制类型(0允许;1禁止))*/
    private Byte limitType;

    public Long getDmId(){
        return this.dmId;
    }
    public void setDmId(Long dmId){
        this.dmId = dmId;
    }
    public Long getCczxId(){
        return this.cczxId;
    }
    public void setCczxId(Long cczxId){
        this.cczxId = cczxId;
    }
    public Long getProvinceId(){
        return this.provinceId;
    }
    public void setProvinceId(Long provinceId){
        this.provinceId = provinceId;
    }
    public Long getCityId(){
        return this.cityId;
    }
    public void setCityId(Long cityId){
        this.cityId = cityId;
    }
    public Byte getLimitType(){
        return this.limitType;
    }
    public void setLimitType(Byte limitType){
        this.limitType = limitType;
    }

    @Override
    public String toString() {
        return "CsCczxDeliveryRegion[" +
        "dmId=" + dmId +
        ",cczxId=" + cczxId +
        ",provinceId=" + provinceId +
        ",cityId=" + cityId +
        ",limitType=" + limitType +
        ']';
    }

}