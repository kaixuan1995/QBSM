//package com.qbsm.service.util;
//
//import java.lang.reflect.Method;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//
//import com.qbsm.dao.core.QueryDao;
//import com.qbsm.service.exception.DateException;
//
//
//public class QueryDaoProxy implements MethodInterceptor {
//	
//	private QueryDao queryDao = new QueryDao();
//
//	public Object getQueryDao() {
//		Enhancer enhancer = new Enhancer();
//		enhancer.setSuperclass(queryDao.getClass());
//		// 设置回调函数
//		enhancer.setCallback(this);
//		return enhancer.create();
//	}
//
//	public Object intercept(Object obj, Method method, Object[] args,
//			MethodProxy methodProxy) throws Throwable {
//		Object object = null;
//		try{
//			queryDao.openConnection();
//			object = method.invoke(queryDao, args);
//		}catch(Exception e) {
//			throw new DateException("QueryDaoProxy代理异常");
//		}finally{
//			queryDao.closeConnection();
//		}
//		return object;
//	}
//	
//	
//}
