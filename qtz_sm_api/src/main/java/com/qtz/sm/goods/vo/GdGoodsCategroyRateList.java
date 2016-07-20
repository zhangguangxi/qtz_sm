package com.qtz.sm.goods.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GdGoodsCategroyRateList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GdGoodsCategroyRate> categropRates = new ArrayList<GdGoodsCategroyRate>();

	public List<GdGoodsCategroyRate> getCategropRates() {
		return categropRates;
	}

	public void setCategropRates(List<GdGoodsCategroyRate> categropRates) {
		this.categropRates = categropRates;
	}

}
