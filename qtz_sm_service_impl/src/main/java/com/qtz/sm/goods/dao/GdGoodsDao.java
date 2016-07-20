package com.qtz.sm.goods.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.shop.vo.ShopValueVo;
/**
 * <p>Title:GdGoodsDao</p>
 * <p>Description:商品DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsDao extends BizDao<GdGoods,java.lang.Long> {

	/**
	 * 根据商品ID获取属性列
	 * @author yangwei
	 * @param goodsId		商品ID
	 * @throws DaoException
	 */
	List<ShopValueVo> findSkuList(Long goodsId) throws DaoException;
	
}