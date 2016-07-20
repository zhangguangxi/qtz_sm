package com.qtz.sm.goods.service.impl;

import com.qtz.sm.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.qtz.sm.goods.service.GdGoodsTypePropertyOptionService;
import com.qtz.sm.goods.vo.GdGoodsTypePropertyOption;

public class GdGoodsTypePropertyOptionServiceImplTest extends BaseTest {
	
	@Autowired
	private GdGoodsTypePropertyOptionService service ;
	
	private static LogTool log = LogTool.getInstance(GdGoodsTypePropertyOptionServiceImplTest.class);

	@Test
	public void testGetPropOptionList() {
		try {
			GdGoodsTypePropertyOption opt = service.findVo(1718865781983237L, null);
			opt.setVal("中国*****新信息");
			service.modVoNotNull(opt);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
