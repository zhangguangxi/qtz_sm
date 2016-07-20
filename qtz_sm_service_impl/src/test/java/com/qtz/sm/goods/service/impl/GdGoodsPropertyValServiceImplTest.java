package com.qtz.sm.goods.service.impl;

import java.util.List;
import java.util.Map;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.common.Constants;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.service.GdGoodsPropertyValService;

public class GdGoodsPropertyValServiceImplTest extends BaseTest {

	private static LogTool log = LogTool.getInstance(GdGoodsPropertyValServiceImplTest.class);

	@Autowired
	private GdGoodsPropertyValService propValService;
	
	@Test
	public void testGetGoodsPropBoList() {
		Long goodsTypeId = 1L;
		Long goodsId = 1L;
		try {
			List<GdGoodsPropertyBo> list = propValService.getGoodsPropBoList(goodsTypeId, goodsId);
			log.info("============================================");
			System.out.println(Constants.ACTI_N);
			log.info(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetGoodsPropBoMap() {
		Long goodsTypeId = 1L;
		Long goodsId = 1L;
		try {
			Map<Long, List<GdGoodsPropertyBo>> map = propValService.getGoodsPropBoMap(goodsTypeId, goodsId);
			log.info("============================================");
			log.info(map);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
