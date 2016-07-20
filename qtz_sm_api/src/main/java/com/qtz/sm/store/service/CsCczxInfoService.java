package com.qtz.sm.store.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.store.vo.CsCczxAddress;
import com.qtz.sm.store.vo.CsCczxInfo;

/**
 * Title:CsCczxInfoService<br/>
 * Description:(仓储中心信息SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author gyl
 * @version v1.0 2016-04-20
 */
public interface CsCczxInfoService extends BaseService<CsCczxInfo,Long>{

	CsCczxInfo findDeatailVo(Long suppId) throws ServiceException;
	
	CsCczxInfo findVoByCsCczxInfoName (String name) throws ServiceException;

	void addCczxInfo(CsCczxInfo csCczxInfo ,CsCczxAddress csCczxAddress )throws ServiceException;
}