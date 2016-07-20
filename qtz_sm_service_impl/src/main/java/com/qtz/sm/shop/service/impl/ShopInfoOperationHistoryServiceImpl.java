package com.qtz.sm.shop.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.service.ShopInfoOperationHistoryService;
import com.qtz.sm.shop.dao.ShopInfoOperationHistoryDao;
/**
 * <p>Title:ShopInfoOperationHistoryServiceImpl</p>
 * <p>Description:便利店操作记录服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-17
 */
@Service("shopInfoOperationHistoryServiceImpl")
public class ShopInfoOperationHistoryServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopInfoOperationHistory,java.lang.Long> implements ShopInfoOperationHistoryService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopInfoOperationHistoryServiceImpl.class);
	/**注入便利店操作记录DAO接口类*/
	@Resource(name="shopInfoOperationHistoryDaoImpl")
    private ShopInfoOperationHistoryDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopInfoOperationHistory,java.lang.Long> getDao() {
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