package com.qtz.sm.scm.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.scm.vo.CsGylStaff;
import com.qtz.sm.scm.dao.CsGylStaffDao;

/**
 * Title:CsGylStaffDaoImpl<br/>
 * Description:(供应链公司员工DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGylStaffDaoImpl")
public class CsGylStaffDaoImpl extends MyBaitsDaoImpl<CsGylStaff,Long> implements CsGylStaffDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGylStaffDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

