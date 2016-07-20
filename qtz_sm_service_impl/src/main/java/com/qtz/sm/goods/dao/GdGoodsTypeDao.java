package com.qtz.sm.goods.dao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.vo.GdGoodsType;
/**
 * <p>Title:GdGoodsTypeDao</p>
 * <p>Description:商品分类DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsTypeDao extends BizDao<GdGoodsType,java.lang.Long> {
	
	/**
	 * 获取某分类下子分类最大的排序序号
	 * @author 欧江波 928482427@qq.com
	 * @param parentId			父分类ID
	 * @return
	 * @throws DaoException
	 */
	Integer getMaxSeqByParentId(Long parentId) throws DaoException;
}