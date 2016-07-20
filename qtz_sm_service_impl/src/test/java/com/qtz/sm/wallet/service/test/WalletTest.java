package com.qtz.sm.wallet.service.test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.qtz.sm.BaseTest;
import org.junit.Test;

import com.mall.core.exception.ServiceException;
import com.qtz.sm.wallet.config.WalletConfig;
import com.qtz.sm.wallet.model.WtOrder;
import com.qtz.sm.wallet.model.WtOrderDetail;
import com.qtz.sm.wallet.service.WtWalletService;

public class WalletTest extends BaseTest {
//	@Autowired
//	private WalletConfig wtConfig;
	
	@Resource
	private WtWalletService walletService;
	
	@Test
	public void testWalletConfig(){
		System.out.println("测试结果：" + WalletConfig.CsProfitRate);
		System.out.println("测试结果：" + WalletConfig.CsProfitRate);
		System.out.println("测试结果：" + WalletConfig.BldglProfitRate);
	}
	
	
	@Test
	public void addBldWallet(){
		try {
			walletService.addBldWallet(1L, "test", 1L);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 分润测试
	 * @throws ServiceException 
	 */
	@Test
	public void addDistributions() throws ServiceException{
//		String orderJson = "{\"orderId\":17,\"orderType\":2,\"shopId\":null,\"storageId\":null,\"orderDetails\":"
//				+ "[{\"dmId\":1,\"sku\":1706283527178240,\"skuDescription\":\"颜色：黑色， 尺寸：L\",\"goodsId\":1706283527047168,\"goodsName\":\"2016夏季新款纯棉_111111111\","
//				+ "\"supplierId\":1707720957773824,\"total\":1,\"gysPrice\":256.00,\"gylPrice\":281.60,\"yccglPrice\":309.76,\"price\":340.74,\"amount\":340.74},{\"dmId\":2,\"sku\":1707212203149312,\"skuDescription\":\"颜色：黑色， 尺寸：L\",\"goodsId\":1707212202182656,\"goodsName\":\"裙子11111111111\",\"supplierId\":1707720957773824,\"total\":1,\"gysPrice\":99.00,\"gylPrice\":108.90,\"yccglPrice\":119.79,\"price\":131.77,\"amount\":131.77}]}";
//		WtOrder order = JSONObject.parseObject(orderJson, WtOrder.class);
		WtOrder order = new WtOrder();
		order.setOrderId(17L);
		order.setOrderType(2);
		order.setShopId(1707278742685696L);
		order.setStorageId(1707680781092864L);
		
		List<WtOrderDetail> itemList = new ArrayList<WtOrderDetail>();
		
		WtOrderDetail item1 = new WtOrderDetail();
		item1.setOrderItemId(1L);
		item1.setSku(1706283527178240L);
		item1.setSkuDescription("颜色：黑色， 尺寸：L");
		item1.setGoodsId(1706283527047168L);
		item1.setGoodsName("2016夏季新款纯棉_111111111");
		item1.setSupplierId(1707720957773824L);
		item1.setTotal(1);
		item1.setSupplierPrice(256.00);
		item1.setGylPremiumRate(0.1);
		item1.setYccglPremiumRate(0.1);
		item1.setBldglPremiumRate(0.1);
		item1.setCsPremiumRate(0.1);
		item1.setPrice(340.74);
		item1.setAmount(340.74);
		itemList.add(item1);
		
		order.setOrderDetails(itemList);
		
		walletService.addDistributions(order);
	}
	
	/**
	 * 取消分润测试
	 * @throws ServiceException 
	 */
	@Test
	public void modUnDistributions() throws ServiceException{
		WtOrder order = new WtOrder();
		order.setOrderId(14L);
		order.setOrderType(1);
		List<WtOrderDetail> orderDetails = new ArrayList<WtOrderDetail>();
		
//		WtOrderDetail detail = new WtOrderDetail();
//		detail.setDmId(1L);
//		orderDetails.add(detail );
		
		order.setOrderDetails(orderDetails );
		walletService.modUnDistributions(order);
	}
	
	public static void main(String[] args) {
		BigDecimal rate = new BigDecimal(1.9, new MathContext(5, RoundingMode.HALF_EVEN));
		System.out.println(rate.compareTo(new BigDecimal(1.9+"")));
		System.out.println(rate.equals(new BigDecimal(1.9+"")));
		
		BigDecimal rate2 = new BigDecimal(1.9, new MathContext(2, RoundingMode.HALF_EVEN));
		System.out.println(rate2.compareTo(new BigDecimal(1.9+"")));
		System.out.println(rate2.equals(new BigDecimal(1.9+"")));
		
		BigDecimal rate3 = new BigDecimal(1.9, new MathContext(8, RoundingMode.FLOOR));
		System.out.println(rate3.compareTo(new BigDecimal(1.9)));
		System.out.println(rate3.equals(new BigDecimal(1.9)));
		
		BigDecimal rate4 = new BigDecimal(1.9);
		System.out.println(rate4.compareTo(new BigDecimal(1.9)));
		System.out.println(rate4.equals(new BigDecimal(1.9)));
		
		
		BigDecimal rateT = BigDecimal.valueOf(1.9);
		System.out.println(rateT.compareTo(new BigDecimal(new Double(1.9).toString())));
		
		
		Double d = null;
		BigDecimal rateF = BigDecimal.valueOf(d);
	}
}
