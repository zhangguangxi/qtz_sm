package com.qtz.sm.goods.model;

import com.mall.core.vo.VO;

public class GdGoodsSkuRateExtOut extends VO<java.lang.Long> implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8567995593124447390L;
	private long skuId;
	private String gdName;
	private long onsaleTime;

	private String identifier;
	private String suppliername;
	private String address;

	/**
	 * 供应商供货价
	 */
	private Double supplierPrice;
	/**
	 * 溢价率
	 */
	private Double rate;
	/**
	 * 溢价后价格
	 */
	private Double increasePrice;

	@Override
	public Long getDmId() {
		return super.getDmId();
	}

	@Override
	public void setDmId(Long dmId) {
		super.setDmId(dmId);
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public long getSkuId() {
		return skuId;
	}

	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}

	public String getGdName() {
		return gdName;
	}

	public void setGdName(String gdName) {
		this.gdName = gdName;
	}

	public Double getSupplierPrice() {
		return supplierPrice;
	}

	public void setSupplierPrice(Double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}

	public Double getIncreasePrice() {
		return increasePrice;
	}

	public void setIncreasePrice(Double increasePrice) {
		this.increasePrice = increasePrice;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getSuppliername() {
		return suppliername;
	}

	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getOnsaleTime() {
		return onsaleTime;
	}

	public void setOnsaleTime(long onsaleTime) {
		this.onsaleTime = onsaleTime;
	}

}
