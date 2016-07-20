package com.qtz.sm.wallet.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:WtGylWithdrawalsPage</p>
 * <p>Description:供应链提现流水分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public class WtGylWithdrawalsPage extends Pager<com.qtz.sm.wallet.vo.WtGylWithdrawals,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1659115039688705L;

	
	
	/** 创建时间开始查询时间 */
	private java.lang.Long startCreateOn;
	/** 创建时间结束查询时间 */
	private java.lang.Long endCreateOn;

	
	public java.lang.Long getStartCreateOn() {
		return startCreateOn;
	}

	public void setStartCreateOn(java.lang.Long startCreateOn) {
		this.startCreateOn = startCreateOn;
	}

	public java.lang.Long getEndCreateOn() {
		return endCreateOn;
	}

	public void setEndCreateOn(java.lang.Long endCreateOn) {
		this.endCreateOn = endCreateOn;
	}
	
}