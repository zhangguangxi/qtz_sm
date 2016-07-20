package com.qtz.sm.scm.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.scm.vo.CsGylInfo;
import com.qtz.sm.scm.dao.CsGylInfoDao;

/**
 * Title:CsGylInfoDaoImpl<br/>
 * Description:(供应链公司信息DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGylInfoDaoImpl")
public class CsGylInfoDaoImpl extends MyBaitsDaoImpl<CsGylInfo,Long> implements CsGylInfoDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGylInfoDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

