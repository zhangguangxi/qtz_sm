package com.qtz.sm.controller.supermarket;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mall.core.common.StringUtil;
import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespJsonPHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.DaoException;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.mall.core.log.LogTool;
import com.mall.core.vo.Pager;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.supermarket.page.SupermarketCategoryPage;
import com.qtz.sm.supermarket.service.SupermarketCategoryAssociateService;
import com.qtz.sm.supermarket.service.SupermarketCategoryService;
import com.qtz.sm.supermarket.vo.SupermarketCategory;
import com.qtz.sm.supermarket.vo.SupermarketCategoryAssociate;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import utils.StringUtils;

/**
 * <p>Title:com.qtz.sm.supermarket.controller.CategoryController</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 *
 * @author 孙选 - Laven
 * @version v1.0 2016/4/29
 */
@Api(value = "/supermarket/category", description = "超市分类管理")
@Controller("SupermarketCategoryController")
@RequestMapping("/supermarket/category")
public class CategoryController extends BaseController {

    private static LogTool log = LogTool.getInstance(CategoryController.class);

    @Resource(name = "supermarketCategoryServiceImpl")
    private SupermarketCategoryService supermarketCategoryService;

    @Resource(name = "supermarketCategoryAssociateServiceImpl")
    private SupermarketCategoryAssociateService supermarketCategoryAssociateService;

