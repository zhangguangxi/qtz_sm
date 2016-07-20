package com.qtz.sm.goods.service;
import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.model.GdGoodsBrandsBo;
import com.qtz.sm.goods.vo.GdGoodsBrands;
/**
 * <p>Title:GdGoodsBrandsService</p>
 * <p>Description:品牌系列服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsBrandsService extends BaseService<GdGoodsBrands,java.lang.Long> {
	
	/**
	 * 添加品牌
	 * @param supplierId		供应商ID
	 * @param brandsName		品牌名称
	 * @throws ServiceException
	 */
	public void addBrands(Long supplierId, String brandsName) throws ServiceException;
	
	/**
	 * 编辑品牌
	 * @param supplierId    供应商ID
	 * @param brandsList	品牌列表
	 * @throws ServiceException
	 */
	public void modBrandsList(Long supplierId, List<GdGoodsBrandsBo> brandsList) throws ServiceException;
	
	/**
	 * 获取品牌列表
	 * @param supplierId	供应商ID
	 * @return
	 * @throws ServiceException
	 */
	public List<GdGoodsBrands> getBrandsList(Long supplierId) throws ServiceException;
}