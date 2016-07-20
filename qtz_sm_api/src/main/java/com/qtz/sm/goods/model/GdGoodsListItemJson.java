package com.qtz.sm.goods.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.VO;

public class GdGoodsListItemJson extends VO<Long> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long goodsId;
	/**商品编码*/
	private String goodsCode;
	/**商品名称*/
	private String goodsName;
	
	/**商品来源*/
	private String source;
	/**商品状态*/
	private Integer goodsStatus;
	/**上架时间*/
	private Long shelveTime;
	
	/**SKU列表*/
	private List<SkuJson> skus = new ArrayList<SkuJson>();
	/**
	 * 添加SKU属性
	 * @param skuJson
	 */
	public void addSkuJson(SkuJson skuJson){
		this.skus.add(skuJson);
	}
	////////////////////////////////////////////
	public GdGoodsListItemJson(){
		
	}
	public GdGoodsListItemJson(Long goodsId, String goodsCode, String goodsName) {
		super();
		this.goodsId = goodsId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
	}
	public GdGoodsListItemJson(Long goodsId, String goodsCode, String goodsName, String source, Integer goodsStatus,
			Long shelveTime) {
		super();
		this.goodsId = goodsId;
		this.goodsCode = goodsCode;
		this.goodsName = goodsName;
		this.source = source;
		this.goodsStatus = goodsStatus;
		this.shelveTime = shelveTime;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public List<SkuJson> getSkus() {
		return skus;
	}
	public void setSkus(List<SkuJson> skus) {
		this.skus = skus;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsCode() {
		return goodsCode;
	}
	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Integer getGoodsStatus() {
		return goodsStatus;
	}
	public void setGoodsStatus(Integer goodsStatus) {
		this.goodsStatus = goodsStatus;
	}
	public Long getShelveTime() {
		return shelveTime;
	}
	public void setShelveTime(Long shelveTime) {
		this.shelveTime = shelveTime;
	}
	@Override
	public String toString() {
		return "GdGoodsListItemJson [goodsId=" + goodsId + ", goodsCode=" + goodsCode + ", goodsName=" + goodsName
				+ ", source=" + source + ", goodsStatus=" + goodsStatus + ", shelveTime=" + shelveTime + ", skus="
				+ skus + "]";
	}

	public static class SkuJson implements Serializable{
		
		private static final long serialVersionUID = 1L;
		/**SKU编号*/
		private Long skuId;
		/**SKU属性值串,格式形如"黑色,XL"*/
		private String valueStr;
		
		/**拿货价*/
		private Double buyPrice;
		/**溢价*/
		private Double priceRate;
		/**卖货价,卖个下级用户价格*/
		private Double sellPrice;
		
		/**可用库存*/
		private Integer avaStock;
		/**冻结库存*/
		private Integer frozenStock;
		
		/**可选列*/
		private String ext1;
		private String ext2;
		private String ext3;
		
		public SkuJson(Long skuId) {
			super();
			this.skuId = skuId;
		}
		public SkuJson(Long skuId, String valueStr) {
			super();
			this.skuId = skuId;
			this.valueStr = valueStr;
		}
		/***
		 * 设置库存信息
		 * @param avaStock		可用库存
		 * @param frozenStock	冻结库存
		 */
		public void setStocks(Integer avaStock, Integer frozenStock) {
			this.avaStock = avaStock;
			this.frozenStock = frozenStock;
		}
		/**
		 * 设置价格信息
		 * @param buyPrice		拿货价
		 * @param priceRate		溢价比率
		 * @param sellPrice		卖货价
		 */
		public void setPrices(Double buyPrice, Double priceRate, Double sellPrice) {
			this.buyPrice = buyPrice;
			this.priceRate = priceRate;
			this.sellPrice = sellPrice;
		}
		/////////////////////////////////////////////////
		
		public Long getSkuId() {
			return skuId;
		}
		public void setSkuId(Long skuId) {
			this.skuId = skuId;
		}
		public String getValueStr() {
			return valueStr;
		}
		public void setValueStr(String valueStr) {
			this.valueStr = valueStr;
		}
		public Double getBuyPrice() {
			return buyPrice;
		}
		public void setBuyPrice(Double buyPrice) {
			this.buyPrice = buyPrice;
		}
		public Double getPriceRate() {
			return priceRate;
		}
		public void setPriceRate(Double priceRate) {
			this.priceRate = priceRate;
		}
		public Double getSellPrice() {
			return sellPrice;
		}
		public void setSellPrice(Double sellPrice) {
			this.sellPrice = sellPrice;
		}
		public Integer getAvaStock() {
			return avaStock;
		}
		public void setAvaStock(Integer avaStock) {
			this.avaStock = avaStock;
		}
		public Integer getFrozenStock() {
			return frozenStock;
		}
		public void setFrozenStock(Integer frozenStock) {
			this.frozenStock = frozenStock;
		}
		public String getExt1() {
			return ext1;
		}
		public void setExt1(String ext1) {
			this.ext1 = ext1;
		}
		public String getExt2() {
			return ext2;
		}
		public void setExt2(String ext2) {
			this.ext2 = ext2;
		}
		public String getExt3() {
			return ext3;
		}
		public void setExt3(String ext3) {
			this.ext3 = ext3;
		}
		@Override
		public String toString() {
			return "SkuJson [skuId=" + skuId + ", valueStr=" + valueStr + ", buyPrice=" + buyPrice + ", priceRate="
					+ priceRate + ", sellPrice=" + sellPrice + ", avaStock=" + avaStock + ", frozenStock=" + frozenStock
					+ ", ext1=" + ext1 + ", ext2=" + ext2 + ", ext3=" + ext3 + "]";
		}
	}

}
