package com.qtz.sm.supp.dao;

import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.supp.vo.CsGysStock;

/**
 * Title:CsGysStockDao<br/>
 * Description:(供应商商品库存DAO接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGysStockDao extends BizDao<CsGysStock,Long>{
	
	
	CsGysStock queryCsGysStockInfo(Long skuId) throws DaoException ;
	
	/**
	 * 根据商品ID获取供应商商品库存价格列表
	 * @author 欧江波 928482427@qq.com
	 * @param goodsId	商品ID
	 * @return
	 * @throws DaoException
	 */
	List<CsGysStock> getStocksByGoodsId(Long goodsId) throws DaoException;
	
	
	List<CsGysStock> queryFindList(CsGysStock csGysStock) throws DaoException;
}
