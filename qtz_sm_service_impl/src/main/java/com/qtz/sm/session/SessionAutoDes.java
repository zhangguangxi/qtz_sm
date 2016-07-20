package com.qtz.sm.session;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.mall.core.common.SpringContextHolder;
import com.mall.core.exception.ServiceException;
import com.mall.core.vo.DdmSession;
import com.qtz.sm.session.service.SessionService;

import cache.BaseProperties;

/**(
 * 
 * <p>Title:SessionManage</p>
 * <p>Description:(session自动销毁)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年3月12日
 */
public class SessionAutoDes{
	
	public SessionAutoDes(){
		System.out.println("session 自动清理清除线程正在启动");
		Timer timer = new Timer(false);
		//每隔三个小时执行
		timer.scheduleAtFixedRate(task, 1000, 1000 * 60*60*3);		
	}
	private TimerTask task = new TimerTask() {
		@Override
		public void run() {
			SessionService sessionService=(SessionService)SpringContextHolder.getApplicationContext().getBean("sessionServiceImpl");
			try {
				List<DdmSession> appAllSession = sessionService.getAPPAllSession();
				for (DdmSession ddmSession : appAllSession) {
					System.out.println("正在销毁session...");
					long lastOperaTime = ddmSession.getLastOperaTime();
					long  nowTime=new Date().getTime();
					if(lastOperaTime+Long.valueOf(BaseProperties.getBaseProperties("APP_FAILURE_Time"))>=nowTime){
						//如果最后操作时间+过期时间大于等于现在时间就销毁session
						sessionService.removeAppSession(ddmSession.getId());
					}
				}
			} catch (ServiceException e) {
				System.out.println("查询session 出错 ....");
				e.printStackTrace();
			}
		}
		
	};
}
