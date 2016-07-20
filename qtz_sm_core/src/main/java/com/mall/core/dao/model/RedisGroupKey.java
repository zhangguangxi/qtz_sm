package com.mall.core.dao.model;
/**
 * 
 * <p>Title:RedisGroupKey</p>
 * <p>Description:(redis 分表  c常亮名字忘记大写)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月14日
 */
public class RedisGroupKey {
	/**
	 * 手验证码分组 有效期
	 */
	public final static String amf_phone_code="amf.phone.code.";
	/**
	 * 支付宝支付密码
	 */
	public final static String amf_phone_pay_pwd_code="amf.pay.pwd.phone.code.";
	/**
	 * 用户最后操作
	 */
	public final static String amf_user_lastOpera="amf.user.lastOpera.";
	/**
	 * 融云token
	 */
	public final static String amf_rongyun_token="amf.rongyun.token.";
	/**
	 * 用户信息图片
	 */
	public final static String amf_user_TopImgs="amf.user.TopImgs.";
	/**
	 * session
	 */
	public final static String amf_user_app_session="amf.user.app.session";
	/**
	 * 用户loc
	 */
	public final static String amf_user_loc="amf.user.loc.";
	/**
	 * 商家验证码
	 */
	public final static String seller_coupon_code="amf.seller.coupon.code.";
	/**
	 * 用户信息
	 */
	public final static String amf_user="amf.user.";
	/**
	 * 动态
	 */
	public final static String amf_user_dynamic="amf.user.dynamic.";
	/**
	 * 订单id后缀
	 */
	public final static String amf_pay_order_suffix="amf.pay.order.suffix";
	/**
	 * 订单统计
	 */
	public final static String amf_order_count="amf.order.count.";
	
	/**
	 * 团队关系维护
	 */
	public final static String REDIS_teamEelation="teamEelation";
	
	/**
	 * 团队关系维护
	 */
	public final static String REDIS_teamEelation_mysql="teamEelationmysql";
	
	/**
	 * 年费分润
	 */
	public final static String pp_order_fenrun="pp_order_fenrun";
	/**
	 * 对账任务运行中标记
	 */
	public final static String recon_task_running_tag="recon_task_running_tag";
}
