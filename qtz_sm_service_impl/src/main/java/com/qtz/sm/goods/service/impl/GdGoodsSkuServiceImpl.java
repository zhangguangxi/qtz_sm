package com.qtz.sm.goods.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsSkuDao;
import com.qtz.sm.goods.dao.GdGoodsSkuPropertyDao;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.model.GdGoodsSkuPropBo;
import com.qtz.sm.goods.service.GdGoodsSkuService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyOptionService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.vo.GdGoodsSku;
import com.qtz.sm.goods.vo.GdGoodsSkuProperty;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;

import utils.StringUtils;

/**
 * Title:GdGoodsSkuServiceImpl
 * Description:商品SKU服务实现类
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @author 谭林清 - tanlinqingaction@126.com
 * @Reversion 欧江波 928482427@qq.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsSkuServiceImpl")
public class GdGoodsSkuServiceImpl extends BaseServiceImpl<GdGoodsSku, java.lang.Long> implements GdGoodsSkuService {
	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(GdGoodsSkuServiceImpl.class);
	/** 注入商品SKUDAO接口类 */
	@Resource(name = "gdGoodsSkuDaoImpl")
	private GdGoodsSkuDao dao;
	@Autowired
	private GdGoodsSkuPropertyDao propDao;
	@Autowired
	private GdGoodsTypePropertyService propService;
	@Autowired
	private GdGoodsTypePropertyOptionService optionService;

	@Override
	protected BizDao<GdGoodsSku, java.lang.Long> getDao() {
		return dao;
	}

	@Override
	protected LogTool getLog() {
		return log;
	}
	
	/***
	 * 获取单个SKU的属性BO对象列表
	 * @param propConfigMap
	 * @param goodsTypePropValConfigMap
	 * @param skuId
	 * @return
	 * @throws ServiceException
	 */
	private List<GdGoodsPropertyBo> getGoodsSkuPropBos(Map<Long, GdGoodsTypeProperty> propConfigMap, 
			Map<Long, GdGoodsTypePropertyOption> goodsTypePropValConfigMap, Long skuId) throws ServiceException {
		// 商品SKU属性列表
		GdGoodsSkuProperty skuPropQuery = new GdGoodsSkuProperty();
		skuPropQuery.setGoodsSkuId(skuId);
		try {
			List<GdGoodsSkuProperty> skuPropList = propDao.findList(skuPropQuery);
			List<GdGoodsPropertyBo> resultList = new ArrayList<GdGoodsPropertyBo>();
			if (skuPropList != null) {
				for (GdGoodsSkuProperty skuProp: skuPropList){
					Long propId = skuProp.getProId();
					Long propValId = skuProp.getProValId();
					
					GdGoodsPropertyBo bo = new GdGoodsPropertyBo();
					bo.setDmId(skuProp.getDmId());
					bo.setPropId(propId);
					bo.setOtherValue(skuProp.getOtherValue());
					bo.setPropValId(propValId);
					
					//属性ID名称
					GdGoodsTypeProperty gtPropConfig = propConfigMap.get(propId);
					if (gtPropConfig != null){
						bo.setPropIdName(gtPropConfig.getName());
					}
					
					//属性值名称
					GdGoodsTypePropertyOption optionValConfig = goodsTypePropValConfigMap.get(propValId);
					if (optionValConfig != null){
						bo.setPropValName(optionValConfig.getVal());
					}
					resultList.add(bo);
				}
			}
			return resultList;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获取商品SKU属性BO列表
	 * @param goodsTypeId	商品分类ID
	 * @param goodsId		商品ID
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<GdGoodsSkuPropBo> getSkuPropBoList(Long goodsTypeId, Long goodsId) throws ServiceException {
		List<GdGoodsSkuPropBo> resultList = new ArrayList<GdGoodsSkuPropBo>();
		//商品分类属性配置信息
		Map<Long, GdGoodsTypeProperty> propConfigMap = propService.getGoodsTypePropMap(goodsTypeId, true, false, true, true, true);
		
		//商品分类属性值选项配置信息(属性值ID=>属性值对象)
		Map<Long, GdGoodsTypePropertyOption> goodsTypePropValConfigMap =  new HashMap<Long, GdGoodsTypePropertyOption>();
		for (Iterator<Long> it = propConfigMap.keySet().iterator();it.hasNext();) {
			Long propId = it.next();
			Map<Long, GdGoodsTypePropertyOption> propOption = optionService.getPropOptionMap(propId, true);
			goodsTypePropValConfigMap.putAll(propOption);
		}
		
		//商品所有SKU
		GdGoodsSku goodsSkuQuery = new GdGoodsSku();
		goodsSkuQuery.setGoodsId(goodsId);
		List<GdGoodsSku> skuList = this.findList(goodsSkuQuery);
		if(skuList != null){
			for (GdGoodsSku sku:skuList){
				//skuId
				Long skuId = sku.getDmId();
				//获取商品SKU属性,propId=>属性BO对象
				List<GdGoodsPropertyBo> skuPropList = this.getGoodsSkuPropBos(propConfigMap, goodsTypePropValConfigMap, skuId);
				//SKU属性值串
				String[] valueStrs = this.getSkuValueStr(skuPropList);
				
				// SKU属性BO对象
				GdGoodsSkuPropBo skuPropBo = new GdGoodsSkuPropBo();
				skuPropBo.setSkuId(skuId);
				skuPropBo.setProps(skuPropList);
				skuPropBo.setValueStrs(valueStrs);
				
				resultList.add(skuPropBo);
			}
		}
		return resultList;
	}
	
	/**
	 * 【获取商品所有SKU组合列表】，包括尚未添加到商品SKU表中的组合，返回结果类似[ [黑色，XL],[黑色, L],... ]
	 * @param goodsTypeId	商品分类ID
	 * @param goodsId		商品ID
	 * @param propCombines	属性组，类似[ [黑色,白色], [XL,L] ]
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<GdGoodsSkuPropBo> getAllSkuPropBoList(Long goodsTypeId, Long goodsId, List<List<GdGoodsPropertyBo>> propCombines) throws ServiceException {
		//1、获取商品分类SKU属性配置信息
		List<GdGoodsTypeProperty> goodsTypePropList = propService.getGoodsTypePropList(goodsTypeId, true, false, true, true, true);
		
		//所有属性值组合对象
		if (propCombines == null){
			propCombines = new ArrayList<List<GdGoodsPropertyBo>>();
		}
		for (GdGoodsTypeProperty prop:goodsTypePropList){
			//单个属性属性值列表
			List<GdGoodsPropertyBo> propboList = new ArrayList<GdGoodsPropertyBo>();
			List<GdGoodsTypePropertyOption> options = prop.getOptions();
			for (GdGoodsTypePropertyOption valOption:options) {
				GdGoodsPropertyBo bo = new GdGoodsPropertyBo();
				bo.setDmId(-1L);
				bo.setOtherValue("");
				bo.setPropId(prop.getDmId());
				bo.setPropIdName(prop.getName());
				bo.setPropValId(valOption.getDmId());
				bo.setPropValName(valOption.getVal());
				
				propboList.add(bo);
			}
			propCombines.add(propboList);
		}
		
		//2、获取商品SKU属性值组合(求笛卡尔乘积)
		List<List<GdGoodsPropertyBo>> allCombines = new ArrayList<List<GdGoodsPropertyBo>>();
		descartes(propCombines, allCombines, 0, new ArrayList<GdGoodsPropertyBo>());
		
		//3、获取已经添加到商品SKU表中的属性值组合
		List<GdGoodsSkuPropBo> skuPropBoList = this.getSkuPropBoList(goodsTypeId, goodsId);
		//属性值ID串=>属性值对象
		Map<String, GdGoodsSkuPropBo> skuPropBoMap = new HashMap<String, GdGoodsSkuPropBo>();
		for (GdGoodsSkuPropBo bo:skuPropBoList) {
			skuPropBoMap.put(bo.getSkuValueIdStr(), bo);
		}
		
		List<GdGoodsSkuPropBo> resultList = new ArrayList<GdGoodsSkuPropBo>();
		for (List<GdGoodsPropertyBo> propBo:allCombines) {
			GdGoodsSkuPropBo skuBo = new GdGoodsSkuPropBo();
			String[] valueStrs = this.getSkuValueStr(propBo);
			
			Long skuId = -1L;
			//查看该组合是否已经添加到商品SKU表中
			if (skuPropBoMap.containsKey(valueStrs[0])) {
				GdGoodsSkuPropBo skuPropBo = skuPropBoMap.get(valueStrs[0]);
				if (skuPropBo != null){
					skuId = skuPropBo.getSkuId();
					skuBo.setProps(skuPropBo.getProps());
				}
			} else {
				skuBo.setProps(propBo);
			}
			skuBo.setSkuId(skuId);
			
			skuBo.setValueStrs(valueStrs);
			resultList.add(skuBo);
		}
		
		return resultList;
	}
	/**
     * 笛卡尔乘积算法 把一个List{[1,2],[3,4],[a,b]}转化成
     * List{[1,3,a],[1,3,b],[1,4,a],[1,4,b],[2,3,a],[2,3,b],[2,4,a],[2,4,b]}数组输出
     * 
     * @param dimvalue原List
     * @param result通过乘积转化后的数组
     * @param layer 中间参数
     * @param curList 中间参数
     */
    private void descartes(List<List<GdGoodsPropertyBo>> dimvalue, List<List<GdGoodsPropertyBo>> result, int layer, List<GdGoodsPropertyBo> curList) {
        if (layer < dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                this.descartes(dimvalue, result, layer + 1, curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                	List<GdGoodsPropertyBo> list = new ArrayList<>(curList);
                    list.add(dimvalue.get(layer).get(i));
                    this.descartes(dimvalue, result, layer + 1, list);
                }
            }
        } else if (layer == dimvalue.size() - 1) {
            if (dimvalue.get(layer).size() == 0) {
                result.add(curList);
            } else {
                for (int i = 0; i < dimvalue.get(layer).size(); i++) {
                	List<GdGoodsPropertyBo> list = new ArrayList<>(curList);
                    list.add(dimvalue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }
	
	/**
	 * 获取SKU属性值串，形如“黑色,XL”
	 * @param skuPropList
	 * @return	属性值ID串，属性值名称串
	 */
	@Override
	public String[/**属性值ID串，属性值名称串*/] getSkuValueStr(List<GdGoodsPropertyBo> skuPropList) throws ServiceException {
		Collections.sort(skuPropList, new Comparator<GdGoodsPropertyBo>() {
            public int compare(GdGoodsPropertyBo arg0, GdGoodsPropertyBo arg1) {
                return arg0.getPropValId().compareTo(arg1.getPropValId());
            }
        });
		StringBuffer valueSb = new StringBuffer();
		StringBuffer idSb = new StringBuffer();
		if (skuPropList !=null && !skuPropList.isEmpty()) {
			for (int i = 0; i<skuPropList.size(); i++){
				GdGoodsPropertyBo bo = skuPropList.get(i);
				if(StringUtils.isNotBlank(bo.getPropValName())){
					valueSb = valueSb.append(bo.getPropValName()).append(",");
				}
				idSb = idSb.append(bo.getPropValId()).append(",");
			}
		}
		if (StringUtils.isNotBlank(valueSb.toString())  && StringUtils.isNotBlank(idSb.toString())  ) {
			String valueStrResult = valueSb.substring(0, valueSb.length()-1);
			String idStrResult = idSb.substring(0, idSb.length()-1);
			
			return new String[]{idStrResult, valueStrResult};
		} else {
			return new String[]{"", ""};
		}
	}
}