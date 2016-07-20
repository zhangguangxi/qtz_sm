package com.qtz.sm.shopManage.vo;

/**
 * <p>Title:com.qtz.sm.shopManage.vo.ShopManageVo</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 刘晓峰 - Laven.liu
 * @version v1.0 2016/5/4
 */
public class ShopManageVo extends ShopManage {

    private static final long serialVersionUID = 2852965986232546625L;
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
                .append("provinceName", provinceName)
                .append("cityName", cityName)
                .append("areaName", areaName)
                .append("townName", townName)
                .toString();
    }
}
