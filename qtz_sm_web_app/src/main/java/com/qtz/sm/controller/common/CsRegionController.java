package com.qtz.sm.controller.common;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mall.core.common.response.RespHandler;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.controller.BaseController;

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

}
