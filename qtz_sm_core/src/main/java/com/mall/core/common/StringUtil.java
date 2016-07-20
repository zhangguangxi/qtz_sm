package com.mall.core.common;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import antlr.collections.impl.Vector;
import cache.BaseProperties;

import com.alibaba.fastjson.JSONObject;


@SuppressWarnings("all")
public class StringUtil {
	
	/**
	 * 全国一级行政区名称集合
	 */
	public static final String[] toponeRegionArr = {
		"北京市","天津市","上海市","重庆市","河北省","山西省","陕西省","山东省","河南省","辽宁省",
		"吉林省","黑龙江省","江苏省","浙江省","安徽省","江西省","福建省","湖北省","湖南省","四川省",
		"贵州省","云南省","广东省","海南省","甘肃省","青海省","台湾省","内蒙古自治区","新疆维吾尔自治区","西藏自治区",
		"广西壮族自治区","宁夏回族自治区","香港特别行政区","澳门特别行政区"};
	
	public static final String DEFAULT_PATH_SEPARATOR = ",";
	
	/**纯数字 */
	public static final int TYPE_NUM = 0;
	/**纯字母 */
	public static final int TYPE_LETTER = 1;
	/**数字+字母 */
	public static final int TYPE_NUM_AND_LETTER = 2;
	
	/**
	 * 判断数组是否有效
	 */
	public static boolean isValid(Object[] arr){
		if(arr == null || arr.length == 0){
			return false ;
		}
		return true ;
	}
	
	/** 
	* 【连接字符串】(这里用一句话描述这个方法的作用)
	* @param list
	* @param s
	* @return  
	*/
	public static String join(List list,String s){
		StringBuffer sb = new StringBuffer();
		if(null == list) return null;
		for (Object object : list) {
			if(sb.toString().length() > 0) sb.append(s);
			sb.append(object.toString());
		}
		return sb.toString();
	}
	
	/** 
	* 【添加前缀 和 后缀】(如果已经存在则不添加)
	* @param strs
	* @param s
	* @return  
	*/
	public static String prdfixAndSuffix(String strs,char s){
		String str = new String(new char[]{s});
		if(!StringUtil.isEmpty(strs)){//拼接商品属性ID集合字符串 逗号分隔，收尾必须是逗号！
			StringBuffer sb = new StringBuffer();
			String one = strs.substring(0, 1);
			String two = strs.substring(strs.length()-1, strs.length());
			if(!str.equals(one)){
				sb.append(str);
			}
			sb.append(strs);	
			if(!str.equals(two)){
				sb.append(str);
			}
			strs = sb.toString();
		}
	    return strs;
	}
	
