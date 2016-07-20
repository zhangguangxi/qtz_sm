package com.qtz.sm.goods.enums;

public enum PropertyDisplayTypeEnum {
	radio(0, "单选"), checkbox(1, "多选"), txt(2, "单行输入"), textarea(3, "多行输入");

	private PropertyDisplayTypeEnum(Integer value, String text) {
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
