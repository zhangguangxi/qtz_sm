package com.mall.core.common.response;

public class PagerDm {
	
	private int nowPage; //当前页
	
	private int pageSize;//每页数量
	
	private long count;//总记录数
	
	private int nextPage;//下一页

	

	public PagerDm() {
		super();
	}

	public PagerDm(int nowPage, int pageSize, long count, int nextPage) {
		super();
		this.nowPage = nowPage;
		this.pageSize = pageSize;
		this.count = count;
		this.nextPage = nextPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	
}
