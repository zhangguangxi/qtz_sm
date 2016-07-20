package com.qtz.sm.common.vo;

import java.io.Serializable;

import com.mall.core.vo.VO;

/**
 * Title:CsRegions<br/>
 * Description:(行政区域VO实体类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-21
 */
public class CsRegions extends VO<Integer> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -7136238137771931713L;
    /**(区域ID)*/
//    private Integer dmId;
    /**(父级ID)*/
    private Integer parentId;
    /**(名称)*/
    private String name;
    /**(简称)*/
    private String shortName;
    /**(经度)*/
    private Float longitude;
    /**(纬度)*/
    private Float latitude;
    /**(电话区号)*/
    private String cityCode;
    /**(邮政编码)*/
    private String zipCode;
    /**(等级(1省/直辖市,2地级市,3区县,4镇/街道))*/
    private Byte level;
    /**(排序)*/
    private Byte sort;
    /**(状态(0禁用/1启用))*/
    private Byte status;

    public Integer getDmId(){
        return this.dmId;
    }
    public void setDmId(Integer dmId){
        this.dmId = dmId;
    }
    public Integer getParentId(){
        return this.parentId;
    }
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getShortName(){
        return this.shortName;
    }
    public void setShortName(String shortName){
        this.shortName = shortName;
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
    public String getCityCode(){
        return this.cityCode;
    }
    public void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public String getZipCode(){
        return this.zipCode;
    }
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    public Byte getLevel(){
        return this.level;
    }
    public void setLevel(Byte level){
        this.level = level;
    }
    public Byte getSort(){
        return this.sort;
    }
    public void setSort(Byte sort){
        this.sort = sort;
    }
    public Byte getStatus(){
        return this.status;
    }
    public void setStatus(Byte status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "CsRegions[" +
        "dmId=" + dmId +
        ",parentId=" + parentId +
        ",name=" + name +
        ",shortName=" + shortName +
        ",longitude=" + longitude +
        ",latitude=" + latitude +
        ",cityCode=" + cityCode +
        ",zipCode=" + zipCode +
        ",level=" + level +
        ",sort=" + sort +
        ",status=" + status +
        ']';
    }

}
