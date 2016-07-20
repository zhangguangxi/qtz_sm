package com.qtz.sm.goods.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.FifteenLongId;
import com.mall.core.common.response.RespCode;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsDao;
import com.qtz.sm.goods.enums.GoodsStatusEnum;
import com.qtz.sm.goods.model.GdGoodsOutJson;
import com.qtz.sm.goods.model.GdGoodsOutJson.GdGoodsPropertyCustom;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.model.GdGoodsPropertyBoAdapter;
import com.qtz.sm.goods.model.GdGoodsSkuFilter;
import com.qtz.sm.goods.model.GdGoodsSkuPropBo;
import com.qtz.sm.goods.service.GdGoodsBrandsService;
import com.qtz.sm.goods.service.GdGoodsDescPictureService;
import com.qtz.sm.goods.service.GdGoodsPictureService;
import com.qtz.sm.goods.service.GdGoodsPropertyValService;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.service.GdGoodsSkuPropertyService;
import com.qtz.sm.goods.service.GdGoodsSkuRateService;
import com.qtz.sm.goods.service.GdGoodsSkuService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.service.GdGoodsTypeService;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.goods.vo.GdGoodsBrands;
import com.qtz.sm.goods.vo.GdGoodsDescPicture;
import com.qtz.sm.goods.vo.GdGoodsPicture;
import com.qtz.sm.goods.vo.GdGoodsPropertyVal;
import com.qtz.sm.goods.vo.GdGoodsSku;
import com.qtz.sm.goods.vo.GdGoodsSkuProperty;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
import com.qtz.sm.shop.vo.ShopValueVo;
import com.qtz.sm.supp.service.CsGysStockService;
import com.qtz.sm.supp.vo.CsGysStock;

import utils.StringUtils;

