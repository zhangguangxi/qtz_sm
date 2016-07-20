package com.qtz.sm.goods.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.enums.YesOrNoEnum;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsTypePropertyDao;
import com.qtz.sm.goods.enums.DisableEnum;
import com.qtz.sm.goods.service.GdGoodsTypePropertyOptionService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.service.GdGoodsTypeService;
import com.qtz.sm.goods.vo.GdGoodsType;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;
/**
 * <p>Title:GdGoodsTypePropertyServiceImpl</p>
 * <p>Description:商品分类属性服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsTypePropertyServiceImpl")
public class GdGoodsTypePropertyServiceImpl extends BaseServiceImpl<GdGoodsTypeProperty,java.lang.Long> implements GdGoodsTypePropertyService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(GdGoodsTypePropertyServiceImpl.class);
	/**注入商品分类属性DAO接口类*/
	@Resource(name="gdGoodsTypePropertyDaoImpl")
    private GdGoodsTypePropertyDao dao;
	@Autowired
	private GdGoodsTypePropertyOptionService optionService;
	@Autowired
	private GdGoodsTypeService goodsTypeService;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<GdGoodsTypeProperty,java.lang.Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	
	
	/**
	 * 获取商品分类属性列表
	 * @author 欧江波 928482427@qq.com
	 * @param onlyEnable  			是否只取可用属性
	 * @param goodsTypeId			商品分类ID
	 * @param isKey					是否关键属性
	 * @param isSale				是否销售属性
	 * @param withValueOptions		是否把属性可选属性值也取出来
	 * @param withParentProps		是否父商品分类的属性也取出来
	 * @return				商品分类属性列表
	 * @throws ServiceException
	 */
	public List<GdGoodsTypeProperty> getGoodsTypePropList(Long goodsTypeId, boolean onlyEnable, boolean isKey, 
			boolean isSale, boolean withValueOptions, boolean withParentProps) throws ServiceException {
		List<GdGoodsTypeProperty> resultList = new ArrayList<GdGoodsTypeProperty>();
		
		if (withParentProps){
			//查询商品分类的父节点
			List<GdGoodsType> parentGoodsTypeList = goodsTypeService.getParentGoodsTypeList(goodsTypeId);
			if (parentGoodsTypeList == null || parentGoodsTypeList.isEmpty()){
				return resultList;
			}
			//查询商品分类属性配置
			for (GdGoodsType goodsType:parentGoodsTypeList){
				List<GdGoodsTypeProperty> propList = getCurrentGoodsTypePropList(goodsType.getDmId(), onlyEnable, isKey, isSale, withValueOptions);
				resultList.addAll(propList);
			}
		} else {
			resultList = this.getCurrentGoodsTypePropList(goodsTypeId, onlyEnable, isKey, isSale, withValueOptions);
		}
		return resultList;
	}
	
	//获取当前分类节点下所有属性信息
	private List<GdGoodsTypeProperty> getCurrentGoodsTypePropList(Long goodsTypeId, boolean onlyEnable, boolean isKey,
			boolean isSale, boolean withValueOptions) throws ServiceException {
		GdGoodsTypeProperty goodsTypePropQuery = new GdGoodsTypeProperty();
		goodsTypePropQuery.setGoodsTypeId(goodsTypeId);
		if(onlyEnable){
			goodsTypePropQuery.setStatus(DisableEnum.enable.getValue());
		}
		if(isKey){
			goodsTypePropQuery.setIsKey(YesOrNoEnum.YES.getValue());
		}
		if(isSale){
			goodsTypePropQuery.setIsSale(YesOrNoEnum.YES.getValue());
		}
		try {
			List<GdGoodsTypeProperty> resultList =  dao.findList(goodsTypePropQuery);
			if (withValueOptions){
				for (GdGoodsTypeProperty prop:resultList) {
					//获取分类属性值选项值列表
					List<GdGoodsTypePropertyOption>	optionList = optionService.getPropOptionList(prop.getDmId(), onlyEnable);
					prop.setOptions(optionList);
				}
			}
			return resultList;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	
	/**
	 * 获取商品分类属性Map，键为商品分类属性ID，值为商品分类属性对象
	 * @author 欧江波 928482427@qq.com
	 * @param onlyEnable  			是否只取可用属性
	 * @param goodsTypeId			商品分类ID
	 * @param isKey					是否关键属性
	 * @param isSale				是否销售属性
	 * @param withValueOptions		是否把属性可选属性值也取出来
	 * @param withParentProps		是否父商品分类的属性也取出来
	 * @return
	 * @throws ServiceException
	 */
	public Map<Long, GdGoodsTypeProperty> getGoodsTypePropMap(Long goodsTypeId, boolean onlyEnable, boolean isKey, 
			boolean isSale, boolean withValueOptions, boolean withParentProps) throws ServiceException {
		List<GdGoodsTypeProperty> props = this.getGoodsTypePropList(goodsTypeId, onlyEnable, isKey, isSale, withValueOptions, withParentProps);
		Map<Long, GdGoodsTypeProperty> resultMap = new HashMap<Long, GdGoodsTypeProperty>();
		if (props != null && !props.isEmpty()){
			for (GdGoodsTypeProperty prop:props){
				resultMap.put(prop.getDmId(), prop);
			}
		}
		return resultMap;
	}
}