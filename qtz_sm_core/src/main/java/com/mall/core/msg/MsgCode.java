package com.mall.core.msg;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * <p>Title:MsgCode</p>
 * <p>Description:(消息id)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月9日
 */
public class MsgCode {
	/**
	 * 认证审核成功
	 */
	public static final JSONObject authren_success = new JSONObject(respMsg(1, "认证通过")); 
	/**
	 * 认证审核失败
	 */
	public static final JSONObject authren_failed = new JSONObject(respMsg(2, "认证没有通过")); 
	
	
	public static Map<String, Object> respMsg(int code, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msgID", code);
		map.put("msg", msg);
		return map;
	}
}
