package com.qtz.sm.goods.model;

import java.io.Serializable;

import com.qtz.sm.goods.page.GdGoodsOperationHistoryPage;

public class GdGoodsOperationHistoryListJson implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//商品ID
	private Long goodsId;
	//商品名称
	private String goodsName;
	//商品编码
	private String goodsCode;
	//商品操作记录列表
	private GdGoodsOperationHistoryPage historyPage;
	
	///////////////////////////////////////
	public GdGoodsOperationHistoryListJson(){
		
	}
	public GdGoodsOperationHistoryListJson(Long goodsId, String goodsName, String goodsCode,
			GdGoodsOperationHistoryPage historyPage) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsCode = goodsCode;
		this.historyPage = historyPage;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}
	public GdGoodsOperationHistoryPage getHistoryPage() {
		return historyPage;
	}
	public void setHistoryPage(GdGoodsOperationHistoryPage historyPage) {
		this.historyPage = historyPage;
	}
	@Override
	public String toString() {
		return "GdGoodsOperationHistoryListJson [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsCode="
				+ goodsCode + ", historyPage=" + historyPage + "]";
	}

}
