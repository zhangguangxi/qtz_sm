package com.mall.core.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.mall.core.common.model.BrowserModel;




/**
 * <p>Title:GetRequestinfoUtil</p>
 * <p>Description:(获得http请求信息工具类)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-28
 */

public class GetRequestinfoUtil{
	/**
	 * 注意：此方法获得客户端ip不一定是正确的!
	 * 尽量获得真实的客户端ip方法。当服务器使用apache等web服务器做代理时，常规request.getRemoteAddr
	 * ()只能得到apache服务器上的ip地址。
	 * 当客户端使用代理访问时，ip地址是http头中X-Forwarded-For的第一个不为unknown的ip。
	 * 
	 * @Title: getIpAddr
	 * @Description: 
	 * @param request
	 * @return String
	 * @author 赵汉江
	 * @date 2013-3-12 上午10:43:06
	 * @version V1.0
	 */
	public static String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
		{
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * 获得客户端的浏览器信息
	 * @Title: getBrowser
	 * @Description: 
	 * @param request
	 * @return String
	 * @author 赵汉江
	 * @date 2013-11-27
	 * @version V1.0
	 */
	public static BrowserModel getBrowser(HttpServletRequest request){
		String u = request.getHeader("user-agent");
		
		BrowserModel bm = new BrowserModel(u.indexOf("Trident") > -1
				,u.indexOf("Presto") > -1
				,u.indexOf("AppleWebKit") > -1
				,u.indexOf("Gecko") > -1 && u.indexOf("KHTML") == -1
//				,Pattern.matches("/AppleWebKit.* .*/i",u) || Pattern.matches("/AppleWebKit/i",u)
				,!!Pattern.matches("/\\(i[^;]+;( U;)? CPU.+Mac OS X/",u)
				,u.indexOf("Android") > -1 || u.indexOf("Linux") > -1
				,u.indexOf("iPhone") > -1 || u.indexOf("Mac") > -1
				,u.indexOf("iPad") > -1
				,u.indexOf("Safari") == -1);
		return bm;
	}
	
	/**
	 * 获得http下载文件名称，根据request判断客户端浏览器编码 并去除所有空格
	 * @Title: getHttpFileName
	 * @Description: 
	 * @param request
	 * @param name
	 * @return
	 * @throws UnsupportedEncodingException String
	 * @author 赵汉江
	 * @date 2014-3-21 下午05:43:32
	 * @version V1.0
	 */
	public static String getHttpDownloadFileName(HttpServletRequest request,String name) throws UnsupportedEncodingException{
		name = name.replace(" ", "");
		String excelFileName = new String(name.getBytes(), "ISO8859-1");
		//在linux服务器上 IE下载必须加上下面这段代码,window也行
		if(request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0){
			excelFileName = URLEncoder.encode(name, "UTF-8");
		}
		return excelFileName;
	}
}