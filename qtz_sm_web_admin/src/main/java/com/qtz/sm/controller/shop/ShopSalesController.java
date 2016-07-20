package com.qtz.sm.controller.shop;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mall.core.common.response.RespHandler;
import com.mall.core.common.response.RespMsg;
import com.mall.core.exception.ExceptionCode;
import com.mall.core.exception.ServiceException;
import com.qtz.sm.controller.BaseController;
import com.qtz.sm.shop.service.ShopSalesService;
import com.qtz.sm.shop.vo.ShopSales;

@RestController
@RequestMapping(value = "/shop/sales")
public class ShopSalesController extends BaseController {

	@Autowired
	private ShopSalesService shopSalesService;

	/**
	 * 添加店铺销量信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param shopComment
	 *            店铺销量信息对象
	 * @param shopSales
	 * @throws IOException
	 */
	@RequestMapping(value = "addSales", method = RequestMethod.POST)
	public void addSales(@RequestHeader("token") String sid, 
			ShopSales shopSales,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //参数验证
            checkVo(shopSales);
            //信息填充
            Date date = new Date();
            shopSales.setUpdateTime(date);

            //添加
            shopSalesService.addVo(shopSales);
            RespHandler.respOK(response);
        } catch (ServiceException se) {
            log.error("添加店铺销量信息出错！", se);
            RespHandler.respError(RespMsg.addSales_error, response);
        } catch (Exception e) {
            log.error("添加店铺销量信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
	/**
	 * 统计销量信息<br/>
	 * 
	 * @param sid
	 *            凭证
	 * @param dmID
	 *            评论信息ID
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "countSales", method = RequestMethod.GET)
	public void countSales(@RequestHeader("token") String sid, 
			ShopSales shopSales,
			HttpServletResponse response) throws IOException {
		try {
			//登陆检查
            checkLogin(sid, response);

            //统计
            Integer count = shopSalesService.countSales(shopSales);
            RespHandler.respOK(count,response);
        } catch (ServiceException se) {
            log.error("统计销量信息出错！", se);
            RespHandler.respError(RespMsg.countSales_error, response);
        } catch (Exception e) {
            log.error("统计销量信息出现系统错误！", e);
            RespHandler.respServerError(response);
        }
	}
	
    /**
     * 添加便利店销量时，数据验证
     *
     * @param shopSales 便利店销量信息
     * @throws ServiceException
     */
    private void checkVo(ShopSales shopSales) throws ServiceException {
        if (null == shopSales.getShopId()) {
            throw new ServiceException(ExceptionCode.SHOP_ID_IS_NULL, "便利店ID为空！");
        }
        if (null == shopSales.getShopGoodsId()) {
            throw new ServiceException("商品ID为空！");
        }
        if (null == shopSales.getShopSkuId()) {
            throw new ServiceException("SkuID为空！");
        }
        if (null == shopSales.getSales()) {
        	throw new ServiceException("销售量为空！");
        }

    }

}
