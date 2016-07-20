package com.qtz.sm.shop.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.core.common.DateUtil;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.MD5Util;
import com.mall.core.common.StringUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.session.vo.User;
import com.qtz.sm.shop.dao.ShopInfoDao;
import com.qtz.sm.shop.service.ShopInfoOperationHistoryService;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.service.ShopStaffService;
import com.qtz.sm.shop.vo.ShopInfo;
import com.qtz.sm.shop.vo.ShopInfoOperationHistory;
import com.qtz.sm.shop.vo.ShopInfoVo;
import com.qtz.sm.shop.vo.ShopStaff;
import com.qtz.sm.shopManage.service.ShopManageCategoryPropertyService;
import com.qtz.sm.shopManage.service.ShopManageCategoryService;
import com.qtz.sm.shopManage.service.ShopManageService;
import com.qtz.sm.shopManage.vo.ShopManage;
import com.qtz.sm.shopManage.vo.ShopManageCategory;
import com.qtz.sm.shopManage.vo.ShopManageCategoryProperty;
import com.qtz.sm.store.service.CsCczxInfoService;
import com.qtz.sm.store.vo.CsCczxInfo;
import com.qtz.sm.wallet.service.WtBldglIncomeService;
import com.qtz.sm.wallet.service.WtWalletService;

/**
 * <p>Title:ShopInfoServiceImpl</p>
 * <p>Description:便利店基本信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选
 * @version v1.0 2016-05-26
 */
@Service("shopInfoServiceImpl")
public class ShopInfoServiceImpl extends BaseServiceImpl<com.qtz.sm.shop.vo.ShopInfo, Long> implements ShopInfoService {
    /**
     * 初始化日志对象
     */
    private static LogTool log = LogTool.getInstance(ShopInfoServiceImpl.class);
    /**
     * 注入便利店基本信息DAO接口类
     */
    @Resource(name = "shopInfoDaoImpl")
    private ShopInfoDao dao;

    /**
     * 便利店员工服务
     */
    @Resource(name = "shopStaffServiceImpl")
    private ShopStaffService shopStaffService;

    /**
     * 便利店管理公司服务
     */
    @Resource(name = "shopManageServiceImpl")
    private ShopManageService shopManageService;

    /**
     * 地区服务
     */
    @Resource(name = "csRegionsServiceImpl")
    private CsRegionsService csRegionsService;

    /**
     * 钱包服务
     */
    @Resource(name = "wtWalletServiceImpl")
    private WtWalletService wtWalletService;

    /**
     * 仓储中心服务
     */
    @Resource(name = "csCczxInfoServiceImpl")
    private CsCczxInfoService csCczxInfoService;
    
    /**
     * 便利店管理公司运营分类服务
     */
    @Resource(name = "shopManageCategoryServiceImpl")
    private ShopManageCategoryService shopManageCategoryService;
    
    /**
     * 便利店管理公司运营分类与便利店关联服务
     */
    @Resource(name = "shopManageCategoryPropertyServiceImpl")
    private ShopManageCategoryPropertyService shopManageCategoryPropertyService;
    
    @Autowired
    private WtBldglIncomeService wtBldglIncomeService;
    
    @Autowired
    private ShopInfoOperationHistoryService shopInfoOperationHistoryService;

