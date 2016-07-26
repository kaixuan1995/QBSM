package com.qbsm.web.formbean;

/**
 * 登陆界面视图
 */

public class LoginFormBean {
	private String user_name; // 用户名
	private String user_password; // 密码

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	@Override
	public String toString() {
		return "LoginFormBean [user_name=" + user_name + ", user_password="
				+ user_password + "]";
	}
	
	

}
