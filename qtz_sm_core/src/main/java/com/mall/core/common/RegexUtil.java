package com.mall.core.common;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <p>Title:RegexUtil</p>
 * <p>Description:(正则表达式工具类
 * 90%的验证都调用了Regular方法 但是本类也可删除大部分方法 涉及到正则的判断都直接穿参数和正则表达式 
 * 是为了方便业务类调用和有更直观的含义 建议不要这么做
 * Pattern的matcher已经被同步synchronized 所以 此类的任何使用正则验证的方法都不需要同步)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-28
 */

public class RegexUtil
{
	public static void main(String[] args) {  
//    	System.out.println(""+RegexUtil.isYearMonth("1201-01"));;
//    	System.out.println(RegexUtil.isDate("2013-01-01"));
//    	System.out.println(RegexUtil.isDate("20131212"));
//    	System.out.println(RegexUtil.isNumber1("2013.0606"));
//    	System.out.println(RegexUtil.isNumber1("-2013.0606"));
//    	System.out.println(RegexUtil.isNumber1("201366"));
//    	System.out.println(RegexUtil.isNumber1("-201366"));
 // /v[\\d]\\.[\\d]/auth/.*:/v1.1/auth/register
    	
		//System.out.println("/v1.1/user/perfectPersonInfo".matches("/v.*/user/perfectSellerInfo"));
    	//System.out.println(Pattern.matches(str_eng_num_length,"Tsdfh-85siahi132"));
    	//System.out.println(isTxtExplain("@@!@#!SDAF@##$@TGFDB VX"));
    	System.out.println(isMobile("17306278205"));
    	
        }
	
	//------------------常量定义  
    /** 
     * Email正则表达式=^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$ 
     */ 
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";  
      
    /** 
     * 电话号码正则表达式= (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)  
     */ 
    public static final String PHONE = "0\\d{2,3}-\\d{5,9}|0\\d{2,3}-\\d{5,9}";  //"(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?$)" ;  
    /** 
     * 手机号码正则表达式=(^(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7})$ 
     */ 
    public static final String MOBILE ="(^(13\\d|14[57]|15[^4,\\D]|17[03678]|18\\d)\\d{8}|170[059]\\d{7})$";  
 
    /** 
     * IP地址正则表达式 ((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)) 
     */ 
    public static final String IPADDRESS = "((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))";  
 
    /** 
     * Integer正则表达式 ^-?(([1-9]\d*$)|0) 
     */ 
    public static final String  INTEGER = "^-?(([1-9]\\d*$)|0)";  
    /** 
     * 正整数正则表达式 >=0 ^[1-9]\d*|0$ 
     */ 
    public static final String  INTEGER_NEGATIVE = "^[1-9]\\d*|0$";  
    /** 
     * 负整数正则表达式 <=0 ^-[1-9]\d*|0$ 
     */ 
    public static final String  INTEGER_POSITIVE = "^-[1-9]\\d*|0$";      
    /** 
     * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$ 
     */ 
    public static final String  DOUBLE ="^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";  
    /** 
     * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　 
     */ 
    public static final String  DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";  
    /** 
     * 负Double正则表达式 <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$ 
     */ 
    public static final String  DOUBLE_POSITIVE ="^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";   
    /** 
     * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁 
     */ 
    public static final String  AGE="^(?:[1-9][0-9]?|1[01][0-9]|120)$";  
    /** 
     * 邮编正则表达式  [1-9]\d{5}(?!\d) 国内6位邮编 
     */ 
    public static final String  CODE="[1-9]\\d{5}(?!\\d)";    
    /** 
     * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$ 
     */ 
    public static final String STR_ENG_NUM_="^\\w+$";  
    /** 
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$  
     */ 
    public static final String STR_ENG_NUM="^\\w+$";  
    /** 
     * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$  6-15位
     */ 
    public static final String str_eng_num_length="^[A-Za-z0-9]{6,16}+$";  
    /** 
     * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$ 
     */ 
    public static final String STR_ENG="^[A-Za-z]+$";  
    /** 
     * 匹配中文字符 ^[\Α-\￥]+$ 
     */ 
    public static final String STR_CHINA="^[\\Α-\\￥]+$";    
    /** 
     * 过滤特殊字符串正则 
     * regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
     */ 
    public static final String STR_SPECIAL="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
    /** 
     *只能输英文 数字 中文 ^[a-zA-Z0-9\一-\龥]+$ 
     */ 
    public static final String STR_ENG_CHA_NUM="^[a-zA-Z0-9\\一-\\龥]+$";  
    
