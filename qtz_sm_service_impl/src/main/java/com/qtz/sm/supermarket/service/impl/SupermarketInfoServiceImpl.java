package com.qtz.sm.supermarket.service.impl;

import com.mall.core.common.MD5Util;
import com.mall.core.common.StringUtil;
import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.service.impl.BaseServiceImpl;
import com.qtz.sm.supermarket.dao.SupermarketInfoDao;
import com.qtz.sm.supermarket.service.SupermarketInfoService;
import com.qtz.sm.supermarket.service.SupermarketStaffService;
import com.qtz.sm.supermarket.vo.SupermarketInfo;
import com.qtz.sm.supermarket.vo.SupermarketStaff;
import com.qtz.sm.wallet.service.WtWalletService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:SupermarketInfoServiceImpl</p>
 * <p>Description:超市基本信息服务实现类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-26
 */
@Service("supermarketInfoServiceImpl")
public class SupermarketInfoServiceImpl extends BaseServiceImpl<com.qtz.sm.supermarket.vo.SupermarketInfo, Long> implements SupermarketInfoService {
    /**
     * 初始化日志对象
     */
    private static LogTool log = LogTool.getInstance(SupermarketInfoServiceImpl.class);
    /**
     * 注入超市基本信息DAO接口类
     */
    @Resource(name = "supermarketInfoDaoImpl")
    private SupermarketInfoDao dao;

    @Resource(name = "supermarketStaffServiceImpl")
    private SupermarketStaffService supermarketStaffService;

    /**
     * 钱包服务
     */
    @Resource(name = "wtWalletServiceImpl")
    private WtWalletService wtWalletService;

    /**
     * 【取得】业务DAO对象
     *
     * @return 业务DAO对象
     */
    @Override
    protected BizDao<com.qtz.sm.supermarket.vo.SupermarketInfo, Long> getDao() {
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
    public void addVoStaffAddWallet(SupermarketInfo supermarketInfo) throws ServiceException {
        try {
            /*1、查询当前便利店手机号码在系统中是否存在，不存在则可用*/
            SupermarketInfo queryParam = new SupermarketInfo();
            queryParam.setMobile(supermarketInfo.getMobile());
            List<SupermarketInfo> supermarketInfoList = dao.findList(queryParam);
            if (supermarketInfoList.size() > 0) {
                throw new ServiceException("超市手机号码已经存在！");
            }

			/*2、如果手机号可用，则新建超市*/
            supermarketInfo = this.addVo(supermarketInfo);

			/*3、为超市添加用户*/
            addStaff(supermarketInfo);

			/*4、为超市创建钱包*/
            wtWalletService.addCsWallet(supermarketInfo.getDmId(), supermarketInfo.getName());
        } catch (DaoException de) {
            log.error("添加超市失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("添加超市出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void modVoAndStaff(SupermarketInfo supermarketInfo) throws ServiceException {
        try {
            SupermarketInfo temp = dao.findVo(supermarketInfo.getDmId(), null);
            if(StringUtil.isEmpty(temp)){
            	throw new ServiceException("超市信息不存在.");
            }
            SupermarketStaff supermarketStaff = new SupermarketStaff();
            supermarketStaff.setSupermarketId(temp.getDmId());
            supermarketStaff.setPhone(temp.getMobile());
            List<SupermarketStaff> supermarketStaffList = supermarketStaffService.findList(supermarketStaff);
            //当前超市没有对应手机号码的用户，则新建
            if (null == supermarketStaffList || supermarketStaffList.size() == 0) {
                /*为超市添加用户*/
                addStaff(supermarketInfo);
            } else {
                //手机号码有变更
                if (!temp.getMobile().equals(supermarketInfo.getMobile())) {
                    supermarketStaff = supermarketStaffList.get(0);
                    //修改手机号码
                    supermarketStaff.setPhone(supermarketInfo.getMobile());
                    supermarketStaffService.modVoNotNull(supermarketStaff);
                }
            }
            //修改便利店信息
            dao.modVoNotNull(supermarketInfo);
            //判断是否更换名称
	        if(!temp.getName().equals(supermarketInfo.getName())){
	        	Map<String, Object> map = new HashMap<>();
	        	map.put("ownerId", supermarketInfo.getDmId());
	        	map.put("ownerName", supermarketInfo.getName());
	        	wtWalletService.updateOwnerName(map);
	        }
        } catch (DaoException de) {
            log.error("修改超市失败！", de);
            throw new ServiceException(de.getErrorMessage());
        } catch (Exception e) {
            log.error("修改超市出现系统错误！", e);
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * 为超市添加系统用户
     *
     * @param supermarketInfo 超市信息
     * @throws ServiceException
     */
    private void addStaff(SupermarketInfo supermarketInfo) throws ServiceException {
        /*为超市添加用户*/
        SupermarketStaff supermarketStaff = new SupermarketStaff();
        supermarketStaff.setSupermarketId(supermarketInfo.getDmId());
        supermarketStaff.setName(supermarketInfo.getName());
        //密码为身份证后6位
        String lpIdCard = supermarketInfo.getLpIdCard();
        String pwd = lpIdCard.substring((lpIdCard.length()-6));
        supermarketStaff.setPwd(MD5Util.getMD5(pwd));
        supermarketStaff.setPhone(supermarketInfo.getMobile());
        supermarketStaff.setStatus(0);
        supermarketStaffService.addVo(supermarketStaff);
    }
}