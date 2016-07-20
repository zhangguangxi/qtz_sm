package com.qtz.sm.shopManage.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shopManage.service.ShopManageStaffService;
import com.qtz.sm.shopManage.dao.ShopManageStaffDao;
/**
 * <p>Title:ShopManageStaffServiceImpl</p>
 * <p>Description:便利店管理员工服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Service("shopManageStaffServiceImpl")
public class ShopManageStaffServiceImpl extends BaseServiceImpl<com.qtz.sm.shopManage.vo.ShopManageStaff,Long> implements ShopManageStaffService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopManageStaffServiceImpl.class);
	/**注入便利店管理员工DAO接口类*/
	@Resource(name="shopManageStaffDaoImpl")
    private ShopManageStaffDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shopManage.vo.ShopManageStaff,Long> getDao() {
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