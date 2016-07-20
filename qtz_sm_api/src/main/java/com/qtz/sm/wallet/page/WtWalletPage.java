package com.qtz.sm.wallet.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:WtWalletPage</p>
 * <p>Description:钱包信息分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-13
 */
public class WtWalletPage extends Pager<com.qtz.sm.wallet.vo.WtWallet,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1660539661846529L;

	
	/** 创建时间 */
	private java.lang.Long createTime;
	/** 修改人 */
	private java.lang.Long createBy;
	
	/** 项目总额范围查询 */
	private java.lang.Double minAmount;
	
	/** 项目总额范围查询 */
	private java.lang.Double maxAmount;
	
	
		return minAmount;
	}
	public void setMinAmount(java.lang.Double minAmount) {
		this.minAmount = minAmount;
	}
	public java.lang.Double getMaxAmount() {
		return maxAmount;
	}
	public void setMaxAmount(java.lang.Double maxAmount) {
		this.maxAmount = maxAmount;
	}
	public java.lang.Long getDmId() {
	
		return createTime;
	}
	public void setCreateTime(java.lang.Long createTime) {
		this.createTime = createTime;
	}
	public java.lang.Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.Long createBy) {
		this.createBy = createBy;
	}
	public String toString() {
}