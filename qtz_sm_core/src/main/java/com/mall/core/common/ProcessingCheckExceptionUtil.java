package com.mall.core.common;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>Title:ProcessingCheckExceptionUtil</p>
 * <p>Description:(spring异常处理类)</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 深圳市爱免费信息科技有限公司</p>
 * @author 唐礼军 - tanlijun
 * @version v1.0 2016年4月7日
 */
public class ProcessingCheckExceptionUtil {

	public static JSONArray abnormalConversion(List<FieldError> list)
	{
		JSONArray result = null;
		if (null != list && list.size() > 0) {
			result= new JSONArray();
			for (FieldError error : list) {
				ObjectError er = new ObjectError(error.getField(), error.getDefaultMessage());
				result.add(er);
			}
		}
		return result;
	}
}
