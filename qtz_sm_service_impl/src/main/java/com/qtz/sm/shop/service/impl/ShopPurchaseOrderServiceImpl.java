package com.qtz.sm.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mall.core.common.StringUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.shop.dao.ShopPurchaseOrderDao;
import com.qtz.sm.shop.service.ShopGoodsService;
import com.qtz.sm.shop.service.ShopGoodsSkuService;
import com.qtz.sm.shop.service.ShopPurchaseOrderItemService;
import com.qtz.sm.shop.service.ShopPurchaseOrderService;
import com.qtz.sm.shop.vo.ShopGoods;
import com.qtz.sm.shop.vo.ShopGoodsSku;
import com.qtz.sm.shop.vo.ShopPurchaseOrder;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItem;
import com.qtz.sm.shop.vo.ShopPurchaseOrderItemVo;
import com.qtz.sm.shop.vo.ShopPurchaseOrderVo;
import com.qtz.sm.shop.vo.ShopValueVo;
import com.qtz.sm.store.service.CsCczxInfoService;
import com.qtz.sm.store.service.CsCczxStockService;
import com.qtz.sm.store.vo.CsCczxInfo;

/**
 * <p>Title:ShopPurchaseOrderServiceImpl</p>
 * <p>Description:便利店采购订单服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-05-04
 */
@Service("shopPurchaseOrderServiceImpl")
public class ShopPurchaseOrderServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopPurchaseOrder, Long> implements ShopPurchaseOrderService {
    /**
     * 初始化日志对象
     */
    private static LogTool log = LogTool.getInstance(ShopPurchaseOrderServiceImpl.class);
    /**
     * 注入便利店采购订单DAO接口类
     */
    @Resource(name = "shopPurchaseOrderDaoImpl")
    private ShopPurchaseOrderDao dao;

    /**
     * 便利店采购订单项服务
     */
    @Resource(name = "shopPurchaseOrderItemServiceImpl")
    private ShopPurchaseOrderItemService shopPurchaseOrderItemService;

    /**
     * 便利店商品sku服务
     */
    @Resource(name = "shopGoodsSkuServiceImpl")
    private ShopGoodsSkuService shopGoodsSkuService;

    /**
     * 仓储中心服务
     */
    @Resource(name = "csCczxInfoServiceImpl")
    private CsCczxInfoService csCczxInfoService;

    /**
     * 地区服务
     */
    @Resource(name = "csRegionsServiceImpl")
    private CsRegionsService csRegionsService;
    
    /**
     * 便利店商品服务
     */
    @Resource(name = "shopGoodsServiceImpl")
    private ShopGoodsService shopGoodsService;
    
    /** 仓储中心sku库存 */
    //@Resource(name = "csCczxStockServiceImpl")
    private CsCczxStockService csCczxStockService;

    /**
     * 【取得】业务DAO对象
     *
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<com.qtz.sm.shop.vo.ShopPurchaseOrder, Long> getDao() {
        return dao;
    }

    /**
     * 【取得】日志对象
     *
     * @return 日志对象
     */
    @Override
    protected LogTool getLog() {
        return log;
    }

    //Service Manage
    @Override
    public ShopPurchaseOrderVo findById(Long purchaseOrderId) throws ServiceException {
        ShopPurchaseOrder shopPurchaseOrder = this.findVo(purchaseOrderId, null);
        
        ShopPurchaseOrderVo shopPurchaseOrderVo = new ShopPurchaseOrderVo();
        BeanUtils.copyProperties(shopPurchaseOrder, shopPurchaseOrderVo);

        //TODO 查询订单项信息，包括sku信息
        ShopPurchaseOrderItem shopPurchaseOrderItem = new ShopPurchaseOrderItem();
        shopPurchaseOrderItem.setPurchaseOrderId(purchaseOrderId);
        
        //************** 组装商品所属 的 sku****************//
        List<ShopPurchaseOrderItemVo> list = shopPurchaseOrderItemService.getOrderItem(shopPurchaseOrderItem);//查询所有对应的sku 值，以及商品名称，商品编号
        Set<String> goodsName = new HashSet<String>();//过滤重复商品名称
        for (int i = 0; i < list.size(); i++) {
        	goodsName.add(list.get(i).getGoodsName());
		}
        
        List<ShopPurchaseOrderItemVo> shopPurchaseOrderItemVoList = new ArrayList<ShopPurchaseOrderItemVo>();
        for (String names : goodsName) {
        	ShopPurchaseOrderItemVo shopPurchaseOrderItemVo = new ShopPurchaseOrderItemVo();//一条商品数据
        	List<ShopValueVo>  shopValueVo = new ArrayList<ShopValueVo>();//每条商品对应的sku值
			for (int j = 0; j < list.size(); j++) {
				//找出属于该商品名称的sku值
				if(names.equals(list.get(j).getGoodsName())){
					ShopValueVo vo = new ShopValueVo();
					vo.setDmId(list.get(j).getDmId());
					vo.setSkuId(list.get(j).getSkuId());
					vo.setVal(list.get(j).getVal());
					vo.setActualNum(list.get(j).getPurchaseNum());
					shopValueVo.add(vo);
					shopPurchaseOrderItemVo.setGoodsId(list.get(j).getGoodsId());
				}
			}
			shopPurchaseOrderItemVo.setGoodsName(names);
			shopPurchaseOrderItemVo.setShopValueVoList(shopValueVo);//该商品 所对应的sku值
			shopPurchaseOrderItemVoList.add(shopPurchaseOrderItemVo);
		}
        
        shopPurchaseOrderVo.setShopPurchaseOrderItemVoList(shopPurchaseOrderItemVoList);//所有商品重新组装数据
        //******************************
        
        //查询仓储中心、省、市、区信息
        //所属仓储中心信息
        CsCczxInfo csCczxInfo = csCczxInfoService.findVo(shopPurchaseOrder.getCczxId(), null);
        if(csCczxInfo!=null)
        shopPurchaseOrderVo.setCczxName(csCczxInfo.getName());
        //省、市、区、镇
        Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(shopPurchaseOrder.getProvinceId(),
                shopPurchaseOrder.getCityId(),
                shopPurchaseOrder.getAreaId(),
                shopPurchaseOrder.getTownId());
        shopPurchaseOrderVo.setProvinceName(csRegionsMap.get("province").getName());
        shopPurchaseOrderVo.setCityName(csRegionsMap.get("city").getName());
        shopPurchaseOrderVo.setAreaName(csRegionsMap.get("area").getName());
        shopPurchaseOrderVo.setTownName(csRegionsMap.get("town").getName());
        return shopPurchaseOrderVo;
    }

