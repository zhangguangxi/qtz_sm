package com.qtz.sm.store.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.store.vo.CsCczxDeliveryRegion;
import com.qtz.sm.store.dao.CsCczxDeliveryRegionDao;

/**
 * Title:CsCczxDeliveryRegionDaoImpl<br/>
 * Description:(仓储中心供货区域DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csCczxDeliveryRegionDaoImpl")
public class CsCczxDeliveryRegionDaoImpl extends MyBaitsDaoImpl<CsCczxDeliveryRegion,Long> implements CsCczxDeliveryRegionDao{

    /**MYBatis命名空间名*/
    private static String preName = CsCczxDeliveryRegionDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

