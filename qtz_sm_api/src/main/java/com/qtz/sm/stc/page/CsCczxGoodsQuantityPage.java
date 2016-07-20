package com.qtz.sm.stc.page;

import java.io.Serializable;
import java.util.Set;

import com.mall.core.vo.Pager;
import com.qtz.sm.stc.vo.CsCczxGoodsQuantity;

/**
 * 云仓储管理公司   商品管理   商品库   映射
 * @author Administrator
 *
 */
public class CsCczxGoodsQuantityPage extends Pager<CsCczxGoodsQuantity,Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8371833732584116963L;
	
	/**
	 * 商品编号
	 */
	private Long goodsId;
	/**
	 * 商品编号集合
	 */
	private Set<Long> goodsIds;
	/**
	 * 商品名称
	 */
	private String  goodsName;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * sku值
	 */
	private String str;

    /**(数量)*/
    private Integer stockQuantity;
    /**(待配送数量)*/
    private Integer awaitQuantity;
    
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Integer getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public Integer getAwaitQuantity() {
		return awaitQuantity;
	}
	public void setAwaitQuantity(Integer awaitQuantity) {
		this.awaitQuantity = awaitQuantity;
	}

	public Set<Long> getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(Set<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}


}
