package com.qtz.sm.store.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.store.vo.CsCczxStock;
import com.qtz.sm.supermarket.vo.SupermarketSkuStatus;
import com.qtz.sm.store.dao.CsCczxStockDao;

/**
 * Title:CsCczxStockDaoImpl<br/>
 * Description:(仓储中心商品库存DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-25
 */
@Repository("csCczxStockDaoImpl")
public class CsCczxStockDaoImpl extends MyBaitsDaoImpl<CsCczxStock,Long> implements CsCczxStockDao{

    /**MYBatis命名空间名*/
    private static String preName = CsCczxStockDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

	@Override
	public CsCczxStock queryCsCczxStockInfo(Long skuId) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsCczxStockDao.class).queryCsCczxStockInfo(skuId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	/**
	 * 云仓储管理公司    商品管理    商品库     商品详情   商品规格
	 */
	@Override
	public List<CsCczxStock> queryStockQuantityAndPrice(Long goodsId) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsCczxStockDao.class).queryStockQuantityAndPrice(goodsId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	@Override
	public CsCczxStock getStockInfo(Map<String, String> param) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(CsCczxStockDao.class).getStockInfo(param);
	}

	@Override
	public List<SupermarketSkuStatus> getGoodsStock(Map<String, String> param) throws DaoException {
		return getMyBaitsTemplate().getSqlSession().getMapper(CsCczxStockDao.class).getGoodsStock(param);
	}

}

