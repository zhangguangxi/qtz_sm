package com.qtz.sm.shopManage.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shopManage.service.ShopManageSplitPointService;
import com.qtz.sm.shopManage.dao.ShopManageSplitPointDao;
/**
 * <p>Title:ShopManageSplitPointServiceImpl</p>
 * <p>Description:便利店公司分成点服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-01
 */
@Service("shopManageSplitPointServiceImpl")
public class ShopManageSplitPointServiceImpl extends BaseServiceImpl<com.qtz.sm.shopManage.vo.ShopManageSplitPoint,java.lang.Long> implements ShopManageSplitPointService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopManageSplitPointServiceImpl.class);
	/**注入便利店公司分成点DAO接口类*/
	@Resource(name="shopManageSplitPointDaoImpl")
    private ShopManageSplitPointDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shopManage.vo.ShopManageSplitPoint,java.lang.Long> getDao() {
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