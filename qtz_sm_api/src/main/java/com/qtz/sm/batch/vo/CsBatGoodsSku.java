package com.qtz.sm.batch.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:CsBatGoodsSku</p>
 * <p>Description:批发单商品与SKU关系VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
public class CsBatGoodsSku extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1660901532633088L;

		/** 主键ID */	private java.lang.Long dmId;	/** 批发单商品ID */	private java.lang.Long batGoodsId;	/** 规格ID */
	private java.lang.Long skuId;	/** 批发数量 */	private java.lang.Integer batNum;
	

    //关联数据，非数据库字段
    /**商品编号 */
    private String goodsNum;
    /** 商品名称 */
    private String goodsName;
    /** 商品ID */
    private Long goodsId;
	/** 规格属性 */
	private String val;
    	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getBatGoodsId() {	    return this.batGoodsId;	}	public void setBatGoodsId(java.lang.Long batGoodsId) {	    this.batGoodsId=batGoodsId;	}	public java.lang.Long getSkuId() {	    return this.skuId;	}	public void setSkuId(java.lang.Long skuId) {	    this.skuId=skuId;	}	public java.lang.Integer getBatNum() {	    return this.batNum;	}	public void setBatNum(java.lang.Integer batNum) {	    this.batNum=batNum;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "batGoodsId:" + getBatGoodsId() +"," + "skuId:" + getSkuId() +"," + "batNum:" + getBatNum() +"]";	}
	public String getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
}