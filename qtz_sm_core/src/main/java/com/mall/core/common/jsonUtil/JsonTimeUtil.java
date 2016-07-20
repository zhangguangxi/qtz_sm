package com.mall.core.common.jsonUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;



/**
 * <p>Title:JsonTimeUtil</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-28
 */

public class JsonTimeUtil {

	protected Locale locale;
	private static final String Simple_Date_Format = "yyyy-MM-dd";
	private static final int Simple_Date_Format_Length = Simple_Date_Format
			.length();
	private static final String Simple_DateTime_Format = "yyyy-MM-dd HH:mm:ss";
	private static final String[] MONTH_ICS = { "JUN", "FEB", "MAR", "APR",
		"MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };
	private static final String[] xingQi_CN = { "星期日", "星期一", "星期二", "星期三",
			"星期四", "星期五", "星期六" };
	
	private static final String datePattern = "yyyy-MM-dd";
	private static final long PER_DAY_MILLION_SECOND = 86400000;
	private static final String YEAR = "y";
	private static final String MONTH = "M";
	private static final String DAY_OF_MONTH = "d";
	private static final String HOUR = "H";
	private static final String MINUTE = "m";
	private static final String SECOND = "s";

	public JsonTimeUtil() {
		this(Locale.getDefault());
	}

	public JsonTimeUtil(Locale locale) {
		this.locale = locale;
	}

	/**
	 * 格式化时间格式
	 * 
	 * @param inDate
	 *            String 需要格式的字符串
	 * @return String 返回格式化好的日期字符串
	 */
	public static String formatDatetime(String inDate) {

		char day_sep = 0;
		if (inDate.indexOf("-") > 0) {
			day_sep = '-';
		} else if (inDate.indexOf("/") > 0) {
			day_sep = '/';
		} else if (inDate.indexOf(".") > 0) {
			day_sep = '.';
		}

		if (day_sep == 0) {
			return "";
		}

		String inYear = "", inMonth = "", inDay = "";
		String inHour = "", inMinute = "", inSecond = "";

		inYear = inDate.substring(0, inDate.indexOf(day_sep));
		inDate = inDate.substring(inDate.indexOf(day_sep) + 1);

		if (inDate.indexOf(day_sep) > 0) {
			inMonth = inDate.substring(0, inDate.indexOf(day_sep));
			inDate = inDate.substring(inDate.indexOf(day_sep) + 1);

			if (inDate.indexOf(" ") > 0) {
				inDay = inDate.substring(0, inDate.indexOf(" "));
				inDate = inDate.substring(inDate.indexOf(" ") + 1);
			} else {
				inDay = inDate;
				inDate = "";
			}
		}

		if (inDate.indexOf(":") > 0) {
			inHour = inDate.substring(0, inDate.indexOf(":"));
			inDate = inDate.substring(inDate.indexOf(":") + 1);

			if (inDate.indexOf(":") > 0) {
				inMinute = inDate.substring(0, inDate.indexOf(":"));
				inDate = inDate.substring(inDate.indexOf(":") + 1);

				inSecond = inDate;
			}
		}

		String result = inYear.trim() + "." + inMonth.trim() + "."
				+ inDay.trim();
		if (inHour.trim().length() > 0) {
			result += " " + inHour.trim() + ":" + inMinute.trim() + ":"
					+ inSecond.trim();
		} else {
			result += " 00:00:00";
		}

		return result;
	}

	/**
	 * 比较时间大小
	 * 
	 * @param datetime1
	 *            String 第一个日期字符串字符串
	 * @param datetime2
	 *            String 第一个日期字符串字符串
	 * @return boolean 若大于则返回true,否则返回false
	 */
	public static boolean compareDatetime(String datetime1, String datetime2) {

		long ldatetime1 = 0;
		long ldatetime2 = 0;

		if (datetime1 != "") {
			if (datetime1.length() == 10) { // 若为2005-03-31格式，则转换为2005-03-31
				// 00:00:00格式
				datetime1 = datetime1 + " 00:00:00";
			}
			ldatetime1 = Timestamp.valueOf(datetime1).getTime();
		}
		if (datetime2 != "") {
			if (datetime2.length() == 10) {
				datetime2 = datetime2 + " 00:00:00";
			}
			ldatetime2 = Timestamp.valueOf(datetime2).getTime();
		}
		if (datetime1 == "") {
			ldatetime1 = 0;
		}
		if (datetime2 == "") {
			ldatetime2 = 0;
		}
		if (ldatetime1 >= ldatetime2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 字符串转换为普通的日期
	 */
	public static java.util.Date strToSysDate(String str) {
		if (null != str && str.length() > 0) {
			try {
				if (str.length() <= Simple_Date_Format_Length) { // 只包含日期。
					return (new SimpleDateFormat(Simple_Date_Format))
							.parse(str);
				} else { // 包含日期时间
					return (new SimpleDateFormat(Simple_DateTime_Format))
							.parse(str);
				}
			} catch (ParseException error) {
				return null;
			}
		} else {
			return null;
		}
	}

	public static Object convert(Class<?> type, Object value) {
		if (value == null) {
			return null;
		} else if (type == Date.class) {
			return convertToDate(type, value);
		} else if (type == String.class) {
			return convertToString(type, value);
		} else if (type == Boolean.class) {
			return convertToBoolean(type, value);
		} else if (type == Object.class) {
			return convertToObject(type, value);
		}

		throw new ConversionException("Could not convert "
				+ value.getClass().getName() + " to " + type.getName());
	}

	@SuppressWarnings({ "deprecation" })
	protected static Object convertToString(Class<?> type, Object value) {
		String str;
		java.sql.Timestamp dat;
		boolean ifdate;
		if (value instanceof Date) {
			try {
				dat = (java.sql.Timestamp) value;
				if ((dat.getHours() == 0) && (dat.getMinutes() == 0)
						&& (dat.getSeconds() == 0)) {
					ifdate = true;
				} else {
					ifdate = false;
				}
				str = (new SimpleDateFormat(Simple_DateTime_Format))
						.format(dat);
				if ((str.length() <= Simple_Date_Format_Length) || (ifdate)) { // 只包含日期。
					str = (new SimpleDateFormat(Simple_Date_Format))
							.format(dat);
				}
				return str;
			} catch (Exception e) {
				throw new ConversionException("Error converting Date to String");
			}
		}

		if (value instanceof Boolean) {
			try {
				// if (!Constants.ifBoolToStr)
				// return value;
				Boolean obj = (Boolean) value;
				if (obj.booleanValue()) {
					return "是";
				} else {
					return "否";
				}

			} catch (Exception e) {
				throw new ConversionException("Error converting Date to String");
			}
		}
		return value.toString();
	}

	protected static Object convertToDate(Class<?> type, Object value) {
		if (value instanceof Date) {
			return value;
		}

		if (value instanceof String) {
			try {
				if (StringUtils.isEmpty(value.toString())) {
					return null;
				}
				return new java.sql.Date(strToSysDate((String) value).getTime());
			} catch (Exception pe) {
				throw new ConversionException("Error converting String to Date");
			}
		}

		throw new ConversionException("Could not convert "
				+ value.getClass().getName() + " to " + type.getName());
	}

	protected static Object convertToBoolean(Class<?> type, Object value) {

		if (value instanceof String) {
			try {
				// if (!Constants.ifBoolToStr)
				// return value;
				@SuppressWarnings("unused")
				Boolean obj = null;
				if (value.equals("1")) {
					return Boolean.valueOf("true");
				} else {
					return Boolean.valueOf("false");
				}
				// return obj.valueOf((String)value);

			} catch (Exception pe) {
				throw new ConversionException("Error converting String to Date");
			}
		}

		return value;
	}

	protected static Object convertToObject(Class<?> type, Object value) {
		return value;
	}

	public static Timestamp getNowTime() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date = formatter.parse(formatter
				.format(new java.util.Date()));
		return new Timestamp(date.getTime());
	}
	

	public static Calendar getCurrentCalendar() {
		Calendar cal = Calendar.getInstance();

		return cal;
	}

	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 将指定字符串转换成日期
	 * 
	 * @param date
	 *            String 日期字符串
	 * @param datePattern
	 *            String 日期格式
	 * @return Date
	 */
	public static java.util.Date getFormatDate(String date, String datePattern) {
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(
				datePattern);
		return sd.parse(date, new java.text.ParsePosition(0));
	}

	public static java.util.Date getFormatDate_HaveException(String date,
			String datePattern) throws ParseException {
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(
				datePattern);
		return sd.parse(date);
	}
	/**
	 * 将Timestamp类型的时间转换成字符
	 * 
	 * @Title: timestamp2Str
	 * @param timestamp
	 * @param datePattern
	 * @return
	 */
	public static String timestamp2Str(Timestamp timestamp) {
		return timestamp2Str(timestamp,Simple_DateTime_Format);
	}
	/**
	 * 将Timestamp类型的时间转换成字符
	 * 
	 * @Title: timestamp2Str
	 * @param timestamp
	 * @param datePattern
	 * @return
	 */
	public static String timestamp2Str(Timestamp timestamp, String datePattern) {
		String str = "";
		if (timestamp != null) {
			SimpleDateFormat format = new SimpleDateFormat(datePattern);
			str = format.format(timestamp);
		}
		return str;
	}

	/**
	 * 将字符类型的时间转换成Timestamp
	 * 
	 * @Title: str2Timestamp
	 * @param str
	 * @param datePattern
	 * @return
	 */
	public static Timestamp str2Timestamp(String str, String datePattern) {
		Timestamp time = null;
		if (!"".equals(str) && str != null) {
			try {
				SimpleDateFormat format = new SimpleDateFormat(datePattern);
				Date date = format.parse(str);
				time = new Timestamp(date.getTime());
			} catch (ParseException pe) {
				System.out.println("转换字符串出现异常:" + pe.getMessage());
				pe.printStackTrace();
			}
		}
		return time;
	}

	/**
	 * 返回当前日期
	 * 
	 * @功能:
	 * @返回类型:@java.sql.Timestamp
	 */
	public static java.sql.Timestamp getTiemstamp() {
		return new java.sql.Timestamp(new java.util.Date().getTime());
	}

	/**
	 * 将日期转换成指定字符串
	 * 
	 * @param date
	 *            Date 日期
	 * @param datePattern
	 *            String 日期格式
	 * @return String
	 */
	public static String getFormatDateString(java.util.Date date,
			String datePattern) {
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(
				datePattern);
		return sd.format(date);
	}

	/**
	 * 得到ICS系统的月三字代码
	 * 
	 * @param monthId
	 *            int 下标从0开始，0表示1月
	 * @return String
	 */
	private static String getMonth_ICS_ByIndex(int monthId) {
		return MONTH_ICS[monthId];
	}

	/**
	 * 将日期转换为ICS日期格式
	 * 
	 * @param date
	 *            String 日期格式为"yyyyMMdd"
	 * @return String 例如"1月3日，03JUN"
	 */
	public static String getDate_ICS(String date_Str) {

		int month = Integer.parseInt(date_Str.substring(4, 6)) - 1;
		String day = date_Str.substring(6, 8);
		String year = date_Str.substring(2, 4);
		String date_ICS = day + getMonth_ICS_ByIndex(month) + year;
		return date_ICS;
	}

	/**
	 * 将日期转换为ICS日期格式
	 * 
	 * @param date
	 *            String 日期格式为"yyyyMMdd"
	 * @return String 例如"1月3日，03JUN"
	 */
	public static String getDate_ICS(Date date) {
		String date_Str = getFormatDateString(date, "yyyyMMdd");
		int month = Integer.parseInt(date_Str.substring(4, 6)) - 1;
		String day = date_Str.substring(6, 8);
		String year = date_Str.substring(2, 4);
		String date_ICS = day + getMonth_ICS_ByIndex(month) + year;
		return date_ICS;
	}

	/**
	 * 返回中国习惯DayOfWeek，1表示星期一
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String getDayOfWeek_CN(Date date) {
		String date_Str = getFormatDateString(date, "yyyyMMdd");
		int year = Integer.parseInt(date_Str.substring(0, 4));
		int month = Integer.parseInt(date_Str.substring(4, 6)) - 1;
		int day = Integer.parseInt(date_Str.substring(6, 8));
		java.util.Calendar date_C = new java.util.GregorianCalendar(year,
				month, day);
		int dayOfWeek = date_C.get(java.util.Calendar.DAY_OF_WEEK);

		return xingQi_CN[dayOfWeek - 1];
	}

	/**
	 * 返回中国习惯DayOfWeek，1表示星期一
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String getDayOfWeek_CN(String date) {
		// String date_Str = getFormatDateString(date,"yyyyMMdd");
		System.out.println("date is " + date);
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5, 7)) - 1;
		int day = Integer.parseInt(date.substring(8, 10));
		java.util.Calendar date_C = new java.util.GregorianCalendar(year,
				month, day);
		int dayOfWeek = date_C.get(java.util.Calendar.DAY_OF_WEEK);
		String dayOfWeek_CN = null;
		if (dayOfWeek == 1) {
			dayOfWeek_CN = "7";
		} else {
			dayOfWeek_CN = Integer.toString(dayOfWeek - 1);
		}
		return dayOfWeek_CN;
	}

	/**
	 * 返回2个时间之间的月份差
	 * 
	 * @param date1
	 *            String
	 * @param date2
	 *            String
	 * @param datePattern
	 *            String
	 * @return int
	 */
	public static int getBetweenMonths(String date1, String date2,
			String datePattern) {
		Date d1 = getFormatDate(date1, datePattern);
		Date d2 = getFormatDate(date2, datePattern);
		long d1_days = d1.getTime() / PER_DAY_MILLION_SECOND;
		long d2_days = d2.getTime() / PER_DAY_MILLION_SECOND;
		return (int) ((d2_days - d1_days) / 30);
	}

	/**
	 * @Title: returnFromDate
	 * @Description: 返回当前日期,格式为"yyyy-MM-dd HH:mm:ss"
	 * @param fromDate
	 * @return
	 * @throws ParseException
	 */
	public static String returnFromDate(String fromDate) {
		try {
			return getNxtDate(fromDate, datePattern, DAY_OF_MONTH, 0);
		} catch (ParseException e) {
			//LogAdmin.errorLog(e);
		}
		return null;
	}

	/**
	 * @Title: returnToDate
	 * @Description: 返回当前日期的后一个日期,格式为"yyyy-MM-dd HH:mm:ss"
	 * @param toDate
	 * @return
	 * @throws ParseException
	 */
	public static String returnToDate(String toDate) {
		try {
			return getNxtDate(toDate, datePattern, DAY_OF_MONTH, 1);
		} catch (ParseException e) {
			//LogAdmin.errorLog(e);
		}
		return null;
	}

	/**
	 * 某一个日期的前一个日期
	 * 
	 * @param d
	 *            ,某一个日期
	 * @param field
	 *            日历字段 y 年 M 月 d 日 H 时 m 分 s 秒
	 * @param amount
	 *            数量
	 * @return 一个日期
	 */
	public static Timestamp getNxtDate(Date d, String field, int amount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if (field != null && !field.equals("")) {
			if (field.equals(YEAR)) {
				calendar.add(Calendar.YEAR, amount);
			} else if (field.equals(MONTH)) {
				calendar.add(Calendar.MONTH, amount);
			} else if (field.equals(DAY_OF_MONTH)) {
				calendar.add(Calendar.DAY_OF_MONTH, amount);
			} else if (field.equals(HOUR)) {
				calendar.add(Calendar.HOUR, amount);
			} else if (field.equals(MINUTE)) {
				calendar.add(Calendar.MINUTE, amount);
			} else if (field.equals(SECOND)) {
				calendar.add(Calendar.SECOND, amount);
			}
		} else {
			return null;
		}
		Timestamp ts = new Timestamp(calendar.getTimeInMillis());
		return ts;
	}

	/**
	 * @Title: getNxtDate
	 * @Description: 某一个时间的前amount个或者后amount个时间
	 * @param date
	 *            字符型时间
	 * @param datePattern
	 *            日期格式
	 * @param field
	 *            日历字段 y 年 M 月 d 日 H 时 m 分 s 秒
	 * @param amount
	 *            amount个时间
	 * @return
	 * @throws ParseException
	 */
	public static String getNxtDate(String date, String datePattern,
			String field, int amount) throws ParseException {
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(
				datePattern);
		Date d = sd.parse(date);
		Timestamp nxtC = getNxtDate(d, field, amount);
		String nxtD = String.valueOf(nxtC);
		return nxtD;
	}

	/**
	 * @Title: getCurrentDate
	 * @Description: 获取当前日期yyyy-MM-dd
	 * @return java.lang.String
	 * @author: create by 朱王军 May 14, 2010 1:16:49 PM
	 */
	public static String getCurrentDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(new Date());
		return s;
	}

	/**
	 * @Title: getCurrentDate
	 * @Description: 获取当前日期
	 * @param iType
	 * @return java.lang.String
	 * @author: create by 朱王军 May 14, 2010 1:17:41 PM
	 */
	public static String getCurrentDate(int iType) {
		SimpleDateFormat df = null;
		Timestamp ts = null;
		long currentTimeMillis = 0;
		switch (iType) {
		case 0:
			df = new SimpleDateFormat("yyyy-MM-dd");
			break;
		case 1:
			df = new SimpleDateFormat("yyyy-MM-dd HH");
			break;
		case 2:
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			break;
		case 3:
			df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			break;
		case 4:
			currentTimeMillis = System.currentTimeMillis();
			ts = new Timestamp(currentTimeMillis);
			return ts.toString();
		}
		String s = df.format(new Date());
		return s;
	}

	/**
	 * @Title: getNextDate
	 * @Description: 获取当前日期后的一天
	 * @return java.lang.String
	 * @author: create by 朱王军 May 14, 2010 1:19:21 PM
	 */
	public static String getNextDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * @Title: getPreviousDate
	 * @Description: 获取当前日期前的一天
	 * @return java.lang.String
	 * @author: create by 朱王军 May 14, 2010 1:20:18 PM
	 */
	public static String getPreviousDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String s = df.format(calendar.getTime());
		return s;
	}

