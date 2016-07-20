package com.mall.core.exception;


public class ExceptionCode {
	/**
	 * 空异常
	 */
	public static final int NULL_EXCEPTION=1;
	/**
	 * 用户空异常
	 */
	public static final int USER_NULL_EXCEPTION=2;
	/**
	 *  商品分类空异常
	 */
	public static final int GOODSCATEGORY_NULL_EXCEPTION=3;
	/**
	 * 删除分类存在商品
	 */
	public static final int DEL_GOODSCATEGORY_EXIST_PRODUCT=4;
	/**
	 * 商家店铺错误  用户对应错误
	 */
	public static final int SELLER_STORE_ERROR1=5;
	/**
	 * 商品发布人错误
	 */
	public static final int GOODS_PUBLISH_USER_ERROR=6;
	/**
	 * 用户类型不匹配
	 */
	public static final int USERTYPE_DONT_MATCH=7;
	/**
	 * 用户没有认证
	 */
	public static final int USER_NO_AUTHEN=8;
	/**
	 * 商家没有开通店铺
	 */
	public static final int SELLER_NO_OPEN_STORE=9;
	/**
	 * 订单提交有误
	 */
	public static final int ORDER_SUB_ERROR=10;
	/**
	 * 用户无此优惠卷
	 */
	public static final int USER_NO_SUCH_COUPON=11;
	/**
	 * 优惠卷未达到使用规则
	 */
	public static final int COUPON_NOT_TO_USE_RULES=12;
	/**
	 * 收货地址 status 错误
	 */
	public static final int RECEIVINGINFO_STATUS_ERROR=13;
	/**
	 * 订单已支付
	 */
	public static final int ORDER_PAY_ERROR=14;
	/**
	 * 订单不存在
	 */
	public static final int ORDER_INEXISTENCE=15;
	/**
	 * 存在相同有效分类
	 */
	public static final int EXIST_VALID_GOODS_CATEGORY_NAME=16;
	/**
	 * 金额错误
	 */
	public static final int PRICE_ERROR=17;
	/**
	 * 提交订单有误	商家不允许下单
	 */
	public static final int ORDER_SUB_SELLER_ERROR=18;
	/**
	 * 商品下架
	 */
	public static final int	GOODS_DOWN=19;
	/**
	 * 订单小于起送价格
	 */
	public static final int ORDER_LT_SENDFEE=20;
	/**
	 * 订单商家所属不正确
	 */
	public static final int ORDER_SELLER_ERROR=21;
	
	
	/**
	 * 订单未支付
	 */
	public static final int ORDER_PAY_ERROR_1=22;
	/**
	 * 订单接单状态有误
	 */
	public static final int SELLER_RECEIVING_ORDER_STATUS_ERROR=23;
	/**
	 * 订单下单用户不正确
	 */
	public static final int ORDER_USER_FALSENESS=24;
	/**
	 * 店铺存在 并且有效
	 */
	public static final int SELLER_STORE_EXIST_VALID=25;
	/**
	 * 钱包未开通
	 */
	public static final int WALLET_DID_NOT__OPEN=26;
	/**
	 * 验证码错误
	 */
	public static final int VERIFICATION_CODE_ERROR=27;
	/**
	 * 不支持此银行
	 */
	public static final int DOES_NOT_SUPPORT_THE_BANK=28;
	/**
	 * 余额不足
	 */
	public static final int NOT_SUFFICIENT_FUNDS=29;
	/**
	 * 钱包开通用户错误
	 */
	public static final int WALLET_ERROR_1=30;
	/**
	 * 支付密码错误
	 */
	public static final int WALLET_PAY_PWD_ERROR=31;
	/**
	 * 不能退款
	 */
	public static final int NO_REFUND=32;
	/**
	 * 订单状态错误
	 */
	public static final int ORDER_STATUS_ERROR=33;
	/**
	 * 支付密码没有设置
	 */
	public static final int WALLET_PAY_PWD_NULL_ERROR=34;
	/**
	 * 该订单状态不能取消
	 */
	public static final int ORDERS_STATUS_CANNOT_BE_CANCELLED=35;
	/**
	 * 登陆类型错误 请登陆个人版应用
	 */
	public static final int LOGIN_USER_TYPE_ERROR_1=36;
	/**
	 * 登陆类型错误 请登录商家版应用
	 */
	public static final int LOGIN_USER_TYPE_ERROR_2=37;
	/**
	 * 存在提现申请
	 */
	public static final int EXIST_WITHDRAWAL_APPLY=38;
	/**
	 * 店铺停止停业中 
	 */
	public static final int STORE_CLOSE_BUSINESS=39;
	/**
	 * 不支持到店
	 */
	public static final int STORE_ISSTOP_1=40;
	/**
	 * 不支持派送
	 */
	public static final int STORE_ISSEND_1=41;
	/**
	 * 店铺更新错误 到店和派送至少支持一个
	 */
	public static final int STORE_UPDATE_ERROR=42;
	
