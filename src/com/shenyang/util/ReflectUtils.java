package com.shenyang.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;

public class ReflectUtils {
	public static Object invokeGetMethod(Object obj,Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Object value = method.invoke(obj, null);
		return value;
	}
	/**
	 * 执行set方法
	 * @param 对象
	 * @param 方法名称
	 * @param 参数
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void invokeSetMethod(Object obj,Method method,Object param) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		if(param instanceof BigDecimal){
			param = ((BigDecimal) param).intValue();
		}
		method.invoke(obj, param);
	}
}