/**
 * Title:GdGoodsServiceImpl
 * Description:商品服务实现类
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @ReVersion 欧江波 928482427@qq.com 2016-05-23
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsServiceImpl")
public class GdGoodsServiceImpl extends BaseServiceImpl<GdGoods, java.lang.Long> implements GdGoodsService {
	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(GdGoodsServiceImpl.class); 
	/** 注入商品DAO接口类 */
	@Resource(name = "gdGoodsDaoImpl")
	private GdGoodsDao dao;

	@Override
	protected BizDao<GdGoods, java.lang.Long> getDao() {
		return dao;
	}
	@Override
	protected LogTool getLog() {
		return log;
	}

	@Autowired
	private GdGoodsTypePropertyService typePropService;
	@Autowired
	private GdGoodsPropertyValService propValService;
	@Autowired
	private GdGoodsSkuService skuService;
	@Autowired
	private GdGoodsSkuPropertyService skuPropService;
	@Autowired
	private FifteenLongId fifteenLongId;
	@Autowired
	private GdGoodsBrandsService brandsService;
	@Autowired
	private GdGoodsTypeService goodsTypeService;
	@Autowired
	private GdGoodsPictureService pictureService;
	@Autowired
	private CsGysStockService gysStockService;
	@Autowired
	private GdGoodsSkuRateService skuRateService;
	@Autowired
	private GdGoodsDescPictureService descPictureService;
	
	/**
	 * 获取商品详情
	 * @author 欧江波 928482427@qq.com
	 * @param goodsId 			商品ID
	 * @param skuPriceMap		SKU价格信息
	 * @param skuStockMap 		SKU库存信息
	 * @param withPropConfig 	是否包含属性配置信息
	 * @param withAllSku		是否包含所有的SKU，包括尚未添加到商品SKU表中的SKU属性值组合
	 * @param  skuFilter		sku过滤器，只返回通过检测的SKU
	 * @param extraInfo 		附加额外信息，需要实现序列号接口
	 * @throws ServiceException
	 */
	@Override
	public GdGoodsOutJson getGoodsDetail(Long goodsId, Map<Long, Double> skuPriceMap, Map<Long, Integer> skuStockMap, boolean withPropConfig, boolean withAllSku, Object extraInfo, GdGoodsSkuFilter skuFilter) throws ServiceException {
			if (extraInfo != null && !(extraInfo instanceof Serializable)){
				throw new ServiceException("参数extraInfo需要实现序列号接口");
			}
		
			//返回的商品JSON对象
			GdGoodsOutJson result = new GdGoodsOutJson();
			
			//1、获取商品基本信息
			GdGoods goods = this.findVo(goodsId, null);
			if (goods == null){
				return result; 
			}
			// 1_1 获取和商品分类名称、商品ID字符串
			//商品分类ID字符串
			String[] goodsTypeStrs = goodsTypeService.getGoodsTypeStrs(goods.getGoodsTypeId(), ",");
			String goodsTypeName = goodsTypeStrs[1];
			
			// 1_2 获取品牌名称
			String brandsName = "";
			GdGoodsBrands brands = brandsService.findVo(goods.getBrandsId(), null);
			if (brands != null){
				brandsName = brands.getCnName();
			}
			
			//2、获取商品基本属性
			List<GdGoodsPropertyBo> basicPropList = propValService.getGoodsPropBoList(goods.getGoodsTypeId(), goodsId);
			
			//3、获取商品SKU信息
			List<GdGoodsSkuPropBo> skuBoList = new ArrayList<GdGoodsSkuPropBo>();
			if (withAllSku) {
				skuBoList = skuService.getAllSkuPropBoList(goods.getGoodsTypeId(), goodsId, new ArrayList<List<GdGoodsPropertyBo>>());
			} else {
				skuBoList = skuService.getSkuPropBoList(goods.getGoodsTypeId(), goodsId);
			}
			
			//3.1 商品SKU过滤
			List<GdGoodsSkuPropBo> acceptSkuBoList = new ArrayList<GdGoodsSkuPropBo>();
			if (skuFilter == null) {
				acceptSkuBoList = skuBoList;
			} else {
				for (GdGoodsSkuPropBo skuPropBo:skuBoList) {
					boolean passed = skuFilter.accept(skuPropBo.getSkuId());
					if (passed){
						acceptSkuBoList.add(skuPropBo);
					}
				}
			}
			
			//4、获取商品图片
			GdGoodsPicture picQuery = new GdGoodsPicture();
			picQuery.setGoodsId(goodsId);
			List<GdGoodsPicture> picList = pictureService.findList(picQuery);
			
			//4.1、获取商品描述图片
			GdGoodsDescPicture descPicQuery = new GdGoodsDescPicture();
			descPicQuery.setGoodsId(goodsId);
			List<GdGoodsDescPicture> descPicList = descPictureService.findList(descPicQuery);
			
			//6、拼装成商品JSON对象
			String goodsStatusDesc = GoodsStatusEnum.getTextByValue(goods.getStatus());
			result.addGoodsJson(goodsId, goods.getName(), goods.getCode(), goods.getBrandsId(), goods.getGoodsTypeId(), 
					goods.getRemark(), goods.getStatus(), goodsStatusDesc, brandsName, goodsTypeName);
			
			//属性ID=>属性列表
			List<GdGoodsPropertyBo> customPropBos = new ArrayList<GdGoodsPropertyBo>();
			LinkedHashMap<Long, List<GdGoodsPropertyBo>> propBoMap = convertToMap(basicPropList, customPropBos);
			for(Iterator<Long> it = propBoMap.keySet().iterator(); it.hasNext();){
				Long propId = it.next();
				List<GdGoodsPropertyBo> bos = propBoMap.get(propId);
				GdGoodsPropertyBoAdapter boAdapter = GdGoodsPropertyBoAdapter.transFrom(bos);
				result.addGdGoodsPropertyBoAdapter(boAdapter);
			}
			//自定义属性
			for (GdGoodsPropertyBo bo:customPropBos) {
				GdGoodsPropertyCustom custom = new GdGoodsPropertyCustom(bo.getDmId(), bo.getOtherKey(), bo.getOtherValue());
				result.addGdGoodsPropertyCustom(custom);
			}
			
			//SKU价格库存属性信息
			for (GdGoodsSkuPropBo skuBo:acceptSkuBoList){
				Double price = new Double(0);
				if (skuPriceMap != null && !skuPriceMap.isEmpty()) {
					price = skuPriceMap.get(skuBo.getSkuId());
				}
				Integer stock = 0;
				if (skuStockMap != null && !skuStockMap.isEmpty()) {
					stock = skuStockMap.get(skuBo.getSkuId());
				}
				String[] strs = skuService.getSkuValueStr(skuBo.getProps());
				result.addSkuJson(skuBo.getSkuId(), price, stock, skuBo.getProps(), strs[0], strs[1]);
			}
			if (picList != null) {
				for (GdGoodsPicture pic:picList) {
					result.addImgJson(pic.getDmId(), pic.getPicUrl());
				}
			}
			if (descPicList != null) {
				for (GdGoodsDescPicture pic:descPicList) {
					result.addDescImgJson(pic.getDmId(), pic.getPicUrl());
				}
			}
			if (extraInfo != null){
				result.addExtraInfo(extraInfo);
			}
			//7、获取商品属性配置
			if (withPropConfig) {
				List<GdGoodsTypeProperty> basicPropConfigs = typePropService.getGoodsTypePropList(goods.getGoodsTypeId(), true, true, false, true, true);
				List<GdGoodsTypeProperty> skuPropConfigs = typePropService.getGoodsTypePropList(goods.getGoodsTypeId(), true, false, true, true, true);
				result.addBasicPropConfig(basicPropConfigs);
				result.addSkuPropConfig(skuPropConfigs);
			}
			return result;
	}
	
	/**
	 * 基本属性List->Map
	 * @param propBoList     传入值，所有基本属性列表
	 * @param customPropBos  返回值，自定义基本属性列表
	 * @return
	 */
	private LinkedHashMap<Long, List<GdGoodsPropertyBo>> convertToMap(List<GdGoodsPropertyBo> propBoList, List<GdGoodsPropertyBo> customPropBos) {
		LinkedHashMap<Long, List<GdGoodsPropertyBo>> resultMap = new LinkedHashMap<Long, List<GdGoodsPropertyBo>>();
		if (propBoList == null || propBoList.isEmpty()) {
			return resultMap;
		}
		for(GdGoodsPropertyBo bo: propBoList){
			Long propId = bo.getPropId();
			if (propId == null) { //自定义属性
				customPropBos.add(bo);
				continue;
			}
			
			List<GdGoodsPropertyBo> propBos = resultMap.get(propId);
			if (propBos == null) {
				propBos = new ArrayList<GdGoodsPropertyBo>();
				resultMap.put(propId, propBos);
			}
			propBos.add(bo);
		}
		return resultMap;
	}
	
	@Override
	public void addGoods (GdGoodsOutJson goodsOut, Long supplierId) throws ServiceException {
		
		// 商品信息
		GdGoodsOutJson.GoodsJson goodsJson = goodsOut.getGoods();
		//  基本属性 
		List<GdGoodsPropertyBo> basicPropJsonList = goodsOut.getBasicProps();
		//  SKU属性 
		List<GdGoodsOutJson.GoodsSkuJson> skuJsonList = goodsOut.getSkus();
		// 商品图片地址
		List<GdGoodsOutJson.GoodsImgJson> imgJsonList = goodsOut.getImgs();
		// 商品描述图片地址
		List<GdGoodsOutJson.GoodsDescImgJson> descImgJsonList = goodsOut.getDescImgs();
	
		if (goodsJson == null) {
			return ;
		}
		//1、添加商品
		Long goodsId = addGoods(supplierId, goodsJson);
		
		//2、添加商品基本属性
		if (basicPropJsonList != null && !basicPropJsonList.isEmpty()) {
			this.addBasicProps(basicPropJsonList, goodsId);
		}
		
		//3、添加SKU属性信息
		if (skuJsonList != null && !skuJsonList.isEmpty()) {
			//获取商品分类溢价率
			this.addSkuInfo(supplierId, skuJsonList, goodsId);
		}
		
		//4、添加商品图片
		if (imgJsonList != null && !imgJsonList.isEmpty()) {
			for (GdGoodsOutJson.GoodsImgJson imgJson : imgJsonList ) {
				GdGoodsPicture goodsPicVo = new GdGoodsPicture();
				goodsPicVo.setCreateOn(System.currentTimeMillis());
				goodsPicVo.setDmId(fifteenLongId.nextId());
				goodsPicVo.setGoodsId(goodsId);
				goodsPicVo.setPicUrl(imgJson.getUrl());
				goodsPicVo.setPropertyId(null);
				pictureService.addVo(goodsPicVo);
			}
		}
		
		//5、添加商品描述商品图片
		if (descImgJsonList != null && !descImgJsonList.isEmpty()) {
			for (GdGoodsOutJson.GoodsDescImgJson descImgJson : descImgJsonList ) {
				GdGoodsDescPicture goodsPicVo = new GdGoodsDescPicture();
				goodsPicVo.setCreateOn(System.currentTimeMillis());
				goodsPicVo.setDmId(fifteenLongId.nextId());
				goodsPicVo.setGoodsId(goodsId);
				goodsPicVo.setPicUrl(descImgJson.getUrl());
				descPictureService.addVo(goodsPicVo);
			}
		}
	}
	private void addBasicProps(List<GdGoodsPropertyBo> basicPropJsonList, Long goodsId) throws ServiceException {
		for (GdGoodsPropertyBo propBo : basicPropJsonList ) {
			GdGoodsPropertyVal propVal = new GdGoodsPropertyVal();
			propVal.setDmId(fifteenLongId.nextId());
			propVal.setGoodsId(goodsId);
			propVal.setGoodsTypeProId(propBo.getPropId());
			propVal.setGoodsTypeProValId(propBo.getPropValId());
			propVal.setOtherValue(propBo.getOtherValue());
			propVal.setOtherKey(propBo.getOtherKey());
			
			propValService.addVo(propVal);
		}
	}
	private void addSkuInfo(Long supplierId, List<GdGoodsOutJson.GoodsSkuJson> skuJsonList, Long goodsId) throws ServiceException {
		for (GdGoodsOutJson.GoodsSkuJson skuJson : skuJsonList ) {
			Double price = skuJson.getPrice();
			Integer stock = skuJson.getStock();
			List<GdGoodsPropertyBo> skuPropBoList = skuJson.getSkuProps();
			
			//3.1添加SKU
			GdGoodsSku skuVo = new GdGoodsSku();
			Long skuId = fifteenLongId.nextId();
			skuVo.setDmId(skuId);
			skuVo.setGoodsId(goodsId);
			skuVo.setStockinId(null);
			skuService.addVo(skuVo);
			
			//3.2添加SKU库存
			CsGysStock stockVo = new CsGysStock();
			stockVo.setDmId(fifteenLongId.nextId());
			stockVo.setGysId(supplierId);
			stockVo.setSkuId(skuId);
			stockVo.setPrice(price);
			stockVo.setPriceInit(price);
			stockVo.setStockQuantity(stock);
			 // (待配送数量)
			stockVo.setAwaitQuantity(0);			
			// (在售状态(0在售,1停售))
			stockVo.setIsOnsale((byte)1);			
			 // (审核状态(0已同意,1已拒绝,2未审核))
			stockVo.setIsCheck((byte)2);			
			gysStockService.addVo(stockVo);
			
			//3.3 添加SKU属性
			if (skuPropBoList != null && !skuPropBoList.isEmpty()) {
				for (GdGoodsPropertyBo skuBo: skuPropBoList) {
					GdGoodsSkuProperty skuPropVo = new GdGoodsSkuProperty();
					skuPropVo.setDmId(fifteenLongId.nextId());
					skuPropVo.setGoodsSkuId(skuId);
					skuPropVo.setOtherValue(skuBo.getOtherValue());
					skuPropVo.setProId(skuBo.getPropId());
					skuPropVo.setProValId(skuBo.getPropValId());
					skuPropService.addVo(skuPropVo);
				}
			}
			
			//3.4  初始化商品价格
			GdGoodsSkuRate skuRate = new GdGoodsSkuRate();
			skuRate.setSkuId(skuId);
			skuRateService.addVo(skuRate);
		}
	}
	private Long addGoods(Long supplierId, GdGoodsOutJson.GoodsJson goodsJson) throws ServiceException {
		GdGoods goodsVo = new GdGoods();
		Long goodsId = fifteenLongId.nextId();
		goodsVo.setDmId(goodsId);
		goodsVo.setSupplierId(supplierId); 
		goodsVo.setBrandsId(goodsJson.getBrandsId());
		goodsVo.setGoodsTypeId(goodsJson.getGoodsTypeId());
		goodsVo.setCode(goodsJson.getGoodsCode());
		goodsVo.setName(goodsJson.getGoodsName());
		goodsVo.setRemark(goodsJson.getDesc());
		goodsVo.setStatus(GoodsStatusEnum.notCheck.getValue());
		goodsVo.setCreateBy(supplierId);				
		goodsVo.setCreateOn(System.currentTimeMillis());
		goodsVo.setUpdateBy(supplierId);				
		goodsVo.setUpdateOn(System.currentTimeMillis());
		goodsVo.setGoodsProValMsg("");
		this.addVo(goodsVo);
		return goodsId;
	}
	/**
	 * 更新商品状态
	 * @param goodsId		商品名称
	 * @param newGoodsStatusEnum	新状态
	 * @throws ServiceException
	 */
	public void updateGoodsStatus(Long goodsId, GoodsStatusEnum newGoodsStatusEnum) throws ServiceException {
		GdGoods goods = this.findVo(goodsId, null);
		if (goods != null){
			goods.setStatus(newGoodsStatusEnum.getValue());
			this.modVoNotNull(goods);
		}
	}
	//list转为Map,键 =属性实际值对象主键ID
	private Map<Long, GdGoodsPropertyVal> convertListToMap(List<GdGoodsPropertyVal> list) {
		Map<Long, GdGoodsPropertyVal> map = new HashMap<Long, GdGoodsPropertyVal>();
		if (list != null) {
			for(GdGoodsPropertyVal item:list){
				Long dmId = item.getDmId();
				map.put(dmId, item);
			}
		}
		return map;
	}
	
	/**
	 * 编辑商品详情
	 * @author 欧江波 928482427@qq.com
	 * @param goodsOut		商品对象
	 * @param supplierId	供应商ID
	 * @throws ServiceException
	 */
	@Override
	public Integer modGoods(GdGoodsOutJson goodsOut, Long supplierId, String ip, Long operator) throws ServiceException {
		// 商品信息
		GdGoodsOutJson.GoodsJson goodsJson = goodsOut.getGoods();
		//  基本属性 
		List<GdGoodsPropertyBo> basicPropJsonList = goodsOut.getBasicProps();
		//  SKU属性 
		List<GdGoodsOutJson.GoodsSkuJson> skuJsonList = goodsOut.getSkus();
		// 商品图片地址
		List<GdGoodsOutJson.GoodsImgJson> imgJsonList = goodsOut.getImgs();
		// 商品描述图片地址
		List<GdGoodsOutJson.GoodsDescImgJson> descImgJsonList = goodsOut.getDescImgs();
		
		if (goodsJson == null) {
			return RespCode.gd_goods_edit_json_error;
		}
		//1、更新商品信息
		Long goodsId = goodsJson.getGoodsId();
		GdGoods goodsVo = this.findVo(goodsJson.getGoodsId(), null);
		if (goodsVo == null) {
			return RespCode.gd_goods_not_found;
		}
		boolean goodsUpdated = this.compareGoodsInfo(supplierId, goodsJson, goodsVo);
		
		//2、更新商品基本属性
		List<GdGoodsPropertyVal> propValUpdate = new ArrayList<GdGoodsPropertyVal>();
		List<GdGoodsPropertyVal> propValAdd = new ArrayList<GdGoodsPropertyVal>();
		List<Long> propValDel = new ArrayList<Long>();
		
		if (basicPropJsonList != null && !basicPropJsonList.isEmpty()) {
			//2.1获取数据库中商品基本属性
			GdGoodsPropertyVal propValQuery = new GdGoodsPropertyVal();
			propValQuery.setGoodsId(goodsId);
			List<GdGoodsPropertyVal> dbPropValList = propValService.findList(propValQuery);
			//属性实际值主键ID => 商品属性实际值对象
			Map<Long, GdGoodsPropertyVal> dbPropValMap = convertListToMap(dbPropValList);
			this.compareBasicProps(basicPropJsonList, goodsId, propValUpdate, propValAdd, propValDel, dbPropValMap);
		}
		
		//3、添加或者更新SKU属性信息
		List<GdGoodsSku> goodsSkuAdd = new ArrayList<GdGoodsSku>();
		List<CsGysStock> stockAdd = new ArrayList<CsGysStock>();
		List<CsGysStock> stockUpdate = new ArrayList<CsGysStock>();
		List<GdGoodsSkuProperty> skuPropAdd = new ArrayList<GdGoodsSkuProperty>();
		
		if (skuJsonList != null && !skuJsonList.isEmpty()) {
			
			//数据库中已经存在的商品SKU组合
			List<GdGoodsSkuPropBo> skuCombineListInDb = skuService.getSkuPropBoList(goodsVo.getGoodsTypeId(), goodsId);
			Map<String, GdGoodsSkuPropBo> skuCombineMapInDb = new HashMap<String, GdGoodsSkuPropBo>();
			for (GdGoodsSkuPropBo bo:skuCombineListInDb) {
				skuCombineMapInDb.put(bo.getSkuValueIdStr(), bo);
			}
			
			//获取商品价格库存列表
			List<CsGysStock> stockList = gysStockService.getStocksByGoodsId(goodsId);
			Map<Long, CsGysStock> stockMap = new HashMap<Long, CsGysStock>();
			if (stockList != null) {
				for (CsGysStock stock:stockList) {
					stockMap.put(stock.getSkuId(), stock);
				}
			}
			this.compareSkuInfo(supplierId, skuJsonList, goodsId, goodsSkuAdd, stockAdd, stockUpdate, skuPropAdd, skuCombineMapInDb, stockMap);
		}
		
		//4、添加商品图片
		List<GdGoodsPicture> goodsPicAdd = new ArrayList<GdGoodsPicture>();
		List<GdGoodsPicture> goodsPicUpdate = new ArrayList<GdGoodsPicture>();
		List<Long> goodsPicDel = new ArrayList<Long>();
		if (imgJsonList != null && !imgJsonList.isEmpty()) {
			//2.1获取数据库中商品图片列表
			GdGoodsPicture picQuery = new GdGoodsPicture();
			picQuery.setGoodsId(goodsId);
			List<GdGoodsPicture> dbPicList = pictureService.findList(picQuery);
			//图片主键ID => 图片对象
			Map<Long, GdGoodsPicture> dbPicMap = convertGoodsPicListToMap(dbPicList);
			this.compareImg(imgJsonList, goodsId, goodsPicAdd, goodsPicUpdate, goodsPicDel, dbPicMap);
		}
		
		//4.1 编辑商品描述图片
		List<GdGoodsDescPicture> descPicAdd = new ArrayList<GdGoodsDescPicture>();
		List<GdGoodsDescPicture> descPicUpdate = new ArrayList<GdGoodsDescPicture>();
		List<Long> descPicDel = new ArrayList<Long>();
		if (descImgJsonList != null && !descImgJsonList.isEmpty()) {
			//2.1获取数据库中商品图片列表
			GdGoodsDescPicture picQuery = new GdGoodsDescPicture(); 
			picQuery.setGoodsId(goodsId);
			List<GdGoodsDescPicture> dbPicList = descPictureService.findList(picQuery);
			//图片主键ID => 图片对象
			Map<Long, GdGoodsDescPicture> dbPicMap = convertDescPicListToMap(dbPicList);
			this.compareDescImg(descImgJsonList, goodsId, descPicAdd, descPicUpdate, descPicDel, dbPicMap);
		}
				
		// 5 做更新处理
		
		//5.1更新商品
		if (goodsUpdated){
			this.modVoNotNull(goodsVo);
		}
		//5.2更新基本属性
		for (GdGoodsPropertyVal propVal:propValUpdate) {
			propValService.modVoNotNull(propVal);
		}
		for (GdGoodsPropertyVal propVal:propValAdd) {
			propVal.setDmId(fifteenLongId.nextId());
			propValService.addVo(propVal);
		}
		for (Long propValId:propValDel) {
			propValService.delId(propValId);
		}
		
		//5.3 SKU属性
		//获取商品分类溢价率
		for (GdGoodsSku sku:goodsSkuAdd) {
			skuService.addVo(sku);
		}
		for (GdGoodsSkuProperty skuProp:skuPropAdd) {
			skuPropService.addVo(skuProp);
		}
		for (CsGysStock stock:stockAdd) {
			stock.setDmId(fifteenLongId.nextId());
			gysStockService.addVo(stock);
			
			//增加SKU溢价
			GdGoodsSkuRate skuRate = new GdGoodsSkuRate();
			skuRate.setSkuId(stock.getSkuId());
			skuRateService.addVo(skuRate);
		}
		for (CsGysStock stock:stockUpdate) {
			gysStockService.modVoNotNull(stock);
		}
		
		// 5.4商品图片
		for (GdGoodsPicture pic:goodsPicAdd) {
			pictureService.addVo(pic);
		}
		for (GdGoodsPicture pic:goodsPicUpdate) {
			pic.setDmId(fifteenLongId.nextId());
			pictureService.modVoNotNull(pic);
		}
		for (Long dmId:goodsPicDel) {
			pictureService.delId(dmId);
		}
		//5.5商品描述图片
		for (GdGoodsDescPicture pic:descPicAdd) {
			descPictureService.addVo(pic);
		}
		for (GdGoodsDescPicture pic:descPicUpdate) {
			pic.setDmId(fifteenLongId.nextId());
			descPictureService.modVoNotNull(pic);
		}
		for (Long dmId:descPicDel) {
			descPictureService.delId(dmId);
		}
		return 0;
	}
	private void compareDescImg(List<GdGoodsOutJson.GoodsDescImgJson> descImgJsonList, Long goodsId,
			List<GdGoodsDescPicture> descPicAdd, List<GdGoodsDescPicture> descPicUpdate, List<Long> descPicDel,
			Map<Long, GdGoodsDescPicture> dbPicMap) {
		//客户端图片主键ID列表
		Set<Long> clientPicKeys = new HashSet<Long>();
		for (GdGoodsOutJson.GoodsDescImgJson imgJson : descImgJsonList ) {
			Long imgId = imgJson.getImgId();
			if (imgId == null || imgId == -1) { //添加
				GdGoodsDescPicture goodsPicVo = new GdGoodsDescPicture();
				goodsPicVo.setCreateOn(System.currentTimeMillis());
				goodsPicVo.setDmId(fifteenLongId.nextId());
				goodsPicVo.setGoodsId(goodsId);
				goodsPicVo.setPicUrl(imgJson.getUrl());
				descPicAdd.add(goodsPicVo);
			} else {
				GdGoodsDescPicture goodsPicVo = dbPicMap.get(imgId);
				if ( goodsPicVo != null && !goodsPicVo.getPicUrl().equals(imgJson.getUrl())){
					goodsPicVo.setPicUrl(imgJson.getUrl());
					descPicUpdate.add(goodsPicVo);
				}
			}
			clientPicKeys.add(imgId);
		}
		
		//要删除的属性值
		Set<Long> dbPicKeys = dbPicMap.keySet();
		clientPicKeys.retainAll(dbPicKeys);	//取clientPicKeys和dbPicKeys的交集
		dbPicKeys.removeAll(clientPicKeys);	//取dbPicKeys和交集的差集
		
		descPicDel.addAll(dbPicKeys);
	}
	private void compareImg(List<GdGoodsOutJson.GoodsImgJson> imgJsonList, Long goodsId,
			List<GdGoodsPicture> goodsPicAdd, List<GdGoodsPicture> goodsPicUpdate, List<Long> goodsPicDel,
			Map<Long, GdGoodsPicture> dbPicMap) {
		//客户端图片主键ID列表
		Set<Long> clientPicKeys = new HashSet<Long>();
		for (GdGoodsOutJson.GoodsImgJson imgJson : imgJsonList ) {
			Long imgId = imgJson.getImgId();
			if (imgId == null || imgId == -1) { //添加
				GdGoodsPicture goodsPicVo = new GdGoodsPicture();
				goodsPicVo.setCreateOn(System.currentTimeMillis());
				goodsPicVo.setDmId(fifteenLongId.nextId());
				goodsPicVo.setGoodsId(goodsId);
				goodsPicVo.setPicUrl(imgJson.getUrl());
				goodsPicVo.setPropertyId(null);
				goodsPicAdd.add(goodsPicVo);
			} else {
				GdGoodsPicture goodsPicVo = dbPicMap.get(imgId);
				if ( goodsPicVo != null && !goodsPicVo.getPicUrl().equals(imgJson.getUrl())){
					goodsPicVo.setPicUrl(imgJson.getUrl());
					goodsPicUpdate.add(goodsPicVo);
				}
			}
			clientPicKeys.add(imgId);
		}
		
		//要删除的属性值
		Set<Long> dbPicKeys = dbPicMap.keySet();
		clientPicKeys.retainAll(dbPicKeys);	//取clientPicKeys和dbPicKeys的交集
		dbPicKeys.removeAll(clientPicKeys);	//取dbPicKeys和交集的差集
		
		goodsPicDel.addAll(dbPicKeys);
	}
	//比较SKU信息
	private void compareSkuInfo(Long supplierId, List<GdGoodsOutJson.GoodsSkuJson> skuJsonList, Long goodsId,
			List<GdGoodsSku> goodsSkuAdd, List<CsGysStock> stockAdd, List<CsGysStock> stockUpdate,
			List<GdGoodsSkuProperty> skuPropAdd, Map<String, GdGoodsSkuPropBo> skuCombineMapInDb,
			Map<Long, CsGysStock> stockMap) {
		for (GdGoodsOutJson.GoodsSkuJson skuJson : skuJsonList ) {
			Double price = skuJson.getPrice();
			Integer stock = skuJson.getStock();
			List<GdGoodsPropertyBo> skuPropBoList = skuJson.getSkuProps();
			
			//3.1添加或者更新SKU,SKU不可删除
			Long skuId = skuJson.getSkuId();
			Long nSkuId = -1L;
			if (skuId == null || skuId == -1){ //代表添加
				GdGoodsSku skuVo = new GdGoodsSku();
				nSkuId = fifteenLongId.nextId();
				skuVo.setDmId(nSkuId);
				skuVo.setGoodsId(goodsId);
				skuVo.setStockinId(null);	
				goodsSkuAdd.add(skuVo);
			} else { //此处信息无需变动
			}
			
			
			//3.2添加获取更新SKU库存
			if (skuId == null || skuId == -1){ //代表添加
				CsGysStock stockVo = new CsGysStock();
				stockVo.setDmId(fifteenLongId.nextId());
				stockVo.setGysId(supplierId);
				stockVo.setSkuId(nSkuId);
				stockVo.setPrice(price);
				stockVo.setStockQuantity(stock);
				 // (待配送数量)
				stockVo.setAwaitQuantity(0);			
				// (在售状态(0在售,1停售))
				stockVo.setIsOnsale((byte)1);			
				 // (审核状态(0已同意,1已拒绝,2未审核))
				stockVo.setPriceInit(price);
				stockVo.setIsCheck((byte)2);			
				
				stockAdd.add(stockVo);
			} else {
				CsGysStock stockVo = stockMap.get(skuId);
				if (stockVo !=null){
					boolean stockVoUpdated = false;
					if (price != null && !price.equals(stockVo.getPrice())){
						stockVo.setPrice(price);
						stockVoUpdated = true;
					}
					if (stock != null && !stock.equals(stockVo.getStockQuantity())){
						stockVo.setStockQuantity(stock);
						stockVoUpdated = true;
					}
					if (stockVoUpdated){
						stockUpdate.add(stockVo);
					}
				}
			}
			
			//3.3 添加SKU属性组合，SKU属性组不可以更改、删除，只能下架
			if (skuPropBoList != null && !skuPropBoList.isEmpty()) {
				if (skuId == null || skuId == -1){ //代表添加
					
					String skuIdStr = skuJson.getValueIdStr();
					if (skuCombineMapInDb.containsKey(skuIdStr)) { //存在属性组合，提示前端页面
						log.info("该属性值组合已经存在,skuIdStr = "+skuIdStr + ",skuId="+skuId);
						//return RespCode.gd_goods_edit_sku_combine_exsit;
					} else {
						for (GdGoodsPropertyBo skuBo: skuPropBoList) {
							GdGoodsSkuProperty skuPropVo = new GdGoodsSkuProperty();
							skuPropVo.setDmId(fifteenLongId.nextId());
							skuPropVo.setGoodsSkuId(nSkuId);
							skuPropVo.setOtherValue(skuBo.getOtherValue());
							skuPropVo.setProId(skuBo.getPropId());
							skuPropVo.setProValId(skuBo.getPropValId());
							skuPropAdd.add(skuPropVo);
						}
					}
				}
			}
		}
	}
	//比较基本属性
	private void compareBasicProps(List<GdGoodsPropertyBo> basicPropJsonList, Long goodsId,
			List<GdGoodsPropertyVal> propValUpdate, List<GdGoodsPropertyVal> propValAdd, List<Long> propValDel,
			Map<Long, GdGoodsPropertyVal> dbPropValMap) {
		
		//客户端属性值key列表
		Set<Long> clientPropValKeys = new HashSet<Long>();
		for (GdGoodsPropertyBo propBo : basicPropJsonList ) {
			if (!dbPropValMap.containsKey(propBo.getDmId())) { //数据库不存在该值，添加 dmId = -1
				GdGoodsPropertyVal propVal = new GdGoodsPropertyVal();
				propVal.setDmId(fifteenLongId.nextId());
				propVal.setGoodsId(goodsId);
				propVal.setGoodsTypeProId(propBo.getPropId());
				propVal.setGoodsTypeProValId(propBo.getPropValId());
				propVal.setOtherValue(propBo.getOtherValue());
				propVal.setOtherKey(propBo.getOtherKey());
				
				propValAdd.add(propVal);
			} else {	//存在，更新,属性值变化
				GdGoodsPropertyVal propVal = dbPropValMap.get(propBo.getDmId());
				if (propVal != null) { //查看是否发生变化
					boolean propValUpdated = false;
					if (propBo.getPropValId() != null && !propBo.getPropValId().equals(propVal.getGoodsTypeProValId())) {
						propValUpdated = true;
						propVal.setGoodsTypeProValId(propBo.getPropValId());
					}
					if (StringUtils.isNotBlank(propBo.getOtherValue()) && !propBo.getOtherValue().equals(propVal.getOtherValue())) {
						propValUpdated = true;
						propVal.setOtherValue(propBo.getOtherValue());
					}
					if (StringUtils.isNotBlank(propBo.getOtherKey()) && !propBo.getOtherKey().equals(propVal.getOtherKey())) {
						propValUpdated = true;
						propVal.setOtherKey(propBo.getOtherKey());
					}
					if (propValUpdated){
						propValUpdate.add(propVal);
					}
				}
			}
			clientPropValKeys.add(propBo.getDmId());
		}
		
		//要删除的属性值
		Set<Long> dbPropValKeys = dbPropValMap.keySet();
		if ( dbPropValKeys != null) {
			clientPropValKeys.retainAll(dbPropValKeys);	//取交集
			dbPropValKeys.removeAll(clientPropValKeys);	//取dbPropValKeys和交集的差集
			propValDel.addAll(dbPropValKeys);
		}
	}
	//比较商品基本信息
	private boolean compareGoodsInfo(Long supplierId, GdGoodsOutJson.GoodsJson goodsJson, GdGoods goodsVo) {
		boolean goodsUpdated = false;
		if (goodsJson.getBrandsId() != null && !goodsJson.getBrandsId().equals(goodsVo.getBrandsId())) {
			goodsUpdated =true;
			goodsVo.setBrandsId(goodsJson.getBrandsId());
		}
		if (StringUtils.isNotBlank(goodsJson.getGoodsCode()) && !goodsJson.getGoodsCode().equals(goodsVo.getCode())) {
			goodsUpdated =true;
			goodsVo.setCode(goodsJson.getGoodsCode());
		}
		if (StringUtils.isNotBlank(goodsJson.getGoodsName()) && !goodsJson.getGoodsName().equals(goodsVo.getName())) {
			goodsUpdated =true;
			goodsVo.setName(goodsJson.getGoodsName());
		}
		if (StringUtils.isNotBlank(goodsJson.getDesc()) && !goodsJson.getDesc().equals(goodsVo.getRemark())) {
			goodsUpdated =true;
			goodsVo.setRemark(goodsJson.getDesc());
		}
		if (goodsJson.getGoodsStatus() != null && !goodsJson.getGoodsStatus().equals(goodsVo.getStatus())) {
			goodsUpdated =true;
			goodsVo.setStatus(goodsJson.getGoodsStatus());
		}
		if (goodsUpdated){
			goodsVo.setUpdateBy(supplierId);				
			goodsVo.setUpdateOn(System.currentTimeMillis());
		}
		return goodsUpdated;
	}
	
	private Map<Long, GdGoodsDescPicture> convertDescPicListToMap(List<GdGoodsDescPicture> dbPicList) {
		Map<Long, GdGoodsDescPicture> map = new HashMap<Long, GdGoodsDescPicture>();
		if (dbPicList != null) {
			for(GdGoodsDescPicture item:dbPicList){
				Long dmId = item.getDmId();
				map.put(dmId, item);
			}
		}
		return map;
	}
	private Map<Long, GdGoodsPicture> convertGoodsPicListToMap(List<GdGoodsPicture> dbPicList) {
		Map<Long, GdGoodsPicture> map = new HashMap<Long, GdGoodsPicture>();
		if (dbPicList != null) {
			for(GdGoodsPicture item:dbPicList){
				Long dmId = item.getDmId();
				map.put(dmId, item);
			}
		}
		return map;
	}
	
	@Override
	public List<ShopValueVo> findSkuList(Long goodsId) throws ServiceException {
		try {
			return dao.findSkuList(goodsId);
		} catch (Exception e) {
			log.error("根据商品ID获取sku属性值出现系统错误！", e);
            throw new ServiceException(RespCode.get_skuVal_fail,"根据商品ID获取sku属性值出现系统错误.");
		}
	}
}