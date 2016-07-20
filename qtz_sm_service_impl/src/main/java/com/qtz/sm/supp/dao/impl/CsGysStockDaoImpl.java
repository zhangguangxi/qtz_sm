package com.qtz.sm.supp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.supp.dao.CsGysStockDao;
import com.qtz.sm.supp.vo.CsGysStock;

/**
 * Title:CsGysStockDaoImpl<br/>
 * Description:(供应商商品库存DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGysStockDaoImpl")
public class CsGysStockDaoImpl extends MyBaitsDaoImpl<CsGysStock,Long> implements CsGysStockDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGysStockDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

	@Override
	public CsGysStock queryCsGysStockInfo(Long skuId) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsGysStockDao.class).queryCsGysStockInfo(skuId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
	
	/**
	 * 根据商品ID获取供应商商品库存价格列表
	 * @author 欧江波 928482427@qq.com
	 * @param goodsId	商品ID
	 * @return
	 * @throws DaoException
	 */
	@Override
	public List<CsGysStock> getStocksByGoodsId(Long goodsId) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".getStocksByGoodsId", goodsId);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".getStocksByGoodsId(" + goodsId + ")调用【报错】了！", e);
		}
	}

	@Override
	public List<CsGysStock> queryFindList(CsGysStock csGysStock) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().selectList(preName+".queryFindList", csGysStock);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".queryFindList(" + csGysStock + ")调用【报错】了！", e);
		}
	}
    

}

