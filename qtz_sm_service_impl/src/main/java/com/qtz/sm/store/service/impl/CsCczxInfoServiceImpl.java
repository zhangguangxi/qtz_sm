package com.qtz.sm.store.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.MD5Util;
import com.mall.core.common.StringUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.stc.service.CsYccInfoService;
import com.qtz.sm.stc.vo.CsYccInfo;
import com.qtz.sm.store.dao.CsCczxInfoDao;
import com.qtz.sm.store.service.CsCczxAddressService;
import com.qtz.sm.store.service.CsCczxDeliveryRegionService;
import com.qtz.sm.store.service.CsCczxInfoService;
import com.qtz.sm.store.service.CsCczxStaffService;
import com.qtz.sm.store.vo.CsCczxAddress;
import com.qtz.sm.store.vo.CsCczxDeliveryRegion;
import com.qtz.sm.store.vo.CsCczxInfo;
import com.qtz.sm.store.vo.CsCczxStaff;
import com.qtz.sm.supp.vo.CsGysDeliveryRegion;
import com.qtz.sm.wallet.service.WtWalletService;

/**
 * Title:CsCczxInfoServiceImpl<br/>
 * Description:(仓储中心信息SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csCczxInfoServiceImpl")
public class CsCczxInfoServiceImpl extends BaseServiceImpl<CsCczxInfo,Long> implements CsCczxInfoService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsCczxInfoServiceImpl.class);
    /**注入CsCczxInfoDao接口类*/
    @Resource(name="csCczxInfoDaoImpl")
    private CsCczxInfoDao dao;

    @Resource(name = "csCczxAddressServiceImpl")
    private CsCczxAddressService csCczxAddressService;

    @Resource(name = "csCczxDeliveryRegionServiceImpl")
    private CsCczxDeliveryRegionService csCczxDeliveryRegionService;
    
    @Autowired
    private CsRegionsService csRegionsService;
    
    @Autowired
   	private WtWalletService wtWalletService;
    
    
    @Autowired
   	private CsCczxStaffService csCczxStaffService;
    
    @Autowired
    private CsYccInfoService csYccInfoService;
    
    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsCczxInfo,Long> getDao() {
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
    public CsCczxInfo findDeatailVo(Long suppId) throws ServiceException {
        if (null == suppId) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
        }
        CsCczxInfo info = this.findVo(suppId,null);
        if(null == info){
            throw new ServiceException(ExceptionConstants.ERRORCODE_7,"不存在该仓储中心,suppId="+suppId);
        }
        CsCczxAddress address = csCczxAddressService.findOnlyAddress(suppId);
        info.setJsonAddress(JSON.toJSONString(address));
        CsCczxDeliveryRegion dr = new CsCczxDeliveryRegion();
        dr.setCczxId(suppId);
        List<CsCczxDeliveryRegion> drList = csCczxDeliveryRegionService.findList(dr);
        info.setJsonArea(JSON.toJSONString(drList));
        return info;
    }
    
    @Override
    public CsCczxInfo findVoByCsCczxInfoName (String name) throws ServiceException{
    	if (StringUtil.isEmpty(name)) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,name=" + name);
        }
        name = name.trim();
        try {
            return dao.findVoByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    
    
    
    @Override
    public void addCczxInfo(CsCczxInfo csCczxInfo ,CsCczxAddress csCczxAddress )throws ServiceException{

		List<CsYccInfo> yccList =  csYccInfoService.findList(new CsYccInfo());
		if( yccList.size() > 0 ){
	    	this.addVo(csCczxInfo);
	    	//添加仓储中心地址
			csCczxAddress.setCczxId(csCczxInfo.getDmId());
			csCczxAddress.setAddress(csCczxAddress.getAddress()!=null?csCczxAddress.getAddress():"");
			csCczxAddress.setFullAddress(csRegionsService.getFullAddressByIds(csCczxAddress.getProvinceId(),csCczxAddress.getCityId(), csCczxAddress.getCountyId(),null,"")+csCczxAddress.getAddress());
			csCczxAddressService.addVo(csCczxAddress);
			//添加员工信息start
			addCczxStaff(csCczxInfo);
			//添加员工信息end
			//添加钱包信息
			wtWalletService.addCczxWallet(csCczxInfo.getDmId(),csCczxInfo.getName(),yccList.get(0).getDmId());
		}
		
    	
    }
    
    private void addCczxStaff(CsCczxInfo csCczxInfo ) throws ServiceException{
    	CsCczxStaff staff = new CsCczxStaff();
    	staff.setPhone(csCczxInfo.getContactPhone());
    	List<CsCczxStaff> stafflist = csCczxStaffService.findList(staff);
    	if(stafflist.size() > 0){
       	 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,员工信息已存在" + csCczxInfo.getContactPhone());
       }else{
    	   staff.setCczxId(csCczxInfo.getDmId());
   		   staff.setName(csCczxInfo.getName());	
   		   staff.setAccountType(new Byte("0"));
   		   //密码为身份证后6位
           String lpIdCard = csCczxInfo.getLpIdCard();
           String pwd = lpIdCard.substring((lpIdCard.length() - 6));     
           staff.setPwd(MD5Util.getMD5("123456"));
           staff.setStatus(new Byte("0"));
   		   csCczxStaffService.addVo(staff);
       }    	
    }
}

