/**
 * SupermarketCategoryServiceImplTest
 * @version 2016年6月6日下午4:38:33
 * @author guangxi.zhang 张光喜 510647180@qq.com
 */
package com.qtz.sm.supp.service.impl;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;
import com.qtz.sm.supermarket.page.SupermarketCategoryPage;
import com.qtz.sm.supermarket.service.SupermarketCategoryAssociateService;
import com.qtz.sm.supermarket.vo.SupermarketCategoryGoods;

public class SupermarketCategoryServiceImplTest extends BaseTest {
    
    private static LogTool log = LogTool.getInstance(SupermarketCategoryServiceImplTest.class);
    @Autowired
    private SupermarketCategoryAssociateService service;
    
    @Test
    public void testQueryGoodsInfoBySupermarketCategoryId() {
        try {
            SupermarketCategoryPage supermarketCategoryPage = new SupermarketCategoryPage();
            supermarketCategoryPage.setDmId(1710375918602240l);
//            supermarketCategoryPage.setPid(firstLevelSupermarketCategoryId);
            supermarketCategoryPage.setLatitude(12.0);
            supermarketCategoryPage.setLongitude(11.0);
            supermarketCategoryPage.setNowPage(1);
            supermarketCategoryPage.setPageSize(10);
            supermarketCategoryPage.setOrderDirection(false);
            supermarketCategoryPage.setCondition(10,1);

            Pager<SupermarketCategoryGoods, Long> pager = service.queryGoodsInfoPageBySupermarketCategoryId(supermarketCategoryPage);
            log.info("========================================");
            log.info("list>>>>>"+JSONObject.toJSONString(pager));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    Pager<SupermarketCategoryGoods,Long> queryGoodsInfoPageBySupermarketCategoryId(SupermarketCategoryPage supermarketCategoryPage )throws ServiceException;
    
    
}
