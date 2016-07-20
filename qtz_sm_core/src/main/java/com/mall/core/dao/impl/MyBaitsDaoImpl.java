package com.mall.core.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.mall.core.common.Global;
import com.mall.core.dao.AbstractDao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.Pager;
import com.mall.core.vo.VO;
import com.mongodb.DBObject;

public abstract class MyBaitsDaoImpl<T extends VO<PK>,PK extends Serializable>  extends AbstractDao<T,PK>  implements BizDao<T,PK>{

	/** 
	* 【增加】VO对象
	* @param 	vo				VO对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public T addVo(T vo) throws DaoException {
		try {
			 return (T)getMyBaitsTemplate().add(getPreName(),Global.MYBTS_ADD_VO, vo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	
	@Override
	public void delVo(T vo) throws DaoException {
		
		
	}
	@Override
	public Long findCount(T vo) throws DaoException {
	    List<T> findList = findList(vo);
	    if(findList!=null){
	      return Long.parseLong(findList.size()+"");
	    }
		return 0l;
	}

	@Override
	public int modReplace(T vo) throws DaoException {
		return 0;
	}

	@Override
	public void delFileds(String filedsName, PK id) throws DaoException {
		
	}
	public T findVoMax(T vo,String filed,int sort) throws DaoException{
		return null;
	}
	/** 
	* 【增加】VO集合对象
	* @param 	list			VO集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	public void addList(List <T> list) throws DaoException {
		try {
			getMyBaitsTemplate().add(getPreName(),Global.MYBTS_ADD_LIST, list);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	
	/** 
	* 【删除】ID值的对象
	* @param 	id			  	VO对应的ID值
	* @throws 	DaoException  	DAO异常对象
	*/
	
	public void delId(PK id) throws DaoException {
		try {
			getMyBaitsTemplate().del(getPreName(),Global.MYBTS_DEL_ID, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	
	/** 
	* 【删除】多个ID值的对象
	* @param 	ids			  	多个ID值
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public void delIds(PK... ids) throws DaoException {
		try {
			getMyBaitsTemplate().del(getPreName(),Global.MYBTS_DEL_IDS, ids);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	
	/** 
	* 【删除】List中多个ID值的对象
	* @param 	list		  	List集合ID值
	* @throws 	DaoException  	DAO异常对象
	*/
	
	public void delList(Collection <PK> list) throws DaoException {
		try {
			getMyBaitsTemplate().del(getPreName(),Global.MYBTS_DEL_LIST, list);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	
	/** 
	* 【修改】VO对象
	* @param 	vo		  	  	vo对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public void modVo(T vo) throws DaoException {
		try {
			getMyBaitsTemplate().mod(getPreName(),Global.MYBTS_MOD_VO, vo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	
	/** 
	* 【修改】VO对象,字段为NULL的不修改
	* @param 	vo		  	  	vo对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public void modVoNotNull(T vo) throws DaoException {
		try {
			getMyBaitsTemplate().mod(getPreName(),Global.MYBTS_MOD_VO_NOT_NULL, vo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
	
	
	/** 
	* 【查询】ID值对应的VO
	* @param 	id			  	字段ID值
	* @return	VO			  	VO对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public T findVo(PK id,T clazz) throws DaoException {
		try {
			return (T) getMyBaitsTemplate().findVoBySqlid(getPreName(),Global.MYBTS_FIND_VO, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	/** 
	* 【查询】List对象方法
	* @param 	param			参数对象			（必须）
	* @return	List<E>			E对象的List集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public List<T> findList(T param) throws DaoException {
		try {
			return getMyBaitsTemplate().findList(getPreName(),Global.MYBTS_FIND_List, param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	/** 
	* 【分页查询】分页对象
	* @param 	page		  	分页对象
	* @return	Pager	  	  	分页对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public Pager<T,PK> query(Pager<T,PK> page,Class<T> clazz) throws DaoException {
		try {
			return getMyBaitsTemplate().query(getPreName(),page);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".query(" + page + ")调用【报错】了！", e);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(Class<T> clazz,PK... ids) throws DaoException{
		return null;
	}
	
	/**
	 * 
	  * 操作记录
	  * @param sourceId
	  * @return List
	  * @throws ServiceException  
	  * @time:2016年5月23日 下午4:54:04
	  * @version
	 */
	@SuppressWarnings("unchecked")
	public List<T> findOperateTakeList(Long sourceId) throws DaoException{
		try {
			return getMyBaitsTemplate().findList(getPreName(),Global.MYBTS_FIND_TAKE_List, sourceId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 
	  * 【查询返回指定字段】
	  * @param id
	  * @param clazz
	  * @param fileds
	  * @return
	  * @throws DaoException
	 */
	public T findVo(PK id, T clazz ,DBObject fileds) throws DaoException{
		return null;
	}
	
	public List<T> findList(T obj,DBObject fileds) throws DaoException{
		return null;
	}
	
	public Pager<T,PK> query(Pager<T,PK> page, Class<T> clazz,DBObject fileds) throws DaoException{
		return page;
	}
	
	/** 
	* 【分页查询】分页对象
	* @param 	page		  	分页对象
	* @return	Pager	  	  	分页对象
	* @throws 	DaoException  	DAO异常对象
	*/
	
	@SuppressWarnings("unchecked")
	public Pager<T,PK> queryAduit(Pager<T,PK> page,Class<T> clazz) throws DaoException {
		try {
			return getMyBaitsTemplate().queryAduit(getPreName(),page);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + ".query(" + page + ")调用【报错】了！", e);
		}
	}
	
}
