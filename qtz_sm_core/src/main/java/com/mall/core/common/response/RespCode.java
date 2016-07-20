package com.mall.core.common.response;

import com.mall.core.common.StringLengthConstant;

public class RespCode {
	
	
	/************************************(需要做客户端做特殊处理的code码)***************************************************************/
	//start
	/********(用户模块)********/
	//100027  请完善信息
	
	/********(钱包模块)********/
	//108006 支付密码未设置
	
	/*********(登录模块)*******/
	//110001 过期重新登录
	//110002 用户未登陆
	
	
	
	/**********(商铺分类)*********/
	//106001 分类不存在
	
	
	//end
	/**
	 * api过期
	 */
	public static int api_failure = 100000;
	public static int _404 = 404;
	public static int _400 = 400;
	public static int _405 = 405;
	public static int _500 = 500;
	public static int _timeout = 200;
	/**********************************(用户模块100)*******************************************************/
	
	//(缺少code  100001 100004 100008  100005 100006 100007 100009)
	
	
	/**
	 * 手机号码格式错误
	 */
	public static int phone_format_error=100002;
	/**
	 * 手机号码已存在
	 */
	public static int phone_already_registered=100003;
	
	/**
	 * 用户被封号
	 */
	public static int user_seal_number=100010;
	/**
	 * 通信录为null
	 */
	public static int address_book_is_null=100011;
	/**
	 * 用户登录失败
	 */
	public static int user_logout_failed=100012;
	/**
	 * 关注朋友失败
	 */
	public static int user_add_friend_failed=100013;
	/**
	 * 获取朋友失败
	 */
	public static int get_user_friend_failed=100014;
	/**
	 * 用户类型不是商家
	 */
	public static int user_no_business=100015;
	/**
	 * 用户类型不是个人
	 */
	public static int user_no_person=100016;
	/**
	 * 更新用户名称失败
	 */
	public static int update_headImg_nick_failed=100017;
	/**
	 * 移除朋友失败
	 */
	public static int user_remove_friend_failed=100018;
	/**
	 * 该用户已经关注过
	 */
	public static int user_friend_existence=100019;
	/**
	 * 用户已不是你关注对象
	 */
	public static int no_existence_flist=100019;
	/**
	 * 获取粉丝列表失败
	 */
	public static int get_user_fans_failed=100020;
	/**
	 * idcard错误
	 */
	public static int idcard_format_error=100021;
	/**
	 * 账户类型不匹配
	 */
	public static int userType_Dont_match=100022;
	/**
	 * 行政区信息有误
	 */
	public static int region_data_erro=100023;
	/**
	 * 获取主页信息失败
	 */
	public static int get_index_failed=100024;
	/**
	 * 搜索失败
	 */
	public static int search_failed=100025;
	/**
	 * 用户账号只能修改一次
	 */
	public static int user_account_failed=100025;
	/**
	 * 账号存在重复
	 */
	public static int user_account_repeat=100026;
	/**
	 * 请完善信息
	 */
	public static int steps_error=100027;
	/**
	 * 没有经纬度
	 */
	public static int no_loc=100028;
	/**
	 * 未注册
	 */
	public static int unregistered=100029;
	/**
	 * 账号不存在
	 */
	public static int account_inexistence=100030;
	/**
	 * 不能自己关注自己
	 */
	public static int follower_error_meFollowerMe=100031;
	/**
	 * 邀请码错误
	 */
	public static int invitationCode_error_1=100032;
	/**
	 * 邀请码错误
	 */
	public static int invitationCode_error_2=100033;
	/**
	 * 旧密码错误
	 */
	public static int oldPw_error=100034;
	/**
	 * 背景图片数量不正确第一张不能够删除
	 */
	public static int backgoundImg_num_error_1=100035;
	/**
	 * 背景图片数量不正确
	 */
	public static int backgoundImg_num_error_2=100036;
	/**
	 * 邀请数量不够
	 */
	public static int invitationCode_error_3=100037;
	/**
	 * 请登录个人版本应用
	 */
	public static int login_user_type_error_1=100038;
	/**
	 * 请登录商家版本应用
	 */
	public static int login_user_type_error_2=100039;
	/**
	 * 我的邀请失败
	 */
	public static int getMyInvitation_error=100040;
	/**
	 * 获取用户信息失败
	 */
	public static int getUser_error=100041;
	/**
	 * 获取自己信息失败
	 */
	public static int getMyUser_error=100042;
	/**
	 * 批量获取用户信息失败
	 */
	public static int getUsers_error=100043;
	/**
	 * 获取主页用户失败
	 */
	public static int getIndexUser_error=100044;
	/**
	 * 移除关注失败
	 */
	public static int removeFollower_error=100045;
	/**
	 * 获取好友列表失败
	 */
	public static int getFollowers_error=100046;
	/**
	 * 获取粉丝
	 */
	public static int getFans_=100047;
	/**
	 * 请选择头像
	 */
	public static int headimg_param_null=100048;
	/**
	 * 请输入昵称
	 */
	public static int nickname_param_null=100048;
	/**
	 * 上传通讯录失败
	 */
	public static int addAddressbook_error=100049;
	/**
	 * 请选择性别
	 */
	public static int sex_param_error=100050;
	/**
	 * 完善个人信息失败
	 */
	public static int perfectPersonInfo_error=100051;
	/**
	 * 请选择地区
	 */
	public static int region_province_id_error=100052;
	/**
	 * 完善个人信息2失败
	 */
	public static int perfectPersonInfo2_error=100053;
	/**
	 * 请选择服务行业
	 */
	public static int category_param_null=100054;
	/**
	 *请输入店铺地址
	 */
	public static int enterAddress_param_null=100055;
	/**
	 * tag null
	 */
	public static int perfectSellerInfo_tag_null=100056;
	/**
	 * 完善商家信息失败
	 */
	public static int perfectSellerInfo_error=100057;
	/**
	 * 完善商家信息2失败
	 */
	public static int perfectSellerInfo2_error=100058;
	/**
	 * 请输入密码
	 */
	public static int pwd_param_null=100059;
	/**
	 * 重置密码失败
	 */
	public static int retrievePassword_error=100060;
	/**
	 * 修改密码失败
	 */
	public static int alterPassword_error=100061;
	/**
	 * 上传背景图片失败
	 */
	public static int uploadBackgroundImg_error=100062;
	/**
	 * 请选择你需要删除的图片
	 */
	public static int urlValue_param_null=100063;
	/**
	 * 删除失败
	 */
	public static int delBackGroundImg_error=100064;
	/**
	 * 获取关注粉丝相册统计失败
	 */
	public static int getFollowerFansPhototsCount_error=100065;
	/**
	 * 更新个人信息失败
	 */
	public static int updatePersonal_error=100066;
	/**
	 * 更新商家信息失败
	 */
	public static int updateSeller_error=100067;
	/**
	 * 更新地区标示错误
	 */
	public static int regionMark_error=100068;
	/**
	 * 获取附近个人用户失败
	 */
	public static int getNearPersion_error=100069;
	/**
	 * 获取我的客户失败
	 */
	public static int myCustomer_error=100071;
	/**
	 * 获取收货信息失败
	 */
	public static int getReceivingInfo_error=100071;
	/**
	 * 添加或者更新收货信息失败
	 */
	public static int addOrUpdateReceivingInfo_error=100072;
	/**
	 * 收货信息不存在可能已经删除
	 */
	public static int userReceivingInfo_null=100073;
	/**
	 * 移除收货信息失败
	 */
	public static int removeReceivingInfo_error=100074;
	/**
	 * 我的团队失败
	 */
	public static int getMyTeam_error=100075;
	
