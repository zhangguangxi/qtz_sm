package com.qtz.sm.store.dao;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.store.vo.CsCczxAddress;

/**
 * Title:CsCczxAddressDao<br/>
 * Description:(仓储中心地址DAO接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsCczxAddressDao extends BizDao<CsCczxAddress,Long>{
	
	CsCczxAddress findVoByCczxId(Long cczxId)throws DaoException;

}
