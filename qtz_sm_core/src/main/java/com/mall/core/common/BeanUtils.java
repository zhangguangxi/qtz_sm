package com.mall.core.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.alibaba.fastjson.JSON;
import com.mall.core.log.LogTool;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 当把Person类作为BeanUtilTest的内部类时，程序出错<br>
 * java.lang.NoSuchMethodException: Property '**' has no setter method<br>
 * 本质：内部类 和 单独文件中的类的区别 <br>
 * BeanUtils.populate方法的限制：<br>
 * The class must be public, and provide a public constructor that accepts no
 * arguments. <br>
 * This allows tools and applications to dynamically create new instances of
 * your bean, <br>
 * without necessarily knowing what Java class name will be used ahead of time
 */
public class BeanUtils {
	private static LogTool log = LogTool.getInstance(BeanUtils.class);
	public static void bean2Update(Object bean,Update update) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Field[] fields = bean.getClass().getDeclaredFields();  
		 for (Field field : fields) {
			  // 获取属性名  
	      String varName = field.getName();  
	      // 修改访问控制权限  
	      boolean accessFlag = field.isAccessible();  
	      if (!accessFlag) {  
	        field.setAccessible(true);  
	      }  
		  Object param = field.get(bean); 
		  if (param == null) {  
	        continue;  
	      } else if(varName.equals("serialVersionUID"))
	      {
	    	  continue;
	      }
	      else if (param instanceof Integer) {//判断变量的类型  
	        int value = ((Integer) param).intValue();  
	        update.set(varName, value);
	      } else if (param instanceof String) {  
	        String value = (String) param;  
	        update.set(varName, value);
	      } else if (param instanceof Double) {  
	        double value = ((Double) param).doubleValue();  
	        update.set(varName, value);
	      } else if (param instanceof Float) {  
	        float value = ((Float) param).floatValue();  
	        update.set(varName, value);
	      } else if (param instanceof Long) {  
	        long value = ((Long) param).longValue();  
	        update.set(varName, value);
	      } else if (param instanceof Boolean) {  
	        boolean value = ((Boolean) param).booleanValue();  
	        update.set(varName, value);
	      } else if (param instanceof Date) {  
	        Date value = (Date) param;  
	        update.set(varName, value);
	      }  
	      else
	      {
	    	  update.set(varName, JSON.parseObject(JSON.toJSONString(param), param.getClass()));
	      }
	      // 恢复访问控制权限  
	      field.setAccessible(accessFlag);  
		 }
	}
	
	public static Query bean2WhereLike(Object bean) throws IllegalArgumentException, IllegalAccessException{
		Query query=new Query();
		Field[] fields = bean.getClass().getDeclaredFields();  
		 for (Field field : fields) {
			  // 获取属性名  
	      String varName = field.getName();  
	      // 修改访问控制权限  
	      boolean accessFlag = field.isAccessible();  
	      if (!accessFlag) {  
	        field.setAccessible(true);  
	      }  
		  Object param = field.get(bean); 
		  if (param == null) {  
	        continue;  
	      } 
		  else if(varName.equals("serialVersionUID"))
	      {
	    	  continue;
	      }else if("stime".equals(varName) || "etime".equals(varName)){
	    	  continue;
	      }else if (param instanceof Integer) {//判断变量的类型  
	        int value = ((Integer) param).intValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	       
	      } else if (param instanceof String) {  
	        String value = (String) param;  
	        query.addCriteria( Criteria.where(varName).regex(".*?" +value+ ".*"));
	      } else if (param instanceof Double) {  
	        double value = ((Double) param).doubleValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	      } else if (param instanceof Float) {  
	        float value = ((Float) param).floatValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	      } else if (param instanceof Long) {  
	        long value = ((Long) param).longValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	      } else if (param instanceof Boolean) {  
	        boolean value = ((Boolean) param).booleanValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	      } else if (param instanceof Date) {  
	        Date value = (Date) param;  
	        query.addCriteria( Criteria.where(varName).is(value));
	      }  
	      // 恢复访问控制权限  
	      field.setAccessible(accessFlag);  
		 }
		return query;
	}
	
	
	public static Query bean2Where(Object bean) throws IllegalArgumentException, IllegalAccessException{
		Query query=new Query();
		Field[] fields = bean.getClass().getDeclaredFields();  
		StringBuffer queryFiles = new StringBuffer();
		for (Field field : fields) {
			  // 获取属性名  
	      String varName = field.getName();  
	      // 修改访问控制权限  
	      boolean accessFlag = field.isAccessible();  
	      if (!accessFlag) {  
	        field.setAccessible(true);  
	      }  
		  Object param = field.get(bean); 
		  if (param == null) {  
	        continue;  
	      } else if(varName.equals("serialVersionUID"))
	      {
	    	  continue;
	      }else if (param instanceof Integer) {//判断变量的类型  
	        int value = ((Integer) param).intValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	        queryFiles.append(varName+"="+value+";");
	        
	      } else if (param instanceof String) {  
	        String value = (String) param;  
	        query.addCriteria( Criteria.where(varName).is(value));
	        queryFiles.append(varName+"="+value+";");
	      } else if (param instanceof Double) {  
	        double value = ((Double) param).doubleValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	        queryFiles.append(varName+"="+value+";");
	      } else if (param instanceof Float) {  
	        float value = ((Float) param).floatValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	        queryFiles.append(varName+"="+value+";");
	      } else if (param instanceof Long) {  
	        long value = ((Long) param).longValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	        queryFiles.append(varName+"="+value+";");
	      } else if (param instanceof Boolean) {  
	        boolean value = ((Boolean) param).booleanValue();  
	        query.addCriteria( Criteria.where(varName).is(value));
	        queryFiles.append(varName+"="+value+";");
	      } else if (param instanceof Date) {  
	        Date value = (Date) param;  
	        query.addCriteria( Criteria.where(varName).is(value));
	        queryFiles.append(varName+"="+value+";");
	      }  
	      // 恢复访问控制权限  
	      field.setAccessible(accessFlag);  
		 }
		log.debug(queryFiles.toString());
		return query;
	}
	
	public static DBObject bean3Where(Object bean) throws IllegalArgumentException, IllegalAccessException{
		DBObject ref=new BasicDBObject();
		Field[] fields = bean.getClass().getDeclaredFields();  
		 for (Field field : fields) {
			  // 获取属性名  
	      String varName = field.getName();  
	      // 修改访问控制权限  
	      boolean accessFlag = field.isAccessible();  
	      if (!accessFlag) {  
	        field.setAccessible(true);  
	      }  
		  Object param = field.get(bean); 
		  if (param == null) {  
	        continue;  
	      } else if(varName.equals("serialVersionUID"))
	      {
	    	  continue;
	      }else if (param instanceof Integer) {//判断变量的类型  
	        int value = ((Integer) param).intValue();  
	        ref.put(varName, value);
	      } else if (param instanceof String) {  
	        String value = (String) param;  
	        ref.put(varName, value);
	      } else if (param instanceof Double) {  
	        double value = ((Double) param).doubleValue();  
	        ref.put(varName, value);
	      } else if (param instanceof Float) {  
	        float value = ((Float) param).floatValue();  
	        ref.put(varName, value);
	      } else if (param instanceof Long) {  
	        long value = ((Long) param).longValue();  
	        ref.put(varName, value);
	      } else if (param instanceof Boolean) {  
	        boolean value = ((Boolean) param).booleanValue();  
	        ref.put(varName, value);
	      } else if (param instanceof Date) {  
	        Date value = (Date) param;  
	        ref.put(varName, value);
	      }  
	      // 恢复访问控制权限  
	      field.setAccessible(accessFlag);  
		 }
		return ref;
	}
	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
	public static void transMap2Bean2(Map<String, Object> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			org.apache.commons.beanutils.BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
	}

	// Map --> Bean 1: 利用Introspector,PropertyDescriptor实现 Map --> Bean
	@Deprecated
	public static void transMap2Bean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				if (map.containsKey(key)) {
					try {
						Object value = map.get(key);
						// 得到property对应的setter方法
						Method setter = property.getWriteMethod();
						setter.invoke(obj, value);
						
					} catch (Exception e) {
						System.out.println("transMap2Bean Error " + e);
						continue;
					}
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}

	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
	public static Map<String, Object> transBean2Map(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();

				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);

					map.put(key, value);
				}

			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}
}
