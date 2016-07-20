package com.qtz.sm.stc.dao;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.stc.vo.CsCczxSplitPoint;
/**
 * <p>Title:CsCczxSplitPointDao</p>
 * <p>Description:仓储中心分成表DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-06-14
 */
public interface CsCczxSplitPointDao extends BizDao<com.qtz.sm.stc.vo.CsCczxSplitPoint,java.lang.Long> {
	
	
	CsCczxSplitPoint findByCczxIdVo(Long cczxId) throws DaoException;
	
}