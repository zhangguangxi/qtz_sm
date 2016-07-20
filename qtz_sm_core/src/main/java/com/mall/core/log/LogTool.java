package com.mall.core.log;
import org.apache.log4j.Logger;

import com.mall.core.exception.ServiceException;

/**
 * <p>Title:LogTool</p>
 * <p>Description:日志工具类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

public class LogTool {
	/**日志对象*/
	private Logger logger = Logger.getLogger(LogTool.class);
	
	/**
	 * 得到当前实例对象
	 * @return 当前实例对象
	 * @param objects class
	 */
	
	@SuppressWarnings("rawtypes")
	public static LogTool getInstance(Object...objects) {
		LogTool log = new LogTool();
		if(null != objects && objects.length > 0 && objects[0] instanceof Class) {
			log.logger = Logger.getLogger((Class)objects[0]);
		}
		return log;
	}
	
	/**
	 * 打印DEBUG级别日志
	 * @param message 日志信息
	 */
	
	public void debug(Object message) {
		logger.debug("☆☆☆【" + message + "】☆☆☆");
	}
	
	/**
	 * 打印ERROR级别日志
	 * @param message 日志信息
	 */
	
	public void error(Object message, Object...objects) {
		if(null != objects && objects.length > 0 && objects[0] instanceof Throwable) {
			logger.error("◆◆◆【" + message + "】◆◆◆", (Throwable)objects[0]);
		} else {
			logger.error("◆◆◆【" + message + "】◆◆◆");
		}
	}
	/**
	 * 打印ERROR级别日志
	 * @param message 日志信息
	 */
	
	public void error(Object message) {
		if(message instanceof Throwable){
			logger.error("接口调用报错 ◆◆◆["+message+"]◆◆◆",(Throwable)message);
		}else{
			logger.error("◆◆◆【" + message + "】◆◆◆");
		}
	}
	
	/**
     * 打印ERROR级别日志
     * @param message 日志信息
     */
    
    public void error(ServiceException message) {
        if(message.getErrorType()==0 && message instanceof Throwable){
            logger.error("接口调用报错 ◆◆◆["+message+"]◆◆◆",(Throwable)message);
        }else{
            logger.error("◆◆◆【" + message.getErrorMessage() + "】◆◆◆");
        }
    }
	
	/**
	 * 打印FATAL级别日志
	 * @param message 日志信息
	 */
	
	public void fatal(Object message) {
		logger.fatal("◇◇◇【" + message + "】◇◇◇");
	}
	
	/**
	 * 打印INFO级别日志
	 * @param message 日志信息
	 */
	
	public void info(Object message) {
		logger.info("★★★【" + message + "】★★★");
	}
	
	/**
	 * 打印WARN级别日志
	 * @param message 日志信息
	 */
	
	public void warn(Object message) {
		logger.warn("※※※【" + message + "】※※※");
	}
	
	/**
	 * 是否有DEBUG级别
	 * @return true/false
	 */
	
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}
}