package com.qtz.sm.supp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.FifteenLongId;
import com.mall.core.common.MD5Util;
import com.mall.core.common.StringUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.goods.model.GdGoodsBrandsBo;
import com.qtz.sm.goods.service.GdGoodsBrandsService;
import com.qtz.sm.goods.vo.GdGoodsBrands;
import com.qtz.sm.scm.service.CsGylInfoService;
import com.qtz.sm.scm.vo.CsGylInfo;
import com.qtz.sm.supp.dao.CsGysInfoDao;
import com.qtz.sm.supp.service.CsGysAddressService;
import com.qtz.sm.supp.service.CsGysDeliveryRegionService;
import com.qtz.sm.supp.service.CsGysInfoService;
import com.qtz.sm.supp.service.CsGysStaffService;
import com.qtz.sm.supp.vo.CsGysAddress;
import com.qtz.sm.supp.vo.CsGysDeliveryRegion;
import com.qtz.sm.supp.vo.CsGysInfo;
import com.qtz.sm.supp.vo.CsGysStaff;
import com.qtz.sm.wallet.service.WtGysIncomeService;
import com.qtz.sm.wallet.service.WtWalletService;

/**
 * Title:CsGysInfoServiceImpl<br/>
 * Description:(供应商信息SERVICE实现类)<br/>
 * Copyright: Copyright © 2016<br/>
 * Company: 深圳市擎天柱信息技术有限公司<br/>
 *
 * @author 甘佳-jackgrays@matrix.com
 * @version v1.0 2016-04-20
 */
@Service("csGysInfoServiceImpl")
public class CsGysInfoServiceImpl extends BaseServiceImpl<CsGysInfo, Long> implements CsGysInfoService {

    /**
     * 初始化日志对象
     */
    private static LogTool log = LogTool.getInstance(CsGysInfoServiceImpl.class);
    /**
     * 注入CsGysInfoDao接口类
     */
    @Resource(name = "csGysInfoDaoImpl")
    private CsGysInfoDao dao;

    @Autowired
    private FifteenLongId fifteenLongId;

    @Resource(name = "csRegionsServiceImpl")
    private CsRegionsService csRegionsService;

    @Resource(name = "csGysStaffServiceImpl")
    private CsGysStaffService csGysStaffService;

    @Resource(name = "csGysAddressServiceImpl")
    private CsGysAddressService csGysAddressService;

    @Resource(name = "csGysDeliveryRegionServiceImpl")
    private CsGysDeliveryRegionService csGysDeliveryRegionService;
    
    @Autowired
   	private WtWalletService wtWalletService;
    
    @Autowired
    private  CsGylInfoService csGylInfoService;
    
    @Autowired
    private  GdGoodsBrandsService gdGoodsBrandsService;
    
    @Autowired
    private WtGysIncomeService wtGysIncomeService;

