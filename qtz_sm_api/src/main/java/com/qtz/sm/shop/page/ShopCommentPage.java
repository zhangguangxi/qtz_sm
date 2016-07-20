package com.qtz.sm.shop.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:ShopCommentPage</p>
 * <p>Description:店铺评论信息分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
public class ShopCommentPage extends Pager<com.qtz.sm.shop.vo.ShopComment,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1681805665585153L;

	
	/** 不低于该星级评价 */
	private java.lang.Double startLevel;
	/** 不高于星级评价 */
	private java.lang.Double endLevel;
		return startLevel;
	}
	public void setStartLevel(java.lang.Double startLevel) {
		this.startLevel = startLevel;
	}
	public java.lang.Double getEndLevel() {
		return endLevel;
	}
	public void setEndLevel(java.lang.Double endLevel) {
		this.endLevel = endLevel;
	}
	public java.lang.String getTitle() {
}