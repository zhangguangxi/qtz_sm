package com.qtz.sm.shop.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.service.ShopSalesService;
import com.qtz.sm.shop.vo.ShopSales;
import com.qtz.sm.shop.dao.ShopSalesDao;
/**
 * <p>Title:ShopSalesServiceImpl</p>
 * <p>Description:店铺销量信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
@Service("shopSalesServiceImpl")
public class ShopSalesServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopSales,java.lang.Long> implements ShopSalesService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopSalesServiceImpl.class);
	/**注入店铺销量信息DAO接口类*/
	@Resource(name="shopSalesDaoImpl")
    private ShopSalesDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopSales,java.lang.Long> getDao() {
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
	public Integer countSales(ShopSales shopSales) throws ServiceException {
		try {
			Integer result = dao.countSales(shopSales);
			return result == null?0:result;
		} catch (DaoException e) {
			log.error("根据便利店ID和SKUID统计销售信息出现系统错误！", e);
            throw new ServiceException(e.getMessage());
		}
	}
}