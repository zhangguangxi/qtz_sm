package com.qtz.sm.batch.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.batch.vo.CsBatOrder;

/**
 * Title:CsBatOrderService<br/>
 * Description:(批发单SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-21
 */
public interface CsBatOrderService extends BaseService<CsBatOrder,Long>{

	/**
	 * 添加仓储中心批发单
	 * @author yangwei
	 * @param csBatOrder		仓储中心批发单信息
	 * @throws ServiceException
	 */
	void addOrder(CsBatOrder csBatOrder)throws ServiceException;

	/**
	 * 获取批发单详情信息
	 * @author yangwei
	 * @param csBatOrder		批发单信息
	 * @throws ServiceException
	 */
	CsBatOrder getOrderDetail(Long dmId)throws ServiceException;

	/**
	 * 修改批发店状态信息
	 * @author yangwei
	 * @param csBatOrder		批发单信息
	 * @throws ServiceException
	 */
	void updateBatOrder(CsBatOrder csBatOrder)throws ServiceException;

}