    //Service Manage
    @Override
    public void addOrder(ShopPurchaseOrder shopPurchaseOrder) throws ServiceException {
    	shopPurchaseOrder = this.addVo(shopPurchaseOrder);//1、添加进货单主表
        List<ShopPurchaseOrderItem> shopPurchaseOrderItemList = shopPurchaseOrder.getShopPurchaseOrderItemList();
        if(null==shopPurchaseOrderItemList || shopPurchaseOrderItemList.size() == 0){
        	throw new ServiceException("采购订单商品不能为空"); 
        }
        Set<Long> goodsIdSet = new HashSet<Long>();
        List<ShopGoodsSku> shopGoodsList = new ArrayList<ShopGoodsSku>();
        for (ShopPurchaseOrderItem shopPurchaseOrderItem : shopPurchaseOrderItemList) {
            //设置采购订单ID
            shopPurchaseOrderItem.setPurchaseOrderId(shopPurchaseOrder.getDmId());
            shopPurchaseOrderItem.setCreateTime(new Date());
            shopPurchaseOrderItem.setUpdateTime(new Date());
            
            goodsIdSet.add(shopPurchaseOrderItem.getGoodsId());//过滤商品ID
            
            ShopGoodsSku shopGoodsSku = new ShopGoodsSku();
            shopGoodsSku.setShopId(shopPurchaseOrder.getShopId());
            shopGoodsSku.setSkuId(shopPurchaseOrderItem.getSkuId());
            shopGoodsSku.setGoodsId(shopPurchaseOrderItem.getGoodsId());
            //先判断是否已经存在该商品Sku，不存在则添加，存在则不添加
            List<ShopGoodsSku> list = shopGoodsSkuService.findList(shopGoodsSku);
            if(null == list || list.size() == 0){
            	 shopGoodsSku.setActual(0);
                 shopGoodsSku.setFreezeNum(0);
                 shopGoodsSku.setUpdateTime(new Date());
                 shopGoodsSku.setCreateTime(new Date());
                 shopGoodsList.add(shopGoodsSku);
            }
        }
        shopPurchaseOrderItemService.addList(shopPurchaseOrderItemList);//2、添加进货单从表
        //3、添加商品
//        List<ShopGoods> list = new ArrayList<ShopGoods>();
        for(Long goodsId:goodsIdSet){
        	ShopGoods shopGoods = new ShopGoods();
        	shopGoods.setGoodsId(goodsId);
        	shopGoods.setShopId(shopPurchaseOrder.getShopId());
        	//先判断是否已经存在该商品ID，不存在则添加，存在则不添加
        	List<ShopGoods> ShopGoodsList = shopGoodsService.findList(shopGoods);
        	if(null == ShopGoodsList || ShopGoodsList.size() == 0){
        		shopGoods.setCreateTime(new Date());
        		shopGoods.setUpdateTime(new Date());
            	shopGoods.setStatus(0);
            	shopGoods.setShopCategoryId(0L);
            	shopGoods = shopGoodsService.addVo(shopGoods);
        		for (int i = 0; i < shopGoodsList.size(); i++) {
        			 if(shopGoodsList.get(i).getGoodsId().equals(goodsId)){
        				 shopGoodsList.get(i).setShopGoodsId(shopGoods.getDmId());
					 }
				}
//            	list.add(shopGoods);
        	}
        }
//        if(null!=list && list.size()>0){
//        	shopGoodsService.addList(list);
//        }
        //4、添加便利店商品sku
        if(null!=shopGoodsList && shopGoodsList.size()>0){
        	shopGoodsSkuService.addList(shopGoodsList);
        }
    }

