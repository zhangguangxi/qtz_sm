package com.qtz.sm.shopManage.vo;

import com.mall.core.vo.VO;

/**
 * <p>
 * Title:ShopManageGoods
 * </p>
 * <p>
 * Description:商品管理 商品库 映射VO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 * 
 * @author 孙选 
 * @version v1.0 2016年6月12日 上午10:18:19
 */
public class GoodsSkuVo extends VO<java.lang.Long>implements java.io.Serializable {

	/**
	 * @Description: serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 468534608114660108L;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * sku值
	 */
	private String str;
	/**
	 * 便利店供货价（云仓储销售价）
	 */
	private Double bldglPrice;
	/**
	 * 便利店议价率
	 */
	private Double bldglRate;
	/**
	 * 便利店销售价格
	 */
	private Double bldglRatePrice;

	/**
	 * 仓储中心进货价 （云仓储销售价）
	 */
	private Double cczxPrice;
	/**
	 * 超市议价率
	 */
	private Double spmkRate;
	/**
	 * 超市销售价格
	 */
	private Double spmkRatePrice;

	/**
	 * 上架时间
	 */
	private Long onsaleTime;

	/**
	 * 商品状态(0在售,1停售)
	 */
	private Integer isOnsale;

	/**
	 * 仓储中心库存
	 */
	private Integer stock;

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

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public Double getBldglRatePrice() {
		return bldglRatePrice;
	}

	public void setBldglRatePrice(Double bldglRatePrice) {
		this.bldglRatePrice = bldglRatePrice;
	}

	public Double getCczxPrice() {
		return cczxPrice;
	}

	public void setCczxPrice(Double cczxPrice) {
		this.cczxPrice = cczxPrice;
	}

	public Double getSpmkRate() {
		return spmkRate;
	}

	public void setSpmkRate(Double spmkRate) {
		this.spmkRate = spmkRate;
	}

	public Double getSpmkRatePrice() {
		return spmkRatePrice;
	}

	public void setSpmkRatePrice(Double spmkRatePrice) {
		this.spmkRatePrice = spmkRatePrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
