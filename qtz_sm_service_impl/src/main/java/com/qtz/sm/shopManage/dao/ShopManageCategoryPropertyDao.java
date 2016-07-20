package com.qtz.sm.shopManage.dao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
/**
 * <p>Title:ShopManageCategoryPropertyDao</p>
 * <p>Description:便利店管理公司运营分类与便利店关联DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
public interface ShopManageCategoryPropertyDao extends BizDao<com.qtz.sm.shopManage.vo.ShopManageCategoryProperty,java.lang.Long> {
	
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
	public void delByShopId(Long shopId) throws DaoException;
}