package com.qtz.sm.shopManage.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shopManage.dao.ShopManageCategoryDao;
import com.qtz.sm.shopManage.service.ShopManageCategoryService;
import com.qtz.sm.shopManage.vo.ShopManageCategory;
/**
 * <p>Title:ShopManageCategoryServiceImpl</p>
 * <p>Description:便利店管理公司运营分类服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
@Service("shopManageCategoryServiceImpl")
public class ShopManageCategoryServiceImpl extends BaseServiceImpl<com.qtz.sm.shopManage.vo.ShopManageCategory,java.lang.Long> implements ShopManageCategoryService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopManageCategoryServiceImpl.class);
	/**注入便利店管理公司运营分类DAO接口类*/
	@Resource(name="ShopManageCategoryDaoImpl")
    private ShopManageCategoryDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shopManage.vo.ShopManageCategory,java.lang.Long> getDao() {
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
	@Override
	public List<ShopManageCategory> findListByShopId(ShopManageCategory shopManageCategory) throws ServiceException {
		try {
			return this.dao.findListByShopId(shopManageCategory);
		} catch (DaoException e) {
			log.error("根据便利店ID查询所属运营分类信息失败！", e);
	        throw new ServiceException(e.getMessage());
		}
	}
}