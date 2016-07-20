/*
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.qtz.sm.goods.model;

import java.io.Serializable;

import com.mall.core.vo.VO;

/**
 * 商品分类属性值
 * 
 * @version 2016年5月26日下午3:49:26
 * @author guangxi.zhang
 */
public class GdGoodsTypePropertyOptionBO extends VO<Long> implements Serializable {
    private static final long serialVersionUID = -3709997160022632287L;
    
    /** 商品分类属性ID */
    private java.lang.Long goodsTypeProId;
    /** 值 */
    private java.lang.String val;
    /** 状态 */
    private java.lang.Integer status;
    
    public java.lang.Long getGoodsTypeProId() {
        return goodsTypeProId;
    }
    
    public java.lang.String getVal() {
        return val;
    }
    
    public java.lang.Integer getStatus() {
        return status;
    }
    
    public void setGoodsTypeProId(java.lang.Long goodsTypeProId) {
        this.goodsTypeProId = goodsTypeProId;
    }
    
    public void setVal(java.lang.String val) {
        this.val = val;
    }
    
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GdGoodsTypePropertyOptionBO [goodsTypeProId=" + goodsTypeProId + ", val=" + val + ", status=" + status
                + "]";
    }
}
