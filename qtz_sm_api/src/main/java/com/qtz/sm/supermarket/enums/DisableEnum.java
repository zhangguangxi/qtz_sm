package com.qtz.sm.supermarket.enums;

/**
 * DisableEnum
 * 
 * @version 2016年5月31日下午6:31:33
 * @author guangxi.zhang 张光喜 510647180@qq.com
 */
public enum DisableEnum {
    enable(0,"启用"),disable(1,"禁用");
    
    private DisableEnum(Integer value, String text) {
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
