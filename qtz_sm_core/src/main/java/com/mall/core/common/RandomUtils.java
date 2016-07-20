package com.mall.core.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 * <p>Title:RandomUtils</p>
 * <p>Description:(随机数工具包)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年3月12日
 */
public class RandomUtils {
	
	//验证码类型，评论
		public static final String VERIFY_TYPE_COMMENT="VERIFY_TYPE_COMMENT";
		
		/**
		 * 验证码类型为仅数字 0~9
		 */
		public static final int TYPE_NUM_ONLY=0;

		/**
		 * 验证码类型为仅字母，即大写、小写字母混合
		 */
		public static final int TYPE_LETTER_ONLY=1;
		
		/**
		 * 验证码类型为数字、大写字母、小写字母混合
		 */
		public static final int TYPE_ALL_MIXED=2;

		/**
		 * 验证码类型为数字、大写字母混合
		 */
		public static final int TYPE_NUM_UPPER=3;		
		
		/**
		 * 验证码类型为数字、小写字母混合
		 */
		public static final int TYPE_NUM_LOWER=4;	
		
		/**
		 * 验证码类型为仅大写字母
		 */
		public static final int TYPE_UPPER_ONLY=5;
		
		/**
		 * 验证码类型为仅小写字母
		 */
		public static final int TYPE_LOWER_ONLY=6;
		
	/**
	 * 生成随机组合的数字字符串
	 * @param length 长度大于 0 , 小于 10
	 * @return
	 */
	public static String randDigitStr(int length) {
		if(length > 10) {
			length = 10;
		} else if (length <= 0){
			length = 1;
		}
		String[] chars = new String[] {"0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9"};
		List<String> list = Arrays.asList(chars);
		Collections.shuffle(list);
		List<String> list2 = list.subList(0, length);
		StringBuffer sb = new StringBuffer();
		for(String i : list2) {
			sb.append(i);
		}
		return sb.toString();
	}
	
	/**
	 * 生成验证码字符串
	 * @param type 验证码类型，参见本类的静态属性
	 * @param length 验证码长度，大于0的整数
	 * @param exChars 需排除的特殊字符（仅对数字、字母混合型验证码有效，无需排除则为null）
	 * @return 验证码字符串
	 */
	public static String generateTextCode(int type,int length,String exChars){
		
		if(length<=0) return "";
		
		StringBuffer code=new StringBuffer();
		int i=0;
		Random r=new Random();
		
		switch(type)
		{
		
		//仅数字
		case TYPE_NUM_ONLY:
			while(i<length){
	            int t=r.nextInt(10);
				if(exChars==null||exChars.indexOf(t+"")<0){//排除特殊字符
					code.append(t);
					i++;
				}
			}
			break;
			
	    //仅字母（即大写字母、小写字母混合）
		case TYPE_LETTER_ONLY:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97||(t>=65&&t<=90))&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;	
				}
			}
			break;
		
		//数字、大写字母、小写字母混合
		case TYPE_ALL_MIXED:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97||(t>=65&&t<=90)||(t>=48&&t<=57))&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;	
			
		//数字、大写字母混合
		case TYPE_NUM_UPPER:
			while(i<length){
				int t=r.nextInt(91);
				if((t>=65||(t>=48&&t<=57))&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
			break;		
			
		//数字、小写字母混合
		case TYPE_NUM_LOWER:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97||(t>=48&&t<=57))&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
		break;		
			
		//仅大写字母
		case TYPE_UPPER_ONLY:
			while(i<length){
				int t=r.nextInt(91);
				if((t>=65)&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
		break;				
			
		//仅小写字母
		case TYPE_LOWER_ONLY:
			while(i<length){
				int t=r.nextInt(123);
				if((t>=97)&&(exChars==null||exChars.indexOf((char)t)<0)){
					code.append((char)t);
					i++;
				}
			}
		break;				
		
		}
		
		return code.toString();
	}
	private static Set<Integer> set=new HashSet<Integer>();
	
	static{
			//a();
	}
	
	public static Set<Integer> getSet() {
		return set;
	}
	
	 /**
		 * 随机指定范围内N个不重复的数
		 * 利用HashSet的特征，只能存放不同的值
		 * @param min 指定范围最小值
		 * @param max 指定范围最大值
		 * @param n 随机数个数
		 * @param HashSet<Integer> set 随机数结果集
		 */
	    public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
	        if (n > (max - min + 1) || max < min) {
	            return;
	        }
	        for (int i = 0; i < n; i++) {
	            // 调用Math.random()方法
	            int num = (int) (Math.random() * (max - min)) + min;
	            set.add(num);// 将不同的数存入HashSet中
	        }
	        int setSize = set.size();
	        // 如果存入的数小于指定生成的个数，则调用递归再生成剩余个数的随机数，如此循环，直到达到指定大小
	        if (setSize < n) {
	        	randomSet(min, max, n - setSize, set);// 递归
	        }
	    }
	    /**
		 * 随机指定范围内N个不重复的数
		 * 在初始化的无重复待选数组中随机产生一个数放入结果中，
		 * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
		 * 然后从len-2里随机产生下一个随机数，如此类推
		 * @param max  指定范围最大值
		 * @param min  指定范围最小值
		 * @param excludes 排除随机数
		 * @param n  随机数个数
		 * @return int[] 随机数结果集
		 */
		public static int[] randomArray(int min,int max,int n,List<Integer> excludes){
			int len = max-min+1;
			
			if(max < min || n > len){
				return null;
			}
			
			//初始化给定范围的待选数组
			int[] source=new int[len-excludes.size()];
	        for (int i = min; i < min+len; i++){
	        	if(excludes.contains(i)){
	        		continue;
	        	}
	        	int index=(i-min)-excludes.size();
	        	
	        	source[index>0?index:0]=i;
	        }
//	        for (int i = 0; i < source.length; i++) {
//				System.out.println(source[i]);
//			}
	        len=len-excludes.size();
	        int[] result = new int[n];
	        Random rd = new Random();
	        int index = 0;
	        for (int i = 0; i < result.length; i++) {
	        	//待选数组0到(len-2)随机一个下标
	            index = Math.abs(rd.nextInt() % len--);
	            //将随机到的数放入结果集
	            result[i] = source[index];
	            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
	            source[index] = source[len];
	        }
	        return result;
		}
	/**
	 * 
	  * 【获取优惠卷 验证码】
	  * @param excludes 排除
	  * @return  
	  * @time:2015年6月2日 上午11:59:50
	  * @author 涂鑫
	  * @version
	 */
	private static  RandomUtils randomUtils=new RandomUtils();
	
	public static int getCouponCode(List<Integer> excludes)	{
		int[] reult1 = randomArray(randomUtils.getMin(),randomUtils.getMax(),1,excludes );
		return reult1.length>0?reult1[0]:0;
	}
	
	private int min =100000;
	
	private int max =999999;
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		List<Integer> excludes =new ArrayList<Integer>();
//		excludes.add(1);
//		excludes.add(2);
//		excludes.add(3);
//		excludes.add(4);
//		excludes.add(5);
//		excludes.add(6);
//		excludes.add(7);
//		excludes.add(8);
//		excludes.add(9);
		for (int i =0 ;  i < 10000;i++) {
		  String randDigitStr = randDigitStr(6);
		  if(randDigitStr.length()<6)
		  System.out.println(randDigitStr);
        }
		
	//	System.out.println(getCouponCode(excludes));
//		int[] reult1 = randomArray(1,10,1,excludes );
//		 System.out.println("#########################");
//		for (int i = 0; i < reult1.length; i++) {
//			System.out.println(reult1[i]);
//		}
	}
}
