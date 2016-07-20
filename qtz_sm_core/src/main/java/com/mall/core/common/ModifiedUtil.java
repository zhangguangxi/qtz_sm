package com.mall.core.common;

import java.io.InputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * <p>Title:ModifiedUtil</p>
 * <p>Description:(xml解析工具类)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-28
 */

public class ModifiedUtil {
	
	
	/**
	 * 通用xml文件解析方法
	 * @author 唐礼军
	 * 创建日期：2014-9-4下午02:05:45
	 * 修改日期：
	 * 
	 * @param xml_url 如 config/xml/key.xml
	 * 根节点名必须为 <root></root>
	 * @param typeName xml节点名 此节点下的节点名必须与要映射的类的属性名对应
	 * @param type 要映射的类名
	 * @return
	 */
	public static List<?> parseXML(String xml_url, String typeName,Class<?> type)
	{
		InputStream is = null;
		XStream xstream = new XStream(new DomDriver());
		is=ModifiedUtil.class.getClassLoader().getResourceAsStream(xml_url);
		xstream.alias("root", List.class);
		xstream.alias(typeName, type);
		Object o = xstream.fromXML(is);
		if (o instanceof List) {
			List<?> list = (List<?>) o;
			return list;
		}
		return null;
	}
}
