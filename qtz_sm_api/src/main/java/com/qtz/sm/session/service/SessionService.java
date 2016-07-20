package com.qtz.sm.session.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.vo.DdmSession;
import com.qtz.sm.session.vo.User;


/**
 * 
 * <p>Title:SessionService</p>
 * <p>Description:(session 服务借口)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015年3月10日
 */
public interface SessionService {
	
	/** 
	* 【获取 app user】(这里用一句话描述这个方法的作用)

	* @param sid
	* @return
	* @throws RpcException  
	*/
	public User getSessionUser(String sid) throws ServiceException;
	/** 
	* 【获取 app session,如果不存在则创建一个新session】(这里用一句话描述这个方法的作用)
	* <p>更改session后必须调用saveSession方法</p>
	* @param sid
	* @return
	* @throws RpcException  
	*/
	public DdmSession getAppSession(String sid) throws ServiceException;
	
	/** 
	 * 【保存 app session】(这里用一句话描述这个方法的作用)
	 * @param sid
	 * @return
	 * @throws RpcException  
	 */
	public void saveAppSession(DdmSession s) throws ServiceException;
	/** 
	 * 【保存 app session】(这里用一句话描述这个方法的作用)
	 * @param sid
	 * @param minute 过期时间  分钟
	 * @return
	 * @throws RpcException  
	 */
	public void saveAppSession(DdmSession s,int minute) throws ServiceException;
	
	/** 
	* 【新建一个 app session】(这里用一句话描述这个方法的作用)
	* @return
	* @throws RpcException  
	*/
	public DdmSession newAppSession() throws ServiceException;
	
	/** 
	* 【删除一个 app session】(这里用一句话描述这个方法的作用)
	* @param sid
	* @throws RpcException  
	*/
	public boolean removeAppSession(String sid) throws ServiceException;
	/**
	 * 
	  * 【取得所有session】
	  * @return
	  * @throws ServiceException
	 */
	public List<DdmSession> getAPPAllSession() throws ServiceException;
	/**
	 * 
	* 【设置过期时间】(这里用一句话描述这个方法的作用)
	* @param sid session id
	* @param minutes 延迟过期分钟
	* @throws ServiceException
	 */
	void saveWaitTime(String sid,int minutes)throws ServiceException;
	/**
	 * 
	  * 【刷新用户】
	  * @throws ServiceException  
	  * @time:2015年4月23日 下午6:45:22
	  * @author 涂鑫
	  * @version
	 */
	void refreshUser(Long userID,String sid) throws ServiceException;
}
