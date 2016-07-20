package com.qtz.sm.shop.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.service.ShopStaffService;
import com.qtz.sm.shop.dao.ShopStaffDao;
/**
 * <p>Title:ShopStaffServiceImpl</p>
 * <p>Description:便利店员工服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Service("shopStaffServiceImpl")
public class ShopStaffServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopStaff,Long> implements ShopStaffService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopStaffServiceImpl.class);
	/**注入便利店员工DAO接口类*/
	@Resource(name="shopStaffDaoImpl")
    private ShopStaffDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopStaff,Long> getDao() {
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