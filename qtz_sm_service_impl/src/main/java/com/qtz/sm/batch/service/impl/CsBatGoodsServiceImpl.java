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
import com.qtz.sm.batch.service.CsBatGoodsService;
import com.qtz.sm.batch.vo.CsBatGoods;
import com.qtz.sm.batch.dao.CsBatGoodsDao;
/**
 * <p>Title:CsBatGoodsServiceImpl</p>
 * <p>Description:批发单与商品关系服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
@Service("csBatGoodsServiceImpl")
public class CsBatGoodsServiceImpl extends BaseServiceImpl<com.qtz.sm.batch.vo.CsBatGoods,java.lang.Long> implements CsBatGoodsService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(CsBatGoodsServiceImpl.class);
	/**注入批发单与商品关系DAO接口类*/
	@Resource(name="csBatGoodsDaoImpl")
    private CsBatGoodsDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.batch.vo.CsBatGoods,java.lang.Long> getDao() {
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
	public List<CsBatGoods> getGoodsInfoByOrderId(Long dmId) throws ServiceException {
		try {
			List<CsBatGoods> result= dao.getGoodsInfoByOrderId(dmId);
			return result;
		} catch (DaoException e) {
			log.error("根据批发单ID获取商品详情信息出现系统错误！", e);
            throw new ServiceException(RespCode.getGoodsInfo_error ,"根据批发单ID获取商品详情信息出现系统错误.");
		}
	}
}