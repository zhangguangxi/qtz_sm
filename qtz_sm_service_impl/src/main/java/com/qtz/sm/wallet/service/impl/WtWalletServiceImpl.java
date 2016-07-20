package com.qtz.sm.wallet.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.mall.core.common.FifteenLongId;
import com.mall.core.common.jsonUtil.JSONUtils;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.goods.service.GdGoodsService;
import com.qtz.sm.goods.service.GdGoodsSkuRateService;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.scm.service.CsGylInfoService;
import com.qtz.sm.scm.vo.CsGylInfo;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.vo.ShopInfo;
import com.qtz.sm.shopManage.service.ShopManageService;
import com.qtz.sm.shopManage.vo.ShopManage;
import com.qtz.sm.stc.service.CsYccInfoService;
import com.qtz.sm.stc.vo.CsYccInfo;
import com.qtz.sm.store.service.CsCczxInfoService;
import com.qtz.sm.store.vo.CsCczxInfo;
import com.qtz.sm.supermarket.service.SupermarketInfoService;
import com.qtz.sm.supermarket.vo.SupermarketInfo;
import com.qtz.sm.supp.service.CsGysInfoService;
import com.qtz.sm.supp.vo.CsGysInfo;
import com.qtz.sm.wallet.config.WalletConfig;
import com.qtz.sm.wallet.dao.WtWalletDao;
import com.qtz.sm.wallet.enums.IncomeStatusEnum;
import com.qtz.sm.wallet.enums.WalletItemEnum;
import com.qtz.sm.wallet.model.WtOrder;
import com.qtz.sm.wallet.model.WtOrderDetail;
import com.qtz.sm.wallet.page.WtWalletPage;
import com.qtz.sm.wallet.service.WtBldIncomeService;
import com.qtz.sm.wallet.service.WtBldglIncomeService;
import com.qtz.sm.wallet.service.WtCczxIncomeService;
import com.qtz.sm.wallet.service.WtCsIncomeService;
import com.qtz.sm.wallet.service.WtGylIncomeService;
import com.qtz.sm.wallet.service.WtGysIncomeService;
import com.qtz.sm.wallet.service.WtWalletService;
import com.qtz.sm.wallet.service.WtYccBldglIncomeService;
import com.qtz.sm.wallet.service.WtYccCsIncomeService;
import com.qtz.sm.wallet.validator.ValidFirst;
import com.qtz.sm.wallet.vo.WtBldIncome;
import com.qtz.sm.wallet.vo.WtBldglIncome;
import com.qtz.sm.wallet.vo.WtCczxIncome;
import com.qtz.sm.wallet.vo.WtCsIncome;
import com.qtz.sm.wallet.vo.WtGylIncome;
import com.qtz.sm.wallet.vo.WtGysIncome;
import com.qtz.sm.wallet.vo.WtWallet;
import com.qtz.sm.wallet.vo.WtYccBldglIncome;
import com.qtz.sm.wallet.vo.WtYccCsIncome;

import utils.BeanValidator;

/**
 * <p>
 * Title:WtWalletServiceImpl
 * </p>
 * <p>
 * Description:钱包信息服务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 * 
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-25
 */
