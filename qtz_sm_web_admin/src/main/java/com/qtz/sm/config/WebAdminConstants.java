package com.qtz.sm.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import com.mall.core.common.UtilsOperaProperies;

/**
 * <p>Title:Constants</p>
 * <p>Description:(web-admin工程常量)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 欧江波 928482427@qq.com
 * @version v1.0 2014-10-3
 */
public class WebAdminConstants{
	
	public static class Aliyun {
	
		//阿里云 key
		public static final String ALIYUN_OSS_ACCESS_KEY="ALIYUN_OSS_ACCESS_KEY";
		//阿里云 key secret
		public static final String ALIYUN_OSS_ACCESS_KEY_SECRET="ALIYUN_OSS_ACCESS_KEY_SECRET";

		//桶1  个人数据
		public static final String ALIYUN_OSS_BUCKET_ONE="ALIYUN_OSS_BUCKET_ONE";
		//桶1绑定域名
		public static final String qtzimg01="qtzimg01";
		
		//桶2
		public static final String ALIYUN_OSS_BUCKET_TWO="ALIYUN_OSS_BUCKET_TWO";
		//桶2绑定域名
		public static final String qtzimg02="qtzimg02";

	}
	
	
	private static String filePro = "base.properties";  //属性文件路径
	private static Map<String,String> filePro_map= UtilsOperaProperies.readProperties(filePro);  //缓存属性文件夹

	/** 
	* 【获得系统属性文件值】(这里用一句话描述这个方法的作用)
	* @param key 属性key
	* @return  属性值
	*/
	public static String getValueByKeyFromfilePro(String key){
		return filePro_map.get(key);
	}
	
	/** 
	* 【写入系统属性文件值 并更新缓存】(这里用一句话描述这个方法的作用)
	* @param key
	* @param value
	* @throws Exception  
	*/
	public synchronized static void setValueFromfilePro(String key,String value) throws Exception{
		String path =Thread.currentThread().getContextClassLoader().getResource(filePro).getFile();//获取路径并转换成流
		Properties props = new Properties();//属性集合对象
		props.load(new FileInputStream(path));  
        OutputStream fos = new FileOutputStream(path);              
        props.setProperty(key, value);//往属性文件插值
        props.store(fos, "Update '" + key + "' value");  
        System.out.println(props.getProperty(key));
		filePro_map= UtilsOperaProperies.readProperties(filePro);  //缓存属性文件夹
	}
}
