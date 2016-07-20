package com.qtz.sm.store.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.store.vo.CsCczxDeliveryRegion;
import com.qtz.sm.store.dao.CsCczxDeliveryRegionDao;
import com.qtz.sm.store.service.CsCczxDeliveryRegionService;

/**
 * Title:CsCczxDeliveryRegionServiceImpl<br/>
 * Description:(仓储中心供货区域SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csCczxDeliveryRegionServiceImpl")
public class CsCczxDeliveryRegionServiceImpl extends BaseServiceImpl<CsCczxDeliveryRegion,Long> implements CsCczxDeliveryRegionService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsCczxDeliveryRegionServiceImpl.class);
    /**注入CsCczxDeliveryRegionDao接口类*/
    @Resource(name="csCczxDeliveryRegionDaoImpl")
    private CsCczxDeliveryRegionDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsCczxDeliveryRegion,Long> getDao() {
        return dao;
    }

    /**
     * 【取得】日志对象
     * @return 	日志对象
     */
    @Override
    protected LogTool getLog() {
        return log;
    }

}

