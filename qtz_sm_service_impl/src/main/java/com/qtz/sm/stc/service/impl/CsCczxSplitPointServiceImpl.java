package com.qtz.sm.stc.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.stc.service.CsCczxSplitPointService;
import com.qtz.sm.stc.vo.CsCczxSplitPoint;
import com.qtz.sm.stc.dao.CsCczxSplitPointDao;
/**
 * <p>Title:CsCczxSplitPointServiceImpl</p>
 * <p>Description:仓储中心分成表服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-06-14
 */
@Service("csCczxSplitPointServiceImpl")
public class CsCczxSplitPointServiceImpl extends BaseServiceImpl<com.qtz.sm.stc.vo.CsCczxSplitPoint,java.lang.Long> implements CsCczxSplitPointService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(CsCczxSplitPointServiceImpl.class);
	/**注入仓储中心分成表DAO接口类*/
	@Resource(name="CsCczxSplitPointDaoImpl")
    private CsCczxSplitPointDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.stc.vo.CsCczxSplitPoint,java.lang.Long> getDao() {
		return dao;
	}
	/** 
	* 【取得】日志对象
	* @return 	日志对象  
	*/
	@Override
	protected LogTool getLog() {
		return log;
	}
	
	public CsCczxSplitPoint findByCczxIdVo(Long cczxId) throws ServiceException{
		 try {
			return dao.findByCczxIdVo(cczxId);
		} catch (DaoException e) {
            throw new ServiceException(e);
        }
	}
}