	/**
	 * 我的团队详细用户信息失败
	 */
	public static int getMyTeamInfo_error=100076;
	/**
	 * 添加我的邀请人失败
	 */
	public static int addVistUserCode_error=100077;
	/**
	 * 获取我的邀请人失败
	 */
	public static int getByViscode_error=100078;
	/**
	 * 请选择行政区
	 */
	public static int region_null=100079;
	/**
	 * 身份证号码为null
	 */
	public static int persno_null=100080;
	/**
	 * getUserWeb失败
	 */
	public static int getUser_web_error=100081;
	/**
	 * 请求转发  商品列表
	 */
	public static int request_forward_getGoodsCategoryList=100082;
	/**
	 * 请求转发  getUser
	 */
	public static int request_forward_getUser=100083;
	
	
	/**
	 *自己不能邀请自己
	 */
	public static int invitationCode_error_4=100084;
	/**
	 * 存在邀请人
	 *
	 */
	public static int invitationCode_error_5=100085;
	/**
	 * 折扣点小于4
	 */
	public static int scale_lt_4=100086;
	/**
	 * 已经完善过信息
	 */
	public static int has_perfect=100087;
	
	/**
	 * 邀请人不存在
	 * 
	 */
	public static final int invitationCode_error_6=100088;
	
	/**
	 * 邀请人比被邀请人晚注册
	 * 
	 */
	public static final int invitationCode_error_7=100089;
	/**
	 * 新密码不能和旧密码一至
	 * 
	 */
	public static final int pwd_param_equal=100090;

	/**
	 *  代理商手机号码不存在
	 */
	public static final int agent_phone_not_exist = 100091;

