package com.qtz.sm.wallet.model;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.qtz.sm.wallet.validator.ValidFirst;

public class WtOrder implements Serializable {

	private static final long serialVersionUID = -8049642660350483007L;
	
	/** 超市订单 */
	public static final int SUPERMARKET_ORDER = 1;
	/** 便利店订单*/
	public static final int SHOP_ORDER = 2;
	
	/** 订单ID */
	@NotNull(message="116101:订单ID不能为空", groups=ValidFirst.class)
	private Long orderId;
	
	/** 订单类型 1：超市， 2：便利店 */
	@NotNull(message="116102:订单类型不能为空", groups=ValidFirst.class)
	private Integer orderType;
	
	/** 店铺ID， 超市订单为超市ID， 便利店订单为便利店ID */
	@NotNull(message="116103:店铺ID不能为空")
	private Long shopId;
	
	/** 仓储中心ID */
	@NotNull(message="116104:仓储中心ID不能为空")
	private Long storageId;
	
//	/** 订单支付完成时间，以该时间+结算周期 确定 收入结算时间， 单位：毫秒 */
//	private Long orderFinishTime;
	
	/** 订单明细列表 */
	@Valid
	@NotEmpty(message="116105:订单明细不能为空")
	private List<WtOrderDetail> orderDetails;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}

	public List<WtOrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<WtOrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
}
