package com.mall.core.common.enums;

public enum YesOrNoEnum {
	YES(0, "是"), NO(1, "否");

	private YesOrNoEnum(Integer value, String text) {
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
