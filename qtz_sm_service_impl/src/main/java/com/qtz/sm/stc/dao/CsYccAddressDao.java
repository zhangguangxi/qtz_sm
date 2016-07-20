package com.qtz.sm.stc.dao;

import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.stc.vo.CsYccAddress;

/**
 * Title:CsYccAddressDao<br/>
 * Description:(云仓储地址DAO接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsYccAddressDao extends BizDao<CsYccAddress,Long>{
	
	 CsYccAddress findVoByYccId(@Param("yccId") Long yccId) throws DaoException;

}
