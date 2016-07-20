package com.qtz.sm.supp.service;

import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.supp.vo.CsGysInfo;

/**
 * Title:CsGysInfoService<br/>
 * Description:(供应商信息SERVICE接口类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * 
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
public interface CsGysInfoService extends BaseService<CsGysInfo, Long> {

	/**
	 * 根据省市ID获取下一个编号
	 * 
	 * @param provinceId
	 * @param cityId
	 * @return
	 * @throws ServiceException
	 */
	String findNextIdentifier(Integer provinceId, Integer cityId) throws ServiceException;

	/**
	 * 新增供应商并初始化相关数据(由供应链公司操作)
	 * 
	 * @param info
	 * @throws ServiceException
	 */
	void addSuppAndInit(CsGysInfo info, Map<String,Object> map) throws ServiceException;

	/**
	 * 根据公司名称精确查找供应商（数据库记录）
	 * 
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	CsGysInfo findVoByName(String name) throws ServiceException;
	
	
	/**保存重新编辑的供应商
	 * @param info
	 * @throws ServiceException
	 */
	void modSuppInfo(CsGysInfo info, Map<String,Object> map) throws ServiceException;

	/**
	 * 获取供应商详情，包含附属属性
	 * @param suppId
	 * @return
	 * @throws ServiceException
	 */
	CsGysInfo findDeatailVo(Long suppId) throws ServiceException;

}