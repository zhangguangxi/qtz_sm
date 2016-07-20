package com.qtz.sm.supermarket.vo;
import com.mall.core.vo.VO;
/**
 * <p>Title:Advertisement</p>
 * <p>Description:广告位VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱科技有限公司</p>
 * @author 张光喜 - zhangguangxito@sina.cn 
 * @version v1.0 2016-05-31
 */
public class SupermarketAdvertisement extends VO<Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1686433504118784L;
	/** 图片 */	private java.lang.String icon;	/** 跳转方式 */	private java.lang.String url;	/** 排序 */	private java.lang.Integer sort;	/** 类型(0:商家  1:个人) */	private java.lang.Integer type;	/** 客户端类型(0:全部 1:安卓 2:ios) */	private java.lang.Integer clientType;	/** 状态(0:启用 1:禁用) */	private java.lang.Integer status;	/** 创建时间 */	private String createTime;	/** 更新时间 */	private String updateTime;
	    public Long getDmId() {
        return dmId;
    }
    public void setDmId(Long dmId) {
        this.dmId = dmId;
    }
    public java.lang.String getIcon() {	    return this.icon;	}	public void setIcon(java.lang.String icon) {	    this.icon=icon;	}	public java.lang.String getUrl() {	    return this.url;	}	public void setUrl(java.lang.String url) {	    this.url=url;	}	public java.lang.Integer getSort() {	    return this.sort;	}	public void setSort(java.lang.Integer sort) {	    this.sort=sort;	}	public java.lang.Integer getType() {	    return this.type;	}	public void setType(java.lang.Integer type) {	    this.type=type;	}	public java.lang.Integer getClientType() {	    return this.clientType;	}	public void setClientType(java.lang.Integer clientType) {	    this.clientType=clientType;	}	public java.lang.Integer getStatus() {	    return this.status;	}	public void setStatus(java.lang.Integer status) {	    this.status=status;	}
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    @Override
    public String toString() {
        return "Advertisement [dmId=" + dmId + ", icon=" + icon + ", url=" + url + ", sort=" + sort + ", type=" + type
                + ", clientType=" + clientType + ", status=" + status + ", createTime=" + createTime + ", updateTime="
                + updateTime + "]";
    }
}