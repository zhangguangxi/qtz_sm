package com.qtz.sm.shopManage.page;

import java.io.Serializable;
import java.util.List;

import com.mall.core.vo.Pager;
import com.qtz.sm.shopManage.vo.ShopMAndSuperMKGoods;

/**
 * @Description:商品管理 商品库 映射
 * @author:SunXuan
 * @time:2016年6月12日 上午11:39:49
 */
public class ShopMAndSuperMKGoodsPage extends Pager<ShopMAndSuperMKGoods, Long>implements Serializable {

	/** 
	 * @Description: serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */
	private static final long serialVersionUID = 266428931168852542L;
	/**
	 * 商品ID
	 */
	private Long goodsId;
	/**
	 * 商品编号
	 */
	private String code;
	/**
	 * 商品名称
	 */
	private String goodsName;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * 起始便利店进货价
	 */
	private Double startPrice;
	/**
	 * 起始便利店进货价
	 */
	private Double endPrice;
	/**
	 * 起始便利店销售价格
	 */
	private Double startRatePrice;

	/**
	 * 截至便利店销售价格
	 */
	private Double endRatePrice;

	/**
	 * 起始上架时间
	 */
	private Long onsaleTimeStart;

	/**
	 * 结束上架时间
	 */
	private Long onsaleTimeEnd;
	
	/**
	 * 商品状态(0在售,1停售)
	 */
	private Integer isOnsale;
	
	//**********查询参数*****//
	private List<Long> goodsIds;
	private Integer type ;//0:供应链议价；1：云仓储管理公司议价；2：便利店管理公司议价；3：胖胖超市
	/**起始上架时间*/
	private String onsaleDateStart;
	/** 结束上架时间*/
	private String onsaleDateEnd;
	
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

	public List<Long> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(List<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(Double startPrice) {
		this.startPrice = startPrice;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}

	public Double getStartRatePrice() {
		return startRatePrice;
	}

	public void setStartRatePrice(Double startRatePrice) {
		this.startRatePrice = startRatePrice;
	}

	public Double getEndRatePrice() {
		return endRatePrice;
	}

	public void setEndRatePrice(Double endRatePrice) {
		this.endRatePrice = endRatePrice;
	}

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getOnsaleDateStart() {
		return onsaleDateStart;
	}

	public void setOnsaleDateStart(String onsaleDateStart) {
		this.onsaleDateStart = onsaleDateStart;
	}

	public String getOnsaleDateEnd() {
		return onsaleDateEnd;
	}

	public void setOnsaleDateEnd(String onsaleDateEnd) {
		this.onsaleDateEnd = onsaleDateEnd;
	}

}
