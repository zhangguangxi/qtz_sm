package com.qtz.sm.shopManage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.mall.core.common.CollectionUtil;
import com.mall.core.common.DateUtil;
import com.mall.core.common.ExceptionConstants;
import com.mall.core.common.MD5Util;
import com.mall.core.common.StringUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.mall.core.vo.Pager;
import com.qtz.sm.common.service.CsRegionsService;
import com.qtz.sm.common.vo.CsRegions;
import com.qtz.sm.shopManage.dao.ShopManageDao;
import com.qtz.sm.shopManage.dao.ShopMAndSuperMKGoodsDao;
import com.qtz.sm.shopManage.page.ShopMAndSuperMKGoodsPage;
import com.qtz.sm.shopManage.service.ShopManageService;
import com.qtz.sm.shopManage.service.ShopManageStaffService;
import com.qtz.sm.shopManage.vo.ShopManage;
import com.qtz.sm.shopManage.vo.GoodsSkuVo;
import com.qtz.sm.shopManage.vo.ShopMAndSuperMKGoods;
import com.qtz.sm.shopManage.vo.ShopManageStaff;
import com.qtz.sm.shopManage.vo.ShopManageVo;
import com.qtz.sm.wallet.service.WtWalletService;

/**
 * <p>Title:ShopManageServiceImpl</p>
 * <p>Description:便利店管理公司服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 孙选
 * @version v1.0 2016-05-26
 */
@Service("shopManageServiceImpl")
public class ShopManageServiceImpl extends BaseServiceImpl<com.qtz.sm.shopManage.vo.ShopManage, Long> implements ShopManageService {
    /**
     * 初始化日志对象
     */
    private static LogTool log = LogTool.getInstance(ShopManageServiceImpl.class);
    /**
     * 注入便利店管理公司DAO接口类
     */
    @Resource(name = "shopManageDaoImpl")
    private ShopManageDao dao;

    @Resource(name = "shopManageStaffServiceImpl")
    private ShopManageStaffService shopManageStaffService;

    @Resource(name = "csRegionsServiceImpl")
    private CsRegionsService csRegionsService;

    /**
     * 钱包服务
     */
    @Resource(name = "wtWalletServiceImpl")
    private WtWalletService wtWalletService;
    
    @Resource(name = "shopMAndSuperMKGoodsDaoImpl")
    private ShopMAndSuperMKGoodsDao shopMAndSuperMKGoodsDao;

    /**
     * 【取得】业务DAO对象
     *
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<com.qtz.sm.shopManage.vo.ShopManage, Long> getDao() {
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
    public ShopManageVo findById(Long id) throws ServiceException {
        ShopManage shopManage = this.findVo(id, null);
        ShopManageVo shopManageVo = new ShopManageVo();
        BeanUtils.copyProperties(shopManage, shopManageVo);
        //从mongodb中取出省市区名称
        //省、市、区、镇
        Map<String, CsRegions> csRegionsMap = csRegionsService.getAddressByIds(shopManage.getProvinceId(),
                shopManage.getCityId(),
                shopManage.getAreaId(),
                shopManage.getTownId());
        shopManageVo.setProvinceName(csRegionsMap.get("province")!=null?csRegionsMap.get("province").getName():"");
        shopManageVo.setCityName(csRegionsMap.get("city")!=null?csRegionsMap.get("city").getName():"");
        shopManageVo.setAreaName(csRegionsMap.get("area")!=null?csRegionsMap.get("area").getName():"");
        shopManageVo.setTownName(csRegionsMap.get("town")!=null?csRegionsMap.get("town").getName():"");
        return shopManageVo;
    }

    @Override
    public void addVoStaffAddWallet(ShopManage shopManage) throws ServiceException {
        try {
            /*1、查询当前便利店管理公司手机号码在系统中是否存在，不存在则可用*/
            ShopManage queryParam = new ShopManage();
            queryParam.setMobile(shopManage.getMobile());
            List<ShopManage> shopManageList = dao.findList(queryParam);
            if (shopManageList.size() > 0) {
                throw new ServiceException("便利店管理公司手机号码已经存在！");
            }

            /*2、如果手机号可用，则新建便利店管理公司*/
            shopManage = this.addVo(shopManage);

            /*3、为便利店管理公司添加用户*/
            addStaff(shopManage);

