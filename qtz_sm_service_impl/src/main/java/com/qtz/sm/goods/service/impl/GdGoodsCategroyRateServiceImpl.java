package com.qtz.sm.goods.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.enums.CompanyType;
import com.qtz.sm.goods.dao.GdGoodsCategroyRateDao;
import com.qtz.sm.goods.enums.GoodsTypeRateTypeEnum;
import com.qtz.sm.goods.model.GdGoodsCategroyRateBo;
import com.qtz.sm.goods.model.GdGoodsCategroyRateUpdateBo;
import com.qtz.sm.goods.service.GdGoodsCategroyRateService;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;

/**
 * <p>
 * Title:GdGoodsCategroyRateServiceImpl
 * </p>
 * <p>
 * Description:商品SKU溢价率服务实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsCategroyRateServiceImpl")
public class GdGoodsCategroyRateServiceImpl extends BaseServiceImpl<GdGoodsCategroyRate, java.lang.Long>
		implements GdGoodsCategroyRateService {
	/** 初始化日志对象 */
	private static LogTool log = LogTool.getInstance(GdGoodsCategroyRateServiceImpl.class);
	/** 注入商品SKU溢价率DAO接口类 */
	@Resource(name = "gdGoodsCategroyRateDaoImpl")
	private GdGoodsCategroyRateDao dao;

	/**
	 * 【取得】业务DAO对象
	 * 
	 * @return 业务DAO对象
	 */
	@Override
	protected BizDao<GdGoodsCategroyRate, java.lang.Long> getDao() {
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
	public void updateList(String dmIds, String rates, int sourceType) throws ServiceException {
		if (StringUtils.isEmpty(dmIds)) {
			throw new ServiceException("商品分类不能为空");
		}
		if (StringUtils.isEmpty(rates)) {
			throw new ServiceException("商品分类议价率不能为空");
		}
		String[] ids = dmIds.split(";");
		String[] rs = rates.split(";");

		if (ids.length != rs.length) {
			throw new ServiceException("商品分类议价参数数量不匹配");
		}
		try {
			for (int i = 0; i < ids.length; i++) {
				String dmId = ids[i];
				String rate = rs[i];
				GdGoodsCategroyRate obj;
				obj = getDao().findVo(Long.parseLong(dmId), null);

				if (GoodsTypeRateTypeEnum.supplierRate == sourceType) {
					obj.setGylRate(Double.parseDouble(rate));
				} else if (GoodsTypeRateTypeEnum.cloudStoreRate == sourceType) {
					obj.setYccglRate(Double.parseDouble(rate));
				} else if (GoodsTypeRateTypeEnum.bldCPRate == sourceType) {
					obj.setBldglRate(Double.parseDouble(rate));
				} else if (GoodsTypeRateTypeEnum.ppcsRate == sourceType) {
					obj.setPpcsRate(Double.parseDouble(rate));
				}
				getDao().modVoNotNull(obj);
			}
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<GdGoodsCategroyRateBo> getRatesWithCategoryInfo(Long parentGoodsTypeId, Integer rateType) throws ServiceException{
		try {
			if (rateType == null){
				return null;
			}
			List<GdGoodsCategroyRate> list = this.dao.getRatesWithCategoryInfo(parentGoodsTypeId);
			List<GdGoodsCategroyRateBo> resultList =  new ArrayList<GdGoodsCategroyRateBo>();
			if (list != null) {
				for (GdGoodsCategroyRate r:list) {
					GdGoodsCategroyRateBo bo = new GdGoodsCategroyRateBo(r.getDmId(), r.getGoodsTypeId(), r.getParentId(), r.getName(), rateType);
					Double rate = new Double(0);
					if (rateType == CompanyType.SuperMarket.value()){
						rate = r.getPpcsRate();
					} else if (rateType == CompanyType.StoreManager.value()){
						rate = r.getBldglRate();
					} else if (rateType == CompanyType.CloudStorage.value()){
						rate = r.getYccglRate();
					} else if (rateType == CompanyType.SupplyChain.value()){
						rate = r.getGylRate();
					}
					bo.setRate(rate);
					resultList.add(bo);
				}
			}
			return resultList;
		} catch (DaoException e) {
			log.error(e);
			throw new ServiceException(e);
		}
	}
	@Override
	public GdGoodsCategroyRate getCategoryRate(Long goodsTypeId) throws ServiceException {
		GdGoodsCategroyRate categroyRate = null;
		try {
			GdGoodsCategroyRate categoryRateQuery = new GdGoodsCategroyRate();
			categoryRateQuery.setGoodsTypeId(goodsTypeId);
			List<GdGoodsCategroyRate> categroyRateList = getDao().findList(categoryRateQuery);
			
			if(categroyRateList !=null && !categroyRateList.isEmpty()){
				categroyRate = categroyRateList.get(0);
			}
			return categroyRate;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<GdGoodsCategroyRateBo> findTree(Long goodsTypeId, Integer rateType) throws ServiceException {
		List<GdGoodsCategroyRateBo> allRateList = this.getRatesWithCategoryInfo(null, rateType);
		return buildTree(allRateList, goodsTypeId);
	}
	private List<GdGoodsCategroyRateBo> buildTree(List<GdGoodsCategroyRateBo> allRateList, Long goodsTypeId) throws ServiceException {
		List<GdGoodsCategroyRateBo> rateList = new ArrayList<GdGoodsCategroyRateBo>();
		for (GdGoodsCategroyRateBo rate:allRateList) {
			Long parentTypeId = rate.getParentId();
			if (parentTypeId != null && parentTypeId.equals(goodsTypeId)) {
				List<GdGoodsCategroyRateBo> childs = buildTree(allRateList, rate.getGoodsTypeId());
				if (childs!= null && !childs.isEmpty()){
					rate.setChilds(childs);
				}
				rateList.add(rate);
			}
		}
		return rateList;
	}

	@Override
	public void updateTree(List<GdGoodsCategroyRateUpdateBo> categoryRateBoList, Integer companyType) throws ServiceException {
		if (companyType == null || categoryRateBoList == null) {
			return;
		}
		for(GdGoodsCategroyRateUpdateBo bo:categoryRateBoList){
			Long dmId = bo.getDmId();
			Double rate = bo.getRate();
			
			GdGoodsCategroyRate rateVo = this.findVo(dmId, null);
			if (rateVo == null) {
				continue ;
			}
			if (companyType == CompanyType.SuperMarket.value()){
				rateVo.setPpcsRate(rate); 
			} else if (companyType == CompanyType.StoreManager.value()){
				rateVo.setBldglRate(rate);
			} else if (companyType == CompanyType.CloudStorage.value()){
				rateVo.setYccglRate(rate);
			} else if (companyType == CompanyType.SupplyChain.value()){
				rateVo.setGylRate(rate);
			}
			this.modVoNotNull(rateVo);
		}
	}
}