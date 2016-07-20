package com.qtz.sm.shop.vo;


import com.mall.core.vo.VO;

/**
 * <p>Title:SkuProDetail</p>
 * <p>Description:便利店商品sku属性类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-27
 */
public class SkuProDetail extends VO<Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 5434268560662366743L;

	
	/** 规格详情ID */
	private Long proDetailId;
	/** 规格详情名称 */
	private String name;
	/** 组合属性状态：true,包含;false,不包含 */
	private Boolean status = false;
	/** 规格属性 */
	private SkuPro skuPro;
	/** 最底层的规格需要带上skuId */
	private Long skuId;
	/** 最底层的规格需要带上价格 */
	private Double price;
	public Long getProDetailId() {
		return proDetailId;
	}
	public void setProDetailId(Long proDetailId) {
		this.proDetailId = proDetailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
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
	public SkuPro getSkuPro() {
		return skuPro;
	}
	public void setSkuPro(SkuPro skuPro) {
		this.skuPro = skuPro;
	}
}
