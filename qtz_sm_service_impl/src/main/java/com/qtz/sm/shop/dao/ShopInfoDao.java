package com.qtz.sm.shop.dao;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;

/**
 * <p>Title:ShopInfoDao</p>
 * <p>Description:便利店基本信息DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
public interface ShopInfoDao extends BizDao<com.qtz.sm.shop.vo.ShopInfo, Long> {

    /**
     * 通过便利店编码前缀查找相同前缀的最大编码
     *
     * @param prefix 便利店编码前缀
     * @return 最大编码
     * @throws DaoException
     */
    String findMaxCode(String prefix) throws DaoException;

}