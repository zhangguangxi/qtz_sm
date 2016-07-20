package com.qtz.sm.goods.model;

import java.io.Serializable;
/**
 * 
* @Title: GdGoodsCategroyRateUpdateBo.java
* @Package com.qtz.sm.goods.model
* @Description: 更新商品分类溢价的时候使用
* @author 欧江波 meoujb@163.com
* @date 2016年6月21日 上午10:14:33
* @version V1.0
 */
public class GdGoodsCategroyRateUpdateBo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//商品分类溢价主键ID
	private Long dmId;
	
	//价格
	private double rate;
	
	public GdGoodsCategroyRateUpdateBo(){
		
	}

	public GdGoodsCategroyRateUpdateBo(Long dmId, double rate) {
		super();
		this.dmId = dmId;
		this.rate = rate;
	}

	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "GdGoodsCategroyRateUpdateBo [dmId=" + dmId + ", rate=" + rate + "]";
	}
}
