package com.mall.core.exception;
import com.mall.core.common.Global;

/**
 * <p>Title:DaoException</p>
 * <p>Description:数据处理层异常类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

public class DaoException extends Exception {
	/**
	 * 对象版本编号
	 */
	private static final long serialVersionUID = 7696590695741706297L;
	/**
	 * 错误信息
	 */
	private String errorMessage = null;
	
	/**
	 * 概括错误信息
	 */
	private String errorTitle = null;
	
	/**错误类型，缺省为0*/
	private int errorType = 0;
	
	/**
	 * DaoException构造函数
	 */
	
	public DaoException() {
		super();
		errorMessage = "DAO layer is Error!!";
	}
	
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage
	 */
	
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage
	 */
	public DaoException(int errorType,String errorTitle) {
		super(errorTitle);
		this.errorTitle = errorTitle;
		this.errorMessage = errorTitle;
		this.errorType = errorType;
	}
	
	public DaoException(String argMessage) {
		super(argMessage);
		errorMessage = argMessage;
	}
	
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage
	 * @param argThr
	 */
	public DaoException(int errorType,String argMessage, Throwable argThr) {
		super(argMessage,argThr);
		this.errorType = errorType;
	}
	
	/**
	 * DaoException构造函数根据传递的异常信息
	 * @param argMessage
	 * @param argThr
	 */
	
	public DaoException(String argMessage, Throwable argThr) {
		super(argMessage,argThr);
	}
	
	/**
	 * DaoException构造函数通过传递异常对象
	 * @param argThr
	 */
	
	public DaoException(Throwable argThr) {
		super(argThr);
		errorMessage = argThr.getLocalizedMessage();
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorTitle() {
		return errorTitle;
	}

	public void setErrorTitle(String errorTitle) {
		this.errorTitle = errorTitle;
	}

	public int getErrorType() {
		return errorType;
	}

	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}

	/**
	 * 当该异常被打印出来的时候执行
	 * @return String
	 */
	
	public String toString() {
		StringBuffer sqlString = new StringBuffer(Global.EXPCEPTION_DAO);
		sqlString.append("****************************************DaoException Start****************************************\n");
		sqlString.append(errorMessage);
		sqlString.append("\n****************************************DaoException End****************************************");
		return sqlString.toString();
	}	
}
