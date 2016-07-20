package com.qtz.sm.scm.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.scm.vo.CsGylAddress;
import com.qtz.sm.scm.vo.CsGylInfo;

/**
 * Title:CsGylInfoService<br/>
 * Description:(供应链公司信息SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGylInfoService extends BaseService<CsGylInfo,Long>{

	CsGylInfo findDeatailVo(Long suppId) throws ServiceException;
	
	void addGylInfo(CsGylInfo gylInfo,CsGylAddress csGylAddress)throws ServiceException;

}