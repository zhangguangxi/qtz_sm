package com.qtz.sm.shop.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.common.CollectionUtil;
import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespCode;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.model.GdGoodsPropertyBo;
import com.qtz.sm.goods.model.GdGoodsSkuPropBo;
import com.qtz.sm.goods.service.GdGoodsPropertyValService;
import com.qtz.sm.goods.service.GdGoodsSkuService;
import com.qtz.sm.shop.dao.ShopGoodsDao;
import com.qtz.sm.shop.dao.ShopGoodsSkuDao;
import com.qtz.sm.shop.page.ShopGoodsPage;
import com.qtz.sm.shop.service.ShopGoodsService;
import com.qtz.sm.shop.vo.ShopGdGoodsVo;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopGoodsSkuVo;
import com.qtz.sm.shop.vo.ShopGoodsVo;
import com.qtz.sm.shop.vo.SkuPro;
import com.qtz.sm.shop.vo.SkuProDetail;
import com.qtz.sm.supermarket.vo.SupermarketGoodsSkuVo;
import com.qtz.sm.shop.vo.ShopValueVo;

/**
 * <p>Title:ShopGoodsServiceImpl</p>
 * <p>Description:便利店商品服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Service("shopGoodsServiceImpl")
public class ShopGoodsServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopGoods,Long> implements ShopGoodsService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopGoodsServiceImpl.class);
	
	/**注入便利店商品DAO接口类*/
	@Resource(name="shopGoodsDaoImpl")
    private ShopGoodsDao dao;
	
	/**注入便利店商品SkuDAO接口类*/
	@Resource(name="shopGoodsSkuDaoImpl")
	private ShopGoodsSkuDao shopGoodsSkuDao;
	
    /**
     * 注入商品属性服务接口类
     */
    @Resource(name = "gdGoodsSkuServiceImpl")
  	private GdGoodsSkuService gdGoodsSkuService;
    
    /**
     * 注入商品SKU服务接口类
     */
    @Resource(name = "gdGoodsPropertyValServiceImpl")
  	private GdGoodsPropertyValService gdGoodsPropertyValService;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopGoods,Long> getDao() {
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
	@Override
	public void updateBatch(Map<String,Object>  map) throws ServiceException{
		try {
			dao.updateBatch(map);
		} catch (Exception e) {
			log.error("批量修改便利店的商品分类出现系统错误！", e);
            throw new ServiceException(e.getMessage());
		}
		
	}
	
	@Override
	public List<ShopValueVo> findSkuList(Long goodsId) throws ServiceException {
		try {
			return dao.findSkuList(goodsId);
		} catch (Exception e) {
			log.error("根据商品ID获取sku属性值出现系统错误！", e);
            throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Pager<ShopGdGoodsVo, Long> findGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws ServiceException {
		Pager<ShopGdGoodsVo, Long> pager = new Pager<ShopGdGoodsVo, Long>();
		try {
			pager.setNowPage(shopGoodsPage.getNowPage());
			pager.setPageSize(shopGoodsPage.getPageSize());
			pager.setOrderField(shopGoodsPage.getOrderField());
			pager.setOrderDirection(false);
			
			Set<Long> set = null;
			//查询条件是skuID或者 库存的时候 需要先找出所有的商品ID进行过滤 然后分页查询商品
			if(null != shopGoodsPage.getSkuId() 
					|| null != shopGoodsPage.getStartActual()
					|| null != shopGoodsPage.getEndActual()){
				List<Long> list = shopGoodsSkuDao.findGoodIdList(shopGoodsPage);
				if(null == list || list.size() ==0){
					pager.setRowCount(0);
					pager.setList(new ArrayList<ShopGdGoodsVo>());
					return pager;
				}else{
					set = new HashSet<Long>(list);
				}
			}
			shopGoodsPage.setShopGoodsIds(set);
			Integer rowCount = dao.findGdGoodsListByShopIdCount(shopGoodsPage);
			List<ShopGdGoodsVo> list = dao.findGdGoodsListByShopId(shopGoodsPage);
			pager.setList(list);
			pager.setRowCount(rowCount);
			return pager;
		} catch (Exception e) {
			log.error("根据便利店ID分页查询商品列表出现系统错误！", e);
            throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public Pager<ShopGdGoodsVo, Long> queryGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws ServiceException {
		Pager<ShopGdGoodsVo, Long> pager = new Pager<ShopGdGoodsVo, Long>();
		try {
			pager.setNowPage(shopGoodsPage.getNowPage());
			pager.setPageSize(shopGoodsPage.getPageSize());
			pager.setOrderField(shopGoodsPage.getOrderField());
			pager.setOrderDirection(false);
			List<ShopGdGoodsVo> list = dao.queryGdGoodsListByShopId(shopGoodsPage);
			Integer rowCount = 0;
			if(list == null || list.size() == 0){
				pager.setList(new ArrayList<ShopGdGoodsVo>());
				pager.setRowCount(rowCount);
				return pager;
			}else{
				rowCount = list.size();
				//List 分页
				list = CollectionUtil.trunList(list, shopGoodsPage.getNowPage(), shopGoodsPage.getPageSize());
			}
			pager.setList(list);
			pager.setRowCount(rowCount);
			return pager;
		} catch (Exception e) {
			log.error("根据便利店ID分页查询商品列表出现系统错误！", e);
			throw new ServiceException(e.getMessage());
		}
	}
	@Override 
	public List<ShopValueVo> findSkuList(ShopGoods shopGoods) throws ServiceException {
		try {
			return dao.findSkuListByShopId(shopGoods);
		} catch (Exception e) {
			log.error("根据便利店ID查询商品所对应的sku属性值出现系统错误！", e);
            throw new ServiceException(e.getMessage());
		}
	}
	@Override
	public ShopGoodsVo getShopGoodsVo(ShopGoods shopGoods) throws ServiceException {
		try {
			ShopGoodsVo shopGoodsVo = dao.getShopGoodsVo(shopGoods);
			//查询商品属性值
			List<GdGoodsPropertyBo> goodsProList = gdGoodsPropertyValService.getGoodsPropBoList(shopGoodsVo.getGoodsTypeId(), shopGoodsVo.getGoodsId());
			List<ShopGoodsSkuVo> list = getShopGoodsSkus(shopGoods);
			List<GdGoodsSkuPropBo> skuPros = gdGoodsSkuService.getAllSkuPropBoList(shopGoodsVo.getGoodsTypeId(), shopGoodsVo.getGoodsId(), null);
			List<GdGoodsSkuPropBo> removeList = new ArrayList<GdGoodsSkuPropBo>();
			Map<String,ShopGoodsSkuVo> skuVoMap = new HashMap<String,ShopGoodsSkuVo>();
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
				for(ShopGoodsSkuVo vo : list)
				{
					//便于前台通过规格详情ID获取对应的sku信息
					if( vo.getSkuId().longValue()==skuPropBo.getSkuId().longValue())
					{
						vo.setSkuValueIdStr(skuPropBo.getSkuValueIdStr());
						flag = true;
					}
				}
				if( !flag )
				{
					skuPropBo.setSkuId(-1L);
				}
				else
				{
					valueIdSet.add(skuPropBo.getSkuValueIdStr());
				}
			}
			//转换，便于获取sku价格和ID属性
			for(ShopGoodsSkuVo vo : list)
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
							ShopGoodsSkuVo goodsSkuVo = skuVoMap.get(detail.getProDetailId());
							detail.setSkuId(goodsSkuVo.getSkuId());
							detail.setPrice(goodsSkuVo.getPrice());
						}
					}
				}else
				{
					setSkuPro(skuPro, chileSkuPro,num,size,skuVoMap,"",skuPros,valueIdSet);
				}
			}
			shopGoodsVo.setSkuPro(skuPro);
			shopGoodsVo.setGoodsProList(goodsProList);
			return shopGoodsVo;
		} catch (DaoException e) {
			log.error("根据便利店ID和商品ID获取商品详情出现系统错误！", e);
            throw new ServiceException(RespCode.shop_goods_not_have,"便利店商品规格不存在.");
		}
	}
	@Override
	public List<ShopGoodsSkuVo> getShopGoodsSkus(ShopGoods shopGoods) throws ServiceException {
		try {
			return dao.getShopGoodsSkus(shopGoods);
		} catch (DaoException e) {
			log.error("根据便利店ID和商品ID获取商品下面所有SKU信息出现系统错误！", e);
            throw new ServiceException(RespCode.shop_sku_not_have,"便利店sku不存在.");
		}
	}
	@Override
	public List<ShopGoodsVo> getShopGoodsVoList(ShopGoods shopGoods) throws ServiceException {
		try {
			return dao.getShopGoodsVoList(shopGoods);
		} catch (DaoException e) {
			log.error("根据便利店ID和商品分类ID获取商品信息出现系统错误！", e);
            throw new ServiceException(RespCode.shop_goods_not_have,"便利店商品不存在");
		}
	}
	
	private void setSkuPro(SkuPro skuPro,SkuPro chileSkuPro,int level,int size,Map<String,ShopGoodsSkuVo> skuVoMap,
			String head,List<GdGoodsSkuPropBo> skuPros,Set<String> valueIdSet)
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
    					ShopGoodsSkuVo goodsSkuVo = skuVoMap.get(skuValueIdStr);
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
	public void updateBatchStatus(Map<String, Object> map) throws ServiceException {
		try {
			dao.updateBatchStatus(map);
		} catch (DaoException e) {
			log.error("根据便利店ID跟多个商品ID，批量修改便利店的商品上下架状态出现系统错误！", e);
            throw new ServiceException(e.getMessage());
		}
		
	}
}