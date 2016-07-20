package com.qtz.sm.shopManage.dao;
import java.util.List;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
/**
 * <p>Title:ShopManageCategoryDao</p>
 * <p>Description:便利店管理公司运营分类DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
import com.qtz.sm.shopManage.vo.ShopManageCategory;
public interface ShopManageCategoryDao extends BizDao<com.qtz.sm.shopManage.vo.ShopManageCategory,java.lang.Long> {
	
	/**
	 * 根据便利店ID查询所属运营分类信息
	 * @Description:TODO
	 * @param shopManageCategory
	 * @return
	 * @throws DaoException
	 * List<ShopManageCategory>
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月6日 下午2:52:00
	 */
	public List<ShopManageCategory> findListByShopId(ShopManageCategory shopManageCategory) throws DaoException;
}