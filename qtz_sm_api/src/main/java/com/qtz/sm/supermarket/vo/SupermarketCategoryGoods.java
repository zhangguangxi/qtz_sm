/**
 * <p>Title:SupermarketCategoryAssociate</p>
 * <p>Description:超市类目商品信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 张光喜- ZhangGunagxi
 * @version v1.0 2016-06-06
 */
package com.qtz.sm.supermarket.vo;

import java.io.Serializable;

import com.mall.core.vo.VO;

public class SupermarketCategoryGoods extends VO<Long>implements Serializable {
    private static final long serialVersionUID = -2908976598251568243L;
    /** 商品名称 */
    private String goodsName;
    /** 商品图片(取第一张图作为展示图) */
    private String pictureUrl;
    /** 商品价格(获取sku最低价格作为展示价格) */
    private Double price;
    /** 仓储中心id */
    private Long cczxId;
    
    public Long getDmId() {
        return this.dmId;
    }
    
    public void setDmId(Long dmId) {
        this.dmId = dmId;
    }
    
    public String getGoodsName() {
        return goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getPictureUrl() {
        return pictureUrl;
    }
    
    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Long getCczxId() {
        return cczxId;
    }
    
    public void setCczxId(Long cczxId) {
        this.cczxId = cczxId;
    }
    
    public String toString() {
        return "SupermarketCategoryGoods [goodsName=" + goodsName + ", pictureUrl=" + pictureUrl + ", price=" + price
                + ", cczxId=" + cczxId + "]";
    }
    
}
