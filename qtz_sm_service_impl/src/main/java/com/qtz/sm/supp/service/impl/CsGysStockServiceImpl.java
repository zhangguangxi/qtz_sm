package com.qtz.sm.supp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.response.RespCode;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.batch.vo.CsBatGoodsSku;
import com.qtz.sm.supp.dao.CsGysStockDao;
import com.qtz.sm.supp.service.CsGysStockService;
import com.qtz.sm.supp.vo.CsGysStock;

/**
 * Title:CsGysStockServiceImpl<br/>
 * Description:(供应商商品库存SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGysStockServiceImpl")
public class CsGysStockServiceImpl extends BaseServiceImpl<CsGysStock,Long> implements CsGysStockService{

    /**初始化日志对象*/
    private static LogTool log = LogTool.getInstance(CsGysStockServiceImpl.class);
    /**注入CsGysStockDao接口类*/
    @Resource(name="csGysStockDaoImpl")
    private CsGysStockDao dao;

    /**
     * 【取得】业务DAO对象
     * @return 	业务DAO对象
     */
    @Override
    protected BizDao<CsGysStock,Long> getDao() {
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
    
    /**
     * 供应商----扣减库存
     * @param skuId
     * @param deductionsQuantity
     * @throws ServiceException
     */
    @Override
    public void gysDeductionsStock(Long skuId,Integer deductionsQuantity ,Integer status) throws ServiceException{
    	try {
    		//查询当前库存
    		 CsGysStock csStock = dao.queryCsGysStockInfo(skuId);
    		 if( csStock.getStockQuantity() < deductionsQuantity ){
    			 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "供应商----扣减库存扣减数量大于当前库存数量,deductionsQuantity=" + deductionsQuantity);
    		 }else{
    			 if(status == 1){
    				//减数量， 加冻结数量
        			 //更新库存数量= 当前库存数量 - 扣减库存数量
        			 csStock.setStockQuantity( csStock.getStockQuantity()- deductionsQuantity );
        			 csStock.setAwaitQuantity( csStock.getAwaitQuantity() + deductionsQuantity);
        			 dao.modVoNotNull(csStock);
    			 }else if(status == 2){
    				//减冻结数量
    				 csStock.setAwaitQuantity( csStock.getAwaitQuantity() - deductionsQuantity);
        			 dao.modVoNotNull(csStock);
    			 }
    			
    		 }
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
    }
    /**
 	 * 根据商品ID获取供应商商品库存价格列表
 	 * @author 欧江波 928482427@qq.com
 	 * @param goodsId	商品ID
 	 * @return
 	 * @throws DaoException
 	 */
    @Override
 	public List<CsGysStock> getStocksByGoodsId(Long goodsId) throws ServiceException {
 		try {
    		return dao.getStocksByGoodsId(goodsId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
 	}

	@Override
	public void updateStock(CsBatGoodsSku sku, int orderStatus)  throws ServiceException{
		try {
			//获取当前库存数据
			CsGysStock nowStock = dao.queryCsGysStockInfo(sku.getSkuId());
			Integer deductionNum = sku.getBatNum();
			if(nowStock==null)
			{
				throw new ServiceException(RespCode.gd_sku_not_found,"SKU信息为空，skuId："+sku.getSkuId());
			}
			if(orderStatus == 1){
				 if( nowStock.getStockQuantity() < deductionNum ){
	    			 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "供应商----扣减库存扣减数量大于当前库存数量,扣减数量：" + sku.getBatNum()+"当前库存：" + nowStock.getStockQuantity());
	    		 }
				//减数量， 加冻结数量
    			 //更新库存数量= 当前库存数量 - 扣减库存数量
				 nowStock.setStockQuantity( nowStock.getStockQuantity()- deductionNum );
				 nowStock.setAwaitQuantity( nowStock.getAwaitQuantity() + deductionNum);
			 }else if(orderStatus == 2){
				//减冻结数量
				 nowStock.setAwaitQuantity( nowStock.getAwaitQuantity() - deductionNum);
			 }
    		dao.modVoNotNull(nowStock);
		} catch (DaoException e) {
			log.error("更新供应商库存信息出现系统错误！", e);
			throw new ServiceException(RespCode.update_batOrder_error,"更新供应商库存信息出现系统错误.");
		}
	}

	
	@Override
	public List<CsGysStock> queryFindList(CsGysStock csGysStock) throws ServiceException {
		try {
			return dao.queryFindList(csGysStock);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}

