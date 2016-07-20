package com.qtz.sm.wallet.service.test;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.wallet.service.WtBankCardService;
import com.qtz.sm.wallet.vo.WtBankCard;

public class BankCardTest extends BaseTest {
	@Autowired
	private WtBankCardService wtBankCardService;
	@Test
	public void  saveBindingBank(){
		String sid="99999999999999";
		String cardNum="99999999999999"; 
		String bankName="中国农业银行";
		String bankBranch="宝安支行";
		String bankAddress="宝安大道119号";
		String cardholderName="徐工";
		Integer accountType=1;
		//WtBankCard wtBankCard
		try {
			WtBankCard wtBankCard=wtBankCardService.saveBindingBank(sid,cardNum,bankName,bankBranch,bankAddress ,cardholderName,accountType);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
