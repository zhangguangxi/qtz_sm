package com.qtz.sm.goods.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.goods.dao.GdGoodsOperationHistoryDao;
/**
 * <p>Title:GdGoodsOperationHistoryDaoImpl</p>
 * <p>Description:商品操作历史记录DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-15
 */
@Repository("gdGoodsOperationHistoryDaoImpl")
public class GdGoodsOperationHistoryDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.goods.vo.GdGoodsOperationHistory,java.lang.Long> implements GdGoodsOperationHistoryDao {
	/**MYBatis命名空间名*/
	private static String preName = GdGoodsOperationHistoryDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}