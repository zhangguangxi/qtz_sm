package com.qtz.sm.batch.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:CsBatOrderPage</p>
 * <p>Description:批发单基础信息分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
public class CsBatOrderPage extends Pager<com.qtz.sm.batch.vo.CsBatOrder,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1660901523458049L;

	
	/** 下单开始时间 */
	private java.lang.Long startTime;
	/** 下单结束时间 */
	private java.lang.Long endTime;
		return startTime;
	}
	public void setStartTime(java.lang.Long startTime) {
		this.startTime = startTime;
	}
	public java.lang.Long getEndTime() {
		return endTime;
	}
	public void setEndTime(java.lang.Long endTime) {
		this.endTime = endTime;
	}
	public String toString() {
}