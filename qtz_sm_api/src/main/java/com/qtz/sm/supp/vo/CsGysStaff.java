package com.qtz.sm.supp.vo;

import java.io.Serializable;

import com.mall.core.vo.VO;

/**
 * Title:CsGysStaff<br/>
 * Description:(供应商员工VO实体类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsGysStaff extends VO<Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = 7951581396100336349L;
    /**(主键ID)*/
//    private Long dmId;
    /**(供应商ID)*/
    private Long gysId;
    /**(名称)*/
    private String name;
    /**(密码)*/
    private String pwd;
    /**(手机号)*/
    private String phone;
    /**(账号类型(0管理员,1普通员工))*/
    private Byte accountType;
    /**(状态:0正常,1离职)*/
    private Byte status;

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
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getPwd(){
        return this.pwd;
    }
    public void setPwd(String pwd){
        this.pwd = pwd;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public Byte getAccountType(){
        return this.accountType;
    }
    public void setAccountType(Byte accountType){
        this.accountType = accountType;
    }
    public Byte getStatus(){
        return this.status;
    }
    public void setStatus(Byte status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "CsGysStaff[" +
        "dmId=" + dmId +
        ",gysId=" + gysId +
        ",name=" + name +
        ",pwd=" + pwd +
        ",phone=" + phone +
        ",accountType=" + accountType +
        ",status=" + status +
        ']';
    }

}
