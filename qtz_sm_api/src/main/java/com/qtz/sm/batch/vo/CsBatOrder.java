package com.qtz.sm.batch.vo;
import java.util.List;

import com.mall.core.vo.VO;
/**
 * <p>Title:CsBatOrder</p>
 * <p>Description:批发单基础信息VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-06-30
 */
public class CsBatOrder extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1728853780908032L;

	
	/** 批发单商品信息 */
	private List<CsBatGoods> goodsList;
	public List<CsBatGoods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<CsBatGoods> goodsList) {
		this.goodsList = goodsList;
	}
}