	/**
	 * 折扣点业务参数错误
	 */
	public static final int scale_param_error=43;
	/**
	 * 错误的预约时间
	 */
	public static final int ERROR_MAKETIME=44;
	/**
	 * 活动已领取完毕
	 */
	public static final int ACT_NO_AWARD_INEXISTENCE=45;
	/**
	 * 活动已经过期	
	 */
	public static final int ACT_PASS_INEXISTENCE=46;
	/**
	 * 未加入活动
	 */
	public static final int ACT_NO_JOIN_INEXISTENCE=47;
	/**
	 * 已经领取(活动)
	 */
	public static final int ACT_OK_AWARD_INEXISTENCE=48;
	/**
	 * 商家无此优惠卷
	 */
	public static final int USERSELLER_NO_COUPON=49;
	/**
	 * 存在个人审核
	 */
	public static final int EXIST_PERSON_STATUS_0=50;
	/**
	 * 个人认证已经认证过
	 */
	public static final int EXIST_PERSON_STATUS_1=51;
	/**
	 * 存在商家认证
	 */
	public static final int EXIST_SELLER_STATUS_0=52;
	/**
	 * 已认证商家
	 */
	public static final int EXIST_SELLER_STATUS_1=53;
	/**
	 * 法人照片
	 */
	public static final int LEGAL_IMG_PARRAM_NULL=54;
	/**
	 * 身份证照片不对
	 */
	public static final int PNOIMG_LENGTH_LT_2=55;
	/**
	 * 发布中的的劵
	 */
	public static final int COUPON_PUBLISHSTATUS_1_OR_2=56;
	/**
	 * 优惠劵已过期
	 */
	public static final int COUPON_NO_DATE_ERROR=57;
	/**
	 * 发布中的优惠劵超出数量
	 */
	public static final int COUPON_PUBLISHSTATUS_NUM_ERROR=58;
	/**
	 * 领取数量超出
	 */
	public static final int COUPON_PULL_NUM_ERROR=59;
	/**
	 * 领取劵超过限制
	 */
	public static final int COUPON_ASTRICT_NUM_ERROR=60;
	/**
	 * 不存在
	 */
	public static final int COUPON_CODE_DONT_EXIST=61;
	/**
	 * 优惠劵已经使用
	 */
	public static final int COUPON_ALREADY_USE_ERROR=62;
	/**
	 * 活动未开始
	 */
	public static final int COUPON_ACTIVE_NO_START_ERROR=63;
	/**
	 * 无动态发布权限
	 */
	public static final int NO_DYNAMIC_ABILITY=64;
	/**
	 * 评论不存在可能已经删除
	 */
	public static final int COMMENT_NULL=65;
	/**
	 * 动态不存在
	 */
	public static final int DYNAMIC_NULL=66;
	/**
	 * 已赞
	 */
	public static final int exist_praise=67;
	/**
	 * 融云token失败
	 */
	public static final int ROCLOUD_TOKEN_ERROR=68;
	/**
	 * 融云未知异常
	 */
	public static final int ROCLOUD_UNKNOWN_ERROR=69;
	/**
	 * 用户被封号
	 */
	public static final int USER_TITLES=70;
	/**
	 * 店铺不存在
	 */
	public static final int SELLER_STORE_NULL=71;

	
	/**
	 * 为手机号码的邀请码查询不到
	 */
	public static final int VISTCODE_IS_PHONE_NULL=72;
	/**
	 * 邀请码不存在
	 */
	public static final int VISTCODE_NULL=73;
	/**
	 * 已经试用过店铺产品无法使用
	 */
	public static final int HAVE_TO_TRY_PP_STORE=75;
	/**
	 * 店铺关闭
	 */
	public static final int SELLER__STORE_CLOSE=76;
	/**
	 * 店铺被人工关闭
	 */
	public static final int SELLER__STORE_ARTIFICIAL_CLOSED=77;
	/**
	 * 店铺数据异常
	 */
	public static final int SELLER__STORE_DATA_EXCEPTION=78;
	/**
	 * 存在身份证
	 */
	public static final int EXIST_IDCARD=79;
	/**
	 * 存在银行卡号
	 */
	public static final int EXIST_BANKCARD=80;
	
