package com.qtz.sm.wallet.enums;

/**
 * 钱包类型：（ 0:INCOME 对账中，1：INCOMED 应收，2：PAID 已付，3：UPPAID 未付，4：WITHDRAWALS 提现）
 * 
 */
public enum WalletItemEnum {
	INCOME(0, "对账中"),
	INCOMED(1, "应收"), 
	PAID(2,"已付"),
	UPPAID(3,"未付"),
	WITHDRAWALS(4,"提现");
	
	private WalletItemEnum(Integer value, String text) {
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
