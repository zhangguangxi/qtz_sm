package com.qtz.sm.stc.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.stc.dao.CsYccAddressDao;
import com.qtz.sm.stc.vo.CsYccAddress;

/**
 * Title:CsYccAddressDaoImpl<br/>
 * Description:(云仓储地址DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csYccAddressDaoImpl")
public class CsYccAddressDaoImpl extends MyBaitsDaoImpl<CsYccAddress,Long> implements CsYccAddressDao{

    /**MYBatis命名空间名*/
    private static String preName = CsYccAddressDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }
    
    @Override
	public CsYccAddress findVoByYccId(Long yccId) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsYccAddressDao.class).findVoByYccId(yccId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

}

