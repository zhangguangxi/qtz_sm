package com.qtz.sm.supermarket.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.supermarket.service.SupermarketStaffService;
import com.qtz.sm.supermarket.dao.SupermarketStaffDao;
/**
 * <p>Title:SupermarketStaffServiceImpl</p>
 * <p>Description:超市员工服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Service("supermarketStaffServiceImpl")
public class SupermarketStaffServiceImpl extends BaseServiceImpl<com.qtz.sm.supermarket.vo.SupermarketStaff,Long> implements SupermarketStaffService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(SupermarketStaffServiceImpl.class);
	/**注入超市员工DAO接口类*/
	@Resource(name="supermarketStaffDaoImpl")
    private SupermarketStaffDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.supermarket.vo.SupermarketStaff,Long> getDao() {
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