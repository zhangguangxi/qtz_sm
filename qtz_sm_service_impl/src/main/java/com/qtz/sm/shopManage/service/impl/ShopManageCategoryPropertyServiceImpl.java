package com.qtz.sm.shopManage.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shopManage.service.ShopManageCategoryPropertyService;
import com.qtz.sm.shopManage.dao.ShopManageCategoryPropertyDao;
/**
 * <p>Title:ShopManageCategoryPropertyServiceImpl</p>
 * <p>Description:便利店管理公司运营分类与便利店关联服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
@Service("shopManageCategoryPropertyServiceImpl")
public class ShopManageCategoryPropertyServiceImpl extends BaseServiceImpl<com.qtz.sm.shopManage.vo.ShopManageCategoryProperty,java.lang.Long> implements ShopManageCategoryPropertyService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopManageCategoryPropertyServiceImpl.class);
	/**注入便利店管理公司运营分类与便利店关联DAO接口类*/
	@Resource(name="ShopManageCategoryPropertyDaoImpl")
    private ShopManageCategoryPropertyDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shopManage.vo.ShopManageCategoryProperty,java.lang.Long> getDao() {
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
	public void delByShopId(Long shopId) throws ServiceException {
		try {
			this.dao.delByShopId(shopId);
		} catch (DaoException e) {
			log.error("根据便利店ID删除关联运营分类信息失败！", e);
	        throw new ServiceException(e.getMessage());
		}
		
	}
}