package com.qtz.sm.shopManage.dao;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;

/**
 * <p>Title:ShopManageDao</p>
 * <p>Description:便利店管理公司DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选 
 * @version v1.0 2016-06-12
 */
public interface ShopManageDao extends BizDao<com.qtz.sm.shopManage.vo.ShopManage, Long> {

    /**
     * 通过便利店管理公司编码前缀查找相同前缀的最大编码
     *
     * @param prefix 便利店管理公司编码前缀
     * @return 最大编码
     * @throws DaoException
     */
    String findMaxCode(String prefix) throws DaoException;

}