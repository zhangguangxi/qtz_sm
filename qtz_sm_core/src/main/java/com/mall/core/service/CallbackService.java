package com.mall.core.service;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.vo.VO;


/**
 * 
 * <p>Title:CallbackService</p>
 * <p>Description:(回调接口)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年8月26日
 */
public interface CallbackService<T extends VO<Long>> extends Serializable{
	
	
	public abstract JSONObject returnData(T vo ,Object...objs);
	
	public  JSONObject returnData(T vo );

}
