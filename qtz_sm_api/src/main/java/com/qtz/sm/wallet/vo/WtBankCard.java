package com.qtz.sm.wallet.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:WtBankCard</p>
 * <p>Description:银行卡信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-26
 */
public class WtBankCard extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1636587845519360L;

	
	private java.lang.String cardNum;
	/** 使能 */
	public java.lang.String getCardNum() {
	    return this.cardNum;
	}
	public void setCardNum(java.lang.String cardNum) {
	    this.cardNum=cardNum;
	}
}