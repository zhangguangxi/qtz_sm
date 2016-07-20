package com.qtz.sm.stc.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.stc.vo.CczxGoods;

/**
 * 云仓储管理公司   商品管理   商品库
 * @author Administrator
 *
 */
public interface CczxGoodsService extends BaseService<CczxGoods,Long>{
	
	 Pager<CczxGoods, Long> queryCczxGoodsPage( Pager<CczxGoods, Long> page)throws ServiceException;

}
