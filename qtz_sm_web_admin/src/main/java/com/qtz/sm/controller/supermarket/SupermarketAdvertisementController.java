package com.qtz.sm.controller.supermarket;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.mall.core.common.DateUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.supermarket.enums.DisableEnum;
import com.qtz.sm.supermarket.page.SupermarketAdvertisementPage;
import com.qtz.sm.supermarket.service.SupermarketAdvertisementService;
import com.qtz.sm.supermarket.vo.SupermarketAdvertisement;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * <p>
 * Title:AdvertisementController
 * </p>
 * <p>
 * Description:广告Controller类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 * 
 * @author 张光喜
 * @version v1.0 2016-05-25
 */

@Api(value = "/supermarket/advertisement", description = "超市广告管理")
@Controller("SupermarketAdvertisement")
@RequestMapping("/supermarket/advertisement")
public class SupermarketAdvertisementController extends BaseController {
    /** 初始化日志对象 */
    private final static Logger log = Logger.getLogger(SupermarketAdvertisementController.class);
    
    /**广告位服务*/
    @Resource(name = "supermarketAdvertisementServiceImpl")
    private SupermarketAdvertisementService advertisementService;
    
    
    /**
     * 
     * 新增广告位
     * @version  2016年6月1日上午9:17:12
     * @author   guangxi.zhang
     * @param    token               凭证
     * @param    advertisementJson   广告位信息JSON
     * @param    request
     * @param    response
     * @throws   IOException
     */
    @ApiOperation(value = "新增广告位", notes = "新增广告位", position = 0)
    @RequestMapping(value = "/addAdvertisement", method = RequestMethod.POST, consumes = "application/json")
    public void addAdvertisement(@ApiParam("token") @RequestHeader("token") String token,
            @ApiParam("广告位json") @RequestBody String advertisementJson, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            log.info("AdvertisementController>>>>>>addAdvertisement开始!,广告位JSON:" + advertisementJson);
            SupermarketAdvertisement vo = JSON.parseObject(advertisementJson, SupermarketAdvertisement.class);
            vo.setCreateTime(DateUtil.getCurrentDateTime());
            vo.setUpdateTime(DateUtil.getCurrentDateTime());
            advertisementService.addVo(vo);
            RespHandler.respOK(response);
            log.info("AdvertisementController>>>>>>addAdvertisement结束!");
        }
        catch (ServiceException e) {
            log.error("AdvertisementController>>>>>>addAdvertisement失败", e);
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    /**
     * 
     * 编辑广告位
     * @version 2016年6月1日上午9:19:29
     * @author  guangxi.zhang
     * @param   token               凭证
     * @param   advertisementJson   广告位信息
     * @param   request
     * @param   response
     * @throws  IOException
     */
    @ApiOperation(value = "编辑广告位", notes = "编辑广告位", position = 0)
    @RequestMapping(value = "/updateAdvertisement", method = RequestMethod.POST, consumes = "application/json")
    public void updateAdvertisement(@ApiParam("token") @RequestHeader("token") String token,
            @ApiParam("广告位json") @RequestBody String advertisementJson, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            log.info("AdvertisementController>>>>>>updateAdvertisement开始!广告位JSON:"+advertisementJson);
            SupermarketAdvertisement vo = JSON.parseObject(advertisementJson, SupermarketAdvertisement.class);
            vo.setUpdateTime(DateUtil.getCurrentDateTime());
            advertisementService.modVoNotNull(vo);
            RespHandler.respOK(response);
            log.info("AdvertisementController>>>>>>updateAdvertisement结束!");
        }
        catch (ServiceException e) {
            log.info("AdvertisementController>>>>>>updateAdvertisement失败!");
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    
    /**
     * 
     * 修改广告位状态
     * @version 2016年6月1日上午9:22:37
     * @author  guangxi.zhang    
     * @param   token                   凭证
     * @param   dmId                    广告位id
     * @param   request
     * @param   response
     * @throws  IOException
     */
    @ApiOperation(value = "修改广告位状态", notes = "修改广告位状态", position = 0)
    @RequestMapping(value = "/updateAdvertisementStatus", method = RequestMethod.POST)
    public void updateAdvertisementStatus(@ApiParam("token") @RequestHeader("token") String token,
            @ApiParam("广告位id") @RequestParam long dmId, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            log.info("AdvertisementController>>>>>>updateAdvertisementStatus开始!广告位id:"+dmId);
            SupermarketAdvertisement vo = advertisementService.findVo(dmId, null);
            if(vo == null){
                RespHandler.respError(RespMsg.Advertisement.advertisement_not_found, response);
                return;
            }
            Integer status = vo.getStatus();
            vo.setStatus(status==DisableEnum.enable.getValue()?DisableEnum.disable.getValue():DisableEnum.enable.getValue());
            vo.setUpdateTime(DateUtil.getCurrentDateTime());
            advertisementService.modVoNotNull(vo);
            RespHandler.respOK(response);
            log.info("AdvertisementController>>>>>>updateAdvertisementStatus结束!");
        }
        catch (ServiceException e) {
            log.info("AdvertisementController>>>>>>updateAdvertisementStatus失败!");
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    
    /**
     * 
     * 查询广告位详情
     * @version  2016年6月1日上午9:23:49
     * @author   guangxi.zhang
     * @param    token                凭证
     * @param    dmId                 广告位id
     * @param    request
     * @param    response
     * @throws   IOException
     */
    @ApiOperation(value = "广告位详情", notes = "广告位详情", position = 0)
    @RequestMapping(value = "/getAdvertisementById", method = RequestMethod.POST)
    public void getAdvertisementById(@ApiParam("token") @RequestHeader("token") String token,
            @ApiParam("dmId") @RequestParam long dmId, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        try {
            log.info("AdvertisementController>>>>>>getAdvertisementById开始!广告位id:"+dmId);
            SupermarketAdvertisement vo = advertisementService.findVo(dmId, null);
            if(vo == null){
                RespHandler.respError(RespMsg.Advertisement.advertisement_not_found, response);
                return;
            }
            RespHandler.respOK(vo,response);
            log.info("AdvertisementController>>>>>>updateAdvertisementStatus结束!");
        }
        catch (ServiceException e) {
            log.info("AdvertisementController>>>>>>updateAdvertisementStatus失败!");
            RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
        }
    }
    
    /**
     * 
     * 查询广告位列表
     * @version 2016年6月1日上午9:25:19
     * @author  guangxi.zhang
     * @param   token                凭证                 
     * @param   pageIndex            页码   
     * @param   pageSize             每页显示记录数
     * @param   request
     * @param   response
     * @throws  IOException
     */
    @RequestMapping(value="listAdvertisementByPage",method=RequestMethod.POST)
    public void listAdvertisementByPage(@ApiParam("token") @RequestHeader("token") String token,
                                        @RequestParam(defaultValue="1")Integer pageIndex,
                                        @RequestParam(defaultValue="20")Integer pageSize,
                                        HttpServletRequest request,
                                        HttpServletResponse response)throws IOException{
            try {
                    log.info("AdvertisementController>>>>>>listAdvertisementByPage开始!");
                    SupermarketAdvertisementPage page = new SupermarketAdvertisementPage();
                     page.setNowPage(pageIndex);
                     page.setPageSize(pageSize);
                     page.setOrderField("sort");
                     page.setOrderDirection(false);
                     Pager<SupermarketAdvertisement, Long> pager = advertisementService.query(page, SupermarketAdvertisement.class);
                     RespJsonPHandler.respOK(pager, response, request);
                    log.info("AdvertisementController>>>>>>listAdvertisementByPage结束!");
            }
            catch (ServiceException e) {
                log.error("AdvertisementController>>>>>>listAdvertisementByPage失败!",e);
                RespJsonPHandler.respError(e.getErrorType(), e.getErrorTitle(), response, request);
            }
    
    }
        
}