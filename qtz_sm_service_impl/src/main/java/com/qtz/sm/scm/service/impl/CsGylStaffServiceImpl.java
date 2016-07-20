package com.qtz.sm.scm.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.scm.vo.CsGylStaff;
import com.qtz.sm.scm.dao.CsGylStaffDao;
import com.qtz.sm.scm.service.CsGylStaffService;

/**
 * Title:CsGylStaffServiceImpl<br/>
 * Description:(供应链公司员工SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGylStaffServiceImpl")
public class CsGylStaffServiceImpl extends BaseServiceImpl<CsGylStaff,Long> implements CsGylStaffService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsGylStaffServiceImpl.class);
    /**注入CsGylStaffDao接口类*/
    @Resource(name="csGylStaffDaoImpl")
    private CsGylStaffDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsGylStaff,Long> getDao() {
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

