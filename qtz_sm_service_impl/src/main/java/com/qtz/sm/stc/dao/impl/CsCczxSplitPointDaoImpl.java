package com.qtz.sm.stc.dao.impl;
import org.springframework.stereotype.Repository;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.qtz.sm.stc.dao.CsCczxSplitPointDao;
import com.qtz.sm.stc.dao.CsYccInfoDao;
import com.qtz.sm.stc.vo.CsCczxSplitPoint;
/**
 * <p>Title:CsCczxSplitPointDaoImpl</p>
 * <p>Description:仓储中心分成表DAO实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-06-14
 */
@Repository("CsCczxSplitPointDaoImpl")
public class CsCczxSplitPointDaoImpl extends MyBaitsDaoImpl<com.qtz.sm.stc.vo.CsCczxSplitPoint,java.lang.Long> implements CsCczxSplitPointDao {
	/**MYBatis命名空间名*/
	private static String preName = CsCczxSplitPointDao.class.getName();
	/** 
	* 【取得】MYBatis命名空间名
	* @return  	MYBatis命名空间名
	*/
	@Override
	protected String getPreName() {
		return preName;
	}
	@Override
	public CsCczxSplitPoint findByCczxIdVo(Long cczxId) throws DaoException {
		try{
			return getMyBaitsTemplate().getSqlSession().getMapper(CsCczxSplitPointDao.class).findByCczxIdVo(cczxId);
		}catch(Exception e){
			throw new DaoException(e);
		}
	}
}