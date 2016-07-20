package com.qtz.sm.session.service;

import java.util.Map;

import com.mall.core.exception.ServiceException;
import com.mall.core.vo.DdmSession;
import com.qtz.sm.session.vo.UserClientInfo;
/**
 * 
 * <p>Title:UserLoginService</p>
 * <p>Description:(用户登录)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015年3月12日
 */
public interface UserLoginService {
	/**
	 * 
	* 【获取用户融云 token】(这里用一句话描述这个方法的作用)
	* @param userid
	* @return
	* @throws ServiceException
	 */
	String findRongToken(Long userid)throws ServiceException;
	/**
	 * 
	* 【用户登录】(这里用一句话描述这个方法的作用)
	* @param phone
	* @param pwd
	* @param sid
	* @return
	* @throws ServiceException
	 */
	Map<String, Object> login (String phone ,String pwd ,String sid) throws ServiceException;
	
	/**
	 * 
	  * 【用户登录】
	  * @param phone
	  * @param pwd
	  * @param ddmSession
	  * @param userType			登陆时候用户类型
	  * @return
	  * @throws ServiceException
	 */
	Map<String, Object> login (String phone ,String pwd ,DdmSession ddmSession,UserClientInfo clientInfo) throws ServiceException;
	/**
	 * 
	  * 【用户登录】
	  * @param phone
	  * @param pwd
	  * @param ddmSession
	  * @param userType			登陆时候用户类型
	  * @return
	  * @throws ServiceException
	 */
	Map<String, Object> login (String phone ,String pwd ,DdmSession ddmSession,UserClientInfo clientInfo,Integer userType) throws ServiceException;
	/**
	 * 
	* 【员工登陆】(这里用一句话描述这个方法的作用)
	* @param account
	* @param pwd
	* @param sesid  session id
	* @return
	* @throws ServiceException
	 */
	boolean loginEmp(String account,String pwd,String sesid)throws ServiceException;
}

