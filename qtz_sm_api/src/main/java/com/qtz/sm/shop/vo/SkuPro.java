package com.qtz.sm.shop.vo;

import java.util.List;

import com.mall.core.vo.VO;

/**
 * <p>Title:SkuPro</p>
 * <p>Description:便利店商品sku属性规格名称类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 杨威
 * @version v1.0 2016-06-03
 */
public class SkuPro extends VO<Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = -5428030958940191869L;
	
	/** 规格ID */
	private Long proId;
	/** 规格名称 */
	private String name;
	/** 规格级别 */
	private Integer level;
	/** 规格详情列表 */
	private List<SkuProDetail> proDetails;
	public Long getProId() {
		return proId;
	}
	public void setProId(Long proId) {
		this.proId = proId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SkuProDetail> getProDetails() {
		return proDetails;
	}
	public void setProDetails(List<SkuProDetail> proDetails) {
		this.proDetails = proDetails;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
}
