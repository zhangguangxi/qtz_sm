package com.qtz.sm.batch.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.batch.vo.CsBatGoods;
/**
 * <p>Title:CsBatGoodsDao</p>
 * <p>Description:批发单与商品关系DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
public interface CsBatGoodsDao extends BizDao<com.qtz.sm.batch.vo.CsBatGoods,java.lang.Long> {

	/**
	 * 根据批发单ID获取商品详情信息
	 * @author yangwei
	 * @param orderId		批发单ID
	 * @throws DaoException
	 */
	List<CsBatGoods> getGoodsInfoByOrderId(Long orderId) throws DaoException;
	
}