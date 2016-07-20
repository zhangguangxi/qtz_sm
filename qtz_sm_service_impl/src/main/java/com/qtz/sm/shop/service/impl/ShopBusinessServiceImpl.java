package com.qtz.sm.shop.service.impl;
import javax.annotation.Resource;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.shop.vo.ShopBusiness;
import com.qtz.sm.shop.vo.ShopBusinessVo;
import com.qtz.sm.shop.vo.ShopInfo;

import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.service.ShopBusinessService;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.dao.ShopBusinessDao;

import java.util.List;

/**
 * <p>Title:ShopBusinessServiceImpl</p>
 * <p>Description:便利店营业信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Service("shopBusinessServiceImpl")
public class ShopBusinessServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopBusiness,Long> implements ShopBusinessService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopBusinessServiceImpl.class);
	/**注入便利店营业信息DAO接口类*/
	@Resource(name="shopBusinessDaoImpl")
    private ShopBusinessDao dao;
	
	/**
     * 便利店基本信息
     */
    @Resource(name = "shopInfoServiceImpl")
    private ShopInfoService shopInfoService;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopBusiness,Long> getDao() {
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
	public ShopBusiness findVoByShopId(Long shopId) throws ServiceException {
		try {
			ShopBusiness shopBusiness = new ShopBusiness();
			shopBusiness.setShopId(shopId);
			List<ShopBusiness> shopBusinessList = dao.findList(shopBusiness);
			if (null == shopBusinessList || shopBusinessList.size() == 0) {
				throw new ServiceException(ExceptionCode.SHOP_BUSINESS_IS_NOT_FIND, "没有找到便利店营业信息.");
			}
			return shopBusinessList.get(0);
		} catch (DaoException de) {
			throw new ServiceException(de.getErrorType(), de.getErrorTitle(), de.getErrorMessage());
		}
	}
	@Override
	public ShopBusinessVo getVoShopId(Long shopId) throws ServiceException {
		try {
			
			ShopBusiness shopBusiness = new ShopBusiness();
			shopBusiness.setShopId(shopId);
			List<ShopBusiness> shopBusinessList = dao.findList(shopBusiness);
			if (null == shopBusinessList || shopBusinessList.size() == 0) {
				throw new ServiceException(ExceptionCode.SHOP_BUSINESS_IS_NOT_FIND, "没有找到便利店营业信息.");
			}
			return setValue(shopBusinessList.get(0), shopInfoService.findById(shopId));
		} catch (DaoException de) {
			throw new ServiceException(de.getErrorType(), de.getErrorTitle(), de.getErrorMessage());
		}
	}
	
	/**
	 * 拼装便利店数据
	 * @Description:TODO
	 * @param shopBusiness
	 * @param shopInfo
	 * @return
	 * ShopBusinessVo
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年5月23日 下午4:31:46
	 */
	ShopBusinessVo setValue(ShopBusiness shopBusiness,ShopInfo shopInfo){
		if(null != shopBusiness && null != shopInfo){
			ShopBusinessVo vo = new ShopBusinessVo();
			vo.setShopBusiness(shopBusiness);
			vo.setShopInfo(shopInfo);
			return vo;
		}else{
			return null;
		}
		
		
	}
}