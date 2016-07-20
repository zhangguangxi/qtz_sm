package com.qtz.sm.stc.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.stc.vo.CsYccStaff;
import com.qtz.sm.stc.dao.CsYccStaffDao;

/**
 * Title:CsYccStaffDaoImpl<br/>
 * Description:(云仓储公司员工DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csYccStaffDaoImpl")
public class CsYccStaffDaoImpl extends MyBaitsDaoImpl<CsYccStaff,Long> implements CsYccStaffDao{

    /**MYBatis命名空间名*/
    private static String preName = CsYccStaffDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

