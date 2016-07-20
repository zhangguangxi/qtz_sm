package com.mall.core.common.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 
 * <p>Title:RespHandler</p>
 * <p>Description:用户返回处理程序</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年3月11日
 */
public class RespHandler {
	
	protected static LogTool log = LogTool.getInstance(RespHandler.class);
	/**
	 * 请求处理成功
	 * @param json
	 * @return
	 */
	public static void respOK(HttpServletResponse response ) throws IOException{
		JSONObject json = new JSONObject();
		json.put("code", 0);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter write = response.getWriter();
		write.write(json.toJSONString());
		write.flush();
		write.close();
	}
	

	/**
	 * 返回xml数据
	 * @param json
	 * @return
	 */
	public static void respXml(HttpServletResponse response ,Object xmlObj) throws IOException{
		 XStream xStreamForResponsetData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
	    //将要提交给API的数据对象转换成XML格式数据Post给API
		 String postDataXML = xStreamForResponsetData.toXML(xmlObj);
		 JSONObject json = new JSONObject();
		json.put("code", 0);
		response.setContentType("text/xml");
		PrintWriter write = response.getWriter();
		write.write(postDataXML);
		write.flush();
		write.close();
	}
	
	/**
	 * 
	* 【请求处理成功】(这里用一句话描述这个方法的作用)
	* @param map
	* @param response
	* @throws IOException
	 */
	public static void respMapOK(Map<String,Object> map,HttpServletResponse response) throws IOException{
		//respOK(null, obj,response);
		JSONObject json=new JSONObject();
		json.put("code", 0);
		
		Iterator<String> itera = map.keySet().iterator();
		String name="";
		while(itera.hasNext()){
			name = itera.next();
			json.put(name, map.get(name));
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter write = response.getWriter();
		write.write(json.toJSONString());
		write.flush();
		write.close();
	}
	/**
	 * 
	* 【请求处理成功】(这里用一句话描述这个方法的作用)
	* @param obj
	* @param response
	* @throws IOException
	 */
	public static void respOK(Object obj,HttpServletResponse response) throws IOException{
		respOK(null, obj, response);
	}
	/**
	 * 请求处理成功
	 * @param json
	 * @return
	 * @throws IOException 
	 */
	public static void respOK(Pager<?, ?> page,Object obj,HttpServletResponse response) throws IOException {
		// 分页数据
		PagerDm dmpage = null;
		if (null != page) {
			@SuppressWarnings("unused")
			int resultNum = 0;
			if (null != page.getList() && !page.getList().isEmpty())
				resultNum = page.getList().size();
			int nextPage = 0;
			if (page.getPageCount() > page.getNowPage())
				nextPage = 1;

			dmpage = new PagerDm(page.getNowPage(), page.getPageSize(),
					page.getRowCount(), nextPage);
		}
		JSONObject json=new JSONObject();
		json.put("code", 0);
		json.put("page", dmpage);
		json.put("data", obj);
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter write = response.getWriter();
		log.debug("<<<<<<<<调试输出日志:"+json.toString()+">>>>>>>");
		write.write(json.toJSONString());
		write.flush();
		write.close();
	}
	
	/**
	 * 请求处理失败
	 * @param httpStatus
	 * @param msg
	 * @param response
	 * @return
	 */
	public static Object respError(int httpStatus, JSONObject msg, HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			json.putAll(msg);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(httpStatus);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 请求处理api登陆权限失败
	 * @param httpStatus
	 * @param msg
	 * @param response
	 * @return
	 */
	public static Object respApiLoginError(HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			json.putAll(RespMsg.user_not_login);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 请求参数校验失败
	 * @param httpStatus
	 * @param msg
	 * @param response
	 * @return
	 */
	public static Object respArgNotValidEx(JSONObject jsonObject, HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			json.putAll(jsonObject);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 请求处理失败
	 * @param httpStatus
	 * @param msg
	 * @param response
	 * @return
	 */
	public static Object respError(JSONObject msg, HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			json.putAll(msg);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 服务段抛出异常处理
	 * @param code
	 * @param httpStatus
	 * @param response
	 * @return
	 */
	public static Object respServerError(HttpServletResponse response) {
		try {
			JSONObject json = new JSONObject();
			json.putAll(RespMsg.server_throws_exception);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(500);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 服务段抛出异常处理
	 * @param code
	 * @param httpStatus
	 * @param response
	 * @return
	 */
	public static Object respServerTimeout(HttpServletResponse response) {
		try {
			JSONObject json = new JSONObject();
			json.putAll(RespMsg._timeout);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(200);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	  * 【界面找不到】
	  * @param response  
	  * @time:2015年12月24日 上午9:16:57
	  * @author 涂鑫
	  * @version
	 */
	public static void resp404(HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			json.putAll(RespMsg.error_404);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(404);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	  * 【请求方法找不到】
	  * @param response  
	  * @time:2015年12月24日 上午9:17:19
	  * @author 涂鑫
	  * @version
	 */
	public static void resp405(HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			json.putAll(RespMsg.error_405);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(405);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	  * 【参数匹配不上】
	  * @param response  
	  * @time:2016年1月6日 上午11:05:37
	  * @author 涂鑫
	  * @version
	 */
	public static void resp400(HttpServletResponse response){
		try {
			JSONObject json = new JSONObject();
			json.putAll(RespMsg.error_400);
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(400);
			PrintWriter out = response.getWriter();
			out.println(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void respException(int exceptionType,String msg,HttpServletResponse response){
		try {
			JSONObject json=new JSONObject();
			json.put("code", exceptionType);
			json.put("msg", msg);
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter write = response.getWriter();
			log.debug("<<<<<<<<调试输出日志:"+json.toString()+">>>>>>>");
			write.write(json.toJSONString());
			write.flush();
			write.close();
		}catch (IOException e){
			log.error(e);
		}
	}
	
	
}
