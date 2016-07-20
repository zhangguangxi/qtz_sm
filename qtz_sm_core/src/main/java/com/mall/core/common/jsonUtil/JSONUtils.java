package com.mall.core.common.jsonUtil;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * <p>Title:JSONUtils</p>
 * <p>Description:()</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月3日
 */
public class JSONUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JSONUtils.class);

	/**
	 * 对象转JSON字符串
	 * 
	 * @param obj
	 * @return
	 */

	public static String Object2JSON(Object obj) {
		return JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
	}

	/**
	 * json字符串转换为对象，
	 * 
	 * @param <T>
	 * @param json
	 * @param valueType
	 * @return
	 */
	public static <T> T JSON2Object(String json, Class<T> valueType) {
		return JSON.parseObject(json, valueType);
	}

	/**
	 * 将json字符串转换为键值对的对象
	 * @param json
	 * @return
	 */
	public static TreeMap<Object, Object> string2Map(String json) {
		TreeMap<Object, Object> myMap = new TreeMap<Object, Object>();
		JSONObject jo = (JSONObject) JSONObject.parseObject(json);
		if (jo != null) {
			logger.debug("string2Map - {}", jo.toString());
			
			Iterator<Entry<String, Object>> iter = jo.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Object> entry = iter.next();
				myMap.put(entry.getKey(), entry.getValue());
			}
		}
		return myMap;
	}
	/**
	 * json字符串转换json对象
		 * 2014年8月8日 
		 * @return JSONObject
		 * @author tuxing
		 * @version 1.2
	 */
	public static  JSONObject stringToJson(String json){ 
		if(json.indexOf("{")==-1||json.lastIndexOf("}")==-1)return null;
		return (JSONObject) JSONObject.parseObject(json);
	}
	public static void main(String[] args) {
		stringToJson("0");
	}
}
