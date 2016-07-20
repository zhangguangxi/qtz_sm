package com.qtz.sm.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsTypePropertyOptionDao;
import com.qtz.sm.goods.enums.DisableEnum;
import com.qtz.sm.goods.service.GdGoodsTypePropertyOptionService;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;

/**
 * <p>
 * Title:GdGoodsTypePropertyOptionServiceImpl
 * Description:商品分类属性值选项服务实现类
 * Copyright: Copyright (c) 2016
 * Company: 深圳市擎天柱信息科技有限公司
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsTypePropertyOptionServiceImpl")
public class GdGoodsTypePropertyOptionServiceImpl extends BaseServiceImpl<GdGoodsTypePropertyOption, java.lang.Long>
		implements GdGoodsTypePropertyOptionService {
	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(GdGoodsTypePropertyOptionServiceImpl.class);
	/** 注入商品分类属性值选项DAO接口类 */
	@Resource(name = "gdGoodsTypePropertyOptionDaoImpl")
	private GdGoodsTypePropertyOptionDao dao;

	/**
	 * 【取得】业务DAO对象
	 * 
	 * @return 业务DAO对象
	 */
	@Override
	protected BizDao<GdGoodsTypePropertyOption, java.lang.Long> getDao() {
		return dao;
	}

	/**
	 * 【取得】日志对象
	 * 
	 * @return 日志对象
	 */
	@Override
	protected LogTool getLog() {
		return log;
	}

	@Override
	public List<GdGoodsTypePropertyOption> getPropOptionList(Long goodsTypePropId, boolean onlyEnabled)
			throws ServiceException {
		GdGoodsTypePropertyOption optionQuery = new GdGoodsTypePropertyOption();
		optionQuery.setGoodsTypeProId(goodsTypePropId);
		if (onlyEnabled) {
			optionQuery.setStatus(DisableEnum.enable.getValue());
		}
		try {
			return dao.findList(optionQuery);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Map<Long, GdGoodsTypePropertyOption> getPropOptionMap(Long goodsTypePropId, boolean onlyEnabled)
			throws ServiceException {
		List<GdGoodsTypePropertyOption> options = this.getPropOptionList(goodsTypePropId, onlyEnabled);
		Map<Long, GdGoodsTypePropertyOption> resultMap = new HashMap<Long, GdGoodsTypePropertyOption>();
		if (options != null) {
			for (GdGoodsTypePropertyOption opt : options) {
				resultMap.put(opt.getDmId(), opt);
			}
		}
		return resultMap;
	}

	@Override
	public List<GdGoodsTypePropertyOption> getPropOptionsByGoodsType(Long goodsTypeId)
			throws ServiceException {
		try {
			return dao.getOptionsByGoodsType(goodsTypeId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Map<Long, GdGoodsTypePropertyOption> getPropOptionMapByGoodsType(Long goodsTypeId)
			throws ServiceException {
		List<GdGoodsTypePropertyOption> options = this.getPropOptionsByGoodsType(goodsTypeId);
		Map<Long, GdGoodsTypePropertyOption> resultMap = new HashMap<Long, GdGoodsTypePropertyOption>();
		if (options != null && !options.isEmpty()) {
			for (GdGoodsTypePropertyOption option:options) {
				resultMap.put(option.getDmId(), option);
			}
		}
		return resultMap;
	}
}