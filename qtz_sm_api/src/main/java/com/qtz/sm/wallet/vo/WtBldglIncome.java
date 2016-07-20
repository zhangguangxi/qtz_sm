package com.qtz.sm.wallet.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:WtBldglIncome</p>
 * <p>Description:便利店管理公司应收货款流水VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-12
 */
public class WtBldglIncome extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1659554040088576L;

	//	/** 主键ID */
//	private java.lang.Long dmId;
	/** 收款人ID */
	private java.lang.Long ownerId;
	/** 订单ID */
	private java.lang.Long orderId;
	/** 订单项ID */
	private java.lang.Long orderItemId;
	/** 商品SKU */
	private java.lang.Long skuId;
	/** 商品单价 */
	private java.lang.Double price;
	/** 商品数量 */
	private java.lang.Integer total;
	/** 货款金额 */
	private java.lang.Double amount;
	/** 预解冻时间 */
	private java.lang.Long preReleaseTime;
	/** 修正后的解冻时间 */
	private java.lang.Long releaseTime;
	/** 入账时间 */
	private java.lang.Long createTime;
	/** 修改时间 */
	private java.lang.Long updateTime;
	/** 状态:0完成,1冻结中,2退货 4结算失败 */
	private java.lang.Integer stat;
	/** 商品名称 */
	private java.lang.String goodsName;
	/** 商品规格描述 */
	private java.lang.String skuDescription;
	/** 收入对账结算记录表的主键ID*/
	private java.lang.Long incomeSettlementId;
	/** 商品ID*/
	private java.lang.Long goodsId;
	
	public java.lang.Long getDmId() {
	    return this.dmId;
	}
	public void setDmId(java.lang.Long dmId) {
	    this.dmId=dmId;
	}
	public java.lang.Long getOwnerId() {
	    return this.ownerId;
	}
	public void setOwnerId(java.lang.Long ownerId) {
	    this.ownerId=ownerId;
	}
	public java.lang.Long getOrderId() {
	    return this.orderId;
	}
	public void setOrderId(java.lang.Long orderId) {
	    this.orderId=orderId;
	}
	public java.lang.Long getOrderItemId() {
	    return this.orderItemId;
	}
	public void setOrderItemId(java.lang.Long orderItemId) {
	    this.orderItemId=orderItemId;
	}
	public java.lang.Long getSkuId() {
	    return this.skuId;
	}
	public void setSkuId(java.lang.Long skuId) {
	    this.skuId=skuId;
	}
	public java.lang.Double getPrice() {
	    return this.price;
	}
	public void setPrice(java.lang.Double price) {
	    this.price=price;
	}
	public java.lang.Integer getTotal() {
	    return this.total;
	}
	public void setTotal(java.lang.Integer total) {
	    this.total=total;
	}
	public java.lang.Double getAmount() {
	    return this.amount;
	}
	public void setAmount(java.lang.Double amount) {
	    this.amount=amount;
	}
	public java.lang.Long getCreateTime() {
	    return this.createTime;
	}
	public void setCreateTime(java.lang.Long createTime) {
	    this.createTime=createTime;
	}
	public java.lang.Long getPreReleaseTime() {
		return preReleaseTime;
	}
	public void setPreReleaseTime(java.lang.Long preReleaseTime) {
		this.preReleaseTime = preReleaseTime;
	}
	public java.lang.Long getReleaseTime() {
	    return this.releaseTime;
	}
	public void setReleaseTime(java.lang.Long releaseTime) {
	    this.releaseTime=releaseTime;
	}
	public java.lang.Long getUpdateTime() {
	    return this.updateTime;
	}
	public void setUpdateTime(java.lang.Long updateTime) {
	    this.updateTime=updateTime;
	}
	public java.lang.Integer getStat() {
	    return this.stat;
	}
	public void setStat(java.lang.Integer stat) {
	    this.stat=stat;
	}
	public java.lang.String getGoodsName() {
	    return this.goodsName;
	}
	public void setGoodsName(java.lang.String goodsName) {
	    this.goodsName=goodsName;
	}
	public java.lang.String getSkuDescription() {
	    return this.skuDescription;
	}
	public void setSkuDescription(java.lang.String skuDescription) {
	    this.skuDescription=skuDescription;
	}
	public java.lang.Long getIncomeSettlementId() {
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
	    return "[" + "dmId:" + getDmId() +"," + ",goodsId:" +  getGoodsId() +"," + "ownerId:" + getOwnerId() +"," + "orderId:" + getOrderId() +"," + "orderItemId:" + getOrderItemId() +"," + "skuId:" + getSkuId() +"," + "price:" + getPrice() +"," + "total:" + getTotal() +"," + "amount:" + getAmount() +"," + "createTime:" + getCreateTime() +"," + "preReleaseTime:" + getPreReleaseTime() + "releaseTime:" + getReleaseTime() +"," + "updateTime:" + getUpdateTime() +"," + "stat:" + getStat() +"," + "goodsName:" + getGoodsName() +"," + "skuDescription:" + getSkuDescription() + ","+ "incomeSettlementId:" + getIncomeSettlementId() + "]";
	}
}