package com.qtz.sm.goods.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.common.Global;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.dao.GdGoodsSkuRateDao;
import com.qtz.sm.goods.model.GdGoodsSkuRateExtOut;
import com.qtz.sm.goods.model.GdGoodsStock;
import com.qtz.sm.goods.page.GdGoodsSkuRatePage;
import com.qtz.sm.goods.page.GdGoodsStockPage;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;

/**
 * <p>Title:GdGoodsSkuRateDaoImpl</p>
 * <p>Description:商品SKU议价DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-19
 */
@Repository("gdGoodsSkuRateDaoImpl")
public class GdGoodsSkuRateDaoImpl extends MyBaitsDaoImpl<GdGoodsSkuRate, java.lang.Long> implements GdGoodsSkuRateDao {
	/** MYBatis命名空间名 */
	private static String preName = GdGoodsSkuRateDao.class.getName();

	/**
	 * 【取得】MYBatis命名空间名
	 * 
	 * @return MYBatis命名空间名
	 */
	@Override
	protected String getPreName() {
		return preName;
	}

	@Override
	public Pager<GdGoodsSkuRateExtOut, Long> queryRates(GdGoodsSkuRatePage page) throws DaoException {
		Pager<GdGoodsSkuRateExtOut, Long> flowPage = new Pager<GdGoodsSkuRateExtOut, Long>();
		try {
			String sqlidOne = "queryCountExt";
			String sqlidTwo = "queryListExt";
			if (null != preName && !preName.equals("")) {
				sqlidOne = preName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != preName && !preName.equals("")) {
				sqlidTwo = preName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getMyBaitsTemplate().getSqlSession().selectOne(sqlidOne, page);
			page.setRowCount(rowCount);
			List<GdGoodsSkuRateExtOut> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public Pager<GdGoodsStock, Long> listGoodsStock(GdGoodsStockPage page) throws DaoException {
		Pager<GdGoodsStock, Long> flowPage = new Pager<GdGoodsStock, Long>();
		try {
			String sqlidOne = "countGoodsStock";
			String sqlidTwo = "listGoodsStock";
			if (null != preName && !preName.equals("")) {
				sqlidOne = preName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != preName && !preName.equals("")) {
				sqlidTwo = preName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getMyBaitsTemplate().getSqlSession().selectOne(sqlidOne, page);
			page.setRowCount(rowCount);
			List<GdGoodsStock> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			flowPage.setList(list);
			flowPage.setRowCount(rowCount);
			return flowPage;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}