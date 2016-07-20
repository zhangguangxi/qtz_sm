package com.qtz.sm.scm.vo;

import java.util.List;

import com.mall.core.vo.VO;

/**
 * 供应链公司   商品管理   商品审核分页以及商品库分页 映射实体
 * @author Administrator
 *
 */
public class CsGylGoodsVo  extends VO<java.lang.Long>implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5853000428304588606L;
	
//	private Long dmId;
	/**
	 * 商品编号
	 */
	private Long goodsId;
	/**
	 * 商品名称
	 */
	private String  goodsName;
	
	private String code;
	/**
	 * skuId
	 */
	private Long skuId;
	/**
	 * sku值
	 */
	private String str;
	/**
	 * 供货价
	 */
	private Double price;
	/**
	 * 供应链 议价率
	 */
	private Double gylRate;
	/**
	 * 仓储中心进货价
	 */
	private Double cczxInPrice;
	
	/**
	 * 上架时间
	 */
	private Long onsaleTime;
	
	/**
	 * gd_goods_sku_rate表的主键
	 */
	private Long goodsSkuRateDmId;
	
	private List<CsGylGoodsVo> csGylGoodsVoInfo;
	
	 //查询条件 start
	  //关联数据，非数据库字段
    /** 商品编号 */
    private String goodsNum;
    /**
     * 商品来源
     */
    private String goodsSource;
    /**
     * 起始进货价
     */
    private Double priceStart;
    /**
     *结束进货价
     */
    private Double priceEnd;
    /**
     * 起始仓储中心进货价
     */
    private Double cczxInPriceStart;
    /**
     *结束仓储中心进货价
     */
    private Double cczxInPriceEnd;

	
	/**
	 * 商品状态(0在售,1停售)
	 */
	private Integer isOnsale;
	
	
	/**
	 * 起始上架时间
	 */
	private String onsaleTimeStart;
	
	/**
	 * 结束上架时间
	 */
	private String onsaleTimeEnd;
	
	private Integer pageNum;
	
	private Integer pageSize;
	//查询条件 start
	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getGylRate() {
		return gylRate;
	}

	public void setGylRate(Double gylRate) {
		this.gylRate = gylRate;
	}

	public Double getCczxInPrice() {
		return cczxInPrice;
	}

	public void setCczxInPrice(Double cczxInPrice) {
		this.cczxInPrice = cczxInPrice;
	}

	public Long getOnsaleTime() {
		return onsaleTime;
	}

	public void setOnsaleTime(Long onsaleTime) {
		this.onsaleTime = onsaleTime;
	}

	public Long getGoodsSkuRateDmId() {
		return goodsSkuRateDmId;
	}

	public void setGoodsSkuRateDmId(Long goodsSkuRateDmId) {
		this.goodsSkuRateDmId = goodsSkuRateDmId;
	}

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public String getOnsaleTimeStart() {
		return onsaleTimeStart;
	}

	public void setOnsaleTimeStart(String onsaleTimeStart) {
		this.onsaleTimeStart = onsaleTimeStart;
	}

	public String getOnsaleTimeEnd() {
		return onsaleTimeEnd;
	}

	public void setOnsaleTimeEnd(String onsaleTimeEnd) {
		this.onsaleTimeEnd = onsaleTimeEnd;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsSource() {
		return goodsSource;
	}

	public void setGoodsSource(String goodsSource) {
		this.goodsSource = goodsSource;
	}

	public Double getPriceStart() {
		return priceStart;
	}

	public void setPriceStart(Double priceStart) {
		this.priceStart = priceStart;
	}

	public Double getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(Double priceEnd) {
		this.priceEnd = priceEnd;
	}

	public Double getCczxInPriceStart() {
		return cczxInPriceStart;
	}

	public void setCczxInPriceStart(Double cczxInPriceStart) {
		this.cczxInPriceStart = cczxInPriceStart;
	}

	public Double getCczxInPriceEnd() {
		return cczxInPriceEnd;
	}

	public void setCczxInPriceEnd(Double cczxInPriceEnd) {
		this.cczxInPriceEnd = cczxInPriceEnd;
	}

	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public List<CsGylGoodsVo> getCsGylGoodsVoInfo() {
		return csGylGoodsVoInfo;
	}

	public void setCsGylGoodsVoInfo(List<CsGylGoodsVo> csGylGoodsVoInfo) {
		this.csGylGoodsVoInfo = csGylGoodsVoInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
