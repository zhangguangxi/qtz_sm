package com.qtz.sm.shop.service.impl;
import javax.annotation.Resource;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.service.ShopGoodsSkuService;
import com.qtz.sm.shop.dao.ShopGoodsSkuDao;

import java.util.Date;

/**
 * <p>Title:ShopGoodsSkuServiceImpl</p>
 * <p>Description:便利店商品sku服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Service("shopGoodsSkuServiceImpl")
public class ShopGoodsSkuServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopGoodsSku,Long> implements ShopGoodsSkuService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopGoodsSkuServiceImpl.class);
	/**注入便利店商品skuDAO接口类*/
	@Resource(name="shopGoodsSkuDaoImpl")
    private ShopGoodsSkuDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopGoodsSku,Long> getDao() {
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
	public void modAddActualStock(Long shopSkuId, int stock) throws ServiceException {
		//增加实际库存
		try {
			dao.addActualStock(shopSkuId, stock, new Date());
		} catch (DaoException e) {
			log.error("增加实际库存失败！");
			throw new ServiceException("增加实际库存失败！");
		}
	}

	@Override
	public void modSubActualStock(Long shopSkuId, int stock) throws ServiceException {
		//减实际库存，加冻结库存
		try {
			dao.subActualStock(shopSkuId, stock, new Date());
		} catch (DaoException e) {
			log.error("减实际库存，加冻结库存失败！");
			throw new ServiceException("减实际库存，加冻结库存失败！");
		}
	}

	@Override
	public void modSubFreezeStock(Long shopSkuId, int stock) throws ServiceException {
		//减冻结库存
		try {
			dao.subFreezeStock(shopSkuId, stock, new Date());
		} catch (DaoException e) {
			log.error("减冻结库存失败！");
			throw new ServiceException("减冻结库存失败！");
		}
	}
}