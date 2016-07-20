package com.qtz.sm.stc.service;
import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.stc.vo.CsCczxSplitPoint;
/**
 * <p>Title:CsCczxSplitPointService</p>
 * <p>Description:仓储中心分成表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市好实再商贸有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-06-14
 */
public interface CsCczxSplitPointService extends BaseService<com.qtz.sm.stc.vo.CsCczxSplitPoint,java.lang.Long> {
	
	 CsCczxSplitPoint findByCczxIdVo(Long cczxId) throws ServiceException;
	
}