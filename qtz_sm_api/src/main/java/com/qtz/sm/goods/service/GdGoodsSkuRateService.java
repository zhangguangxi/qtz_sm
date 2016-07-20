package com.qtz.sm.goods.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.model.GdGoodsSkuRateExtOut;
import com.qtz.sm.goods.model.GdGoodsStock;
import com.qtz.sm.goods.page.GdGoodsSkuRatePage;
import com.qtz.sm.goods.page.GdGoodsStockPage;
import com.qtz.sm.goods.vo.GdGoodsOperationHistory;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;

/**
 * <p>Title:GdGoodsSkuRateService</p>
 * <p>Description:商品SKU议价服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-19
 */
public interface GdGoodsSkuRateService extends BaseService<GdGoodsSkuRate, java.lang.Long> {

	/**
	 * 查询溢价列表
	 * 
	 * @param page
	 *            溢价分页对象
	 * @return
	 * @throws ServiceException
	 */
	Pager<GdGoodsSkuRateExtOut, Long> queryRates(GdGoodsSkuRatePage page) throws ServiceException;

	/**
	 * 商品
	 * @param page
	 * @param class1
	 * @return
	 */
	Pager<GdGoodsStock, Long> listGoodsStock(GdGoodsStockPage page) throws ServiceException;
	
	/***
	 * 获取商品指定SKU编号的溢价信息，如果商品SKU溢价表中没有设置溢价信息，使用商品分类的溢价信息
	 * @author 欧江波 928482427@qq.com
	 * @param skuId		SKU编号
	 * @param goodsId	商品ID
	 * @return
	 * @throws ServiceException
	 */
	GdGoodsSkuRate getGoodsSkuRate(Long goodsId, Long skuId) throws ServiceException;
	
	/**
	 * 修改价格
	 * @author 欧江波 928482427@qq.com
	 * @param goodsId			商品ID
	 * @param skuId				skuID
	 * @param newPrice			新价格
	 * @param companyType		公司类型，参考CompanyType枚举类
	 * @param ip 				操作人IP
	 * @param operator			操作人ID
	 * @throws ServiceException
	 */
	void modifyPrice(Long goodsId, Long skuId, Double newPrice, Integer companyType, String ip, Long operator) throws ServiceException;
	
	/**
	 * 商品管理-商品库-议价 并添加操作记录
	 * @param gdGoodsSkuRate
	 * @param gdGoodsOperationHistory
	 * @throws ServiceException
	 * @author: SunXuan
	 * @time:2016年6月16日 下午5:44:01
	 */
	void updateGoodsSkuRate(GdGoodsSkuRate gdGoodsSkuRate,GdGoodsOperationHistory gdGoodsOperationHistory) throws ServiceException;

}