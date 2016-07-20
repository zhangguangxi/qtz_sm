package com.qtz.sm.search.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:SearchTypePage</p>
 * <p>Description:搜索分类分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-26
 */
public class SearchTypePage extends Pager<com.qtz.sm.search.vo.SearchType,Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1679041752172545L;

		/** 分类名称 */	private java.lang.String name;	/** 状态0-不可用，1-可用 */	private java.lang.Integer status;	/** 创建时间 */	private java.lang.Long createTime;	public java.lang.String getName() {	    return this.name;	}	public void setName(java.lang.String name) {	    this.name=name;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}	public java.lang.Long getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Long createTime) {	    this.createTime=createTime;	}	public String toString() {	    return "[" + "name:" + getName() +"," + "status:" + getStatus() +"," + "createTime:" + getCreateTime() +"]";	}
}