	public static String appendByLength(String str,int length,char c){
		if(str == null || c == 0 || str.length() >= length){
			return str;
		}
		StringBuffer sb = new StringBuffer(str);
		for (int i = 0; i < (length - str.length()); i++) {
			sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * 将数组变换成字符串,使用","号分割
	 */
	public static String arr2Str(Object[] arr) {
		String temp = "" ;
		if(isValid(arr)){
			for(Object s : arr){
				temp = temp + s + "," ;
			}
			return temp.substring(0,temp.length() - 1);
		}
		return temp;
	}
	
	
	public static int compare(String s1, String s2) {
		if ((s2 == null) && (s1 == null)) {
			return 0;
		}
		if (s2 == null) {
			return 1;
		}
		if (s1 == null) {
			return -1;
		}
		Collator cnCollator = Collator.getInstance(Locale.getDefault());
		return cnCollator.compare(s1, s2);
	}

	public static String decodeFromAscii(String s) {
		if ((s == null) || (s.length() == 0) || (s.length() % 2 != 0)) {
			return null;
		}
		String result = new String();
		for (int i = 0; i < s.length(); i += 2) {
			String str = s.substring(i, i + 2);
			result = result + getValueFromAscii(str);
		}
		return result;
	}

	public static StringBuilder delLastChar(StringBuilder str, char ch) {
		if (str.length() < 1) {
			return str;
		}
		if (str.charAt(str.length() - 1) == ch) {
			str.deleteCharAt(str.length() - 1);
		}
		return str;
	}

	public static StringBuilder delFirstChar(StringBuilder str, char ch) {
		if (str.length() < 1) {
			return str;
		}
		if (str.charAt(0) == ch) {
			str.deleteCharAt(0);
		}
		return str;
	}

	public static String encodeToAscii(String s) {
		if ((s == null) || (s.length() == 0)) {
			return null;
		}
		String result = new String();
		for (int i = 0; i < s.length(); ++i) {
			result = result + getASCII16(s.charAt(i));
		}
		return result;
	}

	private static String getASCII16(char c) {
		String hexString = Integer.toHexString(c);
		if (hexString.length() == 1) {
			hexString = "0" + hexString;
		}
		return hexString;
	}

	private static String getValueFromAscii(String s) {
		char c = (char) Integer.parseInt(s, 16);
		return String.valueOf(c);
	}

	public static boolean isDigit(String str) {
		return (str != null) && (str.matches("\\d+"));
	}

	public static boolean isEmpty(Object str) {
		if(str instanceof String)
			return (null == str) || (str.toString().trim().length() == 0);
		else if(str instanceof JSONObject)
			return (null == str) || (((JSONObject)str).toString().length() == 0);
		else if(str instanceof Collection)
			return (null == str) || (((Collection) str).size() == 0);
		else if(str instanceof Map)
			return (null == str) || (((Map) str).size() == 0);
		else if(str instanceof Vector)
			return (null == str) || (((Vector) str).size() == 0);
		else
			return null == str;
	}
	
	/** 
	* 【对象转字符串】(这里用一句话描述这个方法的作用)
	* @param str
	* @return  
	*/
	public static String objToStr(Object obj) {
		return (String) (obj = null == obj ? null : obj.toString());
	}
	
	/** 
	* 【字符串转integer】(这里用一句话描述这个方法的作用)
	* @param str
	* @return  
	*/
	public static Integer strToInteger(String str) {
		return null != str && RegexUtil.isNumber(str) ? Integer.parseInt(str) : null;
	}
	
	/** 
	* 【字符串转long】(这里用一句话描述这个方法的作用)
	* @param str
	* @return  
	*/
	public static Long strToLong(String str) {
		return null != str && RegexUtil.isNumber(str) ? Long.parseLong(str) : null;
	}
	
	/** 
	* 【字符串转Double】(这里用一句话描述这个方法的作用)
	* @param str
	* @return  
	*/
	public static Double strToDouble(String str) {
		return null != str && RegexUtil.isNumber(str) ? Double.parseDouble(str) : null;
	}

	public static String killNull(Object str) {
		return killNull(str, "");
	}

	public static String killNull(Object str, String dflt) {
		if (isEmpty(str)) {
			return dflt;
		}
		return str.toString().trim();
	}

	public static List<String> split(String str, String delim) {
		List splitList = null;
		StringTokenizer st = null;
		if (str == null) {
			return splitList;
		}
		if (delim != null) {
			st = new StringTokenizer(str, delim);
		} else {
			st = new StringTokenizer(str);
		}
		if (st.hasMoreTokens()) {
			splitList = new ArrayList();
			while (st.hasMoreTokens()) {
				splitList.add(st.nextToken());
			}
		}
		return splitList;
	}

	public static List<Integer> toIntList(String str) {
		if (str == null) {
			return Collections.EMPTY_LIST;
		}
		String[] strs = str.split("\\D+");
		List list = new ArrayList(strs.length);
		for (String s : strs) {
			if (s.length() > 0) {
				list.add(Integer.valueOf(s));
			}
		}
		return list;
	}

	public static String getRegexByStr(String str) {
		StringBuffer regex = new StringBuffer(".*");
		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) == '*') {
				regex.append("\\");
			}
			regex.append(str.charAt(i));
			regex.append(".*");
		}
		return regex.toString();
	}

	public static boolean matchString(String str1, String str2) {
		return (str1.matches(getRegexByStr(str2))) || (str2.matches(getRegexByStr(str1)));
	}

	public static String replaceNewlineChar(String str) {
		return str.replace("\r\n", "").replace("\r", "").replace("\n", "");
	}
	
	  /**
	   * 判断一个字符串中是否包含另一个字符串
	   * @param parentStr	父串
	   * @param subStr	子串
	   * @return 如果父串包含子串的内容返回真，否则返回假
	   */
	  public static boolean isInclude(String parentStr, String subStr) {
		  return parentStr.indexOf(subStr) >= 0;
	  }


	  /**
	   * 判断一个字符串中是否包含另一个字符串数组的任何一个
	   * @param parentStr	父串
	   * @param subStrs	子串集合(以逗号分隔的字符串)
	   * @return 如果父串包含子串的内容返回真，否则返回假
	   */
	  public static boolean isIncludes(String parentStr, String subStrs) {
	    String[] subStrArray = strToStrArray(subStrs);
	    for(int j = 0 ; j<subStrArray.length ; j++){
	      String subStr = subStrArray[j];
	      if(isInclude(parentStr,subStr))
	        return true;
	      else
	        continue;
	    }
	    return false;
	  }
	  /**
	   * 将一个中间带逗号分隔符的字符串转换为字符型数组对象
	   * @param str 待转换的符串对象
	   * @return 字符型数组
	   */
	  public static String[] strToStrArray(String str) {
	    return strToStrArrayManager(str, DEFAULT_PATH_SEPARATOR);
	  }	
	  /**
	   * 将字符串对象按给定的分隔符separator转象为字符型数组对象
	   * @param str 待转换的符串对象
	   * @param separator 字符型分隔符
	   * @return 字符型数组
	   */
	  public static String[] strToStrArray(String str, String separator) {
	    return strToStrArrayManager(str, separator);
	  }
	  
	  /**
	   * 根据一条完整的目录路径截取末节点
	   * @Title: getLastmenupathByPath
	   * @Description: 
	   * @param path
	   * @return String
	   * @author 赵汉江
	   * @date 2013-4-26 下午01:57:09
	   * @version V1.0
	   */
	  public static String getLastmenupathByPath(String path)
	  {
		  String menupath = null;
		//截取目录标题路径前后斜杠/
			if(path != null && path.trim().length() > 0)
			{
				if(path.indexOf("/")  < 0)
				{
					menupath = path;
				}
				else
				{
					String[] menupaths = path.split("/");
					menupath = menupaths[menupaths.length - 1];
				}
			}
			return menupath;
	  }
	  
	  private static String[] strToStrArrayManager(
		      String str,
		      String separator) {

		    StringTokenizer strTokens = new StringTokenizer(str, separator);
		    String[] strArray = new String[strTokens.countTokens()];
		    int i = 0;

		    while (strTokens.hasMoreTokens()) {
		      strArray[i] = strTokens.nextToken().trim();
		      i++;
		    }

		    return strArray;
		  }
	  
	  /**
	   * 根据阿拉伯数字得到汉字
	   * @Title: getCNByNumber
	   * @Description: 
	   * @param number
	   * @return String
	   * @author 赵汉江
	   * @date 2013-5-25 下午07:27:14
	   * @version V1.0
	   */
	  public static String getCNByNumber(int number)
	  {
		  String cn = "";
		  switch (number) {
		case 1:
			cn = "一";
			break;
		case 2:
			cn = "二";
			break;
		case 3:
			cn = "三";
			break;
		case 4:
			cn = "四";
			break;
		case 5:
			cn = "五";
			break;
		case 6:
			cn = "六";
			break;
		case 7:
			cn = "七";
			break;
		case 8:
			cn = "八";
			break;
		case 9:
			cn = "九";
			break;
		case 10:
			cn = "十";
			break;
		case 11:
			cn = "十一";
			break;
		case 12:
			cn = "十二";
			break;
		}
		  return cn;
	  }
	  
		/***
		 * 将一个字符串list按照指定的链接字符串 拼接成字符串返回
		 * @Title: strListToJoinStr
		 * @Description: 
		 * @param strList
		 * @param joinstr
		 * @return String
		 * @author 赵汉江
		 * @date 2013-5-30 下午08:21:47
		 * @version V1.0
		 */
	    public static String strListToJoinStr(List<? extends Object> strList,String joinstr)
	    {
		    if(isEmpty(strList))
		    {
		            return null;
		    }
		    StringBuilder ressb=new StringBuilder(); 
		    for(int i = 0;i<strList.size();i++) 
		    {
		    	if(i > 0)
		    	{
		    		ressb.append(joinstr);
		    	}
		    	ressb.append(strList.get(i));
		    }
		    return ressb.toString();
	    }
	    
	    /**
	     * 根据行政区全称 解析 他的一级行政区名称
	     * @Title: getToponeRegionByRegionname
	     * @Description: 
	     * @param regionname
	     * @return String
	     * @author 赵汉江
	     * @date 2013-8-1 下午05:07:39
	     * @version V1.0
	     */
	    public static String getToponeRegionByRegionname(String regionname)
	    {
	    	if(regionname == null || regionname.trim().length() == 0)
	    	{
	    		return null;
	    	}
	    	for(int i=0;toponeRegionArr != null && i<toponeRegionArr.length;i++)
	    	{
	    		String region = toponeRegionArr[i];
	    		if(regionname.indexOf(region,0) > -1)
	    		{
	    			return region;
	    		}
	    	}
	    	return null;
	    }
	    
	    /**
	     * 统计字符串中汉字个数
	     * @Title: getLenOfString
	     * @Description: 
	     * @param nickname
	     * @return int
	     * @author 赵汉江
	     * @date 2013-9-3 下午08:39:20
	     * @version V1.0
	     */
	    public static int getCNLenOfString(String nickname)
	    {
	        // 汉字个数
	        int chCnt = 0;
	        String regEx = "[\\u4e00-\\u9fa5]"; // 如果考虑繁体字，u9fa5-->u9fff
	        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regEx);
	        java.util.regex.Matcher m = p.matcher(nickname);
	        while (m.find())
	        {
	        	chCnt++;
	        }
	        return chCnt;
	    }
	    
	    public static String appendImg01(String imgs){
	    	if (StringUtil.isEmpty(imgs)) {
              return null;
            }
	    	return appendImg(BaseProperties.getBaseProperties(Constants.qtzimg01), imgs);
	    }
	    
	    public static String appendImg02(String imgs){
	      if (StringUtil.isEmpty(imgs)) {
            return null;
          }
	    	return appendImg(BaseProperties.getBaseProperties(Constants.qtzimg02), imgs);
	    }
	    public static String appendImg03(String imgs){
	      if (StringUtil.isEmpty(imgs)) {
            return null;
          }
	    	
	    	return appendImg(BaseProperties.getBaseProperties(Constants.qtzimg03), imgs);
	    }
	    public static String appendImg(String bucketUrl,String imgs){
	    	String[] split = imgs.split(",");
	    	String img="";
	    	int i=0;
	    	for (String string : split) {
	    		if(isSimilar("http", string)){
		    		i++;
		    		img+=string+",";
	    			continue;
		    	}
	    		img +=bucketUrl+"/"+string+",";
	    		i++;
			}
	    	if(i>0){
	    		img=img.substring(0,img.lastIndexOf(","));
	    	}
	    	return img;
	    }
	    /**
	     * 
	      * 【截取 第n次出现的字符串】
	      * @param str
	      * @param ofString
	      * @param n
	      * @return
	     */
	    public static String subN(String str,String ofString, int n){
	    	int i=0;
	    	if(n==0){
	    		n=1;
	    	}
	    	int indexOf=0;
	    	while(i<n){
	    	 indexOf = str.indexOf(ofString, indexOf+1);
	    	i++;
	    	}
	    	return str.substring(indexOf+1);
	    }
	    
	    /** 
	    * 【判断字符串是否符合指定格式】(这里用一句话描述这个方法的作用)
	    * @param type
	    * @param s
	    * @return  
	    */
	    public static boolean isAccord(int type,String s){
			boolean isAccord = false;
			//数字
	    	if(TYPE_NUM == type && RegexUtil.isInteger(s)){
	    			isAccord = true;
	    	}//字母
	    	else if(TYPE_LETTER == type && RegexUtil.isEnglish(s)){
	    		isAccord = true;
	    	}//数字+字母
	    	else if(TYPE_NUM_AND_LETTER == type && (RegexUtil.isInteger(s) || RegexUtil.isEnglish(s))){
	    		isAccord = true;
	    	}
	    	return isAccord;
		}
	    
	    public static void main(String[] args) {
	    	String string="xxx/aaa/324234234/1231345/4356445/6456/45/6/45645/6/45";
	    	int n=1;
	    	int i=0;
	    	int indexOf=0;
	    	while(i<n){
	    	 indexOf = string.indexOf("/", indexOf+1);
	    	i++;
	    	}
	    	System.out.println(indexOf);
	    	System.out.println(string.substring(indexOf+1));
	    	
	    	
//	    	List<String> strList = new ArrayList<String>();
//	    	strList.add("a");
//	    	strList.add(null);
//	    	strList.add("c");
//	    	String joinstr = "<>";
//	    	System.out.println(getToponeRegionByRegionname("湖南省双峰县"));
//	    	System.out.println(appendByLength("111111", 12, '0'));
	    	//System.out.println(StringUtil.prdfixAndSuffix(",", ','));
		}
	    
	    /**
	     * 获得格式化后的下载文件名称
	     * 格式化包括 转码 和 取出字符空格
	     * @Title: formatDownloadFileName
	     * @Description: 
	     * @param filename
	     * @return
	     * @throws UnsupportedEncodingException String
	     * @author 赵汉江
	     * @date 2013-11-2 上午11:22:06
	     * @version V1.0
	     */
//	    public static String formatDownloadFileName(String filename) throws UnsupportedEncodingException
//	    {
//	    	if(isNotEmpty(filename))
//			{
//	    		filename = filename.replace(" ", "");
//	    		filename = new String(filename.getBytes(), "ISO8859-1");
//			}
//			return filename;
//	    }
	    /**
	     * 
	      * 【判断一个字符串是否以另外一个字符串开头】
	      * @param one 开头字符串
	      * @param anotherString 总字符串
	      * @return 
	     */
	    public static boolean isSimilar(String one, String anotherString) {
			int length = one.length();
			if(length > anotherString.length()) {
				//如果被期待为开头的字符串的长度大于anotherString的长度
				return false;
			}
			if (one.equalsIgnoreCase(anotherString.substring(0, length))) {
				return true;
			} else {
				return false;
			}
		}
}