package com.qtz.sm.supp.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.supp.vo.CsGysAddress;

/**
 * Title:CsGysAddressService<br/>
 * Description:(供应商地址SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGysAddressService extends BaseService<CsGysAddress, Long> {

    /**
     * 查找供应商地址
     *
     * @param suppId
     * @return
     * @throws ServiceException
     */
    CsGysAddress findOnlyAddress(Long suppId) throws ServiceException;


    /**
     * 删除供应商所有地址
     *
     * @param suppId
     * @throws ServiceException
     */
    void delAllAddress(Long suppId) throws ServiceException;

}