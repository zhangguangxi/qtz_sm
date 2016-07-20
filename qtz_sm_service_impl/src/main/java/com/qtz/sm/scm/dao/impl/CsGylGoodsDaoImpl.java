package com.qtz.sm.scm.dao.impl;

import org.springframework.stereotype.Repository;

import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.qtz.sm.scm.dao.CsGylGoodsDao;
import com.qtz.sm.scm.vo.CsGylGoodsVo;

/**
 * <!-- 供应链公司   商品管理   商品审核分页以及商品库分页      分页 -->
 * @author Administrator
 *
 */
@Repository("csGylGoodsDaoImpl")
public class CsGylGoodsDaoImpl extends MyBaitsDaoImpl<CsGylGoodsVo,Long> implements CsGylGoodsDao{

	/**MYBatis命名空间名*/
    private static String preName = CsGylGoodsDao.class.getName();

	@Override
	protected String getPreName() {
		 return preName;
	}




}
