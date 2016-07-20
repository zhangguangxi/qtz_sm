package com.qtz.sm.shop.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.service.ShopReportService;
import com.qtz.sm.shop.dao.ShopReportDao;
/**
 * <p>Title:ShopReportServiceImpl</p>
 * <p>Description:店铺举报信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
@Service("shopReportServiceImpl")
public class ShopReportServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopReport,java.lang.Long> implements ShopReportService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopReportServiceImpl.class);
	/**注入店铺举报信息DAO接口类*/
	@Resource(name="shopReportDaoImpl")
    private ShopReportDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopReport,java.lang.Long> getDao() {
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