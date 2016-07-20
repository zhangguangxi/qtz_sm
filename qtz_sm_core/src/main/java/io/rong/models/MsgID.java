package io.rong.models;


/**
 * 
 * <p>Title:MsgID</p>
 * <p>Description:(消息id 定义表)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月24日
 */
public class MsgID {
	// ******************通知消息类型 100 -  199 *******************************************//
	
	//通知动态
	public final static int push_dynamic=100; 
	
	//动态评论
	public final static int push_dynamic_comment=101;
	
	//动态点赞
	public final static int push_dynamic_praise=102;
	/**
	 * 商家分类
	 */
	public final static int push_seller_category=103;
}
