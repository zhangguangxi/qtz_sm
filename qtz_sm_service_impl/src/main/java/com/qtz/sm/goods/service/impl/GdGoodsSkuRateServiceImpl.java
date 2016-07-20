package com.qtz.sm.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.Arith;
import com.mall.core.common.FifteenLongId;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.goods.dao.GdGoodsDao;
import com.qtz.sm.goods.dao.GdGoodsSkuRateDao;
import com.qtz.sm.goods.enums.GoodsOperationActionEnum;
import com.qtz.sm.goods.model.GdGoodsSkuRateExtOut;
import com.qtz.sm.goods.model.GdGoodsStock;
import com.qtz.sm.goods.page.GdGoodsSkuRatePage;
import com.qtz.sm.goods.page.GdGoodsStockPage;
import com.qtz.sm.goods.service.GdGoodsCategroyRateService;
import com.qtz.sm.goods.service.GdGoodsOperationHistoryService;
import com.qtz.sm.goods.service.GdGoodsSkuRateService;
import com.qtz.sm.goods.service.GdGoodsTypeService;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;
import com.qtz.sm.goods.vo.GdGoodsOperationHistory;
import com.qtz.sm.goods.vo.GdGoodsSkuRate;
import com.qtz.sm.goods.vo.GdGoodsType;
import com.qtz.sm.supp.dao.CsGysStockDao;
import com.qtz.sm.supp.vo.CsGysStock;

/**
 * <p>Title:GdGoodsSkuRateServiceImpl</p>
 * <p>Description:商品SKU议价服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 郭云龙- 252735833@qq.com
 * @version v1.0 2016-05-19
 */
