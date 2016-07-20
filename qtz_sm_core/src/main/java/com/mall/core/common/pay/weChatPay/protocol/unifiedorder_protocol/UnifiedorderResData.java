package com.mall.core.common.pay.weChatPay.protocol.unifiedorder_protocol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * <p>Title:UnifiedorderResData</p>
 * <p>Description:(统一下单返回数据)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年10月10日
 */
public class UnifiedorderResData {
	//返回状态码
	private String return_code = "";
	//返回信息
    private String return_msg = "";
  //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    //公众id
    private String appid = "";
    //商户号
    private String mch_id = "";
    //微信返回的随机字符串
    private String nonce_str = "";
    //微信返回的签名
    private String sign = "";
    //业务结果	SUCCESS/FAIL
    private String result_code = "";
    //错误代码
    private String err_code = "";
    //err_code_des
    private String err_code_des = "";
    //调用接口提交的终端设备号
    private String device_info = "";
    
    
    
    
  //业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）
    //交易类型
    private String trade_type = "";
    //预支付交易会话标识
    private String prepay_id="";
    //二维码连接
    private String code_url;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
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
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getCode_url() {
		return code_url;
	}
	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
    
    
    
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
