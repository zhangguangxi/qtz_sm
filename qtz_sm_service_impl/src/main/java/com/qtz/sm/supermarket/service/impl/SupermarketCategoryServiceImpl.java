package com.qtz.sm.supermarket.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespCode;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.solr.SearchShopSolrService;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.model.GdGoodsSkuPropBo;
import com.qtz.sm.goods.service.GdGoodsPropertyValService;
import com.qtz.sm.goods.service.GdGoodsSkuService;
import com.qtz.sm.search.page.SearchPage;
import com.qtz.sm.shop.vo.SkuPro;
import com.qtz.sm.shop.vo.SkuProDetail;
import com.qtz.sm.supermarket.dao.SupermarketCategoryDao;
import com.qtz.sm.supermarket.service.SupermarketCategoryAssociateService;
import com.qtz.sm.supermarket.service.SupermarketCategoryService;
import com.qtz.sm.supermarket.vo.SupermarketCategory;
import com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate;
import com.qtz.sm.supermarket.vo.SupermarketCategoryVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsSkuVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsVo;

/**
 * Title:SupermarketCategoryServiceImpl Description:超市类目服务实现类 Copyright:
 * Copyright (c) 2016 Company: 深圳擎天柱信息技术有限公司
 * 
 * @author Sunxuan - Laven
 * @version v1.0 2016-04-29
 */
