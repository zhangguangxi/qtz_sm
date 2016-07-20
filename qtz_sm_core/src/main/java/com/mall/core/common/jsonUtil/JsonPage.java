package com.mall.core.common.jsonUtil;

import java.util.List;


/**
 * <p>Title:JsonPage</p>
 * <p>Description:(用一句话描述该文件做什么)</p>
 * <p>Copyright: Copyright (c) 2014</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 唐礼军 - tanglijun
 * @version v1.0 2014-9-28
 */

public class JsonPage {
	//表记录总个数
	int total ;
	//显示的记录数据存放到rows集合中
	List<?> rows ;
	//当前页号
	int page;
	//页面
	int pageSize;
	
	public int getTotal() {
		return total;
	}
	public List<?> getRows() {
		return rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public JsonPage() {
	}
	public JsonPage(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
}