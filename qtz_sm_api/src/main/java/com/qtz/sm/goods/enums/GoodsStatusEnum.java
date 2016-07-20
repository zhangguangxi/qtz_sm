package com.qtz.sm.goods.enums;

import utils.StringUtils;

/**
 * <p>Title:商品状态</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 欧江波 - meoujb@163.com
 * @version 1.0 2016年5月24日
 */
public enum GoodsStatusEnum {

	notCheck(0, "尚未审核"),  checkPassed(1, "审核通过"), checkNotPassed(2, "审核未通过"), shelves(3, "上架"), unShelves(4, "下架"), all(10, "全部");

	private GoodsStatusEnum(Integer value, String text) {
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
	
	/**
	 * 根据商品状态ID获取商品状态描述信息
	 * @param value
	 * @return
	 */
	public static String getTextByValue(Integer value){
		for (GoodsStatusEnum en : GoodsStatusEnum.values()) {  
            if  (en.getValue() == value) {  
                return  en.getText();  
            }  
        }  
		return "";
	}
	/***
	 * 获取商品状态ID串(不包含全部状态值10)，格式为"1,2,3"
	 * @param withQuotes 是否包含引号
	 * @return
	 */
	public static String getAllValuesStr(boolean withQuotes){
		StringBuffer sb = new StringBuffer();
		for (GoodsStatusEnum en : GoodsStatusEnum.values()) {  
			if (!en.getValue().equals(all.getValue())){
				if (withQuotes){
					sb.append("'");
				}
				sb.append(en.getValue());
				if (withQuotes){
					sb.append("'");
				}
				sb.append(",");
			}
        }  
		if (StringUtils.isNotBlank(sb.toString())){
			return sb.substring(0, sb.length()-1);	
		}
		return "";
	}
}
