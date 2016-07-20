package com.qtz.sm.goods.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.common.Global;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.dao.GdGoodsTypePropertyOptionDao;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;

/**
 * <p>
 * Title:GdGoodsTypePropertyOptionDaoImpl
 * </p>
 * <p>
 * Description:商品分类属性值选项DAO实现类
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
@Repository("gdGoodsTypePropertyOptionDaoImpl")
public class GdGoodsTypePropertyOptionDaoImpl extends MyBaitsDaoImpl<GdGoodsTypePropertyOption, java.lang.Long>
        implements GdGoodsTypePropertyOptionDao {
    /** MYBatis命名空间名 */
    private static String preName = GdGoodsTypePropertyOptionDao.class.getName();
    
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
	public List<GdGoodsTypePropertyOption> getOptionsByGoodsType(Long goodsTypeId) throws DaoException {
		try {
			String statement = "getOptionsByGoodsType";
			if (null != preName && !preName.equals("")) {
				statement = preName + Global.SPLIT_DIAN + statement;
			}
			return getMyBaitsTemplate().getSqlSession().selectList(statement, goodsTypeId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}