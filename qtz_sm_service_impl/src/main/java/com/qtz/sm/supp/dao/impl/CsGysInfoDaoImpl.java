package com.qtz.sm.supp.dao.impl;

import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.supp.vo.CsGysInfo;
import com.qtz.sm.supp.dao.CsGysInfoDao;

/**
 * Title:CsGysInfoDaoImpl<br/>
 * Description:(供应商信息DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csGysInfoDaoImpl")
public class CsGysInfoDaoImpl extends MyBaitsDaoImpl<CsGysInfo,Long> implements CsGysInfoDao{

    /**MYBatis命名空间名*/
    private static String preName = CsGysInfoDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

	@Override
	public String findMaxIdentifier(String prefix) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsGysInfoDao.class).findMaxIdentifier(prefix);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

	@Override
	public CsGysInfo findVoByName(String name) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsGysInfoDao.class).findVoByName(name);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

}

