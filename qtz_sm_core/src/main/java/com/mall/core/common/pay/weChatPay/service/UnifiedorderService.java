package com.mall.core.common.pay.weChatPay.service;

import com.mall.core.common.pay.weChatPay.common.Configure;
import com.mall.core.common.pay.weChatPay.protocol.unifiedorder_protocol.UnifiedorderReqData;

/**
 * 
 * <p>Title:UnifiedorderService</p>
 * <p>Description:(统一下单服务)</p>
 * <p>Copyright: Copyright (c) 2015</p>
 * <p>Company: 深圳市擎天柱信息科技有限公司</p>
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年10月10日
 */
public class UnifiedorderService extends BaseService{

	public UnifiedorderService(Configure configure) throws ClassNotFoundException,
			IllegalAccessException, InstantiationException {
		super(Configure.UNIFIEDORDER_API,configure);
	}
	/**
	 * 
	  * 【请求】
	  * @param unifiedorderReqData			请求数据
	  * @return								xml数据
	  * @throws Exception  
	  * @time:2015年10月10日 下午4:59:33
	  * @author 涂鑫
	  * @version
	 */
	public String request(UnifiedorderReqData unifiedorderReqData)throws Exception{
		String responseString = sendPost(unifiedorderReqData);
		return responseString;
	}
}