    /**
     * 【取得】业务DAO对象
     *
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<CsGysInfo, Long> getDao() {
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
    public String findNextIdentifier(Integer provinceId, Integer cityId) throws ServiceException {

        String prefix = csRegionsService.findPrefixCodeById(provinceId, cityId);
        StringBuffer result = new StringBuffer();
        result.append(prefix);
        try {
            synchronized (this) {
                String maxSn = dao.findMaxIdentifier(prefix);
                if (null == maxSn) {
                    result.append(String.format("%010d", 1));// 之前没有就从1开始编号
                } else {
                    long sn = Long.parseLong(maxSn.substring(6));// 从第六位开始
                    result.append(String.format("%010d", ++sn));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result.toString();
    }

    @Override
    public void addSuppAndInit(CsGysInfo info, Map<String,Object> map) throws ServiceException {
        if (null == info) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,info=" + info);
        }
        if (null == info.getIdentifier() || info.getIdentifier().length() != 16) {
            throw new ServiceException(ExceptionCode.WRONG_IDENTIFIER, "编号错误," + info.getIdentifier());
        }
        CsGysInfo hasVo = this.findVoByName(info.getName());
        if (null != hasVo) {
            throw new ServiceException(ExceptionCode.EXIST_SUPP_NAME, "已存在相同名称的供应商:" + info.getName());
        }
        String contactPhone = info.getContactPhone();
//        long suppId = fifteenLongId.nextId();
//        info.setDmId(suppId);
        Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
        info.setEstablishTime(dt);
        info.setStatus((byte) 0);
        this.addVo(info);
        
        
        // 初始化一个管理员员工
        CsGysStaff staff = new CsGysStaff();
        staff.setPhone(contactPhone);
        List<CsGysStaff> stafflist = csGysStaffService.findList(staff);
        if(stafflist.size() > 0){
        	 throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,员工信息已存在" + contactPhone);
        }else{
        	staff.setGysId(info.getDmId());
            staff.setName(info.getLpName());
            staff.setPwd(MD5Util.getMD5("123456"));
            staff.setAccountType((byte) 0);
            staff.setStatus((byte) 0);    
            csGysStaffService.addVo(staff);
        }   
        
        CsGysAddress csGysAddress = new CsGysAddress();
	
      //供货商地址添加
		
		csGysAddress.setProvinceId(Integer.valueOf(map.get("provinceId").toString()!=null?map.get("provinceId").toString():null));
        csGysAddress.setCityId(Integer.valueOf(map.get("cityId").toString()!=null?map.get("cityId").toString():null));
        csGysAddress.setCountyId(Integer.valueOf(map.get("countyId").toString()!=null?map.get("countyId").toString():null));
        csGysAddress.setAddress(map.get("address").toString()!=null?map.get("address").toString():"");
        csGysAddress.setFullAddress(csRegionsService.getFullAddressByIds(csGysAddress.getProvinceId(),csGysAddress.getCityId(), csGysAddress.getCountyId(),null,null)+csGysAddress.getAddress());
        csGysAddress.setGysId(info.getDmId());
//        csGysAddressService.delAllAddress(suppId);//先删除
        csGysAddressService.addVo(csGysAddress);
        //供应商供货区域
        if(info.getRegionList().size() > 0 ){
        	for(int i =0;i<info.getRegionList().size();i++){
        		CsGysDeliveryRegion csGysDeliveryRegion = new CsGysDeliveryRegion();
        		csGysDeliveryRegion.setProvinceId(info.getRegionList().get(i).getProvinceId());
                csGysDeliveryRegion.setCityId(info.getRegionList().get(i).getCityId());
                csGysDeliveryRegion.setGysId(info.getDmId());
                csGysDeliveryRegion.setLimitType(new Byte("0"));
//                csGysDeliveryRegionService.delAllDeliveryRegion(suppId);//先删除区域
                csGysDeliveryRegionService.addVo(csGysDeliveryRegion);
            }
        }
        
        

        //添加钱包信息
       List<CsGylInfo> list = csGylInfoService.findList(new CsGylInfo());
       if(list.size() > 0){
    	   wtWalletService.addGysWallet(info.getDmId(),info.getName(),list.get(0).getDmId());
       }     
       
       //修改品牌信息
       if( null != info.getGdGoodsBrandList() && info.getGdGoodsBrandList().size()>0){
    	   for(int i =0 ;i<info.getGdGoodsBrandList().size(); i++){
    		   gdGoodsBrandsService.addBrands(info.getDmId(), info.getGdGoodsBrandList().get(i).getCnName());
    	   }	   
       }
       
       
       
//     //添加地址信息
//     String jsonAddress = info.getJsonAddress();
//     if (!StringUtil.isEmpty(jsonAddress)) {
//         //先删除地址
//         csGysAddressService.delAllAddress(suppId);
//         CsGysAddress address = JSON.parseObject(jsonAddress, CsGysAddress.class);
//         address.setGysId(suppId);
//         String fullAddress = csRegionsService.getFullAddressByIds(address.getProvinceId(), address.getCityId(), address.getCountyId(), address.getTownId(), address.getAddress());
//         address.setFullAddress(fullAddress);
//         csGysAddressService.addVo(address);
//     }
//     //添加限制区域
//     String jsonArea = info.getJsonArea();
//     if (!StringUtil.isEmpty(jsonArea)) {
//         csGysDeliveryRegionService.delAllDeliveryRegion(suppId);//先删除区域
//         List<CsGysDeliveryRegion> drList = JSON.parseArray(jsonArea, CsGysDeliveryRegion.class);
//         csGysDeliveryRegionService.addDeliveryRegionList(suppId, drList);
//     }
    }

    @Override
    public CsGysInfo findVoByName(String name) throws ServiceException {
        if (StringUtil.isEmpty(name)) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,name=" + name);
        }
        name = name.trim();
        try {
            return dao.findVoByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void modSuppInfo(CsGysInfo info, Map<String,Object> map) throws ServiceException {
        Long dmId = info.getDmId();
        if (null == dmId) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,info.dmId=" + dmId);
        }
        Long suppId = dmId;
        CsGysInfo old = this.findVo(dmId, null);
        String oldContactPhone = old.getContactPhone();
        String newContactPhone = info.getContactPhone();
        if (!oldContactPhone.equals(newContactPhone)) {//修改联系人手机号,要同时修改员工表里的管理员
            CsGysStaff qStaff = new CsGysStaff();
            qStaff.setGysId(dmId);
            qStaff.setPhone(oldContactPhone);
            List<CsGysStaff> qList = csGysStaffService.findList(qStaff);
            for (CsGysStaff staff : qList) {
                staff.setPhone(newContactPhone);
                csGysStaffService.modVoNotNull(staff);
            }
        }
        this.modVoNotNull(info);
        
        CsGysAddress csGysAddress = new CsGysAddress();

      //供货商地址添加
		csGysAddressService.delAllAddress(suppId);//先删除
        csGysAddress.setProvinceId(Integer.valueOf(map.get("provinceId").toString()!=null?map.get("provinceId").toString():null));
        csGysAddress.setCityId(Integer.valueOf(map.get("cityId").toString()!=null?map.get("cityId").toString():null));
        csGysAddress.setCountyId(Integer.valueOf(map.get("countyId").toString()!=null?map.get("countyId").toString():null));
        csGysAddress.setAddress(map.get("address").toString()!=null?map.get("address").toString():"");
        csGysAddress.setFullAddress(csRegionsService.getFullAddressByIds(csGysAddress.getProvinceId(),csGysAddress.getCityId(), csGysAddress.getCountyId(),null,null)+csGysAddress.getAddress());
        csGysAddress.setGysId(info.getDmId());
        
        csGysAddressService.addVo(csGysAddress);
        //供应商供货区域
        
        if(info.getRegionList().size() > 0 ){
        	csGysDeliveryRegionService.delAllDeliveryRegion(suppId);//先删除区域
        	for(int i =0;i<info.getRegionList().size();i++){
        		CsGysDeliveryRegion csGysDeliveryRegion = new CsGysDeliveryRegion();
        		csGysDeliveryRegion.setProvinceId(info.getRegionList().get(i).getProvinceId());
                csGysDeliveryRegion.setCityId(info.getRegionList().get(i).getCityId());
                csGysDeliveryRegion.setGysId(info.getDmId());
                csGysDeliveryRegion.setLimitType(new Byte("0"));
//                csGysDeliveryRegionService.delAllDeliveryRegion(suppId);//先删除区域
                csGysDeliveryRegionService.addVo(csGysDeliveryRegion);
            }
        }
        
        
        List<GdGoodsBrandsBo> gdbList = new ArrayList<GdGoodsBrandsBo>();
        if( null != info.getGdGoodsBrandList() && info.getGdGoodsBrandList().size()>0){
     	   for(int i =0 ;i<info.getGdGoodsBrandList().size(); i++){
     		  GdGoodsBrandsBo gbb = new GdGoodsBrandsBo();
	     	  gbb.setCnName(info.getGdGoodsBrandList().get(i).getCnName());
	     	  gbb.setDmId(info.getGdGoodsBrandList().get(i).getDmId());
	     	  gdbList.add(gbb);
     		   gdGoodsBrandsService.modBrandsList(info.getDmId(), gdbList);
     	   }	   
        }
        
        
        if(info.getEttlementCycle() != old.getEttlementCycle()){
       	 //修改结算周期
       	if(  null != info.getEttlementCycle()){
       		 wtGysIncomeService.modSettlementTime(info.getDmId(),info.getEttlementCycle());
       	}
          
       }
//        //修改地址信息
//        String jsonAddress = info.getJsonAddress();
//        if (!StringUtil.isEmpty(jsonAddress)) {
//            //先删除地址
//            csGysAddressService.delAllAddress(suppId);
//            CsGysAddress address = JSON.parseObject(jsonAddress, CsGysAddress.class);
//            address.setGysId(suppId);
//            String fullAddress = csRegionsService.getFullAddressByIds(address.getProvinceId(), address.getCityId(), address.getCountyId(), address.getTownId(), address.getAddress());
//            address.setFullAddress(fullAddress);
//            csGysAddressService.addVo(address);
//        }
//        //添加供货区域
//        String jsonArea = info.getJsonArea();
//        if (!StringUtil.isEmpty(jsonArea)) {
//            csGysDeliveryRegionService.delAllDeliveryRegion(suppId);//先删除区域
//            List<CsGysDeliveryRegion> drList = JSON.parseArray(jsonArea, CsGysDeliveryRegion.class);
//            csGysDeliveryRegionService.addDeliveryRegionList(suppId, drList);
//        }
    }

    @Override
    public CsGysInfo findDeatailVo(Long suppId) throws ServiceException {
        if (null == suppId) {
            throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误,suppId=" + suppId);
        }
        CsGysInfo info = this.findVo(suppId,null);
        if(null == info){
            throw new ServiceException(ExceptionConstants.ERRORCODE_7,"不存在该供应商,suppId="+suppId);
        }
        CsGysAddress address = csGysAddressService.findOnlyAddress(suppId);
        info.setJsonAddress(JSON.toJSONString(address));
        CsGysDeliveryRegion dr = new CsGysDeliveryRegion();
        dr.setGysId(suppId);
        List<CsGysDeliveryRegion> drList = csGysDeliveryRegionService.findList(dr);
        info.setJsonArea(JSON.toJSONString(drList));
        return info;
    }

}
