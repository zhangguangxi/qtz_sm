package com.qtz.sm.shop.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.dao.ShopPurchaseOrderItemDao;
import com.qtz.sm.shop.service.ShopPurchaseOrderItemService;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItem;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItemVo;
/**
 * <p>Title:ShopPurchaseOrderItemServiceImpl</p>
 * <p>Description:便利店采购订单项服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 孙选 - Laven
 * @version v1.0 2016-05-04
 */
@Service("shopPurchaseOrderItemServiceImpl")
public class ShopPurchaseOrderItemServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopPurchaseOrderItem,Long> implements ShopPurchaseOrderItemService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopPurchaseOrderItemServiceImpl.class);
	/**注入便利店采购订单项DAO接口类*/
	@Resource(name="shopPurchaseOrderItemDaoImpl")
    private ShopPurchaseOrderItemDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopPurchaseOrderItem,Long> getDao() {
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
	public List<ShopPurchaseOrderItemVo> getOrderItem(ShopPurchaseOrderItem shopPurchaseOrderItem)
			throws ServiceException {
		
		try {
			return dao.getOrderItem(shopPurchaseOrderItem);
		} catch (Exception e) {
			log.error("通过进货单ID查出商品SKU出现系统错误！", e);
            throw new ServiceException(e.getMessage());
		}
	}
}