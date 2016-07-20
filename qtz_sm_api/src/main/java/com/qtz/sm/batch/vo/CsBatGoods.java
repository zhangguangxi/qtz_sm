package com.qtz.sm.batch.vo;
import java.util.List;

import com.mall.core.vo.VO;
/**
 * <p>Title:CsBatGoods</p>
 * <p>Description:批发单与商品关系VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
public class CsBatGoods extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1660901530355712L;

	
	
	 //关联数据，非数据库字段
	/** 商品名称 */
	private String name;
	/** 供应商ID */
	private Long supplierId;
	/** 批发单Sku信息 */
	private List<CsBatGoodsSku> skuList;
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<CsBatGoodsSku> getSkuList() {
		return skuList;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public void setSkuList(List<CsBatGoodsSku> skuList) {
		this.skuList = skuList;
	}
	public String toString() {
}