package com.qtz.sm.wallet.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:WtCsIncome</p>
 * <p>Description:超市应收货款流水VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public class WtCsIncome extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1659554042873856L;

	
	private java.lang.Long preReleaseTime;
	/** 修正后的解冻时间 */
	private java.lang.Long releaseTime;
	/** 入账时间 */
	private java.lang.Long createTime;
	/** 收入对账结算记录表的主键ID*/
	private java.lang.Long incomeSettlementId;
	/** 商品ID*/
	private java.lang.Long goodsId;
	
		return preReleaseTime;
	}
	public void setPreReleaseTime(java.lang.Long preReleaseTime) {
		this.preReleaseTime = preReleaseTime;
	}
	public java.lang.Long getReleaseTime() {
		return incomeSettlementId;
	}
	public void setIncomeSettlementId(java.lang.Long incomeSettlementId) {
		this.incomeSettlementId = incomeSettlementId;
	}
	public java.lang.Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(java.lang.Long goodsId) {
		this.goodsId = goodsId;
	}
	public String toString() {
}