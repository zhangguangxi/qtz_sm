package com.qtz.sm.shop.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopInfoOperationHistory</p>
 * <p>Description:便利店操作记录VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-17
 */
public class ShopInfoOperationHistory extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1710103458531328L;

	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}