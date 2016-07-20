package com.mall.core.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

/**
 * <p>Title:Constants</p>
 * <p>Description:(系统常量工具类)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-10-3
 */
public class Constants{
	
//	public static final int USER_TYPE_MEMBER=0;//会员用户
	
//	public static final int USER_TYPE_EMPLOY=1;//员工用户
	
	public static final String system_user="100000";//系统用户
	
	public static final String code_99899="998899";//系统用户
	
	
	public static final Long system_user_long=100000l;//系统用户
	/** session中的用户对象 */
	public static final String SESSION_USER = "session_user";
	/** session中的用户对象 所在的公司 (徐运贤 2016-04-25)*/
	public static final String SESSION_COMPANY = "session_company";
	/**
	 * 融云key
	 */
	public static final String RONG_CLOUD_APP_KEY="RONG_CLOUD_APP_KEY";
	/**
	 * 融云 secret
	 */
	public static final String RONG_CLOUD_APP_SECRET="RONG_CLOUD_APP_SECRET";
	/**
	 * 阿里云 key
	 */
	public static final String ALIYUN_OSS_ACCESS_KEY="ALIYUN_OSS_ACCESS_KEY";
	/**
	 * 阿里云 key secret
	 */
	public static final String ALIYUN_OSS_ACCESS_KEY_SECRET="ALIYUN_OSS_ACCESS_KEY_SECRET";
	/**
	 * 桶1  个人数据
	 */
	public static final String ALIYUN_OSS_BUCKET_ONE="ALIYUN_OSS_BUCKET_ONE";
	/**
	 * 桶1绑定域名
	 */
	public static final String qtzimg01="qtzimg01";
	/**
	 * 订单id前缀
	 */
	public static final String orderId_prefix="orderId.prefix";
	
	/**
	 * 桶2
	 */
	public static final String ALIYUN_OSS_BUCKET_TWO="ALIYUN_OSS_BUCKET_TWO";
	/**
	 * 桶2绑定域名
	 */
	public static final String qtzimg02="qtzimg02";
	/**
	 * 桶3
	 */
	public static final String ALIYUN_OSS_BUCKET_THREE="ALIYUN_OSS_BUCKET_THREE";
	/**
	 * 桶3绑定域名
	 */
	public static final String qtzimg03="qtzimg03";
	
	/**
	 * 极光ppsh AppKey
	 */
	public static final String ppSHAppKey = "8758567730bb4583b2fc3589";
	/**
	 * 极光ppsh MasterSecret
	 */
	public static final String ppSHMasterSecret = "317c4930139b9ce8b8b7a528";
	/**
	 * 极光ppsj AppKey
	 */
	public static final String ppSJAppKey = "766267141dc836c41064ec70";
	/**
	 * 极光ppsj MasterSecret
	 */
	public static final String ppSJMasterSecret = "091e7e9e8b364b8339aeddf2";
	
	private static String filePro = "config/base.properties";  //属性文件路径
	private static Map<String,String> filePro_map= UtilsOperaProperies.readProperties(filePro);  //缓存属性文件夹
    public static final int SYSTEM_INITILZ_SUC = 100; 
		/*********************************************************************Start*
		 * 系统常用字面量参数区
		 *********************/
		//实体数据有效码：Y-有效
		public static final String ACTI_Y = "Y";
		//实体数据有效码：N-无效
	    public static final String ACTI_N = "N";
	    /** xingbie nan  */
		public static final String SEX_MAN = "1";
		/** xingbie nv  */
	    public static final String SEX_WOMAN = "0";
	    /** redis中的session分组 */
		public static final String SESSION = "session";
		/** 好实再平台单点登录会话ID */
		public static final String HSZ_JSESSIONID = "hsz_jsessionid";
		/** 好实再平台供应商会话ID */
		public static final String HSZ_SUPP_JSESSIONID = "hsz_supp_jsessionid";
		/** 好实再平台管理员会话ID */
		public static final String HSZ_ADMIN_JSESSIONID = "hsz_admin_jsessionid";
	
