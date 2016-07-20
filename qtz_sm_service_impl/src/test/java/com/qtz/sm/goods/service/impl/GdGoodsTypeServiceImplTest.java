package com.qtz.sm.goods.service.impl;

import static org.junit.Assert.fail;

import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.service.GdGoodsTypeService;
import com.qtz.sm.goods.vo.GdGoodsType;

public class GdGoodsTypeServiceImplTest extends BaseTest {
	
	@Autowired
	private GdGoodsTypeService service;
	
	private static LogTool log = LogTool.getInstance(GdGoodsServiceImplTest.class);
	
	@Test
	public void testGetGoodsTypeStrs() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetParentGoodsTypeList() {
		Long goodsTypeId = 10L;
		try {
			List<GdGoodsType> list = service.getParentGoodsTypeList(goodsTypeId);
			log.info("============================================================");
			log.info(list);
		} catch (ServiceException e) {
			
		}
	}

}
