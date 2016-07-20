/*
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.qtz.sm.goods.model;

import java.io.Serializable;

import com.mall.core.vo.VO;

/**
 * GdGoodsTypeJson
 * 
 * @version 2016年5月29日下午3:31:40
 * @author guangxi.zhang 张光喜 510647180@qq.com
 */
public class GdGoodsTypeBo extends VO<Long> implements Serializable{
    
    private static final long serialVersionUID = 1L;

    /** 商品分类名称 */
    private String name;
    
    /** 商品父类id */
    private Long parentId;
    
    /** 商品层级 */
    private Integer level;
    
    public GdGoodsTypeBo() {
    
    }
    
    public GdGoodsTypeBo(Long goodsTypeId, String name, Long parentId, Integer level) {
        super();
        this.dmId = goodsTypeId;
        this.name = name;
        this.parentId = parentId;
        this.level = level;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getParentId() {
        return parentId;
    }
    
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    
    public Integer getLevel() {
        return level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        return "GdGoodsTypeJson [goodsTypeId=" + dmId + ", name=" + name + ", parentId=" + parentId + ", level="
                + level + "]";
    }
    
}
