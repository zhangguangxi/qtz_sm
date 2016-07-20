package com.qtz.sm.supp.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.supp.vo.CsGysAddress;
import com.qtz.sm.supp.dao.CsGysAddressDao;

/**
 * Title:CsGysAddressDaoImpl<br/>
 * Description:(供应商地址DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGysAddressDaoImpl")
public class CsGysAddressDaoImpl extends MyBaitsDaoImpl<CsGysAddress,Long> implements CsGysAddressDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGysAddressDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

