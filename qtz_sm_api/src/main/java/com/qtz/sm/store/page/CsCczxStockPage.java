package com.qtz.sm.store.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:CsCczxStockPage</p>
 * <p>Description:仓储中心库存分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-17
 */
public class CsCczxStockPage extends Pager<com.qtz.sm.store.vo.CsCczxStock,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1666611934578689L;

	
	/**仓储中心ID**/
	private Long cczxId;
	
	public Long getCczxId() {
		return cczxId;
	}
	public void setCczxId(Long cczxId) {
		this.cczxId = cczxId;
	}
}