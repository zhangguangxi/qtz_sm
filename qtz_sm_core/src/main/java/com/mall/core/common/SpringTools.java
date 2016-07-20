package com.mall.core.common;
import java.util.Locale;
import org.springframework.context.ApplicationContext;

/**
 * <p>Title:SpringTools</p>
 * <p>Description:spring工具类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-29
 */

public class SpringTools {
	/**应用上下文*/
	private static ApplicationContext appCtx;
	
	/**
	 * 构造方法
	 */
	
	private SpringTools() {
	}
	
	/**
	 * 配置应用上下文
	 * @param ctx 应用上下文
	 */
	
	public synchronized static void configure(ApplicationContext ctx) {
		if (appCtx != null) {
			throw new IllegalStateException("只能设置一次!");
		}
		appCtx = ctx;
	}
	
	/**
	 * 释放spring应用上下文
	 * @exception Throwable 异常
	 */
	
	protected void finalize() throws Throwable {
		super.finalize();
		appCtx = null;
		System.err.println("SpringUtils finalize.........");
	}
	
	/**
	 * 销毁spring应用上下文
	 */
	
	public static void destroy() {
		appCtx = null;
	}
	
	/**
	 * 根据spring定义的名字得到对应的实例
	 * @param name 在spring定义的Bean名字
	 * @return 配置的实例对象
	 */
	
	public static Object getSpringBean(String name) {
		return appCtx.getBean(name);
	}
	
	/**
	 * 功能:得到通用dao的实例
	 * @return CommonDao对象
	 */
	
//	public static CommonDao getCommonDao(){
//		return (CommonDao)appCtx.getBean(Global.SPRING_PUBLIC_COMMON_DAO);
//	}
	
	/**
	 * 功能:根据国际化的key得到相应的值
	 * @param key 国际化的key
	 * @return 国际化的值
	 */
	
	public static String getMessage(String key) {
		return getMessage(key, null);
	}
	
	/**
	 * 功能:根据国际化的key得到相应的值
	 * @param key 国际化的key
	 * @param param 值中定义的参数{0},{1}等的替换
	 * @return 国际化的值
	 */
	
	public static String getMessage(String key, Object[] param) {
		// 设置默认的语言环境为中文，此设置不影响机器的语言环境
		Locale locale = new Locale("zh", "CN");
		Locale.setDefault(locale);
		return appCtx.getMessage(key, param, Locale.getDefault());
	}
	
	/**
	 * 得到spring上下文对象
	 * @return ApplicationContext
	 */
	
	public static ApplicationContext getContext() {
		return appCtx;
	}
}