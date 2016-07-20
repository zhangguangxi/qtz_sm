package com.qtz.sm.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
/**
 * 
 * <p>Title:GZipFilter</p>
 * <p>Description:(gzip 压缩)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年7月16日
 */
public class GZipFilter implements Filter {

	public void destroy() {
	}

	/**
	 * 判断浏览器是否支持GZIP
	 * 
	 * @param request
	 * @return
	 */
	private static boolean isGZipEncoding(HttpServletRequest request) {
		boolean flag = false;
		String encoding = request.getHeader("Accept-Encoding");
		if (!StringUtils.isEmpty(encoding) && encoding.indexOf("gzip") != -1) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 这里没有采用tomcat gzip压缩 如果需要启动tomcatgzip 需要关闭这里压缩
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		if (isGZipEncoding(req)) {
			Wrapper wrapper = new Wrapper(resp);
			chain.doFilter(request, wrapper);
			byte[] gzipData = gzip(wrapper.getResponseData());
			resp.addHeader("Content-Encoding", "gzip");
			resp.setContentLength(gzipData.length);
			ServletOutputStream output = null;
			try {
				output=resp.getOutputStream();
			} catch (IllegalStateException e) {
				return;
			}
			output.write(gzipData);
			output.flush();
			output.close();
		} else {
			chain.doFilter(request, response);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	private byte[] gzip(byte[] data) {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10240);
		GZIPOutputStream output = null;
		try {
			output = new GZIPOutputStream(byteOutput);
			output.write(data);
		} catch (IOException e) {
		} finally {
			try {
				output.close();
			} catch (IOException e) {
			}
		}
		return byteOutput.toByteArray();
	}

}
