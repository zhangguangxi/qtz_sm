package com.qtz.sm.store.page;

import java.io.Serializable;

import com.mall.core.vo.Pager;
import com.qtz.sm.store.vo.CsCczxAddress;

/**
 * Title:CsCczxAddressPage<br/>
 * Description:(仓储中心地址Page分页类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-29
 */
public class CsCczxAddressPage extends Pager<CsCczxAddress,Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -185407115770048897L;
    /**(主键ID)*/
    private Long dmId;
    /**(仓储中心ID)*/
    private Long cczxId;
    /**(省ID)*/
    private Integer provinceId;
    /**(市ID)*/
    private Integer cityId;
    /**(县区ID)*/
    private Integer countyId;
    /**(镇,街道ID)*/
    private Integer townId;
    /**(村,门牌号)*/
    private String address;
    /**(地址全称)*/
    private String fullAddress;
    /**(所在经度)*/
    private Float longitude;
    /**(所在纬度)*/
    private Float latitude;

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
    public Integer getProvinceId(){
        return this.provinceId;
    }
    public void setProvinceId(Integer provinceId){
        this.provinceId = provinceId;
    }
    public Integer getCityId(){
        return this.cityId;
    }
    public void setCityId(Integer cityId){
        this.cityId = cityId;
    }
    public Integer getCountyId(){
        return this.countyId;
    }
    public void setCountyId(Integer countyId){
        this.countyId = countyId;
    }
    public Integer getTownId(){
        return this.townId;
    }
    public void setTownId(Integer townId){
        this.townId = townId;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getFullAddress(){
        return this.fullAddress;
    }
    public void setFullAddress(String fullAddress){
        this.fullAddress = fullAddress;
    }
    public Float getLongitude(){
        return this.longitude;
    }
    public void setLongitude(Float longitude){
        this.longitude = longitude;
    }
    public Float getLatitude(){
        return this.latitude;
    }
    public void setLatitude(Float latitude){
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "CsCczxAddressPage[" +
        "dmId=" + dmId +
        ",cczxId=" + cczxId +
        ",provinceId=" + provinceId +
        ",cityId=" + cityId +
        ",countyId=" + countyId +
        ",townId=" + townId +
        ",address=" + address +
        ",fullAddress=" + fullAddress +
        ",longitude=" + longitude +
        ",latitude=" + latitude +
        ']';
    }

}
