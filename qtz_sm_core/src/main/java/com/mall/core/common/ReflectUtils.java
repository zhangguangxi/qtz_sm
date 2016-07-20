package com.mall.core.common;
import java.lang.reflect.ParameterizedType;  
import java.lang.reflect.Type;  


/**
 * 反射工具类
 * <p>Title:ReflectUtils</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2014-12-12
 */
@SuppressWarnings("unchecked")  
public class ReflectUtils {  
      
    /** 
     * 获得超类的参数类型，取第一个参数类型 
     * @param <T> 类型参数 
     * @param clazz 超类类型 
     */  
    @SuppressWarnings("rawtypes")  
    public static <T> Class<T> getClassGenricType(final Class clazz) {  
        return getClassGenricType(clazz, 0);  
    }
      
    /** 
     * 根据索引获得超类的参数类型 
     * @param clazz 超类类型 
     * @param index 索引 
     */  
    @SuppressWarnings("rawtypes")  
    public static Class getClassGenricType(final Class clazz, final int index) {  
        Type genType = clazz.getGenericSuperclass();  
        if (!(genType instanceof ParameterizedType)) {  
            return Object.class;  
        }  
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();  
        if (index >= params.length || index < 0) {  
            return Object.class;  
        }  
        if (!(params[index] instanceof Class)) {  
            return Object.class;  
        }  
        return (Class) params[index];  
    }  
}  