package com.qtz.sm.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.model.GdGoodsSkuPropBo;
import com.qtz.sm.goods.service.GdGoodsSkuService;
import com.qtz.sm.supp.service.CsGysStockService;
import com.qtz.sm.supp.vo.CsGysStock;

public class GdGoodsSkuServiceImplTest extends BaseTest {

	private static LogTool log = LogTool.getInstance(GdGoodsSkuServiceImplTest.class);

	@Autowired
	private GdGoodsSkuService skuService;
	@Autowired
	private CsGysStockService gysStockService;

	@Test
	public void testGetGoodsSkuPropBoList() {
		Long goodsId = 1L;
		Long goodsTypeId = 1L;
		try {
			List<GdGoodsSkuPropBo> list = skuService.getSkuPropBoList(goodsTypeId, goodsId);
			log.info("333333333333333333333333333333333");
			log.info(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetSkuStock(){
		Long skuId = 1684658909579264l;
		CsGysStock stockQuery = new CsGysStock();
		stockQuery.setDmId(skuId);
		try {
			List<CsGysStock> stockList = gysStockService.findList(stockQuery);
			log.info("44444444444444444444444444");
			log.info(stockList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testGetAllSkuPropBoList(){
		Long goodsTypeId = 1727160901822464L;
		Long goodsId = 1727178074793984L;
		try {
			List<List<GdGoodsPropertyBo>> propCombines = new ArrayList<List<GdGoodsPropertyBo>>();
			List<GdGoodsSkuPropBo> skuPropBoList = skuService.getAllSkuPropBoList(goodsTypeId, goodsId, propCombines);
			log.info("55555555555555555555555555555555555555555555");
			log.info(skuPropBoList);
			log.info(propCombines);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
