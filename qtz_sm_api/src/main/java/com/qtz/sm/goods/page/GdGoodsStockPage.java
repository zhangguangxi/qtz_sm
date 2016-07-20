package com.qtz.sm.goods.page;

import java.io.Serializable;

import com.mall.core.vo.Pager;
import com.qtz.sm.goods.model.GdGoodsStock;

/**
 * Title:GdGoodsStock<br/>
 * Description:(商品库存模型--用于商品库展示数据使用)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class GdGoodsStockPage extends Pager<GdGoodsStock,java.lang.Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = -1125590374348665813L;
    /**(在售状态)*/
    private Integer isOnsale;
    /**(议价类型 0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市议价)*/
    private Integer rateType;
    /**(开始时间)*/
    private Long startTimeStamp;
    /**(结束时间)*/
    private Long endTimeStamp;
    /**(最小金额)*/
    private Long minPrice;
    /**(最大金额)*/
    private Long maxPrice;
    
	public Integer getIsOnsale() {
		return isOnsale;
	}
	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}
	public Integer getRateType() {
		return rateType;
	}
	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}
	public Long getStartTimeStamp() {
		return startTimeStamp;
	}
	public void setStartTimeStamp(Long startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
	}
	public Long getEndTimeStamp() {
		return endTimeStamp;
	}
	public void setEndTimeStamp(Long endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
	}
	public Long getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}
	public Long getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
	}
	

}
