package com.qtz.sm.supermarket.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.supermarket.service.SupermarketBusinessService;
import com.qtz.sm.supermarket.dao.SupermarketBusinessDao;
/**
 * <p>Title:SupermarketBusinessServiceImpl</p>
 * <p>Description:超市运营信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-14
 */
@Service("supermarketBusinessServiceImpl")
public class SupermarketBusinessServiceImpl extends BaseServiceImpl<com.qtz.sm.supermarket.vo.SupermarketBusiness,java.lang.Long> implements SupermarketBusinessService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(SupermarketBusinessServiceImpl.class);
	/**注入超市运营信息DAO接口类*/
	@Resource(name="supermarketBusinessDaoImpl")
    private SupermarketBusinessDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.supermarket.vo.SupermarketBusiness,java.lang.Long> getDao() {
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