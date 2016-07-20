package io;

import java.io.IOException;

import com.mall.core.common.Constants;

import cache.BaseProperties;
import io.rong.ApiHttpClient;
import io.rong.models.FormatType;
import io.rong.models.SdkHttpResult;
import io.rong.models.TxtMessage;

public class Test {
	 
	@SuppressWarnings("unused")
	private static final String portraitUri="http://img01.hsz88.com/ImageExt/t5/4796594c24bd491087ecb8e155e4ae3f.jpg!!200x200";
	
	public static void main(String[] args) throws IOException {
		
//		CustomTxtMessage ctt = new CustomTxtMessage("sdfdsfs<br>ds\"\"fdsfdsfdsf");
//		System.out.println(ctt.toString());	
	
//		//获取toke
		try {
			
			BaseProperties.initMap("config/base.properties");
			TxtMessage  msg=new TxtMessage("广播消息");
			SdkHttpResult result=ApiHttpClient.publishBroadcast(Constants.system_user, msg, "pushContent", "pushData", FormatType.json);
			System.out.println(result.getResult());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
}
