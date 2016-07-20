package com.qtz.sm.wallet.dao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.vo.WtBankCard;
/**
 * <p>Title:WtBankCardDao</p>
 * <p>Description:银行卡信息DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-26
 */
public interface WtBankCardDao extends BizDao<com.qtz.sm.wallet.vo.WtBankCard,java.lang.Long> {
	
	public WtBankCard queryWtBankCardGroupInfo(Long companyId,int companyType) throws DaoException;

}