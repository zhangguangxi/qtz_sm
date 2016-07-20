package com.qtz.sm.supp.dao.impl;

import java.util.List;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.DaoException;
import com.mall.core.log.LogTool;
import com.qtz.sm.supp.dao.CsGysStockDao;
import com.qtz.sm.supp.vo.CsGysStock;
/**
 * 欧江波 928482427@qq.com
 * @author Administrator
 *
 */
public class CsGysStockDaoImplTest extends BaseTest {
	
	private static LogTool log = LogTool.getInstance(CsGysStockDaoImplTest.class);

	@Autowired
	private CsGysStockDao stockDao;
	
	@Test
	public void testGetStocksByGoodsId() {
		Long goodsId = 1L;
		try {
			List<CsGysStock> list = stockDao.getStocksByGoodsId(goodsId);
			log.info("============================================");
			log.info(list);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

}
