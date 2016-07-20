package com.mall.core.common.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;

/**
 * <p>Title:RespJsonPHandler</p>
 * <p>Description:(jsonP调用返回结果封装类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月1日
 */
public class RespJsonPHandler {
	
	private static LogTool log = LogTool.getInstance(RespJsonPHandler.class);
	/**
	 * 请求处理成功
	 * @param json
	 * @return
	 */
	public static void respOK(HttpServletResponse response,HttpServletRequest request){
		
		JSONObject json = new JSONObject();
		json.put("code", 0);
		send(json,response,request);
	}
	
	/**
	 * 
	* 【请求处理成功】(这里用一句话描述这个方法的作用)
	* @param map
	* @param response
	 */
	public static void respMapOK(Map<String,Object> map,HttpServletResponse response,HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.put("code", 0);
		
		Iterator<String> itera = map.keySet().iterator();
		String name="";
		while(itera.hasNext()){
			name = itera.next();
			json.put(name, map.get(name));
		}
		
		send(json,response,request);
	}
	/**
	 * 
	* 【请求处理成功】(这里用一句话描述这个方法的作用)
	* @param obj
	* @param response
	 */
	public static void respOK(Object obj,HttpServletResponse response,HttpServletRequest request){
		
		respOK(null, obj, response,request);
	}
	/**
	 * 请求处理成功
	 * @param json
	 * @return
	 */
	public static void respOK(Pager<?, ?> page,Object obj,HttpServletResponse response,HttpServletRequest request){
		// 分页数据
		PagerDm dmpage = null;
		JSONObject json = new JSONObject();
		if (null != page) {
			int nextPage = 0;
			if (page.getPageCount() > page.getNowPage())
				nextPage = 1;

			dmpage = new PagerDm(page.getNowPage(), page.getPageSize(), page.getRowCount(), nextPage);
			json.put("list", page.getList());
			json.put("page", dmpage);
		}
		json.put("code", 0);
		json.put("data", obj);

		send(json,response,request);
	}
	
	/**
	 * 请求处理失败
	 * @param httpStatus
	 * @param msg
	 * @param response
	 * @return
	 */
	private static void respError(int httpStatus, JSONObject msg, HttpServletResponse response,HttpServletRequest request){
		
		response.setStatus(httpStatus);
		send(msg,response,request);
	}

	/**
	 * 请求处理失败
	 * @param httpStatus
	 * @param msg
	 * @param response
	 * @return
	 */
	public static void respError(JSONObject msg, HttpServletResponse response,HttpServletRequest request){
		send(msg,response,request);
	}
	
	/**
	 * 服务段抛出异常处理
	 * @param code
	 * @param httpStatus
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public static void respServerError(HttpServletResponse response,HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.putAll(RespMsg.server_throws_exception);
		respError(500, json, response, request);
	}
	
	
	/**
	 * 服务段抛出异常处理
	 * @param code
	 * @param httpStatus
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	public static void respServerTimeout(HttpServletResponse response,HttpServletRequest request){
		
		JSONObject json = new JSONObject();
		json.putAll(RespMsg._timeout);
		send(json,response,request);
	}
	
	/**
	 * 
	  * 【界面找不到】
	  * @param response  
	  * @time:2015年12月24日 上午9:16:57
	  * @author 涂鑫
	  * @version
	 */
	public static void resp404(HttpServletResponse response,HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.putAll(RespMsg.error_404);
		respError(404, json, response, request);
	}
	
	/**
	 * 
	  * 【请求方法找不到】
	  * @param response  
	  * @time:2015年12月24日 上午9:17:19
	  * @author 涂鑫
	  * @version
	 */
	public static void resp405(HttpServletResponse response,HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.putAll(RespMsg.error_405);
		respError(405, json, response, request);
	}
	/**
	 * 
	  * 【参数匹配不上】
	  * @param response  
	  * @time:2016年1月6日 上午11:05:37
	  * @author 涂鑫
	  * @version
	 */
	public static void resp400(HttpServletResponse response,HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.putAll(RespMsg.error_400);
		respError(400, json, response, request);
	}


	/**
	* 【入参验证校验不通过错误提示信息】
	* @param exceptionType
	* @param msg
	* @param response
	* @param request  
	*/
	public static void respInputError(int exceptionType,JSONArray msg,HttpServletResponse response,HttpServletRequest request){
		JSONObject json=new JSONObject();
		json.put("code", exceptionType);
		json.put("errors", msg);
		send(json,response,request);
	}
	
	/**
	* 【返回内容】
	* @param json
	* @param response
	* @param request
	*/
	private static void send(JSONObject json, HttpServletResponse response,HttpServletRequest request)
	{
		log.debug("<<<<<<<<调试输出日志:"+json.toString()+">>>>>>>");
		response.setContentType("text/plain");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
		try {
			out = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			String jsonpCallback = request.getParameter("callback");//客户端请求参数  
			if (null != jsonpCallback) {
				out.println(jsonpCallback + "("+json.toJSONString() + ")");//返回jsonp格式数据  
			}else{
				out.println(json);
			}
			out.flush();  
			out.close();
		} catch (IOException e) {
			log.error(e);
		}
	}

	/**
	* 【处理返回结果】
	* @param errorType
	* @param errorTitle
	* @param resp
	* @param req  
	*/
	public static void respError(int errorType, String errorTitle, HttpServletResponse resp, HttpServletRequest req) {
		JSONObject json=new JSONObject();
		if(errorType == 0){
			errorType = ExceptionCode.SERVER_EXCEPTION;
		}
		json.put("code", errorType);
		json.put("msg", errorTitle);
		send(json,resp,req);
	}
	/**
	 * 请求处理api登陆权限失败
	 * @param httpStatus
	 * @param msg
	 * @param response
	 * @return
	 */
	public static void respApiLoginError(HttpServletResponse response, HttpServletRequest request){
		JSONObject json = new JSONObject();
		json.putAll(RespMsg.user_not_login);
		send(json, response, request);
	}
}
