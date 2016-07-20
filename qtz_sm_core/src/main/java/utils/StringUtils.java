package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title:utils.StringUtils</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 刘晓峰 - Laven.liu
 * @version v1.0 2016/4/28
 */
public class StringUtils extends org.apache.commons.lang.StringUtils {

    /**
     * 邮箱验证
     *
     * @param email 邮箱
     * @return 验证结果
     */
    public static boolean checkEmail(String email) {
        return checkRegex("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$", email);
    }

    /**
     * 邮政编码验证
     *
     * @param zipCode 邮政编码吗
     * @return 验证结果
     */
    public static boolean checkZipCode(String zipCode) {
        return checkRegex("\\d{6}", zipCode);
    }

    /**
     * 手机号验证
     *
     * @param mobile 手机号码
     * @return 验证结果
     */
    public static boolean checkMobile(String mobile) {
        return checkRegex("^1(3[0-9]|5[0-35-9]|8[0-9]|4[57]|7[0678])[0-9]{8}$", mobile);
//        return checkRegex("^((13[0-9])|(15[^4,\\D])|(18[0,2,5-9]))\\d{8}$", mobile);
    }

    /**
     * 检查是否为正整数 不包含 0
     *
     * @param number 数字
     * @return 验证结果
     */
    public static boolean checkPositiveInteger(String number) {
        return checkRegex("^[0-9]*[1-9][0-9]*$", number);
    }

    /**
     * 身份证验证
     *
     * @param IDCard 身份证号码
     * @return 验证结果
     */
    public static boolean checkIDCard(String IDCard) {
        try {
            return new IDCardUtil().IDCardValidate(IDCard);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 正则验证
     *
     * @param regex   正则
     * @param content 内容
     * @return 验证结果
     */
    public static boolean checkRegex(String regex, String content) {
        if (StringUtils.isEmpty(content)) {
            return false;
        }
        return Pattern.compile(regex).matcher(content).matches();
    }

    private static class IDCardUtil {

        public IDCardUtil() {
        }

        /*********************************** 身份证验证开始 ****************************************/
        /**
         * 身份证号码验证 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
         * 八位数字出生日期码，三位数字顺序码和一位数字校验码。 2、地址码(前六位数）
         * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 3、出生日期码（第七位至十四位）
         * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 4、顺序码（第十五位至十七位）
         * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。 5、校验码（第十八位数）
         * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
         * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
         * （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
         */

        /**
         * 功能：身份证的有效验证
         *
         * @param IDStr 身份证号
         * @return 有效：返回"" 无效：返回String信息
         * @throws NumberFormatException 数字转换异常
         * @throws ParseException        日期转换异常
         */
        public boolean IDCardValidate(String IDStr) throws NumberFormatException, ParseException {
            // 记录错误信息
            String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
            String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7",
                    "9", "10", "5", "8", "4", "2"};
            String Ai = "";
            // ================ 号码的长度 15位或18位 ================
            if (IDStr.length() != 15 && IDStr.length() != 18) {
                return false;
            }
            // ================ 数字 除最后以为都为数字 ================
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);
            } else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }
            if (!isNumeric(Ai)) {
                return false;
            }
            // ================ 出生年月是否有效 ================
            // 年份
            String strYear = Ai.substring(6, 10);
            // 月份
            String strMonth = Ai.substring(10, 12);
            // 月份
            String strDay = Ai.substring(12, 14);
            if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
                return false;
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150 || (gc.getTime().getTime()
                    - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                return false;
            }

            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                return false;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                return false;
            }
            // ================ 地区码是否有效 ================
            Hashtable<String, String> h = GetAreaCode();
            if (h.get(Ai.substring(0, 2)) == null) {
                return false;
            }
            // ================ 判断最后一位的值 ================
            int TotalmulAiWi = 0;
            for (int i = 0; i < 17; i++) {
                TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i)))
                        * Integer.parseInt(Wi[i]);
            }
            int modValue = TotalmulAiWi % 11;
            String strVerifyCode = ValCodeArr[modValue];
            Ai = Ai + strVerifyCode;

            if (IDStr.length() == 18) {
                if (!Ai.equalsIgnoreCase(IDStr)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 功能：设置地区编码
         *
         * @return Hashtable 对象
         */
        private Hashtable<String, String> GetAreaCode() {
            Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("11", "北京");
            hashtable.put("12", "天津");
            hashtable.put("13", "河北");
            hashtable.put("14", "山西");
            hashtable.put("15", "内蒙古");
            hashtable.put("21", "辽宁");
            hashtable.put("22", "吉林");
            hashtable.put("23", "黑龙江");
            hashtable.put("31", "上海");
            hashtable.put("32", "江苏");
            hashtable.put("33", "浙江");
            hashtable.put("34", "安徽");
            hashtable.put("35", "福建");
            hashtable.put("36", "江西");
            hashtable.put("37", "山东");
            hashtable.put("41", "河南");
            hashtable.put("42", "湖北");
            hashtable.put("43", "湖南");
            hashtable.put("44", "广东");
            hashtable.put("45", "广西");
            hashtable.put("46", "海南");
            hashtable.put("50", "重庆");
            hashtable.put("51", "四川");
            hashtable.put("52", "贵州");
            hashtable.put("53", "云南");
            hashtable.put("54", "西藏");
            hashtable.put("61", "陕西");
            hashtable.put("62", "甘肃");
            hashtable.put("63", "青海");
            hashtable.put("64", "宁夏");
            hashtable.put("65", "新疆");
            hashtable.put("71", "台湾");
            hashtable.put("81", "香港");
            hashtable.put("82", "澳门");
            hashtable.put("91", "国外");
            return hashtable;
        }

        /**
         * 功能：判断字符串是否为数字
         *
         * @param str 被判断字符串
         * @return 检查结果
         */
        private boolean isNumeric(String str) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(str);

            return isNum.matches();
        }

        /**
         * 功能：判断字符串是否为日期格式
         *
         * @param strDate 判断字符串
         * @return 检查结果
         */
        public boolean isDate(String strDate) {
            Pattern pattern = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]"
                    + "?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])"
                    + "|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2]"
                    + "[0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])"
                    + "|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                    + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s"
                    + "(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
            Matcher m = pattern.matcher(strDate);
            return m.matches();
        }
    }

}
