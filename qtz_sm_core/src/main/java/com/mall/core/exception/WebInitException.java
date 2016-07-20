package com.mall.core.exception;

/**
 * <p>Title:WebInitException</p>
 * <p>Description:控制异常类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

public class WebInitException extends Exception {

	/** (info) */
	private static final long serialVersionUID = 9004784572463018700L;
//	/**
//	 * 对象版本编号
//	 */
//	private static final long serialVersionUID = 7696590695741706297L;
//	/**
//	 * 错误信息
//	 */
//	private String errorMessage = null;
//	
//	/**
//	 * WebInitException构造函数
//	 */
//	
//	public WebInitException() {
//		super();
//		errorMessage = "WebInit layer is Error!!";
//	}
//	
//	/**
//	 * WebInitException构造函数根据传递的异常信息
//	 * @param argMessage
//	 */
//	
//	public WebInitException(String argMessage) {
//		super(argMessage);
//		errorMessage = argMessage;
//	}
//	
//	/**
//	 * WebInitException构造函数根据传递的异常信息
//	 * @param argMessage
//	 * @param argThr
//	 */
//	
//	public WebInitException(String argMessage, Throwable argThr) {
//		super(argMessage,argThr);
//	}
//	
//	/**
//	 * WebInitException构造函数通过传递异常对象
//	 * @param argThr
//	 */
//	
//	public WebInitException(Throwable argThr) {
//		super(argThr);
//		errorMessage = argThr.getLocalizedMessage();
//	}
//	
//	/**
//	 * 当该异常被打印出来的时候执行
//	 * @return String
//	 */
//	
//	public String toString() {
//		StringBuffer sqlString = new StringBuffer(Global.EXPCEPTION_WEBINIT);
//		sqlString.append("****************************************WebInitException Start****************************************\n");
//		sqlString.append(errorMessage);
//		sqlString.append("\n****************************************WebInitException End****************************************");
//		return sqlString.toString();
//	}	
}