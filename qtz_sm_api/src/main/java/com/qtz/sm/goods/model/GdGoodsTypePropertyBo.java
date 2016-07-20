/*
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.qtz.sm.goods.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.VO;

/**
 * 
 * GdGoodsTypePropertyBo
 * 
 * @version 2016年5月29日下午3:46:54
 * @author guangxi.zhang 张光喜 510647180@qq.com
 */
public class GdGoodsTypePropertyBo extends VO<Long> implements Serializable {
    
    private static final long serialVersionUID = -6445349181886433442L;
    /** 商品分类id */
    private Long goodsTypeId;

    /** 属性名称 */
    private String name;

    /** 是否必须 */
    private Integer required;
    
    /** 可选值 */
    private List<GdGoodsTypePropertyOptionBO> values = new ArrayList<GdGoodsTypePropertyOptionBO>();
    
    /** 显示类型 */
    private Integer displayType;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getRequired() {
        return required;
    }
    
    public void setRequired(Integer required) {
        this.required = required;
    }
    
    public Integer getDisplayType() {
        return displayType;
    }
    
    public void setDisplayType(Integer displayType) {
        this.displayType = displayType;
    }
    
    public List<GdGoodsTypePropertyOptionBO> getValues() {
        return values;
    }
    
    public void setValues(List<GdGoodsTypePropertyOptionBO> values) {
        this.values = values;
    }
    
    public Long getGoodsTypeId() {
        return goodsTypeId;
    }
    
    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

	@Override
	public String toString() {
		return "GdGoodsTypePropertyBo [goodsTypeId=" + goodsTypeId + ", name=" + name + ", required=" + required
				+ ", values=" + values + ", displayType=" + displayType + "]";
	}
}
