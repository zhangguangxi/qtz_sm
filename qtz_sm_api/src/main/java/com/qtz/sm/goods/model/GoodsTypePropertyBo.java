package com.qtz.sm.goods.model;

import java.io.Serializable;
import java.util.List;

import com.mall.core.vo.VO;

/**
 * 商品分类属性
 * 
 * @author 谭林清
 *        
 */
public class GoodsTypePropertyBo extends VO<Long> implements Serializable {
    private static final long serialVersionUID = -8573244174025365253L;
    /**
     * 商品分类id
     */
    private Long goodsTypeId;
    /**
     * 属性名称
     */
    private String name;
    /**
     * 是否必须
     */
    private Integer required;
    /**
     * 可选值
     */
    // private String[] value;
    
    private List<GdGoodsTypePropertyOptionBO> values;
    
    /**
     * 显示类型
     */
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
    
}
