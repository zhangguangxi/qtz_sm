package com.mall.core.exception;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.mall.core.log.LogTool;

/**
 * <p>Title:MyControlException</p>
 * <p>Description:系统总控异常类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-10-3
 */

public class MyControlException implements HandlerExceptionResolver {

	protected static final LogTool log =  LogTool.getInstance(MyControlException.class) ; 
	
	/** 
	* 【处理】系统异常
	* @param 	request				接收请求对象
	* @param	response			响应请求对象
	* @param	handler				处理对象
	* @param	ex					异常对象
	* @retrun 	ModelAndView  		模型和视图对象
	*/
	
	public ModelAndView resolveException(HttpServletRequest request, 
										 HttpServletResponse response, 
										 Object handler, 
										 Exception ex) {
		Map<String, String> model = getMapException(ex);
		return new ModelAndView("error/error", model);
	}
	
	
	
	/** 
	* 【取得】异常参数MAP对象
	* @param 	ex		异常对象
	* @return  	Map		异常参数MAP对象
	*/
	
	private Map <String, String> getMapException(Exception ex) {
		Map<String, String> model = new HashMap<String, String>();
		Throwable throwable = ex;
		ByteArrayOutputStream buf = new ByteArrayOutputStream();   
		ex.printStackTrace(new PrintWriter(buf, true));  
		model.put("exlist", buf.toString());
log.error(model.get("exlist"));
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}
		if (throwable instanceof ActionException || throwable instanceof DaoException || throwable instanceof ServiceException) {
			model.put("level", "biz"); 
		} else if(throwable instanceof SocketException) {
			model.put("level", "socket"); 
		} else if (throwable instanceof SQLException) {
			model.put("level", "sql"); 
		} else {
			model.put("level", "sys"); 
		}
		model.put("exname", throwable.getClass().getSimpleName());
		model.put("exmsg", throwable.getMessage());
		return model;
	}
	
	
	/** 
	* 【取得】异常级别：[sys：系统异常, biz:业务异常, socket:网络异常, sql:SQL执行异常]
	* @param 	ex			异常对象
	* @return  	String		[sys：系统异常, biz:业务异常, socket:网络异常, sql:SQL执行异常]
	*/
	
	public String getExceptionLevel(Exception ex) {
		Throwable throwable = ex;
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}
		if (throwable instanceof ActionException || throwable instanceof DaoException || throwable instanceof ServiceException) {
			return "biz";
		} else if(throwable instanceof SocketException) {
			return "socket";
		} else if (throwable instanceof SQLException) {
			return "sql";
		} else {
			return "sys";
		}
	}
	
	
	/** 
	* 【取得】详细异常信息串
	* @param 	ex			异常对象
	* @return  	String		详细异常信息串
	*/
	
	public String getDetailException(Exception ex) {
		String CException = "";
		ByteArrayOutputStream buf = new ByteArrayOutputStream();   
		ex.printStackTrace(new PrintWriter(buf, true));   
		CException = buf.toString();
		return CException;
	}
	
	
	/** 
	* 【取得】最底层异常信息串
	* @param 	ex			异常对象
	* @return  	String		最度层异常信息串
	*/
	
	public String getLowerException(Exception ex) {
		String CException = "";
		Throwable throwable = ex;
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}
		ByteArrayOutputStream buf = new ByteArrayOutputStream();   
		throwable.printStackTrace(new PrintWriter(buf,true));   
		CException = buf.toString();
		return CException;
	}
	
	
	/** 
	* 【取得】最底层异常对象名
	* @param 	ex			异常对象
	* @return  	String		最度层异常对象名
	*/
	
	public String getLowerExceptionName(Exception ex) {
		String CException = "";
		Throwable throwable = ex;
		while (throwable.getCause() != null) {
			throwable = throwable.getCause();
		}
		CException = throwable.getClass().getSimpleName();
		return CException;
	}
}