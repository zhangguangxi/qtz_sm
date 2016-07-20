package com.qtz.sm.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.model.GdGoodsBrandsBo;
import com.qtz.sm.goods.service.GdGoodsBrandsService;
import com.qtz.sm.goods.vo.GdGoodsBrands;

public class GdGoodsBrandsServiceImplTest extends BaseTest {

	@Autowired
	GdGoodsBrandsService brandsService;
	private static LogTool log = LogTool.getInstance(GdGoodsBrandsServiceImplTest.class);

	@Test
	public void testAddBrands() {
		try {
			brandsService.addBrands(11L, "aaaa");
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testModBrandsList() {
		try {
			Long supplierId = 1706523788511232L;
			List<GdGoodsBrands> list = brandsService.getBrandsList(1706523788511232L);
			log.info("==========================="+list);
			List<GdGoodsBrandsBo> boList = new ArrayList<GdGoodsBrandsBo>();
			for (GdGoodsBrands brands:list) {
				GdGoodsBrandsBo bo = new GdGoodsBrandsBo(brands.getDmId(), brands.getCnName()+"aaa");
				boList.add(bo);
			}
			brandsService.modBrandsList(supplierId, boList);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetBrandsList() {
		try {
			List<GdGoodsBrands> list = brandsService.getBrandsList(1706523788511232L);
			log.info("==========================="+list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
