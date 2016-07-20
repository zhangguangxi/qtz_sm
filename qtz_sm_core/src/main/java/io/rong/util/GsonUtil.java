package io.rong.util;

import com.alibaba.fastjson.JSON;


public class GsonUtil {

//	private static Gson gson = new Gson();
//
//	public static String toJson(Object obj, Type type) {		
//		return gson.toJson(obj, type);		
//	}		
//	
//	public static Object fromJson(String str,Type type){
//		return gson.fromJson(str, type);
//	}
	
	@SuppressWarnings("rawtypes")
	public static String toJson(Object obj, Class cls) {		
		return JSON.toJSONString(obj);
	}	
	
}  
