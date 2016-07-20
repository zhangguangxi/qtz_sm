package com.qtz.sm.goods.service.impl;

import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.goods.model.GdGoodsCategroyRateBo;
import com.qtz.sm.goods.service.GdGoodsCategroyRateService;

public class GdGoodsCategroyRateServiceImplTest extends BaseTest {

	@Autowired
	GdGoodsCategroyRateService categoryRateService;
	
	private static LogTool log = LogTool.getInstance(GdGoodsCategroyRateServiceImplTest.class);
	
	@Test
	public void testUpdateList() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetAllRatesWithCategoryInfo() {
		try {
			Long goodsTypeId = 0L;
			List<GdGoodsCategroyRateBo> rates = categoryRateService.getRatesWithCategoryInfo(null, CompanyType.CloudStorage.value());
			log.info("==========================="+rates);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFindTree() {
		try {
			Long goodsTypeId = 0L;
			List<GdGoodsCategroyRateBo> rates = categoryRateService.findTree(goodsTypeId, CompanyType.CloudStorage.value());
			log.info("==========================="+rates);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
