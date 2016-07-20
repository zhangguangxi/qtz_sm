package com.qtz.sm.supp.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.supp.vo.CsGysDeliveryRegion;

import java.util.List;

/**
 * Title:CsGysDeliveryRegionService<br/>
 * Description:(供应商供货区域SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGysDeliveryRegionService extends BaseService<CsGysDeliveryRegion,Long>{

    /**删除所有限制区域
     * @param suppId
     * @throws ServiceException
     */
    void delAllDeliveryRegion(Long suppId) throws ServiceException;

    /** 添加限制区域列表
     * @param suppId
     * @param voList
     * @throws ServiceException
     */
    void addDeliveryRegionList(Long suppId, List<CsGysDeliveryRegion> voList) throws ServiceException;

}