	/**
	 * @Title: getFormatDateString1
	 * @Description: 
	 * @param java.sql.Date
	 * @param datePattern
	 * @return
	 * @author: create by 朱王军 May 17, 2010 2:00:48 PM
	 */
	public static String getFormatDateString1(java.sql.Date date,
			String datePattern) {
		java.text.SimpleDateFormat sd = new java.text.SimpleDateFormat(
				datePattern);
		return sd.format(date);
	}
	/**
	 * 将一个日期转换为年月日的字符串

	 */
	public static String DateToStr(Date date)
	{
			SimpleDateFormat sdf=new SimpleDateFormat(Simple_Date_Format);
			String result="";
			try
			{
				result=sdf.format(date);
			}
			catch(Exception e)
			{
				
			}
			return result;
		}

	/**
	 * 王刚
	 * <p>MethodName: getPartOfDate</p>
	 * <p>Description: 获取日期的部分信息</p>
	 * @param date
	 * @param part
	 * @return
	 * @throws
	 */
	public static int getPartOfDate(Date date,int part){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date); 
		
		return calendar.get(part);
	}
	
	/**
	 * 
	 * <p>MethodName: getDateByPart</p>
	 * <p>Description: 根据制定的年月日生成日期</p>
	 * @param year
	 * @param month
	 * @param date
	 * @return
	 * @throws
	 */
	public static Date getDateByPart(int year,int month,int date){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.DATE,month);
		calendar.set(Calendar.MONTH,date);
		
		return calendar.getTime();
	}
	
	public static void main(String args[])throws Exception {
		System.out.println(Integer.parseInt(getCurrentDate().substring(0,4))-1);
		System.out.println(getCurrentDate().substring(5,7));
	}

}
