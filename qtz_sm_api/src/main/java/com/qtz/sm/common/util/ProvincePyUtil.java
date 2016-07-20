package com.qtz.sm.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:(ProvincePyUtil)<br/>
 * Description:(获取省份首字母)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * 
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016年4月22日
 */
public class ProvincePyUtil {

	private static final Map<Integer, String> pyMap = new HashMap<Integer, String>(34);

	static {
		pyMap.put(110000, "BJ");
		pyMap.put(120000, "TJ");
		pyMap.put(130000, "HB");
		pyMap.put(140000, "SX");
		pyMap.put(150000, "NM");
		pyMap.put(210000, "LN");
		pyMap.put(220000, "JL");
		pyMap.put(230000, "HL");
		pyMap.put(310000, "SH");
		pyMap.put(320000, "JS");
		pyMap.put(330000, "ZJ");
		pyMap.put(340000, "AH");
		pyMap.put(350000, "FJ");
		pyMap.put(360000, "JX");
		pyMap.put(370000, "SD");
		pyMap.put(410000, "HN");
		pyMap.put(420000, "HB");
		pyMap.put(430000, "HN");
		pyMap.put(440000, "GD");
		pyMap.put(450000, "GX");
		pyMap.put(460000, "HN");
		pyMap.put(500000, "CQ");
		pyMap.put(510000, "SC");
		pyMap.put(520000, "GZ");
		pyMap.put(530000, "YN");
		pyMap.put(540000, "XZ");
		pyMap.put(610000, "SX");
		pyMap.put(620000, "GS");
		pyMap.put(630000, "QH");
		pyMap.put(640000, "NX");
		pyMap.put(650000, "XJ");
		pyMap.put(710000, "TW");
		pyMap.put(810000, "XG");
		pyMap.put(820000, "AM");
	}

	/**
	 * 获取省份拼音字母前缀，没有返回 null
	 * 
	 * @param key
	 * @return
	 */
	public static final String getFirstPY(Integer key) {
		return pyMap.get(key);
	}

}
