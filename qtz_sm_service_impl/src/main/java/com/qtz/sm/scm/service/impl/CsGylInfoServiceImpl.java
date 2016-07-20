package com.qtz.sm.scm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.MD5Util;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.scm.dao.CsGylInfoDao;
import com.qtz.sm.scm.service.CsGylAddressService;
import com.qtz.sm.scm.service.CsGylInfoService;
import com.qtz.sm.scm.service.CsGylStaffService;
import com.qtz.sm.scm.vo.CsGylAddress;
import com.qtz.sm.scm.vo.CsGylInfo;
import com.qtz.sm.scm.vo.CsGylStaff;
import com.qtz.sm.stc.service.CsYccInfoService;
import com.qtz.sm.stc.vo.CsYccInfo;
import com.qtz.sm.wallet.service.WtWalletService;

/**
 * Title:CsGylInfoServiceImpl<br/>
 * Description:(供应链公司信息SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGylInfoServiceImpl")
public class CsGylInfoServiceImpl extends BaseServiceImpl<CsGylInfo,Long> implements CsGylInfoService{

	

//    @Resource(name = "csGylAddressServiceImpl")
//    private CsGylAddressService csGylAddressService;
	
    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsGylInfoServiceImpl.class);
    /**注入CsGylInfoDao接口类*/
    @Resource(name="csGylInfoDaoImpl")
    private CsGylInfoDao dao;

    @Autowired
    private WtWalletService wtWalletService;
    
    @Autowired
    private CsYccInfoService csYccInfoService;
    
    @Autowired
    private CsGylStaffService csGylStaffService;
    
    @Autowired
    private CsGylAddressService csGylAddressService;
    
    @Autowired
    private CsRegionsService csRegionsService;
    
    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsGylInfo,Long> getDao() {
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
	public CsGylInfo findDeatailVo(Long suppId) throws ServiceException {
		if (null == suppId) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
        }
        CsGylInfo info = this.findVo(suppId,null);
        if(null == info){
            throw new ServiceException(ExceptionConstants.ERRORCODE_7,"不存在该供应商,suppId="+suppId);
        }
        CsGylAddress address = csGylAddressService.findOnlyAddress(suppId);
        info.setJsonAddress(JSON.toJSONString(address));
        return info;
	}
	
	public void addGylInfo(CsGylInfo gylInfo,CsGylAddress csGylAddress)throws ServiceException{
		List<CsGylInfo> list = this.findList(new CsGylInfo());
		CsGylAddress csGylAddressVo = new CsGylAddress();
		for(CsGylInfo g :list){
			this.delId(g.getDmId());
		}
		this.addVo(gylInfo);
		CsGylStaff csGylStaff =new CsGylStaff();
		csGylStaff.setPhone(gylInfo.getContactPhone());
		List<CsGylStaff> csGylStafflist = csGylStaffService.findList(csGylStaff);
		if(csGylStafflist.size() >0){
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,员工信息已存在" + gylInfo.getContactPhone());
		}else{
			csGylStaff.setAccountType(new Byte("0"));
			csGylStaff.setStatus(new Byte("0"));
			csGylStaff.setName(gylInfo.getName());
			csGylStaff.setPwd(MD5Util.getMD5("123456"));
			csGylStaff.setGylId(gylInfo.getDmId());
			csGylStaffService.addVo(csGylStaff);
		}
		csGylAddressVo.setGylId(gylInfo.getDmId());
		csGylAddressVo.setProvinceId(csGylAddress.getProvinceId());
		csGylAddressVo.setCityId(csGylAddress.getCityId());
		csGylAddressVo.setCountyId(csGylAddress.getCountyId());
		csGylAddressVo.setAddress(csGylAddress.getAddress());
		csGylAddressVo.setFullAddress(csRegionsService.getFullAddressByIds(csGylAddress.getProvinceId(),csGylAddress.getCityId(), csGylAddress.getCountyId(),null,null)+csGylAddress.getAddress());
		
    	csGylAddressService.addVo(csGylAddressVo);
		//添加钱包
    	wtWalletService.addGylWallet(gylInfo.getDmId(), gylInfo.getName() ,csYccInfoService.findList(new CsYccInfo()).get(0).getDmId());
	}

}

