package com.qtz.sm.goods.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:GdGoodsSkuRate</p>
 * <p>Description:商品SKU议价VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-19
 */
public class GdGoodsSkuRate extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1669295778514944L;

	
	/**非数据库字段：供应商价格*/
	private Double gysPrice;
		return gysPrice;
	}
	public void setGysPrice(Double gysPrice) {
		this.gysPrice = gysPrice;
	}
	@Override
	public String toString() {
		return "GdGoodsSkuRate [skuId=" + skuId + ", gylRate=" + gylRate + ", yccglRate=" + yccglRate + ", bldglRate="
				+ bldglRate + ", ppcsRate=" + ppcsRate + ", gylPrice=" + gylPrice + ", yccglPrice=" + yccglPrice
				+ ", bldglPrice=" + bldglPrice + ", ppcsPrice=" + ppcsPrice + ", gysPrice=" + gysPrice + "]";
	}
}