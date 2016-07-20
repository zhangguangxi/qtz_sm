package com.qtz.sm.stc.vo;

import java.util.List;

import com.mall.core.vo.VO;

/**
 * 云仓储管理公司   商品管理   商品库   映射
 * @author Administrator
 *
 */
public class CczxGoods extends VO<java.lang.Long>implements java.io.Serializable{

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
	
	private String code;
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
	/**
	 * 仓储中心进货价
	 */
	private Double yccglPrice;
	/**
	 * 议价率
	 */
	private Double bldglRate;
	/**
	 * 便利店进货价格
	 */
	private Double bldglPrice;
	
	/**
	 * 上架时间
	 */
	private Long onsaleTime;
	
	/**
	 * gd_goods_sku_rate表的主键
	 */
	private Long goodsSkuRateDmId;
	
	/**
	 * 商品状态(0在售,1停售)
	 */
	private Integer isOnsale;
	
	private List<CczxGoods> cczxGoodsInfo;
	
	

	
	/**
	 * 起始仓储中心进货价
	 */			   
	private Double cczxInPriceStart;
	
	/**
	 * 截至仓储中心进货价
	 */
	private Double cczxInPriceEnd;
	
	/**
	 * 起始便利店进货价
	 */
	private Double bldInPriceStart;
	
	
	/**
	 * 起始便利店进货价
	 */
	private Double bldInPriceEnd;
	
	

	
	
	/**
	 * 起始上架时间
	 */
	private String onsaleTimeStart;
	
	/**
	 * 结束上架时间
	 */
	private String onsaleTimeEnd;
	
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

	public Double getYccglPrice() {
		return yccglPrice;
	}

	public void setYccglPrice(Double yccglPrice) {
		this.yccglPrice = yccglPrice;
	}

	public Double getBldglRate() {
		return bldglRate;
	}

	public void setBldglRate(Double bldglRate) {
		this.bldglRate = bldglRate;
	}

	public Double getBldglPrice() {
		return bldglPrice;
	}

	public void setBldglPrice(Double bldglPrice) {
		this.bldglPrice = bldglPrice;
	}

	public Long getOnsaleTime() {
		return onsaleTime;
	}

	public void setOnsaleTime(Long onsaleTime) {
		this.onsaleTime = onsaleTime;
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

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public Long getGoodsSkuRateDmId() {
		return goodsSkuRateDmId;
	}

	public void setGoodsSkuRateDmId(Long goodsSkuRateDmId) {
		this.goodsSkuRateDmId = goodsSkuRateDmId;
	}


	public Double getBldInPriceStart() {
		return bldInPriceStart;
	}

	public void setBldInPriceStart(Double bldInPriceStart) {
		this.bldInPriceStart = bldInPriceStart;
	}

	public Double getBldInPriceEnd() {
		return bldInPriceEnd;
	}

	public void setBldInPriceEnd(Double bldInPriceEnd) {
		this.bldInPriceEnd = bldInPriceEnd;
	}

	public Double getCczxInPriceStart() {
		return cczxInPriceStart;
	}

	public void setCczxInPriceStart(Double cczxInPriceStart) {
		this.cczxInPriceStart = cczxInPriceStart;
	}

	public Double getCczxInPriceEnd() {
		return cczxInPriceEnd;
	}

	public void setCczxInPriceEnd(Double cczxInPriceEnd) {
		this.cczxInPriceEnd = cczxInPriceEnd;
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

	public List<CczxGoods> getCczxGoodsInfo() {
		return cczxGoodsInfo;
	}

	public void setCczxGoodsInfo(List<CczxGoods> cczxGoodsInfo) {
		this.cczxGoodsInfo = cczxGoodsInfo;
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
