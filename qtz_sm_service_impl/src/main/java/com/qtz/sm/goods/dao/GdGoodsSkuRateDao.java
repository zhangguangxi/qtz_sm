package com.qtz.sm.goods.dao;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.model.GdGoodsSkuRateExtOut;
import com.qtz.sm.goods.model.GdGoodsStock;
import com.qtz.sm.goods.page.GdGoodsSkuRatePage;
import com.qtz.sm.goods.page.GdGoodsStockPage;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;

/**
 * <p>Title:GdGoodsSkuRateDao</p>
 * <p>Description:商品SKU议价DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-19
 */
public interface GdGoodsSkuRateDao extends BizDao<GdGoodsSkuRate, java.lang.Long> {

	Pager<GdGoodsSkuRateExtOut, Long> queryRates(GdGoodsSkuRatePage page) throws DaoException;

	Pager<GdGoodsStock, Long> listGoodsStock(GdGoodsStockPage page) throws DaoException;

}