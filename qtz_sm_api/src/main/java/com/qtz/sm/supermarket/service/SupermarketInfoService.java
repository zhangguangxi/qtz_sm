package com.qtz.sm.supermarket.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.supermarket.vo.SupermarketInfo;

/**
 * <p>Title:SupermarketInfoService</p>
 * <p>Description:超市基本信息服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
public interface SupermarketInfoService extends BaseService<com.qtz.sm.supermarket.vo.SupermarketInfo, Long> {

    /**
     * 添加超市时，同时为超市添加一个系统用户，和一个钱包
     *
     * @param supermarketInfo 超市信息
     * @throws ServiceException
     */
    void addVoStaffAddWallet(SupermarketInfo supermarketInfo) throws ServiceException;

    /**
     * 修改超市信息
     * 1、当手机号码未修改时，则不更新用户信息
     * 2、当手机号码对应的用户不存在，则为它新建用户
     * 3、当修改了手机号码时，同时修改它所关联系统用户的手机号码
     *
     * @param supermarketInfo 超市信息
     * @throws ServiceException
     */
    void modVoAndStaff(SupermarketInfo supermarketInfo) throws ServiceException;
}