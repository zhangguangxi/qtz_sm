package com.qtz.sm.stc.page;

import java.io.Serializable;
import java.util.Set;

import com.mall.core.vo.Pager;
import com.qtz.sm.stc.vo.CczxGoods;

/**
 * 云仓储管理公司   商品管理   商品库   映射
 * @author Administrator
 *
 */
public class CczxGoodsPage extends Pager<CczxGoods,Long> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8371833732584116963L;
	/**
	 * 商品编号
	 */
	private Long goodsId;
	
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
	/**
	 * 仓储中心进货价
	 */
	private Double yccglPrice;
	/**
	 * 议价率
	 */
	private Double bldglRate;
	/**
	 * 便利店进货价格
	 */
	private Double bldglPrice;
	
	/**
	 * 上架时间
	 */
	private Long onsaleTime;
	
	/**
	 * gd_goods_sku_rate表的主键
	 */
	private Long goodsSkuRateDmId;
	
	/**
	 * 商品状态(0在售,1停售)
	 */
	private Integer isOnsale;
	
	
	/**
	 * 起始仓储中心进货价
	 */			   
	private Double cczxInPriceStart;
	
	/**
	 * 截至仓储中心进货价
	 */
	private Double cczxInPriceEnd;
	
	/**
	 * 起始便利店进货价
	 */
	private Double bldInPriceStart;
	
	
	/**
	 * 起始便利店进货价
	 */
	private Double bldInPriceEnd;
	
	
	/**
	 * 起始上架时间
	 */
	private Long onsaleTimeStart;
	
	/**
	 * 结束上架时间
	 */
	private Long onsaleTimeEnd;

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

	public Double getYccglPrice() {
		return yccglPrice;
	}

	public void setYccglPrice(Double yccglPrice) {
		this.yccglPrice = yccglPrice;
	}

	public Double getBldglRate() {
		return bldglRate;
	}

	public void setBldglRate(Double bldglRate) {
		this.bldglRate = bldglRate;
	}

	public Double getBldglPrice() {
		return bldglPrice;
	}

	public void setBldglPrice(Double bldglPrice) {
		this.bldglPrice = bldglPrice;
	}

	public Long getOnsaleTime() {
		return onsaleTime;
	}

	public void setOnsaleTime(Long onsaleTime) {
		this.onsaleTime = onsaleTime;
	}

	public Long getOnsaleTimeStart() {
		return onsaleTimeStart;
	}

	public void setOnsaleTimeStart(Long onsaleTimeStart) {
		this.onsaleTimeStart = onsaleTimeStart;
	}

	public Long getOnsaleTimeEnd() {
		return onsaleTimeEnd;
	}

	public void setOnsaleTimeEnd(Long onsaleTimeEnd) {
		this.onsaleTimeEnd = onsaleTimeEnd;
	}

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public Long getGoodsSkuRateDmId() {
		return goodsSkuRateDmId;
	}

	public void setGoodsSkuRateDmId(Long goodsSkuRateDmId) {
		this.goodsSkuRateDmId = goodsSkuRateDmId;
	}



	public Double getBldInPriceStart() {
		return bldInPriceStart;
	}

	public void setBldInPriceStart(Double bldInPriceStart) {
		this.bldInPriceStart = bldInPriceStart;
	}

	public Double getBldInPriceEnd() {
		return bldInPriceEnd;
	}

	public void setBldInPriceEnd(Double bldInPriceEnd) {
		this.bldInPriceEnd = bldInPriceEnd;
	}

	public Double getCczxInPriceStart() {
		return cczxInPriceStart;
	}

	public void setCczxInPriceStart(Double cczxInPriceStart) {
		this.cczxInPriceStart = cczxInPriceStart;
	}

	public Double getCczxInPriceEnd() {
		return cczxInPriceEnd;
	}

	public void setCczxInPriceEnd(Double cczxInPriceEnd) {
		this.cczxInPriceEnd = cczxInPriceEnd;
	}

	public Set<Long> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(Set<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}
}
