package com.mall.core.vo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.mall.core.common.UUIDFactory;

/**
 * 
 * <p>Title:DdmSession</p>
 * <p>Description:(Session 数据)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015年3月10日
 */
public class DdmSession implements java.io.Serializable{
	private static final long serialVersionUID = -8967010027221622900L;
	private String id;//sesion id
	private long creatTime;//session 创建时间
	private long lastOperaTime;//session  最后操作时间
	private long failureTime;//失效时间
	
	private Map<String,Object> map;//session  存储的数据
	
	public DdmSession() {
		super();
		id = UUIDFactory.newUUID();
		creatTime = new Date().getTime();
		map = new HashMap<String, Object>();
	}
	/**
	 * 
	* 【查询】(得到session 数据)
	* @param key
	* @return
	 */
	public Object getSessionObject(String key){
		
		return map.get(key);
		
	}
	/**
	 * 
	* 【删除】(删除 session 数据)
	* @param key
	 */
	public void delSessionObject(String key){
		map.remove(key);
	}
	/**
	 * 
	* 【保存】(session 保存数据)
	* @param key
	* @param obj
	 */
	public void save(String key,Object obj){
		
		map.put(key, obj);
	}
	public String getId() {
		return id;
	}
	public long getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(long creatTime) {
		this.creatTime = creatTime;
	}
	public long getLastOperaTime() {
		return lastOperaTime;
	}
	public void setLastOperaTime(long lastOperaTime) {
		this.lastOperaTime = lastOperaTime;
	}
	public long getFailureTime() {
		return failureTime;
	}
	public void setFailureTime(long failureTime) {
		this.failureTime = failureTime;
	}
	
	
}
