package com.qtz.sm.goods.page;
import java.util.ArrayList;
import java.util.List;

import com.mall.core.vo.Pager;
/**
 * <p>Title:GdGoodsOperationHistoryPage</p>
 * <p>Description:商品操作历史记录分页类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-15
 */
public class GdGoodsOperationHistoryPage extends Pager<com.qtz.sm.goods.vo.GdGoodsOperationHistory,java.lang.Long> implements java.io.Serializable{

	/**类的版本号*/
	private static final long serialVersionUID = 1707564473960449L;
	
	
	//非数据库字段
	/** 操作人姓名 */
	private String opratorName;
	/** 员工职位 */
	private String position;
	
	//非数据库字段,用于查询
	private List<Long> operatorList = new ArrayList<Long>();
	
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
	public List<Long> getOperatorList() {
		return operatorList;
	}
	public void setOperatorList(List<Long> operatorList) {
		this.operatorList = operatorList;
	}
	@Override
	public String toString() {
		return "GdGoodsOperationHistoryPage [dmId=" + dmId + ", operator=" + operator + ", goodsId=" + goodsId + ", ip="
				+ ip + ", act=" + act + ", content=" + content + ", reason=" + reason + ", operateOn=" + operateOn
				+ ", opratorName=" + opratorName + ", position=" + position + ", operatorList=" + operatorList + "]";
	}
}