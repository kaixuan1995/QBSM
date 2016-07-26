package com.qbsm.service.menberInfoService;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.qbsm.bean.User;
import com.qbsm.service.ServiceFactory;

public class Test_MenberInfoServiceImpl {

	public static void main(String[] args) {
		
		User u = new User();
		u.setUser_name("1111");
		new ServiceFactory().getService("MenberInfoServiceImpl.login",u);
	}
	
}
