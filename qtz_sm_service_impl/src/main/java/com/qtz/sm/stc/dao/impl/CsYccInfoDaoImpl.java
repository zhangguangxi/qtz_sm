package com.qtz.sm.stc.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.stc.dao.CsYccInfoDao;
import com.qtz.sm.stc.vo.CsYccInfo;

/**
 * Title:CsYccInfoDaoImpl<br/>
 * Description:(云仓储公司信息DAO实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Repository("csYccInfoDaoImpl")
public class CsYccInfoDaoImpl extends MyBaitsDaoImpl<CsYccInfo,Long> implements CsYccInfoDao{

    /**MYBatis命名空间名*/
    private static String preName = CsYccInfoDao.class.getName();

    @Override
    protected String getPreName() {
        return preName;
    }

    @Override
	public CsYccInfo findVoByName(String name) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsYccInfoDao.class).findVoByName(name);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
}

