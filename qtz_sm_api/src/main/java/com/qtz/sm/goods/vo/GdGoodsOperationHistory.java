package com.qtz.sm.goods.vo;
import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.VO;
/**
 * <p>Title:GdGoodsOperationHistory</p>
 * <p>Description:商品操作历史记录VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-15
 */
public class GdGoodsOperationHistory extends VO<java.lang.Long> implements java.io.Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1707564473960448L;

	
	/** 主键ID */
//	private java.lang.Long dmId;
	/** 操作人 */
	private java.lang.Long operator;
	/** 商品ID */
	private java.lang.Long goodsId;
	/** IP地址 */
	private java.lang.String ip;
	/** 操作动作,如ACT_MODIFY_PRICE */
	private java.lang.String act;
	/** 操作内容，如将价格15改为10 */
	private java.lang.String content;
	/** 操作原因，如降价 */
	private java.lang.String reason;
	/** 操作时间 */
	private java.lang.Long operateOn;
	
	//非数据库字段,用于查询
	private List<Long> operatorList = new ArrayList<Long>();
	
	//非数据库字段
	/** 操作人姓名 */
	private String opratorName;
	/** 员工职位 */
	private String position;
	
	public java.lang.Long getDmId() {
	    return this.dmId;
	}
	public void setDmId(java.lang.Long dmId) {
	    this.dmId=dmId;
	}
	public java.lang.Long getOperator() {
	    return this.operator;
	}
	public void setOperator(java.lang.Long operator) {
	    this.operator=operator;
	}
	public java.lang.Long getGoodsId() {
	    return this.goodsId;
	}
	public void setGoodsId(java.lang.Long goodsId) {
	    this.goodsId=goodsId;
	}
	public java.lang.String getIp() {
	    return this.ip;
	}
	public void setIp(java.lang.String ip) {
	    this.ip=ip;
	}
	public java.lang.String getAct() {
	    return this.act;
	}
	public void setAct(java.lang.String act) {
	    this.act=act;
	}
	public java.lang.String getContent() {
	    return this.content;
	}
	public void setContent(java.lang.String content) {
	    this.content=content;
	}
	public java.lang.String getReason() {
	    return this.reason;
	}
	public void setReason(java.lang.String reason) {
	    this.reason=reason;
	}
	public java.lang.Long getOperateOn() {
	    return this.operateOn;
	}
	public void setOperateOn(java.lang.Long operateOn) {
	    this.operateOn=operateOn;
	}
	public List<Long> getOperatorList() {
		return operatorList;
	}
	public void setOperatorList(List<Long> operatorList) {
		this.operatorList = operatorList;
	}
	public String getOpratorName() {
		return opratorName;
	}
	public void setOpratorName(String opratorName) {
		this.opratorName = opratorName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "GdGoodsOperationHistory [dmId=" + dmId + ", operator=" + operator + ", goodsId=" + goodsId + ", ip="
				+ ip + ", act=" + act + ", content=" + content + ", reason=" + reason + ", operateOn=" + operateOn
				+ ", operatorList=" + operatorList + ", opratorName=" + opratorName + ", position=" + position + "]";
	}
}