package com.qtz.sm.store.dao;

import java.util.List;
import java.util.Map;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.store.vo.CsCczxStock;
import com.qtz.sm.supermarket.vo.SupermarketSkuStatus;

/**
 * Title:CsCczxStockDao<br/>
 * Description:(仓储中心商品库存DAO接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-25
 */
public interface CsCczxStockDao extends BizDao<CsCczxStock,Long>{
	
	CsCczxStock queryCsCczxStockInfo(Long skuId)throws DaoException ;
	
	/**
	 * 云仓储管理公司    商品管理    商品库     商品详情   商品规格
	 * @param goodsId
	 * @return
	 * @throws DaoException
	 */
	List<CsCczxStock> queryStockQuantityAndPrice(Long goodsId)throws DaoException ;

	/**
	 * 根据仓储中心ID和SKUID获取对应仓储中心SKU的库存信息
	 * @param skuId
	 * @param cczxId
	 * @return
	 * @throws DaoException
	 */
	CsCczxStock getStockInfo(Map<String, String> param)throws DaoException ;

	/**
	 * 根据仓储中心ID和SKUID获取商品库存信息
	 * @param cczxId 仓储中心ID
	 * @param skuIds skuId字符串
	 * @throws DaoException
	 */
	List<SupermarketSkuStatus> getGoodsStock(Map<String, String> param)throws DaoException ;

}
