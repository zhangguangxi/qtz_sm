package com.qtz.sm.scm.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.scm.vo.CsGylGoodsVo;

public interface CsGylGoodsService  extends BaseService<CsGylGoodsVo,Long>{
	
	/**
	 * <!-- 供应链公司   商品管理   商品审核分页以及商品库分页      分页 -->
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	Pager<CsGylGoodsVo, Long>  queryCsGylGoodsPage(Pager<CsGylGoodsVo, Long> page) throws ServiceException;

}
