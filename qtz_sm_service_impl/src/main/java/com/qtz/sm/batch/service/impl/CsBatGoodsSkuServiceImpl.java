package com.qtz.sm.batch.service.impl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.mall.core.common.response.RespCode;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.batch.service.CsBatGoodsSkuService;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
import com.qtz.sm.batch.dao.CsBatGoodsSkuDao;
/**
 * <p>Title:CsBatGoodsSkuServiceImpl</p>
 * <p>Description:批发单商品与SKU关系服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
@Service("csBatGoodsSkuServiceImpl")
public class CsBatGoodsSkuServiceImpl extends BaseServiceImpl<com.qtz.sm.batch.vo.CsBatGoodsSku,java.lang.Long> implements CsBatGoodsSkuService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(CsBatGoodsSkuServiceImpl.class);
	/**注入批发单商品与SKU关系DAO接口类*/
	@Resource(name="csBatGoodsSkuDaoImpl")
    private CsBatGoodsSkuDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.batch.vo.CsBatGoodsSku,java.lang.Long> getDao() {
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
	public List<CsBatGoodsSku> getSkuInfoByDmId(Long dmId) throws ServiceException {
		try {
			List<CsBatGoodsSku> result = dao.getSkuInfoByDmId(dmId);
			return result;
		} catch (DaoException e) {
			log.error("根据批发单下面商品ID获取商品SKU详情信息出现系统错误！", e);
            throw new ServiceException(RespCode.get_skuVal_fail,"根据批发单下面商品ID获取商品SKU详情信息出现系统错误.");
		}
	}
	@Override
	public List<CsBatGoodsSku> findListByBatOrderId(Long batOrderId) throws ServiceException {
		try {
			List<CsBatGoodsSku> result = dao.findListByBatOrderId(batOrderId);
			return result;
		} catch (DaoException e) {
			log.error("根据批发单ID获取商品SKU信息出现系统错误！", e);
            throw new ServiceException(RespCode.get_skuVal_fail,"根据批发单ID获取商品SKU信息出现系统错误.");
		}
	}
}