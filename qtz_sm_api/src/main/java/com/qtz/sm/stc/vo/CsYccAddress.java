package com.qtz.sm.stc.vo;

import java.io.Serializable;

import com.mall.core.vo.VO;

/**
 * Title:CsYccAddress<br/>
 * Description:(云仓储地址VO实体类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-29
 */
public class CsYccAddress extends VO<Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -5279849358906669191L;
    /**(主键ID)*/
//    private Long dmId;
    /**(云仓储公司ID)*/
    private Long yccId;
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

    public Long getDmId(){
        return this.dmId;
    }
    public void setDmId(Long dmId){
        this.dmId = dmId;
    }
    public Long getYccId(){
        return this.yccId;
    }
    public void setYccId(Long yccId){
        this.yccId = yccId;
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

    @Override
    public String toString() {
        return "CsYccAddress[" +
        "dmId=" + dmId +
        ",yccId=" + yccId +
        ",provinceId=" + provinceId +
        ",cityId=" + cityId +
        ",countyId=" + countyId +
        ",townId=" + townId +
        ",address=" + address +
        ",fullAddress=" + fullAddress +
        ']';
    }

}
