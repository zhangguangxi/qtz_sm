package com.qtz.sm.wallet.dao;
import java.util.Map;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.wallet.vo.WtWallet;
/**
 * <p>Title:WtWalletDao</p>
 * <p>Description:钱包信息DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 徐运贤 - xuyunxian@126.com
 * @version v1.0 2016-05-13
 */
public interface WtWalletDao extends BizDao<com.qtz.sm.wallet.vo.WtWallet,java.lang.Long> {
	public void delByOwner(Long ownerId) throws DaoException;

	int modWalletAmountIncr(WtWallet wtWallet) throws DaoException;
	
	/**
	 * 修改钱包中的名称
	 * @Description:TODO
	 * @param map
	 * @throws ServiceException
	 * void
	 * @exception:
	 * @author: SunXuan
	 * @time:2016年6月21日 下午4:22:11
	 */
	public void updateOwnerName(Map<String, Object> map) throws DaoException;
	
}