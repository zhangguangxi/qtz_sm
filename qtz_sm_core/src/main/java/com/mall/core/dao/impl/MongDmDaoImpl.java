package com.mall.core.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.mall.core.common.Arith;
import com.mall.core.common.BeanUtils;
import com.mall.core.common.jsonUtil.JSONUtils;
import com.mall.core.dao.AbstractDao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.mall.core.vo.VO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

@SuppressWarnings("rawtypes")
public abstract class MongDmDaoImpl<T extends VO<PK>,PK extends Serializable> extends AbstractDao implements BizDao<T, PK> {
	private static Map<String, DBCollection> dbCollMap = new HashMap<String, DBCollection>();
	
	private static DBCollection getDBColl(String collectionName) {
		if(dbCollMap.containsKey(collectionName)) {
			return dbCollMap.get(collectionName);
		} else {
			DBCollection collection = getMongoTemplate().getCollection(collectionName);
			dbCollMap.put(collectionName, collection);
			return collection;
		}
	}	
	@Override
	public T addVo(T vo) throws DaoException {
		getMongoTemplate().insert(vo, getPreName());
		
		return vo;
	}

	@Override
	public void addList(List<T> list) throws DaoException {
		for (T t : list) {
			getMongoTemplate().insert(t,getPreName());
		}
		
	}

