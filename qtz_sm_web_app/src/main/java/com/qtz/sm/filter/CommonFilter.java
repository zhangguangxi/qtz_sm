package com.qtz.sm.filter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.mall.core.log.LogTool;

public class CommonFilter implements Filter{
	private static LogTool log = LogTool.getInstance(CommonFilter.class);
	public void destroy() {
	}

	@SuppressWarnings("rawtypes")
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
	  Map<String,String> params = new HashMap<String,String>();
	  HttpServletRequest aHttpServletRequest=(HttpServletRequest)request;
	  log.info("请求API "+aHttpServletRequest.getRequestURL());
	 // String sid = aHttpServletRequest.getHeader("token");
      Map requestParams = request.getParameterMap();
      for (Iterator iterator = requestParams.keySet().iterator(); iterator.hasNext();) {
          String name = (String) iterator.next();
          String[] values = (String[]) requestParams.get(name);//获取名字集合 值
          String valueStr = "";
          for (int i = 0; i < values.length; i++) {
              valueStr = (i == values.length - 1) ? valueStr + values[i]
                      : valueStr + values[i] + ",";
          }
          log.debug("name="+name+"       values="+valueStr);
          params.put(name, valueStr);
      }
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
		log.debug("过滤器初始化开始执行...");
	}
}
