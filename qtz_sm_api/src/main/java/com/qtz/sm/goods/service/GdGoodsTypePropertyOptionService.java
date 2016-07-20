package com.qtz.sm.goods.service;

import java.util.List;
import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;

/**
 * <p>
 * Title:GdGoodsTypePropertyOptionService
 * </p>
 * <p>
 * Description:商品分类属性值选项服务接口类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsTypePropertyOptionService extends BaseService<GdGoodsTypePropertyOption, java.lang.Long> {
    /**
     * 【获取商品分类属性可选值列表】（某属性下所有可选属性值）
     * 
     * @author 欧江波 928482427@qq.com
     * @param goodsTypePropId 商品分类属性ID
     * @param onlyEnabled 是否可用
     * @return 商品分类属性可选值列表
     * @throws ServiceException
     */
    public List<GdGoodsTypePropertyOption> getPropOptionList(Long goodsTypePropId, boolean onlyEnabled) throws ServiceException;
    
    /**
     * 【获取属性选项值Map】,键为商品分类属性值ID，值为商品分类属性值对象（某属性下所有可选属性值）
     * @param goodsTypePropId 商品分类属性ID
     * @param onlyEnabled 是否可用
     * @return
     * @throws ServiceException
     */
    public Map<Long, GdGoodsTypePropertyOption> getPropOptionMap(Long goodsTypePropId, boolean onlyEnabled) throws ServiceException;
    
    /**
     * 【获取商品分类属性可选值列表】（某分类下所有可选属性值）
     * 
     * @author 欧江波 928482427@qq.com
     * @param goodsTypeId 商品分类ID
     * @return 商品分类属性可选值列表
     * @throws ServiceException
     */
    public List<GdGoodsTypePropertyOption> getPropOptionsByGoodsType(Long goodsTypeId) throws ServiceException;
    
    /**
     * 【获取属性选项值Map】,键为商品分类属性值ID，值为商品分类属性值对象（某分类下所有可选属性值）
     * @param goodsTypeId 商品分类ID
     * @return
     * @throws ServiceException
     */
    public Map<Long, GdGoodsTypePropertyOption> getPropOptionMapByGoodsType(Long goodsTypeId) throws ServiceException;
    
}