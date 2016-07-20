package com.qtz.sm.stc.page;
import com.mall.core.vo.Pager;
/**
 * <p>Title:CsCczxSplitPointPage</p>
 * <p>Description:仓储中心分成表分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-06-14
 */
public class CsCczxSplitPointPage extends Pager<com.qtz.sm.stc.vo.CsCczxSplitPoint,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1706225136863233L;

		/** 仓储中心分成点主键 */	private java.lang.Long dmId;	/** 仓储中心Id */	private java.lang.Long csCczxId;	/** 分成点 */	private java.lang.Double splitPoint;	public java.lang.Long getDmId() {	    return this.dmId;	}	public void setDmId(java.lang.Long dmId) {	    this.dmId=dmId;	}	public java.lang.Long getCsCczxId() {	    return this.csCczxId;	}	public void setCsCczxId(java.lang.Long csCczxId) {	    this.csCczxId=csCczxId;	}	public java.lang.Double getSplitPoint() {	    return this.splitPoint;	}	public void setSplitPoint(java.lang.Double splitPoint) {	    this.splitPoint=splitPoint;	}	public String toString() {	    return "[" + "dmId:" + getDmId() +"," + "csCczxId:" + getCsCczxId() +"," + "splitPoint:" + getSplitPoint() +"]";	}
}