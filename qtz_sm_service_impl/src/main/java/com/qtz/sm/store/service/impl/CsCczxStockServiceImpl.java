//package com.qtz.sm.store.service.impl;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import org.springframework.stereotype.Service;
//
//import com.mall.core.common.ExceptionConstants;
//import com.mall.core.common.response.RespCode;
//import com.mall.core.dao.BizDao;
//import com.mall.core.exception.DaoException;
//import com.mall.core.exception.ServiceException;
//import com.mall.core.log.LogTool;
//import com.mall.core.service.impl.BaseServiceImpl;
//
//import com.qtz.sm.store.vo.CsCczxStock;
//import com.qtz.sm.supermarket.vo.SupermarketSkuStatus;
//import com.qtz.orig.order.vo.SupermarketGoods;
//import com.qtz.orig.order.vo.SupermarketOrder;
//import com.qtz.sm.batch.vo.CsBatGoodsSku;
//import com.qtz.sm.batch.vo.CsBatOrder;
//import com.qtz.sm.shop.service.ShopGoodsSkuService;
//import com.qtz.sm.shop.vo.ShopGoodsSku;
//import com.qtz.sm.store.dao.CsCczxStockDao;
//import com.qtz.sm.store.service.CsCczxStockService;
//
///**
// * Title:CsCczxStockServiceImpl<br/>
// * Description:(仓储中心商品库存SERVICE实现类)<br/>
// * Copyright: Copyright © 2016<br/>
// * Company: 深圳市擎天柱信息技术有限公司<br/>
// * @author 甘佳-jackgrays@matrix.com
// * @version v1.0 2016-04-25
// */
//@Service("csCczxStockServiceImpl")
//public class CsCczxStockServiceImpl extends BaseServiceImpl<CsCczxStock,Long> implements CsCczxStockService{
//
//    /**初始化日志对象*/
//    private static LogTool log = LogTool.getInstance(CsCczxStockServiceImpl.class);
//    /**注入CsCczxStockDao接口类*/
//    @Resource(name="csCczxStockDaoImpl")
//    private CsCczxStockDao dao;
//
//    /**注入便利店sku库存信息接口类*/
//    @Resource(name="shopGoodsSkuServiceImpl")
//    private ShopGoodsSkuService shopGoodsSkuService;
//
//    /**
//     * 【取得】业务DAO对象
//     * @return 	业务DAO对象
//     */
//    @Override
//    protected BizDao<CsCczxStock,Long> getDao() {
//        return dao;
//    }
//
//    /**
//     * 【取得】日志对象
//     * @return 	日志对象
//     */
//    @Override
//    protected LogTool getLog() {
//        return log;
//    }
//
//    /**
//     * 仓储中心----扣减库存
//     * @param skuId
//     * @param deductionsQuantity
//     * @throws ServiceException
//     */
//    @Override
//    public void cczxDeductionsStock(Long skuId,Integer deductionsQuantity,Integer status) throws ServiceException{
//    	try {
//    		CsCczxStock cczxStock =  dao.queryCsCczxStockInfo(skuId);
//    		 if( cczxStock.getStockQuantity() < deductionsQuantity ){
//    			 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "仓储中心----扣减库存扣减数量大于当前库存数量,deductionsQuantity=" + deductionsQuantity);
//    		 }else{
//    			 if(status == 1){
//    				//减数量， 加冻结数量
//        			 //更新库存数量= 当前库存数量 - 扣减库存数量
//        			 cczxStock.setStockQuantity( cczxStock.getStockQuantity() - deductionsQuantity );
//        			 cczxStock.setAwaitQuantity(cczxStock.getAwaitQuantity() + deductionsQuantity);
//        			 dao.modVoNotNull(cczxStock);
//    			 }else if(status == 2){
//    				//减冻结数量
//    				 cczxStock.setAwaitQuantity(cczxStock.getAwaitQuantity() - deductionsQuantity);
//        			 dao.modVoNotNull(cczxStock);
//    			 }
//
//    		 }
//		} catch (DaoException e) {
//			throw new ServiceException(e);
//		}
//    }
//
//    /**
//	 * 云仓储管理公司    商品管理    商品库     商品详情   商品规格
//	 */
//    @Override
//    public List<CsCczxStock> queryStockQuantityAndPrice(Long goodsId)throws ServiceException{
//    	try {
//			return dao.queryStockQuantityAndPrice(goodsId);
//		} catch (DaoException e) {
//			throw new ServiceException(e);
//		}
//    }
//
//	@Override
//	public void updateStock(CsBatGoodsSku sku, CsBatOrder nowOrder) throws ServiceException {
//		try {
//			Map<String,String> param = new HashMap<String,String>();
//			param.put("skuId", String.valueOf(sku.getSkuId()));
//			param.put("cczxId", String.valueOf(nowOrder.getCczxId()));
//			CsCczxStock cczxStock =  dao.getStockInfo(param);
//			if(cczxStock==null)
//			{
//				//如果不存在库存信息，则新增
//				cczxStock = new CsCczxStock();
//				cczxStock.setCczxId(nowOrder.getCczxId());
//				cczxStock.setSkuId(sku.getSkuId());
//				cczxStock.setStockQuantity(sku.getBatNum());
//				cczxStock.setGysId(nowOrder.getGysId());
//				//无冻结库存
//				cczxStock.setAwaitQuantity(0);
//				//商品SKU状态为在售
//				cczxStock.setIsOnsale(0);
//				cczxStock.setOnsaleTime(new Date().getTime());
//				addVo(cczxStock);
//			}else
//			{
//				//如果存在库存信息，则更新（确认收货增加库存）
//				CsCczxStock newStock = new CsCczxStock();
//				newStock.setDmId(cczxStock.getDmId());
//				newStock.setStockQuantity(cczxStock.getStockQuantity()+sku.getBatNum());
//				modVoNotNull(newStock);
//			}
//		} catch (DaoException e) {
//			log.error("更新仓储中心库存信息出现系统错误！", e);
//			throw new ServiceException(RespCode.update_batOrder_error,"更新仓储中心库存信息出现系统错误.");
//		}
//
//	}
//
//	@Override
//	public void updateSuperMarketStockByOrder(SupermarketOrder order, int status) throws ServiceException {
//		try {
//			List<SupermarketGoods> orderGoodsList = order.getSupermarketGoods();
//			for(SupermarketGoods supermarketGoods : orderGoodsList)
//			{
//				//获取库存信息
//				Map<String,String> param = new HashMap<String,String>();
//				param.put("skuId", String.valueOf(supermarketGoods.getSkuId()));
//				param.put("cczxId", String.valueOf(order.getStorageId()));
//				CsCczxStock cczxStock = dao.getStockInfo(param);
//				if(cczxStock==null)
//				{
//					log.error("获取仓储中心库存信息为空，仓储中心ID："+order.getStorageId()+"，skuID："+supermarketGoods.getSkuId());
//					throw new ServiceException(RespCode.getSotck_isNull_error,"获取仓储中心库存信息为空.");
//				}
//				CsCczxStock newStock = new CsCczxStock();
//				newStock.setDmId(cczxStock.getDmId());
//				//状态：0，创建订单扣减库存加预占库存；1,取消订单返回库存减预占库存；2,商家拒绝接单返回库存减预占库存；3，用户确认收货扣减预占库存；4，商家确认收货退款返回库存减预占库存
//				if(status==0)
//				{
//					newStock.setStockQuantity(cczxStock.getStockQuantity()-supermarketGoods.getGoodsNum());
//					newStock.setAwaitQuantity(cczxStock.getAwaitQuantity()+supermarketGoods.getGoodsNum());
//				}else if(status==1||status==2||status==4)
//				{
//					newStock.setStockQuantity(cczxStock.getStockQuantity()+supermarketGoods.getGoodsNum());
//					newStock.setAwaitQuantity(cczxStock.getAwaitQuantity()-supermarketGoods.getGoodsNum());
//				}else if(status==3)
//				{
//					newStock.setAwaitQuantity(cczxStock.getAwaitQuantity()-supermarketGoods.getGoodsNum());
//				}
//				if( newStock.getStockQuantity() < 0 ){
//	    			 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "仓储中心----扣减库存扣减数量大于当前库存数量,skuID："+supermarketGoods.getSkuId()+",当前实际库存："+ cczxStock.getStockQuantity()+",当前扣减数量为：" + supermarketGoods.getGoodsNum());
//	    		 }
//				//更新库存信息
//				modVoNotNull(newStock);
//			}
//		} catch (DaoException e) {
//			log.error("更新仓储中心库存信息出现系统错误！", e);
//			throw new ServiceException(RespCode.getSotck_error,"更新仓储中心库存信息出现系统错误.");
//		}
//	}
//
//	@Override
//	public void updateShopStockByOrder(SupermarketOrder order, int status) throws ServiceException {
//		//订单状态：0，创建订单扣减库存加预占库存；1,取消订单返回库存减预占库存；2,商家拒绝接单返回库存减预占库存；3，用户确认收货扣减预占库存；4，商家确认收货退款返回库存减预占库存
//		try {
//			List<SupermarketGoods> orderGoodsList = order.getSupermarketGoods();
//			for(SupermarketGoods supermarketGoods : orderGoodsList)
//			{
//				//获取库存信息
//				ShopGoodsSku skuStock = shopGoodsSkuService.findVo(supermarketGoods.getSkuId(), new ShopGoodsSku());
//				if(skuStock==null)
//				{
//					log.error("获取便利店库存信息为空，便利店ID："+order.getSellerId()+"，skuID："+supermarketGoods.getSkuId());
//					throw new ServiceException(RespCode.getSotck_isNull_error,"获取便利店库存信息为空.");
//				}
//				ShopGoodsSku newStock = new ShopGoodsSku();
//				newStock.setDmId(skuStock.getDmId());
//				//状态：0，创建订单扣减库存加预占库存；1,取消订单返回库存减预占库存；2,商家拒绝接单返回库存减预占库存；3，用户确认收货扣减预占库存；4，商家确认收货退款返回库存减预占库存
//				if(status==0)
//				{
//					newStock.setActual(skuStock.getActual()-supermarketGoods.getGoodsNum());
//					newStock.setFreezeNum(skuStock.getFreezeNum()+supermarketGoods.getGoodsNum());
//				}else if(status==1||status==2||status==4)
//				{
//					newStock.setActual(skuStock.getActual()+supermarketGoods.getGoodsNum());
//					newStock.setFreezeNum(skuStock.getFreezeNum()-supermarketGoods.getGoodsNum());
//				}else if(status==3)
//				{
//					newStock.setFreezeNum(skuStock.getFreezeNum()-supermarketGoods.getGoodsNum());
//				}
//				if( newStock.getActual() < 0 ){
//	    			 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "仓储中心----扣减库存扣减数量大于当前库存数量,skuID："+supermarketGoods.getSkuId()+",当前实际库存："+ newStock.getActual()+",当前扣减数量为：" + supermarketGoods.getGoodsNum());
//	    		 }
//				//更新库存信息
//				shopGoodsSkuService.modVoNotNull(newStock);
//			}
//		} catch (ServiceException e) {
//			log.error("更新便利店存信息出现系统错误！", e);
//			throw new ServiceException(RespCode.getSotck_error,"更新便利店库存信息出现系统错误.");
//		}
//	}
//
//	@Override
//	public List<SupermarketSkuStatus> getGoodsStock(Long cczxId, String skuIds) throws ServiceException {
//		try {
//			Map<String, String> param = new HashMap<String, String>();
//			param.put("cczxId", String.valueOf(cczxId));
//			param.put("skuIds", skuIds);
//			return dao.getGoodsStock(param);
//		} catch (DaoException e) {
//			log.error("根据仓储中心ID和SKUID获取商品库存信息出现系统错误！", e);
//			throw new ServiceException(RespCode.getSotck_error,"根据仓储中心ID和SKUID获取商品库存信息出现系统错误.");
//		}
//	}
//
//}
//
