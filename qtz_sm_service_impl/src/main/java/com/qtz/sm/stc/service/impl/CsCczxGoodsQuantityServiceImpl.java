package com.qtz.sm.stc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.stc.dao.CsCczxGoodsQuantityDao;
import com.qtz.sm.stc.service.CsCczxGoodsQuantityService;
import com.qtz.sm.stc.vo.CsCczxGoodsQuantity;

@Service("csCczxGoodsQuantityServiceImpl")
public class CsCczxGoodsQuantityServiceImpl extends BaseServiceImpl<CsCczxGoodsQuantity,Long>  implements CsCczxGoodsQuantityService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsCczxGoodsQuantityServiceImpl.class);
    /**注入接口类*/
    @Resource(name="csCczxGoodsQuantityDaoImpl")
    private CsCczxGoodsQuantityDao dao;

	@Override
	protected LogTool getLog() {
		return log;
	}

	@Override
	protected BizDao<CsCczxGoodsQuantity, Long> getDao() {
		return dao;
	}



}
