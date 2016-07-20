package cache;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Map;

import com.mall.core.common.UtilsOperaProperies;
/**
 * 
 * <p>Title:BaseProperties</p>
 * <p>Description:(配置文件 缓存)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - changbo.li
 * @version v1.0 2015年3月10日
 */
public class BaseProperties {
	
	private static Map<String,String> map;
	

	
	/**
	 * 
	* 【得到配置参数】(这里用一句话描述这个方法的作用)
	* @param key
	* @return
	 */
	public static String getBaseProperties(String key){
		
		if(null == map) return null;
		
		return map.get(key);
	}
	
	/**
	 * 
	* 【配置参数初始化】(这里用一句话描述这个方法的作用)
	* @param filePath  配置文件url
	 */
	public static void initMap(String filePath)
	{
		map = UtilsOperaProperies.readProperties(filePath);
	}


	/**
	 * 【Linux下和shell脚本配合】(info)
	 */
	public static void writePidToFile() {
		if(System.getProperty("os.name").toLowerCase().equals("linux")){
			String fileName = System.getProperty("pid.file");
			if(null == fileName)
				return;//console 模式下不需要
//				throw new IllegalStateException("for the linux system,you need to set the system env \"pid.file\" ");
			File file = new File(fileName);
			file.deleteOnExit();
			try {
				FileWriter fw = new FileWriter(file);
				fw.write(ManagementFactory.getRuntimeMXBean().getName().split("@")[0]);
				fw.flush();
				fw.close();
			}
			catch (IOException e) {
				throw new IllegalAccessError("can't write to the pid.file \""
						+ file.getAbsolutePath() + "\"");
			}
		}
	}

	
	
}
