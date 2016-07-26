package com.qbsm.dao.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.lang.StringUtils;

import com.qbsm.dao.annotatioin.AnnotationUtil;


/**
 * @title BeanUtils
 * @description 1，兼容基本类型与包装类型 2，兼容不规范setter 3，支持copy类型检测
 */
@SuppressWarnings("rawtypes")
public class ResetBeanUtils extends BeanUtils {

	private static final String SETTER_PREFIX = "set";

	public static List copy(List<Map> list, Class clazz) {
		if (list == null || clazz == null) {
			return null;
		}
		List<Object> result = new ArrayList<Object>();
		Map nameMap = AnnotationUtil.NameMapping(clazz);
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		PropertyDescriptor[] propertyDescriptors = getPropertyDescriptor(clazz);
		try {
			for (int i = 0; i < list.size(); i++) {
				Object obj = clazz.newInstance();
				Map map = list.get(i);
				for (int j = 0; j < propertyDescriptors.length; j++) {
					PropertyDescriptor descriptor = propertyDescriptors[j];
					String propertyName = descriptor.getName();
					if (map.containsKey(nameMap.get(propertyName))) {
						Object propertyValue = map.get(nameMap.get(propertyName));
						if (propertyValue != null && !propertyValue.toString().trim().equalsIgnoreCase("")) {
							setProperty(obj, propertyName, propertyValue);
						}
					}
				}
				result.add(obj);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 判断在clz类中是否含有名为propertyName的属性
	 * 
	 * @param clz
	 * @param propertyName
	 * @return
	 */
	public static boolean isHaveFiled(Class clz, String propertyName) {
		if (clz == null) {
			return false;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);
			if (beanInfo != null) {
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor prop : props) {
					if (prop.getName().equals(propertyName)) {
						return true;
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static PropertyDescriptor[] getPropertyDescriptor(Class clazz) {
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);// 获取类属性
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		return propertyDescriptors;
	}

	public static void copy(Object source, Object target) {
		try {
			// 基于内省机制获取bean的属性
			BeanInfo targetBeanInfo = Introspector.getBeanInfo(target.getClass());
			// 根据getter来判断有哪些属性
			PropertyDescriptor[] targetProps = targetBeanInfo.getPropertyDescriptors();
			for (PropertyDescriptor targetProp : targetProps) {
				try {
					// 获取source与target相同name的属性
					PropertyDescriptor sourceProp = getPropertyDescriptor(source.getClass(), targetProp.getName());
					Method targetWriteMethod = targetProp.getWriteMethod();
					if (targetWriteMethod == null) {
						// 如果不存在标准的setter，可能存在public xxx(非void)
						// setXXX方法（譬如thrift生成的setter）
						MethodDescriptor methodDesc = getMethodDescriptor(target.getClass(), SETTER_PREFIX
								+ targetProp.getName().substring(0, 1).toUpperCase() + targetProp.getName().substring(1));
						if (methodDesc != null) {
							targetWriteMethod = methodDesc.getMethod();
						}
					}
					// 如果target具有setter方法，并且source具有getter方法
					if (targetWriteMethod != null && sourceProp != null && sourceProp.getReadMethod() != null) {
						Object value = getValue(sourceProp.getReadMethod(), source, new Object[0]);
						if (value != null) {
							// 判断getter与setter的参数类型是否匹配，否则容易报IllegalArgumentException:
							// argument type mismatch
							if (value.getClass() == targetWriteMethod.getParameterTypes()[0]) {
								setValue(targetWriteMethod, target, new Object[] { value });
							} else if (value.getClass().isPrimitive() || targetWriteMethod.getParameterTypes()[0].isPrimitive()) {
								setValue(targetWriteMethod, target, new Object[] { value });
							}
						}
					}
				} catch (Exception e) {
					continue;
				}
			}
		} catch (Throwable ex) {
		}
	}

	private static Object getValue(Method method, Object obj, Object[] args) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if (method == null || obj == null) {
			return null;
		}
		if (!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			method.setAccessible(true);
		}
		return method.invoke(obj, args);
	}

	private static void setValue(Method method, Object obj, Object[] args) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if (method == null || obj == null) {
			return;
		}
		if (!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			method.setAccessible(true);
		}
		method.invoke(obj, args);
	}

	private static MethodDescriptor getMethodDescriptor(Class<?> clz, String methodName) throws IntrospectionException {
		if (clz == null || StringUtils.isBlank(methodName)) {
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(clz);
		if (beanInfo != null) {
			MethodDescriptor[] methods = beanInfo.getMethodDescriptors();
			for (MethodDescriptor method : methods) {
				if (method.getName().equals(methodName)) {
					return method;
				}
			}
		}
		return null;
	}

	private static PropertyDescriptor getPropertyDescriptor(Class<?> clz, String propertyName) throws IntrospectionException {
		if (clz == null) {
			return null;
		}
		BeanInfo beanInfo = Introspector.getBeanInfo(clz);
		if (beanInfo != null) {
			PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor prop : props) {
				if (prop.getName().equals(propertyName)) {
					return prop;
				}
			}
		}
		return null;
	}

}
