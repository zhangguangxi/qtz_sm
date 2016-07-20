/*
 * 未经本公司正式书面同意，其他任何个人、团体不得使用、复制、修改或发布本软件.
 */
package com.qtz.sm.solr;

import javax.annotation.Resource;

import com.qtz.sm.BaseTest;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.common.solr.SearchShopSolrService;
import com.qtz.sm.search.page.SearchPage;

/**
 * @version 2016年6月27日上午11:21:11
 * @author guangxi.zhang 张光喜 510647180@qq.com
 */
public class SearchShopSolrServiceImplTest extends BaseTest {
    
    @Resource(name = "searchShopSolrServiceImpl")
    private SearchShopSolrService SearchShopSolrService;
    
    @Test
    public void testSearchCCZXInfo(){
        SearchPage page = new SearchPage();
        page.setLatitude(12.0);
        page.setLongitude(12.0);
        Object obj;
		try {
			 obj = SearchShopSolrService.searchCCZXInfo(page);
			System.out.println("data==>"+JSONObject.toJSONString(obj));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
    }
}
