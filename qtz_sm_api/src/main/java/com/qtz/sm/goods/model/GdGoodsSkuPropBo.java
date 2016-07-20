package com.qtz.sm.goods.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title:商品SKU</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 欧江波 - meoujb@163.com
 * @version 1.0 2016年5月24日
 */
public class GdGoodsSkuPropBo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * SKU编号
	 */
	private Long skuId;
	
	/***
	 * 商品SKU属性信息
	 */
	private List<GdGoodsPropertyBo> props = new ArrayList<GdGoodsPropertyBo>();
	
	/**SKU属性值串*/
	private String skuValueStr;
	
	/**SKU属性值ID串*/
	private String skuValueIdStr;
	
	public void addSkuProp(GdGoodsPropertyBo prop){
		this.props.add(prop);
	}
	public void setValueStrs(String[] strs){
		if (strs != null && strs.length == 2) {
			this.skuValueIdStr = strs[0];
			this.skuValueStr = strs[1];
		}
	}
	
	///////////////////////////////////////////
	public GdGoodsSkuPropBo() {
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public List<GdGoodsPropertyBo> getProps() {
		return props;
	}

	public void setProps(List<GdGoodsPropertyBo> props) {
		this.props = props;
	}

	public String getSkuValueStr() {
		return skuValueStr;
	}

	public void setSkuValueStr(String skuValueStr) {
		this.skuValueStr = skuValueStr;
	}

	public String getSkuValueIdStr() {
		return skuValueIdStr;
	}

	public void setSkuValueIdStr(String skuValueIdStr) {
		this.skuValueIdStr = skuValueIdStr;
	}

	@Override
	public String toString() {
		return "GdGoodsSkuPropBo [skuId=" + skuId + ", props=" + props + ", skuValueStr=" + skuValueStr
				+ ", skuValueIdStr=" + skuValueIdStr + "]";
	}
	
}
