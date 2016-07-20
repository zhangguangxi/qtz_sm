package com.qtz.sm.store.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.store.vo.CsCczxAddress;

/**
 * Title:CsCczxAddressService<br/>
 * Description:(仓储中心地址SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author gyl
 * @version v1.0 2016-04-20
 */
public interface CsCczxAddressService extends BaseService<CsCczxAddress,Long>{

    /**
     * 查找供应商地址
     *
     * @param suppId
     * @return
     * @throws ServiceException
     */
    CsCczxAddress findOnlyAddress(Long suppId) throws ServiceException;


    /**
     * 删除供应商所有地址
     *
     * @param suppId
     * @throws ServiceException
     */
    void delAllAddress(Long suppId) throws ServiceException;
    
    /**
     * 根据cczxId查询实体对象
     * @param cczxId
     * @return
     * @throws ServiceException
     */
    CsCczxAddress findVoByCczxId(Long cczxId) throws ServiceException;
}