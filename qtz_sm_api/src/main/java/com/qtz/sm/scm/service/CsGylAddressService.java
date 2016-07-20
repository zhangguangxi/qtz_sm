package com.qtz.sm.scm.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.scm.vo.CsGylAddress;

/**
 * Title:CsGylAddressService<br/>
 * Description:(供应链公司地址SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGylAddressService extends BaseService<CsGylAddress,Long>{

	CsGylAddress findOnlyAddress(Long suppId) throws ServiceException;

}