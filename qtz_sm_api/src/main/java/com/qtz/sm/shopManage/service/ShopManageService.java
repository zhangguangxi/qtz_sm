package com.qtz.sm.shopManage.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.shopManage.page.ShopMAndSuperMKGoodsPage;
import com.qtz.sm.shopManage.vo.ShopManage;
import com.qtz.sm.shopManage.vo.ShopMAndSuperMKGoods;
import com.qtz.sm.shopManage.vo.ShopManageVo;

/**
 * <p>Title:ShopManageService</p>
 * <p>Description:便利店管理公司服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选
 * @version v1.0 2016-05-26
 */
public interface ShopManageService extends BaseService<com.qtz.sm.shopManage.vo.ShopManage, Long> {

    /**
     * 通过便利店管理公司ID查询便利店管理公司详细信息
     *
     * @param id 便利店管理公司ID
     * @return 便利店管理公司信息
     * @throws ServiceException
     */
    ShopManageVo findById(Long id) throws ServiceException;

    /**
     * 添加一个便利店管理公司，同时创建一个便利店管理公司系统用户，和一个便利店管理公司钱包
     *
     * @param shopManage 便利店管理公司对象
     * @throws ServiceException
     */
    void addVoStaffAddWallet(ShopManage shopManage) throws ServiceException;

    /**
     * 修改便利店管理公司信息
     * 1、当手机号码未修改时，则不更新用户信息
     * 2、当手机号码对应的用户不存在，则为它新建用户
     * 3、当修改了手机号码时，同时修改它所关联系统用户的手机号码
     *
     * @param shopManage 便利店管理公司信息
     * @throws ServiceException
     */
    void modVoAndStaff(ShopManage shopManage) throws ServiceException;

    /**
     * 通过省份，城市生成便利店管理公司编号
     *
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @return 便利店管理公司ID
     * @throws ServiceException
     */
    String createCode(Integer provinceId, Integer cityId) throws ServiceException;
    
    /**
     * 
     * @Description:分页查询便利店管理公司商品库 比较复杂
     * @param page
     * @return
     * @throws ServiceException
     * Pager<ShopManage,Long>
     * @exception:
     * @author: SunXuan
     * @time:2016年6月12日 上午11:36:01
     */
    public  Pager<ShopMAndSuperMKGoods, Long> queryGoodsOld(ShopMAndSuperMKGoodsPage page) throws ServiceException;
    
    /**
     * 
     * @Description:分页查询便利店管理公司商品库 比较简洁
     * @param page
     * @return
     * @throws ServiceException
     * Pager<ShopManage,Long>
     * @exception:
     * @author: SunXuan
     * @time:2016年6月28日 上午11:36:01
     */
    public  Pager<ShopMAndSuperMKGoods, Long> queryGoods(ShopMAndSuperMKGoodsPage page) throws ServiceException;
    
    /**
     * 查询商品详情
     * @Description:TODO
     * @param shopManageGoods
     * @return
     * @throws ServiceException
     * List<ShopManageGoods>
     * @exception:
     * @author: SunXuan
     * @time:2016年6月12日 下午3:42:08
     */
    public List<ShopMAndSuperMKGoods> getShopManageGoodsList (ShopMAndSuperMKGoods shopMAndSuperMKGoods) throws ServiceException;
    
}