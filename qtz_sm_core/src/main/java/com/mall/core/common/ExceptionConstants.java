package com.mall.core.common;

/**
 * 异常常量类
 * <p>Title:ExceptionConstants</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 赵汉江 - zhaohanjiang
 * @version v1.0 2014-12-27
 */
public class ExceptionConstants {

	/**<p>ErrorCode = 0 成功,没有返回数据</p>*/
	public static final int ERRORCODE_0 = 0;
	/**<p>ErrorCode = 1  成功,有返回数据</p>*/
	public static final int ERRORCODE_1 = 1;
	/**<p>ErrorCode = -1  失败</p>*/
	public static final int ERRORCODE_NEGATIVE1 = -1;
	/**<p>ErrorCode = 2 数据不存在</p>*/
	public static final int ERRORCODE_2 = 2;
	/**<p>ErrorCode = 3  sessionKey 过期</p>*/
	public static final int ERRORCODE_3 = 3;
	/**<p>ErrorCode = 4  权限不够</p>*/
	public static final int ERRORCODE_4 = 4;
	/**<p>ErrorCode = 5  版本不对</p>*/
	public static final int ERRORCODE_5 = 5;
	/**<p>ErrorCode = 6 当前session已经绑定登录用户</p> */
	public static final int ERRORCODE_6 = 6;
	/**<p>ErrorCode = 7 入参错误</p> */
	public static final int ERRORCODE_7 = 7;
	/**商品库存不足*/
	public static final int ERRORCODE_8 = 8;
	/**无效订单*/
	public static final int ERRORCODE_9 = 9;
	/**无效付款记录*/
	public static final int ERRORCODE_10 = 10;
	/**应付金额 与 实付金额 不符*/
	public static final int ERRORCODE_11 = 11;
	/**订单商品已经全退了*/
	public static final int ERRORCODE_12 = 12;
	/**运费模板错误，不存在缺省地区设置*/
	public static final int ERRORCODE_13 = 13;
	/**应退金额 与 实退金额 不符*/
	public static final int ERRORCODE_14 = 14;
	/**已经存在退款信息*/
	public static final int ERRORCODE_15 = 15;
	/**商品已下架*/
	public static final int ERRORCODE_16 = 16;
	/**<p>ErrorCode = 17 仲裁失败，不符合仲裁规则</p> */
	public static final int ERRORCODE_17 = 17;
	/**商品已下架*/
	public static final int ERRORCODE_18 = 18;
}
