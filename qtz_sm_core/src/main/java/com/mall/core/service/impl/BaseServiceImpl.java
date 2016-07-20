package com.mall.core.service.impl;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.mall.core.common.FifteenLongId;
import com.mall.core.common.ReflectUtils;
import com.mall.core.common.SpringContextHolder;
import com.mall.core.common.UUIDFactory;
import com.mall.core.common.impl.FifteenLongIdImpl;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.mall.core.vo.VO;
import com.mongodb.DBObject;

/**
 * <p>Title:BaseServiceImpl</p>
 * <p>Description:业务服务基类实现类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-10-3
 */

public abstract class BaseServiceImpl<T extends VO<PK>,PK extends Serializable> implements BaseService<T,PK>{

	
	/** 
	* 【取得】日志对象
	* @return  	日志对象
	*/
	protected abstract LogTool getLog();
	
	/** 
	* 【取得】业务DAO对象
	* @return  	业务DAO对象
	*/
	protected abstract BizDao<T,PK> getDao();
	
	/** 
	* 【增加】VO对象
	* @param 	vo					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T addVo(T vo)  throws ServiceException {
		try {
			if (null == vo.getDmId() || "".equals(vo.getDmId())) {
				
				Class c = ReflectUtils.getClassGenricType(this.getClass(),1);
				if(null == c){
					throw new ServiceException("主键类型为空，主键="+c);
				}
				else if(c.toString().endsWith(String.class.toString()))
					vo.setDmId((PK) UUIDFactory.newUUID());
				else if(c.toString().endsWith(Long.class.toString())){
					vo.setDmId((PK)new Long(((FifteenLongId) SpringContextHolder.getApplicationContext().getBean(FifteenLongIdImpl.class)).nextId()));
				}
				else
					throw new ServiceException("未知的主键类型，类型="+c.toString());
			}
			return getDao().addVo(vo);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【增加】多个VO对象的集合
	* @param 	list				多个VO对象的集合
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addList(List <T> list) throws ServiceException {
		try {
			if (null != list && list.size() > 0) {
				for (T vo: list) {
					if (null == vo.getDmId() || "".equals(vo.getDmId())) {
						
						Class c = ReflectUtils.getClassGenricType(this.getClass(),1);
						if(null == c){
							throw new ServiceException("主键类型为空，主键="+c);
						}
						else if(c.toString().endsWith(String.class.toString()))
							vo.setDmId((PK) UUIDFactory.newUUID());
						else if(c.toString().endsWith(Long.class.toString()))
							vo.setDmId((PK)new Long(((FifteenLongId) SpringContextHolder.getBean("fifteenLongIdImpl")).nextId()));
						else
							throw new ServiceException("未知的主键类型，类型="+c.toString());
						
					}
				}
				getDao().addList(list);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【删除】ID值的单条记录
	* @param 	id					ID值
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public void delId(PK id)throws ServiceException {
		try {
			getDao().delId(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【删除】多个ID值的多条记录
	* @param 	ids					多个ID值
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	@SuppressWarnings("unchecked")
	public void delIds(PK... ids) throws ServiceException {
		try {
			getDao().delIds(ids);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【删除】集合中多个ID值的多条记录
	* @param 	list				多个ID值的集合
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public void delList(Collection <PK> list) throws ServiceException {
		try {
			getDao().delList(list);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【修改】VO对象
	* @param 	vo					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public void modVo(T vo) throws ServiceException {
		try {
			getDao().modVo(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【修改】VO对象,字段为NULL的不修改字段值
	* @param 	vo					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public void modVoNotNull(T vo)  throws ServiceException {
		try {
			getDao().modVoNotNull(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【查询】ID值对应的VO对象
	* @param 	id					ID值
	* @return	VO			  		VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public T findVo(PK id ,T clazz) throws ServiceException {
		try {
			return getDao().findVo(id,clazz);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	public T findVo(PK id,T clazz,DBObject fileds) throws ServiceException{
		try {
			return getDao().findVo(id,clazz,fileds);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【查询】List对象方法
	* @param 	obj					参数对象			（必须）
	* @return	List<E>				E对象的List集合对象
	* @throws 	ServiceException  	SERVER异常对象
	*/
	public List<T> findList(T obj) throws ServiceException {
		try {
			return getDao().findList(obj);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 查询  list 
	 */
	public List<T> findList(T obj,DBObject fileds) throws ServiceException {
		try {
			return getDao().findList(obj,fileds);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【分页查询】分页对象
	* @param 	page		  		分页对象
	* @return	Pager	  	  		分页对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public Pager<T,PK> query(Pager<T,PK> page ,Class<T> clazz) throws ServiceException {
		try {
			return getDao().query(page,clazz);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【分页查询】分页对象
	* @param 	page		  		分页对象
	* @return	Pager	  	  		分页对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public Pager<T,PK> query(Pager<T,PK> page ,Class<T> clazz,DBObject fileds) throws ServiceException {
		try {
			return getDao().query(page,clazz,fileds);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【保存】VO对象
	* @param 	vo					VO对象
	* @return	VO					VO对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T save(T vo) throws ServiceException {
		T result = null;
		try {
			if (null == vo.getDmId() || "".equals(vo.getDmId())) {
				
				Class c = ReflectUtils.getClassGenricType(this.getClass(),1);
				if(null == c){
					throw new ServiceException("主键类型为空，主键="+c);
				}
				else if(c.toString().endsWith(String.class.toString()))
					vo.setDmId((PK) UUIDFactory.newUUID());
				else if(c.toString().endsWith(Long.class.toString()))
					vo.setDmId((PK)new Long(((FifteenLongId) SpringContextHolder.getBean("fifteenLongIdImpl")).nextId()));
				else
					throw new ServiceException("未知的主键类型，类型="+c.toString());
				
				result = getDao().addVo(vo);
				
			} else {
				getDao().modVo(vo);
				result = vo;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return result;
	}
	
	/** 
	* 【保存】列表集合
	* @param 	list				列表集合
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void saveList(Collection <T> list)throws ServiceException {
		try {
			if (null != list && list.size() > 0) {
				T vo = null;
				Iterator <T> iter = list.iterator();
				while (iter.hasNext()) {
					vo = iter.next();
					if (null == vo.getDmId() || "".equals(vo.getDmId())) {

						Class c = ReflectUtils.getClassGenricType(this.getClass(),1);
						if(null == c){
							throw new ServiceException("主键类型为空，主键="+c);
						}
						else if(c.toString().endsWith(String.class.toString()))
							vo.setDmId((PK) UUIDFactory.newUUID());
						else if(c.toString().endsWith(Long.class.toString()))
							vo.setDmId((PK)new Long(((FifteenLongId) SpringContextHolder.getBean("fifteenLongIdImpl")).nextId()));
						else
							throw new ServiceException("未知的主键类型，类型="+c.toString());

						getDao().addVo(vo);
					} else {
						getDao().modVoNotNull(vo);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	@Override
	public T  modReplace(T vo) throws ServiceException{
		try{
			if(vo==null || vo.getDmId()==null){
				throw new ServiceException("null exception");
			}
			getDao().modReplace(vo);
		}catch(DaoException e){
			throw new ServiceException(e);
		}
		
		return vo;
	}
	
	@Override
	public List<T> findOperateTakeList(Long sourceId) throws ServiceException {
		try {
			return getDao().findOperateTakeList(sourceId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	/** 
	* 【分页查询】分页对象
	* @param 	page		  		分页对象
	* @return	Pager	  	  		分页对象
	* @throws 	ServiceException  	SERVICE异常对象
	*/
	public Pager<T,PK> queryAudit(Pager<T,PK> page ,Class<T> clazz) throws ServiceException {
		try {
			return getDao().queryAduit(page,clazz);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}