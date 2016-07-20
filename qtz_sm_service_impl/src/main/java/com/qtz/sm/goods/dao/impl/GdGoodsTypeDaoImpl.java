package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.common.Global;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.dao.GdGoodsTypeDao;
import com.qtz.sm.goods.vo.GdGoodsType;
/**
 * <p>Title:GdGoodsTypeDaoImpl</p>
 * <p>Description:商品分类DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Repository("gdGoodsTypeDaoImpl")
public class GdGoodsTypeDaoImpl extends MyBaitsDaoImpl<GdGoodsType,java.lang.Long> implements GdGoodsTypeDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsTypeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	/**
	 * 获取某分类下子分类最大的排序序号
	 * @author 欧江波 928482427@qq.com
	 * @param parentId			父分类ID
	 * @return
	 * @throws DaoException
	 */
	@Override
	public Integer getMaxSeqByParentId(Long parentId) throws DaoException {
		try {
			String statement = "getMaxSeqByParentId";
			if (null != preName && !preName.equals("")) {
				statement = preName + Global.SPLIT_DIAN + statement;
			}
			Object obj = getMyBaitsTemplate().getSqlSession().selectOne(statement, parentId);
			if (obj == null) {
				return 0;
			} else {
				return (Integer)obj;
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}