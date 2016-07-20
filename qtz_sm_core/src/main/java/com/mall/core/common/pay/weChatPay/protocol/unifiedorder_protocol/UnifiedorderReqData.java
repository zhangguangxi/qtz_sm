package com.mall.core.common.pay.weChatPay.protocol.unifiedorder_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.mall.core.common.pay.weChatPay.common.Configure;
import com.mall.core.common.pay.weChatPay.common.RandomStringGenerator;
import com.mall.core.common.pay.weChatPay.common.Signature;

/**
 * 
 * <p>Title:UnifiedorderReqData</p>
 * <p>Description:(统一下单)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年10月10日
 */
public class UnifiedorderReqData {
	//公众账号ID
		private String appid="";
		//商户号
		private String mch_id="";
		//随机字符串
		private String nonce_str = "";
		//签名
		private String sign = "";
		//商品描述
		private String body="";
		//商户订单号
		private String out_trade_no="";
		//总金额
		private Integer total_fee;
		//终端ip
		private String spbill_create_ip;
		//异步通知url
		private String notify_url="";
		//交易类型
		private String trade_type="";
		
		
		/**
		 * 
		 * 构造函数
		 * @param body				商品描述
		 * @param out_trade_no		商户订单id
		 * @param total_fee			订单总价只能是整数
		 * @param spbill_create_ip	终端ip 服务器ip
		 * @param notify_url		异步通知url
		 * @param trade_type		支付类型
		 */
		public UnifiedorderReqData( String body,
				String out_trade_no, Integer total_fee, String spbill_create_ip,
				String notify_url, String trade_type,Configure configure) {
			super();
			 //微信分配的公众号ID（开通公众号之后可以获取到）
	        setAppid(configure.getAppid());

	        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
	        setMch_id(configure.getMchid());
			//根据API给的签名规则进行签名
	        //随机字符串，不长于32 位
	        this.nonce_str=RandomStringGenerator.getRandomStringByLength(32);
			this.body = body;
			this.out_trade_no = out_trade_no;
			this.total_fee = total_fee;
			this.spbill_create_ip = spbill_create_ip;
			this.notify_url = notify_url;
			this.trade_type = trade_type;
			String sign = Signature.getSign(toMap(),configure.getKey());
		    this.sign=sign;
		}
		public String getAppid() {
			return appid;
		}
		public void setAppid(String appid) {
			this.appid = appid;
		}
		public String getMch_id() {
			return mch_id;
		}
		public void setMch_id(String mch_id) {
			this.mch_id = mch_id;
		}
		public String getNonce_str() {
			return nonce_str;
		}
		public void setNonce_str(String nonce_str) {
			this.nonce_str = nonce_str;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getOut_trade_no() {
			return out_trade_no;
		}
		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}
		public Integer getTotal_fee() {
			return total_fee;
		}
		public void setTotal_fee(Integer total_fee) {
			this.total_fee = total_fee;
		}
		public String getSpbill_create_ip() {
			return spbill_create_ip;
		}
		public void setSpbill_create_ip(String spbill_create_ip) {
			this.spbill_create_ip = spbill_create_ip;
		}
		public String getNotify_url() {
			return notify_url;
		}
		public void setNotify_url(String notify_url) {
			this.notify_url = notify_url;
		}
		public String getTrade_type() {
			return trade_type;
		}
		public void setTrade_type(String trade_type) {
			this.trade_type = trade_type;
		}
		
		//参数转换map
		public Map<String,Object> toMap(){
	        Map<String,Object> map = new HashMap<String, Object>();
	        Field[] fields = this.getClass().getDeclaredFields();
	        for (Field field : fields) {
	            Object obj;
	            try {
	                obj = field.get(this);
	                if(obj!=null){
	                    map.put(field.getName(), obj);
	                }
	            } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	        return map;
	    }

}
