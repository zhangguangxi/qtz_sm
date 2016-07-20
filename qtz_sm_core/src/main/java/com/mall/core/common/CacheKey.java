package com.mall.core.common;

import java.util.List;

import com.mall.core.exception.WebInitException;
import com.mall.core.log.LogTool;


/**
 * <p>Title:CacheKey</p>
 * <p>Description:(缓存前端加密密钥)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-30
 */

public class CacheKey {

	private static LogTool logger = LogTool.getInstance(CacheKey.class);// 日志对象
	
	
	/** 
	* 【解析密钥xml文件】 
	*/
	
	@SuppressWarnings("unchecked")
	public static void initKeys() throws WebInitException
	{
		logger.info("缓存前端加密文件开始");
		try {
			String xml_url = "config/xml/key.xml";
			List<String> list = (List<String>) ModifiedUtil.parseXML(xml_url, "element", String.class);
			DesUtils.keyList.addAll(list);
			
		} catch (Exception e) {
			logger.error("缓存前端加密文件错误", e);
		}
		logger.info("缓存前端加密文件结束");
	}
}
