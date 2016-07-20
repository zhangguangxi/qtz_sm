package com.qtz.sm.shop.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.shop.vo.ShopBusiness;
import com.qtz.sm.shop.vo.ShopBusinessVo;

/**
 * <p>Title:ShopBusinessService</p>
 * <p>Description:便利店营业信息服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
public interface ShopBusinessService extends BaseService<com.qtz.sm.shop.vo.ShopBusiness, Long> {

    /**
     * 查询便利店营业信息
     *
     * @param shopId 便利店ID
     * @return 便利店营业信息对象
     * @throws ServiceException
     */
    ShopBusiness findVoByShopId(Long shopId) throws ServiceException;

    /**
     * 根据便利店ID查询便利店详细信息（含店铺地址，营业时间，联系电话）
     * @Description:TODO
     * @param shopId
     * @return
     * @throws ServiceException
     * ShopBusiness
     * @exception:
     * @author: SunXuan
     * @time:2016年5月23日 下午4:18:58
     */
    ShopBusinessVo getVoShopId(Long shopId) throws ServiceException;
}