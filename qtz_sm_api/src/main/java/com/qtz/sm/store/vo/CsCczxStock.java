package com.qtz.sm.store.vo;

import java.util.List;
import java.util.Set;

import com.mall.core.vo.VO;

/**
 * <p>
 * Title:CsCczxStock
 * </p>
 * <p>
 * Description:仓储中心库存VO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-17
 */
public class CsCczxStock extends VO<java.lang.Long>implements java.io.Serializable {

	/** 类的版本号 */
	private static final long serialVersionUID = 1666611934578688L;

	/** 主键ID */
//	private java.lang.Long dmId;
	
	private Set<Long>goodsIds;
	/** 供应商ID */
	private java.lang.Long gysId;
	/** 商品SKU */
	private java.lang.Long skuId;
	/** 数量 */
	private java.lang.Integer stockQuantity;
	/** 待配送数量 */
	private java.lang.Integer awaitQuantity;
	/** 在售状态(0在售,1停售) */
	private java.lang.Integer isOnsale;
	/** 上架时间 */
	private java.lang.Long onsaleTime;
	/**仓储中心ID**/
	private Long cczxId;
	
	private String code;

	// 关联数据，非数据库字段
    /** 商品编号 */
    private String goodsNum;
    /** 商品名称 */
    private String goodsName;
    /** 商品ID */
    private Long goodsId;
    
    /**
     * 云仓储管理公司议价率
     * 仓储中心进货价 = yccglRate * yccglRate
     */
    private Double yccglRate;
    /**
     * 云仓储管理公司议价后价格
     * 仓储中心进货价 = yccglRate * yccglRate
     */
    private Double yccglPrice;
    
    private List<CsCczxStock> csCczxStockInfo;
    
    
    /**
     * 起始数量
     */
    private Integer stockQuantityStart;
    /**
     * 截至数量
     */
    private Integer stockQuantityEnd;
    
	private Integer pageNum;
	
	private Integer pageSize;
    

	public java.lang.Long getDmId() {
		return this.dmId;
	}

	public void setDmId(java.lang.Long dmId) {
		this.dmId = dmId;
	}

	public java.lang.Long getGysId() {
		return this.gysId;
	}

	public void setGysId(java.lang.Long gysId) {
		this.gysId = gysId;
	}

	public java.lang.Long getSkuId() {
		return this.skuId;
	}

	public void setSkuId(java.lang.Long skuId) {
		this.skuId = skuId;
	}

	public java.lang.Integer getStockQuantity() {
		return this.stockQuantity;
	}

	public void setStockQuantity(java.lang.Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public java.lang.Integer getAwaitQuantity() {
		return this.awaitQuantity;
	}

	public void setAwaitQuantity(java.lang.Integer awaitQuantity) {
		this.awaitQuantity = awaitQuantity;
	}

	public java.lang.Integer getIsOnsale() {
		return this.isOnsale;
	}

	public void setIsOnsale(java.lang.Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public java.lang.Long getOnsaleTime() {
		return this.onsaleTime;
	}

	public void setOnsaleTime(java.lang.Long onsaleTime) {
		this.onsaleTime = onsaleTime;
	}

	public String toString() {
		return "[" + "dmId:" + getDmId() + "," + "gysId:" + getGysId() + "," + "skuId:" + getSkuId() + ","
				+ "stockQuantity:" + getStockQuantity() + "," + "awaitQuantity:" + getAwaitQuantity() + ","
				+ "isOnsale:" + getIsOnsale() + "," + "onsaleTime:" + getOnsaleTime() + "]";
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

	public Double getYccglRate() {
		return yccglRate;
	}

	public void setYccglRate(Double yccglRate) {
		this.yccglRate = yccglRate;
	}

	public Double getYccglPrice() {
		return yccglPrice;
	}

	public void setYccglPrice(Double yccglPrice) {
		this.yccglPrice = yccglPrice;
	}

	public Long getCczxId() {
		return cczxId;
	}

	public void setCczxId(Long cczxId) {
		this.cczxId = cczxId;
	}

	public Set<Long> getGoodsIds() {
		return goodsIds;
	}

	public void setGoodsIds(Set<Long> goodsIds) {
		this.goodsIds = goodsIds;
	}

	public List<CsCczxStock> getCsCczxStockInfo() {
		return csCczxStockInfo;
	}

	public void setCsCczxStockInfo(List<CsCczxStock> csCczxStockInfo) {
		this.csCczxStockInfo = csCczxStockInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}