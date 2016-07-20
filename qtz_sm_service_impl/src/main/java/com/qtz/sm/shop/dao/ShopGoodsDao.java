package com.qtz.sm.shop.dao;
import java.util.List;
import java.util.Map;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.shop.page.ShopGoodsPage;
import com.qtz.sm.shop.vo.ShopGdGoodsVo;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopGoodsSkuVo;
import com.qtz.sm.shop.vo.ShopGoodsVo;
import com.qtz.sm.shop.vo.ShopValueVo;
/**
 * <p>Title:ShopGoodsDao</p>
 * <p>Description:便利店商品DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选 - Laven
 * @version v1.0 2016-04-26
 */
public interface ShopGoodsDao extends BizDao<com.qtz.sm.shop.vo.ShopGoods,Long> {
	
	/**
	 * 根据便利店ID跟多个商品ID，批量修改便利店的商品上下架状态
	 * @Description:TODO
	 * @param map
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月7日 下午5:39:36
	 */
	void updateBatchStatus(Map<String,Object>  map) throws DaoException;
	/**
	 * 批量修改便利店的商品分类
	 * @Description:TODO
	 * @param map
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月18日 下午3:39:36
	 */
	void updateBatch(Map<String,Object>  map) throws DaoException;
	
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
	List<ShopValueVo> findSkuList(Long goodsId) throws DaoException;
	
	/**
	 * 
	 * @Description:根据便利店ID查询商品所对应的sku属性值
	 * @param shopGoods
	 * @return
	 * @throws ServiceException
	 * List<ShopValueVo>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月25日 下午3:07:57
	 */
	List<ShopValueVo> findSkuListByShopId(ShopGoods shopGoods) throws DaoException;
	
	/**
	 * 
	 * @Description:根据便利店ID 分页查询商品列表 List直接分页
	 * @param shopGoodsPage
	 * @return
	 * @throws ServiceException
	 * Pager<ShopGdGoodsVo,Long>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月28日 下午3:33:05
	 */
	List<ShopGdGoodsVo> queryGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws DaoException;
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
	List<ShopGdGoodsVo> findGdGoodsListByShopId(ShopGoodsPage shopGoodsPage) throws DaoException;
	/**
	 * 
	 * @Description:根据便利店ID 分页查询商品列表条数
	 * @param shopGoodsPage
	 * @return
	 * @throws DaoException
	 * Integer
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月26日 下午4:29:00
	 */
	Integer findGdGoodsListByShopIdCount(ShopGoodsPage shopGoodsPage) throws DaoException;

	/**
	 * 
	 * @Description:根据便利店ID和商品ID获取商品详情
	 * @param shopGoods
	 * @return
	 * @throws DaoException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月27日
	 */
	ShopGoodsVo getShopGoodsVo(ShopGoods shopGoods) throws DaoException;

	/**
	 * 
	 * @Description:根据便利店ID和商品ID获取商品下面所有SKU信息
	 * @param shopGoods
	 * @return
	 * @throws DaoException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月27日
	 */
	List<ShopGoodsSkuVo> getShopGoodsSkus(ShopGoods shopGoods)throws DaoException;

	/**
	 * 
	 * @Description:根据便利店分类ID获取下面所有商品信息(前台APP使用)
	 * @param shopGoods
	 * @return
	 * @throws DaoException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月30日
	 */
	List<ShopGoodsVo> getShopGoodsVoList(ShopGoods shopGoods)throws DaoException;

	/**
	 * 
	 * @Description:根据便利店商品信息获取下面商品规格统计信息
	 * @param shopGoods
	 * @return
	 * @throws DaoException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月30日
	 */
	Integer getSpecificationCount(ShopGoods shopGoods)throws DaoException;

	/**
	 * 
	 * @Description:根据便利店商品信息获取下面商品最低价格
	 * @param shopGoods
	 * @return
	 * @throws DaoException
	 * @exception:
	 * @author: yangwei
	 * @time:2016年5月30日
	 */
	Double getGoodsMinPrice(ShopGoods shopGoods)throws DaoException;
}