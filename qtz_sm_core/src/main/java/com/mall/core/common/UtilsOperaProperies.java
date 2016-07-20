package com.mall.core.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mall.core.common.model.Propertes;




/**
 * <p>Title:UtilsOperaProperies</p>
 * <p>Description:(proptries 的文件操作工具类)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-28
 */

public class UtilsOperaProperies {

	private static Log log = LogFactory.getLog(UtilsOperaProperies.class);
	
	/** 
	* 【读取properties的全部信息】
	* @param filePath
	* @return  
	*/
	public static Properties readPropertiesP(String filePath)
	{
		if(filePath == null || filePath.equals("filePath")) return null;
		 Properties props = new Properties();
		 InputStream in = null;
		 try {
			in = UtilsOperaProperies.class.getClassLoader().getResourceAsStream(filePath);
			try {
				props.load(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} finally
		{
			try {
				if(null != in) in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		 return props;
	}
	
	
	/** 
	* 【读取properties的全部信息】(这里用一句话描述这个方法的作用)
	* @param filePath
	* @return  
	*/
	public static List<Propertes> readPropertiesList(String filePath)
	{
		if(filePath == null || filePath.equals("filePath")) return null;
		List<Propertes> list = new ArrayList<Propertes>();
		String key = "",Property="";
        Properties props = readPropertiesP(filePath);
        Propertes pop = null;
		Enumeration<?> en = props.propertyNames();
        while (en.hasMoreElements()) 
        {
       	    key = (String) en.nextElement();
            Property = props.getProperty (key);
            pop = new Propertes(key, Property);
            list.add(pop);
        }
		return list;
	}
	
	
	/** 
	* 【读取properties的全部信息】
	* @param filePath
	* @return  
	*/
	public static Map<String,String> readProperties(String filePath) {
    	if(filePath == null || filePath.equals("filePath")) return null;
         Map<String,String> map = new HashMap<String, String>();
         String key = "",Property="";
         Properties props =readPropertiesP(filePath);
         Enumeration<?> en = props.propertyNames();
         while (en.hasMoreElements()) 
         {
        	 key = (String) en.nextElement();
             Property = props.getProperty (key);
             map.put(key, Property);
             log.info(">>>>>>>>>>>>"+key+" = "+Property);
         }
         return map;
    }
	
	/** 
	* 【得到文件的真实路径】
	* @param filePath
	* @param key
	* @return  
	*/
	public static String readValue(String filePath,String key) {
		 
		 if(filePath == null || filePath.equals("")) return null;
		 if(key == null || key.equals("")) return null;
		 Properties props = readPropertiesP(filePath);
		
		 return props.getProperty (key);
	 }
	 
	 public static void main(String[] args) 
	 {
		 
	 }
	 
}
