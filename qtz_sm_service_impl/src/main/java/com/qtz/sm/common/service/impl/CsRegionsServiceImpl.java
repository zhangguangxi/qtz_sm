package com.qtz.sm.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.dao.CsRegionsDao;
import com.qtz.sm.common.model.ResionModel;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.util.ProvincePyUtil;
import com.qtz.sm.common.vo.CsRegions;

/**
 * Title:CsRegionsServiceImpl<br/>
 * Description:(行政区域SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-21
 */
@Service("csRegionsServiceImpl")
public class CsRegionsServiceImpl extends BaseServiceImpl<CsRegions, Integer> implements CsRegionsService {

    /**
     * 初始化日志对象
     */
    private static LogTool log = LogTool.getInstance(CsRegionsServiceImpl.class);
    /**
     * 注入CsRegionsDao接口类
     */
    @Resource(name = "csRegionsDaoImpl")
    private CsRegionsDao dao;

    /**
     * 【取得】业务DAO对象
     *
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<CsRegions, Integer> getDao() {
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
    public String findPrefixCodeById(Integer provinceId, Integer cityId) throws ServiceException {
        if(null == cityId){
            cityId=0;
        }
        StringBuffer result = new StringBuffer();
        String provinceCode = ProvincePyUtil.getFirstPY(provinceId);
        if (null == provinceCode) {
            provinceCode = "ZG";//找不到就默认中国
        }
        result.append(provinceCode);
        String cityCode = null;
        CsRegions vo = this.findVo(cityId, null);
        if (null != vo) {
            cityCode = vo.getCityCode();
        }
        if (null == cityCode) {
            cityCode = "0086";//找不到就默认电话区号
        } else {
            cityCode = String.format("%04d", Integer.valueOf(cityCode));
        }
        result.append(cityCode);
        return result.toString();
    }

    @Override
    public String getFullAddressByIds(Integer provinceId, Integer cityId, Integer countyId, Integer townId, String address) throws ServiceException {
        StringBuffer result = new StringBuffer();
        if (null != provinceId) {
            CsRegions vo = this.findVo(provinceId, null);
            if (null != vo) {
                result.append(vo.getShortName());
            }
        }
        if (null != cityId) {
            CsRegions vo = this.findVo(cityId, null);
            if (null != vo) {
                result.append(vo.getName());
            }
        }
        if (null != countyId) {
            CsRegions vo = this.findVo(countyId, null);
            if (null != vo) {
                result.append(vo.getName());
            }
        }
        if (null != townId) {
            CsRegions vo = this.findVo(townId, null);
            if (null != vo) {
                result.append(vo.getShortName());
            }else{
            	 result.append("");
            }
        }else{
       	 result.append("");
       }
        if( null != address && !"".equals(address)){
        	 result.append(address);
        }else{
        	 result.append("");
        }
       
        return result.toString();
    }

    @Override
    public Map<String, CsRegions> getAddressByIds(Integer provinceId, Integer cityId, Integer countyId, Integer townId) throws ServiceException {
        Map<String, CsRegions> result = new HashMap<>();
        if (null != provinceId) {
            CsRegions province = this.findVo(provinceId, null);
            if (null == province) {
                province = new CsRegions();
            }
            result.put("province", province);
        }
        if (null != cityId) {
            CsRegions city = this.findVo(cityId, null);
            if (null == city) {
                city = new CsRegions();
            }
            result.put("city", city);
        }
        if (null != countyId) {
            CsRegions area = this.findVo(countyId, null);
            if (null == area) {
                area = new CsRegions();
            }
            result.put("area", area);
        }
        if (null != townId) {
            CsRegions town = this.findVo(townId, null);
            if (null == town) {
                town = new CsRegions();
                town.setName("");
                town.setShortName("");
            }
            result.put("town", town);
        }
        return result;
    }

    /**
     * JSON查询所有省市区
     */
	@Override
	public List<ResionModel> getResion() throws ServiceException {
		List<ResionModel> models = new ArrayList<ResionModel>();
		
		//省
		CsRegions cr = new CsRegions();
		cr.setParentId(0);
		List<CsRegions> provices = this.findList(cr);
		
		if(provices != null && provices.size()>0){
			for(int i =0; i<provices.size();i++){
				CsRegions provice = provices.get(i);
				ResionModel proviceModel = new ResionModel(provice.getName(),provice.getDmId().toString(),null);	
				
				CsRegions cc = new CsRegions();
				cc.setParentId(provice.getDmId());
				List<CsRegions> cities = this.findList(cc);
				
				List<ResionModel> citiesModel = new ArrayList<ResionModel>();
				
				for(int j=0;j<cities.size();j++){
					CsRegions city = cities.get(j);
					ResionModel cityModel = new ResionModel(city.getName(),city.getDmId().toString(),null);	
					citiesModel.add(cityModel);
					proviceModel.setChilds(citiesModel);
					
					
					CsRegions qx = new CsRegions();
					qx.setParentId(city.getDmId());
					List<CsRegions> counties = this.findList(qx);
					
					List<ResionModel> countiesModel = new ArrayList<ResionModel>();
					
					for (int k = 0; k < counties.size(); k++) {
						CsRegions county = counties.get(k);
						ResionModel countyModel = new ResionModel(county.getName(),county.getDmId().toString(),null);	
						countiesModel.add(countyModel);
						cityModel.setChilds(countiesModel);
					}
				}
				models.add(proviceModel);
			}
		}
		return models;
	}
}

