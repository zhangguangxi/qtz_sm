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
	/** 图片 */
	
        return dmId;
    }
    public void setDmId(Long dmId) {
        this.dmId = dmId;
    }
    public java.lang.String getIcon() {
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