    /**
     * 只能输入中文 英文空格 数子 常用符号 1-600 位的字符串
     */
    public static final String STR_ENG_CHA_NUM_SYMBOL="^[a-zA-Z0-9\\一-\\龥\\s\\W]{1,600}+$";
    /** 
     *   
     */ 
    /*** 
     * 日期正则 支持： 
     *  YYYY-MM-DD  
     *  YYYY/MM/DD  
     *  YYYY_MM_DD  
     *  YYYYMMDD 
     *  YYYY.MM.DD的形式 
     */ 
    
    public static final String DATE_ALL="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)" +  
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)" +  
            "|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +  
            "([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)" +  
            "|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +  
            "(0?2)([-\\/\\._]?)(29)$)" +  
            "|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +  
            "([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|" +  
            "(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";  
    
    public static final String YEAR_MONTH="^\\d{4}-?(?:0[1-9]|1[0-2])$";
    
    /** 
     * URL正则表达式 
      * 匹配 http www ftp 
     */ 
    public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +  
                                    "(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +  
                                    "(\\w*:)*(\\w*\\+)*(\\w*\\.)*" +  
                                    "(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";   
 
    /** 
     * 身份证正则表达式 
     */ 
    public static final String IDCARD="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +  
                                        "(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +  
                                        "[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";  
       /** 
        * 1.匹配科学计数 e或者E必须出现有且只有一次 不含Dd 
        * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$ 
        */ 
       public final static String SCIENTIFIC_A ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))$";  
       /** 
        * 2.匹配科学计数 e或者E必须出现有且只有一次 结尾包含Dd 
        * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$ 
        */ 
       public final static String SCIENTIFIC_B ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))[dD]?$";  
       /** 
         * 3.匹配科学计数 是否含有E或者e都通过 结尾含有Dd的也通过（针对Double类型） 
         * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$ 
         */ 
       public final static String SCIENTIFIC_C ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?[dD]?$";  
       /** 
         * 4.匹配科学计数 是否含有E或者e都通过 结尾不含Dd 
         * 正则 ^[-+]?(\d+(\.\d*)?|\.\d+)([eE]([-+]?([012]?\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$ 
        */ 
       public final static String SCIENTIFIC_D ="^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$";  
      
       /**
        * 密码正则  6 - 16 长度 不能9位一下数字 不能有空格
        */
       public final static String passReg="^(?!\\d{1,8}$)[\\S]{6,16}$";
