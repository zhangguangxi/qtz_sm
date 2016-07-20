package com.qtz.sm.goods.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.mall.core.dao.BizDao;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.service.GdGoodsDescPictureService;
import com.qtz.sm.goods.dao.GdGoodsDescPictureDao;
/**
 * <p>Title:GdGoodsDescPictureServiceImpl</p>
 * <p>Description:商品描述图片服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-16
 */
@Service("gdGoodsDescPictureServiceImpl")
public class GdGoodsDescPictureServiceImpl extends BaseServiceImpl<com.qtz.sm.goods.vo.GdGoodsDescPicture,java.lang.Long> implements GdGoodsDescPictureService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(GdGoodsDescPictureServiceImpl.class);
	/**注入商品描述图片DAO接口类*/
	@Resource(name="gdGoodsDescPictureDaoImpl")
    private GdGoodsDescPictureDao dao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.goods.vo.GdGoodsDescPicture,java.lang.Long> getDao() {
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