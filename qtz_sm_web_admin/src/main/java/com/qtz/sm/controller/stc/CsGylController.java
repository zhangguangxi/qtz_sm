package com.qtz.sm.controller.stc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.ProcessingCheckExceptionUtil;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.supp.service.CsGysInfoService;
import com.qtz.sm.supp.vo.CsGysInfo;

/**
 * Title:(供应链公司控制类)<br/>
 * Description:(供应链公司控制类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016年4月26日
 */
//@Controller
//@RequestMapping(value = "/csgyl/")
public class CsGylController extends BaseController {

    private static final LogTool log = LogTool.getInstance(CsGylController.class);

    @Resource(name = "csGysInfoServiceImpl")
    private CsGysInfoService csGysInfoService;

    /**
     * 由省市ID获取供应商编号
     *
     * @param provinceId
     * @param cityId
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "getIdentifier")
    public void getIdentifier(@RequestParam Integer provinceId, @RequestParam Integer cityId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Map<String, Object> resMap = new HashMap<String, Object>();
            String result = csGysInfoService.findNextIdentifier(provinceId, cityId);
            resMap.put("prefix", result.substring(0, 6));
            resMap.put("endfix", result.substring(6));
            resMap.put("result", result);
            RespJsonPHandler.respOK(resMap, response, request);
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }

    /**
     * 新增供应商
     *
     * @param vo
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "addSupplier")
    public void addSupplier(@Valid CsGysInfo vo, Integer provinceId, Integer cityI, BindingResult result,
                            HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (result.hasErrors()) {
                RespJsonPHandler.respInputError(ExceptionConstants.ERRORCODE_NEGATIVE1,
                        ProcessingCheckExceptionUtil.abnormalConversion(result.getFieldErrors()), response, request);
            } else {
                String identifier = csGysInfoService.findNextIdentifier(provinceId, cityI);
                vo.setIdentifier(identifier);
//                csGysInfoService.addSuppAndInit(vo);
                RespJsonPHandler.respOK(identifier, response, request);
            }
        } catch (ServiceException e) {
            log.error(e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }

}
