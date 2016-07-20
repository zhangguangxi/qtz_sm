package com.qtz.sm.search.page;import com.mall.core.vo.Pager;/** * <p>Title:SearchListPage</p> * <p>Description:搜索列表分页类</p> * <p>Copyright: Copyright (c) 2016</p> * <p>Company: 深圳市擎天柱信息科技有限公司</p> * @author 郭云龙- 252735833@qq.com * @version v1.0 2016-06-06 */public class SearchListPage extends Pager<com.qtz.sm.search.vo.SearchList,Long> implements java.io.Serializable{	/**类的版本号*/	private static final long serialVersionUID = 1694503164479489L;		/** 主键ID */	private Long dmId;	/** 搜索分类 */	private Long searchTypeId;	/** 关键字 */	private String keyword;	/** 搜索店铺，存在则表示店内搜索 */	private Long shopId;	/** 搜索时间 */	private Long searchTime;	/** 搜索人编号 */	private Long searchUser;	/**  */	private Integer searchWays;	public Long getDmId() {	    return this.dmId;	}	public void setDmId(Long dmId) {	    this.dmId=dmId;	}	public Long getSearchTypeId() {	    return this.searchTypeId;	}	public void setSearchTypeId(Long searchTypeId) {	    this.searchTypeId=searchTypeId;	}	public String getKeyword() {	    return this.keyword;	}	public void setKeyword(String keyword) {	    this.keyword=keyword;	}	public Long getShopId() {	    return this.shopId;	}	public void setShopId(Long shopId) {	    this.shopId=shopId;	}	public Long getSearchTime() {	    return this.searchTime;	}	public void setSearchTime(Long searchTime) {	    this.searchTime=searchTime;	}	public Long getSearchUser() {	    return this.searchUser;	}	public void setSearchUser(Long searchUser) {	    this.searchUser=searchUser;	}	public Integer getSearchWays() {	    return this.searchWays;	}	public void setSearchWays(Integer searchWays) {	    this.searchWays=searchWays;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "searchTypeId:" + getSearchTypeId() +"," + "keyword:" + getKeyword() +"," + "shopId:" + getShopId() +"," + "searchTime:" + getSearchTime() +"," + "searchUser:" + getSearchUser() +"," + "searchWays:" + getSearchWays() +"]";	}}