package com.qtz.sm.supp.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.supp.vo.CsGysStaff;
import com.qtz.sm.supp.dao.CsGysStaffDao;
import com.qtz.sm.supp.service.CsGysStaffService;

/**
 * Title:CsGysStaffServiceImpl<br/>
 * Description:(供应商员工SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGysStaffServiceImpl")
public class CsGysStaffServiceImpl extends BaseServiceImpl<CsGysStaff,Long> implements CsGysStaffService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsGysStaffServiceImpl.class);
    /**注入CsGysStaffDao接口类*/
    @Resource(name="csGysStaffDaoImpl")
    private CsGysStaffDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsGysStaff,Long> getDao() {
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

}

