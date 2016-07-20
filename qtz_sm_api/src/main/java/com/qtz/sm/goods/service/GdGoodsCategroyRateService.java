package com.qtz.sm.goods.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.model.GdGoodsCategroyRateBo;
import com.qtz.sm.goods.model.GdGoodsCategroyRateUpdateBo;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;

/**
 * Title:GdGoodsCategroyRateService
 * Description:商品SKU溢价率服务接口类
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @author 欧江波 meoujb@163.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsCategroyRateService extends BaseService<GdGoodsCategroyRate, java.lang.Long> {

	void updateList(String dmIds, String rates, int sourceType) throws ServiceException;
	
	/**
	 * 获取分类议价列表(包含所有子分类议价信息),各公司角色只能获取自己角色议价信息
	 * @author	欧江波 meoujb@163.com
	 * @param goodsTypeId 商品分类ID,为NULL获取所有分类议价信息
	 * @param rateType 议价类型，参考CompanyType枚举定义
	 * @return
	 * @throws ServiceException
	 */
	List<GdGoodsCategroyRateBo> findTree(Long goodsTypeId, Integer rateType) throws ServiceException;
	
	/**
	 * 批量更新分类溢价信息
	 * @param categoryRateList	分类议价树
	 * @param companyType 公司类型，参考Company定义
	 * @throws ServiceException
	 */
	void updateTree(List<GdGoodsCategroyRateUpdateBo> categoryRateList, Integer companyType) throws ServiceException;

	/**
	 * 获取parentGoodsTypeId下直接分类议价列表（包含分类名称和父分类ID）,各公司角色只能获取自己角色议价信息
	 * @author	欧江波 meoujb@163.com
	 * @goodsTypeId 商品分类ID,为NULL获取所有分类议价信息
	 * @param rateType 议价类型，参考CompanyType枚举定义
	 * @return
	 * @throws ServiceException
	 */
	List<GdGoodsCategroyRateBo> getRatesWithCategoryInfo(Long parentGoodsTypeId, Integer rateType) throws ServiceException;
	
	/**
	 * 获取商品分类溢价信息
	 * @author 欧江波 meoujb@163.com
	 * @param goodsTypeId	商品分类ID
	 * @return
	 * @throws ServiceException
	 */
	GdGoodsCategroyRate getCategoryRate(Long goodsTypeId) throws ServiceException;

}