package com.qtz.sm.supermarket.service;

import java.util.List;

import com.mall.core.exception.ServiceException;
import com.mall.core.service.BaseService;
import com.mall.core.vo.Pager;
import com.qtz.sm.supermarket.page.SupermarketCategoryPage;
import com.qtz.sm.supermarket.vo.SupermarketCategory;
import com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate;
import com.qtz.sm.supermarket.vo.SupermarketCategoryGoods;

/**
 * <p>Title:SupermarketCategoryAssociateService</p>
 * <p>Description:超市类目与商品类目关联表服务接口类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳擎天柱信息技术有限公司</p>
 *
 * @author 刘晓峰 - Laven
 * @version v1.0 2016-04-29
 */
public interface SupermarketCategoryAssociateService extends BaseService<com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate, Long> {

    /**
     * 通过超市分类ID查找关联后台分类信息，包括后台分类名称
     *
     * @param supermarketCategoryId 超市分类ID
     * @return 关联后台分类信息列表
     * @throws ServiceException
     */
    List<SupermarketCategoryAssociate> findBySupermarketCategoryId(Long supermarketCategoryId) throws ServiceException;
    
    /**
     * 根据超市ID 查询已经关联的商品分类 
     *
     * @param supermarketId 超市ID
     * @return 关联后台分类信息列表
     * @throws ServiceException
     */
    List<SupermarketCategoryAssociate> getList(SupermarketCategoryAssociate supermarketCategoryAssociate) throws ServiceException;

    /**
     * 超市分类批量关联后台分类
     * 1、超市分类必须是叶子分类
     * 2、后台分类也必须是叶子分类
     * 3、后台叶子分类如果已经被当前超市其他叶子分类关联，则不能再被关联
     *
     * @param supermarketCategoryId 超市分类ID
     * @param categoryIds           后台分类ID列表
     * @throws ServiceException
     */
    void addCategoryAssociate(Long supermarketCategoryId, List<Long> categoryIds) throws ServiceException;
    
  /**
   * 批量添加超市 二级分类 关联商品 分类
   * @Description:TODO
   * @param supermarketCategory
   * @throws ServiceException
   * void
   * @exception:
   * @author: SunXuan
   * @time:2016年5月31日 下午4:01:22
   */
    void addCategoryAssociate(SupermarketCategory supermarketCategory) throws ServiceException;
    
    
    /**
     **根据超市仓储中心id,商品分类id获取相应的商品信息(商品id 商品名称 商品图片(首张上传图片) 商品价格(sku最低价格))
     * @version 2016年6月6日下午3:14:08              
     * @author  guangxi.zhang               
     * @param   supermarketCategoryPage                商品分类查询条件对象
     * @return  Pager<SupermarketCategoryGoods,Long>   商品分类分页查询结果对象 
     * @throws  ServiceException
     */
    Pager<SupermarketCategoryGoods,Long> queryGoodsInfoPageBySupermarketCategoryId(SupermarketCategoryPage supermarketCategoryPage )throws ServiceException;

}