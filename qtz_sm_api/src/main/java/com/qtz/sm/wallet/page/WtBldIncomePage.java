package com.qtz.sm.wallet.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:WtBldIncomePage</p>
 * <p>Description:便利店分润流水分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public class WtBldIncomePage extends Pager<com.qtz.sm.wallet.vo.WtBldIncome,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1659554032388097L;

	
	private java.lang.Long preReleaseTime;
	/** 修正后的解冻时间 */
	private java.lang.Long releaseTime;
	/** 入账时间 */
	private java.lang.Long createTime;
	/** 收入对账结算记录表的主键ID*/
	private java.lang.Long incomeSettlementId;
	/** 查询入账时间的开始时间 */
	private java.lang.Long startTime;
	
	/** 查询入账时间的结束时间 */
	private java.lang.Long endTime;
	/** 商品ID*/
	private java.lang.Long goodsId;
	
	
	public java.lang.Long getStartTime() {
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
	@Override
	public String toString() {
		return "[dmId=" + dmId + ",goodsId=" +  goodsId+",  ownerId=" + ownerId + ", orderId=" + orderId + ", orderItemId="+ orderItemId + ", skuId=" + skuId + ", price=" + price + ", total=" + total + ", amount=" + amount+ ", createTime=" + createTime + ", releaseTime=" + releaseTime + ", updateTime=" + updateTime+ ", stat=" + stat + ", goodsName=" + goodsName + ", skuDescription=" + skuDescription+ ", incomeSettlementId=" + incomeSettlementId + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
}