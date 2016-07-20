package com.qtz.sm.goods.service.impl;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.enums.GoodsStatusEnum;
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.model.GdGoodsSkuFilter;
import com.qtz.sm.goods.page.GdGoodsPage;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.vo.GdGoods;

public class GdGoodsServiceImplTest extends BaseTest {

	@Autowired
	private GdGoodsService service;
	
	private static LogTool log = LogTool.getInstance(GdGoodsServiceImplTest.class);
	
	@Test
	public void testGetGoodsInfo() {
		Long goodsId = 1L;
		Map<Long, Double> priceMap = new HashMap<Long, Double>();
		priceMap.put(1L, 25.3);
		Map<Long, Integer> stockMap = new HashMap<>();
		stockMap.put(1L, 20);
		boolean withPropConfig = true;
		GdGoods extraInfo = new GdGoods();
		extraInfo.setName("1111");
		try {
			GdGoodsSkuFilter filter = new GdGoodsSkuFilter() {
				final Map<Long, String> map = new HashMap<>();
				@Override
				public boolean accept(Long skuId) {
					if (map.containsKey(skuId)){
						return true;
					}
					return false;
				}
			};
			GdGoodsOutJson outJson = service.getGoodsDetail(goodsId, priceMap, stockMap, withPropConfig, true, extraInfo, filter);
			log.info("获取并且序列化成JSON=====================================================");
			SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, 
					SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullBooleanAsFalse};  
			String data = JSON.toJSONString(outJson, features);
			log.info(data);
			
			log.info("反序列化成对象=====================================================");
			GdGoodsOutJson goodsBean = JSON.parseObject(data, GdGoodsOutJson.class);
			
			JSONObject jsonObject = JSON.parseObject(data);
			JSONObject goodsObj = jsonObject.getJSONObject("goods");
			Integer goodsIdParsed = goodsObj.getInteger("goodsId");
			//GdGoodsOutJson.GoodsJson goodsBean1 =JSON.parseObject(data, GdGoodsOutJson.GoodsJson.class);
			log.info(goodsObj);
			log.info(goodsIdParsed);
			
			//TODO:goods反序列化失败，手动处理
			/*
			JSONObject jsonObject = JSON.parseObject(goodsJson);
			JSONObject goodsObj = jsonObject.getJSONObject("goods");
			Long goodsId = goodsObj.getLong("goodsId");
			String goodsName = goodsObj.getString("goodsName");
			String goodsCode = goodsObj.getString("goodsCode");
			Long brandsId = goodsObj.getLong("brandsId");
			String brandsName = goodsObj.getString("brandsName");
			Long goodsTypeId = goodsObj.getLong("goodsTypeId");
			String goodsTypeName  = goodsObj.getString("goodsTypeName");
			String desc = goodsObj.getString("desc");
			Integer goodsStatus = goodsObj.getInteger("goodsStatus");
			String goodsStausDesc = goodsObj.getString("goodsStausDesc");
			goodsOut.addGoodsJson(goodsId, goodsName, goodsCode, brandsId, goodsTypeId, desc, goodsStatus, goodsStausDesc, brandsName, goodsTypeName);
			*/
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testAddGoods() {
		
	}

	@Test
	public void testUpdateGoodsStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryGoodsPage() {
		GdGoodsPage pageQuery = null;
		GoodsStatusEnum goodsStatusEnum = GoodsStatusEnum.all;
		Long shelveStart = 110L;
		Long shelveEnd = 2223332324424242L;
		String searchKeyWord = "男";
		String mysqlCondition = " and 1";
//		GdGoodsQueryResultFilter filter = new GdGoodsQueryResultFilter() {
//			
//			@Override
//			public boolean doFilter(GdGoods goods) throws ServiceException {
//				if(goods.getName().contains("XXX")){
//					return false;
//				}
//				return true;
//			}
//		};
		/*
		try {
			GdGoodsPage resultPage = service.queryGoodsPage(pageQuery, goodsStatusEnum, shelveStart, shelveEnd, searchKeyWord, mysqlCondition, filter);
			log.info("=====================================================");
			Object result = com.alibaba.fastjson.JSON.toJSON(resultPage);
			log.info(result);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		*/
		
	}


	@Test
	public void testQueryGoodsListJsonPage() {
		GdGoodsPage pageQuery = null;
		GoodsStatusEnum goodsStatusEnum = GoodsStatusEnum.all;
		Long shelveStart = 110L;
		Long shelveEnd = 2223332324424242L;
		String searchKeyWord = "男";
		String mysqlCondition = " and 1";
//		GdGoodsQueryResultFilter filter = new GdGoodsQueryResultFilter() {
//			
//			@Override
//			public boolean doFilter(GdGoods goods) throws ServiceException {
//				if(goods.getName().contains("XXX")){
//					return false;
//				}
//				return true;
//			}
//		};
		/*
		try {
			GdGoodsPage resultGoodsPage = service.queryGoodsPage(pageQuery, goodsStatusEnum, shelveStart, shelveEnd, searchKeyWord, mysqlCondition, filter);
			String[] headers = new String[]{"商品编号，商品名称，商品SKU编号"};
			Map<Long, Double[]> priceMap = new HashMap<Long, Double[]>();
			priceMap.put(1L, new Double[]{25d, 0.1, 26.5});
			Map<Long, Integer[]> stockMap = new HashMap<>();
			stockMap.put(1L, new Integer[]{20,11});
			GdGoodsListJsonPage jsonPage = service.queryGoodsListJsonPage(resultGoodsPage, headers, priceMap, stockMap);
			log.info("=====================================================");
			Object result = com.alibaba.fastjson.JSON.toJSON(jsonPage);
			log.info(result);
			log.info("==================");
		} catch (ServiceException e) {
			e.printStackTrace();
		}*/
	}

}
