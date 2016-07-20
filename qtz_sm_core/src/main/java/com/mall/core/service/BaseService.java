package com.mall.core.service;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.mall.core.vo.VO;
import com.mongodb.DBObject;

/**
 * <p>Title:BaseService</p>
 * <p>Description:业务服务基类接口</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-10-03
 */

public interface BaseService<T extends VO<PK>,PK extends Serializable> {
	
	/** 
	* 【增加】VO对象
	* @param 	vo					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public T addVo(T vo)  throws ServiceException;
	
	
	/** 
	* 【增加】多个VO对象的集合
	* @param 	list				多个VO对象的集合
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public void addList(List <T> list) throws ServiceException;
	
	
	/** 
	* 【删除】ID值的单条记录
	* @param 	id					ID值
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public void delId(PK id)throws ServiceException;

	
	/** 
	* 【删除】多个ID值的多条记录
	* @param 	ids					多个ID值
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public void delIds(PK... ids) throws ServiceException;
	
	
	/** 
	* 【删除】集合中多个ID值的多条记录
	* @param 	list				多个ID值的集合
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public void delList(Collection <PK> list) throws ServiceException;
	
	
	/** 
	* 【修改】VO对象
	* @param 	vo					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public void modVo(T vo) throws ServiceException;
	
	
	/** 
	* 【修改】VO对象,字段为NULL的不修改字段值
	* @param 	vo					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public void modVoNotNull(T vo)  throws ServiceException;
	
	
	/** 
	* 【查询】ID值对应的VO对象
	* @param 	id					ID值
	* @return	VO			  		VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public T findVo(PK id,T clazz) throws ServiceException;
	
	/**
	 * 
	  * 【查询返回指定字段值】
	  * @param id
	  * @param clazz
	  * @param fileds
	  * @return
	  * @throws ServiceException
	 */
	public T findVo(PK id,T clazz,DBObject fileds) throws ServiceException;
	
	
	/** 
	* 【查询】List对象方法
	* @param 	obj					参数对象			（必须）
	* @return	List<E>				E对象的List集合对象
	* @throws 	ServiceException  	SERVER异常对象
	*/
	
	public List<T> findList(T obj) throws ServiceException;
	/**
	 * 
	  * 【查询】 list对象方法
	  * @param obj 参数
	  * @param fileds 返回指定字段
	  * @return
	  * @throws ServiceException
	 */
	public List<T> findList(T obj,DBObject fileds) throws ServiceException;
	
	
	/** 
	* 【分页查询】分页对象
	* @param 	page		  		分页对象
	* @return	Pager	  	  		分页对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public Pager<T,PK> query(Pager<T,PK> page, Class<T> clazz) throws ServiceException;
	
	
	/** 
	* 【分页查询】分页对象
	* @param 	page		  		分页对象
	* @return	Pager	  	  		分页对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public Pager<T,PK> query(Pager<T,PK> page, Class<T> clazz,DBObject fileds) throws ServiceException;
	
	
	/** 
	* 【保存】VO对象
	* @param 	vo					VO对象
	* @return	vo					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public T save(T vo) throws ServiceException;
	
	
	/** 
	* 【保存】列表集合
	* @param 	list				列表集合
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	
	public void saveList(Collection <T> list)throws ServiceException;
	
	/**
	 * 
	  * 【替换 重改结构】
	  * @param vo
	  * @return
	  * @throws ServiceException  
	  * @time:2015年8月5日 下午4:54:04
	  * @author 涂鑫
	  * @version
	 */
	public T  modReplace(T vo) throws ServiceException;


	/**
	 * 
	  * 操作记录
	  * @param sourceId
	  * @return List
	  * @throws ServiceException  
	  * @time:2016年5月23日 下午4:54:04
	  * @version
	 */
	public List<T> findOperateTakeList(Long sourceId) throws ServiceException; 

	public Pager<T,PK> queryAudit(Pager<T,PK> page ,Class<T> clazz) throws ServiceException;
}