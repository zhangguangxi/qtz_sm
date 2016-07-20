package com.mall.core.exception;
import com.mall.core.common.Global;

/**
 * <p>Title:ActionException</p>
 * <p>Description:动作(ACTION)层异常类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

public class ActionException extends Exception {
	/**
	 * 对象版本编号
	 */
	private static final long serialVersionUID = 7696590695741706297L;
	/**
	 * 错误信息
	 */
	private String errorMessage = null;
	
	/**
	 * ActionException构造函数
	 */
	
	public ActionException() {
		super();
		errorMessage = "Action layer is Error!!";
	}
	
	/**
	 * ActionException构造函数根据传递的异常信息
	 * @param argMessage
	 */
	
	public ActionException(String argMessage) {
		super(argMessage);
		errorMessage = argMessage;
	}
	
	/**
	 * ActionException构造函数根据传递的异常信息
	 * @param argMessage
	 * @param argThr
	 */
	
	public ActionException(String argMessage, Throwable argThr) {
		super(argMessage,argThr);
	}
	
	/**
	 * ActionException构造函数通过传递异常对象
	 * @param argThr
	 */
	
	public ActionException(Throwable argThr) {
		super(argThr);
		errorMessage = argThr.getLocalizedMessage();
	}
	
	/**
	 * 当该异常被打印出来的时候执行
	 * @return String
	 */
	
	public String toString() {
		StringBuffer sqlString = new StringBuffer(Global.EXPCEPTION_ACTION);
		sqlString.append("****************************************ActionException Start****************************************\n");
		sqlString.append(errorMessage);
		sqlString.append("\n****************************************ActionException End****************************************");
		return sqlString.toString();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
}