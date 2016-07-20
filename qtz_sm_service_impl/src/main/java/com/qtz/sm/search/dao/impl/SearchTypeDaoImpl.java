package com.qtz.sm.search.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.search.dao.SearchTypeDao;
/**
 * <p>Title:SearchTypeDaoImpl</p>
 * <p>Description:搜索分类DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
@Repository("searchTypeDaoImpl")
public class SearchTypeDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.search.vo.SearchType,Long> implements SearchTypeDao {
	/**MYBatis命名空间名*/
	private static String preName = SearchTypeDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
}