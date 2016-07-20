package com.mall.core.common.model;

import java.io.Serializable;

/**
 * 浏览器信息模型类
 * @Package com.allearn.common.model
 * @ClassName: BrowserModel
 * @Description: 
 * @author 赵汉江
 * @date 2013-11-27 上午09:36:21
 * @version V1.0
 */
public class BrowserModel implements Serializable {
	
	/** (info) */
	private static final long serialVersionUID = 3999449987197126767L;
	private boolean trident;
	private boolean presto;
	private boolean webKit;
	private boolean gecko;
	private boolean mobile;
	private boolean ios;
	private boolean android;
	private boolean iPhone;
	private boolean iPad;
	private boolean webApp;
	
	public BrowserModel() {
		super();
	}
	public BrowserModel(boolean trident, boolean presto, boolean webKit,
			boolean gecko, boolean ios, boolean android,
			boolean iPhone, boolean iPad, boolean webApp) {
		super();
		this.trident = trident;
		this.presto = presto;
		this.webKit = webKit;
		this.gecko = gecko;
		this.ios = ios;
		this.android = android;
		this.iPhone = iPhone;
		this.iPad = iPad;
		this.webApp = webApp;
	}
	public boolean isTrident() {
		return trident;
	}
	public void setTrident(boolean trident) {
		this.trident = trident;
	}
	public boolean isPresto() {
		return presto;
	}
	public void setPresto(boolean presto) {
		this.presto = presto;
	}
	public boolean isWebKit() {
		return webKit;
	}
	public void setWebKit(boolean webKit) {
		this.webKit = webKit;
	}
	public boolean isGecko() {
		return gecko;
	}
	public void setGecko(boolean gecko) {
		this.gecko = gecko;
	}
	public boolean isMobile() {
		return this.android || this.ios || this.iPad || this.iPhone;
	}
	public void setMobile(boolean mobile) {
		this.mobile = mobile;
	}
	public boolean isIos() {
		return ios;
	}
	public void setIos(boolean ios) {
		this.ios = ios;
	}
	public boolean isAndroid() {
		return android;
	}
	public void setAndroid(boolean android) {
		this.android = android;
	}
	public boolean isiPhone() {
		return iPhone;
	}
	public void setiPhone(boolean iPhone) {
		this.iPhone = iPhone;
	}
	public boolean isiPad() {
		return iPad;
	}
	public void setiPad(boolean iPad) {
		this.iPad = iPad;
	}
	public boolean isWebApp() {
		return webApp;
	}
	public void setWebApp(boolean webApp) {
		this.webApp = webApp;
	}
	@Override
	public String toString() {
		return "BrowserModel [android=" + android + ", gecko=" + gecko
				+ ", iPad=" + iPad + ", iPhone=" + iPhone + ", ios=" + ios
				+ ", mobile=" + mobile + ", presto=" + presto + ", trident="
				+ trident + ", webApp=" + webApp + ", webKit=" + webKit + "]";
	}
	
}