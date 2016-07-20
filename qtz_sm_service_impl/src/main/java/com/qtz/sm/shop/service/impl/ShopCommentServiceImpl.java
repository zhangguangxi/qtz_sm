package com.qtz.sm.shop.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.shop.service.ShopCommentService;
import com.qtz.sm.shop.dao.ShopCommentDao;
/**
 * <p>Title:ShopCommentServiceImpl</p>
 * <p>Description:店铺评论信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 杨威
 * @version v1.0 2016-05-28
 */
@Service("shopCommentServiceImpl")
public class ShopCommentServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopComment,java.lang.Long> implements ShopCommentService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(ShopCommentServiceImpl.class);
	/**注入店铺评论信息DAO接口类*/
	@Resource(name="shopCommentDaoImpl")
    private ShopCommentDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.shop.vo.ShopComment,java.lang.Long> getDao() {
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