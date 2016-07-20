package com.mall.core.common;

/**
 * <p>Title:Global</p>
 * <p>Description:静态常量类</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 罗丁丁 - Stank.Luo
 * @version v1.0 2014-9-28
 */

public final class Global {
	
	/**第三方参数 */
	/**
	 * 融云  App Key
	 */
	public static final String  RONG_CLOUD_APP_KEY="RONG_CLOUD_APP_KEY";
	/**
	 * 融云App Secret
	 */
	public static final String  RONG_CLOUD_APP_SECRET="RONG_CLOUD_APP_SECRET";
	/**
	 * 阿里云 oss  Access Key
	 */
	public static final String  ALIYUN_OSS_ACCESS_KEY="ALIYUN_OSS_ACCESS_KEY"; 
	/**
	 * 阿里云 oss  Access Key Secret
	 */
	public static final String  ALIYUN_OSS_ACCESS_KEY_SECRET="ALIYUN_OSS_ACCESS_KEY_SECRET"; 
	
	/**
	 * 阿里云 oss  桶名前缀
	 */
	public static final String  ALIYUN_OSS_BUCKET="ALIYUN_OSS_BUCKET_"; 
	/**
	 * 规则分割符号
	 */
	public static final String notesSeparator="0xd733480";
	
	/**初始化web应用根路径。如：http://serverIP:port/WebRoot/*/
	public static String appPath = null;
	/**WEB根目录路径*/
	public static String WEB_ROOT_PATH = null;
	/**分隔符号：$~$*/
	public static final String SPLIT = "$~$";
	/**分隔符号：,*/
	public static final String SPLIT_COMMA = ",";
	/**分隔符号：.*/
	public static final String SPLIT_DIAN= ".";
	/**CORE框架配置文件.在web.xml中定义的*/
	public static final String CORE_CONFIG = "coreConfig";
	/**Spring配置文件（core/conf/core-common.xml），commonDao:commonDao*/
	public static final String SPRING_PUBLIC_COMMON_DAO = "commonDao";
	/**模板语言工具类名*/
	public static final String SPRING_PUBLIC_VELOCITY_TOOL = "velocityTool";
	
	//********************业务规则配置，相关业务规则路径及规则文件中的元素名********************
	/**规则配置路径,在config/core-config.xml中配置*/
	public static String RULE_PATH = null;
	/**规则配置文件中的ROOT*/
	public static final String RULE_ROOT = "root";
	/**规则配置文件中的ROOT的子结点*/
	public static final String RULE_CHILD = "rule";
	/**规则配置文件中的ROOT的子结点属性name*/
	public static final String RULE_NAME = "name";
	/**规则配置文件中的ROOT的子结点属性format*/
	public static final String RULE_FORMAT = "format";
	/**IBATIES中对应规则的SQLID号*/
	public static final String IBTS_RULE_SQL = "rulens.sql";
	
	
	//*****************************缓存配置及相关配置的元素名***************************
	/**规则缓存路径,在config/core-config.xml中配置*/
	public static String CACHE_PATH = null;
	/**缓存配置文件中的ROOT*/
	public static final String CACHE_ROOT = "root";
	/**缓存配置文件中的ROOT的子结点*/
	public static final String CACHE_CHILD = "cache";
	/**缓存配置文件中的ROOT的子结点属性sql*/
	public static final String CACHE_SQL = "sql";
	/**IBATIES中对应缓存的SQLID号*/
	public static final String IBTS_CACHE_SQL = "cachens.sql";
	
	
	
	
	//*************************日志TAG,区分不同的日志类型*****************************
	/**ACTION异常日志标识*/
	public static final String EXPCEPTION_ACTION = "【ACTION异常信息:】\n";
	/**业务服务层异常日志标识*/
	public static final String EXPCEPTION_SERVICE = "【SERVER异常信息:】\n";
	/**数据访问层异常日志标识*/
	public static final String EXPCEPTION_DAO = "【DAO异常信息:】\n";
	/**WEB初始时异常日志标识*/
	public static final String EXPCEPTION_WEBINIT = "【WEBINIT异常信息:】\n";
	
	/**【ACTION层】日志标识*/
	public static final String LOG_ACTION = "【ACTION层】>>>";
	/**【SERVICE层】日志标识*/
	public static final String LOG_SERVICE = "【SERVICE层】>>>";
	/**【DAO层】日志标识*/
	public static final String LOG_DAO = "【DAO层】>>>";
	
	
	//******************************Ibatis命名规则********************************
	/**增加命名*/
	public static String MYBTS_ADD = "add";
	/**增加单个VO对象命名*/
	public static String MYBTS_ADD_VO = "addVo";
	/**增加多个VO集合的命名*/
	public static String MYBTS_ADD_LIST = "addList";
	/**删除命名*/
	public static String MYBTS_DEL = "del";
	/**删除单个ID命名*/
	public static String MYBTS_DEL_ID = "delId";
	/**删除多个ID串命名*/
	public static String MYBTS_DEL_IDS = "delIds";
	/**删除外个ID集合的名称 */
	public static String MYBTS_DEL_LIST = "delList";
	/**修改命名*/
	public static final String MYBTS_MOD = "mod";
	/**修改单个VO对象命名*/
	public static final String MYBTS_MOD_VO = "modVo";
	/**修改多个VO集合对象命名*/
	public static final String MYBTS_MOD_List = "modList";
	/**修改单个VO对象不为空的字段的命名*/
	public static final String MYBTS_MOD_VO_NOT_NULL = "modVoNotNull";
	/**修改多个VO集合对象不为空的字段的命名*/
	public static final String MYBTS_MOD_List_NOT_NULL = "modListNotNull";
	/**查询命名*/
	public static final String MYBTS_FIND = "find";
	/**查询单个VO对象的命名*/
	public static final String MYBTS_FIND_VO = "findVo";
	/**查询多个VO集合对象的命名*/
	public static final String MYBTS_FIND_List = "findList";
	/**分页查询命名*/
	public static final String MYBTS_QUERY = "query";
	/**分页查询总记录数的命名*/
	public static final String MYBTS_QUERY_COUNT = "queryCount";
	/**分页查询多个VO集合对象的命名*/
	public static final String MYBTS_QUERY_LIST = "queryList";
	/**查询操作记录同一个源ID*/
	public static final String MYBTS_FIND_TAKE_List = "findOperateTakeList";
	/**分页查询审核总记录数的命名*/
	public static final String MYBTS_QUERY_AUDIT_COUNT = "queryAuditCount";
	/**分页查询多个审核VO集合对象的命名*/
	public static final String MYBTS_QUERY_AUDIT_LIST = "queryAuditList";
	
}