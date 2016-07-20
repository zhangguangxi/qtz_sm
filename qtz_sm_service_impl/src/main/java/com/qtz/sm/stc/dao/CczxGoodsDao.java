package com.qtz.sm.stc.dao;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.sm.stc.vo.CczxGoods;

public interface CczxGoodsDao extends BizDao<CczxGoods,Long>{
	
	public  Pager<CczxGoods, Long>  queryCczxGoodsPage(Pager<CczxGoods, Long> page) throws DaoException;

}
