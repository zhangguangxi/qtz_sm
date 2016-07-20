package com.qtz.sm.supp.dao;

import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.supp.vo.CsGysInfo;

/**
 * Title:CsGysInfoDao<br/>
 * Description:(供应商信息DAO接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * 
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGysInfoDao extends BizDao<CsGysInfo, Long> {

	/**
	 * 根据省市前缀获取最大编号,没有返回空
	 * 
	 * @param prefix
	 * @return
	 * @throws DaoException
	 */
	String findMaxIdentifier(@Param("prefix") String prefix) throws DaoException;

	/**
	 * 根据公司名称精确查找供应商
	 * 
	 * @param name
	 * @return
	 * @throws DaoException
	 */
	CsGysInfo findVoByName(@Param("name") String name) throws DaoException;

}
