package com.qtz.sm.goods.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsSkuPropertyDao;
import com.qtz.sm.goods.service.GdGoodsSkuPropertyService;
import com.qtz.sm.goods.vo.GdGoodsSkuProperty;
/**
 * <p>Title:GdGoodsSkuPropertyServiceImpl</p>
 * <p>Description:商品SKU属性服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsSkuPropertyServiceImpl")
public class GdGoodsSkuPropertyServiceImpl extends BaseServiceImpl<GdGoodsSkuProperty,java.lang.Long> implements GdGoodsSkuPropertyService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(GdGoodsSkuPropertyServiceImpl.class);
	/**注入商品SKU属性DAO接口类*/
	@Resource(name="gdGoodsSkuPropertyDaoImpl")
    private GdGoodsSkuPropertyDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<GdGoodsSkuProperty,java.lang.Long> getDao() {
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
}