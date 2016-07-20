package com.qtz.sm.goods.service;
import java.util.List;
import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.goods.model.GdGoodsOperationHistoryListJson;
public interface GdGoodsOperationHistoryService extends BaseService<com.qtz.sm.goods.vo.GdGoodsOperationHistory,java.lang.Long> {
	
	/**
	 * 获取操作记录
	 * @param goodsId		商品ID
	 * @param oprators		操作人ID列表
	 * @param oprators		操作人map，键为员工ID，值为员工姓名
	 * @param oprators		职位map，，键为员工ID，值为职位
	 * @param pageIndex		页码
	 * @param pageSize		页大小
	 * @return
	 * @throws ServiceException
	 */
	public GdGoodsOperationHistoryListJson getGoodsOpretorHistory(Long goodsId, List<Long> oprators, Map<Long, String> userMap,  Map<Long, String> positionMap, 
			Integer pageIndex, Integer pageSize) throws ServiceException;
}