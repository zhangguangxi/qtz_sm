package com.qtz.sm;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mall.core.log.LogTool;

import cache.BaseProperties;

/**
 * 
 * <p>Title:StartService</p>
 * <p>Description:(服务启动)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 徐运贤- xuyunxian
 * @version v1.0 2016年5月25日
 */
public class StartService {

	  public static final LogTool log = LogTool.getInstance(StartService.class);

	  public static void main(String[] args) throws Exception {

	    BaseProperties.writePidToFile();
	    final URL url = StartService.class.getResource("/config/log4j.xml");
	    DOMConfigurator.configure(url);
	    BaseProperties.initMap("config/base.properties");
	    log.warn("========StartService准备启动服务========");

	    log.info("基础配置文件初始化");
	    ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

	    // new SessionAutoDes();//自动清理session
	    // 加载缓存配置
	    // SystemCache.getInstance().init();

	    // ctx.getBean("startQuertz"); //任务调度器

	    //int port = Integer.valueOf(BaseProperties.getBaseProperties("port"));
	    //String addr = BaseProperties.getBaseProperties("band_ip");

		  new Thread(new Runnable() {
			  @Override
			  public void run() {
				  while (true){
					  try {
						  TimeUnit.HOURS.sleep(1);
					  } catch (InterruptedException e) {
						  e.printStackTrace();
					  }
				  }
			  }
		  }).start();
		  log.warn("服务启动成功");
	  }
}
