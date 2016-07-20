package com.qtz.sm.goods.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.FifteenLongId;
import com.mall.core.common.Pinyin4jUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.goods.dao.GdGoodsBrandsDao;
import com.qtz.sm.goods.enums.DisableEnum;
import com.qtz.sm.goods.model.GdGoodsBrandsBo;
import com.qtz.sm.goods.service.GdGoodsBrandsService;
import com.qtz.sm.goods.vo.GdGoodsBrands;

import utils.StringUtils;
/**
 * <p>Title:GdGoodsBrandsServiceImpl</p>
 * <p>Description:品牌系列服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
@Service("gdGoodsBrandsServiceImpl")   
public class GdGoodsBrandsServiceImpl extends BaseServiceImpl<GdGoodsBrands,java.lang.Long> implements GdGoodsBrandsService  {
	/**初始化日志对象*/
	private static LogTool log = LogTool.getInstance(GdGoodsBrandsServiceImpl.class);
	/**注入品牌系列DAO接口类*/
	@Resource(name="gdGoodsBrandsDaoImpl")
    private GdGoodsBrandsDao dao;
	@Autowired
	private FifteenLongId fifteenLongId;
    
	/** 
	* 【取得】业务DAO对象
	* @return 	业务DAO对象  
	*/
	@Override
	protected BizDao<GdGoodsBrands,java.lang.Long> getDao() {
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
	public void addBrands(Long supplierId, String brandsName) throws ServiceException {
		if (supplierId == null){
			throw new ServiceException("供应商ID为空");
		}
		GdGoodsBrands brands = new GdGoodsBrands();
		brands.setSupplierId(supplierId);
		brands.setCnName(brandsName);
		brands.setPinyin(Pinyin4jUtil.getPinYin(brandsName));
		brands.setDmId(fifteenLongId.nextId());
		brands.setStatus(DisableEnum.enable.getValue());
		brands.setCreateBy(supplierId);
		brands.setCreateOn(System.currentTimeMillis());
		brands.setUpdateOn(supplierId);
		brands.setUpdateBy(System.currentTimeMillis());
		this.addVo(brands);
	}
	@Override
	public void modBrandsList(Long supplierId, List<GdGoodsBrandsBo> brandsList) throws ServiceException {
		if (brandsList == null){
			throw new ServiceException("品牌列表为空");
		}
		
		for (GdGoodsBrandsBo bo: brandsList) {
			Long dmId = bo.getDmId();
			String cnName = bo.getCnName();
			if (dmId == null || dmId == -1) {
				addBrands(supplierId, bo.getCnName());
			} else {
				GdGoodsBrands boDb = this.findVo(dmId, null);
				if (boDb == null) {
					log.info("品牌不存在，dmId="+dmId);
					continue;
				} else {//判断是否需要更新
					if (StringUtils.isNotBlank(cnName) && !cnName.equals(boDb.getCnName())){
						boDb.setCnName(cnName);
						boDb.setPinyin(Pinyin4jUtil.getPinYin(cnName));
						boDb.setUpdateOn(supplierId);
						boDb.setUpdateBy(System.currentTimeMillis());
						
						this.modVoNotNull(boDb);
					}
				}
			}
		}
	}
	@Override
	public List<GdGoodsBrands> getBrandsList(Long supplierId) throws ServiceException {
		if (supplierId == null){
			throw new ServiceException("供应商ID为空");
		}
		GdGoodsBrands brand = new GdGoodsBrands();
		brand.setSupplierId(supplierId);
		brand.setStatus(DisableEnum.enable.getValue());

		List<GdGoodsBrands> brands = this.findList(brand);
		return brands;
	}
}