@Service("supermarketCategoryServiceImpl")
public class SupermarketCategoryServiceImpl extends BaseServiceImpl<com.qtz.sm.supermarket.vo.SupermarketCategory, Long>
		implements SupermarketCategoryService {
	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(SupermarketCategoryServiceImpl.class);
	/** 注入超市类目DAO接口类 */
	@Resource(name = "supermarketCategoryDaoImpl")
	private SupermarketCategoryDao dao;

	@Resource(name = "supermarketCategoryAssociateServiceImpl")
	private SupermarketCategoryAssociateService supermarketCategoryAssociateService;
	
	@Resource(name = "searchShopSolrServiceImpl")
	private SearchShopSolrService searchShopSolrService;
    /**
     * 注入商品SKU服务接口类
     */
    @Resource(name = "gdGoodsSkuServiceImpl")
  	private GdGoodsSkuService gdGoodsSkuService;
    
    /**
     * 注入商品属性服务接口类
     */
    @Resource(name = "gdGoodsPropertyValServiceImpl")
  	private GdGoodsPropertyValService gdGoodsPropertyValService;
    
	/**
	 * 【取得】业务DAO对象
	 * 
	 * @return 业务DAO对象
	 */
	@Override
	protected BizDao<com.qtz.sm.supermarket.vo.SupermarketCategory, Long> getDao() {
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

	@Override
	public SupermarketCategory findById(Long id) throws ServiceException {
		SupermarketCategory supermarketCategory = this.findVo(id, null);
		// pid!=0 即为二级分类 则需要查出关联的商品分类信息
		if (null != supermarketCategory && supermarketCategory.getPid() != 0) {
			List<SupermarketCategoryAssociate> supermarketCategoryAssociateList = supermarketCategoryAssociateService
					.findBySupermarketCategoryId(id);
			supermarketCategory.setSupermarketCategoryAssociateList(supermarketCategoryAssociateList);
		}
		return supermarketCategory;
	}

	public void addInfo(SupermarketCategory supermarketCategory) throws ServiceException {
		boolean flag = false;
		//先添加主表
		supermarketCategory = super.addVo(supermarketCategory);
		
		if (supermarketCategory != null && supermarketCategory.getSupermarketCategoryAssociateList() != null
				&& supermarketCategory.getSupermarketCategoryAssociateList().size() > 0) {
			SupermarketCategoryAssociate supermarketCategoryAssociate = new SupermarketCategoryAssociate();
			for (int i = 0; i < supermarketCategory.getSupermarketCategoryAssociateList().size(); i++) {
				// 把二级分类ID放入
				supermarketCategory.getSupermarketCategoryAssociateList().get(i).setSupermarketCategoryId(supermarketCategory.getDmId());
				//第三级商品分类编号
				if(supermarketCategory.getSupermarketCategoryAssociateList().get(i).getLevel()==3){
					supermarketCategoryAssociate = supermarketCategory.getSupermarketCategoryAssociateList().get(i);
					supermarketCategoryAssociate.setSupermarketId(supermarketCategory.getSupermarketId());
				}
			}
			// 判断该商品分类是否已经被关联
			List<SupermarketCategoryAssociate> list = supermarketCategoryAssociateService.getList(supermarketCategoryAssociate);
			if (null != list && list.size() > 0) {
				throw new ServiceException(ExceptionCode.supermarket_category_id_have, "已有其他分类关联.");
			}else{
				flag = true;
			}
		}
		//是否添加管理商品分类
		if (flag) {
			// 把商品123级分类挂到 超市二级分类下
			supermarketCategoryAssociateService.addCategoryAssociate(supermarketCategory);
		}
	}

	public void updateInfo(SupermarketCategory supermarketCategory) throws ServiceException {
		
		// 判断该商品分类是否已经被关联
		SupermarketCategoryAssociate supermarketCategoryAssociate = new SupermarketCategoryAssociate();
		for (int i = 0; i < supermarketCategory.getSupermarketCategoryAssociateList().size(); i++) {
			// 把二级分类ID放入
			supermarketCategory.getSupermarketCategoryAssociateList().get(i).setSupermarketCategoryId(supermarketCategory.getDmId());
			//第三级商品分类编号
			if(supermarketCategory.getSupermarketCategoryAssociateList().get(i).getLevel()==3){
				supermarketCategoryAssociate = supermarketCategory.getSupermarketCategoryAssociateList().get(i);
				supermarketCategoryAssociate.setSupermarketId(supermarketCategory.getSupermarketId());
			}
		}
		List<SupermarketCategoryAssociate> list = supermarketCategoryAssociateService.getList(supermarketCategoryAssociate);
		//若存在并且与当前修改的超市二级分类编号不相同 则不能修改
		if (null != list && list.size() > 0 
				&& !supermarketCategory.getDmId().equals(list.get(0).getSupermarketCategoryId()))  {
			throw new ServiceException(ExceptionCode.supermarket_category_id_have, "已有其他分类关联.");
		}
		//修改信息
		this.modVoNotNull(supermarketCategory);
		// 把商品123级分类挂到 超市二级分类下
		supermarketCategoryAssociateService.addCategoryAssociate(supermarketCategory);
	}

	@Override
	public List<SupermarketCategoryVo> getAll(Double latitude,Double longitude) throws ServiceException {
		try {
			List<SupermarketCategoryVo> list = dao.findFirstLevelAll(1);
			SearchPage page = new SearchPage();
			page.setLatitude(latitude);
			page.setLongitude(longitude);
			Map<String,String> cczxInfo= (Map<String, String>) searchShopSolrService.searchCCZXInfo(page);
			String cczxId = cczxInfo.get("cczxID");
			for(SupermarketCategoryVo vo : list)
			{
				Map<String,String> param = new HashMap<String,String>();
				param.put("supermarketCategoryId", String.valueOf(vo.getDmId()));
				param.put("cczxId", cczxId);
				List<SupermarketGoodsVo> goodsList = dao.getGoodsList(param);
				vo.setGoodsVoList(goodsList);
			}
			return list;
		} catch (DaoException e) {
			log.error("获取超市首页分类以及商品信息出现系统错误！", e);
            throw new ServiceException(RespCode.getGoodsInfo_error,"获取超市首页分类以及商品信息出现系统错误.");
		}
	}

	@Override
	public SupermarketGoodsVo getGoodsDetail(Long cczxId, Long goodsId) throws ServiceException {
		try {
			//根据仓储中心ID和商品ID查询商品所对应的sku属性值
			SupermarketGoodsVo goodsVo = dao.getSupermarketGoodsDetail(goodsId);
			//查询商品属性值
			List<GdGoodsPropertyBo> goodsProList = gdGoodsPropertyValService.getGoodsPropBoList(goodsVo.getGoodsTypeId(), goodsId);
			goodsVo.setCczxId(cczxId);
			goodsVo.setSkuPro(getSkuPros(cczxId, goodsId, goodsVo.getGoodsTypeId()));
			goodsVo.setGoodsProList(goodsProList);
			return goodsVo;
		} catch (DaoException e) {
			log.error("获取商品详情出现系统错误！", e);
            throw new ServiceException(RespCode.getGoodsInfo_error,"获取商品详情出现系统错误.");
		}
	}
	
	@Override
	public SupermarketGoodsVo getGoodsSpecifications(Long cczxId, Long goodsId) throws ServiceException {
		try {
			//根据仓储中心ID和商品ID查询商品所对应的sku属性值
			SupermarketGoodsVo goodsVo = dao.getSupermarketGoodsVo(goodsId);
			goodsVo.setCczxId(cczxId);
			goodsVo.setSkuPro(getSkuPros(cczxId, goodsId, goodsVo.getGoodsTypeId()));
			return goodsVo;
		} catch (DaoException e) {
			log.error("获取商品规格详情出现系统错误！", e);
            throw new ServiceException(RespCode.getGoodsInfo_error,"获取商品规格详情出现系统错误.");
		}
	}
	
	private SkuPro getSkuPros(Long cczxId, Long goodsId, Long goodsTypeId)throws ServiceException
	{ 
		try {
			Map<String,Long> param = new HashMap<String,Long>();
			param.put("goodsId", goodsId);
			param.put("cczxId", cczxId);
			List<SupermarketGoodsSkuVo> list = dao.getShopGoodsSkus(param);
			List<GdGoodsSkuPropBo> skuPros = gdGoodsSkuService.getAllSkuPropBoList(goodsTypeId, goodsId, null);
			List<GdGoodsSkuPropBo> removeList = new ArrayList<GdGoodsSkuPropBo>();
			Map<String,SupermarketGoodsSkuVo> skuVoMap = new HashMap<String,SupermarketGoodsSkuVo>();
			//删除不存在的规格属性
			for(GdGoodsSkuPropBo skuPropBo : skuPros)
			{
				if(-1==skuPropBo.getSkuId().longValue())
				{
					removeList.add(skuPropBo);
				}
			}
			skuPros.removeAll(removeList);
			Set<String> set = new HashSet<String>();
			Set<String> valueIdSet = new HashSet<String>();
			int size = 0;
			for(GdGoodsSkuPropBo skuPropBo : skuPros)
			{
				List<GdGoodsPropertyBo> propsBo = skuPropBo.getProps();
				if(propsBo!=null && propsBo.size()!=0)
				{
					size = propsBo.size();
					for(int i=0;i<size;i++)
					{
						GdGoodsPropertyBo propBo = propsBo.get(i);
						String key = i+"_"+propBo.getPropId()+"-"+propBo.getPropIdName()+"_"+propBo.getPropValId()+"-"+propBo.getPropValName();
						set.add(key);
					}
				}
				
				//商品下sku是否包含该属性，默认不包含，如果库存不为0，则表示该商品可售，否则该sku不可售
				boolean flag = false;
				for(SupermarketGoodsSkuVo vo : list)
				{
					//如果库存信息不为0
					if( vo.getSkuId().longValue()==skuPropBo.getSkuId().longValue() )
					{
						vo.setSkuValueIdStr(skuPropBo.getSkuValueIdStr());
						if(vo.getStockQuantity().intValue()!=0)
						{
							flag = true;
						}
					}
				}
				if( !flag )
				{
					skuPropBo.setSkuId(-1L);
				}else
				{
					valueIdSet.add(skuPropBo.getSkuValueIdStr());
				}
			}
			//转换，便于获取sku价格和ID属性
			for(SupermarketGoodsSkuVo vo : list)
			{
				skuVoMap.put(vo.getSkuValueIdStr(), vo);
			}
			SkuPro skuPro = new SkuPro();
			SkuPro chileSkuPro = null;
			SkuProDetail skuProDetail = null;
			for(int num = 0;num<size;num++)
			{ 
				chileSkuPro = new SkuPro();
				chileSkuPro.setLevel(num);
				Iterator<String> iterator = set.iterator();
				List<SkuProDetail> proDetails = new ArrayList<SkuProDetail>();
				while(iterator.hasNext())
				{
					skuProDetail = new SkuProDetail();
					String key = iterator.next();
					String[] pros = key.split("_");
					if(num==Integer.parseInt(pros[0])){
						String[] nameProp = pros[1].split("-");
						chileSkuPro.setProId(Long.parseLong(nameProp[0]));
						chileSkuPro.setName(nameProp[1]);
						String[] valueProp = pros[2].split("-");
						skuProDetail.setProDetailId(Long.parseLong(valueProp[0]));
						skuProDetail.setName(valueProp[1]);
						proDetails.add(skuProDetail);
					}
				}
				chileSkuPro.setProDetails(proDetails);
				if(num==0)
				{
					skuPro = chileSkuPro;
					for(SkuProDetail detail : proDetails)
					{
						for(String key : valueIdSet)
	    				{
	    					//设置规格组合属性
	    					if(key.indexOf(String.valueOf(detail.getProDetailId()))>-1)
	    					{
	    						detail.setStatus(true);
	    						break;
	    					}
	    				}
						//最底层需要设置价格和skuID属性
						if(num==(size-1))
						{
							SupermarketGoodsSkuVo goodsSkuVo = skuVoMap.get(detail.getProDetailId());
							detail.setSkuId(goodsSkuVo.getSkuId());
							detail.setPrice(goodsSkuVo.getPrice());
						}
					}
				}else
				{
					setSkuPro(skuPro, chileSkuPro,num,size,skuVoMap,"",skuPros,valueIdSet);
				}
			}
			return skuPro;
		} catch (DaoException e) {
			log.error("获取商品规格详情出现系统错误！", e);
            throw new ServiceException(RespCode.getGoodsInfo_error,"获取商品规格详情出现系统错误.");
		}
	}
	private void setSkuPro(SkuPro skuPro,SkuPro chileSkuPro,int level,int size,Map<String,
					SupermarketGoodsSkuVo> skuVoMap,String head,List<GdGoodsSkuPropBo> skuPros,Set<String> valueIdSet)
    {
	 	//是否是上下级的关系，如果子规格比当前规格大1，则认为是上下级关系，否则在当前规格的子规格里面递归查询
    	boolean relation = false;
    	if((level-1)==skuPro.getLevel())
    	{
    		relation = true;
    	}
    	List<SkuProDetail> proDetails = skuPro.getProDetails();
    	for(SkuProDetail spd : proDetails)
    	{
    		String idString = null;
    		if(StringUtil.isEmpty(head))
    		{
    			idString = String.valueOf(spd.getProDetailId());
    		}else
    		{
    			idString =head+","+ spd.getProDetailId();
    		}
    		if(relation)
    		{
    			SkuPro newpro = new SkuPro();
    			newpro.setProId(chileSkuPro.getProId());
    			newpro.setName(chileSkuPro.getName());
    			newpro.setLevel(chileSkuPro.getLevel());
    			List<SkuProDetail> newproDetails = new ArrayList<SkuProDetail>();
    			for(SkuProDetail detail : chileSkuPro.getProDetails())
    			{
    				SkuProDetail newDetail = new SkuProDetail();
    				newDetail.setProDetailId(detail.getProDetailId());
    				newDetail.setName(detail.getName());
    				newDetail.setSkuPro(detail.getSkuPro());
    				String skuValueIdStr = idString+','+newDetail.getProDetailId();
    				for(String key : valueIdSet)
    				{
    					//设置规格组合属性
    					if(key.indexOf(skuValueIdStr)>-1)
    					{
    						newDetail.setStatus(true);
    						break;
    					}
    				}
    				//最底层需要设置价格和skuID属性
    				if(level==(size-1))
    				{
    					SupermarketGoodsSkuVo goodsSkuVo = skuVoMap.get(skuValueIdStr);
    					if(goodsSkuVo!=null)
    					{
    						newDetail.setSkuId(goodsSkuVo.getSkuId());
    						newDetail.setPrice(goodsSkuVo.getPrice());
    					}
					}
    				newproDetails.add(newDetail);
    			}
    			newpro.setProDetails(newproDetails);
    			spd.setSkuPro(newpro);
    		}else
    		{
    			//在当前规格的子规格里面递归查询
    			setSkuPro(spd.getSkuPro(), chileSkuPro,level,size,skuVoMap,idString,skuPros,valueIdSet);
    		}
    	}
    }

	@Override
	public List<SupermarketGoodsVo> getEnjoyGoodsList(Long cczxId) throws ServiceException {
		try {
			return dao.getEnjoyGoodsList(cczxId);
		} catch (DaoException e) {
			throw new ServiceException(e.getErrorType(), e.getErrorTitle(), e.getErrorMessage());
		}
	}
}