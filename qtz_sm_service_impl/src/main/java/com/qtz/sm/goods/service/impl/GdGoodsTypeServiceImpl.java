package com.qtz.sm.goods.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.DateUtil;
import com.mall.core.common.FifteenLongId;
import com.mall.core.common.Pinyin4jUtil;
import com.mall.core.common.enums.YesOrNoEnum;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsTypeDao;
import com.qtz.sm.goods.enums.DisableEnum;
import com.qtz.sm.goods.model.GdGoodsTypeBo;
import com.qtz.sm.goods.model.GdGoodsTypeOutJson;
import com.qtz.sm.goods.model.GdGoodsTypePropertyBo;
import com.qtz.sm.goods.model.GdGoodsTypePropertyOptionBO;
import com.qtz.sm.goods.service.GdGoodsCategroyRateService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyOptionService;
import com.qtz.sm.goods.service.GdGoodsTypePropertyService;
import com.qtz.sm.goods.service.GdGoodsTypeService;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;
import com.qtz.sm.goods.vo.GdGoodsType;
import com.qtz.sm.goods.vo.GdGoodsTypeProperty;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;

/**
 * <p>
 * Title:GdGoodsTypeServiceImpl
 * </p>
 * <p>
 * Description:商品分类服务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsTypeServiceImpl")
public class GdGoodsTypeServiceImpl extends BaseServiceImpl<GdGoodsType, java.lang.Long> implements GdGoodsTypeService {
    /** 初始化日志对象 */
    private static LogTool log = LogTool.getInstance(GdGoodsTypeServiceImpl.class);
    /** 注入商品分类DAO接口类 */
    @Resource(name = "gdGoodsTypeDaoImpl")
    private GdGoodsTypeDao dao;
    
    /**
     * 【取得】业务DAO对象
     * 
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<GdGoodsType, java.lang.Long> getDao() {
        return dao;
    }
    
    /**
     * 【取得】日志对象
     * 
     * @return 日志对象
     */
    @Override
    protected LogTool getLog() {
        return log;
    }
    
    @Autowired
    private FifteenLongId fifteenLongId;
    @Autowired
    private GdGoodsTypePropertyService gtProService;
    @Autowired
    private GdGoodsTypePropertyOptionService gtProOptionService;
    @Autowired
    private GdGoodsCategroyRateService rateService;
    
    @Override
    public List<GdGoodsType> listGoodsTypesByParentId(long parentId) throws ServiceException {
        try {
            GdGoodsType goodsType = new GdGoodsType();
            goodsType.setParentId(parentId);
            goodsType.setStatus(DisableEnum.enable.getValue());
            
            return getDao().findList(goodsType);
        }
        catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
  
    @Override
    public List<GdGoodsType> listGoodsTypesByLevel(int level) throws ServiceException {
        try {
            GdGoodsType gt = new GdGoodsType();
            gt.setLevel(level);
            gt.setStatus(DisableEnum.enable.getValue());
            return getDao().findList(gt);
        }
        catch (DaoException e) {
            log.error(e);
            throw new ServiceException(e);
        }
    }
    
    /**
     * 获取商品分类字符串，格式类似于"10,11,12", "“服装/衣服/男装”
     * 
     * @return 1.ID字符串，2.名称字符串
     * @param goodsTypeId 商品分类ID
     * @param splitStr 分割符
     * @throws ServiceException
     */
    @Override
    public String[] getGoodsTypeStrs(Long goodsTypeId, String splitStr) throws ServiceException {
    	List<GdGoodsType> parentGoodsTypeList = this.getParentGoodsTypeList(goodsTypeId) ;
    	StringBuffer idSb = new StringBuffer();
    	StringBuffer nameSb = new StringBuffer();
    	if (parentGoodsTypeList != null && !parentGoodsTypeList.isEmpty()){
    		for (int i = parentGoodsTypeList.size()-1; i>=0; i--) {
        		GdGoodsType goodsType = parentGoodsTypeList.get(i);
        		idSb = idSb.append(goodsType.getDmId()).append(",");
        		nameSb = nameSb.append(goodsType.getName()).append(",");
        	}
    		String idStr = idSb.substring(0, idSb.length() -1);
    		String nameStr = nameSb.substring(0, nameSb.length() -1);
    		return new String[] {idStr, nameStr};
    	} else {
    		return new String[] {idSb.toString(), nameSb.toString()};
    	}
    }
    
    @Transactional
    public void addGoodsType(long uid, GdGoodsTypeOutJson goodsTypeOutJson) throws ServiceException {
        try {
            log.info("addGoodsType>>>>>>增加商品分类开始!");
            if (goodsTypeOutJson == null) {
                log.info("addGoodsType>>>>>>商品分类对象为空!");
                return;
            }
            /** 商品分类基本信息 */
            GdGoodsTypeBo goodsTypeBo = goodsTypeOutJson.getGoodsTypeJson();
            /** 商品分类基本属性 */
            List<GdGoodsTypePropertyBo> basicProps = goodsTypeOutJson.getBasicProps();
            /** 商品分类销售属性 */
            List<GdGoodsTypePropertyBo> saleProps = goodsTypeOutJson.getSaleProps();
            
            /** 添加 商品分类 */
            GdGoodsType gt = addGdGoodsTypeDetail(uid, goodsTypeBo);
            
            /** 添加 商品基本属性 */
            addGoodsTypeProp(gt, basicProps, true, false);
            
            /** 添加商品销售属性 */
            addGoodsTypeProp(gt, saleProps, false, true);
            
            /** 添加商品分类溢价率 */
            addCategoryRate(gt);
            log.info("addGoodsType>>>>>>增加商品分类结束！");
        }
        catch (Exception e) {
            log.error("addGoodsType>>>>>>增加商品分类失败!" + e);
            throw new ServiceException(e);
        }
    }
    
    @Override
    public void updateGoodsType(long uid, GdGoodsType goodsTypeFront) throws ServiceException {
        try {
            log.info("updateGoodsType>>>>>>更新商品分类开始!goodsTypeOutJson:"+JSON.toJSONString(goodsTypeFront));
            if (goodsTypeFront == null) {
                log.info("updateGoodsType>>>>>>商品分类为空!");
                return;
            }
            /** 商品分类基本信息 */
            Long goodsTypeId = goodsTypeFront.getDmId();
            GdGoodsType goodsTypeDb = dao.findVo(goodsTypeFront.getDmId(), null);
            if (goodsTypeDb == null) {
            	log.info("updateGoodsType>>>>>>数据库中该商品分类不存在,dmId="+goodsTypeId); //TODO:
                return;
            }
            
            /** 商品分类基本属性 */
            List<GdGoodsTypeProperty> basicPropsListFront = goodsTypeFront.getGdTypeProesKey();
            /** 商品分类销售属性 */
            List<GdGoodsTypeProperty> salePropsListFront = goodsTypeFront.getGdTypeProesSale();
            
            /** 查询数据库中（属性值map）*/
            Map<Long, GdGoodsTypePropertyOption> propOptionsMapDB = gtProOptionService.getPropOptionMapByGoodsType(goodsTypeId);
            
            /** 查询数据库中（属性MAP）*/
            Map<Long, GdGoodsTypeProperty> basicPropMap = gtProService.getGoodsTypePropMap(goodsTypeId, false, true, false, true, false);
            Map<Long, GdGoodsTypeProperty> salePropMap = gtProService.getGoodsTypePropMap(goodsTypeId, false, false, true, true, false);
           
            /** 存放更新的商品分类属性集合 */
    		List<GdGoodsTypeProperty> propListForUpdate = new ArrayList<GdGoodsTypeProperty>();
    		/** 存放新增的商品分类属性集合 */
    		List<GdGoodsTypeProperty> propListForAdd = new ArrayList<GdGoodsTypeProperty>();
    		
    		/** 存放更新的商品分类属性值集合 */
    		List<GdGoodsTypePropertyOption> optionListForUpdate = new ArrayList<GdGoodsTypePropertyOption>();
    		/** 存放新增的商品分类属性值集合 */
    		List<GdGoodsTypePropertyOption> optionListForAdd = new ArrayList<GdGoodsTypePropertyOption>();
    		
    		//比较属性
            compareProps(basicPropsListFront, propOptionsMapDB, basicPropMap, propListForUpdate, propListForAdd, optionListForUpdate, optionListForAdd);
            compareProps(salePropsListFront, propOptionsMapDB, salePropMap, propListForUpdate, propListForAdd, optionListForUpdate, optionListForAdd);
            
            //更新_添加属性
            for (GdGoodsTypeProperty prop: propListForUpdate) {
            	gtProService.modVoNotNull(prop);
            }
            
            for (GdGoodsTypeProperty prop: propListForAdd) {
            	prop.setDmId(fifteenLongId.nextId());
            	gtProService.addVo(prop);
            }
            
            //更新_添加属性可选值
            for (GdGoodsTypePropertyOption option: optionListForUpdate) {
            	gtProOptionService.modVoNotNull(option);
            }
            
            for (GdGoodsTypePropertyOption option: optionListForAdd) {
            	option.setDmId(fifteenLongId.nextId());
            	gtProOptionService.addVo(option);
            }
            
            //更新商品分类信息
            updateGoodsTypeDetail(uid, goodsTypeFront, goodsTypeDb);
            
            log.info("updateGoodsType>>>>>>更新商品分类结束！");
        }
        catch (Exception e) {
            log.error("updateGoodsType>>>>>>更新商品分类失败！",e);
            throw new ServiceException(e);
        }
    }

    //将数据库中和客户端上传的属性和属性值选项进行比较
	private void compareProps(List<GdGoodsTypeProperty> propsListFront, 
			Map<Long, GdGoodsTypePropertyOption> propOptionsMapDB, Map<Long, GdGoodsTypeProperty> basicPropMap,
			List<GdGoodsTypeProperty> propListForUpdate, List<GdGoodsTypeProperty> propListForAdd,
			List<GdGoodsTypePropertyOption> optionListForUpdate, List<GdGoodsTypePropertyOption> optionListForAdd) {
		 if (propsListFront == null || propsListFront.isEmpty()) {
			 return ;
		 }
	   	 for (GdGoodsTypeProperty prop:propsListFront){
	   		 Long propId = prop.getDmId();
	   		 if (basicPropMap.containsKey(propId)) {	//存在，更新
	   			 
	   			 //商品属性是否需要变更
	   			 GdGoodsTypeProperty propDb = basicPropMap.get(propId);
	   			 Integer dataType = prop.getDataType();
	   			 String name = prop.getName();
	   			 Integer required = prop.getRequired();
	   			 Integer status = prop.getStatus();
	   			 List<GdGoodsTypePropertyOption> options = prop.getOptions();
	   			 boolean propNeedUpdate = false;
	   			 if (StringUtils.isNotBlank(name) && !name.equals(propDb.getName())){
	   				propNeedUpdate = true;
	   				propDb.setName(name);
	   			 }
	   			 if (dataType!= null && dataType != propDb.getDataType()){
	   				propNeedUpdate = true;
	   				propDb.setDataType(dataType);
	   			 }
	   			 if (required!= null && required != propDb.getRequired()){
	   				propNeedUpdate = true;
	   				propDb.setRequired(required);
	   			 }
	   			 if (status!= null && status != propDb.getStatus()){
	   				propNeedUpdate = true;
	   				propDb.setStatus(status);
	   			 }
	   			 if (propNeedUpdate) {
	   				 propListForUpdate.add(propDb);
	   			 }
	   			 //商品属性值是否需要变更
	   			 if(options == null || options.isEmpty()){
	   				 continue;
	   			 }
   				 for (GdGoodsTypePropertyOption option:options) {
       				 if (propOptionsMapDB.containsKey(option.getDmId())) { //存在，更新
       					 GdGoodsTypePropertyOption optionDb = propOptionsMapDB.get(option.getDmId());
       					 boolean optionNeedUpdate = false;
       					 String optionName = option.getVal();
       					 Integer optionStatus = option.getStatus();
       					 if (utils.StringUtils.isNotBlank(optionName) && !optionName.equals(optionDb.getVal())) {
       						optionNeedUpdate = true;
       						optionDb.setVal(optionName);
       					 }
       					 if (optionStatus!=null && optionStatus != optionDb.getStatus()) {
							optionNeedUpdate = true;
							optionDb.setStatus(optionStatus);
						 }
       					 if (optionNeedUpdate){
       						optionListForUpdate.add(optionDb);
       					 }
       				 } else { //添加
       					optionListForAdd.add(option);
       				 }
	   			 }
	   		 } else {	//添加
	   			propListForAdd.add(prop);
	   		 }
	   	 }
	}
	/**
     * 
     * 更新商品分类
     * 
     * @author guangxi.zhang
     * @param uid 用户id
     * @param goodsTypeBo 商品分类信息
     * @return dmId
     * @throws DaoException
     */
    private void updateGoodsTypeDetail(long uid, GdGoodsType goodsTypeFront, GdGoodsType goodsTypeDb) throws ServiceException {
        try {
            log.info("updateGoodsTypeDetail>>>>>>更新商品分类明细开始!");
            Long parentId = goodsTypeFront.getParentId();
            String name = goodsTypeFront.getName();
            
            boolean needUpdate = false;
            if (utils.StringUtils.isNotBlank(name) && !name.equals(goodsTypeDb.getName())) {
            	needUpdate = true;
            	goodsTypeDb.setName(name);
            }
            if (parentId != null && !parentId.equals(goodsTypeDb.getParentId())) {
            	needUpdate = true;
            	goodsTypeDb.setParentId(parentId);
            }
            
            if (needUpdate) {
            	dao.modVoNotNull(goodsTypeDb);
            }
            log.info("updateGoodsTypeDetail>>>>>>更新商品分类明细结束!");
        }
        catch (Exception e) {
            log.error("updateGoodsTypeDetail>>>>>>更新商品分类明细失败!",e);
            throw new ServiceException(e);
        }
    }
    
    /**
     * 
     * 增加商品分类
     * 
     * @version 2016年5月29日下午4:50:37
     * @author guangxi.zhang
     * @param uid 用户id
     * @param gdGoodsTypeBo 商品分类业务对象
     * @return GdGoodsType 商品分类对象
     * @throws ServiceException
     */
    private GdGoodsType addGdGoodsTypeDetail(long uid, GdGoodsTypeBo gdGoodsTypeBo) throws ServiceException {
        try {
            log.info("addGdGoodsTypeDetail>>>>>>增加商品分类开始!gdGoodsTypeBo:" + JSONObject.toJSONString(gdGoodsTypeBo));
            Integer maxSeq = dao.getMaxSeqByParentId(gdGoodsTypeBo.getParentId());
            GdGoodsType vo = new GdGoodsType();
            vo.setDmId(fifteenLongId.nextId());
            vo.setName(gdGoodsTypeBo.getName());
            vo.setPinyin(Pinyin4jUtil.getPinYin(gdGoodsTypeBo.getName()));
            vo.setParentId(gdGoodsTypeBo.getParentId());
            vo.setLevel(gdGoodsTypeBo.getLevel());
            vo.setCreateOn(DateUtil.getTimeInSeconds());
            vo.setCreateBy(uid); 
            vo.setUpdateOn(DateUtil.getTimeInSeconds());
            vo.setStatus(DisableEnum.enable.getValue());
            vo.setSeq(maxSeq);		
            vo.setUpdateBy(uid); 
            getDao().addVo(vo);
            log.info("addGdGoodsTypeDetail>>>>>>增加商品分类结束!");
            return vo;
        }
        catch (Exception e) {
            log.error(e, "addGdGoodsTypeDetail>>>>>>增加商品分类失败!",e);
            throw new ServiceException(e);
        }
    }
    
    /**
     * 
     * 添加商品分类属性
     * 
     * @version 2016年5月29日下午9:26:28
     * @author guangxi.zhang
     * @param gt 商品类目对象
     * @param goodsTypeBoList 商品类目属性列表
     * @param isBasic 是否是基本属性
     * @param isSale 是否是销售属性
     * @throws ServiceException 异常信息
     */
    private void addGoodsTypeProp(GdGoodsType gt, List<GdGoodsTypePropertyBo> goodsTypeBoList, boolean isBasic,
            boolean isSale) throws ServiceException {
        try {
            log.info("addGoodsTypeProp>>>>>>添加商品分类属性开始!");
            if (goodsTypeBoList == null) {
                log.info("addGoodsTypeProp>>>>>商品分类基本属性对象为空!");
                return;
            }
            
            List<GdGoodsTypeProperty> props = new ArrayList<GdGoodsTypeProperty>();
            List<GdGoodsTypePropertyOption> options = new ArrayList<GdGoodsTypePropertyOption>();
            
            log.info("addGoodsTypeProp>>>>>商品分类属性集合对象:" + JSONObject.toJSONString(goodsTypeBoList));
            /** 遍历商品类目属性 */
            for (GdGoodsTypePropertyBo PropBo : goodsTypeBoList) {
                GdGoodsTypeProperty goodsTypeProp = new GdGoodsTypeProperty();
                goodsTypeProp.setDmId(fifteenLongId.nextId());
                goodsTypeProp.setGoodsTypeId(gt.getDmId());
                goodsTypeProp.setName(PropBo.getName());
                goodsTypeProp.setDataType(PropBo.getDisplayType());
                goodsTypeProp.setIsSale(isSale ? YesOrNoEnum.YES.getValue() : YesOrNoEnum.NO.getValue());
                goodsTypeProp.setIsKey(isBasic ? YesOrNoEnum.YES.getValue() : YesOrNoEnum.NO.getValue());
                goodsTypeProp.setRequired(PropBo.getRequired());
                goodsTypeProp.setStatus(DisableEnum.enable.getValue());
                props.add(goodsTypeProp);
                /** 遍历商品属性值 */
                List<GdGoodsTypePropertyOptionBO> goodsTypePropBoList = PropBo.getValues();
                log.info("addGoodsTypeProp>>>>>商品分类属性值集合:" + JSONObject.toJSONString(goodsTypePropBoList));
                if (goodsTypePropBoList != null && goodsTypeBoList.size() > 0) {
                    for (GdGoodsTypePropertyOptionBO propOptionBo : goodsTypePropBoList) {
                        GdGoodsTypePropertyOption option = new GdGoodsTypePropertyOption();
                        option.setDmId(fifteenLongId.nextId());
                        option.setGoodsTypeProId(goodsTypeProp.getDmId());
                        option.setStatus(DisableEnum.enable.getValue());
                        option.setVal(propOptionBo.getVal());
                        options.add(option);
                    }
                }
                
            }
            log.info("addGoodsTypeProp>>>>>>待增加商品类目属性:" + JSONObject.toJSONString(props));
            log.info("addGoodsTypeProp>>>>>>待增加的商品类目属性值:" + JSONObject.toJSONString(options));
            gtProService.addList(props);
            gtProOptionService.addList(options);
            log.info("addGoodsTypeProp>>>>>>添加商品分类属性结束!");
        }
        catch (Exception e) {
            log.error("addGoodsTypeProp>>>>>增加商品属性失败!", e);
            throw new ServiceException(e);
        }
        
    }
    
    /**
     * 
     * 添加类目议价
     * 
     * @version 2016年5月26日下午7:58:26
     * @author guangxi.zhang
     * @param gt 商品分类实体
     * @throws ServiceException
     */
    
    private void addCategoryRate(GdGoodsType gt) throws ServiceException {
        try {
            log.error("addCategoryRate>>>>>>添加商品类目议价开始!");
            if (gt == null) {
                log.info("addCategoryRate>>>>>>商品类目对象为空！");
                return;
            }
            GdGoodsCategroyRate rate = new GdGoodsCategroyRate(gt.getDmId(), null, null, null, null);
            rateService.addVo(rate);
            log.error("addCategoryRate>>>>>>添加商品类目议价结束!");
        }
        catch (Exception e) {
            log.error("addCategoryRate>>>>>>添加商品类目议价失败!" ,e);
            throw new ServiceException(e);
        }
    }
 
	@Override
	public List<GdGoodsType> getParentGoodsTypeList(Long goodsTypeId) throws ServiceException {
		 List<GdGoodsType> resultList = new ArrayList<>();
		//获取当前分类信息
		GdGoodsType goodsType = this.findVo(goodsTypeId, null);
		if (goodsType == null) {
			return resultList;
		} else {
			resultList.add(goodsType);
			resultList.addAll(getParentGoodsTypeList(goodsType.getParentId()));
			return resultList;
		}
	}

	@Override
	public GdGoodsType getParentGoodsType(Long goodsTypeId) throws ServiceException {
		//获取当前分类信息
		GdGoodsType goodsType = this.findVo(goodsTypeId, null);
		if (goodsType != null) {
			goodsType = this.findVo(goodsType.getParentId(), null);
			return goodsType;
		}else {
			return null;
		}
	}
}