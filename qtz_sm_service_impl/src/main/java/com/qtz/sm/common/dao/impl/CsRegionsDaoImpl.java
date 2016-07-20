package com.qtz.sm.common.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.common.dao.CsRegionsDao;

/**
 * Title:CsRegionsDaoImpl<br/>
 * Description:(行政区域DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-21
 */
@Repository("csRegionsDaoImpl")
public class CsRegionsDaoImpl extends MyBaitsDaoImpl<CsRegions,Integer> implements CsRegionsDao{

    /**MYBatis命名空间名*/
    private static String preName = CsRegionsDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

}

