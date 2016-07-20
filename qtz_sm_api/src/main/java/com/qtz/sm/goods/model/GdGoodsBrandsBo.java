package com.qtz.sm.goods.model;

import java.io.Serializable;
/**
 * 
* @Title: GdGoodsBrandsUpdateBo.java
* @Package com.qtz.sm.goods.model
* @Description: 品牌交互BO对象
* @author 欧江波 meoujb@163.com
* @date 2016年6月21日 上午10:27:20
* @version V1.0
 */
public class GdGoodsBrandsBo implements Serializable {

	private static final long serialVersionUID = 1L;

	//品牌主键ID
	private Long dmId;
	//品牌名称
	private String cnName;
	
	public GdGoodsBrandsBo(){
		
	}
	public GdGoodsBrandsBo(Long dmId, String cnName) {
		super();
		this.dmId = dmId;
		this.cnName = cnName;
	}

	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	@Override
	public String toString() {
		return "GdGoodsBrandsBo [dmId=" + dmId + ", cnName=" + cnName + "]";
	}
	
}
