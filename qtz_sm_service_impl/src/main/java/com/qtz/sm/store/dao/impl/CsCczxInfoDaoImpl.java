package com.qtz.sm.store.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.store.dao.CsCczxInfoDao;
import com.qtz.sm.store.vo.CsCczxInfo;

/**
 * Title:CsCczxInfoDaoImpl<br/>
 * Description:(仓储中心信息DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csCczxInfoDaoImpl")
public class CsCczxInfoDaoImpl extends MyBaitsDaoImpl<CsCczxInfo,Long> implements CsCczxInfoDao{

    /**MYBatis命名空间名*/
    private static String preName = CsCczxInfoDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

	@Override
	public CsCczxInfo findVoByName(String name) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsCczxInfoDao.class).findVoByName(name);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}

}

