package com.qtz.sm.controller.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.common.model.ResionModel;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.controller.BaseController;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping(value = "/region/")
public class CsRegionController extends BaseController {

	private static final LogTool log = LogTool.getInstance(CsRegionController.class);

	@Resource(name = "csRegionsServiceImpl")
	private CsRegionsService csRegionsService;

	/**
	 * 获取下级行政区域
	 * @param parentId 父级区域ID
	 * @param req
	 * @param resp
	 * @throws IOException
	 */
	@RequestMapping(value = "getRegions")
	public void getRegions(Integer parentId, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			CsRegions vo = new CsRegions();
			if(null == parentId){
				parentId = 0;
			}
			vo.setParentId(parentId);
			List<CsRegions> list = csRegionsService.findList(vo);
			RespHandler.respOK(list, resp);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respException(e.getErrorType(), e.getErrorTitle(), resp);
		}
	}
	
	/**
	 * 获取省市区全部的JSON
	 */
	@RequestMapping(value = "getResionJson")
	public void getResionJson(HttpServletRequest req, HttpServletResponse response) throws IOException {
		try {
			List<ResionModel> models = csRegionsService.getResion();
			Map<String,List<ResionModel>> map = new HashMap<String,List<ResionModel>>();
			map.put("citylist",models);
			RespHandler.respOK(map, response);
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respException(e.getErrorType(), e.getErrorTitle(), response);
		}
	}
	
	/**
	 * 
	 * @Description:根据主键ID获取详情
	 * @param dmId
	 * @param req
	 * @param response
	 * @throws IOException
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月12日 下午3:23:01
	 */
	@ApiOperation(value = "根据主键ID获取详情",notes = "根据主键ID获取详情",position = 0)
	@RequestMapping(value = "getInfoJson", method = RequestMethod.GET)
	public void getInfoJson(
			@ApiParam(value = "主键ID",required=true) @RequestParam Integer dmId
			,HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			CsRegions csRegions = csRegionsService.findVo(dmId, null);
			if (null == csRegions) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
                RespJsonPHandler.respOK(csRegions, response, request);
            }
		} catch (ServiceException e) {
			log.error(e);
			RespHandler.respException(e.getErrorType(), e.getErrorTitle(), response);
		}
	}

	
	
}
