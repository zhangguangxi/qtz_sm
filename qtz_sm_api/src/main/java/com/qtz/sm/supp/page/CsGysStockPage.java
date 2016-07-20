package com.qtz.sm.supp.page;

import java.io.Serializable;
import java.util.Set;

import com.mall.core.vo.Pager;
import com.qtz.sm.supp.vo.CsGysStock;

/**
 * Title:CsGysStockPage<br/>
 * Description:(供应商商品库存Page分页类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public class CsGysStockPage extends Pager<CsGysStock,Long> implements Serializable {

    /**(序列化UID)*/
    private static final long serialVersionUID = 1841762345478735798L;
    /**(主键ID)*/
    private Long dmId;
    
    private Set<Long> goodsIds;
    
    /**(供应商ID)*/
    private Long gysId;
    /**(商品SKU)*/
    private Long skuId;
    /**(数量)*/
    private Integer stockQuantity;
    /**(待配送数量)*/
    private Integer awaitQuantity;
    /**(在售状态(0在售,1停售))*/
    private Byte isOnsale;
    /**
     * 上架时间
     */
    private Long onsaleTime;
    /**(审核状态(0已同意,1已拒绝,2未审核))*/
    private Byte isCheck;

    /**(价格)*/
    private Double price;
    
    /** 初始价格 */
	private Double priceInit;
    
  //关联数据，非数据库字段
    /** 商品编号 */
    private String goodsNum;
    /** 商品名称 */
    private String goodsName;
    /** 商品ID */
    private Long goodsId;
    
    //查询条件 start
    /**
     * 起始价格
     */
    private Double priceStart;
    /**
     * 截至价格
     */
    private Double priceEnd;
    /**
     * 起始数量
     */
    private Integer stockQuantityStart;
    /**
     * 截至数量
     */
    private Integer stockQuantityEnd;
    /**
	 * 起始上架时间
	 */
    private String onsaleTimeStart;
    /**
	 * 结束上架时间
	 */
    private String  onsaleTimeEnd;
    //查询条件 end
    
    
    public Long getDmId(){
        return this.dmId;
    }
    public void setDmId(Long dmId){
        this.dmId = dmId;
    }
    public Long getGysId(){
        return this.gysId;
    }
    public void setGysId(Long gysId){
        this.gysId = gysId;
    }
    public Long getSkuId(){
        return this.skuId;
    }
    public void setSkuId(Long skuId){
        this.skuId = skuId;
    }
    public Integer getStockQuantity(){
        return this.stockQuantity;
    }
    public void setStockQuantity(Integer stockQuantity){
        this.stockQuantity = stockQuantity;
    }
    public Integer getAwaitQuantity(){
        return this.awaitQuantity;
    }
    public void setAwaitQuantity(Integer awaitQuantity){
        this.awaitQuantity = awaitQuantity;
    }
    public Byte getIsOnsale(){
        return this.isOnsale;
    }
    public void setIsOnsale(Byte isOnsale){
        this.isOnsale = isOnsale;
    }
    public Byte getIsCheck(){
        return this.isCheck;
    }
    public void setIsCheck(Byte isCheck){
        this.isCheck = isCheck;
    }

    @Override
    public String toString() {
        return "CsGysStockPage[" +
        "dmId=" + dmId +
        ",gysId=" + gysId +
        ",skuId=" + skuId +
        ",stockQuantity=" + stockQuantity +
        ",awaitQuantity=" + awaitQuantity +
        ",isOnsale=" + isOnsale +
        ",isCheck=" + isCheck +
        ']';
    }
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getOnsaleTime() {
		return onsaleTime;
	}
	public void setOnsaleTime(Long onsaleTime) {
		this.onsaleTime = onsaleTime;
	}
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Double getPriceStart() {
		return priceStart;
	}
	public void setPriceStart(Double priceStart) {
		this.priceStart = priceStart;
	}
	public Double getPriceEnd() {
		return priceEnd;
	}
	public void setPriceEnd(Double priceEnd) {
		this.priceEnd = priceEnd;
	}
	public Integer getStockQuantityStart() {
		return stockQuantityStart;
	}
	public void setStockQuantityStart(Integer stockQuantityStart) {
		this.stockQuantityStart = stockQuantityStart;
	}
	public Integer getStockQuantityEnd() {
		return stockQuantityEnd;
	}
	public void setStockQuantityEnd(Integer stockQuantityEnd) {
		this.stockQuantityEnd = stockQuantityEnd;
	}

	public Double getPriceInit() {
		return priceInit;
	}
	public void setPriceInit(Double priceInit) {
		this.priceInit = priceInit;
	}
	public Set<Long> getGoodsIds() {
		return goodsIds;
	}
	public void setGoodsIds(Set<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}
	public String getOnsaleTimeStart() {
		return onsaleTimeStart;
	}
	public void setOnsaleTimeStart(String onsaleTimeStart) {
		this.onsaleTimeStart = onsaleTimeStart;
	}
	public String getOnsaleTimeEnd() {
		return onsaleTimeEnd;
	}
	public void setOnsaleTimeEnd(String onsaleTimeEnd) {
		this.onsaleTimeEnd = onsaleTimeEnd;
	}

}
