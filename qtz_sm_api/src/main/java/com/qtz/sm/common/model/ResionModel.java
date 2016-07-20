package com.qtz.sm.common.model;

import java.io.Serializable;
import java.util.List;

public class ResionModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4995032125271047042L;
	// 名称
	private String name;
	// 编码
	private String dmId;

	private List<ResionModel> childs;

	public ResionModel() {
		super();
	}

	public ResionModel(String name, String dmId, List<ResionModel> childs) {
		super();
		this.name = name;
		this.dmId = dmId;
		this.childs = childs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDmId() {
		return dmId;
	}

	public void setDmId(String dmId) {
		this.dmId = dmId;
	}

	public List<ResionModel> getChilds() {
		return childs;
	}

	public void setChilds(List<ResionModel> childs) {
		this.childs = childs;
	}

}
