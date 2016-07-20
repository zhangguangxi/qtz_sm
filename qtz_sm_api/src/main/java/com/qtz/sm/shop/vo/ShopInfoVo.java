package com.qtz.sm.shop.vo;

import java.util.List;

import com.qtz.sm.shopManage.vo.ShopManageCategory;

/**
 * <p>Title:com.qtz.sm.shop.vo.ShopInfoVo</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 刘晓峰 - Laven.liu
 * @version v1.0 2016/5/4
 */
public class ShopInfoVo extends ShopInfo {

    private static final long serialVersionUID = -725670761720719850L;
    /**
     * 所属便利店管理公司名称
     */
    private String shopManageName;

    /**
     * 所属仓储中心名称 ShopInfo 已经有了
     */
//    private String cczxName;

    /**
     * 省份名称
     */
    private String provinceName;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 县/区名称
     */
    private String areaName;

    /**
     * 镇/街道名称
     */
    private String townName;
    
    /** 所属运营分类信息 */
    private List<ShopManageCategory> shopManageCategoryList;

    public String getShopManageName() {
        return shopManageName;
    }

    public void setShopManageName(String shopManageName) {
        this.shopManageName = shopManageName;
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

    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("shopManageName", shopManageName)
                .append("provinceName", provinceName)
                .append("cityName", cityName)
                .append("areaName", areaName)
                .append("townName", townName)
                .toString();
    }

	public List<ShopManageCategory> getShopManageCategoryList() {
		return shopManageCategoryList;
	}

	public void setShopManageCategoryList(List<ShopManageCategory> shopManageCategoryList) {
		this.shopManageCategoryList = shopManageCategoryList;
	}
}