		/** request中的session对象 */
		public static final String REQUEST_SESSION = "request_session";
		/** session中的用户权限集合 */
		public static final String SESSION_USPLIST = "session_usplist";
		/** session中的验证码 */
		public static final String SESSION_CERT_CODE = "session_cert_code";
		/** 系统代码 员工岗位类型 */
		public static final String SYS_CODE_YUANGONGGANGWEI = "1000100000";
		/** 操作成功 */
		public static final String SUCCESS_TRUE = "true";
		/** 操作错误 */
		public static final String SUCCESS_FALSE = "false";
		/** 操作成功 */
		public static final String FLAG_MSG_SUCCESS = "操作成功";
		/** 操作失败 */
		public static final String FLAG_MSG_ERROR = "操作失败";
		/** 当前session已经绑定登录用户 */
		public static final String FLAG_MSG_ERROR_ERRORCODE_6 = "当前session已经绑定登录用户";
		/** 入参错误 */
		public static final String FLAG_MSG_ERROR_ERRORCODE_7 = "入参错误";
		/** 操作标示 */
		public static final String SUCCESS = "Success";
		/** 操作提示 */
		public static final String MESSAGE = "Message";
		/** 返回数据 */
		public static final String DATAS = "Datas";
		/** 返回数据 数量 */
		public static final String TOTALCOUNT = "TotalCount";
		/** 返回sessionKey*/
		public static final String SKEY = "Skey";
		
		
		/**<p>错误码</p>*/
		public static final String ERRORCODE = "ErrorCode";
		
	    //字符串字面量：0
	    public static final int ZERO = 0;
	    //字符串字面量：1
		public static final int ONE = 1;
	    //字符串字面量：2
	    public static final int TWO = 2;
	    //字符串字面量：3
	    public static final int THREE = 3;
	    //字符串字面量：4
	    public static final int FOUR = 4;
	    //字符串字面量：5
	    public static final int FIVE = 5;
	    //字符串字面量：6
	    public static final int SIX = 6;
	    //字符串字面量：7
	    public static final int SEVEN = 7;
	    //字符串字面量：8
	    public static final int EIGHT = 8;
	    //字符串字面量：9
	    public static final int NINE = 9;
	    /** 系统常用字面量参数区 * End */
	    /*********************************************************************Start*
	    /**
	     * 省级区划编码格式
	     */
	    public static final String PATTERN_PROVINCE = "\\d\\d0000";
	    /**
	     * 地市级区划编码格式
	     */
	    public static final String PATTERN_CITY = "\\d\\d\\d[1-9]00|\\d\\d[1-9]\\d00";
	    /**
	     * 区县级区划编码格式
	     */
	    public static final String PATTERN_COUNTY = "\\d\\d\\d\\d\\d[1-9]|\\d\\d\\d\\d[1-9]\\d";
	    /**
	     * 每页数量
	     */
	    public static final int PAGE_NUM=20;
		/** 
		* 【获得系统属性文件值】(这里用一句话描述这个方法的作用)
		* @param key 
		* 		属性key
		* @return  
		* 		属性值
		*/
		public static String getValueByKeyFromfilePro(String key){
			return filePro_map.get(key);
		}
		/**
		 * 消费满@元,即可减@元,同时赠送@.每人限领取@张
		 */
		public static String rulesString1="消费满{1}元,即可减{2}元,同时赠送{3},每人限领取{4}张.";
		/**
		 * 消费满@元,同时赠送@.每人限领取@张
		 */
		public static String rulesString2="消费满{1}元,同时赠送{2},每人限领取{3}张.";
		/**
		 * 消费满@元,即可减@元.每人限领取@张
		 */
		public static String rulesString3="消费满{1}元,即可减{2}元,每人限领取{3}张.";
		/**
		 * 折扣券   消费满{1}元,享受{2}折优惠
		 */
		public static String disCountRulesString3="消费满{1}元,享受{2}折优惠,每人限领取{3}张.";
		/**
		 * 礼物券
		 */
		public static String giftRulesString3="消费满{1}元,赠送{2},每人限领取{3}张.";
		/**
		 * 霸王劵
		 */
		public static String archlordRulesString3="消费在{1}元内,免单,每人限领取{2}张.";
		/**
		 * 年
		 */
		public static final String YEAR="Y";
		/**
		 * 月
		 */
		
		public static final String MONTH="M";
		/**
		 * 天数
		 */
		public static final String DAY="D";
		
		/** 
		* 【写入系统属性文件值 并更新缓存】(这里用一句话描述这个方法的作用)
		* @param key
		* @param value
		* @throws Exception  
		*/
		public synchronized static void setValueFromfilePro(String key,String value) throws Exception{
			String path =Thread.currentThread().getContextClassLoader().getResource(filePro).getFile();//获取路径并转换成流
			Properties props = new Properties();//属性集合对象
			props.load(new FileInputStream(path));  
            OutputStream fos = new FileOutputStream(path);              
            props.setProperty(key, value);//往属性文件插值
            props.store(fos, "Update '" + key + "' value");  
            System.out.println(props.getProperty(key));
			filePro_map= UtilsOperaProperies.readProperties(filePro);  //缓存属性文件夹
		}
}
