package com.mall.core.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



/**
 * <p>Title:MD5Util</p>
 * <p>Description:(使用JDK自带API实现MD5算法对给定字符串加密)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-30
 */

public class MD5Util {
	
	/** 
	* 【】加密
	* @param pwd
	* @return  
	*/
	
	public static String encrypt(String pwd)
	{
		pwd = getMD5(pwd);
		StringBuilder str = new StringBuilder(pwd);
//		try {
////			str.insert(5, RuleTool.getRuleValue("encrypt"));
////			str.insert(15, RuleTool.getRuleValue("encrypt"));
////			str.insert(28, RuleTool.getRuleValue("encrypt"));
////			str.insert(35, RuleTool.getRuleValue("encrypt"));
//			str.setLength(40);
//		} catch (ServiceException e) {
//			log.error("生成密码加密字符异常！", e);
//		}
		
		return str.toString();
	}
	
	/** 
	* 【】解密
	* @param pwd
	* @return  
	*/
	
	public static String decrypt(String pwd)
	{
		StringBuilder str = new StringBuilder(pwd);
		str.delete(5, 7);
		str.delete(13, 15);
		str.delete(24, 26);
		str.delete(29, 31);
		str.setLength(32);
		
		return str.toString();
	}
    
    /** 
    * 【MD5加密】
    * @param source
    * @return  
    */
    
    public static String getMD5(String pwd){
        String s=null; 
        byte[] source = pwd.getBytes();
        //用来将字节转换成16进制表示的字符  
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9',  
                'a','b','c','d','e','f'};  
        try {  
            MessageDigest md=MessageDigest.getInstance("MD5");  
            md.update(source);  
            //MD5的计算结果是一个128位的长整数，用字节表示为16个字节  
            byte[] tmp=md.digest();  
            //每个字节用16进制表示的话，使用2个字符(高4位一个,低4位一个)，所以表示成16进制需要32个字符  
            char[] str=new char[16*2];  
            int k=0;//转换结果中对应的字符位置  
            for(int i=0;i<16;i++){//对MD5的每一个字节转换成16进制字符  
                byte byte0=tmp[i];  
                str[k++]=hexDigits[byte0>>>4 & 0xf];//对字节高4位进行16进制转换  
                str[k++]=hexDigits[byte0 & 0xf];    //对字节低4位进行16进制转换  
            }  
            s=new String(str);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }
        return s;  
    }
    
	public static void main(String[] args) {
		System.out.println(MD5Util.getMD5("123456"));
	}
}