package com.mall.core.common.response;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * <p>
 * Title:RespMsg
 * </p>
 * <p>
 * Description:返回状态信息
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年3月11日
 */
public class RespMsg {

	public Integer serviceCode;

	public void setServiceCode(Integer serviceCode) {
		this.serviceCode = serviceCode;
	}

	// code 协议 100模块名字 001 错误码

	/**
	 * 用户未登录
	 */
	public static final JSONObject user_not_login = new JSONObject(respOperationMsg(RespCode.user_not_login, "请登录."));

	/**
	 * 请求参数错误
	 */
	// public static final JSONObject request_parameter_error = new
	// JSONObject(respMsg(3, "请求参数错误。"));
	/**
	 * 手机号码格式错误
	 */
	public static final JSONObject phone_format_error = new JSONObject(respOperationMsg(RespCode.phone_format_error, "请输入正确的手机号码格式."));

	/**
	 * 手机号码已经注册
	 */
	public static final JSONObject phone_already_registered = new JSONObject(respOperationMsg(RespCode.phone_already_registered, "手机号码已被注册."));

	/**
	 * 验证码获取失败
	 */
	public static JSONObject auth_code_get_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.auth_code_get_failed, code));
	}

	/**
	 * 验证码不正确
	 */
	public static final JSONObject user_auth_code_error = new JSONObject(respOperationMsg(RespCode.user_auth_code_error, "验证码不正确."));
	/**
	 * 请求当中没有token
	 */
	// public static final JSONObject no_sessionid = new
	// JSONObject(respErrorMsg(100006, "请登录."));

	/**
	 * 用户密码格式错误
	 */
	public static final JSONObject user_pass_format_error = new JSONObject(respOperationMsg(RespCode.user_pass_format_error, "密码格式请输入6-16位数字字母."));

	/**
	 * 用户注册失败
	 */
	public static JSONObject user_registered_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.user_registered_failed, code));
	}

	/**
	 * 登录失败
	 */
	public static JSONObject user_login_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.user_login_failed, code));
	}

	/**
	 * "优惠券已经领取完毕。"
	 */
	public static final JSONObject coupon_no_num_error = new JSONObject(respOperationMsg(RespCode.coupon_no_num_error, "优惠券已经领取完毕."));
	/**
	 * 优惠券没有赠送给你
	 */
	public static final JSONObject coupon_no_have_error = new JSONObject(respOperationMsg(RespCode.coupon_no_have_error, "优惠券没有赠送给你."));
	/**
	 * 优惠券重复领取
	 */
	public static final JSONObject coupon_alway_have_error = new JSONObject(respOperationMsg(RespCode.coupon_alway_have_error, "优惠券重复领取,或者超过商家限制数量."));
	/**
	 * 不是商家粉丝，不能在此领取
	 */
	// public static final JSONObject coupon_no_fans_error = new
	// JSONObject(respMsg(17, "不是商家粉丝，不能在此领取。"));
	/**
	 * 活动未开始
	 */
	public static final JSONObject coupon_active_no_start_error = new JSONObject(respOperationMsg(RespCode.coupon_active_no_start_error, "活动未开始."));
	/**
	 * 活动已结束
	 */
	public static final JSONObject coupon_active_end_error = new JSONObject(respOperationMsg(RespCode.coupon_active_end_error, "活动已结束."));

	/**
	 * 活动无效
	 */
	// public static final JSONObject coupon_active_no_error = new
	// JSONObject(respMsg(20, "活动无效。"));
	/**
	 * 优惠券无效
	 */
	public static JSONObject coupon_no_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.coupon_no_error, code));
	}

	/**
	 * 用户无此优惠券
	 */
	public static final JSONObject coupon_user_no_error = new JSONObject(respOperationMsg(RespCode.coupon_user_no_error, "用户无此优惠券."));

	/**
	 * 优惠券已经过期
	 */
	public static final JSONObject coupon_no_date_error = new JSONObject(respOperationMsg(RespCode.coupon_no_date_error, "优惠券已经过期."));
	/**
	 * 优惠券已经使用过了
	 */
	public static final JSONObject coupon_already_use_error = new JSONObject(respOperationMsg(RespCode.coupon_already_use_error, "优惠券已经使用过了."));
	/**
	 * 不是认证的商家不能创建活动
	 */
	public static final JSONObject acticity_no_seller_error = new JSONObject(respOperationMsg(RespCode.acticity_no_seller_error, "不是认证的商家不能创建活动."));

	/**
	 * 获取商家认证信息失败
	 */
	public static JSONObject getSellerInfo_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getSellerInfo_failed, code));
	}

	/**
	 * 没有发布动态权限
	 */
	public static final JSONObject no_permision_upload = new JSONObject(respOperationMsg(RespCode.no_permision_upload, "没有发布权限."));

	/**
	 * 用户不存在
	 */
	public static final JSONObject user_no_existence = new JSONObject(respOperationMsg(RespCode.user_no_existence, "用户不存在或者用户密码错误."));

	/**
	 * 评论内容过长
	 */
	public static final JSONObject reply_content_long = new JSONObject(respOperationMsg(RespCode.reply_content_long, "评论内容过长."));
	/**
	 * 动态不存在
	 */
	public static final JSONObject dynamic_id_no_existence = new JSONObject(respOperationMsg(RespCode.dynamic_id_no_existence, "动态内容不存在或者已经删除."));

	/**
	 * 回复评论人不存在
	 */
	public static final JSONObject reply_id_no_existence = new JSONObject(respOperationMsg(RespCode.reply_id_no_existence, "回复评论人信息不存在."));

	/**
	 * 用户被封号
	 */
	public static final JSONObject user_seal_number = new JSONObject(respOperationMsg(RespCode.user_seal_number, "用户已经被封号."));
	/**
	 * 转载来源为空
	 */
	public static final JSONObject reprint_absObs_isnull = new JSONObject(respOperationMsg(RespCode.reprint_absObs_isnull, "转载来源为空"));
	/**
	 * 通讯录参数为null
	 */
	public static final JSONObject address_book_is_null = new JSONObject(respOperationMsg(RespCode.address_book_is_null, "通讯录参数为空."));

	/**
	 * 用户退出
	 */
	public static JSONObject user_logout_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.user_logout_failed, code));
	}

	/**
	 * 评论失败
	 */
	public static JSONObject comment_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.comment_error, code));
	}

	/**
	 * 关注朋友失败
	 */
	public static JSONObject user_add_friend_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.user_add_friend_failed, code));
	}

	/**
	 * 获取好友失败
	 */
	public static JSONObject get_user_friend_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.get_user_friend_failed, code));
	}

	/**
	 * 行业id 错误
	 */
	public static final JSONObject category_id_error = new JSONObject(respOperationMsg(RespCode.category_id_error, "行业信息可能有误."));

	/**
	 * 用户不是商家类型
	 */
	public static final JSONObject user_no_business = new JSONObject(respOperationMsg(RespCode.user_no_business, "商家账户类型"));

	/**
	 * 用户不是个人账户类型
	 */
	public static final JSONObject user_no_person = new JSONObject(respOperationMsg(RespCode.user_no_person, "不是个人账户类型."));

	/**
	 * 动态发布类型错误
	 */
	public static final JSONObject publishType_error = new JSONObject(respOperationMsg(RespCode.publishType_error, "动态发布类型错误"));

	/**
	 * 更新头像昵称失败
	 */
	public static JSONObject update_headImg_nick_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.update_headImg_nick_failed, code));
	}

	/**
	 * 移除朋友失败
	 */
	public static JSONObject user_remove_friend_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.user_remove_friend_failed, code));
	}

	/**
	 * 该用户已经关注过了
	 */
	public static final JSONObject user_friend_existence = new JSONObject(respOperationMsg(RespCode.user_friend_existence, "该用户已经关注过了..."));
	/**
	 * 这个用户已经不是你所关注的
	 */
	public static final JSONObject no_existence_flist = new JSONObject(respOperationMsg(RespCode.no_existence_flist, ""));

	/**
	 * 获取好友粉丝列表失败
	 */
	public static JSONObject get_user_fans_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.get_user_fans_failed, code));
	}

	/**
	 * 身份证格式错误
	 */
	public static final JSONObject idcard_format_error = new JSONObject(respOperationMsg(RespCode.idcard_format_error, "身份证格式错误"));

	/**
	 * 提交商业认证失败
	 */
	public static JSONObject sub_sellerapprove_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.sub_sellerapprove_failed, code));
	}

	/**
	 * 处理个人认证失败
	 */
	public static JSONObject save_personAuthen_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.save_personAuthen_failed, code));
	}

	/**
	 * 账户类型不匹配
	 */
	public static final JSONObject userType_Dont_match = new JSONObject(respOperationMsg(RespCode.userType_Dont_match, "账户类型不匹配."));

	/**
	 * 行政区id有误
	 */
	public static final JSONObject region_data_erro = new JSONObject(respOperationMsg(RespCode.region_data_erro, ""));

	/**
	 * 还有未审核的个人资料不能提交
	 */
	public static final JSONObject person_no_check_no_submit = new JSONObject(respOperationMsg(RespCode.person_no_check_no_submit, "还有正在审核数据不能更改."));
	/**
	 * 审核通过的个人资料不能提交
	 */
	public static final JSONObject person_ok_check_no_submit = new JSONObject(respOperationMsg(RespCode.person_ok_check_no_submit, "审核通过不能更改"));

	/**
	 * 还有未审核的商家资料不能提交
	 */
	public static final JSONObject seller_no_check_no_submit = new JSONObject(respOperationMsg(RespCode.person_ok_check_no_submit, "还有正在审核数据不能更改"));

	/**
	 * 请求转发 商品列表
	 */
	public static final JSONObject request_forward_getGoodsCategoryList = new JSONObject(respOperationMsg(
			RespCode.request_forward_getGoodsCategoryList, ""));
	/**
	 * 请求转发 getUser
	 */
	public static final JSONObject request_forward_getUser = new JSONObject(respOperationMsg(RespCode.request_forward_getUser, ""));

	/**
	 * 获取主页信息失败
	 */
	public static JSONObject get_index_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.get_index_failed, code));
	}

	/**
	 * 获取个人认证失败
	 */
	public static JSONObject get_pserson_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.get_pserson_failed, code));
	}

	/**
	 * 动态获取失败
	 */
	public static JSONObject get_dynamic_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.get_dynamic_failed, code));
	}

	/**
	 * 回复失败
	 */
	public static JSONObject reply_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.reply_failed, code));
	}

	/**
	 * 评论失败
	 */
	public static JSONObject comment_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.comment_failed, code));
	}

	/**
	 * 点赞失败
	 */
	public static JSONObject praise_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.praise_failed, code));
	}

	/**
	 * 点赞存在
	 */
	public static final JSONObject praise_exist = new JSONObject(respOperationMsg(RespCode.praise_exist, "点赞存在 "));

	/**
	 * 搜索失败
	 */
	public static JSONObject search_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.search_failed, code));
	}

	/**
	 * 优惠券操作失败
	 */
	public static JSONObject coupon_failed(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.coupon_failed, code));
	}

	/**
	 * 账户没有认证
	 */
	public static final JSONObject account_no_auth = new JSONObject(respOperationMsg(RespCode.account_no_auth, "账户没有认证."));
	/**
	 * 卷不存在
	 */
	public static final JSONObject coupon_null = new JSONObject(respOperationMsg(RespCode.coupon_null, "券不存在或者已经被删除."));
	/**
	 * 商家没有此优惠券
	 */
	public static final JSONObject userSeller_no_coupon = new JSONObject(respOperationMsg(RespCode.userSeller_no_coupon, "商家没有此优惠券"));
	/**
	 * 账号已经修改过
	 */
	public static final JSONObject user_account_failed = new JSONObject(respOperationMsg(RespCode.user_account_failed, "账号只能修改一次."));
	/**
	 * 商家认证不能修改地区
	 */
	public static final JSONObject business_auth_Ok_cant_address = new JSONObject(respOperationMsg(RespCode.business_auth_Ok_cant_address,
			"商家认证通过不能修改地区"));
	/**
	 * 账号存在或者重复
	 */
	public static final JSONObject user_account_repeat = new JSONObject(respOperationMsg(RespCode.user_account_repeat, "账号重复已经存在."));
	/**
	 * 没有这个优惠卷
	 */
	public static final JSONObject dynamic_coupon_id_error = new JSONObject(respOperationMsg(RespCode.coupon_user_no_error, "优惠券错误 你没有这张优惠券不能发布."));

	/**
	 * 评论不存在
	 */
	public static final JSONObject comment_id_no_exist = new JSONObject(respOperationMsg(RespCode.comment_id_no_exist, "这条评论不存在."));

	/**
	 * 步奏不正确
	 */
	public static final JSONObject steps_error = new JSONObject(respOperationMsg(RespCode.steps_error, "请完善信息."));

	/**
	 * 不能自己回复自己
	 */
	public static final JSONObject comment_cant_reply_oneself = new JSONObject(respOperationMsg(RespCode.comment_cant_reply_oneself, "不能自己回复自己."));
	/**
	 * 没有定位信息
	 */
	public static final JSONObject no_loc = new JSONObject(respOperationMsg(RespCode.no_loc, "重新定位试试."));
	/**
	 * 未注册
	 */
	public static final JSONObject unregistered = new JSONObject(respOperationMsg(RespCode.unregistered, "未注册."));
	/**
	 * 重复发布优惠卷
	 */
	public static final JSONObject coupon_repeat_publish = new JSONObject(respOperationMsg(RespCode.coupon_repeat_publish, "优惠卷已经发布了."));
	/**
	 * 领取渠道错误
	 */
	public static final JSONObject coupon_pull_channel_error = new JSONObject(respOperationMsg(RespCode.coupon_pull_channel_error,
			"优惠卷渠道错误,可能还没有开通其他渠道"));
	/**
	 * 劵码不存在
	 */
	public static final JSONObject coupon_code_dont_exist = new JSONObject(respOperationMsg(RespCode.coupon_code_dont_exist, "劵码不存在"));

	/**
	 * 日期不准确
	 */
	public static final JSONObject coupo_data_inaccurate = new JSONObject(respOperationMsg(RespCode.coupo_data_inaccurate, "日期不准确"));
	/**
	 * 优惠券发布数量超出
	 */
	public static final JSONObject coupon_publish_num_error = new JSONObject(respOperationMsg(RespCode.coupon_publish_num_error, "优惠券发布数量超过4张"));
	/**
	 * 账户不存在
	 */
	public static final JSONObject account_inexistence = new JSONObject(respOperationMsg(RespCode.account_inexistence, "账户不存在."));
	/**
	 * 关注错误自己关注自己
	 */
	public static final JSONObject follower_error_meFollowerMe = new JSONObject(respOperationMsg(RespCode.follower_error_meFollowerMe, "关注错误自己关注自己."));

	/**
	 * 活动已过期
	 */
	public static final JSONObject ACT_PASS_inexistence = new JSONObject(respOperationMsg(RespCode.act_pass_inexistence, "活动已过期."));
	/**
	 * 未加入活动
	 */
	public static final JSONObject ACT_NO_JOIN_inexistence = new JSONObject(respOperationMsg(RespCode.act_no_join_inexistence, "未加入活动."));
	/**
	 * 奖品领完了
	 */
	public static final JSONObject ACT_NO_AWARD_inexistence = new JSONObject(respOperationMsg(RespCode.act_no_award_inexistence, "奖品领完了."));
	/**
	 * 已经领取了
	 */
	public static final JSONObject ACT_OK_AWARD_inexistence = new JSONObject(respOperationMsg(RespCode.act_ok_award_inexistence, "已经领取了."));
	/**
	 * 活动不存在
	 */
	public static final JSONObject ACT_NO_inexistence = new JSONObject(respOperationMsg(RespCode.act_no_inexistence, "活动不存在."));

	/**
	 * 邀请码错误
	 */
	public static final JSONObject invitationCode_error_1 = new JSONObject(respOperationMsg(RespCode.invitationCode_error_1, "邀请码错误."));
	/**
	 * 自己不能邀请自己
	 */
	public static final JSONObject invitationCode_error_4 = new JSONObject(respOperationMsg(RespCode.invitationCode_error_4, "自己不能邀请自己."));
	/**
	 * 存在邀请人
	 */
	public static final JSONObject invitationCode_error_5 = new JSONObject(respOperationMsg(RespCode.invitationCode_error_5, "存在邀请人."));

	/**
	 * 邀请人不存在
	 */
	public static final JSONObject invitationCode_error_6 = new JSONObject(respOperationMsg(RespCode.invitationCode_error_6, "邀请人不存在."));
	
	/**
	 * 邀请人比您晚注册，不能邀请您
	 */
	public static final JSONObject invitationCode_error_7 = new JSONObject(respOperationMsg(RespCode.invitationCode_error_7, "邀请人比您晚注册，不能邀请您."));
	
	/**
	 * 邀请码错误
	 */
	public static final JSONObject invitationCode_error_2 = new JSONObject(respOperationMsg(RespCode.invitationCode_error_2,
			"邀请码错误.邀请码输入的是手机号码 但此手机号码可能没注册"));

	/**
	 * 接口过期
	 */
	public static final JSONObject api_Failure = new JSONObject(respOperationMsg(RespCode.api_failure, "接口过期,需要升级客户端"));
	/**
	 * 旧密码错误
	 */
	public static final JSONObject oldPw_error = new JSONObject(respOperationMsg(RespCode.oldPw_error, "旧密码错误"));

	/**
	 * 输入两次密码不一致
	 */
	public static final JSONObject confirmPw_error = new JSONObject(respOperationMsg(RespCode.retrievePassword_error, "输入两次密码不一致"));

	/**
	 * 背景图片数量不正确
	 */
	public static final JSONObject backgoundImg_num_error_1 = new JSONObject(respOperationMsg(RespCode.backgoundImg_num_error_1, "不能够删除第一张."));
	/**
	 * 背景图片数量不正确
	 */
	public static final JSONObject backgoundImg_num_error_2 = new JSONObject(respOperationMsg(RespCode.backgoundImg_num_error_2, "背景图片数量不正确超出8张"));
	/**
	 * 邀请数量不够
	 */
	public static final JSONObject invitationCode_error_3 = new JSONObject(respOperationMsg(RespCode.invitationCode_error_3, "邀请数量不够,请要求朋友"));
	/**
	 * 商品分类不存在
	 */
	public static final JSONObject goodsCategory_dont_exist = new JSONObject(respOperationMsg(RespCode.goodsCategory_dont_exist, "商品分类不存在"));
	/**
	 * 商品分类中存在商品
	 */
	public static final JSONObject goodsCategory_product_error = new JSONObject(respOperationMsg(RespCode.goodsCategory_product_error, "商品分类中存在商品"));
	/**
	 * 商家没有开通店铺
	 */
	public static final JSONObject seller_no_store = new JSONObject(respOperationMsg(RespCode.seller_no_store, "商家已关闭(未开通)"));
	/**
	 * 商家店铺过期被关闭
	 */
	public static final JSONObject seller__store_close = new JSONObject(respOperationMsg(RespCode.seller_no_store, "商家店铺过期被关闭了哦"));
	/**
	 * 存在相同商品分类名
	 */
	public static final JSONObject exist_valid_goods_category_name = new JSONObject(respOperationMsg(RespCode.exist_valid_goods_category_name,
			"存在相同商品分类名"));
	/**
	 * 金额错误
	 */
	public static final JSONObject price_error = new JSONObject(respOperationMsg(RespCode.price_error, "金额错误"));
	/**
	 * 店铺存在并且有效
	 */
	public static final JSONObject seller_store_exist_valid = new JSONObject(respOperationMsg(RespCode.seller_store_exist_valid, "店铺存在并且有效"));
	/**
	 * 银行卡号错误
	 */
	public static final JSONObject bank_id_erroe = new JSONObject(respOperationMsg(RespCode.bank_id_erroe, "银行卡号错误"));
	/**
	 * 不支持此银行
	 */
	public static final JSONObject does_not_support_the_bank = new JSONObject(respOperationMsg(RespCode.does_not_support_the_bank, "不支持此银行"));
	/**
	 * 支付密码错误
	 */
	public static final JSONObject wallet_pay_pwd_error = new JSONObject(respOperationMsg(RespCode.wallet_pay_pwd_error, "支付密码错误"));
	/**
	 * 订单已支付
	 */
	public static final JSONObject order_pay_error = new JSONObject(respOperationMsg(RespCode.order_pay_error, "订单已支付"));
	/**
	 * 余额不足
	 */
	public static final JSONObject not_sufficient_funds = new JSONObject(respOperationMsg(RespCode.not_sufficient_funds, "余额不足"));
	/**
	 * 银行卡不存在
	 */
	public static final JSONObject bank_inexistence = new JSONObject(respOperationMsg(RespCode.bank_inexistence, "银行卡不存在"));

	/**
	 * 订单总价小于店铺起送价
	 */
	public static final JSONObject order_min_inexistence = new JSONObject(respOperationMsg(RespCode.order_min_inexistence, "订单总价小于店铺起送价"));

	/**
	 * 未设置支付密码错误
	 */
	public static final JSONObject wallet_pay_pwd_null_error = new JSONObject(respOperationMsg(RespCode.wallet_pay_pwd_null_error, "未设置支付密码"));
	/**
	 * 查询订单支付状态失败 未支付
	 */
	public static final JSONObject query_orderPayment_status_failure = new JSONObject(respOperationMsg(RespCode.query_orderPayment_status_failure,
			"该订单未支付"));
	/**
	 * 该订单状态不能取消
	 */
	public static final JSONObject orders_status_cannot_be_cancelled = new JSONObject(respOperationMsg(RespCode.orders_status_cannot_be_cancelled,
			"该订单状态不能取消"));

	/**
	 * 请登录个人版本应用
	 */
	public static final JSONObject login_user_type_error_1 = new JSONObject(respOperationMsg(RespCode.login_user_type_error_1, "请登陆个人版本应用"));

	/**
	 * 请登录商家版本应用
	 */
	public static final JSONObject login_user_type_error_2 = new JSONObject(respOperationMsg(RespCode.login_user_type_error_1, "请登陆商家版本应用"));
	/**
	 * 存在提款申请
	 */
	public static final JSONObject exist_withdrawal_apply = new JSONObject(respOperationMsg(RespCode.exist_withdrawal_apply, "存在提款申请,请在处理完后再次申请."));
	/**
	 * 店铺非营业阶段
	 */
	public static final JSONObject store_close_business = new JSONObject(respOperationMsg(RespCode.store_close_business, "店铺非营业阶段"));

	/**
	 * 错误的预约时间
	 */
	public static final JSONObject error_makeTime = new JSONObject(respOperationMsg(RespCode.error_makeTime, "预约时间不能小于当前时间"));

	public static final JSONObject error_404 = new JSONObject(respOperationMsg(RespCode._404, "请求不存在404"));

	public static final JSONObject error_400 = new JSONObject(respOperationMsg(RespCode._400, "请检查接口需要的参数"));

	public static final JSONObject error_405 = new JSONObject(respOperationMsg(RespCode._405, "请求方式错误"));

	/**
	 * token过期
	 */
	public static final JSONObject session_overdue = new JSONObject(respOperationMsg(RespCode.session_overdue, "请重新登录"));

	/**
	 * 保存活动用户地址出错
	 */
	public static JSONObject save_act_user_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.save_act_user_error, code));
	}

	/**
	 * 服务器异常
	 */
	public static final JSONObject server_throws_exception = new JSONObject(respOperationMsg(RespCode._500, "服务器出现繁忙，请稍后再请求."));
	/**
	 * 服务器超时
	 */
	public static final JSONObject _timeout = new JSONObject(respOperationMsg(RespCode._timeout, "响应超时,服务器繁忙."));

	/**
	 * 获取活动参与用户错误
	 */
	public static JSONObject show_act_user_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.show_act_user_error, code));
	}

	/**
	 * 是否可以领取
	 */
	public static JSONObject accept_award_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.accept_award_error, code));
	}

	/**
	 * 查询活动详情失败
	 */
	public static JSONObject show_act_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.accept_award_error, code));
	}

	/**
	 * 请输入活动名字
	 */
	public static JSONObject build_near_act_name_param_error = new JSONObject(respOperationMsg(RespCode.build_near_act_name_param_error, "请输入活动名字"));
	/**
	 * 请输入活动开始时间
	 */
	public static JSONObject build_near_act_stime_param_error = new JSONObject(respOperationMsg(RespCode.build_near_act_stime_param_error, "请输入活动时间"));
	/**
	 * 请输入活动海报
	 */
	public static JSONObject build_near_act_headImg_param_error = new JSONObject(respOperationMsg(RespCode.build_near_act_headImg_param_error,
			"请输入活动海报"));
	/**
	 * 请输入定位地址
	 */
	public static JSONObject build_near_act_addr_param_error = new JSONObject(respOperationMsg(RespCode.build_near_act_addr_param_error, "请定位地址"));
	/**
	 * 请输入活动结束时间
	 */
	public static JSONObject build_near_act_etime_param_error = new JSONObject(respOperationMsg(RespCode.build_near_act_etime_param_error,
			"请输入活动结束时间"));
	/**
	 * 请输入活动介绍
	 */
	public static JSONObject build_near_act_txt_param_error = new JSONObject(respOperationMsg(RespCode.build_near_act_txt_param_error, "请输入活动介绍"));
	/**
	 * 领取优惠劵来源错误
	 */
	public static JSONObject source_param_error = new JSONObject(respOperationMsg(RespCode.source_param_error, "领取优惠劵来源错误."));

	/**
	 * 
	 * 【创建附近活动操作失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午10:02:17
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject build_near_act_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.build_near_act_error, code));
	}

	/**
	 * 
	 * 【发布优惠劵错误】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 上午10:40:21
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject publish_coupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.publish_coupon_error, code));
	}

	/**
	 * 
	 * 【创建附近的活动失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午10:59:58
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject build_coupon_act_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.build_coupon_act_error, code));
	}

	/**
	 * 
	 * 【商家发布未过期的列表】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:03:46
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject show_seller_live_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.show_seller_live_error, code));
	}

	/**
	 * 
	 * 【用户参加的未过期活动】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:05:09
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject show_join_live_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.show_seller_live_error, code));
	}

	/**
	 * 
	 * 【查看过期活动】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:10:21
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject show_history_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.show_history_error, code));
	}

	/**
	 * 
	 * 【查看最新活动】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:18:48
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject show_new_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.show_new_error, code));
	}

	/**
	 * 
	 * 【查看活动详情】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:19:12
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject show_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.show_error, code));
	}

	/**
	 * 
	 * 【查看报名活动用户失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:21:37
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject show_users_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.show_users_error, code));
	}

	/**
	 * 
	 * 【查看个人认证信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:25:49
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject person_show_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.person_show_error, code));
	}

	/**
	 * 
	 * 【保存个人认证失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:37:16
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject save_personAuthen_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.save_personAuthen_error, code));
	}

	/**
	 * 
	 * 【查看商家认证信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:41:19
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject sell_show_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.sell_show_error, code));
	}

	/**
	 * 
	 * 【保存商家认证信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 上午11:46:58
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject sell_save_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.sell_save_error, code));
	}

	/**
	 * 请上次身份证
	 */
	public static JSONObject pnoImg_param_error = new JSONObject(respOperationMsg(RespCode.pnoImg_param_error, "请上传身份证正反面."));

	/**
	 * 请上次身份证
	 */
	public static JSONObject legal_pnoImg_param_error = new JSONObject(respOperationMsg(RespCode.legal_pnoImg_param_error, "请上传法人身份证."));
	/**
	 * 请输入申请人名字
	 */
	public static JSONObject authen_name_param_error = new JSONObject(respOperationMsg(RespCode.authen_name_param_error, "请输入申请人名字."));
	/**
	 * 请输入身份证
	 */
	public static JSONObject persno_null = new JSONObject(respOperationMsg(RespCode.persno_null, "请输入身份证."));

	/**
	 * 
	 * 【推送银联订单信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 下午2:19:39
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject push_unionpay_order_into_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.push_unionpay_order_into_error, code));
	}

	/**
	 * 
	 * 【推送微信订单失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 下午2:30:31
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject push_wechatpay_order_into_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.push_wechatpay_order_into_error, code));
	}

	/**
	 * 订单不存在
	 */
	public static JSONObject order_null = new JSONObject(respOperationMsg(RespCode.order_null, "订单不存在."));
	/**
	 * 限制数量
	 */
	public static JSONObject astrict_num_param_error = new JSONObject(respOperationMsg(RespCode.astrict_num_param_error, "请输入限制数量."));
	/**
	 * 请输入优惠劵名字
	 */
	public static JSONObject coupon_name_param_error = new JSONObject(respOperationMsg(RespCode.coupon_name_param_error, "请输入优惠劵名字."));
	/**
	 * 优惠劵价格请输入0-9999范围
	 */
	public static JSONObject coupon_money_param_error = new JSONObject(respOperationMsg(RespCode.coupon_money_param_error, "优惠劵金额范围请输入0-9999."));
	/**
	 * 优惠劵创建数量范围是1-100000
	 */
	public static JSONObject coupon_creat_num_param_error = new JSONObject(respOperationMsg(RespCode.coupon_creat_num_param_error,
			"优惠劵创建数量范围是1-100000."));
	/**
	 * 请输入优惠劵发布时间与结束时间
	 */
	public static JSONObject coupon_time_param_error = new JSONObject(respOperationMsg(RespCode.coupon_time_param_error, "请输入优惠劵的发布时间与结束时间."));
	/**
	 * 优惠劵名字请控制在12个字符内
	 */
	public static JSONObject coupon_name_length_param_error = new JSONObject(respOperationMsg(RespCode.coupon_name_length_param_error,
			"优惠劵名字请控制在12个字符内."));
	/**
	 * 优惠劵开始结束时间必须大于30分钟
	 */
	public static JSONObject coupon_time_30_param_error = new JSONObject(
			respOperationMsg(RespCode.coupon_time_30_param_error, "优惠劵结束时间必须大于开始时间30分钟."));
	/**
	 * 请校准手机时间和开始时间是否正确
	 */
	public static JSONObject coupon_startTime_param_error = new JSONObject(respOperationMsg(RespCode.coupon_startTime_param_error,
			"请校准手机时间和开始时间是否正确."));
	/**
	 * 请输入优惠劵说明
	 */
	public static JSONObject coupon_explainTxt_param_error = new JSONObject(respOperationMsg(RespCode.coupon_explainTxt_param_error, "请输入优惠劵说明."));
	/**
	 * 请输入优惠劵规则
	 */
	public static JSONObject coupon_couponRules_param_error = new JSONObject(respOperationMsg(RespCode.coupon_couponRules_param_error, "请输入优惠劵规则."));
	/**
	 * 消费满
	 */
	public static JSONObject coupon_monetary_param_error = new JSONObject(
			respOperationMsg(RespCode.coupon_monetary_param_error, "消费满减免金额范围在0-999999"));
	/**
	 * 请输入优惠价值金额
	 */
	public static JSONObject coupon_favorableMoney_param_error = new JSONObject(respOperationMsg(RespCode.coupon_favorableMoney_param_error,
			"请输入优惠价值金额."));
	/**
	 * 请输入优惠赠送
	 */
	public static JSONObject coupon_favorableDonate_param_error = new JSONObject(respOperationMsg(RespCode.coupon_favorableDonate_param_error,
			"请输入优惠赠送."));
	/**
	 * 优惠金额大于劵价值
	 */
	public static JSONObject coupon_favorableMoney_gt_monetart_param_error = new JSONObject(respOperationMsg(
			RespCode.coupon_favorableMoney_gt_monetart_param_error, "优惠金额大于劵价值."));
	/**
	 * 折扣劵折扣请输入1-9
	 */
	public static JSONObject coupon_discount_favorableMoney_gt_10_lt_0_param_error = new JSONObject(respOperationMsg(
			RespCode.coupon_discount_favorableMoney_gt_10_lt_0_param_error, "折扣劵折扣请输入1-9."));
	/**
	 * 发布中的劵
	 */
	public static JSONObject coupon_publishstatus_1_or_2 = new JSONObject(respOperationMsg(RespCode.coupon_publishstatus_1_or_2, "发布中的劵."));
	/**
	 * 霸王卷减免金额0-9999
	 */
	public static JSONObject coupon_archLord_favorableMoney_param_error = new JSONObject(respOperationMsg(
			RespCode.coupon_archLord_favorableMoney_param_error, "霸王劵减免金额范围1-9999"));
	/**
	 * 发布中的优惠劵无法修改
	 */
	public static JSONObject update_coupon_publishStatus_1_or_2 = new JSONObject(respOperationMsg(RespCode.update_coupon_publishStatus_1_or_2,
			"发布中的劵无法修改."));

	/**
	 * 请上传图片或者内容
	 */
	public static JSONObject content_img_param_null = new JSONObject(respOperationMsg(RespCode.content_img_param_null, "请上传图片或者内容."));
	/**
	 * 图片超过8张
	 */
	public static JSONObject img_param_leng_gt_8 = new JSONObject(respOperationMsg(RespCode.img_param_leng_gt_8, "图片超过8张."));
	/**
	 * 发布优惠劵请选择优惠劵
	 */
	public static JSONObject publish_coupon_absObj_param_null = new JSONObject(respOperationMsg(RespCode.publish_coupon_absObj_param_null,
			"发布优惠劵请选择优惠劵."));
	/**
	 * 定位异常请重新定位
	 */
	public static JSONObject dynamic_loc_param_error = new JSONObject(respOperationMsg(RespCode.dynamic_loc_param_error, "定位异常请重新订单."));
	/**
	 * 评论内容为null
	 */
	public static JSONObject comment_content_param_null = new JSONObject(respOperationMsg(RespCode.comment_content_param_null, "请输入评论内容."));
	/**
	 * 请输入回复内容
	 */
	public static JSONObject reply_content_param_null = new JSONObject(respOperationMsg(RespCode.reply_content_param_null, "请输入回复内容."));
	/**
	 * 请输入起送费和派送费
	 */
	public static JSONObject sendOutUpFee_or_sendFee_param_null = new JSONObject(respOperationMsg(RespCode.sendOutUpFee_or_sendFee_param_null,
			"请输入起送费和派送费."));
	/**
	 * 结束时间要大于开始时间
	 */
	public static JSONObject startTime_gt_endTime = new JSONObject(respOperationMsg(RespCode.startTime_gt_endTime, "结束时间要大于开始时间."));
	/**
	 * pushCode参数为null
	 */
	public static JSONObject pushCode_param_null = new JSONObject(respOperationMsg(RespCode.pushCode_param_null, ""));
	/**
	 * 请输入银行卡名字
	 */
	public static JSONObject binding_bankName_param_null = new JSONObject(respOperationMsg(RespCode.binding_bankName_param_null, "请输入绑定银行卡名字."));
	
	/**
	 * 请输入银行卡卡号
	 */
	public static JSONObject binding_bankNum_param_null = new JSONObject(respOperationMsg(RespCode.binding_bankNum_param_null, "请输入绑定银行卡卡号."));
	
	/**
	 * 请输入银行卡卡类型
	 */
	public static JSONObject binding_bankAccountType_param_null = new JSONObject(respOperationMsg(RespCode.binding_bankNum_param_null, "请输入绑定银行卡卡类型."));
	
	/**
	 * 请输入银行地址
	 */
	public static JSONObject binding_bankAddress_param_null = new JSONObject(respOperationMsg(RespCode.binding_bankAddress_param_null, "请输入绑定银行地址."));
	/**
	 * 请输入支付密码
	 */
	public static JSONObject applyDrawing_pwd_param_null = new JSONObject(respOperationMsg(RespCode.applyDrawing_pwd_param_null, "请输入支付密码."));
	/**
	 * 请选择提现银行卡
	 */
	public static JSONObject applyDrawing_bankId_param_null = new JSONObject(respOperationMsg(RespCode.applyDrawing_bankId_param_null, "请选择提现银行卡."));
	/**
	 * 请输入提现金额
	 */
	public static JSONObject applyDrawing_price_param_null = new JSONObject(respOperationMsg(RespCode.applyDrawing_price_param_null, "请输入提现金额."));
	/**
	 * 请输入验证码
	 */
	public static JSONObject authCode_param_null = new JSONObject(respOperationMsg(RespCode.authCode_param_null, "输入验证码."));
	/**
	 * 请输入手机号码
	 */
	public static JSONObject register_phone_param_null = new JSONObject(respOperationMsg(RespCode.register_phone_param_null, "请输入手机号码."));
	/**
	 * 请输入注册密码
	 */
	public static JSONObject register_pwd_param_null = new JSONObject(respOperationMsg(RespCode.register_pwd_param_null, "请输入注册密码."));
	/**
	 * userType错误
	 */
	public static JSONObject register_userType_error = new JSONObject(respOperationMsg(RespCode.register_pwd_param_null, ""));
	/**
	 * userType错误
	 */
	public static JSONObject login_userType_error = new JSONObject(respOperationMsg(RespCode.register_pwd_param_null, ""));

	/**
	 * 请输入手机号码
	 */
	public static JSONObject login_phone_param_null = new JSONObject(respOperationMsg(RespCode.register_phone_param_null, "请输入手机号码."));
	/**
	 * 请输入注册密码
	 */
	public static JSONObject login_pwd_param_null = new JSONObject(respOperationMsg(RespCode.register_pwd_param_null, "请输入登陆密码."));
	/**
	 * 请选择头像
	 */
	public static JSONObject headimg_param_null = new JSONObject(respOperationMsg(RespCode.headimg_param_null, "请选择头像."));
	/**
	 * 请输入昵称
	 */
	public static JSONObject nickname_param_null = new JSONObject(respOperationMsg(RespCode.nickname_param_null, "请输入昵称."));
	/**
	 * 请选择性别
	 */
	public static JSONObject sex_param_error = new JSONObject(respOperationMsg(RespCode.sex_param_error, "请选择性别."));
	/**
	 * 请选择地区
	 */
	public static JSONObject region_province_id_error = new JSONObject(respOperationMsg(RespCode.region_province_id_error, "请选择地区."));
	/**
	 * 请选择服务行业
	 */
	public static JSONObject category_param_null = new JSONObject(respOperationMsg(RespCode.category_param_null, "请选择服务行业."));
	/**
	 * 请输入店铺地址
	 */
	public static JSONObject enterAddress_param_null = new JSONObject(respOperationMsg(RespCode.enterAddress_param_null, "请输入店铺地址."));
	/**
	 * tag null
	 */
	public static JSONObject perfectSellerInfo_tag_null = new JSONObject(respOperationMsg(RespCode.perfectSellerInfo_tag_null, ""));
	/**
	 * 请输入密码
	 */
	public static JSONObject pwd_param_null = new JSONObject(respOperationMsg(RespCode.pwd_param_null, "请输入密码."));
	/**
	 * 请输入密码
	 */
	public static JSONObject pwd_param_equal = new JSONObject(respOperationMsg(RespCode.pwd_param_equal, "新密码不能与旧密码相同."));
	/**
	 * 代理商手机号码不存在
	 */
	public static JSONObject agent_phone_not_exist = new JSONObject(respOperationMsg(RespCode.agent_phone_not_exist, "代理商手机号码不存在."));
	/**
	 * 密码过于简单
	 */
	public static JSONObject pwd_too_short = new JSONObject(respOperationMsg(RespCode.pwd_too_short, "密码过于简单."));
	/**
	 * 请选择你需要删除的图片
	 */
	public static JSONObject urlValue_param_null = new JSONObject(respOperationMsg(RespCode.urlValue_param_null, "请选择你需要删除的图片."));
	/**
	 * 更新地区标示错误
	 */
	public static JSONObject regionMark_error = new JSONObject(respOperationMsg(RespCode.regionMark_error, ""));
	/**
	 * 收货信息不存在可能已经删除
	 */
	public static JSONObject userReceivingInfo_null = new JSONObject(respOperationMsg(RespCode.userReceivingInfo_null, "收货信息不存在可能已经删除."));
	/**
	 * 请选择行政区
	 */
	public static JSONObject region_null = new JSONObject(respOperationMsg(RespCode.region_null, "请选择行政区."));
	/**
	 * 已经试用过店铺产品
	 */
	public static JSONObject have_to_try_pp_store = new JSONObject(respOperationMsg(RespCode.have_to_try_pp_store, "您已经试用过次产品了无法再试用了哦."));
	/**
	 * 店铺被人工关闭
	 */
	public static JSONObject seller__store_artificial_closed = new JSONObject(respOperationMsg(RespCode.seller__store_artificial_closed,
			"店铺已被后台人工关闭."));
	/**
	 * 店铺数据异常请稍后再试
	 */
	public static JSONObject seller__store_data_exception = new JSONObject(respOperationMsg(RespCode.seller__store_data_exception, "抱歉店铺数据异常请稍后再试."));
	/**
	 * 该身份证已经认证过
	 */
	public static JSONObject exist_idcard = new JSONObject(respOperationMsg(RespCode.exist_idcard, "身份证已认证."));
	/**
	 * 不支持微信支付
	 */
	public static JSONObject not_support_wechat_pay = new JSONObject(respOperationMsg(RespCode.not_support_wechat_pay, "暂时不支持微信支付."));
	/**
	 * 存在银行卡号
	 */
	public static JSONObject exist_bankcard = new JSONObject(respOperationMsg(RespCode.exist_bankcard, "存在相同银行卡号哦,刷新试试?"));
	/**
	 * 折扣点小于4
	 */
	public static JSONObject scale_lt_4 = new JSONObject(respOperationMsg(RespCode.scale_lt_4, "折扣点不能小于4哦"));
	/**
	 * 店铺不支持派送
	 */
	public static JSONObject store_issend_1 = new JSONObject(respOperationMsg(RespCode.seller_store_issend_1, "店铺不支持派送请刷新试试."));
	/**
	 * 店铺不支持到店
	 */
	public static JSONObject store_isstop_1 = new JSONObject(respOperationMsg(RespCode.seller_store_isstop_1, "店铺不支持堂食请刷新试试."));
	/**
	 * 个人账户只能周二提现
	 */
	public static JSONObject persion_only_2_drawing = new JSONObject(respOperationMsg(RespCode.persion_only_2_drawing, "只能周二提现哦."));
	/**
	 * 订单不属于未支付状态
	 */
	public static JSONObject order_unpay_1 = new JSONObject(respOperationMsg(RespCode.order_unpay_1, "该订单不能够接单请刷新试试."));
	/**
	 * 订单状态过期
	 */
	public static JSONObject seller_receiving_order_status_error = new JSONObject(respOperationMsg(RespCode.seller_receiving_order_status_error,
			"该订单状态过期请刷新试试."));
	/**
	 * 以完善过信息
	 */
	public static JSONObject has_perfect = new JSONObject(respOperationMsg(RespCode.has_perfect, "您已经完善过信息请不要重复完善."));
	/**
	 * 存在营业执照名称
	 */
	public static JSONObject exist_businesslicensename = new JSONObject(respOperationMsg(RespCode.exist_businesslicensename, "存在营业执照名称不能修改."));
	/**
	 * 字段长度过长
	 */
	public static JSONObject businessLicenseName_length_gt_30 = new JSONObject(respOperationMsg(RespCode.businessLicenseName_length_gt_30,
			"营业执照名称请尽量控制在5-30个长度."));
	/**
	 * 营业执照名称小于5个字符
	 */
	public static JSONObject businessLicenseName_length_lt_5 = new JSONObject(respOperationMsg(RespCode.businessLicenseName_length_lt_5,
			"营业执照名称请尽量控制在5-30个长度."));

	/**
	 * 
	 * 【提现金额不能小于业务需求】
	 * 
	 * @param price
	 * @return
	 * @time:2016年1月18日 上午11:39:00
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject drawing_lt_price_business(String price) {
		String str = "提现金额不能小于" + price + "";
		return new JSONObject(respOperationMsg(RespCode.drawing_lt_price_business, str));
	}

	/**
	 * 
	 * 【创建优惠卷失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 下午6:00:38
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject create_coupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.push_wechatpay_order_into_error, code));
	}

	/**
	 * 
	 * 【更新优惠劵失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 上午9:32:58
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject update_coupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.update_coupon_error, code));
	}

	/**
	 * 
	 * 【发布动态失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:00:59
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject uploadDynamic_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.uploadDynamic_error, code));
	}

	/**
	 * 
	 * 【创建或者更新优惠劵失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 下午12:40:00
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject create_or_update_coupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.update_coupon_error, code));
	}

	/**
	 * 
	 * 【获取领取记录详情失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 上午9:36:58
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject viewCouponPullRecord_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.viewCouponPullRecord_error, code));
	}

	/**
	 * 
	 * 【获取优惠劵详情失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 上午9:38:37
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject viewCouponInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.viewCouponInfo_error, code));
	}

	/**
	 * 
	 * 【创建或者修改优惠劵失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 下午6:31:33
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject creat_or_update_giftcoupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.creat_or_update_giftcoupon_error, code));
	}

	/**
	 * 
	 * 【创建或者修改霸王卷失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月25日 下午6:39:29
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject create_or_update_archlordcoupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.create_or_update_archlordcoupon_error, code));
	}

	/**
	 * 
	 * 【获取公开优惠劵失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 下午12:44:01
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getCoupon_public_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getCoupon_public_error, code));
	}

	/**
	 * 
	 * 【创建或者修改折扣劵失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 上午9:17:48
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject create_or_update_discountcoupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.create_or_update_discountcoupon_error, code));
	}

	/**
	 * 
	 * 【领取优惠劵失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 下午12:07:56
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject pull_coupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.pull_coupon_error, code));
	}

	/**
	 * 
	 * 【我的优惠劵获取失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 下午12:10:01
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject myCoupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.myCoupon_error, code));
	}

	/**
	 * 
	 * 【使用经纬度错误】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 下午12:29:24
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject useCoupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.useCoupon_error, code));
	}

	/**
	 * 
	 * 【领取使用详情失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 下午12:31:18
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getCouponRecordInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getCouponRecordInfo_error, code));
	}

	/**
	 * 
	 * 【更新店铺失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:10:09
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject updateStore_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.updateStore_error, code));
	}

	/**
	 * 
	 * 【开通店铺失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:12:25
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject openStore_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.openStore_error, code));
	}

	/**
	 * 
	 * 【撤销优惠劵失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月26日 下午12:32:55
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject undoCoupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getCouponRecordInfo_error, code));
	}

	/**
	 * 
	 * 【获取附近商家动态失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:05:45
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getNearSellerLastDynamic_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getNearSellerLastDynamic_error, code));
	}

	/**
	 * 
	 * 【获取动态圈失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:07:26
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getDynamicCircle_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getDynamicCircle_error, code));
	}

	/**
	 * 
	 * 【查询动态失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:10:42
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject queryDynamic_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.queryDynamic_error, code));
	}

	/**
	 * 
	 * 【回复失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:28:34
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject reply_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.reply_error, code));
	}

	/**
	 * 
	 * 【点赞失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:32:38
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject praise_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.praise_error, code));
	}

	/**
	 * 
	 * 【获取动态评论失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:34:39
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getComment_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getComment_error, code));
	}

	/**
	 * 
	 * 【获点赞列表失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:39:34
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getPraise_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getPraise_error, code));
	}

	/**
	 * 
	 * 【去除点赞失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:42:15
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject removePraise_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.removePraise_error, code));
	}

	/**
	 * 
	 * 【删除评论失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:48:33
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject removeComment_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.removeComment_error, code));
	}

	/**
	 * 
	 * 【获取动态统计失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:50:54
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getDynamicCount_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getDynamicCount_error, code));
	}

	/**
	 * 
	 * 【删除分类失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:52:59
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject delCategory_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.delCategory_error, code));
	}

	/**
	 * 
	 * 【获取商品分类列表失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:55:39
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getCategoryList_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getCategoryList_error, code));
	}

	/**
	 * 添加产品失败
	 */
	public static JSONObject addGoods_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.addGoods_error, code));
	}

	/**
	 * 更新产品失败
	 */
	public static JSONObject updateGoods_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.updateGoods_error, code));
	}

	/**
	 * 
	 * 【主页留言失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:13:37
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject subLeaveWord_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.subLeaveWord_error, code));
	}

	/**
	 * 
	 * 【提交app留言失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:15:29
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject subAppLeaveWord_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.subAppLeaveWord_error, code));
	}

	/**
	 * 
	 * 【提交订单失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:18:30
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject subOrder_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.subOrder_error, code));
	}

	/**
	 * 
	 * 【获取可用优惠卷信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:19:40
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getUsableCoupon_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getUsableCoupon_error, code));
	}

	/**
	 * 
	 * 【计算订单价格失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:20:50
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject calculateOrderPrice_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.calculateOrderPrice_error, code));
	}

	/**
	 * 
	 * 【查询订单列表失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:22:00
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getOrderList_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getOrderList_error, code));
	}

	/**
	 * 
	 * 【接单失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:23:09
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject receivingOrder_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.receivingOrder_error, code));
	}

	/**
	 * 
	 * 【拒绝接单失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:35:49
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject refusedOrder_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.refusedOrder_error, code));
	}

	/**
	 * 
	 * 【取消订单失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:36:47
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject cancelOrder_error() {
		return new JSONObject(respOperationMsg(RespCode.cancelOrder_error, "取消订单失败"));
	}

	/**
	 * 
	 * 【查询订单状态出错】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:40:24
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getOrderLog_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getOrderLog_error, code));
	}

	/**
	 * 
	 * 【确认消费失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:45:39
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject confirmOrder_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.confirmOrder_error, code));
	}

	/**
	 * 
	 * 【申请退款失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:47:11
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject applyRefund_error() {
		return new JSONObject(respOperationMsg(RespCode.applyRefund_error, "申请退款失败"));
	}
	
	/**
	 * 
	 * 【确认收货失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:47:11
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject confirmReceipt_error() {
		return new JSONObject(respOperationMsg(RespCode.confirmReceipt_error, "确认收货失败"));
	}

	/**
	 * 
	 * 【同意退款失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:48:29
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject agreedRefund_error() {
		return new JSONObject(respOperationMsg(RespCode.agreedRefund_error, "同意退款失败"));
	}
	
	/**
	 * 
	 * 【拒绝退款失败】
	 * 
	 * @param code
	 * @return
	 * @time:2016年6月20日
	 * @author yangwei
	 * @version
	 */
	public static JSONObject refusedRefund_error() {
		return new JSONObject(respOperationMsg(RespCode.refusedRefund_error, "拒绝退款失败"));
	}
	
	/**
	 * 
	 * 【添加仲裁失败】
	 * 
	 * @param code
	 * @return
	 * @time:2016年6月20日
	 * @author yangwei
	 * @version
	 */
	public static JSONObject arbitration_error() {
		return new JSONObject(respOperationMsg(RespCode.arbitration_error, "添加仲裁失败"));
	}

	/**
	 * 
	 * 【查询订单支付状态失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:51:21
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject queryOrderPaymentStatus_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.queryOrderPaymentStatus_error, code));
	}

	/**
	 * 
	 * 【获取订单商品失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:50:51
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getOrderGoodsList_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getOrderGoodsList_error, code));
	}

	/**
	 * 
	 * 【获取产品详情失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:01:38
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getGoodsInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getGoodsInfo_error, code));
	}

	/**
	 * 
	 * 【创建或者更新分类失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:54:24
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject createOrUpdateCategory_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.createOrUpdateCategory_error, code));
	}

	/**
	 * 
	 * 【删除动态失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午10:45:42
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject removeDynamic_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.removeDynamic_error, code));
	}

	/**
	 * 
	 * 【查询订单数量失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:53:10
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getOrderCount_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getOrderCount_error, code));
	}

	/**
	 * 
	 * 【提交店铺订单失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:56:23
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject subSellerStoreOrder_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.subSellerStoreOrder_error, code));
	}

	/**
	 * 
	 * 【获取店铺信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:04:16
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getStoreInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getStoreInfo_error, code));
	}

	/**
	 * 
	 * 【获取我的店铺失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:05:08
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject myStore_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.myStore_error, code));
	}

	/**
	 * 
	 * 【支付年费失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 上午11:55:07
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getPpSellerStoreService_errors(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getPpSellerStoreService_errors, code));
	}

	/**
	 * 
	 * 【获取下级标签失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:19:16
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getNextCategory_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getNextCategory_error, code));
	}

	/**
	 * 
	 * 【获取商家店铺分类失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:20:42
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getStoreCategory_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getStoreCategory_error, code));
	}

	/**
	 * 
	 * 【提交推送code失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:24:35
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject subPushCode_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.subPushCode_error, code));
	}

	/**
	 * 
	 * 【获取融云token失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:26:08
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject rtoken_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.rtoken_error, code));
	}

	/**
	 * 
	 * 【获取支付配置失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:27:43
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getPayParameter_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getPayParameter_error, code));
	}

	/**
	 * 
	 * 【获取主页数据失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:28:46
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getIndexDate_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getIndexDate_error, code));
	}

	/**
	 * 
	 * 【获取广告数据失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:29:35
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getAdvertDate_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getAdvertDate_error, code));
	}

	/**
	 * 
	 * 【获取默认头像失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:30:44
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getDefaultHeadImg_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getDefaultHeadImg_error, code));
	}

	/**
	 * 
	 * 【获取折扣店失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:31:36
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getScale_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getScale_error, code));
	}

	/**
	 * 
	 * 【获取银行卡号失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:32:29
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getBankList_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getBankList_error, code));
	}

	/**
	 * 
	 * 【发送支付密码短信失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:33:59
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject sendPayPwdCode_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.sendPayPwdCode_error, code));
	}

	/**
	 * 
	 * 【设置支付密码失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:35:10
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject setPayPwd_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.setPayPwd_error, code));
	}

	/**
	 * 
	 * 【获取钱包信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:36:04
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getWallet_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getWallet_error, code));
	}

	/**
	 * 
	 * 【绑定银行卡失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:37:22
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject bindingBank_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.bindingBank_error, code));
	}

	/**
	 * 
	 * 【删除绑定银行卡失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午1:40:30
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject delBindingBank_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.delBindingBank_error, code));
	}

	/**
	 * 
	 * 【提现申请失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午2:03:46
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject applyDrawing_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.applyDrawing_error, code));
	}

	/**
	 * 
	 * 【付款失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午2:10:16
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject payment_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.payment_error, code));
	}

	/**
	 * 
	 * 【我的邀请】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午2:56:59
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getMyInvitation_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getMyInvitation_error, code));
	}

	/**
	 * 
	 * 【获取用户信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午2:59:06
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getUser_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getUser_error, code));
	}

	/**
	 * 
	 * 【获取自己信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午3:00:35
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getMyUser_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getMyUser_error, code));
	}

	/**
	 * 
	 * 【批量获取用户信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午3:12:04
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getUsers_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getUsers_error, code));
	}

	/**
	 * 
	 * 【获取主页附近商家失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午3:17:32
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getIndexUser_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getIndexUser_error, code));
	}

	/**
	 * 
	 * 【移除关注失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午3:27:56
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject removeFollower_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.removeFollower_error, code));
	}

	/**
	 * 
	 * 【获取好友列表失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午3:31:22
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getFollowers_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.removeFollower_error, code));
	}

	/**
	 * 
	 * 【上传通讯录失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午3:52:16
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject addAddressbook_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.addAddressbook_error, code));
	}

	/**
	 * 
	 * 【完善个人信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午3:57:11
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject perfectPersonInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.perfectPersonInfo_error, code));
	}

	/**
	 * 
	 * 【完善商家信息2失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:23:54
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject perfectSellerInfo2_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.perfectSellerInfo2_error, code));
	}

	/**
	 * 
	 * 【完善商家信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:22:21
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject perfectSellerInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.perfectSellerInfo_error, code));
	}

	/**
	 * 
	 * 【完善个人信息2失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:05:14
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject perfectPersonInfo2_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.perfectPersonInfo2_error, code));
	}

	/**
	 * 
	 * 【重置密码失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:30:49
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject retrievePassword_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.retrievePassword_error, code));
	}

	/**
	 * 
	 * 【修改密码失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:32:15
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject alterPassword_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.alterPassword_error, code));
	}

	/**
	 * 
	 * 【上传背景图片失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:33:13
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject uploadBackgroundImg_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.uploadBackgroundImg_error, code));
	}

	/**
	 * 
	 * 【删除失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:36:00
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject delBackGroundImg_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.delBackGroundImg_error, code));
	}

	/**
	 * 
	 * 【获取关注粉丝相册统计失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:37:21
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getFollowerFansPhototsCount_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getFollowerFansPhototsCount_error, code));
	}

	/**
	 * 
	 * 【更新个人信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:38:52
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject updatePersonal_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.updatePersonal_error, code));
	}

	/**
	 * 
	 * 【更新商家信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:48:24
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject updateSeller_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.updateSeller_error, code));
	}

	/**
	 * 
	 * 【获取附近个人用户失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:55:16
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getNearPersion_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getNearPersion_error, code));
	}

	/**
	 * 
	 * 【获取我的客户失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:56:39
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject myCustomer_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.myCustomer_error, code));
	}

	/**
	 * 
	 * 【获取收货信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:57:51
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getReceivingInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getReceivingInfo_error, code));
	}

	/**
	 * 
	 * 【添加或者更新收货信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午4:59:42
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject addOrUpdateReceivingInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.addOrUpdateReceivingInfo_error, code));
	}

	/**
	 * 
	 * 【移除收货信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午5:02:46
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject removeReceivingInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.removeReceivingInfo_error, code));
	}

	/**
	 * 
	 * 【我的团队失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午5:03:50
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getMyTeam_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getMyTeam_error, code));
	}

	/**
	 * 
	 * 【我的团队详细用户信息失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午5:04:59
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getMyTeamInfo_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getMyTeamInfo_error, code));
	}

	/**
	 * 
	 * 【添加我的邀请人失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午5:06:31
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject addVistUserCode_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.addVistUserCode_error, code));
	}

	/**
	 * 
	 * 【获取我的邀请人失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午5:07:49
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getByViscode_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getByViscode_error, code));
	}

	/**
	 * 
	 * 【getUserWeb失败】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月28日 下午5:25:37
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject getUser_web_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getUser_web_error, code));
	}

	/**
	 * 
	 * 【查找金额流水】
	 * 
	 * @param code
	 * @return
	 * @time:2015年12月31日 下午3:15:38
	 * @author 涂鑫
	 * @version
	 */
	public static JSONObject findMoneyLogsByDay(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.findMoneyLogsByDay, code));
	}


	/**
	 * 
	 * 【发送业务消息】
	 * 
	 * @param code
	 * @param msg
	 * @return
	 * @time:2015年12月24日 上午9:25:51
	 * @author 涂鑫
	 * @version
	 */
	public static Map<String, Object> respOperationMsg(int code, String msg) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("msg", msg);
		return map;
	}

	/**
	 * 
	 * 【发送错误code码】
	 * 
	 * @param code
	 * @param msg
	 * @return
	 * @time:2015年12月24日 上午9:26:08
	 * @author 涂鑫
	 * @version
	 */
	public static Map<String, Object> respErrorMsg(int code, Integer code2) {
		if (code2.intValue() == 0) {
			code2 = null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		if (code2 != null) {
			map.put("msg", "服务器繁忙,请稍后再试" + "(" + Integer.parseInt(code + "" + code2) + ")");
		} else {
			map.put("msg", "服务器繁忙,请稍后再试" + "(" + code + ")");
		}

		return map;
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	// 票据不能为空
	public static final JSONObject token_cannot_null = new JSONObject(respOperationMsg(RespCode.token_cannot_null, "票据不能为空."));
	// 钱包对账中ID不能为空
	public static final JSONObject recon_record_id_cannot_null = new JSONObject(respOperationMsg(RespCode.token_cannot_null, "钱包对账中ID不能为空."));
	//
	public static final JSONObject extract_apply_date_cannot_null = new JSONObject(respOperationMsg(RespCode.token_cannot_null, "提现日期不能为空."));
	// 获取用户钱包失败
	public static final JSONObject get_user_wallet_fail = new JSONObject(respOperationMsg(RespCode.get_user_wallet_fail, "获取用户钱包失败."));
	// 获取用户钱包数据失败
	public static final JSONObject get_user_wallet_msg_fail = new JSONObject(respOperationMsg(RespCode.get_user_wallet_msg_fail, "获取用户钱包数据失败."));
	//代理商提现金额错误
	public static final JSONObject wrong_withdraw_money = new JSONObject(respOperationMsg(RespCode.wrong_withdraw_money, "请输入正确的提现金额."));
	//代理商只能每月15号提现
	public static final JSONObject wrong_date_agent_withdraw = new JSONObject(respOperationMsg(RespCode.wrong_date_agent_withdraw, "代理商只能每月15号提现."));
	//代理商提现时间必须在当天17点之前
	public static final JSONObject wrong_hour_agent_withdraw = new JSONObject(respOperationMsg(RespCode.wrong_hour_agent_withdraw, "代理商提现时间必须在当天17点之前."));

	/**
	 * 订单已完成，不能申请退款
	 */
	public static final JSONObject ORDERFINISHED_REJECT_REIM = new JSONObject(respOperationMsg(RespCode.order_finished_reject_reim, "订单已完成，不能申请退款."));
	
	
	//dhf
	/////////////////////////////////////////////////////////////////
	/**
	 * 获取商品分类失败
	 */
	public static final JSONObject goods_type_get_fail = new JSONObject(respOperationMsg(RespCode.goods_type_get_fail, "获取商品分类失败."));
	/**
	 * 添加商品分类失败
	 */
	public static final JSONObject add_goods_type_fail = new JSONObject(respOperationMsg(RespCode.add_goods_type_fail, "添加商品分类失败."));
	/**
	 * 修改商品分类失败
	 */
	public static final JSONObject mod_goods_type_fail = new JSONObject(respOperationMsg(RespCode.mod_goods_type_fail, "修改商品分类信息失败."));
	/**
	 * 获取文件上传签名失败
	 */
	public static final JSONObject get_file_upload_sign_fail = new JSONObject(respOperationMsg(RespCode.get_file_upload_sign_fail, "获取文件上传签名失败."));
	/**
	 * 记录不存在
	 */
	public static final JSONObject goods_type_not_exsist = new JSONObject(respOperationMsg(RespCode.goods_type_not_exsist, "获取文件上传签名失败."));
	/**
	 * 添加商品失败
	 */
	public static final JSONObject add_goods_fail = new JSONObject(respOperationMsg(RespCode.add_goods_fail, "添加商品失败."));
	/** 编辑商品失败
	 */
	public static final JSONObject edit_goods_fail = new JSONObject(respOperationMsg(RespCode.edit_goods_fail, "编辑商品失败."));
	/**
	 * 获取商品信息失败
	 */
	public static final JSONObject get_goods_type_fail = new JSONObject(respOperationMsg(RespCode.get_goods_type_fail, "获取商品信息失败"));
	
	
	//sm-wallet
	//////////////////////////////////////////////////////////////////////////


	//sm-shop
	/**
	 * 查询便利店营业信息失败
	 */
	public static final JSONObject shop_business_query_id = new JSONObject(respOperationMsg(RespCode.getStoreInfo_error, "查询便利店营业信息失败."));
	/**
	 * 便利店营业信息已存在
	 */
	public static final JSONObject shop_business_have = new JSONObject(respOperationMsg(RespCode.shop_business_have, "便利店营业信息已存在."));
	/**
	 * 便利店营业信息不存在
	 */
	public static final JSONObject shop_business_not_have = new JSONObject(respOperationMsg(RespCode.shop_business_not_have, "便利店营业信息不存在."));

	/**
	 * 便利店ID为空
	 */
	public static final JSONObject shop_id_is_null = new JSONObject(respOperationMsg(RespCode.shop_id_is_null, "便利店ID为空."));

	/**
	 * 便利店名称为空
	 */
	public static final JSONObject shop_name_is_null = new JSONObject(respOperationMsg(RespCode.shop_name_is_null, "便利店名称为空."));

	/**
	 * 便利店手机号码为空
	 */
	public static final JSONObject shop_phone_is_null = new JSONObject(respOperationMsg(RespCode.shop_phone_is_null, "便利店手机号码为空."));

	/**
	 * 便利店手机号码格式不对
	 */
	public static final JSONObject shop_phone_fomat_error = new JSONObject(respOperationMsg(RespCode.shop_phone_fomat_error, "便利店手机号码格式不对."));

	/**
	 * 便利店分类名称为空
	 */
	public static final JSONObject shop_category_name_is_null = new JSONObject(respOperationMsg(RespCode.shop_category_name_is_nul, "便利店分类名称为空."));

	/**
	 * 便利店营业信息ID为空
	 */
	public static final JSONObject shop_business_id_is_null = new JSONObject(respOperationMsg(RespCode.shop_business_id_is_null, "便利店营业信息ID为空."));
	
	/**
	 * 便利店名称已存在
	 */
	public static final JSONObject shop_name_have = new JSONObject(respOperationMsg(RespCode.shop_name_have, "便利店名称已存在."));
	
	/**
	 * 便利店分类名称已存在
	 */
	public static final JSONObject shop_category_name_have = new JSONObject(respOperationMsg(RespCode.shop_name_have, "便利店分类名称已存在."));
	
	
	//shopManage
	/**
	 * 便利店管理公司分成点已存在
	 */
	public static final JSONObject shop_manage_split_point_not_null = new JSONObject(respOperationMsg(RespCode.shop_manage_split_point_not_null, "便利店管理公司分成点已存在."));
	/**
	 * 便利店管理公司名称已存在
	 */
	public static final JSONObject shop_manage_name_have = new JSONObject(respOperationMsg(RespCode.shop_manage_name_have, "便利店管理公司名称已存在."));
	
	/**
	 * 便利店管理公司运营分类名称已存在
	 */
	public static final JSONObject shop_manage_category_name_have = new JSONObject(respOperationMsg(RespCode.shop_manage_category_name_have, "运营分类名称已存在."));
	
	
	//supermarket
	/**
	 * 名称已经存在
	 */
	public static final JSONObject supermarket_category_name_have = new JSONObject(respOperationMsg(RespCode.supermarket_category_name_have, "名称已经存在."));
	/**
	 * 已有其他分类关联
	 */
	public static final JSONObject supermarket_category_id_have = new JSONObject(respOperationMsg(RespCode.supermarket_category_id_have, "已有其他分类关联."));
	/**
	 * 超市名称已存在
	 */
	public static final JSONObject supermarket_name_have = new JSONObject(respOperationMsg(RespCode.supermarket_name_have, "超市名称已存在."));
	/**
	 * 查询超市营业信息失败
	 */
	public static final JSONObject supermarket_business_query_id = new JSONObject(respOperationMsg(RespCode.getStoreInfo_error, "查询超市营业信息失败."));
	/**
	 * 超市营业信息已存在
	 */
	public static final JSONObject supermarket_business_have = new JSONObject(respOperationMsg(RespCode.supermarket_business_have, "超市营业信息已存在."));
	
	/**查询商品二级商品分类下商品信息失败*/
	public static final JSONObject find_second_category_goods_fail = new JSONObject(respOperationMsg(RespCode.find_second_category_goods_fail, "超市营业信息已存在."));
     
    /**查询二级商品分类列表失败*/
	public static final JSONObject find_second_category_fail = new JSONObject(respOperationMsg(RespCode.find_second_category_fail, "超市营业信息已存在."));
	/**
	 * 
	 * 【获取收入流水失败】
	 * 
	 * @param code
	 * @return
	 * @time:2016年4月28日 下午1:32:29
	 * @author 徐云贤
	 * @version
	 */
	public static JSONObject getIncomeList_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getIncomeList_error, code));
	}

	/**
	 * 
	 * 【获取收入流水失败】
	 * 
	 * @param code
	 * @return
	 * @time:2016年4月28日 下午1:32:29
	 * @author 徐云贤
	 * @version
	 */

	public static JSONObject getWithdrawals_error(Integer code) {
		return new JSONObject(respErrorMsg(RespCode.getWithdrawalsList_error, code));
	}

	/**
	 * 数据未找到
	 */
	public static final JSONObject not_found = new JSONObject(respOperationMsg(RespCode._404, "数据为找到."));
	
	/**
	 * 数据类型不匹配
	 */
	public static final JSONObject not_found_type = new JSONObject(respOperationMsg(RespCode._404, "数据类型不匹配."));
	
	
	/**
	 * 仓库名称存在
	 */
	public static final JSONObject ycc_name_exist = new JSONObject(respOperationMsg(RespCode.ycc_name_exist, "仓库名称存在"));
	
	/**
	 * 仓库名称存在
	 */
	public static final JSONObject ycc_addRess_not_exist = new JSONObject(respOperationMsg(RespCode.ycc_addRess_not_exist, "仓储信息绑定的仓库地址不存在"));
	
	/**
     *  仓储中心id不存在 
     */
    public static final JSONObject cczx_id_not_exist = new JSONObject(respOperationMsg(RespCode.cczx_id_not_exist, "仓储中心id不存在"));
    
	
	
	/**
	 * 商品模块
	 * @author Administrator
	 *
	 */
	public static class Goods{
		/**商品不存在*/
		public static final JSONObject gd_goods_not_found = new JSONObject(respOperationMsg(RespCode.gd_goods_not_found, "商品不存在"));
		/**获取商品分类属性失败*/
		public static final JSONObject gd_get_prop_config_fail = new JSONObject(respOperationMsg(RespCode.gd_get_prop_config_fail, "获取商品分类属性失败"));
		/**上传图片失败*/
		public static final JSONObject gd_upload_img_fail = new JSONObject(respOperationMsg(RespCode.gd_upload_img_fail, "上传图片失败"));
		/** 编辑商品，上行JSON错误 */
		public static final JSONObject gd_goods_edit_json_error = new JSONObject(respOperationMsg(RespCode.gd_goods_edit_json_error, "编辑商品，上行JSON错误"));
		/**编辑商品，SKU属性值组合已经存在*/
		public static final JSONObject gd_goods_edit_sku_combine_exsit = new JSONObject(respOperationMsg(RespCode.gd_goods_edit_sku_combine_exsit, "编辑商品，上行JSON错误"));
	}
	
	/**
	 * 钱包模块
	*/
	
	//源单ID不存在
	public static final JSONObject wallet_sourceId_not_exist = new JSONObject(respOperationMsg(RespCode.wallet_sourceId_not_exist, "源单ID不存在"));
	
	//提现金额不能为空
	public static final JSONObject wallet_amount_not_exist = new JSONObject(respOperationMsg(RespCode.wallet_amount_not_exist, "提现金额不能为空"));
		
	//提现银行卡号不能为空
	public static final JSONObject wallet_bankCardId_not_exist = new JSONObject(respOperationMsg(RespCode.wallet_bankCardId_not_exist, "提现银行卡号不能为空"));
	
	//便利店Id不能为空
	public static final JSONObject wallet_bldId_not_exist = new JSONObject(respOperationMsg(RespCode.wallet_bldId_not_exist, "便利店Id不能为空"));
	
	//便利店分润溢价不能为空
	public static final JSONObject wallet_bldrate_not_exist = new JSONObject(respOperationMsg(RespCode.wallet_bldrate_not_exist, "便利店分润溢价不能为空"));
		
	//组织Id不能为空
	public static final JSONObject wallet_groupId_not_exist = new JSONObject(respOperationMsg(RespCode.wallet_groupId_not_exist, "组织Id不能为空"));
	
	/**
	 * 对私账号持卡人必须为法人
	 */
	public static JSONObject binding_bankName_lpname_null = new JSONObject(respOperationMsg(RespCode.binding_bankName_lpname_null, "对私账号持卡人必须为法人"));
	
	
	/**
	 * 对公账号持卡人必须为公司名称
	 */
	public static JSONObject binding_bankName_companyName_null = new JSONObject(respOperationMsg(RespCode.binding_bankName_companyName_null, "对公账号持卡人必须为公司名称"));
	
	
	/**
	 * 每个用户只能绑定一张银行卡
	 */
	public static JSONObject WTAA_USER_BAKE = new JSONObject(respOperationMsg(RespCode.wtll_user_bake, "每个用户只能绑定一张银行卡"));
	/**
	 * 存在银行卡号
	 */
	public static JSONObject EXIST_BANKCARD = new JSONObject(respOperationMsg(RespCode.exist_bankcard, "存在银行卡号"));
	
	/**
	 * 没有绑定银行卡
	 */
	public static final JSONObject EXIST_BING_BANKCARD = new JSONObject(respOperationMsg(RespCode.exist_bing_bankcard, "没有找到银行卡"));
	
	
	/**
	 * 该银行卡不属于该组织请重新绑定银行卡
	 */
	public static final JSONObject NOT_BELONG_BING_BANKCARD = new JSONObject(respOperationMsg(RespCode.not_belong_bing_bankcard, "该银行卡不属于该组织请重新绑定银行卡"));
	
	
	/**
	 * 钱包没设定
	 */
	public static final JSONObject WALL_NOT_SET = new JSONObject(respOperationMsg(RespCode.wall_not_set, "钱包没设定"));


	/**
	 * 钱包没初始化
	 */
	public static final JSONObject WALL_NOT_INITIALIZED = new JSONObject(respOperationMsg(RespCode.wall_not_initialized, "钱包没初始化"));
	
	/**
	 * 没有发现该申请！
	 */
	public static final JSONObject NOT_PRESENT_APPLICATION = new JSONObject(respOperationMsg(RespCode.not_present_application, "没有发现该申请！"));

	
	/**
	 * 只能对没提现的申请操作
	 */
	public static final JSONObject NOT_TO_MENTION_PRESENT = new JSONObject(respOperationMsg(RespCode.not_to_mention_present, "只能对没提现的申请操作！"));
	
	/**
	 *请同意提现或者不同意提现！
	 */
	public static final JSONObject CONSENT_TO_WITHDRAWAL = new JSONObject(respOperationMsg(RespCode.consent_to_withdrawal, "只能执行同意提现操作！"));

	
	/**
	 *只能对已提现的申请操作！
	 */
	public static final JSONObject ALREADY_TO_MENTION_PRESENT = new JSONObject(respOperationMsg(RespCode.already_to_mention_present, "只能对已提现的申请操作！"));

	
	/**
	 *只能执行驳回操作！
	 */
	public static final JSONObject TO_MENTION_BH_PRESENT = new JSONObject(respOperationMsg(RespCode.to_mention_bh_present, "只能执行驳回操作"));

	
	/**
	 *只能对已驳回的申请操作！
	 */
	public static final JSONObject ALREADY_TO_BH_PRESENT = new JSONObject(respOperationMsg(RespCode.already_to_bh_present, "只能对已驳回的申请操作！"));

	
	/**
	 *只能执行追回操作！
	 */
	public static final JSONObject TO_MENTION_ZH_PRESENT = new JSONObject(respOperationMsg(RespCode.to_mention_zh_present, "只能执行追回操作！"));

		
	/**
	 * 广告模块
	 */
	 public static class Advertisement{
	    /**广告位不存在*/
	    public static final JSONObject advertisement_not_found= new JSONObject(respOperationMsg(RespCode.advertisement_not_found, "广告位不存在"));
	} 
	 
	/**	添加举报信息异常 */
	public static final JSONObject addReport_error = new JSONObject(respOperationMsg(RespCode.addReport_error, "添加举报信息异常"));
	/**	获取举报信息异常 */
	public static final JSONObject getReport_error = new JSONObject(respOperationMsg(RespCode.getReport_error, "获取举报信息异常"));
	/**	删除举报信息异常 */
	public static final JSONObject delReport_error = new JSONObject(respOperationMsg(RespCode.delReport_error, "删除举报信息异常"));
	
	/**	添加评论信息异常 */
	public static final JSONObject addComment_error = new JSONObject(respOperationMsg(RespCode.addComment_error, "添加评论信息异常"));
	/**	添加评论信息异常 */
	public static final JSONObject getComment_error = new JSONObject(respOperationMsg(RespCode.getComment_error, "获取评论信息异常"));
	/**	删除评论信息异常 */
	public static final JSONObject removeComment_error = new JSONObject(respOperationMsg(RespCode.removeComment_error, "删除评论信息异常"));
	
	/**	添加批发单异常 */
	public static final JSONObject add_batOrder_error = new JSONObject(respOperationMsg(RespCode.add_batOrder_error, "添加批发单异常 "));
	/**	获取批发单列表异常 */
	public static final JSONObject get_batOrder_list_error = new JSONObject(respOperationMsg(RespCode.get_batOrder_list_error, "获取批发单列表异常 "));
	
	/**	添加便利店销量信息异常 */
	public static final JSONObject addSales_error = new JSONObject(respOperationMsg(RespCode.addSales_error, "添加便利店销量信息异常 "));
	/**	统计便利店销量信息异常 */
	public static final JSONObject countSales_error = new JSONObject(respOperationMsg(RespCode.countSales_error, "统计便利店销量信息异常 "));
	
	/**	非法参数异常  */
	public static final JSONObject illegalParameter_error = new JSONObject(respOperationMsg(RespCode.illegalParameter_error, "非法参数异常 "));

	/**
	 * 钱包相关
	 * 
	 * @author 肖来龙 lois siau
	 *
	 */
	public static class Wallet {
		public static final JSONObject DISTRIBUTE_INCOME_FAIL = new JSONObject(respOperationMsg(RespCode.wtallet_distriute_income_fail, "分润失败"));
		public static final JSONObject UNDISTRIBUTE_INCOME_FAIL = new JSONObject(respOperationMsg(RespCode.wtallet_undistriute_income_fail, "取消分润失败"));
	}
}
