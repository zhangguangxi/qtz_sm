package com.qtz.sm.search.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.search.service.SearchTypeService;
import com.qtz.sm.search.dao.SearchTypeDao;
/**
 * <p>Title:SearchTypeServiceImpl</p>
 * <p>Description:搜索分类服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
@Service("searchTypeServiceImpl")
public class SearchTypeServiceImpl extends BaseServiceImpl<com.qtz.sm.search.vo.SearchType,Long> implements SearchTypeService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(SearchTypeServiceImpl.class);
	/**注入搜索分类DAO接口类*/
	@Resource(name="searchTypeDaoImpl")
    private SearchTypeDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.search.vo.SearchType,Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
}