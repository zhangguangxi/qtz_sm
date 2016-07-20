package com.qtz.sm.common.enums;

/** 0:对公， 1：对私*/
public enum BankAccountType {
	BUSINESS(0), PERSON(1);
	private int value;

	private BankAccountType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
}
