package com.qtz.sm.stc.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.stc.vo.CsYccAddress;

/**
 * Title:CsYccAddressService<br/>
 * Description:(云仓储地址SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsYccAddressService extends BaseService<CsYccAddress,Long>{
	
	 CsYccAddress findVoByYccId(Long yccId)throws ServiceException;

}