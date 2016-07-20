package com.qtz.sm.shop.vo;

import java.util.List;

/**
 * <p>Title:com.qtz.sm.shop.vo.ShopPurchaseOrderVo</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 孙选 - Laven.liu
 * @version v1.0 2016/5/6
 */
public class ShopPurchaseOrderVo  implements java.io.Serializable {

    private static final long serialVersionUID = 4549220823852956723L;

    private Long dmId;
    /**所属仓储中心名称*/
    private String cczxName;
    /** 省份名称*/
    private String provinceName;
    /**城市名称*/
    private String cityName;
    /** 县/区名称*/
    private String areaName;
    /** 镇/街道名称*/
    private String townName;
    //TODO 需要构建订单项详细信息模型
    /** 订单编号 */
	private String code;
	/** 订单状态 */
	private String statu;
	/** 订单sku总数量 */
	private Integer skuNum;
	/** 详细地址 */
	private String address;
	/** 状态 0：待受理，1：待配送，2：配送中，3：已完成 */
	private Integer status;
	/** 备注 */
	private String remark;
	/** 手机号码 */
	private String mobile;
	/** 修改时间 */
	private java.util.Date updateTime;
	/** 创建下单时间 */
	private java.util.Date createTime;
	/** 收货人 */
	private String customer;
	
	/** 商品sku集合 */
	private List<ShopPurchaseOrderItemVo> shopPurchaseOrderItemVoList;

    public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public Integer getSkuNum() {
		return skuNum;
	}

	public void setSkuNum(Integer skuNum) {
		this.skuNum = skuNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public java.util.Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public List<ShopPurchaseOrderItemVo> getShopPurchaseOrderItemVoList() {
		return shopPurchaseOrderItemVoList;
	}

	public void setShopPurchaseOrderItemVoList(List<ShopPurchaseOrderItemVo> shopPurchaseOrderItemVoList) {
		this.shopPurchaseOrderItemVoList = shopPurchaseOrderItemVoList;
	}

	public String getCczxName() {
        return cczxName;
    }

    public void setCczxName(String cczxName) {
        this.cczxName = cczxName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

	public Long getDmId() {
		return dmId;
	}

	public void setDmId(Long dmId) {
		this.dmId = dmId;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
}
