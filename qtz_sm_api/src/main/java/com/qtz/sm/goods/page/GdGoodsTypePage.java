package com.qtz.sm.goods.page;
import com.mall.core.vo.Pager;
import com.qtz.sm.goods.vo.GdGoodsType;
/**
 * <p>Title:GdGoodsTypePage</p>
 * <p>Description:商品分类分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public class GdGoodsTypePage extends Pager<GdGoodsType,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1625492715407360L;
	
	/** 主键ID */
	
	/**
	 * 名称或拼音查询
	 */
	private String nameOrPinyinLike;
	
	public String getNameOrPinyinLike() {
		return nameOrPinyinLike;
	}
	public void setNameOrPinyinLike(String nameOrPinyinLike) {
		this.nameOrPinyinLike = nameOrPinyinLike;
	}
	public java.lang.Long getDmId() {
}