    /**
     * 【取得】业务DAO对象
     *
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<com.qtz.sm.shop.vo.ShopInfo, Long> getDao() {
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
    public ShopInfoVo findById(Long id) throws ServiceException {
        ShopInfo shopInfo = this.findVo(id, null);
        ShopInfoVo shopInfoVo = new ShopInfoVo();
        BeanUtils.copyProperties(shopInfo, shopInfoVo);

        //查询所属便利店管理公司信息
        ShopManage shopManage = shopManageService.findVo(shopInfo.getShopManageId(), null);
        if (shopManage != null) {
            shopInfoVo.setShopManageName(shopManage.getName());
        }

        //所属仓储中心信息
        CsCczxInfo csCczxInfo = csCczxInfoService.findVo(shopInfo.getCczxId(), null);
        if (csCczxInfo != null) {
            shopInfoVo.setCczxName(csCczxInfo.getName());
        }
        
        //所属运营分类 并是0启用的
        ShopManageCategory ShopManageCategory = new ShopManageCategory();
        ShopManageCategory.setShopId(shopInfo.getDmId());
        ShopManageCategory.setStatus(0);
        shopInfoVo.setShopManageCategoryList(shopManageCategoryService.findListByShopId(ShopManageCategory));
        
        //省、市、区、镇
        Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(shopInfo.getProvinceId(),
                shopInfo.getCityId(),
                shopInfo.getAreaId(),
                shopInfo.getTownId());
        shopInfoVo.setProvinceName(csRegionsMap.get("province")!=null?csRegionsMap.get("province").getName():"");
        shopInfoVo.setCityName(csRegionsMap.get("city")!=null?csRegionsMap.get("city").getName():"");
        shopInfoVo.setAreaName(csRegionsMap.get("area")!=null?csRegionsMap.get("area").getName():"");
        shopInfoVo.setTownName(csRegionsMap.get("town")!=null?csRegionsMap.get("town").getName():"");
        return shopInfoVo;
    }

    @Override
    public void addVoStaffAddWallet(ShopInfo shopInfo) throws ServiceException {
        try {
            /*1、查询当前便利店手机号码在系统中是否存在，不存在则可用*/
            ShopInfo queryParam = new ShopInfo();
            queryParam.setMobile(shopInfo.getMobile());
            List<ShopInfo> shopInfoList = dao.findList(queryParam);
            if (shopInfoList.size() > 0) {
                throw new ServiceException("便利店手机号码已经存在！");
            }
            
            //查询便利店编号是否存在
            queryParam.setMobile(null);
            queryParam.setShopManageId(shopInfo.getShopManageId());
            queryParam.setCode(shopInfo.getCode());
            List<ShopInfo> lists = dao.findList(queryParam);
            if (lists.size() > 0) {
                throw new ServiceException("便利店编号已经存在！");
            }
            
			/*2、如果手机号可用，则新建便利店*/
            shopInfo = this.addVo(shopInfo);

			/*3、为便利店添加用户*/
            addShopStaff(shopInfo);

			/*4、为便利店创建钱包*/
            wtWalletService.addBldWallet(shopInfo.getDmId(), shopInfo.getName(),shopInfo.getShopManageId());
            
