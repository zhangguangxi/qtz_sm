package com.qtz.sm.common.service;

import java.util.List;
import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.common.model.ResionModel;
import com.qtz.sm.common.vo.CsRegions;

/**
 * Title:CsRegionsService<br/>
 * Description:(行政区域SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-21
 */
public interface CsRegionsService extends BaseService<CsRegions, Integer> {

    /**
     * 根据省市ID获取编号前缀
     *
     * @param provinceId
     * @param cityId
     * @return
     * @throws ServiceException
     */
    String findPrefixCodeById(Integer provinceId, Integer cityId) throws ServiceException;

    /**
     * 根据省市县镇ID组成地址全称
     *
     * @param provinceId 省ID,如:广东(这个取简称,如广西壮族自治区返回广西)
     * @param cityId     市ID,如:深圳
     * @param countyId   县区ID,如:南山
     * @param townId     乡镇街道ID,如:南山街道
     * @param address    详细地址,如:大冲商务中心5号楼41层
     * @return 广东深圳市南山区南山街道大冲商务中心5号楼41层
     * @throws ServiceException
     */
    String getFullAddressByIds(Integer provinceId, Integer cityId, Integer countyId, Integer townId, String address) throws ServiceException;

    /**
     * 根据市县镇ID查找名称集合
     *
     * @param provinceId 省ID
     * @param cityId     市ID
     * @param countyId   县区ID
     * @param townId     乡镇ID
     * @return 地区名称集合
     * @throws ServiceException
     */
    Map<String, CsRegions> getAddressByIds(Integer provinceId, Integer cityId, Integer countyId, Integer townId) throws ServiceException;

    List<ResionModel> getResion()throws ServiceException;

}