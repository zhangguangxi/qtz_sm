package com.qtz.sm.scm.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.scm.vo.CsGylAddress;
import com.qtz.sm.supp.vo.CsGysAddress;
import com.qtz.sm.scm.dao.CsGylAddressDao;
import com.qtz.sm.scm.service.CsGylAddressService;

/**
 * Title:CsGylAddressServiceImpl<br/>
 * Description:(供应链公司地址SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGylAddressServiceImpl")
public class CsGylAddressServiceImpl extends BaseServiceImpl<CsGylAddress,Long> implements CsGylAddressService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsGylAddressServiceImpl.class);
    /**注入CsGylAddressDao接口类*/
    @Resource(name="csGylAddressDaoImpl")
    private CsGylAddressDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsGylAddress,Long> getDao() {
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
	public CsGylAddress findOnlyAddress(Long suppId) throws ServiceException {
		if (null == suppId) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
        }
        CsGylAddress vo = new CsGylAddress();
        vo.setGylId(suppId);
        List<CsGylAddress> voList = this.findList(vo);
        if (voList.size() == 1) {
            return voList.get(0);
        } else {
            for (CsGylAddress a : voList) {
                this.delId(a.getDmId());
            }
            return null;
        }
	}

}

