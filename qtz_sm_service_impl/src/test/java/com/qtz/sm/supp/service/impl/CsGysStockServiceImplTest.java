package com.qtz.sm.supp.service.impl;

import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.supp.service.CsGysStockService;
import com.qtz.sm.supp.vo.CsGysStock;

public class CsGysStockServiceImplTest extends BaseTest {

	private static LogTool log = LogTool.getInstance(CsGysStockServiceImplTest.class);

	@Autowired
	private CsGysStockService stockService;
	
	@Test
	public void testGetStocksByGoodsId() {
		Long goodsId = 1L;
		try {
			List<CsGysStock> list = stockService.getStocksByGoodsId(goodsId);
			log.info("============================================");
			log.info(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
