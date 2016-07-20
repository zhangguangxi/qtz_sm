package com.qtz.sm.goods.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsDao;
import com.qtz.sm.goods.dao.GdGoodsOperationHistoryDao;
import com.qtz.sm.goods.model.GdGoodsOperationHistoryListJson;
import com.qtz.sm.goods.page.GdGoodsOperationHistoryPage;
import com.qtz.sm.goods.service.GdGoodsOperationHistoryService;
import com.qtz.sm.goods.vo.GdGoods;
import com.qtz.sm.goods.vo.GdGoodsOperationHistory;
/**
 * <p>Title:GdGoodsOperationHistoryServiceImpl</p>
 * <p>Description:商品操作历史记录服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
  * <p>Company: 深圳擎天柱信息技术有限公司</p>
 * @author 欧江波 - 928482427@qq.com
 * @version v1.0 2016-06-15
 */
@Service("gdGoodsOperationHistoryServiceImpl")
public class GdGoodsOperationHistoryServiceImpl extends BaseServiceImpl<com.qtz.sm.goods.vo.GdGoodsOperationHistory,java.lang.Long> implements GdGoodsOperationHistoryService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(GdGoodsOperationHistoryServiceImpl.class);
	/**注入商品操作历史记录DAO接口类*/
	@Resource(name="gdGoodsOperationHistoryDaoImpl")
    private GdGoodsOperationHistoryDao dao;
	@Autowired
	private GdGoodsDao goodsDao;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<com.qtz.sm.goods.vo.GdGoodsOperationHistory,java.lang.Long> getDao() {
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
	@Override
	public GdGoodsOperationHistoryListJson getGoodsOpretorHistory(Long goodsId, List<Long> oprators, Map<Long, String> userMap,  Map<Long, String> positionMap, 
			Integer pageIndex, Integer pageSize)
			throws ServiceException {
		try {
			if(oprators == null || oprators.isEmpty()){
				throw new ServiceException("操作人oprators参数不能为空");
			}
			GdGoods goods = goodsDao.findVo(goodsId, null);
			if (goods == null){
				return null;
			}
			GdGoodsOperationHistoryPage queryPage = new GdGoodsOperationHistoryPage();
			queryPage.setGoodsId(goodsId);
			queryPage.setOperatorList(oprators);
			queryPage.setNowPage(pageIndex);
			queryPage.setPageSize(pageSize);
			GdGoodsOperationHistoryPage historyPage = (GdGoodsOperationHistoryPage)dao.query(queryPage, null);
			
			GdGoodsOperationHistoryListJson json = new GdGoodsOperationHistoryListJson(goodsId, goods.getName(), goods.getCode(), historyPage);
			if (userMap == null) {
				userMap = new HashMap<Long, String>();
			}
			if (positionMap == null) {
				positionMap = new HashMap<Long, String>();
			}
			
			if(historyPage != null){
				List<GdGoodsOperationHistory> histories = historyPage.getList() ;
				if (histories != null && !histories.isEmpty()){
					for (GdGoodsOperationHistory his:histories) {
						his.setOpratorName(userMap.get(his.getOperator()));
						his.setPosition(positionMap.get(his.getOperator()));
					}
				}
			}
			return json;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}