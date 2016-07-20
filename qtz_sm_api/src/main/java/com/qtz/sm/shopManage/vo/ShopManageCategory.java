package com.qtz.sm.shopManage.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:ShopManageCategory</p>
 * <p>Description:便利店管理公司运营分类VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 孙选
 * @version v1.0 2016-06-06
 */
public class ShopManageCategory extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1694538681010176L;

	
	
	//查询条件
	private Long shopId;//便利店ID
	
	
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}
}