/*
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.qtz.sm.goods.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.VO;

/**
 * 商品分类详情前后台JSON交互对象
 * 
 * @version 2016年5月29日下午3:09:00
 * @author guangxi.zhang 张光喜 510647180@qq.com
 */
public class GdGoodsTypeOutJson extends VO<Long> implements Serializable {
    private static final long serialVersionUID = -8723069465203719859L;
    
    /** 商品分类基本信息 */
    private GdGoodsTypeBo goodsTypeJson;
    
    /** 商品基本属性 */
    List<GdGoodsTypePropertyBo> basicProps = new ArrayList<GdGoodsTypePropertyBo>();
    
    /** 商品销售属性 */
    List<GdGoodsTypePropertyBo> saleProps = new ArrayList<GdGoodsTypePropertyBo>();
        
    /**添加商品分类基本信息*/
    public void setGoodsTypeJson(GdGoodsTypeBo goodsTypeJson) {
        this.goodsTypeJson = goodsTypeJson;
    }
    
    /**添加商品分类基本属性列表*/
    public void setBasicProps(List<GdGoodsTypePropertyBo> basicProps) {
        this.basicProps = basicProps;
    }
    /**添加商品分类销售属性列表*/
    public void setSaleProps(List<GdGoodsTypePropertyBo> saleProps) {
        this.saleProps = saleProps;
    }
    
    public GdGoodsTypeBo getGoodsTypeJson() {
        return goodsTypeJson;
    }
    
    public List<GdGoodsTypePropertyBo> getBasicProps() {
        return basicProps;
    }
    
    public List<GdGoodsTypePropertyBo> getSaleProps() {
        return saleProps;
    }
    
    @Override
    public String toString() {
        return "GdGoodsTypeOutJson [goodsTypeJson=" + goodsTypeJson + ", basicProps=" + basicProps + ", saleProps="
                + saleProps + "]";
    }
    
}
