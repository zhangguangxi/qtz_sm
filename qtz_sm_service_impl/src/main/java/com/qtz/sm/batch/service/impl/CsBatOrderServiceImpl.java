package com.qtz.sm.batch.service.impl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespCode;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.batch.service.CsBatGoodsService;
import com.qtz.sm.batch.service.CsBatGoodsSkuService;
import com.qtz.sm.batch.service.CsBatOrderService;
import com.qtz.sm.batch.vo.CsBatGoods;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
import com.qtz.sm.batch.vo.CsBatOrder;
import com.qtz.sm.shop.vo.ShopGoodsSku;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItem;
import com.qtz.sm.store.service.CsCczxStockService;
import com.qtz.sm.supp.service.CsGysStockService;
import com.qtz.sm.batch.dao.CsBatOrderDao;
/**
 * <p>Title:CsBatOrderServiceImpl</p>
 * <p>Description:批发单基础信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-13
 */
//@Service("csBatOrderServiceImpl")
public class CsBatOrderServiceImpl extends BaseServiceImpl<com.qtz.sm.batch.vo.CsBatOrder,java.lang.Long> implements CsBatOrderService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(CsBatOrderServiceImpl.class);
	/**注入批发单基础信息DAO接口类*/
	@Resource(name="csBatOrderDaoImpl")
    private CsBatOrderDao dao;
	
	@Resource(name="csBatGoodsServiceImpl")
    private CsBatGoodsService goodsService;
	
	@Resource(name="csBatGoodsSkuServiceImpl")
    private CsBatGoodsSkuService skuService;
	
	@Resource(name="csGysStockServiceImpl")
    private CsGysStockService gysStockService;
	
	
//	@Resource(name="csCczxStockServiceImpl")
//    private CsCczxStockService cczxStockService;
	
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.batch.vo.CsBatOrder,java.lang.Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	@Override
	public void addOrder(CsBatOrder csBatOrder) throws ServiceException {
		//拆单--按照商品的供应商
		Map<Long,List<CsBatGoods>> gysGoodsMap = new HashMap<Long,List<CsBatGoods>>();
		for(CsBatGoods batGoods : csBatOrder.getGoodsList())
		{
			List<CsBatGoods> list = gysGoodsMap.get(batGoods.getSupplierId());
			if(list!=null&&list.size()>0)
			{
				list.add(batGoods);
			}else
			{
				List<CsBatGoods> goodsLis = new ArrayList<CsBatGoods>();
				goodsLis.add(batGoods);
				gysGoodsMap.put(batGoods.getSupplierId(), goodsLis);
			}
		}
		for(Long gysId : gysGoodsMap.keySet())
		{
			//重新生成新的拆单ID
			csBatOrder.setDmId(null);
			csBatOrder.setGysId(gysId);
			// 计算订单sku总数
			Integer skuCount = statisticSku(gysGoodsMap.get(gysId));
			csBatOrder.setTotalNum(skuCount);
			//添加仓储中心批发单
			CsBatOrder result = addVo(csBatOrder);
			for(CsBatGoods batGoods : gysGoodsMap.get(gysId))
			{
				batGoods.setOrderId(result.getDmId());
				goodsService.addVo(batGoods);
				for(CsBatGoodsSku sku : batGoods.getSkuList())
				{
					sku.setBatGoodsId(batGoods.getDmId());
					skuService.addVo(sku);
				}
			}
		}
	}
	
	private Integer statisticSku(List<CsBatGoods> goodsList) {
		List<CsBatGoodsSku> skuList = null;
		Integer count = 0;
		if (goodsList != null && goodsList.size() > 0) {
			for (CsBatGoods batGoods : goodsList) {
				skuList = batGoods.getSkuList();
				if(skuList!=null&&skuList.size()>0)
				{
					for(CsBatGoodsSku sku : skuList)
					{
						count += sku.getBatNum();
					}
				}
			}
		}
		return count;
	}
	@Override
	public CsBatOrder getOrderDetail(Long dmId) throws ServiceException {
		CsBatOrder csBatOrder = findVo(dmId, new CsBatOrder());
		List<CsBatGoods> goodsList = goodsService.getGoodsInfoByOrderId(csBatOrder.getDmId());
		List<CsBatGoodsSku> skuList = null;
		for(CsBatGoods goods : goodsList)
		{
			skuList = skuService.getSkuInfoByDmId(goods.getDmId());
			goods.setSkuList(skuList);
		}
		csBatOrder.setGoodsList(goodsList);
		return csBatOrder;
	}
	@Override
	public void updateBatOrder(CsBatOrder csBatOrder) throws ServiceException {
		CsBatOrder nowOrder = findVo(csBatOrder.getDmId(), new CsBatOrder());
		int orderStatus = csBatOrder.getStatus();
		if(nowOrder==null)
		{
			throw new ServiceException(RespCode.batOrder_no_have_error,"当前批发单不存在.");
		}
		if((nowOrder.getStatus()+1)!=orderStatus)
		{
			throw new ServiceException(RespCode.batOrder_status_error,"批发单状态不正确，无法进行该操作.");
		}
		if(orderStatus==1)
		{
			csBatOrder.setConfirmTime(new Date().getTime());
		}else if(orderStatus==2)
		{
			csBatOrder.setDeliveryTime(new Date().getTime());
		}else if(orderStatus==3)
		{
			csBatOrder.setFinishTime(new Date().getTime());
		}
		
		 //查询采购订单的订单SKU项
        List<CsBatGoodsSku> skuList = skuService.findListByBatOrderId(csBatOrder.getDmId());
        //采购订单状态：0：待受理，1：待配送，2：配送中，3：已完成
        switch (orderStatus) {
            case 1 : {
                // 订单状态从“待受理”修改为“待配送”时，供应商商品sku减实际库存，加锁定库存
                for (CsBatGoodsSku sku : skuList) {
                	gysStockService.updateStock(sku, orderStatus);
                }
                break;
            }
            case 2 : {
                // 订单状态从“待配送”修改为“配送中”是，供应商商品sku减锁定库存
                for (CsBatGoodsSku sku : skuList) {
                	gysStockService.updateStock(sku, orderStatus);
                }
                break;
            }
            case 3 : {
                //订单状态从“配送中”修改为“已完成”时，仓储商品sku增加实际库存
                for (CsBatGoodsSku sku : skuList) {
//                	cczxStockService.updateStock(sku,nowOrder);
                }
                
                break;
            }
            default : {
                throw new ServiceException(ExceptionConstants.ERRORCODE_7,"订单状态码有误！");
            }
        }
		modVoNotNull(csBatOrder);
	}
}