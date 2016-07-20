package com.qtz.sm.search.vo;import com.mall.core.vo.VO;import java.util.List;/** * <p>Title:SearchList</p> * <p>Description:搜索列表VO类</p> * <p>Copyright: Copyright (c) 2016</p> * <p>Company: 深圳市擎天柱信息科技有限公司</p> * @author 郭云龙- 252735833@qq.com * @version v1.0 2016-06-06 */public class ShopSearch extends VO<Long> implements java.io.Serializable {		/**类的版本号*/	private static final long serialVersionUID = 1694503164479488L;	/** 物流费用 */	private Double logisticsMoney;	/** 起送金额 */	private Double minimumMoney;	/** 店铺名称 */	private String name;	/** 空间距离 */	private String distance ;	/** 店铺图片 */	private String icon;	/** 经营范围 */	private Object scopes;	/** 是否存在起送价 */	private Boolean isSendPrice;	/** 商店营业状态 是否开业  status+service_start_time+service_end_time 关联判断**/	private Boolean isOpened;	/** 是否在服务范围内 **/	private Boolean isServiceScope;	public Boolean getOpened() {		return isOpened;	}	public void setOpened(Boolean opened) {		isOpened = opened;	}	public Boolean getServiceScope() {		return isServiceScope;	}	public void setServiceScope(Boolean serviceScope) {		isServiceScope = serviceScope;	}	public Boolean getSendPrice() {		return isSendPrice;	}	public void setSendPrice(Boolean sendPrice) {		isSendPrice = sendPrice;	}	@Override	public Long getDmId() {		return dmId;	}	@Override	public void setDmId(Long dmId) {		this.dmId = dmId;	}	public Double getLogisticsMoney() {		return logisticsMoney;	}	public void setLogisticsMoney(Double logisticsMoney) {		this.logisticsMoney = logisticsMoney;	}	public Double getMinimumMoney() {		return minimumMoney;	}	public void setMinimumMoney(Double minimumMoney) {		this.minimumMoney = minimumMoney;	}	public String getName() {		return name;	}	public void setName(String name) {		this.name = name;	}	public String getDistance() {		return distance;	}	public void setDistance(String distance) {		this.distance = distance;	}	public String getIcon() {		return icon;	}	public void setIcon(String icon) {		this.icon = icon;	}	public Object getScopes() {		return scopes;	}	public void setScopes(Object scopes) {		this.scopes = scopes;	}}