    //Service Manage
    @Override
    public void modStatus(Long purchaseOrderId, int status) throws ServiceException {
        ShopPurchaseOrder oldShopPurchaseOrder = this.findVo(purchaseOrderId, null);
        if(StringUtil.isEmpty(oldShopPurchaseOrder)){
        	throw new ServiceException("订单信息不存在！"); 
        }
        int sub = status - oldShopPurchaseOrder.getStatus();
        if (sub <= 0) {
            throw new ServiceException("订单状态不能逆向修改！"); 
        } else if (sub > 1) {
            throw new ServiceException("订单状态不能越级修改！");
        } else {
            //查询采购订单的订单项
            ShopPurchaseOrderItem queryParams = new ShopPurchaseOrderItem();
            queryParams.setPurchaseOrderId(purchaseOrderId);
            List<ShopPurchaseOrderItem> shopPurchaseOrderItemList = shopPurchaseOrderItemService.findList(queryParams);
            //采购订单状态：0：待受理，1：待配送，2：配送中，3：已完成
            switch (status) {
                case 1 : {
                    //TODO 订单状态从“待受理”修改为“待配送”时，仓储中心商品sku减实际库存，加锁定库存
                    for (ShopPurchaseOrderItem shopPurchaseOrderItem : shopPurchaseOrderItemList) {
                    	csCczxStockService.cczxDeductionsStock(shopPurchaseOrderItem.getSkuId(), shopPurchaseOrderItem.getPurchaseNum(), status);
                    }
                    break;
                }
                case 2 : {
                    //TODO 订单状态从“待配送”修改为“配送中”是，仓储中心商品sku减锁定库存
                    for (ShopPurchaseOrderItem shopPurchaseOrderItem : shopPurchaseOrderItemList) {
                    	csCczxStockService.cczxDeductionsStock(shopPurchaseOrderItem.getSkuId(), shopPurchaseOrderItem.getPurchaseNum(), status);
                    }
                    break;
                }
                case 3 : {
                	Map<String, Object> map = new HashMap<String, Object>();
                	Set<Long> set = new HashSet<Long>();
                    //订单状态从“配送中”修改为“已完成”是，便利店商品sku增加实际库存
                    for (ShopPurchaseOrderItem shopPurchaseOrderItem : shopPurchaseOrderItemList) {
                    	set.add(shopPurchaseOrderItem.getGoodsId());//过滤相同商品ID
                        //查询便利店sku
                        ShopGoodsSku shopGoodsSku = new ShopGoodsSku();
                        shopGoodsSku.setShopId(oldShopPurchaseOrder.getShopId());
                        shopGoodsSku.setSkuId(shopPurchaseOrderItem.getSkuId());
                        shopGoodsSku.setGoodsId(shopPurchaseOrderItem.getGoodsId());
                        List<ShopGoodsSku> shopGoodsSkuList = shopGoodsSkuService.findList(shopGoodsSku);
                        if (null == shopGoodsSkuList || shopGoodsSkuList.size() <= 0) {
                            throw new ServiceException("sku不存在！");
                        }
                        shopGoodsSku = shopGoodsSkuList.get(0);
                        //增加便利店sku库存
                        shopGoodsSkuService.modAddActualStock(shopGoodsSku.getDmId(), shopPurchaseOrderItem.getPurchaseNum());
                    }
                    
                    map.put("status", 1);//1上架0下架
                    map.put("onlineTime", new Date());//上架时间
                    map.put("shopId", oldShopPurchaseOrder.getShopId());//便利店编号ID
                    map.put("shopGoodsIds", set);
                    //上架商品
                    shopGoodsService.updateBatchStatus(map);
                    break;
                }
                default : {
                    throw new ServiceException("订单状态码有误！");
                }
            }

            //修改采购订单状态
            ShopPurchaseOrder shopPurchaseOrder = new ShopPurchaseOrder();
            shopPurchaseOrder.setDmId(oldShopPurchaseOrder.getDmId());
            shopPurchaseOrder.setStatus(status);
            shopPurchaseOrder.setUpdateTime(new Date());
            this.modVoNotNull(shopPurchaseOrder);
        }
    }
    
    @Override
	public List<ShopPurchaseOrderItemVo> getList(ShopPurchaseOrderItem shopPurchaseOrderItem) throws ServiceException {
		
		return shopPurchaseOrderItemService.getOrderItem(shopPurchaseOrderItem);
	}
}