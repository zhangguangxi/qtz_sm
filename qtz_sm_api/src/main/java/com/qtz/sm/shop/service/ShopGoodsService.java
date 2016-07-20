package com.qtz.sm.shop.service;
import java.util.List;
import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.shop.page.ShopGoodsPage;
import com.qtz.sm.shop.vo.ShopGdGoodsVo;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopGoodsSkuVo;
import com.qtz.sm.shop.vo.ShopGoodsVo;
import com.qtz.sm.shop.vo.ShopValueVo;
/**
 * <p>Title:ShopGoodsService</p>
 * <p>Description:便利店商品服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选 - Laven
 * @version v1.0 2016-04-26
 */
public interface ShopGoodsService extends BaseService<com.qtz.sm.shop.vo.ShopGoods,Long> {
	

	/**
	 * 根据便利店ID跟多个商品ID，批量修改便利店的商品上下架状态
	 * @Description:TODO
	 * @param map
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月7日 下午5:39:36
	 */
	public void updateBatchStatus(Map<String,Object>  map) throws ServiceException;
	/**
	 * 批量修改便利店的商品分类
	 * @Description:TODO
	 * @param map
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月18日 下午3:39:36
	 */
	public void updateBatch(Map<String,Object>  map) throws ServiceException;
	
	/**
	 * 
	 * @Description:根据商品ID获取sku属性值
	 * @param goodsId
	 * @return
	 * @throws ServiceException
	 * List<ShopValueVo>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月25日 下午3:07:57
	 */
	public List<ShopValueVo> findSkuList(Long goodsId) throws ServiceException;
	
	/**
	 * 
	 * @Description:根据便利店ID和商品ID查询商品所对应的sku属性值
	 * @param shopGoods
	 * @return
	 * @throws ServiceException
	 * List<ShopValueVo>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月25日 下午3:07:57
	 */
	public List<ShopValueVo> findSkuList(ShopGoods shopGoods) throws ServiceException;
	
	/**
	 * 
	 * @Description:根据便利店ID 分页查询商品列表
	 * @param shopGoodsPage
	 * @return
	 * @throws ServiceException
	 * Pager<ShopGdGoodsVo,Long>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月25日 下午5:33:05
	 */
	public Pager<ShopGdGoodsVo, Long> findGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws ServiceException;
	/**
	 * 
	 * @Description:根据便利店ID 分页查询商品列表  通过List分页
	 * @param shopGoodsPage
	 * @return
	 * @throws ServiceException
	 * Pager<ShopGdGoodsVo,Long>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月28日 下午3:33:05
	 */
	public Pager<ShopGdGoodsVo, Long> queryGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws ServiceException;

	/**
	 * 
	 * @Description:根据便利店ID和商品ID获取商品详情
	 * @param shopGoods
	 * @return
	 * @throws ServiceException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月27日 
	 */
	ShopGoodsVo getShopGoodsVo(ShopGoods shopGoods) throws ServiceException;

	/**
	 * 
	 * @Description:根据便利店ID和商品ID获取商品下面所有SKU信息
	 * @param shopGoods
	 * @return
	 * @throws ServiceException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月27日 
	 */
	List<ShopGoodsSkuVo> getShopGoodsSkus(ShopGoods shopGoods)throws ServiceException;
	
	/**
	 * 
	 * @Description:根据便利店ID和商品分类ID获取商品信息
	 * @param shopGoods
	 * @return
	 * @throws ServiceException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月30日 
	 */
	List<ShopGoodsVo> getShopGoodsVoList(ShopGoods shopGoods) throws ServiceException;
}