	/**
	 * 密码过于简单
	 */
	public static final int pwd_too_short = 100092;
	
	
	/**********************************(优惠卷模块101)*******************************************************/
	/**
	 * 优惠券已经领取完毕
	 */
	public static int coupon_no_num_error=101001;
	/**
	 * 优惠券没有赠送给你
	 */
	public static int coupon_no_have_error=101002;
	/**
	 * 优惠卷重复领取
	 */
	public static int coupon_alway_have_error=101003;
	/**
	 * 优惠卷不存在
	 */
	public static int coupon_no_error=101006;
	/**
	 * 用户无此优惠卷
	 */
	public static int coupon_user_no_error=101007;
	/**
	 * 优惠卷过期
	 */
	public static int coupon_no_date_error=101008;
	/**
	 * 优惠卷已使用
	 */
	public static int coupon_already_use_error=101009;
	/**
	 * 不是认证商家不能创建活动
	 */
	public static int acticity_no_seller_error=101010;
	/**
	 * 优惠卷操作失败
	 */
	public static int coupon_failed=101011;
	/**
	 * 劵不存在或已经被删除
	 */
	public static int coupon_null=101012;
	/**
	 * 商家没有此优惠卷
	 */
	public static int userSeller_no_coupon=101013;
	/**
	 * 重复发布优惠卷
	 */
	public static int coupon_repeat_publish=101014;
	/**
	 * 领取渠道错误
	 */
	public static int coupon_pull_channel_error=101015;
	/**
	 * 劵码不存在
	 */
	public static int coupon_code_dont_exist=101015;
	/**
	 * 日期不准确
	 */
	public static int coupo_data_inaccurate=101016;
	/**
	 * 优惠劵只能发布4张
	 */
	public static int coupon_publish_num_error=101017;
	/**
	 * 请输入限制数量
	 */
	public static int astrict_num_param_error=101018;
	/**
	 * 请输入优惠劵名字
	 */
	public static int coupon_name_param_error=101019;
	/**
	 * 优惠劵范围请输入0-9999	
	 */
	public static int coupon_money_param_error=101020;
	/**
	 * 优惠劵创建数量范围是1-100000
	 */
	public static int coupon_creat_num_param_error=101021;
	/**
	 * 请输入优惠劵发布时间与结束时间
	 */
	public static int coupon_time_param_error=101022;
	/**
	 * 优惠劵名字长度12个字符
	 * @see StringLengthConstant
	 */
	public static int coupon_name_length_param_error=101023;
	/**
	 * 优惠劵开始结束时间必须大于30分钟
	 */
	public static int coupon_time_30_param_error=101024;
	/**
	 * 请校准输入时间
	 */
	public static int coupon_startTime_param_error=101025;
	/**
	 * 请输入优惠劵说明
	 */
	public static int coupon_explainTxt_param_error=101026;
	/**
	 * 请输入优惠劵规则
	 */
	public static int coupon_couponRules_param_error=101027;
	/**
	 * 消费满金额输入范围0-99999999
	 */
	public static int coupon_monetary_param_error=101028;
	/**
	 * 请输入优惠金额价值
	 */
	public static int coupon_favorableMoney_param_error=101029;
	/**
	 * 请输入优惠赠送
	 */
	public static int coupon_favorableDonate_param_error=101030;
	/**
	 * 优惠金额大于劵价值
	 */
	public static int coupon_favorableMoney_gt_monetart_param_error=101031;
	/**
	 * 折扣劵折扣请输入1-9
	 */
	public static int coupon_discount_favorableMoney_gt_10_lt_0_param_error=101032;
	/**
	 * 发布中的劵
	 */
	public static int coupon_publishstatus_1_or_2=101033;
	/**
	 * 霸王劵优惠金额范围1-9999
	 */
	public static int coupon_archLord_favorableMoney_param_error=101034;
	/**
	 * 创建或者修改优惠劵失败
	 */
	public static int creat_or_update_giftcoupon_error=101035;
	/**
	 * 创建或者修改霸王卷失败
	 */
	public static int create_or_update_archlordcoupon_error=101036;
	/**
	 * 创建或者修改折扣劵失败
	 */
	public static int create_or_update_discountcoupon_error=101037;
	/**
	 * 发布中的优惠卷无法修改
	 */
	public static int update_coupon_publishStatus_1_or_2=101038;
	/**
	 * 更新优惠劵失败
	 */
	public static int update_coupon_error=101040;
	/**
	 * 获取领取记录详情失败
	 */
	public static int viewCouponPullRecord_error=101041;
	/**
	 * 获取优惠劵详情失败
	 */
	public static int viewCouponInfo_error=101042;
	/**
	 * 发布优惠劵错误
	 */
	public static int publish_coupon_error=101043;
	/**
	 * 领取优惠劵来源错误
	 */
	public static int source_param_error=101044;

	/**
	 * 领取优惠劵失败
	 */
	public static int pull_coupon_error=101045;
	/**
	 * 我的优惠劵获取失败
	 */
	public static int myCoupon_error=101046;
	/**
	 * 使用优惠劵失败
	 */
	public static int useCoupon_error=101047;
	/**
	 * 领取使用详情失败
	 */
	public static int getCouponRecordInfo_error=101048;
	/*
	 *撤销优惠劵失败 
	 */
	public static int undoCoupon_error=101049;
	/**
	 * 获取公开优惠劵失败
	 */
	public static int getCoupon_public_error=101050;
	
	
	
	
	
	
	
	
	/**********************************(认证模块102)*******************************************************/
	/**
	 *	获取认证信息失败 
	 */
	public static int getSellerInfo_failed=102001;
	/**
	 * 提交商家认证失败
	 */
	public static int sub_sellerapprove_failed=102002;
	/**
	 * 提交个人认证失败
	 */
	public static int save_personAuthen_failed=102003;
	/**
	 *  还有未审核的个人资料
	 */
	public static int person_no_check_no_submit=102004;
	/**
	 * 审核通过的个人资料不能更改
	 */
	public static int person_ok_check_no_submit=102005;
	/**
	 * 获取个人 认证失败
	 */
	public static int get_pserson_failed=102006;
	/**
	 * 账户没有认证
	 */
	public static int account_no_auth=102007;
	/**
	 * 商家认证不能修改地区
	 */
	public static int business_auth_Ok_cant_address=102008;
	/**
	 * 查看个人认证信息失败
	 */
	public static int person_show_error=102009;
	/**
	 * 身份证参数错误
	 */
	public static int pnoImg_param_error=102010;
	/**
	 * 保存个人认证失败
	 */
	public static int save_personAuthen_error=102010;
	/**
	 * 商家认证信息失败
	 */
	public static int sell_show_error=102011;
	/*
	 * 保存商家认证信息失败
	 */
	public static int sell_save_error=102012;
	/**
	 * 请上传法人身份证图片
	 */
	public static int legal_pnoImg_param_error=102013;
	/**
	 * 请上传认证申请人名字
	 */
	public static int authen_name_param_error=102014;
	/**
	 * ID重复身份证
	 */
	public static int exist_idcard=102015;
	/**
	 * 存在营业执照名称
	 */
	public static int exist_businesslicensename=102016;
	/**
	 * 字段长度过长
	 */
	public static int businessLicenseName_length_gt_30=102017;
	/**
	 * 字段长度小于5
	 */
	public static int businessLicenseName_length_lt_5=102018;

	
	
	
	