////------------------验证方法       
    /** 
     * 判断字段是否为空 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static synchronized boolean StrisNull(String str) {  
        return null == str || str.trim().length() <= 0 ? true : false ;  
    }  
    /** 
     * 判断字段是非空 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean StrNotNull(String str) {  
        return !StrisNull(str) ;  
    }  
    /** 
     * 字符串null转空 
     * @param str 
     * @return boolean 
     */ 
    public static  String nulltoStr(String str) {  
        return StrisNull(str)?"":str;  
    }     
    /** 
     * 字符串null赋值默认值  
     * @param str    目标字符串 
     * @param defaut 默认值 
     * @return String 
     */ 
    public static  String nulltoStr(String str,String defaut) {  
        return StrisNull(str)?defaut:str;  
    }  
    /** 
     * 判断字段是否为Email 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isEmail(String str) {  
        return Regular(str,EMAIL);  
    }  
    /** 
     * 判断是否为电话号码 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isPhone(String str) {  
        return Regular(str,PHONE);  
    }  
    /** 
     * 判断是否为手机号码 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isMobile(String str) {  
        return Regular(str,MOBILE);  
    }  
    /** 
     * 判断是否为Url 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isUrl(String str) {  
        return Regular(str,URL);  
    }     
    /** 
     * 判断是否为IP地址 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isIpaddress(String str) {  
        return Regular(str,IPADDRESS);  
    }     
    
    /**  
     * 判断字段是否为数字 正负整数 正负浮点数 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isNumber1(String str) {  
    	return isInteger(str) || isDouble(str);
    }
    
    /** 
     * 判断字段是否为INTEGER  符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isInteger(String str) {  
        return Regular(str,INTEGER);  
    }  
    /** 
     * 判断字段是否为正整数正则表达式 >=0 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isINTEGER_NEGATIVE(String str) {  
        return Regular(str,INTEGER_NEGATIVE);  
    }  
    /** 
     * 判断字段是否为负整数正则表达式 <=0 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isINTEGER_POSITIVE(String str) {  
        return Regular(str,INTEGER_POSITIVE);  
    }     
    /** 
     * 判断字段是否为DOUBLE 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isDouble(String str) {  
        return Regular(str,DOUBLE);  
    }  
    /**  
     * 判断字段是否为正浮点数正则表达式 >=0 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isDOUBLE_NEGATIVE(String str) {  
        return Regular(str,DOUBLE_NEGATIVE);  
    }  
    /** 
     * 判断字段是否为负浮点数正则表达式 <=0 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isDOUBLE_POSITIVE(String str) {  
        return Regular(str,DOUBLE_POSITIVE);  
    }     
    /** 
     * 判断字段是否为日期 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isDate(String str) {  
        return Regular(str,DATE_ALL);  
    }     
    
    public static boolean isYearMonth(String str)
    {
    	return Regular(str,YEAR_MONTH );
    }
    
    /** 
     * 判断字段是否为年龄 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isAge(String str) {  
        return Regular(str,AGE) ;  
    }  
    /** 
     * 判断字段是否超长 
     * 字串为空返回fasle, 超过长度{leng}返回true 反之返回false 
     * @param str 
     * @param leng 
     * @return boolean 
     */ 
    public static  boolean isLengOut(String str,int leng) {       
        return StrisNull(str)?false:str.trim().length() > leng ;  
    }  
    /** 
     * 判断字段是否为身份证 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isIdCard(String str) {  
        if(StrisNull(str)) return false ;  
        if(str.trim().length() < 15){
        	return true;
        }
        if(str.trim().length() == 15 || str.trim().length() == 18) {  
                return Regular(str,IDCARD);  
        }else {  
            return false ;  
        }  
          
    }  
    /** 
     * 判断字段是否为邮编 符合返回true 
     * @param str 
     * @return boolean 
     */ 
    public static  boolean isCode(String str) {  
        return Regular(str,CODE) ;  
    }  
    /** 
     * 判断字符串是不是全部是汉字 
     * @param str 
     * @return boolean 
     */ 
    public static boolean isChina(String str) {  
        return Regular(str,STR_CHINA) ;  
    }  
    /** 
     * 判断字符串是不是全部是英文字母 
     * @param str 
     * @return boolean 
     */ 
    public static boolean isEnglish(String str) {  
        return Regular(str,STR_ENG) ;  
    }  
    /** 
     * 判断字符串是不是全部是英文字母+数字 
     * @param str 
     * @return boolean 
     */ 
    public static boolean isENG_NUM(String str) {  
        return Regular(str,STR_ENG_NUM) ;  
    }  
    /** 
     * 判断字符串是不是全部是英文字母+数字+下划线 
     * @param str 
     * @return boolean 
     */ 
    public static boolean isENG_NUM_(String str) {  
        return Regular(str,STR_ENG_NUM_) ;  
    }  
    /** 
     * 过滤特殊字符串 返回过滤后的字符串 
     * @param str 
     * @return boolean 
     */ 
    public static  String filterStr(String str) {  
        Pattern p = Pattern.compile(STR_SPECIAL);  
        Matcher m = p.matcher(str);  
        return   m.replaceAll("").trim();  
    }     
    /** 
     * 匹配是否符合正则表达式pattern 匹配返回true 
     * @param str 匹配的字符串 
     * @param pattern 匹配模式 
     * @return boolean 
     */ 
    private static  boolean Regular(String str,String pattern){  
        if(null == str || str.trim().length()<=0)  
            return false;           
        Pattern p = Pattern.compile(pattern);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }  
    
    /**
     * 匹配优惠券说明
      * 【】
      * @param str
      * @return
     */
    public static boolean isTxtExplain(String str){
    	return Regular(str, STR_ENG_CHA_NUM_SYMBOL);
    }
    /** 
     * 判断是不是科学计数法 如果是 返回true 
     * 匹配科学计数 e或者E必须出现有且只有一次 结尾不含D 
     * 匹配模式可参考本类定义的 SCIENTIFIC_A，SCIENTIFIC_B,SCIENTIFIC_C,SCIENTIFIC_D 
     * 若判断为其他模式可调用 Regular(String str,String pattern)方法 
     * @param str 科学计数字符串 
     * @return boolean 
     */ 
    public static  boolean isScientific(String str){  
        if(StrisNull(str))  
            return false;   
        return Regular(str,RegexUtil.SCIENTIFIC_A);  
    }     
    
	
	/**
	 * 判断是否属于正整数
	 * @param str 输入字符
	 * @return 如果正确返回true，如果失败返回false
	 * @author 赵汉江
	 */
	 public static boolean isNumber(String str)
     {
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("^[0-9]*[1-9][0-9]*$");
        java.util.regex.Matcher match=pattern.matcher(str);
        if(match.matches() == false)
        {
           return false;
        }
        else
        {
           return true;
        }
     }
	 
	 
	/** 
	* 【替换HTML代码Font样式中的汉字】
	* @param inputstr
	* @return  
	*/
	
	public static String replaceHTMLFontCN(String inputstr){
		 String sFont = "font.*?[;\"']";
		 String reg="/^[u0391-uffe5]+$/";
		  
		 if(!Pattern.matches(reg, inputstr)){
			 inputstr = inputstr.replaceAll(sFont, "");
		 }
		 return inputstr;
	 }
	/**
	 * 
	  * 【是否是密码只能由数字，英文 大于六位数小于15位数】
	  * @param str
	  * @return
	 */
	public static boolean isPass(String str){
		return Pattern.matches(passReg, str);
	}
	/**
	 * 来源集合
	 */
	private static Set<Integer> sourceSet = new HashSet<Integer>();
	/**
	 * 
	  * 【判断是否是来源】
	  * @param source
	  * @return  
	  * @time:2015年5月29日 上午11:40:11
	  * @author 涂鑫
	  * @version
	 */
	public static boolean isSource(int source){
		if(sourceSet.isEmpty()){
			sourceSet.add(1); 
			sourceSet.add(0); 
			sourceSet.add(2); 
			sourceSet.add(3); 
			sourceSet.add(4); 
		}
		return sourceSet.contains(source);
	}
	
	/**
	 * 
	  * 【经纬度有消息】
	  * @param lon
	  * @param lat
	  * @return  
	  * @time:2015年6月12日 下午4:15:37
	  * @author 涂鑫
	  * @version
	 */
	public static boolean isLonLat(double lon,double lat){
		if(lon>180 || lon < -180){
			return false;
		}
		if(lat > 90 || lat < -90){
			return false;
		}
		return true;
	}
	// 性别集合 
		private static Set<Integer> sexSet = new HashSet<Integer>();
		/**
		 * 判断性别是否合法
		 * @param sex
		 * @return
		 */
		public static boolean isLegalSex(int sex) {
			if(sexSet.isEmpty()) {
				sexSet.add(1); // 男
				sexSet.add(0); // 女
			}
			return sexSet.contains(sex);
		}
		
}