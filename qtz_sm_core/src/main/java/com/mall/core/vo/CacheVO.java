package com.mall.core.vo;

/**
 * <p>Title:CodeListVO</p>
 * <p>Description:缓存VO对象</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-10-4
 */

public class CacheVO extends VO<String> {
	
	/**(用一句话描述这个变量表示什么)*/
	private static final long serialVersionUID = -6731782122466853744L;

	private String pid = null;
	
	private String name = null;
	
	private String code = null;

	
	/**
	 * 【取得】id 
	 * @return	id
	 */
	
	public String getId() {
		return dmId;
	}

	
	/**
	 * 【设置】id 
	 * @param 	id
	 */
	
	public void setId(String dmId) {
		this.dmId = dmId;
	}

	
	/**
	 * 【取得】pid 
	 * @return	pid
	 */
	
	public String getPid() {
		return pid;
	}

	
	/**
	 * 【设置】pid 
	 * @param 	pid
	 */
	
	public void setPid(String pid) {
		this.pid = pid;
	}

	
	/**
	 * 【取得】name 
	 * @return	name
	 */
	
	public String getName() {
		return name;
	}

	
	/**
	 * 【设置】name 
	 * @param 	name
	 */
	
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * 【取得】code 
	 * @return	code
	 */
	
	public String getCode() {
		return code;
	}

	
	/**
	 * 【设置】code 
	 * @param 	code
	 */
	
	public void setCode(String code) {
		this.code = code;
	}
}
