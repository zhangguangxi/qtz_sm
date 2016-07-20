package com.qtz.sm.stc.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.MD5Util;
import com.mall.core.common.StringUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.shopManage.service.ShopManageService;
import com.qtz.sm.shopManage.vo.ShopManage;
import com.qtz.sm.stc.dao.CsYccInfoDao;
import com.qtz.sm.stc.service.CsYccAddressService;
import com.qtz.sm.stc.service.CsYccInfoService;
import com.qtz.sm.stc.service.CsYccStaffService;
import com.qtz.sm.stc.vo.CsYccAddress;
import com.qtz.sm.stc.vo.CsYccInfo;
import com.qtz.sm.stc.vo.CsYccStaff;
import com.qtz.sm.supermarket.service.impl.SupermarketInfoServiceImpl;
import com.qtz.sm.supermarket.vo.SupermarketInfo;
import com.qtz.sm.wallet.service.WtWalletService;

/**
 * Title:CsYccInfoServiceImpl<br/>
 * Description:(云仓储公司信息SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 谷一帅-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csYccInfoServiceImpl")
public class CsYccInfoServiceImpl extends BaseServiceImpl<CsYccInfo,Long> implements CsYccInfoService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsYccInfoServiceImpl.class);
    /**注入CsYccInfoDao接口类*/
    @Resource(name="csYccInfoDaoImpl")
    private CsYccInfoDao dao;
    
    
    @Autowired
    private WtWalletService wtWalletService;
    
    /**
     * 便利店管理公司服务
     */
    @Resource(name = "shopManageServiceImpl")
    private ShopManageService shopManageService;
    
    /**
     * 便利店管理公司服务
     */
    @Resource(name = "supermarketInfoServiceImpl")
    private SupermarketInfoServiceImpl supermarketInfoServiceImpl;
    @Autowired
    private CsYccStaffService csYccStaffService;
    
    @Autowired
    private CsYccAddressService  csYccAddressService;
    
    @Autowired
    private CsRegionsService csRegionsService;
    

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsYccInfo,Long> getDao() {
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
    public CsYccInfo findVoByName(String name) throws ServiceException {
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
    public void addYccInfo(CsYccInfo csYccInfo,CsYccAddress csYccAddress) throws ServiceException{
    	CsYccAddress csYccAddressVo = new CsYccAddress();
    	List<CsYccInfo> cList =  this.findList(new CsYccInfo());
    	for(CsYccInfo c : cList){
    		this.delId(c.getDmId());
    	}
    	this.addVo(csYccInfo);
    	//添加员工
    	CsYccStaff csYccStaff = new CsYccStaff();
    	csYccStaff.setPhone(csYccInfo.getContactPhone());
    	List<CsYccStaff> list = csYccStaffService.findList(csYccStaff);
    	if( list.size() >0 ){
    		throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,员工信息已存在" + csYccInfo.getContactPhone());
    	}else{
    		csYccStaff.setAccountType(new Byte("0"));
        	csYccStaff.setStatus(new Byte("0"));
        	csYccStaff.setName(csYccInfo.getName());
        	csYccStaff.setPwd(MD5Util.getMD5("123456"));
        	csYccStaff.setYccId(csYccInfo.getDmId());
        	csYccStaffService.addVo(csYccStaff);
    	}
    	
    	csYccAddressVo.setYccId(csYccInfo.getDmId());
    	csYccAddressVo.setProvinceId(csYccAddress.getProvinceId());
    	csYccAddressVo.setCityId(csYccAddress.getCityId());
    	csYccAddressVo.setCountyId(csYccAddress.getCountyId());
    	csYccAddressVo.setAddress(csYccAddress.getAddress());
    	csYccAddressVo.setFullAddress(csRegionsService.getFullAddressByIds(csYccAddressVo.getProvinceId(),csYccAddressVo.getCityId(), csYccAddressVo.getCountyId(),null,null)+csYccAddressVo.getAddress());
    	csYccAddressService.addVo(csYccAddressVo);
    	//添加钱包
    	wtWalletService.addYccWallet(csYccInfo.getDmId(), csYccInfo.getName(),supermarketInfoServiceImpl.findList(new SupermarketInfo()).get(0).getDmId(),shopManageService.findList(new ShopManage()).get(0).getDmId());
    }
}

