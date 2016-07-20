package com.qtz.sm.wallet.config;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

//@Component
@Configuration
@PropertySources(
	@PropertySource(ignoreResourceNotFound=true, value="classpath:config/base.properties")
)
public class WalletConfig {
	 
	@Autowired
	Environment env;
	
	public static BigDecimal CsProfitRate = BigDecimal.valueOf(0.9);	//4位精度够了
	public static BigDecimal BldglProfitRate = BigDecimal.valueOf(0.9); //4位精度
	
	@Bean
	public int init(){
		try{
			CsProfitRate = env.getProperty("profit.rate.cs", BigDecimal.class, CsProfitRate);
			BldglProfitRate = env.getProperty("profit.rate.bldgl", BigDecimal.class, BldglProfitRate);
		} catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
}
