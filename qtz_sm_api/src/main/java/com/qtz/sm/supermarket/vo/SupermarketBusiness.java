package com.qtz.sm.supermarket.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:SupermarketBusiness</p>
 * <p>Description:超市运营信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-14
 */
public class SupermarketBusiness extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1706242999584768L;

	
	
	//*******接收参数*******//
	/** 营业开始时间（精确到时分） */
	private String serviceStartTimeStr;
	/** 营业结束时间（精确到时分） */
	private String serviceEndTimeStr;
	
	
	public String getServiceStartTimeStr() {
		return serviceStartTimeStr;
	}
	public void setServiceStartTimeStr(String serviceStartTimeStr) {
		this.serviceStartTimeStr = serviceStartTimeStr;
	}
	public String getServiceEndTimeStr() {
		return serviceEndTimeStr;
	}
	public void setServiceEndTimeStr(String serviceEndTimeStr) {
		this.serviceEndTimeStr = serviceEndTimeStr;
	}
}