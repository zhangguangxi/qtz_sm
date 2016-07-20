package com.qtz.sm.shopManage.service;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
/**
 * <p>Title:ShopManageCategoryPropertyService</p>
 * <p>Description:便利店管理公司运营分类与便利店关联服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
public interface ShopManageCategoryPropertyService extends BaseService<com.qtz.sm.shopManage.vo.ShopManageCategoryProperty,java.lang.Long> {
	
	/**
	 * 根据便利店ID删除信息
	 * @Description:TODO
	 * @param shopId
	 * @throws DaoException
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月6日 下午3:53:30
	 */
	public void delByShopId(Long shopId) throws ServiceException;
}