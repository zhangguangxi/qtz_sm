package com.qtz.sm.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.model.GdGoodsOperationHistoryListJson;
import com.qtz.sm.goods.service.GdGoodsOperationHistoryService;

public class GdGoodsOperationHistoryServiceImplTest extends BaseTest {
	
	private static LogTool log = LogTool.getInstance(GdGoodsOperationHistoryServiceImplTest.class);

	@Autowired
	private GdGoodsOperationHistoryService service;
	@Test
	public void testGetGoodsOpretorHistory() {
		Long goodsId = 1L;
		List<Long> oprators = new ArrayList<Long>();
		oprators.add(1L);
		try {
			GdGoodsOperationHistoryListJson json = service.getGoodsOpretorHistory(goodsId, oprators, null, null, 1, 2);
			log.info("==========================="+json);
			log.info("==========================="+com.alibaba.fastjson.JSON.toJSONString(json));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}
