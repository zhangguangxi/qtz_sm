/**    
 * 文件名：ShortUrlGenerator.java    
 *    
 * 版本信息：    
 * 日期：2014年4月24日    
 * Copyright 东方开放科技 Corporation 2014     
 * 版权所有    
 *    
 */
package com.mall.core.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * Title:ShortUrlGenerator
 * </p>
 * <p>
 * Description:()
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年4月8日
 */
public class ShortUrlGenerator {

	public static String shortParams(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "qingtianzhu.team.com";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z"

		};
		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = MD5Util.getMD5(key + url);
		String hex = sMD5EncryptResult;

		// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
		String sTempSubString = hex.substring(8, 8 + 8);

		// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
		// long ，则会越界
		long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
		String outChars = "";
		for (int j = 0; j < 6; j++) {
			// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
			long index = 0x0000003D & lHexLong;
			// 把取得的字符相加
			outChars += chars[(int) index];
			// 每次循环按位右移 5 位
			lHexLong = lHexLong >> 5;
		}
		// 把字符串存入对应索引的输出数组

		return outChars;
	}

	public static String[] shortUrl(String url) {
		// 可以自定义生成 MD5 加密字符传前的混合 KEY
		String key = "qingtianzhu.team.com";
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
				"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
				"B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
				"W", "X", "Y", "Z"

		};
		// 对传入网址进行 MD5 加密
		String sMD5EncryptResult = MD5Util.getMD5(key + url);
		String hex = sMD5EncryptResult;

		String[] resUrl = new String[4];
		for (int i = 0; i < 4; i++) {

			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);

			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
			// long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl;
	}

	public static String randomAccount(String mix) {
		String key = shortParams(System.currentTimeMillis() + "&" + mix);
		return key;
	}

	public static void main(String[] args) {
		// 长连接： http://tech.sina.com.cn/i/2011-03-23/11285321288.shtml
		// 新浪解析后的短链接为： http://t.cn/h1jGSC
		// String sLongUrl =
		// "http://tech.sina.com.cn/i/2011-03-23/11285321288.shtml" ; //
		// 3BD768E58042156E54626860E241E999

		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 10000000; i++) {
			String key = shortParams(String.valueOf(System.currentTimeMillis()));
			list.add(key);
		}
		System.out.println(list.size());
		// System. out .println(shortParams("123456@123"));
	}
}
