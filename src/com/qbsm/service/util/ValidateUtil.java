package com.qbsm.service.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import com.qbsm.bean.User;
import com.qbsm.service.annotation.MethodEnum;
import com.qbsm.service.exception.ParseException;
import com.qbsm.service.util.parseUtils.ValidateAnnotationUtil;

@SuppressWarnings("all")
public class ValidateUtil {

	public static String validate(Object obj) {
		ValidateUtil validateUtil = new ValidateUtil();
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		boolean boo = true;
		for(int i = 0;i<fields.length && boo ;i++) {
			if(!validateUtil.validateEmail(obj, fields[i])) {
				return "20003";
			}
			if(!validateUtil.validateLength(obj, fields[i])){
				return "20002";
			}
			if(!validateUtil.validateNull(obj, fields[i])){
				return "20006";
			}
			if(validateUtil.validateEmail(obj, fields[i]) && 
					validateUtil.validateLength(obj, fields[i]) &&
					validateUtil.validateNull(obj, fields[i])) {
				boo = true;
			}else {
				boo =  false;
			}
		}
		return "00000";
	}
	
	/**
	 * 验证email格式是否符合
	 * @param obj
	 * @param field
	 * @return
	 */
	private boolean validateEmail(Object obj,Field field){
		boolean isEmail = ValidateAnnotationUtil.getEmail(field);
		if(isEmail) {
			String checkEmial = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			String fieldValue = (String)getValue(obj,field);
			if(fieldValue == null) {
				return false;
			}
			Pattern pattern = Pattern.compile(checkEmial);
			return pattern.matcher(fieldValue).matches();
		}else {
			return true;
		}
	}
	
	
	public static void main(String[] args) {
		User user = new User();
		user.setUser_email("819537628@qq.com");
		user.setUser_id(5555554);
		user.setOffice_id_fk(11);
		String uss = ValidateUtil.validate(user);
		System.out.println(MessageUtil.getMessage(uss));
	}
	
	
	/**
	 * 验证长度是否符合
	 * @param obj
	 * @param field
	 * @return
	 */
	private boolean validateLength(Object obj,Field field) {
		int maxLength = ValidateAnnotationUtil.getMaxLength(field);
		int minLength = ValidateAnnotationUtil.getMinLength(field);
		if(maxLength != -1 && minLength != -1) {
			String fieldValue = getValue(obj,field)+"";
			if(fieldValue.length()>= minLength && fieldValue.length() <= maxLength) {
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 验证是否为空值
	 * @param obj
	 * @param field
	 * @return
	 */
	private boolean validateNull(Object obj,Field field) {
		boolean notNull = ValidateAnnotationUtil.getIsNull(field);
		if(notNull) {
			Object value = getValue(obj,field);
			if(value != null) {
				return true;
			}else{
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * 得到字段的内容
	 * @param obj
	 * @param field
	 * @return
	 */
	private Object getValue(Object obj,Field field){
		String fieldName = field.getName();
		String methodName = FiledName2MethodName(fieldName,MethodEnum.get);
		Method method = null;
		try {
			Class clazz = obj.getClass();
			method = clazz.getMethod(methodName);
			return method.invoke(obj);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * @author 刘欣
	 * @param filedName
	 *            :类中属性的名称
	 * @param method
	 *            :使用com.xingyao.www.enum里面的Method
	 * @return:返回该方法名 该方法用于得到该属性的get或set方法，其中method是传你需要的方法("get","set")
	 */
	private String FiledName2MethodName(String filedName, Enum method) {
		// 将属相中第一个字母传化成大写
		String str = filedName.replaceFirst(filedName.substring(0, 1),
				filedName.substring(0, 1).toUpperCase());
		// 拼接成set,get方法
		String methodName = method.toString();
		// 返回方法值
		return methodName + str;
	}
	
	
}
