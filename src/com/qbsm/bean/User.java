package com.qbsm.bean;


import com.qbsm.dao.annotatioin.ForeignColumn;
import com.qbsm.dao.annotatioin.PrimaryColumn;
import com.qbsm.dao.annotatioin.Table;
import com.qbsm.service.annotation.ValidateRule;

@Table("t_user")
public class User {

	// 用户表
	@PrimaryColumn("user_id")
	@ValidateRule(minLength=6,maxLength=12)
	private Integer user_id; // 用户id

	@ForeignColumn(ColumnName = "office_id", Table = "t_office")
	@ValidateRule(noNull=true)
	private Integer office_id_fk; // 所属科室

	@ForeignColumn(ColumnName = "role_id", Table = "t_role")
	private Integer role_id_fk; // 所属角色

	private String user_name; // 用户名称
	
	@ValidateRule(email=true)
	private String user_email; //邮箱
	
	private String user_password; // 用户密码
	private String user_address; 			// 用户地址
	private String user_lianxiren; 			// 供应商的联系人
	private String user_phone; 				// 手机号码
	private String user_fax; 				// 传真
	private String user_createtime; 		//用户创建时间
	private String user_lastlogintime;		//最后登陆时间
	private String user_lastloginip;		//最后登陆ip地址
	private Integer user_isdel; 			//是否物理删除
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getOffice_id_fk() {
		return office_id_fk;
	}
	public void setOffice_id_fk(Integer office_id_fk) {
		this.office_id_fk = office_id_fk;
	}
	public Integer getRole_id_fk() {
		return role_id_fk;
	}
	public void setRole_id_fk(Integer role_id_fk) {
		this.role_id_fk = role_id_fk;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_lianxiren() {
		return user_lianxiren;
	}
	public void setUser_lianxiren(String user_lianxiren) {
		this.user_lianxiren = user_lianxiren;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_fax() {
		return user_fax;
	}
	public void setUser_fax(String user_fax) {
		this.user_fax = user_fax;
	}
	public String getUser_createtime() {
		return user_createtime;
	}
	public void setUser_createtime(String user_createtime) {
		this.user_createtime = user_createtime;
	}
	public String getUser_lastlogintime() {
		return user_lastlogintime;
	}
	public void setUser_lastlogintime(String user_lastlogintime) {
		this.user_lastlogintime = user_lastlogintime;
	}
	public String getUser_lastloginip() {
		return user_lastloginip;
	}
	public void setUser_lastloginip(String user_lastloginip) {
		this.user_lastloginip = user_lastloginip;
	}
	public Integer getUser_isdel() {
		return user_isdel;
	}
	public void setUser_isdel(Integer user_isdel) {
		this.user_isdel = user_isdel;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", office_id_fk=" + office_id_fk
				+ ", role_id_fk=" + role_id_fk + ", user_name=" + user_name
				+ ", user_email=" + user_email + ", user_password="
				+ user_password + ", user_address=" + user_address
				+ ", user_lianxiren=" + user_lianxiren + ", user_phone="
				+ user_phone + ", user_fax=" + user_fax + ", user_createtime="
				+ user_createtime + ", user_lastlogintime="
				+ user_lastlogintime + ", user_lastloginip=" + user_lastloginip
				+ ", user_isdel=" + user_isdel + "]";
	}

	

}