	/**********************************(动态模块103)*******************************************************/
	/**
	 * 没有发布动态权限
	 */
	public static int no_permision_upload=103001;
	/**
	 * 评论内容过长
	 */
	public static int reply_content_long=103002;
	/**
	 * 动态不存在
	 */
	public static int dynamic_id_no_existence=103003;
	/**
	 * 回复评论人信息不存在
	 */
	public static int reply_id_no_existence=103004;
	/**
	 * 转载来源为空
	 */
	public static int reprint_absObs_isnull=103005;
	/**
	 * 发布类型
	 */
	public static int publishType_error=103006;
	/**
	 * 动态获取失败
	 */
	public static int get_dynamic_failed=103007;
	/**
	 * 回复失败
	 */
	public static int reply_failed=103008;
	/**
	 * 评论失败
	 */
	public static int comment_failed=103009;
	/**
	 * 点赞失败
	 */
	public static int praise_failed=103010;
	/**
	 * 点赞存在
	 */
	public static int praise_exist=103011;
	/**
	 * 评论不存在
	 */
	public static int comment_id_no_exist=103012;
	/**
	 * 不能回复自己
	 */
	public static int comment_cant_reply_oneself=103013;
	/**
	 * 请上传图片或者内容
	 */
	public static int content_img_param_null=103014;
	/**
	 * 图片超出限制
	 */
	public static int img_param_leng_gt_8=103015;
	/**
	 * 发布优惠劵请选择优惠劵
	 */
	public static int publish_coupon_absObj_param_null=103016;
	/**
	 * 上传动态失败
	 */
	public static int uploadDynamic_error=103017;
	/**
	 * 定位异常重新定位
	 */
	public static int dynamic_loc_param_error=103018;
	/**
	 * 获取附近商家动态失败
	 */
	public static int getNearSellerLastDynamic_error=103019;
	
	/**
	 * 获取动态圈失败
	 */
	public static int getDynamicCircle_error=103020;
	/**
	 * 查询动态失败
	 */
	public static int queryDynamic_error=103021;
	/**
	 * 评论内容为null
	 */
	public static int comment_content_param_null=103022;
	/**
	 * 评论失败
	 */
	public static int comment_error=103023;
	/**
	 * 请输入回复内容
	 */
	public static int reply_content_param_null=103024;
	/**
	 * 回复失败
	 */
	public static int reply_error=103025;
	/**
	 * 点赞失败
	 */
	public static int praise_error=103026;
	/**
	 * 获取动态评论失败
	 */
	public static int getComment_error=103027;
	/**
	 * 获取点赞列表失败
	 */
	public static int getPraise_error=103028;
	/**
	 * 去除点赞
	 */
	public static int removePraise_error=103029;
	/**
	 * 删除动态失败
	 */
	public static int removeDynamic_error=103030;
	/**
	 * 删除评论失败
	 */
	public static int removeComment_error=103031;
	/**
	 * 获取动态统计失败
	 */
	public static int getDynamicCount_error=103032;
	/**
	 * 添加评论失败
	 */
	public static int addComment_error=103033;
	
	
	
	
	
	
	
	
	/**********************************(行业模块104)*******************************************************/
	/**
	 * 行业信息错误
	 */
	public static int category_id_error=104001;
	
	
	
	
	
	/**********************************(活动模块105)*******************************************************/
	
	/**
	 * 活动过期
	 */
	public static int act_pass_inexistence =105001;
	
	/**
	 * 活动未开始
	 */
	public static int coupon_active_no_start_error=105002;
	/**
	 * 活动已经结束
	 */
	public static int coupon_active_end_error=105003;
	/**
	 * 没有加入活动
	 */
	public static int act_no_join_inexistence=105004;
	/**
	 * 奖品领完了
	 */
	public static int act_no_award_inexistence=105005;
	/**
	 * 已经领取了
	 */
	public static int act_ok_award_inexistence=105006;
	/**
	 * 活动不存在
	 */
	public static int act_no_inexistence=105007;
	/**
	 * 保存活动错误
	 */
	public static int save_act_user_error=105008;
	/**
	 * 显示活动用户错误
	 */
	public static int show_act_user_error=105009;
	/**
	 * 是否可以领取
	 */
	public static int accept_award_error=105009;
	/**
	 * 请输入活动名字
	 */
	public static int build_near_act_name_param_error=105010;
	/**
	 * 请输入活动开始时间
	 */
	public static int build_near_act_stime_param_error=105011;
	/**
	 * 请输入活动海报
	 */
	public static int build_near_act_headImg_param_error=105012;
	/**
	 * 请输入地址
	 */
	public static int build_near_act_addr_param_error=105013;
	/**
	 * 请输入活动结束时间
	 */
	public static int build_near_act_etime_param_error=105014;
	/**
	 * 请输入活动介绍
	 */
	public static int build_near_act_txt_param_error=105015;
	/**
	 * 创建优惠券定时抢活动失败
	 */
	public static int build_coupon_act_error=105016;
	/**
	 * 创建附加的活失败
	 */
	public static int build_near_act_error=105017;
	/**
	 * 商家发布的活动未过期的列表失败
	 */
	public static int show_seller_live_error=105018;
	/**
	 * 用户参加的未过期的活动失败
	 */
	public static int show_join_live_error=105019;
	/**
	 * 查看用户已经结束活动失败
	 */
	public static int show_history_error=105020;

