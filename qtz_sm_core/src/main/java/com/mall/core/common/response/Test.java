package com.mall.core.common.response;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.exception.DaoException;
import com.thoughtworks.xstream.XStream;

public class Test {
	
	private static  String url = "http://61.145.229.29:9003/MWGate/wmgw.asmx/MongateSendSubmit";
	private static String userName = "JC2309";
	private static String password = "589102";
	public static void main(String[] args) throws Exception {
		
		RespMsg rm = new RespMsg();
		
		Field[] fields = rm.getClass().getDeclaredFields(); 
		 for (Field field : fields) 
		 {
			 //String varName = field.getName();  
			// System.out.println(varName);
			 boolean accessFlag = field.isAccessible();  
		      if (!accessFlag) {  
		        field.setAccessible(true);  
		      }  
		      Object param = field.get(rm); 
		      if (param instanceof JSONObject){
		    	  @SuppressWarnings("unused")
				JSONObject jp = (JSONObject) param;
		    	 // System.out.println(jp.getString("code")+"="+jp.getString("msg"));
		      }
		      
		 }
//		sendSMS("15914128820", "员工您好，感谢您对此次测试的配合。");

		sendMesQxtSms("18610998446", "【爱免费】尊敬的用户：您的验证码为：898989(此验证码1分钟内有效)");
//		
//		String xml="<?xml version=\"1.0\"encoding=\"gbk\"?><response><code>03</code><message><desmobile>13632901570</desmobile><msgid>D4770815052211203800</msgid></message></response>";
//		xml = xml.replace("<?xml version=\"1.0\"encoding=\"gbk\"?><response><code>","");
//		xml = xml.substring(0, 2);
//		System.out.println(xml);
//		Map<String,Class> map = new HashMap<String, Class>();
//		map.put("QxtSms",QxtSms.class);
//		QxtSms qxt = (QxtSms) xml2Bean(map, xml);
//		System.out.println(qxt.getCode());
		
		
	}
	
	
	/** 
     * 将XML转换为Bean 
     * 
     * @param clazzMap 别名-类名映射Map 
     * @param xml      要转换为bean对象的xml字符串 
     * @return Java Bean对象 
     */ 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object xml2Bean(Map<String, Class> clazzMap, String xml) { 
        XStream xstream = new XStream(); 
        for (Iterator it = clazzMap.entrySet().iterator(); it.hasNext();) { 
            Map.Entry<String, Class> m = (Map.Entry<String, Class>) it.next(); 
            xstream.alias(m.getKey(), m.getValue()); 
        } 
        Object bean = xstream.fromXML(xml); 
        return bean; 
    } 
	
	
	/**
	 * 
	* 【企信通短信平台发送短信】(这里用一句话描述这个方法的作用)
	* @param phone
	* @param mes
	* @throws DaoException
	 */
	private static void sendMesQxtSms(String phone,String mes)throws DaoException{
		
		 int    statusCode = 0;
		 GetMethod method = null;  
		 HttpClient httpClient=null;
		try {
			httpClient = new HttpClient();
			StringBuffer http = new StringBuffer();
			http.append("http://221.179.180.158:9007/QxtSms/QxtFirewall?")
			    .append("OperID=").append("amfei")
			    .append("&OperPass=").append("amfei66")
			    //.append("&SendTime=&ValidTime=&AppendID=")
			    .append("&DesMobile=").append(phone)
			    .append("&Content=").append(URLEncoder.encode(mes,"GBK"))
			    .append("&ContentType=15");
			
			method = new GetMethod(http.toString());//SMS_MENG_WANG_URL
			
			// 设置  请求超时为 5 秒   
			method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,  5000 );   
			
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,  
			                new DefaultHttpMethodRetryHandler(3, false));  
			//执行请求
			statusCode = httpClient.executeMethod(method);
			 /* 4 判断访问的状态码 */   
             if  (statusCode != HttpStatus.SC_OK) {   
            	 System.out.println(method.getStatusLine());
            	 throw new DaoException("短信发送失败");
             }   
             // HTTP响应头部信息，这里简单打印   
             Header[] headers = method.getResponseHeaders();   
              for  (Header h : headers)   
                 System.out.println(h.getName() +  "------------ "  + h.getValue());   
//                
              // 读取 HTTP 响应内容，这里简单打印网页内容   
              byte [] responseBody = method.getResponseBody(); // 读取为字节数组   
              String response =  new  String(responseBody, "GBK");   
              System.out.println( "----------response:" +response); 
              
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}finally{
			method.releaseConnection();
		}
	}
	
	
	
	/**
	 * 发送短信
	 * @param phone  接受短信手机号码 多个手机用 ， 隔开eg:phone1,phone2
	 * @param mes  短信内容
	 * @return 200  成功     
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String sendSMS(String phone,String mes) throws Exception
	{
		
		 String soapRequestData =null;
		 int    statusCode = 0;
		 PostMethod postMethod=null;
		try {
			HttpClient httpClient = new HttpClient();
			postMethod = new PostMethod(url);
			postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		
		    NameValuePair[]    data    = {    
		        new    NameValuePair("userId",userName),  
		        new    NameValuePair("password",password),  
		        new    NameValuePair("pszMobis",phone),  
		        new    NameValuePair("pszMsg",mes),  
		        new    NameValuePair("iMobiCount", phone.split(",").length+""),  
		        new    NameValuePair("pszSubPort", "*")};  
		        postMethod.setRequestBody(data);  
		        statusCode    =    httpClient.executeMethod(postMethod); 
		        soapRequestData =    postMethod.getResponseBodyAsString(); 
		        soapRequestData = soapRequestData.replace("<?xml version=\"1.0\" encoding=\"utf-8\"?><string xmlns=\"http://tempuri.org/\">", "");
		        soapRequestData = soapRequestData.replace("</string>", "");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
System.out.println(">>>>> 接受短信手机："+phone+";连接状态码："+statusCode+";发送返回码："+soapRequestData);

		return soapRequestData;
	}
	
}
