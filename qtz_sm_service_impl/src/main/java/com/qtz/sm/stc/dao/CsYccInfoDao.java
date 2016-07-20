package com.qtz.sm.stc.dao;

import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.stc.vo.CsYccInfo;

/**
 * Title:CsYccInfoDao<br/>
 * Description:(云仓储公司信息DAO接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsYccInfoDao extends BizDao<CsYccInfo,Long>{
	
	/**
	 * 根据仓储名称精确查找供应商
	 * 
	 * @param name
	 * @return
	 * @throws DaoException
	 */
	CsYccInfo findVoByName(@Param("name") String name) throws DaoException;

}
