package com.qtz.sm.store.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;

import com.qtz.sm.store.vo.CsCczxAddress;
import com.qtz.sm.supp.vo.CsGysAddress;
import com.qtz.sm.store.dao.CsCczxAddressDao;
import com.qtz.sm.store.service.CsCczxAddressService;

/**
 * Title:CsCczxAddressServiceImpl<br/>
 * Description:(仓储中心地址SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * 
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csCczxAddressServiceImpl")
public class CsCczxAddressServiceImpl extends BaseServiceImpl<CsCczxAddress, Long>implements CsCczxAddressService {

	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(CsCczxAddressServiceImpl.class);
	/** 注入CsCczxAddressDao接口类 */
	@Resource(name = "csCczxAddressDaoImpl")
	private CsCczxAddressDao dao;

	/**
	 * 【取得】业务DAO对象
	 * 
	 * @return 业务DAO对象
	 */
	@Override
	protected BizDao<CsCczxAddress, Long> getDao() {
		return dao;
	}

	/**
	 * 【取得】日志对象
	 * 
	 * @return 日志对象
	 */
	@Override
	protected LogTool getLog() {
		return log;
	}

	@Override
	public CsCczxAddress findOnlyAddress(Long suppId) throws ServiceException {
		if (null == suppId) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
		}
		CsCczxAddress vo = new CsCczxAddress();
		vo.setCczxId(suppId);
		List<CsCczxAddress> voList = this.findList(vo);
		if (voList.size() == 1) {
			return voList.get(0);
		} else {
			for (CsCczxAddress a : voList) {
				this.delId(a.getDmId());
			}
			return null;
		}
	}

	@Override
	public void delAllAddress(Long suppId) throws ServiceException {
		if (null == suppId) {
			throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
		}
		CsCczxAddress vo = new CsCczxAddress();
		vo.setCczxId(suppId);
		List<CsCczxAddress> voList = this.findList(vo);
		for (CsCczxAddress a : voList) {
			this.delId(a.getDmId());
		}
	}

	@Override
	public CsCczxAddress findVoByCczxId(Long cczxId) throws ServiceException {
		if(null == cczxId){
   		 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,yccId=" + cczxId);
   	}
   	try {
           return dao.findVoByCczxId(cczxId);
       } catch (DaoException e) {
           throw new ServiceException(e);
       }
	}

}
