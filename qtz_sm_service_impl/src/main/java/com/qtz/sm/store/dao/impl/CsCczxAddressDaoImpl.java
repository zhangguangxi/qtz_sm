package com.qtz.sm.store.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.store.dao.CsCczxAddressDao;
import com.qtz.sm.store.vo.CsCczxAddress;

/**
 * Title:CsCczxAddressDaoImpl<br/>
 * Description:(仓储中心地址DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csCczxAddressDaoImpl")
public class CsCczxAddressDaoImpl extends MyBaitsDaoImpl<CsCczxAddress,Long> implements CsCczxAddressDao{

    /**MYBatis命名空间名*/
    private static String preName = CsCczxAddressDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

	@Override
	public CsCczxAddress findVoByCczxId(Long cczxId) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsCczxAddressDao.class).findVoByCczxId(cczxId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

}