	/**
	 * 查询最新活动失败
	 */
	public static int show_new_error=105021;
	/**
	 * 查看活动详情
	 */
	public static int show_error=105022;
	/**
	 * 查看报名活动用户失败
	 */
	public static int show_users_error=105023;

	
	
	
	
	
	/**********************************(商品模块106)*******************************************************/
	/**
	 * 商品分类不存在
	 */
	public static int goodsCategory_dont_exist=106001;
	/**
	 * 商品分类存在商品
	 */
	public static int goodsCategory_product_error=106002;
	/**
	 * 存在相同分类名字	
	 */
	public static int exist_valid_goods_category_name=106003;
	/**
	 * 金额错误
	 */
	public static int price_error=106004;
	/**
	 * 店铺存在并且有效
	 */
	public static int seller_store_exist_valid=106005;
	/**
	 *	删除分类失败
	 */
	public static int delCategory_error=106006;
	/**
	 * 创建或者更新分类失败
	 */
	public static int createOrUpdateCategory_error=106007;
	/**
	 * 获取商品分类列表失败
	 */
	public static int getCategoryList_error=106008;
	/**
	 * 添加产品失败
	 */
	public static int addGoods_error=106009;
	/**
	 * 获取产品详情失败
	 */
	public static int getGoodsInfo_error=106010;
	/**
	 * 更新产品失败
	 */
	public static int updateGoods_error=106011;
	
	/**
	 * 商品不存在
	 */
	public static int gd_goods_not_found=106012;
	/**
	 * 获取商品分类属性失败
	 */
	public static int gd_get_prop_config_fail=106013;
	/**
	 * 上传图片错误
	 */
	public static int gd_upload_img_fail=106014;
	/**
	 * 编辑商品，上行JSON错误
	 */
	public static int gd_goods_edit_json_error=106015;
	/**
	 * 编辑商品，SKU属性值组合已经存在
	 */
	public static int gd_goods_edit_sku_combine_exsit=106016;
	/**
	 * sku不存在
	 */
	public static int gd_sku_not_found=106017;
	
	
	
	/**********************************(店铺模块107)*******************************************************/
	/**
	 * 没有开通店铺
	 */
	public static int  seller_no_store=107001;
	/**
	 * 店铺非营业阶段
	 */
	public static int  store_close_business=107002;
	/**
	 * 错误的预约时间
	 */
	public static int  error_makeTime=107003;
	/**
	 * 获取店铺信息失败
	 */
	public static int  getStoreInfo_error=107004;
	/**
	 * 我的店铺失败
	 */
	public static int  myStore_error=107005;
	/**
	 * 请输入起送费和派送费
	 */
	public static int  sendOutUpFee_or_sendFee_param_null=107006;
	/**
	 * 更新店铺失败
	 */
	public static int  updateStore_error=107007;
	/**
	 * 开通店铺失败
	 */
	public static int  openStore_error=107008;
	/**
	 * 结束时间要大于开始时间
	 */
	public static int  startTime_gt_endTime=107009;
	/**
	 * 店铺被人工关闭
	 */
	public static int  seller__store_artificial_closed=107010;
	/**
	 * 店铺数据异常请稍后再试
	 */
	public static int  seller__store_data_exception=107011;
	/**
	 * 店铺不支持派送
	 */
	public static int  seller_store_issend_1=107012;
	/**
	 * 店铺不支持到店
	 */
	public static int  seller_store_isstop_1=107013;

	/**
	 * 便利店ID为空
	 */
	public static int shop_id_is_null = 107014;

	/**
	 * 便利店名称不能为空
	 */
	public static int shop_name_is_null = 107015;

	/**
	 * 便利店手机号码为空
	 */
	public static int shop_phone_is_null = 107016;

	/**
	 * 便利店手机号码格式错误
	 */
	public static int shop_phone_fomat_error = 107017;

	/**
	 * 便利店分类名称为空
	 */
	public static int shop_category_name_is_nul = 107018;

	/**
	 * 便利店营业信息ID为空
	 */
	public static int shop_business_id_is_null = 107019;
	
	/**
	 * 便利店管理公司分成点已存在
	 */
	public static int shop_manage_split_point_not_null = 107020;

	/**
	 * 便利店名称已存在
	 */
	public static int shop_name_have = 107021;
	/**
	 * 便利店分类名称已存在
	 */
	public static int shop_category_name_have = 107022;
	
	/**
	 * 便利店管理公司名称已存在
	 */
	public static int shop_manage_name_have = 107023;
	/**
	 * 便利店管理公司运营分类名称已存在
	 */
	public static int shop_manage_category_name_have = 107024;
	/**
	 * 便利店营业信息已存在
	 */
	public static int  shop_business_have = 107025;
	/**
	 * 便利店营业信息不存在
	 */
	public static int  shop_business_not_have = 107026;
	/**
	 * 便利店商品不存在
	 */
	public static int  shop_goods_not_have = 107027;
	/**
	 * 便利店Sku不存在
	 */
	public static int  shop_sku_not_have = 107028;
	/**
	 * 添加便利店销量信息异常
	 */
	public static int  addSales_error = 107029;
	/**
	 * 统计便利店销量信息异常
	 */
	public static int  countSales_error = 107030;
	
	
	
