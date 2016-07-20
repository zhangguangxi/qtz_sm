package com.mall.core.common;

import java.math.BigDecimal;

//*****************************************************************************
/**
 * <p>Title:Arith</p>
 * <p>Description:(精确的计算工具)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - lichangbo
 * @version v1.0 2014-10-28
 */
//*****************************************************************************
public class Arith {
	//默认除法运算精度
    private static final int DEF_DIV_SCALE = 2;
    
    private static final double EARTH_RADIUS = 6371.393;
    
    //这个类不能实例化
    private Arith(){
    }
    /**
     * 提供精确的加法运算。
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    /**
     * 提供精确的减法运算。
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    } 
    /**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * 小数点以后2位，以后的数字舍。
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1,double v2){
        return div(v1,v2,DEF_DIV_SCALE);
    }
    /** 
     * 对double数据进行取精度. 
     * <p> 
     * For example: <br> 
     * double value = 100.345678; <br> 
     * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br> 
     * ret为100.3457 <br> 
     *  
     * @param value 
     *            double数据. 
     * @param scale 
     *            精度位数(保留的小数位数). 
     * @param roundingMode 
     *            精度取值方式. 
     * @return 精度计算后的数据. 
     */ 
    public static double round(double value, int scale, int roundingMode) {  
        BigDecimal bd = new BigDecimal(value);  
        bd = bd.setScale(scale, roundingMode);  
        double d = bd.doubleValue();  
        bd = null;  
        return d;  
    }
    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字舍。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1,double v2,int scale){
        if(scale<0){
            throw new IllegalArgumentException(
                "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_DOWN).doubleValue();
    }
    
    
    /***
	 * 计算两点间的距离  
	 * @return
	 */
	public static double getPointDistance(double lat1, double lon1, double lat2, double lon2) {
		double result = 0;
		double radLat1 = radian(lat1);
		double ratlat2 = radian(lat2);
		double a = radian(lat1) - radian(lat2);
		double b = radian(lon1) - radian(lon2);
		result = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(ratlat2)
				* Math.pow(Math.sin(b / 2), 2)));
		result = result * EARTH_RADIUS;
		result = Math.round(result * 1000);
//		 new   BigDecimal(2363.6555225).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
		return result;
	}

	//  转换为弧度
	public static double radian(double d) {
		return (d * Math.PI) / 180.00;
	}
	public static void main(String[] args) {
		//double pointDistance = getPointDistance(22.584555589, 113.865777789, 22.57986596477807, 113.8710102638402);
//		double pointDistance = getPointDistance(22.56359, 113.86828, 22.58137, 113.85450);
		double rond=round(20.02123, 1, BigDecimal.ROUND_UP);
		System.out.println(rond);
		System.out.println(2.10);
	}
}

