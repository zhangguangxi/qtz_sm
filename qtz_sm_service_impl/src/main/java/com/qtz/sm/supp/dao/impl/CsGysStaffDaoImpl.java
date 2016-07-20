package com.qtz.sm.supp.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.supp.vo.CsGysStaff;
import com.qtz.sm.supp.dao.CsGysStaffDao;

/**
 * Title:CsGysStaffDaoImpl<br/>
 * Description:(供应商员工DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGysStaffDaoImpl")
public class CsGysStaffDaoImpl extends MyBaitsDaoImpl<CsGysStaff,Long> implements CsGysStaffDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGysStaffDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

