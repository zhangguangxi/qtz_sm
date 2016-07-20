package com.qtz.sm.stc.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.stc.vo.CsYccStaff;
import com.qtz.sm.stc.dao.CsYccStaffDao;
import com.qtz.sm.stc.service.CsYccStaffService;

/**
 * Title:CsYccStaffServiceImpl<br/>
 * Description:(云仓储公司员工SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csYccStaffServiceImpl")
public class CsYccStaffServiceImpl extends BaseServiceImpl<CsYccStaff,Long> implements CsYccStaffService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsYccStaffServiceImpl.class);
    /**注入CsYccStaffDao接口类*/
    @Resource(name="csYccStaffDaoImpl")
    private CsYccStaffDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsYccStaff,Long> getDao() {
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

