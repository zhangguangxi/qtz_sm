package com.qtz.sm.stc.vo;

import java.util.List;

import com.mall.core.vo.VO;

/**
 * 云仓储管理公司   商品管理   商品库   映射
 * @author Administrator
 *
 */
public class CsCczxGoodsQuantity extends VO<java.lang.Long>implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8371833732584116963L;
	/**
	 * 商品编号
	 */
	private Long goodsId;
	/**
	 * 商品名称
	 */
	private String  goodsName;
	
	private String  code;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * sku值
	 */
	private String str;

    /**(数量)*/
    private Integer stockQuantity;
    /**(待配送数量)*/
    private Integer awaitQuantity;
    
    /**(数量)*/
    private Integer stockQuantityStart;
    /**(待配送数量)*/
    private Integer stockQuantityEnd;
    
    private List<CczxGoods> skuAndStockInfo;
    
	private Integer pageNum;
	
	private Integer pageSize;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Integer getAwaitQuantity() {
		return awaitQuantity;
	}

	public void setAwaitQuantity(Integer awaitQuantity) {
		this.awaitQuantity = awaitQuantity;
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

	public List<CczxGoods> getSkuAndStockInfo() {
		return skuAndStockInfo;
	}

	public void setSkuAndStockInfo(List<CczxGoods> skuAndStockInfo) {
		this.skuAndStockInfo = skuAndStockInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


}
