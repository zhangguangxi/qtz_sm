package com.qtz.sm.goods.service.impl;

import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;

public class GdGoodsTypePropertyServiceTest extends BaseTest {

	private static LogTool log = LogTool.getInstance(GdGoodsTypePropertyServiceTest.class);

	@Autowired
	private GdGoodsTypePropertyService gdTypePropService;

	@Test
	public void testGetGoodsTypePropList() {
		Long goodsTypeId = 10L;
		try {
			List<GdGoodsTypeProperty> list = gdTypePropService.getGoodsTypePropList(goodsTypeId, true, false, false, true, true);
			 log.info("============================================");
			log.info(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