@Service("wtWalletServiceImpl")
public class WtWalletServiceImpl extends BaseServiceImpl<com.qtz.sm.wallet.vo.WtWallet, java.lang.Long>
		implements WtWalletService {
	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(WtWalletServiceImpl.class);
	/** 注入钱包信息DAO接口类 */
	@Resource(name = "wtWalletDaoImpl")
	private WtWalletDao dao;
	
	@Autowired
	private FifteenLongId fifteenLongId;

	@Resource(name="gdGoodsSkuRateServiceImpl")
	private GdGoodsSkuRateService gdSkuRateService;
	
	@Resource(name="csCczxInfoServiceImpl")
	private CsCczxInfoService cczxInfoService;
	
	@Resource(name="supermarketInfoServiceImpl")
	private SupermarketInfoService supermarketInfoService;
	
	@Resource(name="shopInfoServiceImpl")
	private ShopInfoService shopInfoService;
	
	@Resource(name="shopManageServiceImpl")
	private ShopManageService shopManageService;
	
	@Resource(name="csYccInfoServiceImpl")
	private CsYccInfoService csYccInfoService;
	
	@Resource(name="csGylInfoServiceImpl")
	private CsGylInfoService csGylInfoService;
	
	@Resource(name="csGysInfoServiceImpl")
	private CsGysInfoService csGysInfoService;
	
	@Resource(name="gdGoodsServiceImpl")
	private GdGoodsService goodsService;
	
	/** 供应商收入流水 */
	@Resource(name = "wtGysIncomeServiceImpl")
	private WtGysIncomeService gysIncomeService;
	
	/** 供应链收入流水 */
	@Resource(name = "wtGylIncomeServiceImpl")
	private WtGylIncomeService gylIncomeService;
	
	/** 超市收入流水 */
	@Resource(name = "wtCsIncomeServiceImpl")
	private WtCsIncomeService csIncomeService;
	
	/** 便利店收入流水 */
	@Resource(name = "wtBldIncomeServiceImpl")
	private WtBldIncomeService bldIncomeService;
	
	/** 便利店管理公司收入流水 */
	@Resource(name = "wtBldglIncomeServiceImpl")
	private WtBldglIncomeService bldglIncomeService;
	
	/** 仓储中心收入流水 */
	@Resource(name = "wtCczxIncomeServiceImpl")
	private WtCczxIncomeService cczxIncomeService;
	
	/** 云仓储管理公司从超市获得的收入流水 */
	@Resource(name = "wtYccCsIncomeServiceImpl")
	private WtYccCsIncomeService yccCsIncomeService;
	
	/** 云仓储管理公司从便利店管理公司获得的收入流水  */
	@Resource(name = "wtYccBldglIncomeServiceImpl")
	private WtYccBldglIncomeService yccBldglIncomeService;

	
	/**
	 * 【取得】业务DAO对象
	 * 
	 * @return 业务DAO对象
	 */
	@Override
	protected BizDao<com.qtz.sm.wallet.vo.WtWallet, java.lang.Long> getDao() {
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
	public void addGysWallet(long companyId, String companyName,long payerId) throws ServiceException {
		try {
			dao.delByOwner(companyId);
			WtWallet vo = new WtWallet();
			vo.setCreateTime(System.currentTimeMillis());
			vo.setOwnerType(CompanyType.Supplier.value());
			vo.setOwnerId(companyId);
			vo.setOwnerName(companyName);

			vo.setPayerType(CompanyType.SupplyChain.value());
			vo.setItemAmount(0.0);
			vo.setPayerId(payerId);
			
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public void addGylWallet(long companyId, String companyName,long payerId) throws ServiceException {
		try {
			dao.delByOwner(companyId);
			WtWallet vo = new WtWallet();
			vo.setCreateTime(System.currentTimeMillis());
			vo.setOwnerType(CompanyType.SupplyChain.value());
			vo.setOwnerId(companyId);
			vo.setOwnerName(companyName);
			

			vo.setItemAmount(0.0);
			vo.setPayerType(CompanyType.CloudStorage.value());

			vo.setDmId(null);
			vo.setPayerId(payerId);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setPayerId(payerId);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setPayerId(payerId);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);

			vo.setPayerType(CompanyType.Supplier.value());

			vo.setDmId(null);
			vo.setPayerId(null);
			vo.setItemId(WalletItemEnum.PAID.getValue());
			vo.setItemName(WalletItemEnum.PAID.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setPayerId(null);
			vo.setItemId(WalletItemEnum.UPPAID.getValue());
			vo.setItemName(WalletItemEnum.UPPAID.getText());
			addVo(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addYccWallet(long companyId, String companyName,long cspayerId,long bldglpayerId) throws ServiceException {
		try {
			dao.delByOwner(companyId);
			WtWallet vo = new WtWallet();
			vo.setCreateTime(System.currentTimeMillis());
			vo.setOwnerType(CompanyType.CloudStorage.value());
			vo.setOwnerId(companyId);
			vo.setOwnerName(companyName);
			vo.setItemAmount(0.0);

			
			//云仓储从超市的应收、对账、提现钱包
			vo.setPayerType(CompanyType.SuperMarket.value());
			vo.setPayerId(cspayerId);
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);
			vo.setPayerId(cspayerId);
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);
			vo.setPayerId(cspayerId);
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);

			
			//云仓储从便利店管理公司的应收、对账、提现钱包
			vo.setPayerType(CompanyType.StoreManager.value());
			vo.setPayerId(bldglpayerId);
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);
			vo.setPayerId(bldglpayerId);
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);
			vo.setPayerId(bldglpayerId);
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);

			
			// 云仓储向供应链的已付未付钱包
			vo.setPayerType(CompanyType.SupplyChain.value());
			vo.setPayerId(null);
			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.PAID.getValue());
			vo.setItemName(WalletItemEnum.PAID.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setPayerId(null);
			vo.setItemId(WalletItemEnum.UPPAID.getValue());
			vo.setItemName(WalletItemEnum.UPPAID.getText());
			addVo(vo);
			
			// 云仓储向仓储中心的已付未付钱包
			vo.setPayerType(CompanyType.StorageCenter.value());
			vo.setDmId(null);
			vo.setPayerId(null);
			vo.setItemId(WalletItemEnum.PAID.getValue());
			vo.setItemName(WalletItemEnum.PAID.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setPayerId(null);
			vo.setItemId(WalletItemEnum.UPPAID.getValue());
			vo.setItemName(WalletItemEnum.UPPAID.getText());
			addVo(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addCczxWallet(long companyId, String companyName,long payerId) throws ServiceException {
		try {
			dao.delByOwner(companyId);
			WtWallet vo = new WtWallet();
			vo.setCreateTime(System.currentTimeMillis());
			vo.setOwnerType(CompanyType.StorageCenter.value());
			vo.setOwnerId(companyId);
			vo.setOwnerName(companyName);
			vo.setItemAmount(0.0);
			vo.setPayerId(payerId);

			vo.setPayerType(CompanyType.CloudStorage.value());

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		
	}

	@Override
	public void addCsWallet(long companyId, String companyName) throws ServiceException {
		try {
			dao.delByOwner(companyId);

			WtWallet vo = new WtWallet();
			vo.setCreateTime(System.currentTimeMillis());
			vo.setOwnerType(CompanyType.SuperMarket.value());
			vo.setOwnerId(companyId);
			vo.setOwnerName(companyName);
			vo.setItemAmount(0.0);

			vo.setPayerType(CompanyType.PPSH.value());

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);

			vo.setPayerType(CompanyType.CloudStorage.value());

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.PAID.getValue());
			vo.setItemName(WalletItemEnum.PAID.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.UPPAID.getValue());
			vo.setItemName(WalletItemEnum.UPPAID.getText());
			addVo(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addBldglWallet(long companyId, String companyName) throws ServiceException {
		try {
			dao.delByOwner(companyId);
			WtWallet vo = new WtWallet();
			vo.setCreateTime(System.currentTimeMillis());
			vo.setOwnerType(CompanyType.StoreManager.value());
			vo.setOwnerId(companyId);
			vo.setOwnerName(companyName);
			vo.setItemAmount(0.0);

			vo.setPayerType(CompanyType.PPSH.value());

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);

			vo.setPayerType(CompanyType.CloudStorage.value());

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.PAID.getValue());
			vo.setItemName(WalletItemEnum.PAID.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.UPPAID.getValue());
			vo.setItemName(WalletItemEnum.UPPAID.getText());
			addVo(vo);

			vo.setPayerType(CompanyType.Store.value());

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.PAID.getValue());
			vo.setItemName(WalletItemEnum.PAID.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.UPPAID.getValue());
			vo.setItemName(WalletItemEnum.UPPAID.getText());
			addVo(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addBldWallet(long companyId, String companyName,long payerId) throws ServiceException {
		try {
			dao.delByOwner(companyId);
			WtWallet vo = new WtWallet();
			vo.setCreateTime(System.currentTimeMillis());
			vo.setOwnerType(CompanyType.Store.value());
			vo.setOwnerId(companyId);
			vo.setOwnerName(companyName);
			vo.setItemAmount(0.0);
			vo.setPayerId(payerId);

			vo.setPayerType(CompanyType.StoreManager.value());

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOMED.getValue());
			vo.setItemName(WalletItemEnum.INCOMED.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.INCOME.getValue());
			vo.setItemName(WalletItemEnum.INCOME.getText());
			addVo(vo);

			vo.setDmId(null);
			vo.setItemId(WalletItemEnum.WITHDRAWALS.getValue());
			vo.setItemName(WalletItemEnum.WITHDRAWALS.getText());
			addVo(vo);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Pager<WtWallet, Long> getUppaidList(WtWalletPage wpage) throws ServiceException {
		try {
			//wpage.setItemId(WalletItemEnum.UPPAID.getValue());
			wpage.setItemId(WalletItemEnum.INCOMED.getValue());
			Pager<WtWallet, Long> page=this.query(wpage, null);
			return page;
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}


	public int modWalletAmountIncr(WtWallet wallet) throws ServiceException {
		try {
			return dao.modWalletAmountIncr(wallet);
		} catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	
	@Override
	public WtWallet getWallet(WtWallet wallet) throws ServiceException {
		log.debug("查询钱包，请求参数---->>>" + JSONUtils.Object2JSON(wallet));
		try {
			Assert.notNull(wallet.getOwnerId(), "钱包所有者为空");
			Assert.notNull(wallet.getOwnerType(), "钱包所有者类型为空");
			Assert.notNull(wallet.getItemId(), "钱包类型为空");
			Assert.notNull(wallet.getPayerType(), "付款人类型为空");
			List<WtWallet>	result=this.findList(wallet);
			if(result != null){
				if(result.size() == 1){
					return result.get(0);
				}else{
					log.error("根据查询条件查询到的钱包有" + result.size() + "个");
				}
			}else{
				log.error("根据查询条件未查询到对应钱包");
			}
			return null;
		}catch (Exception e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * 
	 * @param ownerId	钱包所有者的ID
	 * @param itemId	钱包类型： 对账中钱包、应收钱包
	 * @return	查询到对应1个钱包则正常返回， 否则返回null
	 * @throws ServiceException
	 */
	@Override
	public WtWallet getWallet(Long ownerId, Integer ownerType, Integer itemId, Integer payerType) throws ServiceException {
		WtWallet wallet = new WtWallet();
		wallet.setOwnerId(ownerId);
		wallet.setOwnerType(ownerType);
		wallet.setItemId(itemId);
		wallet.setPayerType(payerType);
		
		
		List<WtWallet> result =this.findList(wallet);
		if(result != null){
			if(result.size() == 1){
				return result.get(0);
			}else{
				final String errMsg = "钱包有问题，根据查询条件查询到的钱包有" + result.size() + "个, param-->wallet:" + JSONUtils.Object2JSON(wallet);
				log.error(errMsg);
				throw new ServiceException(ExceptionCode.WALLET_ERROR, errMsg);
			}
		}else{
			final String errMsg = "钱包有问题，根据查询条件未查询到对应钱包, param-->wallet:" + JSONUtils.Object2JSON(wallet);
			log.error(errMsg);
			throw new ServiceException(ExceptionCode.WALLET_ERROR, errMsg);
		}
	}
	
	/**
	 * 取消分润，必须保证该笔订单未结算过才能申请退货取消分润，
	 * 	要达到这一要求则是必须要求 分润收入解冻周期大于 订单退货周期
	 * 
	 * 取消分润步骤：
	 * 
	 * 1、判断该比订单收入是否结算过， （只检测该笔收入分给其中一个组织的收入是否结算过，不检测所有）
	 * 2、对应对账中钱包扣钱
	 * 3、收入流水状态设为退货
	 * 
	 * 
	 * @param order
	 * @throws ServiceException
	 */
	@Override
	public void modUnDistributions(WtOrder order) throws ServiceException {
		if(order == null){
			throw new ServiceException("参数order is null");
		}
		//校验参数，检验不通过则抛出异常
		BeanValidator.validate(order,ValidFirst.class);
				
		Integer orderType = order.getOrderType();
		if(WtOrder.SUPERMARKET_ORDER == orderType){
			unDistributionForCsOrder(order);
		}else if(WtOrder.SHOP_ORDER == orderType ){
			unDistributionForStoreOrder(order);
		}else{
			log.error("订单类型未知，orderType=" + orderType);
			throw new ServiceException("订单类型未知，orderType=" + orderType);
		}
	}
	
	
	/**
	 * 超市订单取消分润
	 * 
	 * @param order
	 * @throws ServiceException 
	 */
	private void unDistributionForCsOrder(WtOrder order) throws ServiceException {
		try{
			Long orderId = order.getOrderId();
			Assert.notNull(orderId, "超市退货订单ID为空");
			
			//判断该笔订单是否已分润
			WtCsIncome dupCheckQuery = new WtCsIncome();
			dupCheckQuery.setOrderId(orderId);
			List<WtCsIncome> csIncomeList = csIncomeService.findList(dupCheckQuery);
			if(csIncomeList == null || csIncomeList.size() <= 0){
				throw new ServiceException("此超市订单未分润过,不用取消分润, orderId=" + orderId);
			}
			
			List<WtOrderDetail> orderDetails = order.getOrderDetails();
			
			//如果没有传订单详情则将整订单剩余未退货的商品退货
			if(CollectionUtils.isEmpty(orderDetails)){
				orderDetails = new ArrayList<WtOrderDetail>();
				for (WtCsIncome item : csIncomeList) {
					//冻结的，为可退货的
					if(IncomeStatusEnum.FREEZE.getValue().equals(item.getStat()) ){
						WtOrderDetail detail = new WtOrderDetail();
						detail.setOrderItemId(item.getOrderItemId()); 		//设置订单item ID
						orderDetails.add(detail);
					}
				}
				Assert.notEmpty(orderDetails, "该超市订单无还可退货商品, orderId=" + orderId);
				order.setOrderDetails(orderDetails);
			}
			
			
			//根据退货订单明细的信息，逐一给各组织的对账中账户扣钱
			for (WtOrderDetail oItem : orderDetails) {
				Long orderItemId = oItem.getOrderItemId();

				
//				超市流水
				WtCsIncome csIncomeQuery = new WtCsIncome();
				csIncomeQuery.setOrderId(orderId);
				csIncomeQuery.setOrderItemId(orderItemId);
				WtCsIncome csIncome = csIncomeService.findList(csIncomeQuery ).get(0);
				Assert.notNull("根据订单详情ID未查询到超市流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( IncomeStatusEnum.REFUND.getValue().equals(csIncome.getStat()) ){
					throw new ServiceException("重复请求，该退货订单明细产生的收入流水已取消过，orderItemId=" + orderItemId);
				}
				if( !IncomeStatusEnum.FREEZE.getValue().equals(csIncome.getStat()) ){
					throw new ServiceException("超市流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(csIncome));
				}
				
//				云仓储管理中心流水
				WtYccCsIncome yccCsIncomeQuery = new WtYccCsIncome();
				yccCsIncomeQuery.setOrderId(orderId);
				yccCsIncomeQuery.setOrderItemId(orderItemId);
				WtYccCsIncome yccCsIncome = yccCsIncomeService.findList(yccCsIncomeQuery).get(0);
				Assert.notNull(yccCsIncome, "根据订单详情ID未查询到云仓储从超市的流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(yccCsIncome.getStat()) ){
					throw new ServiceException("云仓储从超市的流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(yccCsIncome));
				}
				
//				仓储中心流水
				WtCczxIncome cczxIncomeQuery = new WtCczxIncome();
				cczxIncomeQuery.setOrderId(orderId);
				cczxIncomeQuery.setOrderItemId(orderItemId);
				WtCczxIncome cczxIncome = cczxIncomeService.findList(cczxIncomeQuery).get(0);
				Assert.notNull(cczxIncome, "根据订单详情ID未查询到仓储中心流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(cczxIncome.getStat()) ){
					throw new ServiceException("仓储中心流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(cczxIncome));
				}
				
//				供应链流水
				WtGylIncome gylIncomeQuery = new WtGylIncome();
				gylIncomeQuery.setOrderId(orderId);
				gylIncomeQuery.setOrderItemId(orderItemId);
				WtGylIncome gylIncome = gylIncomeService.findList(gylIncomeQuery).get(0);
				Assert.notNull(gylIncome, "根据订单详情ID未查询到供应链流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(gylIncome.getStat()) ){
					throw new ServiceException("供应链流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(gylIncome));
				}
		
//				供应商流水
				WtGysIncome gysIncomeQuery = new WtGysIncome();
				gysIncomeQuery.setOrderId(orderId);
				gysIncomeQuery.setOrderItemId(orderItemId);
				WtGysIncome gysIncome = gysIncomeService.findList(gysIncomeQuery).get(0);
				Assert.notNull(gysIncome, "根据订单详情ID未查询到供应商流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(gysIncome.getStat()) ){
					throw new ServiceException("供应商流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(gysIncome));
				}
				
				
//				步骤二：修改流水状态
		
//				修改超市流水状态
				WtCsIncome csIncomeMod = new WtCsIncome();
				csIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				csIncomeMod.setDmId(csIncome.getDmId());
				csIncomeService.modVoNotNull(csIncomeMod );
				
//				修改云仓储从超市获得的收入流水状态
				WtYccCsIncome yccCsIncomeMod = new WtYccCsIncome();
				yccCsIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				yccCsIncomeMod.setDmId(yccCsIncome.getDmId());
				yccCsIncomeService.modVoNotNull(yccCsIncomeMod );
				
				
//				修改仓储中心收入流水状态
				WtCczxIncome cczxIncomeMod = new WtCczxIncome();
				cczxIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				cczxIncomeMod.setDmId(cczxIncome.getDmId());
				cczxIncomeService.modVoNotNull(cczxIncomeMod );
				
//				修改供应链收入流水状态
				WtGylIncome gylIncomeMod = new WtGylIncome();
				gylIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				gylIncomeMod.setDmId(gylIncome.getDmId());
				gylIncomeService.modVoNotNull(gylIncomeMod );
				
//				修改供应商收入流水状态
				WtGysIncome gysIncomeMod = new WtGysIncome();
				gysIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				gysIncomeMod.setDmId(gysIncome.getDmId());
				gysIncomeService.modVoNotNull(gysIncomeMod );
				
				
				//步骤三： 对账中钱包金额减少
				addCsIncomeWtAmount(csIncome.getOwnerId(), -csIncome.getAmount());				//超市对账中钱包减去之前增加的收入
				addYccCsIncomeWtAmount(yccCsIncome.getOwnerId(), -yccCsIncome.getAmount());		//云仓储从超市获得的对账中收入钱包减去之前增加的收入
				addCczxIncomeWtAmount(cczxIncome.getOwnerId(), -cczxIncome.getAmount());		//仓储中心分润对账中钱包减去之前增加的收入
				addGylIncomeWtAmount(gylIncome.getOwnerId(), -gylIncome.getAmount());			//供应链对账中钱包减去之前增加的收入
				addGysIncomeWtAmount(gysIncome.getOwnerId(), -gysIncome.getAmount());			//供应商对账中钱包减去之前增加的收入
			}
		}
		catch(ServiceException e){
			log.error(e);
			throw e;
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}
		
	}

	/**
	 * 便利店订单取消分润
	 * 
	 * @param order
	 * @throws ServiceException 
	 */
	private void unDistributionForStoreOrder(WtOrder order) throws ServiceException {
		try{
			Long orderId = order.getOrderId();
			Assert.notNull(orderId, "便利店退货订单ID为空");
			
			//判断该笔订单是否已分润
			WtBldglIncome dupCheckQuery = new WtBldglIncome();
			dupCheckQuery.setOrderId(orderId);
			List<WtBldglIncome> bldglIncomeList = bldglIncomeService.findList(dupCheckQuery );
			
			if(bldglIncomeList == null || bldglIncomeList.size() <= 0){
				throw new ServiceException("此便利店订单未分润过,不用取消分润, orderId=" + orderId);
			}
			
			List<WtOrderDetail> orderDetails = order.getOrderDetails();
			//如果没有传订单详情则将整订单剩余未退货的商品退货
			if(CollectionUtils.isEmpty(orderDetails)){
				orderDetails = new ArrayList<WtOrderDetail>();
				for (WtBldglIncome item : bldglIncomeList) {
					//冻结的，为可退货的
					if(IncomeStatusEnum.FREEZE.getValue().equals(item.getStat()) ){
						WtOrderDetail detail = new WtOrderDetail();
						detail.setOrderItemId(item.getOrderItemId()); 		//设置订单item ID
						orderDetails.add(detail);
					}
				}
				Assert.notEmpty(orderDetails, "该便利店订单无还可退货商品，orderId="+ orderId);
				order.setOrderDetails(orderDetails);
			}
			
			//根据退货订单明细的信息，逐一给各组织的对账中账户扣钱
			for (WtOrderDetail oItem : orderDetails) {
				Long orderItemId = oItem.getOrderItemId();

				
//				便利店管理公司流水
				WtBldglIncome bldglIncomeQuery = new WtBldglIncome();
				bldglIncomeQuery.setOrderId(orderId);
				bldglIncomeQuery.setOrderItemId(orderItemId);
				WtBldglIncome bldglIncome = bldglIncomeService.findList(bldglIncomeQuery ).get(0);
				Assert.notNull("根据订单详情ID未查询到便利店管理公司收入流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( IncomeStatusEnum.REFUND.getValue().equals(bldglIncome.getStat()) ){
					throw new ServiceException("重复请求，该退货订单明细产生的收入流水已取消过，orderItemId=" + orderItemId);
				}
				if( !IncomeStatusEnum.FREEZE.getValue().equals(bldglIncome.getStat()) ){
					throw new ServiceException("便利店管理公司流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(bldglIncome));
				}
				
//				便利店流水
				WtBldIncome bldIncomeQuery = new WtBldIncome();
				bldIncomeQuery.setOrderId(orderId);
				bldIncomeQuery.setOrderItemId(orderItemId);
				WtBldIncome bldIncome = bldIncomeService.findList(bldIncomeQuery ).get(0);
				Assert.notNull("根据订单详情ID未查询到便利店收入流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(bldIncome.getStat()) ){
					throw new ServiceException("便利店流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(bldIncome));
				}
				
//				云仓储管理中心流水
				WtYccBldglIncome yccBldglIncomeQuery = new WtYccBldglIncome();
				yccBldglIncomeQuery.setOrderId(orderId);
				yccBldglIncomeQuery.setOrderItemId(orderItemId);
				WtYccBldglIncome yccBldglIncome = yccBldglIncomeService.findList(yccBldglIncomeQuery).get(0);
				Assert.notNull(yccBldglIncome, "根据订单详情ID未查询到云仓储从便利店管理公司的流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(yccBldglIncome.getStat()) ){
					throw new ServiceException("云仓储从便利店管理公司的流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(yccBldglIncome));
				}
				
//				仓储中心流水
				WtCczxIncome cczxIncomeQuery = new WtCczxIncome();
				cczxIncomeQuery.setOrderId(orderId);
				cczxIncomeQuery.setOrderItemId(orderItemId);
				WtCczxIncome cczxIncome = cczxIncomeService.findList(cczxIncomeQuery).get(0);
				Assert.notNull(cczxIncome, "根据订单详情ID未查询到仓储中心流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(cczxIncome.getStat()) ){
					throw new ServiceException("仓储中心流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(cczxIncome));
				}
				
//				供应链流水
				WtGylIncome gylIncomeQuery = new WtGylIncome();
				gylIncomeQuery.setOrderId(orderId);
				gylIncomeQuery.setOrderItemId(orderItemId);
				WtGylIncome gylIncome = gylIncomeService.findList(gylIncomeQuery).get(0);
				Assert.notNull(gylIncome, "根据订单详情ID未查询到供应链流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(gylIncome.getStat()) ){
					throw new ServiceException("供应链流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(gylIncome));
				}
		
//				供应商流水
				WtGysIncome gysIncomeQuery = new WtGysIncome();
				gysIncomeQuery.setOrderId(orderId);
				gysIncomeQuery.setOrderItemId(orderItemId);
				WtGysIncome gysIncome = gysIncomeService.findList(gysIncomeQuery).get(0);
				Assert.notNull(gysIncome, "根据订单详情ID未查询到供应商流水, orderId=" + orderId + ", orderItemId=" + orderItemId);
				if( !IncomeStatusEnum.FREEZE.getValue().equals(gysIncome.getStat()) ){
					throw new ServiceException("供应商流水不是冻结状态，无法退货， " + JSONUtils.Object2JSON(gysIncome));
				}
				
				
//				步骤二：修改流水状态
				
//				修改便利店管理公司流水状态
				WtBldglIncome bldglIncomeMod = new WtBldglIncome();
				bldglIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				bldglIncomeMod.setDmId(bldglIncome.getDmId());
				bldglIncomeService.modVoNotNull(bldglIncomeMod );
				
//				修改便利店流水状态
				WtBldIncome bldIncomeMod = new WtBldIncome();
				bldIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				bldIncomeMod.setDmId(bldIncome.getDmId());
				bldIncomeService.modVoNotNull(bldIncomeMod );
				
//				修改云仓储从便利店管理公司获得的收入流水状态
				WtYccBldglIncome yccBldglIncomeMod = new WtYccBldglIncome();
				yccBldglIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				yccBldglIncomeMod.setDmId(yccBldglIncome.getDmId());
				yccBldglIncomeService.modVoNotNull(yccBldglIncomeMod );
				
				
//				修改仓储中心收入流水状态
				WtCczxIncome cczxIncomeMod = new WtCczxIncome();
				cczxIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				cczxIncomeMod.setDmId(cczxIncome.getDmId());
				cczxIncomeService.modVoNotNull(cczxIncomeMod );
				
//				修改供应链收入流水状态
				WtGylIncome gylIncomeMod = new WtGylIncome();
				gylIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				gylIncomeMod.setDmId(gylIncome.getDmId());
				gylIncomeService.modVoNotNull(gylIncomeMod );
				
//				修改供应商收入流水状态
				WtGysIncome gysIncomeMod = new WtGysIncome();
				gysIncomeMod.setStat(IncomeStatusEnum.REFUND.getValue());
				gysIncomeMod.setDmId(gysIncome.getDmId());
				gysIncomeService.modVoNotNull(gysIncomeMod );
				
				
				//步骤三： 对账中钱包金额减少
				addBldglIncomeWtAmount(bldglIncome.getOwnerId(), -bldglIncome.getAmount());			//便利店管理公司对账中钱包减去之前增加的收入
				addBldIncomeWtAmount(bldIncome.getOwnerId(), -bldIncome.getAmount());				//便利店对账中钱包减去之前增加的收入
				addYccBldglIncomeWtAmount(yccBldglIncome.getOwnerId(), -yccBldglIncome.getAmount());//云仓储从便利店管理公司获得的对账中收入钱包减去之前增加的收入
				addCczxIncomeWtAmount(cczxIncome.getOwnerId(), -cczxIncome.getAmount());			//仓储中心分润对账中钱包减去之前增加的收入
				addGylIncomeWtAmount(gylIncome.getOwnerId(), -gylIncome.getAmount());				//供应链对账中钱包减去之前增加的收入
				addGysIncomeWtAmount(gysIncome.getOwnerId(), -gysIncome.getAmount());				//供应商对账中钱包减去之前增加的收入
			}
		}
		catch(ServiceException e){
			log.error(e);
			throw e;
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}
		
	}
	
	
	/**
	 * 
	 * 订单分润规则：
	 * 
	 * 超市收入	= 订单金额 * 分成系数0.9， 即 胖胖生活抽成 1成后的订单金额，						（从胖胖生活app端 收）
	 * 超市支出  	= 超市从仓储中心进货的货款（即云仓储溢价后的出货价）							（支付给云仓储管理公司）
	 * 
	 * 便利店管理公司收入 = 订单金额 * 分成系数0.9， 即 胖胖生活抽成 1成后的订单金额，						（从胖胖生活app端 收）
	 * 便利店管理公司支出 = 便利店从仓储中心进货的货款（即云仓储溢价后的价格）	+ 应付给便利店的分润							（支付给便利店 和 云仓储管理公司）
	 * 
	 * 便利店收入 = 订单金额 * 便利店分润率。 		注意：这里是直接用订单金额 * 便利店分润系数，
	 *  	对便利店而言分润依据是根据 订单来的， 便利店才不管 胖胖生活与便利店管理公司之间抽成多少。			（向便利店管理公司收）
	 *  
	 * 云仓储收入 = 便利店从仓储中心进货的货款（即云仓储溢价后的价格）
	 * 云仓储支出 = 仓储中心从供应链进货应付给供应链的货款（即供应链溢价后的出货价） + 便利店从仓储中心进货的货款（即云仓储溢价后的价格） * 仓储中心分润系数		（支付给供应链 和 仓储中心）
	 * 		
	 * 仓储中心收入 = 便利店从仓储中心进货的货款（即云仓储溢价后的出货价） * 仓储中心分润系数 
	 * 				+ 超市从仓储中心进货的货款（即云仓储溢价后的出货价） * 仓储中心分润系数	（向云仓储收）
	 * 		仓储中心分润的依据就是出货价，根据供给 便利店 和超市的供货价分润。
	 * 
	 * 供应链收入 = 供应链供给仓储中心的供货价，即溢价后价格。	（向云仓储收）
	 * 供应链支出 = 供应链从供应商进货的价格。				（支付给供应商）
	 * 
	 * 供应商收入 = 供货给供应链的批发价。		（向供应链收）
	 * 
	 * 
	 * 
	 * @param order
	 * @throws ServiceException 
	 */
	@Override
	public void addDistributions(WtOrder order) throws ServiceException {
		if(order == null){
			throw new ServiceException("参数order is null");
		}
		//校验参数，检验不通过则抛出异常
		BeanValidator.validate(order);
		
		Integer orderType = order.getOrderType();
		if(WtOrder.SUPERMARKET_ORDER == orderType){
			distributionForCsOrder(order);
		}else if(WtOrder.SHOP_ORDER == orderType ){
			distributionForStoreOrder(order);
		}else{
			log.error("订单类型未知，orderType=" + orderType);
			throw new ServiceException("订单类型未知，orderType=" + orderType);
		}
	}
	
	
	/**
	 * 超市订单分润
	 * 
	 * 分润规则：
	 * 
	 * 超市收入	= 订单金额 * 分成系数0.9， 即 胖胖生活抽成 1成后的订单金额，						（从胖胖生活app端 收）
	 * 
	 * 云仓储收入 = 便利店从仓储中心进货的货款（即云仓储溢价后的价格）
	 * 		
	 * 仓储中心收入 = 超市从仓储中心进货的货款（即云仓储溢价后的出货价） * 仓储中心分润系数	（向云仓储收）
	 * 		仓储中心分润的依据就是出货价，根据供给超市的供货价分润。
	 * 
	 * 供应链收入 = 供应链供给仓储中心的供货价，从供应商拿的货进行溢价后的价格。	（向云仓储收）
	 * 
	 * 供应商收入 = 供货给供应链的批发价。		（向供应链收）
	 * 
	 * @param order
	 * @throws ServiceException 
	 */
	private void distributionForCsOrder(WtOrder order) throws ServiceException {
		try{
			long now = System.currentTimeMillis();
			Long storageId = order.getStorageId();
			Long orderId = order.getOrderId();
			Long supermarketId = order.getShopId();		//超市ID
			Assert.notNull(orderId, "超市订单ID为空");
			Assert.notNull(storageId, "超市订单仓储中心ID为空");
			
			List<WtOrderDetail> orderDetails = order.getOrderDetails();
			Assert.notEmpty(orderDetails, "超市订单明细为空");
			
			
			//重复请求数据校验
			WtCsIncome dupCheckQuery = new WtCsIncome();
			dupCheckQuery.setOrderId(orderId);
			Long incomeCount = csIncomeService.findCount(dupCheckQuery);
			if(incomeCount > 0){
				throw new ServiceException("重复请求，此超市订单已经分润过了, orderId=" + orderId);
			}
			
			//步骤一：超市信息
			SupermarketInfo supermarketInfo = null;
			if(supermarketId == null){
				List<SupermarketInfo> smList =supermarketInfoService.findList(new SupermarketInfo());
				if(smList == null || smList.size() > 1){
					throw new ServiceException("超市订单未提供超市Id，并且数据库上的超市没有或不止一个");
				}else{
//					只查到一条记录则正常；
					supermarketInfo = smList.get(0);
				}
			}else{
				supermarketInfo = supermarketInfoService.findVo(supermarketId, null);
			}
			Assert.notNull(supermarketInfo, "超市信息为空，supermarketId=" + supermarketId);
			Assert.notNull(supermarketId, "超市Id为空，supermarketId=" + supermarketId);
			
			
			//步骤二：云仓储管理公司信息
			CsYccInfo csYccInfo = null;
			List<CsYccInfo> yccList =csYccInfoService.findList(new CsYccInfo());
			if(yccList == null || yccList.size() > 1){
				throw new ServiceException("数据库上的云仓储管理公司没有或个数不止一个");
			}else{
//				只查到一条记录则正常；
				csYccInfo = yccList.get(0);
			}
			
			
			//步骤三：仓储中心信息
			CsCczxInfo cczxInfo = cczxInfoService.findVo(storageId, null);
			Assert.notNull(cczxInfo, "未查到仓储中心storageId=" + storageId);

			
			//步骤四：供应链信息
			CsGylInfo csGylInfo = null;
			List<CsGylInfo> gylList =csGylInfoService.findList(new CsGylInfo());
			if(gylList == null || gylList.size() > 1){
				throw new ServiceException("数据库上的供应链没有或个数不止一个");
			}else{
//				只查到一条记录则正常；
				csGylInfo = gylList.get(0);
			}
			
			//步骤五：各层级组织的结算周期
			int csSettlement = supermarketInfo.getEttlementCycle();
			int	yccSettlement = csYccInfo.getEttlementCycle();
			int cczxSettlement = cczxInfo.getEttlementCycle();
			int gylSettlement = csGylInfo.getEttlementCycle();

			//步骤六： 云仓储给仓储中心的分润率
			BigDecimal cczxProfitRate=BigDecimal.valueOf(cczxInfo.getFenRunPoint());		//仓储中心分润率

			//判断订单详细SKU对应的溢价分润
			for (WtOrderDetail oItem : orderDetails) {
				Long goodsId = oItem.getGoodsId();
//				Long skuId = oItem.getSku();
				final int totalSku = oItem.getTotal();	//sku件数
				final BigDecimal oItemAmount = BigDecimal.valueOf(oItem.getAmount());		//订单 sku 总金额
				if(oItemAmount.compareTo(BigDecimal.valueOf(oItem.getPrice()*totalSku)) != 0){
					//还是不认为订单提供的有问题吧
					log.error("订单sku 总金额 不等于 sku价格 乘 件数， " + JSONUtils.Object2JSON(oItem));
				}
				
				Long supplierId = oItem.getSupplierId();
				//步骤七：供应供应商信息, 先根据商品ID查询商品信息，获取商品供应商ID，在获取供应商信息
				if(supplierId == null || supplierId == 0){
					GdGoods goods = goodsService.findVo(goodsId, null);
					Assert.notNull(goods, "未查到商品信息，goodsId=" + goodsId);
					supplierId = goods.getSupplierId();
				}
				CsGysInfo gysInfo = csGysInfoService.findVo(supplierId, null);
				//供应商的结算周期
				int gysSettlement = gysInfo.getEttlementCycle();
				
//				//步骤八：商品各层溢价率
//				GdGoodsSkuRate goodsSkuRate = gdSkuRateService.getGoodsSkuRate(goodsId, skuId);
//				Assert.notNull(goodsSkuRate, "未查到SKU:" + skuId +" 的溢价率");
//				Double csSkuRate=goodsSkuRate.getPpcsRate();			//超市溢价率
//				Double yccSkuRate=goodsSkuRate.getYccglRate();			//云仓储管理公司溢价率
//				Double gylSkuRate=goodsSkuRate.getGylRate();			//供应链溢价率
				
//				现在各层中间价格直接从订单传过来了
				
				//步骤九：各组织分润，收入金额计算
//				Double csAmount = oItem.getAmount()*0.9;	//胖胖生活抽1成， 剩余0.9
//				Double yccAmount = oItem.getAmount()/(1+csSkuRate);	//云仓储管理中心出货价，
//				Double gylAmount = yccAmount/(1+yccSkuRate);		//供应链的出货价
//				Double gysAmount = gylAmount/(1+gylSkuRate);			//供应商出货价
//				Double cczxAmount = yccAmount*cczxProfitRate;		//仓储中心分润金额 = 云仓储出货价*分润率
				
				//现在 又 改为根据定价反推进出货价，避免责任落到自己头上，若是 商品最终销售价因某些原因 不是计算出来的价格，而最后一层的溢价率又没变，
				//那各层分到到收入也不是预定的金额~~~ 虽然不是很合理，我只是按要求这么做, 责任不在这边
				
//				BigDecimal csAmount  = BigDecimal.valueOf(oItem.getAmount()).multiply(WalletConfig.CsProfitRate).setScale(2, RoundingMode.HALF_EVEN);
//				BigDecimal yccAmount = BigDecimal.valueOf(oItem.getYccglPrice()).multiply(new BigDecimal(totalSku));
//				BigDecimal gylAmount = BigDecimal.valueOf(oItem.getGylPrice()).multiply(new BigDecimal(totalSku)).setScale(2, RoundingMode.HALF_EVEN);
//				BigDecimal gysAmount = BigDecimal.valueOf(oItem.getGysPrice()).multiply(new BigDecimal(totalSku)).setScale(2, RoundingMode.HALF_EVEN);
//				BigDecimal cczxAmount = yccAmount.multiply(cczxProfitRate).setScale(2, RoundingMode.HALF_EVEN);		//仓储中心分润金额 = 云仓储出货价*分润率
//				
//				yccAmount = yccAmount.setScale(2, RoundingMode.HALF_EVEN);		//因为之前 yccAmount 作为了中间变量参与计算，所以之前未进行舍入
				
				
//				步骤八：商品各层溢价率
				BigDecimal csSkuRate=BigDecimal.valueOf(1+oItem.getCsPremiumRate());				//超市溢价率
				BigDecimal yccSkuRate=BigDecimal.valueOf(1+oItem.getYccglPremiumRate());			//云仓储管理公司溢价率
				BigDecimal gylSkuRate=BigDecimal.valueOf(1+oItem.getYccglPremiumRate());			//供应链溢价率
				
				//步骤九：各组织分润，收入金额计算
				BigDecimal csAmount  = oItemAmount.multiply(WalletConfig.CsProfitRate).setScale(2, RoundingMode.HALF_DOWN);	//胖胖生活抽1成， 剩余0.9
				BigDecimal yccAmount = oItemAmount.divide(csSkuRate, 5, RoundingMode.HALF_DOWN);	//云仓储管理中心出货价
				BigDecimal gylAmount = yccAmount.divide(yccSkuRate, 5, RoundingMode.HALF_DOWN);		//供应链的出货价
				BigDecimal gysAmount = gylAmount.divide(gylSkuRate, 2, RoundingMode.HALF_DOWN);		//供应商出货价
				BigDecimal cczxAmount = yccAmount.multiply(cczxProfitRate).setScale(2, RoundingMode.HALF_DOWN);		//仓储中心分润金额 = 云仓储出货价*分润率
				
				yccAmount = yccAmount.setScale(2, RoundingMode.HALF_DOWN);		//因为之前 yccAmount 作为了中间变量参与计算，所以之前精度更高
				gylAmount = gylAmount.setScale(2, RoundingMode.HALF_DOWN);		//因为之前 gylAmount 作为了中间变量参与计算，所以之前精度更高
				
				if( gysAmount.compareTo(BigDecimal.valueOf(oItem.getSupplierPrice()*totalSku)) != 0)		//计算出来的供应商出货价 和 供应商商品真实出货价 比较。
				{
					log.error("超市分润，计算反推出来的供应商sku出货价格跟供应商商品sku真实出货价格不同，价格相差" + gysAmount.divide(BigDecimal.valueOf(totalSku)).subtract(BigDecimal.valueOf(oItem.getSupplierPrice()) ) 
							+"元, gysAmount=" + gysAmount + ", totalSku=" + totalSku + ", 原supplierPrice=" + oItem.getSupplierPrice());
				}
				log.info(new StringBuffer(50).append("{orderId=").append(orderId).append(",oderItemId=").append(oItem.getOrderItemId())
						.append(", totalSKu=").append(totalSku).append(", price=").append(oItem.getPrice())
						.append(", amount=").append(oItemAmount).append(", csAmount=").append(csAmount)
						.append(", yccAmount=").append(yccAmount).append(", cczxAmount=").append(cczxAmount)
						.append(", gylAmount").append(gylAmount).append(", gysAmount").append(gysAmount) );
				
				
				//步骤十： 收入流水插入
				addCsIncome(oItem, csAmount.doubleValue(), orderId, supermarketId, now, csSettlement);		//超市收入流水
				addYccCsIncome(oItem, yccAmount.doubleValue(), orderId, csYccInfo.getDmId(), now, yccSettlement);	//云仓储从超市获得的收入流水
				addCczxIncome(oItem, cczxAmount.doubleValue(), orderId, storageId, now, cczxSettlement);			//仓储中心收入流水
				addGylIncome(oItem, gylAmount.doubleValue(), orderId, csGylInfo.getDmId(), now, gylSettlement);		//供应链的收入流水
				addGysIncome(oItem, gysAmount.doubleValue(), orderId, supplierId, now, gysSettlement);				//供应商的收入流水
				
				//步骤十一：对账中钱包金额增加
				addCsIncomeWtAmount(supermarketId, csAmount.doubleValue());				//超市对账中钱包增加收入
				addYccCsIncomeWtAmount(csYccInfo.getDmId(), yccAmount.doubleValue());		//云仓储从超市获得的对账中收入钱包增加收入
				addCczxIncomeWtAmount(storageId, cczxAmount.doubleValue());				//仓储中心分润对账中钱包增加收入
				addGylIncomeWtAmount(csGylInfo.getDmId(), gylAmount.doubleValue());		//供应链对账中钱包增加收入
				addGysIncomeWtAmount(supplierId, gysAmount.doubleValue());				//供应商对账中钱包增加收入
			}
		}
		catch(ServiceException e){
			log.error(e);
			throw e;
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}
		
	}
	
	
	/**
	 * 为便利店订单分润
	 * 
	 * 分润规则：
	 * 
	 * 便利店管理公司收入 = 订单金额 * 分成系数0.9， 即 胖胖生活抽成 1成后的订单金额，						（从胖胖生活app端 收）
	 * 
	 * 便利店收入 = 订单金额 * 便利店分润率。 		注意：这里是直接用订单金额 * 便利店分润系数，
	 *  	对便利店而言分润依据是根据 订单来的， 便利店才不管 胖胖生活与便利店管理公司之间抽成多少。			（向便利店管理公司收）
	 *  
	 * 云仓储收入 = 便利店从仓储中心进货的货款（即云仓储溢价后的价格）
	 * 		
	 * 仓储中心收入 = 便利店从仓储中心进货的货款（即云仓储溢价后的出货价） * 仓储中心分润系数 		（向云仓储收）
	 * 		仓储中心分润的依据就是出货价，根据供给 便利店 的供货价分润。
	 * 
	 * 供应链收入 = 供应链供给仓储中心的供货价，从供应商拿的货进行溢价后的价格。	（向云仓储收）
	 * 
	 * 供应商收入 = 供货给供应链的批发价。		（向供应链收）
	 * 
	 * @param order
	 * @throws ServiceException 
	 */
	
	private void distributionForStoreOrder(WtOrder order) throws ServiceException {
		try{
			long now = System.currentTimeMillis();
			Long storageId = order.getStorageId();
			Long orderId = order.getOrderId();
			Long shopId = order.getShopId();		//便利店ID
			Assert.notNull(orderId, "便利店订单ID为空");
			Assert.notNull(storageId, "便利店订单仓储中心ID为空");
			
			List<WtOrderDetail> orderDetails = order.getOrderDetails();
			Assert.notEmpty(orderDetails, "便利店订单明细为空");
			
			//重复请求数据校验
			WtBldglIncome dupCheckQuery = new WtBldglIncome();
			dupCheckQuery.setOrderId(orderId);
			Long incomeCount = bldglIncomeService.findCount(dupCheckQuery);
			if(incomeCount > 0){
				throw new ServiceException("重复请求，此便利店订单已经分润过了, orderId=" + orderId);
			}
			
			//步骤一：便利店信息
			ShopInfo shopInfo = null;
			if(shopId == null){
//				由调用方传递参数过来 或 通过sku 获得 店铺ID----》待完善
				throw new ServiceException("便利店Id为空");
			}
			shopInfo = shopInfoService.findVo(shopId, null);
			Assert.notNull(shopInfo, "便利店Id为空，shopId=" + shopId);
			
			
			//步骤二：仓储中心信息
			if(storageId == null){
				storageId = shopInfo.getCczxId();
			}
			CsCczxInfo cczxInfo = cczxInfoService.findVo(storageId, null);
			Assert.notNull(cczxInfo, "未查到仓储中心storageId=" + storageId);
			
			
//			步骤三： 便利店管理公司信息
			Long shopManageId = shopInfo.getShopManageId();
			ShopManage shopManageInfo= shopManageService.findVo(shopManageId, null);
			Assert.notNull(shopManageInfo, "未查询到便利店管理公司 ");
			
			
			//步骤四：云仓储管理公司信息
			CsYccInfo csYccInfo = null;
			List<CsYccInfo> yccList =csYccInfoService.findList(new CsYccInfo());
			if(yccList == null || yccList.size() > 1){
				throw new ServiceException("数据库上的云仓储管理公司没有或个数不止一个");
			}else{
//				只查到一条记录则正常；
				csYccInfo = yccList.get(0);
			}
			
			//步骤五：供应链信息
			CsGylInfo csGylInfo = null;
			List<CsGylInfo> gylList =csGylInfoService.findList(new CsGylInfo());
			if(gylList == null || gylList.size() > 1){
				throw new ServiceException("数据库上的供应链没有或个数不止一个");
			}else{
//				只查到一条记录则正常；
				csGylInfo = gylList.get(0);
			}
			
			//步骤六：各层级组织的结算周期
			int bldglSettlementCycle = shopManageInfo.getEttlementCycle();
			int bldSettlementCycle = shopInfo.getEttlementCycle();
			int	yccSettlementCycle = csYccInfo.getEttlementCycle();
			int cczxSettlementCycle = cczxInfo.getEttlementCycle();
			int gylSettlementCycle = csGylInfo.getEttlementCycle();

			//步骤七： 云仓储给仓储中心的分润率
			BigDecimal cczxProfitRate= BigDecimal.valueOf(cczxInfo.getFenRunPoint());		//仓储中心分润率
			
			
			//步骤八： 便利店管理公司给便利店的分润率
			BigDecimal bldProfitRate=BigDecimal.valueOf(shopInfo.getSplitPoint());		//便利店的分润率

			
			//判断订单详细SKU对应的溢价分润
			for (WtOrderDetail oItem : orderDetails) {
				Long goodsId = oItem.getGoodsId();
//				Long skuId = oItem.getSku();
				int totalSku = oItem.getTotal();	//sku件数
				final BigDecimal oItemAmount = BigDecimal.valueOf(oItem.getAmount());		//订单 sku 总金额
				if(oItemAmount.compareTo(BigDecimal.valueOf(oItem.getPrice()*totalSku)) != 0){
					//还是不认为订单提供的有问题吧
					log.error("订单sku 总金额 不等于 sku价格 乘 件数， " + JSONUtils.Object2JSON(oItem));
				}
				Long supplierId = oItem.getSupplierId();
				//步骤九：供应供应商信息, 先根据商品ID查询商品信息，获取商品供应商ID，在获取供应商信息
				if(supplierId == null || supplierId == 0){
					GdGoods goods = goodsService.findVo(goodsId, null);
					Assert.notNull(goods, "未查到商品信息，goodsId=" + goodsId);
					supplierId = goods.getSupplierId();
				}
				CsGysInfo gysInfo = csGysInfoService.findVo(supplierId, null);
				//供应商的结算周期
				int gysSettlement = gysInfo.getEttlementCycle();
				
				//步骤十：商品各层溢价率
//				GdGoodsSkuRate goodsSkuRate = gdSkuRateService.getGoodsSkuRate(goodsId, skuId);
//				Assert.notNull(goodsSkuRate, "未查到SKU:" + skuId +" 的溢价率");
//				Double bldglSkuRate=goodsSkuRate.getBldglRate();		//便利店管理公司溢价率
//				Double yccSkuRate=goodsSkuRate.getYccglRate();			//云仓储管理公司溢价率
//				Double gylSkuRate=goodsSkuRate.getGylRate();			//供应链溢价率
				
				
				//步骤十一：各组织分润，收入金额计算
//				Double bldglAmount = oItem.getAmount()*0.9;				//胖胖生活抽1成， 剩余0.9
//				Double yccAmount = oItem.getAmount()/(1+bldglSkuRate);	//云仓储管理中心出货价，
//				Double gylAmount = yccAmount/(1+yccSkuRate);			//供应链的出货价
//				Double gysAmount = gylAmount/(1+gylSkuRate);			//供应商出货价
//				Double cczxAmount = yccAmount*cczxProfitRate;			//仓储中心分润金额 = 云仓储出货价*分润率
//				Double bldAmount = oItem.getAmount()*bldProfitRate;		//便利店分润金额 = 订单金额*便利店分润率
				

//				BigDecimal bldglAmount  = BigDecimal.valueOf(oItem.getAmount()).multiply(WalletConfig.BldglProfitRate).setScale(2, RoundingMode.HALF_EVEN);
//				BigDecimal yccAmount = BigDecimal.valueOf(oItem.getYccglPrice()).multiply(new BigDecimal(totalSku));
//				BigDecimal gylAmount = BigDecimal.valueOf(oItem.getGylPrice()).multiply(new BigDecimal(totalSku)).setScale(2, RoundingMode.HALF_EVEN);
//				BigDecimal gysAmount = BigDecimal.valueOf(oItem.getGysPrice()).multiply(new BigDecimal(totalSku)).setScale(2, RoundingMode.HALF_EVEN);
//				BigDecimal cczxAmount = yccAmount.multiply(cczxProfitRate).setScale(2, RoundingMode.HALF_EVEN);			//仓储中心分润金额 = 云仓储出货价*分润率
//				BigDecimal bldAmount = BigDecimal.valueOf(oItem.getAmount()).multiply(bldProfitRate).setScale(2, RoundingMode.HALF_EVEN);		//便利店分润金额 = 订单金额*便利店分润率
//				
//				yccAmount = yccAmount.setScale(2, RoundingMode.HALF_EVEN);		//因为之前 yccAmount 作为了中间变量参与计算，所以之前未进行舍入
				
				
				//步骤十：商品各层溢价率
				BigDecimal bldglSkuRate=BigDecimal.valueOf(1+oItem.getBldglPremiumRate());		//便利店管理公司溢价率
				BigDecimal yccSkuRate=BigDecimal.valueOf(1+oItem.getYccglPremiumRate());		//云仓储管理公司溢价率
				BigDecimal gylSkuRate=BigDecimal.valueOf(1+oItem.getYccglPremiumRate());		//供应链溢价率
				
				
				//步骤十一：各组织分润，收入金额计算
				BigDecimal bldglAmount  = oItemAmount.multiply(WalletConfig.BldglProfitRate).setScale(2, RoundingMode.HALF_DOWN);	//胖胖生活抽1成， 剩余0.9
				BigDecimal yccAmount = oItemAmount.divide(bldglSkuRate, 5, RoundingMode.HALF_DOWN);	//云仓储管理中心出货价
				BigDecimal gylAmount = yccAmount.divide(yccSkuRate, 5, RoundingMode.HALF_DOWN);		//供应链的出货价
				BigDecimal gysAmount = gylAmount.divide(gylSkuRate, 2, RoundingMode.HALF_DOWN);		//供应商出货价
				BigDecimal cczxAmount = yccAmount.multiply(cczxProfitRate).setScale(2, RoundingMode.HALF_DOWN);		//仓储中心分润金额 = 云仓储出货价*分润率
				BigDecimal bldAmount = oItemAmount.multiply(bldProfitRate).setScale(2, RoundingMode.HALF_DOWN);		//便利店分润金额 = 订单金额*便利店分润率
				
				yccAmount = yccAmount.setScale(2, RoundingMode.HALF_DOWN);		//因为之前 yccAmount 作为了中间变量参与计算，所以之前精度更高
				gylAmount = gylAmount.setScale(2, RoundingMode.HALF_DOWN);		//因为之前 gylAmount 作为了中间变量参与计算，所以之前精度更高
				
				if( gysAmount.compareTo(BigDecimal.valueOf(oItem.getSupplierPrice()*totalSku)) != 0)		//计算出来的供应商出货价 和 供应商商品真实出货价 比较。
				{
					log.error("便利店分润，计算反推出来的供应商sku出货价格跟供应商商品sku真实出货价格不同，价格相差" + gysAmount.divide(BigDecimal.valueOf(totalSku)).subtract(BigDecimal.valueOf(oItem.getSupplierPrice()) ) 
							+"元, gysAmount=" + gysAmount + ", totalSku=" + totalSku + ", 原supplierPrice=" + oItem.getSupplierPrice());
				}
				log.info(new StringBuffer(50).append("{orderId=").append(orderId).append(",oderItemId=").append(oItem.getOrderItemId())
						.append(", totalSKu=").append(totalSku).append(", price=").append(oItem.getPrice()).append(", amount=").append(oItemAmount)
						.append(", bldglAmount=").append(bldglAmount).append(", bldAmount=").append(bldAmount)
						.append(", yccAmount=").append(yccAmount).append(", cczxAmount=").append(cczxAmount)
						.append(", gylAmount").append(gylAmount).append(", gysAmount").append(gysAmount) );
				
				
				//步骤十二： 收入流水插入
				addBldglIncome(oItem, bldglAmount.doubleValue(), orderId, shopId, now, bldglSettlementCycle);				//便利店管理公司收入流水
				addBldIncome(oItem, bldAmount.doubleValue(), orderId, shopId, now, bldSettlementCycle);					//便利店从便利店管理公司获得的分润收入流水
				addYccBldglIncome(oItem, yccAmount.doubleValue(), orderId, csYccInfo.getDmId(), now, yccSettlementCycle);	//云仓储从超市获得的收入流水
				addCczxIncome(oItem, cczxAmount.doubleValue(), orderId, storageId, now, cczxSettlementCycle);				//仓储中心收入流水
				addGylIncome(oItem, gylAmount.doubleValue(), orderId, csGylInfo.getDmId(), now, gylSettlementCycle);		//供应链的收入流水
				addGysIncome(oItem, gysAmount.doubleValue(), orderId, supplierId, now, gysSettlement);					//供应商的收入流水
				
				//步骤十三：对账中钱包金额增加
				addBldglIncomeWtAmount(shopManageId, bldglAmount.doubleValue());			//便利店管理公司对账中钱包增加收入
				addBldIncomeWtAmount(shopId, bldAmount.doubleValue()); 					//便利店从便利店管理公司获得的收入对账中钱包增加收入
				addYccBldglIncomeWtAmount(csYccInfo.getDmId(), yccAmount.doubleValue());	//云仓储从便利店管理公司获得的收入对账中钱包增加收入
				addCczxIncomeWtAmount(storageId, cczxAmount.doubleValue());				//仓储中心分润对账中钱包增加收入
				addGylIncomeWtAmount(csGylInfo.getDmId(), gylAmount.doubleValue());		//供应链对账中钱包增加收入
				addGysIncomeWtAmount(supplierId, gysAmount.doubleValue());				//供应商对账中钱包增加收入
			}
		}
		catch(ServiceException e){
			log.error(e);
			throw e;
		}
		catch(Exception e){
			log.error(e);
			throw new ServiceException(e);
		}
	}

	/**
	 * 计算预计解冻时间
	 * 
	 * @param postTime 	入账时间
	 * @param settlentCycle	结算周期，现在结算周期的单位是 天
	 * @return
	 */
	private long calReleaseTime(long postTime, long settlentCycle){
		return postTime + settlentCycle*86400000;	//86400000=24*3600*1000
	}
	
	/**
	 * 超市收入流水，即超市待收货款金额
	 * 
	 * @param wtOrderDetail
	 * @param csAmout
	 * @param orderId
	 * @param supermarketId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addCsIncome(final WtOrderDetail wtOrderDetail, Double csAmount,Long orderId, Long supermarketId,long createTime, long settlementCycle) throws ServiceException {
		WtCsIncome wtIncome=new WtCsIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(csAmount);			//收入流水，待收金额
		wtIncome.setOwnerId(supermarketId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		csIncomeService.addVo(wtIncome);
	}

	

	
	/**
	 * 便利店管理公司收入流水
	 * 
	 * @param wtOrderDetail
	 * @param amount
	 * @param orderId
	 * @param supermarketId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addBldglIncome(final WtOrderDetail wtOrderDetail, Double amount,Long orderId, Long bldglId,long createTime, long settlementCycle) throws ServiceException {
		WtBldglIncome wtIncome=new WtBldglIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(amount);			//收入流水，待收金额
		wtIncome.setOwnerId(bldglId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		bldglIncomeService.addVo(wtIncome);
	}
	
	
	/**
	 * 便利店收入流水
	 * 
	 * @param wtOrderDetail
	 * @param amount
	 * @param orderId
	 * @param supermarketId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addBldIncome(final WtOrderDetail wtOrderDetail, Double amount,Long orderId, Long bldId,long createTime, long settlementCycle) throws ServiceException {
		WtBldIncome wtIncome=new WtBldIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(amount);			//收入流水，待收金额
		wtIncome.setOwnerId(bldId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		bldIncomeService.addVo(wtIncome);
	}
	
	
	/**
	 * 云仓储从超市获取的收入流水
	 * 
	 * @param wtOrderDetail
	 * @param amount
	 * @param orderId
	 * @param yccId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addYccCsIncome(final WtOrderDetail wtOrderDetail, Double amount,Long orderId, Long yccId,long createTime, long settlementCycle) throws ServiceException {
		WtYccCsIncome wtIncome=new WtYccCsIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(amount);			//收入流水，待收金额
		wtIncome.setOwnerId(yccId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		yccCsIncomeService.addVo(wtIncome);
	}


	/**
	 * 云仓储从便利店管理公司获取的收入流水
	 * 
	 * @param wtOrderDetail
	 * @param amount
	 * @param orderId
	 * @param yccId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addYccBldglIncome(final WtOrderDetail wtOrderDetail, Double amount,Long orderId, Long yccId,long createTime, long settlementCycle) throws ServiceException {
		WtYccBldglIncome wtIncome=new WtYccBldglIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(amount);			//收入流水，待收金额
		wtIncome.setOwnerId(yccId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		yccBldglIncomeService.addVo(wtIncome);
	}
	
	
	
	/**
	 * 仓储中心的收入流水 
	 * 
	 * @param wtOrderDetail
	 * @param amount
	 * @param orderId
	 * @param yccId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addCczxIncome(final WtOrderDetail wtOrderDetail, Double amount,Long orderId, Long cczxId,long createTime, long settlementCycle) throws ServiceException {
		WtCczxIncome wtIncome=new WtCczxIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(amount);			//收入流水，待收金额
		wtIncome.setOwnerId(cczxId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		cczxIncomeService.addVo(wtIncome);
	}
	
	/**
	 * 供应链的收入流水 
	 * 
	 * @param wtOrderDetail
	 * @param amount
	 * @param orderId
	 * @param yccId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addGylIncome(final WtOrderDetail wtOrderDetail, Double amount,Long orderId, Long gylId,long createTime, long settlementCycle) throws ServiceException {
		WtGylIncome wtIncome=new WtGylIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(amount);			//收入流水，待收金额
		wtIncome.setOwnerId(gylId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		gylIncomeService.addVo(wtIncome);
	}
	
	/**
	 * 供应链的收入流水 
	 * 
	 * @param wtOrderDetail
	 * @param amount
	 * @param orderId
	 * @param yccId
	 * @param createTime
	 * @param settlementCycle
	 * @throws ServiceException 
	 */
	private void addGysIncome(final WtOrderDetail wtOrderDetail, Double amount,Long orderId, Long gylId,long createTime, long settlementCycle) throws ServiceException {
		WtGysIncome wtIncome=new WtGysIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setAmount(amount);			//收入流水，待收金额
		wtIncome.setOwnerId(gylId);
		wtIncome.setOrderId(orderId);
		wtIncome.setOrderItemId(wtOrderDetail.getOrderItemId());
		wtIncome.setSkuId(wtOrderDetail.getSku());
		wtIncome.setSkuDescription(wtOrderDetail.getSkuDescription() );
		wtIncome.setGoodsId(wtOrderDetail.getGoodsId());
		wtIncome.setGoodsName(wtOrderDetail.getGoodsName());
		wtIncome.setPrice(wtOrderDetail.getPrice());
		wtIncome.setTotal(wtOrderDetail.getTotal());
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(createTime);
		long releaseTime = calReleaseTime(createTime, settlementCycle);
		wtIncome.setPreReleaseTime(releaseTime );
		wtIncome.setReleaseTime(releaseTime );
		gysIncomeService.addVo(wtIncome);
	}
	
	
	/**
	 * 给超市对账中钱包增加收入
	 * 
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addCsIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.SuperMarket.value());
		wtQuery.setPayerType(CompanyType.PPSH.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给超市，付款人是胖胖生活，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	/**
	 * 给便利店管理公司对账中钱包增加收入
	 * 
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addBldglIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.StoreManager.value());
		wtQuery.setPayerType(CompanyType.PPSH.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给便利店管理公司，付款人是胖胖生活，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	/**
	 * 给便利店对账中钱包增加收入
	 * 
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addBldIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.Store.value());
		wtQuery.setPayerType(CompanyType.StoreManager.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给便利店，付款人是便利店管理公司，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	/**
	 * 给云仓储从超市获得收入的对账中钱包增加收入
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addYccCsIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.CloudStorage.value());
		wtQuery.setPayerType(CompanyType.SuperMarket.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给云仓储管理公司，付款人是便利店管理公司，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	/**
	 * 给云仓储从便利店管理公司获得收入的对账中钱包增加收入
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addYccBldglIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.CloudStorage.value());
		wtQuery.setPayerType(CompanyType.StoreManager.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给云仓储管理公司，付款人是便利店管理公司，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	/**
	 * 给仓储中心的对账中钱包增加收入
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addCczxIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.StorageCenter.value());
		wtQuery.setPayerType(CompanyType.CloudStorage.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给仓储中心，付款人是云仓储管理公司，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	
	/**
	 * 给供应链的对账中钱包增加收入
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addGylIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.SupplyChain.value());
		wtQuery.setPayerType(CompanyType.CloudStorage.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给供应链，付款人是云仓储管理公司，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	
	/**
	 * 给供应商的对账中钱包增加收入
	 * @param ownerId
	 * @param amount
	 * @throws ServiceException
	 */
	private void addGysIncomeWtAmount(Long ownerId, Double amount) throws ServiceException{
		Long incomeWtId = null;
		
		WtWallet wtQuery = new WtWallet();
		wtQuery.setOwnerId(ownerId);
		wtQuery.setOwnerType(CompanyType.Supplier.value());
		wtQuery.setPayerType(CompanyType.SupplyChain.value());
		wtQuery.setItemId(WalletItemEnum.INCOME.getValue());
		WtWallet incomeWt = this.getWallet(wtQuery);
		if(incomeWt != null){
			incomeWtId = incomeWt.getDmId();
		}else{
			throw new ServiceException("未查询到相应钱包");
		}
		
		WtWallet incomeWalletMod = new WtWallet();
		incomeWalletMod.setDmId(incomeWtId);
		incomeWalletMod.setItemAmount(amount);
		incomeWalletMod.setUpdateTime(System.currentTimeMillis());
		log.info("分润给供应商，付款人是供应链，收入增加，对账中钱包增加：" + JSONUtils.Object2JSON(incomeWalletMod));
		this.modWalletAmountIncr(incomeWalletMod);
	}
	
	
	/**
	 * 检查对应付款人的已付钱包
	 * 
	 * @param payerId   付款人Id
	 * @param ownerType 属主类型
	 * @param payerType 付款人类型
	 * @return 我的钱包
	 * @throws ServiceException
	 */
	public WtWallet getPaidByTissue(Long payerId, int ownerType, int payerType,int itemId) {
		WtWallet paidWtQuery = new WtWallet();
		paidWtQuery.setOwnerId(payerId);
		paidWtQuery.setOwnerType(ownerType);
		paidWtQuery.setItemId(itemId);
		paidWtQuery.setPayerType(payerType);
		try {
			paidWtQuery = getWallet(paidWtQuery);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return paidWtQuery;
	}

	@Override
	public void updateOwnerName(Map<String, Object> map) throws ServiceException {
		try {
			this.dao.updateOwnerName(map);
		} catch (Exception e) {
			log.error("修改钱包名称出现系统错误！", e);
			throw new ServiceException(e.getMessage());
		}
	}
	
}