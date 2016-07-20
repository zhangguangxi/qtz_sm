package com.mall.core.vo;

import java.io.Serializable;
import java.util.List;

import com.mall.core.common.Constants;


/**
 * <p>Title:Pager</p>
 * <p>Description:分页模型</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 李昌波 - lichangbo
 * @version v1.0 2014-9-29
 */

public class Pager<T extends VO<PK>,PK extends Serializable> implements java.io.Serializable{

	private static final long serialVersionUID = -329214263764952565L;
	
	private int nowPage = 1;// 当前页
	private int rowCount = 0; // 总行数
	private int pageSize = Constants.PAGE_NUM; //每页显示条数
	private int pageCount = 0; //总页数
	private int pageOffset = 0;//当前页起始记录
	private int pageTail = 0;//当前页到达的记录
	private String orderField;//排序字段
	private boolean orderDirection ;//升
	private int length = 6;// 页面显示分页按钮个数
	private int startIndex = 0;// 开始分页数字
	private int endIndex = 0;// 结束分页数字
	private int[] indexs;//显示分页的页数数组
	private List<T> list;//返回的结果集
	private List<?> list2;//返回结果集(对象模型结果集数据)
	/**业务ID*/
	protected PK id;

	
	/**
	 * 【取得】id 
	 * @return	id
	 */
	
	public PK getId() {
		return id;
	}

	
	/**
	 * 【设置】id 
	 * @param 	id
	 */
	
	public void setId(PK id) {
		this.id = id;
	}
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int[] getIndexs() {
		int len = getEndIndex() - getStartIndex() + 1;
		if(len < 7)
		{
			startIndex = getStartIndex() - (7- len);
		}
		if(startIndex <= 0) startIndex = 1;
		 
		len = endIndex - startIndex + 1;
		indexs = new int[len];
		for (int i = 0; i < len; i++) {
			indexs[i] = (startIndex + i);
		}
		return indexs;
	}

	public void setIndexs(int[] indexs) {
		this.indexs = indexs;
	}

	public int getStartIndex() {
		startIndex = nowPage - (length / 2);
		if (startIndex < 1) {
			startIndex = 1;
		}
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		if (getStartIndex() < 1) {
			setStartIndex(1);
		}
		endIndex = (getStartIndex() + length) <= getPageCount() ? (getStartIndex() + length)
				: getPageCount();
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public Pager() {
		this.orderDirection = true;
	}

	protected void doPage() {
		this.pageCount = this.rowCount / this.pageSize + 1;
		// 如果模板==0，且总数大于1，则减一
		if ((this.rowCount % this.pageSize == 0) && pageCount > 1)
			this.pageCount--;
		
		// Mysql 算法
		this.pageOffset = (this.nowPage - 1) * this.pageSize;
		this.pageTail = this.pageOffset + this.pageSize;
		if ((this.pageOffset + this.pageSize) > this.rowCount)
			this.pageTail = this.rowCount;
	}

	public Boolean getOrderCondition() {
//		String condition = "";
//		if (this.orderField != null && this.orderField.length() != 0) {
//			condition = " order by " + orderField
//					+ (orderDirection ? " asc " : " desc ");
//		}
//		return condition;
		return orderDirection;
	}
	/**
	 * 
	  * 【mysql 排序】
	  * @return  
	  * @time:2015年8月28日 下午3:50:59
	  * @author 涂鑫
	  * @version
	 */
	public String getMysqlOrderCondition(){
		String condition = "";
		if (this.orderField != null && this.orderField.length() != 0) {
			condition = " order by t." + orderField
					+ (orderDirection ? " asc " : " desc ");
		}
		condition += " limit " + pageOffset + "," + pageSize;
		return condition;
	}
	public String getMysqlQueryCondition() {
		String condition = "";
		if (this.orderField != null && this.orderField.length() != 0) {
			condition = " order by " + orderField
					+ (orderDirection ? " asc " : " desc ");
		}
		condition += " limit " + pageOffset + "," + pageSize;
		return condition;
	}

	public void setOrderDirection(boolean orderDirection) {
		this.orderDirection = orderDirection;
	}

	public boolean isOrderDirection() {
		return orderDirection;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getOrderField() {
		return orderField;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		if(nowPage <= 0) nowPage = 1;
		this.nowPage = nowPage;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public int getPageOffset() {
		this.pageOffset = (this.nowPage - 1) * this.pageSize;
		return pageOffset;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageTail(int pageTail) {
		this.pageTail = pageTail;
	}

	public int getPageTail() {
		return pageTail;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		this.doPage();
	}

	public int getRowCount() {
		return rowCount;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public List<?> getList2() {
		return list2;
	}


	public void setList2(List<?> list2) {
		this.list2 = list2;
	}
	
}
