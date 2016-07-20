package com.qtz.sm.session.dao;

import java.util.List;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.DdmSession;

public interface DdmSessionDao {
	/** 
	* 【获取session】(这里用一句话描述这个方法的作用)
	* @param sid
	* @return
	* @throws RpcException  
	*/
	public DdmSession getSession(String sid) throws ServiceException;
	
	/** 
	 * 【保存session】(这里用一句话描述这个方法的作用)
	 * @param sid
	 * @return
	 * @throws RpcException  
	 */
	public void saveSession(DdmSession s) throws ServiceException;
	/** 
	 * 【保存session】(这里用一句话描述这个方法的作用)
	 * @param sid
	 * @param minute 过期时间  分钟
	 * @return
	 * @throws RpcException  
	 */
	public void saveSession(DdmSession s,int minute) throws ServiceException;
	/** 
	* 【删除一个session】(这里用一句话描述这个方法的作用)
	* @param sid
	* @throws RpcException  
	*/
	public boolean removeSession(String sid) throws ServiceException;
	/**
	 * 
	  * 【取得所有session】
	  * @return
	  * @throws DaoException
	 */
	public List<DdmSession> getAPPAllSession() throws ServiceException; 
	

}
