package com.qtz.sm.stc.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.stc.vo.CsYccAddress;
import com.qtz.sm.stc.vo.CsYccInfo;

/**
 * Title:CsYccInfoService<br/>
 * Description:(云仓储公司信息SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 谷一帅-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsYccInfoService extends BaseService<CsYccInfo,Long>{
	
	/**
	 * 根据仓储名称精确查找（数据库记录）
	 * 
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	CsYccInfo findVoByName(String name) throws ServiceException;
	
	void addYccInfo(CsYccInfo csYccInfo,CsYccAddress csYccAddress) throws ServiceException;

}