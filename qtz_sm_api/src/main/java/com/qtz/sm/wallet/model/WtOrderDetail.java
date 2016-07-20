package com.qtz.sm.wallet.model;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.qtz.sm.wallet.validator.ValidFirst;

/**
 * 订单商品明细
 * 
 */
@GroupSequence(value = { ValidFirst.class, WtOrderDetail.class})
public class WtOrderDetail implements Serializable {
	private static final long serialVersionUID = 162604605230598503L;
	/** 订单商品明细dmId*/
	@NotNull(message="116111:订单明细ID不能为空", groups={ValidFirst.class})
	private Long orderItemId;
	
	 /*订单商品明细sku*/
	@NotNull(message="116112:商品SKU编号不能为空")
	private Long sku;
	
	/** 商品属性规格描述， 提供颜色 尺码即可，如： 红色，XL  */
	@NotEmpty(message="116113:商品SKU基本描述不能为空")
	private String skuDescription;
	
	/**商品ID*/
	@NotNull(message="116114:商品ID不能为空")
	private Long goodsId;
	
	/** 商品名称 */
	@NotNull(message="116115:商品名称不能为空")
	private String goodsName;
	
	/** 供应商Id */
	@NotNull(message="116116:商品供应商Id不能为空")
	private Long supplierId;
	
	/**订单商品明细sku 的件数 */
	@NotNull(message="116117:商品SKU的件数不能为空")
	@Min(value=1, message="116117:商品SKU 的件数至少1个")
	private Integer total;
	
	/** 供应商商品SKU出货价格 */
	@NotNull(message="116118:供应商商品SKU出货价格不能为空")
	@Min(value=0, message="116118:供应商商品SKU出货价格必须大于0")
	private Double supplierPrice;
	
	/** 供应链商品SKU 溢价率(premium) */
	@NotNull(message="116119:供应链商品SKU溢价率不能为空")
	@Min(value=0, message="116119:供应链商品SKU溢价率比须大于0")
	private Double gylPremiumRate;
	
	/** 云仓储管理公司商品SKU溢价率 */
	@NotNull(message="116120:云仓储管理公司商品SKU溢价率不能为空")
	@Min(value=0, message="116120:云仓储管理公司商品SKU溢价率须大于0")
	private Double yccglPremiumRate;
	
	/** 便利店管理公司商品 sku溢价率 */
//	@NotNull(message="116121:便利店管理公司商品SKU溢价率须不能为空")
	@Min(value=0, message="116121:便利店管理公司商品SKU溢价率须大于0")
	private Double bldglPremiumRate;
	
	/** 超市商品 sku溢价率 */
//	@NotNull(message="116121:超市商品SKU溢价率须大于0")
	@Min(value=0, message="116121:超市商品SKU溢价率须大于0")
	private Double csPremiumRate;
	
	/** 订单 sku 的 价格 */
	@NotNull(message="116122:商品SKU价格不能为空")
	@Min(value=0, message="116122:商品SKU价格须大于0")
	private Double price;
	
	/** 总价 */
	@NotNull(message="116123:商品SKU的购买总价不能为空")
	@Min(value=0, message="116121:商品SKU购买总价须大于0")
	private Double amount;
	
	
	public Long getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}
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
	public Long getSku() {
		return sku;
	}
	public void setSku(Long sku) {
		this.sku = sku;
	}
	public String getSkuDescription() {
		return skuDescription;
	}
	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Double getSupplierPrice() {
		return supplierPrice;
	}
	public void setSupplierPrice(Double supplierPrice) {
		this.supplierPrice = supplierPrice;
	}
	public Double getGylPremiumRate() {
		return gylPremiumRate;
	}
	public void setGylPremiumRate(Double gylPremiumRate) {
		this.gylPremiumRate = gylPremiumRate;
	}
	public Double getYccglPremiumRate() {
		return yccglPremiumRate;
	}
	public void setYccglPremiumRate(Double yccglPremiumRate) {
		this.yccglPremiumRate = yccglPremiumRate;
	}
	public Double getBldglPremiumRate() {
		return bldglPremiumRate;
	}
	public void setBldglPremiumRate(Double bldglPremiumRate) {
		this.bldglPremiumRate = bldglPremiumRate;
	}
	public Double getCsPremiumRate() {
		return csPremiumRate;
	}
	public void setCsPremiumRate(Double csPremiumRate) {
		this.csPremiumRate = csPremiumRate;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
	@Override
	public String toString() {
		return "WtOrderDetail [orderItemId=" + orderItemId + ", sku=" + sku + ", skuDescription=" + skuDescription + ", goodsId="
				+ goodsId + ", goodsName=" + goodsName + ", supplierId=" + supplierId + ", total=" + total
				+ ", supplierPrice=" + supplierPrice + ", gylPremiumRate=" + gylPremiumRate + ", yccglPremiumRate="
				+ yccglPremiumRate + ", bldglPremiumRate=" + bldglPremiumRate + ", csPremiumRate=" + csPremiumRate
				+ ", price=" + price + ", amount=" + amount + "]";
	}
}
