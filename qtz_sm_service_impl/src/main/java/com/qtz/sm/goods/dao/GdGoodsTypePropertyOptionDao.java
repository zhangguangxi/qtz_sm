package com.qtz.sm.goods.dao;

import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;

/**
 * <p>
 * Title:GdGoodsTypePropertyOptionDao
 * </p>
 * <p>
 * Description:商品分类属性值选项DAO接口类
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
public interface GdGoodsTypePropertyOptionDao extends BizDao<GdGoodsTypePropertyOption, java.lang.Long> {
	/**
	 * 根据商品分类ID获取该分类下属性所有可选值列表
	 * @author 欧江波 928482427@qq.com
	 * @param goodsTypeId	商品分类ID
	 * @return
	 * @throws DaoException
	 */
	public List<GdGoodsTypePropertyOption> getOptionsByGoodsType(Long goodsTypeId) throws DaoException;
}