package com.qtz.sm.supp.service.impl;

import javax.annotation.Resource;

import com.mall.core.exception.ServiceException;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.supp.vo.CsGysDeliveryRegion;
import com.qtz.sm.supp.dao.CsGysDeliveryRegionDao;
import com.qtz.sm.supp.service.CsGysDeliveryRegionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Title:CsGysDeliveryRegionServiceImpl<br/>
 * Description:(供应商供货区域SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGysDeliveryRegionServiceImpl")
public class CsGysDeliveryRegionServiceImpl extends BaseServiceImpl<CsGysDeliveryRegion,Long> implements CsGysDeliveryRegionService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsGysDeliveryRegionServiceImpl.class);
    /**注入CsGysDeliveryRegionDao接口类*/
    @Resource(name="csGysDeliveryRegionDaoImpl")
    private CsGysDeliveryRegionDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsGysDeliveryRegion,Long> getDao() {
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

    @Override
    public void delAllDeliveryRegion(Long suppId) throws ServiceException {
        CsGysDeliveryRegion vo = new CsGysDeliveryRegion();
        vo.setGysId(suppId);
        List<CsGysDeliveryRegion> voList = this.findList(vo);
        for (CsGysDeliveryRegion fvo:voList){
            this.delId(fvo.getDmId());
        }
    }

    @Override
    public void addDeliveryRegionList(Long suppId, List<CsGysDeliveryRegion> voList) throws ServiceException {
        if(null == voList || voList.size() == 0){
            return;
        }else {
            Set<String> checkSet = new HashSet<>();
            for (CsGysDeliveryRegion vo : voList){
                vo.setGysId(suppId);
                if(checkSet.contains(vo.getProvinceId()))
                this.addVo(vo);
            }
        }
    }
}

