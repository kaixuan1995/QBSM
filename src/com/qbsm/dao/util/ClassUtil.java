package com.qbsm.dao.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@SuppressWarnings("rawtypes")
public class ClassUtil {

	/**
	 * 获得类路径
	 * 
	 * @param clazz
	 * @return
	 */
	public String getClassPath(Class clazz) {
		return clazz.getClassLoader().getResource("/").getPath();
	}

	/**
	 * 获得所有的对象对应的字节码文件
	 * 
	 * @param objects
	 * @return
	 */
	public static Class[] getClass(Object[] objects) {
		Class[] clazzs = new Class[objects.length];
		for (int i = 0; i < objects.length; i++) {
			clazzs[i] = objects[i].getClass();
		}
		return clazzs;
	}

	/**
	 * 判断一个类是否为基本数据类型。
	 * 
	 * @param clazz
	 *            要判断的类。
	 * @return true 表示为基本数据类型。
	 */
	public static boolean isBaseDataType(Class clazz) {
		return (clazz.equals(String.class) || clazz.equals(Integer.class) || clazz.equals(Byte.class) || clazz.equals(Long.class)
				|| clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class)
				|| clazz.equals(BigDecimal.class) || clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || clazz.equals(Date.class) || clazz
					.isPrimitive());
	}
}
