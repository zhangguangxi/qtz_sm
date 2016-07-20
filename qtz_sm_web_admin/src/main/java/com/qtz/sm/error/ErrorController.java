package com.qtz.sm.error;

import com.mall.core.common.response.RespJsonPHandler;
import com.qtz.sm.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value="error/")
public class ErrorController extends BaseController{
	
	
	@RequestMapping(value="404")
	public void _404(HttpServletRequest request, HttpServletResponse response){
		RespJsonPHandler.resp404(response,request);
	}
	
	@RequestMapping(value="405")
	public void _405(HttpServletRequest request, HttpServletResponse response){
		RespJsonPHandler.resp405(response,request);
	}

	@RequestMapping(value="400")
	public void _400(HttpServletRequest request, HttpServletResponse response){
		RespJsonPHandler.resp400(response,request);
	}
	
	@RequestMapping(value="500")
	public void _500(HttpServletRequest request, HttpServletResponse response){
		RespJsonPHandler.respServerError(response,request);
	}

	@RequestMapping(value="timeout")
	public void _timeout(HttpServletRequest request, HttpServletResponse response){
		RespJsonPHandler.respServerTimeout(response,request);
	}

}
