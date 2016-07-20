package com.qtz.sm.goods.service.impl;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.goods.service.GdGoodsSkuRateService;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;

public class GdGoodsSkuRateServiceImplTest extends BaseTest {
	
	private static LogTool log = LogTool.getInstance(GdGoodsSkuRateServiceImplTest.class);

	@Autowired
	private GdGoodsSkuRateService skuRateService;
	
	@Test
	public void testGetGoodsSkuRateList() {
		Long goodsId = 1717263765440512L;
		Long skuId = 1717263830943744L;
		try {
			GdGoodsSkuRate list = skuRateService.getGoodsSkuRate(goodsId, skuId);
			log.info("============================================");
			log.info(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testModifyPrice() {
		Long goodsId = 1717263765440512L;
		Long skuId = 1717263830943744L;
		Double newPrice = new Double(250);
		Integer companyType = CompanyType.StoreManager.value();
		String ip = "";
		Long operator = 1L;
		try {
			skuRateService.modifyPrice(goodsId, skuId, newPrice, companyType, ip, operator);
			log.info("============================================");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
