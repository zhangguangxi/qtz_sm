package com.mall.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.mall.core.common.Global;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.mall.core.vo.VO;

public class MyBaitsTemplate<T extends VO<PK>,PK extends Serializable> extends SqlSessionDaoSupport{
	
	/** 
	* 【增加】方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @throws 	DaoException  	DAO异常对象
	*/
	public void add(String perName,String sqlid) throws DaoException {
		if (null == sqlid || sqlid.equals("")) throw new DaoException(this.getClass().getName() + ".add(" + sqlid + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			@SuppressWarnings("unused")
			int result = getSqlSession().insert(sqlid);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/** 
	* 【增加】方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				实体VO对象或List对象	（必须）
	* @throws 	DaoException  	DAO异常对象
	*/
	@SuppressWarnings("rawtypes")
	public Object add(String perName,String sqlid, Object obj) throws DaoException {
		if (null == sqlid || sqlid.equals("") || null == obj || (null != obj && obj instanceof List && ((List)obj).size() == 0)) throw new DaoException(this.getClass().getName() + ".add(" + sqlid + ", " + obj + ")传入的参数不能为空或空对象！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			getSqlSession().insert(sqlid, obj);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return obj;
	}
	
	/** 
	* 【修改】方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @throws 	DaoException  	DAO异常对象
	*/
	public void mod(String perName,String sqlid) throws DaoException {
		if(null == sqlid || sqlid.equals("")) throw new DaoException(this.getClass().getName() + ".mod(" + sqlid + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			getSqlSession().update(sqlid);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/** 
	* 【修改】方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				实体VO对象或List对象	（必须）
	* @throws 	DaoException  	DAO异常对象
	*/
	public void mod(String perName,String sqlid, T obj) throws DaoException {
		if(null == sqlid || sqlid.equals("") || null == obj) throw new DaoException(this.getClass().getName() + ".mod(" + sqlid + ", " + obj + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			getSqlSession().update(sqlid, obj);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/** 
	* 【删除】方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @throws 	DaoException  	DAO异常对象
	*/
	@SuppressWarnings("unchecked")
	public void del(String perName,PK sqlid) throws DaoException {
		if(null == sqlid || sqlid.equals("")) throw new DaoException(this.getClass().getName() + ".del(" + sqlid + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = (PK) (perName + Global.SPLIT_DIAN + sqlid);
			}
			getSqlSession().delete((String) sqlid);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/** 
	* 【删除】方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				实体VO对象或List对象	（必须）
	* @throws 	DaoException  	DAO异常对象
	*/
	public void del(String perName,String sqlid, Object obj) throws DaoException {
		if(null == sqlid || sqlid.equals("") || null == obj) throw new DaoException(this.getClass().getName() + ".del(" + sqlid + ", " + obj + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			getSqlSession().delete(sqlid, obj);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/** 
	* 【查询】VO对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @return	T				对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public T findVoBySqlid(String perName,String sqlid) throws DaoException {
		T result = null;
		if(null == sqlid || sqlid.equals("")) throw new DaoException(this.getClass().getName() + ".findVoBySqlid(" + sqlid + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().<T>selectOne(sqlid);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【查询】VO对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				实体VO对象或List对象	（必须）
	* @return	T				对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public T findVoBySqlid(String perName,String sqlid, Object obj) throws DaoException {
		T result = null;
		if(null == sqlid || sqlid.equals("") || null == obj) throw new DaoException(this.getClass().getName() + ".findVoBySqlid(" + sqlid + ", " + obj + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().<T>selectOne(sqlid, obj);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【查询】List对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @return	List<E>			E对象的List集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public List<T> findList(String perName,String sqlid) throws DaoException {
		List<T> result = null;
		if(null == sqlid || sqlid.equals("")) throw new DaoException(this.getClass().getName() + ".findList(" + sqlid + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().selectList(sqlid);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【查询】List对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				实体VO对象或List对象	（必须）
	* @return	List<E>			E对象的List集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public List<T> findList(String perName,String sqlid, Object obj) throws DaoException {
		List<T> result = null;
		if(null == sqlid || sqlid.equals("") || null == obj) throw new DaoException(this.getClass().getName() + ".findList(" + sqlid + ", " + obj + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().selectList(sqlid, obj);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【查询】List对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				实体VO对象或List对象	（必须）
	* @param 	rowBounds		RowBounds对象		（必须）
	* @return	List<E>			E对象的List集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public List<T> findList(String perName,String sqlid, T obj, RowBounds rowBounds) throws DaoException {
		List<T> result = null;
		if(null == sqlid || sqlid.equals("") || null == obj || null == rowBounds) throw new DaoException(this.getClass().getName() + ".findList(" + sqlid + ", " + obj + ", " + rowBounds + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().selectList(sqlid, obj, rowBounds);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【查询】Map对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	param			参数				（必须）
	* @return	Map<K, V>		Map集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public <K, V> Map<K, V> findMap(String perName,String sqlid, String param) throws DaoException {
		Map<K, V> result = null;
		if(null == sqlid || sqlid.equals("") || null == param || param.equals("")) throw new DaoException(this.getClass().getName() + ".findMap(" + sqlid + ", " + param + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().<K, V>selectMap(sqlid, param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【查询】Map对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				对象参数			（必须）
	* @param 	param			参数				（必须）
	* @return	Map<K, V>		Map集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public <K, V> Map<K, V> findMap(String perName,String sqlid, Object obj, String param) throws DaoException {
		Map<K, V> result = null;
		if(null == sqlid || sqlid.equals("") || null == obj || null == param || param.equals("")) throw new DaoException(this.getClass().getName() + ".findMap(" + sqlid + ", " + obj + "," + param + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().<K, V>selectMap(sqlid, obj, param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【查询】Map对象方法
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	obj				对象参数			（必须）
	* @param 	param			参数				（必须）
	* @param 	rowBounds		RowBounds对象		（必须）
	* @return	Map<K, V>		Map集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public <K, V> Map<K, V> findMap(String perName,String sqlid, Object obj, String param, RowBounds rowBounds) throws DaoException {
		Map<K, V> result = null;
		if(null == sqlid || sqlid.equals("") || null == obj || null == param || param.equals("")) throw new DaoException(this.getClass().getName() + ".findMap(" + sqlid + ", " + obj + "," + param + "," + rowBounds + ")传入的参数不能为空！");
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().<K, V>selectMap(sqlid, obj, param, rowBounds);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【分页查询】
	* @param 	page			分页对象
	* @return	Pager			分页对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public Pager<T,PK> query(String perName,Pager<T,PK> page) throws DaoException {
		if(null == page) throw new DaoException(this.getClass().getName() + ".query(" + page + ")传入的参数不能为空！");
		try {
			String sqlidOne = Global.MYBTS_QUERY_COUNT;
			String sqlidTwo = Global.MYBTS_QUERY_LIST;
			if (null != perName && !perName.equals("")) {
				sqlidOne = perName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != perName && !perName.equals("")) {
				sqlidTwo = perName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getSqlSession().selectOne(sqlidOne, page);
			page.setRowCount(rowCount);
			List<T> list = getSqlSession().selectList(sqlidTwo, page);
			page.setList(list);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return page;
	}
	
	/** 
	* 【分页查询】总记录数
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	page			分页对象			（必须）
	* @return	int				总记录数
	* @throws 	DaoException  	DAO异常对象
	*/
	protected int queryCount(String perName,String sqlid, Pager<T,PK> page) throws DaoException {
		if(null == sqlid || sqlid.equals("") || null == page) throw new DaoException(this.getClass().getName() + ".queryCount(" + sqlid + " , " + page + ")传入的参数不能为空！");
		int result = 0;
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().selectOne(sqlid, page);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【分页查询】VO集合对象
	* @param 	sqlid			SQL配置ID号		（必须）
	* @param 	page			分页对象			（必须）
	* @return	List<VO>		VO集合对象
	* @throws 	DaoException  	DAO异常对象
	*/
	protected List<T> queryList(String perName,String sqlid, Pager<T,PK> page) throws DaoException {
		if(null == sqlid || sqlid.equals("") || null == page) throw new DaoException(this.getClass().getName() + ".queryList(" + sqlid + " , " + page + ")传入的参数不能为空！");
		List<T> result = null;
		try {
			if (null != perName && !perName.equals("")) {
				sqlid = perName + Global.SPLIT_DIAN + sqlid;
			}
			result = getSqlSession().selectList(sqlid, page);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return result;
	}
	
	/** 
	* 【分页查询】
	* @param 	page			分页对象
	* @return	Pager			分页对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public Pager<T,PK> queryAduit(String perName,Pager<T,PK> page) throws DaoException {
		if(null == page) throw new DaoException(this.getClass().getName() + ".queryAduit(" + page + ")传入的参数不能为空！");
		try {
			String sqlidOne = Global.MYBTS_QUERY_AUDIT_COUNT;
			String sqlidTwo = Global.MYBTS_QUERY_AUDIT_LIST;
			if (null != perName && !perName.equals("")) {
				sqlidOne = perName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != perName && !perName.equals("")) {
				sqlidTwo = perName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getSqlSession().selectOne(sqlidOne, page);
			page.setRowCount(rowCount);
			List<T> list = getSqlSession().selectList(sqlidTwo, page);
			page.setList(list);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return page;
	}
}
