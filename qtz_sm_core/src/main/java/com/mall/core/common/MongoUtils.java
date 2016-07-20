package com.mall.core.common;

import java.lang.reflect.Field;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoUtils {

	/**
	 * 将对象转换为 DBObject
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static DBObject convertDBObject(Object obj)throws Exception{
		String json=JSON.toJSONString(obj,
		        SerializerFeature.DisableCircularReferenceDetect);
		return (DBObject)JSON.parse(json);
	}


	/**
	 * 将对象属性转换为DBobject  作为查询
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static DBObject convertFieldDBObject(Class clazz)throws Exception{
		Field[] fields = clazz.getDeclaredFields();
		DBObject ref = new BasicDBObject();
		for (Field field:fields) {
			if(!field.getName().equalsIgnoreCase("serialVersionUID")){
				ref.put(field.getName(), "");
			}
		}
		ref.put("dmId", "");
		return ref;
	}
}
