package com.qtz.sm.stc.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.stc.dao.CsYccAddressDao;
import com.qtz.sm.stc.service.CsYccAddressService;
import com.qtz.sm.stc.vo.CsYccAddress;

/**
 * Title:CsYccAddressServiceImpl<br/>
 * Description:(云仓储地址SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csYccAddressServiceImpl")
public class CsYccAddressServiceImpl extends BaseServiceImpl<CsYccAddress,Long> implements CsYccAddressService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsYccAddressServiceImpl.class);
    /**注入CsYccAddressDao接口类*/
    @Resource(name="csYccAddressDaoImpl")
    private CsYccAddressDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsYccAddress,Long> getDao() {
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
    public CsYccAddress findVoByYccId(Long yccId) throws ServiceException{
    	
    	if(null == yccId){
    		 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,yccId=" + yccId);
    	}
    	try {
            return dao.findVoByYccId(yccId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}

