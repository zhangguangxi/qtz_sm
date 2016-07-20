package com.mall.core.common;

import java.util.UUID;

/**
 * UUID工厂类
 * 
 * @author renwc
 * @since 2012-11-14
 */
public class UUIDFactory {
	
	/**
	 * 生产UUID
	 * 部署前须校准服务器时钟 *
	 * 
	 * @return
	 */
	public static String newUUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

	/*
	 * 单元测试方法
	 * @param args
	 */
	public static void main(String[] arg){
		//正确性测试
		String uuid = newUUID();
		System.out.println("uuid.length()="+uuid.length()+" : "+uuid);
		
		//并发测试
		java.util.concurrent.ExecutorService pool = java.util.concurrent.Executors.newFixedThreadPool(5);
		for (int i = 0; i < 3; i++) {
			pool.execute(new Runnable(){
				public void run() {
					int j = 1;
					do{
						System.out.println(j+"-"+newUUID());
					}while(7 > j++);
				}
			});
		}
		pool.shutdown();
	}

}
