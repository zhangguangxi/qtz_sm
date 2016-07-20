package com.qtz.sm.shop.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.shop.vo.ShopInfo;
import com.qtz.sm.shop.vo.ShopInfoVo;

/**
 * <p>Title:ShopInfoService</p>
 * <p>Description:便利店基本信息服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
public interface ShopInfoService extends BaseService<com.qtz.sm.shop.vo.ShopInfo, Long> {

    /**
     * 通过便利店ID查询便利店详细信息
     *
     * @param id 便利店ID
     * @return 便利店详细信息
     * @throws ServiceException
     */
    ShopInfoVo findById(Long id) throws ServiceException;

    /**
     * 添加便利店，同时为便利店添加一个系统用户，和一个便利店钱包
     *
     * @param shopInfo 便利店对象
     * @throws ServiceException
     */
    void addVoStaffAddWallet(ShopInfo shopInfo) throws ServiceException;

    /**
     * 修改便利店信息
     * 1、当手机号码未修改时，则不更新用户信息
     * 2、当手机号码对应的用户不存在，则为它新建用户
     * 3、当修改了手机号码时，同时修改它所关联系统用户的手机号码
     *
     * @param shopInfo 便利店对象
     * @throws ServiceException
     */
    void modVoAndStaff(ShopInfo shopInfo,User user) throws ServiceException;

    /**
     * 通过省份，城市生成便利店编号
     *
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @return 便利店编号
     * @throws ServiceException
     */
    String createCode(Integer provinceId, Integer cityId) throws ServiceException;
    
    /**
     * 禁用启用便利店
     * @Description:TODO
     * @param shopInfo
     * @param user
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年6月17日 上午11:22:31
     */
    void updateStatus(ShopInfo shopInfo,User user) throws ServiceException;

}