	/**********************************(钱包模块108)*******************************************************/
	/**
	 * 银行卡号错误
	 */
	public static int bank_id_erroe=108001;
	/**
	 * 不支持此银行
	 */
	public static int does_not_support_the_bank=108002;
	/**
	 * 支付密码错误
	 */
	public static int wallet_pay_pwd_error=108003;
	/**
	 * 余额不足
	 */
	public static int not_sufficient_funds=108004;
	/**
	 * 银行卡不存在
	 */
	public static int bank_inexistence=108005;
	/**
	 * 支付密码没设置
	 */
	public static int wallet_pay_pwd_null_error=108006;
	/**
	 * 存在提款申请
	 */
	public static int exist_withdrawal_apply=108007;
	/**
	 * 发送支付密码短信失败
	 */
	public static int sendPayPwdCode_error=108008;
	/**
	 * 	设置支付密码失败
	 */
	public static int setPayPwd_error=108009;
	/**
	 * 获取钱包信息失败
	 */
	public static int getWallet_error=108010;
	/**
	 * 绑定银行卡失败
	 */
	public static int bindingBank_error=108011;
	/**
	 * 请输入银行卡名字
	 */
	public static int binding_bankName_param_null=108012;
	
	/**
	 * 请输入银行卡名字
	 */
	public static int binding_bankNum_param_null=108013;
	
	/**
	 * 请输入银行卡名字
	 */
	public static int binding_bankAccountType_param_null=108014;
	
	/**
	 * 请输入银行地址
	 */
	public static int binding_bankAddress_param_null=208013;
	
	/**
	 * 删除绑定银行卡失败
	 */
	public static int delBindingBank_error=108012;
	/**
	 * 提现申请失败
	 */
	public static int applyDrawing_error=108013;
	/**
	 * 请输入支付密码
	 */
	public static int applyDrawing_pwd_param_null=108014;
	/**
	 * 请选择提现银行卡
	 */
	public static int applyDrawing_bankId_param_null=108015;
	/**
	 * 请输入提现金额
	 */
	public static int applyDrawing_price_param_null=108016;
	/**
	 * 付款失败
	 */
	public static int payment_error=108017;
	/**
	 * 查找金额日志失败
	 */
	public static int findMoneyLogsByDay=108018;
	/**
	 * 存在银行卡
	 */
	public static int exist_bankcard=108019;
	/**
	 * 提现金额小于业务需求
	 */
	public static int drawing_lt_price_business=108020;
	/**
	 * 个人用户只能周二提现
	 */
	public static int persion_only_2_drawing=108021;
	
	///////////////////////////////////////////////////v3.5//////////////////////////////////////
	/**
	 * 钱包对账中ID不能为空
	 */
	public static int recon_record_id_cannot_null =108022;
	/**
	 * 提现日期不能为空
	 */
	public static int extract_apply_date_cannot_null =108023;
	/**
	 *钱包为空 
	 */
	public static int get_user_wallet_fail =108024;
	
	/**
	 * 获取用户钱包数据失败
	 */
	public static int get_user_wallet_msg_fail = 108025;

	/**
	 * 请输入正确的提现金额
	 */
	public static int wrong_withdraw_money = 108026;

	/**
	 * 代理商提现日期错误
	 */
	public static int wrong_date_agent_withdraw = 108027;

	/**
	 * 代理商提现时间必须在当天17点之前
	 */
	public static int wrong_hour_agent_withdraw = 108028;

	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	/**********************************(商品订单模块109)*******************************************************/
	/**
	 * 订单已支付
	 */
	public static int order_pay_error=109001;
	/**
	 * 订单小于店铺起送价格
	 */
	public static int order_min_inexistence=109002;
	/**
	 * 订单未支付
	 */
	public static int query_orderPayment_status_failure=109003;
	/**
	 * 订单不能取消
	 */
	public static int orders_status_cannot_be_cancelled=109004;
	/**
	 * 订单不存在
	 */
	public static int order_null=109005;
	/**
	 * 提交订单失败
	 */
	public static int subOrder_error=109006;
	/**
	 * 获取可用优惠卷信息失败
	 */
	public static int getUsableCoupon_error=109007;
	/**
	 * 计算订单价格失败
	 */
	public static int calculateOrderPrice_error=109008;
	/**
	 * 查询订单列表失败
	 */
	public static int getOrderList_error=109009;
	/**
	 * 接单失败
	 */
	public static int receivingOrder_error=109010;
	/**
	 * 拒绝接单失败
	 */
	public static int refusedOrder_error=109011;
	/**
	 * 取消订单
	 */
	public static int cancelOrder_error=109012;
	/**
	 * 查询订单状态失败
	 */
	public static int getOrderLog_error=109013;
	/**
	 * 确认消费失败
	 */
	public static int confirmOrder_error=109014;
	/**
	 * 申请退款失败
	 */
	public static int applyRefund_error=109012;
	/**
	 * 同意退款失败
	 */
	public static int agreedRefund_error=109013;
	/**
	 * 获取订单商品失败
	 */
	public static int getOrderGoodsList_error=109014;
	/**
	 * 查询订单支付状态失败
	 */
	public static int queryOrderPaymentStatus_error=109015;
	/**
	 * 查询订单数量失败
	 */
	public static int getOrderCount_error=109016;
	/**
	 * 提交支付店铺订单失败
	 */
	public static int subSellerStoreOrder_error=109017;
	/**
	 * 已经试用过店铺产品
	 */
	public static int have_to_try_pp_store=109018;
	/**
	 * 订单未支付不能接单
	 */
	public static int order_unpay_1=109019;
	/**
	 * 订单状态不正确
	 */
	public static int seller_receiving_order_status_error=109020;
	
