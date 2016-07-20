package com.mall.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.mall.core.log.LogTool;

/**
 * 
 * <p>Title:MethodTimeAop</p>
 * <p>Description:(方法时间相应)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2016年1月14日
 */
public class MethodTimeAop {
	private static LogTool log = LogTool.getInstance(MethodTimeAop.class);
	/**
	 * 
	  * 【增强】
	  * @param point
	  * @return
	  * @throws Throwable  
	  * @time:2016年1月14日 下午12:58:17
	  * @author 涂鑫
	  * @version
	 */
	 public Object around(ProceedingJoinPoint point) throws Throwable {
		 	Long startTime=System.currentTimeMillis();
			String start = "方法调用计时开始: <<<<<<<方法名:"+point.getTarget().getClass().getName() + ":" + point.getSignature().getName()+"开始时间:"+startTime;		//操作名称
			if (log.isDebugEnabled()) {
				log.debug(start);
			}
			Object target = point.proceed();
			Long endTime=System.currentTimeMillis();
			String end = "方法调用计时结束: <<<<<<<方法名:"+point.getTarget().getClass().getName() + ":" + point.getSignature().getName()+"结束时间:"+endTime;		//操作名称
			if (log.isDebugEnabled()) {
				log.debug(end);
				log.debug("======="+point.getTarget().getClass().getName() + ":" + point.getSignature().getName()+"=========共耗时:"+(endTime-startTime));
			}
		 return target;
	 }

}
