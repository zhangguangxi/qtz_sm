package com.qtz.sm.stc.model;

import java.io.Serializable;
import java.util.List;

import com.qtz.sm.goods.vo.GdGoodsCategroyRate;

public class ResultBo implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -685668266513875431L;
	
	private List<GdGoodsCategroyRate> list;

	public List<GdGoodsCategroyRate> getList() {
		return list;
	}

	public void setList(List<GdGoodsCategroyRate> list) {
		this.list = list;
	}
	 
}
