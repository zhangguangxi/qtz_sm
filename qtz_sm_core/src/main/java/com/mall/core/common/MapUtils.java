package com.mall.core.common;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * map工具包
 * 
 * 项目名称：tianmazu-utils 类名称：MapUtils.java 类描述： 创建人：tuxing
 * 创建时间：2014年12月16日下午2:18:59
 */
public class MapUtils {

	public static JSONObject stringToJson(String json) {
		if (json.indexOf("{") == -1 || json.lastIndexOf("}") == -1)
			return null;
		return (JSONObject) JSONObject.parseObject(json);
	}

	/**
	 * map 转换成对象
	 * @return 
	 */
	public static <T> T convertMap(Class<T> clazz, Map<?, ?> map) {
		String jsonString = JSONObject.toJSONString(map);
		return JSONObject.toJavaObject(stringToJson(jsonString), clazz);
	}

	/**
	 * 对象转换 map 有异常
	 * @throws IntrospectionException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Deprecated  
	public static Map<?,?> convertBean(Object bean) throws Exception {
		Class type = bean.getClass(); 
        Map returnMap = new HashMap(); 
        BeanInfo beanInfo = Introspector.getBeanInfo(type); 
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
        for (int i = 0; i< propertyDescriptors.length; i++) { 
            PropertyDescriptor descriptor = propertyDescriptors[i]; 
            String propertyName = descriptor.getName(); 
            if (!propertyName.equals("class")) { 
                Method readMethod = descriptor.getReadMethod(); 
                Object result = readMethod.invoke(bean, new Object[0]); 
                if (result != null) { 
                    returnMap.put(propertyName, result); 
                } else { 
                    returnMap.put(propertyName, ""); 
                } 
            } 
        } 
        return returnMap; 
	}
}
