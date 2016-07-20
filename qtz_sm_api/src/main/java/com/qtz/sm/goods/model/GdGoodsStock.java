package com.qtz.sm.goods.model;

import java.io.Serializable;

import com.mall.core.vo.VO;
import com.qtz.sm.goods.vo.GdGoods;

/**
 * Title:GdGoodsStock<br/>
 * Description:(商品库存模型--用于商品库展示数据使用)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class GdGoodsStock extends VO<Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -1125590374348665813L;
    /**(主键ID SKU议价主键 rateId)*/
    private Long rateId;
    /**(供应商ID)*/
    private Long gysId;
    /**(商品SKU)*/
    private Long skuId;
    /**(出厂价格)*/
    private Double price;
    /** 商品编号 */
    private String goodsNum;
    /** 商品名称 */
    private String goodsName;
    /** 商品ID */
    private Long goodsId;
    /** 上架时间 */
    private Long onsaleTime;
    /** 商品来源（供应商） */
    private String source;
    /** 议价后价格 */
    private Double ratePrice;
    /** 议价率 */
    private Double rate;
    /** 议价状态（是否完成SKU溢价 0：未完成，1：已完成） */
    private Integer rateStatus;
    /** 议价类型（0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市议价；） */
    private Integer rateType;
    
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
    public Long getGysId(){
        return this.gysId;
    }
    public void setGysId(Long gysId){
        this.gysId = gysId;
    }
    public Long getSkuId(){
        return this.skuId;
    }
    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Double getRatePrice() {
		return ratePrice;
	}
	public void setRatePrice(Double ratePrice) {
		this.ratePrice = ratePrice;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getRateStatus() {
		return rateStatus;
	}
	public void setRateStatus(Integer rateStatus) {
		this.rateStatus = rateStatus;
	}
	public Integer getRateType() {
		return rateType;
	}
	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}
	public Long getRateId() {
		return rateId;
	}
	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}
	public Long getOnsaleTime() {
		return onsaleTime;
	}
	public void setOnsaleTime(Long onsaleTime) {
		this.onsaleTime = onsaleTime;
	}
}
