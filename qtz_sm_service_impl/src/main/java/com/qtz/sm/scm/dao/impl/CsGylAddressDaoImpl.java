package com.qtz.sm.scm.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.scm.vo.CsGylAddress;
import com.qtz.sm.scm.dao.CsGylAddressDao;

/**
 * Title:CsGylAddressDaoImpl<br/>
 * Description:(供应链公司地址DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGylAddressDaoImpl")
public class CsGylAddressDaoImpl extends MyBaitsDaoImpl<CsGylAddress,Long> implements CsGylAddressDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGylAddressDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

