package com.qtz.sm.stc.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.stc.dao.CsCczxGoodsQuantityDao;
import com.qtz.sm.stc.vo.CsCczxGoodsQuantity;
@Repository("csCczxGoodsQuantityDaoImpl")
public class CsCczxGoodsQuantityDaoImpl extends MyBaitsDaoImpl<CsCczxGoodsQuantity,Long> implements CsCczxGoodsQuantityDao{

	/**MYBatis命名空间名*/
    private static String preName = CsCczxGoodsQuantityDao.class.getName();

	@Override
	protected String getPreName() {
		 return preName;
	}
	

}
