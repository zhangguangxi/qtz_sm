package com.qtz.sm.wallet.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mall.core.common.Global;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.wallet.dao.WtWalletDao;
import com.qtz.sm.wallet.vo.WtWallet;

/**
 * <p>
 * Title:WtWalletDaoImpl
 * </p>
 * <p>
 * Description:钱包信息DAO实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳擎天柱信息技术有限公司
 * </p>
 * 
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-13
 */
@Repository("wtWalletDaoImpl")
public class WtWalletDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.wallet.vo.WtWallet, java.lang.Long>
		implements WtWalletDao {
	/** MYBatis命名空间名 */
	private static String preName = WtWalletDao.class.getName();

	/**
	 * 【取得】MYBatis命名空间名
	 * 
	 * @return MYBatis命名空间名
	 */
	@Override
	protected String getPreName() {
		return preName;
	}

	@Override
	public void delByOwner(Long ownerId) throws DaoException {
		try {
			String sqlidOne = "delByOwner";
			if (null != getPreName() && !getPreName().equals("")) {
				sqlidOne = getPreName() + Global.SPLIT_DIAN + sqlidOne;
			}
			this.getMyBaitsTemplate().getSqlSession().delete(sqlidOne, ownerId);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	
	/**
	 * 增量操作钱包金额
	 */
	@Override
	public int modWalletAmountIncr(WtWallet wtWallet) throws DaoException {
		try {
			return getMyBaitsTemplate().getSqlSession().update(preName+".modWalletAmountIncr", wtWallet);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateOwnerName(Map<String, Object> map) throws DaoException {
		try {
			getMyBaitsTemplate().getSqlSession().getMapper(WtWalletDao.class).updateOwnerName(map);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		
	}
}