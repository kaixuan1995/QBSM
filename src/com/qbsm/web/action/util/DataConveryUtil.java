package com.qbsm.web.action.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataConveryUtil {
	/**
	 * 将rsValue数据转成clazz对应的类型
	 * 
	 * @param clazz
	 * @param rsValue
	 * @return
	 */
	public static Object castValue(Class<?> clazz, Object rsValue) {
		if (rsValue == null) {
			return null;
		} else {
			if (clazz == Byte.class) {
				return Byte.parseByte(rsValue.toString());
			} else if (clazz == int.class || clazz == Integer.class) {
				return Integer.parseInt(rsValue.toString());
			} else if (clazz == float.class || clazz == Float.class) {
				return Float.parseFloat(rsValue.toString());
			} else if (clazz == double.class || clazz == Double.class) {
				return Double.parseDouble(rsValue.toString());
			} else if (clazz == long.class || clazz == Long.class) {
				return Long.parseLong(rsValue.toString());
			} else if (clazz == String.class) {
				return rsValue;
			} else if (clazz == boolean.class || clazz == Boolean.class) {
				return Boolean.parseBoolean(rsValue.toString());
			} else if (clazz == Date.class) {
				Date ddate = null;
				try {
					ddate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
							.parse(rsValue.toString());
				} catch (Exception e1) {
					try {
						ddate = new SimpleDateFormat("yyyy-MM-dd HH:mm")
								.parse(rsValue.toString());
					} catch (Exception e2) {
						try {
							ddate = new SimpleDateFormat("yyyy-MM-dd")
									.parse(rsValue.toString());
						} catch (Exception e3) {
							return null;
						}
					}
				}
				return ddate;
			} else {
				System.out.println("警告：该方法中存在对象中未定义的数据类型");
				return null;
			}
		}
	}
}
