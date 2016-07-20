package com.qtz.sm.goods.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.model.GdGoodsTypeOutJson;
import com.qtz.sm.goods.vo.GdGoodsType;

/**
 * <p>
 * Title:GdGoodsTypeService
 * </p>
 * <p>
 * Description:商品分类服务接口类
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
public interface GdGoodsTypeService extends BaseService<GdGoodsType, java.lang.Long> {

	/**
	 * 根据父分类获取直接商品分类
	 * 
	 * @param parentId  父分类ID
	 * @return
	 * @throws ServiceException
	 */
	List<GdGoodsType> listGoodsTypesByParentId(long parentId) throws ServiceException;

  /**
     * 添加商品分类
     * 
     * @param sid		用户id
     * @param goodsTypeJson	商品分类相关信息
     * @throws ServiceException
     */
    void addGoodsType(long uid, GdGoodsTypeOutJson goodsTypeOut) throws ServiceException;
    
    /**
     * 
     *  更新商品分类
     * @version 2016年5月26日上午9:58:27
     * @author guangxi.zhang
     * @param uid 用户id
     * @param gdGoodsTypeFront 前端上传的商品分类信息
     * @throws ServiceException
     */
    void updateGoodsType(long uid, GdGoodsType gdGoodsTypeFront) throws ServiceException;
    
	/**
	 * 根据层级获取商品分类
	 * 
	 * @param level 层级
	 * @return
	 * @throws ServiceException
	 */
	List<GdGoodsType> listGoodsTypesByLevel(int level) throws ServiceException;
	
	/**
	 * 获取商品分类字符串，格式类似于"10,11,12",  "“服装/衣服/男装”
	 * @return 	1.ID字符串，2.名称字符串
	 * @param goodsTypeId 商品分类ID
	 * @param splitStr 分割符
	 * @throws ServiceException
	 */
	public String[/**1.ID字符串，2.名称字符串*/] getGoodsTypeStrs(Long goodsTypeId, String splitStr) throws ServiceException;
	
	/**
	 * 获取当前商品分类ID的所有父节点
	 * @param goodsTypeId		商品分类ID
	 * @return
	 * @throws ServiceException
	 */
	public List<GdGoodsType> getParentGoodsTypeList(Long goodsTypeId) throws ServiceException;
	
	/**
	 * 获取当前商品分类ID的父节点
	 * @param goodsTypeId	当前分类ID
	 * @return
	 * @throws ServiceException
	 */
	public GdGoodsType getParentGoodsType(Long goodsTypeId) throws ServiceException;
	
}