	/**
	 * 订单已完成，不能申请退款
	 */
	public static int order_finished_reject_reim = 109021;
	
	/**
	 * 拒绝退款失败
	 */
	public static int refusedRefund_error=109022;
	
	/**
	 * 确认收货失败
	 */
	public static int confirmReceipt_error=109023;
	
	/**
	 * 添加仲裁信息失败
	 */
	public static int arbitration_error=109024;
	
	/**************************************** 极光推送code ********************************************/
	
	/**
	 * 下单
	 */
	public static final String place_an_order="109114";
	
	/**
	 * 接单
	 */
	public static final String order_receiving="109115";
	
	/**
	 * 拒绝接单
	 */
	public static final String refuse_place_an_order="109116";
	
	/**
	 * 申请退款
	 */
	public static final String apply_for_refund="109117";
	
	/**
	 * 同意退款
	 */
	public static final String agree_to_refund="109118";
	
	/**
	 * 不同意退款
	 */
	public static final String no_agree_to_refund="109119";
	
	/**
	 * 支付未接单  退款
	 */
	public static final String order_cancel="109200";
	
	
	
	/**********************************(登录模块110)*******************************************************/
	
	/**
	 * 重新登录
	 */
	public static  int session_overdue=110001;
	/**
	 * 用户未登陆
	 */
	public static int user_not_login=110002;
	/**
	 * 验证码获取失败
	 */
	public static int auth_code_get_failed=110003;
	/**
	 * 登录失败
	 */
	public static int user_login_failed=110004;
	/**
	 * 用户验证码不正确
	 */
	public static int user_auth_code_error=110005;
	/**
	 * 用户密码格式错误
	 */
	public static int user_pass_format_error=110006;
	/**
	 * 用户注册失败
	 */
	public static int user_registered_failed=110007;
	/**
	 * 用户不存在或者密码不正确
	 */
	public static int user_no_existence=110008;
	/**
	 * 短信验证码发送失败
	 */
	
	/**
	 * 请输入验证码
	 */
	public static int authCode_param_null=110009;
	/**
	 * 请输入手机号码
	 */
	public static int register_phone_param_null=110010;
	/**
	 * 请输入注册密码
	 */
	public static int register_pwd_param_null=110011;
	/**
	 * userType错误
	 */
	public static int register_userType_error=110012;
	
	/**
	 * 请输入手机号码
	 */
	public static int login_phone_param_null=110013;
	/**
	 * 请输入注册密码
	 */
	public static int login_pwd_param_null=110014;
	
	//票据不能为空
	public static int token_cannot_null = 110015;
	
	
	
	
	/**********************************(支付模块111)*******************************************************/
	/**
	 * 推送银联订单失败
	 */
	public static int push_unionpay_order_into_error=1111001;
	/**
	 * 推送微信订单失败
	 */
	public static int push_wechatpay_order_into_error=1111002;
	/**
	 * 支付年费失败
	 */
	public static int getPpSellerStoreService_errors=1111003;
	/**
	 * 不支持微信支付
	 */
	public static int not_support_wechat_pay=1111004;
	
	
	/**********************************(主页留言模块112)*******************************************************/
	/**
	 * 主页留言失败
	 */
	public static int subLeaveWord_error=112001;
	/**
	 * 提交app留言失败
	 */
	public static int subAppLeaveWord_error=112002;
	
	
	
	/**********************************(配置模块113)*******************************************************/
	/**
	 * 获取下级标签失败
	 */
	public static int getNextCategory_error=113001;
	/**
	 * 获取商家店铺分类失败
	 */
	public static int getStoreCategory_error=113002;
	/**
	 * pushCode参数为null
	 */
	public static int pushCode_param_null=113003;
	/**
	 * 提交code吗失败
	 */
	public static int subPushCode_error=113004;
	/**
	 * 获取融云token失败
	 */
	public static int rtoken_error=113005;
	/**
	 * 获取支付配置失败
	 */
	public static int getPayParameter_error=113006;
	/**
	 * 获取主页数据失败
	 */
	public static int getIndexDate_error=113007;
	/**
	 * 获取广告数据失败
	 */
	public static int getAdvertDate_error=113008;
	/**
	 * 获取默认头像失败
	 */
	public static int getDefaultHeadImg_error=113009;
	/**
	 * 获取折扣点失败
	 */
	public static int getScale_error=113010;
	/**
	 * 获取银行卡号失败
	 */
	public static int getBankList_error=113011;
	
	
	/******************************广告模块114*******************************************/
	/**
     * 广告位不存在
     */
    public static int advertisement_not_found=114001;
	
	
	/******************************极光推送CODE*******************************************/
	
	/**
	 * 审核通过 
	 */
	public static final String audit_pass="200101";
	
	/**
	 * 审核不通过 
	 */
	public static final String audit_not_pass="200102";
	
	/**
	 * 同意提现
	 */
	public static final String agree_cash="300101";
	/**
	 * 驳回
	 */
	public static final String reject_cash="300102";
	/**
	 * 银行驳回
	 */
	public static final String reject_of_bank="300103";
	/**
	 * 店铺过期
	 */
	public static final String sellerStore_expired="400001";
	/**
	 * 剩余几天店铺过期
	 */
	public static final String sellerStore_expired_by="400002";
	
	/**
	 * 全网通知
	 */
	public static final String system_notice="500001";
	
