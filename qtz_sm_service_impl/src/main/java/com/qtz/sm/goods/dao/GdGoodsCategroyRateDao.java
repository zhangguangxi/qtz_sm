package com.qtz.sm.goods.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mall.core.dao.BizDao;
import com.mall.core.exception.DaoException;
import com.qtz.sm.goods.vo.GdGoodsCategroyRate;
/**
 * <p>Title:GdGoodsCategroyRateDao</p>
 * <p>Description:商品SKU溢价率DAO接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 谭林清 - tanlinqingaction@126.com
 * @version v1.0 2016-04-18
 */
public interface GdGoodsCategroyRateDao extends BizDao<GdGoodsCategroyRate,java.lang.Long> {
	
	/**
	 *  获取parentGoodsTypeId下直接分类议价列表（包含分类名称和父分类ID）
	 * @author	欧江波 928482427@qq.com
	 * @parentGoodsTypeId  null 获取所有分类议价列表
	 * @return
	 * @throws DaoException
	 */
	public List<GdGoodsCategroyRate> getRatesWithCategoryInfo(@Param(value="parentGoodsTypeId")Long parentGoodsTypeId) throws DaoException;
}