package com.qtz.sm.filter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 
 * <p>Title:ContextListener</p>
 * <p>Description:(监听器初始化项目)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年11月6日
 */
@Component
public class ContextListener implements ApplicationListener<ContextRefreshedEvent>  {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent()==null){
		//SysConfigurService sysConfigurService = event.getApplicationContext().getBean(SysConfigurService.class);
		try {
			//WXPay.initSDKConfiguration(sysConfigurService.getBaseProperties("weChat.key"), sysConfigurService.getBaseProperties("appID"), sysConfigurService.getBaseProperties("mchID"), null, sysConfigurService.getBaseProperties("certLocalPath"), sysConfigurService.getBaseProperties("certPassword"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		}
	}

	
}
