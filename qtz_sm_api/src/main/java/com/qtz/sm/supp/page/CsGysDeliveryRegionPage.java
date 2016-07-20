package com.qtz.sm.supp.page;

import java.io.Serializable;

import com.mall.core.vo.Pager;
import com.qtz.sm.supp.vo.CsGysDeliveryRegion;

/**
 * Title:CsGysDeliveryRegionPage<br/>
 * Description:(供应商供货区域Page分页类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsGysDeliveryRegionPage extends Pager<CsGysDeliveryRegion,Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = 6746469656164123438L;
    /**(主键ID)*/
    private Long dmId;
    /**(供应商ID)*/
    private Long gysId;
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
    public Long getGysId(){
        return this.gysId;
    }
    public void setGysId(Long gysId){
        this.gysId = gysId;
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
        return "CsGysDeliveryRegionPage[" +
        "dmId=" + dmId +
        ",gysId=" + gysId +
        ",provinceId=" + provinceId +
        ",cityId=" + cityId +
        ",limitType=" + limitType +
        ']';
    }

}