	//dhf
	/**
	 * 商品分类获取失败
	 */
	public static final int goods_type_get_fail=600001;
	/**
	 * 商品分类添加失败
	 */
	public static final int add_goods_type_fail=600002;
	
	/**
	 * 获取文件上传签名失败
	 */
	public static final int get_file_upload_sign_fail=600003;

	/**
	 * 商品分类不存在
	 */
	public static final int goods_type_not_exsist=600004;
	/**
	 * 添加商品失败
	 */
	public static final int add_goods_fail=600005;

	/**
	 * 修改商品分类信息失败
	 */
	public static final int mod_goods_type_fail=600006;
	/**
	 * 修改商品分类信息失败
	 */
	public static final int get_goods_type_fail=600007;
	/**
	 * 编辑商品失败
	 */
	public static final int edit_goods_fail=600008;

	
	
	//sm-wallet
	////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获取钱包失败
	 */
	public static int getWalletList_error=800001;
	/**
	 * 获取收入流水失败
	 */
	public static int getIncomeList_error=800005;
	
	/**
	 * 获取提现流水失败
	 */
	public static int getWithdrawalsList_error=800006;
	
	/**
	 * 仓储名称存在
	 */
	public static int ycc_name_exist=900011;
	/**
	 * 仓储地址不存在
	 */
	public static int ycc_addRess_not_exist=900012; 
	
	
	/**
     * 仓储地址不存在
     */
    public static int cczx_id_not_exist=900013;
	
	
	
	/**
	 * 钱包  源单id	不存在
	 */
	public static int wallet_sourceId_not_exist=1000001; 
	
	public static int wallet_amount_not_exist=1000002; 
	
	public static int wallet_bankCardId_not_exist=1000003; 
	
	public static int wallet_bldId_not_exist=1000004; 
	
	public static int wallet_bldrate_not_exist=1000005; 
	
	public static int wallet_groupId_not_exist=1000006; 
	
	public static int wtll_user_bake=1000007; 

	
	/**
	 * 没有绑定银行卡
	 */
	public static final int exist_bing_bankcard = 1000008;
	
	/**
	 * 该银行卡不属于该组织请重新绑定银行卡
	 */
	public static final int not_belong_bing_bankcard = 1000009;
	
	/**
	 * 钱包没设定
	 */
	public static final int wall_not_set = 1000010;
	
	/**
	 * 钱包没初始化
	 */
	public static final int wall_not_initialized  = 1000011;
	
	/**
	 * 没有发现该申请！
	 */
	public static final int not_present_application  = 1000012;
	
	/**
	 * 只能对没提现的申请操作
	 */
	public static final int not_to_mention_present = 1000013;
	
	/**
	 *请同意提现或者不同意提现！
	 */
	public static final int consent_to_withdrawal  = 1000014;
	
	/**
	 *只能对已提现的申请操作！
	 */
	public static final int already_to_mention_present  = 1000015;
	
	/**
	 *只能执行驳回操作！
	 */
	public static final int  to_mention_bh_present  = 1000016;
	
	
	/**
	 *只能对已驳回的申请操作！
	 */
	public static final int already_to_bh_present  = 1000017;
	
	/**
	 *只能执行追回操作！
	 */
	public static final int  to_mention_zh_present  = 1000018;
	
	public static int binding_bankName_lpname_null=1000019; 
	
	public static int binding_bankName_companyName_null=1000020; 
	

	//supermarket
	/******************************超市模块115*******************************************/
	/**
	 * 分类名称已存在
	 */
	public static int supermarket_category_name_have=1150001; 
	/**
	 * 已有其他分类关联
	 */
	public static int supermarket_category_id_have=1150002; 
	/**
	 * 名称已存在
	 */
	public static int supermarket_name_have=1150003; 
	/**
	 * 超市营业信息已存在
	 */
	public static int supermarket_business_have=1150003; 
	
	 /**获取超市二级商品分类下商品信息失败 */
	public static int find_second_category_goods_fail= 1150004;
	 
	/**根据一级商品分类id查询二级商品分类列表失败*/
	public static int find_second_category_fail=1150005;
	
	/**获取sku属性失败*/
	public static int get_skuVal_fail=1150006;
	
	/**非法参数*/
	public static int  illegalParameter_error = 1150007;
	
	/** 获取库存信息异常 */
	public static int  getSotck_error = 1150007;
	
	/** 获取库存信息为空 */
	public static int  getSotck_isNull_error = 1150008;
	
	/*****************************钱包模块 116*****************************************/
	public static final int wtallet_distriute_income_fail 	= 116100; 
	public static final int wtallet_undistriute_income_fail = 116101;
	
	/****************************钱包模块********************************************/
	
	/*****************************举报模块 117*****************************************/
	/** 添加举报信息异常 */
	public static final int addReport_error = 117001; 
	/** 获取举报信息异常 */
	public static final int getReport_error = 117002; 
	/** 删除举报信息异常 */
	public static final int delReport_error = 117003; 
	
	/****************************举报模块********************************************/
	
	/*****************************批发单模块 118*****************************************/
	/** 添加批发单信息异常 */
	public static final int add_batOrder_error = 118001; 
	/** 批发单不存在 */
	public static final int batOrder_no_have_error = 118002; 
	/** 批发单状态错误 */
	public static final int batOrder_status_error = 118003; 
	/** 更新批发单错误 */
	public static final int update_batOrder_error = 118004; 
	/** 获取批发单列表错误 */
	public static final int get_batOrder_list_error = 118005; 
	
	/****************************批发单模块********************************************/
	
	
}
