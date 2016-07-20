package com.qtz.sm.wallet.enums;

/**
 * 提现状态 (0：表示对账中，1：表示已提现，2：表示驳回，3：表示取消)
 * 
 * <p>
 * Title:GoodsCategory
 * </p>
 * <p>
 * Description:(类描述)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 
 * @version
 */
public enum WithdrawalsStatusEnum {

	APPLY(0, "对账中"), APPLIED(1, "已提现"), APPLYBACK(2, "驳回"), APPLYCANCEL(3, "取消"),RECOVER(4,"追回");
	
	public static final String PAYMENT_APPLYBACK = "货款提现(驳回)";
	public static final String RUNSUB_APPLYBACK = "分润提现(驳回)";
	public static final String CASHBACK_APPLYBACK = "返现提现(驳回)";
	public static final String REIM_APPLYBACK = "退款提现(驳回)";
	
	public static final String PAYMENT_RECOVER = "货款提现(追回)";
	public static final String RUNSUB_RECOVER = "分润提现(追回)";
	public static final String CASHBACK_RECOVER = "返现提现(追回)";
	public static final String REIM_RECOVER = "退款提现(追回)";
	

	private WithdrawalsStatusEnum(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	private Integer value;

	private String text;

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
