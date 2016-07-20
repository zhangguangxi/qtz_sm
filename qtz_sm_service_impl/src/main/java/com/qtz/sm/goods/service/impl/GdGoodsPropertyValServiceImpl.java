package com.qtz.sm.goods.service.impl;
import java.util.ArrayList;
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
import com.qtz.sm.goods.dao.GdGoodsPropertyValDao;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.service.GdGoodsPropertyValService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyOptionService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.vo.GdGoodsPropertyVal;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;
/**
 * <p>Title:GdGoodsPropertyValServiceImpl</p>
 * <p>Description:商品属性实际值服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsPropertyValServiceImpl")
public class GdGoodsPropertyValServiceImpl extends BaseServiceImpl<GdGoodsPropertyVal,java.lang.Long> implements GdGoodsPropertyValService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(GdGoodsPropertyValServiceImpl.class);
	/**注入商品属性实际值DAO接口类*/
	@Resource(name="gdGoodsPropertyValDaoImpl")
    private GdGoodsPropertyValDao dao;
	@Autowired
	private GdGoodsTypePropertyService propService;
	@Autowired
	private GdGoodsTypePropertyOptionService propOptionService;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<GdGoodsPropertyVal,java.lang.Long> getDao() {
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
	 * 根据商品ID获取商品属性BO列表
	 * @author 欧江波 928482427@qq.com
	 * @param goodsId 商品ID
	 * @return 商品属性BO列表
	 * @throws ServiceException
	 */
	@Override
	public List<GdGoodsPropertyBo> getGoodsPropBoList(Long goodsTypeId, Long goodsId) throws ServiceException {
		//商品分类属性配置信息（包括父类，包括属性值，基本属性）
		Map<Long, GdGoodsTypeProperty> propConfigMap =  propService.getGoodsTypePropMap(goodsTypeId, true, true, false, true, true);
		
		//商品分类属性值选项配置信息(属性值ID=>属性值对象)
		Map<Long, GdGoodsTypePropertyOption> goodsTypePropValConfigMap =  new HashMap<Long, GdGoodsTypePropertyOption>();
		for (Iterator<Long> it = propConfigMap.keySet().iterator();it.hasNext();) {
			Long propId = it.next();
			Map<Long, GdGoodsTypePropertyOption> propOption = propOptionService.getPropOptionMap(propId, true);
			goodsTypePropValConfigMap.putAll(propOption);
		}
			
		//获取商品属性列表
		GdGoodsPropertyVal gdPropValQuery = new GdGoodsPropertyVal();
		gdPropValQuery.setGoodsId(goodsId);
		try {
			List<GdGoodsPropertyVal> propValList = dao.findList(gdPropValQuery);
			List<GdGoodsPropertyBo> resultList = new ArrayList<GdGoodsPropertyBo>();
			if(propValList != null){
				for(GdGoodsPropertyVal propVal: propValList){
					Long propId = propVal.getGoodsTypeProId();
					Long propValId = propVal.getGoodsTypeProValId();
					
					GdGoodsPropertyBo bo = new GdGoodsPropertyBo();
					bo.setDmId(propVal.getDmId());
					bo.setPropId(propId);
					bo.setOtherValue(propVal.getOtherValue());
					bo.setPropValId(propValId);
					bo.setOtherKey(propVal.getOtherKey());
					
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
	 * 根据商品分类ID和商品ID获取商品属性BO_MAP,键为属性ID，值为属性_BO列表
	 * @author 欧江波 928482427@qq.com
	 * @param goodsTypeId 商品分类ID
	 * @param goodsId 商品ID
	 * @return 商品属性BO列表
	 * @throws ServiceException
	 */
	@Override
	public Map<Long, List<GdGoodsPropertyBo>> getGoodsPropBoMap(Long goodsTypeId, Long goodsId)
			throws ServiceException {
		List<GdGoodsPropertyBo> propBoList = this.getGoodsPropBoList(goodsTypeId, goodsId);
		Map<Long, List<GdGoodsPropertyBo>> resultMap = new HashMap<Long, List<GdGoodsPropertyBo>>();
		for (GdGoodsPropertyBo bo:propBoList){
			Long propId = bo.getPropId();
			List<GdGoodsPropertyBo> propBoListTemp = resultMap.get(propId);
			if (propBoListTemp == null){
				propBoListTemp = new ArrayList<GdGoodsPropertyBo>();
				resultMap.put(propId, propBoListTemp);
			}
			propBoListTemp.add(bo);
		}
		return resultMap;
	}

}