	/**
	 * 折扣点小于4
	 */
	public static final int SCALE_LT_4=81;
	/**
	 * 提现金额小于业务需求
	 */
	public static final int DRAWING_LT_PRICE_BUSINESS=82;
	/**
	 * 自己不能邀请自己
	 */
	public static final int INVITATIONCODE_ERROR_4=83;
	/**
	 * 存在邀请人
	 */
	public static final int INVITATIONCODE_ERROR_5=84;
	
	/**
	 * 用户未登陆
	 */
	public static final int USER_NO_LOGIN=85;
	/**
	 * 个人只能星期2申请提现
	 */
	public static final int PERSION_ONLY_2_DRAWING=86;
	/**
	 * 代理商只能15号申请提现
	 */
	public static final int AGENT_ONLY_15_DRAWING=87;
	/**
	 * 订单状态不正确(非支付)不能接单
	 */
	public static final int ORDER_UNPAY=88;
	/**
	 * 绑定银行卡名字不为null
	 */
	public static final int CARDHOLDERNAME_PARAM_NULL=89;
	/**
	 * 已经完善过信息
	 */
	public static final int HAS_PERFECT=90;
	/**
	 * 存在营业执照名称
	 */
	public static final int EXIST_BUSINESSLICENSENAME=91;
	/**
	 * 字段长度过长
	 */
	public static final int BUSINESSLICENSENAME_LENGTH_GT_30=92;
	/**
	 * 字段长度小于5个字
	 */
	public static final int BUSINESSLICENSENAME_LENGTH_LT_5=93;
	/**
	 * 个人不能申请货款提现
	 */
	public static final int PERSION_ONLY_4_DRAWING=94;
	/**
	 * 商家不能申请提现
	 */
	public static final int AGENT_ONLY_1_DRAWING=95;
	/**
	 * 订单已完成，不能申请退款
	 */
	public static final int ORDERFINISHED_REJECTREIM = 96;
	
	
	/**
	 * 钱包开通持卡人错误 
	 */
	public static final int WALLET_CARDHOLDERNAME_ERROR=1030;

	/** (编号长度不够) */
	public static final int WRONG_IDENTIFIER = 1110;
	
	/** (已存在相同名称的供应商) */
	public static final int EXIST_SUPP_NAME = 1111;
	
	/** (联系人手机号不能为空) */
	public static final int EMPTY_CONTACT_PHONE = 1112;
	
	/** (供应商名称不能为空) */
	public static final int EMPTY_SUPP_NAME = 1111;

	/**便利店ID不能为空*/
	public static final int SHOP_ID_IS_NULL = 97;

	/**
	 * 便利店名称不能为空
	 */
	public static final int SHOP_NAME_IS_NULL = 98;

	/**
	 * 手机号码为空
	 */
	public static final int PHONE_IS_NULL = 99;

	/**
	 * 手机号码格式不对
	 */
	public static final int PHONE_FOMAT_ERROR = 100;

	/**
	 * 便利店营业信息ID不能为空
	 */
	public static final int SHOP_BUSINESS_ID_IS_NULL = 101;

	/**没有找到便利店营业信息*/
	public static final int SHOP_BUSINESS_IS_NOT_FIND = 102;

