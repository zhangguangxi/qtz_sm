package com.qtz.sm.wallet.dao.impl;
import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtBankCardDao;
import com.qtz.sm.wallet.vo.WtBankCard;
/**
 * <p>Title:WtBankCardDaoImpl</p>
 * <p>Description:银行卡信息DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-04-26
 */
@Repository("wtBankCardDaoImpl")
public class WtBankCardDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtBankCard,java.lang.Long> implements WtBankCardDao {
	/**MYBatis命名空间名*/
	private static String preName = WtBankCardDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	
	@Override
	public WtBankCard queryWtBankCardGroupInfo(Long companyId,int companyType) throws DaoException{
		try {
			WtBankCard wtBankCard=new WtBankCard();
			wtBankCard.setCompanyType(companyType);
			wtBankCard.setCompanyId(companyId);
			return (WtBankCard) getMyBaitsTemplate().findVoBySqlid(getPreName(),"queryCompanyGroupInfo", wtBankCard);
		} catch (Exception e) {
			throw new DaoException(this.getClass().getName() + "queryCompanyGroupInfo调用【报错】了！", e);
		}
	}
}