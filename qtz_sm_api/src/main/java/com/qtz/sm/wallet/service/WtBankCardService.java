package com.qtz.sm.wallet.service;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.wallet.vo.WtBankCard;

/**
 * <p>
 * Title:WtBankCardService
 * </p>
 * <p>
 * Description:银行卡信息服务接口类
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
public interface WtBankCardService extends BaseService<com.qtz.sm.wallet.vo.WtBankCard, java.lang.Long> {

	public WtBankCard saveBindingBank(WtBankCard wtBankCard) throws ServiceException;

	public WtBankCard saveBindingBank(String sid, String cardNum, String bankName, String bankBranch,
			String bankAddress, String cardholderName, Integer accountType) throws ServiceException;

	public WtBankCard queryWtBankCardGroupInfo(Long companyId,int companyType) throws ServiceException;
}