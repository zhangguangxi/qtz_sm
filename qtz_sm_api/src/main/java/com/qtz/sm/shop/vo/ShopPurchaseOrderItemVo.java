package com.qtz.sm.shop.vo;import java.util.List;import com.mall.core.vo.VO;/** * @Description:订货单商品sku列表 * @author:SunXuan * @time:2016年5月24日 下午6:37:35 */public class ShopPurchaseOrderItemVo extends VO<Long> implements java.io.Serializable {		/**类的版本号*/	private static final long serialVersionUID = 335313112604276475L;//	private Long dmId;		private Long skuId;//skuId		private Long goodsId;//商品ID 		private String goodsName;//商品名称		private String val;//规格		private Integer purchaseNum;//采购库存		private List<ShopValueVo> ShopValueVoList; //商品对应的sku属性值	public Long getDmId() {		return dmId;	}	public void setDmId(Long dmId) {		this.dmId = dmId;	}	public Long getSkuId() {		return skuId;	}	public void setSkuId(Long skuId) {		this.skuId = skuId;	}	public Long getGoodsId() {		return goodsId;	}	public void setGoodsId(Long goodsId) {		this.goodsId = goodsId;	}	public String getGoodsName() {		return goodsName;	}	public void setGoodsName(String goodsName) {		this.goodsName = goodsName;	}	public String getVal() {		return val;	}	public void setVal(String val) {		this.val = val;	}	public List<ShopValueVo> getShopValueVoList() {		return ShopValueVoList;	}	public void setShopValueVoList(List<ShopValueVo> shopValueVoList) {		ShopValueVoList = shopValueVoList;	}	public Integer getPurchaseNum() {		return purchaseNum;	}	public void setPurchaseNum(Integer purchaseNum) {		this.purchaseNum = purchaseNum;	}}