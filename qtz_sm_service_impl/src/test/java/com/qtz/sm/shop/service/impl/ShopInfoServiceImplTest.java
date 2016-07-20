package com.qtz.sm.shop.service.impl;

import com.qtz.sm.BaseTest;
import com.qtz.sm.shop.service.ShopInfoService;
import com.qtz.sm.shop.vo.ShopInfo;
import org.junit.Test;

import javax.annotation.Resource;

import java.util.Date;

/**
 * <p>Title:com.qtz.sm.shop.service.impl.ShopInfoServiceImplTest</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 刘晓峰 - Laven.liu
 * @version v1.0 2016/4/28
 */
public class ShopInfoServiceImplTest extends BaseTest {

    @Resource(name = "shopInfoServiceImpl")
    private ShopInfoService shopInfoService;

    @Test
    public void testAddVoStaffAddWallet() throws Exception {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setName("便利店1");
        shopInfo.setMobile("45646");
        shopInfo.setShopManageId(1L);
        shopInfo.setCczxId(1L);
        shopInfo.setLpName("法人");
        shopInfo.setLpIdCard("165465465465");
        shopInfo.setLicence("5546");
        shopInfo.setIdCardBehind("fsdf");
        shopInfo.setIdCardFront("sdfs");
        shopInfo.setCreateTime(new Date());
        shopInfo.setUpdateTime(new Date());

        shopInfoService.addVoStaffAddWallet(shopInfo);
    }
}