package com.qtz.sm.shop.vo;
import java.util.List;

import com.mall.core.vo.VO;
import com.qtz.sm.shop.vo.ShopValueVo;
/**
 * <p>Title:GdGoods</p>
 * <p>Description:商品VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选 
 * @version v1.0 2016-06-06
 */
public class ShopGdGoodsVo extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1678897741793280L;

	
	/** 商品sku列表值 */
	private List<ShopValueVo> valList;
	/** skuId */
	private Long skuId;
	
	public List<ShopValueVo> getValList() {
		return valList;
	}
	public void setValList(List<ShopValueVo> valList) {
		this.valList = valList;
	}
	public java.lang.Long getDmId() {
	@Override
	public String toString() {
		return "GdGoods [dmId=" + dmId + ", supplierId=" + supplierId + ", brandsId=" + brandsId + ", goodsTypeId="
				+ goodsTypeId + ", code=" + code + ", name=" + name + ", remark=" + remark + ", status=" + status
				+ ", createBy=" + createBy + ", createOn=" + createOn + ", updateBy=" + updateBy + ", updateOn="
				+ updateOn + ", goodsProValMsg=" + goodsProValMsg + ", valList=" + valList + "]";
	}
	public Long getSkuId() {
		return skuId;
	}
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
}