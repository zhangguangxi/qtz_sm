package com.qtz.sm.common.enums;

/** 1:Supplier 供应商，2：SupplyChain 供应链，3：CloudStorage 云仓储，4：StorageCenter 仓储中心，5：StoreManager 便利店管理公司，6：Store 便利店，7：SuperMarket 超市，8：PPSH 胖胖生活*/
public enum CompanyType {
	//供应商
	Supplier(1),
	//供应链
	SupplyChain(2),
	// 云仓储
	CloudStorage(3),
	//仓储中心
	StorageCenter(4),
	//便利店管理公司
	StoreManager(5),
	//便利店
	Store(6),
	//超市
	SuperMarket(7),
	//胖胖生活
	PPSH(8);
	
	private int value;
	
	private CompanyType(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
	
	public static Boolean existTypeValue(int companyType) {
		for (CompanyType obj : CompanyType.values()) {
			if (companyType == obj.value()) {
				return true;
			}
		}
		return false;
	}
}
