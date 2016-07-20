package com.qtz.sm.store.dao;

import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.store.vo.CsCczxInfo;

/**
 * Title:CsCczxInfoDao<br/>
 * Description:(仓储中心信息DAO接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsCczxInfoDao extends BizDao<CsCczxInfo,Long>{
	
	CsCczxInfo findVoByName(@Param("name") String name) throws DaoException;

}
