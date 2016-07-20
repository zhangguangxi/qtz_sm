package com.qtz.sm.store.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.store.vo.CsCczxStaff;
import com.qtz.sm.store.dao.CsCczxStaffDao;

/**
 * Title:CsCczxStaffDaoImpl<br/>
 * Description:(仓储中心员工DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csCczxStaffDaoImpl")
public class CsCczxStaffDaoImpl extends MyBaitsDaoImpl<CsCczxStaff,Long> implements CsCczxStaffDao{

    /**MYBatis命名空间名*/
    private static String preName = CsCczxStaffDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

