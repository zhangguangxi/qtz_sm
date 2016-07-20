package com.qtz.sm.scm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.scm.dao.CsGylGoodsDao;
import com.qtz.sm.scm.service.CsGylGoodsService;
import com.qtz.sm.scm.vo.CsGylGoodsVo;

/**
 * <!-- 供应链公司   商品管理   商品审核分页以及商品库分页      分页 -->
 * @author Administrator
 *
 */
@Service("csGylGoodsServiceImpl")
public class CsGylGoodsServiceImpl extends BaseServiceImpl<CsGylGoodsVo,Long> implements CsGylGoodsService{


    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsGylGoodsServiceImpl.class);
    /**注入CsYccAddressDao接口类*/
    @Resource(name="csGylGoodsDaoImpl")
    private CsGylGoodsDao dao;
    
    @Override
	protected BizDao<CsGylGoodsVo, Long> getDao() {
		return dao;
	}

	@Override
	protected LogTool getLog() {
		return log;
	}

	/**
	 * <!-- 供应链公司   商品管理   商品审核分页以及商品库分页      分页 -->
	 */
	@Override
	public Pager<CsGylGoodsVo, Long> queryCsGylGoodsPage(Pager<CsGylGoodsVo, Long> page)throws ServiceException {
//		try {
//			return dao.queryCsGylGoodsPage(page);
			return null;
//			return getDao().query(page, clazz)
//		} catch (DaoException e) {
//            throw new ServiceException(e);
//        }
	}

	

}
