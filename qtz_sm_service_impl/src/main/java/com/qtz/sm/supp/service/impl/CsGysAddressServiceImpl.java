package com.qtz.sm.supp.service.impl;

import javax.annotation.Resource;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.supp.vo.CsGysAddress;
import com.qtz.sm.supp.dao.CsGysAddressDao;
import com.qtz.sm.supp.service.CsGysAddressService;

import java.util.List;

/**
 * Title:CsGysAddressServiceImpl<br/>
 * Description:(供应商地址SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGysAddressServiceImpl")
public class CsGysAddressServiceImpl extends BaseServiceImpl<CsGysAddress, Long> implements CsGysAddressService {

    /**
     * 初始化日志对象
     */
    private static LogTool log = LogTool.getInstance(CsGysAddressServiceImpl.class);
    /**
     * 注入CsGysAddressDao接口类
     */
    @Resource(name = "csGysAddressDaoImpl")
    private CsGysAddressDao dao;

    /**
     * 【取得】业务DAO对象
     *
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<CsGysAddress, Long> getDao() {
        return dao;
    }

    /**
     * 【取得】日志对象
     *
     * @return 日志对象
     */
    @Override
    protected LogTool getLog() {
        return log;
    }

    @Override
    public CsGysAddress findOnlyAddress(Long suppId) throws ServiceException {
        if (null == suppId) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
        }
        CsGysAddress vo = new CsGysAddress();
        vo.setGysId(suppId);
        List<CsGysAddress> voList = this.findList(vo);
        if (voList.size() == 1) {
            return voList.get(0);
        } else {
            for (CsGysAddress a : voList) {
                this.delId(a.getDmId());
            }
            return null;
        }
    }

    @Override
    public void delAllAddress(Long suppId) throws ServiceException {
        if (null == suppId) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
        }
        CsGysAddress vo = new CsGysAddress();
        vo.setGysId(suppId);
        List<CsGysAddress> voList = this.findList(vo);
        for (CsGysAddress a : voList) {
            this.delId(a.getDmId());
        }
    }

}

