package com.qtz.sm.stc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.stc.dao.CczxGoodsDao;
import com.qtz.sm.stc.service.CczxGoodsService;
import com.qtz.sm.stc.vo.CczxGoods;

@Service("cczxGoodsServiceImpl")
public class CczxGoodsServiceImpl extends BaseServiceImpl<CczxGoods,Long>  implements CczxGoodsService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CczxGoodsServiceImpl.class);
    /**注入CsYccAddressDao接口类*/
    @Resource(name="cczxGoodsDaoImpl")
    private CczxGoodsDao dao;

	@Override
	protected LogTool getLog() {
		return log;
	}

	@Override
	protected BizDao<CczxGoods, Long> getDao() {
		return dao;
	}

	@Override
	public Pager<CczxGoods, Long> queryCczxGoodsPage(Pager<CczxGoods, Long> page)throws ServiceException {
		try {
			return dao.queryCczxGoodsPage(page);
		} catch (DaoException e) {
            throw new ServiceException(e);
        }
	}

}
