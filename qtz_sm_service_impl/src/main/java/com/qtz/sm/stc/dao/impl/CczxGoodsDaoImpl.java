package com.qtz.sm.stc.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mall.core.common.Global;
import com.mall.core.dao.impl.MyBaitsDaoImpl;
import com.mall.core.exception.DaoException;
import com.mall.core.vo.Pager;
import com.qtz.sm.stc.dao.CczxGoodsDao;
import com.qtz.sm.stc.vo.CczxGoods;

@Repository("cczxGoodsDaoImpl")
public class CczxGoodsDaoImpl extends MyBaitsDaoImpl<CczxGoods,Long> implements CczxGoodsDao{
	
	/**MYBatis命名空间名*/
    private static String preName = CczxGoodsDao.class.getName();

	@Override
	protected String getPreName() {
		 return preName;
	}

	/** 
	* 【分页查询】
	* @param 	page			分页对象
	* @return	Pager			分页对象
	* @throws 	DaoException  	DAO异常对象
	*/
	public  Pager<CczxGoods, Long>  queryCczxGoodsPage(Pager<CczxGoods, Long> page) throws DaoException {
		if(null == page) throw new DaoException(this.getClass().getName() + ".queryCczxGoodsPage(" + page + ")传入的参数不能为空！");
		try {
			String sqlidOne = "queryGoodsCczxCount";
			String sqlidTwo ="queryGoodsCczxList";
			if (null != preName && !preName.equals("")) {
				sqlidOne = preName + Global.SPLIT_DIAN + sqlidOne;
			}
			if (null != preName && !preName.equals("")) {
				sqlidTwo = preName + Global.SPLIT_DIAN + sqlidTwo;
			}
			Integer rowCount = getMyBaitsTemplate().getSqlSession().selectOne(sqlidOne, page);
			page.setRowCount(rowCount);
			List<CczxGoods> list = getMyBaitsTemplate().getSqlSession().selectList(sqlidTwo, page);
			page.setList(list);
		} catch (Exception e) {
			throw new DaoException(e);
		}
		return page;
	}
}
