package com.qtz.sm.wallet.service.test;

import javax.annotation.Resource;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.common.FifteenLongId;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.wallet.service.WtBldglIncomeService;

public class BldglIncomeTest extends BaseTest {
	@Autowired
	private WtBldglIncomeService bldglIncomeService;
	
	@Resource
	private FifteenLongId fifteenLongId;
	
	
	@Test
	public void  modSettlementTime(){
		
		try {
			bldglIncomeService.modSettlementTime(null, 7);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
}
