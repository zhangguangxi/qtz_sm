package com.qtz.sm.supermarket.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.supermarket.dao.SupermarketAdvertisementDao;
import com.qtz.sm.supermarket.service.SupermarketAdvertisementService;
/**
 * <p>Title:AdvertisementServiceImpl</p>
 * <p>Description:广告位服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱科技限公司</p>
 * @author 张光喜 - zhangguangxito@sina.cn 
 * @version v1.0 2016-05-31
 */
@Service("supermarketAdvertisementServiceImpl")
public class SupermarketAdvertisementServiceImpl extends BaseServiceImpl<com.qtz.sm.supermarket.vo.SupermarketAdvertisement,java.lang.Long> implements SupermarketAdvertisementService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(SupermarketAdvertisementServiceImpl.class);
	/**注入广告位DAO接口类*/
	@Resource(name="supermarketAdvertisementDaoImpl")
    private SupermarketAdvertisementDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.supermarket.vo.SupermarketAdvertisement,java.lang.Long> getDao() {
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