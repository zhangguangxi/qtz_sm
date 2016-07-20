package com.qtz.sm.interceptor;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * <p>Title:StepsInterceptor</p>
 * <p>Description:(步奏拦截器)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月21日
 */
public class StepsInterceptor implements HandlerInterceptor{
	//排除
	private List<String> excludeUrls;

	public void setExcludeUrls(List<String> excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public List<String> getExcludeUrls() {
		return excludeUrls;
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		if (excludeUrls.contains(url)) {
			return true;
		} else {
			for (String regex : excludeUrls) {
				//String subN = StringUtil.subN(url, "/", 2);
				if(Pattern.matches(regex.toString(), url.toString())){
					return true;
				}
				
			}
		}	
		// String sid = request.getHeader("token");
		return true;
	}

}
