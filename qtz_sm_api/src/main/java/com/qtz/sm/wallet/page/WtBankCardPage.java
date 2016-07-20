package com.qtz.sm.wallet.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:WtBankCardPage</p>
 * <p>Description:银行卡信息分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-28
 */
public class WtBankCardPage extends Pager<com.qtz.sm.wallet.vo.WtBankCard,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1639844211640321L;

	
	private java.lang.String bankAddress;
	/** 银行卡号 */
	public java.lang.String getCardNum() {
	    return this.cardNum;
	}
	public void setCardNum(java.lang.String cardNum) {
	    this.cardNum=cardNum;
	}
}