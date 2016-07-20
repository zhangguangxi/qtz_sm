package utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.mall.core.exception.ServiceException;

/**
 * 对象验证器
 * 
 */
public class BeanValidator {
	//获得验证器
    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    
    /**
     * 验证某个bean的参数
     * 
     * @param object 被校验的参数
     * @throws ServiceException 如果参数校验不成功则抛出此异常
     */
    public static <T> void validate(T object, Class<?>... groups) throws ServiceException {
    	//执行验证
    	Set<ConstraintViolation<T>> constraintViolations = validator.validate(object, groups);
    	//如果有验证信息，则将第一个取出来包装成异常返回
    	if(constraintViolations == null || constraintViolations.size() == 0){
    		return ;
    	}
    	ConstraintViolation<T> constraintViolation = null;
    	for (ConstraintViolation<T> item : constraintViolations) {
    		constraintViolation = item;
    		break;
    	}
    	
    	if (constraintViolation != null) {
    		String msg = constraintViolation.getMessage();
    		String[] errArr = msg.split(":");
    		int code = -1;
    		if(errArr.length == 2){
    			code = Integer.valueOf(errArr[0]);
    			msg = errArr[1];
    		}
    		throw new ServiceException(code, constraintViolation.getPropertyPath() + msg);
    	}
    }
    
}