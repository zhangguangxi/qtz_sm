package com.qtz.sm.supermarket.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.qtz.sm.supermarket.vo.SupermarketCategory;
import com.qtz.sm.supermarket.vo.SupermarketCategoryVo;
import com.qtz.sm.supermarket.vo.SupermarketGoodsVo;

/**
 * <p>Title:SupermarketCategoryService</p>
 * <p>Description:超市类目服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-29
 */
public interface SupermarketCategoryService extends BaseService<com.qtz.sm.supermarket.vo.SupermarketCategory, Long> {

    /**
     * 通过超市分类ID查找分类详细信息，包括超市分类所关联的后台分类信息
     *
     * @param id 超市分类ID
     * @return 超市分类详细信息对象
     * @throws ServiceException
     */
    SupermarketCategory findById(Long id) throws ServiceException;
    
    /**
     * 
     * @Description:添加二级分类 并判断关联的商品是否已经存在
     * @param supermarketCategory
     * @return
     * @throws ServiceException
     * String
     * @exception:
     * @author: SunXuan
     * @time:2016年6月2日 下午2:17:46
     */
    void addInfo(SupermarketCategory supermarketCategory) throws ServiceException;
    /**
     * 
     * @Description:修改二级分类 并判断关联的商品是否已经存在
     * @param supermarketCategory
     * @return
     * @throws ServiceException
     * String
     * @exception:
     * @author: SunXuan
     * @time:2016年6月2日 下午2:17:46
     */
    void updateInfo(SupermarketCategory supermarketCategory) throws ServiceException;

    /**
     * 
     * @Description:获取超市首页分类以及商品信息
     * @param Double latitude 经度
     * @param Double longitude 纬度
     * @return
     * @throws ServiceException
     * String
     * @exception:
     * @author: yangwei
     * @time:2016年6月6日
     */
	List<SupermarketCategoryVo> getAll(Double latitude,Double longitude) throws ServiceException;

	 /**
     * 
     * @Description:获取超市商品详情信息
     * @param cczxId
     * @param goodsId
     * @return
     * @throws ServiceException
     * @exception:
     * @author: yangwei
     * @time:2016年6月6日 
     */
	SupermarketGoodsVo getGoodsDetail(Long cczxId, Long goodsId) throws ServiceException;

	/**
     * 
     * @Description:获取超市商品规格信息
     * @param cczxId
     * @param goodsId
     * @return
     * @throws ServiceException
     * @exception:
     * @author: yangwei
     * @time:2016年6月22日 
     */
	SupermarketGoodsVo getGoodsSpecifications(Long cczxId, Long goodsId) throws ServiceException;

	/**
     * 
     * @Description:获取可能喜欢的超市商品信息
     * @param cczxId
     * @throws ServiceException
     * @exception:
     * @author: yangwei
     * @time:2016年6月28日 
     */
	List<SupermarketGoodsVo> getEnjoyGoodsList(Long cczxId) throws ServiceException;

}