            /*4、为便利店管理公司创建钱包*/
            wtWalletService.addBldglWallet(shopManage.getDmId(), shopManage.getName());
        } catch (DaoException de) {
            log.error("添加便利店管理公司失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("添加便利店管理公司出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void modVoAndStaff(ShopManage shopManage) throws ServiceException {
        try {
            ShopManage temp = dao.findVo(shopManage.getDmId(), null);
            if(StringUtil.isEmpty(temp)){
            	throw new ServiceException("便利店管理公司信息不存在.");
            }
            ShopManageStaff shopManageStaff = new ShopManageStaff();
            shopManageStaff.setShopManageId(temp.getDmId());
            shopManageStaff.setPhone(temp.getMobile());
            List<ShopManageStaff> shopManageStaffList = shopManageStaffService.findList(shopManageStaff);
            //当手机号码对应的用户不存在，则为它新建用户
            if (null == shopManageStaffList || shopManageStaffList.size() == 0) {
                addStaff(shopManage);
            } else {
                //手机号码有变更
                if (!temp.getMobile().equals(shopManage.getMobile())) {
                    shopManageStaff = shopManageStaffList.get(0);
                    //修改手机号码
                    shopManageStaff.setPhone(shopManage.getMobile());
                    shopManageStaffService.modVoNotNull(shopManageStaff);
                }
            }
            //修改便利店信息
            dao.modVoNotNull(shopManage);
            
            //判断是否更换名称
	        if(!temp.getName().equals(shopManage.getName())){
	        	Map<String, Object> map = new HashMap<>();
	        	map.put("ownerId", shopManage.getDmId());
	        	map.put("ownerName", shopManage.getName());
	        	wtWalletService.updateOwnerName(map);
	        }
        } catch (DaoException de) {
            log.error("修改便利店管理公司信息失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("修改便利店管理公司信息出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
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
            log.error("通过省份，城市生成便利店管理公司编号失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("通过省份，城市生成便利店管理公司编号出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 为便利店管理公司添加系统用户
     *
     * @param shopManage 便利店管理公司信息对象
     * @throws ServiceException
     */
    private void addStaff(ShopManage shopManage) throws ServiceException {
        ShopManageStaff shopManageStaff = new ShopManageStaff();
        shopManageStaff.setShopManageId(shopManage.getDmId());
        shopManageStaff.setName(shopManage.getName());
        //密码为身份证后6位
        String lpIdCard = shopManage.getLpIdCard();
        String pwd = lpIdCard.substring((lpIdCard.length()-6));
        shopManageStaff.setPwd(MD5Util.getMD5(pwd));
        shopManageStaff.setPhone(shopManage.getMobile());
        shopManageStaff.setStatus(0);
        shopManageStaffService.addVo(shopManageStaff);
    }

	@Override
	public Pager<ShopMAndSuperMKGoods, Long> queryGoodsOld(ShopMAndSuperMKGoodsPage shopManageGoodsGoodsPage) throws ServiceException {
		Pager<ShopMAndSuperMKGoods, Long> pager = new Pager<ShopMAndSuperMKGoods, Long>();
		try {
			pager.setNowPage(shopManageGoodsGoodsPage.getNowPage());
			pager.setPageSize(shopManageGoodsGoodsPage.getPageSize());
			pager.setOrderField(shopManageGoodsGoodsPage.getOrderField());
			pager.setOrderDirection(false);
			
			List<Long> listGoodsId = null;
			Map<Long, List<GoodsSkuVo>> map = new HashMap<Long, List<GoodsSkuVo>>();
			//查询条件是skuID或者 库存的时候 需要先找出所有的商品ID进行过滤 然后分页查询商品
			ShopMAndSuperMKGoods shopManageGoods = new ShopMAndSuperMKGoods();
			BeanUtils.copyProperties(shopManageGoodsGoodsPage, shopManageGoods);
			//时间转换
			if(!StringUtil.isEmpty(shopManageGoods.getOnsaleDateStart())){
				shopManageGoods.setOnsaleTimeStart(DateUtil.getStrDataTimes(shopManageGoods.getOnsaleDateStart()));
			}
			if(!StringUtil.isEmpty(shopManageGoods.getOnsaleDateEnd())){
				shopManageGoods.setOnsaleTimeEnd(DateUtil.getStrDataTimes(shopManageGoods.getOnsaleDateEnd()));
			}
			//根据条件 获取所有数据
			List<ShopMAndSuperMKGoods> list = shopMAndSuperMKGoodsDao.findList(shopManageGoods);
			if(null == list || list.size() ==0){
				pager.setRowCount(0);
				pager.setList(new ArrayList<ShopMAndSuperMKGoods>());
				return pager;
			}else{
				listGoodsId = new ArrayList<Long>();
				for (int i = 0; i < list.size(); i++) {
					listGoodsId.add(list.get(i).getGoodsId());
					map.put(list.get(i).getGoodsId(), list.get(i).getGoodsSkuVos());
				}
				shopManageGoodsGoodsPage.setGoodsIds(listGoodsId);
			}
			Pager<ShopMAndSuperMKGoods, Long> page =  shopMAndSuperMKGoodsDao.query(shopManageGoodsGoodsPage, ShopMAndSuperMKGoods.class);
			shopManageGoodsGoodsPage.setGoodsIds(null);//清空
			//将对应的商品sku 放入对应的商品中
			if(page!=null && page.getList() !=null && page.getList().size()>0){
				for (int i = 0; i < page.getList().size(); i++) {
					page.getList().get(i).setGoodsSkuVos(map.get(page.getList().get(i).getGoodsId()));
				}
			}
			return page;
		} catch (Exception e) {
			log.error("便利店公司查询商品库列表出现系统错误！", e);
			 throw new ServiceException(e.getMessage());
		}
	}
	
	@Override
	public Pager<ShopMAndSuperMKGoods, Long> queryGoods(ShopMAndSuperMKGoodsPage shopManageGoodsGoodsPage) throws ServiceException {
		Pager<ShopMAndSuperMKGoods, Long> pager = new Pager<ShopMAndSuperMKGoods, Long>();
		try {
			pager.setNowPage(shopManageGoodsGoodsPage.getNowPage());
			pager.setPageSize(shopManageGoodsGoodsPage.getPageSize());
			pager.setOrderField(shopManageGoodsGoodsPage.getOrderField());
			pager.setOrderDirection(false);
			
			Map<Long, List<GoodsSkuVo>> map = new HashMap<Long, List<GoodsSkuVo>>();
			//查询条件是skuID或者 库存的时候 需要先找出所有的商品ID进行过滤 然后分页查询商品
			ShopMAndSuperMKGoods shopManageGoods = new ShopMAndSuperMKGoods();
			BeanUtils.copyProperties(shopManageGoodsGoodsPage, shopManageGoods);
			//时间转换
			if(!StringUtil.isEmpty(shopManageGoods.getOnsaleDateStart())){
				shopManageGoods.setOnsaleTimeStart(DateUtil.getStrDataTimes(shopManageGoods.getOnsaleDateStart()));
			}
			if(!StringUtil.isEmpty(shopManageGoods.getOnsaleDateEnd())){
				shopManageGoods.setOnsaleTimeEnd(DateUtil.getStrDataTimes(shopManageGoods.getOnsaleDateEnd()));
			}
			//根据条件取出所有
			List<ShopMAndSuperMKGoods> list = shopMAndSuperMKGoodsDao.findList(shopManageGoods);
			Integer rowCount = 0;
			if(null == list || list.size() ==0){
				pager.setRowCount(rowCount);
				pager.setList(new ArrayList<ShopMAndSuperMKGoods>());
				return pager;
			}else{
				rowCount = list.size();//总长度
				//分页拿数据
				list = CollectionUtil.trunList(list, shopManageGoodsGoodsPage.getNowPage(), shopManageGoodsGoodsPage.getPageSize());
			}
			pager.setRowCount(rowCount);
			pager.setList(list);
			return pager;
		} catch (Exception e) {
			log.error("便利店公司查询商品库列表出现系统错误！", e);
			throw new ServiceException(e.getMessage());
		}
	}
	

	@Override
	public List<ShopMAndSuperMKGoods> getShopManageGoodsList(ShopMAndSuperMKGoods shopMAndSuperMKGoods) throws ServiceException {
		try {
			return shopMAndSuperMKGoodsDao.findList(shopMAndSuperMKGoods);
		} catch (DaoException e) {
			 log.error("便利店公司查询商品库列表出现系统错误！", e);
			 throw new ServiceException(e.getMessage());
		}
	}
}