@Service("gdGoodsSkuRateServiceImpl")
public class GdGoodsSkuRateServiceImpl extends BaseServiceImpl<GdGoodsSkuRate, java.lang.Long>
		implements GdGoodsSkuRateService {
	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(GdGoodsSkuRateServiceImpl.class);
	/** 注入商品分类溢价率DAO接口类 */
	@Resource(name = "gdGoodsSkuRateDaoImpl")
	private GdGoodsSkuRateDao dao;
	
	@Autowired
	private GdGoodsDao goodsDao;
	
	@Autowired
	private CsGysStockDao gysStockDao;
	
	@Autowired
	private GdGoodsCategroyRateService categoryRateService;
	
	@Autowired
	private GdGoodsOperationHistoryService gdGoodsOperationHistoryService;
	
	@Autowired
	private FifteenLongId fifteenLongId;
	
	
	@Autowired
	private GdGoodsTypeService goodsTypeService;

	/**
	 * 【取得】业务DAO对象
	 * 
	 * @return 业务DAO对象
	 */
	@Override
	protected BizDao<GdGoodsSkuRate, java.lang.Long> getDao() {
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

	@Override
	public Pager<GdGoodsSkuRateExtOut, Long> queryRates(GdGoodsSkuRatePage page) throws ServiceException {
		try {
			return dao.queryRates(page);
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Pager<GdGoodsStock, Long> listGoodsStock(GdGoodsStockPage page) throws ServiceException  {
		try {
			return dao.listGoodsStock(page);
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	//获取分类溢价
	private GdGoodsCategroyRate getCategoryRate(Long goodsTypeId) throws ServiceException{
		GdGoodsCategroyRate rate = categoryRateService.getCategoryRate(goodsTypeId);
		if (rate == null) {
			GdGoodsType parentGoodsType =  goodsTypeService.getParentGoodsType(goodsTypeId);
			if (parentGoodsType == null){
				return null;
			} else {
				return categoryRateService.getCategoryRate(parentGoodsType.getDmId());
			}
		}
		return rate;
	}
	
	//获取指定CompanyType的分类溢价率
	private Double getCategoryRate(Long goodsTypeId, Integer companyType) throws ServiceException{
		GdGoodsCategroyRate rate = this.getCategoryRate(goodsTypeId);
		if (rate == null) {
			return new Double(0);
		}
		
		if (companyType == CompanyType.SupplyChain.value()) {//供应链议价
			if (rate.getGylRate() == null) {
				GdGoodsType parentGoodsType =  goodsTypeService.getParentGoodsType(goodsTypeId);
				if (parentGoodsType == null){
					return new Double(0);
				} else {
					return getCategoryRate(parentGoodsType.getDmId(), companyType);
				}
			} else {
				return rate.getGylRate();
			}
		} else if (companyType == CompanyType.CloudStorage.value() ) {//云仓储管理公司议价
			if (rate.getYccglRate() == null) {
				GdGoodsType parentGoodsType =  goodsTypeService.getParentGoodsType(goodsTypeId);
				if (parentGoodsType == null){
					return new Double(0);
				} else {
					return getCategoryRate(parentGoodsType.getDmId(), companyType);
				}
			} else {
				return rate.getYccglRate();
			}
		} else if (companyType == CompanyType.StoreManager.value() ) {//便利店管理公司议价
			if (rate.getBldglRate() == null) {
				GdGoodsType parentGoodsType =  goodsTypeService.getParentGoodsType(goodsTypeId);
				if (parentGoodsType == null){
					return null;
				} else {
					return getCategoryRate(parentGoodsType.getDmId(), companyType);
				}
			} else {
				return rate.getBldglRate();
			}
		} else if (companyType == CompanyType.SuperMarket.value()) {//胖胖超市议价
			if (rate.getPpcsRate() == null) {
				GdGoodsType parentGoodsType =  goodsTypeService.getParentGoodsType(goodsTypeId);
				if (parentGoodsType == null){
					return new Double(0);
				} else {
					return getCategoryRate(parentGoodsType.getDmId(), companyType);
				}
			} else {
				return rate.getPpcsRate();
			}
		} else {
			throw new ServiceException("参数companyType值非法，companyType="+companyType);
		}
	}

	/***
	 * 获取商品指定SKU编号的溢价信息，没有SKU溢价率，使用当前级别的分类溢价,当前分类没有溢价信息，使用上级分类溢价
	 * 同时该方法会从供应商开始计算出各角色价格
	 * @author 欧江波 928482427@qq.com
	 * @param skuId		SKU编号
	 * @param goodsId	商品ID
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public GdGoodsSkuRate getGoodsSkuRate(Long goodsId, Long skuId) throws ServiceException {
		try {
			//获取SKU溢价
			GdGoodsSkuRate skuRate = getGoodsSkuRate(skuId); 
			if (skuRate == null) {
				return null;
			}
			
			//获取商品
			GdGoods gdGoodsVo = goodsDao.findVo(goodsId, null);
			if (gdGoodsVo == null) {
				throw new ServiceException("商品不存在,goodsId="+goodsId);
			}
			
			//获取商品分类的溢价信息
			//GdGoodsCategroyRate categroyRate = this.getCategoryRate(gdGoodsVo.getGoodsTypeId());
			
			//SKU溢价表中没有设置溢价信息，使用商品分类的溢价信息
			/** 供应链议价率 */
			java.lang.Double gylRate = skuRate.getGylRate();
			/** 云仓储管理公司议价率 */
			java.lang.Double yccglRate = skuRate.getYccglRate();
			/** 便利店管理公司议价率 */
			java.lang.Double bldglRate = skuRate.getBldglRate();
			/** 胖胖超市议价率 */
			java.lang.Double ppcsRate = skuRate.getPpcsRate();
			
			/** 供应链议价后价格 */
			java.lang.Double gylPrice	= skuRate.getGylPrice();
			/** 云仓储管理公司议价后价格 */
			java.lang.Double yccglPrice = skuRate.getYccglPrice();
			/** 便利店管理公司议价后价格 */
			java.lang.Double bldglPrice = skuRate.getBldglPrice();
			/** 胖胖超市议价后价格 */
			java.lang.Double ppcsPrice =  skuRate.getPpcsPrice();
			
			//供应链议价率
			Long goodsTypeId = gdGoodsVo.getGoodsTypeId();
			if (gylRate == null){
				gylRate = this.getCategoryRate(goodsTypeId, CompanyType.SupplyChain.value());
			}
			//云仓储管理公司议价率
			if (yccglRate == null){
				yccglRate = this.getCategoryRate(goodsTypeId, CompanyType.CloudStorage.value());
			}
			//便利店管理公司议价率
			if (bldglRate == null){
				bldglRate = this.getCategoryRate(goodsTypeId, CompanyType.StoreManager.value());
			}
			//胖胖超市议价率
			if (ppcsRate == null){
				ppcsRate = this.getCategoryRate(goodsTypeId, CompanyType.SuperMarket.value());
			}
			
			//获取供应商价格
			CsGysStock stock = getGysStock(skuId);
			Double gysPrice = null;
			if (stock != null){
				gysPrice = stock.getPrice();
			}
			if (gysPrice != null){ 
				gylPrice = Arith.mul(gysPrice, 1+gylRate);
				yccglPrice = Arith.mul(gylPrice, 1+yccglRate);
				bldglPrice = Arith.mul(yccglPrice, 1+bldglRate);
				ppcsPrice = Arith.mul(yccglPrice, 1+ppcsRate);
			} else {
				throw new ServiceException("供应商未设置价格,skuId="+skuId);
			}
			skuRate.setGysPrice(gysPrice);
			skuRate.setBldglPrice(bldglPrice);
			skuRate.setBldglRate(bldglRate);
			skuRate.setGylPrice(gylPrice);
			skuRate.setGylRate(gylRate);
			skuRate.setPpcsPrice(ppcsPrice);
			skuRate.setPpcsRate(ppcsRate);
			skuRate.setYccglPrice(yccglPrice);
			skuRate.setYccglRate(yccglRate);
			return skuRate;
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	//获取供应商库存价格信息
	private CsGysStock getGysStock(Long skuId) throws DaoException {
		CsGysStock gysStockQuery = new CsGysStock();
		gysStockQuery.setSkuId(skuId);
		List<CsGysStock> stockList =  gysStockDao.findList(gysStockQuery);
		CsGysStock stock = null;
		if (stockList != null & !stockList.isEmpty()) {
			stock = stockList.get(0);
		}
		return stock;
	}

	//获取商品SKU议价信息
	private GdGoodsSkuRate getGoodsSkuRate(Long skuId) throws DaoException {
		GdGoodsSkuRate skuRateQuery = new GdGoodsSkuRate();
		skuRateQuery.setSkuId(skuId);
		List<GdGoodsSkuRate> skuRateList = dao.findList(skuRateQuery);
		GdGoodsSkuRate skuRate = null;
		if (skuRateList != null && !skuRateList.isEmpty()) {
			skuRate = skuRateList.get(0);
		}
		return skuRate;
	}

	/**
	 * 修改价格
	 * 价格改动规则：
	 *（1）最底层的供应商，只可以下调价格，不可以上调价格，价格基础以第一次商品审核通过的那个价格为标准线
	 * （2）供应链价格 = 供应商价格*（1+供应链溢价率）
	 * （3）上级商家改了价格后，都会把下级商家设置的价格覆盖掉
	 * @param goodsId			商品ID
	 * @param skuId				SKU_ID
	 * @param newPrice			新价格
	 * @param companyType		公司类型，参考CompanyType枚举类
	 * @throws ServiceException
	 */
	@Override
	public void modifyPrice(Long goodsId, Long skuId, Double newPrice, Integer companyType, String ip, Long operator) throws ServiceException {
		try {
			if (companyType == null){
				throw new ServiceException("参数companyType不能为空");
			}
			if (newPrice == null){
				throw new ServiceException("参数newPrice不能为空");
			}
			
			GdGoodsSkuRate skuRate = this.getGoodsSkuRate(goodsId, skuId);
			if (skuRate == null) {  //添加
				skuRate = new GdGoodsSkuRate();
				skuRate.setSkuId(skuId);
				this.addVo(skuRate);
			}
			boolean update = false;
			Double newRate = null;
			Double orginalRate = null;
			if (companyType == CompanyType.SupplyChain.value()) {//供应链议价
				//获取供应商价格
				CsGysStock stock = getGysStock(skuId);
				if (stock!=null && stock.getPrice()!=null && !newPrice.equals(skuRate.getGylPrice())) { 
					orginalRate = skuRate.getGylRate();
					newRate = Arith.div(newPrice, stock.getPrice(), 5)-1;
					skuRate.setGylRate(newRate);
					skuRate.setYccglRate(null); //不改变其他角色的溢价率
					skuRate.setPpcsRate(null);
					skuRate.setBldglRate(null);
					update = true;
				}
			} else if (companyType == CompanyType.StorageCenter.value()  && !newPrice.equals(skuRate.getYccglPrice())) {//云仓储管理公司议价
				if (skuRate.getGylPrice() != null){
					orginalRate = skuRate.getYccglRate();
					newRate = Arith.div(newPrice, skuRate.getGylPrice(), 5)-1;
					skuRate.setYccglRate(newRate);
					skuRate.setPpcsRate(null);
					skuRate.setBldglRate(null);
					skuRate.setGylRate(null);
					update = true;
				}
			} else if (companyType == CompanyType.StoreManager.value()  && !newPrice.equals(skuRate.getBldglPrice())) {//便利店管理公司议价
				if (skuRate.getYccglPrice() != null){
					orginalRate = skuRate.getBldglRate();
					newRate = Arith.div(newPrice, skuRate.getYccglPrice(), 5)-1;
					skuRate.setBldglRate(newRate);
					skuRate.setPpcsRate(null);
					skuRate.setYccglRate(null);
					skuRate.setGylRate(null);
					update = true;
				}
			} else if (companyType == CompanyType.SuperMarket.value()  && !newPrice.equals(skuRate.getPpcsPrice())) {//胖胖超市议价
				if (skuRate.getYccglPrice() != null){
					orginalRate = skuRate.getPpcsRate();
					newRate = Arith.div(newPrice, skuRate.getYccglPrice(), 5)-1;
					skuRate.setPpcsRate(newRate);
					skuRate.setBldglRate(null);
					skuRate.setYccglRate(null);
					skuRate.setGylRate(null);
					update = true;
				}
			} else {
				throw new ServiceException("参数userRoleType值非法，userRoleType="+companyType);
			}
			if(update){
				skuRate.setBldglPrice(null);
				skuRate.setGylPrice(null);
				skuRate.setPpcsPrice(null);
				skuRate.setYccglPrice(null);
				this.modVoNotNull(skuRate);
				this.addModifyPriceHistory(goodsId, orginalRate, newRate, ip, operator);
			}
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	private void addModifyPriceHistory(Long goodsId, double orginalRate, double newRate, String ip, Long operator)
			throws ServiceException {
		String content = "将价格由\""+orginalRate+"\"改为\""+newRate+"\"";
		GdGoodsOperationHistory historyVo = new GdGoodsOperationHistory();
		historyVo.setDmId(fifteenLongId.nextId());
		historyVo.setOperator(operator);
		historyVo.setGoodsId(goodsId);
		historyVo.setIp(ip);
		historyVo.setAct(GoodsOperationActionEnum.modify_sku_rate.getValue());
		historyVo.setContent(content);
		historyVo.setReason("");
		historyVo.setOperateOn(System.currentTimeMillis());
		
		gdGoodsOperationHistoryService.addVo(historyVo);
	}

	@Override
	public void updateGoodsSkuRate(GdGoodsSkuRate gdGoodsSkuRate, GdGoodsOperationHistory gdGoodsOperationHistory) throws ServiceException {
		try {
			dao.modVoNotNull(gdGoodsSkuRate);
			if(gdGoodsOperationHistory!=null){
				//添加操作记录
				gdGoodsOperationHistoryService.addVo(gdGoodsOperationHistory);
			}
		} catch (DaoException e) {
			log.error("商品管理-商品库-议价失败！", e);
			throw new ServiceException("商品管理-商品库-议价失败");
		}
	}
}