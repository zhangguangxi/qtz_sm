package com.qtz.sm.supermarket.dao;
import java.util.List;
import java.util.Map;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.supermarket.vo.SupermarketCategoryVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsSkuVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsVo;
/**
 * <p>Title:SupermarketCategoryDao</p>
 * <p>Description:超市类目DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-29
 */
public interface SupermarketCategoryDao extends BizDao<com.qtz.sm.supermarket.vo.SupermarketCategory,Long> {

	/**
     * 获取超市一级分类VO信息
     *
     * @return 商品分类
     * @throws DaoException
     */
	List<SupermarketCategoryVo> findFirstLevelAll(int level)throws DaoException;


	/**
     * 根据一级分类信息获取6款商品信息
     *
     * @return 商品信息
     * @throws DaoException
     */
	List<SupermarketGoodsVo> getGoodsList(Map<String,String> param)throws DaoException;

	/**
     * 根据商品ID获取商品信息(主图)
     *
     * @return 商品信息
     * @throws DaoException
     */
	SupermarketGoodsVo getSupermarketGoodsVo(Long goodsId)throws DaoException;

	/**
     * 根据商品ID获取商品SKU信息
     *
     * @return 商品信息
     * @throws DaoException
     */
	List<SupermarketGoodsSkuVo> getShopGoodsSkus(Map<String,Long> param)throws DaoException;

	/**
     * 根据商品ID获取商品详细信息(多图)
     *
     * @return 商品信息
     * @throws DaoException
     */
	SupermarketGoodsVo getSupermarketGoodsDetail(Long goodsId)throws DaoException;

	/**
     * 获取可能喜欢的超市商品信息
     *
     * @return 商品信息
     * @throws DaoException
     */
	List<SupermarketGoodsVo> getEnjoyGoodsList(Long cczxId)throws DaoException;
	
}