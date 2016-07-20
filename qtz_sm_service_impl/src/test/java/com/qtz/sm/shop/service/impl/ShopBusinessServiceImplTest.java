package com.qtz.sm.shop.service.impl;

import com.mall.core.log.LogTool;
import com.qtz.sm.BaseTest;
import com.qtz.sm.shop.service.ShopBusinessService;
import com.qtz.sm.shop.vo.ShopBusiness;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * <p>Title:com.qtz.sm.shop.service.impl.ShopBusinessServiceImplTest</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 刘晓峰 - Laven.liu
 * @version v1.0 2016/4/26
 */
public class ShopBusinessServiceImplTest extends BaseTest {

    private static LogTool log = LogTool.getInstance(ShopBusinessServiceImplTest.class);

    @Autowired
    private ShopBusinessService shopBusinessService;

    @Test
    public void testAddVo() throws Exception{
        ShopBusiness shopBusiness = new ShopBusiness();
        shopBusiness.setShopId(1L);
        shopBusiness.setCreateTime(new Date());
        shopBusiness.setUpdateTime(new Date());

        shopBusinessService.addVo(shopBusiness);
    }
}