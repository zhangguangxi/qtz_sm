package com.mall.core.common.model;

import java.io.Serializable;


/**
 * <p>Title:Propertes</p>
 * <p>Description:(proprtes 配置文件模型)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-28
 */

public class Propertes implements Serializable{

	private static final long serialVersionUID = -5265492093723490274L;
	private String key;
	private String values;
	
	public Propertes(String key, String values) {
		super();
		this.key = key;
		this.values = values;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValues() {
		return values;
	}
	public void setValues(String values) {
		this.values = values;
	}
	
	
}
