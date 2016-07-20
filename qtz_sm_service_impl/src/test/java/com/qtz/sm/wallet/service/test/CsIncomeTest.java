package com.qtz.sm.wallet.service.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.Resource;

import com.qtz.sm.BaseTest;
import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.common.FifteenLongId;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.wallet.enums.IncomeStatusEnum;
import com.qtz.sm.wallet.service.WtCsIncomeService;
import com.qtz.sm.wallet.vo.WtCsIncome;

public class CsIncomeTest extends BaseTest {
	@Autowired
	private WtCsIncomeService csIncomeService;
	
	@Resource
	private FifteenLongId fifteenLongId;
	
	public static void main(String[] args) {
		double a = 5.5D;
		double b = 4.5D;
		double c = 1.465D;
		double d = 1.455D;
		BigDecimal decimal = new BigDecimal(c).setScale(2, RoundingMode.HALF_EVEN);			//构造的BigDecimal 不等于 1.465， 不精确 ：1.4650000000000000799360577730112709105014801025390625
		BigDecimal decimal2 = BigDecimal.valueOf(c).setScale(2, RoundingMode.HALF_EVEN);	//这样构造的BigDecimal 才精确
		BigDecimal decimal3 = new BigDecimal(c+"").setScale(2, RoundingMode.HALF_EVEN);		//这样构造的BigDecimal 才精确
		
		
		System.out.println(new BigDecimal(a).setScale(0, RoundingMode.HALF_EVEN).doubleValue());
		System.out.println(new BigDecimal(b).setScale(0, RoundingMode.HALF_EVEN).doubleValue());
		System.out.println(new BigDecimal(c+"").setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		System.out.println(new BigDecimal(d+"").setScale(2, RoundingMode.HALF_EVEN).doubleValue());
	}
	
	
	@Test
	public void  saveCsIncome(){
		WtCsIncome wtIncome=new WtCsIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setOwnerId(1L);
		wtIncome.setOrderId(1L);
		wtIncome.setOrderItemId(1L);
		wtIncome.setSkuId(1L);
		wtIncome.setSkuDescription("Test" );
		wtIncome.setGoodsName("可可可乐" + RandomUtils.nextInt(100));
		wtIncome.setPrice(new BigDecimal(1.455+"").setScale(2, RoundingMode.HALF_EVEN).doubleValue());
		wtIncome.setTotal(3);
		wtIncome.setAmount(new BigDecimal(4.365+"").setScale(2, RoundingMode.HALF_EVEN).doubleValue());			//收入流水，待收金额
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(System.currentTimeMillis());
		wtIncome.setPreReleaseTime(System.currentTimeMillis()+1000*24*3600*7 );
		wtIncome.setReleaseTime(System.currentTimeMillis()+1000*24*3600*7 );
		try {
			WtCsIncome wtCsIncome = csIncomeService.addVo(wtIncome );
			System.out.println("结果:" +JSONObject.toJSONString(wtCsIncome));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void  saveCsIncome2(){
		WtCsIncome wtIncome=new WtCsIncome();
		wtIncome.setDmId(fifteenLongId.nextId());
		wtIncome.setOwnerId(1L);
		wtIncome.setOrderId(1L);
		wtIncome.setOrderItemId(1L);
		wtIncome.setSkuId(1L);
		wtIncome.setSkuDescription("Test" );
		wtIncome.setGoodsName("可可可乐" + RandomUtils.nextInt(100));
		wtIncome.setPrice(1.455);
		wtIncome.setTotal(3);
		wtIncome.setAmount(4.365);			//收入流水，待收金额
		wtIncome.setStat(IncomeStatusEnum.FREEZE.getValue());  //冻结
		wtIncome.setCreateTime(System.currentTimeMillis());
		wtIncome.setPreReleaseTime(System.currentTimeMillis()+1000*24*3600*7 );
		wtIncome.setReleaseTime(System.currentTimeMillis()+1000*24*3600*7 );
		try {
			WtCsIncome wtCsIncome = csIncomeService.addVo(wtIncome );
			System.out.println("结果:" +JSONObject.toJSONString(wtCsIncome));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
}
