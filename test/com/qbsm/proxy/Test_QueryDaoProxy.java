package com.qbsm.proxy;

import java.lang.reflect.Proxy;

import com.qbsm.dao.core.QueryDao;
import com.qbsm.service.menberInfoService.MenberInfoService;
import com.qbsm.service.menberInfoService.MenberInfoServiceImpl;



public class Test_QueryDaoProxy {

	public static void main(String[] args) {
		QueryDaoProxy queryDaoProxy = new QueryDaoProxy();
		MenberInfoService m = new MenberInfoServiceImpl();
//		QueryDao queryDao = QueryDao.getQueryDao();
		queryDaoProxy.setObj(m);
		MenberInfoService uu = (MenberInfoService) Proxy.newProxyInstance(m.getClass().getClassLoader(),m.getClass().getInterfaces(),queryDaoProxy);
	}
}
