package com.qtz.sm.goods.enums;
/**
 * <p>Title:商品操作动作枚举类</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 欧江波 - meoujb@163.com
 * @version 1.0 2016年5月25日
 */
public enum GoodsOperationActionEnum {
	check_unpassed("check_unpassed", "未通过审核"), check_passed("check_passed", "通过审核"), modify_price("modify_price", "修改价格"),
	modify_sku_rate("modify_sku_rate", "修改价格");
	private GoodsOperationActionEnum(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	private String value;

	private String desc;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
