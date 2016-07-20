package com.qtz.sm.batch.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.batch.dao.CsBatOrderDao;
/**
 * <p>Title:CsBatOrderDaoImpl</p>
 * <p>Description:批发单基础信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
@Repository("csBatOrderDaoImpl")
public class CsBatOrderDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.batch.vo.CsBatOrder,java.lang.Long> implements CsBatOrderDao {
	/**MYBatis命名空间名*/
	private static String preName = CsBatOrderDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}