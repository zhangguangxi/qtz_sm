package com.qtz.sm.supp.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.supp.vo.CsGysDeliveryRegion;
import com.qtz.sm.supp.dao.CsGysDeliveryRegionDao;

/**
 * Title:CsGysDeliveryRegionDaoImpl<br/>
 * Description:(供应商供货区域DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGysDeliveryRegionDaoImpl")
public class CsGysDeliveryRegionDaoImpl extends MyBaitsDaoImpl<CsGysDeliveryRegion,Long> implements CsGysDeliveryRegionDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGysDeliveryRegionDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

