package com.qbsm.service.util.parseUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.qbsm.service.annotation.ValidateRule;


/**
 * 
 * @author 刘欣
 */
@SuppressWarnings("all")
public class ValidateAnnotationUtil {
	/**
	 * 得到字段的ValidateRule注解
	 * @param field
	 * @return
	 */
	private static Annotation getAnnotation(Field field){
		return field.getAnnotation(ValidateRule.class);
	}
	
	/**
	 * 得到ValidateRule注解里面的maxLength
	 * @param className
	 * @return
	 */
	public static int getMaxLength(Field field) {
		ValidateRule validateRule = (ValidateRule) getAnnotation(field);
		if(validateRule != null) {
			return validateRule.maxLength();
		}else {
			return -1;
		}
	}
	
	/**
	 * 得到ValidateRule注解里面的minLength
	 * @param className
	 * @return
	 */
	public static int getMinLength(Field field) {
		ValidateRule validateRule = (ValidateRule) getAnnotation(field);
		if(validateRule != null) {
			return validateRule.minLength();
		}else {
			return -1;
		}
	}
	
	/**
	 * 得到ValidateRule注解里面的minLength
	 * @param className
	 * @return
	 */
	public static boolean getEmail(Field field) {
		ValidateRule validateRule = (ValidateRule) getAnnotation(field);
		if(validateRule != null){
			return validateRule.email();
		}else {
			return false;
		}
	}
	
//	public static boolean getDate(Field field){
//		ValidateRule validateRule = (ValidateRule) getAnnotation(field);
//		if(validateRule != null){
//			return validateRule.date();
//		}else {
//			return false;
//		}
//	}
	
	public static boolean getIsNull(Field field) {
		ValidateRule validateRule = (ValidateRule) getAnnotation(field);
		if(validateRule != null){
			return validateRule.noNull();
		}else {
			return false;
		}
	}
	
}