    /**
     * 查询一/二级超市分类基本信息
     * @Description:TODO
     * @param token
     * @param supermarketCategoryId
     * @param typeCode
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午5:41:21
     */
    @ApiOperation(value = "一/二超市分类基本信息",
            notes = "一/二超市分类基本信息",
            position = 0)
    @RequestMapping(value = "/getSupermarketCategoryInfo", method = RequestMethod.GET)
    public void getSupermarketCategoryInfo(@RequestHeader("token") String token,
                          @ApiParam(value = "超市分类ID",required=true) @RequestParam Long supermarketCategoryId,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);
            SupermarketCategory supermarketCategory = supermarketCategoryService.findById(supermarketCategoryId);
            if (null == supermarketCategory) {
                RespHandler.respError(RespMsg.not_found, response);
            } else {
                RespHandler.respOK(supermarketCategory, response);
            }
        } catch (ServiceException se) {
            log.error("查询一/二超市分类基本信息出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询一/二超市分类基本信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:查询一页超市一级分类数据
     * @param token         token
     * @param supermarketId 超市ID
     * @param pageNum       页面游标
     * @param pageSize      页面大小
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:36:44
     */
    @ApiOperation(value = "查询一页超市一级分类数据",
            notes = "查询一页超市一级分类数据",
            position = 1)
    @RequestMapping(value = "/list/first", method = RequestMethod.GET)
    public void queryFirstCategoryPage(@RequestHeader("token") String token,
                                       @ApiParam(value = "超市ID", required = true) @RequestParam("supermarketId") Long supermarketId,
                                       @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                       @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //分页查询
            Pager<SupermarketCategory, Long> pager = querPage(supermarketId, 0L, pageNum, pageSize);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询超市一级分类列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市一级分类列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:查询一页超市二级分类数据
      * @param token         token
     * @param supermarketId 超市ID
     * @param pageNum       页面游标
     * @param pageSize      页面大小
     * @param pid           一级ID
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:36:59
     */
    @ApiOperation(value = "查询一页超市二级分类数据",
            notes = "查询一页超市二级分类数据",
            position = 2)
    @RequestMapping(value = "/list/second", method = RequestMethod.GET)
    public void querySecondCategoryPage(@RequestHeader("token") String token,
                                        @ApiParam(value = "超市ID", required = true) @RequestParam("supermarketId") Long supermarketId,
                                        @ApiParam(value = "一级类目ID", required = true) @RequestParam("pid") Long pid,
                                        @ApiParam(value = "页面游标") @RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                                        @ApiParam(value = "页面大小") @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //分页查询
            Pager<SupermarketCategory, Long> pager = querPage(supermarketId, pid, pageNum, pageSize);
            RespJsonPHandler.respOK(pager, response, request);
        } catch (ServiceException se) {
            log.error("查询超市二级分类列表出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("查询超市二级分类列表出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:添加一级超市分类
     * @param token
     * @param supermarketCategory	超市分类对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:37:52
     */
    @ApiOperation(value = "添加一级超市分类",
            notes = "添加一级超市分类",
            position = 3)
    @RequestMapping(value = "/addFirst", method = RequestMethod.POST)
    public void addFirst(@RequestHeader("token") String token,
                    @ApiParam(value = "一级超市分类信息对象", required = true) @RequestBody SupermarketCategory supermarketCategory,
                    HttpServletRequest request,
                    HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            //参数验证
            checkVo(supermarketCategory);

            //信息填充
            Date date = new Date();
            supermarketCategory.setCreateTime(date);
            supermarketCategory.setUpdateTime(date);
            
            SupermarketCategory queryVo = new SupermarketCategory();
            queryVo.setLevel(1);
            queryVo.setSupermarketId(supermarketCategory.getSupermarketId());
            queryVo.setName(supermarketCategory.getName());
            List<SupermarketCategory> list = supermarketCategoryService.findList(queryVo);//名称不能相同
            if(null != list && list.size() >0 ){
            	RespHandler.respError(RespMsg.supermarket_category_name_have, response);
            }else{
            	//添加
                supermarketCategoryService.addVo(supermarketCategory);
                RespHandler.respOK(response);
            }
        } catch (ServiceException se) {
            log.error("添加一级超市分类出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("添加一级超市分类出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }
    /**
     * 
     * @Description:添加二级超市分类
     * @param token
     * @param supermarketCategory	超市分类对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:38:04
     */
    @ApiOperation(value = "添加二级超市分类",
    		notes = "添加二级超市分类",
    		position = 3)
    @RequestMapping(value = "/addSecond", method = RequestMethod.POST)
    public void addSecond(@RequestHeader("token") String token,
    		@ApiParam(value = "二级超市分类信息对象", required = true) @RequestBody SupermarketCategory supermarketCategory,
    		HttpServletRequest request,
    		HttpServletResponse response) {
    	try {
    		//登陆检查
    		checkLogin(token, response);
    		
    		//参数验证
    		checkVo(supermarketCategory);
    		
    		//信息填充
    		Date date = new Date();
    		supermarketCategory.setCreateTime(date);
    		supermarketCategory.setUpdateTime(date);
    		SupermarketCategory queryVo = new SupermarketCategory();
    		queryVo.setLevel(2);
            queryVo.setSupermarketId(supermarketCategory.getSupermarketId());
            queryVo.setName(supermarketCategory.getName());
            List<SupermarketCategory> list = supermarketCategoryService.findList(queryVo);//名称不能相同
            if(null != list && list.size() >0 ){
             	RespHandler.respError(RespMsg.supermarket_category_name_have, response);
            }else{
            	 supermarketCategoryService.addInfo(supermarketCategory);
            	 RespHandler.respOK(response);
            }
    	} catch (ServiceException se) {
    		RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
    	} catch (Exception e) {
    		log.error("添加二级超市分类出现系统错误！", e);
    		RespJsonPHandler.respServerError(response, request);
    	}
    }

    /**
     * 
     * @Description:修改超市分类信息
     * @param token               token
     * @param typeCode            分类级别 1级，2级
     * @param supermarketCategory 超市分类信息对象
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:38:17
     */
    @ApiOperation(value = "修改超市分类信息",
            notes = "超市分类信息对象",
            position = 4)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestHeader("token") String token,
    		           @ApiParam(value = "分类级别1或2级", required = true) @RequestParam("typeCode") Integer typeCode,
                       @ApiParam(value = "超市分类信息对象", required = true) @RequestBody SupermarketCategory supermarketCategory,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆验证
            checkLogin(token, response);

            //参数验证
            checkModVo(supermarketCategory);
            supermarketCategory.setUpdateTime(new Date());

            SupermarketCategory queryVo = new SupermarketCategory();
            queryVo.setLevel(typeCode);
            queryVo.setSupermarketId(supermarketCategory.getSupermarketId());
            queryVo.setName(supermarketCategory.getName());
            List<SupermarketCategory> list = supermarketCategoryService.findList(queryVo);
            //不属于同一个ID的名称已经存在
            if(null != list && list.size() >0 && !supermarketCategory.getDmId().equals(list.get(0).getDmId())){
            	RespHandler.respError(RespMsg.supermarket_category_name_have, response);
            }else{
	            //如果是修改2级分类 并且 要修改关联商品分类
	            if(typeCode==2 
	            		&& supermarketCategory!=null 
	    				&& supermarketCategory.getSupermarketCategoryAssociateList() !=null
	    				&& supermarketCategory.getSupermarketCategoryAssociateList().size() > 0){
	    			//把商品123级分类挂到 超市二级分类下
	            	supermarketCategoryService.updateInfo(supermarketCategory);
	            	RespHandler.respOK(response);
	    		}else{
	    			//修改数据
	                supermarketCategoryService.modVoNotNull(supermarketCategory);
	                RespHandler.respOK(response);
	    		}
            }
        } catch (ServiceException se) {
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改超市分类信息出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

    /**
     * 
     * @Description:修改超市分类状态 0启用，1禁用
     * @param token          token
     * @param shopCategoryId 超市分类ID
     * @param status         超市分类状态
     * @param request
     * @param response
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:40:32
     */
    @ApiOperation(value = "修超市分类状态",
            notes = "修改超市分类状态，便利店分类状态：0启用，1禁用",
            position = 4)
    @RequestMapping(value = "/updateStatus", method = RequestMethod.GET)
    public void updateStatus(@RequestHeader("token") String token,
                       @ApiParam(value ="超市分类ID", required = true) @RequestParam Long shopCategoryId,
                       @ApiParam(value = "需要修改的分类状态", required = true) @RequestParam Integer status,
                       HttpServletRequest request,
                       HttpServletResponse response) {
        try {
            //登陆检查
            checkLogin(token, response);

            SupermarketCategory supermarketCategory = new SupermarketCategory();
            supermarketCategory.setDmId(shopCategoryId);
            supermarketCategory.setStatus(status);
            supermarketCategory.setUpdateTime(new Date());

            SupermarketCategory query = supermarketCategoryService.findVo(shopCategoryId, null);
            if(!StringUtil.isEmpty(query)){
            	supermarketCategoryService.modVoNotNull(supermarketCategory);
                RespJsonPHandler.respOK(response, request);
            }else{
            	RespHandler.respError(RespMsg.not_found, response);
            }
            
        } catch (ServiceException se) {
            log.error("修改超市分类状态出错！", se);
            RespJsonPHandler.respError(se.getErrorType(), se.getErrorMessage(), response, request);
        } catch (Exception e) {
            log.error("修改超市分类状态出现系统错误！", e);
            RespJsonPHandler.respServerError(response, request);
        }
    }

//    /**
//     * 超市叶子分类关联后台叶子分类
//     *
//     * @param token                 token
//     * @param supermarketCategoryId 超市分类ID
//     * @param categoryIds           后台分类列表
//     * @param request               request
//     * @param response              response
//     */
//    @ApiOperation(value = "超市叶子分类关联后台叶子分类", notes = "超市叶子分类关联后台叶子分类", position = 5)
//    @RequestMapping(value = "/associate/{supermarketCategoryId}", method = RequestMethod.POST)
//    public void associate(@RequestHeader("token") String token,
//                          @ApiParam("超市分类ID") @PathVariable("supermarketCategoryId") Long supermarketCategoryId,
//                          @ApiParam(value = "后台分类列表", required = true) List<Long> categoryIds,
//                          HttpServletRequest request,
//                          HttpServletResponse response) {
//        try {
//            //TODO 数据验证
//            supermarketCategoryAssociateService.addCategoryAssociate(supermarketCategoryId, categoryIds);
//            RespHandler.respOK(response);
//        } catch (ServiceException se) {
//            log.error("超市叶子分类关联后台叶子分类失败！", se);
//            //TODO 错误码尚未提供
//            RespHandler.respError(RespMsg.server_throws_exception, response);
//        } catch (Exception e) {
//            log.error("超市叶子分类关联后台叶子分类出现系统错误！", e);
//            RespHandler.respServerError(response);
//        }
//    }

    /**
     * 
     * @Description:查询一页超市分类数据
     * @param supermarketId 超市ID
     * @param pid           超市分类父节点ID
     * @param pageNum       页面游标
     * @param pageSize      页面大小
     * @return
     * @throws ServiceException
     * @throws DaoException
     * Pager<SupermarketCategory,Long>
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:40:53
     */
    private Pager<SupermarketCategory, Long> querPage(Long supermarketId,
                                                      Long pid,
                                                      Integer pageNum,
                                                      Integer pageSize) throws ServiceException, DaoException {
        //参数填充
        SupermarketCategoryPage supermarketCategoryPage = new SupermarketCategoryPage();
        supermarketCategoryPage.setSupermarketId(supermarketId);
        supermarketCategoryPage.setPid(pid);
        supermarketCategoryPage.setNowPage(pageNum);
        supermarketCategoryPage.setPageSize(pageSize);
        supermarketCategoryPage.setOrderField("create_time");
        supermarketCategoryPage.setOrderDirection(false);
        //分页查询
        Pager<SupermarketCategory, Long> pager = supermarketCategoryService.query(supermarketCategoryPage, SupermarketCategory.class);
        //查询2级超级分类把旗下的商品分类也取出来
        if(0!=pid.intValue() && null != pager && pager.getList()!=null && pager.getList().size()> 0 ){
        	for (int j = 0; j < pager.getList().size(); j++) {
        		List<SupermarketCategoryAssociate> list = supermarketCategoryAssociateService.findBySupermarketCategoryId(pager.getList().get(j).getDmId());
        		StringBuffer name = new StringBuffer("");
        		if(list !=null && list.size()>0){
        			//拼接出商品分类名称组
	        		for (int i = 0; i < list.size(); i++) {
						if(i==0){
							name .append(list.get(i).getGoodsCategoryName());
						}else{
							name .append(">"+list.get(i).getGoodsCategoryName());
						}
					}
        		}
        		pager.getList().get(j).setGoodsCategoryNames(name.toString());
				pager.getList().get(j).setSupermarketCategoryAssociateList(list);
			}
        }
        return pager;
    }

    
    
    /**
     * 
     * @Description:添加超市分类时，数据检查
     * @param supermarketCategory	超市分类对象
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:41:13
     */
    private void checkVo(SupermarketCategory supermarketCategory) throws ServiceException {
    	Long pid = 0L;//默认1级分类是0
        if (null == supermarketCategory.getPid() || pid.equals(supermarketCategory.getPid()))  {
            supermarketCategory.setPid(pid);
            //一级分类
            supermarketCategory.setLevel(1);
        } else {
            //二级分类
            supermarketCategory.setLevel(2);
        }
        if (StringUtils.isEmpty(supermarketCategory.getName())) {
            throw new ServiceException(ExceptionCode.supermarket_category_name_is_null,"超市分类名称为空！");
        }
        if (null == supermarketCategory.getStatus()) {
            supermarketCategory.setStatus(0);
        }
        if (null == supermarketCategory.getSort()) {
            supermarketCategory.setSort(0);
        }
    }

    /**
     * 
     * @Description:修改超市分类时，数据检查
     * @param supermarketCategory
     * @throws ServiceException
     * void
     * @exception:
     * @author: SunXuan
     * @time:2016年5月31日 下午6:41:26
     */
    private void checkModVo(SupermarketCategory supermarketCategory) throws ServiceException {
        if (null == supermarketCategory.getDmId()) {
            throw new ServiceException(ExceptionCode.DMID_IS_NULL,"超市分类ID为空！");
        }
        checkVo(supermarketCategory);
    }
}
