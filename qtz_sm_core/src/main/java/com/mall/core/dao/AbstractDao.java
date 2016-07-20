package com.mall.core.dao;


import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mall.core.common.SpringContextHolder;
import com.mall.core.vo.VO;

public abstract class AbstractDao<T extends VO<PK>,PK extends Serializable> {
	
	/** 
	* 【取得MYBatis命名空间
	* @return  	MYBatis命名空间
	*/
	protected abstract String getPreName();
	private  MyBaitsTemplate<T,PK> myBaitsTemplate;
	private static MongoTemplate mongoTemplate;
	private static StringRedisTemplate stringRedisTemplate;
	private static RedisTemplate<Serializable, Serializable> redisTemplate;
	
	
	//用于设置redis连接数据库
	public static JedisConnectionFactory getConnectionFactory() {
		return SpringContextHolder.getApplicationContext().getBean(JedisConnectionFactory.class);
	}
	//目前还没有找到同层调用不同数据库的方法目前采用springJDBC提供
	private static JdbcTemplate jdbcTemplate;
	
	public static JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = SpringContextHolder.getApplicationContext().getBean(JdbcTemplate.class);
		}
		return jdbcTemplate;
	}
	//privatge static 
	public static  MongoTemplate getMongoTemplate() {
		if(mongoTemplate==null){
			mongoTemplate=SpringContextHolder.getApplicationContext().getBean(MongoTemplate.class);
		}
		return mongoTemplate;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  MyBaitsTemplate getMyBaitsTemplate() {
		if(null == myBaitsTemplate)  myBaitsTemplate = ((MyBaitsTemplate<T,PK>) SpringContextHolder.getBean("myBaitsTemplate"));
		return myBaitsTemplate;
	}
	public static StringRedisTemplate getStringRedisTemplate() {
		if (stringRedisTemplate == null) {
			stringRedisTemplate = SpringContextHolder.getApplicationContext().getBean(StringRedisTemplate.class);
		}
		return stringRedisTemplate;
	}
	
	public static RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		if(redisTemplate==null){
			redisTemplate=SpringContextHolder.getBean("redisTemplate");
	}
		return redisTemplate;
	}
	
}
