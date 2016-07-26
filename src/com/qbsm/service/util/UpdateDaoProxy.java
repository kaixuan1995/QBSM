//package com.qbsm.service.util;
//
//import java.lang.reflect.Method;
//
//import net.sf.cglib.proxy.Enhancer;
//import net.sf.cglib.proxy.MethodInterceptor;
//import net.sf.cglib.proxy.MethodProxy;
//
//import com.qbsm.dao.TransactionManager;
//import com.qbsm.dao.core.UpdateDao;
//import com.qbsm.service.exception.DateException;
//
//public class UpdateDaoProxy implements MethodInterceptor {
//
//	private UpdateDao updateDao = new UpdateDao();
//	
//	public Object getUpdateDao() {
//		Enhancer enhancer = new Enhancer();
//		enhancer.setSuperclass(updateDao.getClass());
//		// 设置回调函数
//		enhancer.setCallback(this);
//		return enhancer.create();
//	}
//
//	public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//		TransactionManager transactionManager = null;
//		Object object = null;
//		try {
//			updateDao.openConnection();
//			transactionManager = updateDao.getTransactionManager();
//			transactionManager.startTransaction();
//			object = method.invoke(updateDao, args);
//			transactionManager.commit();
//		} catch (Exception e) {
//			transactionManager.rollback();
//			throw new DateException("UpdateDaoProxy代理异常");
//		} finally {
//			updateDao.closeConnection();
//		}
//		return object;
//	}
//
//}
