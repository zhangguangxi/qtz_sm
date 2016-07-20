package com.qtz.sm.goods.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.qtz.sm.goods.vo.GdGoodsTypeProperty;

/**
 * <p>Title:GdGoodsOutJson</p>
 * <p>Description:商品详情前后端交互JSON对象 </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version 1.0 2016年5月26日
 */
public class GdGoodsOutJson implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**商品信息*/
	private GoodsJson goods;

	/** 基本属性（值拆散）*/
	private List<GdGoodsPropertyBo> basicProps = new ArrayList<GdGoodsPropertyBo>();
	/** 基本属性 (值合并) */
	private List<GdGoodsPropertyBoAdapter> basicPropsAdapter = new ArrayList<GdGoodsPropertyBoAdapter>();
	/** 基本属性（自定义） */
	private List<GdGoodsPropertyCustom> basicPropsCustom = new ArrayList<GdGoodsPropertyCustom>();
	
	/** SKU属性 */
	private List<GoodsSkuJson> skus = new ArrayList<GoodsSkuJson>();
	
	/**商品图片地址*/
	private List<GoodsImgJson> imgs = new ArrayList<GoodsImgJson>();
	
	/**商品基本属性配置*/
	private List<GdGoodsTypeProperty> basicPropConfigs = new ArrayList<GdGoodsTypeProperty>();
	
	/**商品SKU属性配置*/
	private List<GdGoodsTypeProperty> skuPropConfigs = new ArrayList<GdGoodsTypeProperty>();
	
	/**额外信息对象.需要实现Serializable接口*/
	private Object extraInfo;
	
	/**商品描述图片地址*/
	private List<GoodsDescImgJson> descImgs = new ArrayList<GoodsDescImgJson>();
	
	/**
	 * 添加基本属性（自定义）
	 */
	public void addGdGoodsPropertyCustom(GdGoodsPropertyCustom custom){
		this.basicPropsCustom.add(custom);
	}
	
	/**
	 * 添加基本属性适配对象
	 */
	public void addGdGoodsPropertyBoAdapter(GdGoodsPropertyBoAdapter adapter){
		this.basicPropsAdapter.add(adapter);
	}
	/**
	 * 添加商品描述图片
	 * @param imgId
	 * @param url
	 */
	public void addDescImgJson(Long imgId, String url){
		GoodsDescImgJson img = new GoodsDescImgJson(imgId, url);
		this.descImgs.add(img);
	}
	/**
	 * 添加sku对象
	 * @param skuId
	 * @param price
	 * @param stock
	 * @param skuProps
	 */
	public void addSkuJson(Long skuId, Double price, Integer stock, List<GdGoodsPropertyBo> skuProps, String valueIdStr, String valueNameStr){
		GoodsSkuJson sku = new GoodsSkuJson(skuId, price, stock, skuProps);
		sku.setValueIdStr(valueIdStr);
		sku.setValueNameStr(valueNameStr);
		this.skus.add(sku);
	}
	/**
	 * 添加商品图片
	 * @param imgId
	 * @param url
	 */
	public void addImgJson(Long imgId, String url){
		GoodsImgJson img = new GoodsImgJson(imgId, url);
		this.imgs.add(img);
	}
	/**
	 * 添加商品信息
	 * @param imgId
	 * @param url
	 */
	public void addGoodsJson(Long goodsId, String goodsName, String goodsCode, Long brandsId, Long goodsTypeId, String desc,
			Integer goodsStatus, String goodsStausDesc, String brandsName, String goodsTypeName){
		GoodsJson goodsJson = new GoodsJson(goodsId, goodsName, goodsCode, brandsId, goodsTypeId, desc, goodsStatus, goodsStausDesc);
		goodsJson.setBrandsName(brandsName);
		goodsJson.setGoodsTypeName(goodsTypeName);
		
		this.goods = goodsJson;
	}
	/***
	 * 添加基本属性列表
	 * @param basicProps
	 */
	public void addBasicProps(List<GdGoodsPropertyBo> basicProps) {
		this.basicProps = basicProps;
	}
	/**
	 * 添加额外信息
	 * @param extraInfo
	 */
	public void addExtraInfo(Object extraInfo) {
		this.extraInfo = extraInfo;
	}
	/**
	 * 添加商品基本属性配置
	 * @param extraInfo
	 */
	public void addBasicPropConfig(List<GdGoodsTypeProperty> propConfigs) {
		this.basicPropConfigs = propConfigs;
	}
	/**
	 * 添加商品SKU属性配置
	 * @param extraInfo
	 */
	public void addSkuPropConfig(List<GdGoodsTypeProperty> propConfigs) {
		this.skuPropConfigs = propConfigs;
	}
	/////////////////////////////////////////////////////
	public GoodsJson getGoods() {
		return goods;
	}
	public List<GdGoodsPropertyBo> getBasicProps() {
		return basicProps;
	}
	public List<GoodsSkuJson> getSkus() {
		return skus;
	}
	public List<GoodsImgJson> getImgs() {
		return imgs;
	}
	public Object getExtraInfo() {
		return extraInfo;
	}
	public List<GdGoodsTypeProperty> getBasicPropConfigs() {
		return basicPropConfigs;
	}
	public List<GdGoodsTypeProperty> getSkuPropConfigs() {
		return skuPropConfigs;
	}
	public void setGoods(GoodsJson goods) {
		this.goods = goods;
	}
	public void setBasicProps(List<GdGoodsPropertyBo> basicProps) {
		this.basicProps = basicProps;
	}
	public void setSkus(List<GoodsSkuJson> skus) {
		this.skus = skus;
	}
	public void setImgs(List<GoodsImgJson> imgs) {
		this.imgs = imgs;
	}
	public void setExtraInfo(Object extraInfo) {
		this.extraInfo = extraInfo;
	}
	public List<GoodsDescImgJson> getDescImgs() {
		return descImgs;
	}
	public List<GdGoodsPropertyBoAdapter> getBasicPropsAdapter() {
		return basicPropsAdapter;
	}
	public void setBasicPropsAdapter(List<GdGoodsPropertyBoAdapter> basicPropsAdapter) {
		this.basicPropsAdapter = basicPropsAdapter;
	}
	public List<GdGoodsPropertyCustom> getBasicPropsCustom() {
		return basicPropsCustom;
	}
	public void setBasicPropsCustom(List<GdGoodsPropertyCustom> basicPropsCustom) {
		this.basicPropsCustom = basicPropsCustom;
	}
	@Override
	public String toString() {
		return "GdGoodsOutJson [goods=" + goods + ", basicProps=" + basicProps + ", basicPropsAdapter="
				+ basicPropsAdapter + ", skus=" + skus + ", imgs=" + imgs + ", basicPropConfigs=" + basicPropConfigs
				+ ", skuPropConfigs=" + skuPropConfigs + ", extraInfo=" + extraInfo + ", descImgs=" + descImgs + "]";
	}
	
	public static class GdGoodsPropertyCustom implements Serializable {

		private static final long serialVersionUID = 1L;
		
		/**
		 * 属性值主键ID
		 */
		private Long dmId;
		
		/**
		 * 自定义KEY
		 */
		private String otherKey;
		
		/**
		 * 其他值
		 */
		private String otherValue;
		
		public GdGoodsPropertyCustom(){
			
		}

		public GdGoodsPropertyCustom(Long dmId, String otherKey, String otherValue) {
			super();
			this.dmId = dmId;
			this.otherKey = otherKey;
			this.otherValue = otherValue;
		}

		public Long getDmId() {
			return dmId;
		}

		public void setDmId(Long dmId) {
			this.dmId = dmId;
		}

		public String getOtherKey() {
			return otherKey;
		}

		public void setOtherKey(String otherKey) {
			this.otherKey = otherKey;
		}

		public String getOtherValue() {
			return otherValue;
		}

		public void setOtherValue(String otherValue) {
			this.otherValue = otherValue;
		}

		@Override
		public String toString() {
			return "GdGoodsPropertyCustom [dmId=" + dmId + ", otherKey=" + otherKey + ", otherValue=" + otherValue
					+ "]";
		}
	}
	
	
	public static class GoodsJson implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private Long goodsId;
		/**商品名称*/
		private String goodsName;
		/**商品编码*/
		private String goodsCode;
		/**品牌ID*/
		private Long brandsId;
		private String brandsName;
		/**商品分类ID*/
		private Long goodsTypeId;
		private String goodsTypeName;
		private String desc;
		/**商品状态*/
		private Integer goodsStatus;
		/**商品状态描述*/
		private String goodsStausDesc;
		
		public GoodsJson(){
			
		}
		public GoodsJson(Long goodsId, String goodsName, String goodsCode, Long brandsId, String brandsName,
				Long goodsTypeId, String goodsTypeName, String desc, Integer goodsStatus, String goodsStausDesc) {
			super();
			this.goodsId = goodsId;
			this.goodsName = goodsName;
			this.goodsCode = goodsCode;
			this.brandsId = brandsId;
			this.brandsName = brandsName;
			this.goodsTypeId = goodsTypeId;
			this.goodsTypeName = goodsTypeName;
			this.desc = desc;
			this.goodsStatus = goodsStatus;
			this.goodsStausDesc = goodsStausDesc;
		}
		public GoodsJson(Long goodsId, String goodsName, String goodsCode, Long brandsId, Long goodsTypeId, String desc,
				Integer goodsStatus, String goodsStausDesc) {
			super();
			this.goodsId = goodsId;
			this.goodsName = goodsName;
			this.goodsCode = goodsCode;
			this.brandsId = brandsId;
			this.goodsTypeId = goodsTypeId;
			this.desc = desc;
			this.goodsStatus = goodsStatus;
			this.goodsStausDesc = goodsStausDesc;
		}
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
		public String getGoodsCode() {
			return goodsCode;
		}
		public void setGoodsCode(String goodsCode) {
			this.goodsCode = goodsCode;
		}
		public Long getBrandsId() {
			return brandsId;
		}
		public void setBrandsId(Long brandsId) {
			this.brandsId = brandsId;
		}
		public String getBrandsName() {
			return brandsName;
		}
		public void setBrandsName(String brandsName) {
			this.brandsName = brandsName;
		}
		public Long getGoodsTypeId() {
			return goodsTypeId;
		}
		public void setGoodsTypeId(Long goodsTypeId) {
			this.goodsTypeId = goodsTypeId;
		}
		public String getGoodsTypeName() {
			return goodsTypeName;
		}
		public void setGoodsTypeName(String goodsTypeName) {
			this.goodsTypeName = goodsTypeName;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Integer getGoodsStatus() {
			return goodsStatus;
		}
		public void setGoodsStatus(Integer goodsStatus) {
			this.goodsStatus = goodsStatus;
		}
		public String getGoodsStausDesc() {
			return goodsStausDesc;
		}
		public void setGoodsStausDesc(String goodsStausDesc) {
			this.goodsStausDesc = goodsStausDesc;
		}
		@Override
		public String toString() {
			return "GoodsJson [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsCode=" + goodsCode
					+ ", brandsId=" + brandsId + ", brandsName=" + brandsName + ", goodsTypeId=" + goodsTypeId
					+ ", goodsTypeName=" + goodsTypeName + ", desc=" + desc + ", goodsStatus=" + goodsStatus
					+ ", goodsStausDesc=" + goodsStausDesc + "]";
		}
	}
	
	public static class GoodsSkuJson implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private Long skuId;
		/** 价格 */
		private Double price;
		/** 库存*/
		private Integer stock;
		/** SKU属性 */
		private List<GdGoodsPropertyBo> skuProps;
		/** SKU属性值名称串 */
		private String valueNameStr;
		/**SKU属性值ID串*/
		private String valueIdStr;
		
		public GoodsSkuJson(){
			
		}
		public GoodsSkuJson(Long skuId, Double price, Integer stock, List<GdGoodsPropertyBo> skuProps) {
			super();
			this.skuId = skuId;
			this.price = price;
			this.stock = stock;
			this.skuProps = skuProps;
		}
		public Long getSkuId() {
			return skuId;
		}
		public void setSkuId(Long skuId) {
			this.skuId = skuId;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public Integer getStock() {
			return stock;
		}
		public void setStock(Integer stock) {
			this.stock = stock;
		}
		public List<GdGoodsPropertyBo> getSkuProps() {
			return skuProps;
		}
		public void setSkuProps(List<GdGoodsPropertyBo> skuProps) {
			this.skuProps = skuProps;
		}
		public String getValueNameStr() {
			return valueNameStr;
		}
		public void setValueNameStr(String valueNameStr) {
			this.valueNameStr = valueNameStr;
		}
		public String getValueIdStr() {
			return valueIdStr;
		}
		public void setValueIdStr(String valueIdStr) {
			this.valueIdStr = valueIdStr;
		}
		@Override
		public String toString() {
			return "GoodsSkuJson [skuId=" + skuId + ", price=" + price + ", stock=" + stock + ", skuProps=" + skuProps
					+ ", valueNameStr=" + valueNameStr + ", valueIdStr=" + valueIdStr + "]";
		}
	}
	
	public static class GoodsImgJson implements Serializable {
		
		private static final long serialVersionUID = 1L;
		/**商品图片ID*/
		private Long imgId;
		/**商品图片地址*/
		private String url;
		
		public GoodsImgJson() {
			super();
		}
		public GoodsImgJson(Long imgId, String url) {
			super();
			this.imgId = imgId;
			this.url = url;
		}
		public Long getImgId() {
			return imgId;
		}
		public void setImgId(Long imgId) {
			this.imgId = imgId;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		@Override
		public String toString() {
			return "GoodsImgJson [imgId=" + imgId + ", url=" + url + "]";
		}
	}
	
	public static class GoodsDescImgJson implements Serializable {
		private static final long serialVersionUID = 1L;
		/**商品图片ID*/
		private Long imgId;
		/**商品图片地址*/
		private String url;
		
		public GoodsDescImgJson() {
			super();
		}
		public GoodsDescImgJson(Long imgId, String url) {
			super();
			this.imgId = imgId;
			this.url = url;
		}
		public Long getImgId() {
			return imgId;
		}
		public void setImgId(Long imgId) {
			this.imgId = imgId;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		@Override
		public String toString() {
			return "GoodsDescImgJson [imgId=" + imgId + ", url=" + url + "]";
		}
	}
}
