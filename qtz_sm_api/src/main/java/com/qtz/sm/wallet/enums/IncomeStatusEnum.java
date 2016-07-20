package com.qtz.sm.wallet.enums;

/**
 * 状态:0对账结算完成,1冻结中,2退货,3结算运行中,4结算失败
 * 
 * @author
 */
public enum IncomeStatusEnum {
	FINISH(0, "对账完成"),
	FREEZE(1, "冻结中"), 
	REFUND(2,"退货"),
	FAIL(4,"结算失败");
	
	private IncomeStatusEnum(Integer value, String text) {
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