            /*5、关联经营范围分类*/
            //先删除，以免重复提交
            shopManageCategoryPropertyService.delByShopId(shopInfo.getDmId());
            //后添加
            List<ShopManageCategoryProperty> list = new ArrayList<ShopManageCategoryProperty>();
            for (int i = 0; i < shopInfo.getShopManageCategoryPropertyList().size(); i++) {
            	ShopManageCategoryProperty vo = new ShopManageCategoryProperty();
            	vo.setShopId(shopInfo.getDmId());
            	vo.setShopManageCategoryId(shopInfo.getShopManageCategoryPropertyList().get(i).getShopManageCategoryId());
            	list.add(vo);
			}
            shopManageCategoryPropertyService.addList(list);
        } catch (DaoException de) {
            log.error("添加便利店失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("添加便利店出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
    }
    
    @Override
    public void modVoAndStaff(ShopInfo shopInfo,User user) throws ServiceException {
        try {
            ShopInfo temp = dao.findVo(shopInfo.getDmId(), null);
            if(StringUtil.isEmpty(temp)){
            	throw new ServiceException("便利店信息不存在.");
            }
            ShopStaff shopStaff = new ShopStaff();
            shopStaff.setShopId(temp.getDmId());
            shopStaff.setPhone(temp.getMobile());
            List<ShopStaff> shopStaffList = shopStaffService.findList(shopStaff);
            //当前便利店没有对应手机号码的用户，则新建
            if (null == shopStaffList || shopStaffList.size() == 0) {
                /*为便利店添加用户*/
                addShopStaff(shopInfo);
            } else {
                //1、手机号码有变更
                if (!temp.getMobile().equals(shopInfo.getMobile())) {
                    shopStaff = shopStaffList.get(0);
                    //修改手机号码
                    shopStaff.setPhone(shopInfo.getMobile());
                    shopStaffService.modVoNotNull(shopStaff);
                }
            }
            //2、修改便利店信息
            dao.modVoNotNull(shopInfo);
            
            //3、先运营分类删除，以免重复提交
	        shopManageCategoryPropertyService.delByShopId(shopInfo.getDmId());
	        //后添加运营分类
	        List<ShopManageCategoryProperty> list = new ArrayList<ShopManageCategoryProperty>();
	        for (int i = 0; i < shopInfo.getShopManageCategoryPropertyList().size(); i++) {
	        	ShopManageCategoryProperty vo = new ShopManageCategoryProperty();
	        	vo.setShopId(shopInfo.getDmId());
	        	vo.setShopManageCategoryId(shopInfo.getShopManageCategoryPropertyList().get(i).getShopManageCategoryId());
	        	list.add(vo);
			}
	        shopManageCategoryPropertyService.addList(list);
	        
	        //4、若更改了结算周期需要推送给钱包处理
	        if(temp.getEttlementCycle()!= null 
	        		&& shopInfo.getEttlementCycle() != null
	        		&& !temp.getEttlementCycle().equals(shopInfo.getEttlementCycle())){
	        	wtBldglIncomeService.modSettlementTime(temp.getDmId(), shopInfo.getEttlementCycle());
	        }
	        
	        String content = getContent(temp, shopInfo);//5、操作内容
	        if(!content.equals("")){
		        ShopInfoOperationHistory shopInfoOperationHistory = new ShopInfoOperationHistory();
		        shopInfoOperationHistory.setOperator(user.getDmId());
		        shopInfoOperationHistory.setShopId(shopInfo.getDmId());//便利店ID
				shopInfoOperationHistory.setIp(user.getIp());
				shopInfoOperationHistory.setContent(content.toString());
				shopInfoOperationHistory.setOperateOn(DateUtil.dateToSecond(new Date()));
				//添加操作记录
		        shopInfoOperationHistoryService.addVo(shopInfoOperationHistory);
	        }
	        
	        //6、判断是否更换便利店名称
	        if(!temp.getName().equals(shopInfo.getName())){
	        	Map<String, Object> map = new HashMap<>();
	        	map.put("ownerId", shopInfo.getDmId());
	        	map.put("ownerName", shopInfo.getName());
	        	wtWalletService.updateOwnerName(map);
	        }
        } catch (DaoException de) {
            log.error("修改便利店失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("修改便利店出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 比较信息是否被修改
     * @Description:TODO
     * @param temp
     * @param shopInfo
     * @return
     * String
     * @exception:
     * @author: SunXuan
     * @time:2016年6月17日 上午10:40:32
     */
    protected String getContent (ShopInfo temp ,ShopInfo shopInfo ){
    	StringBuffer content = new StringBuffer("");//操作内容
    	if(!temp.getName().equals(shopInfo.getName())){
    		content.append("便利店名称由 \""+temp.getName() +"\" 改为 \"" + shopInfo.getName() +"\" ;");
    	}
    	if(!temp.getMobile().equals(shopInfo.getMobile())){
    		content.append("手机号码由 \""+temp.getMobile() +"\" 改为 \"" + shopInfo.getMobile() +"\" ;");
    	}
    	if(!temp.getAddress().equals(shopInfo.getAddress())){
    		content.append("地址由 \""+temp.getAddress() +"\" 改为 \"" + shopInfo.getAddress() +"\" ;");
    	}
    	if(!temp.getFranchiseFees().equals(shopInfo.getFranchiseFees())){
    		content.append("加盟费由 \""+temp.getFranchiseFees() +"\" 改为 \"" + shopInfo.getFranchiseFees() +"\" ;");
    	}
    	if(!temp.getSplitPoint().equals(shopInfo.getSplitPoint())){
    		content.append("分成点由 \""+temp.getSplitPoint() +"\" 改为 \"" + shopInfo.getSplitPoint() +"\" ;");
    	}
    	if(!temp.getEttlementCycle().equals(shopInfo.getEttlementCycle())){
    		content.append("结算周期由 \""+temp.getEttlementCycle() +"\" 改为 \"" + shopInfo.getEttlementCycle() +"\" ;");
    	}
    	if(!temp.getLpName().equals(shopInfo.getLpName())){
    		content.append("运营者由 \""+temp.getLpName() +"\" 改为 \"" + shopInfo.getLpName() +"\" ;");
    	}
    	if(!temp.getLpIdCard().equals(shopInfo.getLpIdCard())){
    		content.append("身份证号由 \""+temp.getLpIdCard() +"\" 改为 \"" + shopInfo.getLpIdCard() +"\" ;");
    	}
    	if(!temp.getIdCardBehind().equals(shopInfo.getIdCardBehind())){
    		content.append("身份证反面图片已修改;");
    	}
    	if(!temp.getIdCardFront().equals(shopInfo.getIdCardFront())){
    		content.append("身份证正面图片已修改;");
    	}
    	if(!temp.getLicence().equals(shopInfo.getLicence())){
    		content.append("营业执照由已修改;");
    	}
		return content.toString();
    	
    }
    
    @Override
    public String createCode(Integer provinceId, Integer cityId) throws ServiceException {
        try {
            if (null == provinceId || null == cityId) {
                throw new ServiceException(ExceptionConstants.ERRORCODE_7, "入参错误provinceId=" + provinceId + ",cityId=" + cityId);
            }
            String prefix = csRegionsService.findPrefixCodeById(provinceId, cityId);
            StringBuffer result = new StringBuffer();
            result.append(prefix);
            synchronized (this) {
                String maxSn = dao.findMaxCode(prefix);
                if (null == maxSn) {
                    result.append(String.format("%010d", 1));// 之前没有就从1开始编号
                } else {
                    long sn = Long.parseLong(maxSn.substring(6));// 从第六位开始
                    result.append(String.format("%010d", ++sn));
                }
            }
            return result.toString();
        } catch (DaoException de) {
            log.error("通过省份，城市生成便利店编号失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("通过省份，城市生成便利店编号出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 为便利店添加系统用户
     *
     * @param shopInfo 便利店信息
     * @throws ServiceException
     */
    private void addShopStaff(ShopInfo shopInfo) throws ServiceException {
        /*为便利店添加用户*/
        ShopStaff shopStaff = new ShopStaff();
        shopStaff.setShopId(shopInfo.getDmId());
        shopStaff.setName(shopInfo.getName());
        //密码为身份证后6位
        String lpIdCard = shopInfo.getLpIdCard();
        String pwd = lpIdCard.substring((lpIdCard.length() - 6));
        shopStaff.setPwd(MD5Util.getMD5(pwd));
        shopStaff.setPhone(shopInfo.getMobile());
        shopStaff.setStatus(0);
        shopStaffService.addVo(shopStaff);
    }

	@Override
	public void updateStatus(ShopInfo shopInfo, User user) throws ServiceException {
		try {
			dao.modVoNotNull(shopInfo);
	        ShopInfoOperationHistory shopInfoOperationHistory = new ShopInfoOperationHistory();
	        StringBuffer content = new StringBuffer("便利店状态由");
	        if(shopInfo.getDisabled().equals(0)){
	        	content.append(" \"禁用\" 改为 \"启用\"");
	        }else{
	        	content.append(" \"启用\" 改为 \"禁用\"");
	        }
	        shopInfoOperationHistory.setOperator(user.getDmId());
	        shopInfoOperationHistory.setShopId(shopInfo.getDmId());//便利店ID
			shopInfoOperationHistory.setIp(user.getIp());
			shopInfoOperationHistory.setContent(content.toString());
			shopInfoOperationHistory.setOperateOn(DateUtil.dateToSecond(new Date()));
			//添加操作记录
	        shopInfoOperationHistoryService.addVo(shopInfoOperationHistory);
		} catch (DaoException e) {
			 throw new ServiceException("修改便利店状态失败");
		}
		
	}
}