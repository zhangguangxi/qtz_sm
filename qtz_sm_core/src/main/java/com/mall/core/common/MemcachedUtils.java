//package com.mall.core.common;
//
//import java.io.IOException;
//import java.io.Serializable;
//import java.util.concurrent.TimeoutException;
//
//import net.rubyeye.xmemcached.KeyIterator;
//import net.rubyeye.xmemcached.MemcachedClient;
//import net.rubyeye.xmemcached.MemcachedClientBuilder;
//import net.rubyeye.xmemcached.XMemcachedClientBuilder;
//import net.rubyeye.xmemcached.command.BinaryCommandFactory;
//import net.rubyeye.xmemcached.exception.MemcachedException;
//import net.rubyeye.xmemcached.utils.AddrUtil;
//
//import com.mall.core.common.Constants;
//import com.mall.core.common.StringUtil;
//import com.mall.core.log.LogTool;
//
///**
// * 集中式缓存 工具类
// * <p>Title:MemcachedUtils</p>
// * <p>Description:(用一句话描述该文件做什么)</p>
// * <p>Copyright: Copyright (c) 2014</p>
// * <p>Company: 深圳市好实再商贸有限公司</p>
// * @author 赵汉江 - zhaohanjiang
// * @version v1.0 2014-12-6
// */
//public class MemcachedUtils {
//	
//	private static LogTool log = LogTool.getInstance(MemcachedUtils.class);
//	
//	private static MemcachedClientBuilder builder = null;
//	private static MemcachedClient client = null;
//	
//	static{
//		String memcachedAddrs = Constants.getValueByKeyFromfilePro("memcachedAddrs");
//		builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(memcachedAddrs));
//		builder.setFailureMode(true);//宕机报警
//		builder.setCommandFactory(new BinaryCommandFactory());//使用二进制文件
//		builder.setConnectionPoolSize(10);//线程池大小
//		builder.getConfiguration().setSessionIdleTimeout(10 * 1000); /* 默认如果连接超过5秒没有任何IO操作发生即认为空闲并发起心跳检测，你可以调长这个时间：
//																                        设置为10秒;*/
//		
//		try {
//			client = builder.build();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		/*client.setMergeFactor(50);  
//		 * Xmemcached默认会做两个优化：将连续的单个get合并成一个multi get批量操作获取，将连续的请求合并成socket发送缓冲区大小的buffer发送。
//		   如果你对响应时间比较在意，那么可以将合并的因子减小，或者关闭合并buffer的优化：	
//		   默认是150，缩小到50*/
//		
//		client.setOptimizeMergeBuffer(false); //关闭合并buffer的优化
//		
//		client.setEnableHeartBeat(false);  //如果你对心跳检测不在意，也可以关闭心跳检测，减小系统开销
//		
//		builder.getConfiguration().setStatisticsServer(false); //这个关闭，仅仅是关闭了心跳的功能，客户端仍然会去统计连接是否空闲，禁止统计可以通过：
//												
//		client.setOpTimeout(5 *1000); /* 这是由于xmemcached的通讯层是基于非阻塞IO的，那么在请求发送给memcached之后，
//									 * 需要等待应答的到来，这个等待时间默认是1秒，如果 超过1秒就抛出java.util.TimeoutExpcetion给用户。
//									 * 如果你频繁抛出此异常，可以尝试将全局的等待时间设置长一些，如我在压测中 设置为5秒：*/
//	}
//
//	/** 
//	* 【获取缓存】(这里用一句话描述这个方法的作用)
//	* @param key
//	* @return
//	* @throws Exception  
//	*/
//	public static Object getAttribute(String key) throws Exception {
//		if(StringUtil.isEmpty(key)){
//			log.error("键不能为空");
//			throw new Exception("键不能为空");
//		}
//		Object obj = null;
////		MemcachedClient client = builder.build();
//		obj = client.get(key);
//		return obj;
//	}
//	
//	/** 
//	* 【保存缓存】(这里用一句话描述这个方法的作用)
//	* @param key
//	* @param obj
//	* @return
//	* @throws Exception  
//	*/
//	public static Object setAttribute(String key,Serializable obj) throws Exception {
//		if(StringUtil.isEmpty(key)){
//			log.error("键不能为空");
//			throw new Exception("键不能为空");
//		}
////		MemcachedClient client = builder.build();
//		/**
//		 * 第一个是存储的key名称，
//		 * 第二个是expire时间（单位秒），超过这个时间,memcached将这个数据替换出去，0表示永久存储（默认是一个月)
//		 * 第三个参数就是实际存储的数据
//		 */
//		client.set(key, 0, obj);
//		return obj;
//	}
//	
//	/** 
//	* 【删除缓存】(这里用一句话描述这个方法的作用)
//	* @param key
//	* @return
//	* @throws Exception  
//	*/
//	public static boolean removeAttribute(String key) throws Exception {
//		if(StringUtil.isEmpty(key)){
//			log.error("键不能为空");
//			throw new Exception("键不能为空");
//		}
////		MemcachedClient client = builder.build();
//		return client.delete(key);
//	}
//	
//	public static void main(String[] args) throws MemcachedException, InterruptedException, TimeoutException, IOException {
//		String memcachedIp = Constants.getValueByKeyFromfilePro("memcachedIp");
//		String memcachedPort = Constants.getValueByKeyFromfilePro("memcachedPort");
//		builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(memcachedIp+":"+memcachedPort));
//		builder.setFailureMode(true);//宕机报警
////		builder.setCommandFactory(new BinaryCommandFactory());//使用二进制文件
//		builder.setConnectionPoolSize(10);//线程池大小
//		builder.getConfiguration().setSessionIdleTimeout(10 * 1000); /* 默认如果连接超过5秒没有任何IO操作发生即认为空闲并发起心跳检测，你可以调长这个时间：
//																                        设置为10秒;*/
//		client = builder.build();
//		KeyIterator it=client.getKeyIterator(AddrUtil.getOneAddress("192.168.7.95:11211"));
//		while(it.hasNext())
//		{
//		   String key=it.next();
//		   System.out.println(key);
//		}
//	}
//
////	public static void main(String[] args) {
//////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.7.95:11211"));
////		// AddrUtil.getAddresses("server1:11211 server2:11211")
////		// 宕机报警
//////		builder.setFailureMode(true);
////		// 使用二进制文件
//////		builder.setCommandFactory(new BinaryCommandFactory());
////		/**
////		 * 设置连接池大小，即客户端个数 In a high concurrent enviroment,you may want to pool
////		 * memcached clients. But a xmemcached client has to start a reactor
////		 * thread and some thread pools, if you create too many clients,the cost
////		 * is very large. Xmemcached supports connection pool instreadof client
////		 * pool. you can create more connections to one or more memcached
////		 * servers, and these connections share the same reactor and thread
////		 * pools, it will reduce the cost of system. 默认的pool
////		 * size是1。设置这一数值不一定能提高性能，请依据你的项目的测试结果为准。初步的测试表明只有在大并发下才有提升。
////		 * 设置连接池的一个不良后果就是，同一个memcached的连接之间的数据更新并非同步的
////		 * 因此你的应用需要自己保证数据更新的原子性（采用CAS或者数据之间毫无关联）。
////		 */
////		builder.setConnectionPoolSize(10);
////		MemcachedClient client = null;
////		try {
////			client = builder.build();
////		} catch (IOException e1) {
////			e1.printStackTrace();
////		}
////		try {
////			/**
////			 * 第一个是存储的key名称，
////			 * 第二个是expire时间（单位秒），超过这个时间,memcached将这个数据替换出去，0表示永久存储（默认是一个月)
////			 * 第三个参数就是实际存储的数据
////			 */
////			client.set("hello", 0, "Hello,xmemcached");
////			String value = client.get("hello");
////			System.out.println("hello=" + value);
////			client.delete("hello");
////			value = client.get("hello");
////			System.out.println("hello=" + value);
////			// value=client.get(“hello”,3000);
////			/**
////			 * Memcached是通过cas协议实现原子更新，所谓原子更新就是compare and set，
////			 * 原理类似乐观锁，每次请求存储某个数据同时要附带一个cas值， memcached比对这个cas值与当前存储数据的cas值是否相等，
////			 * 如果相等就让新的数据覆盖老的数据，如果不相等就认为更新失败， 这在并发环境下特别有用
////			 */
////			GetsResponse<Integer> result = client.gets("a");
////			long cas = result.getCas();
////			// 尝试将a的值更新为2
////			if (!client.cas("a", 0, 2, cas)) {
////				System.err.println("cas error");
////			}
////		} catch (MemcachedException e) {
////			System.err.println("MemcachedClient operation fail");
////			e.printStackTrace();
////		} catch (TimeoutException e) {
////			System.err.println("MemcachedClient operation timeout");
////			e.printStackTrace();
////		} catch (InterruptedException e) {
////			// ignore
////		}
////		try {
////			// close memcached client
////			client.shutdown();
////		} catch (IOException e) {
////			System.err.println("Shutdown MemcachedClient fail");
////			e.printStackTrace();
////		}
////	}
////
////	public void test2() throws TimeoutException, InterruptedException,
////			MemcachedException, IOException {
////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
////				AddrUtil.getAddresses("localhost:11211"));
////		MemcachedClient client = builder.build();
////		client.flushAll();
////		if (!client.set("hello", 0, "world")) {
////			System.err.println("set error");
////		}
////		if (client.add("hello", 0, "dennis")) {
////			System.err.println("Add error,key is existed");
////		}
////		if (!client.replace("hello", 0, "dennis")) {
////			System.err.println("replace error");
////		}
////		client.append("hello", " good");
////		client.prepend("hello", "hello ");
////		String name = client.get("hello", new StringTranscoder());
////		System.out.println(name);
////
////		/**
////		 * 而删除数据则是通过deleteWithNoReply方法，这个方法删除数据并且告诉memcached
////		 * 不用返回应答，因此这个方法不会等待应答直接返回，特别适合于批量处理
////		 */
////		client.deleteWithNoReply("hello");
////	}
////
////	public void incrDecr() throws IOException, TimeoutException,
////			InterruptedException, MemcachedException {
////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
////				AddrUtil.getAddresses("localhost:11211"));
////		MemcachedClient client = builder.build();
////		/**
////		 * 第一个参数指定递增的key名称， 第二个参数指定递增的幅度大小， 第三个参数指定当key不存在的情况下的初始值。
////		 * 两个参数的重载方法省略了第三个参数，默认指定为0。
////		 */
////		assert (1 == client.incr("a", 5, 1));
////		assert (6 == client.incr("a", 5));
////		assert (10 == client.incr("a", 4));
////		assert (9 == client.decr("a", 1));
////		assert (7 == client.decr("a", 2));
////	}
////
////	public void counter() throws Exception {
////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
////				AddrUtil.getAddresses("localhost:11211"));
////		MemcachedClient client = builder.build();
////		Counter counter = client.getCounter("counter", 0);
////		counter.incrementAndGet();
////		counter.decrementAndGet();
////		counter.addAndGet(-10);
////	}
////
////	public void auth() throws Exception {
////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
////				AddrUtil.getAddresses("localhost:11211"));
////		builder.addAuthInfo(AddrUtil.getOneAddress("localhost:11211"),
////				AuthInfo.typical("cacheuser", "123456"));
////		// Must use binary protocol
////		builder.setCommandFactory(new BinaryCommandFactory());
////		MemcachedClient client = builder.build();
////	}
////
////	public void nioPool() throws Exception {
////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
////				AddrUtil.getAddresses("localhost:11211"));
////		builder.setConnectionPoolSize(5);
////	}
////
////	/**
////	 * 这里应该安装kestrel消息服务器，才能使用如下API生效
////	 * 
////	 * @throws IOException
////	 * @throws MemcachedException
////	 * @throws InterruptedException
////	 * @throws TimeoutException
////	 */
////	public void testGet() throws IOException, TimeoutException,
////			InterruptedException, MemcachedException {
////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
////				AddrUtil.getAddresses("localhost:11212"));
////		MemcachedClient client = builder.build();
////		String value = client.get("1");
////		System.out.println("hello=" + value);
////	}
////
////	public void testGet2() throws IOException, TimeoutException,
////			InterruptedException, MemcachedException {
////		MemcachedClientBuilder builder = new XMemcachedClientBuilder(
////				AddrUtil.getAddresses("localhost:11212"));
////		MemcachedClient client = builder.build();
////		// String value = client.get("srp_"+MD5Util.MD5("3rdsearch_周杰伦"));
////		// System.out.println(value);
////	}
//}
