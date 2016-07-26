package com.qbsm.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import com.qbsm.service.util.parseUtils.ServiceXMLUtil;

@SuppressWarnings("all")
public class ServiceFactory {
	//得到service对象，并执行方法
	public List<?> getService(String serviceXMLName,Object...objects){
		//得到service方法里面参数的Class对象
		Class[] clazz = getClasses(objects);
		//得到service名称
		String serviceImplName = ServiceXMLUtil.getServiceImplName(serviceXMLName);
		//得到service的方法名称
		String mehthodName = ServiceXMLUtil.getMethodName(serviceXMLName);
		try {
			//得到service的实例
			Object service = Class.forName(serviceImplName).newInstance();
			//通过方法名，得到方法的实例
			Method serviceMethod = service.getClass().getDeclaredMethod(mehthodName, clazz);
			//执行方法，得到方法的返回值
			return (List<?>) serviceMethod.invoke(service, objects);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private Class[] getClasses(Object...objects){
		//定义一个Class数组，保存方法参数的Class对象
		Class[] clazz = new Class[objects.length];
		//参数对象的名称
		for(int i = 0;i<objects.length;i++) {
			String argClassName = objects[i].getClass().getName();
			if("java.lang.Integer".equals(argClassName)) {
				clazz[i] = int.class;
			}else if("java.lang.Boolean".equals(argClassName)){
				clazz[i] = boolean.class;
			}else if("java.lang.String".equals(argClassName)) {
				clazz[i] = String.class;
			}else if("java.lang.Char".equals(argClassName)){
				clazz[i] = char.class;
			}else if("java.util.ArrayList".equals(argClassName)) {
				clazz[i] = List.class;
			}else if("java.lang.Date".equals(argClassName)) {
				clazz[i] = Date.class;
			}else {
				clazz[i] = objects[i].getClass();
			}
		}
		return clazz;
	}
}
