package io.rong;

import io.rong.models.ChatroomInfo;
import io.rong.models.FormatType;
import io.rong.models.GroupInfo;
import io.rong.models.Message;
import io.rong.models.SdkHttpResult;
import io.rong.util.HttpUtil;

import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.List;

import cache.BaseProperties;

import com.mall.core.common.Constants;
/**
 * 荣云集成 工具
 * <p>Title:ApiHttpClient</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015年3月21日
 */

public class ApiHttpClient {

	private static final String RONGCLOUDURI = "https://api.cn.rong.io";
	
	private static final String UTF8 = "UTF-8";
	
	private static final String APP_KEY=BaseProperties.getBaseProperties(Constants.RONG_CLOUD_APP_KEY);//荣云分配key
	
	private static final String APP_SECRET=BaseProperties.getBaseProperties(Constants.RONG_CLOUD_APP_SECRET); //荣云分配SECRET
	
	
	
	/**
	 * 
	* 【获取token】(这里用一句话描述这个方法的作用)
	* @param userId  用户ID （必传）
	* @param userName  用户名 （必传）
	* @param portraitUri  头像地址 （必传）
	* @param format （必传）  
	* @return
	* @throws Exception
	 */
	public static SdkHttpResult getToken(String userId, String userName, String portraitUri,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(APP_KEY, APP_SECRET, RONGCLOUDURI
						+ "/user/getToken." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
		sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	/**
	 * 
	* 【检查用户在线状态】(这里用一句话描述这个方法的作用)
	* @param userId   用户ID （必传）
	* @param format （必传）
	* @return  "code":200  正常 "status": 0 不在线  1 在线
	* @throws Exception
	 */
	public static SdkHttpResult checkOnline(String userId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,
				RONGCLOUDURI + "/user/checkOnline." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	/**
	 * 
	* 【 刷新用户信息】(这里用一句话描述这个方法的作用)
	* @param userId 用户ID （必传）
	* @param userName  用户名（新）
	* @param portraitUri 头像地址(新)
	* @param format （必传）
	* @return  {"code":200}
	* @throws Exception
	 */
	public static SdkHttpResult refreshUser(String userId, String userName, String portraitUri,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/user/refresh." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&name=").append(URLEncoder.encode(userName, UTF8));
		sb.append("&portraitUri=").append(URLEncoder.encode(portraitUri, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	/**
	 * 
	* 【封禁用户】(这里用一句话描述这个方法的作用)
	* @param userId 用户 Id。（必传）
	* @param minute 禁时长,单位为分钟，最大值为43200分钟。（必传）
	* @param format
	* @return  {"code":200}
	* @throws Exception
	 */
	public static SdkHttpResult blockUser(String userId, int minute, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET, RONGCLOUDURI + "/user/block." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&minute=").append(
				URLEncoder.encode(String.valueOf(minute), UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	
	/**
	 * 
	* 【 解禁用户】(这里用一句话描述这个方法的作用)
	* @param userId 用户 Id。（必传）
	* @param format （必传）
	* @return  {"code":200}
	* @throws Exception
	 */
	public static SdkHttpResult unblockUser(String userId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET, RONGCLOUDURI + "/user/unblock." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	/**
	 * 
	* 【获取被封禁用户】(这里用一句话描述这个方法的作用)
	* @param format （必传）
	* @return {"code":200,"users":[{"userId":"jlk456j5","blockEndTime":"2015-01-11 01:28:20"}]}
	* @throws Exception
	 */
	public static SdkHttpResult queryBlockUsers(FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/user/block/query." + format.toString());

		return HttpUtil.returnResult(conn);
	}

	/**
	 * 
	* 【 给用户添加用户到黑名单】(这里用一句话描述这个方法的作用)
	* @param userId 申请用户 （必传）
	* @param blackUserIds 被加黑的用户Id。(必传)
	* @param format (必传)
	* @return  {"code":200}
	* @throws Exception
	 */
	public static SdkHttpResult blackUser(String userId, List<String> blackUserIds, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/user/blacklist/add." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		if (blackUserIds != null) {
			for (String blackId : blackUserIds) {
				sb.append("&blackUserId=").append(
						URLEncoder.encode(blackId, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	/**
	 * 
	* 【 从黑名单移除用户】(这里用一句话描述这个方法的作用)
	* @param userId 用户 Id。（必传）
	* @param blackUserIds 被移除黑名单的用户Id。(必传)
	* @param format
	* @return {"code":200}
	* @throws Exception
	 */
	public static SdkHttpResult unblackUser(String userId, List<String> blackUserIds, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/user/blacklist/remove." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		if (blackUserIds != null) {
			for (String blackId : blackUserIds) {
				sb.append("&blackUserId=").append(
						URLEncoder.encode(blackId, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	/**
	 * 
	* 【获取某用户的黑名单列表】(这里用一句话描述这个方法的作用)
	* @param userId 用户 Id。（必传）
	* @param format （必传）
	* @return {"code":200,"users":["jlk454","jlk457"]} 此处是用户id
	* @throws Exception
	 */
	public static SdkHttpResult QueryblackUser(String userId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/user/blacklist/query." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}
	/**
	 * 
	* 【发送消息】(这里用一句话描述这个方法的作用)
	* @param fromUserId  发送人用户 Id。（必传）
	* @param toUserIds  接收用户 Id 集合  （必传）
	* @param msg  发送消息内容 （必传）
	* @param format
	* @return  {"code":200}
	* @throws Exception
	 */
	public static SdkHttpResult publishMessage(String fromUserId, List<String> toUserIds, Message msg,
				FormatType format) throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/message/private/publish." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
			if (toUserIds != null) {
				for (int i = 0; i < toUserIds.size(); i++) {
					sb.append("&toUserId=").append(
							URLEncoder.encode(toUserIds.get(i), UTF8));
				}
			}
			sb.append("&objectName=")
					.append(URLEncoder.encode(msg.getType(), UTF8));
			sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		/**
		 * 
		* 【发送消息】(这里用一句话描述这个方法的作用)
		* @param fromUserId  发送人用户 Id。（必传）
		* @param toUserIds 接收用户 Id 集合 （必传）
		* @param msg 发送消息内容 （必传）
		* @param pushContent 自定义消息，定义显示的 Push 内容。(可选)
		* @param pushData  针对 iOS 平台，Push 通知附加的 payload 字段，字段名为 appData。(可选)
		* @param format
		* @return {"code":200}
		* @throws Exception
		 */
		public static SdkHttpResult publishMessage(String fromUserId, List<String> toUserIds, Message msg,
				String pushContent, String pushData, FormatType format)
				throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/message/publish." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
			if (toUserIds != null) {
				for (int i = 0; i < toUserIds.size(); i++) {
					sb.append("&toUserId=").append(
							URLEncoder.encode(toUserIds.get(i), UTF8));
				}
			}
			sb.append("&objectName=")
					.append(URLEncoder.encode(msg.getType(), UTF8));
			sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

			if (pushContent != null) {
				sb.append("&pushContent=").append(
						URLEncoder.encode(pushContent, UTF8));
			}

			if (pushData != null) {
				sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
			}

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		/**
		 * 
		* 【发送系统消息】(这里用一句话描述这个方法的作用)
		* @param fromUserId  发送人用户 Id。（必传）
		* @param toUserIds 接收用户Id集合。（必传）
		* @param msg 消息 （必传）
		* @param pushContent  如果为自定义消息，定义显示的 Push 内容。(可选)
		* @param pushData 针对 iOS 平台，Push 通知附加的 payload 字段，字段名为 appData。(可选)
		* @param format
		* @return {"code":200}
		* @throws Exception
		 */
		public static SdkHttpResult publishSystemMessage(String fromUserId, List<String> toUserIds,
				Message msg, String pushContent, String pushData, FormatType format)
				throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/message/system/publish." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
			if (toUserIds != null) {
				for (int i = 0; i < toUserIds.size(); i++) {
					sb.append("&toUserId=").append(
							URLEncoder.encode(toUserIds.get(i), UTF8));
				}
			}
			sb.append("&objectName=")
					.append(URLEncoder.encode(msg.getType(), UTF8));
			sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

			if (pushContent != null) {
				sb.append("&pushContent=").append(
						URLEncoder.encode(pushContent, UTF8));
			}

			if (pushData != null) {
				sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
			}

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}
		/**
		 * 
		* 【发送广播消息 方法】(某发送消息给一个应用下的所有注册用户。)
		* @param fromUserId 发送人用户 Id。（必传）
		* @param msg 消息 （必传）
		* @param pushContent 如果为自定义消息，定义显示的 Push 内容。(可选)
		* @param pushData 针对 iOS 平台，Push 通知附加的 payload 字段，字段名为 appData。(可选)
		* @param format
		* @return {"code":200}
		* @throws Exception
		 */
		public static SdkHttpResult publishBroadcast( String fromUserId,Message msg,String pushContent, String pushData, FormatType format)throws Exception{
			
			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/message/broadcast." + format.toString());
			StringBuilder sb = new StringBuilder();
			sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
			
			sb.append("&objectName=")
					.append(URLEncoder.encode(msg.getType(), UTF8));
			sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

			if (pushContent != null) {
				sb.append("&pushContent=").append(
						URLEncoder.encode(pushContent, UTF8));
			}
			if (pushData != null) {
				sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
			}

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}
		
		
		// 发送群消息
		public static SdkHttpResult publishGroupMessage(String fromUserId, List<String> toGroupIds,
				Message msg, String pushContent, String pushData, FormatType format)
				throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/message/group/publish." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
			if (toGroupIds != null) {
				for (int i = 0; i < toGroupIds.size(); i++) {
					sb.append("&toGroupId=").append(
							URLEncoder.encode(toGroupIds.get(i), UTF8));
				}
			}
			sb.append("&objectName=")
					.append(URLEncoder.encode(msg.getType(), UTF8));
			sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

			if (pushContent != null) {
				sb.append("&pushContent=").append(
						URLEncoder.encode(pushContent, UTF8));
			}

			if (pushData != null) {
				sb.append("&pushData=").append(URLEncoder.encode(pushData, UTF8));
			}

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		// 发送聊天室消息
		public static SdkHttpResult publishChatroomMessage(String fromUserId, List<String> toChatroomIds,
				Message msg, FormatType format) throws Exception {

			HttpURLConnection conn = HttpUtil
					.CreatePostHttpConnection(APP_KEY,APP_SECRET, RONGCLOUDURI
							+ "/message/chatroom/publish." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("fromUserId=").append(URLEncoder.encode(fromUserId, UTF8));
			if (toChatroomIds != null) {
				for (int i = 0; i < toChatroomIds.size(); i++) {
					sb.append("&toChatroomId=").append(
							URLEncoder.encode(toChatroomIds.get(i), UTF8));
				}
			}
			sb.append("&objectName=")
					.append(URLEncoder.encode(msg.getType(), UTF8));
			sb.append("&content=").append(URLEncoder.encode(msg.toString(), UTF8));

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		// 创建聊天室
		public static SdkHttpResult createChatroom(List<ChatroomInfo> chatrooms, FormatType format) throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/chatroom/create." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("1=1");
			if (chatrooms != null) {
				for (ChatroomInfo info : chatrooms) {
					if (info != null) {
						sb.append(
								String.format("&chatroom[%s]=",
										URLEncoder.encode(info.getId(), UTF8)))
								.append(URLEncoder.encode(info.getName(), UTF8));
					}
				}
			}
			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		// 销毁聊天室
		public static SdkHttpResult destroyChatroom(List<String> chatroomIds, FormatType format)
				throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/chatroom/destroy." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("1=1");
			if (chatroomIds != null) {
				for (String id : chatroomIds) {
					sb.append("&chatroomId=").append(URLEncoder.encode(id, UTF8));
				}
			}

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		// 查询聊天室信息
		public static SdkHttpResult queryChatroom(List<String> chatroomIds, FormatType format) throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/chatroom/query." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("1=1");
			if (chatroomIds != null) {
				for (String id : chatroomIds) {
					sb.append("&chatroomId=").append(URLEncoder.encode(id, UTF8));
				}
			}

			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		// 获取消息历史记录下载地址
		public static SdkHttpResult getMessageHistoryUrl( String date, FormatType format) throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/message/history." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("date=").append(URLEncoder.encode(date, UTF8));
			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}

		// 删除消息历史记录
		public static SdkHttpResult deleteMessageHistory(String date, FormatType format) throws Exception {

			HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/message/history/delete." + format.toString());

			StringBuilder sb = new StringBuilder();
			sb.append("date=").append(URLEncoder.encode(date, UTF8));
			HttpUtil.setBodyParameter(sb, conn);

			return HttpUtil.returnResult(conn);
		}
	// 创建群
	public static SdkHttpResult createGroup(List<String> userIds, String groupId, String groupName,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET, RONGCLOUDURI + "/group/create." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
		if (userIds != null) {
			for (String id : userIds) {
				sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
			}
		}
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 加入群
	public static SdkHttpResult joinGroup(String userId, String groupId, String groupName, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/group/join." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 批量加入群
	public static SdkHttpResult joinGroupBatch(List<String> userIds, String groupId, String groupName,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET, RONGCLOUDURI + "/group/join." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));
		if (userIds != null) {
			for (String id : userIds) {
				sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
			}
		}
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 退出群
	public static SdkHttpResult quitGroup(String userId, String groupId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/group/quit." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 批量退出群
	public static SdkHttpResult quitGroupBatch(List<String> userIds, String groupId, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET, RONGCLOUDURI + "/group/quit." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		if (userIds != null) {
			for (String id : userIds) {
				sb.append("&userId=").append(URLEncoder.encode(id, UTF8));
			}
		}

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 解散群
	public static SdkHttpResult dismissGroup(String userId, String groupId, FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI
						+ "/group/dismiss." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		sb.append("&groupId=").append(URLEncoder.encode(groupId, UTF8));
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 同步用户群信息
	public static SdkHttpResult syncGroup(String userId, List<GroupInfo> groups, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI + "/group/sync." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("userId=").append(URLEncoder.encode(userId, UTF8));
		if (groups != null) {
			for (GroupInfo info : groups) {
				if (info != null) {
					sb.append(
							String.format("&group[%s]=",
									URLEncoder.encode(info.getId(), UTF8)))
							.append(URLEncoder.encode(info.getName(), UTF8));
				}
			}
		}
		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 刷新群信息
	public static SdkHttpResult refreshGroupInfo(String groupId, String groupName,
			FormatType format) throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(APP_KEY,APP_SECRET,RONGCLOUDURI
						+ "/group/refresh." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(groupId, UTF8));
		sb.append("&groupName=").append(URLEncoder.encode(groupName, UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	// 刷新群信息
	public static SdkHttpResult refreshGroupInfo( GroupInfo group, FormatType format)
			throws Exception {

		HttpURLConnection conn = HttpUtil
				.CreatePostHttpConnection(APP_KEY,APP_SECRET, RONGCLOUDURI
						+ "/group/refresh." + format.toString());

		StringBuilder sb = new StringBuilder();
		sb.append("groupId=").append(URLEncoder.encode(group.getId(), UTF8));
		sb.append("&groupName=").append(
				URLEncoder.encode(group.getName(), UTF8));

		HttpUtil.setBodyParameter(sb, conn);

		return HttpUtil.returnResult(conn);
	}

	
}