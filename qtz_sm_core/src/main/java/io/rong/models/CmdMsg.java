package io.rong.models;

import io.rong.util.GsonUtil;
/**
 * 
 * <p>Title:CmdMsg</p>
 * <p>Description:(命令消息)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月24日
 */
public class CmdMsg extends Message{

	
	private String name;
	private String data;
	
	
	public CmdMsg(String name, String data) {
		this.type="RC:CmdNtf";
		this.name = name;
		this.data = data;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return GsonUtil.toJson(this, CmdMsg.class);
	}
}