	@Override
	public void delId(PK id) throws DaoException {
		Criteria criteria=Criteria.where("dmId").in(id);
		Query query=new Query();
		query.addCriteria(criteria);
		getMongoTemplate().remove(query, getPreName());
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delIds(PK... ids) throws DaoException {
		for (PK pk : ids) {
			delId(pk);
		}
	}

	@Override
	public void delList(Collection<PK> list) throws DaoException {
		for (PK pk : list) {
			delId(pk);
		}
		
	}
	
	@Override
	public void delVo(T vo) throws DaoException {
		try {
			Query query =BeanUtils.bean2Where(vo);
			getMongoTemplate().remove(query, getPreName());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		};
		
		
	}
	
	@Override
	public void modVo(T vo) throws DaoException {
		modVoNotNull(vo);
	}

	@Override
	public void modVoNotNull(T vo) throws DaoException {
		if (vo.getDmId()==null) {
			return;
		}
		Criteria criteria=Criteria.where("dmId").is(vo.getDmId());
		Query query=new Query();
		query.addCriteria(criteria);
		Update update=new Update();
		try {
			
			BeanUtils.bean2Update(vo, update);
			if (update.getUpdateObject().toMap().size()<=0) {
				return;
			}
			WriteResult updateMulti = getMongoTemplate().updateFirst(query, update, getPreName());//getPreName()
			updateMulti.getN();
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}catch (Exception e) {
			throw new DaoException(e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findVo(PK id,T clazz) throws DaoException {
		Query query =new Query();
		try {
			//query = BeanUtils.bean2Where(clazz);
			query.addCriteria(Criteria.where("dmId").is(id));
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} 
		 VO findOne =  getMongoTemplate().findOne(query,clazz.getClass(),getPreName());
		return findOne==null?null:(T)findOne;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findVo(PK id,T clazz,DBObject fileds) throws DaoException {
		Query query =new  BasicQuery(new BasicDBObject(),fileds);
		try {
			//query = BeanUtils.bean2Where(clazz);
			query.addCriteria(Criteria.where("dmId").is(id));
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} 
		 VO findOne =  getMongoTemplate().findOne(query,clazz.getClass(),getPreName());
		return findOne==null?null:(T)findOne;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(T vo) throws DaoException {
		
		Query query =null;
		try {
			query = BeanUtils.bean2Where(vo);
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}
		return (List<T>) getMongoTemplate().find(query, vo.getClass(),getPreName());
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(Class<T> clazz,PK... ids) throws DaoException{
		Query query=new Query();
		query.addCriteria(Criteria.where("dmId").in((Object[])ids));
		return getMongoTemplate().find(query, clazz, getPreName());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findList(T vo,DBObject fileds) throws DaoException {
		Query query =null;
		try {
			query = BeanUtils.bean2Where(vo);
			query=new BasicQuery(query.getQueryObject().toString(),fileds.toString());
		} catch (IllegalArgumentException e) {
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			throw new DaoException(e);
		}
		return (List<T>) getMongoTemplate().find(query, vo.getClass(),getPreName());
		
	}

	@Override

	public Pager<T, PK> query(Pager<T, PK> page, Class<T> clazz) throws DaoException {
		Query query =null;
		try {
			query = BeanUtils.bean2Where(page);
			long count = getMongoTemplate().count(query, getPreName());
			query.skip(page.getPageOffset());
			query.limit(page.getPageSize());
			if(null != page.getOrderField() && !"".equals(page.getOrderField()))
			{
				if(page.getOrderCondition()){
					
					query.with(new Sort(Direction.ASC, page.getOrderField()));
				}
				else{
					query.with(new Sort(Direction.DESC, page.getOrderField()));
				}
				
			}
			
			List<T> list = (List<T>)getMongoTemplate().find(query, clazz, getPreName());
			page.setList(list);
			String valueOf = String.valueOf(count);
			page.setRowCount(Integer.valueOf(valueOf));
			return page;
		} catch (IllegalArgumentException e) {
			throw new DaoException();
		} catch (IllegalAccessException e) {
			throw new DaoException();
		}
	}
	
	@Override
	public Pager<T, PK> query(Pager<T, PK> page, Class<T> clazz,DBObject fileds) throws DaoException {
		Query query =null;
		try {
			
			query = BeanUtils.bean2Where(page);
			query=new BasicQuery(query.getQueryObject().toString(),fileds.toString());
			long count = getMongoTemplate().count(query, getPreName());
			query.skip(page.getPageOffset());
			query.limit(page.getPageSize());
			if(null != page.getOrderField() && !"".equals(page.getOrderField()))
			{
				if(page.getOrderCondition()){
					
					query.with(new Sort(Direction.ASC, page.getOrderField()));
				}
				else{
					query.with(new Sort(Direction.DESC, page.getOrderField()));
				}
				
			}
			
			List<T> list = (List<T>)getMongoTemplate().find(query, clazz, getPreName());
			page.setList(list);
			String valueOf = String.valueOf(count);
			page.setRowCount(Integer.valueOf(valueOf));
			return page;
		} catch (IllegalArgumentException e) {
			throw new DaoException();
		} catch (IllegalAccessException e) {
			throw new DaoException();
		}
	}
	/**
	 * 
	* 【分页查询】(这里用一句话描述这个方法的作用)
	* @param page
	* @param query
	* @param clazz
	* @param fileds  查询的字段
	* @return
	* @throws DaoException
	 */
	public Pager<T, PK> query(Pager<T, PK> page,Query query, Class<T> clazz,DBObject fileds) throws DaoException {
		try {
			long count = getMongoTemplate().count(query, getPreName());
			if(null != fileds){
				query=new BasicQuery(query.getQueryObject().toString(),fileds.toString());
			}
			query.skip(page.getPageOffset());
			query.limit(page.getPageSize());
			if(null != page.getOrderField() && !"".equals(page.getOrderField()))
			{
				if(page.getOrderCondition()){
					
					query.with(new Sort(Direction.ASC, page.getOrderField()));
				}
				else{
					query.with(new Sort(Direction.DESC, page.getOrderField()));
				}
				
			}
			List<T> list = (List<T>)getMongoTemplate().find(query, clazz, getPreName());
			page.setList(list);
			String valueOf = String.valueOf(count);
			page.setRowCount(Integer.valueOf(valueOf));
			return page;
		} catch (IllegalArgumentException e) {
			throw new DaoException();
		} 
	}
	
	
	
	/**
	 * 添加
	 * @param collectionName
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  int insert(DBObject val) throws DaoException{
		DBCollection dbColl = getDBColl(getPreName());
		WriteResult result = dbColl.insert(val);
		System.out.println(result.getN());
		return (result.getN() >=0)? 0 : 1;
	}
	
	/**
	 * 修改或者添加
	 * @param collectionName
	 * @param ref
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  int updateOrinsert(DBObject ref ,DBObject val )throws DaoException{
		if( null == ref || null == val ){
			return 1;
		}
		DBCollection dbColl = getDBColl(getPreName());
		WriteResult result =  dbColl.update(ref, val, true, false);
		return (result.getN() >= 0 ) ? 0 : 1;
	}
	
	/**
	 * 修改
	 * @param collectionName
	 * @param ref
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  int update(DBObject ref ,DBObject val )throws DaoException{
		if( null == ref || null == val ){
			return 1;
		}
		DBCollection dbColl = getDBColl(getPreName());
		WriteResult result =  dbColl.update(ref, val, false, false);
		return (result.getN() >= 0 ) ? 0 : 1;
	}
	
	/**
	 * 批量修改
	 * @param collectionName
	 * @param ref
	 * @param val
	 * @return
	 * @throws Exception
	 */
	public  int updateAll(DBObject ref ,DBObject val )throws DaoException{
		if( null == ref || null == val ){
			return 1;
		}
		DBCollection dbColl = getDBColl(getPreName());
		WriteResult result =  dbColl.update(ref, val, false, true);
		return (result.getN() >= 0 ) ? 0 : 1;
	}
	
	/**
	 * 查找单个所有值
	 * @param collectionName
	 * @param ref
	 * @return
	 * @throws Exception
	 */
	public  DBObject findOne( DBObject ref) throws Exception {
		DBCollection dbColl = getDBColl(getPreName());
		DBObject one = dbColl.findOne(ref);
		if( null != one ) {
			one.removeField("_id");
		}
		return one;
	}
	/**
	 * 
	  * 查找单个值 排序
	  * @param ref
	  * @param sort
	  * @param fileds
	  * @return
	  * @throws Exception
	 */
	public  DBObject findOne( DBObject ref,DBObject sort,DBObject fields) throws DaoException {
		DBCollection dbColl = getDBColl(getPreName());
		DBObject one = dbColl.findOne(ref,fields,sort);
		if( null != one ) {
			one.removeField("_id");
		}
		return one;
	}
	
	
	/**
	 * 查找指定的值
	 * @param collectionName
	 * @param ref
	 * @param fields
	 * @return
	 * @throws Exception
	 */
	public  DBObject findOne( DBObject ref,  DBObject fields) throws DaoException {
		DBCollection dbColl = getDBColl(getPreName());
		DBObject one = dbColl.findOne(ref, fields);
		if( null != one ) {
			one.removeField("_id");
		}
		return one;
	}
	
	/**
	 * 查询列表 分页 排序
	 * @param collectionName
	 * @param ref
	 * @param sort
	 * @param pageIndex
	 * @param pageSize
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public  <T> List<T> findList( DBObject ref, DBObject sort, int pageIndex, int pageSize, Class<T> clz) throws Exception {
		if( null == ref ) {
			throw new Exception("Parameter is null!");
		}
		DBCollection dbColl = getDBColl(getPreName());
		List<T> list = new ArrayList<T>();
		DBCursor cursor = dbColl.find(ref).sort(sort).skip((pageIndex - 1) * pageSize).limit(pageSize);
		while(cursor.hasNext()){
			DBObject one = cursor.next();
			if( null != one ) {
				one.removeField("_id");
			}
			list.add(JSON.parseObject(one.toString(), clz));
		}
		return list;
	}
	
	public T findVoMax(T vo,String filed,int sort) throws DaoException{
		
		Query query =null;
		try {
			
			query = BeanUtils.bean2Where(vo);
			
			query.skip(0);
			query.limit(1);
			if(sort == 1){
				query.with(new Sort(Direction.ASC, filed));
			}
			else
			{
				query.with(new Sort(Direction.DESC,filed));
			}
			
			@SuppressWarnings("unchecked")
			List<T> list = (List<T>)getMongoTemplate().find(query, vo.getClass(), getPreName());
			if(null == list || list.isEmpty()) return null;
			return list.get(0);
		} catch (IllegalArgumentException e) {
			throw new DaoException();
		} catch (IllegalAccessException e) {
			throw new DaoException();
		}
	}

	/**
	 * 查询列表 分页 无排序
	 * @param collectionName
	 * @param ref
	 * @param pageIndex
	 * @param pageSize
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public  <T> List<T> findList( DBObject ref, int pageIndex, int pageSize, Class<T> clz) throws DaoException {
		if( null == ref ) {
			throw new DaoException("Parameter is null!");
		}
		DBCollection dbColl = getDBColl(getPreName());
		List<T> list = new ArrayList<T>();
		DBCursor cursor = dbColl.find(ref).skip((pageIndex - 1) * pageSize).limit(pageSize);
		while(cursor.hasNext()){
			DBObject one = cursor.next();
			if( null != one ) {
				one.removeField("_id");
			}
			list.add(JSON.parseObject(one.toString(), clz));
		}
		return list;
	}
	
	/**
	 * 查询指定属性集合
	 * @param collectionName
	 * @param ref
	 * @param field
	 * @param pageIndex
	 * @param pageSize
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("hiding")
	public  <T> List<T> findFieldList(DBObject ref, DBObject field, int pageIndex, int pageSize, Class<T> clz) throws DaoException {
		if( null == ref ) {
			throw new DaoException("Parameter is null!");
		}
		DBCollection dbColl = getDBColl(getPreName());
		List<T> list = new ArrayList<T>();
		DBCursor cursor = dbColl.find(ref, field).skip((pageIndex - 1) * pageSize).limit(pageSize);
		while(cursor.hasNext()){
			DBObject one = cursor.next();
			if( null != one ) {
				one.removeField("_id");
			}
			list.add(JSON.parseObject(one.toString(), clz));
		}
		return list;
	}
	
	
	public  List<Map> findFieldListMap(DBObject ref, DBObject field, int pageIndex, int pageSize, Class<T> clz) throws DaoException {
		if( null == ref ) {
			throw new DaoException("Parameter is null!");
		}
		DBCollection dbColl = getDBColl(getPreName());
		List<Map> list = new ArrayList<Map>();
		DBCursor cursor = dbColl.find(ref, field).skip((pageIndex - 1) * pageSize).limit(pageSize);
		while(cursor.hasNext()){
			DBObject one = cursor.next();
			if( null != one ) {
				one.removeField("_id");
			}
			list.add(one.toMap());
		}
		return list;
	}
	
	/**
	 * 查询列表数量
	 * @param collectionName
	 * @param ref
	 * @return
	 * @throws Exception
	 */
	public  long findListCount( DBObject ref) throws DaoException{
		if( null == ref ) {
			throw new DaoException("Parameter is null!");
		}
		DBCollection dbColl = getDBColl(getPreName());
		return dbColl.count(ref);
		
	}
	
	/**
	 * 查询附近的人
	 * @param collectionName
	 * @param ref
	 * @param field
	 * @param sort
	 * @param lon
	 * @param lat
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public  List<Map> findList( DBObject ref, DBObject field, DBObject sort, double lon, double lat, int pageIndex, int pageSize) throws DaoException {
		if(null == ref){
			throw new DaoException("Parameter is null !");
		}
		DBCollection dbColl = getDBColl(getPreName());
		List<Map> list = new ArrayList<Map>();
		DBCursor cursor = dbColl.find(ref,field).skip((pageIndex - 1) * pageSize).limit(pageSize).sort(sort);
		while(cursor != null && cursor.hasNext()) {
			@SuppressWarnings("unchecked")
			Map<String,Object> map = cursor.next().toMap();//每一条纪录是一个map
			Map locMap = (Map)map.get("loc");
			//position 位置下面的坐标
			double tempLat = Double.parseDouble(locMap.get("lat").toString());//纬度
			double tempLon = Double.parseDouble(locMap.get("lon").toString());//经度
		//	这里lat  lon  是定位坐标地点  distance 两点相差距离
			double distance = Arith.getPointDistance(lat, lon, tempLat, tempLon);
			map.put("dis", distance);
			map.remove("_id"); // 移除ID
			//map.remove(UserKey.position);  // 移除地址信息
			list.add(map);
		}
//		//进行降序排序
		Collections.sort(list, new Comparator<Map>(){
			public int compare(Map o1, Map o2) {
				if((Double)o1.get("dis") - (Double)o2.get("dis") > 0) {
					return 1;
				}else if((Double)o1.get("dis") - (Double)o2.get("dis") == 0) {
					return 0;
				}else {
					return -1;
				}
			}
		});
		return list;
	}

	
	/**
	 * 查询附近的人
	 * @param collectionName
	 * @param ref
	 * @param field
	 * @param sort
	 * @param lon
	 * @param lat
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public  List<Map> findList2( DBObject ref, DBObject field, DBObject sort, double lon, double lat, int startRecord, int pageSize) throws DaoException {
		if(null == ref){
			throw new DaoException("Parameter is null !");
		}
		DBCollection dbColl = getDBColl(getPreName());
		List<Map> list = new ArrayList<Map>();
		DBCursor cursor = dbColl.find(ref,field).skip(startRecord).limit(pageSize).sort(sort);
		while(cursor != null && cursor.hasNext()) {
			@SuppressWarnings("unchecked")
			Map<String,Object> map = cursor.next().toMap();//每一条纪录是一个map
			Map locMap = (Map)map.get("loc");
			//position 位置下面的坐标
			double tempLat = Double.parseDouble(locMap.get("lat").toString());//纬度
			double tempLon = Double.parseDouble(locMap.get("lon").toString());//经度
		//	这里lat  lon  是定位坐标地点  distance 两点相差距离
			double distance = Arith.getPointDistance(lat, lon, tempLat, tempLon);
			map.put("dis", distance);
			map.remove("_id"); // 移除ID
			//map.remove(UserKey.position);  // 移除地址信息
			list.add(map);
		}
//		//进行降序排序
//		Collections.sort(list, new Comparator<Map>(){
//			public int compare(Map o1, Map o2) {
//				if((Double)o1.get("dis") - (Double)o2.get("dis") > 0) {
//					return 1;
//				}else if((Double)o1.get("dis") - (Double)o2.get("dis") == 0) {
//					return 0;
//				}else {
//					return -1;
//				}
//			}
//		});
		return list;
	}
	/**
	 * 检验某个数据在数据库中是否存在
	 * @param collectionName
	 * @param ref
	 * @return
	 * @throws Exception 
	 */
	public  boolean isExist(DBObject ref) throws Exception {
		if(null == ref){
			throw new Exception("Parameter is null !");
		}
		DBCollection dbColl = getDBColl(getPreName());
		return dbColl.count(ref) > 0 ? true : false;
	}

	@Override
	public Long findCount(T vo) throws DaoException {
		Query query =new Query();
		try {
			query = BeanUtils.bean2Where(vo);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new DaoException(e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
		return getMongoTemplate().count(query, getPreName());
	}
	
	@Override
	public int modReplace(T vo)throws DaoException{
		DBObject ref =new BasicDBObject();
		ref.put("dmId", vo.getDmId());
		DBObject val = new BasicDBObject();
		val.putAll((DBObject)com.mongodb.util.JSON.parse(JSONUtils.Object2JSON(vo)));
		return update(ref, val);
		
	}
	@Override
	public void delFileds(String filedsName ,PK id) throws DaoException{
		if(StringUtils.isEmpty(filedsName) || id==null){
			throw new DaoException("null excepiton");
		}
		Query query =new Query();
		query.addCriteria(Criteria.where("dmId").is(id));
		Update update=new Update();
		update.unset(filedsName);
		getMongoTemplate().updateFirst(query, update, getPreName());
	}

}