	/**
	 * 便利店分类名称为空
	 */
	public static final int SHOP_CATEGORY_NAME_IS_NULL = 103;
	/**
	 * 便利店分类ID为空
	 */
	public static final int SHOP_CATEGORY_ID_IS_NULL = 104;
	/**
	 * 便利店管理公司分类ID为空
	 */
	public static final int SHOP_MANAGE_CATEGORY_ID_IS_NULL = 105;
	/**
	 * 便利店管理公司ID为空
	 */
	public static final int SHOP_MANAGE_ID_IS_NULL = 106;
	/**
	 * 便利店管理公司名称为空
	 */
	public static final int SHOP_MANAGE_NAME_IS_NULL = 107;
	/**
	 * 仓储中心ID为空
	 */
	public static final int CZZX_ID_NULL = 108;
	/**
	 * 法人名称为空
	 */
	public static final int LPNAME_IS_NULL = 109;
	
	/**
	 * 法人身份证为空
	 */
	public static final int LPIDCARD_IS_NULL = 110;
	/**
	 * 营业执照为空
	 */
	public static final int LICENCE_IS_NULL = 111;
	/**
	 * 身份证正面照片为空
	 */
	public static final int IDCARDFRONT_IS_NULL = 112;
	/**
	 * 身份证反面照片为空
	 */
	public static final int IDCARDBEHIND_IS_NULL = 113;
	/**
	 * 省ID为空
	 */
	public static final int PROVINCEID_IS_NULL = 114;
	/**
	 * 市ID为空
	 */
	public static final int CITYID_IS_NULL = 115;
	/**
	 * 身份证号码格式不对
	 */
	public static final int LPIDCARD_NOT_TRUE = 116;
	/**
	 * 便利店管理公司经营分类名称为空
	 */
	public static final int SHOP_MANAGE_CATEGORY_NAME_IS_NULL = 117;
	/**
	 * 主键ID为空
	 */
	public static final int DMID_IS_NULL = 118;
	
	/**超市ID不能为空*/
	public static final int SUPERMARKET_ID_IS_NULL = 119;
	
	/**已有其他分类关联*/
	public static final int supermarket_category_id_have=120; 
	
	/**超市分类名称为空*/
	public static final int supermarket_category_name_is_null=121; 
	
	/**服务器异常*/
	public static final int SERVER_EXCEPTION=122; 
	
	/**钱包有异常 */
	public static final int WALLET_ERROR = 123;
	/**钱包 金额 非正常 */
	public static final int WALLET_AMOUNT_UNNORMAL = 124;
	/**收入流水对账结算失败 */
	public static final int WALLET_SETTLEMENT_FAIL = 125;
	
	
	
	
	
	
	
	
	/**
	 *仓储名称
	 */
	public static final int CS_YCC_NAME_IS_NULL = 1;
	
	/**
	 *仓储管理中心-营业执照链接
	 */
	public static final int CS_YCC_LICENCE_IS_NULL = 1;
	/**
	 *仓储管理中心-身份证正面
	 */
	public static final int CS_YCC_IDCARDFRONT_IS_NULL = 1;
	/**
	 *仓储管理中心-身份证反面
	 */
	public static final int CS_YCC_IDCARDBEHIND_IS_NULL = 1;
	
	/** (已存在相同名称的仓库) */
	public static final int EXIST_YCC_NAME = 1111;
	
	/**
	 *省ID
	 */
	public static final int CS_YCC_PROVINCE_ID_IS_NULL = 1;
	
	/**
	 *市ID
	 */
	public static final int CS_YCC_CITY_ID_IS_NULL = 1;
	
	/**
	 *县区ID
	 */
	public static final int CS_YCC_COUNTY_ID_IS_NULL = 1;
	
	/**
	 *镇,街道ID
	 */
	public static final int CS_YCC_TOWN_ID_IS_NULL = 1;
	
	/**
	 * 供应链SkuId
	 */
	public static final int  CS_GYL_SKUID_IS_NULL =1; 
	
	/**
	 * 供应链云仓储管理公司议价后价格
	 */
	public static final int  CS_GYL_GYLPRICE_IS_NULL =1; 
	
	/**
	 * 每个用户只能绑定一张银行卡
	 */
	public static final int  WTAA_USER_BAKE =101; 
	
	
	
}
